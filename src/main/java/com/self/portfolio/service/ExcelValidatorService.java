package com.self.portfolio.service;

import org.springframework.web.multipart.MultipartFile;

public interface ExcelValidatorService {
    
    public void validateExcel(MultipartFile file);
}