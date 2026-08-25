package androidx.appcompat.app;

import android.R;
import android.animation.ValueAnimator;
import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.res.TypedArray;
import android.util.TypedValue;
import android.view.ContextThemeWrapper;
import android.view.KeyCharacterMap;
import android.view.KeyEvent;
import android.view.View;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.DecelerateInterpolator;
import androidx.appcompat.widget.ActionBarContainer;
import androidx.appcompat.widget.ActionBarContextView;
import androidx.appcompat.widget.ActionBarOverlayLayout;
import androidx.appcompat.widget.Toolbar;
import androidx.appcompat.widget.y1;
import androidx.core.view.b2;
import androidx.core.view.n2;
import java.util.ArrayList;
import java.util.WeakHashMap;

/* JADX INFO: loaded from: classes.dex */
public final class l1 extends f implements ActionBarOverlayLayout.a {

    /* JADX INFO: renamed from: y, reason: collision with root package name */
    public static final AccelerateInterpolator f1092y = new AccelerateInterpolator();
    public static final DecelerateInterpolator z = new DecelerateInterpolator();

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public Context f1093a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public Context f1094b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public ActionBarOverlayLayout f1095c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public ActionBarContainer f1096d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public y1 f1097e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public ActionBarContextView f1098f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public final View f1099g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public boolean f1100h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public k1 f1101i;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public k1 f1102j;
    public io.sentry.internal.debugmeta.c k;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public boolean f1103l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final ArrayList f1104m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public int f1105n;

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    public boolean f1106o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public boolean f1107p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public boolean f1108q;

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    public boolean f1109r;

    /* JADX INFO: renamed from: s, reason: collision with root package name */
    public n.k f1110s;

    /* JADX INFO: renamed from: t, reason: collision with root package name */
    public boolean f1111t;

    /* JADX INFO: renamed from: u, reason: collision with root package name */
    public boolean f1112u;

    /* JADX INFO: renamed from: v, reason: collision with root package name */
    public final j1 f1113v;

    /* JADX INFO: renamed from: w, reason: collision with root package name */
    public final j1 f1114w;
    public final b0 x;

    public l1(Activity activity, boolean z5) {
        new ArrayList();
        this.f1104m = new ArrayList();
        this.f1105n = 0;
        this.f1106o = true;
        this.f1109r = true;
        this.f1113v = new j1(this, 0);
        this.f1114w = new j1(this, 1);
        this.x = new b0(this);
        View decorView = activity.getWindow().getDecorView();
        y(decorView);
        if (z5) {
            return;
        }
        this.f1099g = decorView.findViewById(R.id.content);
    }

    public final void A(boolean z5) {
        boolean z10 = this.f1107p;
        boolean z11 = this.f1108q;
        final b0 b0Var = this.x;
        View view = this.f1099g;
        if (!z11 && z10) {
            if (this.f1109r) {
                this.f1109r = false;
                n.k kVar = this.f1110s;
                if (kVar != null) {
                    kVar.a();
                }
                int i10 = this.f1105n;
                j1 j1Var = this.f1113v;
                if (i10 != 0 || (!this.f1111t && !z5)) {
                    j1Var.c();
                    return;
                }
                this.f1096d.setAlpha(1.0f);
                this.f1096d.setTransitioning(true);
                n.k kVar2 = new n.k();
                float f10 = -this.f1096d.getHeight();
                if (z5) {
                    this.f1096d.getLocationInWindow(new int[]{0, 0});
                    f10 -= r12[1];
                }
                n2 n2VarA = b2.a(this.f1096d);
                n2VarA.e(f10);
                final View view2 = (View) n2VarA.f2294a.get();
                if (view2 != null) {
                    view2.animate().setUpdateListener(b0Var != null ? new ValueAnimator.AnimatorUpdateListener() { // from class: androidx.core.view.l2
                        @Override // android.animation.ValueAnimator.AnimatorUpdateListener
                        public final void onAnimationUpdate(ValueAnimator valueAnimator) {
                            b0Var.a();
                        }
                    } : null);
                }
                boolean z12 = kVar2.f20424e;
                ArrayList arrayList = kVar2.f20420a;
                if (!z12) {
                    arrayList.add(n2VarA);
                }
                if (this.f1106o && view != null) {
                    n2 n2VarA2 = b2.a(view);
                    n2VarA2.e(f10);
                    if (!kVar2.f20424e) {
                        arrayList.add(n2VarA2);
                    }
                }
                boolean z13 = kVar2.f20424e;
                if (!z13) {
                    kVar2.f20422c = f1092y;
                }
                if (!z13) {
                    kVar2.f20421b = 250L;
                }
                if (!z13) {
                    kVar2.f20423d = j1Var;
                }
                this.f1110s = kVar2;
                kVar2.b();
                return;
            }
            return;
        }
        if (this.f1109r) {
            return;
        }
        this.f1109r = true;
        n.k kVar3 = this.f1110s;
        if (kVar3 != null) {
            kVar3.a();
        }
        this.f1096d.setVisibility(0);
        int i11 = this.f1105n;
        j1 j1Var2 = this.f1114w;
        if (i11 == 0 && (this.f1111t || z5)) {
            this.f1096d.setTranslationY(0.0f);
            float f11 = -this.f1096d.getHeight();
            if (z5) {
                this.f1096d.getLocationInWindow(new int[]{0, 0});
                f11 -= r12[1];
            }
            this.f1096d.setTranslationY(f11);
            n.k kVar4 = new n.k();
            n2 n2VarA3 = b2.a(this.f1096d);
            n2VarA3.e(0.0f);
            final View view3 = (View) n2VarA3.f2294a.get();
            if (view3 != null) {
                view3.animate().setUpdateListener(b0Var != null ? new ValueAnimator.AnimatorUpdateListener() { // from class: androidx.core.view.l2
                    @Override // android.animation.ValueAnimator.AnimatorUpdateListener
                    public final void onAnimationUpdate(ValueAnimator valueAnimator) {
                        b0Var.a();
                    }
                } : null);
            }
            boolean z14 = kVar4.f20424e;
            ArrayList arrayList2 = kVar4.f20420a;
            if (!z14) {
                arrayList2.add(n2VarA3);
            }
            if (this.f1106o && view != null) {
                view.setTranslationY(f11);
                n2 n2VarA4 = b2.a(view);
                n2VarA4.e(0.0f);
                if (!kVar4.f20424e) {
                    arrayList2.add(n2VarA4);
                }
            }
            boolean z15 = kVar4.f20424e;
            if (!z15) {
                kVar4.f20422c = z;
            }
            if (!z15) {
                kVar4.f20421b = 250L;
            }
            if (!z15) {
                kVar4.f20423d = j1Var2;
            }
            this.f1110s = kVar4;
            kVar4.b();
        } else {
            this.f1096d.setAlpha(1.0f);
            this.f1096d.setTranslationY(0.0f);
            if (this.f1106o && view != null) {
                view.setTranslationY(0.0f);
            }
            j1Var2.c();
        }
        ActionBarOverlayLayout actionBarOverlayLayout = this.f1095c;
        if (actionBarOverlayLayout != null) {
            WeakHashMap weakHashMap = b2.f2200a;
            androidx.core.view.i1.c(actionBarOverlayLayout);
        }
    }

    @Override // androidx.appcompat.widget.ActionBarOverlayLayout.a
    public final void a() {
        if (this.f1107p) {
            this.f1107p = false;
            A(true);
        }
    }

    @Override // androidx.appcompat.widget.ActionBarOverlayLayout.a
    public final void b(boolean z5) {
        this.f1106o = z5;
    }

    @Override // androidx.appcompat.widget.ActionBarOverlayLayout.a
    public final void c() {
        if (this.f1107p) {
            return;
        }
        this.f1107p = true;
        A(true);
    }

    @Override // androidx.appcompat.widget.ActionBarOverlayLayout.a
    public final void d() {
        n.k kVar = this.f1110s;
        if (kVar != null) {
            kVar.a();
            this.f1110s = null;
        }
    }

    @Override // androidx.appcompat.app.f
    public final boolean f() {
        y1 y1Var = this.f1097e;
        if (y1Var == null || !y1Var.h()) {
            return false;
        }
        this.f1097e.collapseActionView();
        return true;
    }

    @Override // androidx.appcompat.app.f
    public final void g(boolean z5) {
        if (z5 == this.f1103l) {
            return;
        }
        this.f1103l = z5;
        ArrayList arrayList = this.f1104m;
        int size = arrayList.size();
        for (int i10 = 0; i10 < size; i10++) {
            ((c) arrayList.get(i10)).a();
        }
    }

    @Override // androidx.appcompat.app.f
    public final int h() {
        return this.f1097e.s();
    }

    @Override // androidx.appcompat.app.f
    public final Context i() {
        if (this.f1094b == null) {
            TypedValue typedValue = new TypedValue();
            this.f1093a.getTheme().resolveAttribute(com.arvio.tv.R.attr.actionBarWidgetTheme, typedValue, true);
            int i10 = typedValue.resourceId;
            if (i10 != 0) {
                this.f1094b = new ContextThemeWrapper(this.f1093a, i10);
            } else {
                this.f1094b = this.f1093a;
            }
        }
        return this.f1094b;
    }

    @Override // androidx.appcompat.app.f
    public final void k() {
        z(this.f1093a.getResources().getBoolean(com.arvio.tv.R.bool.abc_action_bar_embed_tabs));
    }

    @Override // androidx.appcompat.app.f
    public final boolean m(int i10, KeyEvent keyEvent) {
        androidx.appcompat.view.menu.q qVar;
        k1 k1Var = this.f1101i;
        if (k1Var == null || (qVar = k1Var.f1068n) == null) {
            return false;
        }
        qVar.setQwertyMode(KeyCharacterMap.load(keyEvent.getDeviceId()).getKeyboardType() != 1);
        return qVar.performShortcut(i10, keyEvent, 0);
    }

    @Override // androidx.appcompat.widget.ActionBarOverlayLayout.a
    public final void onWindowVisibilityChanged(int i10) {
        this.f1105n = i10;
    }

    @Override // androidx.appcompat.app.f
    public final void p(boolean z5) {
        if (this.f1100h) {
            return;
        }
        q(z5);
    }

    @Override // androidx.appcompat.app.f
    public final void q(boolean z5) {
        int i10 = z5 ? 4 : 0;
        int iS = this.f1097e.s();
        this.f1100h = true;
        this.f1097e.i((i10 & 4) | (iS & (-5)));
    }

    @Override // androidx.appcompat.app.f
    public final void r(int i10) {
        this.f1097e.q(i10);
    }

    @Override // androidx.appcompat.app.f
    public final void s(boolean z5) {
        n.k kVar;
        this.f1111t = z5;
        if (z5 || (kVar = this.f1110s) == null) {
            return;
        }
        kVar.a();
    }

    @Override // androidx.appcompat.app.f
    public final void t(String str) {
        this.f1097e.j(str);
    }

    @Override // androidx.appcompat.app.f
    public final void u(String str) {
        this.f1097e.setTitle(str);
    }

    @Override // androidx.appcompat.app.f
    public final void v(CharSequence charSequence) {
        this.f1097e.setWindowTitle(charSequence);
    }

    @Override // androidx.appcompat.app.f
    public final n.b w(io.sentry.internal.debugmeta.c cVar) {
        k1 k1Var = this.f1101i;
        if (k1Var != null) {
            k1Var.a();
        }
        this.f1095c.setHideOnContentScrollEnabled(false);
        this.f1098f.e();
        k1 k1Var2 = new k1(this, this.f1098f.getContext(), cVar);
        androidx.appcompat.view.menu.q qVar = k1Var2.f1068n;
        qVar.w();
        try {
            if (!((n.a) k1Var2.f1069o.f17367l).b(k1Var2, qVar)) {
                return null;
            }
            this.f1101i = k1Var2;
            k1Var2.i();
            this.f1098f.c(k1Var2);
            x(true);
            return k1Var2;
        } finally {
            qVar.v();
        }
    }

    public final void x(boolean z5) {
        n2 n2VarK;
        n2 n2VarI;
        if (z5) {
            if (!this.f1108q) {
                this.f1108q = true;
                ActionBarOverlayLayout actionBarOverlayLayout = this.f1095c;
                if (actionBarOverlayLayout != null) {
                    actionBarOverlayLayout.setShowingForActionMode(true);
                }
                A(false);
            }
        } else if (this.f1108q) {
            this.f1108q = false;
            ActionBarOverlayLayout actionBarOverlayLayout2 = this.f1095c;
            if (actionBarOverlayLayout2 != null) {
                actionBarOverlayLayout2.setShowingForActionMode(false);
            }
            A(false);
        }
        ActionBarContainer actionBarContainer = this.f1096d;
        WeakHashMap weakHashMap = b2.f2200a;
        if (!actionBarContainer.isLaidOut()) {
            if (z5) {
                this.f1097e.r(4);
                this.f1098f.setVisibility(0);
                return;
            } else {
                this.f1097e.r(0);
                this.f1098f.setVisibility(8);
                return;
            }
        }
        if (z5) {
            n2VarI = this.f1097e.k(4, 100L);
            n2VarK = this.f1098f.i(0, 200L);
        } else {
            n2VarK = this.f1097e.k(0, 200L);
            n2VarI = this.f1098f.i(8, 100L);
        }
        n.k kVar = new n.k();
        ArrayList arrayList = kVar.f20420a;
        arrayList.add(n2VarI);
        View view = (View) n2VarI.f2294a.get();
        long duration = view != null ? view.animate().getDuration() : 0L;
        View view2 = (View) n2VarK.f2294a.get();
        if (view2 != null) {
            view2.animate().setStartDelay(duration);
        }
        arrayList.add(n2VarK);
        kVar.b();
    }

    public final void y(View view) {
        y1 wrapper;
        ActionBarOverlayLayout actionBarOverlayLayout = (ActionBarOverlayLayout) view.findViewById(com.arvio.tv.R.id.decor_content_parent);
        this.f1095c = actionBarOverlayLayout;
        if (actionBarOverlayLayout != null) {
            actionBarOverlayLayout.setActionBarVisibilityCallback(this);
        }
        KeyEvent.Callback callbackFindViewById = view.findViewById(com.arvio.tv.R.id.action_bar);
        if (callbackFindViewById instanceof y1) {
            wrapper = (y1) callbackFindViewById;
        } else {
            if (!(callbackFindViewById instanceof Toolbar)) {
                throw new IllegalStateException("Can't make a decor toolbar out of ".concat(callbackFindViewById != null ? callbackFindViewById.getClass().getSimpleName() : "null"));
            }
            wrapper = ((Toolbar) callbackFindViewById).getWrapper();
        }
        this.f1097e = wrapper;
        this.f1098f = (ActionBarContextView) view.findViewById(com.arvio.tv.R.id.action_context_bar);
        ActionBarContainer actionBarContainer = (ActionBarContainer) view.findViewById(com.arvio.tv.R.id.action_bar_container);
        this.f1096d = actionBarContainer;
        y1 y1Var = this.f1097e;
        if (y1Var == null || this.f1098f == null || actionBarContainer == null) {
            throw new IllegalStateException(l1.class.getSimpleName().concat(" can only be used with a compatible window decor layout"));
        }
        this.f1093a = y1Var.getContext();
        if ((this.f1097e.s() & 4) != 0) {
            this.f1100h = true;
        }
        Context context = this.f1093a;
        int i10 = context.getApplicationInfo().targetSdkVersion;
        this.f1097e.getClass();
        z(context.getResources().getBoolean(com.arvio.tv.R.bool.abc_action_bar_embed_tabs));
        TypedArray typedArrayObtainStyledAttributes = this.f1093a.obtainStyledAttributes(null, j.a.f19017a, com.arvio.tv.R.attr.actionBarStyle, 0);
        if (typedArrayObtainStyledAttributes.getBoolean(14, false)) {
            ActionBarOverlayLayout actionBarOverlayLayout2 = this.f1095c;
            if (!actionBarOverlayLayout2.f1431r) {
                throw new IllegalStateException("Action bar must be in overlay mode (Window.FEATURE_OVERLAY_ACTION_BAR) to enable hide on content scroll");
            }
            this.f1112u = true;
            actionBarOverlayLayout2.setHideOnContentScrollEnabled(true);
        }
        int dimensionPixelSize = typedArrayObtainStyledAttributes.getDimensionPixelSize(12, 0);
        if (dimensionPixelSize != 0) {
            ActionBarContainer actionBarContainer2 = this.f1096d;
            WeakHashMap weakHashMap = b2.f2200a;
            androidx.core.view.k1.s(actionBarContainer2, dimensionPixelSize);
        }
        typedArrayObtainStyledAttributes.recycle();
    }

    public final void z(boolean z5) {
        if (z5) {
            this.f1096d.setTabContainer(null);
            this.f1097e.o();
        } else {
            this.f1097e.o();
            this.f1096d.setTabContainer(null);
        }
        this.f1097e.getClass();
        this.f1097e.m(false);
        this.f1095c.setHasNonEmbeddedTabs(false);
    }

    public l1(Dialog dialog) {
        new ArrayList();
        this.f1104m = new ArrayList();
        this.f1105n = 0;
        this.f1106o = true;
        this.f1109r = true;
        this.f1113v = new j1(this, 0);
        this.f1114w = new j1(this, 1);
        this.x = new b0(this);
        y(dialog.getWindow().getDecorView());
    }
}
