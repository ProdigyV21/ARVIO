package io.sentry.android.ndk;

import com.discord.org.webrtc.i;
import io.sentry.android.core.SentryAndroidOptions;
import io.sentry.android.core.e1;
import io.sentry.ndk.NdkOptions;
import io.sentry.protocol.t;
import io.sentry.u5;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

/* JADX INFO: loaded from: classes5.dex */
public final class SentryNdk {
    private static final CountDownLatch loadLibraryLatch = new CountDownLatch(1);

    static {
        new Thread(new i(5), "SentryNdkLoadLibs").start();
    }

    private SentryNdk() {
    }

    public static void close() {
        try {
            if (!loadLibraryLatch.await(2000L, TimeUnit.MILLISECONDS)) {
                throw new IllegalStateException("Timeout waiting for Sentry NDK library to load");
            }
            io.sentry.ndk.SentryNdk.close();
        } catch (InterruptedException e5) {
            throw new IllegalStateException("Thread interrupted while waiting for NDK libs to be loaded", e5);
        }
    }

    public static void init(SentryAndroidOptions sentryAndroidOptions) {
        t sdkVersion = sentryAndroidOptions.getSdkVersion();
        int i10 = e.f16849a;
        if (sdkVersion != null) {
            u5.d().b("maven:io.sentry:sentry-android-ndk", "8.40.0");
        }
        try {
            if (!loadLibraryLatch.await(2000L, TimeUnit.MILLISECONDS)) {
                throw new IllegalStateException("Timeout waiting for Sentry NDK library to load");
            }
            String dsn = sentryAndroidOptions.getDsn();
            ac.b.V(dsn, "DSN is required for sentry-ndk");
            boolean zIsDebug = sentryAndroidOptions.isDebug();
            String outboxPath = sentryAndroidOptions.getOutboxPath();
            ac.b.V(outboxPath, "outbox path is required for sentry-ndk");
            NdkOptions ndkOptions = new NdkOptions(dsn, zIsDebug, outboxPath, sentryAndroidOptions.getRelease(), sentryAndroidOptions.getEnvironment(), sentryAndroidOptions.getDist(), sentryAndroidOptions.getMaxBreadcrumbs(), sentryAndroidOptions.getNativeSdkName());
            int ndkHandlerStrategy = sentryAndroidOptions.getNdkHandlerStrategy();
            if (ndkHandlerStrategy == e1.SENTRY_HANDLER_STRATEGY_DEFAULT.getValue()) {
                ndkOptions.setNdkHandlerStrategy(io.sentry.ndk.b.SENTRY_HANDLER_STRATEGY_DEFAULT);
            } else if (ndkHandlerStrategy == e1.SENTRY_HANDLER_STRATEGY_CHAIN_AT_START.getValue()) {
                ndkOptions.setNdkHandlerStrategy(io.sentry.ndk.b.SENTRY_HANDLER_STRATEGY_CHAIN_AT_START);
            }
            Double tracesSampleRate = sentryAndroidOptions.getTracesSampleRate();
            if (tracesSampleRate == null) {
                ndkOptions.setTracesSampleRate(0.0f);
            } else {
                ndkOptions.setTracesSampleRate(tracesSampleRate.floatValue());
            }
            io.sentry.ndk.SentryNdk.init(ndkOptions);
            if (sentryAndroidOptions.isEnableScopeSync()) {
                sentryAndroidOptions.addScopeObserver(new d(sentryAndroidOptions));
            }
            sentryAndroidOptions.setDebugImagesLoader(new a());
        } catch (InterruptedException e5) {
            throw new IllegalStateException("Thread interrupted while waiting for NDK libs to be loaded", e5);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ void lambda$static$0() {
        try {
            io.sentry.ndk.SentryNdk.loadNativeLibraries();
        } catch (Throwable unused) {
        }
        loadLibraryLatch.countDown();
    }
}
