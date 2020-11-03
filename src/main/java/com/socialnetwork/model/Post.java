package com.socialnetwork.model;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Data
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String caption;
    private Timestamp createdTime;
    private Timestamp modifiedTime;
    @ManyToOne
    private User author;
    private String notification;
    @Column(columnDefinition = "TEXT")
    private String linkImage;
    @Transient
    private MultipartFile image;
}
