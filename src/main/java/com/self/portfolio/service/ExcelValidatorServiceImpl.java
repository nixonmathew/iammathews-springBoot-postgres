package com.self.portfolio.service;

import com.self.portfolio.exception.UserException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class ExcelValidatorServiceImpl implements ExcelValidatorService{

    private static Logger LOGGER = LoggerFactory.getLogger(ExcelValidatorServiceImpl.class);

    @Override
    public void validateExcel(MultipartFile file) {

        LOGGER.info("Service -- Validating Excel");
        
        System.out.println("getOriginalFilename" + file.getOriginalFilename());
        System.out.println("getSize" + file.getSize());
        System.out.println("getContentType" + file.getContentType());
        System.out.println("getName" + file.getName());
        
        if(!(file.getContentType().equalsIgnoreCase("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet"))){
            throw new UserException("Please attach valid xlsx file format");
        }

    }

}
