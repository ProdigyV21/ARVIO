package j$.time;

import androidx.media3.common.C;
import j$.util.Objects;
import java.io.Externalizable;
import java.io.IOException;
import java.io.InvalidClassException;
import java.io.ObjectInput;
import java.io.ObjectOutput;
import java.io.StreamCorruptedException;

/* JADX INFO: loaded from: classes2.dex */
public final class o implements Externalizable {
    private static final long serialVersionUID = -7683839454370182990L;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public byte f18323a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public Object f18324b;

    public o() {
    }

    public o(byte b10, Object obj) {
        this.f18323a = b10;
        this.f18324b = obj;
    }

    @Override // java.io.Externalizable
    public final void writeExternal(ObjectOutput objectOutput) throws IOException {
        byte b10 = this.f18323a;
        Object obj = this.f18324b;
        objectOutput.writeByte(b10);
        switch (b10) {
            case 1:
                Duration duration = (Duration) obj;
                objectOutput.writeLong(duration.f18096a);
                objectOutput.writeInt(duration.f18097b);
                return;
            case 2:
                Instant instant = (Instant) obj;
                objectOutput.writeLong(instant.f18098a);
                objectOutput.writeInt(instant.f18099b);
                return;
            case 3:
                LocalDate localDate = (LocalDate) obj;
                objectOutput.writeInt(localDate.f18102a);
                objectOutput.writeByte(localDate.f18103b);
                objectOutput.writeByte(localDate.f18104c);
                return;
            case 4:
                ((LocalTime) obj).k0(objectOutput);
                return;
            case 5:
                LocalDateTime localDateTime = (LocalDateTime) obj;
                LocalDate localDate2 = localDateTime.f18107a;
                objectOutput.writeInt(localDate2.f18102a);
                objectOutput.writeByte(localDate2.f18103b);
                objectOutput.writeByte(localDate2.f18104c);
                localDateTime.f18108b.k0(objectOutput);
                return;
            case 6:
                ZonedDateTime zonedDateTime = (ZonedDateTime) obj;
                LocalDateTime localDateTime2 = zonedDateTime.f18128a;
                LocalDate localDate3 = localDateTime2.f18107a;
                objectOutput.writeInt(localDate3.f18102a);
                objectOutput.writeByte(localDate3.f18103b);
                objectOutput.writeByte(localDate3.f18104c);
                localDateTime2.f18108b.k0(objectOutput);
                zonedDateTime.f18129b.g0(objectOutput);
                zonedDateTime.f18130c.Z(objectOutput);
                return;
            case 7:
                objectOutput.writeUTF(((t) obj).f18335b);
                return;
            case 8:
                ((ZoneOffset) obj).g0(objectOutput);
                return;
            case 9:
                m mVar = (m) obj;
                mVar.f18317a.k0(objectOutput);
                mVar.f18318b.g0(objectOutput);
                return;
            case 10:
                OffsetDateTime offsetDateTime = (OffsetDateTime) obj;
                LocalDateTime localDateTime3 = offsetDateTime.f18119a;
                LocalDate localDate4 = localDateTime3.f18107a;
                objectOutput.writeInt(localDate4.f18102a);
                objectOutput.writeByte(localDate4.f18103b);
                objectOutput.writeByte(localDate4.f18104c);
                localDateTime3.f18108b.k0(objectOutput);
                offsetDateTime.f18120b.g0(objectOutput);
                return;
            case 11:
                objectOutput.writeInt(((q) obj).f18328a);
                return;
            case 12:
                s sVar = (s) obj;
                objectOutput.writeInt(sVar.f18332a);
                objectOutput.writeByte(sVar.f18333b);
                return;
            case 13:
                j jVar = (j) obj;
                objectOutput.writeByte(jVar.f18312a);
                objectOutput.writeByte(jVar.f18313b);
                return;
            case 14:
                n nVar = (n) obj;
                objectOutput.writeInt(nVar.f18320a);
                objectOutput.writeInt(nVar.f18321b);
                objectOutput.writeInt(nVar.f18322c);
                return;
            default:
                throw new InvalidClassException("Unknown serialized type");
        }
    }

    @Override // java.io.Externalizable
    public final void readExternal(ObjectInput objectInput) {
        byte b10 = objectInput.readByte();
        this.f18323a = b10;
        this.f18324b = a(b10, objectInput);
    }

    public static Object a(byte b10, ObjectInput objectInput) throws IOException {
        switch (b10) {
            case 1:
                Duration duration = Duration.f18095c;
                long j10 = objectInput.readLong();
                long j11 = objectInput.readInt();
                return Duration.m(j$.com.android.tools.r8.a.L(j10, j$.com.android.tools.r8.a.Q(j11, C.NANOS_PER_SECOND)), (int) j$.com.android.tools.r8.a.P(j11, C.NANOS_PER_SECOND));
            case 2:
                Instant instant = Instant.EPOCH;
                return Instant.ofEpochSecond(objectInput.readLong(), objectInput.readInt());
            case 3:
                LocalDate localDate = LocalDate.f18100d;
                return LocalDate.of(objectInput.readInt(), objectInput.readByte(), objectInput.readByte());
            case 4:
                return LocalTime.f0(objectInput);
            case 5:
                LocalDateTime localDateTime = LocalDateTime.f18105c;
                LocalDate localDate2 = LocalDate.f18100d;
                return LocalDateTime.X(LocalDate.of(objectInput.readInt(), objectInput.readByte(), objectInput.readByte()), LocalTime.f0(objectInput));
            case 6:
                LocalDateTime localDateTime2 = LocalDateTime.f18105c;
                LocalDate localDate3 = LocalDate.f18100d;
                LocalDateTime localDateTimeX = LocalDateTime.X(LocalDate.of(objectInput.readInt(), objectInput.readByte(), objectInput.readByte()), LocalTime.f0(objectInput));
                ZoneOffset zoneOffsetF0 = ZoneOffset.f0(objectInput);
                ZoneId zoneId = (ZoneId) a(objectInput.readByte(), objectInput);
                Objects.requireNonNull(localDateTimeX, "localDateTime");
                Objects.requireNonNull(zoneOffsetF0, "offset");
                Objects.requireNonNull(zoneId, "zone");
                if (!(zoneId instanceof ZoneOffset) || zoneOffsetF0.equals(zoneId)) {
                    return new ZonedDateTime(localDateTimeX, zoneId, zoneOffsetF0);
                }
                throw new IllegalArgumentException("ZoneId must match ZoneOffset");
            case 7:
                int i10 = t.f18334d;
                return ZoneId.W(objectInput.readUTF(), false);
            case 8:
                return ZoneOffset.f0(objectInput);
            case 9:
                int i11 = m.f18316c;
                return new m(LocalTime.f0(objectInput), ZoneOffset.f0(objectInput));
            case 10:
                int i12 = OffsetDateTime.f18118c;
                LocalDate localDate4 = LocalDate.f18100d;
                return new OffsetDateTime(LocalDateTime.X(LocalDate.of(objectInput.readInt(), objectInput.readByte(), objectInput.readByte()), LocalTime.f0(objectInput)), ZoneOffset.f0(objectInput));
            case 11:
                int i13 = q.f18327b;
                return q.U(objectInput.readInt());
            case 12:
                int i14 = s.f18331c;
                int i15 = objectInput.readInt();
                byte b11 = objectInput.readByte();
                j$.time.temporal.a.YEAR.H(i15);
                j$.time.temporal.a.MONTH_OF_YEAR.H(b11);
                return new s(i15, b11);
            case 13:
                int i16 = j.f18311c;
                byte b12 = objectInput.readByte();
                byte b13 = objectInput.readByte();
                Month monthX = Month.X(b12);
                Objects.requireNonNull(monthX, "month");
                j$.time.temporal.a.DAY_OF_MONTH.H(b13);
                if (b13 <= monthX.W()) {
                    return new j(monthX.getValue(), b13);
                }
                throw new DateTimeException("Illegal value for DayOfMonth field, value " + ((int) b13) + " is not valid for month " + monthX.name());
            case 14:
                n nVar = n.f18319d;
                return n.a(objectInput.readInt(), objectInput.readInt(), objectInput.readInt());
            default:
                throw new StreamCorruptedException("Unknown serialized type");
        }
    }

    private Object readResolve() {
        return this.f18324b;
    }
}
