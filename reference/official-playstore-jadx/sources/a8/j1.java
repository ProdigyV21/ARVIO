package a8;

/* JADX INFO: loaded from: classes5.dex */
public final class j1 extends kotlin.jvm.internal.r implements r7.a {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final /* synthetic */ int f205i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final /* synthetic */ k1 f206l;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public /* synthetic */ j1(k1 k1Var, int i10) {
        super(0);
        this.f205i = i10;
        this.f206l = k1Var;
    }

    @Override // r7.a
    public final Object invoke() {
        switch (this.f205i) {
            case 0:
                return new i1(this.f206l);
            default:
                return this.f206l.B();
        }
    }
}
