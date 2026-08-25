package xb;

import java.io.InterruptedIOException;
import java.util.concurrent.TimeUnit;

/* JADX INFO: loaded from: classes5.dex */
public class q0 {

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public static final p0 f22791d = new p0();

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public boolean f22792a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public long f22793b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public long f22794c;

    public q0 a() {
        this.f22792a = false;
        return this;
    }

    public q0 b() {
        this.f22794c = 0L;
        return this;
    }

    public long c() {
        if (this.f22792a) {
            return this.f22793b;
        }
        throw new IllegalStateException("No deadline");
    }

    public q0 d(long j10) {
        this.f22792a = true;
        this.f22793b = j10;
        return this;
    }

    public boolean e() {
        return this.f22792a;
    }

    public void f() throws InterruptedIOException {
        if (Thread.currentThread().isInterrupted()) {
            throw new InterruptedIOException("interrupted");
        }
        if (this.f22792a && this.f22793b - System.nanoTime() <= 0) {
            throw new InterruptedIOException("deadline reached");
        }
    }

    public q0 g(long j10, TimeUnit timeUnit) {
        if (j10 < 0) {
            throw new IllegalArgumentException(androidx.compose.foundation.c.q(j10, "timeout < 0: ").toString());
        }
        this.f22794c = timeUnit.toNanos(j10);
        return this;
    }

    public long h() {
        return this.f22794c;
    }
}
