package j$.time.zone;

import j$.time.Clock;
import j$.time.DayOfWeek;
import j$.time.Instant;
import j$.time.LocalDate;
import j$.time.LocalDateTime;
import j$.time.LocalTime;
import j$.time.Month;
import j$.time.ZoneOffset;
import j$.time.chrono.p;
import j$.time.temporal.m;
import j$.util.Objects;
import j$.util.concurrent.ConcurrentHashMap;
import java.io.InvalidObjectException;
import java.io.ObjectInputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.TimeZone;

/* JADX INFO: loaded from: classes2.dex */
public final class f implements Serializable {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public static final long[] f18405i = new long[0];

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public static final e[] f18406j = new e[0];
    public static final LocalDateTime[] k = new LocalDateTime[0];

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public static final b[] f18407l = new b[0];
    private static final long serialVersionUID = 3044319355680032515L;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final long[] f18408a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final ZoneOffset[] f18409b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final long[] f18410c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final LocalDateTime[] f18411d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final ZoneOffset[] f18412e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final e[] f18413f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public final TimeZone f18414g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public final transient ConcurrentHashMap f18415h = new ConcurrentHashMap();

    public static Object a(LocalDateTime localDateTime, b bVar) {
        LocalDateTime localDateTime2 = bVar.f18391b;
        if (bVar.l()) {
            if (localDateTime.W(localDateTime2)) {
                return bVar.f18392c;
            }
            if (!localDateTime.W(bVar.f18391b.a0(bVar.f18393d.f18126b - bVar.f18392c.f18126b))) {
                return bVar.f18393d;
            }
        } else {
            if (!localDateTime.W(localDateTime2)) {
                return bVar.f18393d;
            }
            if (localDateTime.W(bVar.f18391b.a0(bVar.f18393d.f18126b - bVar.f18392c.f18126b))) {
                return bVar.f18392c;
            }
        }
        return bVar;
    }

    public f(long[] jArr, ZoneOffset[] zoneOffsetArr, long[] jArr2, ZoneOffset[] zoneOffsetArr2, e[] eVarArr) {
        this.f18408a = jArr;
        this.f18409b = zoneOffsetArr;
        this.f18410c = jArr2;
        this.f18412e = zoneOffsetArr2;
        this.f18413f = eVarArr;
        if (jArr2.length == 0) {
            this.f18411d = k;
        } else {
            ArrayList arrayList = new ArrayList();
            int i10 = 0;
            while (i10 < jArr2.length) {
                int i11 = i10 + 1;
                b bVar = new b(jArr2[i10], zoneOffsetArr2[i10], zoneOffsetArr2[i11]);
                if (bVar.l()) {
                    arrayList.add(bVar.f18391b);
                    arrayList.add(bVar.f18391b.a0(bVar.f18393d.f18126b - bVar.f18392c.f18126b));
                } else {
                    arrayList.add(bVar.f18391b.a0(bVar.f18393d.f18126b - bVar.f18392c.f18126b));
                    arrayList.add(bVar.f18391b);
                }
                i10 = i11;
            }
            this.f18411d = (LocalDateTime[]) arrayList.toArray(new LocalDateTime[arrayList.size()]);
        }
        this.f18414g = null;
    }

    public f(ZoneOffset zoneOffset) {
        ZoneOffset[] zoneOffsetArr = {zoneOffset};
        this.f18409b = zoneOffsetArr;
        long[] jArr = f18405i;
        this.f18408a = jArr;
        this.f18410c = jArr;
        this.f18411d = k;
        this.f18412e = zoneOffsetArr;
        this.f18413f = f18406j;
        this.f18414g = null;
    }

    public f(TimeZone timeZone) {
        ZoneOffset[] zoneOffsetArr = {i(timeZone.getRawOffset())};
        this.f18409b = zoneOffsetArr;
        long[] jArr = f18405i;
        this.f18408a = jArr;
        this.f18410c = jArr;
        this.f18411d = k;
        this.f18412e = zoneOffsetArr;
        this.f18413f = f18406j;
        this.f18414g = timeZone;
    }

    public static ZoneOffset i(int i10) {
        return ZoneOffset.d0(i10 / 1000);
    }

    private void readObject(ObjectInputStream objectInputStream) throws InvalidObjectException {
        throw new InvalidObjectException("Deserialization via serialization delegate");
    }

    private Object writeReplace() {
        return new a(this.f18414g != null ? (byte) 100 : (byte) 1, this);
    }

    public static int c(long j10, ZoneOffset zoneOffset) {
        return LocalDate.e0(j$.com.android.tools.r8.a.Q(j10 + ((long) zoneOffset.f18126b), 86400)).getYear();
    }

    public final boolean h() {
        b bVar;
        TimeZone timeZone = this.f18414g;
        if (timeZone != null) {
            if (timeZone.useDaylightTime() || this.f18414g.getDSTSavings() != 0) {
                return false;
            }
            Instant instantNow = Instant.now();
            b bVar2 = null;
            if (this.f18414g != null) {
                long epochSecond = instantNow.getEpochSecond();
                if (instantNow.getNano() > 0 && epochSecond < Long.MAX_VALUE) {
                    epochSecond++;
                }
                int iC = c(epochSecond, d(instantNow));
                b[] bVarArrB = b(iC);
                int length = bVarArrB.length - 1;
                while (true) {
                    if (length >= 0) {
                        bVar = bVarArrB[length];
                        if (epochSecond > bVar.f18390a) {
                            break;
                        }
                        length--;
                    } else if (iC > 1800) {
                        b[] bVarArrB2 = b(iC - 1);
                        for (int length2 = bVarArrB2.length - 1; length2 >= 0; length2--) {
                            bVar = bVarArrB2[length2];
                            if (epochSecond <= bVar.f18390a) {
                            }
                        }
                        long jMin = Math.min(epochSecond - 31104000, (Clock.systemUTC().a() / 1000) + 31968000);
                        int offset = this.f18414g.getOffset((epochSecond - 1) * 1000);
                        long jI = LocalDate.of(1800, 1, 1).I() * 86400;
                        while (true) {
                            if (jI > jMin) {
                                break;
                            }
                            int offset2 = this.f18414g.getOffset(jMin * 1000);
                            if (offset != offset2) {
                                int iC2 = c(jMin, i(offset2));
                                b[] bVarArrB3 = b(iC2 + 1);
                                int length3 = bVarArrB3.length - 1;
                                while (true) {
                                    if (length3 < 0) {
                                        b[] bVarArrB4 = b(iC2);
                                        bVar2 = bVarArrB4[bVarArrB4.length - 1];
                                        break;
                                    }
                                    bVar2 = bVarArrB3[length3];
                                    if (epochSecond > bVar2.f18390a) {
                                        break;
                                    }
                                    length3--;
                                }
                            } else {
                                jMin -= 7776000;
                            }
                        }
                    }
                }
                bVar2 = bVar;
            } else if (this.f18410c.length != 0) {
                long epochSecond2 = instantNow.getEpochSecond();
                if (instantNow.getNano() > 0 && epochSecond2 < Long.MAX_VALUE) {
                    epochSecond2++;
                }
                long[] jArr = this.f18410c;
                long j10 = jArr[jArr.length - 1];
                if (this.f18413f.length <= 0 || epochSecond2 <= j10) {
                    int iBinarySearch = Arrays.binarySearch(this.f18410c, epochSecond2);
                    if (iBinarySearch < 0) {
                        iBinarySearch = (-iBinarySearch) - 1;
                    }
                    if (iBinarySearch > 0) {
                        int i10 = iBinarySearch - 1;
                        long j11 = this.f18410c[i10];
                        ZoneOffset[] zoneOffsetArr = this.f18412e;
                        bVar2 = new b(j11, zoneOffsetArr[i10], zoneOffsetArr[iBinarySearch]);
                    }
                } else {
                    ZoneOffset[] zoneOffsetArr2 = this.f18412e;
                    ZoneOffset zoneOffset = zoneOffsetArr2[zoneOffsetArr2.length - 1];
                    int iC3 = c(epochSecond2, zoneOffset);
                    b[] bVarArrB5 = b(iC3);
                    int length4 = bVarArrB5.length - 1;
                    while (true) {
                        if (length4 < 0) {
                            int i11 = iC3 - 1;
                            if (i11 > c(j10, zoneOffset)) {
                                b[] bVarArrB6 = b(i11);
                                bVar2 = bVarArrB6[bVarArrB6.length - 1];
                            }
                        } else {
                            b bVar3 = bVarArrB5[length4];
                            if (epochSecond2 > bVar3.f18390a) {
                                bVar2 = bVar3;
                                break;
                            }
                            length4--;
                        }
                    }
                }
            }
            if (bVar2 != null) {
                return false;
            }
        } else if (this.f18410c.length != 0) {
            return false;
        }
        return true;
    }

    public final ZoneOffset d(Instant instant) {
        TimeZone timeZone = this.f18414g;
        if (timeZone != null) {
            return i(timeZone.getOffset(instant.toEpochMilli()));
        }
        if (this.f18410c.length == 0) {
            return this.f18409b[0];
        }
        long epochSecond = instant.getEpochSecond();
        if (this.f18413f.length > 0) {
            if (epochSecond > this.f18410c[r7.length - 1]) {
                b[] bVarArrB = b(c(epochSecond, this.f18412e[r7.length - 1]));
                b bVar = null;
                for (int i10 = 0; i10 < bVarArrB.length; i10++) {
                    bVar = bVarArrB[i10];
                    if (epochSecond < bVar.f18390a) {
                        return bVar.f18392c;
                    }
                }
                return bVar.f18393d;
            }
        }
        int iBinarySearch = Arrays.binarySearch(this.f18410c, epochSecond);
        if (iBinarySearch < 0) {
            iBinarySearch = (-iBinarySearch) - 2;
        }
        return this.f18412e[iBinarySearch + 1];
    }

    public final List f(LocalDateTime localDateTime) {
        Object objE = e(localDateTime);
        if (!(objE instanceof b)) {
            return Collections.singletonList((ZoneOffset) objE);
        }
        b bVar = (b) objE;
        return bVar.l() ? Collections.EMPTY_LIST : j$.com.android.tools.r8.a.M(new Object[]{bVar.f18392c, bVar.f18393d});
    }

    /* JADX WARN: Code restructure failed: missing block: B:27:0x0064, code lost:
    
        if (r8.U(r0) > 0) goto L34;
     */
    /* JADX WARN: Code restructure failed: missing block: B:33:0x0087, code lost:
    
        if (r8.f18108b.g0() <= r0.f18108b.g0()) goto L44;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object e(j$.time.LocalDateTime r8) {
        /*
            Method dump skipped, instruction units count: 264
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: j$.time.zone.f.e(j$.time.LocalDateTime):java.lang.Object");
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final b[] b(int i10) {
        LocalDate localDateV;
        b[] bVarArr = f18407l;
        Integer numValueOf = Integer.valueOf(i10);
        b[] bVarArr2 = (b[]) this.f18415h.get(numValueOf);
        if (bVarArr2 != null) {
            return bVarArr2;
        }
        long j10 = 1;
        int i11 = 0;
        int i12 = 1;
        if (this.f18414g != null) {
            if (i10 < 1800) {
                return bVarArr;
            }
            LocalDateTime localDateTime = LocalDateTime.f18105c;
            LocalDate localDateOf = LocalDate.of(i10 - 1, 12, 31);
            j$.time.temporal.a.HOUR_OF_DAY.H(0);
            long jW = j$.com.android.tools.r8.a.w(new LocalDateTime(localDateOf, LocalTime.f18111g[0]), this.f18409b[0]);
            long j11 = 1000;
            int offset = this.f18414g.getOffset(jW * 1000);
            long j12 = 31968000 + jW;
            while (jW < j12) {
                long j13 = jW + 7776000;
                long j14 = j11;
                if (offset != this.f18414g.getOffset(j13 * j14)) {
                    while (j13 - jW > j10) {
                        long jQ = j$.com.android.tools.r8.a.Q(j13 + jW, 2L);
                        if (this.f18414g.getOffset(jQ * j14) == offset) {
                            jW = jQ;
                        } else {
                            j13 = jQ;
                        }
                        j10 = 1;
                    }
                    if (this.f18414g.getOffset(jW * j14) == offset) {
                        jW = j13;
                    }
                    ZoneOffset zoneOffsetI = i(offset);
                    int offset2 = this.f18414g.getOffset(jW * j14);
                    ZoneOffset zoneOffsetI2 = i(offset2);
                    if (c(jW, zoneOffsetI2) == i10) {
                        bVarArr = (b[]) Arrays.copyOf(bVarArr, bVarArr.length + 1);
                        bVarArr[bVarArr.length - 1] = new b(jW, zoneOffsetI, zoneOffsetI2);
                    }
                    offset = offset2;
                } else {
                    jW = j13;
                }
                j11 = j14;
                j10 = 1;
            }
            if (1916 <= i10 && i10 < 2100) {
                this.f18415h.putIfAbsent(numValueOf, bVarArr);
            }
            return bVarArr;
        }
        e[] eVarArr = this.f18413f;
        b[] bVarArr3 = new b[eVarArr.length];
        int i13 = 0;
        while (i13 < eVarArr.length) {
            e eVar = eVarArr[i13];
            byte b10 = eVar.f18397b;
            if (b10 < 0) {
                Month month = eVar.f18396a;
                long j15 = i10;
                int iV = month.V(p.f18175d.T(j15)) + 1 + eVar.f18397b;
                LocalDate localDate = LocalDate.f18100d;
                j$.time.temporal.a.YEAR.H(j15);
                Objects.requireNonNull(month, "month");
                j$.time.temporal.a.DAY_OF_MONTH.H(iV);
                localDateV = LocalDate.V(i10, month.getValue(), iV);
                DayOfWeek dayOfWeek = eVar.f18398c;
                if (dayOfWeek != null) {
                    localDateV = localDateV.B(new m(dayOfWeek.getValue(), i12));
                }
            } else {
                Month month2 = eVar.f18396a;
                LocalDate localDate2 = LocalDate.f18100d;
                j$.time.temporal.a.YEAR.H(i10);
                Objects.requireNonNull(month2, "month");
                j$.time.temporal.a.DAY_OF_MONTH.H(b10);
                localDateV = LocalDate.V(i10, month2.getValue(), b10);
                DayOfWeek dayOfWeek2 = eVar.f18398c;
                if (dayOfWeek2 != null) {
                    localDateV = localDateV.B(new m(dayOfWeek2.getValue(), i11));
                }
            }
            if (eVar.f18400e) {
                localDateV = localDateV.plusDays(1L);
            }
            LocalDateTime localDateTimeX = LocalDateTime.X(localDateV, eVar.f18399d);
            d dVar = eVar.f18401f;
            ZoneOffset zoneOffset = eVar.f18402g;
            ZoneOffset zoneOffset2 = eVar.f18403h;
            dVar.getClass();
            int i14 = c.f18394a[dVar.ordinal()];
            if (i14 == 1) {
                localDateTimeX = localDateTimeX.a0(zoneOffset2.f18126b - ZoneOffset.UTC.f18126b);
            } else if (i14 == 2) {
                localDateTimeX = localDateTimeX.a0(zoneOffset2.f18126b - zoneOffset.f18126b);
            }
            bVarArr3[i13] = new b(localDateTimeX, eVar.f18403h, eVar.f18404i);
            i13++;
            i11 = 0;
        }
        if (i10 < 2100) {
            this.f18415h.putIfAbsent(numValueOf, bVarArr3);
        }
        return bVarArr3;
    }

    public final boolean g(Instant instant) {
        ZoneOffset zoneOffsetI;
        TimeZone timeZone = this.f18414g;
        if (timeZone != null) {
            zoneOffsetI = i(timeZone.getRawOffset());
        } else if (this.f18410c.length == 0) {
            zoneOffsetI = this.f18409b[0];
        } else {
            int iBinarySearch = Arrays.binarySearch(this.f18408a, instant.getEpochSecond());
            if (iBinarySearch < 0) {
                iBinarySearch = (-iBinarySearch) - 2;
            }
            zoneOffsetI = this.f18409b[iBinarySearch + 1];
        }
        return !zoneOffsetI.equals(d(instant));
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof f) {
            f fVar = (f) obj;
            if (Objects.equals(this.f18414g, fVar.f18414g) && Arrays.equals(this.f18408a, fVar.f18408a) && Arrays.equals(this.f18409b, fVar.f18409b) && Arrays.equals(this.f18410c, fVar.f18410c) && Arrays.equals(this.f18412e, fVar.f18412e) && Arrays.equals(this.f18413f, fVar.f18413f)) {
                return true;
            }
        }
        return false;
    }

    public final int hashCode() {
        return ((((Objects.hashCode(this.f18414g) ^ Arrays.hashCode(this.f18408a)) ^ Arrays.hashCode(this.f18409b)) ^ Arrays.hashCode(this.f18410c)) ^ Arrays.hashCode(this.f18412e)) ^ Arrays.hashCode(this.f18413f);
    }

    public final String toString() {
        TimeZone timeZone = this.f18414g;
        if (timeZone != null) {
            return "ZoneRules[timeZone=" + timeZone.getID() + "]";
        }
        return "ZoneRules[currentStandardOffset=" + this.f18409b[r0.length - 1] + "]";
    }
}
