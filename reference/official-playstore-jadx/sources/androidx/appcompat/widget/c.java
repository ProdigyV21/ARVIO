package androidx.appcompat.widget;

import android.view.View;

/* JADX INFO: loaded from: classes.dex */
public final class c implements View.OnClickListener {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final /* synthetic */ int f1519i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final /* synthetic */ Object f1520l;

    public /* synthetic */ c(Object obj, int i10) {
        this.f1519i = i10;
        this.f1520l = obj;
    }

    @Override // android.view.View.OnClickListener
    public final void onClick(View view) {
        switch (this.f1519i) {
            case 0:
                ((n.b) this.f1520l).a();
                break;
            default:
                l4 l4Var = ((Toolbar) this.f1520l).W;
                androidx.appcompat.view.menu.t tVar = l4Var == null ? null : l4Var.f1657l;
                if (tVar != null) {
                    tVar.collapseActionView();
                }
                break;
        }
    }
}
