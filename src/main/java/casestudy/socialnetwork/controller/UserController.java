package casestudy.socialnetwork.controller;

import casestudy.socialnetwork.service.post.PostService;
import casestudy.socialnetwork.model.Post;
import casestudy.socialnetwork.model.User;
import casestudy.socialnetwork.service.user.UserService;
import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import org.cloudinary.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import java.io.File;
import java.io.IOException;
import java.util.Map;

@Controller
public class UserController {

    final String CLOUDINARY_URL = "cloudinary://164245624698981:tS41AaGhjV1iRXZJ7zKPSYIqPvo@nghia169";
    @Autowired
    private UserService userService;

    @Autowired
    private PostService postService;

    private String getPrincipal(){
        String userName;
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (principal instanceof UserDetails) {
            userName = ((UserDetails)principal).getUsername();
        } else {
            userName = principal.toString();
        }
        return userName;
    }

    @GetMapping("/")
    public String loginPage(){
        return "sign-in-page";
    }

//    @RequestMapping("/admin")
//    public String adminPage(){
//        return "admin";
//    }

    @GetMapping("/profile")
    public String getProfile(Model model){
        model.addAttribute("user", userService.getCurrentUser());
        model.addAttribute("posts",postService.findAllByAuthor(userService.getCurrentUser()));
        for(Post p : postService.findAllByAuthor(userService.getCurrentUser())){
            System.out.println(p.getCreatedTime());
            System.out.println("-----------");
        }
        System.out.println(userService.getCurrentUser());
        return "profile";
    }

    @PostMapping("/sign-up")
    public ModelAndView signUp(User user) {
        ModelAndView mav = new ModelAndView("/sign-in-page");
        mav.addObject("user", user);
        String role = "ROLE_USER";
        user.setRoles(role);
        user.setActive(true);
        MultipartFile avatar = user.getImage();
        Cloudinary cloudinary = new Cloudinary(CLOUDINARY_URL);
        try {
            File imgFile = File.createTempFile("temp", avatar.getOriginalFilename()).toPath().toFile();
            avatar.transferTo(imgFile);
            Map responseMp3 = cloudinary.uploader().upload(imgFile, ObjectUtils.emptyMap());
            JSONObject jsonObject = new JSONObject(responseMp3);
            String urlImg = jsonObject.getString("url");
            System.out.println(urlImg);
            user.setLinkImage(urlImg);
        } catch (IOException e) {
            e.printStackTrace();
        }
        for (User user1 : userService.findAll()){
            if (user1.getUserName().equals(user.getUserName())) return mav.addObject("");
        }
        userService.save(user);
        return mav;
    }

    @GetMapping("/no-door")
    public String accessDenied() {
        return "nodoor";
    }

//    @GetMapping("/profile")
//    public  String profile(){
//        return
//                "profile";
//    }
}