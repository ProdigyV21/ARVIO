package androidx.appcompat.widget;

import android.content.Context;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import java.util.ArrayList;

/* JADX INFO: loaded from: classes.dex */
public final class l4 implements androidx.appcompat.view.menu.d0 {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public androidx.appcompat.view.menu.q f1656i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public androidx.appcompat.view.menu.t f1657l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ Toolbar f1658m;

    public l4(Toolbar toolbar) {
        this.f1658m = toolbar;
    }

    @Override // androidx.appcompat.view.menu.d0
    public final void b(androidx.appcompat.view.menu.q qVar, boolean z) {
    }

    @Override // androidx.appcompat.view.menu.d0
    public final boolean d(androidx.appcompat.view.menu.t tVar) {
        Toolbar toolbar = this.f1658m;
        KeyEvent.Callback callback = toolbar.f1488s;
        if (callback instanceof n.c) {
            ((n.c) callback).onActionViewCollapsed();
        }
        toolbar.removeView(toolbar.f1488s);
        toolbar.removeView(toolbar.f1487r);
        toolbar.f1488s = null;
        ArrayList arrayList = toolbar.O;
        for (int size = arrayList.size() - 1; size >= 0; size--) {
            toolbar.addView((View) arrayList.get(size));
        }
        arrayList.clear();
        this.f1657l = null;
        toolbar.requestLayout();
        tVar.C = false;
        tVar.f1379n.p(false);
        toolbar.u();
        return true;
    }

    @Override // androidx.appcompat.view.menu.d0
    public final void e() {
        if (this.f1657l != null) {
            androidx.appcompat.view.menu.q qVar = this.f1656i;
            if (qVar != null) {
                int size = qVar.f1347f.size();
                for (int i10 = 0; i10 < size; i10++) {
                    if (this.f1656i.getItem(i10) == this.f1657l) {
                        return;
                    }
                }
            }
            d(this.f1657l);
        }
    }

    @Override // androidx.appcompat.view.menu.d0
    public final boolean f(androidx.appcompat.view.menu.i0 i0Var) {
        return false;
    }

    @Override // androidx.appcompat.view.menu.d0
    public final boolean g(androidx.appcompat.view.menu.t tVar) {
        Toolbar toolbar = this.f1658m;
        toolbar.c();
        ViewParent parent = toolbar.f1487r.getParent();
        if (parent != toolbar) {
            if (parent instanceof ViewGroup) {
                ((ViewGroup) parent).removeView(toolbar.f1487r);
            }
            toolbar.addView(toolbar.f1487r);
        }
        View actionView = tVar.getActionView();
        toolbar.f1488s = actionView;
        this.f1657l = tVar;
        ViewParent parent2 = actionView.getParent();
        if (parent2 != toolbar) {
            if (parent2 instanceof ViewGroup) {
                ((ViewGroup) parent2).removeView(toolbar.f1488s);
            }
            m4 m4VarH = Toolbar.h();
            m4VarH.f1666a = (toolbar.x & 112) | 8388611;
            m4VarH.f1667b = 2;
            toolbar.f1488s.setLayoutParams(m4VarH);
            toolbar.addView(toolbar.f1488s);
        }
        for (int childCount = toolbar.getChildCount() - 1; childCount >= 0; childCount--) {
            View childAt = toolbar.getChildAt(childCount);
            if (((m4) childAt.getLayoutParams()).f1667b != 2 && childAt != toolbar.f1480i) {
                toolbar.removeViewAt(childCount);
                toolbar.O.add(childAt);
            }
        }
        toolbar.requestLayout();
        tVar.C = true;
        tVar.f1379n.p(false);
        KeyEvent.Callback callback = toolbar.f1488s;
        if (callback instanceof n.c) {
            ((n.c) callback).onActionViewExpanded();
        }
        toolbar.u();
        return true;
    }

    @Override // androidx.appcompat.view.menu.d0
    public final boolean h() {
        return false;
    }

    @Override // androidx.appcompat.view.menu.d0
    public final void i(Context context, androidx.appcompat.view.menu.q qVar) {
        androidx.appcompat.view.menu.t tVar;
        androidx.appcompat.view.menu.q qVar2 = this.f1656i;
        if (qVar2 != null && (tVar = this.f1657l) != null) {
            qVar2.d(tVar);
        }
        this.f1656i = qVar;
    }
}
