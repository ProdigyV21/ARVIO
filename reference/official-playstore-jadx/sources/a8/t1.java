package a8;

/* JADX INFO: loaded from: classes5.dex */
public final class t1 extends kotlin.jvm.internal.r implements r7.a {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final /* synthetic */ int f270i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final /* synthetic */ u1 f271l;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public /* synthetic */ t1(u1 u1Var, int i10) {
        super(0);
        this.f270i = i10;
        this.f271l = u1Var;
    }

    @Override // r7.a
    public final Object invoke() {
        switch (this.f270i) {
            case 0:
                return m8.c.c(this.f271l.k());
            default:
                u1 u1Var = this.f271l;
                return u1Var.c(u1Var.f282i);
        }
    }
}
