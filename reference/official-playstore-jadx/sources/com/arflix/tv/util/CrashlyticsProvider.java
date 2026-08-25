package com.arflix.tv.util;

import androidx.media3.exoplayer.offline.DownloadService;
import com.arflix.tv.BuildConfig;
import com.arflix.tv.util.AppLogger;
import com.google.firebase.crashlytics.FirebaseCrashlytics;
import java.net.URL;
import kotlin.Metadata;
import kotlin.text.o;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000D\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0010\u0003\n\u0002\b\b\n\u0002\u0010\t\n\u0002\b\r\n\u0002\u0010\u0000\n\u0002\b\b\bÇ\n\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0017\u0010\u0006\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0004H\u0002¢\u0006\u0004\b\u0006\u0010\u0007J\r\u0010\t\u001a\u00020\b¢\u0006\u0004\b\t\u0010\nJ\r\u0010\f\u001a\u00020\u000b¢\u0006\u0004\b\f\u0010\u0003J\u001f\u0010\u000f\u001a\u00020\u000b2\u0006\u0010\r\u001a\u00020\u00042\u0006\u0010\u000e\u001a\u00020\u0004H\u0016¢\u0006\u0004\b\u000f\u0010\u0010J\u001f\u0010\u000f\u001a\u00020\u000b2\u0006\u0010\r\u001a\u00020\u00042\u0006\u0010\u000e\u001a\u00020\u0011H\u0016¢\u0006\u0004\b\u000f\u0010\u0012J\u001f\u0010\u000f\u001a\u00020\u000b2\u0006\u0010\r\u001a\u00020\u00042\u0006\u0010\u000e\u001a\u00020\bH\u0016¢\u0006\u0004\b\u000f\u0010\u0013J\u0017\u0010\u0015\u001a\u00020\u000b2\u0006\u0010\u0014\u001a\u00020\u0004H\u0016¢\u0006\u0004\b\u0015\u0010\u0016J\u0017\u0010\u0019\u001a\u00020\u000b2\u0006\u0010\u0018\u001a\u00020\u0017H\u0016¢\u0006\u0004\b\u0019\u0010\u001aJ\u0019\u0010\u001c\u001a\u00020\u000b2\b\u0010\u001b\u001a\u0004\u0018\u00010\u0004H\u0016¢\u0006\u0004\b\u001c\u0010\u0016J?\u0010#\u001a\u00020\u000b2\b\u0010\u001d\u001a\u0004\u0018\u00010\u00042\b\u0010\u001e\u001a\u0004\u0018\u00010\u00042\b\u0010\u001f\u001a\u0004\u0018\u00010\u00042\b\u0010!\u001a\u0004\u0018\u00010 2\b\u0010\"\u001a\u0004\u0018\u00010 ¢\u0006\u0004\b#\u0010$J\u0015\u0010&\u001a\u00020\u000b2\u0006\u0010%\u001a\u00020\u0004¢\u0006\u0004\b&\u0010\u0016J\u001d\u0010)\u001a\u00020\u000b2\u0006\u0010'\u001a\u00020\u00042\u0006\u0010(\u001a\u00020\b¢\u0006\u0004\b)\u0010\u0013J\u0010\u0010*\u001a\u00020\u0004HÖ\u0001¢\u0006\u0004\b*\u0010+J\u0010\u0010,\u001a\u00020\u0011HÖ\u0001¢\u0006\u0004\b,\u0010-J\u001a\u00100\u001a\u00020\b2\b\u0010/\u001a\u0004\u0018\u00010.HÖ\u0003¢\u0006\u0004\b0\u00101R\u0014\u00102\u001a\u00020\u00048\u0002X\u0082T¢\u0006\u0006\n\u0004\b2\u00103R\u0016\u00104\u001a\u00020\b8\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b4\u00105¨\u00066"}, d2 = {"Lcom/arflix/tv/util/CrashlyticsProvider;", "Lcom/arflix/tv/util/AppLogger$CrashContextProvider;", "<init>", "()V", "", "url", "extractHost", "(Ljava/lang/String;)Ljava/lang/String;", "", "initialize", "()Z", "Lx6/t0;", "disable", "key", "value", "setCustomKey", "(Ljava/lang/String;Ljava/lang/String;)V", "", "(Ljava/lang/String;I)V", "(Ljava/lang/String;Z)V", "message", "log", "(Ljava/lang/String;)V", "", "throwable", "recordException", "(Ljava/lang/Throwable;)V", "userId", "setUserId", "contentId", "contentType", "streamUrl", "", "position", "duration", "setPlaybackContext", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/Long;Ljava/lang/Long;)V", "screenName", "setScreenContext", "networkType", "isConnected", "setNetworkContext", "toString", "()Ljava/lang/String;", "hashCode", "()I", "", "other", "equals", "(Ljava/lang/Object;)Z", "TAG", "Ljava/lang/String;", "isInitialized", "Z", "app_playRelease"}, k = 1, mv = {2, 3, 0}, xi = 48)
public final /* data */ class CrashlyticsProvider implements AppLogger.CrashContextProvider {
    private static final String TAG = "Crashlytics";
    private static boolean isInitialized;
    public static final CrashlyticsProvider INSTANCE = new CrashlyticsProvider();
    public static final int $stable = 8;

    private CrashlyticsProvider() {
    }

    private final String extractHost(String url) {
        try {
            return new URL(url).getHost();
        } catch (Exception unused) {
            return "invalid_url";
        }
    }

    public final void disable() {
        isInitialized = false;
        try {
            FirebaseCrashlytics firebaseCrashlytics = FirebaseCrashlytics.getInstance();
            firebaseCrashlytics.setCrashlyticsCollectionEnabled(false);
            firebaseCrashlytics.deleteUnsentReports();
        } catch (Throwable unused) {
        }
    }

    public boolean equals(Object other) {
        return this == other || (other instanceof CrashlyticsProvider);
    }

    public int hashCode() {
        return 45172687;
    }

    public final boolean initialize() {
        if (!BuildConfig.ENABLE_CRASH_REPORTING.booleanValue()) {
            isInitialized = false;
            AppLogger.INSTANCE.init(null);
            return false;
        }
        try {
            FirebaseCrashlytics.getInstance().setCrashlyticsCollectionEnabled(true);
            isInitialized = true;
            AppLogger.INSTANCE.init(this);
            return true;
        } catch (Exception unused) {
            isInitialized = false;
            AppLogger.INSTANCE.init(null);
            return false;
        }
    }

    @Override // com.arflix.tv.util.AppLogger.CrashContextProvider
    public void log(String message) {
        if (isInitialized) {
            try {
                FirebaseCrashlytics.getInstance().log(o.I0(500, message));
            } catch (Exception unused) {
            }
        }
    }

    @Override // com.arflix.tv.util.AppLogger.CrashContextProvider
    public void recordException(Throwable throwable) {
        if (isInitialized) {
            try {
                FirebaseCrashlytics.getInstance().recordException(throwable);
            } catch (Exception unused) {
            }
        }
    }

    @Override // com.arflix.tv.util.AppLogger.CrashContextProvider
    public void setCustomKey(String key, String value) {
        if (isInitialized) {
            try {
                FirebaseCrashlytics.getInstance().setCustomKey(key, value);
            } catch (Exception unused) {
            }
        }
    }

    public final void setNetworkContext(String networkType, boolean isConnected) {
        setCustomKey("network_type", networkType);
        setCustomKey("network_connected", isConnected);
    }

    public final void setPlaybackContext(String contentId, String contentType, String streamUrl, Long position, Long duration) {
        String strExtractHost;
        String str = "none";
        if (contentId == null) {
            contentId = "none";
        }
        setCustomKey(DownloadService.KEY_CONTENT_ID, contentId);
        if (contentType == null) {
            contentType = "unknown";
        }
        setCustomKey("content_type", contentType);
        if (streamUrl != null && (strExtractHost = INSTANCE.extractHost(streamUrl)) != null) {
            str = strExtractHost;
        }
        setCustomKey("stream_url_host", str);
        if (position != null) {
            INSTANCE.setCustomKey("playback_position", (int) position.longValue());
        }
        if (duration != null) {
            INSTANCE.setCustomKey("playback_duration", (int) duration.longValue());
        }
    }

    public final void setScreenContext(String screenName) {
        setCustomKey("screen", screenName);
    }

    @Override // com.arflix.tv.util.AppLogger.CrashContextProvider
    public void setUserId(String userId) {
        if (isInitialized) {
            try {
                FirebaseCrashlytics firebaseCrashlytics = FirebaseCrashlytics.getInstance();
                if (userId == null) {
                    userId = "";
                }
                firebaseCrashlytics.setUserId(userId);
            } catch (Exception unused) {
            }
        }
    }

    public String toString() {
        return "CrashlyticsProvider";
    }

    @Override // com.arflix.tv.util.AppLogger.CrashContextProvider
    public void setCustomKey(String key, int value) {
        if (isInitialized) {
            try {
                FirebaseCrashlytics.getInstance().setCustomKey(key, value);
            } catch (Exception unused) {
            }
        }
    }

    @Override // com.arflix.tv.util.AppLogger.CrashContextProvider
    public void setCustomKey(String key, boolean value) {
        if (isInitialized) {
            try {
                FirebaseCrashlytics.getInstance().setCustomKey(key, value);
            } catch (Exception unused) {
            }
        }
    }
}
