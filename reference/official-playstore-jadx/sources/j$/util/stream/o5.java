package j$.util.stream;

/* JADX INFO: loaded from: classes2.dex */
public final class o5 extends f5 {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public long f18818b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public long f18819c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final /* synthetic */ p5 f18820d;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public o5(p5 p5Var, l5 l5Var) {
        super(l5Var);
        this.f18820d = p5Var;
        this.f18818b = p5Var.f18829s;
        long j10 = p5Var.f18830t;
        this.f18819c = j10 < 0 ? Long.MAX_VALUE : j10;
    }

    @Override // j$.util.stream.f5, j$.util.stream.l5
    public final void c(long j10) {
        this.f18691a.c(v3.W(j10, this.f18820d.f18829s, this.f18819c));
    }

    @Override // j$.util.stream.j5, j$.util.stream.l5
    public final void accept(int i10) {
        long j10 = this.f18818b;
        if (j10 == 0) {
            long j11 = this.f18819c;
            if (j11 > 0) {
                this.f18819c = j11 - 1;
                this.f18691a.accept(i10);
                return;
            }
            return;
        }
        this.f18818b = j10 - 1;
    }

    @Override // j$.util.stream.f5, j$.util.stream.l5
    public final boolean f() {
        return this.f18819c == 0 || this.f18691a.f();
    }
}
