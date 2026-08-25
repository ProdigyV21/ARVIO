package androidx.appcompat.view.menu;

import android.view.View;
import androidx.appcompat.widget.k2;

/* JADX INFO: loaded from: classes.dex */
public final class b extends k2 {

    /* JADX INFO: renamed from: t, reason: collision with root package name */
    public final /* synthetic */ int f1263t = 0;

    /* JADX INFO: renamed from: u, reason: collision with root package name */
    public final /* synthetic */ View f1264u;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public b(ActionMenuItemView actionMenuItemView) {
        super(actionMenuItemView);
        this.f1264u = actionMenuItemView;
    }

    @Override // androidx.appcompat.widget.k2
    public final g0 b() {
        androidx.appcompat.widget.g gVar;
        switch (this.f1263t) {
            case 0:
                c cVar = ((ActionMenuItemView) this.f1264u).f1229w;
                if (cVar == null || (gVar = ((androidx.appcompat.widget.h) cVar).f1593a.D) == null) {
                    return null;
                }
                return gVar.a();
            default:
                androidx.appcompat.widget.k kVar = ((androidx.appcompat.widget.j) this.f1264u).f1602n.C;
                if (kVar == null) {
                    return null;
                }
                return kVar.a();
        }
    }

    @Override // androidx.appcompat.widget.k2
    public final boolean c() {
        g0 g0VarB;
        switch (this.f1263t) {
            case 0:
                ActionMenuItemView actionMenuItemView = (ActionMenuItemView) this.f1264u;
                p pVar = actionMenuItemView.f1227u;
                return pVar != null && pVar.b(actionMenuItemView.f1224r) && (g0VarB = b()) != null && g0VarB.a();
            default:
                ((androidx.appcompat.widget.j) this.f1264u).f1602n.l();
                return true;
        }
    }

    @Override // androidx.appcompat.widget.k2
    public boolean d() {
        switch (this.f1263t) {
            case 1:
                androidx.appcompat.widget.m mVar = ((androidx.appcompat.widget.j) this.f1264u).f1602n;
                if (mVar.E != null) {
                    return false;
                }
                mVar.j();
                return true;
            default:
                return super.d();
        }
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public b(androidx.appcompat.widget.j jVar, androidx.appcompat.widget.j jVar2) {
        super(jVar2);
        this.f1264u = jVar;
    }
}
