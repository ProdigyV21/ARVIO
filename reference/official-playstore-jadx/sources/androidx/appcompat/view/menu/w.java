package androidx.appcompat.view.menu;

import android.view.MenuItem;

/* JADX INFO: loaded from: classes.dex */
public final class w implements MenuItem.OnActionExpandListener {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final MenuItem.OnActionExpandListener f1394a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final /* synthetic */ y f1395b;

    public w(y yVar, MenuItem.OnActionExpandListener onActionExpandListener) {
        this.f1395b = yVar;
        this.f1394a = onActionExpandListener;
    }

    @Override // android.view.MenuItem.OnActionExpandListener
    public final boolean onMenuItemActionCollapse(MenuItem menuItem) {
        return this.f1394a.onMenuItemActionCollapse(this.f1395b.f(menuItem));
    }

    @Override // android.view.MenuItem.OnActionExpandListener
    public final boolean onMenuItemActionExpand(MenuItem menuItem) {
        return this.f1394a.onMenuItemActionExpand(this.f1395b.f(menuItem));
    }
}
