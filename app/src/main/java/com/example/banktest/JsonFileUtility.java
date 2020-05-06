package com.example.banktest;


import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Environment;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.Writer;


public class JsonFileUtility {

    /**
     * Saves an object to file
     * @param object
     * @param fileName
     */
    public static void saveFile(JSONObject object, String folderName, String fileName, Context context) {
        try {
            Writer output = null;
            File file = new File(context.getFilesDir() + "/" + folderName + "/" + fileName + ".json");
            file.getParentFile().mkdirs();
            output = new BufferedWriter(new FileWriter(file));
            output.write(object.toString());
            output.close();


        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Loads Json object from the file
     * @param fileName
     * @param context
     * @return
     */
    public static JSONObject loadFile(String folderName, String fileName, Context context) {
        String json = null;
        try {
            File file = new File(context.getFilesDir() + "/" + folderName + "/" + fileName + ".json");
            if (!file.exists()){
                return null;
            }
            InputStream is = new FileInputStream(file);
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            json = new String(buffer, "UTF-8");
        } catch (IOException ex) {
            ex.printStackTrace();

        }
        if (json == null){
            return null;
        }
        try {
            return new JSONObject(json);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Loads file and returns JSON object
     * @param file
     * @return JSON object
     */
    public static JSONObject loadFile(File file) {
        String json = null;
        try {
            InputStream is = new FileInputStream(file);
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            json = new String(buffer, "UTF-8");
        } catch (IOException ex) {
            ex.printStackTrace();

        }
        if (json == null){
            return null;
        }
        try {
            return new JSONObject(json);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }
}
