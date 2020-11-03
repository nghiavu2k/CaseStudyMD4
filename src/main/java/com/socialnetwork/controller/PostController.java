package com.socialnetwork.controller;

import com.socialnetwork.model.Post;
import com.socialnetwork.service.post.PostService;
import com.socialnetwork.service.user.UserService;
import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import org.cloudinary.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.Map;

@Controller
@RequestMapping("/post")
public class PostController {
    @Autowired
    UserService userService;


    private String getPrincipal() {
        String userName;
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (principal instanceof UserDetails) {
            userName = ((UserDetails) principal).getUsername();
        } else {
            userName = principal.toString();
        }
        return userName;
    }



    //tao link duong dan cloud de luu anh
    final String CLOUDINARY_URL = "cloudinary://164245624698981:tS41AaGhjV1iRXZJ7zKPSYIqPvo@nghia169";
    @Autowired
    PostService postService;

    @GetMapping("")
    public String getAll(Model model) {
        Iterable<Post> posts = postService.findAll();
        model.addAttribute("posts", posts);
        return "";
    }

    @GetMapping("/create")
    public String showCreateForm(Model model) {
        model.addAttribute("post", new Post());
        return "profile";
    }

    @PostMapping("/create")
    public String savePost(Post post) {
        //xử lý lưu ảnh với cloud để trả về linkImg kiểu String xong lưu Post
        MultipartFile multipartFile = post.getImage();
        Cloudinary cloudinary = new Cloudinary(CLOUDINARY_URL);
        try {
            File imgFile = File.createTempFile("temp", multipartFile.getOriginalFilename()).toPath().toFile();
            multipartFile.transferTo(imgFile);
            Map responseMp3 = cloudinary.uploader().upload(imgFile, ObjectUtils.emptyMap());
            JSONObject jsonObject = new JSONObject(responseMp3);
            String urlImg = jsonObject.getString("url");
            System.out.println(urlImg);
            post.setLinkImage(urlImg);
            post.setAuthor(userService.getCurrentUser());
        } catch (IOException e) {
            e.printStackTrace();
        }
        postService.save(post);
        return "redirect:/profile";
    }

    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable("id") Long id, Model model) {
        Post post = postService.findById(id).get();
        model.addAttribute("post", post);
        return "";
    }

    @PostMapping("/edit/{id}")
    public String editPost(Post post) {
        //xử lý lưu ảnh với cloud để trả về linkImg kiểu String xong lưu Post
        MultipartFile multipartFile = post.getImage();
        Cloudinary cloudinary = new Cloudinary(CLOUDINARY_URL);
        try {
            File imgFile = File.createTempFile("test", multipartFile.getOriginalFilename()).toPath().toFile();
            multipartFile.transferTo(imgFile);
            Map responseMp3 = cloudinary.uploader().upload(imgFile, ObjectUtils.emptyMap());
            JSONObject jsonObject = new JSONObject(responseMp3);
            String urlImg = jsonObject.getString("url");
            post.setLinkImage(urlImg);
        } catch (IOException e) {
            e.printStackTrace();
            e.getMessage();
        }
        postService.save(post);
        return "";
    }

    @GetMapping("/delete/{id}")
    public String deletePost(@PathVariable("id") Long id) {
        postService.remove(postService.findById(id).get());
        return "redirect:/profile";
    }
}
