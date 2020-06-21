package com.self.portfolio.service;

import java.util.List;
import com.self.portfolio.entity.UserDataExtract;
import org.springframework.web.multipart.MultipartFile;

public interface UserDataExtractService {

    List<UserDataExtract> saveDataFromUploadFile(MultipartFile file);

}