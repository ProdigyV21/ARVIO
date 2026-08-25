package androidx.core.view;

import android.graphics.Rect;
import android.os.Build;
import android.util.Log;
import android.view.View;
import android.view.WindowInsets;
import j$.util.Objects;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

/* JADX INFO: loaded from: classes3.dex */
public abstract class i3 extends n3 {

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public static boolean f2249h = false;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public static Method f2250i;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public static Class f2251j;
    public static Field k;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public static Field f2252l;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final WindowInsets f2253c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public androidx.core.graphics.c[] f2254d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public androidx.core.graphics.c f2255e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public q3 f2256f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public androidx.core.graphics.c f2257g;

    public i3(q3 q3Var, WindowInsets windowInsets) {
        super(q3Var);
        this.f2255e = null;
        this.f2253c = windowInsets;
    }

    private androidx.core.graphics.c t(int i10, boolean z) {
        androidx.core.graphics.c cVarA = androidx.core.graphics.c.f2085e;
        for (int i11 = 1; i11 <= 256; i11 <<= 1) {
            if ((i10 & i11) != 0) {
                cVarA = androidx.core.graphics.c.a(cVarA, u(i11, z));
            }
        }
        return cVarA;
    }

    private androidx.core.graphics.c v() {
        q3 q3Var = this.f2256f;
        return q3Var != null ? q3Var.f2300a.i() : androidx.core.graphics.c.f2085e;
    }

    private androidx.core.graphics.c w(View view) {
        if (Build.VERSION.SDK_INT >= 30) {
            throw new UnsupportedOperationException("getVisibleInsets() should not be called on API >= 30. Use WindowInsets.isVisible() instead.");
        }
        if (!f2249h) {
            y();
        }
        Method method = f2250i;
        if (method != null && f2251j != null && k != null) {
            try {
                Object objInvoke = method.invoke(view, null);
                if (objInvoke == null) {
                    Log.w("WindowInsetsCompat", "Failed to get visible insets. getViewRootImpl() returned null from the provided view. This means that the view is either not attached or the method has been overridden", new NullPointerException());
                    return null;
                }
                Rect rect = (Rect) k.get(f2252l.get(objInvoke));
                if (rect != null) {
                    return androidx.core.graphics.c.b(rect.left, rect.top, rect.right, rect.bottom);
                }
            } catch (ReflectiveOperationException e5) {
                Log.e("WindowInsetsCompat", "Failed to get visible insets. (Reflection error). " + e5.getMessage(), e5);
            }
        }
        return null;
    }

    private static void y() {
        try {
            f2250i = View.class.getDeclaredMethod("getViewRootImpl", null);
            Class<?> cls = Class.forName("android.view.View$AttachInfo");
            f2251j = cls;
            k = cls.getDeclaredField("mVisibleInsets");
            f2252l = Class.forName("android.view.ViewRootImpl").getDeclaredField("mAttachInfo");
            k.setAccessible(true);
            f2252l.setAccessible(true);
        } catch (ReflectiveOperationException e5) {
            Log.e("WindowInsetsCompat", "Failed to get visible insets. (Reflection error). " + e5.getMessage(), e5);
        }
        f2249h = true;
    }

    @Override // androidx.core.view.n3
    public void d(View view) {
        androidx.core.graphics.c cVarW = w(view);
        if (cVarW == null) {
            cVarW = androidx.core.graphics.c.f2085e;
        }
        z(cVarW);
    }

    @Override // androidx.core.view.n3
    public boolean equals(Object obj) {
        if (super.equals(obj)) {
            return Objects.equals(this.f2257g, ((i3) obj).f2257g);
        }
        return false;
    }

    @Override // androidx.core.view.n3
    public androidx.core.graphics.c f(int i10) {
        return t(i10, false);
    }

    @Override // androidx.core.view.n3
    public androidx.core.graphics.c g(int i10) {
        return t(i10, true);
    }

    @Override // androidx.core.view.n3
    public final androidx.core.graphics.c k() {
        if (this.f2255e == null) {
            WindowInsets windowInsets = this.f2253c;
            this.f2255e = androidx.core.graphics.c.b(windowInsets.getSystemWindowInsetLeft(), windowInsets.getSystemWindowInsetTop(), windowInsets.getSystemWindowInsetRight(), windowInsets.getSystemWindowInsetBottom());
        }
        return this.f2255e;
    }

    @Override // androidx.core.view.n3
    public q3 m(int i10, int i11, int i12, int i13) {
        q3 q3VarC = q3.c(null, this.f2253c);
        int i14 = Build.VERSION.SDK_INT;
        h3 g3Var = i14 >= 30 ? new g3(q3VarC) : i14 >= 29 ? new f3(q3VarC) : new e3(q3VarC);
        g3Var.g(q3.a(k(), i10, i11, i12, i13));
        g3Var.e(q3.a(i(), i10, i11, i12, i13));
        return g3Var.b();
    }

    @Override // androidx.core.view.n3
    public boolean o() {
        return this.f2253c.isRound();
    }

    @Override // androidx.core.view.n3
    public boolean p(int i10) {
        for (int i11 = 1; i11 <= 256; i11 <<= 1) {
            if ((i10 & i11) != 0 && !x(i11)) {
                return false;
            }
        }
        return true;
    }

    @Override // androidx.core.view.n3
    public void q(androidx.core.graphics.c[] cVarArr) {
        this.f2254d = cVarArr;
    }

    @Override // androidx.core.view.n3
    public void r(q3 q3Var) {
        this.f2256f = q3Var;
    }

    public androidx.core.graphics.c u(int i10, boolean z) {
        androidx.core.graphics.c cVarI;
        int i11;
        if (i10 == 1) {
            return z ? androidx.core.graphics.c.b(0, Math.max(v().f2087b, k().f2087b), 0, 0) : androidx.core.graphics.c.b(0, k().f2087b, 0, 0);
        }
        if (i10 == 2) {
            if (z) {
                androidx.core.graphics.c cVarV = v();
                androidx.core.graphics.c cVarI2 = i();
                return androidx.core.graphics.c.b(Math.max(cVarV.f2086a, cVarI2.f2086a), 0, Math.max(cVarV.f2088c, cVarI2.f2088c), Math.max(cVarV.f2089d, cVarI2.f2089d));
            }
            androidx.core.graphics.c cVarK = k();
            q3 q3Var = this.f2256f;
            cVarI = q3Var != null ? q3Var.f2300a.i() : null;
            int iMin = cVarK.f2089d;
            if (cVarI != null) {
                iMin = Math.min(iMin, cVarI.f2089d);
            }
            return androidx.core.graphics.c.b(cVarK.f2086a, 0, cVarK.f2088c, iMin);
        }
        androidx.core.graphics.c cVar = androidx.core.graphics.c.f2085e;
        if (i10 == 8) {
            androidx.core.graphics.c[] cVarArr = this.f2254d;
            cVarI = cVarArr != null ? cVarArr[f4.f.m(8)] : null;
            if (cVarI != null) {
                return cVarI;
            }
            androidx.core.graphics.c cVarK2 = k();
            androidx.core.graphics.c cVarV2 = v();
            int i12 = cVarK2.f2089d;
            if (i12 > cVarV2.f2089d) {
                return androidx.core.graphics.c.b(0, 0, 0, i12);
            }
            androidx.core.graphics.c cVar2 = this.f2257g;
            return (cVar2 == null || cVar2.equals(cVar) || (i11 = this.f2257g.f2089d) <= cVarV2.f2089d) ? cVar : androidx.core.graphics.c.b(0, 0, 0, i11);
        }
        if (i10 == 16) {
            return j();
        }
        if (i10 == 32) {
            return h();
        }
        if (i10 == 64) {
            return l();
        }
        if (i10 != 128) {
            return cVar;
        }
        q3 q3Var2 = this.f2256f;
        v vVarE = q3Var2 != null ? q3Var2.f2300a.e() : e();
        if (vVarE == null) {
            return cVar;
        }
        int i13 = Build.VERSION.SDK_INT;
        return androidx.core.graphics.c.b(i13 >= 28 ? t.d(vVarE.f2323a) : 0, i13 >= 28 ? t.f(vVarE.f2323a) : 0, i13 >= 28 ? t.e(vVarE.f2323a) : 0, i13 >= 28 ? t.c(vVarE.f2323a) : 0);
    }

    public boolean x(int i10) {
        if (i10 != 1 && i10 != 2) {
            if (i10 == 4) {
                return false;
            }
            if (i10 != 8 && i10 != 128) {
                return true;
            }
        }
        return !u(i10, false).equals(androidx.core.graphics.c.f2085e);
    }

    public void z(androidx.core.graphics.c cVar) {
        this.f2257g = cVar;
    }
}
