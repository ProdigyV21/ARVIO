package a8;

/* JADX INFO: loaded from: classes5.dex */
public final class o1 extends kotlin.jvm.internal.r implements r7.a {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final /* synthetic */ int f234i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final /* synthetic */ p1 f235l;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public /* synthetic */ o1(p1 p1Var, int i10) {
        super(0);
        this.f234i = i10;
        this.f235l = p1Var;
    }

    @Override // r7.a
    public final Object invoke() {
        switch (this.f234i) {
            case 0:
                return kotlin.reflect.b0.b(this.f235l, false);
            default:
                p1 p1Var = this.f235l;
                g8.r0 setter = p1Var.C().x().getSetter();
                return setter == null ? i9.q.g(p1Var.C().x(), h8.g.f15863a) : setter;
        }
    }
}
