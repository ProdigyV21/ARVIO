package com.google.android.gms.cast;

import android.text.TextUtils;
import com.google.android.gms.cast.internal.CastUtils;
import java.util.Collection;
import java.util.Locale;

/* JADX INFO: loaded from: classes4.dex */
public final class CastMediaControlIntent {
    public static final String ACTION_SYNC_STATUS = "com.google.android.gms.cast.ACTION_SYNC_STATUS";
    public static final String DEFAULT_MEDIA_RECEIVER_APPLICATION_ID = "CC1AD845";
    public static final int ERROR_CODE_REQUEST_FAILED = 1;
    public static final int ERROR_CODE_SESSION_START_FAILED = 2;
    public static final int ERROR_CODE_TEMPORARILY_DISCONNECTED = 3;
    public static final String EXTRA_CAST_APPLICATION_ID = "com.google.android.gms.cast.EXTRA_CAST_APPLICATION_ID";
    public static final String EXTRA_CAST_LANGUAGE_CODE = "com.google.android.gms.cast.EXTRA_CAST_LANGUAGE_CODE";
    public static final String EXTRA_CAST_RELAUNCH_APPLICATION = "com.google.android.gms.cast.EXTRA_CAST_RELAUNCH_APPLICATION";
    public static final String EXTRA_CAST_STOP_APPLICATION_WHEN_SESSION_ENDS = "com.google.android.gms.cast.EXTRA_CAST_STOP_APPLICATION_WHEN_SESSION_ENDS";
    public static final String EXTRA_CUSTOM_DATA = "com.google.android.gms.cast.EXTRA_CUSTOM_DATA";
    public static final String EXTRA_DEBUG_LOGGING_ENABLED = "com.google.android.gms.cast.EXTRA_DEBUG_LOGGING_ENABLED";
    public static final String EXTRA_ERROR_CODE = "com.google.android.gms.cast.EXTRA_ERROR_CODE";

    private CastMediaControlIntent() {
    }

    public static String categoryForCast(String str) throws IllegalArgumentException {
        if (str == null) {
            throw new IllegalArgumentException("applicationId cannot be null");
        }
        zzs zzsVar = new zzs(null);
        zzsVar.zzb(str);
        return zzsVar.zzd().zza();
    }

    public static String categoryForRemotePlayback() {
        zzs zzsVar = new zzs(null);
        zzsVar.zza("com.google.android.gms.cast.CATEGORY_CAST_REMOTE_PLAYBACK");
        return zzsVar.zzd().zza();
    }

    public static String languageTagForLocale(Locale locale) {
        return CastUtils.zzd(locale);
    }

    public static String categoryForRemotePlayback(String str) throws IllegalArgumentException {
        if (!TextUtils.isEmpty(str)) {
            zzs zzsVar = new zzs(null);
            zzsVar.zza("com.google.android.gms.cast.CATEGORY_CAST_REMOTE_PLAYBACK");
            zzsVar.zzb(str);
            return zzsVar.zzd().zza();
        }
        throw new IllegalArgumentException("applicationId cannot be null or empty");
    }

    public static String categoryForCast(String str, Collection<String> collection) {
        if (str == null) {
            throw new IllegalArgumentException("applicationId cannot be null");
        }
        if (collection != null) {
            zzs zzsVar = new zzs(null);
            zzsVar.zzb(str);
            zzsVar.zzc(collection);
            return zzsVar.zzd().zza();
        }
        throw new IllegalArgumentException("namespaces cannot be null");
    }

    public static String categoryForCast(Collection<String> collection) throws IllegalArgumentException {
        if (collection != null) {
            zzs zzsVar = new zzs(null);
            zzsVar.zzc(collection);
            return zzsVar.zzd().zza();
        }
        throw new IllegalArgumentException("namespaces cannot be null");
    }
}
