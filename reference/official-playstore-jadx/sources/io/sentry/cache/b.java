package io.sentry.cache;

import io.sentry.w5;
import io.sentry.y6;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.nio.charset.Charset;

/* JADX INFO: loaded from: classes5.dex */
public abstract class b {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final Charset f17146a = Charset.forName("UTF-8");

    public static void a(y6 y6Var, String str, String str2) {
        File fileB = b(y6Var, str);
        if (fileB == null) {
            y6Var.getLogger().q(w5.INFO, "Cache dir is not set, cannot delete from scope cache", new Object[0]);
            return;
        }
        File file = new File(fileB, str2);
        y6Var.getLogger().q(w5.DEBUG, "Deleting %s from scope cache", str2);
        if (file.delete()) {
            return;
        }
        y6Var.getLogger().q(w5.INFO, "Failed to delete: %s", file.getAbsolutePath());
    }

    public static File b(y6 y6Var, String str) {
        String cacheDirPath = y6Var.getCacheDirPath();
        if (cacheDirPath == null) {
            return null;
        }
        File file = new File(cacheDirPath, str);
        file.mkdirs();
        return file;
    }

    public static Object c(y6 y6Var, String str, String str2, Class cls) {
        File fileB = b(y6Var, str);
        if (fileB == null) {
            y6Var.getLogger().q(w5.INFO, "Cache dir is not set, cannot read from scope cache", new Object[0]);
            return null;
        }
        File file = new File(fileB, str2);
        if (!file.exists()) {
            y6Var.getLogger().q(w5.DEBUG, "No entry stored for %s", str2);
            return null;
        }
        try {
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(new FileInputStream(file), f17146a));
            try {
                Object objE = y6Var.getSerializer().e(bufferedReader, cls);
                bufferedReader.close();
                return objE;
            } finally {
            }
        } catch (Throwable th) {
            y6Var.getLogger().k(w5.ERROR, th, "Error reading entity from scope cache: %s", str2);
            return null;
        }
    }

    public static void d(y6 y6Var, Object obj, String str, String str2) {
        File fileB = b(y6Var, str);
        if (fileB == null) {
            y6Var.getLogger().q(w5.INFO, "Cache dir is not set, cannot store in scope cache", new Object[0]);
            return;
        }
        try {
            FileOutputStream fileOutputStream = new FileOutputStream(new File(fileB, str2));
            try {
                BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(fileOutputStream, f17146a));
                try {
                    y6Var.getSerializer().a(bufferedWriter, obj);
                    bufferedWriter.close();
                    fileOutputStream.close();
                } finally {
                }
            } finally {
            }
        } catch (Throwable th) {
            y6Var.getLogger().k(w5.ERROR, th, "Error persisting entity: %s", str2);
        }
    }
}
