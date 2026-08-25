package androidx.mediarouter.media;

import java.util.Collection;

/* JADX INFO: loaded from: classes3.dex */
public final class b implements r {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final /* synthetic */ f f4298a;

    public /* synthetic */ b(f fVar) {
        this.f4298a = fVar;
    }

    @Override // androidx.mediarouter.media.r
    public void a(s sVar, m mVar, Collection collection) {
        f fVar = this.f4298a;
        if (sVar == fVar.z && mVar != null) {
            g0 g0Var = fVar.f4348y.f4386a;
            String strD = mVar.d();
            c0 c0Var = new c0(g0Var, strD, fVar.b(g0Var, strD));
            c0Var.f(mVar);
            if (fVar.f4329d == c0Var) {
                return;
            }
            fVar.j(fVar, c0Var, fVar.z, 3, true, fVar.f4348y, collection);
            fVar.f4348y = null;
            fVar.z = null;
            return;
        }
        if (sVar == fVar.f4330e) {
            if (mVar != null) {
                fVar.q(fVar.f4329d, mVar);
            }
            i0 i0Var = fVar.f4329d;
            i0Var.getClass();
            c0 c0Var2 = i0Var instanceof c0 ? (c0) i0Var : null;
            if (c0Var2 != null) {
                c0Var2.i(collection);
            }
        }
    }
}
