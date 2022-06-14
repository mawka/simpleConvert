package com.ma3ka.simpleConvert.util;


import com.ma3ka.simpleConvert.domain.UploadedFile;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class FileValidator implements Validator {

    @Override
    public void validate(Object uploadedFile, Errors errors) {

        UploadedFile file = (UploadedFile) uploadedFile;

        if (file.getFile().getSize() == 0) {
            errors.rejectValue("file", "uploadForm.selectFile", "Please select a file!");
        }
    }

    @Override
    public boolean supports(Class<?> clazz) {
        // TODO Auto-generated method stub
        return false;
    }

}