package j6;

import com.squareup.moshi.e0;
import java.lang.reflect.Type;
import java.lang.reflect.WildcardType;

/* JADX INFO: loaded from: classes4.dex */
public final class d implements WildcardType {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final Type f19233i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final Type f19234l;

    public d(Type[] typeArr, Type[] typeArr2) {
        if (typeArr2.length > 1) {
            throw new IllegalArgumentException();
        }
        if (typeArr.length != 1) {
            throw new IllegalArgumentException();
        }
        if (typeArr2.length != 1) {
            typeArr[0].getClass();
            e.b(typeArr[0]);
            this.f19234l = null;
            this.f19233i = e.a(typeArr[0]);
            return;
        }
        typeArr2[0].getClass();
        e.b(typeArr2[0]);
        if (typeArr[0] != Object.class) {
            throw new IllegalArgumentException();
        }
        this.f19234l = e.a(typeArr2[0]);
        this.f19233i = Object.class;
    }

    public final boolean equals(Object obj) {
        return (obj instanceof WildcardType) && e0.b(this, (WildcardType) obj);
    }

    @Override // java.lang.reflect.WildcardType
    public final Type[] getLowerBounds() {
        Type type = this.f19234l;
        return type != null ? new Type[]{type} : e.f19236b;
    }

    @Override // java.lang.reflect.WildcardType
    public final Type[] getUpperBounds() {
        return new Type[]{this.f19233i};
    }

    public final int hashCode() {
        Type type = this.f19234l;
        return (type != null ? type.hashCode() + 31 : 1) ^ (this.f19233i.hashCode() + 31);
    }

    public final String toString() {
        Type type = this.f19234l;
        if (type != null) {
            return "? super " + e.j(type);
        }
        Type type2 = this.f19233i;
        if (type2 == Object.class) {
            return "?";
        }
        return "? extends " + e.j(type2);
    }
}
