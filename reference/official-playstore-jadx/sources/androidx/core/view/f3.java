package androidx.core.view;

import android.view.WindowInsets;

/* JADX INFO: loaded from: classes3.dex */
public class f3 extends h3 {

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final WindowInsets.Builder f2234c;

    public f3() {
        this.f2234c = android.support.v4.media.session.q.e();
    }

    @Override // androidx.core.view.h3
    public q3 b() {
        a();
        q3 q3VarC = q3.c(null, this.f2234c.build());
        q3VarC.f2300a.q(this.f2245b);
        return q3VarC;
    }

    @Override // androidx.core.view.h3
    public void d(androidx.core.graphics.c cVar) {
        this.f2234c.setMandatorySystemGestureInsets(cVar.d());
    }

    @Override // androidx.core.view.h3
    public void e(androidx.core.graphics.c cVar) {
        this.f2234c.setStableInsets(cVar.d());
    }

    @Override // androidx.core.view.h3
    public void f(androidx.core.graphics.c cVar) {
        this.f2234c.setSystemGestureInsets(cVar.d());
    }

    @Override // androidx.core.view.h3
    public void g(androidx.core.graphics.c cVar) {
        this.f2234c.setSystemWindowInsets(cVar.d());
    }

    @Override // androidx.core.view.h3
    public void h(androidx.core.graphics.c cVar) {
        this.f2234c.setTappableElementInsets(cVar.d());
    }

    public f3(q3 q3Var) {
        WindowInsets.Builder builderE;
        super(q3Var);
        WindowInsets windowInsetsB = q3Var.b();
        if (windowInsetsB != null) {
            builderE = android.support.v4.media.session.q.f(windowInsetsB);
        } else {
            builderE = android.support.v4.media.session.q.e();
        }
        this.f2234c = builderE;
    }
}
