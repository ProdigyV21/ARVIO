package io.sentry;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import java.util.concurrent.atomic.AtomicBoolean;

/* JADX INFO: loaded from: classes4.dex */
public final class m0 {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public static volatile m0 f17460i;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final long f17462a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public volatile String f17463b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public volatile long f17464c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final AtomicBoolean f17465d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final k0 f17466e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final ExecutorService f17467f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public static final long f17458g = TimeUnit.HOURS.toMillis(5);

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public static final long f17459h = TimeUnit.SECONDS.toMillis(1);

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public static final io.sentry.util.b f17461j = new io.sentry.util.b();

    public m0() {
        k0 k0Var = new k0(0);
        this.f17465d = new AtomicBoolean(false);
        this.f17467f = Executors.newSingleThreadExecutor(new l0(0));
        this.f17462a = f17458g;
        this.f17466e = k0Var;
        a();
    }

    public final void a() {
        try {
            this.f17467f.submit(new d5(this, 18)).get(f17459h, TimeUnit.MILLISECONDS);
        } catch (InterruptedException unused) {
            Thread.currentThread().interrupt();
            this.f17464c = TimeUnit.SECONDS.toMillis(1L) + System.currentTimeMillis();
        } catch (RuntimeException | ExecutionException | TimeoutException unused2) {
            this.f17464c = TimeUnit.SECONDS.toMillis(1L) + System.currentTimeMillis();
        }
    }
}
