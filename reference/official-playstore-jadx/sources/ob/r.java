package ob;

import androidx.fragment.app.a2;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import xb.o0;
import xb.q0;

/* JADX INFO: loaded from: classes5.dex */
public final class r implements o0 {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final xb.l f20944i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public int f20945l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public int f20946m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public int f20947n;

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    public int f20948o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public int f20949p;

    public r(xb.l lVar) {
        this.f20944i = lVar;
    }

    @Override // xb.o0
    public final long read(xb.j jVar, long j10) throws IOException {
        int i10;
        int i11;
        do {
            int i12 = this.f20948o;
            xb.l lVar = this.f20944i;
            if (i12 == 0) {
                lVar.skip(this.f20949p);
                this.f20949p = 0;
                if ((this.f20946m & 4) == 0) {
                    i10 = this.f20947n;
                    int iT = ib.c.t(lVar);
                    this.f20948o = iT;
                    this.f20945l = iT;
                    int i13 = lVar.readByte() & 255;
                    this.f20946m = lVar.readByte() & 255;
                    Logger logger = t.f20950n;
                    if (logger.isLoggable(Level.FINE)) {
                        xb.m mVar = e.f20894a;
                        logger.fine(e.a(this.f20947n, this.f20945l, i13, this.f20946m, true));
                    }
                    i11 = lVar.readInt() & Integer.MAX_VALUE;
                    this.f20947n = i11;
                    if (i13 != 9) {
                        throw new IOException(a2.i(i13, " != TYPE_CONTINUATION"));
                    }
                }
            } else {
                long j11 = lVar.read(jVar, Math.min(j10, i12));
                if (j11 != -1) {
                    this.f20948o -= (int) j11;
                    return j11;
                }
            }
            return -1L;
        } while (i11 == i10);
        throw new IOException("TYPE_CONTINUATION streamId changed");
    }

    @Override // xb.o0
    public final q0 timeout() {
        return this.f20944i.timeout();
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable
    public final void close() {
    }
}
