package com.cleaner.booster.phone.repairer.app.utils;

import android.content.Context;
import android.os.Environment;

import java.io.File;

public class StorageUtils {

    long folderAmount = 0;
    long totalSize = 0;
    public long deleteEmptyFolder(String dir) {

        File f = new File(dir);
        String listFiles[] = f.list();
        long totalSize = 0;
        for (String file : listFiles) {

            File folder = new File(dir + "/" + file);
            if (folder.isDirectory()) {
                totalSize += deleteEmptyFolder(folder.getAbsolutePath());
            } else {
                totalSize += folder.length();
            }
        }
        if (totalSize == 0) {
            f.delete();
        }
        return totalSize;
    }

    public  void deleteCache(Context context) {
        try {
            File dir = context.getCacheDir();
            deleteDir(dir);
        } catch (Exception e) { e.printStackTrace();}
    }

    public  boolean deleteDir(File dir) {
        if (dir != null && dir.isDirectory()) {
            String[] children = dir.list();
            for (int i = 0; i < children.length; i++) {
                boolean success = deleteDir(new File(dir, children[i]));
                if (!success) {
                    return false;
                }
            }
            return dir.delete();
        } else if(dir!= null && dir.isFile()) {
            return dir.delete();
        } else {
            return false;
        }
    }
    }
