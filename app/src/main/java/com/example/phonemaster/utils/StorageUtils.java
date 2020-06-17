package com.example.phonemaster.utils;

import android.os.Environment;

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
    public boolean isSDCard() {

        boolean isSdCard = false;
        Boolean isSDPresent = android.os.Environment.getExternalStorageState().equals(android.os.Environment.MEDIA_MOUNTED);
        Boolean isSDSupportedDevice = Environment.isExternalStorageRemovable();

        if (isSDSupportedDevice && isSDPresent) {
            // yes SD-card is present
            isSdCard = true;
        }
        return isSdCard;
    }
}
