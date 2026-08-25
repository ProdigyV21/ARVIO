package retrofit2;

import java.io.IOException;

/* JADX INFO: loaded from: classes5.dex */
public final class w extends gb.s0 {

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final gb.s0 f21603l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final xb.i0 f21604m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public IOException f21605n;

    public w(gb.s0 s0Var) {
        this.f21603l = s0Var;
        this.f21604m = new xb.i0(new gb.c(this, s0Var.o()));
    }

    @Override // gb.s0, java.io.Closeable, java.lang.AutoCloseable
    public final void close() {
        this.f21603l.close();
    }

    @Override // gb.s0
    public final long k() {
        return this.f21603l.k();
    }

    @Override // gb.s0
    public final gb.d0 l() {
        return this.f21603l.l();
    }

    @Override // gb.s0
    public final xb.l o() {
        return this.f21604m;
    }
}
