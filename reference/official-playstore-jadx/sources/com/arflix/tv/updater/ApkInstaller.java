package com.arflix.tv.updater;

import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInstaller;
import android.net.Uri;
import android.os.Build;
import androidx.core.content.FileProvider;
import androidx.fragment.app.a2;
import androidx.media3.common.C;
import java.io.File;
import java.io.FileInputStream;
import java.io.OutputStream;
import kotlin.Metadata;
import t7.a;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u00008\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\bÇ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0015\u0010\u0007\u001a\u00020\u00062\u0006\u0010\u0005\u001a\u00020\u0004¢\u0006\u0004\b\u0007\u0010\bJ\u0017\u0010\n\u001a\u0004\u0018\u00010\t2\u0006\u0010\u0005\u001a\u00020\u0004¢\u0006\u0004\b\n\u0010\u000bJ\u001f\u0010\u000f\u001a\u0004\u0018\u00010\u000e2\u0006\u0010\u0005\u001a\u00020\u00042\u0006\u0010\r\u001a\u00020\f¢\u0006\u0004\b\u000f\u0010\u0010J\u0015\u0010\u0011\u001a\u00020\t2\u0006\u0010\u0005\u001a\u00020\u0004¢\u0006\u0004\b\u0011\u0010\u000bJ\u001d\u0010\u0013\u001a\u00020\u00122\u0006\u0010\u0005\u001a\u00020\u00042\u0006\u0010\r\u001a\u00020\f¢\u0006\u0004\b\u0013\u0010\u0014¨\u0006\u0015"}, d2 = {"Lcom/arflix/tv/updater/ApkInstaller;", "", "<init>", "()V", "Landroid/content/Context;", "context", "", "canRequestPackageInstalls", "(Landroid/content/Context;)Z", "Landroid/content/Intent;", "buildUnknownSourcesSettingsIntent", "(Landroid/content/Context;)Landroid/content/Intent;", "Ljava/io/File;", "apkFile", "", "checkSignatureConflict", "(Landroid/content/Context;Ljava/io/File;)Ljava/lang/String;", "buildUninstallIntent", "Lx6/t0;", "launchInstall", "(Landroid/content/Context;Ljava/io/File;)V", "app_playRelease"}, k = 1, mv = {2, 3, 0}, xi = 48)
public final class ApkInstaller {
    public static final int $stable = 0;
    public static final ApkInstaller INSTANCE = new ApkInstaller();

    private ApkInstaller() {
    }

    public final Intent buildUninstallIntent(Context context) {
        return new Intent("android.intent.action.DELETE", Uri.parse("package:" + context.getPackageName())).addFlags(268435456);
    }

    public final Intent buildUnknownSourcesSettingsIntent(Context context) {
        if (Build.VERSION.SDK_INT < 26) {
            return null;
        }
        return new Intent("android.settings.MANAGE_UNKNOWN_APP_SOURCES", Uri.parse("package:" + context.getPackageName())).addFlags(268435456);
    }

    public final boolean canRequestPackageInstalls(Context context) {
        if (Build.VERSION.SDK_INT >= 26) {
            return context.getPackageManager().canRequestPackageInstalls();
        }
        return true;
    }

    /* JADX WARN: Removed duplicated region for block: B:24:0x006f  */
    /* JADX WARN: Removed duplicated region for block: B:33:0x0099 A[Catch: NameNotFoundException | Exception -> 0x010b, TryCatch #0 {NameNotFoundException | Exception -> 0x010b, blocks: (B:3:0x0001, B:5:0x0010, B:8:0x0027, B:12:0x002f, B:17:0x0045, B:19:0x004b, B:21:0x0051, B:23:0x005b, B:31:0x0095, B:33:0x0099, B:35:0x009f, B:37:0x00a5, B:39:0x00af, B:45:0x00e5, B:47:0x00eb, B:49:0x00f1, B:51:0x00f7, B:40:0x00c3, B:42:0x00c7, B:44:0x00d1, B:26:0x0073, B:28:0x0077, B:30:0x0081, B:13:0x0038, B:6:0x001d), top: B:55:0x0001 }] */
    /* JADX WARN: Removed duplicated region for block: B:40:0x00c3 A[Catch: NameNotFoundException | Exception -> 0x010b, TryCatch #0 {NameNotFoundException | Exception -> 0x010b, blocks: (B:3:0x0001, B:5:0x0010, B:8:0x0027, B:12:0x002f, B:17:0x0045, B:19:0x004b, B:21:0x0051, B:23:0x005b, B:31:0x0095, B:33:0x0099, B:35:0x009f, B:37:0x00a5, B:39:0x00af, B:45:0x00e5, B:47:0x00eb, B:49:0x00f1, B:51:0x00f7, B:40:0x00c3, B:42:0x00c7, B:44:0x00d1, B:26:0x0073, B:28:0x0077, B:30:0x0081, B:13:0x0038, B:6:0x001d), top: B:55:0x0001 }] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.String checkSignatureConflict(android.content.Context r11, java.io.File r12) {
        /*
            Method dump skipped, instruction units count: 268
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.arflix.tv.updater.ApkInstaller.checkSignatureConflict(android.content.Context, java.io.File):java.lang.String");
    }

    public final void launchInstall(Context context, File apkFile) {
        int i10 = Build.VERSION.SDK_INT;
        try {
            PackageInstaller packageInstaller = context.getPackageManager().getPackageInstaller();
            PackageInstaller.SessionParams sessionParams = new PackageInstaller.SessionParams(1);
            sessionParams.setSize(apkFile.length());
            int iCreateSession = packageInstaller.createSession(sessionParams);
            PackageInstaller.Session sessionOpenSession = packageInstaller.openSession(iCreateSession);
            OutputStream outputStreamOpenWrite = sessionOpenSession.openWrite("app_update", 0L, apkFile.length());
            try {
                FileInputStream fileInputStream = new FileInputStream(apkFile);
                try {
                    a.k(fileInputStream, outputStreamOpenWrite);
                    fileInputStream.close();
                    sessionOpenSession.fsync(outputStreamOpenWrite);
                    outputStreamOpenWrite.close();
                    sessionOpenSession.commit(PendingIntent.getBroadcast(context.getApplicationContext(), iCreateSession, new Intent(ApkInstallReceiver.INSTANCE.actionFor(context)).setPackage(context.getPackageName()), i10 >= 31 ? 167772160 : C.BUFFER_FLAG_FIRST_SAMPLE).getIntentSender());
                    sessionOpenSession.close();
                } finally {
                }
            } finally {
            }
        } catch (Exception e5) {
            a2.v("[ApkInstaller] Session install failed, falling back to ACTION_VIEW: ", e5.getMessage(), System.err);
            try {
                context.startActivity(new Intent("android.intent.action.VIEW").setDataAndType(FileProvider.d(context, apkFile), "application/vnd.android.package-archive").addFlags(1).addFlags(268435456));
            } catch (Exception e6) {
                a2.v("[ApkInstaller] Fallback ACTION_VIEW install failed: ", e6.getMessage(), System.err);
            }
        }
    }
}
