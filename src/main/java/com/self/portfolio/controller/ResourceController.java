package com.self.portfolio.controller;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import com.self.portfolio.dto.UserExcelData;
import com.self.portfolio.service.ExcelValidatorService;
import com.self.portfolio.service.ResourceService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@CrossOrigin
@RequestMapping("/api/resource")
public class ResourceController {

    private static Logger LOGGER = LoggerFactory.getLogger(ResourceController.class);

    @Autowired
    private ResourceService resourceService;

    @Autowired
    private ExcelValidatorService excelValidate;

    @GetMapping(value = "/download-image")
    private ResponseEntity<Resource> downloadImage() {
        LOGGER.info("Resource Controller - Downloading image block");
        return resourceService.downloadImage();
    }

    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    private ResponseEntity<Map<String, List<UserExcelData>>> ExtractExcel(@RequestPart("file") MultipartFile file)
            throws IOException {
        LOGGER.info("Resource Controller - Extracting Excel ");
        excelValidate.validateExcel(file);
        return ResponseEntity.ok(resourceService.ExtractExcel(file));
    }

}