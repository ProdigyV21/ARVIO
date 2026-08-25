package androidx.appcompat.widget;

import android.view.MenuItem;
import androidx.appcompat.widget.ActionMenuView;
import androidx.appcompat.widget.Toolbar;
import java.util.Iterator;

/* JADX INFO: loaded from: classes.dex */
public final class j4 implements ActionMenuView.b {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final /* synthetic */ Toolbar f1619a;

    public j4(Toolbar toolbar) {
        this.f1619a = toolbar;
    }

    @Override // androidx.appcompat.widget.ActionMenuView.b
    public final boolean onMenuItemClick(MenuItem menuItem) {
        Toolbar toolbar = this.f1619a;
        Iterator it = toolbar.Q.f2230b.iterator();
        while (it.hasNext()) {
            if (((androidx.core.view.i0) it.next()).c(menuItem)) {
                return true;
            }
        }
        Toolbar.a aVar = toolbar.S;
        if (aVar != null) {
            return aVar.onMenuItemClick(menuItem);
        }
        return false;
    }
}
