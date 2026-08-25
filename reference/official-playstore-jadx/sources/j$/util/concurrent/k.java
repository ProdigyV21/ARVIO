package j$.util.concurrent;

import java.util.Map;

/* JADX INFO: loaded from: classes2.dex */
public final class k implements Map.Entry {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final Object f18485a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public Object f18486b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final ConcurrentHashMap f18487c;

    public k(Object obj, Object obj2, ConcurrentHashMap concurrentHashMap) {
        this.f18485a = obj;
        this.f18486b = obj2;
        this.f18487c = concurrentHashMap;
    }

    @Override // java.util.Map.Entry
    public final Object getKey() {
        return this.f18485a;
    }

    @Override // java.util.Map.Entry
    public final Object getValue() {
        return this.f18486b;
    }

    @Override // java.util.Map.Entry
    public final int hashCode() {
        return this.f18485a.hashCode() ^ this.f18486b.hashCode();
    }

    public final String toString() {
        return j$.com.android.tools.r8.a.T(this.f18485a, this.f18486b);
    }

    @Override // java.util.Map.Entry
    public final boolean equals(Object obj) {
        Map.Entry entry;
        Object key;
        Object value;
        if (!(obj instanceof Map.Entry) || (key = (entry = (Map.Entry) obj).getKey()) == null || (value = entry.getValue()) == null) {
            return false;
        }
        Object obj2 = this.f18485a;
        if (key != obj2 && !key.equals(obj2)) {
            return false;
        }
        Object obj3 = this.f18486b;
        return value == obj3 || value.equals(obj3);
    }

    @Override // java.util.Map.Entry
    public final Object setValue(Object obj) {
        obj.getClass();
        Object obj2 = this.f18486b;
        this.f18486b = obj;
        this.f18487c.put(this.f18485a, obj);
        return obj2;
    }
}
