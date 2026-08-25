package a8;

import java.lang.reflect.Type;
import java.util.Arrays;

/* JADX INFO: loaded from: classes5.dex */
public final class z0 implements Type {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final Type[] f314i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final int f315l;

    public z0(Type[] typeArr) {
        this.f314i = typeArr;
        this.f315l = Arrays.hashCode(typeArr);
    }

    public final boolean equals(Object obj) {
        if (obj instanceof z0) {
            return Arrays.equals(this.f314i, ((z0) obj).f314i);
        }
        return false;
    }

    @Override // java.lang.reflect.Type
    public final String getTypeName() {
        return kotlin.collections.r.f0(this.f314i, ", ", "[", "]", null, 56);
    }

    public final int hashCode() {
        return this.f315l;
    }

    public final String toString() {
        return getTypeName();
    }
}
