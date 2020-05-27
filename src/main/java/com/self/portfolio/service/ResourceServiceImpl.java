package com.self.portfolio.service;

import org.springframework.core.io.Resource;

import com.self.portfolio.controller.ResourceController;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class ResourceServiceImpl implements ResourceService {

    private static Logger LOGGER = LoggerFactory.getLogger(ResourceController.class);

    private static final String IMAGENAME = "conor.jpg";

    @Override
    public ResponseEntity<Resource> downloadImage() {
        LOGGER.info("Impl - Downloading image started");
        Resource resource = new ClassPathResource("template/" + IMAGENAME);
        return ResponseEntity.ok().contentType(MediaType.APPLICATION_OCTET_STREAM)
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + IMAGENAME).body(resource);

    }

}