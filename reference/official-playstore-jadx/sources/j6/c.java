package j6;

import com.squareup.moshi.e0;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.Arrays;
import java.util.Set;

/* JADX INFO: loaded from: classes4.dex */
public final class c implements ParameterizedType {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final Type f19230i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final Type f19231l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final Type[] f19232m;

    public c(Type type, Type type2, Type... typeArr) {
        if (type2 instanceof Class) {
            Class<?> enclosingClass = ((Class) type2).getEnclosingClass();
            if (type != null) {
                if (enclosingClass == null || e0.d(type) != enclosingClass) {
                    throw new IllegalArgumentException("unexpected owner type for " + type2 + ": " + type);
                }
            } else if (enclosingClass != null) {
                throw new IllegalArgumentException("unexpected owner type for " + type2 + ": null");
            }
        }
        this.f19230i = type == null ? null : e.a(type);
        this.f19231l = e.a(type2);
        this.f19232m = (Type[]) typeArr.clone();
        int i10 = 0;
        while (true) {
            Type[] typeArr2 = this.f19232m;
            if (i10 >= typeArr2.length) {
                return;
            }
            typeArr2[i10].getClass();
            e.b(this.f19232m[i10]);
            Type[] typeArr3 = this.f19232m;
            typeArr3[i10] = e.a(typeArr3[i10]);
            i10++;
        }
    }

    public final boolean equals(Object obj) {
        return (obj instanceof ParameterizedType) && e0.b(this, (ParameterizedType) obj);
    }

    @Override // java.lang.reflect.ParameterizedType
    public final Type[] getActualTypeArguments() {
        return (Type[]) this.f19232m.clone();
    }

    @Override // java.lang.reflect.ParameterizedType
    public final Type getOwnerType() {
        return this.f19230i;
    }

    @Override // java.lang.reflect.ParameterizedType
    public final Type getRawType() {
        return this.f19231l;
    }

    public final int hashCode() {
        int iHashCode = Arrays.hashCode(this.f19232m) ^ this.f19231l.hashCode();
        Set set = e.f19235a;
        Type type = this.f19230i;
        return iHashCode ^ (type != null ? type.hashCode() : 0);
    }

    public final String toString() {
        Type[] typeArr = this.f19232m;
        StringBuilder sb2 = new StringBuilder((typeArr.length + 1) * 30);
        sb2.append(e.j(this.f19231l));
        if (typeArr.length == 0) {
            return sb2.toString();
        }
        sb2.append("<");
        sb2.append(e.j(typeArr[0]));
        for (int i10 = 1; i10 < typeArr.length; i10++) {
            sb2.append(", ");
            sb2.append(e.j(typeArr[i10]));
        }
        sb2.append(">");
        return sb2.toString();
    }
}
