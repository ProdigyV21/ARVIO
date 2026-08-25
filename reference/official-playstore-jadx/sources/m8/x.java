package m8;

import java.lang.annotation.Annotation;
import java.lang.reflect.GenericArrayType;
import java.lang.reflect.Member;
import java.lang.reflect.Method;
import java.lang.reflect.Type;
import java.lang.reflect.TypeVariable;
import java.lang.reflect.WildcardType;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: loaded from: classes5.dex */
public final class x extends w implements w8.q {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final Method f20296a;

    public x(Method method) {
        this.f20296a = method;
    }

    @Override // w8.q
    public final boolean F() {
        Object defaultValue = this.f20296a.getDefaultValue();
        Object tVar = null;
        if (defaultValue != null) {
            Class<?> cls = defaultValue.getClass();
            List list = c.f20266a;
            tVar = Enum.class.isAssignableFrom(cls) ? new t(null, (Enum) defaultValue) : defaultValue instanceof Annotation ? new f(null, (Annotation) defaultValue) : defaultValue instanceof Object[] ? new h(null, (Object[]) defaultValue) : defaultValue instanceof Class ? new p(null, (Class) defaultValue) : new v(null, defaultValue);
        }
        return tVar != null;
    }

    @Override // m8.w
    public final Member H() {
        return this.f20296a;
    }

    @Override // w8.q
    public final List e() {
        Method method = this.f20296a;
        return I(method.getGenericParameterTypes(), method.getParameterAnnotations(), method.isVarArgs());
    }

    @Override // w8.y
    public final ArrayList getTypeParameters() {
        TypeVariable<Method>[] typeParameters = this.f20296a.getTypeParameters();
        ArrayList arrayList = new ArrayList(typeParameters.length);
        for (TypeVariable<Method> typeVariable : typeParameters) {
            arrayList.add(new d0(typeVariable));
        }
        return arrayList;
    }

    @Override // w8.q
    public final c0 y() {
        Type genericReturnType = this.f20296a.getGenericReturnType();
        boolean z = genericReturnType instanceof Class;
        if (z) {
            Class cls = (Class) genericReturnType;
            if (cls.isPrimitive()) {
                return new a0(cls);
            }
        }
        return ((genericReturnType instanceof GenericArrayType) || (z && ((Class) genericReturnType).isArray())) ? new i(genericReturnType) : genericReturnType instanceof WildcardType ? new f0((WildcardType) genericReturnType) : new q(genericReturnType);
    }
}
