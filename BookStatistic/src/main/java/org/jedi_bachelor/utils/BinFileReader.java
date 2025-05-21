package org.jedi_bachelor.utils;

import lombok.Getter;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;

public class BinFileReader<T> {
    private String filePath;
    @Getter
    private T object;

    public BinFileReader(String _filePath) {
        this.filePath = _filePath;
    }

    public void read() {
        try {
            FileInputStream fis = new FileInputStream(this.filePath);
            ObjectInputStream ois = new ObjectInputStream(fis);

            this.object = (T) ois.readObject();
            System.out.println(this.object);

            ois.close();
            fis.close();
        } catch (IOException | ClassNotFoundException ex) {
            ex.printStackTrace();
        }
    }
}