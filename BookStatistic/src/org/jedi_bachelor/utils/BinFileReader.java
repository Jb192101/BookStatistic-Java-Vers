package org.jedi_bachelor.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;

public class BinFileReader<T> {
    private File file;
    private T object;

    public BinFileReader(File _file) {
        setFile(_file);
    }

    public void read() {
        try {
            FileInputStream fis = new FileInputStream(this.file);
            ObjectInputStream ois = new ObjectInputStream(fis);

            this.object = (T) ois.readObject(); // UpCasting

            ois.close();
        } catch (IOException | ClassNotFoundException ex) {
            ex.printStackTrace();
        }
    }

    public File getFile() { return this.file; }
    public T getObject() { return this.object; }

    public void setFile(File _file) {
        if(_file.canRead())
            this.file = _file;
    }

    public void setObject(T _object) {
        this.object = _object;
    }
}
