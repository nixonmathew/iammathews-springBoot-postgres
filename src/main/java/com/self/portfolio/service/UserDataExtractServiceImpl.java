package com.self.portfolio.service;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.transaction.Transactional;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import com.self.portfolio.entity.UserDataExtract;
import com.self.portfolio.exception.UserException;
import com.self.portfolio.repository.UserDataExtractRepository;

import org.apache.commons.io.FilenameUtils;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class UserDataExtractServiceImpl implements UserDataExtractService {

    Logger LOGGER = LoggerFactory.getLogger(UserDataExtractServiceImpl.class);

    @Autowired
    private UserDataExtractRepository userDataExtractRepository;

    @Transactional
    @Override
    public List<UserDataExtract> saveDataFromUploadFile(MultipartFile file) {
        LOGGER.info("ServiceImpl - fileUpload service impl entered");
        String extension = FilenameUtils.getExtension(file.getOriginalFilename());
        List<UserDataExtract> extractedUsers = new ArrayList<UserDataExtract>();
        LOGGER.info("ServiceImpl - fileUpload is " + extension);
        if (extension.equalsIgnoreCase("json")) {
            extractedUsers = readDataFromJsonFile(file, extension);
        } else if (extension.equalsIgnoreCase("csv")) {
            extractedUsers = readDataFromCsv(file, extension);
        } else if (extension.equalsIgnoreCase("xls") || extension.equalsIgnoreCase("xlsx")) {
            extractedUsers = readDataFromExcel(file, extension);
        }
        return extractedUsers;
    }

    private List<UserDataExtract> readDataFromExcel(MultipartFile file, String extension) {
        LOGGER.info("IMPL - Reading from Excel file method started");
        try {
            InputStream is = file.getInputStream();
            XSSFWorkbook wb = new XSSFWorkbook(is);
            LOGGER.info("workbook is " + wb.getSheetName(0));
            XSSFSheet sheet = wb.getSheetAt(0);
            int len = sheet.getLastRowNum();
            for (int i = 1; i <= len; i++) {
                XSSFRow row = sheet.getRow(i);

                UserDataExtract userData = new UserDataExtract(getCellValue(row.getCell(0)),
                        getCellValue(row.getCell(1)), getCellValue(row.getCell(2)), getCellValue(row.getCell(3)),
                        extension);
                userDataExtractRepository.save(userData);
                System.out.println(row);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public String getCellValue(XSSFCell cell) {
        CellType type = cell.getCellType();
        String returnVal;
        switch (type) {
            case STRING:
                System.out.println("STRING" + cell.getStringCellValue());
                returnVal = cell.getStringCellValue();
                break;
            case NUMERIC:
                System.out.println("NUMBER" + cell.getNumericCellValue());
                if (DateUtil.isCellDateFormatted(cell)) {
                    returnVal = String.valueOf(cell.getDateCellValue().getTime());
                } else {
                    returnVal = cell.getRawValue();
                }
                break;
            default:
                returnVal = cell.getRawValue();
                break;
        }
        return returnVal;

    }

    private List<UserDataExtract> readDataFromJsonFile(MultipartFile file, String extension) {
        try {
            InputStream inputStream = file.getInputStream();
            ObjectMapper mapper = new ObjectMapper();
            List<UserDataExtract> extracts = Arrays.asList(mapper.readValue(inputStream, UserDataExtract.class));
            if (extracts != null && extracts.size() > 0) {
                for (UserDataExtract extract : extracts) {
                    extract.setFileType(FilenameUtils.getExtension(file.getOriginalFilename()));
                    userDataExtractRepository.save(extract);
                }
            }
            return extracts;

        } catch (Exception e) {
            throw new UserException("Error while reading the JSON file");
        }
    }

    private List<UserDataExtract> readDataFromCsv(MultipartFile file, String extension) {
        try {
            InputStreamReader inputStreamReader = new InputStreamReader(file.getInputStream());
            CSVReader csvReader = new CSVReaderBuilder(inputStreamReader).withSkipLines(1).build();
            List<String[]> rows = csvReader.readAll();
            List<UserDataExtract> extracts = new ArrayList<UserDataExtract>();
            for (String[] row : rows) {
                UserDataExtract extract = new UserDataExtract(row[0], row[1], row[2], row[3], extension);
                userDataExtractRepository.save(extract);
                extracts.add(extract);
            }
            return extracts;
        } catch (Exception e) {
            System.out.println(e);
            throw new UserException("Couldnot save the user");
        }
    }
}