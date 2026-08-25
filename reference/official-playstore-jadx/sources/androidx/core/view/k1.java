package androidx.core.view;

import android.content.res.ColorStateList;
import android.graphics.PorterDuff;
import android.graphics.Rect;
import android.os.Build;
import android.util.Log;
import android.view.View;
import android.view.WindowInsets;
import com.arvio.tv.R;

/* JADX INFO: loaded from: classes3.dex */
public abstract class k1 {
    public static void a(WindowInsets windowInsets, View view) {
        View.OnApplyWindowInsetsListener onApplyWindowInsetsListener = (View.OnApplyWindowInsetsListener) view.getTag(R.id.tag_window_insets_animation_callback);
        if (onApplyWindowInsetsListener != null) {
            onApplyWindowInsetsListener.onApplyWindowInsets(view, windowInsets);
        }
    }

    public static q3 b(View view, q3 q3Var, Rect rect) {
        WindowInsets windowInsetsB = q3Var.b();
        if (windowInsetsB != null) {
            return q3.c(view, view.computeSystemWindowInsets(windowInsetsB, rect));
        }
        rect.setEmpty();
        return q3Var;
    }

    public static boolean c(View view, float f10, float f11, boolean z) {
        return view.dispatchNestedFling(f10, f11, z);
    }

    public static boolean d(View view, float f10, float f11) {
        return view.dispatchNestedPreFling(f10, f11);
    }

    public static boolean e(View view, int i10, int i11, int[] iArr, int[] iArr2) {
        return view.dispatchNestedPreScroll(i10, i11, iArr, iArr2);
    }

    public static boolean f(View view, int i10, int i11, int i12, int i13, int[] iArr) {
        return view.dispatchNestedScroll(i10, i11, i12, i13, iArr);
    }

    public static ColorStateList g(View view) {
        return view.getBackgroundTintList();
    }

    public static PorterDuff.Mode h(View view) {
        return view.getBackgroundTintMode();
    }

    public static float i(View view) {
        return view.getElevation();
    }

    public static q3 j(View view) {
        if (d3.f2219d && view.isAttachedToWindow()) {
            try {
                Object obj = d3.f2216a.get(view.getRootView());
                if (obj != null) {
                    Rect rect = (Rect) d3.f2217b.get(obj);
                    Rect rect2 = (Rect) d3.f2218c.get(obj);
                    if (rect != null && rect2 != null) {
                        int i10 = Build.VERSION.SDK_INT;
                        h3 g3Var = i10 >= 30 ? new g3() : i10 >= 29 ? new f3() : new e3();
                        g3Var.e(androidx.core.graphics.c.b(rect.left, rect.top, rect.right, rect.bottom));
                        g3Var.g(androidx.core.graphics.c.b(rect2.left, rect2.top, rect2.right, rect2.bottom));
                        q3 q3VarB = g3Var.b();
                        q3VarB.f2300a.r(q3VarB);
                        q3VarB.f2300a.d(view.getRootView());
                        return q3VarB;
                    }
                }
            } catch (IllegalAccessException e5) {
                Log.w("WindowInsetsCompat", "Failed to get insets from AttachInfo. " + e5.getMessage(), e5);
            }
        }
        return null;
    }

    public static String k(View view) {
        return view.getTransitionName();
    }

    public static float l(View view) {
        return view.getTranslationZ();
    }

    public static float m(View view) {
        return view.getZ();
    }

    public static boolean n(View view) {
        return view.hasNestedScrollingParent();
    }

    public static boolean o(View view) {
        return view.isImportantForAccessibility();
    }

    public static boolean p(View view) {
        return view.isNestedScrollingEnabled();
    }

    public static void q(View view, ColorStateList colorStateList) {
        view.setBackgroundTintList(colorStateList);
    }

    public static void r(View view, PorterDuff.Mode mode) {
        view.setBackgroundTintMode(mode);
    }

    public static void s(View view, float f10) {
        view.setElevation(f10);
    }

    public static void t(View view, boolean z) {
        view.setNestedScrollingEnabled(z);
    }

    public static void u(View view, r0 r0Var) {
        if (Build.VERSION.SDK_INT < 30) {
            view.setTag(R.id.tag_on_apply_window_listener, r0Var);
        }
        if (r0Var == null) {
            view.setOnApplyWindowInsetsListener((View.OnApplyWindowInsetsListener) view.getTag(R.id.tag_window_insets_animation_callback));
        } else {
            view.setOnApplyWindowInsetsListener(new j1(view, r0Var));
        }
    }

    public static void v(View view, String str) {
        view.setTransitionName(str);
    }

    public static void w(View view, float f10) {
        view.setTranslationZ(f10);
    }

    public static void x(View view, float f10) {
        view.setZ(f10);
    }

    public static boolean y(View view, int i10) {
        return view.startNestedScroll(i10);
    }

    public static void z(View view) {
        view.stopNestedScroll();
    }
}
