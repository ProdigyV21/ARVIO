package a8;

/* JADX INFO: loaded from: classes5.dex */
public final class d1 extends kotlin.jvm.internal.r implements r7.a {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final /* synthetic */ int f179i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final /* synthetic */ e1 f180l;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public /* synthetic */ d1(e1 e1Var, int i10) {
        super(0);
        this.f179i = i10;
        this.f180l = e1Var;
    }

    @Override // r7.a
    public final Object invoke() {
        switch (this.f179i) {
            case 0:
                return new c1(this.f180l);
            default:
                e1 e1Var = this.f180l;
                return e1Var.C(e1Var.B(), null);
        }
    }
}
