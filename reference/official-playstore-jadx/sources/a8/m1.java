package a8;

/* JADX INFO: loaded from: classes5.dex */
public final class m1 extends kotlin.jvm.internal.r implements r7.a {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final /* synthetic */ int f225i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final /* synthetic */ n1 f226l;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public /* synthetic */ m1(n1 n1Var, int i10) {
        super(0);
        this.f225i = i10;
        this.f226l = n1Var;
    }

    @Override // r7.a
    public final Object invoke() {
        switch (this.f225i) {
            case 0:
                return kotlin.reflect.b0.b(this.f226l, true);
            default:
                n1 n1Var = this.f226l;
                j8.m0 getter = n1Var.C().x().getGetter();
                return getter == null ? i9.q.f(n1Var.C().x(), h8.g.f15863a) : getter;
        }
    }
}
