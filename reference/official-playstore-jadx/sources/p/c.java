package p;

import java.util.Map;

/* JADX INFO: loaded from: classes.dex */
public final class c implements Map.Entry {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final Object f21025i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final Object f21026l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public c f21027m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public c f21028n;

    public c(Object obj, Object obj2) {
        this.f21025i = obj;
        this.f21026l = obj2;
    }

    @Override // java.util.Map.Entry
    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof c)) {
            return false;
        }
        c cVar = (c) obj;
        return this.f21025i.equals(cVar.f21025i) && this.f21026l.equals(cVar.f21026l);
    }

    @Override // java.util.Map.Entry
    public final Object getKey() {
        return this.f21025i;
    }

    @Override // java.util.Map.Entry
    public final Object getValue() {
        return this.f21026l;
    }

    @Override // java.util.Map.Entry
    public final int hashCode() {
        return this.f21025i.hashCode() ^ this.f21026l.hashCode();
    }

    @Override // java.util.Map.Entry
    public final Object setValue(Object obj) {
        throw new UnsupportedOperationException("An entry modification is not supported");
    }

    public final String toString() {
        return this.f21025i + "=" + this.f21026l;
    }
}
