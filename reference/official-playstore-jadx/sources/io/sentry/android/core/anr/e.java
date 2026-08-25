package io.sentry.android.core.anr;

import java.io.File;
import java.util.concurrent.atomic.AtomicBoolean;

/* JADX INFO: loaded from: classes5.dex */
public abstract class e {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final AtomicBoolean f16433a = new AtomicBoolean(true);

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static final Object f16434b = new Object();

    public static boolean a(File file) {
        File file2 = new File(file, "anr_profile_old");
        if (file2.exists()) {
            return file2.delete();
        }
        return true;
    }

    public static void b(File file) {
        AtomicBoolean atomicBoolean = f16433a;
        if (atomicBoolean.get()) {
            synchronized (f16434b) {
                try {
                    if (atomicBoolean.get()) {
                        File file2 = new File(file, "anr_profile");
                        File file3 = new File(file, "anr_profile_old");
                        try {
                            file3.delete();
                        } catch (Throwable unused) {
                        }
                        try {
                            file2.renameTo(file3);
                        } catch (Throwable unused2) {
                        }
                        f16433a.set(false);
                    }
                } catch (Throwable th) {
                    throw th;
                }
            }
        }
    }
}
