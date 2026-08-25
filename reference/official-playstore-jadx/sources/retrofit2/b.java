package retrofit2;

import java.lang.annotation.Annotation;
import java.lang.reflect.Type;

/* JADX INFO: loaded from: classes5.dex */
public final class b extends k {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public boolean f21478a;

    @Override // retrofit2.k
    public final l a(Type type) {
        if (gb.n0.class.isAssignableFrom(b1.f(type))) {
            return a.f21469n;
        }
        return null;
    }

    @Override // retrofit2.k
    public final l b(Type type, Annotation[] annotationArr, u0 u0Var) {
        if (type == gb.s0.class) {
            return b1.i(annotationArr, zc.w.class) ? a.f21470o : a.f21468m;
        }
        if (type == Void.class) {
            return a.f21472q;
        }
        if (!this.f21478a || type != x6.t0.class) {
            return null;
        }
        try {
            return a.f21471p;
        } catch (NoClassDefFoundError unused) {
            this.f21478a = false;
            return null;
        }
    }
}
