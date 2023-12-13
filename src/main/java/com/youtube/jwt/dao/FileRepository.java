package com.youtube.jwt.dao;

import com.youtube.jwt.entity.FileEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FileRepository extends JpaRepository<FileEntity,String> {
}
