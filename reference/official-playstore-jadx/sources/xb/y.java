package xb;

import java.io.EOFException;
import java.io.IOException;
import java.util.zip.DataFormatException;
import java.util.zip.Inflater;

/* JADX INFO: loaded from: classes5.dex */
public final class y implements o0 {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final i0 f22805i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final Inflater f22806l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public int f22807m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public boolean f22808n;

    public y(i0 i0Var, Inflater inflater) {
        this.f22805i = i0Var;
        this.f22806l = inflater;
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable
    public final void close() throws IOException {
        if (this.f22808n) {
            return;
        }
        this.f22806l.end();
        this.f22808n = true;
        this.f22805i.close();
    }

    public final long i(j jVar, long j10) throws IOException {
        Inflater inflater = this.f22806l;
        if (j10 < 0) {
            throw new IllegalArgumentException(androidx.compose.foundation.c.q(j10, "byteCount < 0: ").toString());
        }
        if (this.f22808n) {
            throw new IllegalStateException("closed");
        }
        if (j10 != 0) {
            try {
                j0 j0VarD0 = jVar.D0(1);
                int iMin = (int) Math.min(j10, 8192 - j0VarD0.f22762c);
                boolean zNeedsInput = inflater.needsInput();
                i0 i0Var = this.f22805i;
                if (zNeedsInput && !i0Var.i()) {
                    j0 j0Var = i0Var.f22756l.f22758i;
                    int i10 = j0Var.f22762c;
                    int i11 = j0Var.f22761b;
                    int i12 = i10 - i11;
                    this.f22807m = i12;
                    inflater.setInput(j0Var.f22760a, i11, i12);
                }
                int iInflate = inflater.inflate(j0VarD0.f22760a, j0VarD0.f22762c, iMin);
                int i13 = this.f22807m;
                if (i13 != 0) {
                    int remaining = i13 - inflater.getRemaining();
                    this.f22807m -= remaining;
                    i0Var.skip(remaining);
                }
                if (iInflate > 0) {
                    j0VarD0.f22762c += iInflate;
                    long j11 = iInflate;
                    jVar.f22759l += j11;
                    return j11;
                }
                if (j0VarD0.f22761b == j0VarD0.f22762c) {
                    jVar.f22758i = j0VarD0.a();
                    k0.a(j0VarD0);
                }
            } catch (DataFormatException e5) {
                throw new IOException(e5);
            }
        }
        return 0L;
    }

    @Override // xb.o0
    public final long read(j jVar, long j10) throws IOException {
        do {
            long jI = i(jVar, j10);
            if (jI > 0) {
                return jI;
            }
            Inflater inflater = this.f22806l;
            if (inflater.finished() || inflater.needsDictionary()) {
                return -1L;
            }
        } while (!this.f22805i.i());
        throw new EOFException("source exhausted prematurely");
    }

    @Override // xb.o0
    public final q0 timeout() {
        return this.f22805i.f22755i.timeout();
    }

    public y(o0 o0Var, Inflater inflater) {
        this(new i0(o0Var), inflater);
    }
}
