package androidx.appcompat.view.menu;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.MenuItem;
import android.view.SubMenu;
import android.view.View;

/* JADX INFO: loaded from: classes.dex */
public final class j0 extends f0 implements SubMenu {

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final k0.c f1313f;

    public j0(Context context, k0.c cVar) {
        super(context, cVar);
        this.f1313f = cVar;
    }

    @Override // android.view.SubMenu
    public final void clearHeader() {
        this.f1313f.clearHeader();
    }

    @Override // android.view.SubMenu
    public final MenuItem getItem() {
        return f(this.f1313f.getItem());
    }

    @Override // android.view.SubMenu
    public final SubMenu setHeaderIcon(int i10) {
        this.f1313f.setHeaderIcon(i10);
        return this;
    }

    @Override // android.view.SubMenu
    public final SubMenu setHeaderTitle(int i10) {
        this.f1313f.setHeaderTitle(i10);
        return this;
    }

    @Override // android.view.SubMenu
    public final SubMenu setHeaderView(View view) {
        this.f1313f.setHeaderView(view);
        return this;
    }

    @Override // android.view.SubMenu
    public final SubMenu setIcon(int i10) {
        this.f1313f.setIcon(i10);
        return this;
    }

    @Override // android.view.SubMenu
    public final SubMenu setHeaderIcon(Drawable drawable) {
        this.f1313f.setHeaderIcon(drawable);
        return this;
    }

    @Override // android.view.SubMenu
    public final SubMenu setHeaderTitle(CharSequence charSequence) {
        this.f1313f.setHeaderTitle(charSequence);
        return this;
    }

    @Override // android.view.SubMenu
    public final SubMenu setIcon(Drawable drawable) {
        this.f1313f.setIcon(drawable);
        return this;
    }
}
