package j$.util.stream;

/* JADX INFO: loaded from: classes2.dex */
public final class s5 extends e5 {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public long f18869b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public long f18870c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final /* synthetic */ t5 f18871d;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public s5(t5 t5Var, l5 l5Var) {
        super(l5Var);
        this.f18871d = t5Var;
        this.f18869b = t5Var.f18882s;
        long j10 = t5Var.f18883t;
        this.f18870c = j10 < 0 ? Long.MAX_VALUE : j10;
    }

    @Override // j$.util.stream.e5, j$.util.stream.l5
    public final void c(long j10) {
        this.f18680a.c(v3.W(j10, this.f18871d.f18882s, this.f18870c));
    }

    @Override // j$.util.stream.i5, j$.util.stream.l5
    public final void accept(double d4) {
        long j10 = this.f18869b;
        if (j10 == 0) {
            long j11 = this.f18870c;
            if (j11 > 0) {
                this.f18870c = j11 - 1;
                this.f18680a.accept(d4);
                return;
            }
            return;
        }
        this.f18869b = j10 - 1;
    }

    @Override // j$.util.stream.e5, j$.util.stream.l5
    public final boolean f() {
        return this.f18870c == 0 || this.f18680a.f();
    }
}
