package k3;

import java.util.HashMap;

/* JADX INFO: loaded from: classes4.dex */
public final class a {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final m3.a f19472a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final HashMap f19473b;

    public a(m3.a aVar, HashMap map) {
        this.f19472a = aVar;
        this.f19473b = map;
    }

    public final long a(z2.c cVar, long j10, int i10) {
        long jA = j10 - this.f19472a.a();
        b bVar = (b) this.f19473b.get(cVar);
        long j11 = bVar.f19474a;
        return Math.min(Math.max((long) (Math.pow(3.0d, i10 - 1) * j11 * Math.max(1.0d, Math.log(10000.0d) / Math.log((j11 > 1 ? j11 : 2L) * ((long) r12)))), jA), bVar.f19475b);
    }

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof a)) {
            return false;
        }
        a aVar = (a) obj;
        return this.f19472a.equals(aVar.f19472a) && this.f19473b.equals(aVar.f19473b);
    }

    public final int hashCode() {
        return ((this.f19472a.hashCode() ^ 1000003) * 1000003) ^ this.f19473b.hashCode();
    }

    public final String toString() {
        return "SchedulerConfig{clock=" + this.f19472a + ", values=" + this.f19473b + "}";
    }
}
