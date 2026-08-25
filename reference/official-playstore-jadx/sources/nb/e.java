package nb;

import androidx.appcompat.widget.u;
import java.io.IOException;
import java.util.zip.Deflater;
import xb.h0;
import xb.j;
import xb.j0;
import xb.k;
import xb.k0;
import xb.m0;
import xb.q0;
import xb.w;

/* JADX INFO: loaded from: classes5.dex */
public final class e implements m0 {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final /* synthetic */ int f20710i = 0;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public boolean f20711l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final Object f20712m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final Object f20713n;

    public e(j jVar, Deflater deflater) {
        this.f20712m = new h0(jVar);
        this.f20713n = deflater;
    }

    @Override // xb.m0, java.io.Closeable, java.lang.AutoCloseable
    public final void close() throws Throwable {
        switch (this.f20710i) {
            case 0:
                u uVar = (u) this.f20713n;
                if (this.f20711l) {
                    return;
                }
                this.f20711l = true;
                w wVar = (w) this.f20712m;
                q0 q0Var = wVar.f22799e;
                wVar.f22799e = q0.f22791d;
                q0Var.a();
                q0Var.b();
                uVar.f1762a = 3;
                return;
            default:
                Deflater deflater = (Deflater) this.f20713n;
                if (this.f20711l) {
                    return;
                }
                try {
                    deflater.finish();
                    i(false);
                    th = null;
                } catch (Throwable th) {
                    th = th;
                }
                try {
                    deflater.end();
                    break;
                } catch (Throwable th2) {
                    if (th == null) {
                        th = th2;
                    }
                }
                try {
                    ((h0) this.f20712m).close();
                    break;
                } catch (Throwable th3) {
                    if (th == null) {
                        th = th3;
                    }
                }
                this.f20711l = true;
                if (th != null) {
                    throw th;
                }
                return;
        }
    }

    @Override // xb.m0, java.io.Flushable
    public final void flush() throws IOException {
        switch (this.f20710i) {
            case 0:
                if (!this.f20711l) {
                    ((k) ((u) this.f20713n).f1766e).flush();
                    break;
                }
                break;
            default:
                i(true);
                ((h0) this.f20712m).flush();
                break;
        }
    }

    public void i(boolean z) throws IOException {
        j0 j0VarD0;
        int iDeflate;
        Deflater deflater = (Deflater) this.f20713n;
        h0 h0Var = (h0) this.f20712m;
        j jVar = h0Var.f22751l;
        while (true) {
            j0VarD0 = jVar.D0(1);
            byte[] bArr = j0VarD0.f22760a;
            if (z) {
                try {
                    int i10 = j0VarD0.f22762c;
                    iDeflate = deflater.deflate(bArr, i10, 8192 - i10, 2);
                } catch (NullPointerException e5) {
                    throw new IOException("Deflater already closed", e5);
                }
            } else {
                int i11 = j0VarD0.f22762c;
                iDeflate = deflater.deflate(bArr, i11, 8192 - i11);
            }
            if (iDeflate > 0) {
                j0VarD0.f22762c += iDeflate;
                jVar.f22759l += (long) iDeflate;
                h0Var.i();
            } else if (deflater.needsInput()) {
                break;
            }
        }
        if (j0VarD0.f22761b == j0VarD0.f22762c) {
            jVar.f22758i = j0VarD0.a();
            k0.a(j0VarD0);
        }
    }

    @Override // xb.m0
    public final q0 timeout() {
        switch (this.f20710i) {
            case 0:
                return (w) this.f20712m;
            default:
                return ((h0) this.f20712m).f22750i.timeout();
        }
    }

    public String toString() {
        switch (this.f20710i) {
            case 1:
                return "DeflaterSink(" + ((h0) this.f20712m) + ')';
            default:
                return super.toString();
        }
    }

    @Override // xb.m0
    public final void write(j jVar, long j10) throws IOException {
        switch (this.f20710i) {
            case 0:
                if (this.f20711l) {
                    throw new IllegalStateException("closed");
                }
                ib.c.c(jVar.f22759l, 0L, j10);
                ((k) ((u) this.f20713n).f1766e).write(jVar, j10);
                return;
            default:
                xb.b.c(jVar.f22759l, 0L, j10);
                while (j10 > 0) {
                    j0 j0Var = jVar.f22758i;
                    int iMin = (int) Math.min(j10, j0Var.f22762c - j0Var.f22761b);
                    ((Deflater) this.f20713n).setInput(j0Var.f22760a, j0Var.f22761b, iMin);
                    i(false);
                    long j11 = iMin;
                    jVar.f22759l -= j11;
                    int i10 = j0Var.f22761b + iMin;
                    j0Var.f22761b = i10;
                    if (i10 == j0Var.f22762c) {
                        jVar.f22758i = j0Var.a();
                        k0.a(j0Var);
                    }
                    j10 -= j11;
                }
                return;
        }
    }

    public e(u uVar) {
        this.f20713n = uVar;
        this.f20712m = new w(((k) uVar.f1766e).timeout());
    }
}
