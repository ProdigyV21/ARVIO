package j$.time;

import j$.time.temporal.Temporal;
import j$.time.temporal.TemporalAccessor;
import j$.util.Objects;
import j$.util.concurrent.ConcurrentHashMap;
import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;
import java.io.InvalidObjectException;
import java.io.ObjectInputStream;
import java.io.Serializable;

/* JADX INFO: loaded from: classes2.dex */
public final class ZoneOffset extends ZoneId implements TemporalAccessor, j$.time.temporal.l, Comparable<ZoneOffset>, Serializable {
    private static final long serialVersionUID = 2357656521762053153L;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final int f18126b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final transient String f18127c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public static final ConcurrentHashMap f18122d = new ConcurrentHashMap(16, 0.75f, 4);

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public static final ConcurrentHashMap f18123e = new ConcurrentHashMap(16, 0.75f, 4);
    public static final ZoneOffset UTC = d0(0);

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public static final ZoneOffset f18124f = d0(-64800);

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public static final ZoneOffset f18125g = d0(64800);

    @Override // java.lang.Comparable
    public final int compareTo(ZoneOffset zoneOffset) {
        return zoneOffset.f18126b - this.f18126b;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:33:0x00a0  */
    /* JADX WARN: Removed duplicated region for block: B:35:0x00a8  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static j$.time.ZoneOffset b0(java.lang.String r7) {
        /*
            java.lang.String r0 = "offsetId"
            j$.util.Objects.requireNonNull(r7, r0)
            j$.util.concurrent.ConcurrentHashMap r0 = j$.time.ZoneOffset.f18123e
            java.lang.Object r0 = r0.get(r7)
            j$.time.ZoneOffset r0 = (j$.time.ZoneOffset) r0
            if (r0 == 0) goto L10
            return r0
        L10:
            int r0 = r7.length()
            r1 = 2
            r2 = 1
            r3 = 0
            if (r0 == r1) goto L63
            r1 = 3
            if (r0 == r1) goto L7f
            r4 = 5
            if (r0 == r4) goto L5a
            r5 = 6
            r6 = 4
            if (r0 == r5) goto L50
            r5 = 7
            if (r0 == r5) goto L43
            r1 = 9
            if (r0 != r1) goto L37
            int r0 = e0(r7, r2, r3)
            int r1 = e0(r7, r6, r2)
            int r2 = e0(r7, r5, r2)
            goto L85
        L37:
            j$.time.DateTimeException r0 = new j$.time.DateTimeException
            java.lang.String r1 = "Invalid ID for ZoneOffset, invalid format: "
            java.lang.String r7 = r1.concat(r7)
            r0.<init>(r7)
            throw r0
        L43:
            int r0 = e0(r7, r2, r3)
            int r1 = e0(r7, r1, r3)
            int r2 = e0(r7, r4, r3)
            goto L85
        L50:
            int r0 = e0(r7, r2, r3)
            int r1 = e0(r7, r6, r2)
        L58:
            r2 = r3
            goto L85
        L5a:
            int r0 = e0(r7, r2, r3)
            int r1 = e0(r7, r1, r3)
            goto L58
        L63:
            char r0 = r7.charAt(r3)
            char r7 = r7.charAt(r2)
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            r1.<init>()
            r1.append(r0)
            java.lang.String r0 = "0"
            r1.append(r0)
            r1.append(r7)
            java.lang.String r7 = r1.toString()
        L7f:
            int r0 = e0(r7, r2, r3)
            r1 = r3
            r2 = r1
        L85:
            char r3 = r7.charAt(r3)
            r4 = 43
            r5 = 45
            if (r3 == r4) goto L9e
            if (r3 != r5) goto L92
            goto L9e
        L92:
            j$.time.DateTimeException r0 = new j$.time.DateTimeException
            java.lang.String r1 = "Invalid ID for ZoneOffset, plus/minus not found when expected: "
            java.lang.String r7 = r1.concat(r7)
            r0.<init>(r7)
            throw r0
        L9e:
            if (r3 != r5) goto La8
            int r7 = -r0
            int r0 = -r1
            int r1 = -r2
            j$.time.ZoneOffset r7 = c0(r7, r0, r1)
            return r7
        La8:
            j$.time.ZoneOffset r7 = c0(r0, r1, r2)
            return r7
        */
        throw new UnsupportedOperationException("Method not decompiled: j$.time.ZoneOffset.b0(java.lang.String):j$.time.ZoneOffset");
    }

    @Override // j$.time.ZoneId
    public final j$.time.zone.f V() {
        Objects.requireNonNull(this, "offset");
        return new j$.time.zone.f(this);
    }

    public static int e0(CharSequence charSequence, int i10, boolean z) {
        if (z) {
            String str = (String) charSequence;
            if (str.charAt(i10 - 1) != ':') {
                throw new DateTimeException("Invalid ID for ZoneOffset, colon not found when expected: " + ((Object) str));
            }
        }
        String str2 = (String) charSequence;
        char cCharAt = str2.charAt(i10);
        char cCharAt2 = str2.charAt(i10 + 1);
        if (cCharAt >= '0' && cCharAt <= '9' && cCharAt2 >= '0' && cCharAt2 <= '9') {
            return (cCharAt2 - '0') + ((cCharAt - '0') * 10);
        }
        throw new DateTimeException("Invalid ID for ZoneOffset, non numeric characters found: " + ((Object) str2));
    }

    public static ZoneOffset a0(TemporalAccessor temporalAccessor) {
        Objects.requireNonNull(temporalAccessor, "temporal");
        ZoneOffset zoneOffset = (ZoneOffset) temporalAccessor.G(j$.time.temporal.p.f18369d);
        if (zoneOffset != null) {
            return zoneOffset;
        }
        throw new DateTimeException("Unable to obtain ZoneOffset from TemporalAccessor: " + temporalAccessor + " of type " + temporalAccessor.getClass().getName());
    }

    public static ZoneOffset c0(int i10, int i11, int i12) {
        if (i10 < -18 || i10 > 18) {
            throw new DateTimeException("Zone offset hours not in valid range: value " + i10 + " is not in the range -18 to 18");
        }
        if (i10 > 0) {
            if (i11 < 0 || i12 < 0) {
                throw new DateTimeException("Zone offset minutes and seconds must be positive because hours is positive");
            }
        } else if (i10 < 0) {
            if (i11 > 0 || i12 > 0) {
                throw new DateTimeException("Zone offset minutes and seconds must be negative because hours is negative");
            }
        } else if ((i11 > 0 && i12 < 0) || (i11 < 0 && i12 > 0)) {
            throw new DateTimeException("Zone offset minutes and seconds must have the same sign");
        }
        if (i11 < -59 || i11 > 59) {
            throw new DateTimeException("Zone offset minutes not in valid range: value " + i11 + " is not in the range -59 to 59");
        }
        if (i12 < -59 || i12 > 59) {
            throw new DateTimeException("Zone offset seconds not in valid range: value " + i12 + " is not in the range -59 to 59");
        }
        if (Math.abs(i10) == 18 && (i11 | i12) != 0) {
            throw new DateTimeException("Zone offset not in valid range: -18:00 to +18:00");
        }
        return d0((i11 * 60) + (i10 * 3600) + i12);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static ZoneOffset d0(int i10) {
        if (i10 < -64800 || i10 > 64800) {
            throw new DateTimeException("Zone offset not in valid range: -18:00 to +18:00");
        }
        if (i10 % 900 == 0) {
            Integer numValueOf = Integer.valueOf(i10);
            ConcurrentHashMap concurrentHashMap = f18122d;
            ZoneOffset zoneOffset = (ZoneOffset) concurrentHashMap.get(numValueOf);
            if (zoneOffset != null) {
                return zoneOffset;
            }
            concurrentHashMap.putIfAbsent(numValueOf, new ZoneOffset(i10));
            ZoneOffset zoneOffset2 = (ZoneOffset) concurrentHashMap.get(numValueOf);
            f18123e.putIfAbsent(zoneOffset2.f18127c, zoneOffset2);
            return zoneOffset2;
        }
        return new ZoneOffset(i10);
    }

    public ZoneOffset(int i10) {
        String string;
        this.f18126b = i10;
        if (i10 == 0) {
            string = "Z";
        } else {
            int iAbs = Math.abs(i10);
            StringBuilder sb2 = new StringBuilder();
            int i11 = iAbs / 3600;
            int i12 = (iAbs / 60) % 60;
            sb2.append(i10 < 0 ? "-" : "+");
            sb2.append(i11 < 10 ? "0" : "");
            sb2.append(i11);
            sb2.append(i12 < 10 ? ":0" : ":");
            sb2.append(i12);
            int i13 = iAbs % 60;
            if (i13 != 0) {
                sb2.append(i13 < 10 ? ":0" : ":");
                sb2.append(i13);
            }
            string = sb2.toString();
        }
        this.f18127c = string;
    }

    @Override // j$.time.ZoneId
    public final String k() {
        return this.f18127c;
    }

    @Override // j$.time.temporal.TemporalAccessor
    public final boolean e(j$.time.temporal.o oVar) {
        return oVar instanceof j$.time.temporal.a ? oVar == j$.time.temporal.a.OFFSET_SECONDS : oVar != null && oVar.l(this);
    }

    @Override // j$.time.temporal.TemporalAccessor
    public final j$.time.temporal.s n(j$.time.temporal.o oVar) {
        return j$.time.temporal.p.d(this, oVar);
    }

    @Override // j$.time.temporal.TemporalAccessor
    public final int l(j$.time.temporal.o oVar) {
        if (oVar == j$.time.temporal.a.OFFSET_SECONDS) {
            return this.f18126b;
        }
        if (oVar instanceof j$.time.temporal.a) {
            throw new j$.time.temporal.r(b.a("Unsupported field: ", oVar));
        }
        return j$.time.temporal.p.d(this, oVar).a(H(oVar), oVar);
    }

    @Override // j$.time.temporal.TemporalAccessor
    public final long H(j$.time.temporal.o oVar) {
        if (oVar == j$.time.temporal.a.OFFSET_SECONDS) {
            return this.f18126b;
        }
        if (oVar instanceof j$.time.temporal.a) {
            throw new j$.time.temporal.r(b.a("Unsupported field: ", oVar));
        }
        return oVar.C(this);
    }

    @Override // j$.time.temporal.TemporalAccessor
    public final Object G(c cVar) {
        return (cVar == j$.time.temporal.p.f18369d || cVar == j$.time.temporal.p.f18370e) ? this : j$.time.temporal.p.c(this, cVar);
    }

    @Override // j$.time.temporal.l
    public final Temporal r(Temporal temporal) {
        return temporal.c(this.f18126b, j$.time.temporal.a.OFFSET_SECONDS);
    }

    @Override // j$.time.ZoneId
    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        return (obj instanceof ZoneOffset) && this.f18126b == ((ZoneOffset) obj).f18126b;
    }

    @Override // j$.time.ZoneId
    public final int hashCode() {
        return this.f18126b;
    }

    @Override // j$.time.ZoneId
    public final String toString() {
        return this.f18127c;
    }

    private Object writeReplace() {
        return new o((byte) 8, this);
    }

    private void readObject(ObjectInputStream objectInputStream) throws InvalidObjectException {
        throw new InvalidObjectException("Deserialization via serialization delegate");
    }

    @Override // j$.time.ZoneId
    public final void Z(DataOutput dataOutput) throws IOException {
        dataOutput.writeByte(8);
        g0(dataOutput);
    }

    public final void g0(DataOutput dataOutput) throws IOException {
        int i10 = this.f18126b;
        int i11 = i10 % 900 == 0 ? i10 / 900 : 127;
        dataOutput.writeByte(i11);
        if (i11 == 127) {
            dataOutput.writeInt(i10);
        }
    }

    public static ZoneOffset f0(DataInput dataInput) throws IOException {
        byte b10 = dataInput.readByte();
        return b10 == 127 ? d0(dataInput.readInt()) : d0(b10 * 900);
    }
}
