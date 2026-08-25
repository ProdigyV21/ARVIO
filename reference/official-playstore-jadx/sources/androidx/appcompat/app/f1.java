package androidx.appcompat.app;

import android.content.Context;
import android.view.KeyCharacterMap;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.Window;
import androidx.appcompat.widget.ActionMenuView;
import androidx.appcompat.widget.Toolbar;
import androidx.appcompat.widget.l4;
import androidx.appcompat.widget.q4;
import androidx.core.view.b2;
import java.util.ArrayList;
import java.util.WeakHashMap;

/* JADX INFO: loaded from: classes.dex */
public final class f1 extends f {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final q4 f1042a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final Window.Callback f1043b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final e1 f1044c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public boolean f1045d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public boolean f1046e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public boolean f1047f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public final ArrayList f1048g = new ArrayList();

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public final a0 f1049h = new a0(this, 2);

    public f1(Toolbar toolbar, CharSequence charSequence, l0 l0Var) {
        c1 c1Var = new c1(this);
        q4 q4Var = new q4(toolbar, false);
        this.f1042a = q4Var;
        l0Var.getClass();
        this.f1043b = l0Var;
        q4Var.k = l0Var;
        toolbar.setOnMenuItemClickListener(c1Var);
        q4Var.setWindowTitle(charSequence);
        this.f1044c = new e1(this);
    }

    @Override // androidx.appcompat.app.f
    public final boolean e() {
        return this.f1042a.b();
    }

    @Override // androidx.appcompat.app.f
    public final boolean f() {
        q4 q4Var = this.f1042a;
        l4 l4Var = q4Var.f1723a.W;
        if (l4Var == null || l4Var.f1657l == null) {
            return false;
        }
        q4Var.collapseActionView();
        return true;
    }

    @Override // androidx.appcompat.app.f
    public final void g(boolean z) {
        if (z == this.f1047f) {
            return;
        }
        this.f1047f = z;
        ArrayList arrayList = this.f1048g;
        int size = arrayList.size();
        for (int i10 = 0; i10 < size; i10++) {
            ((c) arrayList.get(i10)).a();
        }
    }

    @Override // androidx.appcompat.app.f
    public final int h() {
        return this.f1042a.f1724b;
    }

    @Override // androidx.appcompat.app.f
    public final Context i() {
        return this.f1042a.f1723a.getContext();
    }

    @Override // androidx.appcompat.app.f
    public final boolean j() {
        q4 q4Var = this.f1042a;
        Toolbar toolbar = q4Var.f1723a;
        a0 a0Var = this.f1049h;
        toolbar.removeCallbacks(a0Var);
        Toolbar toolbar2 = q4Var.f1723a;
        WeakHashMap weakHashMap = b2.f2200a;
        toolbar2.postOnAnimation(a0Var);
        return true;
    }

    @Override // androidx.appcompat.app.f
    public final void l() {
        this.f1042a.f1723a.removeCallbacks(this.f1049h);
    }

    @Override // androidx.appcompat.app.f
    public final boolean m(int i10, KeyEvent keyEvent) {
        Menu menuX = x();
        if (menuX == null) {
            return false;
        }
        menuX.setQwertyMode(KeyCharacterMap.load(keyEvent.getDeviceId()).getKeyboardType() != 1);
        return menuX.performShortcut(i10, keyEvent, 0);
    }

    @Override // androidx.appcompat.app.f
    public final boolean n(KeyEvent keyEvent) {
        if (keyEvent.getAction() == 1) {
            o();
        }
        return true;
    }

    @Override // androidx.appcompat.app.f
    public final boolean o() {
        return this.f1042a.f1723a.t();
    }

    @Override // androidx.appcompat.app.f
    public final void p(boolean z) {
    }

    @Override // androidx.appcompat.app.f
    public final void q(boolean z) {
        q4 q4Var = this.f1042a;
        q4Var.i((q4Var.f1724b & (-5)) | 4);
    }

    @Override // androidx.appcompat.app.f
    public final void r(int i10) {
        this.f1042a.q(i10);
    }

    @Override // androidx.appcompat.app.f
    public final void s(boolean z) {
    }

    @Override // androidx.appcompat.app.f
    public final void t(String str) {
        this.f1042a.j(str);
    }

    @Override // androidx.appcompat.app.f
    public final void u(String str) {
        this.f1042a.setTitle(str);
    }

    @Override // androidx.appcompat.app.f
    public final void v(CharSequence charSequence) {
        this.f1042a.setWindowTitle(charSequence);
    }

    public final Menu x() {
        boolean z = this.f1046e;
        q4 q4Var = this.f1042a;
        if (!z) {
            d1 d1Var = new d1(this);
            e1 e1Var = new e1(this);
            Toolbar toolbar = q4Var.f1723a;
            toolbar.f1475a0 = d1Var;
            toolbar.b0 = e1Var;
            ActionMenuView actionMenuView = toolbar.f1480i;
            if (actionMenuView != null) {
                actionMenuView.E = d1Var;
                actionMenuView.F = e1Var;
            }
            this.f1046e = true;
        }
        return q4Var.f1723a.getMenu();
    }

    @Override // androidx.appcompat.app.f
    public final void k() {
    }
}
