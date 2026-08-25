package io.sentry.cache;

import androidx.activity.n;
import androidx.emoji2.text.q;
import io.sentry.android.core.SentryAndroidOptions;
import io.sentry.c4;
import io.sentry.f4;
import io.sentry.l7;
import io.sentry.protocol.i0;
import io.sentry.protocol.v;
import io.sentry.util.k;
import io.sentry.w5;
import io.sentry.y6;
import j$.util.concurrent.ConcurrentHashMap;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.Collection;

/* JADX INFO: loaded from: classes5.dex */
public final class g extends f4 {

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public static final Charset f17160c = Charset.forName("UTF-8");

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final y6 f17161a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final k f17162b = new k(new a(this, 1));

    public g(SentryAndroidOptions sentryAndroidOptions) {
        this.f17161a = sentryAndroidOptions;
    }

    public final void a(String str) {
        b.a(this.f17161a, ".scope-cache", str);
    }

    public final Object b(y6 y6Var, String str, Class cls) {
        if (!str.equals("breadcrumbs.json")) {
            return b.c(y6Var, ".scope-cache", str, cls);
        }
        try {
            return cls.cast(((io.sentry.cache.tape.g) this.f17162b.a()).y());
        } catch (IOException unused) {
            y6Var.getLogger().q(w5.ERROR, "Unable to read serialized breadcrumbs from QueueFile", new Object[0]);
            return null;
        }
    }

    public final void c(Runnable runnable) {
        y6 y6Var = this.f17161a;
        if (y6Var.isEnableScopePersistence()) {
            if (Thread.currentThread().getName().contains("SentryExecutor")) {
                try {
                    runnable.run();
                    return;
                } catch (Throwable th) {
                    y6Var.getLogger().l(w5.ERROR, "Serialization task failed", th);
                    return;
                }
            }
            try {
                y6Var.getExecutorService().submit(new io.sentry.android.ndk.b(this, runnable, 11));
            } catch (Throwable th2) {
                y6Var.getLogger().l(w5.ERROR, "Serialization task could not be scheduled", th2);
            }
        }
    }

    @Override // io.sentry.b1
    public final void d(i0 i0Var) {
        c(new io.sentry.android.ndk.b(this, i0Var, 7));
    }

    public final void f(Object obj, String str) {
        b.d(this.f17161a, obj, ".scope-cache", str);
    }

    @Override // io.sentry.b1
    public final void k(io.sentry.f fVar) {
        c(new io.sentry.android.ndk.b(this, fVar, 8));
    }

    @Override // io.sentry.f4, io.sentry.b1
    public final void l(Collection collection) {
        if (collection.isEmpty()) {
            c(new n(this, 26));
        }
    }

    @Override // io.sentry.b1
    public final void n(l7 l7Var, c4 c4Var) {
        c(new q(this, l7Var, c4Var, 13));
    }

    @Override // io.sentry.f4, io.sentry.b1
    public final void o(io.sentry.protocol.c cVar) {
        c(new io.sentry.android.ndk.b(this, cVar, 12));
    }

    @Override // io.sentry.f4, io.sentry.b1
    public final void p(v vVar) {
        c(new io.sentry.android.ndk.b(this, vVar, 9));
    }

    @Override // io.sentry.f4, io.sentry.b1
    public final void q(ConcurrentHashMap concurrentHashMap) {
        c(new f(this, concurrentHashMap, 1));
    }

    @Override // io.sentry.f4, io.sentry.b1
    public final void s(ConcurrentHashMap concurrentHashMap) {
        c(new f(this, concurrentHashMap, 0));
    }

    @Override // io.sentry.f4, io.sentry.b1
    public final void t(String str) {
        c(new io.sentry.android.ndk.b(this, str, 10));
    }
}
