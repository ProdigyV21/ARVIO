package m8;

import java.lang.reflect.Field;
import java.lang.reflect.GenericArrayType;
import java.lang.reflect.Member;
import java.lang.reflect.Type;
import java.lang.reflect.WildcardType;

/* JADX INFO: loaded from: classes5.dex */
public final class u extends w implements w8.n {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final Field f20294a;

    public u(Field field) {
        this.f20294a = field;
    }

    @Override // w8.n
    public final boolean C() {
        return this.f20294a.isEnumConstant();
    }

    @Override // m8.w
    public final Member H() {
        return this.f20294a;
    }

    @Override // w8.n
    public final w8.w getType() {
        Type genericType = this.f20294a.getGenericType();
        boolean z = genericType instanceof Class;
        if (z) {
            Class cls = (Class) genericType;
            if (cls.isPrimitive()) {
                return new a0(cls);
            }
        }
        return ((genericType instanceof GenericArrayType) || (z && ((Class) genericType).isArray())) ? new i(genericType) : genericType instanceof WildcardType ? new f0((WildcardType) genericType) : new q(genericType);
    }
}
