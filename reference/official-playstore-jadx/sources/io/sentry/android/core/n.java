package io.sentry.android.core;

import io.sentry.w5;
import io.sentry.y6;

/* JADX INFO: loaded from: classes5.dex */
public final class n extends io.sentry.logger.d implements e0 {
    @Override // io.sentry.logger.d, io.sentry.logger.b, io.sentry.metrics.b
    public final void a(boolean z) {
        h0.f16496o.l(this);
        super.a(z);
    }

    @Override // io.sentry.android.core.e0
    public final void i() {
    }

    @Override // io.sentry.android.core.e0
    public final void j() {
        y6 y6Var = this.f17450l;
        try {
            y6Var.getExecutorService().submit(new m(this, 0));
        } catch (Throwable th) {
            y6Var.getLogger().k(w5.ERROR, th, "Failed to submit log flush in onBackground()", new Object[0]);
        }
    }
}
