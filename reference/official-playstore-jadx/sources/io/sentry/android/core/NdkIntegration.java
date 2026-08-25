package io.sentry.android.core;

import io.sentry.ILogger;
import io.sentry.w5;
import io.sentry.y6;
import java.io.Closeable;

/* JADX INFO: loaded from: classes5.dex */
public final class NdkIntegration implements io.sentry.s1, Closeable {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final Class f16346i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public SentryAndroidOptions f16347l;

    public NdkIntegration(Class cls) {
        this.f16346i = cls;
    }

    public static void i(SentryAndroidOptions sentryAndroidOptions) {
        sentryAndroidOptions.setEnableNdk(false);
        sentryAndroidOptions.setEnableScopeSync(false);
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable
    public final void close() {
        SentryAndroidOptions sentryAndroidOptions = this.f16347l;
        if (sentryAndroidOptions == null || !sentryAndroidOptions.isEnableNdk()) {
            return;
        }
        Class cls = this.f16346i;
        try {
            if (cls != null) {
                try {
                    try {
                        cls.getMethod("close", null).invoke(null, null);
                        this.f16347l.getLogger().q(w5.DEBUG, "NdkIntegration removed.", new Object[0]);
                        i(this.f16347l);
                    } catch (NoSuchMethodException e5) {
                        this.f16347l.getLogger().l(w5.ERROR, "Failed to invoke the SentryNdk.close method.", e5);
                        i(this.f16347l);
                    }
                } catch (Throwable th) {
                    this.f16347l.getLogger().l(w5.ERROR, "Failed to close SentryNdk.", th);
                    i(this.f16347l);
                }
            }
        } catch (Throwable th2) {
            i(this.f16347l);
            throw th2;
        }
    }

    @Override // io.sentry.s1
    public final void l(y6 y6Var) {
        Class cls;
        SentryAndroidOptions sentryAndroidOptions = y6Var instanceof SentryAndroidOptions ? (SentryAndroidOptions) y6Var : null;
        ac.b.V(sentryAndroidOptions, "SentryAndroidOptions is required");
        this.f16347l = sentryAndroidOptions;
        boolean zIsEnableNdk = sentryAndroidOptions.isEnableNdk();
        ILogger logger = this.f16347l.getLogger();
        w5 w5Var = w5.DEBUG;
        logger.q(w5Var, "NdkIntegration enabled: %s", Boolean.valueOf(zIsEnableNdk));
        if (!zIsEnableNdk || (cls = this.f16346i) == null) {
            i(this.f16347l);
            return;
        }
        if (this.f16347l.getCacheDirPath() == null) {
            this.f16347l.getLogger().q(w5.ERROR, "No cache dir path is defined in options.", new Object[0]);
            i(this.f16347l);
            return;
        }
        try {
            cls.getMethod("init", SentryAndroidOptions.class).invoke(null, this.f16347l);
            this.f16347l.getLogger().q(w5Var, "NdkIntegration installed.", new Object[0]);
            xc.d.H("Ndk");
        } catch (NoSuchMethodException e5) {
            i(this.f16347l);
            this.f16347l.getLogger().l(w5.ERROR, "Failed to invoke the SentryNdk.init method.", e5);
        } catch (Throwable th) {
            i(this.f16347l);
            this.f16347l.getLogger().l(w5.ERROR, "Failed to initialize SentryNdk.", th);
        }
    }
}
