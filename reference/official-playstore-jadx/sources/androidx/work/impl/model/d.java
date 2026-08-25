package androidx.work.impl.model;

/* JADX INFO: loaded from: classes3.dex */
public final class d {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final String f6908a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final Long f6909b;

    public d(String str, Long l10) {
        this.f6908a = str;
        this.f6909b = l10;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof d)) {
            return false;
        }
        d dVar = (d) obj;
        return kotlin.jvm.internal.p.a(this.f6908a, dVar.f6908a) && kotlin.jvm.internal.p.a(this.f6909b, dVar.f6909b);
    }

    public final int hashCode() {
        int iHashCode = this.f6908a.hashCode() * 31;
        Long l10 = this.f6909b;
        return iHashCode + (l10 == null ? 0 : l10.hashCode());
    }

    public final String toString() {
        return "Preference(key=" + this.f6908a + ", value=" + this.f6909b + ')';
    }
}
