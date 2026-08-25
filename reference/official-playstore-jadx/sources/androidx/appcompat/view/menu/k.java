package androidx.appcompat.view.menu;

import android.content.Context;
import android.content.res.Resources;
import android.os.Handler;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.HeaderViewListAdapter;
import android.widget.ListAdapter;
import android.widget.PopupWindow;
import androidx.appcompat.view.menu.d0;
import androidx.appcompat.widget.c3;
import androidx.appcompat.widget.g2;
import androidx.appcompat.widget.y2;
import androidx.core.view.b2;
import com.arvio.tv.R;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.WeakHashMap;

/* JADX INFO: loaded from: classes.dex */
public final class k extends z implements View.OnKeyListener, PopupWindow.OnDismissListener {
    public boolean A;
    public boolean B;
    public int C;
    public int D;
    public boolean F;
    public d0.a G;
    public ViewTreeObserver H;
    public PopupWindow.OnDismissListener I;
    public boolean J;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final Context f1314l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final int f1315m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final int f1316n;

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    public final boolean f1317o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public final Handler f1318p;
    public View x;

    /* JADX INFO: renamed from: y, reason: collision with root package name */
    public View f1326y;
    public int z;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public final ArrayList f1319q = new ArrayList();

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    public final ArrayList f1320r = new ArrayList();

    /* JADX INFO: renamed from: s, reason: collision with root package name */
    public final f f1321s = new f(this, 0);

    /* JADX INFO: renamed from: t, reason: collision with root package name */
    public final g f1322t = new g(this, 0);

    /* JADX INFO: renamed from: u, reason: collision with root package name */
    public final a8.e f1323u = new a8.e(this, 1);

    /* JADX INFO: renamed from: v, reason: collision with root package name */
    public int f1324v = 0;

    /* JADX INFO: renamed from: w, reason: collision with root package name */
    public int f1325w = 0;
    public boolean E = false;

    public k(Context context, View view, int i10, boolean z) {
        this.f1314l = context;
        this.x = view;
        this.f1316n = i10;
        this.f1317o = z;
        WeakHashMap weakHashMap = b2.f2200a;
        this.z = view.getLayoutDirection() != 1 ? 1 : 0;
        Resources resources = context.getResources();
        this.f1315m = Math.max(resources.getDisplayMetrics().widthPixels / 2, resources.getDimensionPixelSize(R.dimen.abc_config_prefDialogWidth));
        this.f1318p = new Handler();
    }

    @Override // androidx.appcompat.view.menu.g0
    public final boolean a() {
        ArrayList arrayList = this.f1320r;
        return arrayList.size() > 0 && ((i) arrayList.get(0)).f1310a.I.isShowing();
    }

    @Override // androidx.appcompat.view.menu.d0
    public final void b(q qVar, boolean z) {
        ArrayList arrayList = this.f1320r;
        int size = arrayList.size();
        int i10 = 0;
        while (true) {
            if (i10 >= size) {
                i10 = -1;
                break;
            } else if (qVar == ((i) arrayList.get(i10)).f1311b) {
                break;
            } else {
                i10++;
            }
        }
        if (i10 < 0) {
            return;
        }
        int i11 = i10 + 1;
        if (i11 < arrayList.size()) {
            ((i) arrayList.get(i11)).f1311b.c(false);
        }
        i iVar = (i) arrayList.remove(i10);
        q qVar2 = iVar.f1311b;
        c3 c3Var = iVar.f1310a;
        androidx.appcompat.widget.i0 i0Var = c3Var.I;
        qVar2.r(this);
        if (this.J) {
            y2.b(i0Var, null);
            i0Var.setAnimationStyle(0);
        }
        c3Var.dismiss();
        int size2 = arrayList.size();
        if (size2 > 0) {
            this.z = ((i) arrayList.get(size2 - 1)).f1312c;
        } else {
            View view = this.x;
            WeakHashMap weakHashMap = b2.f2200a;
            this.z = view.getLayoutDirection() == 1 ? 0 : 1;
        }
        if (size2 != 0) {
            if (z) {
                ((i) arrayList.get(0)).f1311b.c(false);
                return;
            }
            return;
        }
        dismiss();
        d0.a aVar = this.G;
        if (aVar != null) {
            aVar.b(qVar, true);
        }
        ViewTreeObserver viewTreeObserver = this.H;
        if (viewTreeObserver != null) {
            if (viewTreeObserver.isAlive()) {
                this.H.removeGlobalOnLayoutListener(this.f1321s);
            }
            this.H = null;
        }
        this.f1326y.removeOnAttachStateChangeListener(this.f1322t);
        this.I.onDismiss();
    }

    @Override // androidx.appcompat.view.menu.d0
    public final void c(d0.a aVar) {
        this.G = aVar;
    }

    @Override // androidx.appcompat.view.menu.g0
    public final void dismiss() {
        ArrayList arrayList = this.f1320r;
        int size = arrayList.size();
        if (size > 0) {
            i[] iVarArr = (i[]) arrayList.toArray(new i[size]);
            for (int i10 = size - 1; i10 >= 0; i10--) {
                i iVar = iVarArr[i10];
                if (iVar.f1310a.I.isShowing()) {
                    iVar.f1310a.dismiss();
                }
            }
        }
    }

    @Override // androidx.appcompat.view.menu.d0
    public final void e() {
        Iterator it = this.f1320r.iterator();
        while (it.hasNext()) {
            ListAdapter adapter = ((i) it.next()).f1310a.f1813m.getAdapter();
            if (adapter instanceof HeaderViewListAdapter) {
                adapter = ((HeaderViewListAdapter) adapter).getWrappedAdapter();
            }
            ((n) adapter).notifyDataSetChanged();
        }
    }

    @Override // androidx.appcompat.view.menu.d0
    public final boolean f(i0 i0Var) {
        for (i iVar : this.f1320r) {
            if (i0Var == iVar.f1311b) {
                iVar.f1310a.f1813m.requestFocus();
                return true;
            }
        }
        if (!i0Var.hasVisibleItems()) {
            return false;
        }
        j(i0Var);
        d0.a aVar = this.G;
        if (aVar != null) {
            aVar.c(i0Var);
        }
        return true;
    }

    @Override // androidx.appcompat.view.menu.d0
    public final boolean h() {
        return false;
    }

    @Override // androidx.appcompat.view.menu.z
    public final void j(q qVar) {
        qVar.b(this, this.f1314l);
        if (a()) {
            t(qVar);
        } else {
            this.f1319q.add(qVar);
        }
    }

    @Override // androidx.appcompat.view.menu.g0
    public final g2 l() {
        ArrayList arrayList = this.f1320r;
        if (arrayList.isEmpty()) {
            return null;
        }
        return ((i) a0.c.h(1, arrayList)).f1310a.f1813m;
    }

    @Override // androidx.appcompat.view.menu.z
    public final void m(View view) {
        if (this.x != view) {
            this.x = view;
            int i10 = this.f1324v;
            WeakHashMap weakHashMap = b2.f2200a;
            this.f1325w = Gravity.getAbsoluteGravity(i10, view.getLayoutDirection());
        }
    }

    @Override // androidx.appcompat.view.menu.z
    public final void n(boolean z) {
        this.E = z;
    }

    @Override // androidx.appcompat.view.menu.z
    public final void o(int i10) {
        if (this.f1324v != i10) {
            this.f1324v = i10;
            View view = this.x;
            WeakHashMap weakHashMap = b2.f2200a;
            this.f1325w = Gravity.getAbsoluteGravity(i10, view.getLayoutDirection());
        }
    }

    @Override // android.widget.PopupWindow.OnDismissListener
    public final void onDismiss() {
        i iVar;
        ArrayList arrayList = this.f1320r;
        int size = arrayList.size();
        int i10 = 0;
        while (true) {
            if (i10 >= size) {
                iVar = null;
                break;
            }
            iVar = (i) arrayList.get(i10);
            if (!iVar.f1310a.I.isShowing()) {
                break;
            } else {
                i10++;
            }
        }
        if (iVar != null) {
            iVar.f1311b.c(false);
        }
    }

    @Override // android.view.View.OnKeyListener
    public final boolean onKey(View view, int i10, KeyEvent keyEvent) {
        if (keyEvent.getAction() != 1 || i10 != 82) {
            return false;
        }
        dismiss();
        return true;
    }

    @Override // androidx.appcompat.view.menu.z
    public final void p(int i10) {
        this.A = true;
        this.C = i10;
    }

    @Override // androidx.appcompat.view.menu.z
    public final void q(PopupWindow.OnDismissListener onDismissListener) {
        this.I = onDismissListener;
    }

    @Override // androidx.appcompat.view.menu.z
    public final void r(boolean z) {
        this.F = z;
    }

    @Override // androidx.appcompat.view.menu.z
    public final void s(int i10) {
        this.B = true;
        this.D = i10;
    }

    @Override // androidx.appcompat.view.menu.g0
    public final void show() {
        if (a()) {
            return;
        }
        ArrayList arrayList = this.f1319q;
        Iterator it = arrayList.iterator();
        while (it.hasNext()) {
            t((q) it.next());
        }
        arrayList.clear();
        View view = this.x;
        this.f1326y = view;
        if (view != null) {
            boolean z = this.H == null;
            ViewTreeObserver viewTreeObserver = view.getViewTreeObserver();
            this.H = viewTreeObserver;
            if (z) {
                viewTreeObserver.addOnGlobalLayoutListener(this.f1321s);
            }
            this.f1326y.addOnAttachStateChangeListener(this.f1322t);
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:69:0x0162  */
    /* JADX WARN: Removed duplicated region for block: B:75:0x0170  */
    /* JADX WARN: Removed duplicated region for block: B:76:0x0172  */
    /* JADX WARN: Removed duplicated region for block: B:79:0x017c  */
    /* JADX WARN: Removed duplicated region for block: B:80:0x0181  */
    /* JADX WARN: Removed duplicated region for block: B:86:0x01bd  */
    /* JADX WARN: Removed duplicated region for block: B:90:0x01c7  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final void t(androidx.appcompat.view.menu.q r20) {
        /*
            Method dump skipped, instruction units count: 566
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.appcompat.view.menu.k.t(androidx.appcompat.view.menu.q):void");
    }
}
