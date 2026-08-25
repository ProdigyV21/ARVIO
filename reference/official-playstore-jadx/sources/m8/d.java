package m8;

import com.google.common.util.concurrent.r0;
import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: loaded from: classes5.dex */
public final class d extends s implements w8.a {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final Annotation f20270a;

    public d(Annotation annotation) {
        this.f20270a = annotation;
    }

    public final boolean equals(Object obj) {
        if (obj instanceof d) {
            return this.f20270a == ((d) obj).f20270a;
        }
        return false;
    }

    @Override // w8.a
    public final ArrayList g() throws IllegalAccessException, InvocationTargetException {
        Annotation annotation = this.f20270a;
        Method[] declaredMethods = ((kotlin.jvm.internal.f) r0.k(annotation)).c().getDeclaredMethods();
        ArrayList arrayList = new ArrayList(declaredMethods.length);
        for (Method method : declaredMethods) {
            Object objInvoke = method.invoke(annotation, null);
            f9.f fVarJ = f9.f.j(method.getName());
            Class<?> cls = objInvoke.getClass();
            List list = c.f20266a;
            arrayList.add(Enum.class.isAssignableFrom(cls) ? new t(fVarJ, (Enum) objInvoke) : objInvoke instanceof Annotation ? new f(fVarJ, (Annotation) objInvoke) : objInvoke instanceof Object[] ? new h(fVarJ, (Object[]) objInvoke) : objInvoke instanceof Class ? new p(fVarJ, (Class) objInvoke) : new v(fVarJ, objInvoke));
        }
        return arrayList;
    }

    @Override // w8.a
    public final f9.b h() {
        return c.a(((kotlin.jvm.internal.f) r0.k(this.f20270a)).c());
    }

    public final int hashCode() {
        return System.identityHashCode(this.f20270a);
    }

    @Override // w8.a
    public final o t() {
        return new o(((kotlin.jvm.internal.f) r0.k(this.f20270a)).c());
    }

    public final String toString() {
        return d.class.getName() + ": " + this.f20270a;
    }
}
