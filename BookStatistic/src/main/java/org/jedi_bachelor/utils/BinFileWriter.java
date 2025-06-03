package org.jedi_bachelor.utils;

import lombok.Getter;
import lombok.Setter;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class BinFileWriter<T> {
    private String filePath;
    @Getter
    @Setter
    private T object;

    public BinFileWriter(String _filePath, T _object) {
        this.filePath = _filePath;
        this.object = _object;
    }

    public void write() {
        try {
            clearFile();

            FileOutputStream fos = new FileOutputStream(this.filePath);
            ObjectOutputStream oos = new ObjectOutputStream(fos);

            oos.writeObject(this.object);
            oos.flush();

            oos.close();
            fos.close();
        } catch(IOException ex) {
            ex.printStackTrace();
        }
    }

    private void clearFile() throws IOException {
        Path path = Paths.get(filePath);
        if (Files.exists(path)) {
            Files.delete(path);
        }
        Files.createFile(path);
    }

    public File getFile() {
        return new File(this.filePath);
    }

    public void setFile(String _filePath) {
        this.filePath = _filePath;
    }
}