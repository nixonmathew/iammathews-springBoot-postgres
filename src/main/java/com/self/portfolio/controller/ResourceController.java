package com.self.portfolio.controller;

import com.self.portfolio.service.ResourceService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin
@RequestMapping("/api/resource")
public class ResourceController {

    private static Logger LOGGER = LoggerFactory.getLogger(ResourceController.class);

    @Autowired
    private ResourceService resourceService;

    @GetMapping(value = "/download-image")
    private ResponseEntity<Resource> downloadImage() {
        LOGGER.info("Resource Controller - Downloading image block");
        return resourceService.downloadImage();
    }
}