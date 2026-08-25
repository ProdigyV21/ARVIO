package j6;

import com.squareup.moshi.e0;
import java.lang.reflect.GenericArrayType;
import java.lang.reflect.Type;

/* JADX INFO: loaded from: classes4.dex */
public final class b implements GenericArrayType {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final Type f19229i;

    public b(Type type) {
        this.f19229i = e.a(type);
    }

    public final boolean equals(Object obj) {
        return (obj instanceof GenericArrayType) && e0.b(this, (GenericArrayType) obj);
    }

    @Override // java.lang.reflect.GenericArrayType
    public final Type getGenericComponentType() {
        return this.f19229i;
    }

    public final int hashCode() {
        return this.f19229i.hashCode();
    }

    public final String toString() {
        return e.j(this.f19229i) + "[]";
    }
}
