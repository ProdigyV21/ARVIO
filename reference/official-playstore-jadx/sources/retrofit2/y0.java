package retrofit2;

import java.lang.reflect.GenericArrayType;
import java.lang.reflect.Type;

/* JADX INFO: loaded from: classes5.dex */
public final class y0 implements GenericArrayType {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final Type f21617i;

    public y0(Type type) {
        this.f21617i = type;
    }

    public final boolean equals(Object obj) {
        return (obj instanceof GenericArrayType) && b1.c(this, (GenericArrayType) obj);
    }

    @Override // java.lang.reflect.GenericArrayType
    public final Type getGenericComponentType() {
        return this.f21617i;
    }

    public final int hashCode() {
        return this.f21617i.hashCode();
    }

    public final String toString() {
        return b1.p(this.f21617i) + "[]";
    }
}
