package lb;

import com.google.android.gms.common.api.internal.g0;
import gb.u;
import java.io.IOException;
import java.net.ProtocolException;
import xb.o0;
import xb.v;

/* JADX INFO: loaded from: classes5.dex */
public final class d extends v {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final long f20089i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public long f20090l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public boolean f20091m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public boolean f20092n;

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    public boolean f20093o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public final /* synthetic */ g0 f20094p;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public d(g0 g0Var, o0 o0Var, long j10) {
        super(o0Var);
        this.f20094p = g0Var;
        this.f20089i = j10;
        this.f20091m = true;
        if (j10 == 0) {
            i(null);
        }
    }

    @Override // xb.v, java.io.Closeable, java.lang.AutoCloseable
    public final void close() throws IOException {
        if (this.f20093o) {
            return;
        }
        this.f20093o = true;
        try {
            super.close();
            i(null);
        } catch (IOException e5) {
            throw i(e5);
        }
    }

    public final IOException i(IOException iOException) {
        if (this.f20092n) {
            return iOException;
        }
        this.f20092n = true;
        g0 g0Var = this.f20094p;
        if (iOException == null && this.f20091m) {
            this.f20091m = false;
            ((u) g0Var.f12735c).getClass();
        }
        return g0Var.n(true, false, iOException);
    }

    @Override // xb.v, xb.o0
    public final long read(xb.j jVar, long j10) throws IOException {
        if (this.f20093o) {
            throw new IllegalStateException("closed");
        }
        try {
            long j11 = delegate().read(jVar, j10);
            if (this.f20091m) {
                this.f20091m = false;
                ((u) this.f20094p.f12735c).getClass();
            }
            if (j11 == -1) {
                i(null);
                return -1L;
            }
            long j12 = this.f20090l + j11;
            long j13 = this.f20089i;
            if (j13 == -1 || j12 <= j13) {
                this.f20090l = j12;
                if (j12 == j13) {
                    i(null);
                }
                return j11;
            }
            throw new ProtocolException("expected " + j13 + " bytes but received " + j12);
        } catch (IOException e5) {
            throw i(e5);
        }
    }
}
