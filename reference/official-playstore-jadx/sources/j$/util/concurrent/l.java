package j$.util.concurrent;

import java.util.Map;

/* JADX INFO: loaded from: classes2.dex */
public class l implements Map.Entry {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final int f18488a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final Object f18489b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public volatile Object f18490c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public volatile l f18491d;

    public l(int i10, Object obj, Object obj2) {
        this.f18488a = i10;
        this.f18489b = obj;
        this.f18490c = obj2;
    }

    public l(int i10, Object obj, Object obj2, l lVar) {
        this(i10, obj, obj2);
        this.f18491d = lVar;
    }

    @Override // java.util.Map.Entry
    public final Object getKey() {
        return this.f18489b;
    }

    @Override // java.util.Map.Entry
    public final Object getValue() {
        return this.f18490c;
    }

    @Override // java.util.Map.Entry
    public final int hashCode() {
        return this.f18489b.hashCode() ^ this.f18490c.hashCode();
    }

    public final String toString() {
        return j$.com.android.tools.r8.a.T(this.f18489b, this.f18490c);
    }

    @Override // java.util.Map.Entry
    public final Object setValue(Object obj) {
        throw new UnsupportedOperationException();
    }

    @Override // java.util.Map.Entry
    public final boolean equals(Object obj) {
        Map.Entry entry;
        Object key;
        Object value;
        if (!(obj instanceof Map.Entry) || (key = (entry = (Map.Entry) obj).getKey()) == null || (value = entry.getValue()) == null) {
            return false;
        }
        Object obj2 = this.f18489b;
        if (key != obj2 && !key.equals(obj2)) {
            return false;
        }
        Object obj3 = this.f18490c;
        return value == obj3 || value.equals(obj3);
    }

    public l a(Object obj, int i10) {
        Object obj2;
        l lVar = this;
        do {
            if (lVar.f18488a == i10 && ((obj2 = lVar.f18489b) == obj || (obj2 != null && obj.equals(obj2)))) {
                return lVar;
            }
            lVar = lVar.f18491d;
        } while (lVar != null);
        return null;
    }
}
