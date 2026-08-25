package androidx.appcompat.view.menu;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.MenuItem;
import android.view.SubMenu;
import android.view.View;

/* JADX INFO: loaded from: classes.dex */
public final class i0 extends q implements SubMenu {
    public final t A;
    public final q z;

    public i0(Context context, q qVar, t tVar) {
        super(context);
        this.z = qVar;
        this.A = tVar;
    }

    @Override // androidx.appcompat.view.menu.q
    public final boolean d(t tVar) {
        return this.z.d(tVar);
    }

    @Override // androidx.appcompat.view.menu.q
    public final boolean e(q qVar, MenuItem menuItem) {
        return super.e(qVar, menuItem) || this.z.e(qVar, menuItem);
    }

    @Override // androidx.appcompat.view.menu.q
    public final boolean f(t tVar) {
        return this.z.f(tVar);
    }

    @Override // android.view.SubMenu
    public final MenuItem getItem() {
        return this.A;
    }

    @Override // androidx.appcompat.view.menu.q
    public final String j() {
        t tVar = this.A;
        int i10 = tVar != null ? tVar.f1367a : 0;
        if (i10 == 0) {
            return null;
        }
        return a0.c.i(i10, "android:menu:actionviewstates:");
    }

    @Override // androidx.appcompat.view.menu.q
    public final q k() {
        return this.z.k();
    }

    @Override // androidx.appcompat.view.menu.q
    public final boolean m() {
        return this.z.m();
    }

    @Override // androidx.appcompat.view.menu.q
    public final boolean n() {
        return this.z.n();
    }

    @Override // androidx.appcompat.view.menu.q
    public final boolean o() {
        return this.z.o();
    }

    @Override // androidx.appcompat.view.menu.q, android.view.Menu
    public final void setGroupDividerEnabled(boolean z) {
        this.z.setGroupDividerEnabled(z);
    }

    @Override // android.view.SubMenu
    public final SubMenu setHeaderIcon(Drawable drawable) {
        u(0, null, 0, drawable, null);
        return this;
    }

    @Override // android.view.SubMenu
    public final SubMenu setHeaderTitle(CharSequence charSequence) {
        u(0, charSequence, 0, null, null);
        return this;
    }

    @Override // android.view.SubMenu
    public final SubMenu setHeaderView(View view) {
        u(0, null, 0, null, view);
        return this;
    }

    @Override // android.view.SubMenu
    public final SubMenu setIcon(Drawable drawable) {
        this.A.setIcon(drawable);
        return this;
    }

    @Override // androidx.appcompat.view.menu.q, android.view.Menu
    public final void setQwertyMode(boolean z) {
        this.z.setQwertyMode(z);
    }

    @Override // android.view.SubMenu
    public final SubMenu setHeaderIcon(int i10) {
        u(0, null, i10, null, null);
        return this;
    }

    @Override // android.view.SubMenu
    public final SubMenu setHeaderTitle(int i10) {
        u(i10, null, 0, null, null);
        return this;
    }

    @Override // android.view.SubMenu
    public final SubMenu setIcon(int i10) {
        this.A.setIcon(i10);
        return this;
    }
}
