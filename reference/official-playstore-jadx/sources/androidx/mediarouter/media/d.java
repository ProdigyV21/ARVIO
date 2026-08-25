package androidx.mediarouter.media;

/* JADX INFO: loaded from: classes3.dex */
public final /* synthetic */ class d implements Runnable {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final /* synthetic */ int f4319i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final /* synthetic */ androidx.emoji2.text.y f4320l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ int f4321m;

    public /* synthetic */ d(androidx.emoji2.text.y yVar, int i10, int i11) {
        this.f4319i = i11;
        this.f4320l = yVar;
        this.f4321m = i10;
    }

    @Override // java.lang.Runnable
    public final void run() {
        u uVarF;
        switch (this.f4319i) {
            case 0:
                i0 i0Var = ((f) ((androidx.appcompat.app.i1) this.f4320l.f2656f).f1061n).f4329d;
                if (i0Var != null) {
                    k0.b();
                    f fVarC = k0.c();
                    int iMin = Math.min(i0Var.f4401q, Math.max(0, this.f4321m));
                    u uVarF2 = fVarC.f(i0Var);
                    if (uVarF2 != null) {
                        uVarF2.f(iMin);
                    }
                }
                break;
            default:
                i0 i0Var2 = ((f) ((androidx.appcompat.app.i1) this.f4320l.f2656f).f1061n).f4329d;
                if (i0Var2 != null) {
                    k0.b();
                    int i10 = this.f4321m;
                    if (i10 != 0 && (uVarF = k0.c().f(i0Var2)) != null) {
                        uVarF.i(i10);
                        break;
                    }
                }
                break;
        }
    }
}
