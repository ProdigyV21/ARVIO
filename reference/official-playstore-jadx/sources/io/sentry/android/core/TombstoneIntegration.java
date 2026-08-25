package io.sentry.android.core;

import android.content.Context;
import io.sentry.w5;
import io.sentry.y6;
import java.io.Closeable;

/* JADX INFO: loaded from: classes5.dex */
public class TombstoneIntegration implements io.sentry.s1, Closeable {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final Context f16384i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final io.sentry.transport.d f16385l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public SentryAndroidOptions f16386m;

    public TombstoneIntegration(Context context) {
        Context applicationContext = context.getApplicationContext();
        this.f16384i = applicationContext != null ? applicationContext : context;
        this.f16385l = io.sentry.transport.d.f17915i;
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable
    public final void close() {
        SentryAndroidOptions sentryAndroidOptions = this.f16386m;
        if (sentryAndroidOptions != null) {
            sentryAndroidOptions.getLogger().q(w5.DEBUG, "TombstoneIntegration removed.", new Object[0]);
        }
    }

    @Override // io.sentry.s1
    public final void l(y6 y6Var) {
        SentryAndroidOptions sentryAndroidOptions = y6Var instanceof SentryAndroidOptions ? (SentryAndroidOptions) y6Var : null;
        ac.b.V(sentryAndroidOptions, "SentryAndroidOptions is required");
        this.f16386m = sentryAndroidOptions;
        sentryAndroidOptions.getLogger().q(w5.DEBUG, "TombstoneIntegration enabled: %s", Boolean.valueOf(this.f16386m.isTombstoneEnabled()));
        if (this.f16386m.isTombstoneEnabled()) {
            if (this.f16386m.getCacheDirPath() == null) {
                this.f16386m.getLogger().q(w5.INFO, "Cache dir is not set, unable to process Tombstones", new Object[0]);
                return;
            }
            try {
                io.sentry.g1 executorService = y6Var.getExecutorService();
                Context context = this.f16384i;
                SentryAndroidOptions sentryAndroidOptions2 = this.f16386m;
                executorService.submit(new n0(context, sentryAndroidOptions2, this.f16385l, new y1(context, sentryAndroidOptions2)));
            } catch (Throwable th) {
                y6Var.getLogger().l(w5.DEBUG, "Failed to start tombstone processor.", th);
            }
            y6Var.getLogger().q(w5.DEBUG, "TombstoneIntegration installed.", new Object[0]);
            xc.d.H("Tombstone");
        }
    }
}
