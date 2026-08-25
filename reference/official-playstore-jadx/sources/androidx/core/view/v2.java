package androidx.core.view;

import android.animation.ValueAnimator;
import android.os.Build;
import android.view.View;
import android.view.WindowInsets;
import j$.util.Objects;
import java.util.WeakHashMap;

/* JADX INFO: loaded from: classes3.dex */
public final class v2 implements View.OnApplyWindowInsetsListener {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final t2 f2325a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public q3 f2326b;

    public v2(View view, t2 t2Var) {
        q3 q3VarB;
        this.f2325a = t2Var;
        WeakHashMap weakHashMap = b2.f2200a;
        q3 q3VarA = l1.a(view);
        if (q3VarA != null) {
            int i10 = Build.VERSION.SDK_INT;
            q3VarB = (i10 >= 30 ? new g3(q3VarA) : i10 >= 29 ? new f3(q3VarA) : new e3(q3VarA)).b();
        } else {
            q3VarB = null;
        }
        this.f2326b = q3VarB;
    }

    @Override // android.view.View.OnApplyWindowInsetsListener
    public final WindowInsets onApplyWindowInsets(View view, WindowInsets windowInsets) {
        if (!view.isLaidOut()) {
            this.f2326b = q3.c(view, windowInsets);
            return w2.h(view, windowInsets);
        }
        q3 q3VarC = q3.c(view, windowInsets);
        n3 n3Var = q3VarC.f2300a;
        if (this.f2326b == null) {
            WeakHashMap weakHashMap = b2.f2200a;
            this.f2326b = l1.a(view);
        }
        if (this.f2326b == null) {
            this.f2326b = q3VarC;
            return w2.h(view, windowInsets);
        }
        t2 t2VarI = w2.i(view);
        if (t2VarI != null && Objects.equals(t2VarI.mDispachedInsets, windowInsets)) {
            return w2.h(view, windowInsets);
        }
        q3 q3Var = this.f2326b;
        int i10 = 0;
        for (int i11 = 1; i11 <= 256; i11 <<= 1) {
            if (!n3Var.f(i11).equals(q3Var.f2300a.f(i11))) {
                i10 |= i11;
            }
        }
        if (i10 == 0) {
            return w2.h(view, windowInsets);
        }
        q3 q3Var2 = this.f2326b;
        b3 b3Var = new b3(i10, (i10 & 8) != 0 ? n3Var.f(8).f2089d > q3Var2.f2300a.f(8).f2089d ? w2.f2329d : w2.f2330e : w2.f2331f, 160L);
        b3Var.f2205a.c(0.0f);
        ValueAnimator duration = ValueAnimator.ofFloat(0.0f, 1.0f).setDuration(b3Var.f2205a.a());
        androidx.core.graphics.c cVarF = n3Var.f(i10);
        androidx.core.graphics.c cVarF2 = q3Var2.f2300a.f(i10);
        int iMin = Math.min(cVarF.f2086a, cVarF2.f2086a);
        int i12 = cVarF.f2087b;
        int i13 = cVarF2.f2087b;
        int iMin2 = Math.min(i12, i13);
        int i14 = cVarF.f2088c;
        int i15 = cVarF2.f2088c;
        int iMin3 = Math.min(i14, i15);
        int i16 = cVarF.f2089d;
        int i17 = i10;
        int i18 = cVarF2.f2089d;
        r2 r2Var = new r2(androidx.core.graphics.c.b(iMin, iMin2, iMin3, Math.min(i16, i18)), androidx.core.graphics.c.b(Math.max(cVarF.f2086a, cVarF2.f2086a), Math.max(i12, i13), Math.max(i14, i15), Math.max(i16, i18)));
        w2.e(view, b3Var, windowInsets, false);
        duration.addUpdateListener(new u2(b3Var, q3VarC, q3Var2, i17, view));
        duration.addListener(new m2(b3Var, view, 1));
        u0.a(view, new androidx.appcompat.view.menu.h(1, view, b3Var, r2Var, duration, false));
        this.f2326b = q3VarC;
        return w2.h(view, windowInsets);
    }
}
