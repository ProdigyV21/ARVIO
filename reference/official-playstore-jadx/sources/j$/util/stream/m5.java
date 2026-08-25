package j$.util.stream;

/* JADX INFO: loaded from: classes2.dex */
public final class m5 extends h5 {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public long f18788b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public long f18789c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final /* synthetic */ n5 f18790d;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public m5(n5 n5Var, l5 l5Var) {
        super(l5Var);
        this.f18790d = n5Var;
        this.f18788b = n5Var.f18806s;
        long j10 = n5Var.f18807t;
        this.f18789c = j10 < 0 ? Long.MAX_VALUE : j10;
    }

    @Override // j$.util.stream.h5, j$.util.stream.l5
    public final void c(long j10) {
        this.f18711a.c(v3.W(j10, this.f18790d.f18806s, this.f18789c));
    }

    @Override // java.util.function.Consumer
    /* JADX INFO: renamed from: accept */
    public final void n(Object obj) {
        long j10 = this.f18788b;
        if (j10 == 0) {
            long j11 = this.f18789c;
            if (j11 > 0) {
                this.f18789c = j11 - 1;
                this.f18711a.n(obj);
                return;
            }
            return;
        }
        this.f18788b = j10 - 1;
    }

    @Override // j$.util.stream.h5, j$.util.stream.l5
    public final boolean f() {
        return this.f18789c == 0 || this.f18711a.f();
    }
}
