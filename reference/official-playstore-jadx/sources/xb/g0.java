package xb;

/* JADX INFO: loaded from: classes5.dex */
public final class g0 implements o0 {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final l f22742i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final j f22743l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public j0 f22744m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public int f22745n;

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    public boolean f22746o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public long f22747p;

    public g0(l lVar) {
        this.f22742i = lVar;
        j jVarH = lVar.h();
        this.f22743l = jVarH;
        j0 j0Var = jVarH.f22758i;
        this.f22744m = j0Var;
        this.f22745n = j0Var != null ? j0Var.f22761b : -1;
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable
    public final void close() {
        this.f22746o = true;
    }

    @Override // xb.o0
    public final long read(j jVar, long j10) {
        j0 j0Var;
        j0 j0Var2;
        if (j10 < 0) {
            throw new IllegalArgumentException(androidx.compose.foundation.c.q(j10, "byteCount < 0: ").toString());
        }
        if (this.f22746o) {
            throw new IllegalStateException("closed");
        }
        j0 j0Var3 = this.f22744m;
        j jVar2 = this.f22743l;
        if (j0Var3 != null && (j0Var3 != (j0Var2 = jVar2.f22758i) || this.f22745n != j0Var2.f22761b)) {
            throw new IllegalStateException("Peek source is invalid because upstream source was used");
        }
        if (j10 == 0) {
            return 0L;
        }
        if (!this.f22742i.request(this.f22747p + 1)) {
            return -1L;
        }
        if (this.f22744m == null && (j0Var = jVar2.f22758i) != null) {
            this.f22744m = j0Var;
            this.f22745n = j0Var.f22761b;
        }
        long jMin = Math.min(j10, jVar2.f22759l - this.f22747p);
        this.f22743l.l(jVar, this.f22747p, jMin);
        this.f22747p += jMin;
        return jMin;
    }

    @Override // xb.o0
    public final q0 timeout() {
        return this.f22742i.timeout();
    }
}
