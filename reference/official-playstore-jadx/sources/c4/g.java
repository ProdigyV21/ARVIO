package c4;

import android.os.Build;
import android.os.Process;
import com.google.android.gms.internal.common.zzy;

/* JADX INFO: loaded from: classes4.dex */
public abstract class g {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static String f7408a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static int f7409b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public static Boolean f7410c;

    /* JADX WARN: Can't wrap try/catch for region: R(4:(5:44|13|38|14|15)|(2:45|16)|40|17) */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static java.lang.String a() throws java.lang.Throwable {
        /*
            java.lang.String r0 = c4.g.f7408a
            if (r0 != 0) goto L75
            int r0 = android.os.Build.VERSION.SDK_INT
            r1 = 28
            if (r0 < r1) goto L11
            java.lang.String r0 = android.app.Application.getProcessName()
            c4.g.f7408a = r0
            goto L75
        L11:
            int r0 = c4.g.f7409b
            if (r0 != 0) goto L1b
            int r0 = android.os.Process.myPid()
            c4.g.f7409b = r0
        L1b:
            java.lang.String r1 = "/cmdline"
            java.lang.String r2 = "/proc/"
            r3 = 0
            if (r0 > 0) goto L23
            goto L73
        L23:
            java.lang.String r4 = java.lang.String.valueOf(r0)     // Catch: java.lang.Throwable -> L62 java.io.IOException -> L6f
            int r4 = r4.length()     // Catch: java.lang.Throwable -> L62 java.io.IOException -> L6f
            int r4 = r4 + 14
            java.lang.StringBuilder r5 = new java.lang.StringBuilder     // Catch: java.lang.Throwable -> L62 java.io.IOException -> L6f
            r5.<init>(r4)     // Catch: java.lang.Throwable -> L62 java.io.IOException -> L6f
            r5.append(r2)     // Catch: java.lang.Throwable -> L62 java.io.IOException -> L6f
            r5.append(r0)     // Catch: java.lang.Throwable -> L62 java.io.IOException -> L6f
            r5.append(r1)     // Catch: java.lang.Throwable -> L62 java.io.IOException -> L6f
            java.lang.String r0 = r5.toString()     // Catch: java.lang.Throwable -> L62 java.io.IOException -> L6f
            android.os.StrictMode$ThreadPolicy r1 = android.os.StrictMode.allowThreadDiskReads()     // Catch: java.lang.Throwable -> L62 java.io.IOException -> L6f
            java.io.BufferedReader r2 = new java.io.BufferedReader     // Catch: java.lang.Throwable -> L64
            java.io.FileReader r4 = new java.io.FileReader     // Catch: java.lang.Throwable -> L64
            r4.<init>(r0)     // Catch: java.lang.Throwable -> L64
            r2.<init>(r4)     // Catch: java.lang.Throwable -> L64
            android.os.StrictMode.setThreadPolicy(r1)     // Catch: java.lang.Throwable -> L62 java.io.IOException -> L6f
            java.lang.String r0 = r2.readLine()     // Catch: java.lang.Throwable -> L5f java.io.IOException -> L70
            com.google.android.gms.common.internal.t.i(r0)     // Catch: java.lang.Throwable -> L5f java.io.IOException -> L70
            java.lang.String r3 = r0.trim()     // Catch: java.lang.Throwable -> L5f java.io.IOException -> L70
        L5b:
            r2.close()     // Catch: java.io.IOException -> L73
            goto L73
        L5f:
            r0 = move-exception
            r3 = r2
            goto L69
        L62:
            r0 = move-exception
            goto L69
        L64:
            r0 = move-exception
            android.os.StrictMode.setThreadPolicy(r1)     // Catch: java.lang.Throwable -> L62 java.io.IOException -> L6f
            throw r0     // Catch: java.lang.Throwable -> L62 java.io.IOException -> L6f
        L69:
            if (r3 == 0) goto L6e
            r3.close()     // Catch: java.io.IOException -> L6e
        L6e:
            throw r0
        L6f:
            r2 = r3
        L70:
            if (r2 == 0) goto L73
            goto L5b
        L73:
            c4.g.f7408a = r3
        L75:
            java.lang.String r0 = c4.g.f7408a
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: c4.g.a():java.lang.String");
    }

    public static boolean b() {
        Boolean boolValueOf = f7410c;
        if (boolValueOf == null) {
            if (Build.VERSION.SDK_INT >= 28) {
                boolValueOf = Boolean.valueOf(Process.isIsolated());
            } else {
                try {
                    Object objInvoke = Process.class.getDeclaredMethod("isIsolated", new Class[0]).invoke(null, new Object[0]);
                    Object[] objArr = new Object[0];
                    if (objInvoke == null) {
                        throw new zzy(f4.f.C("expected a non-null reference", objArr));
                    }
                    boolValueOf = (Boolean) objInvoke;
                } catch (ReflectiveOperationException unused) {
                    boolValueOf = Boolean.FALSE;
                }
            }
            f7410c = boolValueOf;
        }
        return boolValueOf.booleanValue();
    }
}
