package com.self.portfolio.service;

import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public interface ResourceService {
    
    public ResponseEntity<Resource> downloadImage();
}