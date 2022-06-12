package util;


import java.io.*;
import java.lang.reflect.Array;
import java.util.List;

public class FileUtil {


    public static <T> void writeDataToFile(T[] data, String fileName) {
        if (util.StringUtil.isNullOrEmpty(fileName) || DataUtil.isEmptyCollection(data)) {
            return;
        }
        ObjectOutputStream objectOutputStream = null;
        try {
            objectOutputStream = new ObjectOutputStream(new FileOutputStream(fileName));
            objectOutputStream.writeObject(data);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static Object readDataFromFile(String fileName) {
        if (StringUtil.isNullOrEmpty(fileName)) {
            return null;
        }
        ObjectInputStream objectInputStream = null;
        try {
            objectInputStream = new ObjectInputStream(new FileInputStream(fileName));
            return  objectInputStream.readObject();
        } catch (IOException | ClassNotFoundException e) {
            return null;
        }
    }
}
