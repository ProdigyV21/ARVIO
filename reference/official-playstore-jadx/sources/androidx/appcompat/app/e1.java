package androidx.appcompat.app;

import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import androidx.appcompat.widget.ActionMenuView;

/* JADX INFO: loaded from: classes.dex */
public final class e1 implements e0, androidx.appcompat.view.menu.o {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final /* synthetic */ f1 f1040i;

    public /* synthetic */ e1(f1 f1Var) {
        this.f1040i = f1Var;
    }

    @Override // androidx.appcompat.app.e0
    public void a(int i10) {
        if (i10 == 0) {
            f1 f1Var = this.f1040i;
            if (f1Var.f1045d) {
                return;
            }
            f1Var.f1042a.f1733l = true;
            f1Var.f1045d = true;
        }
    }

    @Override // androidx.appcompat.view.menu.o
    public boolean b(androidx.appcompat.view.menu.q qVar, MenuItem menuItem) {
        return false;
    }

    @Override // androidx.appcompat.view.menu.o
    public void f(androidx.appcompat.view.menu.q qVar) {
        androidx.appcompat.widget.m mVar;
        f1 f1Var = this.f1040i;
        Window.Callback callback = f1Var.f1043b;
        ActionMenuView actionMenuView = f1Var.f1042a.f1723a.f1480i;
        if (actionMenuView != null && (mVar = actionMenuView.D) != null && mVar.k()) {
            callback.onPanelClosed(108, qVar);
        } else if (callback.onPreparePanel(0, null, qVar)) {
            callback.onMenuOpened(108, qVar);
        }
    }

    @Override // androidx.appcompat.app.e0
    public View onCreatePanelView(int i10) {
        if (i10 == 0) {
            return new View(this.f1040i.f1042a.f1723a.getContext());
        }
        return null;
    }
}
