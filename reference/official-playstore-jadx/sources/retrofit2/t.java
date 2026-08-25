package retrofit2;

/* JADX INFO: loaded from: classes5.dex */
public final class t extends kotlin.jvm.internal.r implements r7.l {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final /* synthetic */ int f21586i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final /* synthetic */ c f21587l;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public /* synthetic */ t(c cVar, int i10) {
        super(1);
        this.f21586i = i10;
        this.f21587l = cVar;
    }

    @Override // r7.l
    public final Object invoke(Object obj) {
        switch (this.f21586i) {
            case 0:
                this.f21587l.cancel();
                break;
            default:
                this.f21587l.cancel();
                break;
        }
        return x6.t0.f22605a;
    }
}
