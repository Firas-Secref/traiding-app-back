package com.youtube.jwt.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Course implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String courseName;
    private String description;
    private int price;
    private String level;
    @Lob
    @Column(columnDefinition = "MEDIUMBLOB")
    private byte[] courseFile;
    private String fileType;

    @OneToOne
    private Quiz2 quiz2;

}
