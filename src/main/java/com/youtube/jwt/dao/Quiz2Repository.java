package com.youtube.jwt.dao;

import com.youtube.jwt.entity.Quiz2;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface Quiz2Repository extends JpaRepository<Quiz2, Integer> {
}
