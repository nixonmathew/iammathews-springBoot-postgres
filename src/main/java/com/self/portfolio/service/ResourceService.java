package com.self.portfolio.service;

import java.util.List;
import java.util.Map;

import com.self.portfolio.dto.UserExcelData;

import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public interface ResourceService {

    public ResponseEntity<Resource> downloadImage();

    public Map<String, List<UserExcelData>> ExtractExcel(MultipartFile file);

}