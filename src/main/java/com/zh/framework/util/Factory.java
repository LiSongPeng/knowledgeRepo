package com.zh.framework.util;

import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Factory {
    private String indexDirectory;

    public Factory() {
    }

    public void setIndexDirectory(String indexDirectory) {
        this.indexDirectory = indexDirectory;
    }

    public String getIndexDirectory() {
        return indexDirectory;
    }

    public Directory getDirectory() throws IOException {
        Path path = Paths.get(".", indexDirectory);
        return FSDirectory.open(path);
    }
}
