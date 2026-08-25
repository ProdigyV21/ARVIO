package androidx.appcompat.app;

import android.content.Context;
import android.graphics.Rect;
import android.os.Build;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowInsets;
import android.widget.FrameLayout;
import androidx.appcompat.widget.ActionBarContextView;
import androidx.appcompat.widget.w4;
import androidx.core.view.b2;
import androidx.core.view.e3;
import androidx.core.view.f3;
import androidx.core.view.g3;
import androidx.core.view.h3;
import androidx.core.view.n3;
import androidx.core.view.p2;
import androidx.core.view.q3;
import com.arvio.tv.R;
import java.lang.reflect.Method;
import java.util.WeakHashMap;

/* JADX INFO: loaded from: classes.dex */
public final class b0 implements androidx.core.view.r0, p2 {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final /* synthetic */ Object f1033i;

    public /* synthetic */ b0(Object obj) {
        this.f1033i = obj;
    }

    @Override // androidx.core.view.p2
    public void a() {
        ((View) ((l1) this.f1033i).f1096d.getParent()).invalidate();
    }

    @Override // androidx.core.view.r0
    public q3 onApplyWindowInsets(View view, q3 q3Var) {
        int i10;
        boolean z;
        q3 q3VarB;
        boolean z5;
        boolean z10;
        n3 n3Var = q3Var.f2300a;
        int i11 = n3Var.k().f2087b;
        s0 s0Var = (s0) this.f1033i;
        Context context = s0Var.f1189u;
        int i12 = n3Var.k().f2087b;
        ActionBarContextView actionBarContextView = s0Var.F;
        if (actionBarContextView == null || !(actionBarContextView.getLayoutParams() instanceof ViewGroup.MarginLayoutParams)) {
            i10 = 0;
            z = false;
        } else {
            ViewGroup.MarginLayoutParams marginLayoutParams = (ViewGroup.MarginLayoutParams) s0Var.F.getLayoutParams();
            if (s0Var.F.isShown()) {
                if (s0Var.f1183m0 == null) {
                    s0Var.f1183m0 = new Rect();
                    s0Var.f1184n0 = new Rect();
                }
                Rect rect = s0Var.f1183m0;
                Rect rect2 = s0Var.f1184n0;
                rect.set(n3Var.k().f2086a, n3Var.k().f2087b, n3Var.k().f2088c, n3Var.k().f2089d);
                ViewGroup viewGroup = s0Var.K;
                Method method = w4.f1826a;
                if (method != null) {
                    try {
                        method.invoke(viewGroup, rect, rect2);
                    } catch (Exception e5) {
                        Log.d("ViewUtils", "Could not invoke computeFitSystemWindows", e5);
                    }
                }
                int i13 = rect.top;
                int i14 = rect.left;
                int i15 = rect.right;
                ViewGroup viewGroup2 = s0Var.K;
                WeakHashMap weakHashMap = b2.f2200a;
                q3 q3VarA = androidx.core.view.l1.a(viewGroup2);
                int i16 = q3VarA == null ? 0 : q3VarA.f2300a.k().f2086a;
                int i17 = q3VarA == null ? 0 : q3VarA.f2300a.k().f2088c;
                if (marginLayoutParams.topMargin == i13 && marginLayoutParams.leftMargin == i14 && marginLayoutParams.rightMargin == i15) {
                    z10 = false;
                } else {
                    marginLayoutParams.topMargin = i13;
                    marginLayoutParams.leftMargin = i14;
                    marginLayoutParams.rightMargin = i15;
                    z10 = true;
                }
                if (i13 <= 0 || s0Var.M != null) {
                    View view2 = s0Var.M;
                    if (view2 != null) {
                        ViewGroup.MarginLayoutParams marginLayoutParams2 = (ViewGroup.MarginLayoutParams) view2.getLayoutParams();
                        int i18 = marginLayoutParams2.height;
                        int i19 = marginLayoutParams.topMargin;
                        if (i18 != i19 || marginLayoutParams2.leftMargin != i16 || marginLayoutParams2.rightMargin != i17) {
                            marginLayoutParams2.height = i19;
                            marginLayoutParams2.leftMargin = i16;
                            marginLayoutParams2.rightMargin = i17;
                            s0Var.M.setLayoutParams(marginLayoutParams2);
                        }
                    }
                } else {
                    View view3 = new View(context);
                    s0Var.M = view3;
                    view3.setVisibility(8);
                    FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(-1, marginLayoutParams.topMargin, 51);
                    layoutParams.leftMargin = i16;
                    layoutParams.rightMargin = i17;
                    s0Var.K.addView(s0Var.M, -1, layoutParams);
                }
                View view4 = s0Var.M;
                boolean z11 = view4 != null;
                if (z11 && view4.getVisibility() != 0) {
                    View view5 = s0Var.M;
                    view5.setBackgroundColor((view5.getWindowSystemUiVisibility() & 8192) != 0 ? androidx.core.content.b.a(context, R.color.abc_decor_view_status_guard_light) : androidx.core.content.b.a(context, R.color.abc_decor_view_status_guard));
                }
                if (!s0Var.R && z11) {
                    i12 = 0;
                }
                z5 = z10;
                z = z11;
                i10 = 0;
            } else {
                z5 = true;
                i10 = 0;
                if (marginLayoutParams.topMargin != 0) {
                    marginLayoutParams.topMargin = 0;
                    z = false;
                } else {
                    z = false;
                    z5 = false;
                }
            }
            if (z5) {
                s0Var.F.setLayoutParams(marginLayoutParams);
            }
        }
        View view6 = s0Var.M;
        if (view6 != null) {
            view6.setVisibility(z ? i10 : 8);
        }
        if (i11 != i12) {
            int i20 = n3Var.k().f2086a;
            int i21 = n3Var.k().f2088c;
            int i22 = n3Var.k().f2089d;
            int i23 = Build.VERSION.SDK_INT;
            h3 g3Var = i23 >= 30 ? new g3(q3Var) : i23 >= 29 ? new f3(q3Var) : new e3(q3Var);
            g3Var.g(androidx.core.graphics.c.b(i20, i12, i21, i22));
            q3VarB = g3Var.b();
        } else {
            q3VarB = q3Var;
        }
        WeakHashMap weakHashMap2 = b2.f2200a;
        WindowInsets windowInsetsB = q3VarB.b();
        if (windowInsetsB == null) {
            return q3VarB;
        }
        WindowInsets windowInsetsB2 = androidx.core.view.i1.b(view, windowInsetsB);
        return !windowInsetsB2.equals(windowInsetsB) ? q3.c(view, windowInsetsB2) : q3VarB;
    }
}
