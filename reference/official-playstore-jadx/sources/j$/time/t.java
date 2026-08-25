package j$.time;

import j$.util.Objects;
import java.io.DataOutput;
import java.io.IOException;
import java.io.InvalidObjectException;
import java.io.ObjectInputStream;

/* JADX INFO: loaded from: classes2.dex */
public final class t extends ZoneId {

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public static final /* synthetic */ int f18334d = 0;
    private static final long serialVersionUID = 8386373296231747096L;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final String f18335b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final transient j$.time.zone.f f18336c;

    public static t a0(String str, boolean z) {
        j$.time.zone.f fVarA;
        Objects.requireNonNull(str, "zoneId");
        int length = str.length();
        if (length >= 2) {
            for (int i10 = 0; i10 < length; i10++) {
                char cCharAt = str.charAt(i10);
                if ((cCharAt < 'a' || cCharAt > 'z') && ((cCharAt < 'A' || cCharAt > 'Z') && ((cCharAt != '/' || i10 == 0) && ((cCharAt < '0' || cCharAt > '9' || i10 == 0) && ((cCharAt != '~' || i10 == 0) && ((cCharAt != '.' || i10 == 0) && ((cCharAt != '_' || i10 == 0) && ((cCharAt != '+' || i10 == 0) && (cCharAt != '-' || i10 == 0))))))))) {
                    throw new DateTimeException("Invalid ID for region-based ZoneId, invalid format: ".concat(str));
                }
            }
            try {
                fVarA = j$.time.zone.i.a(str);
            } catch (j$.time.zone.g e5) {
                if (z) {
                    throw e5;
                }
                fVarA = null;
            }
            return new t(str, fVarA);
        }
        throw new DateTimeException("Invalid ID for region-based ZoneId, invalid format: ".concat(str));
    }

    public t(String str, j$.time.zone.f fVar) {
        this.f18335b = str;
        this.f18336c = fVar;
    }

    @Override // j$.time.ZoneId
    public final String k() {
        return this.f18335b;
    }

    @Override // j$.time.ZoneId
    public final j$.time.zone.f V() {
        j$.time.zone.f fVar = this.f18336c;
        return fVar != null ? fVar : j$.time.zone.i.a(this.f18335b);
    }

    private Object writeReplace() {
        return new o((byte) 7, this);
    }

    private void readObject(ObjectInputStream objectInputStream) throws InvalidObjectException {
        throw new InvalidObjectException("Deserialization via serialization delegate");
    }

    @Override // j$.time.ZoneId
    public final void Z(DataOutput dataOutput) throws IOException {
        dataOutput.writeByte(7);
        dataOutput.writeUTF(this.f18335b);
    }
}
