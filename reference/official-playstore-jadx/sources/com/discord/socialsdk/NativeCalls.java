package com.discord.socialsdk;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Handler;
import android.os.Looper;
import androidx.activity.n;
import androidx.activity.s;
import com.discord.org.webrtc.i;

/* JADX INFO: loaded from: classes4.dex */
public class NativeCalls {
    private static final String TAG = "DiscordSocialSdk";
    private static DiscordAudioManagerInterface audioManager;

    public static boolean authorize(String str, AuthenticationClientCallback authenticationClientCallback) {
        new Handler(Looper.getMainLooper()).post(new s(str, authenticationClientCallback, 16));
        return true;
    }

    public static void initializeAudioManager() {
        if (audioManager == null) {
            if (Build.VERSION.SDK_INT >= 31) {
                audioManager = DiscordAudioManager.getInstance(DiscordSocialSdkInit.getEngineActivity());
            } else {
                audioManager = DiscordAudioManagerLegacy.getInstance(DiscordSocialSdkInit.getEngineActivity());
            }
        }
    }

    public static boolean isDiscordAppInstalled() {
        Activity engineActivity = DiscordSocialSdkInit.getEngineActivity();
        String[] strArr = {"com.discord", "com.discord.debug"};
        for (int i10 = 0; i10 < 2; i10++) {
            try {
                engineActivity.getPackageManager().getPackageInfo(strArr[i10], 0);
                return true;
            } catch (Exception unused) {
            }
        }
        return false;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ void lambda$authorize$2(String str, AuthenticationClientCallback authenticationClientCallback) {
        AuthenticationActivity.authorize(DiscordSocialSdkInit.getEngineActivity(), str, authenticationClientCallback);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ void lambda$openUrl$5(String str) {
        DiscordSocialSdkInit.getEngineActivity().startActivity(new Intent("android.intent.action.VIEW", Uri.parse(str)));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ void lambda$startForegroundService$3() {
        ForegroundService.start(DiscordSocialSdkInit.getEngineActivity());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ void lambda$stopForegroundService$4() {
        ForegroundService.stop(DiscordSocialSdkInit.getEngineActivity());
    }

    public static void openUrl(String str) {
        new Handler(Looper.getMainLooper()).post(new n(str, 12));
    }

    public static void setActiveCall(boolean z) {
        new Handler(Looper.getMainLooper()).post(new a(z, 1));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void setActiveCallOnMainThread(boolean z) {
        if (audioManager == null) {
            initializeAudioManager();
        }
        audioManager.setActiveCall(z);
    }

    public static void setPreventCommsForBluetooth(boolean z) {
        new Handler(Looper.getMainLooper()).post(new a(z, 0));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void setPreventCommsForBluetoothOnMainThread(boolean z) {
        if (audioManager == null) {
            initializeAudioManager();
        }
        audioManager.setPreventCommsForBluetooth(z);
    }

    public static void startForegroundService() {
        new Handler(Looper.getMainLooper()).post(new i(3));
    }

    public static void stopForegroundService() {
        new Handler(Looper.getMainLooper()).post(new i(2));
    }
}
