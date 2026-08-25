package androidx.appcompat.widget;

import android.view.MenuItem;
import androidx.appcompat.widget.ActionMenuView;

/* JADX INFO: loaded from: classes.dex */
public final class p implements androidx.appcompat.view.menu.o {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final /* synthetic */ ActionMenuView f1710i;

    public p(ActionMenuView actionMenuView) {
        this.f1710i = actionMenuView;
    }

    @Override // androidx.appcompat.view.menu.o
    public final boolean b(androidx.appcompat.view.menu.q qVar, MenuItem menuItem) {
        ActionMenuView.b bVar = this.f1710i.K;
        return bVar != null && bVar.onMenuItemClick(menuItem);
    }

    @Override // androidx.appcompat.view.menu.o
    public final void f(androidx.appcompat.view.menu.q qVar) {
        androidx.appcompat.view.menu.o oVar = this.f1710i.F;
        if (oVar != null) {
            oVar.f(qVar);
        }
    }
}
