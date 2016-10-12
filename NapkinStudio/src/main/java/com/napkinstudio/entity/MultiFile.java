package com.napkinstudio.entity;

import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * Created by User1 on 14.09.2016.
 */
public class MultiFile {

    private List<MultipartFile> uploadingFiles;

    public List<MultipartFile> getFiles() {
        return uploadingFiles;
    }

    public void setFiles(List<MultipartFile> files) {
        this.uploadingFiles = uploadingFiles;
    }
}
