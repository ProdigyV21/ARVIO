package ka;

/* JADX INFO: loaded from: classes5.dex */
public final class d1 extends f1 {

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final l f19571m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final /* synthetic */ h1 f19572n;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public d1(h1 h1Var, long j10, l lVar) {
        super(j10);
        this.f19572n = h1Var;
        this.f19571m = lVar;
    }

    @Override // java.lang.Runnable
    public final void run() {
        this.f19571m.A(this.f19572n);
    }

    @Override // ka.f1
    public final String toString() {
        return super.toString() + this.f19571m;
    }
}
