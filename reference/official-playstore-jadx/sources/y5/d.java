package y5;

import kotlin.jvm.internal.p;

/* JADX INFO: loaded from: classes4.dex */
public final class d {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final Boolean f22885a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final Double f22886b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final Integer f22887c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final Integer f22888d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final Long f22889e;

    public d(Boolean bool, Double d4, Integer num, Integer num2, Long l10) {
        this.f22885a = bool;
        this.f22886b = d4;
        this.f22887c = num;
        this.f22888d = num2;
        this.f22889e = l10;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof d)) {
            return false;
        }
        d dVar = (d) obj;
        return p.a(this.f22885a, dVar.f22885a) && p.a(this.f22886b, dVar.f22886b) && p.a(this.f22887c, dVar.f22887c) && p.a(this.f22888d, dVar.f22888d) && p.a(this.f22889e, dVar.f22889e);
    }

    public final int hashCode() {
        Boolean bool = this.f22885a;
        int iHashCode = (bool == null ? 0 : bool.hashCode()) * 31;
        Double d4 = this.f22886b;
        int iHashCode2 = (iHashCode + (d4 == null ? 0 : d4.hashCode())) * 31;
        Integer num = this.f22887c;
        int iHashCode3 = (iHashCode2 + (num == null ? 0 : num.hashCode())) * 31;
        Integer num2 = this.f22888d;
        int iHashCode4 = (iHashCode3 + (num2 == null ? 0 : num2.hashCode())) * 31;
        Long l10 = this.f22889e;
        return iHashCode4 + (l10 != null ? l10.hashCode() : 0);
    }

    public final String toString() {
        return "SessionConfigs(sessionEnabled=" + this.f22885a + ", sessionSamplingRate=" + this.f22886b + ", sessionRestartTimeout=" + this.f22887c + ", cacheDuration=" + this.f22888d + ", cacheUpdatedTime=" + this.f22889e + ')';
    }
}
