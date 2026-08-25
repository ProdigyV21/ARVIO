package retrofit2;

import java.io.IOException;
import java.lang.reflect.Method;

/* JADX INFO: loaded from: classes5.dex */
public final class b0 extends b1 {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final Method f21479b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final int f21480c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final l f21481d;

    public b0(Method method, int i10, l lVar) {
        this.f21479b = method;
        this.f21480c = i10;
        this.f21481d = lVar;
    }

    @Override // retrofit2.b1
    public final void a(o0 o0Var, Object obj) {
        int i10 = this.f21480c;
        Method method = this.f21479b;
        if (obj == null) {
            throw b1.k(method, i10, "Body parameter value must not be null.", new Object[0]);
        }
        try {
            o0Var.k = (gb.n0) this.f21481d.w(obj);
        } catch (IOException e5) {
            throw b1.l(method, e5, i10, "Unable to convert " + obj + " to RequestBody", new Object[0]);
        }
    }
}
