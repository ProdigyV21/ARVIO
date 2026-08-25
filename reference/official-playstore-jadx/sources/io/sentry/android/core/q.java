package io.sentry.android.core;

import io.sentry.m4;
import io.sentry.w5;
import java.io.File;
import java.util.List;

/* JADX INFO: loaded from: classes5.dex */
public final /* synthetic */ class q implements io.sentry.util.j, m4, a {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final /* synthetic */ int f16725i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final /* synthetic */ SentryAndroidOptions f16726l;

    public /* synthetic */ q(AnrIntegration anrIntegration, SentryAndroidOptions sentryAndroidOptions) {
        this.f16725i = 4;
        this.f16726l = sentryAndroidOptions;
    }

    @Override // io.sentry.util.j
    public Object g() {
        int i10 = this.f16725i;
        SentryAndroidOptions sentryAndroidOptions = this.f16726l;
        switch (i10) {
            case 0:
                return sentryAndroidOptions.getExecutorService();
            case 1:
                List list = io.sentry.android.core.cache.e.f16465u;
                String outboxPath = sentryAndroidOptions.getOutboxPath();
                boolean z = false;
                if (outboxPath != null) {
                    File file = new File(outboxPath, "startup_crash");
                    try {
                        boolean zExists = file.exists();
                        if (zExists && !file.delete()) {
                            sentryAndroidOptions.getLogger().q(w5.ERROR, "Failed to delete the startup crash marker file. %s.", file.getAbsolutePath());
                        }
                        z = zExists;
                    } catch (Throwable th) {
                        sentryAndroidOptions.getLogger().l(w5.ERROR, "Error reading/deleting the startup crash marker file on the disk", th);
                    }
                    break;
                } else {
                    sentryAndroidOptions.getLogger().q(w5.DEBUG, "Outbox path is null, the startup crash marker file does not exist", new Object[0]);
                }
                return Boolean.valueOf(z);
            default:
                return sentryAndroidOptions.getExecutorService();
        }
    }

    public /* synthetic */ q(SentryAndroidOptions sentryAndroidOptions, int i10) {
        this.f16725i = i10;
        this.f16726l = sentryAndroidOptions;
    }
}
