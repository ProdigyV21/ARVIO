package androidx.appcompat.app;

import android.view.MenuItem;
import androidx.appcompat.widget.Toolbar;

/* JADX INFO: loaded from: classes.dex */
public final class c1 implements Toolbar.a {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final /* synthetic */ f1 f1035a;

    public c1(f1 f1Var) {
        this.f1035a = f1Var;
    }

    @Override // androidx.appcompat.widget.Toolbar.a
    public final boolean onMenuItemClick(MenuItem menuItem) {
        return this.f1035a.f1043b.onMenuItemSelected(0, menuItem);
    }
}
