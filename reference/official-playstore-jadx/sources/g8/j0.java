package g8;

/* JADX INFO: loaded from: classes5.dex */
public final class j0 extends kotlin.jvm.internal.r implements r7.l {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final /* synthetic */ int f15516i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final /* synthetic */ f9.c f15517l;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public /* synthetic */ j0(f9.c cVar, int i10) {
        super(1);
        this.f15516i = i10;
        this.f15517l = cVar;
    }

    @Override // r7.l
    public final Object invoke(Object obj) {
        switch (this.f15516i) {
            case 0:
                f9.c cVar = (f9.c) obj;
                return Boolean.valueOf(!cVar.d() && cVar.e().equals(this.f15517l));
            default:
                return ((h8.h) obj).j(this.f15517l);
        }
    }
}
