package io.sentry.android.ndk;

import androidx.activity.n;
import io.sentry.android.core.SentryAndroidOptions;
import io.sentry.c4;
import io.sentry.f;
import io.sentry.f4;
import io.sentry.l7;
import io.sentry.ndk.NativeScope;
import io.sentry.protocol.i0;
import io.sentry.w5;
import io.sentry.y6;

/* JADX INFO: loaded from: classes5.dex */
public final class d extends f4 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final y6 f16847a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final NativeScope f16848b;

    public d(SentryAndroidOptions sentryAndroidOptions) {
        NativeScope nativeScope = new NativeScope();
        ac.b.V(sentryAndroidOptions, "The SentryOptions object is required.");
        this.f16847a = sentryAndroidOptions;
        this.f16848b = nativeScope;
    }

    @Override // io.sentry.b1
    public final void d(i0 i0Var) {
        y6 y6Var = this.f16847a;
        try {
            y6Var.getExecutorService().submit(new b(this, i0Var, 2));
        } catch (Throwable th) {
            y6Var.getLogger().k(w5.ERROR, th, "Scope sync setUser has an error.", new Object[0]);
        }
    }

    @Override // io.sentry.f4, io.sentry.b1
    public final void e(String str, String str2) {
        y6 y6Var = this.f16847a;
        try {
            y6Var.getExecutorService().submit(new c(this, str, str2, 1));
        } catch (Throwable th) {
            y6Var.getLogger().k(w5.ERROR, th, "Scope sync setTag(%s) has an error.", str);
        }
    }

    @Override // io.sentry.f4, io.sentry.b1
    public final void g(String str, String str2) {
        y6 y6Var = this.f16847a;
        try {
            y6Var.getExecutorService().submit(new c(this, str, str2, 0));
        } catch (Throwable th) {
            y6Var.getLogger().k(w5.ERROR, th, "Scope sync setExtra(%s) has an error.", str);
        }
    }

    @Override // io.sentry.b1
    public final void k(f fVar) {
        y6 y6Var = this.f16847a;
        try {
            y6Var.getExecutorService().submit(new b(this, fVar, 0));
        } catch (Throwable th) {
            y6Var.getLogger().k(w5.ERROR, th, "Scope sync addBreadcrumb has an error.", new Object[0]);
        }
    }

    @Override // io.sentry.f4, io.sentry.b1
    public final void m(String str) {
        y6 y6Var = this.f16847a;
        try {
            y6Var.getExecutorService().submit(new b(this, str, 1));
        } catch (Throwable th) {
            y6Var.getLogger().k(w5.ERROR, th, "Scope sync removeExtra(%s) has an error.", str);
        }
    }

    @Override // io.sentry.b1
    public final void n(l7 l7Var, c4 c4Var) {
        y6 y6Var = this.f16847a;
        if (l7Var == null) {
            return;
        }
        try {
            y6Var.getExecutorService().submit(new b(this, l7Var, 3));
        } catch (Throwable th) {
            y6Var.getLogger().k(w5.ERROR, th, "Scope sync setTrace failed.", new Object[0]);
        }
    }

    @Override // io.sentry.f4, io.sentry.b1
    public final void r() {
        y6 y6Var = this.f16847a;
        try {
            y6Var.getExecutorService().submit(new n(this, 22));
        } catch (Throwable th) {
            y6Var.getLogger().k(w5.ERROR, th, "Scope sync clearAttachments has an error.", new Object[0]);
        }
    }
}
