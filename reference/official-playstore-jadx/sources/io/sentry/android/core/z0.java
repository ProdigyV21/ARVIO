package io.sentry.android.core;

import android.content.Context;
import java.io.File;
import java.io.FileOutputStream;
import java.io.RandomAccessFile;
import java.nio.charset.Charset;

/* JADX INFO: loaded from: classes5.dex */
public abstract class z0 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static String f16832a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static final Charset f16833b = Charset.forName("UTF-8");

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public static final io.sentry.util.b f16834c = new io.sentry.util.b();

    public static String a(Context context) {
        io.sentry.util.a aVarA = f16834c.a();
        try {
            if (f16832a == null) {
                File file = new File(context.getFilesDir(), "INSTALLATION");
                try {
                    boolean zExists = file.exists();
                    Charset charset = f16833b;
                    if (!zExists) {
                        FileOutputStream fileOutputStream = new FileOutputStream(file);
                        try {
                            String strB = qb.d.B();
                            fileOutputStream.write(strB.getBytes(charset));
                            fileOutputStream.flush();
                            fileOutputStream.close();
                            f16832a = strB;
                            aVarA.close();
                            return strB;
                        } finally {
                        }
                    }
                    RandomAccessFile randomAccessFile = new RandomAccessFile(file, "r");
                    try {
                        byte[] bArr = new byte[(int) randomAccessFile.length()];
                        randomAccessFile.readFully(bArr);
                        String str = new String(bArr, charset);
                        randomAccessFile.close();
                        f16832a = str;
                    } finally {
                    }
                } catch (Throwable th) {
                    throw new RuntimeException(th);
                }
            }
            String str2 = f16832a;
            aVarA.close();
            return str2;
        } catch (Throwable th2) {
            try {
                aVarA.close();
            } catch (Throwable th3) {
                th2.addSuppressed(th3);
            }
            throw th2;
        }
    }
}
