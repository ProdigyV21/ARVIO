package io.sentry;

import java.io.Closeable;

/* JADX INFO: loaded from: classes4.dex */
public final class ShutdownHookIntegration implements s1, Closeable {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final Runtime f16281i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public Thread f16282l;

    public ShutdownHookIntegration() {
        Runtime runtime = Runtime.getRuntime();
        ac.b.V(runtime, "Runtime is required");
        this.f16281i = runtime;
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable
    public final void close() {
        if (this.f16282l != null) {
            try {
                this.f16281i.removeShutdownHook(this.f16282l);
            } catch (IllegalStateException e5) {
                String message = e5.getMessage();
                if (message == null || !(message.equals("Shutdown in progress") || message.equals("VM already shutting down"))) {
                    throw e5;
                }
            }
        }
    }

    @Override // io.sentry.s1
    public final void l(y6 y6Var) {
        if (!y6Var.isEnableShutdownHook()) {
            y6Var.getLogger().q(w5.INFO, "enableShutdownHook is disabled.", new Object[0]);
            return;
        }
        this.f16282l = new Thread(new p4(y6Var, 3), "sentry-shutdownhook");
        try {
            this.f16281i.addShutdownHook(this.f16282l);
            y6Var.getLogger().q(w5.DEBUG, "ShutdownHookIntegration installed.", new Object[0]);
            xc.d.H("ShutdownHook");
        } catch (IllegalStateException e5) {
            String message = e5.getMessage();
            if (message == null || !(message.equals("Shutdown in progress") || message.equals("VM already shutting down"))) {
                throw e5;
            }
        }
    }
}
