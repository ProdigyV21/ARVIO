package kotlin.reflect;

import java.lang.reflect.GenericArrayType;
import java.lang.reflect.Type;

/* JADX INFO: loaded from: classes5.dex */
public final class a implements GenericArrayType, x {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final Type f19754i;

    public a(Type type) {
        this.f19754i = type;
    }

    public final boolean equals(Object obj) {
        if (obj instanceof GenericArrayType) {
            return kotlin.jvm.internal.p.a(this.f19754i, ((GenericArrayType) obj).getGenericComponentType());
        }
        return false;
    }

    @Override // java.lang.reflect.GenericArrayType
    public final Type getGenericComponentType() {
        return this.f19754i;
    }

    @Override // java.lang.reflect.Type
    public final String getTypeName() {
        return b0.c(this.f19754i) + "[]";
    }

    public final int hashCode() {
        return this.f19754i.hashCode();
    }

    public final String toString() {
        return getTypeName();
    }
}
