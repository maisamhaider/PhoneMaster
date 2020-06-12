package com.example.phonemaster.utils;

import android.app.Activity;
import android.app.ActivityManager;
import android.app.usage.UsageStats;
import android.app.usage.UsageStatsManager;
import android.content.ComponentName;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.database.Cursor;
import android.graphics.drawable.Drawable;
import android.media.MediaScannerConnection;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.os.StatFs;
import android.provider.ContactsContract;
import android.provider.MediaStore;
import android.util.Log;
import android.widget.Toast;

import androidx.core.content.ContextCompat;

import com.example.phonemaster.R;
import com.example.phonemaster.models.DeepCleanAudioModel;
import com.example.phonemaster.models.DeepCleanDocsModel;
import com.example.phonemaster.models.DeepCleanImagesModel;
import com.example.phonemaster.models.DeepCleanPackagesModel;
import com.example.phonemaster.models.DeepCleanVideosModel;
import com.example.phonemaster.models.NumberAndNamesModel;

import java.io.File;
import java.io.FileFilter;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FilenameFilter;
import java.io.IOException;
import java.nio.channels.FileChannel;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;
import java.util.SortedMap;
import java.util.TreeMap;

import static android.content.Context.ACTIVITY_SERVICE;

public class Utils {

    Context context;

    public Utils(Context context) {
        this.context = context;
    }

    public List<File> getListFiles(File parentDir, String extension) {
        ArrayList<File> inFiles = new ArrayList<File>();
        File[] files = parentDir.listFiles();
        if (extension.matches("images")) {
            for (File file : files) {
                if (file.isDirectory()) {
                    inFiles.addAll(getListFiles(file, "images"));
                } else {
                    if (file.getName().endsWith(".jpg")) {
                        inFiles.add(file);
                    } else if (file.getName().endsWith(".png")) {
                        inFiles.add(file);
                    } else if (file.getName().endsWith(".jpeg")) {
                        inFiles.add(file);
                    }
                }
            }
        } else if (extension.matches("audios")) {

            for (File file : files) {
                if (file.isDirectory()) {
                    inFiles.addAll(getListFiles(file, "audios"));
                } else {
                    inFiles.add(file);
                }
            }
        } else if (extension.matches("videos")) {

            for (File file : files) {
                if (file.isDirectory()) {
                    inFiles.addAll(getListFiles(file, "videos"));
                } else {

                    inFiles.add(file);
                }
            }
        } else if (extension.matches("backup")) {

            for (File file : files) {
                if (file.isDirectory()) {
                    inFiles.addAll(getListFiles(file, "backup"));
                } else {

                    inFiles.add(file);
                }
            }
        } else if (extension.matches("databases")) {

            for (File file : files) {
                if (file.isDirectory()) {
                    inFiles.addAll(getListFiles(file, "databases"));
                } else {

                    inFiles.add(file);
                }
            }
        } else if (extension.matches("doc")) {

            for (File file : files) {
                if (file.isDirectory()) {
                    inFiles.addAll(getListFiles(file, "doc"));
                } else {

                    inFiles.add(file);
                }
            }
        }
        return inFiles;
    }

    public float getTotalStorage() {
        long totalStorage;

        StatFs statFs = new StatFs(Environment.getExternalStorageDirectory().getPath());
        totalStorage = (statFs.getBlockSizeLong() * statFs.getBlockCountLong());

        return (totalStorage / (1024 * 1024));//in Mbs
    }


    public static long getAvailableStorage() {
        long megAvailable;

        StatFs stat = new StatFs(Environment.getExternalStorageDirectory().getPath());
        long bytesAvailable;

        bytesAvailable = stat.getAvailableBlocksLong() * stat.getBlockSizeLong();
        megAvailable = bytesAvailable / (1024 * 1024);
        return megAvailable;
    }

//

    public List<ActivityManager.RunningServiceInfo> loadProcessInfo() {


        final ActivityManager activityManager = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
        final List<ActivityManager.RunningServiceInfo> recentTasks = activityManager.getRunningServices(Integer.MAX_VALUE);

        return recentTasks;
    }


    public List<ActivityManager.RunningAppProcessInfo> recentApps() {

        final ActivityManager activityManager = (ActivityManager)
                context.getSystemService(Context.ACTIVITY_SERVICE);
        final List<ActivityManager.RunningAppProcessInfo> recentTasks = Objects.requireNonNull(activityManager).getRunningAppProcesses();
        return recentTasks;
    }

    public List<String> GetAllApkInfo() {

        List<String> ApkPackageName = new ArrayList<>();

        Intent intent = new Intent(Intent.ACTION_MAIN, null);

        intent.addCategory(Intent.CATEGORY_LAUNCHER);

        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_RESET_TASK_IF_NEEDED);

        List<ResolveInfo> resolveInfoList = context.getPackageManager().queryIntentActivities(intent, 0);

        for (ResolveInfo resolveInfo : resolveInfoList) {

            ActivityInfo activityInfo = resolveInfo.activityInfo;
            if (!isSTOPPED(activityInfo)) {
                if (!ApkPackageName.contains(activityInfo.applicationInfo.packageName)) {
                    ApkPackageName.add(activityInfo.applicationInfo.packageName);
                }
            }


        }
        return ApkPackageName;
    }

    public boolean isSTOPPED(ActivityInfo activityInfo) {

        return ((activityInfo.applicationInfo.flags & ApplicationInfo.FLAG_STOPPED) != 0);
    }

    public boolean isSystemPackage(ResolveInfo resolveInfo) {

        return ((resolveInfo.activityInfo.applicationInfo.flags & ApplicationInfo.FLAG_SYSTEM) != 0);
    }

    public String getApplicationLabel(Context context, String packageName) {

        PackageManager packageManager = context.getPackageManager();
        List<ApplicationInfo> packages = packageManager.getInstalledApplications(PackageManager.GET_META_DATA);
        String label = null;

        for (int i = 0; i < packages.size(); i++) {

            ApplicationInfo temp = packages.get(i);

            if (temp.packageName.equals(packageName))
                label = packageManager.getApplicationLabel(temp).toString();
        }

        return label;
    }

    public Drawable getAppIconByPackageName(String ApkTempPackageName) {

        Drawable drawable;

        try {
            drawable = context.getPackageManager().getApplicationIcon(ApkTempPackageName);

        } catch (PackageManager.NameNotFoundException e) {

            e.printStackTrace();

            drawable = ContextCompat.getDrawable(context, R.mipmap.ic_launcher);
        }
        return drawable;
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


    public static float getPercentage(float totalData, float usedData) {

        return (usedData * 100 / totalData);
    }

    public static long getFolderSize(String dir) {

        File f = new File(dir);

        String listFiles[] = f.list();
        long totalSize = 0;
        long folderAmount = 0;
        for (String file : listFiles) {

            File folder = new File(dir + "/" + file);
            if (folder.isDirectory()) {
                totalSize += getFolderSize(folder.getAbsolutePath());
                if (totalSize == 0) {
                    folderAmount++;
                }
            } else {
                totalSize += folder.length();
            }
            if (totalSize == 0) {
                folderAmount++;
            }
        }
        return folderAmount;
    }
    public List<String> GetAllInstalledApkInfo() {

        List<String> ApkPackageName = new ArrayList<>();

        Intent intent = new Intent( Intent.ACTION_MAIN, null );

        intent.addCategory( Intent.CATEGORY_LAUNCHER );

        intent.setFlags( Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_RESET_TASK_IF_NEEDED );

        List<ResolveInfo> resolveInfoList = context.getPackageManager().queryIntentActivities( intent, 0 );

        for (ResolveInfo resolveInfo : resolveInfoList) {

            ActivityInfo activityInfo = resolveInfo.activityInfo;

            if (isSystemPackage( resolveInfo ) || !isSystemPackage( resolveInfo )) {
                if (!ApkPackageName.contains( activityInfo.applicationInfo.packageName )) {
                    ApkPackageName.add( activityInfo.applicationInfo.packageName );
                }
            }
        }

        return ApkPackageName;

    }

//    public void moveFile(String sourcePath) {
//
//        File folder = new File(Environment.getExternalStorageDirectory() + "/Phone Master/Status/");
//        String lastName = getRightStringToThePoint(sourcePath,"/");
//        String destinationPath = Environment.getExternalStorageDirectory() + "/Phone Master/Status/" + lastName;
//
//        if (!folder.exists()) {
//            boolean isCreate = folder.mkdirs();
//            if (isCreate)
//            {
//                Toast.makeText(context, "created", Toast.LENGTH_SHORT).show();
//             }
//            else
//            {
//                Toast.makeText(context, "not created", Toast.LENGTH_SHORT).show();
//            }
//
//        }
//        File sourceFile = new File(sourcePath);
//
//        File destinationFile = new File(destinationPath);
//
//        if (sourceFile.renameTo(destinationFile)) {
//            Toast.makeText(context, "Moving file successful.", Toast.LENGTH_SHORT).show();
//
//        } else {
//            Toast.makeText(context, "Moving file failed.", Toast.LENGTH_SHORT).show();
//        }
//    }

    public void copyFileOrDirectory(String srcDir ) {

        try {
            File src = new File(srcDir);
            String lastName = getRightStringToThePoint(srcDir,"/");
            File dst = new File(Environment.getExternalStorageDirectory() + "/Phone Master/Status/", src.getName());

            if (src.isDirectory()) {

                String files[] = src.list();
                int filesLength = files.length;
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
        if (!destFile.getParentFile().exists())
            destFile.getParentFile().mkdirs();

        if (!destFile.exists()) {
            destFile.createNewFile();
        }

        FileChannel source = null;
        FileChannel destination = null;

        try {
            source = new FileInputStream(sourceFile).getChannel();
            destination = new FileOutputStream(destFile).getChannel();
            destination.transferFrom(source, 0, source.size());
        } finally {
            if (source != null) {
                source.close();
            }
            if (destination != null) {
                destination.close();
            }
        }
    }
    public List<NumberAndNamesModel> getContactList() {
        ContentResolver cr = context.getContentResolver();
        Cursor cur = cr.query(ContactsContract.Contacts.CONTENT_URI,
                null, null, null, null);
        List<NumberAndNamesModel> numberAndNamesModelList = new ArrayList<>();

        if ((cur != null ? cur.getCount() : 0) > 0) {
            while (cur != null && cur.moveToNext()) {
                String id = cur.getString(
                        cur.getColumnIndex(ContactsContract.Contacts._ID));
                String name = cur.getString(cur.getColumnIndex(
                        ContactsContract.Contacts.DISPLAY_NAME));

                if (cur.getInt(cur.getColumnIndex(
                        ContactsContract.Contacts.HAS_PHONE_NUMBER)) > 0) {
                    Cursor pCur = cr.query(
                            ContactsContract.CommonDataKinds.Phone.CONTENT_URI,
                            null,
                            ContactsContract.CommonDataKinds.Phone.CONTACT_ID + " = ?",
                            new String[]{id}, null);
                    while (pCur.moveToNext()) {
                        String phoneNo = pCur.getString(pCur.getColumnIndex(
                                ContactsContract.CommonDataKinds.Phone.NUMBER));
                        numberAndNamesModelList.add(new NumberAndNamesModel(name,phoneNo));
                    }
                    pCur.close();
                }
            }
        }
        if(cur!=null){
            cur.close();
        }
        return numberAndNamesModelList;
    }

    public  List<DeepCleanImagesModel> getAllImagePaths() {
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
        column_index_data = cursor.getColumnIndexOrThrow(MediaStore.MediaColumns.DATA);
        while (cursor.moveToNext()) {
            absolutePath = cursor.getString(column_index_data);
            String title = cursor.getString(cursor.getColumnIndexOrThrow(MediaStore.MediaColumns.TITLE));
             Long size = cursor.getLong(cursor.getColumnIndexOrThrow(MediaStore.MediaColumns.SIZE));
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

    public List<DeepCleanVideosModel> getAllVideosPaths()
    {
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
            Long size = cursor.getLong(cursor.getColumnIndexOrThrow(MediaStore.MediaColumns.SIZE));
//            Long size = (new File(absolutePathOfVideo)).length();
            file = new DeepCleanVideosModel();
            file.setVideoPath(absolutePath);
//            file.setSize(size);
//            file.setType(FileTypes.ImageType);
            if (/*isPhoto(absolutePathOfVideo) &&*/ size > 0)
                list.add(file);
        }
        return list ;
    }

    public List<DeepCleanAudioModel> getAllAudiosPaths()
    {
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
            Long size = cursor.getLong(cursor.getColumnIndexOrThrow(MediaStore.MediaColumns.SIZE));
//            Long size = (new File(absolutePathOfVideo)).length();
            file = new DeepCleanAudioModel();
            file.setAudioPath(absolutePath);
            file.setAudioName(title);
//            file.setSize(size);
//            file.setType(FileTypes.ImageType);
            if (/*isPhoto(absolutePathOfVideo) &&*/ size > 0)
                list.add(file);
        }
        return list ;
    }


        public List<DeepCleanDocsModel> getAllDocs( String path)
        {
            File fold = new File(path);
            List<DeepCleanDocsModel> docList = new ArrayList<>();
            File[] mlist = fold.listFiles();
            File[] mFilelist = fold.listFiles(new AllDoFilter());
            for (File f : mlist) {
                if (f.isDirectory()) {
                    List<DeepCleanDocsModel> fList = getAllDocs(f.getAbsolutePath());
                    docList.addAll(fList);
                }
            }
            for (File f : mFilelist) {
                DeepCleanDocsModel doc = new DeepCleanDocsModel();
                doc.setDocName(f.getName());
//                doc.setSize(f.length());
//                doc.setType(FileTypes.DocumentType);
                doc.setDocPath(f.getAbsolutePath());
                if (f.length() > 0)
                    docList.add(doc);
            }
            return docList;
    }


    public List<DeepCleanPackagesModel> getAllPackages(String path)
    {
        File fold = new File(path);
        List<DeepCleanPackagesModel> docList = new ArrayList<>();
        File[] mlist = fold.listFiles();
        File[] mFilelist = fold.listFiles(new AllPackagesFilter());
        for (File f : mlist) {
            if (f.isDirectory()) {
                List<DeepCleanPackagesModel> fList = getAllPackages(f.getAbsolutePath());
                docList.addAll(fList);
            }
        }
        for (File f : mFilelist) {
            DeepCleanPackagesModel doc = new DeepCleanPackagesModel();
            doc.setPkgName(f.getName());
//                doc.setSize(f.length());
//                doc.setType(FileTypes.DocumentType);
            doc.setPkgPath(f.getAbsolutePath());
            if (f.length() > 0)
                docList.add(doc);
        }
        return docList;
    }
    public float getAllSize( String path)
    {
        float docSize = 0;
        File fold = new File(path);
        File[] mlist = fold.listFiles();
        File[] mFilelist = fold.listFiles();
        for (File f : mlist) {
            if (f.isDirectory()) {
                getAllDocs(f.getAbsolutePath());
            }
        }
        for (File f : mFilelist) {
            DeepCleanDocsModel doc = new DeepCleanDocsModel();
            docSize = docSize + f.length();
        }
        return  docSize;
    }


    public float getAllDocSize( String path)
    {
        float docSize = 0;
        File fold = new File(path);
        File[] mlist = fold.listFiles();
        File[] mFilelist = fold.listFiles(new AllDoFilter());
        for (File f : mlist) {
            if (f.isDirectory()) {
                getAllDocs(f.getAbsolutePath());
            }
        }
        for (File f : mFilelist) {
            DeepCleanDocsModel doc = new DeepCleanDocsModel();
            docSize = docSize + f.length();
        }
        return  docSize;
    }
    public float getAllPkgsSize( String path)
    {
        float docSize = 0;
        File fold = new File(path);
        File[] mlist = fold.listFiles();
        File[] mFilelist = fold.listFiles(new AllPackagesFilter());
        for (File f : mlist) {
            if (f.isDirectory()) {
                getAllDocs(f.getAbsolutePath());
            }
        }
        for (File f : mFilelist) {
            DeepCleanDocsModel doc = new DeepCleanDocsModel();
            docSize =docSize + f.length();
        }
        return  docSize;
    }
    // return images audio videos size
    public float getAllIAAsSize( String forWhat)
    {
        Uri uri = null;
        Cursor cursor;

        float size = 0;
        if (forWhat.matches("videos"))
        {
            uri = MediaStore.Video.Media.EXTERNAL_CONTENT_URI;
        }
        else if (forWhat.matches("images"))
        {
            uri = MediaStore.Images.Media.EXTERNAL_CONTENT_URI;

        }
        else if (forWhat.matches("audios"))
        {
            uri = MediaStore.Audio.Media.EXTERNAL_CONTENT_URI;

        }

        String sortOrder = MediaStore.MediaColumns.DATE_MODIFIED + " DESC";
        cursor = context.getContentResolver().query(uri, null, null,
                null, sortOrder);
         while (cursor.moveToNext()) {
               size = size+cursor.getLong(cursor.getColumnIndexOrThrow(MediaStore.MediaColumns.SIZE));

        }
        return size ;
    }
    public void scanaddedFile(String path) {
        try {
            MediaScannerConnection.scanFile(context, new String[] { path },
                    null, new MediaScannerConnection.OnScanCompletedListener() {
                        public void onScanCompleted(String path, Uri uri) {

                           context. getContentResolver()
                                    .delete(uri, null, null);
                        }
                    });
        } catch (Exception e) {
            e.printStackTrace();
        }

    }


    // return Right string from first
    public String getRightStringToThePoint(String s, String pointString) {
        String d = s;
        String ssDate = d.substring(d.lastIndexOf(pointString) + 1);
        return ssDate;
    }

    public String returnLeftOfString(String s, String pointString) {
        String d = s;
        String ssDate = d.substring(d.lastIndexOf(pointString));
        d = d.replace(ssDate, "");
        return d;
    }

    public static class AllDoFilter implements FileFilter {
        @Override
        public boolean accept(File pathname) {
            String path = pathname.getPath();
            return (path.endsWith(".ods") || path.endsWith(".xls") || path.endsWith(".xlsx")
                    || path.endsWith(".doc") || path.endsWith(".odt") || path.endsWith(".docx")
                    || path.endsWith(".pps") || path.endsWith(".pptx") || path.endsWith(".ppt")
                    || path.endsWith(".PDF") || path.endsWith(".pdf") || path.endsWith(".txt")
                    || path.endsWith(".ziip") || path.endsWith(".7z") || path.endsWith(".rar")
                    || path.endsWith(".rpm") || path.endsWith(".tar.gz")
                    || path.endsWith(".z") || path.endsWith(".zip"));
        }
    }
    public static class AllPackagesFilter implements FileFilter {
        @Override
        public boolean accept(File pathname) {
            String path = pathname.getPath();
            return (path.endsWith(".apk") );
        }
    }
    public String getCalculatedDataSize(float size)
    {
        float sizeBytes = size;
        String sizePrefix = "Bytes";
        float finalSize = size;
        if (sizeBytes>=1024)
        {
            float sizeKb =  sizeBytes /1024;
            sizePrefix = "KB";
            finalSize = sizeKb   ;
             if (sizeKb>=1024)
            {
                float sizeMB = sizeKb /1024;
                sizePrefix = "MB";
                finalSize = sizeMB ;
                if (sizeMB>=1024)
                {
                    float sizeGb = sizeMB /1024;
                    sizePrefix = "GB";
                    finalSize = sizeGb  ;

                }

            }
        }
        return String.format("%.2f",finalSize)+sizePrefix;
    }

}