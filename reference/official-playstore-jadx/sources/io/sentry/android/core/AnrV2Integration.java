package io.sentry.android.core;

import android.content.Context;
import io.sentry.w5;
import io.sentry.y6;
import java.io.Closeable;

/* JADX INFO: loaded from: classes5.dex */
public class AnrV2Integration implements io.sentry.s1, Closeable {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final Context f16324i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final io.sentry.transport.d f16325l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public SentryAndroidOptions f16326m;

    public AnrV2Integration(Context context) {
        Context applicationContext = context.getApplicationContext();
        this.f16324i = applicationContext != null ? applicationContext : context;
        this.f16325l = io.sentry.transport.d.f17915i;
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable
    public final void close() {
        SentryAndroidOptions sentryAndroidOptions = this.f16326m;
        if (sentryAndroidOptions != null) {
            sentryAndroidOptions.getLogger().q(w5.DEBUG, "AnrV2Integration removed.", new Object[0]);
        }
    }

    @Override // io.sentry.s1
    public final void l(y6 y6Var) {
        SentryAndroidOptions sentryAndroidOptions = y6Var instanceof SentryAndroidOptions ? (SentryAndroidOptions) y6Var : null;
        ac.b.V(sentryAndroidOptions, "SentryAndroidOptions is required");
        this.f16326m = sentryAndroidOptions;
        sentryAndroidOptions.getLogger().q(w5.DEBUG, "AnrIntegration enabled: %s", Boolean.valueOf(this.f16326m.isAnrEnabled()));
        if (this.f16326m.getCacheDirPath() == null) {
            this.f16326m.getLogger().q(w5.INFO, "Cache dir is not set, unable to process ANRs", new Object[0]);
            return;
        }
        if (this.f16326m.isAnrEnabled()) {
            try {
                io.sentry.g1 executorService = y6Var.getExecutorService();
                Context context = this.f16324i;
                SentryAndroidOptions sentryAndroidOptions2 = this.f16326m;
                executorService.submit(new n0(context, sentryAndroidOptions2, this.f16325l, new c0(sentryAndroidOptions2)));
            } catch (Throwable th) {
                y6Var.getLogger().l(w5.DEBUG, "Failed to start ANR processor.", th);
            }
            y6Var.getLogger().q(w5.DEBUG, "AnrV2Integration installed.", new Object[0]);
            xc.d.H("AnrV2");
        }
    }
}
