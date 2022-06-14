package com.ma3ka.simpleConvert.controller;

import com.ma3ka.simpleConvert.domain.UploadedFile;
import com.ma3ka.simpleConvert.service.UploadingService;
import com.ma3ka.simpleConvert.util.FileValidator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

@Slf4j
@Controller
@SessionAttributes("filename")
public class FileController {

    @Autowired
    private FileValidator fileValidator;

    @Autowired
    private UploadingService uploadingService;

    @PostMapping(value = "/uploadFile")
    @ResponseBody
    public ModelAndView uploadFile(@ModelAttribute("uploadedFile") UploadedFile uploadedFile, BindingResult result) {

        ModelAndView modelAndView = new ModelAndView();

        MultipartFile file = uploadedFile.getFile();
        fileValidator.validate(uploadedFile, result);

        if (result.hasErrors()) {
            modelAndView.setViewName("main");
        } else {
            String fileName = uploadingService.upload(file);
            RedirectView redirectView = new RedirectView("fileuploaded");
            redirectView.setStatusCode(HttpStatus.FOUND);
            modelAndView.setView(redirectView);
            modelAndView.addObject("filename", fileName);
        }

        return modelAndView;
    }

    @GetMapping(value = "/fileuploaded")
    public String fileUploaded() {
        return "fileuploaded";
    }

}