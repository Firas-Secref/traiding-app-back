package com.youtube.jwt.controller;

import com.youtube.jwt.common.ResponseData;
import com.youtube.jwt.common.ResponseMessage;
import com.youtube.jwt.entity.FileEntity;
import com.youtube.jwt.service.FileServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@Controller
@CrossOrigin("http://localhost:8085")
public class FileController {
    @Autowired
    private FileServiceImpl fileService;
    @PostMapping("/api/v1/upload")
    public ResponseEntity<ResponseMessage> uploadFile(@RequestParam("file") MultipartFile file) throws IOException {
        String message ="";
        try {
            fileService.saveFile(file);
            message="Uploded the file successfully: " +file.getOriginalFilename();
            return ResponseEntity.status(HttpStatus.OK).body(new ResponseMessage(message));
        }catch (Exception e){
            message="Could not upload the file successfully:"+file.getOriginalFilename()+"!";
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new ResponseMessage(message));

        }
    }
    @GetMapping("/api/v1/files")
    public ResponseEntity<List<ResponseData>> getListFiles(){
        List<ResponseData> files=fileService.getAllFiles().map(fileData ->{
            String fileDownloadUri= ServletUriComponentsBuilder
                    .fromCurrentContextPath().path("/api/v1/files/").path(fileData.getId())
                    .toUriString();
            return new ResponseData(fileData.getName(),fileDownloadUri,
                    fileData.getType(),fileData.getData().length);
        }).collect(Collectors.toList());
        return ResponseEntity.status(HttpStatus.OK).body(files);
    }
    @GetMapping("/api/v1/files/{id}")
    public ResponseEntity<byte[]> getFile(@PathVariable String id){
        FileEntity fileData  = fileService.getFile(id);
        return ResponseEntity.ok().header(HttpHeaders.CONTENT_DISPOSITION,"attachement;filename=\""+fileData.getName()+"\"")
                .body((byte[]) fileData.getData());

    }
    @GetMapping("/api/v1/files/download/{id}")
    public ResponseEntity<ByteArrayResource> downloadFile(@PathVariable String id) {
        FileEntity fileData = fileService.getFile(id);

        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(fileData.getType()))
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment;filename=\"" + fileData.getName() + "\"")
                .body(new ByteArrayResource(fileData.getData()));
    }
    @DeleteMapping("/api/v1/files/delete/{id}")
    public ResponseEntity<ResponseMessage> deleteFile(@PathVariable String id) {
        String message = "";
        try {
            // Supprimer le fichier en utilisant le service
            fileService.deleteFile(id);
            message = "File deleted successfully with ID: " + id;
            return ResponseEntity.status(HttpStatus.OK).body(new ResponseMessage(message));
        } catch (Exception e) {
            message = "Could not delete the file with ID: " + id + "!";
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new ResponseMessage(message));
        }
    }
}
