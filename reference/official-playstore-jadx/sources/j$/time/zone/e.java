package j$.time.zone;

import j$.time.DayOfWeek;
import j$.time.LocalTime;
import j$.time.Month;
import j$.time.ZoneOffset;
import j$.util.Objects;
import java.io.DataInput;
import java.io.DataOutput;
import java.io.InvalidObjectException;
import java.io.ObjectInputStream;
import java.io.Serializable;

/* JADX INFO: loaded from: classes2.dex */
public final class e implements Serializable {
    private static final long serialVersionUID = 6889046316657758795L;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final Month f18396a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final byte f18397b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final DayOfWeek f18398c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final LocalTime f18399d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final boolean f18400e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final d f18401f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public final ZoneOffset f18402g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public final ZoneOffset f18403h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final ZoneOffset f18404i;

    public e(Month month, int i10, DayOfWeek dayOfWeek, LocalTime localTime, boolean z, d dVar, ZoneOffset zoneOffset, ZoneOffset zoneOffset2, ZoneOffset zoneOffset3) {
        this.f18396a = month;
        this.f18397b = (byte) i10;
        this.f18398c = dayOfWeek;
        this.f18399d = localTime;
        this.f18400e = z;
        this.f18401f = dVar;
        this.f18402g = zoneOffset;
        this.f18403h = zoneOffset2;
        this.f18404i = zoneOffset3;
    }

    private void readObject(ObjectInputStream objectInputStream) throws InvalidObjectException {
        throw new InvalidObjectException("Deserialization via serialization delegate");
    }

    private Object writeReplace() {
        return new a((byte) 3, this);
    }

    public final void b(DataOutput dataOutput) {
        int iH0 = this.f18400e ? 86400 : this.f18399d.h0();
        int i10 = this.f18402g.f18126b;
        int i11 = this.f18403h.f18126b - i10;
        int i12 = this.f18404i.f18126b - i10;
        byte b10 = iH0 % 3600 == 0 ? this.f18400e ? (byte) 24 : this.f18399d.f18112a : (byte) 31;
        int i13 = i10 % 900 == 0 ? (i10 / 900) + 128 : 255;
        int i14 = (i11 == 0 || i11 == 1800 || i11 == 3600) ? i11 / 1800 : 3;
        int i15 = (i12 == 0 || i12 == 1800 || i12 == 3600) ? i12 / 1800 : 3;
        DayOfWeek dayOfWeek = this.f18398c;
        dataOutput.writeInt((this.f18396a.getValue() << 28) + ((this.f18397b + 32) << 22) + ((dayOfWeek == null ? 0 : dayOfWeek.getValue()) << 19) + (b10 << 14) + (this.f18401f.ordinal() << 12) + (i13 << 4) + (i14 << 2) + i15);
        if (b10 == 31) {
            dataOutput.writeInt(iH0);
        }
        if (i13 == 255) {
            dataOutput.writeInt(i10);
        }
        if (i14 == 3) {
            dataOutput.writeInt(this.f18403h.f18126b);
        }
        if (i15 == 3) {
            dataOutput.writeInt(this.f18404i.f18126b);
        }
    }

    public static e a(DataInput dataInput) {
        d dVar;
        LocalTime localTimeV;
        int i10;
        int i11;
        int i12 = dataInput.readInt();
        Month monthX = Month.X(i12 >>> 28);
        int i13 = ((264241152 & i12) >>> 22) - 32;
        int i14 = (3670016 & i12) >>> 19;
        DayOfWeek dayOfWeekU = i14 == 0 ? null : DayOfWeek.U(i14);
        int i15 = (507904 & i12) >>> 14;
        d dVar2 = d.values()[(i12 & 12288) >>> 12];
        int i16 = (i12 & 4080) >>> 4;
        int i17 = (i12 & 12) >>> 2;
        int i18 = i12 & 3;
        if (i15 == 31) {
            long j10 = dataInput.readInt();
            LocalTime localTime = LocalTime.f18109e;
            j$.time.temporal.a.SECOND_OF_DAY.H(j10);
            int i19 = (int) (j10 / 3600);
            long j11 = j10 - ((long) (i19 * 3600));
            int i20 = (int) (j11 / 60);
            dVar = dVar2;
            localTimeV = LocalTime.V(i19, i20, (int) (j11 - ((long) (i20 * 60))), 0);
        } else {
            dVar = dVar2;
            int i21 = i15 % 24;
            LocalTime localTime2 = LocalTime.f18109e;
            j$.time.temporal.a.HOUR_OF_DAY.H(i21);
            localTimeV = LocalTime.f18111g[i21];
        }
        ZoneOffset zoneOffsetD0 = ZoneOffset.d0(i16 == 255 ? dataInput.readInt() : (i16 - 128) * 900);
        if (i17 == 3) {
            i10 = dataInput.readInt();
        } else {
            i10 = (i17 * 1800) + zoneOffsetD0.f18126b;
        }
        ZoneOffset zoneOffsetD02 = ZoneOffset.d0(i10);
        if (i18 == 3) {
            i11 = dataInput.readInt();
        } else {
            i11 = (i18 * 1800) + zoneOffsetD0.f18126b;
        }
        ZoneOffset zoneOffsetD03 = ZoneOffset.d0(i11);
        boolean z = i15 == 24;
        Objects.requireNonNull(monthX, "month");
        Objects.requireNonNull(localTimeV, "time");
        d dVar3 = dVar;
        Objects.requireNonNull(dVar3, "timeDefnition");
        Objects.requireNonNull(zoneOffsetD0, "standardOffset");
        Objects.requireNonNull(zoneOffsetD02, "offsetBefore");
        Objects.requireNonNull(zoneOffsetD03, "offsetAfter");
        if (i13 < -28 || i13 > 31 || i13 == 0) {
            throw new IllegalArgumentException("Day of month indicator must be between -28 and 31 inclusive excluding zero");
        }
        if (z && !localTimeV.equals(LocalTime.MIDNIGHT)) {
            throw new IllegalArgumentException("Time must be midnight when end of day flag is true");
        }
        if (localTimeV.f18115d != 0) {
            throw new IllegalArgumentException("Time's nano-of-second must be zero");
        }
        return new e(monthX, i13, dayOfWeekU, localTimeV, z, dVar3, zoneOffsetD0, zoneOffsetD02, zoneOffsetD03);
    }

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof e) {
            e eVar = (e) obj;
            if (this.f18396a == eVar.f18396a && this.f18397b == eVar.f18397b && this.f18398c == eVar.f18398c && this.f18401f == eVar.f18401f && this.f18399d.equals(eVar.f18399d) && this.f18400e == eVar.f18400e && this.f18402g.equals(eVar.f18402g) && this.f18403h.equals(eVar.f18403h) && this.f18404i.equals(eVar.f18404i)) {
                return true;
            }
        }
        return false;
    }

    public final int hashCode() {
        int iH0 = ((this.f18399d.h0() + (this.f18400e ? 1 : 0)) << 15) + (this.f18396a.ordinal() << 11) + ((this.f18397b + 32) << 5);
        DayOfWeek dayOfWeek = this.f18398c;
        return ((this.f18402g.f18126b ^ (this.f18401f.ordinal() + (iH0 + ((dayOfWeek == null ? 7 : dayOfWeek.ordinal()) << 2)))) ^ this.f18403h.f18126b) ^ this.f18404i.f18126b;
    }

    public final String toString() {
        StringBuilder sb2 = new StringBuilder("TransitionRule[");
        sb2.append(this.f18404i.f18126b - this.f18403h.f18126b > 0 ? "Gap " : "Overlap ");
        sb2.append(this.f18403h);
        sb2.append(" to ");
        sb2.append(this.f18404i);
        sb2.append(", ");
        DayOfWeek dayOfWeek = this.f18398c;
        if (dayOfWeek != null) {
            byte b10 = this.f18397b;
            if (b10 == -1) {
                sb2.append(dayOfWeek.name());
                sb2.append(" on or before last day of ");
                sb2.append(this.f18396a.name());
            } else if (b10 < 0) {
                sb2.append(dayOfWeek.name());
                sb2.append(" on or before last day minus ");
                sb2.append((-this.f18397b) - 1);
                sb2.append(" of ");
                sb2.append(this.f18396a.name());
            } else {
                sb2.append(dayOfWeek.name());
                sb2.append(" on or after ");
                sb2.append(this.f18396a.name());
                sb2.append(' ');
                sb2.append((int) this.f18397b);
            }
        } else {
            sb2.append(this.f18396a.name());
            sb2.append(' ');
            sb2.append((int) this.f18397b);
        }
        sb2.append(" at ");
        sb2.append(this.f18400e ? "24:00" : this.f18399d.toString());
        sb2.append(" ");
        sb2.append(this.f18401f);
        sb2.append(", standard offset ");
        sb2.append(this.f18402g);
        sb2.append(']');
        return sb2.toString();
    }
}
