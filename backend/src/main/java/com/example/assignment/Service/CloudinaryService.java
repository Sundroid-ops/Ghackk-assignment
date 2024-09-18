package com.example.assignment.Service;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public interface CloudinaryService {
    public String uploadMediaFile(MultipartFile file, String fileName);
}
