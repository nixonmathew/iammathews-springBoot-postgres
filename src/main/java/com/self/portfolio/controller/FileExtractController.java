package com.self.portfolio.controller;

import java.util.List;

import com.self.portfolio.entity.UserDataExtract;
import com.self.portfolio.service.UserDataExtractService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PostMapping;

@RestController
@RequestMapping("/extract")
public class FileExtractController {

    @Autowired
    private UserDataExtractService userDataExtractService;

    Logger LOGGER = LoggerFactory.getLogger(FileExtractController.class);

    @GetMapping("/")
    public String index(Model model) {
        String dummy = "from conroller";
        model.addAttribute("key", dummy);
        return "dummy";
    }

    @PostMapping(value = "/fileUpload")
    public List<UserDataExtract> postMethodName(@ModelAttribute UserDataExtract userDataExtract) {
        LOGGER.info("Controller - fileUpload endpoint entered");
        List<UserDataExtract> extractedUsers = userDataExtractService.saveDataFromUploadFile(userDataExtract.getFile());
        return extractedUsers;
    }

}