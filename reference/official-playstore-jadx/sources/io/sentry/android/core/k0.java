package io.sentry.android.core;

import android.content.Context;
import io.sentry.a7;
import io.sentry.k5;
import io.sentry.y6;
import java.util.Collections;
import java.util.List;

/* JADX INFO: loaded from: classes5.dex */
public final class k0 implements io.sentry.b {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final Context f16656i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final SentryAndroidOptions f16657l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final r0 f16658m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final k5 f16659n;

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    public final io.sentry.cache.g f16660o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public final List f16661p = Collections.singletonList(new i0(this));

    public k0(Context context, r0 r0Var, SentryAndroidOptions sentryAndroidOptions) {
        Context applicationContext = context.getApplicationContext();
        this.f16656i = applicationContext != null ? applicationContext : context;
        this.f16657l = sentryAndroidOptions;
        this.f16658m = r0Var;
        this.f16660o = sentryAndroidOptions.findPersistingScopeObserver();
        this.f16659n = new k5(new io.sentry.u(sentryAndroidOptions, 2));
    }

    public final Object a(y6 y6Var, String str, Class cls) {
        io.sentry.cache.g gVar = this.f16660o;
        if (gVar == null) {
            return null;
        }
        return gVar.b(y6Var, str, cls);
    }

    @Override // io.sentry.d0
    public final a7 i(a7 a7Var, io.sentry.j0 j0Var) {
        return a7Var;
    }

    /* JADX WARN: Removed duplicated region for block: B:151:0x0387  */
    @Override // io.sentry.d0
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final io.sentry.j5 j(io.sentry.j5 r28, io.sentry.j0 r29) {
        /*
            Method dump skipped, instruction units count: 1439
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: io.sentry.android.core.k0.j(io.sentry.j5, io.sentry.j0):io.sentry.j5");
    }

    @Override // io.sentry.d0
    public final io.sentry.protocol.e0 k(io.sentry.protocol.e0 e0Var, io.sentry.j0 j0Var) {
        return e0Var;
    }
}
