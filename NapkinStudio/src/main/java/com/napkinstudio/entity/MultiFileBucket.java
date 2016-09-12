package com.napkinstudio.entity;

import java.util.ArrayList;
import java.util.List;

public class MultiFileBucket {

    List<FileBucket> files = new ArrayList<FileBucket>();

    public MultiFileBucket(){
        files.add(new FileBucket());
        files.add(new FileBucket());
        files.add(new FileBucket());
    }

    public List<FileBucket> getFiles() {
        return files;
    }

    public void setFiles(List<FileBucket> files) {
        this.files = files;
    }
    public void addFile(FileBucket file) { this.files.add(file);}


}