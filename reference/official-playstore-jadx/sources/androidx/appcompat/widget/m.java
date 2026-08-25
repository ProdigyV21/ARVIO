package androidx.appcompat.widget;

import android.content.Context;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.util.SparseBooleanArray;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import androidx.appcompat.view.menu.ActionMenuItemView;
import androidx.appcompat.view.menu.d0;
import androidx.appcompat.view.menu.e0;
import com.arvio.tv.R;
import java.util.ArrayList;

/* JADX INFO: loaded from: classes.dex */
public final class m extends androidx.appcompat.view.menu.d implements androidx.core.view.c {
    public boolean A;
    public final SparseBooleanArray B;
    public k C;
    public g D;
    public i E;
    public h F;
    public final l G;

    /* JADX INFO: renamed from: s, reason: collision with root package name */
    public j f1659s;

    /* JADX INFO: renamed from: t, reason: collision with root package name */
    public Drawable f1660t;

    /* JADX INFO: renamed from: u, reason: collision with root package name */
    public boolean f1661u;

    /* JADX INFO: renamed from: v, reason: collision with root package name */
    public boolean f1662v;

    /* JADX INFO: renamed from: w, reason: collision with root package name */
    public boolean f1663w;
    public int x;

    /* JADX INFO: renamed from: y, reason: collision with root package name */
    public int f1664y;
    public int z;

    public m(Context context) {
        this.f1275i = context;
        this.f1278n = LayoutInflater.from(context);
        this.f1280p = R.layout.abc_action_menu_layout;
        this.f1281q = R.layout.abc_action_menu_item_layout;
        this.B = new SparseBooleanArray();
        this.G = new l(this);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final View a(androidx.appcompat.view.menu.t tVar, View view, ViewGroup viewGroup) {
        View actionView = tVar.getActionView();
        if (actionView == null || tVar.e()) {
            e0.a aVar = view instanceof e0.a ? (e0.a) view : (e0.a) this.f1278n.inflate(this.f1281q, viewGroup, false);
            aVar.c(tVar);
            ActionMenuItemView actionMenuItemView = (ActionMenuItemView) aVar;
            actionMenuItemView.setItemInvoker((ActionMenuView) this.f1282r);
            if (this.F == null) {
                this.F = new h(this);
            }
            actionMenuItemView.setPopupCallback(this.F);
            actionView = (View) aVar;
        }
        actionView.setVisibility(tVar.C ? 8 : 0);
        ViewGroup.LayoutParams layoutParams = actionView.getLayoutParams();
        ((ActionMenuView) viewGroup).getClass();
        if (!(layoutParams instanceof o)) {
            actionView.setLayoutParams(ActionMenuView.j(layoutParams));
        }
        return actionView;
    }

    @Override // androidx.appcompat.view.menu.d0
    public final void b(androidx.appcompat.view.menu.q qVar, boolean z) {
        j();
        g gVar = this.D;
        if (gVar != null && gVar.b()) {
            gVar.f1273i.dismiss();
        }
        d0.a aVar = this.f1279o;
        if (aVar != null) {
            aVar.b(qVar, z);
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // androidx.appcompat.view.menu.d0
    public final void e() {
        int i10;
        ViewGroup viewGroup = (ViewGroup) this.f1282r;
        ArrayList arrayList = null;
        boolean z = false;
        if (viewGroup != null) {
            androidx.appcompat.view.menu.q qVar = this.f1277m;
            if (qVar != null) {
                qVar.i();
                ArrayList arrayListL = this.f1277m.l();
                int size = arrayListL.size();
                i10 = 0;
                for (int i11 = 0; i11 < size; i11++) {
                    androidx.appcompat.view.menu.t tVar = (androidx.appcompat.view.menu.t) arrayListL.get(i11);
                    if ((tVar.x & 32) == 32) {
                        View childAt = viewGroup.getChildAt(i10);
                        androidx.appcompat.view.menu.t itemData = childAt instanceof e0.a ? ((e0.a) childAt).getItemData() : null;
                        View viewA = a(tVar, childAt, viewGroup);
                        if (tVar != itemData) {
                            viewA.setPressed(false);
                            viewA.jumpDrawablesToCurrentState();
                        }
                        if (viewA != childAt) {
                            ViewGroup viewGroup2 = (ViewGroup) viewA.getParent();
                            if (viewGroup2 != null) {
                                viewGroup2.removeView(viewA);
                            }
                            ((ViewGroup) this.f1282r).addView(viewA, i10);
                        }
                        i10++;
                    }
                }
            } else {
                i10 = 0;
            }
            while (i10 < viewGroup.getChildCount()) {
                if (viewGroup.getChildAt(i10) == this.f1659s) {
                    i10++;
                } else {
                    viewGroup.removeViewAt(i10);
                }
            }
        }
        ((View) this.f1282r).requestLayout();
        androidx.appcompat.view.menu.q qVar2 = this.f1277m;
        if (qVar2 != null) {
            qVar2.i();
            ArrayList arrayList2 = qVar2.f1350i;
            int size2 = arrayList2.size();
            for (int i12 = 0; i12 < size2; i12++) {
                androidx.core.view.e eVar = ((androidx.appcompat.view.menu.t) arrayList2.get(i12)).A;
            }
        }
        androidx.appcompat.view.menu.q qVar3 = this.f1277m;
        if (qVar3 != null) {
            qVar3.i();
            arrayList = qVar3.f1351j;
        }
        if (this.f1662v && arrayList != null) {
            int size3 = arrayList.size();
            if (size3 == 1) {
                z = !((androidx.appcompat.view.menu.t) arrayList.get(0)).C;
            } else if (size3 > 0) {
                z = true;
            }
        }
        if (z) {
            if (this.f1659s == null) {
                this.f1659s = new j(this, this.f1275i);
            }
            ViewGroup viewGroup3 = (ViewGroup) this.f1659s.getParent();
            if (viewGroup3 != this.f1282r) {
                if (viewGroup3 != null) {
                    viewGroup3.removeView(this.f1659s);
                }
                ActionMenuView actionMenuView = (ActionMenuView) this.f1282r;
                j jVar = this.f1659s;
                actionMenuView.getClass();
                o oVarI = ActionMenuView.i();
                oVarI.f1673a = true;
                actionMenuView.addView(jVar, oVarI);
            }
        } else {
            j jVar2 = this.f1659s;
            if (jVar2 != null) {
                Object parent = jVar2.getParent();
                Object obj = this.f1282r;
                if (parent == obj) {
                    ((ViewGroup) obj).removeView(this.f1659s);
                }
            }
        }
        ((ActionMenuView) this.f1282r).setOverflowReserved(this.f1662v);
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // androidx.appcompat.view.menu.d0
    public final boolean f(androidx.appcompat.view.menu.i0 i0Var) {
        boolean z;
        if (i0Var.hasVisibleItems()) {
            androidx.appcompat.view.menu.i0 i0Var2 = i0Var;
            while (true) {
                androidx.appcompat.view.menu.q qVar = i0Var2.z;
                if (qVar == this.f1277m) {
                    break;
                }
                i0Var2 = (androidx.appcompat.view.menu.i0) qVar;
            }
            androidx.appcompat.view.menu.t tVar = i0Var2.A;
            ViewGroup viewGroup = (ViewGroup) this.f1282r;
            View view = null;
            view = null;
            if (viewGroup != null) {
                int childCount = viewGroup.getChildCount();
                int i10 = 0;
                while (true) {
                    if (i10 >= childCount) {
                        break;
                    }
                    View childAt = viewGroup.getChildAt(i10);
                    if ((childAt instanceof e0.a) && ((e0.a) childAt).getItemData() == tVar) {
                        view = childAt;
                        break;
                    }
                    i10++;
                }
            }
            if (view != null) {
                i0Var.A.getClass();
                int size = i0Var.f1347f.size();
                int i11 = 0;
                while (true) {
                    if (i11 >= size) {
                        z = false;
                        break;
                    }
                    MenuItem item = i0Var.getItem(i11);
                    if (item.isVisible() && item.getIcon() != null) {
                        z = true;
                        break;
                    }
                    i11++;
                }
                g gVar = new g(this, this.f1276l, i0Var, view);
                this.D = gVar;
                gVar.f1271g = z;
                androidx.appcompat.view.menu.z zVar = gVar.f1273i;
                if (zVar != null) {
                    zVar.n(z);
                }
                g gVar2 = this.D;
                if (!gVar2.b()) {
                    if (gVar2.f1269e == null) {
                        throw new IllegalStateException("MenuPopupHelper cannot be used without an anchor");
                    }
                    gVar2.d(0, 0, false, false);
                }
                d0.a aVar = this.f1279o;
                if (aVar != null) {
                    aVar.c(i0Var);
                }
                return true;
            }
        }
        return false;
    }

    @Override // androidx.appcompat.view.menu.d0
    public final boolean h() {
        int size;
        ArrayList arrayListL;
        int i10;
        boolean z;
        m mVar = this;
        androidx.appcompat.view.menu.q qVar = mVar.f1277m;
        if (qVar != null) {
            arrayListL = qVar.l();
            size = arrayListL.size();
        } else {
            size = 0;
            arrayListL = null;
        }
        int i11 = mVar.z;
        int i12 = mVar.f1664y;
        int iMakeMeasureSpec = View.MeasureSpec.makeMeasureSpec(0, 0);
        ViewGroup viewGroup = (ViewGroup) mVar.f1282r;
        int i13 = 0;
        boolean z5 = false;
        int i14 = 0;
        int i15 = 0;
        while (true) {
            i10 = 2;
            z = true;
            if (i13 >= size) {
                break;
            }
            androidx.appcompat.view.menu.t tVar = (androidx.appcompat.view.menu.t) arrayListL.get(i13);
            int i16 = tVar.f1389y;
            if ((i16 & 2) == 2) {
                i14++;
            } else if ((i16 & 1) == 1) {
                i15++;
            } else {
                z5 = true;
            }
            if (mVar.A && tVar.C) {
                i11 = 0;
            }
            i13++;
        }
        if (mVar.f1662v && (z5 || i15 + i14 > i11)) {
            i11--;
        }
        int i17 = i11 - i14;
        SparseBooleanArray sparseBooleanArray = mVar.B;
        sparseBooleanArray.clear();
        int i18 = 0;
        int i19 = 0;
        while (i18 < size) {
            androidx.appcompat.view.menu.t tVar2 = (androidx.appcompat.view.menu.t) arrayListL.get(i18);
            int i20 = tVar2.f1389y;
            boolean z10 = (i20 & 2) == i10 ? z : false;
            int i21 = tVar2.f1368b;
            if (z10) {
                View viewA = mVar.a(tVar2, null, viewGroup);
                viewA.measure(iMakeMeasureSpec, iMakeMeasureSpec);
                int measuredWidth = viewA.getMeasuredWidth();
                i12 -= measuredWidth;
                if (i19 == 0) {
                    i19 = measuredWidth;
                }
                if (i21 != 0) {
                    sparseBooleanArray.put(i21, z);
                }
                tVar2.f(z);
            } else if ((i20 & 1) == z) {
                boolean z11 = sparseBooleanArray.get(i21);
                boolean z12 = ((i17 > 0 || z11) && i12 > 0) ? z : false;
                if (z12) {
                    View viewA2 = mVar.a(tVar2, null, viewGroup);
                    viewA2.measure(iMakeMeasureSpec, iMakeMeasureSpec);
                    int measuredWidth2 = viewA2.getMeasuredWidth();
                    i12 -= measuredWidth2;
                    if (i19 == 0) {
                        i19 = measuredWidth2;
                    }
                    z12 &= i12 + i19 > 0;
                }
                if (z12 && i21 != 0) {
                    sparseBooleanArray.put(i21, true);
                } else if (z11) {
                    sparseBooleanArray.put(i21, false);
                    for (int i22 = 0; i22 < i18; i22++) {
                        androidx.appcompat.view.menu.t tVar3 = (androidx.appcompat.view.menu.t) arrayListL.get(i22);
                        if (tVar3.f1368b == i21) {
                            if ((tVar3.x & 32) == 32) {
                                i17++;
                            }
                            tVar3.f(false);
                        }
                    }
                }
                if (z12) {
                    i17--;
                }
                tVar2.f(z12);
            } else {
                tVar2.f(false);
                i18++;
                i10 = 2;
                mVar = this;
                z = true;
            }
            i18++;
            i10 = 2;
            mVar = this;
            z = true;
        }
        return z;
    }

    @Override // androidx.appcompat.view.menu.d0
    public final void i(Context context, androidx.appcompat.view.menu.q qVar) {
        this.f1276l = context;
        LayoutInflater.from(context);
        this.f1277m = qVar;
        Resources resources = context.getResources();
        if (!this.f1663w) {
            this.f1662v = true;
        }
        int i10 = 2;
        this.x = context.getResources().getDisplayMetrics().widthPixels / 2;
        Configuration configuration = context.getResources().getConfiguration();
        int i11 = configuration.screenWidthDp;
        int i12 = configuration.screenHeightDp;
        if (configuration.smallestScreenWidthDp > 600 || i11 > 600 || ((i11 > 960 && i12 > 720) || (i11 > 720 && i12 > 960))) {
            i10 = 5;
        } else if (i11 >= 500 || ((i11 > 640 && i12 > 480) || (i11 > 480 && i12 > 640))) {
            i10 = 4;
        } else if (i11 >= 360) {
            i10 = 3;
        }
        this.z = i10;
        int measuredWidth = this.x;
        if (this.f1662v) {
            if (this.f1659s == null) {
                j jVar = new j(this, this.f1275i);
                this.f1659s = jVar;
                if (this.f1661u) {
                    jVar.setImageDrawable(this.f1660t);
                    this.f1660t = null;
                    this.f1661u = false;
                }
                int iMakeMeasureSpec = View.MeasureSpec.makeMeasureSpec(0, 0);
                this.f1659s.measure(iMakeMeasureSpec, iMakeMeasureSpec);
            }
            measuredWidth -= this.f1659s.getMeasuredWidth();
        } else {
            this.f1659s = null;
        }
        this.f1664y = measuredWidth;
        float f10 = resources.getDisplayMetrics().density;
    }

    public final boolean j() {
        Object obj;
        i iVar = this.E;
        if (iVar != null && (obj = this.f1282r) != null) {
            ((View) obj).removeCallbacks(iVar);
            this.E = null;
            return true;
        }
        k kVar = this.C;
        if (kVar == null) {
            return false;
        }
        if (kVar.b()) {
            kVar.f1273i.dismiss();
        }
        return true;
    }

    public final boolean k() {
        k kVar = this.C;
        return kVar != null && kVar.b();
    }

    public final boolean l() {
        androidx.appcompat.view.menu.q qVar;
        if (!this.f1662v || k() || (qVar = this.f1277m) == null || this.f1282r == null || this.E != null) {
            return false;
        }
        qVar.i();
        if (qVar.f1351j.isEmpty()) {
            return false;
        }
        i iVar = new i(this, new k(this, this.f1276l, this.f1277m, this.f1659s));
        this.E = iVar;
        ((View) this.f1282r).post(iVar);
        return true;
    }
}
