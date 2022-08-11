package com.durgesh.controller;
import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.durgesh.model.Candidate;
import com.durgesh.model.User;
import com.durgesh.repository.CandidateRepository;
import com.durgesh.upload.CandidateFileUploadUtil;
 
@Controller
public class CandidateController {
	
	 @Autowired
	    private CandidateRepository candidateRepo;
	 
	 
	 
	 @GetMapping("/candidateForm")
	  
	    public String form(Model model) {
	    	
	    	Candidate candidate=new Candidate();
	    	model.addAttribute("candidate", candidate);
	    	return "candidate";
	    }
	     
	    @PostMapping("/upload_multiple")
	    public String handleFormSubmit(Candidate candidate,
	            @RequestParam("profilePictureFile") MultipartFile multipartFile1,
	            @RequestParam("photoIdFile") MultipartFile multipartFile2,
	            @RequestParam("documentFile") MultipartFile multipartFile3) throws IOException {
	         
	        String profilePictureFileName = StringUtils.cleanPath(multipartFile1.getOriginalFilename());
	        String photoIdFileName = StringUtils.cleanPath(multipartFile2.getOriginalFilename());
	        String documentFileName = StringUtils.cleanPath(multipartFile3.getOriginalFilename());
	         
	        candidate.setProfilePicture(profilePictureFileName);
	        candidate.setPhotoId(photoIdFileName);
	        candidate.setDocument(documentFileName);
	         
	        Candidate savedCandidate = candidateRepo.save(candidate);
	        String uploadDir = "candidates/" + savedCandidate.getId();
	         
	        CandidateFileUploadUtil.saveFile(uploadDir, profilePictureFileName, multipartFile1);
	        CandidateFileUploadUtil.saveFile(uploadDir, photoIdFileName, multipartFile2);
	        CandidateFileUploadUtil.saveFile(uploadDir, documentFileName, multipartFile3);
	         
	        return "message";
	    }

}
