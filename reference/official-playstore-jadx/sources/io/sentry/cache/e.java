package io.sentry.cache;

import io.sentry.android.core.SentryAndroidOptions;
import io.sentry.protocol.t;
import io.sentry.u0;
import io.sentry.y6;
import java.util.Map;

/* JADX INFO: loaded from: classes5.dex */
public final class e implements u0 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final y6 f17156a;

    public e(SentryAndroidOptions sentryAndroidOptions) {
        this.f17156a = sentryAndroidOptions;
    }

    @Override // io.sentry.u0
    public final void a(Map map) {
        i(map, "tags.json");
    }

    @Override // io.sentry.u0
    public final void b(String str) {
        if (str == null) {
            h("dist.json");
        } else {
            i(str, "dist.json");
        }
    }

    @Override // io.sentry.u0
    public final void c(Double d4) {
        if (d4 == null) {
            h("replay-error-sample-rate.json");
        } else {
            i(d4.toString(), "replay-error-sample-rate.json");
        }
    }

    @Override // io.sentry.u0
    public final void d(String str) {
        if (str == null) {
            h("environment.json");
        } else {
            i(str, "environment.json");
        }
    }

    @Override // io.sentry.u0
    public final void e(String str) {
        if (str == null) {
            h("proguard-uuid.json");
        } else {
            i(str, "proguard-uuid.json");
        }
    }

    @Override // io.sentry.u0
    public final void f(t tVar) {
        if (tVar == null) {
            h("sdk-version.json");
        } else {
            i(tVar, "sdk-version.json");
        }
    }

    @Override // io.sentry.u0
    public final void g(String str) {
        if (str == null) {
            h("release.json");
        } else {
            i(str, "release.json");
        }
    }

    public final void h(String str) {
        b.a(this.f17156a, ".options-cache", str);
    }

    public final void i(Object obj, String str) {
        b.d(this.f17156a, obj, ".options-cache", str);
    }
}
