package androidx.appcompat.widget;

import android.view.View;

/* JADX INFO: loaded from: classes.dex */
public final class i implements Runnable {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final k f1598i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final /* synthetic */ m f1599l;

    public i(m mVar, k kVar) {
        this.f1599l = mVar;
        this.f1598i = kVar;
    }

    @Override // java.lang.Runnable
    public final void run() {
        androidx.appcompat.view.menu.o oVar;
        m mVar = this.f1599l;
        androidx.appcompat.view.menu.q qVar = mVar.f1277m;
        if (qVar != null && (oVar = qVar.f1346e) != null) {
            oVar.f(qVar);
        }
        View view = (View) mVar.f1282r;
        if (view != null && view.getWindowToken() != null) {
            k kVar = this.f1598i;
            if (kVar.b()) {
                mVar.C = kVar;
            } else if (kVar.f1269e != null) {
                kVar.d(0, 0, false, false);
                mVar.C = kVar;
            }
        }
        mVar.E = null;
    }
}
