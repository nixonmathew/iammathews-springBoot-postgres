package com.self.portfolio.util;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;

public class Utils {

    @Autowired
    private static ModelMapper modelMapper = new ModelMapper();

    public static <U,T> T convertToDto(U originalClass , Class<T> convertClass){
        return modelMapper.map(originalClass,convertClass);
    }

}
