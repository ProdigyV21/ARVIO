package j$.util.stream;

/* JADX INFO: loaded from: classes2.dex */
public final class q5 extends g5 {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public long f18836b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public long f18837c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final /* synthetic */ r5 f18838d;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public q5(r5 r5Var, l5 l5Var) {
        super(l5Var);
        this.f18838d = r5Var;
        this.f18836b = r5Var.f18856s;
        long j10 = r5Var.f18857t;
        this.f18837c = j10 < 0 ? Long.MAX_VALUE : j10;
    }

    @Override // j$.util.stream.g5, j$.util.stream.l5
    public final void c(long j10) {
        this.f18699a.c(v3.W(j10, this.f18838d.f18856s, this.f18837c));
    }

    @Override // j$.util.stream.k5, j$.util.stream.l5
    public final void accept(long j10) {
        long j11 = this.f18836b;
        if (j11 == 0) {
            long j12 = this.f18837c;
            if (j12 > 0) {
                this.f18837c = j12 - 1;
                this.f18699a.accept(j10);
                return;
            }
            return;
        }
        this.f18836b = j11 - 1;
    }

    @Override // j$.util.stream.g5, j$.util.stream.l5
    public final boolean f() {
        return this.f18837c == 0 || this.f18699a.f();
    }
}
