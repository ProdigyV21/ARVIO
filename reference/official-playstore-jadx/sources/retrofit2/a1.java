package retrofit2;

import java.lang.reflect.Type;
import java.lang.reflect.WildcardType;

/* JADX INFO: loaded from: classes5.dex */
public final class a1 implements WildcardType {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final Type f21476i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final Type f21477l;

    public a1(Type[] typeArr, Type[] typeArr2) {
        if (typeArr2.length > 1) {
            throw new IllegalArgumentException();
        }
        if (typeArr.length != 1) {
            throw new IllegalArgumentException();
        }
        if (typeArr2.length != 1) {
            typeArr[0].getClass();
            b1.b(typeArr[0]);
            this.f21477l = null;
            this.f21476i = typeArr[0];
            return;
        }
        typeArr2[0].getClass();
        b1.b(typeArr2[0]);
        if (typeArr[0] != Object.class) {
            throw new IllegalArgumentException();
        }
        this.f21477l = typeArr2[0];
        this.f21476i = Object.class;
    }

    public final boolean equals(Object obj) {
        return (obj instanceof WildcardType) && b1.c(this, (WildcardType) obj);
    }

    @Override // java.lang.reflect.WildcardType
    public final Type[] getLowerBounds() {
        Type type = this.f21477l;
        return type != null ? new Type[]{type} : b1.f21482a;
    }

    @Override // java.lang.reflect.WildcardType
    public final Type[] getUpperBounds() {
        return new Type[]{this.f21476i};
    }

    public final int hashCode() {
        Type type = this.f21477l;
        return (type != null ? type.hashCode() + 31 : 1) ^ (this.f21476i.hashCode() + 31);
    }

    public final String toString() {
        Type type = this.f21477l;
        if (type != null) {
            return "? super " + b1.p(type);
        }
        Type type2 = this.f21476i;
        if (type2 == Object.class) {
            return "?";
        }
        return "? extends " + b1.p(type2);
    }
}
