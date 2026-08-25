package androidx.core.view;

import android.animation.ValueAnimator;
import android.os.Build;
import android.view.View;
import android.view.animation.PathInterpolator;
import java.util.Collections;

/* JADX INFO: loaded from: classes3.dex */
public final class u2 implements ValueAnimator.AnimatorUpdateListener {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final /* synthetic */ b3 f2318a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final /* synthetic */ q3 f2319b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final /* synthetic */ q3 f2320c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final /* synthetic */ int f2321d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final /* synthetic */ View f2322e;

    public u2(b3 b3Var, q3 q3Var, q3 q3Var2, int i10, View view) {
        this.f2318a = b3Var;
        this.f2319b = q3Var;
        this.f2320c = q3Var2;
        this.f2321d = i10;
        this.f2322e = view;
    }

    @Override // android.animation.ValueAnimator.AnimatorUpdateListener
    public final void onAnimationUpdate(ValueAnimator valueAnimator) {
        float animatedFraction = valueAnimator.getAnimatedFraction();
        b3 b3Var = this.f2318a;
        a3 a3Var = b3Var.f2205a;
        a3Var.c(animatedFraction);
        q3 q3Var = this.f2319b;
        n3 n3Var = q3Var.f2300a;
        float fB = a3Var.b();
        PathInterpolator pathInterpolator = w2.f2329d;
        int i10 = Build.VERSION.SDK_INT;
        h3 g3Var = i10 >= 30 ? new g3(q3Var) : i10 >= 29 ? new f3(q3Var) : new e3(q3Var);
        for (int i11 = 1; i11 <= 256; i11 <<= 1) {
            if ((this.f2321d & i11) == 0) {
                g3Var.c(i11, n3Var.f(i11));
            } else {
                androidx.core.graphics.c cVarF = n3Var.f(i11);
                androidx.core.graphics.c cVarF2 = this.f2320c.f2300a.f(i11);
                float f10 = 1.0f - fB;
                g3Var.c(i11, q3.a(cVarF, (int) (((double) ((cVarF.f2086a - cVarF2.f2086a) * f10)) + 0.5d), (int) (((double) ((cVarF.f2087b - cVarF2.f2087b) * f10)) + 0.5d), (int) (((double) ((cVarF.f2088c - cVarF2.f2088c) * f10)) + 0.5d), (int) (((double) ((cVarF.f2089d - cVarF2.f2089d) * f10)) + 0.5d)));
            }
        }
        w2.f(this.f2322e, g3Var.b(), Collections.singletonList(b3Var));
    }
}
