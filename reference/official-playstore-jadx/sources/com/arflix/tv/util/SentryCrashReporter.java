package com.arflix.tv.util;

import android.content.Context;
import android.content.SharedPreferences;
import androidx.media3.exoplayer.analytics.b;
import com.arflix.tv.BuildConfig;
import com.arflix.tv.util.AppLogger;
import io.sentry.android.core.SentryAndroidOptions;
import io.sentry.android.core.k1;
import io.sentry.android.core.v;
import io.sentry.f;
import io.sentry.j0;
import io.sentry.j5;
import io.sentry.protocol.i0;
import io.sentry.protocol.n;
import io.sentry.s4;
import io.sentry.w5;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicInteger;
import kotlin.Metadata;
import kotlin.text.o;
import x6.c0;
import x6.d0;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000^\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0003\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\b\n\u0002\b\r\n\u0002\u0010\u0000\n\u0002\b\n\n\u0002\u0010\t\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010#\n\u0002\b\u0005\bÇ\n\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u001f\u0010\t\u001a\u00020\b2\u0006\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0007\u001a\u00020\u0006H\u0002¢\u0006\u0004\b\t\u0010\nJ\u0017\u0010\u000e\u001a\u00020\r2\u0006\u0010\f\u001a\u00020\u000bH\u0002¢\u0006\u0004\b\u000e\u0010\u000fJ\u0015\u0010\u0010\u001a\u00020\b2\u0006\u0010\u0005\u001a\u00020\u0004¢\u0006\u0004\b\u0010\u0010\u0011J\r\u0010\u0013\u001a\u00020\u0012¢\u0006\u0004\b\u0013\u0010\u0003J\u001f\u0010\u0016\u001a\u00020\u00122\u0006\u0010\u0014\u001a\u00020\r2\u0006\u0010\u0015\u001a\u00020\rH\u0016¢\u0006\u0004\b\u0016\u0010\u0017J\u001f\u0010\u0016\u001a\u00020\u00122\u0006\u0010\u0014\u001a\u00020\r2\u0006\u0010\u0015\u001a\u00020\u0018H\u0016¢\u0006\u0004\b\u0016\u0010\u0019J\u001f\u0010\u0016\u001a\u00020\u00122\u0006\u0010\u0014\u001a\u00020\r2\u0006\u0010\u0015\u001a\u00020\bH\u0016¢\u0006\u0004\b\u0016\u0010\u001aJ\u0017\u0010\u001c\u001a\u00020\u00122\u0006\u0010\u001b\u001a\u00020\rH\u0016¢\u0006\u0004\b\u001c\u0010\u001dJ\u0017\u0010\u001e\u001a\u00020\u00122\u0006\u0010\u0007\u001a\u00020\u0006H\u0016¢\u0006\u0004\b\u001e\u0010\u001fJ\u0019\u0010!\u001a\u00020\u00122\b\u0010 \u001a\u0004\u0018\u00010\rH\u0016¢\u0006\u0004\b!\u0010\u001dJ\u0010\u0010\"\u001a\u00020\rHÖ\u0001¢\u0006\u0004\b\"\u0010#J\u0010\u0010$\u001a\u00020\u0018HÖ\u0001¢\u0006\u0004\b$\u0010%J\u001a\u0010(\u001a\u00020\b2\b\u0010'\u001a\u0004\u0018\u00010&HÖ\u0003¢\u0006\u0004\b(\u0010)R\u0014\u0010*\u001a\u00020\r8\u0002X\u0082T¢\u0006\u0006\n\u0004\b*\u0010+R\u0014\u0010,\u001a\u00020\r8\u0002X\u0082T¢\u0006\u0006\n\u0004\b,\u0010+R\u0014\u0010-\u001a\u00020\r8\u0002X\u0082T¢\u0006\u0006\n\u0004\b-\u0010+R\u0014\u0010.\u001a\u00020\u00188\u0002X\u0082T¢\u0006\u0006\n\u0004\b.\u0010/R\u0014\u00100\u001a\u00020\u00188\u0002X\u0082T¢\u0006\u0006\n\u0004\b0\u0010/R\u0014\u00102\u001a\u0002018\u0002X\u0082T¢\u0006\u0006\n\u0004\b2\u00103R\u0014\u00104\u001a\u0002018\u0002X\u0082T¢\u0006\u0006\n\u0004\b4\u00103R\u0014\u00105\u001a\u00020\u00188\u0002X\u0082T¢\u0006\u0006\n\u0004\b5\u0010/R\u0014\u00106\u001a\u00020\u00188\u0002X\u0082T¢\u0006\u0006\n\u0004\b6\u0010/R\u0014\u00107\u001a\u00020&8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b7\u00108R\u0014\u0010:\u001a\u0002098\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b:\u0010;R\u0014\u0010<\u001a\u0002098\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b<\u0010;R\u001a\u0010>\u001a\b\u0012\u0004\u0012\u00020\r0=8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b>\u0010?R\u0016\u0010@\u001a\u00020\b8\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b@\u0010A¨\u0006B"}, d2 = {"Lcom/arflix/tv/util/SentryCrashReporter;", "Lcom/arflix/tv/util/AppLogger$CrashContextProvider;", "<init>", "()V", "Landroid/content/Context;", "context", "", "throwable", "", "shouldSendHandledEvent", "(Landroid/content/Context;Ljava/lang/Throwable;)Z", "Landroid/content/SharedPreferences;", "prefs", "", "installationSeed", "(Landroid/content/SharedPreferences;)Ljava/lang/String;", "initialize", "(Landroid/content/Context;)Z", "Lx6/t0;", "disable", "key", "value", "setCustomKey", "(Ljava/lang/String;Ljava/lang/String;)V", "", "(Ljava/lang/String;I)V", "(Ljava/lang/String;Z)V", "message", "log", "(Ljava/lang/String;)V", "recordException", "(Ljava/lang/Throwable;)V", "userId", "setUserId", "toString", "()Ljava/lang/String;", "hashCode", "()I", "", "other", "equals", "(Ljava/lang/Object;)Z", "DISABLED_DSN", "Ljava/lang/String;", "SAMPLING_PREFS", "INSTALLATION_SEED_KEY", "HANDLED_SAMPLE_PERMILLE", "I", "ANR_SAMPLE_PERMILLE", "", "HANDLED_COOLDOWN_MS", "J", "ANR_COOLDOWN_MS", "MAX_HANDLED_PER_PROCESS", "MAX_ANR_PER_PROCESS", "samplingLock", "Ljava/lang/Object;", "Ljava/util/concurrent/atomic/AtomicInteger;", "handledSentThisProcess", "Ljava/util/concurrent/atomic/AtomicInteger;", "anrSentThisProcess", "", "acceptedFingerprints", "Ljava/util/Set;", "isInitialized", "Z", "app_playRelease"}, k = 1, mv = {2, 3, 0}, xi = 48)
public final /* data */ class SentryCrashReporter implements AppLogger.CrashContextProvider {
    private static final long ANR_COOLDOWN_MS = 86400000;
    private static final int ANR_SAMPLE_PERMILLE = 100;
    private static final String DISABLED_DSN = "disabled";
    private static final long HANDLED_COOLDOWN_MS = 604800000;
    private static final int HANDLED_SAMPLE_PERMILLE = 50;
    private static final String INSTALLATION_SEED_KEY = "installation_seed";
    private static final int MAX_ANR_PER_PROCESS = 1;
    private static final int MAX_HANDLED_PER_PROCESS = 1;
    private static final String SAMPLING_PREFS = "arvio_sentry_sampling";
    private static boolean isInitialized;
    public static final SentryCrashReporter INSTANCE = new SentryCrashReporter();
    private static final Object samplingLock = new Object();
    private static final AtomicInteger handledSentThisProcess = new AtomicInteger(0);
    private static final AtomicInteger anrSentThisProcess = new AtomicInteger(0);
    private static final Set<String> acceptedFingerprints = new LinkedHashSet();
    public static final int $stable = 8;

    private SentryCrashReporter() {
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void initialize$lambda$0$0(String str, SentryCrashReporter sentryCrashReporter, Context context, SentryAndroidOptions sentryAndroidOptions) {
        sentryAndroidOptions.setDsn(str);
        sentryAndroidOptions.setRelease("com.arvio.tv@1.9.995+310");
        sentryAndroidOptions.setDist("310");
        sentryAndroidOptions.setEnvironment("release");
        sentryAndroidOptions.setDebug(false);
        sentryAndroidOptions.setSendDefaultPii(false);
        sentryAndroidOptions.setAttachScreenshot(false);
        sentryAndroidOptions.setAttachViewHierarchy(false);
        sentryAndroidOptions.setEnableActivityLifecycleBreadcrumbs(true);
        sentryAndroidOptions.setEnableAppLifecycleBreadcrumbs(true);
        sentryAndroidOptions.setEnableSystemEventBreadcrumbs(false);
        sentryAndroidOptions.setEnableNetworkEventBreadcrumbs(false);
        sentryAndroidOptions.setEnableUserInteractionBreadcrumbs(false);
        sentryAndroidOptions.setMaxBreadcrumbs(50);
        sentryAndroidOptions.setSampleRate(Double.valueOf(1.0d));
        Double dValueOf = Double.valueOf(0.0d);
        sentryAndroidOptions.setTracesSampleRate(dValueOf);
        sentryAndroidOptions.setProfilesSampleRate(dValueOf);
        sentryAndroidOptions.setProfileSessionSampleRate(dValueOf);
        sentryAndroidOptions.setEnableAutoActivityLifecycleTracing(false);
        sentryAndroidOptions.setEnableUserInteractionTracing(false);
        sentryAndroidOptions.setEnableTimeToFullDisplayTracing(false);
        sentryAndroidOptions.setEnableDatabaseTransactionTracing(false);
        sentryAndroidOptions.setEnableCacheTracing(false);
        sentryAndroidOptions.setEnableFramesTracking(false);
        sentryAndroidOptions.setEnableAppStartProfiling(false);
        sentryAndroidOptions.setAnrProfilingSampleRate(dValueOf);
        sentryAndroidOptions.setReportHistoricalAnrs(false);
        sentryAndroidOptions.setAttachAnrThreadDump(false);
        sentryAndroidOptions.getSessionReplay().B(dValueOf);
        sentryAndroidOptions.getSessionReplay().A(dValueOf);
        sentryAndroidOptions.getLogs().f17516a = false;
        sentryAndroidOptions.getMetrics().f17786a = false;
        sentryAndroidOptions.setBeforeSend(new b(sentryCrashReporter, context, 13));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final j5 initialize$lambda$0$0$0(SentryCrashReporter sentryCrashReporter, Context context, j5 j5Var, j0 j0Var) {
        String str;
        boolean z = j5Var.f() != null || j5Var.E == w5.FATAL;
        CrashReportFilter crashReportFilter = CrashReportFilter.INSTANCE;
        if (crashReportFilter.shouldSendSentryEvent(j5Var.a(), j5Var.E, z)) {
            if (z) {
                j5Var.b("arvio_event_kind", "crash");
            } else {
                Throwable thA = j5Var.a();
                if (thA != null && sentryCrashReporter.shouldSendHandledEvent(context, thA)) {
                    j5Var.h(t7.a.E("arvio-handled", crashReportFilter.handledEventFingerprint(thA)));
                    j5Var.b("arvio_event_kind", crashReportFilter.isAnr(thA) ? "anr" : "handled_sample");
                }
            }
            try {
                SharedPreferences sharedPreferences = context.getSharedPreferences("arvio_crash_store", 0);
                String strValueOf = String.valueOf(j5Var.f17984i);
                Throwable thA2 = j5Var.a();
                if (thA2 != null) {
                    String simpleName = thA2.getClass().getSimpleName();
                    String message = thA2.getMessage();
                    str = simpleName + ": " + (message != null ? o.I0(200, message) : "");
                } else {
                    n nVar = j5Var.A;
                    if (nVar == null || (str = nVar.f17649i) == null) {
                        str = "Crash event";
                    }
                }
                SharedPreferences.Editor editorPutString = sharedPreferences.edit().putString("last_crash_id", strValueOf).putString("last_crash_msg", str).putLong("last_crash_time", System.currentTimeMillis()).putString("last_crash_version", "1.9.995 (310)");
                if ((j5Var.f() != null) || j5Var.E == w5.FATAL) {
                    editorPutString.putBoolean("has_pending_crash_report", true);
                }
                editorPutString.commit();
            } catch (Throwable unused) {
            }
            j5Var.f17992s = null;
            j5Var.f17994u = null;
            j5Var.f17987n = null;
            return j5Var;
        }
        return null;
    }

    private final String installationSeed(SharedPreferences prefs) {
        String string = prefs.getString(INSTALLATION_SEED_KEY, null);
        if (string != null) {
            if (o.h0(string)) {
                string = null;
            }
            if (string != null) {
                return string;
            }
        }
        synchronized (samplingLock) {
            String string2 = prefs.getString(INSTALLATION_SEED_KEY, null);
            if (string2 != null) {
                String str = o.h0(string2) ? null : string2;
                if (str != null) {
                    return str;
                }
            }
            String string3 = UUID.randomUUID().toString();
            prefs.edit().putString(INSTALLATION_SEED_KEY, string3).commit();
            return string3;
        }
    }

    private final boolean shouldSendHandledEvent(Context context, Throwable throwable) {
        CrashReportFilter crashReportFilter = CrashReportFilter.INSTANCE;
        String strHandledEventFingerprint = crashReportFilter.handledEventFingerprint(throwable);
        boolean zIsAnr = crashReportFilter.isAnr(throwable);
        SharedPreferences sharedPreferences = context.getSharedPreferences(SAMPLING_PREFS, 0);
        if (!crashReportFilter.isSelectedForHandledSample(installationSeed(sharedPreferences), strHandledEventFingerprint, zIsAnr ? 100 : 50)) {
            return false;
        }
        synchronized (samplingLock) {
            try {
                if (!acceptedFingerprints.add(strHandledEventFingerprint)) {
                    return false;
                }
                AtomicInteger atomicInteger = zIsAnr ? anrSentThisProcess : handledSentThisProcess;
                if (atomicInteger.get() >= 1) {
                    return false;
                }
                long jCurrentTimeMillis = System.currentTimeMillis();
                long j10 = sharedPreferences.getLong("last_" + strHandledEventFingerprint, 0L);
                long j11 = zIsAnr ? 86400000L : HANDLED_COOLDOWN_MS;
                long j12 = jCurrentTimeMillis - j10;
                if (0 <= j12 && j12 < j11) {
                    return false;
                }
                sharedPreferences.edit().putLong("last_" + strHandledEventFingerprint, jCurrentTimeMillis).apply();
                atomicInteger.incrementAndGet();
                return true;
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    public final void disable() {
        isInitialized = false;
        try {
            s4.a();
        } catch (Throwable unused) {
        }
    }

    public boolean equals(Object other) {
        return this == other || (other instanceof SentryCrashReporter);
    }

    public int hashCode() {
        return -673686610;
    }

    public final boolean initialize(Context context) {
        Object c0Var;
        String string = o.L0(BuildConfig.SENTRY_DSN).toString();
        if (!BuildConfig.ENABLE_CRASH_REPORTING.booleanValue() || o.h0(string) || string.equals(DISABLED_DSN)) {
            isInitialized = false;
            AppLogger.INSTANCE.init(null);
            return false;
        }
        try {
            k1.c(context, new v(3), new androidx.media3.exoplayer.hls.b(string, this, context, 4));
            isInitialized = true;
            AppLogger.INSTANCE.init(this);
            c0Var = Boolean.TRUE;
        } catch (Throwable th) {
            c0Var = new c0(th);
        }
        if (d0.a(c0Var) != null) {
            isInitialized = false;
            AppLogger.INSTANCE.init(null);
            c0Var = Boolean.FALSE;
        }
        return ((Boolean) c0Var).booleanValue();
    }

    @Override // com.arflix.tv.util.AppLogger.CrashContextProvider
    public void log(String message) {
        if (isInitialized) {
            f fVar = new f();
            fVar.f17249q = "arvio";
            fVar.f17247o = "diagnostic";
            fVar.f17246n = o.I0(500, message);
            fVar.f17251s = w5.INFO;
            s4.b().k(fVar);
        }
    }

    @Override // com.arflix.tv.util.AppLogger.CrashContextProvider
    public void recordException(Throwable throwable) {
        if (isInitialized && CrashReportFilter.INSTANCE.shouldReportHandledException(throwable)) {
            s4.b().x(throwable);
        }
    }

    @Override // com.arflix.tv.util.AppLogger.CrashContextProvider
    public void setCustomKey(String key, String value) {
        if (isInitialized) {
            s4.i(key, value);
        }
    }

    @Override // com.arflix.tv.util.AppLogger.CrashContextProvider
    public void setUserId(String userId) {
        if (isInitialized) {
            i0 i0Var = null;
            if (userId != null) {
                if (o.h0(userId)) {
                    userId = null;
                }
                if (userId != null) {
                    i0 i0Var2 = new i0();
                    i0Var2.f17598l = userId;
                    i0Var2.f17600n = null;
                    i0Var = i0Var2;
                }
            }
            s4.j(i0Var);
        }
    }

    public String toString() {
        return "SentryCrashReporter";
    }

    @Override // com.arflix.tv.util.AppLogger.CrashContextProvider
    public void setCustomKey(String key, int value) {
        if (isInitialized) {
            s4.h(key, String.valueOf(value));
        }
    }

    @Override // com.arflix.tv.util.AppLogger.CrashContextProvider
    public void setCustomKey(String key, boolean value) {
        if (isInitialized) {
            s4.h(key, String.valueOf(value));
        }
    }
}
