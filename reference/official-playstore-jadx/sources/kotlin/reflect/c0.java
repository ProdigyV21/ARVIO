package kotlin.reflect;

import java.lang.reflect.Type;
import java.lang.reflect.WildcardType;
import java.util.Arrays;

/* JADX INFO: loaded from: classes5.dex */
public final class c0 implements WildcardType, x {

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public static final c0 f19756m = new c0(null, null);

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final Type f19757i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final Type f19758l;

    public c0(Type type, Type type2) {
        this.f19757i = type;
        this.f19758l = type2;
    }

    public final boolean equals(Object obj) {
        if (!(obj instanceof WildcardType)) {
            return false;
        }
        WildcardType wildcardType = (WildcardType) obj;
        return Arrays.equals(getUpperBounds(), wildcardType.getUpperBounds()) && Arrays.equals(getLowerBounds(), wildcardType.getLowerBounds());
    }

    @Override // java.lang.reflect.WildcardType
    public final Type[] getLowerBounds() {
        Type type = this.f19758l;
        return type == null ? new Type[0] : new Type[]{type};
    }

    @Override // java.lang.reflect.Type
    public final String getTypeName() {
        Type type = this.f19758l;
        if (type != null) {
            return "? super " + b0.c(type);
        }
        Type type2 = this.f19757i;
        if (type2 == null || kotlin.jvm.internal.p.a(type2, Object.class)) {
            return "?";
        }
        return "? extends " + b0.c(type2);
    }

    @Override // java.lang.reflect.WildcardType
    public final Type[] getUpperBounds() {
        Type type = this.f19757i;
        if (type == null) {
            type = Object.class;
        }
        return new Type[]{type};
    }

    public final int hashCode() {
        return Arrays.hashCode(getUpperBounds()) ^ Arrays.hashCode(getLowerBounds());
    }

    public final String toString() {
        return getTypeName();
    }
}
