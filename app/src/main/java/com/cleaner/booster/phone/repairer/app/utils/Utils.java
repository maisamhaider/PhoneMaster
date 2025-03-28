package com.cleaner.booster.phone.repairer.app.utils;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.database.Cursor;
import android.media.MediaScannerConnection;
import android.net.Uri;
import android.os.Environment;
import android.os.StatFs;
import android.provider.MediaStore;
import android.widget.Toast;

import androidx.core.content.ContextCompat;

import com.cleaner.booster.phone.repairer.app.models.CommonModel;
import com.cleaner.booster.phone.repairer.app.models.DeepCleanAudioModel;
import com.cleaner.booster.phone.repairer.app.models.DeepCleanDocsModel;
import com.cleaner.booster.phone.repairer.app.models.DeepCleanImagesModel;
import com.cleaner.booster.phone.repairer.app.models.DeepCleanPackagesModel;
import com.cleaner.booster.phone.repairer.app.models.DeepCleanVideosModel;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileFilter;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.channels.FileChannel;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Objects;


public class Utils {

    Context context;
    String destinationPath;
    File folder;
    float docSize = 0;
    float size = 0;

    public Utils(Context context) {
        this.context = context;
    }

    // get all data
    public List<CommonModel> getListFiles(File parentDir) {
        File fold = new File(parentDir.getPath());
        List<CommonModel> docList = new ArrayList<>();
        File[] mlist = fold.listFiles();
        File[] mFilelist = fold.listFiles();

        {
            if (mlist != null)
                for (File f : mlist) {
                    if (f.isDirectory()) {
                        docList.addAll(getListFiles(new File(f.getAbsolutePath())));
                    } else {
                        CommonModel data = new CommonModel();
                        data.setName(f.getName());
                        data.setPath(f.getPath());
                        data.setSize(f.length());
                        //                doc.setType(FileTypes.DocumentType);
                        if (f.length() > 0)
                            docList.add(data);
                    }
                }
        }

        return docList;
    }

    public static boolean externalMemoryAvailable(Activity context) {
        File[] storages = ContextCompat.getExternalFilesDirs(context, null);
        if (storages.length > 1 && storages[0] != null && storages[1] != null)
            return true;
        else
            return false;

    }

    //get specific data
    public List<CommonModel> getListFiles(File parentDir, String forWhat) {
        File fold = new File(parentDir.getPath());
        List<CommonModel> docList = new ArrayList<>();
        File[] mlist = fold.listFiles();


        if (forWhat.matches("images")) {
            if (mlist != null) {
                for (File f : mlist) {
                    if (f.isDirectory()) {
                        docList.addAll(getListFiles(new File(f.getAbsolutePath(), "images")));
                    } else {

                        CommonModel data = new CommonModel();
                        if (f.getAbsolutePath().endsWith(".jpeg") || f.getAbsolutePath().endsWith(".jpg") || f.getAbsolutePath().endsWith(".png")) {
                            data.setName(f.getName());
                            data.setPath(f.getPath());
                            data.setSize(f.length());
                            if (f.length() > 0)
                                docList.add(data);
                        }


                    }
                }
            }

        } else {
            if (mlist != null) {
                for (File f : mlist) {
                    if (f.isDirectory()) {
                        docList.addAll(getListFiles(new File(f.getAbsolutePath(), "videos")));
                    } else {
                        CommonModel data = new CommonModel();
                        if (f.getAbsolutePath().endsWith("mp4")) {
                            data.setName(f.getName());
                            data.setPath(f.getPath());
                            data.setSize(f.length());

                            if (f.length() > 0)
                                docList.add(data);
                        }
                    }
                }
            }
        }
        return docList;
    }

    public float getTotalStorage() {
        long totalStorage;

        StatFs statFs = new StatFs(Environment.getExternalStorageDirectory().getPath());
        totalStorage = (statFs.getBlockSizeLong() * statFs.getBlockCountLong());

        return totalStorage;//in Mbs
    }


    public static long getAvailableStorage() {
        long megAvailable;

        StatFs stat = new StatFs(Environment.getExternalStorageDirectory().getPath());
        long bytesAvailable;

        bytesAvailable = stat.getAvailableBlocksLong() * stat.getBlockSizeLong();
        megAvailable = bytesAvailable;
        return megAvailable;
    }

//


    public List<String> getActiveApps() {

        PackageManager pm = context.getPackageManager();
        List<ApplicationInfo> packages = pm.getInstalledApplications(PackageManager.GET_META_DATA);
        List<String> list = new ArrayList<>();

        for (ApplicationInfo packageInfo : packages) {

            if (isSTOPPED(packageInfo)) {
                if (!list.contains(packageInfo.packageName)) {
                    list.add(packageInfo.packageName);
                }
            }
        }
        return list;
    }

    public List<String> getSystemActiveApps() {

        PackageManager pm = context.getPackageManager();
        List<ApplicationInfo> packages = pm.getInstalledApplications(PackageManager.GET_META_DATA);
        List<String> list = new ArrayList<>();

        for (ApplicationInfo packageInfo : packages) {
            if (isSTOPPED(packageInfo) && isSYSTEM(packageInfo)) {
                if (!list.contains(packageInfo.packageName)) {
                    list.add(packageInfo.packageName);
                }
            }
        }
        return list;
    }


    private boolean isSTOPPED(ApplicationInfo pkgInfo) {

        return ((pkgInfo.flags & ApplicationInfo.FLAG_STOPPED) == 0);
    }

    private boolean isSYSTEM(ApplicationInfo pkgInfo) {

        return ((pkgInfo.flags & ApplicationInfo.FLAG_SYSTEM) != 0);
    }

    public boolean isSystemPackage(ResolveInfo resolveInfo) {

        return ((resolveInfo.activityInfo.applicationInfo.flags & ApplicationInfo.FLAG_SYSTEM) != 0);
    }


    public String GetAppName(String ApkPackageName) {

        String Name = "";

        ApplicationInfo applicationInfo;

        PackageManager packageManager = context.getPackageManager();

        try {

            applicationInfo = packageManager.getApplicationInfo(ApkPackageName, 0);

            if (applicationInfo != null) {

                Name = (String) packageManager.getApplicationLabel(applicationInfo);

            }

        } catch (PackageManager.NameNotFoundException e) {

            e.printStackTrace();
        }
        return Name;
    }


    public float getPercentage(float totalData, float usedData) {

        return (usedData * 100 / totalData);
    }


    public List<String> GetAllInstalledApkInfo() {

        List<String> ApkPackageName = new ArrayList<>();

        Intent intent = new Intent(Intent.ACTION_MAIN, null);

        intent.addCategory(Intent.CATEGORY_LAUNCHER);

        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_RESET_TASK_IF_NEEDED);

        List<ResolveInfo> resolveInfoList = context.getPackageManager().queryIntentActivities(intent, 0);

        for (ResolveInfo resolveInfo : resolveInfoList) {

            ActivityInfo activityInfo = resolveInfo.activityInfo;

            if (isSystemPackage(resolveInfo) || !isSystemPackage(resolveInfo)) {
                if (!ApkPackageName.contains(activityInfo.applicationInfo.packageName)) {
                    ApkPackageName.add(activityInfo.applicationInfo.packageName);
                }
            }
        }

        return ApkPackageName;

    }

    // return left string from first ,
    public static String getRightStringToThePoint(String s, String pointString) {
        String d = s;
        String ssDate = d.substring(d.lastIndexOf(pointString));
        return ssDate;
    }

    public void moveFile(String sourcePath, String finalDir) {

        String lastName = getRightStringToThePoint(sourcePath, "/");

        for (String path : getExternalMounts()) {
            destinationPath = path + "/" + finalDir + lastName;
            folder = new File(path + "/" + finalDir);
        }


        if (!folder.exists()) {
            boolean isCreate = folder.mkdirs();
            if (isCreate) {
//                Toast.makeText(context, "created", Toast.LENGTH_SHORT).show();
            } else {
//                Toast.makeText(context, "not created", Toast.LENGTH_SHORT).show();
            }

        }
        File sourceFile = new File(sourcePath);

        File destinationFile = new File(destinationPath);

        sourceFile.renameTo(destinationFile);

    }

    public static ArrayList<String> getExternalMounts() {
        final ArrayList<String> out = new ArrayList<>();
        String reg = "(?i).*vold.*(vfat|ntfs|exfat|fat32|ext3|ext4).*rw.*";
        String s = "";
        try {
            final Process process = new ProcessBuilder().command("mount")
                    .redirectErrorStream(true).start();
            process.waitFor();
            final InputStream is = process.getInputStream();
            final byte[] buffer = new byte[1024];
            while (is.read(buffer) != -1) {
                s = s + new String(buffer);
            }
            is.close();
        } catch (final Exception e) {
            e.printStackTrace();
        }

        // parse output
        final String[] lines = s.split("\n");
        for (String line : lines) {
            if (!line.toLowerCase(Locale.US).contains("asec")) {
                if (line.matches(reg)) {
                    String[] parts = line.split(" ");
                    for (String part : parts) {
                        if (part.startsWith("/"))
                            if (!part.toLowerCase(Locale.US).contains("vold"))
                                out.add(part);
                    }
                }
            }
        }
        return out;
    }

    public void copyFileOrDirectory(String srcDir) {

        try {
            File src = new File(srcDir);
            File dst = new File(Environment.getExternalStorageDirectory() + "/Phone_Master_Status", src.getName());

            if (src.isDirectory()) {

                String[] files = src.list();
                int filesLength = 0;
                if (files != null) {
                    filesLength = files.length;
                }
                for (int i = 0; i < filesLength; i++) {
                    String src1 = (new File(src, files[i]).getPath());
                    copyFileOrDirectory(src1);
                }
            } else {
                copyFile(src, dst);
                Toast.makeText(context, "Saved", Toast.LENGTH_SHORT).show();

            }
        } catch (Exception e) {
            e.printStackTrace();
            Toast.makeText(context, "Not Saved", Toast.LENGTH_SHORT).show();
        }
    }

    public void copyFile(File sourceFile, File destFile) throws IOException {
        if (!Objects.requireNonNull(destFile.getParentFile()).exists())
            destFile.getParentFile().mkdirs();

        if (!destFile.exists()) {
            destFile.createNewFile();
        }

        try (FileChannel source = new FileInputStream(sourceFile).getChannel(); FileChannel destination = new FileOutputStream(destFile).getChannel()) {
            destination.transferFrom(source, 0, source.size());
        }
    }

    @SuppressLint("Recycle")
    public List<DeepCleanImagesModel> getAllImagePaths() {
        List<DeepCleanImagesModel> list = new ArrayList<>();
        DeepCleanImagesModel file;
        Uri uri;
        Cursor cursor;
        int column_index_data;
        String absolutePath = null;
        uri = MediaStore.Images.Media.EXTERNAL_CONTENT_URI;
        String sortOrder = MediaStore.MediaColumns.DATE_MODIFIED + " DESC";
        cursor = context.getContentResolver().query(uri, null, null,
                null, sortOrder);
        assert cursor != null;
        column_index_data = cursor.getColumnIndexOrThrow(MediaStore.MediaColumns.DATA);
        while (cursor.moveToNext()) {
            absolutePath = cursor.getString(column_index_data);
            String title = cursor.getString(cursor.getColumnIndexOrThrow(MediaStore.MediaColumns.TITLE));
            long size = cursor.getLong(cursor.getColumnIndexOrThrow(MediaStore.MediaColumns.SIZE));
//            Long size = (new File(absolutePathOfVideo)).length();
            file = new DeepCleanImagesModel();
            file.setImagePath(absolutePath);
//            file.setSize(size);
//            file.setType(FileTypes.ImageType);
            if (/*isPhoto(absolutePathOfVideo) &&*/ size > 0)
                list.add(file);
        }
        return list;
    }

    public List<DeepCleanVideosModel> getAllVideosPaths() {
        List<DeepCleanVideosModel> list = new ArrayList<>();
        DeepCleanVideosModel file;
        Uri uri;
        Cursor cursor;
        int column_index_data;
        String absolutePath = null;
        uri = MediaStore.Video.Media.EXTERNAL_CONTENT_URI;
        String sortOrder = MediaStore.MediaColumns.DATE_MODIFIED + " DESC";
        cursor = context.getContentResolver().query(uri, null, null,
                null, sortOrder);
        column_index_data = cursor.getColumnIndexOrThrow(MediaStore.MediaColumns.DATA);
        while (cursor.moveToNext()) {
            absolutePath = cursor.getString(column_index_data);
            String title = cursor.getString(cursor.getColumnIndexOrThrow(MediaStore.MediaColumns.TITLE));
            long size = cursor.getLong(cursor.getColumnIndexOrThrow(MediaStore.MediaColumns.SIZE));
//            Long size = (new File(absolutePathOfVideo)).length();
            file = new DeepCleanVideosModel();
            file.setVideoPath(absolutePath);
//            file.setSize(size);
//            file.setType(FileTypes.ImageType);
            if (/*isPhoto(absolutePathOfVideo) &&*/ size > 0)
                list.add(file);
        }
        return list;
    }

    public List<DeepCleanAudioModel> getAllAudiosPaths() {
        List<DeepCleanAudioModel> list = new ArrayList<>();
        DeepCleanAudioModel file;
        Uri uri;
        Cursor cursor;
        int column_index_data;
        String absolutePath = null;
        uri = MediaStore.Audio.Media.EXTERNAL_CONTENT_URI;
        String sortOrder = MediaStore.MediaColumns.DATE_MODIFIED + " DESC";
        cursor = context.getContentResolver().query(uri, null, null,
                null, sortOrder);
        column_index_data = cursor.getColumnIndexOrThrow(MediaStore.MediaColumns.DATA);
        while (cursor.moveToNext()) {
            absolutePath = cursor.getString(column_index_data);
            String title = cursor.getString(cursor.getColumnIndexOrThrow(MediaStore.MediaColumns.TITLE));
            long size = cursor.getLong(cursor.getColumnIndexOrThrow(MediaStore.MediaColumns.SIZE));
//            Long size = (new File(absolutePathOfVideo)).length();
            file = new DeepCleanAudioModel();
            file.setAudioPath(absolutePath);
            file.setAudioName(title);
//            file.setSize(size);
//            file.setType(FileTypes.ImageType);
            if (/*isPhoto(absolutePathOfVideo) &&*/ size > 0)
                list.add(file);
        }
        return list;
    }


    public List<DeepCleanDocsModel> getAllDocs(String path) {
        File fold = new File(path);
        List<DeepCleanDocsModel> docList = new ArrayList<>();
        File[] mlist = fold.listFiles();
        File[] mFilelist = fold.listFiles(new AllDoFilter());
        if (mlist != null) {
            for (File f : mlist) {
                if (f.isDirectory()) {
                    List<DeepCleanDocsModel> fList = getAllDocs(f.getAbsolutePath());
                    docList.addAll(fList);
                }
            }
        }
        if (mFilelist != null) {
            for (File f : mFilelist) {
                DeepCleanDocsModel doc = new DeepCleanDocsModel();
                doc.setDocName(f.getName());
    //                doc.setSize(f.length());
    //                doc.setType(FileTypes.DocumentType);
                doc.setDocPath(f.getAbsolutePath());
                if (f.length() > 0)
                    docList.add(doc);
            }
        }
        return docList;
    }


    public List<DeepCleanVideosModel> getVideos(String path) {
        File fold = new File(path);
        List<DeepCleanVideosModel> docList = new ArrayList<>();
        File[] mlist = fold.listFiles();
        File[] mFilelist = fold.listFiles();
        if (mlist != null) {
            for (File f : mlist) {
                if (f.isDirectory()) {
                    List<DeepCleanVideosModel> fList = getVideos(f.getAbsolutePath());
                    docList.addAll(fList);
                }
            }
        }
        if (mFilelist != null) {
            for (File f : mFilelist) {
                DeepCleanVideosModel doc = new DeepCleanVideosModel();
                doc.setVideoName(f.getName());
    //                doc.setSize(f.length());
    //                doc.setType(FileTypes.DocumentType);
                doc.setVideoPath(f.getAbsolutePath());
                if (f.length() > 0)
                    docList.add(doc);
            }
        }
        return docList;
    }

    public List<DeepCleanImagesModel> getImages(String path) {
        File fold = new File(path);
        List<DeepCleanImagesModel> docList = new ArrayList<>();
        File[] mlist = fold.listFiles();
        File[] mFilelist = fold.listFiles();
        if (mlist != null) {
            for (File f : mlist) {
                if (f.isDirectory()) {
                    List<DeepCleanImagesModel> fList = getImages(f.getAbsolutePath());
                    docList.addAll(fList);
                }
            }
        }
        if (mFilelist != null) {
            for (File f : mFilelist) {
               DeepCleanImagesModel img = new DeepCleanImagesModel();
               img.setImageName(f.getName());
   //                doc.setSize(f.length());
   //                doc.setType(FileTypes.DocumentType);
               img.setImagePath(f.getAbsolutePath());
               if (f.length() > 0)
                   docList.add(img);
           }
        }
        return docList;
    }


    public List<DeepCleanPackagesModel> getAllPackages(String path) {
        File fold = new File(path);
        List<DeepCleanPackagesModel> docList = new ArrayList<>();
        File[] mlist = fold.listFiles();
        File[] mFilelist = fold.listFiles(new AllPackagesFilter());
        if (mlist != null) {
            for (File f : mlist) {
                if (f.isDirectory()) {
                    List<DeepCleanPackagesModel> fList = getAllPackages(f.getAbsolutePath());
                    docList.addAll(fList);
                }
            }
            if (mFilelist != null) {
                for (File f : mFilelist) {
                    DeepCleanPackagesModel doc = new DeepCleanPackagesModel();
                    doc.setPkgName(f.getName());
    //                doc.setSize(f.length());
    //                doc.setType(FileTypes.DocumentType);
                    doc.setPkgPath(f.getAbsolutePath());
                    if (f.length() > 0)
                        docList.add(doc);
                }
            }
        }
        return docList;
    }

    public float getAllSize(String path) {
        float docSize = 0;
        File fold = new File(path);
        File[] mlist = fold.listFiles();
        File[] mFilelist = fold.listFiles();
        if (mlist != null)
            for (File f : mlist) {
                if (f.isDirectory()) {
                    getAllSize(f.getAbsolutePath());
                }
            }
        if (mlist != null)
            if (mFilelist != null) {
                for (File f : mFilelist) {
                    docSize = docSize + f.length();
                }
            }
        return docSize;
    }


    public float getAllDocSize(String path) {

        File fold = new File(path);
        File[] mlist = fold.listFiles();
        File[] mFilelist = fold.listFiles(new AllDoFilter());
        if (mlist != null) {
            for (File f : mlist) {
                if (f.isDirectory()) {
                    getAllDocSize(f.getAbsolutePath());
                }
        }
        }
        if (mFilelist != null) {
            for (File f : mFilelist) {
                    docSize = docSize + f.length();
            }
        }
        return docSize;
    }

    public float getAllPkgsSize(String path) {
        File fold = new File(path);
        File[] mlist = fold.listFiles();
        File[] mFilelist = fold.listFiles(new AllPackagesFilter());
        if (mlist != null) {
            for (File f : mlist ) {
                if (f.isDirectory()) {
                    getAllPkgsSize(f.getAbsolutePath());
                }
            }
        }

        if (mFilelist != null) {
            for (File f : mFilelist) {
                    size = size + f.length();
            }
        }
        return size;
    }

    // return images audio videos size
    @SuppressLint("Recycle")
    public float getAllIAAsSize(String forWhat) {
        Uri uri = null;
        Cursor cursor = null;

        float size = 0;
        if (forWhat.matches("videos")) {
            uri = MediaStore.Video.Media.EXTERNAL_CONTENT_URI;
        } else if (forWhat.matches("images")) {
            uri = MediaStore.Images.Media.EXTERNAL_CONTENT_URI;

        } else if (forWhat.matches("audios")) {
            uri = MediaStore.Audio.Media.EXTERNAL_CONTENT_URI;

        }

        String sortOrder = MediaStore.MediaColumns.DATE_MODIFIED + " DESC";
        if (uri != null) {
            cursor = context.getContentResolver().query(uri, null, null,
                    null, sortOrder);
        }
        assert cursor != null;
        while (cursor.moveToNext()) {
            size = size + cursor.getLong(cursor.getColumnIndexOrThrow(MediaStore.MediaColumns.SIZE));

        }
        return size;
    }

    //delete file taken from media store
    public void scanaddedFile(String path) {
        try {
            MediaScannerConnection.scanFile(context, new String[]{path},
                    null, (path1, uri) -> context.getContentResolver()
                            .delete(uri, null, null));
        } catch (Exception e) {
            e.printStackTrace();
        }

    }


    public static class AllDoFilter implements FileFilter {
        @Override
        public boolean accept(File pathname) {
            String path = pathname.getPath();
            return (path.endsWith(".ods")
                    || path.endsWith(".xls")
                    || path.endsWith(".xlsx")
                    || path.endsWith(".doc")
                    || path.endsWith(".odt")
                    || path.endsWith(".docx")
                    || path.endsWith(".pps")
                    || path.endsWith(".pptx")
                    || path.endsWith(".ppt")
                    || path.endsWith(".PDF")
                    || path.endsWith(".pdf")
                    || path.endsWith(".txt")
                    || path.endsWith(".ziip")
                    || path.endsWith(".7z")
                    || path.endsWith(".rar")
                    || path.endsWith(".rpm")
                    || path.endsWith(".tar.gz")
                    || path.endsWith(".z")
                    || path.endsWith(".zip"));
        }
    }

    public static class AllImgFilter implements FileFilter {
        @Override
        public boolean accept(File pathname) {
            String path = pathname.getPath();
            return (path.endsWith(".jpeg") ||
                    path.endsWith(".jpg") ||
                    path.endsWith(".png"));
        }
    }

    public static class AllAudioFilter implements FileFilter {
        @Override
        public boolean accept(File pathname) {
            String path = pathname.getPath();
            return (path.endsWith(".mp3") ||
                    path.endsWith(".opus") ||
                    path.endsWith(".m4a") ||
                    path.endsWith(".amr") ||
                    path.endsWith(".mpa") ||
                    path.endsWith(".mid") ||
                    path.endsWith(".ogg") ||
                    path.endsWith(".wav") ||
                    path.endsWith(".wma") ||
                    path.endsWith(".wma") ||
                    path.endsWith(".wpl") ||
                    path.endsWith(".cda") ||
                    path.endsWith(".aif"));
        }
    }

    public static class AllPackagesFilter implements FileFilter {
        @Override
        public boolean accept(File pathname) {
            String path = pathname.getPath();
            return (path.endsWith(".apk"));
        }
    }

    @SuppressLint("DefaultLocale")
    public String getCalculatedDataSize(float size) {
        String sizePrefix = "Bytes";
        float finalSize = size;
        if (size >= 1024) {
            float sizeKb = size / 1024;
            sizePrefix = "KB";
            finalSize = sizeKb;
            if (sizeKb >= 1024) {
                float sizeMB = sizeKb / 1024;
                sizePrefix = "MB";
                finalSize = sizeMB;
                if (sizeMB >= 1024) {
                    float sizeGb = sizeMB / 1024;
                    sizePrefix = "GB";
                    finalSize = sizeGb;
                } } }
        return String.format("%.2f", finalSize) + sizePrefix;
    }

    public float getCalculatedDataSizeFloat(float size) {
        float finalSize = size;
        if (size >= 1024) {
            float sizeKb = size / 1024;
            finalSize = sizeKb;
            if (sizeKb >= 1024) {
                float sizeMB = sizeKb / 1024;
                finalSize = sizeMB;
                if (sizeMB >= 1024) {
                    float sizeGb = sizeMB / 1024;
                    finalSize = sizeGb;

                }
            }
        }
        return finalSize;
    }

    public float cpuTemperature() {
        Process process;
        try {
            process = Runtime.getRuntime().exec("cat sys/class/thermal/thermal_zone0/temp");
            process.waitFor();
            BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
            String line = reader.readLine();
            if (line != null) {
                float temp = Float.parseFloat(line);
                return getFahrenheitToCelsius(temp / 1000.0f);
            } else {
                return getFahrenheitToCelsius(51.0f);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return getFahrenheitToCelsius(0.0f);
        }
    }

    public float getFahrenheitToCelsius(float fahren) {
        return (fahren - 32) * 5 / 9;

    }


    public float getCalculatedDataSizeMB(float size) {
        float sizeBytes = size;
        float finalSize = size;
        if (sizeBytes >= 1024) {
            float sizeKb = sizeBytes / 1024;
            finalSize = sizeKb;
            if (sizeKb >= 1024) {
                float sizeMB = sizeKb / 1024;
                finalSize = sizeMB;
            }
        }
        return finalSize;
    }


}