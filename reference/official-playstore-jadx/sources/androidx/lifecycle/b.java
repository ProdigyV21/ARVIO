package androidx.lifecycle;

import java.lang.reflect.Method;

/* JADX INFO: loaded from: classes3.dex */
public final class b {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final int f3209a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final Method f3210b;

    public b(Method method, int i10) {
        this.f3209a = i10;
        this.f3210b = method;
        method.setAccessible(true);
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof b)) {
            return false;
        }
        b bVar = (b) obj;
        return this.f3209a == bVar.f3209a && this.f3210b.getName().equals(bVar.f3210b.getName());
    }

    public final int hashCode() {
        return this.f3210b.getName().hashCode() + (this.f3209a * 31);
    }
}
