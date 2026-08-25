package retrofit2;

/* JADX INFO: loaded from: classes5.dex */
public final class x extends gb.s0 {

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final gb.d0 f21606l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final long f21607m;

    public x(gb.d0 d0Var, long j10) {
        this.f21606l = d0Var;
        this.f21607m = j10;
    }

    @Override // gb.s0
    public final long k() {
        return this.f21607m;
    }

    @Override // gb.s0
    public final gb.d0 l() {
        return this.f21606l;
    }

    @Override // gb.s0
    public final xb.l o() {
        throw new IllegalStateException("Cannot read raw response body of a converted body.");
    }
}
