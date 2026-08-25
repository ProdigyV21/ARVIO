package t8;

/* JADX INFO: loaded from: classes5.dex */
public final class k extends kotlin.jvm.internal.r implements r7.l {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final /* synthetic */ int f21943i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final /* synthetic */ n f21944l;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public /* synthetic */ k(n nVar, int i10) {
        super(1);
        this.f21943i = i10;
        this.f21944l = nVar;
    }

    @Override // r7.l
    public final Object invoke(Object obj) {
        switch (this.f21943i) {
            case 0:
                return n.u(this.f21944l, (f9.f) obj);
            default:
                return n.v(this.f21944l, (f9.f) obj);
        }
    }
}
