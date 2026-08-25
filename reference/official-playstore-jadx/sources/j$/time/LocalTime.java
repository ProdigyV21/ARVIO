package j$.time;

import androidx.media3.common.C;
import j$.time.temporal.ChronoUnit;
import j$.time.temporal.Temporal;
import j$.time.temporal.TemporalAccessor;
import j$.util.Objects;
import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;
import java.io.InvalidObjectException;
import java.io.ObjectInputStream;
import java.io.Serializable;

/* JADX INFO: loaded from: classes2.dex */
public final class LocalTime implements Temporal, j$.time.temporal.l, Comparable<LocalTime>, Serializable {
    public static final LocalTime MIDNIGHT;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public static final LocalTime f18109e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public static final LocalTime f18110f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public static final LocalTime[] f18111g = new LocalTime[24];
    private static final long serialVersionUID = 6414437269572265201L;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final byte f18112a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final byte f18113b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final byte f18114c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final int f18115d;

    static {
        int i10 = 0;
        while (true) {
            LocalTime[] localTimeArr = f18111g;
            if (i10 < localTimeArr.length) {
                localTimeArr[i10] = new LocalTime(i10, 0, 0, 0);
                i10++;
            } else {
                LocalTime localTime = localTimeArr[0];
                MIDNIGHT = localTime;
                LocalTime localTime2 = localTimeArr[12];
                f18109e = localTime;
                f18110f = new LocalTime(23, 59, 59, 999999999);
                return;
            }
        }
    }

    public static LocalTime now() {
        a aVarB = Clock.b();
        Objects.requireNonNull(aVarB, "clock");
        Instant instant = aVarB.instant();
        ZoneId zoneId = aVarB.f18132a;
        Objects.requireNonNull(instant, "instant");
        Objects.requireNonNull(zoneId, "zone");
        return Z((((long) ((int) j$.com.android.tools.r8.a.P(instant.getEpochSecond() + ((long) zoneId.V().d(instant).f18126b), 86400))) * C.NANOS_PER_SECOND) + ((long) instant.getNano()));
    }

    public static LocalTime Y(int i10, int i11, int i12, int i13) {
        j$.time.temporal.a.HOUR_OF_DAY.H(i10);
        j$.time.temporal.a.MINUTE_OF_HOUR.H(i11);
        j$.time.temporal.a.SECOND_OF_MINUTE.H(i12);
        j$.time.temporal.a.NANO_OF_SECOND.H(i13);
        return V(i10, i11, i12, i13);
    }

    public static LocalTime Z(long j10) {
        j$.time.temporal.a.NANO_OF_DAY.H(j10);
        int i10 = (int) (j10 / 3600000000000L);
        long j11 = j10 - (((long) i10) * 3600000000000L);
        int i11 = (int) (j11 / 60000000000L);
        long j12 = j11 - (((long) i11) * 60000000000L);
        int i12 = (int) (j12 / C.NANOS_PER_SECOND);
        return V(i10, i11, i12, (int) (j12 - (((long) i12) * C.NANOS_PER_SECOND)));
    }

    public static LocalTime W(TemporalAccessor temporalAccessor) {
        Objects.requireNonNull(temporalAccessor, "temporal");
        LocalTime localTime = (LocalTime) temporalAccessor.G(j$.time.temporal.p.f18372g);
        if (localTime != null) {
            return localTime;
        }
        throw new DateTimeException("Unable to obtain LocalTime from TemporalAccessor: " + temporalAccessor + " of type " + temporalAccessor.getClass().getName());
    }

    public static LocalTime V(int i10, int i11, int i12, int i13) {
        if ((i11 | i12 | i13) == 0) {
            return f18111g[i10];
        }
        return new LocalTime(i10, i11, i12, i13);
    }

    public LocalTime(int i10, int i11, int i12, int i13) {
        this.f18112a = (byte) i10;
        this.f18113b = (byte) i11;
        this.f18114c = (byte) i12;
        this.f18115d = i13;
    }

    @Override // j$.time.temporal.TemporalAccessor
    public final boolean e(j$.time.temporal.o oVar) {
        if (oVar instanceof j$.time.temporal.a) {
            return ((j$.time.temporal.a) oVar).U();
        }
        return oVar != null && oVar.l(this);
    }

    @Override // j$.time.temporal.TemporalAccessor
    public final j$.time.temporal.s n(j$.time.temporal.o oVar) {
        return j$.time.temporal.p.d(this, oVar);
    }

    @Override // j$.time.temporal.TemporalAccessor
    public final int l(j$.time.temporal.o oVar) {
        if (oVar instanceof j$.time.temporal.a) {
            return X(oVar);
        }
        return j$.time.temporal.p.a(this, oVar);
    }

    @Override // j$.time.temporal.TemporalAccessor
    public final long H(j$.time.temporal.o oVar) {
        if (oVar instanceof j$.time.temporal.a) {
            if (oVar == j$.time.temporal.a.NANO_OF_DAY) {
                return g0();
            }
            if (oVar == j$.time.temporal.a.MICRO_OF_DAY) {
                return g0() / 1000;
            }
            return X(oVar);
        }
        return oVar.C(this);
    }

    public final int X(j$.time.temporal.o oVar) {
        switch (g.f18307a[((j$.time.temporal.a) oVar).ordinal()]) {
            case 1:
                return this.f18115d;
            case 2:
                throw new j$.time.temporal.r("Invalid field 'NanoOfDay' for get() method, use getLong() instead");
            case 3:
                return this.f18115d / 1000;
            case 4:
                throw new j$.time.temporal.r("Invalid field 'MicroOfDay' for get() method, use getLong() instead");
            case 5:
                return this.f18115d / 1000000;
            case 6:
                return (int) (g0() / 1000000);
            case 7:
                return this.f18114c;
            case 8:
                return h0();
            case 9:
                return this.f18113b;
            case 10:
                return (this.f18112a * 60) + this.f18113b;
            case 11:
                return this.f18112a % 12;
            case 12:
                int i10 = this.f18112a % 12;
                if (i10 % 12 == 0) {
                    return 12;
                }
                return i10;
            case 13:
                return this.f18112a;
            case 14:
                byte b10 = this.f18112a;
                if (b10 == 0) {
                    return 24;
                }
                return b10;
            case 15:
                return this.f18112a / 12;
            default:
                throw new j$.time.temporal.r(b.a("Unsupported field: ", oVar));
        }
    }

    @Override // j$.time.temporal.Temporal
    public final Temporal m(LocalDate localDate) {
        localDate.getClass();
        return (LocalTime) j$.com.android.tools.r8.a.a(localDate, this);
    }

    @Override // j$.time.temporal.Temporal
    /* JADX INFO: renamed from: i0, reason: merged with bridge method [inline-methods] */
    public final LocalTime c(long j10, j$.time.temporal.o oVar) {
        if (!(oVar instanceof j$.time.temporal.a)) {
            return (LocalTime) oVar.G(this, j10);
        }
        j$.time.temporal.a aVar = (j$.time.temporal.a) oVar;
        aVar.H(j10);
        switch (g.f18307a[aVar.ordinal()]) {
            case 1:
                return j0((int) j10);
            case 2:
                return Z(j10);
            case 3:
                return j0(((int) j10) * 1000);
            case 4:
                return Z(j10 * 1000);
            case 5:
                return j0(((int) j10) * 1000000);
            case 6:
                return Z(j10 * 1000000);
            case 7:
                int i10 = (int) j10;
                if (this.f18114c != i10) {
                    j$.time.temporal.a.SECOND_OF_MINUTE.H(i10);
                    return V(this.f18112a, this.f18113b, i10, this.f18115d);
                }
                return this;
            case 8:
                return e0(j10 - ((long) h0()));
            case 9:
                int i11 = (int) j10;
                if (this.f18113b != i11) {
                    j$.time.temporal.a.MINUTE_OF_HOUR.H(i11);
                    return V(this.f18112a, i11, this.f18114c, this.f18115d);
                }
                return this;
            case 10:
                return c0(j10 - ((long) ((this.f18112a * 60) + this.f18113b)));
            case 11:
                return b0(j10 - ((long) (this.f18112a % 12)));
            case 12:
                if (j10 == 12) {
                    j10 = 0;
                }
                return b0(j10 - ((long) (this.f18112a % 12)));
            case 13:
                int i12 = (int) j10;
                if (this.f18112a != i12) {
                    j$.time.temporal.a.HOUR_OF_DAY.H(i12);
                    return V(i12, this.f18113b, this.f18114c, this.f18115d);
                }
                return this;
            case 14:
                if (j10 == 24) {
                    j10 = 0;
                }
                int i13 = (int) j10;
                if (this.f18112a != i13) {
                    j$.time.temporal.a.HOUR_OF_DAY.H(i13);
                    return V(i13, this.f18113b, this.f18114c, this.f18115d);
                }
                return this;
            case 15:
                return b0((j10 - ((long) (this.f18112a / 12))) * 12);
            default:
                throw new j$.time.temporal.r(b.a("Unsupported field: ", oVar));
        }
    }

    public final LocalTime j0(int i10) {
        if (this.f18115d == i10) {
            return this;
        }
        j$.time.temporal.a.NANO_OF_SECOND.H(i10);
        return V(this.f18112a, this.f18113b, this.f18114c, i10);
    }

    @Override // j$.time.temporal.Temporal
    /* JADX INFO: renamed from: a0, reason: merged with bridge method [inline-methods] */
    public final LocalTime d(long j10, j$.time.temporal.q qVar) {
        if (qVar instanceof ChronoUnit) {
            switch (g.f18308b[((ChronoUnit) qVar).ordinal()]) {
                case 1:
                    return d0(j10);
                case 2:
                    return d0((j10 % 86400000000L) * 1000);
                case 3:
                    return d0((j10 % 86400000) * 1000000);
                case 4:
                    return e0(j10);
                case 5:
                    return c0(j10);
                case 6:
                    return b0(j10);
                case 7:
                    return b0((j10 % 2) * 12);
                default:
                    throw new j$.time.temporal.r("Unsupported unit: " + qVar);
            }
        }
        return (LocalTime) qVar.l(this, j10);
    }

    public final LocalTime b0(long j10) {
        return j10 == 0 ? this : V(((((int) (j10 % 24)) + this.f18112a) + 24) % 24, this.f18113b, this.f18114c, this.f18115d);
    }

    public final LocalTime c0(long j10) {
        if (j10 != 0) {
            int i10 = (this.f18112a * 60) + this.f18113b;
            int i11 = ((((int) (j10 % 1440)) + i10) + 1440) % 1440;
            if (i10 != i11) {
                return V(i11 / 60, i11 % 60, this.f18114c, this.f18115d);
            }
        }
        return this;
    }

    public final LocalTime e0(long j10) {
        if (j10 != 0) {
            int i10 = (this.f18113b * 60) + (this.f18112a * 3600) + this.f18114c;
            int i11 = ((((int) (j10 % 86400)) + i10) + 86400) % 86400;
            if (i10 != i11) {
                return V(i11 / 3600, (i11 / 60) % 60, i11 % 60, this.f18115d);
            }
        }
        return this;
    }

    public final LocalTime d0(long j10) {
        if (j10 != 0) {
            long jG0 = g0();
            long j11 = (((j10 % 86400000000000L) + jG0) + 86400000000000L) % 86400000000000L;
            if (jG0 != j11) {
                return V((int) (j11 / 3600000000000L), (int) ((j11 / 60000000000L) % 60), (int) ((j11 / C.NANOS_PER_SECOND) % 60), (int) (j11 % C.NANOS_PER_SECOND));
            }
        }
        return this;
    }

    @Override // j$.time.temporal.Temporal
    /* JADX INFO: renamed from: C */
    public final Temporal v(long j10, ChronoUnit chronoUnit) {
        return j10 == Long.MIN_VALUE ? d(Long.MAX_VALUE, chronoUnit).d(1L, chronoUnit) : d(-j10, chronoUnit);
    }

    @Override // j$.time.temporal.TemporalAccessor
    public final Object G(c cVar) {
        if (cVar == j$.time.temporal.p.f18367b || cVar == j$.time.temporal.p.f18366a || cVar == j$.time.temporal.p.f18370e || cVar == j$.time.temporal.p.f18369d) {
            return null;
        }
        if (cVar == j$.time.temporal.p.f18372g) {
            return this;
        }
        if (cVar == j$.time.temporal.p.f18371f) {
            return null;
        }
        if (cVar == j$.time.temporal.p.f18368c) {
            return ChronoUnit.NANOS;
        }
        return cVar.h(this);
    }

    @Override // j$.time.temporal.l
    public final Temporal r(Temporal temporal) {
        return temporal.c(g0(), j$.time.temporal.a.NANO_OF_DAY);
    }

    @Override // j$.time.temporal.Temporal
    public final long g(Temporal temporal, j$.time.temporal.q qVar) {
        LocalTime localTimeW = W(temporal);
        if (qVar instanceof ChronoUnit) {
            long jG0 = localTimeW.g0() - g0();
            switch (g.f18308b[((ChronoUnit) qVar).ordinal()]) {
                case 1:
                    return jG0;
                case 2:
                    return jG0 / 1000;
                case 3:
                    return jG0 / 1000000;
                case 4:
                    return jG0 / C.NANOS_PER_SECOND;
                case 5:
                    return jG0 / 60000000000L;
                case 6:
                    return jG0 / 3600000000000L;
                case 7:
                    return jG0 / 43200000000000L;
                default:
                    throw new j$.time.temporal.r("Unsupported unit: " + qVar);
            }
        }
        return qVar.between(this, localTimeW);
    }

    public final int h0() {
        return (this.f18113b * 60) + (this.f18112a * 3600) + this.f18114c;
    }

    public final long g0() {
        return (((long) this.f18114c) * C.NANOS_PER_SECOND) + (((long) this.f18113b) * 60000000000L) + (((long) this.f18112a) * 3600000000000L) + ((long) this.f18115d);
    }

    @Override // java.lang.Comparable
    /* JADX INFO: renamed from: U, reason: merged with bridge method [inline-methods] */
    public final int compareTo(LocalTime localTime) {
        int iCompare = Integer.compare(this.f18112a, localTime.f18112a);
        return (iCompare == 0 && (iCompare = Integer.compare(this.f18113b, localTime.f18113b)) == 0 && (iCompare = Integer.compare(this.f18114c, localTime.f18114c)) == 0) ? Integer.compare(this.f18115d, localTime.f18115d) : iCompare;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof LocalTime) {
            LocalTime localTime = (LocalTime) obj;
            if (this.f18112a == localTime.f18112a && this.f18113b == localTime.f18113b && this.f18114c == localTime.f18114c && this.f18115d == localTime.f18115d) {
                return true;
            }
        }
        return false;
    }

    public final int hashCode() {
        long jG0 = g0();
        return (int) (jG0 ^ (jG0 >>> 32));
    }

    public final String toString() {
        StringBuilder sb2 = new StringBuilder(18);
        byte b10 = this.f18112a;
        byte b11 = this.f18113b;
        byte b12 = this.f18114c;
        int i10 = this.f18115d;
        sb2.append(b10 < 10 ? "0" : "");
        sb2.append((int) b10);
        sb2.append(b11 < 10 ? ":0" : ":");
        sb2.append((int) b11);
        if (b12 > 0 || i10 > 0) {
            sb2.append(b12 < 10 ? ":0" : ":");
            sb2.append((int) b12);
            if (i10 > 0) {
                sb2.append('.');
                if (i10 % 1000000 == 0) {
                    sb2.append(Integer.toString((i10 / 1000000) + 1000).substring(1));
                } else if (i10 % 1000 == 0) {
                    sb2.append(Integer.toString((i10 / 1000) + 1000000).substring(1));
                } else {
                    sb2.append(Integer.toString(i10 + 1000000000).substring(1));
                }
            }
        }
        return sb2.toString();
    }

    private Object writeReplace() {
        return new o((byte) 4, this);
    }

    private void readObject(ObjectInputStream objectInputStream) throws InvalidObjectException {
        throw new InvalidObjectException("Deserialization via serialization delegate");
    }

    public final void k0(DataOutput dataOutput) {
        if (this.f18115d == 0) {
            if (this.f18114c == 0) {
                if (this.f18113b == 0) {
                    dataOutput.writeByte(~this.f18112a);
                    return;
                } else {
                    dataOutput.writeByte(this.f18112a);
                    dataOutput.writeByte(~this.f18113b);
                    return;
                }
            }
            dataOutput.writeByte(this.f18112a);
            dataOutput.writeByte(this.f18113b);
            dataOutput.writeByte(~this.f18114c);
            return;
        }
        dataOutput.writeByte(this.f18112a);
        dataOutput.writeByte(this.f18113b);
        dataOutput.writeByte(this.f18114c);
        dataOutput.writeInt(this.f18115d);
    }

    public static LocalTime f0(DataInput dataInput) throws IOException {
        int i10;
        int i11;
        int i12 = dataInput.readByte();
        int i13 = 0;
        if (i12 < 0) {
            i12 = ~i12;
            i11 = 0;
            i10 = 0;
        } else {
            byte b10 = dataInput.readByte();
            if (b10 < 0) {
                int i14 = ~b10;
                i10 = 0;
                i13 = i14;
                i11 = 0;
            } else {
                byte b11 = dataInput.readByte();
                if (b11 < 0) {
                    i11 = ~b11;
                    i10 = 0;
                    i13 = b10;
                } else {
                    i10 = dataInput.readInt();
                    i13 = b10;
                    i11 = b11;
                }
            }
        }
        return Y(i12, i13, i11, i10);
    }
}
