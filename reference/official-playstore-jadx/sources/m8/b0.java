package m8;

import java.lang.reflect.Member;
import java.lang.reflect.Method;

/* JADX INFO: loaded from: classes5.dex */
public final class b0 extends w implements w8.v {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final Object f20265a;

    public b0(Object obj) {
        this.f20265a = obj;
    }

    @Override // m8.w
    public final Member H() {
        io.sentry.android.replay.viewhierarchy.c cVar = f4.f.k;
        Object obj = this.f20265a;
        if (cVar == null) {
            Class<?> cls = obj.getClass();
            try {
                cVar = new io.sentry.android.replay.viewhierarchy.c(cls.getMethod("getType", null), cls.getMethod("getAccessor", null));
            } catch (NoSuchMethodException unused) {
                cVar = new io.sentry.android.replay.viewhierarchy.c(null, null);
            }
            f4.f.k = cVar;
        }
        Method method = cVar.f17073b;
        Method method2 = method != null ? (Method) method.invoke(obj, null) : null;
        if (method2 != null) {
            return method2;
        }
        throw new NoSuchMethodError("Can't find `getAccessor` method");
    }

    @Override // w8.v
    public final w8.w getType() {
        io.sentry.android.replay.viewhierarchy.c cVar = f4.f.k;
        Object obj = this.f20265a;
        if (cVar == null) {
            Class<?> cls = obj.getClass();
            try {
                cVar = new io.sentry.android.replay.viewhierarchy.c(cls.getMethod("getType", null), cls.getMethod("getAccessor", null));
            } catch (NoSuchMethodException unused) {
                cVar = new io.sentry.android.replay.viewhierarchy.c(null, null);
            }
            f4.f.k = cVar;
        }
        Method method = cVar.f17072a;
        Class cls2 = method != null ? (Class) method.invoke(obj, null) : null;
        if (cls2 != null) {
            return new q(cls2);
        }
        throw new NoSuchMethodError("Can't find `getType` method");
    }
}
