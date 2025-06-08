package vn.bacon.parking.service;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import jakarta.servlet.ServletContext;

@Service
public class UploadService {
    private final ServletContext servletContext;

    public UploadService(ServletContext servletContext) {
        this.servletContext = servletContext;
    }

    public String handleSaveUploadFile(MultipartFile file, String targetFolder) {
        // don't upload file
        if (file.isEmpty()) {
            return "";
        }
        
        String fileName = "";
        try {
            // Create base directory if it doesn't exist
            Path uploadPath = Paths.get("src/main/webapp/images", targetFolder);
            File dir = uploadPath.toFile();
            if (!dir.exists()) {
                dir.mkdirs();
            }

            // Create unique filename
            fileName = System.currentTimeMillis() + "-" + file.getOriginalFilename();
            
            // Create the file on server
            File serverFile = new File(dir.getAbsolutePath() + File.separator + fileName);
            try (BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(serverFile))) {
                stream.write(file.getBytes());
            }
        } catch (IOException e) {
            e.printStackTrace();
            return "";
        }
        return fileName;
    }
}
