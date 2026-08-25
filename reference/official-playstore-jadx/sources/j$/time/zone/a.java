package j$.time.zone;

import j$.time.ZoneOffset;
import java.io.DataInput;
import java.io.DataOutput;
import java.io.Externalizable;
import java.io.IOException;
import java.io.InvalidClassException;
import java.io.ObjectInput;
import java.io.ObjectOutput;
import java.io.StreamCorruptedException;
import java.util.TimeZone;

/* JADX INFO: loaded from: classes2.dex */
public final class a implements Externalizable {
    private static final long serialVersionUID = -8885321777449118786L;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public byte f18387a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public Object f18388b;

    public a() {
    }

    public a(byte b10, Object obj) {
        this.f18387a = b10;
        this.f18388b = obj;
    }

    @Override // java.io.Externalizable
    public final void writeExternal(ObjectOutput objectOutput) throws IOException {
        byte b10 = this.f18387a;
        Object obj = this.f18388b;
        objectOutput.writeByte(b10);
        if (b10 != 1) {
            if (b10 == 2) {
                b bVar = (b) obj;
                c(bVar.f18390a, objectOutput);
                d(bVar.f18392c, objectOutput);
                d(bVar.f18393d, objectOutput);
                return;
            }
            if (b10 == 3) {
                ((e) obj).b(objectOutput);
                return;
            } else {
                if (b10 != 100) {
                    throw new InvalidClassException("Unknown serialized type");
                }
                objectOutput.writeUTF(((f) obj).f18414g.getID());
                return;
            }
        }
        f fVar = (f) obj;
        objectOutput.writeInt(fVar.f18408a.length);
        for (long j10 : fVar.f18408a) {
            c(j10, objectOutput);
        }
        for (ZoneOffset zoneOffset : fVar.f18409b) {
            d(zoneOffset, objectOutput);
        }
        objectOutput.writeInt(fVar.f18410c.length);
        for (long j11 : fVar.f18410c) {
            c(j11, objectOutput);
        }
        for (ZoneOffset zoneOffset2 : fVar.f18412e) {
            d(zoneOffset2, objectOutput);
        }
        objectOutput.writeByte(fVar.f18413f.length);
        for (e eVar : fVar.f18413f) {
            eVar.b(objectOutput);
        }
    }

    @Override // java.io.Externalizable
    public final void readExternal(ObjectInput objectInput) throws IOException {
        Object fVar;
        byte b10 = objectInput.readByte();
        this.f18387a = b10;
        if (b10 == 1) {
            long[] jArr = f.f18405i;
            int i10 = objectInput.readInt();
            long[] jArr2 = i10 == 0 ? jArr : new long[i10];
            for (int i11 = 0; i11 < i10; i11++) {
                jArr2[i11] = a(objectInput);
            }
            int i12 = i10 + 1;
            ZoneOffset[] zoneOffsetArr = new ZoneOffset[i12];
            for (int i13 = 0; i13 < i12; i13++) {
                zoneOffsetArr[i13] = b(objectInput);
            }
            int i14 = objectInput.readInt();
            if (i14 != 0) {
                jArr = new long[i14];
            }
            long[] jArr3 = jArr;
            for (int i15 = 0; i15 < i14; i15++) {
                jArr3[i15] = a(objectInput);
            }
            int i16 = i14 + 1;
            ZoneOffset[] zoneOffsetArr2 = new ZoneOffset[i16];
            for (int i17 = 0; i17 < i16; i17++) {
                zoneOffsetArr2[i17] = b(objectInput);
            }
            int i18 = objectInput.readByte();
            e[] eVarArr = i18 == 0 ? f.f18406j : new e[i18];
            for (int i19 = 0; i19 < i18; i19++) {
                eVarArr[i19] = e.a(objectInput);
            }
            fVar = new f(jArr2, zoneOffsetArr, jArr3, zoneOffsetArr2, eVarArr);
        } else if (b10 == 2) {
            int i20 = b.f18389e;
            long jA = a(objectInput);
            ZoneOffset zoneOffsetB = b(objectInput);
            ZoneOffset zoneOffsetB2 = b(objectInput);
            if (zoneOffsetB.equals(zoneOffsetB2)) {
                throw new IllegalArgumentException("Offsets must not be equal");
            }
            fVar = new b(jA, zoneOffsetB, zoneOffsetB2);
        } else if (b10 == 3) {
            fVar = e.a(objectInput);
        } else {
            if (b10 != 100) {
                throw new StreamCorruptedException("Unknown serialized type");
            }
            fVar = new f(TimeZone.getTimeZone(objectInput.readUTF()));
        }
        this.f18388b = fVar;
    }

    private Object readResolve() {
        return this.f18388b;
    }

    public static ZoneOffset b(DataInput dataInput) throws IOException {
        byte b10 = dataInput.readByte();
        return b10 == 127 ? ZoneOffset.d0(dataInput.readInt()) : ZoneOffset.d0(b10 * 900);
    }

    public static void c(long j10, DataOutput dataOutput) throws IOException {
        if (j10 >= -4575744000L && j10 < 10413792000L && j10 % 900 == 0) {
            int i10 = (int) ((j10 + 4575744000L) / 900);
            dataOutput.writeByte((i10 >>> 16) & 255);
            dataOutput.writeByte((i10 >>> 8) & 255);
            dataOutput.writeByte(i10 & 255);
            return;
        }
        dataOutput.writeByte(255);
        dataOutput.writeLong(j10);
    }

    public static long a(DataInput dataInput) {
        int i10 = dataInput.readByte() & 255;
        if (i10 == 255) {
            return dataInput.readLong();
        }
        return (((long) (((i10 << 16) + ((dataInput.readByte() & 255) << 8)) + (dataInput.readByte() & 255))) * 900) - 4575744000L;
    }

    public static void d(ZoneOffset zoneOffset, DataOutput dataOutput) throws IOException {
        int i10 = zoneOffset.f18126b;
        int i11 = i10 % 900 == 0 ? i10 / 900 : 127;
        dataOutput.writeByte(i11);
        if (i11 == 127) {
            dataOutput.writeInt(i10);
        }
    }
}
