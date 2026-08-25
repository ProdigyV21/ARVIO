package j$.time;

import j$.time.chrono.Chronology;
import j$.time.format.DateTimeFormatterBuilder;
import j$.time.temporal.Temporal;
import j$.time.temporal.TemporalAccessor;
import java.io.InvalidObjectException;
import java.io.ObjectInputStream;
import java.io.Serializable;
import java.util.Locale;

/* JADX INFO: loaded from: classes2.dex */
public final class j implements TemporalAccessor, j$.time.temporal.l, Comparable, Serializable {

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public static final /* synthetic */ int f18311c = 0;
    private static final long serialVersionUID = -939150713474957432L;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final int f18312a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final int f18313b;

    @Override // java.lang.Comparable
    public final int compareTo(Object obj) {
        j jVar = (j) obj;
        int i10 = this.f18312a - jVar.f18312a;
        return i10 == 0 ? this.f18313b - jVar.f18313b : i10;
    }

    static {
        DateTimeFormatterBuilder dateTimeFormatterBuilder = new DateTimeFormatterBuilder();
        dateTimeFormatterBuilder.d("--");
        dateTimeFormatterBuilder.k(j$.time.temporal.a.MONTH_OF_YEAR, 2);
        DateTimeFormatterBuilder dateTimeFormatterBuilderAppendLiteral = dateTimeFormatterBuilder.appendLiteral('-');
        dateTimeFormatterBuilderAppendLiteral.k(j$.time.temporal.a.DAY_OF_MONTH, 2);
        dateTimeFormatterBuilderAppendLiteral.toFormatter(Locale.getDefault());
    }

    public j(int i10, int i11) {
        this.f18312a = i10;
        this.f18313b = i11;
    }

    @Override // j$.time.temporal.TemporalAccessor
    public final boolean e(j$.time.temporal.o oVar) {
        return oVar instanceof j$.time.temporal.a ? oVar == j$.time.temporal.a.MONTH_OF_YEAR || oVar == j$.time.temporal.a.DAY_OF_MONTH : oVar != null && oVar.l(this);
    }

    @Override // j$.time.temporal.TemporalAccessor
    public final j$.time.temporal.s n(j$.time.temporal.o oVar) {
        if (oVar == j$.time.temporal.a.MONTH_OF_YEAR) {
            return oVar.r();
        }
        if (oVar != j$.time.temporal.a.DAY_OF_MONTH) {
            return j$.time.temporal.p.d(this, oVar);
        }
        Month monthX = Month.X(this.f18312a);
        monthX.getClass();
        int i10 = h.f18309a[monthX.ordinal()];
        return j$.time.temporal.s.g(1L, i10 != 1 ? (i10 == 2 || i10 == 3 || i10 == 4 || i10 == 5) ? 30 : 31 : 28, Month.X(this.f18312a).W());
    }

    @Override // j$.time.temporal.TemporalAccessor
    public final int l(j$.time.temporal.o oVar) {
        return n(oVar).a(H(oVar), oVar);
    }

    @Override // j$.time.temporal.TemporalAccessor
    public final long H(j$.time.temporal.o oVar) {
        int i10;
        if (!(oVar instanceof j$.time.temporal.a)) {
            return oVar.C(this);
        }
        int i11 = i.f18310a[((j$.time.temporal.a) oVar).ordinal()];
        if (i11 == 1) {
            i10 = this.f18313b;
        } else {
            if (i11 != 2) {
                throw new j$.time.temporal.r(b.a("Unsupported field: ", oVar));
            }
            i10 = this.f18312a;
        }
        return i10;
    }

    @Override // j$.time.temporal.TemporalAccessor
    public final Object G(c cVar) {
        if (cVar == j$.time.temporal.p.f18367b) {
            return j$.time.chrono.p.f18175d;
        }
        return j$.time.temporal.p.c(this, cVar);
    }

    @Override // j$.time.temporal.l
    public final Temporal r(Temporal temporal) {
        if (!Chronology.CC.a(temporal).equals(j$.time.chrono.p.f18175d)) {
            throw new DateTimeException("Adjustment only supported on ISO date-time");
        }
        Temporal temporalC = temporal.c(this.f18312a, j$.time.temporal.a.MONTH_OF_YEAR);
        j$.time.temporal.a aVar = j$.time.temporal.a.DAY_OF_MONTH;
        return temporalC.c(Math.min(temporalC.n(aVar).f18376d, this.f18313b), aVar);
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof j) {
            j jVar = (j) obj;
            if (this.f18312a == jVar.f18312a && this.f18313b == jVar.f18313b) {
                return true;
            }
        }
        return false;
    }

    public final int hashCode() {
        return (this.f18312a << 6) + this.f18313b;
    }

    public final String toString() {
        StringBuilder sb2 = new StringBuilder(10);
        sb2.append("--");
        sb2.append(this.f18312a < 10 ? "0" : "");
        sb2.append(this.f18312a);
        sb2.append(this.f18313b < 10 ? "-0" : "-");
        sb2.append(this.f18313b);
        return sb2.toString();
    }

    private Object writeReplace() {
        return new o((byte) 13, this);
    }

    private void readObject(ObjectInputStream objectInputStream) throws InvalidObjectException {
        throw new InvalidObjectException("Deserialization via serialization delegate");
    }
}
