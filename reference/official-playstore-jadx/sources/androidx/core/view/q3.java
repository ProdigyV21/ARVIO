package androidx.core.view;

import android.os.Build;
import android.view.View;
import android.view.WindowInsets;
import j$.util.Objects;
import java.util.WeakHashMap;

/* JADX INFO: loaded from: classes3.dex */
public final class q3 {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static final q3 f2299b;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final n3 f2300a;

    static {
        if (Build.VERSION.SDK_INT >= 30) {
            f2299b = m3.f2291q;
        } else {
            f2299b = n3.f2295b;
        }
    }

    public q3(WindowInsets windowInsets) {
        int i10 = Build.VERSION.SDK_INT;
        if (i10 >= 30) {
            this.f2300a = new m3(this, windowInsets);
            return;
        }
        if (i10 >= 29) {
            this.f2300a = new l3(this, windowInsets);
        } else if (i10 >= 28) {
            this.f2300a = new k3(this, windowInsets);
        } else {
            this.f2300a = new j3(this, windowInsets);
        }
    }

    public static androidx.core.graphics.c a(androidx.core.graphics.c cVar, int i10, int i11, int i12, int i13) {
        int iMax = Math.max(0, cVar.f2086a - i10);
        int iMax2 = Math.max(0, cVar.f2087b - i11);
        int iMax3 = Math.max(0, cVar.f2088c - i12);
        int iMax4 = Math.max(0, cVar.f2089d - i13);
        return (iMax == i10 && iMax2 == i11 && iMax3 == i12 && iMax4 == i13) ? cVar : androidx.core.graphics.c.b(iMax, iMax2, iMax3, iMax4);
    }

    public static q3 c(View view, WindowInsets windowInsets) {
        windowInsets.getClass();
        q3 q3Var = new q3(windowInsets);
        if (view != null && view.isAttachedToWindow()) {
            WeakHashMap weakHashMap = b2.f2200a;
            q3 q3VarA = l1.a(view);
            n3 n3Var = q3Var.f2300a;
            n3Var.r(q3VarA);
            n3Var.d(view.getRootView());
        }
        return q3Var;
    }

    public final WindowInsets b() {
        n3 n3Var = this.f2300a;
        if (n3Var instanceof i3) {
            return ((i3) n3Var).f2253c;
        }
        return null;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof q3) {
            return Objects.equals(this.f2300a, ((q3) obj).f2300a);
        }
        return false;
    }

    public final int hashCode() {
        n3 n3Var = this.f2300a;
        if (n3Var == null) {
            return 0;
        }
        return n3Var.hashCode();
    }

    public q3() {
        this.f2300a = new n3(this);
    }
}
