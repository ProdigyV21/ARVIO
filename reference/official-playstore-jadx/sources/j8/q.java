package j8;

/* JADX INFO: loaded from: classes5.dex */
public final class q implements r7.l {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final /* synthetic */ int f19333i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final /* synthetic */ s f19334l;

    public /* synthetic */ q(s sVar, int i10) {
        this.f19333i = i10;
        this.f19334l = sVar;
    }

    @Override // r7.l
    public final Object invoke(Object obj) {
        switch (this.f19333i) {
            case 0:
                f9.f fVar = (f9.f) obj;
                if (fVar != null) {
                    s sVar = this.f19334l;
                    return sVar.j(fVar, sVar.i().c(fVar, o8.e.f20798p));
                }
                s.h(8);
                throw null;
            default:
                f9.f fVar2 = (f9.f) obj;
                if (fVar2 != null) {
                    s sVar2 = this.f19334l;
                    return sVar2.j(fVar2, sVar2.i().g(fVar2, o8.e.f20798p));
                }
                s.h(4);
                throw null;
        }
    }
}
