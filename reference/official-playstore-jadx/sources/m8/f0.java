package m8;

import java.lang.reflect.GenericArrayType;
import java.lang.reflect.Type;
import java.lang.reflect.WildcardType;
import java.util.Collection;

/* JADX INFO: loaded from: classes5.dex */
public final class f0 extends c0 implements w8.a0 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final WildcardType f20278a;

    public f0(WildcardType wildcardType) {
        this.f20278a = wildcardType;
    }

    @Override // w8.a0
    public final boolean E() {
        return !kotlin.jvm.internal.p.a(kotlin.collections.r.Z(this.f20278a.getUpperBounds()), Object.class);
    }

    @Override // m8.c0
    public final Type H() {
        return this.f20278a;
    }

    @Override // w8.d
    public final Collection getAnnotations() {
        return kotlin.collections.z.f19728i;
    }

    @Override // w8.a0
    public final c0 p() {
        WildcardType wildcardType = this.f20278a;
        Type[] upperBounds = wildcardType.getUpperBounds();
        Type[] lowerBounds = wildcardType.getLowerBounds();
        if (upperBounds.length > 1 || lowerBounds.length > 1) {
            throw new UnsupportedOperationException("Wildcard types with many bounds are not yet supported: " + wildcardType);
        }
        if (lowerBounds.length == 1) {
            Type type = (Type) kotlin.collections.r.j0(lowerBounds);
            boolean z = type instanceof Class;
            if (z) {
                Class cls = (Class) type;
                if (cls.isPrimitive()) {
                    return new a0(cls);
                }
            }
            return ((type instanceof GenericArrayType) || (z && ((Class) type).isArray())) ? new i(type) : type instanceof WildcardType ? new f0((WildcardType) type) : new q(type);
        }
        if (upperBounds.length != 1) {
            return null;
        }
        Type type2 = (Type) kotlin.collections.r.j0(upperBounds);
        if (kotlin.jvm.internal.p.a(type2, Object.class)) {
            return null;
        }
        boolean z5 = type2 instanceof Class;
        if (z5) {
            Class cls2 = (Class) type2;
            if (cls2.isPrimitive()) {
                return new a0(cls2);
            }
        }
        return ((type2 instanceof GenericArrayType) || (z5 && ((Class) type2).isArray())) ? new i(type2) : type2 instanceof WildcardType ? new f0((WildcardType) type2) : new q(type2);
    }
}
