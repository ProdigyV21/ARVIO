package a8;

/* JADX INFO: loaded from: classes5.dex */
public final class t0 extends kotlin.jvm.internal.r implements r7.a {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final /* synthetic */ int f268i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final /* synthetic */ y0 f269l;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public /* synthetic */ t0(y0 y0Var, int i10) {
        super(0);
        this.f268i = i10;
        this.f269l = y0Var;
    }

    @Override // r7.a
    public final Object invoke() {
        switch (this.f268i) {
            case 0:
                return l8.d.a(this.f269l.f310l);
            default:
                return new w0(this.f269l);
        }
    }
}
