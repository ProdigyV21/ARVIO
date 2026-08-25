package com.discord.socialsdk;

import android.app.Activity;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.Service;
import android.content.Intent;
import android.os.Build;
import android.os.IBinder;
import android.util.Log;
import androidx.core.app.d0;
import androidx.media3.extractor.ts.TsExtractor;
import qb.d;

/* JADX INFO: loaded from: classes4.dex */
public class ForegroundService extends Service {
    private static final String CHANNEL_ID = "DiscordSocialSDKChannel";
    private static final int NOTIFICATION_ID = 1;

    private void createNotificationChannel() {
        if (Build.VERSION.SDK_INT >= 26) {
            ((NotificationManager) getSystemService(NotificationManager.class)).createNotificationChannel(new NotificationChannel(CHANNEL_ID, "Discord Social SDK Service", 2));
        }
    }

    public static void start(Activity activity) {
        Log.d("DiscordSocialSdk", "Starting ForegroundService");
        Intent intent = new Intent(activity, (Class<?>) ForegroundService.class);
        if (Build.VERSION.SDK_INT >= 26) {
            activity.startForegroundService(intent);
        } else {
            activity.startService(intent);
        }
    }

    public static void stop(Activity activity) {
        Log.d("DiscordSocialSdk", "Stopping ForegroundService");
        activity.stopService(new Intent(activity, (Class<?>) ForegroundService.class));
    }

    @Override // android.app.Service
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override // android.app.Service
    public int onStartCommand(Intent intent, int i10, int i11) {
        createNotificationChannel();
        d0 d0Var = new d0(this, CHANNEL_ID);
        d0Var.f1966e = d0.b("Discord Social SDK");
        d0Var.f1967f = d0.b("Voice connected");
        d0Var.B.icon = android.R.drawable.ic_dialog_info;
        Notification notificationA = d0Var.a();
        int i12 = d.f(this, "android.permission.RECORD_AUDIO") == 0 ? TsExtractor.TS_STREAM_TYPE_HDMV_DTS : 2;
        if (Build.VERSION.SDK_INT >= 29) {
            startForeground(1, notificationA, i12);
        } else {
            startForeground(1, notificationA);
        }
        return 1;
    }
}
