package com.socialnetwork.model;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.*;

@Entity
@Data
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String userName;
    private String password;
    private boolean active;
    private String roles;
    private String address;
    private String email;
    private String fullName;
    @Transient
    private MultipartFile image;
    @Column(columnDefinition = "TEXT")
    private String linkImage;
}