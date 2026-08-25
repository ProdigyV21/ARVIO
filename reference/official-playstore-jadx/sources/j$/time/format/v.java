package j$.time.format;

import j$.time.ZoneId;
import j$.time.chrono.ChronoLocalDate;
import j$.time.chrono.Chronology;
import j$.time.temporal.TemporalAccessor;

/* JADX INFO: loaded from: classes2.dex */
public final class v implements TemporalAccessor {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final /* synthetic */ ChronoLocalDate f18295a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final /* synthetic */ TemporalAccessor f18296b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final /* synthetic */ Chronology f18297c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final /* synthetic */ ZoneId f18298d;

    @Override // j$.time.temporal.TemporalAccessor
    public final /* synthetic */ int l(j$.time.temporal.o oVar) {
        return j$.time.temporal.p.a(this, oVar);
    }

    public v(ChronoLocalDate chronoLocalDate, TemporalAccessor temporalAccessor, Chronology chronology, ZoneId zoneId) {
        this.f18295a = chronoLocalDate;
        this.f18296b = temporalAccessor;
        this.f18297c = chronology;
        this.f18298d = zoneId;
    }

    @Override // j$.time.temporal.TemporalAccessor
    public final boolean e(j$.time.temporal.o oVar) {
        ChronoLocalDate chronoLocalDate = this.f18295a;
        if (chronoLocalDate != null && oVar.isDateBased()) {
            return chronoLocalDate.e(oVar);
        }
        return this.f18296b.e(oVar);
    }

    @Override // j$.time.temporal.TemporalAccessor
    public final j$.time.temporal.s n(j$.time.temporal.o oVar) {
        ChronoLocalDate chronoLocalDate = this.f18295a;
        if (chronoLocalDate != null && oVar.isDateBased()) {
            return chronoLocalDate.n(oVar);
        }
        return this.f18296b.n(oVar);
    }

    @Override // j$.time.temporal.TemporalAccessor
    public final long H(j$.time.temporal.o oVar) {
        ChronoLocalDate chronoLocalDate = this.f18295a;
        if (chronoLocalDate != null && oVar.isDateBased()) {
            return chronoLocalDate.H(oVar);
        }
        return this.f18296b.H(oVar);
    }

    @Override // j$.time.temporal.TemporalAccessor
    public final Object G(j$.time.c cVar) {
        if (cVar == j$.time.temporal.p.f18367b) {
            return this.f18297c;
        }
        if (cVar == j$.time.temporal.p.f18366a) {
            return this.f18298d;
        }
        if (cVar == j$.time.temporal.p.f18368c) {
            return this.f18296b.G(cVar);
        }
        return cVar.h(this);
    }

    public final String toString() {
        String str;
        String str2 = "";
        Chronology chronology = this.f18297c;
        if (chronology != null) {
            str = " with chronology " + chronology;
        } else {
            str = "";
        }
        ZoneId zoneId = this.f18298d;
        if (zoneId != null) {
            str2 = " with zone " + zoneId;
        }
        return this.f18296b + str + str2;
    }
}
