package j$.time.format;

import androidx.media3.common.C;
import j$.time.DateTimeException;
import j$.time.Instant;
import j$.time.LocalDate;
import j$.time.LocalTime;
import j$.time.ZoneId;
import j$.time.ZoneOffset;
import j$.time.chrono.ChronoLocalDate;
import j$.time.chrono.Chronology;
import j$.time.temporal.TemporalAccessor;
import j$.util.Objects;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/* JADX INFO: loaded from: classes2.dex */
public final class a0 implements TemporalAccessor {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public ZoneId f18224b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public Chronology f18225c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public boolean f18226d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public b0 f18227e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public ChronoLocalDate f18228f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public LocalTime f18229g;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final Map f18223a = new HashMap();

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public j$.time.n f18230h = j$.time.n.f18319d;

    @Override // j$.time.temporal.TemporalAccessor
    public final /* synthetic */ int l(j$.time.temporal.o oVar) {
        return j$.time.temporal.p.a(this, oVar);
    }

    @Override // j$.time.temporal.TemporalAccessor
    public final /* synthetic */ j$.time.temporal.s n(j$.time.temporal.o oVar) {
        return j$.time.temporal.p.d(this, oVar);
    }

    @Override // j$.time.temporal.TemporalAccessor
    public final boolean e(j$.time.temporal.o oVar) {
        if (((HashMap) this.f18223a).containsKey(oVar)) {
            return true;
        }
        ChronoLocalDate chronoLocalDate = this.f18228f;
        if (chronoLocalDate != null && chronoLocalDate.e(oVar)) {
            return true;
        }
        LocalTime localTime = this.f18229g;
        if (localTime == null || !localTime.e(oVar)) {
            return (oVar == null || (oVar instanceof j$.time.temporal.a) || !oVar.l(this)) ? false : true;
        }
        return true;
    }

    @Override // j$.time.temporal.TemporalAccessor
    public final long H(j$.time.temporal.o oVar) {
        Objects.requireNonNull(oVar, "field");
        Long l10 = (Long) ((HashMap) this.f18223a).get(oVar);
        if (l10 != null) {
            return l10.longValue();
        }
        ChronoLocalDate chronoLocalDate = this.f18228f;
        if (chronoLocalDate != null && chronoLocalDate.e(oVar)) {
            return this.f18228f.H(oVar);
        }
        LocalTime localTime = this.f18229g;
        if (localTime != null && localTime.e(oVar)) {
            return this.f18229g.H(oVar);
        }
        if (oVar instanceof j$.time.temporal.a) {
            throw new j$.time.temporal.r(j$.time.b.a("Unsupported field: ", oVar));
        }
        return oVar.C(this);
    }

    @Override // j$.time.temporal.TemporalAccessor
    public final Object G(j$.time.c cVar) {
        if (cVar == j$.time.temporal.p.f18366a) {
            return this.f18224b;
        }
        if (cVar == j$.time.temporal.p.f18367b) {
            return this.f18225c;
        }
        if (cVar == j$.time.temporal.p.f18371f) {
            ChronoLocalDate chronoLocalDate = this.f18228f;
            if (chronoLocalDate != null) {
                return LocalDate.W(chronoLocalDate);
            }
            return null;
        }
        if (cVar == j$.time.temporal.p.f18372g) {
            return this.f18229g;
        }
        if (cVar == j$.time.temporal.p.f18369d) {
            Long l10 = (Long) ((HashMap) this.f18223a).get(j$.time.temporal.a.OFFSET_SECONDS);
            if (l10 != null) {
                return ZoneOffset.d0(l10.intValue());
            }
            ZoneId zoneId = this.f18224b;
            return zoneId instanceof ZoneOffset ? zoneId : cVar.h(this);
        }
        if (cVar == j$.time.temporal.p.f18370e) {
            return cVar.h(this);
        }
        if (cVar == j$.time.temporal.p.f18368c) {
            return null;
        }
        return cVar.h(this);
    }

    public final void w(j$.time.temporal.o oVar, j$.time.temporal.a aVar, Long l10) {
        Long l11 = (Long) ((HashMap) this.f18223a).put(aVar, l10);
        if (l11 == null || l11.longValue() == l10.longValue()) {
            return;
        }
        throw new DateTimeException("Conflict found: " + aVar + " " + l11 + " differs from " + aVar + " " + l10 + " while resolving  " + oVar);
    }

    public final void k() {
        if (((HashMap) this.f18223a).containsKey(j$.time.temporal.a.INSTANT_SECONDS)) {
            ZoneId zoneId = this.f18224b;
            if (zoneId != null) {
                o(zoneId);
                return;
            }
            Long l10 = (Long) ((HashMap) this.f18223a).get(j$.time.temporal.a.OFFSET_SECONDS);
            if (l10 != null) {
                o(ZoneOffset.d0(l10.intValue()));
            }
        }
    }

    public final void o(ZoneId zoneId) {
        Map map = this.f18223a;
        j$.time.temporal.a aVar = j$.time.temporal.a.INSTANT_SECONDS;
        u(this.f18225c.Q(Instant.ofEpochSecond(((Long) ((HashMap) map).remove(aVar)).longValue()), zoneId).f());
        w(aVar, j$.time.temporal.a.SECOND_OF_DAY, Long.valueOf(r5.b().h0()));
    }

    public final void u(ChronoLocalDate chronoLocalDate) {
        ChronoLocalDate chronoLocalDate2 = this.f18228f;
        if (chronoLocalDate2 != null) {
            if (chronoLocalDate == null || chronoLocalDate2.equals(chronoLocalDate)) {
                return;
            }
            throw new DateTimeException("Conflict found: Fields resolved to two different dates: " + this.f18228f + " " + chronoLocalDate);
        }
        if (chronoLocalDate != null) {
            if (!this.f18225c.equals(chronoLocalDate.a())) {
                throw new DateTimeException("ChronoLocalDate must use the effective parsed chronology: " + this.f18225c);
            }
            this.f18228f = chronoLocalDate;
        }
    }

    public final void q() {
        Map map = this.f18223a;
        j$.time.temporal.a aVar = j$.time.temporal.a.CLOCK_HOUR_OF_DAY;
        if (((HashMap) map).containsKey(aVar)) {
            long jLongValue = ((Long) ((HashMap) this.f18223a).remove(aVar)).longValue();
            b0 b0Var = this.f18227e;
            if (b0Var == b0.STRICT || (b0Var == b0.SMART && jLongValue != 0)) {
                aVar.H(jLongValue);
            }
            j$.time.temporal.a aVar2 = j$.time.temporal.a.HOUR_OF_DAY;
            if (jLongValue == 24) {
                jLongValue = 0;
            }
            w(aVar, aVar2, Long.valueOf(jLongValue));
        }
        Map map2 = this.f18223a;
        j$.time.temporal.a aVar3 = j$.time.temporal.a.CLOCK_HOUR_OF_AMPM;
        if (((HashMap) map2).containsKey(aVar3)) {
            long jLongValue2 = ((Long) ((HashMap) this.f18223a).remove(aVar3)).longValue();
            b0 b0Var2 = this.f18227e;
            if (b0Var2 == b0.STRICT || (b0Var2 == b0.SMART && jLongValue2 != 0)) {
                aVar3.H(jLongValue2);
            }
            w(aVar3, j$.time.temporal.a.HOUR_OF_AMPM, Long.valueOf(jLongValue2 != 12 ? jLongValue2 : 0L));
        }
        Map map3 = this.f18223a;
        j$.time.temporal.a aVar4 = j$.time.temporal.a.AMPM_OF_DAY;
        if (((HashMap) map3).containsKey(aVar4)) {
            Map map4 = this.f18223a;
            j$.time.temporal.a aVar5 = j$.time.temporal.a.HOUR_OF_AMPM;
            if (((HashMap) map4).containsKey(aVar5)) {
                long jLongValue3 = ((Long) ((HashMap) this.f18223a).remove(aVar4)).longValue();
                long jLongValue4 = ((Long) ((HashMap) this.f18223a).remove(aVar5)).longValue();
                if (this.f18227e == b0.LENIENT) {
                    w(aVar4, j$.time.temporal.a.HOUR_OF_DAY, Long.valueOf(j$.com.android.tools.r8.a.L(j$.com.android.tools.r8.a.R(jLongValue3, 12), jLongValue4)));
                } else {
                    aVar4.H(jLongValue3);
                    aVar5.H(jLongValue3);
                    w(aVar4, j$.time.temporal.a.HOUR_OF_DAY, Long.valueOf((jLongValue3 * 12) + jLongValue4));
                }
            }
        }
        Map map5 = this.f18223a;
        j$.time.temporal.a aVar6 = j$.time.temporal.a.NANO_OF_DAY;
        if (((HashMap) map5).containsKey(aVar6)) {
            long jLongValue5 = ((Long) ((HashMap) this.f18223a).remove(aVar6)).longValue();
            if (this.f18227e != b0.LENIENT) {
                aVar6.H(jLongValue5);
            }
            w(aVar6, j$.time.temporal.a.HOUR_OF_DAY, Long.valueOf(jLongValue5 / 3600000000000L));
            w(aVar6, j$.time.temporal.a.MINUTE_OF_HOUR, Long.valueOf((jLongValue5 / 60000000000L) % 60));
            w(aVar6, j$.time.temporal.a.SECOND_OF_MINUTE, Long.valueOf((jLongValue5 / C.NANOS_PER_SECOND) % 60));
            w(aVar6, j$.time.temporal.a.NANO_OF_SECOND, Long.valueOf(jLongValue5 % C.NANOS_PER_SECOND));
        }
        Map map6 = this.f18223a;
        j$.time.temporal.a aVar7 = j$.time.temporal.a.MICRO_OF_DAY;
        if (((HashMap) map6).containsKey(aVar7)) {
            long jLongValue6 = ((Long) ((HashMap) this.f18223a).remove(aVar7)).longValue();
            if (this.f18227e != b0.LENIENT) {
                aVar7.H(jLongValue6);
            }
            w(aVar7, j$.time.temporal.a.SECOND_OF_DAY, Long.valueOf(jLongValue6 / 1000000));
            w(aVar7, j$.time.temporal.a.MICRO_OF_SECOND, Long.valueOf(jLongValue6 % 1000000));
        }
        Map map7 = this.f18223a;
        j$.time.temporal.a aVar8 = j$.time.temporal.a.MILLI_OF_DAY;
        if (((HashMap) map7).containsKey(aVar8)) {
            long jLongValue7 = ((Long) ((HashMap) this.f18223a).remove(aVar8)).longValue();
            if (this.f18227e != b0.LENIENT) {
                aVar8.H(jLongValue7);
            }
            w(aVar8, j$.time.temporal.a.SECOND_OF_DAY, Long.valueOf(jLongValue7 / 1000));
            w(aVar8, j$.time.temporal.a.MILLI_OF_SECOND, Long.valueOf(jLongValue7 % 1000));
        }
        Map map8 = this.f18223a;
        j$.time.temporal.a aVar9 = j$.time.temporal.a.SECOND_OF_DAY;
        if (((HashMap) map8).containsKey(aVar9)) {
            long jLongValue8 = ((Long) ((HashMap) this.f18223a).remove(aVar9)).longValue();
            if (this.f18227e != b0.LENIENT) {
                aVar9.H(jLongValue8);
            }
            w(aVar9, j$.time.temporal.a.HOUR_OF_DAY, Long.valueOf(jLongValue8 / 3600));
            w(aVar9, j$.time.temporal.a.MINUTE_OF_HOUR, Long.valueOf((jLongValue8 / 60) % 60));
            w(aVar9, j$.time.temporal.a.SECOND_OF_MINUTE, Long.valueOf(jLongValue8 % 60));
        }
        Map map9 = this.f18223a;
        j$.time.temporal.a aVar10 = j$.time.temporal.a.MINUTE_OF_DAY;
        if (((HashMap) map9).containsKey(aVar10)) {
            long jLongValue9 = ((Long) ((HashMap) this.f18223a).remove(aVar10)).longValue();
            if (this.f18227e != b0.LENIENT) {
                aVar10.H(jLongValue9);
            }
            w(aVar10, j$.time.temporal.a.HOUR_OF_DAY, Long.valueOf(jLongValue9 / 60));
            w(aVar10, j$.time.temporal.a.MINUTE_OF_HOUR, Long.valueOf(jLongValue9 % 60));
        }
        Map map10 = this.f18223a;
        j$.time.temporal.a aVar11 = j$.time.temporal.a.NANO_OF_SECOND;
        if (((HashMap) map10).containsKey(aVar11)) {
            long jLongValue10 = ((Long) ((HashMap) this.f18223a).get(aVar11)).longValue();
            b0 b0Var3 = this.f18227e;
            b0 b0Var4 = b0.LENIENT;
            if (b0Var3 != b0Var4) {
                aVar11.H(jLongValue10);
            }
            Map map11 = this.f18223a;
            j$.time.temporal.a aVar12 = j$.time.temporal.a.MICRO_OF_SECOND;
            if (((HashMap) map11).containsKey(aVar12)) {
                long jLongValue11 = ((Long) ((HashMap) this.f18223a).remove(aVar12)).longValue();
                if (this.f18227e != b0Var4) {
                    aVar12.H(jLongValue11);
                }
                jLongValue10 = (jLongValue10 % 1000) + (jLongValue11 * 1000);
                w(aVar12, aVar11, Long.valueOf(jLongValue10));
            }
            Map map12 = this.f18223a;
            j$.time.temporal.a aVar13 = j$.time.temporal.a.MILLI_OF_SECOND;
            if (((HashMap) map12).containsKey(aVar13)) {
                long jLongValue12 = ((Long) ((HashMap) this.f18223a).remove(aVar13)).longValue();
                if (this.f18227e != b0Var4) {
                    aVar13.H(jLongValue12);
                }
                w(aVar13, aVar11, Long.valueOf((jLongValue10 % 1000000) + (jLongValue12 * 1000000)));
            }
        }
        Map map13 = this.f18223a;
        j$.time.temporal.a aVar14 = j$.time.temporal.a.HOUR_OF_DAY;
        if (((HashMap) map13).containsKey(aVar14)) {
            Map map14 = this.f18223a;
            j$.time.temporal.a aVar15 = j$.time.temporal.a.MINUTE_OF_HOUR;
            if (((HashMap) map14).containsKey(aVar15)) {
                Map map15 = this.f18223a;
                j$.time.temporal.a aVar16 = j$.time.temporal.a.SECOND_OF_MINUTE;
                if (((HashMap) map15).containsKey(aVar16) && ((HashMap) this.f18223a).containsKey(aVar11)) {
                    p(((Long) ((HashMap) this.f18223a).remove(aVar14)).longValue(), ((Long) ((HashMap) this.f18223a).remove(aVar15)).longValue(), ((Long) ((HashMap) this.f18223a).remove(aVar16)).longValue(), ((Long) ((HashMap) this.f18223a).remove(aVar11)).longValue());
                }
            }
        }
    }

    public final void p(long j10, long j11, long j12, long j13) {
        if (this.f18227e == b0.LENIENT) {
            long jL = j$.com.android.tools.r8.a.L(j$.com.android.tools.r8.a.L(j$.com.android.tools.r8.a.L(j$.com.android.tools.r8.a.R(j10, 3600000000000L), j$.com.android.tools.r8.a.R(j11, 60000000000L)), j$.com.android.tools.r8.a.R(j12, C.NANOS_PER_SECOND)), j13);
            r(LocalTime.Z(j$.com.android.tools.r8.a.P(jL, 86400000000000L)), j$.time.n.a(0, 0, (int) j$.com.android.tools.r8.a.Q(jL, 86400000000000L)));
            return;
        }
        j$.time.temporal.a aVar = j$.time.temporal.a.MINUTE_OF_HOUR;
        int iA = aVar.f18349b.a(j11, aVar);
        j$.time.temporal.a aVar2 = j$.time.temporal.a.NANO_OF_SECOND;
        int iA2 = aVar2.f18349b.a(j13, aVar2);
        if (this.f18227e == b0.SMART && j10 == 24 && iA == 0 && j12 == 0 && iA2 == 0) {
            r(LocalTime.MIDNIGHT, j$.time.n.a(0, 0, 1));
            return;
        }
        j$.time.temporal.a aVar3 = j$.time.temporal.a.HOUR_OF_DAY;
        int iA3 = aVar3.f18349b.a(j10, aVar3);
        j$.time.temporal.a aVar4 = j$.time.temporal.a.SECOND_OF_MINUTE;
        r(LocalTime.Y(iA3, iA, aVar4.f18349b.a(j12, aVar4), iA2), j$.time.n.f18319d);
    }

    public final void r(LocalTime localTime, j$.time.n nVar) {
        LocalTime localTime2 = this.f18229g;
        if (localTime2 != null) {
            if (!localTime2.equals(localTime)) {
                throw new DateTimeException("Conflict found: Fields resolved to different times: " + this.f18229g + " " + localTime);
            }
            j$.time.n nVar2 = this.f18230h;
            nVar2.getClass();
            j$.time.n nVar3 = j$.time.n.f18319d;
            if (nVar2 != nVar3 && nVar != nVar3 && !this.f18230h.equals(nVar)) {
                throw new DateTimeException("Conflict found: Fields resolved to different excess periods: " + this.f18230h + " " + nVar);
            }
            this.f18230h = nVar;
            return;
        }
        this.f18229g = localTime;
        this.f18230h = nVar;
    }

    public final void j(TemporalAccessor temporalAccessor) {
        Iterator it = ((HashMap) this.f18223a).entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry entry = (Map.Entry) it.next();
            j$.time.temporal.o oVar = (j$.time.temporal.o) entry.getKey();
            if (temporalAccessor.e(oVar)) {
                try {
                    long jH = temporalAccessor.H(oVar);
                    long jLongValue = ((Long) entry.getValue()).longValue();
                    if (jH != jLongValue) {
                        throw new DateTimeException("Conflict found: Field " + oVar + " " + jH + " differs from " + oVar + " " + jLongValue + " derived from " + temporalAccessor);
                    }
                    it.remove();
                } catch (RuntimeException unused) {
                    continue;
                }
            }
        }
    }

    public final String toString() {
        StringBuilder sb2 = new StringBuilder(64);
        sb2.append(this.f18223a);
        sb2.append(',');
        sb2.append(this.f18225c);
        if (this.f18224b != null) {
            sb2.append(',');
            sb2.append(this.f18224b);
        }
        if (this.f18228f != null || this.f18229g != null) {
            sb2.append(" resolved to ");
            ChronoLocalDate chronoLocalDate = this.f18228f;
            if (chronoLocalDate != null) {
                sb2.append(chronoLocalDate);
                if (this.f18229g != null) {
                    sb2.append('T');
                    sb2.append(this.f18229g);
                }
            } else {
                sb2.append(this.f18229g);
            }
        }
        return sb2.toString();
    }
}
