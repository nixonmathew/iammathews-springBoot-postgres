package com.self.portfolio.service;

import org.springframework.core.io.Resource;

import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.self.portfolio.controller.ResourceController;
import com.self.portfolio.dto.UserExcelData;
import com.self.portfolio.exception.UserException;
import com.self.portfolio.util.Utils;

import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

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

    @Override
    public Map<String, List<UserExcelData>> ExtractExcel(MultipartFile file) {
        try {
            Path tempPath = Files.createTempDirectory(String.valueOf(new Date().getTime()));
            Path path = Paths.get(tempPath.toString(), file.getName() + ".xlsx");
            file.transferTo(path);

            final InputStream inputStream = Files.newInputStream(path);
            XSSFWorkbook workbook = new XSSFWorkbook(inputStream);
            XSSFSheet sheet = workbook.getSheetAt(0);
            List<UserExcelData> successLeads = new ArrayList<>();
            List<UserExcelData> errorLeads = new ArrayList<>();
            int size = sheet.getLastRowNum();
            for (int i = 1; i <= size; i++) {
                XSSFRow row = sheet.getRow(i);
                if (row != null && row.getCell(0) != null) {
                    try {
                        UserExcelData userExcelData = new UserExcelData(
                            Utils.getCellValue(row.getCell(0),true),
                            Utils.validatePan(row.getCell(1)),
                            Utils.getCellValue(row.getCell(2),true),
                            Utils.getCellValue(row.getCell(3),true),
                            Utils.getCellValue(row.getCell(4),true)
                        );
                        successLeads.add(userExcelData);
                    } catch (Exception e) {
                        try {
                            UserExcelData userExcelData = new UserExcelData(Utils.getFailCheckCellValue(row.getCell(0)),
                                    Utils.getFailCheckCellValue(row.getCell(1)),
                                    Utils.getFailCheckCellValue(row.getCell(2)),
                                    Utils.getFailCheckCellValue(row.getCell(3)),
                                    Utils.getFailCheckCellValue(row.getCell(4)));
                            errorLeads.add(userExcelData);

                        } catch (Exception e1) {
                        }
                    }
                }
            }
            System.out.println(successLeads);
            System.out.println(errorLeads);

            workbook.close();
            Map<String, List<UserExcelData>> allLeads = new HashMap<String, List<UserExcelData>>();
            allLeads.put("Success", successLeads);
            allLeads.put("Error", errorLeads);
            return allLeads;
        } catch (Exception e) {

        }
        throw new UserException("Error while creating lead");
    }

}