package a8;

/* JADX INFO: loaded from: classes5.dex */
public final class g1 extends kotlin.jvm.internal.r implements r7.a {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final /* synthetic */ int f195i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final /* synthetic */ h1 f196l;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public /* synthetic */ g1(h1 h1Var, int i10) {
        super(0);
        this.f195i = i10;
        this.f196l = h1Var;
    }

    @Override // r7.a
    public final Object invoke() {
        switch (this.f195i) {
            case 0:
                return new f1(this.f196l);
            default:
                return this.f196l.B();
        }
    }
}
