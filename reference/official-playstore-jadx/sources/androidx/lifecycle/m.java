package androidx.lifecycle;

/* JADX INFO: loaded from: classes3.dex */
public final /* synthetic */ class m implements w {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final /* synthetic */ int f3258i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final /* synthetic */ Object f3259l;

    public /* synthetic */ m(Object obj, int i10) {
        this.f3258i = i10;
        this.f3259l = obj;
    }

    @Override // androidx.lifecycle.w
    public final void onStateChanged(y yVar, p pVar) {
        switch (this.f3258i) {
            case 0:
                ((na.j1) this.f3259l).h(null, pVar.a());
                break;
            case 1:
                androidx.navigation.s sVar = (androidx.navigation.s) this.f3259l;
                sVar.f4776r = pVar.a();
                if (sVar.f4762c != null) {
                    for (androidx.navigation.i iVar : sVar.f4766g) {
                        iVar.getClass();
                        iVar.f4683n = pVar.a();
                        iVar.c();
                    }
                }
                break;
            default:
                b2.e eVar = (b2.e) this.f3259l;
                if (pVar == p.ON_START) {
                    eVar.f7119f = true;
                } else if (pVar == p.ON_STOP) {
                    eVar.f7119f = false;
                }
                break;
        }
    }
}
