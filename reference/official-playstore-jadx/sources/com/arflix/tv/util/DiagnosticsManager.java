package com.arflix.tv.util;

import android.content.Context;
import kotlin.Metadata;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0006\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\t\bÇ\n\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u000f\u0010\u0005\u001a\u00020\u0004H\u0002¢\u0006\u0004\b\u0005\u0010\u0003J\u0015\u0010\t\u001a\u00020\b2\u0006\u0010\u0007\u001a\u00020\u0006¢\u0006\u0004\b\t\u0010\nJ\u0015\u0010\u000b\u001a\u00020\b2\u0006\u0010\u0007\u001a\u00020\u0006¢\u0006\u0004\b\u000b\u0010\nJ\u001d\u0010\r\u001a\u00020\u00042\u0006\u0010\u0007\u001a\u00020\u00062\u0006\u0010\f\u001a\u00020\b¢\u0006\u0004\b\r\u0010\u000eJ\u0010\u0010\u0010\u001a\u00020\u000fHÖ\u0001¢\u0006\u0004\b\u0010\u0010\u0011J\u0010\u0010\u0013\u001a\u00020\u0012HÖ\u0001¢\u0006\u0004\b\u0013\u0010\u0014J\u001a\u0010\u0016\u001a\u00020\b2\b\u0010\u0015\u001a\u0004\u0018\u00010\u0001HÖ\u0003¢\u0006\u0004\b\u0016\u0010\u0017R\u0014\u0010\u0018\u001a\u00020\u000f8\u0002X\u0082T¢\u0006\u0006\n\u0004\b\u0018\u0010\u0019R\u0014\u0010\u001a\u001a\u00020\u000f8\u0002X\u0082T¢\u0006\u0006\n\u0004\b\u001a\u0010\u0019¨\u0006\u001b"}, d2 = {"Lcom/arflix/tv/util/DiagnosticsManager;", "", "<init>", "()V", "Lx6/t0;", "disableProviders", "Landroid/content/Context;", "context", "", "isReportingEnabled", "(Landroid/content/Context;)Z", "initialize", "enabled", "setReportingEnabled", "(Landroid/content/Context;Z)V", "", "toString", "()Ljava/lang/String;", "", "hashCode", "()I", "other", "equals", "(Ljava/lang/Object;)Z", "PREFERENCES_NAME", "Ljava/lang/String;", "REPORTING_ENABLED_KEY", "app_playRelease"}, k = 1, mv = {2, 3, 0}, xi = 48)
public final /* data */ class DiagnosticsManager {
    public static final int $stable = 0;
    public static final DiagnosticsManager INSTANCE = new DiagnosticsManager();
    private static final String PREFERENCES_NAME = "arvio_privacy_preferences";
    private static final String REPORTING_ENABLED_KEY = "diagnostics_and_usage_enabled";

    private DiagnosticsManager() {
    }

    private final void disableProviders() {
        SentryCrashReporter.INSTANCE.disable();
        CrashlyticsProvider.INSTANCE.disable();
        AppLogger.INSTANCE.init(null);
    }

    public boolean equals(Object other) {
        return this == other || (other instanceof DiagnosticsManager);
    }

    public int hashCode() {
        return -1281417188;
    }

    public final synchronized boolean initialize(Context context) {
        Context applicationContext = context.getApplicationContext();
        if (isReportingEnabled(applicationContext)) {
            return SentryCrashReporter.INSTANCE.initialize(applicationContext) ? true : CrashlyticsProvider.INSTANCE.initialize();
        }
        disableProviders();
        return false;
    }

    public final boolean isReportingEnabled(Context context) {
        return context.getApplicationContext().getSharedPreferences(PREFERENCES_NAME, 0).getBoolean(REPORTING_ENABLED_KEY, true);
    }

    public final synchronized void setReportingEnabled(Context context, boolean enabled) {
        try {
            Context applicationContext = context.getApplicationContext();
            applicationContext.getSharedPreferences(PREFERENCES_NAME, 0).edit().putBoolean(REPORTING_ENABLED_KEY, enabled).apply();
            if (enabled) {
                initialize(applicationContext);
            } else {
                disableProviders();
            }
        } catch (Throwable th) {
            throw th;
        }
    }

    public String toString() {
        return "DiagnosticsManager";
    }
}
