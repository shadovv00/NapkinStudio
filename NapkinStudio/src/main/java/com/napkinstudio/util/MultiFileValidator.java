package com.napkinstudio.util;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.napkinstudio.entity.FileBucket;
import com.napkinstudio.entity.MultiFileBucket;

@Component
public class MultiFileValidator implements Validator {

    public boolean supports(Class<?> clazz) {
        return MultiFileBucket.class.isAssignableFrom(clazz);
    }

    public void validate(Object obj, Errors errors) {
        MultiFileBucket multiBucket = (MultiFileBucket) obj;
        System.out.println("validation start");

        int index=0;

        //TODO: Cnacel static size of file array!

        for(FileBucket file : multiBucket.getFiles()){
            System.out.println(file);
            System.out.println(file.getFile());
            if(file.getFile()!=null){
                if (file.getFile().getSize() == 0) {
                    errors.rejectValue("files["+index+"].file", "missing.file");
                }
            }
            index++;
        }

    }
}