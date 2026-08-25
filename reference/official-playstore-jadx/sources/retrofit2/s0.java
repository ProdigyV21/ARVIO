package retrofit2;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/* JADX INFO: loaded from: classes5.dex */
public final class s0 implements InvocationHandler {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final n0 f21582a = n0.f21524c;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final Object[] f21583b = new Object[0];

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final /* synthetic */ Class f21584c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final /* synthetic */ u0 f21585d;

    public s0(u0 u0Var, Class cls) {
        this.f21585d = u0Var;
        this.f21584c = cls;
    }

    @Override // java.lang.reflect.InvocationHandler
    public final Object invoke(Object obj, Method method, Object[] objArr) {
        v0 v0VarA;
        n0 n0Var = this.f21582a;
        if (method.getDeclaringClass() == Object.class) {
            return method.invoke(this, objArr);
        }
        if (objArr == null) {
            objArr = this.f21583b;
        }
        if (n0Var.c(method)) {
            return n0Var.b(method, this.f21584c, obj, objArr);
        }
        u0 u0Var = this.f21585d;
        v0 v0Var = (v0) u0Var.f21595a.get(method);
        if (v0Var == null) {
            synchronized (u0Var.f21595a) {
                try {
                    v0VarA = (v0) u0Var.f21595a.get(method);
                    if (v0VarA == null) {
                        v0VarA = v0.a(u0Var, method);
                        u0Var.f21595a.put(method, v0VarA);
                    }
                } catch (Throwable th) {
                    throw th;
                }
            }
            v0Var = v0VarA;
        }
        r rVar = (r) v0Var;
        return rVar.b(new y(rVar.f21575a, objArr, rVar.f21576b, rVar.f21577c), objArr);
    }
}
