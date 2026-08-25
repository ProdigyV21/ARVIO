package m8;

import java.lang.reflect.GenericArrayType;
import java.lang.reflect.Type;
import java.lang.reflect.WildcardType;
import java.util.Collection;

/* JADX INFO: loaded from: classes5.dex */
public final class i extends c0 implements w8.f {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final Type f20280a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final c0 f20281b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final kotlin.collections.z f20282c;

    /* JADX WARN: Multi-variable type inference failed */
    public i(Type type) {
        c0 a0Var;
        c0 a0Var2;
        this.f20280a = type;
        if (!(type instanceof GenericArrayType)) {
            if (type instanceof Class) {
                Class cls = (Class) type;
                if (cls.isArray()) {
                    Class<?> componentType = cls.getComponentType();
                    a0Var = componentType.isPrimitive() ? new a0(componentType) : ((componentType instanceof GenericArrayType) || componentType.isArray()) ? new i(componentType) : componentType instanceof WildcardType ? new f0((WildcardType) componentType) : new q(componentType);
                }
            }
            throw new IllegalArgumentException("Not an array type (" + type.getClass() + "): " + type);
        }
        Type genericComponentType = ((GenericArrayType) type).getGenericComponentType();
        boolean z = genericComponentType instanceof Class;
        if (z) {
            Class cls2 = (Class) genericComponentType;
            if (cls2.isPrimitive()) {
                a0Var2 = new a0(cls2);
                this.f20281b = a0Var2;
                this.f20282c = kotlin.collections.z.f19728i;
            }
        }
        a0Var = ((genericComponentType instanceof GenericArrayType) || (z && ((Class) genericComponentType).isArray())) ? new i(genericComponentType) : genericComponentType instanceof WildcardType ? new f0((WildcardType) genericComponentType) : new q(genericComponentType);
        a0Var2 = a0Var;
        this.f20281b = a0Var2;
        this.f20282c = kotlin.collections.z.f19728i;
    }

    @Override // m8.c0
    public final Type H() {
        return this.f20280a;
    }

    @Override // w8.d
    public final Collection getAnnotations() {
        return this.f20282c;
    }

    @Override // w8.f
    public final c0 v() {
        return this.f20281b;
    }
}
