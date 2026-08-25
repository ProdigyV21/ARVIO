package androidx.appcompat.view.menu;

import android.view.MenuItem;

/* JADX INFO: loaded from: classes.dex */
public final class x implements MenuItem.OnMenuItemClickListener {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final MenuItem.OnMenuItemClickListener f1396a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final /* synthetic */ y f1397b;

    public x(y yVar, MenuItem.OnMenuItemClickListener onMenuItemClickListener) {
        this.f1397b = yVar;
        this.f1396a = onMenuItemClickListener;
    }

    @Override // android.view.MenuItem.OnMenuItemClickListener
    public final boolean onMenuItemClick(MenuItem menuItem) {
        return this.f1396a.onMenuItemClick(this.f1397b.f(menuItem));
    }
}
