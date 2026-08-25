package io.sentry.android.core.internal.util;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

/* JADX INFO: loaded from: classes5.dex */
public final class f {

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public static final f f16602c = new f();

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final io.sentry.util.b f16603a = new io.sentry.util.b();

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final ArrayList f16604b = new ArrayList();

    public final ArrayList a() {
        ArrayList arrayList = this.f16604b;
        io.sentry.util.a aVarA = this.f16603a.a();
        try {
            if (!arrayList.isEmpty()) {
                aVarA.close();
                return arrayList;
            }
            File[] fileArrListFiles = new File("/sys/devices/system/cpu").listFiles();
            if (fileArrListFiles == null) {
                ArrayList arrayList2 = new ArrayList();
                aVarA.close();
                return arrayList2;
            }
            for (File file : fileArrListFiles) {
                if (file.getName().matches("cpu[0-9]+")) {
                    try {
                        String strM = qb.d.M(new File(file, "cpufreq/cpuinfo_max_freq"));
                        if (strM != null) {
                            arrayList.add(Integer.valueOf((int) (Long.parseLong(strM.trim()) / 1000)));
                        }
                    } catch (IOException | NumberFormatException unused) {
                    }
                }
            }
            aVarA.close();
            return arrayList;
        } catch (Throwable th) {
            try {
                aVarA.close();
            } catch (Throwable th2) {
                th.addSuppressed(th2);
            }
            throw th;
        }
    }
}
