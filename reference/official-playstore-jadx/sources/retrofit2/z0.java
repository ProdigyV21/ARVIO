package retrofit2;

import j$.util.Objects;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.Arrays;

/* JADX INFO: loaded from: classes5.dex */
public final class z0 implements ParameterizedType {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final Type f21619i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final Type f21620l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final Type[] f21621m;

    public z0(Type type, Type type2, Type... typeArr) {
        if (type2 instanceof Class) {
            if ((type == null) != (((Class) type2).getEnclosingClass() == null)) {
                throw new IllegalArgumentException();
            }
        }
        for (Type type3 : typeArr) {
            Objects.requireNonNull(type3, "typeArgument == null");
            b1.b(type3);
        }
        this.f21619i = type;
        this.f21620l = type2;
        this.f21621m = (Type[]) typeArr.clone();
    }

    public final boolean equals(Object obj) {
        return (obj instanceof ParameterizedType) && b1.c(this, (ParameterizedType) obj);
    }

    @Override // java.lang.reflect.ParameterizedType
    public final Type[] getActualTypeArguments() {
        return (Type[]) this.f21621m.clone();
    }

    @Override // java.lang.reflect.ParameterizedType
    public final Type getOwnerType() {
        return this.f21619i;
    }

    @Override // java.lang.reflect.ParameterizedType
    public final Type getRawType() {
        return this.f21620l;
    }

    public final int hashCode() {
        int iHashCode = Arrays.hashCode(this.f21621m) ^ this.f21620l.hashCode();
        Type type = this.f21619i;
        return iHashCode ^ (type != null ? type.hashCode() : 0);
    }

    public final String toString() {
        Type[] typeArr = this.f21621m;
        int length = typeArr.length;
        Type type = this.f21620l;
        if (length == 0) {
            return b1.p(type);
        }
        StringBuilder sb2 = new StringBuilder((typeArr.length + 1) * 30);
        sb2.append(b1.p(type));
        sb2.append("<");
        sb2.append(b1.p(typeArr[0]));
        for (int i10 = 1; i10 < typeArr.length; i10++) {
            sb2.append(", ");
            sb2.append(b1.p(typeArr[i10]));
        }
        sb2.append(">");
        return sb2.toString();
    }
}
