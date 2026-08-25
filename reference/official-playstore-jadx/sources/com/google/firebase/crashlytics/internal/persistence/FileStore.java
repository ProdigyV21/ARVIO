package com.google.firebase.crashlytics.internal.persistence;

import android.app.Application;
import android.content.Context;
import android.os.Build;
import com.google.firebase.crashlytics.internal.Logger;
import java.io.File;
import java.io.FilenameFilter;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/* JADX INFO: loaded from: classes4.dex */
public class FileStore {
    private static final String CRASHLYTICS_PATH_V1 = ".com.google.firebase.crashlytics.files.v1";
    private static final String CRASHLYTICS_PATH_V2 = ".com.google.firebase.crashlytics.files.v2";
    private static final String NATIVE_REPORTS_PATH = "native-reports";
    private static final String NATIVE_SESSION_SUBDIR = "native";
    private static final String PRIORITY_REPORTS_PATH = "priority-reports";
    private static final String REPORTS_PATH = "reports";
    private static final String SESSIONS_PATH = "open-sessions";
    private final File crashlyticsDir;
    private final File filesDir;
    private final File nativeReportsDir;
    private final File priorityReportsDir;
    private final File reportsDir;
    private final File sessionsDir;

    public FileStore(Context context) {
        String str;
        File filesDir = context.getFilesDir();
        this.filesDir = filesDir;
        if (useV2FileSystem()) {
            str = CRASHLYTICS_PATH_V2 + File.pathSeparator + sanitizeName(Application.getProcessName());
        } else {
            str = CRASHLYTICS_PATH_V1;
        }
        File filePrepareBaseDir = prepareBaseDir(new File(filesDir, str));
        this.crashlyticsDir = filePrepareBaseDir;
        this.sessionsDir = prepareBaseDir(new File(filePrepareBaseDir, SESSIONS_PATH));
        this.reportsDir = prepareBaseDir(new File(filePrepareBaseDir, REPORTS_PATH));
        this.priorityReportsDir = prepareBaseDir(new File(filePrepareBaseDir, PRIORITY_REPORTS_PATH));
        this.nativeReportsDir = prepareBaseDir(new File(filePrepareBaseDir, NATIVE_REPORTS_PATH));
    }

    private void cleanupDir(File file) {
        if (file.exists() && recursiveDelete(file)) {
            Logger.getLogger().d("Deleted previous Crashlytics file system: " + file.getPath());
        }
    }

    private File getSessionDir(String str) {
        return prepareDir(new File(this.sessionsDir, str));
    }

    private static synchronized File prepareBaseDir(File file) {
        try {
            if (file.exists()) {
                if (file.isDirectory()) {
                    return file;
                }
                Logger.getLogger().d("Unexpected non-directory file: " + file + "; deleting file and creating new directory.");
                file.delete();
            }
            if (!file.mkdirs()) {
                Logger.getLogger().e("Could not create Crashlytics-specific directory: " + file);
            }
            return file;
        } catch (Throwable th) {
            throw th;
        }
    }

    private static File prepareDir(File file) {
        file.mkdirs();
        return file;
    }

    public static boolean recursiveDelete(File file) {
        File[] fileArrListFiles = file.listFiles();
        if (fileArrListFiles != null) {
            for (File file2 : fileArrListFiles) {
                recursiveDelete(file2);
            }
        }
        return file.delete();
    }

    private static <T> List<T> safeArrayToList(T[] tArr) {
        return tArr == null ? Collections.EMPTY_LIST : Arrays.asList(tArr);
    }

    public static String sanitizeName(String str) {
        return str.replaceAll("[^a-zA-Z0-9.]", "_");
    }

    private static boolean useV2FileSystem() {
        return Build.VERSION.SDK_INT >= 28;
    }

    public void cleanupPreviousFileSystems() {
        cleanupDir(new File(this.filesDir, ".com.google.firebase.crashlytics"));
        cleanupDir(new File(this.filesDir, ".com.google.firebase.crashlytics-ndk"));
        if (useV2FileSystem()) {
            cleanupDir(new File(this.filesDir, CRASHLYTICS_PATH_V1));
        }
    }

    public void deleteAllCrashlyticsFiles() {
        recursiveDelete(this.crashlyticsDir);
    }

    public boolean deleteSessionFiles(String str) {
        return recursiveDelete(new File(this.sessionsDir, str));
    }

    public List<String> getAllOpenSessionIds() {
        return safeArrayToList(this.sessionsDir.list());
    }

    public File getCommonFile(String str) {
        return new File(this.crashlyticsDir, str);
    }

    public List<File> getCommonFiles(FilenameFilter filenameFilter) {
        return safeArrayToList(this.crashlyticsDir.listFiles(filenameFilter));
    }

    public File getNativeReport(String str) {
        return new File(this.nativeReportsDir, str);
    }

    public List<File> getNativeReports() {
        return safeArrayToList(this.nativeReportsDir.listFiles());
    }

    public File getNativeSessionDir(String str) {
        return prepareDir(new File(getSessionDir(str), NATIVE_SESSION_SUBDIR));
    }

    public File getPriorityReport(String str) {
        return new File(this.priorityReportsDir, str);
    }

    public List<File> getPriorityReports() {
        return safeArrayToList(this.priorityReportsDir.listFiles());
    }

    public File getReport(String str) {
        return new File(this.reportsDir, str);
    }

    public List<File> getReports() {
        return safeArrayToList(this.reportsDir.listFiles());
    }

    public File getSessionFile(String str, String str2) {
        return new File(getSessionDir(str), str2);
    }

    public List<File> getSessionFiles(String str, FilenameFilter filenameFilter) {
        return safeArrayToList(getSessionDir(str).listFiles(filenameFilter));
    }
}
