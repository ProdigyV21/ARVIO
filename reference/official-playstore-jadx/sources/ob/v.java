package ob;

import java.io.IOException;
import java.io.InterruptedIOException;
import okhttp3.internal.http2.StreamResetException;
import xb.o0;
import xb.q0;

/* JADX INFO: loaded from: classes5.dex */
public final class v implements o0 {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final long f20958i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public boolean f20959l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final xb.j f20960m = new xb.j();

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final xb.j f20961n = new xb.j();

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    public boolean f20962o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public final /* synthetic */ x f20963p;

    public v(x xVar, long j10, boolean z) {
        this.f20963p = xVar;
        this.f20958i = j10;
        this.f20959l = z;
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable
    public final void close() {
        long j10;
        x xVar = this.f20963p;
        synchronized (xVar) {
            this.f20962o = true;
            xb.j jVar = this.f20961n;
            j10 = jVar.f22759l;
            jVar.i();
            xVar.notifyAll();
        }
        if (j10 > 0) {
            x xVar2 = this.f20963p;
            byte[] bArr = ib.c.f16268a;
            xVar2.f20966b.x(j10);
        }
        this.f20963p.a();
    }

    @Override // xb.o0
    public final long read(xb.j jVar, long j10) throws Throwable {
        Throwable streamResetException;
        boolean z;
        long j11;
        long j12 = 0;
        if (j10 < 0) {
            throw new IllegalArgumentException(androidx.compose.foundation.c.q(j10, "byteCount < 0: ").toString());
        }
        while (true) {
            x xVar = this.f20963p;
            synchronized (xVar) {
                xVar.k.i();
                try {
                    if (xVar.f() == 0 || this.f20959l) {
                        streamResetException = null;
                    } else {
                        streamResetException = xVar.f20977n;
                        if (streamResetException == null) {
                            streamResetException = new StreamResetException(xVar.f());
                        }
                    }
                    if (this.f20962o) {
                        throw new IOException("stream closed");
                    }
                    xb.j jVar2 = this.f20961n;
                    long j13 = jVar2.f22759l;
                    z = false;
                    if (j13 > j12) {
                        j11 = jVar2.read(jVar, Math.min(j10, j13));
                        long j14 = xVar.f20967c + j11;
                        xVar.f20967c = j14;
                        long j15 = j14 - xVar.f20968d;
                        if (streamResetException == null && j15 >= xVar.f20966b.A.a() / 2) {
                            xVar.f20966b.O(xVar.f20965a, j15);
                            xVar.f20968d = xVar.f20967c;
                        }
                    } else {
                        if (!this.f20959l && streamResetException == null) {
                            try {
                                xVar.wait();
                                z = true;
                            } catch (InterruptedException unused) {
                                Thread.currentThread().interrupt();
                                throw new InterruptedIOException();
                            }
                        }
                        j11 = -1;
                    }
                    xVar.k.l();
                } catch (Throwable th) {
                    xVar.k.l();
                    throw th;
                }
            }
            if (!z) {
                if (j11 != -1) {
                    return j11;
                }
                if (streamResetException == null) {
                    return -1L;
                }
                throw streamResetException;
            }
            j12 = 0;
        }
    }

    @Override // xb.o0
    public final q0 timeout() {
        return this.f20963p.k;
    }
}
