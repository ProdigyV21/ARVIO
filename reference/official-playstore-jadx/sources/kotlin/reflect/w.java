package kotlin.reflect;

import java.io.IOException;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Arrays;

/* JADX INFO: loaded from: classes5.dex */
public final class w implements ParameterizedType, x {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final Class f19919i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final Type f19920l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final Type[] f19921m;

    public w(Class cls, Type type, ArrayList arrayList) {
        this.f19919i = cls;
        this.f19920l = type;
        this.f19921m = (Type[]) arrayList.toArray(new Type[0]);
    }

    public final boolean equals(Object obj) {
        if (!(obj instanceof ParameterizedType)) {
            return false;
        }
        ParameterizedType parameterizedType = (ParameterizedType) obj;
        return kotlin.jvm.internal.p.a(this.f19919i, parameterizedType.getRawType()) && kotlin.jvm.internal.p.a(this.f19920l, parameterizedType.getOwnerType()) && Arrays.equals(this.f19921m, parameterizedType.getActualTypeArguments());
    }

    @Override // java.lang.reflect.ParameterizedType
    public final Type[] getActualTypeArguments() {
        return this.f19921m;
    }

    @Override // java.lang.reflect.ParameterizedType
    public final Type getOwnerType() {
        return this.f19920l;
    }

    @Override // java.lang.reflect.ParameterizedType
    public final Type getRawType() {
        return this.f19919i;
    }

    @Override // java.lang.reflect.Type
    public final String getTypeName() throws IOException {
        StringBuilder sb2 = new StringBuilder();
        Class cls = this.f19919i;
        Type type = this.f19920l;
        if (type != null) {
            sb2.append(b0.c(type));
            sb2.append("$");
            sb2.append(cls.getSimpleName());
        } else {
            sb2.append(b0.c(cls));
        }
        Type[] typeArr = this.f19921m;
        if (typeArr.length != 0) {
            kotlin.collections.r.e0(typeArr, sb2, ", ", "<", ">", v.f19918i);
        }
        return sb2.toString();
    }

    public final int hashCode() {
        int iHashCode = this.f19919i.hashCode();
        Type type = this.f19920l;
        return (iHashCode ^ (type != null ? type.hashCode() : 0)) ^ Arrays.hashCode(this.f19921m);
    }

    public final String toString() {
        return getTypeName();
    }
}
