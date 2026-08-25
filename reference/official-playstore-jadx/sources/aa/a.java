package aa;

import kotlin.jvm.internal.p;

/* JADX INFO: loaded from: classes5.dex */
public final class a {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final Object f771a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final Object f772b;

    public a(Object obj, Object obj2) {
        this.f771a = obj;
        this.f772b = obj2;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof a)) {
            return false;
        }
        a aVar = (a) obj;
        return p.a(this.f771a, aVar.f771a) && p.a(this.f772b, aVar.f772b);
    }

    public final int hashCode() {
        Object obj = this.f771a;
        int iHashCode = (obj == null ? 0 : obj.hashCode()) * 31;
        Object obj2 = this.f772b;
        return iHashCode + (obj2 != null ? obj2.hashCode() : 0);
    }

    public final String toString() {
        StringBuilder sb2 = new StringBuilder("ApproximationBounds(lower=");
        sb2.append(this.f771a);
        sb2.append(", upper=");
        return androidx.compose.material3.d.p(sb2, this.f772b, ')');
    }
}
