package com.discord.socialsdk;

import android.app.Activity;
import android.content.Context;
import android.util.Log;
import com.discord.org.webrtc.ContextUtils;

/* JADX INFO: loaded from: classes4.dex */
public class DiscordSocialSdkInit {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final /* synthetic */ int f12554a = 0;
    private static Activity engineActivity;

    static {
        Log.d("DiscordSocialSdk", "Loading native library");
        System.loadLibrary("discord_partner_sdk");
    }

    public static Context getApplicationContext() {
        return engineActivity.getApplicationContext();
    }

    public static Activity getEngineActivity() {
        return engineActivity;
    }

    private static native void nativeSetContext(Context context);

    public static void setEngineActivity(Activity activity) {
        engineActivity = activity;
        Context applicationContext = activity.getApplicationContext();
        ContextUtils.initialize(applicationContext);
        nativeSetContext(applicationContext);
    }
}
