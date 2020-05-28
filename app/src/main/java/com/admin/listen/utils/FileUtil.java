package com.admin.listen.utils;

import android.app.Activity;
import android.content.Context;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

/**
 * @作者(author)： JQ
 * @创建时间(date)： 2020/5/26 21:37
 **/
public class FileUtil {

    public static String readFile(String filePath) {
        File file = new File(filePath);
        if (!file.isFile()) return null;
        InputStream in = null;
        InputStreamReader inr = null;
        BufferedReader reader = null;
        StringBuffer buffer = new StringBuffer();
        try {
            in = new FileInputStream(file);
            inr = new InputStreamReader(in);
            reader = new BufferedReader(inr);
            String link;
            while ((link = reader.readLine()) != null) {
                buffer.append(link);
            }
        } catch (Exception e) {

        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (inr != null) {
                try {
                    inr.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (in != null) {
                try {
                    in.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return buffer.toString();
    }

    public static boolean writeFile(String path, String name, String data) {
        File file = new File(path);
        if (!file.exists()) {
            file.mkdirs();
        }
        String filePath = file.getAbsolutePath() + File.separator + name;
        FileWriter writer = null;
        try {
            writer = new FileWriter(filePath);
            writer.write(data);
            writer.flush();
            return true;
        } catch (IOException e) {
            return false;
        } finally {
            if (writer != null) {
                try {
                    writer.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }


    public static boolean writeSerialize(Context context, String fileName, Object obj) {
        ObjectOutputStream objectOutputStream = null;
        try {
            objectOutputStream = new ObjectOutputStream(context.openFileOutput(fileName, Context.MODE_PRIVATE));
            objectOutputStream.writeObject(obj);
            objectOutputStream.flush();
        } catch (IOException e) {
            return false;
        } finally {
            if (objectOutputStream != null) {
                try {
                    objectOutputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return true;
    }

    public static Object readSerialize(Context context, String fileName) {
        ObjectInputStream objectInputStream = null;
        try {
            objectInputStream = new ObjectInputStream(context.openFileInput(fileName));
            return objectInputStream.readObject();
        } catch (Exception e) {
            return null;
        }
    }
}
