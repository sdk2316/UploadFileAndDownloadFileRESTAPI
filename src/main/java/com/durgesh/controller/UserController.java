package com.durgesh.controller;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.view.RedirectView;

import com.durgesh.image.FileUploadImageUtil;
import com.durgesh.model.User;
import com.durgesh.repository.UserRepository;

@Controller
public class UserController {
 
    @Autowired
    private UserRepository repo;
    
    
    @GetMapping("/imageform")
  
    public String form(Model model) {
    	
    	User user=new User();
    	model.addAttribute("user", user);
    	return "form";
    }
     
    @PostMapping("/users/save")
    public String saveUser(User user,
            @RequestParam("image") MultipartFile multipartFile) throws IOException {
         
        String fileName = StringUtils.cleanPath(multipartFile.getOriginalFilename());
        user.setPhotos(fileName);
         
        User savedUser = repo.save(user);
 
        String uploadDir = "user-photos/" + savedUser.getId();
 
        FileUploadImageUtil.saveFile(uploadDir, fileName, multipartFile);
         
        return "success";
    }
}