package org.jedi_bachelor.utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;

public class BinFileReader<T> {
    private String filePath;
    private T object;

    public BinFileReader(String _filePath) {
        setFile(_filePath);
    }

    public void read() {
        try {
            FileInputStream fis = new FileInputStream(this.filePath);
            ObjectInputStream ois = new ObjectInputStream(fis);

            this.object = (T) ois.readObject(); // UpCasting

            ois.close();
        } catch (IOException | ClassNotFoundException ex) {
            ex.printStackTrace();
        }
    }

    public String getFilePath() { return this.filePath; }
    public T getObject() { return this.object; }

    public void setFile(String _file) {
        this.filePath = _file;
    }

    public void setObject(T _object) {
        this.object = _object;
    }
}
