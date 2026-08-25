package androidx.appcompat.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Handler;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListAdapter;
import android.widget.PopupWindow;
import java.lang.reflect.Method;
import java.util.WeakHashMap;

/* JADX INFO: loaded from: classes.dex */
public abstract class w2 implements androidx.appcompat.view.menu.g0 {
    public static final Method J;
    public static final Method K;
    public static final Method L;
    public final Handler E;
    public Rect G;
    public boolean H;
    public final i0 I;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final Context f1811i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public ListAdapter f1812l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public g2 f1813m;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public int f1816p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public int f1817q;

    /* JADX INFO: renamed from: s, reason: collision with root package name */
    public boolean f1819s;

    /* JADX INFO: renamed from: t, reason: collision with root package name */
    public boolean f1820t;

    /* JADX INFO: renamed from: u, reason: collision with root package name */
    public boolean f1821u;
    public t2 x;

    /* JADX INFO: renamed from: y, reason: collision with root package name */
    public View f1824y;
    public AdapterView.OnItemClickListener z;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final int f1814n = -2;

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    public int f1815o = -2;

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    public final int f1818r = 1002;

    /* JADX INFO: renamed from: v, reason: collision with root package name */
    public int f1822v = 0;

    /* JADX INFO: renamed from: w, reason: collision with root package name */
    public final int f1823w = Integer.MAX_VALUE;
    public final s2 A = new s2(this, 1);
    public final v2 B = new v2(this);
    public final u2 C = new u2(this);
    public final s2 D = new s2(this, 0);
    public final Rect F = new Rect();

    static {
        int i10 = Build.VERSION.SDK_INT;
        Class cls = Boolean.TYPE;
        if (i10 <= 28) {
            try {
                J = PopupWindow.class.getDeclaredMethod("setClipToScreenEnabled", cls);
            } catch (NoSuchMethodException unused) {
                Log.i("ListPopupWindow", "Could not find method setClipToScreenEnabled() on PopupWindow. Oh well.");
            }
            try {
                L = PopupWindow.class.getDeclaredMethod("setEpicenterBounds", Rect.class);
            } catch (NoSuchMethodException unused2) {
                Log.i("ListPopupWindow", "Could not find method setEpicenterBounds(Rect) on PopupWindow. Oh well.");
            }
        }
        if (Build.VERSION.SDK_INT <= 23) {
            try {
                K = PopupWindow.class.getDeclaredMethod("getMaxAvailableHeight", View.class, Integer.TYPE, cls);
            } catch (NoSuchMethodException unused3) {
                Log.i("ListPopupWindow", "Could not find method getMaxAvailableHeight(View, int, boolean) on PopupWindow. Oh well.");
            }
        }
    }

    public w2(Context context, AttributeSet attributeSet, int i10) {
        int resourceId;
        this.f1811i = context;
        this.E = new Handler(context.getMainLooper());
        TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(attributeSet, j.a.f19030o, i10, 0);
        this.f1816p = typedArrayObtainStyledAttributes.getDimensionPixelOffset(0, 0);
        int dimensionPixelOffset = typedArrayObtainStyledAttributes.getDimensionPixelOffset(1, 0);
        this.f1817q = dimensionPixelOffset;
        if (dimensionPixelOffset != 0) {
            this.f1819s = true;
        }
        typedArrayObtainStyledAttributes.recycle();
        i0 i0Var = new i0(context, attributeSet, i10, 0);
        TypedArray typedArrayObtainStyledAttributes2 = context.obtainStyledAttributes(attributeSet, j.a.f19034s, i10, 0);
        if (typedArrayObtainStyledAttributes2.hasValue(2)) {
            u0.l.c(i0Var, typedArrayObtainStyledAttributes2.getBoolean(2, false));
        }
        i0Var.setBackgroundDrawable((!typedArrayObtainStyledAttributes2.hasValue(0) || (resourceId = typedArrayObtainStyledAttributes2.getResourceId(0, 0)) == 0) ? typedArrayObtainStyledAttributes2.getDrawable(0) : m2.f0.o(context, resourceId));
        typedArrayObtainStyledAttributes2.recycle();
        this.I = i0Var;
        i0Var.setInputMethodMode(1);
    }

    @Override // androidx.appcompat.view.menu.g0
    public final boolean a() {
        return this.I.isShowing();
    }

    public final int b() {
        return this.f1816p;
    }

    public final void d(int i10) {
        this.f1816p = i10;
    }

    @Override // androidx.appcompat.view.menu.g0
    public final void dismiss() {
        i0 i0Var = this.I;
        i0Var.dismiss();
        i0Var.setContentView(null);
        this.f1813m = null;
        this.E.removeCallbacks(this.A);
    }

    public final Drawable f() {
        return this.I.getBackground();
    }

    public final int getVerticalOffset() {
        if (this.f1819s) {
            return this.f1817q;
        }
        return 0;
    }

    public final void h(int i10) {
        this.f1817q = i10;
        this.f1819s = true;
    }

    public void k(ListAdapter listAdapter) {
        t2 t2Var = this.x;
        if (t2Var == null) {
            this.x = new t2(this, 0);
        } else {
            ListAdapter listAdapter2 = this.f1812l;
            if (listAdapter2 != null) {
                listAdapter2.unregisterDataSetObserver(t2Var);
            }
        }
        this.f1812l = listAdapter;
        if (listAdapter != null) {
            listAdapter.registerDataSetObserver(this.x);
        }
        g2 g2Var = this.f1813m;
        if (g2Var != null) {
            g2Var.setAdapter(this.f1812l);
        }
    }

    @Override // androidx.appcompat.view.menu.g0
    public final g2 l() {
        return this.f1813m;
    }

    public final void m(Drawable drawable) {
        this.I.setBackgroundDrawable(drawable);
    }

    public g2 n(Context context, boolean z) {
        return new g2(context, z);
    }

    public final void o(int i10) {
        Drawable background = this.I.getBackground();
        if (background == null) {
            this.f1815o = i10;
            return;
        }
        Rect rect = this.F;
        background.getPadding(rect);
        this.f1815o = rect.left + rect.right + i10;
    }

    @Override // androidx.appcompat.view.menu.g0
    public final void show() {
        int i10;
        int iA;
        int paddingBottom;
        g2 g2Var;
        g2 g2Var2 = this.f1813m;
        Context context = this.f1811i;
        i0 i0Var = this.I;
        if (g2Var2 == null) {
            g2 g2VarN = n(context, !this.H);
            this.f1813m = g2VarN;
            g2VarN.setAdapter(this.f1812l);
            this.f1813m.setOnItemClickListener(this.z);
            this.f1813m.setFocusable(true);
            this.f1813m.setFocusableInTouchMode(true);
            this.f1813m.setOnItemSelectedListener(new p2(this, i));
            this.f1813m.setOnScrollListener(this.C);
            i0Var.setContentView(this.f1813m);
        }
        Drawable background = i0Var.getBackground();
        Rect rect = this.F;
        if (background != null) {
            background.getPadding(rect);
            int i11 = rect.top;
            i10 = rect.bottom + i11;
            if (!this.f1819s) {
                this.f1817q = -i11;
            }
        } else {
            rect.setEmpty();
            i10 = 0;
        }
        boolean z = i0Var.getInputMethodMode() == 2;
        View view = this.f1824y;
        int i12 = this.f1817q;
        if (Build.VERSION.SDK_INT <= 23) {
            Method method = K;
            if (method != null) {
                try {
                    iA = ((Integer) method.invoke(i0Var, view, Integer.valueOf(i12), Boolean.valueOf(z))).intValue();
                } catch (Exception unused) {
                    Log.i("ListPopupWindow", "Could not call getMaxAvailableHeightMethod(View, int, boolean) on PopupWindow. Using the public version.");
                    iA = i0Var.getMaxAvailableHeight(view, i12);
                }
            } else {
                iA = i0Var.getMaxAvailableHeight(view, i12);
            }
        } else {
            iA = q2.a(i0Var, view, i12, z);
        }
        int i13 = this.f1814n;
        if (i13 == -1) {
            paddingBottom = iA + i10;
        } else {
            int i14 = this.f1815o;
            int iA2 = this.f1813m.a(i14 != -2 ? i14 != -1 ? View.MeasureSpec.makeMeasureSpec(i14, 1073741824) : View.MeasureSpec.makeMeasureSpec(context.getResources().getDisplayMetrics().widthPixels - (rect.left + rect.right), 1073741824) : View.MeasureSpec.makeMeasureSpec(context.getResources().getDisplayMetrics().widthPixels - (rect.left + rect.right), Integer.MIN_VALUE), iA);
            paddingBottom = iA2 + (iA2 > 0 ? this.f1813m.getPaddingBottom() + this.f1813m.getPaddingTop() + i10 : 0);
        }
        boolean z5 = i0Var.getInputMethodMode() == 2;
        u0.l.d(i0Var, this.f1818r);
        if (i0Var.isShowing()) {
            View view2 = this.f1824y;
            WeakHashMap weakHashMap = androidx.core.view.b2.f2200a;
            if (view2.isAttachedToWindow()) {
                int width = this.f1815o;
                if (width == -1) {
                    width = -1;
                } else if (width == -2) {
                    width = this.f1824y.getWidth();
                }
                if (i13 == -1) {
                    i13 = z5 ? paddingBottom : -1;
                    if (z5) {
                        i0Var.setWidth(this.f1815o == -1 ? -1 : 0);
                        i0Var.setHeight(0);
                    } else {
                        i0Var.setWidth(this.f1815o == -1 ? -1 : 0);
                        i0Var.setHeight(-1);
                    }
                } else if (i13 == -2) {
                    i13 = paddingBottom;
                }
                i0Var.setOutsideTouchable(true);
                View view3 = this.f1824y;
                int i15 = width;
                int i16 = this.f1816p;
                int i17 = this.f1817q;
                int i18 = i15 < 0 ? -1 : i15;
                if (i13 < 0) {
                    i13 = -1;
                }
                i0Var.update(view3, i16, i17, i18, i13);
                return;
            }
            return;
        }
        int width2 = this.f1815o;
        if (width2 == -1) {
            width2 = -1;
        } else if (width2 == -2) {
            width2 = this.f1824y.getWidth();
        }
        if (i13 == -1) {
            i13 = -1;
        } else if (i13 == -2) {
            i13 = paddingBottom;
        }
        i0Var.setWidth(width2);
        i0Var.setHeight(i13);
        if (Build.VERSION.SDK_INT <= 28) {
            Method method2 = J;
            if (method2 != null) {
                try {
                    method2.invoke(i0Var, Boolean.TRUE);
                } catch (Exception unused2) {
                    Log.i("ListPopupWindow", "Could not call setClipToScreenEnabled() on PopupWindow. Oh well.");
                }
            }
        } else {
            r2.b(i0Var, true);
        }
        i0Var.setOutsideTouchable(true);
        i0Var.setTouchInterceptor(this.B);
        if (this.f1821u) {
            u0.l.c(i0Var, this.f1820t);
        }
        if (Build.VERSION.SDK_INT <= 28) {
            Method method3 = L;
            if (method3 != null) {
                try {
                    method3.invoke(i0Var, this.G);
                } catch (Exception e5) {
                    Log.e("ListPopupWindow", "Could not invoke setEpicenterBounds on PopupWindow", e5);
                }
            }
        } else {
            r2.a(i0Var, this.G);
        }
        i0Var.showAsDropDown(this.f1824y, this.f1816p, this.f1817q, this.f1822v);
        this.f1813m.setSelection(-1);
        if ((!this.H || this.f1813m.isInTouchMode()) && (g2Var = this.f1813m) != null) {
            g2Var.setListSelectionHidden(true);
            g2Var.requestLayout();
        }
        if (this.H) {
            return;
        }
        this.E.post(this.D);
    }
}
