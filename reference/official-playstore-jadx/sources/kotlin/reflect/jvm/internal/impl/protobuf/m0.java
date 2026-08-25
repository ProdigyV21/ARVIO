package kotlin.reflect.jvm.internal.impl.protobuf;

import java.util.Map;

/* JADX INFO: loaded from: classes5.dex */
public final class m0 implements Comparable, Map.Entry {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final Comparable f19808i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public Object f19809l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ i0 f19810m;

    public m0(i0 i0Var, Comparable comparable, Object obj) {
        this.f19810m = i0Var;
        this.f19808i = comparable;
        this.f19809l = obj;
    }

    @Override // java.lang.Comparable
    public final int compareTo(Object obj) {
        return this.f19808i.compareTo(((m0) obj).f19808i);
    }

    @Override // java.util.Map.Entry
    public final boolean equals(Object obj) {
        if (obj != this) {
            if (obj instanceof Map.Entry) {
                Map.Entry entry = (Map.Entry) obj;
                Object key = entry.getKey();
                Comparable comparable = this.f19808i;
                if (comparable == null ? key == null : comparable.equals(key)) {
                    Object obj2 = this.f19809l;
                    Object value = entry.getValue();
                    if (obj2 == null ? value == null : obj2.equals(value)) {
                    }
                }
            }
            return false;
        }
        return true;
    }

    @Override // java.util.Map.Entry
    public final Object getKey() {
        return this.f19808i;
    }

    @Override // java.util.Map.Entry
    public final Object getValue() {
        return this.f19809l;
    }

    @Override // java.util.Map.Entry
    public final int hashCode() {
        Comparable comparable = this.f19808i;
        int iHashCode = comparable == null ? 0 : comparable.hashCode();
        Object obj = this.f19809l;
        return (obj != null ? obj.hashCode() : 0) ^ iHashCode;
    }

    @Override // java.util.Map.Entry
    public final Object setValue(Object obj) {
        this.f19810m.c();
        Object obj2 = this.f19809l;
        this.f19809l = obj;
        return obj2;
    }

    public final String toString() {
        String strValueOf = String.valueOf(this.f19808i);
        String strValueOf2 = String.valueOf(this.f19809l);
        return androidx.compose.material3.d.q(new StringBuilder(strValueOf2.length() + strValueOf.length() + 1), strValueOf, "=", strValueOf2);
    }
}
