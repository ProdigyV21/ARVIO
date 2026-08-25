package androidx.core.view;

import android.view.View;
import android.view.WindowInsets;

/* JADX INFO: loaded from: classes3.dex */
public abstract class l1 {
    public static q3 a(View view) {
        WindowInsets rootWindowInsets = view.getRootWindowInsets();
        if (rootWindowInsets == null) {
            return null;
        }
        q3 q3VarC = q3.c(null, rootWindowInsets);
        n3 n3Var = q3VarC.f2300a;
        n3Var.r(q3VarC);
        n3Var.d(view.getRootView());
        return q3VarC;
    }

    public static int b(View view) {
        return view.getScrollIndicators();
    }

    public static void c(View view, int i10) {
        view.setScrollIndicators(i10);
    }

    public static void d(View view, int i10, int i11) {
        view.setScrollIndicators(i10, i11);
    }
}
