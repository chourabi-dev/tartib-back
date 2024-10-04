package com.solidwall.tartib.controllers;
  
import org.hibernate.validator.constraints.UUID;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
 
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import java.util.HashMap; 
import java.io.*;

@Controller
@RequestMapping("project-intervetion")
public class ProjectIntervention {
    //String uploadDirectory = "uploads";
    String uploadDirectory = System.getProperty("user.dir") + File.separator + "uploads";

    @PostMapping("create")
    public ResponseEntity<?> postMethodName(
        @RequestParam("plan_travail_document") MultipartFile file,
        @RequestParam("cadre_logique_document") MultipartFile file2
        
        
        ) {


        // create the entity
        
        
        
        try { 
            // Create the uploads directory if it doesn't exist
            File uploadsDir = new File(uploadDirectory);
            if (!uploadsDir.exists()) {
                uploadsDir.mkdirs();
            }
 
                String originalFilename = file.getOriginalFilename();
                String uniqueID = String.valueOf(System.currentTimeMillis()); 
                String newFilename = uniqueID + "_" + originalFilename;  
 
                File uploadedFile = new File(uploadsDir, newFilename);
                file.transferTo(uploadedFile); 
            


                HashMap<String, String> res = new HashMap<String, String>();
                res.put("success", "true");
                res.put("filename", uploadedFile.getAbsolutePath() );

                return ResponseEntity.status(200).body(res);

                // update the entity with file name

            } catch (IOException e) {

               // file not uploaded
            }


       
        return ResponseEntity.status(200).body("ok");
       
    }
    
}
