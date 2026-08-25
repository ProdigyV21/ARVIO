package j$.time.chrono;

import j$.time.LocalDate;
import j$.time.LocalTime;
import j$.time.ZoneId;
import j$.time.ZoneOffset;
import j$.time.chrono.Chronology;
import j$.util.concurrent.ConcurrentHashMap;
import java.io.Externalizable;
import java.io.IOException;
import java.io.InvalidClassException;
import java.io.ObjectInput;
import java.io.ObjectOutput;
import java.io.StreamCorruptedException;

/* JADX INFO: loaded from: classes2.dex */
public final class b0 implements Externalizable {
    private static final long serialVersionUID = -6103370247208168577L;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public byte f18139a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public Object f18140b;

    public b0() {
    }

    public b0(byte b10, Object obj) {
        this.f18139a = b10;
        this.f18140b = obj;
    }

    @Override // java.io.Externalizable
    public final void writeExternal(ObjectOutput objectOutput) throws IOException {
        byte b10 = this.f18139a;
        Object obj = this.f18140b;
        objectOutput.writeByte(b10);
        switch (b10) {
            case 1:
                objectOutput.writeUTF(((a) obj).k());
                return;
            case 2:
                e eVar = (e) obj;
                objectOutput.writeObject(eVar.f18144a);
                objectOutput.writeObject(eVar.f18145b);
                return;
            case 3:
                i iVar = (i) obj;
                objectOutput.writeObject(iVar.f18156a);
                objectOutput.writeObject(iVar.f18157b);
                objectOutput.writeObject(iVar.f18158c);
                return;
            case 4:
                u uVar = (u) obj;
                uVar.getClass();
                objectOutput.writeInt(j$.time.temporal.p.a(uVar, j$.time.temporal.a.YEAR));
                objectOutput.writeByte(j$.time.temporal.p.a(uVar, j$.time.temporal.a.MONTH_OF_YEAR));
                objectOutput.writeByte(j$.time.temporal.p.a(uVar, j$.time.temporal.a.DAY_OF_MONTH));
                return;
            case 5:
                objectOutput.writeByte(((v) obj).f18186a);
                return;
            case 6:
                n nVar = (n) obj;
                objectOutput.writeObject(nVar.f18170a);
                objectOutput.writeInt(j$.time.temporal.p.a(nVar, j$.time.temporal.a.YEAR));
                objectOutput.writeByte(j$.time.temporal.p.a(nVar, j$.time.temporal.a.MONTH_OF_YEAR));
                objectOutput.writeByte(j$.time.temporal.p.a(nVar, j$.time.temporal.a.DAY_OF_MONTH));
                return;
            case 7:
                z zVar = (z) obj;
                zVar.getClass();
                objectOutput.writeInt(j$.time.temporal.p.a(zVar, j$.time.temporal.a.YEAR));
                objectOutput.writeByte(j$.time.temporal.p.a(zVar, j$.time.temporal.a.MONTH_OF_YEAR));
                objectOutput.writeByte(j$.time.temporal.p.a(zVar, j$.time.temporal.a.DAY_OF_MONTH));
                return;
            case 8:
                f0 f0Var = (f0) obj;
                f0Var.getClass();
                objectOutput.writeInt(j$.time.temporal.p.a(f0Var, j$.time.temporal.a.YEAR));
                objectOutput.writeByte(j$.time.temporal.p.a(f0Var, j$.time.temporal.a.MONTH_OF_YEAR));
                objectOutput.writeByte(j$.time.temporal.p.a(f0Var, j$.time.temporal.a.DAY_OF_MONTH));
                return;
            case 9:
                f fVar = (f) obj;
                objectOutput.writeUTF(fVar.f18148a.k());
                objectOutput.writeInt(fVar.f18149b);
                objectOutput.writeInt(fVar.f18150c);
                objectOutput.writeInt(fVar.f18151d);
                return;
            default:
                throw new InvalidClassException("Unknown serialized type");
        }
    }

    @Override // java.io.Externalizable
    public final void readExternal(ObjectInput objectInput) throws IOException {
        Object objB;
        byte b10 = objectInput.readByte();
        this.f18139a = b10;
        switch (b10) {
            case 1:
                ConcurrentHashMap concurrentHashMap = a.f18134a;
                objB = Chronology.CC.b(objectInput.readUTF());
                break;
            case 2:
                objB = ((ChronoLocalDate) objectInput.readObject()).J((LocalTime) objectInput.readObject());
                break;
            case 3:
                objB = ((ChronoLocalDateTime) objectInput.readObject()).D((ZoneOffset) objectInput.readObject()).A((ZoneId) objectInput.readObject());
                break;
            case 4:
                LocalDate localDate = u.f18180d;
                int i10 = objectInput.readInt();
                byte b11 = objectInput.readByte();
                byte b12 = objectInput.readByte();
                s.f18178d.getClass();
                objB = new u(LocalDate.of(i10, b11, b12));
                break;
            case 5:
                v vVar = v.f18184d;
                objB = v.o(objectInput.readByte());
                break;
            case 6:
                l lVar = (l) objectInput.readObject();
                int i11 = objectInput.readInt();
                byte b13 = objectInput.readByte();
                byte b14 = objectInput.readByte();
                lVar.getClass();
                objB = new n(lVar, i11, b13, b14);
                break;
            case 7:
                int i12 = objectInput.readInt();
                byte b15 = objectInput.readByte();
                byte b16 = objectInput.readByte();
                x.f18190d.getClass();
                objB = new z(LocalDate.of(i12 + 1911, b15, b16));
                break;
            case 8:
                int i13 = objectInput.readInt();
                byte b17 = objectInput.readByte();
                byte b18 = objectInput.readByte();
                d0.f18143d.getClass();
                objB = new f0(LocalDate.of(i13 - 543, b17, b18));
                break;
            case 9:
                int i14 = f.f18147e;
                objB = new f(Chronology.CC.b(objectInput.readUTF()), objectInput.readInt(), objectInput.readInt(), objectInput.readInt());
                break;
            default:
                throw new StreamCorruptedException("Unknown serialized type");
        }
        this.f18140b = objB;
    }

    private Object readResolve() {
        return this.f18140b;
    }
}
