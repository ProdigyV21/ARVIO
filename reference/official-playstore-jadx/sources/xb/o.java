package xb;

import java.util.concurrent.locks.ReentrantLock;

/* JADX INFO: loaded from: classes5.dex */
public final class o implements o0 {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final p f22777i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public long f22778l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public boolean f22779m;

    public o(p pVar, long j10) {
        this.f22777i = pVar;
        this.f22778l = j10;
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable
    public final void close() {
        if (this.f22779m) {
            return;
        }
        this.f22779m = true;
        p pVar = this.f22777i;
        ReentrantLock reentrantLock = pVar.f22782m;
        reentrantLock.lock();
        try {
            int i10 = pVar.f22781l - 1;
            pVar.f22781l = i10;
            if (i10 == 0) {
                if (pVar.f22780i) {
                    reentrantLock.unlock();
                    pVar.i();
                }
            }
        } finally {
            reentrantLock.unlock();
        }
    }

    @Override // xb.o0
    public final long read(j jVar, long j10) {
        long j11;
        long j12;
        if (this.f22779m) {
            throw new IllegalStateException("closed");
        }
        long j13 = this.f22778l;
        if (j10 < 0) {
            throw new IllegalArgumentException(androidx.compose.foundation.c.q(j10, "byteCount < 0: ").toString());
        }
        long j14 = j10 + j13;
        long j15 = j13;
        while (true) {
            if (j15 >= j14) {
                j11 = -1;
                break;
            }
            j0 j0VarD0 = jVar.D0(1);
            j11 = -1;
            long j16 = j14;
            int iJ = this.f22777i.j(j15, j0VarD0.f22760a, j0VarD0.f22762c, (int) Math.min(j14 - j15, 8192 - r10));
            if (iJ == -1) {
                if (j0VarD0.f22761b == j0VarD0.f22762c) {
                    jVar.f22758i = j0VarD0.a();
                    k0.a(j0VarD0);
                }
                if (j13 == j15) {
                    j12 = -1;
                }
            } else {
                j0VarD0.f22762c += iJ;
                long j17 = iJ;
                j15 += j17;
                jVar.f22759l += j17;
                j14 = j16;
            }
        }
        j12 = j15 - j13;
        if (j12 != j11) {
            this.f22778l += j12;
        }
        return j12;
    }

    @Override // xb.o0
    public final q0 timeout() {
        return q0.f22791d;
    }
}
