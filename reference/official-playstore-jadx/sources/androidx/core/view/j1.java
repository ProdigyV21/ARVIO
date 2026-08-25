package androidx.core.view;

import android.os.Build;
import android.view.View;
import android.view.WindowInsets;
import java.util.WeakHashMap;

/* JADX INFO: loaded from: classes3.dex */
public final class j1 implements View.OnApplyWindowInsetsListener {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public q3 f2273a = null;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final /* synthetic */ View f2274b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final /* synthetic */ r0 f2275c;

    public j1(View view, r0 r0Var) {
        this.f2274b = view;
        this.f2275c = r0Var;
    }

    @Override // android.view.View.OnApplyWindowInsetsListener
    public WindowInsets onApplyWindowInsets(View view, WindowInsets windowInsets) {
        q3 q3VarC = q3.c(view, windowInsets);
        int i10 = Build.VERSION.SDK_INT;
        r0 r0Var = this.f2275c;
        if (i10 < 30) {
            k1.a(windowInsets, this.f2274b);
            if (q3VarC.equals(this.f2273a)) {
                return r0Var.onApplyWindowInsets(view, q3VarC).b();
            }
        }
        this.f2273a = q3VarC;
        q3 q3VarOnApplyWindowInsets = r0Var.onApplyWindowInsets(view, q3VarC);
        if (i10 >= 30) {
            return q3VarOnApplyWindowInsets.b();
        }
        WeakHashMap weakHashMap = b2.f2200a;
        i1.c(view);
        return q3VarOnApplyWindowInsets.b();
    }
}
