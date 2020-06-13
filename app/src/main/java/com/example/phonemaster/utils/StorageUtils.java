package com.example.phonemaster.utils;

import java.io.File;

public class StorageUtils {
    long folderAmount = 0;
    long totalSize = 0;

    public long deleteFolder(String dir) {

        File f = new File(dir);
        String listFiles[] = f.list();
        long totalSize = 0;
        for (String file : listFiles) {

            File folder = new File(dir + "/" + file);
            if (folder.isDirectory()) {
                totalSize += deleteFolder(folder.getAbsolutePath());
            } else {
                totalSize += folder.length();
            }
        }
        if (totalSize == 0) {
            f.delete();
         }

        return totalSize;
    }

}
