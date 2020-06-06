package com.self.portfolio.util;

import com.self.portfolio.exception.UserException;

import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;

public class Utils {

    @Autowired
    private static ModelMapper modelMapper = new ModelMapper();

    public static <U, T> T convertToDto(U originalClass, Class<T> convertClass) {
        return modelMapper.map(originalClass, convertClass);
    }

    public static String validatePan(XSSFCell cell) {
        
        String pan = getCellValue(cell, false);
        if(pan.length() == 10){
            return pan.toUpperCase();
        }
        else{
            throw new UserException("Incorrect PAN");
        }
    }

    public static String getFailCheckCellValue(XSSFCell cell) {
        if (cell == null)
            return String.valueOf("");
        try {
            String returnVal;
            switch (cell.getCellType()) {
                case STRING:
                    returnVal = cell.getStringCellValue();
                    break;
                case NUMERIC:
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
        } catch (Exception e) {
        }
        return String.valueOf("");
    }

    public static String getCellValue(XSSFCell cell, Boolean mandatory) {
        if (cell == null && mandatory) {
            throw new UserException("Null Value");
        } else if (cell == null) {
            return "";
        }
        String returnVal;
        switch (cell.getCellType()) {
            case STRING:
                returnVal = cell.getStringCellValue();
                break;
            case NUMERIC:
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

}
