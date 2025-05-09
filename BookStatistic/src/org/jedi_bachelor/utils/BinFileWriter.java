package org.jedi_bachelor.utils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

public class BinFileWriter<T> {
    private File file;
    private T object;

    public BinFileWriter(String _filePath, T _object) {
        setFile(_filePath);
        this.object = _object;
    }

    public void write() {
        try {
            FileOutputStream fos = new FileOutputStream(this.file);
            ObjectOutputStream oos = new ObjectOutputStream(fos);

            oos.writeObject(this.object);

            oos.close();
        } catch(IOException ex) {
            ex.printStackTrace();
        }
    }

    public File getFile() { return this.file; }
    public T getObject() { return this.object; }

    public void setFile(String _file) {
        this.file = new File(_file);
    }

    public void setFile(File _file) {
        this.file = _file;
    }

    public void setObject(T _object) {
        this.object = _object;
    }
}
