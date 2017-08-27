package utils;

import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Factory {
    public Factory() {
    }

    public Directory getDirectory() throws IOException {
        Path path = Paths.get(".", Constant.indexDirectory);
        return FSDirectory.open(path);
    }
}
