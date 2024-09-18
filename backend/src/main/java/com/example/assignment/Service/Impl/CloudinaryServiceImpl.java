package com.example.assignment.Service.Impl;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import com.example.assignment.Errors.InternalServerError;
import com.example.assignment.Service.CloudinaryService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.Map;

@Service
public class CloudinaryServiceImpl implements CloudinaryService {
    @Autowired
    private Cloudinary cloudinary;
    private static final Logger logs = LoggerFactory.getLogger(CloudinaryServiceImpl.class);
    @Override
    public String uploadMediaFile(MultipartFile file, String fileName) {
        try {
            logs.info("Uploading Data into Cloudinary");
            Map<String, String> uploadData = cloudinary.uploader().upload(file.getBytes(),
                    ObjectUtils.asMap("resource_type", "auto",
                            "context", "custom_name"+ fileName));
            logs.info("Data Uploaded Successfully");
            return uploadData.get("secure_url").toString();

        }catch (Exception exception){
            logs.warn("cloudinary upload error");
            throw new InternalServerError("Cloudinary Upload Error");
        }
    }
}
