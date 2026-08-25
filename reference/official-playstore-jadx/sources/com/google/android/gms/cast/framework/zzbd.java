package com.google.android.gms.cast.framework;

import android.content.Context;
import android.preference.PreferenceManager;

/* JADX INFO: loaded from: classes4.dex */
public final class zzbd {
    public static void zza(Context context) {
        PreferenceManager.getDefaultSharedPreferences(context).edit().putBoolean("googlecast-introOverlayShown", true).apply();
    }
}
