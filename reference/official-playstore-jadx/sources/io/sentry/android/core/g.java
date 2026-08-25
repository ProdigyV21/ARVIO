package io.sentry.android.core;

import android.content.Context;
import io.sentry.b4;
import io.sentry.r4;
import io.sentry.v7;
import io.sentry.w5;
import io.sentry.y6;

/* JADX INFO: loaded from: classes5.dex */
public final /* synthetic */ class g implements v7, b4, r4 {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final /* synthetic */ Object f16485i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final /* synthetic */ Object f16486l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ Object f16487m;

    public /* synthetic */ g(Object obj, Object obj2, Object obj3) {
        this.f16485i = obj;
        this.f16486l = obj2;
        this.f16487m = obj3;
    }

    @Override // io.sentry.b4
    public void b(io.sentry.m1 m1Var) {
        ActivityLifecycleIntegration activityLifecycleIntegration = (ActivityLifecycleIntegration) this.f16485i;
        io.sentry.a1 a1Var = (io.sentry.a1) this.f16486l;
        io.sentry.m1 m1Var2 = (io.sentry.m1) this.f16487m;
        if (m1Var == null) {
            a1Var.y(m1Var2);
            return;
        }
        SentryAndroidOptions sentryAndroidOptions = activityLifecycleIntegration.f16307n;
        if (sentryAndroidOptions != null) {
            sentryAndroidOptions.getLogger().q(w5.DEBUG, "Transaction '%s' won't be bound to the Scope since there's one already in there.", m1Var2.getName());
        }
    }

    @Override // io.sentry.r4
    public void c(y6 y6Var) {
        k1.a((v) this.f16485i, (Context) this.f16486l, (r4) this.f16487m, (SentryAndroidOptions) y6Var);
    }
}
