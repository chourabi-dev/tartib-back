package com.solidwall.tartib.controllers;


import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.File;
import java.io.IOException;

@RestController
@RequestMapping("uploads")
public class FilesAccessController {

    private final ResourceLoader resourceLoader;

    public FilesAccessController(ResourceLoader resourceLoader) {
        this.resourceLoader = resourceLoader;
    }

    @GetMapping("/get-file/{filename:.+}")
    public ResponseEntity<Resource> getFile(@PathVariable String filename) {
        try {
            // Define the path to the uploads directory
            String uploadDirectory = System.getProperty("user.dir") + File.separator + "uploads";
            File file = new File(uploadDirectory, filename);

            // Load the file as a Resource
            Resource resource = resourceLoader.getResource("file:" + file.getAbsolutePath());

            // Check if the file exists
            if (!resource.exists()) {
                return ResponseEntity.notFound().build();
            }

            // Return the file as a response entity with the correct headers
            return ResponseEntity.ok()
                    .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + resource.getFilename() + "\"")
                    .body(resource);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}
