package y8;

import g8.v0;
import java.util.ArrayList;
import y8.y;

/* JADX INFO: loaded from: classes5.dex */
public abstract class l implements y.a {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final /* synthetic */ n f22952a;

    public l(n nVar) {
        this.f22952a = nVar;
    }

    @Override // y8.y.a
    public final y.b b(f9.f fVar) {
        return new k(this.f22952a, fVar, this);
    }

    @Override // y8.y.a
    public final y.a c(f9.b bVar, f9.f fVar) {
        ArrayList arrayList = new ArrayList();
        n nVar = this.f22952a;
        return new i(new m(nVar, g8.y.f(nVar.f22959m, bVar, nVar.f22960n), bVar, arrayList, v0.f15554b), this, fVar, arrayList);
    }

    @Override // y8.y.a
    public final void d(f9.f fVar, f9.b bVar, f9.f fVar2) {
        ((m) this).f22953b.put(fVar, new k9.i(bVar, fVar2));
    }

    @Override // y8.y.a
    public final void e(f9.f fVar, k9.f fVar2) {
        ((m) this).f22953b.put(fVar, new k9.s(fVar2));
    }

    @Override // y8.y.a
    public final void f(f9.f fVar, Object obj) {
        k9.g gVarB = k9.h.b(obj, this.f22952a.f22959m);
        if (gVarB == null) {
            gVarB = new k9.j("Unsupported annotation argument: " + fVar);
        }
        ((m) this).f22953b.put(fVar, gVarB);
    }
}
