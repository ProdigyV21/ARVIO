package io.sentry.android.core;

import android.app.ActivityManager;
import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Handler;
import io.sentry.ILogger;
import io.sentry.w5;
import java.util.Iterator;

/* JADX INFO: loaded from: classes5.dex */
public abstract class q0 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final io.sentry.android.core.util.b f16727a = new io.sentry.android.core.util.b(new com.google.firebase.installations.d(28));

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static final io.sentry.android.core.util.b f16728b = new io.sentry.android.core.util.b(new com.google.firebase.installations.d(29));

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public static final io.sentry.android.core.util.b f16729c = new io.sentry.android.core.util.b(new o0(0));

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public static final io.sentry.android.core.util.b f16730d = new io.sentry.android.core.util.b(new o0(1));

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public static final io.sentry.android.core.util.b f16731e = new io.sentry.android.core.util.b(new o0(2));

    public static /* synthetic */ ApplicationInfo a(Context context) {
        try {
            return context.getPackageManager().getApplicationInfo(context.getPackageName(), PackageManager.ApplicationInfoFlags.of(128L));
        } catch (Throwable unused) {
            return null;
        }
    }

    public static /* synthetic */ PackageInfo b(Context context) {
        try {
            return context.getPackageManager().getPackageInfo(context.getPackageName(), PackageManager.PackageInfoFlags.of(0L));
        } catch (Throwable unused) {
            return null;
        }
    }

    public static boolean c(Context context) {
        if (!context.getPackageName().endsWith(".test")) {
            return false;
        }
        try {
            Iterator<ActivityManager.AppTask> it = ((ActivityManager) context.getSystemService("activity")).getAppTasks().iterator();
            while (it.hasNext()) {
                ComponentName component = it.next().getTaskInfo().baseIntent.getComponent();
                if (component != null && component.getClassName().equals("androidx.compose.ui.tooling.PreviewActivity")) {
                    return true;
                }
            }
            return false;
        } catch (Throwable unused) {
            return false;
        }
    }

    public static String d(ILogger iLogger) {
        try {
            return Build.MODEL.split(" ", -1)[0];
        } catch (Throwable th) {
            iLogger.l(w5.ERROR, "Error getting device family.", th);
            return null;
        }
    }

    public static ActivityManager.MemoryInfo e(Context context, ILogger iLogger) {
        try {
            ActivityManager activityManager = (ActivityManager) context.getSystemService("activity");
            ActivityManager.MemoryInfo memoryInfo = new ActivityManager.MemoryInfo();
            if (activityManager != null) {
                activityManager.getMemoryInfo(memoryInfo);
                return memoryInfo;
            }
            iLogger.q(w5.INFO, "Error getting MemoryInfo.", new Object[0]);
            return null;
        } catch (Throwable th) {
            iLogger.l(w5.ERROR, "Error getting MemoryInfo.", th);
            return null;
        }
    }

    public static PackageInfo f(Context context, ILogger iLogger, r0 r0Var) {
        try {
            r0Var.getClass();
            return Build.VERSION.SDK_INT >= 33 ? context.getPackageManager().getPackageInfo(context.getPackageName(), PackageManager.PackageInfoFlags.of(4096)) : context.getPackageManager().getPackageInfo(context.getPackageName(), 4096);
        } catch (Throwable th) {
            iLogger.l(w5.ERROR, "Error getting package info.", th);
            return null;
        }
    }

    public static PackageInfo g(Context context, r0 r0Var) {
        r0Var.getClass();
        return Build.VERSION.SDK_INT >= 33 ? (PackageInfo) f16727a.a(context) : (PackageInfo) f16728b.a(context);
    }

    public static String h(PackageInfo packageInfo, r0 r0Var) {
        r0Var.getClass();
        return Build.VERSION.SDK_INT >= 28 ? Long.toString(packageInfo.getLongVersionCode()) : Integer.toString(packageInfo.versionCode);
    }

    public static boolean i() {
        try {
            ActivityManager.RunningAppProcessInfo runningAppProcessInfo = new ActivityManager.RunningAppProcessInfo();
            ActivityManager.getMyMemoryState(runningAppProcessInfo);
            return runningAppProcessInfo.importance == 100;
        } catch (Throwable unused) {
            return false;
        }
    }

    public static Intent j(Context context, r0 r0Var, BroadcastReceiver broadcastReceiver, IntentFilter intentFilter, Handler handler) {
        r0Var.getClass();
        return Build.VERSION.SDK_INT >= 33 ? context.registerReceiver(broadcastReceiver, intentFilter, null, handler, 4) : context.registerReceiver(broadcastReceiver, intentFilter, null, handler);
    }
}
