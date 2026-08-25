package io.sentry.android.core;

import android.app.ActivityManager;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import io.sentry.ILogger;
import io.sentry.w5;
import io.sentry.y6;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

/* JADX INFO: loaded from: classes5.dex */
public final class u0 {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public static volatile u0 f16770i;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public static final io.sentry.util.b f16771j = new io.sentry.util.b();

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final Context f16772a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final SentryAndroidOptions f16773b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final r0 f16774c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final Boolean f16775d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final p0 f16776e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final com.google.android.gms.internal.auth.d1 f16777f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public final io.sentry.protocol.o f16778g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public final Long f16779h;

    public u0(Context context, SentryAndroidOptions sentryAndroidOptions) {
        String str;
        p0 p0Var;
        com.google.android.gms.internal.auth.d1 d1Var;
        Bundle bundle;
        PackageInfo packageInfoG;
        PackageManager packageManager;
        this.f16772a = context;
        this.f16773b = sentryAndroidOptions;
        this.f16774c = new r0(sentryAndroidOptions.getLogger());
        io.sentry.android.core.internal.util.f.f16602c.a();
        io.sentry.protocol.o oVar = new io.sentry.protocol.o();
        oVar.f17653i = "Android";
        oVar.f17654l = Build.VERSION.RELEASE;
        oVar.f17656n = Build.DISPLAY;
        ILogger logger = sentryAndroidOptions.getLogger();
        String property = System.getProperty("os.version");
        File file = new File("/proc/version");
        if (file.canRead()) {
            try {
                BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
                try {
                    String line = bufferedReader.readLine();
                    bufferedReader.close();
                    property = line;
                } finally {
                }
            } catch (IOException e5) {
                logger.l(w5.ERROR, "Exception while attempting to read kernel information", e5);
            }
        }
        if (property != null) {
            oVar.f17657o = property;
        }
        if (sentryAndroidOptions.isEnableRootCheck()) {
            oVar.f17658p = Boolean.valueOf(new io.sentry.android.core.internal.util.j(this.f16772a, sentryAndroidOptions.getLogger(), this.f16774c).a());
        }
        this.f16778g = oVar;
        this.f16775d = this.f16774c.a();
        ILogger logger2 = sentryAndroidOptions.getLogger();
        boolean z = false;
        try {
            packageInfoG = q0.g(context, this.f16774c);
            packageManager = context.getPackageManager();
        } catch (IllegalArgumentException unused) {
            str = null;
        }
        if (packageInfoG == null || packageManager == null) {
            p0Var = null;
        } else {
            str = packageInfoG.packageName;
            try {
                String installerPackageName = packageManager.getInstallerPackageName(str);
                p0Var = new p0(installerPackageName == null, installerPackageName);
            } catch (IllegalArgumentException unused2) {
                logger2.q(w5.DEBUG, "%s package isn't installed.", str);
                p0Var = null;
            }
        }
        this.f16776e = p0Var;
        r0 r0Var = this.f16774c;
        r0Var.getClass();
        ApplicationInfo applicationInfo = Build.VERSION.SDK_INT >= 33 ? (ApplicationInfo) q0.f16730d.a(context) : (ApplicationInfo) q0.f16731e.a(context);
        PackageInfo packageInfoG2 = q0.g(context, r0Var);
        if (packageInfoG2 != null) {
            String[] strArr = packageInfoG2.splitNames;
            if (applicationInfo != null && (bundle = applicationInfo.metaData) != null) {
                z = bundle.getBoolean("com.android.vending.splits.required");
            }
            d1Var = new com.google.android.gms.internal.auth.d1(z, strArr);
        } else {
            d1Var = null;
        }
        this.f16777f = d1Var;
        ActivityManager.MemoryInfo memoryInfoE = q0.e(context, sentryAndroidOptions.getLogger());
        if (memoryInfoE != null) {
            this.f16779h = Long.valueOf(memoryInfoE.totalMem);
        } else {
            this.f16779h = null;
        }
    }

    public static Float b(Intent intent, y6 y6Var) {
        try {
            int intExtra = intent.getIntExtra("level", -1);
            int intExtra2 = intent.getIntExtra("scale", -1);
            if (intExtra != -1 && intExtra2 != -1) {
                return Float.valueOf((intExtra / intExtra2) * 100.0f);
            }
            return null;
        } catch (Throwable th) {
            y6Var.getLogger().l(w5.ERROR, "Error getting device battery level.", th);
            return null;
        }
    }

    public static u0 c(Context context, SentryAndroidOptions sentryAndroidOptions) {
        if (f16770i == null) {
            io.sentry.util.a aVarA = f16771j.a();
            try {
                if (f16770i == null) {
                    Context applicationContext = context.getApplicationContext();
                    if (applicationContext != null) {
                        context = applicationContext;
                    }
                    f16770i = new u0(context, sentryAndroidOptions);
                }
                aVarA.close();
            } catch (Throwable th) {
                try {
                    aVarA.close();
                } catch (Throwable th2) {
                    th.addSuppressed(th2);
                }
                throw th;
            }
        }
        return f16770i;
    }

    public static Boolean d(Intent intent, y6 y6Var) {
        try {
            int intExtra = intent.getIntExtra("plugged", -1);
            boolean z = true;
            if (intExtra != 1 && intExtra != 2) {
                z = false;
            }
            return Boolean.valueOf(z);
        } catch (Throwable th) {
            y6Var.getLogger().l(w5.ERROR, "Error getting device charging state.", th);
            return null;
        }
    }

    /* JADX WARN: Can't wrap try/catch for region: R(30:0|2|(1:4)|5|153|6|141|(1:(21:9|(3:147|15|16)|24|(1:26)|27|159|28|(1:33)|155|34|38|(2:40|(1:42)(1:43))(0)|44|(3:138|46|50)|51|(1:53)|54|(1:56)|57|(11:61|(5:63|157|64|(1:66)(1:70)|71)|72|(1:(1:75)(1:76))(1:77)|78|(1:81)|82|(7:84|143|85|89|136|90|94)|(7:96|151|97|(4:(1:100)(1:101)|102|(3:104|(1:(1:166)(2:161|163))(2:106|165)|113)|162)(1:114)|115|(1:117)(1:120)|(6:149|122|126|145|127|131))|132|(1:134))|135)(1:10))(1:13)|140|(0)|24|(0)|27|159|28|(0)|155|34|38|(0)(0)|44|(0)|51|(0)|54|(0)|57|(12:59|61|(0)|72|(0)(0)|78|(1:81)|82|(0)|(0)|132|(0))|135|(1:(0))) */
    /* JADX WARN: Code restructure failed: missing block: B:30:0x009f, code lost:
    
        r9 = move-exception;
     */
    /* JADX WARN: Code restructure failed: missing block: B:31:0x00a0, code lost:
    
        r8.l(io.sentry.w5.ERROR, "Error getting DisplayMetrics.", r9);
        r8 = null;
     */
    /* JADX WARN: Code restructure failed: missing block: B:36:0x00d8, code lost:
    
        r8 = move-exception;
     */
    /* JADX WARN: Code restructure failed: missing block: B:37:0x00d9, code lost:
    
        r2.getLogger().k(io.sentry.w5.ERROR, r8, "Error getting the device's boot time.", new java.lang.Object[0]);
        r8 = null;
     */
    /* JADX WARN: Removed duplicated region for block: B:134:0x02ca  */
    /* JADX WARN: Removed duplicated region for block: B:138:0x011c A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:147:0x0069 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:26:0x0090  */
    /* JADX WARN: Removed duplicated region for block: B:33:0x00aa  */
    /* JADX WARN: Removed duplicated region for block: B:40:0x00ef  */
    /* JADX WARN: Removed duplicated region for block: B:43:0x010e  */
    /* JADX WARN: Removed duplicated region for block: B:53:0x0138  */
    /* JADX WARN: Removed duplicated region for block: B:56:0x014a  */
    /* JADX WARN: Removed duplicated region for block: B:59:0x016a  */
    /* JADX WARN: Removed duplicated region for block: B:63:0x0181  */
    /* JADX WARN: Removed duplicated region for block: B:74:0x01c0  */
    /* JADX WARN: Removed duplicated region for block: B:77:0x01c7  */
    /* JADX WARN: Removed duplicated region for block: B:84:0x01ed  */
    /* JADX WARN: Removed duplicated region for block: B:96:0x0232  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final io.sentry.protocol.f a(boolean r14, boolean r15) {
        /*
            Method dump skipped, instruction units count: 725
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: io.sentry.android.core.u0.a(boolean, boolean):io.sentry.protocol.f");
    }
}
