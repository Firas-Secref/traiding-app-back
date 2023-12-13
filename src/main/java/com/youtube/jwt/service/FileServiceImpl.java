package com.youtube.jwt.service;

import com.youtube.jwt.dao.FileRepository;
import com.youtube.jwt.entity.FileEntity;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Optional;
import java.util.stream.Stream;

@Service
@AllArgsConstructor
public class FileServiceImpl {

    private FileRepository fileRepository;
    public FileEntity saveFile(MultipartFile file) throws IOException {
        String fileName= StringUtils.cleanPath(file.getOriginalFilename());
        FileEntity fileData = new FileEntity(fileName,file.getContentType(),file.getBytes());
        return fileRepository.save(fileData);
    }
    public FileEntity getFile(String id){
        return fileRepository.findById(id).get();
    }
    public Stream<FileEntity> getAllFiles(){
        return fileRepository.findAll().stream();
    }
    public void deleteFile(String id) throws FileNotFoundException {
        Optional<FileEntity> fileOptional = fileRepository.findById(id);
        if (fileOptional.isPresent()) {
            FileEntity fileData = fileOptional.get();
            fileRepository.delete(fileData);
        } else {
            throw new FileNotFoundException("File not found with ID: " + id);
        }
    }
}
