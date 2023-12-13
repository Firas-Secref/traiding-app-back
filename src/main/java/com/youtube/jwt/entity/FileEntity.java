package com.youtube.jwt.entity;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity
@Table(name = "files")
public class FileEntity {
    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid",strategy = "uuid2")
    @Column(length = 100)
    private String id;
    @Column(name = "name")
    private String name;
    @Column(name = "type")
    private String type;
    @Lob
    private byte[] data;
    public FileEntity(){}
    public FileEntity(String name,String type,byte[] data){
        this.name=name;
        this.type=type;
        this.data=data;
    }
    public String getId(){
        return id;
    }

    public String getType() {
        return type;
    }

    public byte[] getData() {
        return data;
    }

    public String getName() {
        return name;
    }
}
