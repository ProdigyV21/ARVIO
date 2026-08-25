package nb;

import androidx.appcompat.widget.u;
import gb.b0;
import java.io.IOException;
import java.util.concurrent.TimeUnit;
import lb.k;

/* JADX INFO: loaded from: classes5.dex */
public final class c extends a {

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final b0 f20704n;

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    public long f20705o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public boolean f20706p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public final /* synthetic */ u f20707q;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public c(u uVar, b0 b0Var) {
        super(uVar);
        this.f20707q = uVar;
        this.f20704n = b0Var;
        this.f20705o = -1L;
        this.f20706p = true;
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable
    public final void close() {
        boolean zU;
        if (this.f20699l) {
            return;
        }
        if (this.f20706p) {
            TimeUnit timeUnit = TimeUnit.MILLISECONDS;
            try {
                zU = ib.c.u(this, 100);
            } catch (IOException unused) {
                zU = false;
            }
            if (!zU) {
                ((k) this.f20707q.f1764c).l();
                i();
            }
        }
        this.f20699l = true;
    }

    /* JADX WARN: Code restructure failed: missing block: B:33:0x009a, code lost:
    
        if (r11.f20706p == false) goto L34;
     */
    @Override // nb.a, xb.o0
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final long read(xb.j r12, long r13) throws java.io.IOException {
        /*
            Method dump skipped, instruction units count: 257
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: nb.c.read(xb.j, long):long");
    }
}
