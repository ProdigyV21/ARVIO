package androidx.appcompat.widget;

import androidx.appcompat.view.menu.d0;

/* JADX INFO: loaded from: classes.dex */
public final class l implements d0.a {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final /* synthetic */ m f1646i;

    public l(m mVar) {
        this.f1646i = mVar;
    }

    @Override // androidx.appcompat.view.menu.d0.a
    public final void b(androidx.appcompat.view.menu.q qVar, boolean z) {
        if (qVar instanceof androidx.appcompat.view.menu.i0) {
            ((androidx.appcompat.view.menu.i0) qVar).z.k().c(false);
        }
        d0.a aVar = this.f1646i.f1279o;
        if (aVar != null) {
            aVar.b(qVar, z);
        }
    }

    @Override // androidx.appcompat.view.menu.d0.a
    public final boolean c(androidx.appcompat.view.menu.q qVar) {
        m mVar = this.f1646i;
        if (qVar == mVar.f1277m) {
            return false;
        }
        ((androidx.appcompat.view.menu.i0) qVar).A.getClass();
        mVar.getClass();
        d0.a aVar = mVar.f1279o;
        if (aVar != null) {
            return aVar.c(qVar);
        }
        return false;
    }
}
