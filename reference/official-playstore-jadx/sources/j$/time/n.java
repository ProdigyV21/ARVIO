package j$.time;

import io.ktor.util.date.GMTDateParser;
import j$.time.chrono.Chronology;
import j$.time.temporal.ChronoUnit;
import j$.time.temporal.Temporal;
import j$.util.Objects;
import java.io.InvalidObjectException;
import java.io.ObjectInputStream;
import java.io.Serializable;
import java.util.regex.Pattern;

/* JADX INFO: loaded from: classes2.dex */
public final class n implements j$.time.temporal.n, Serializable {

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public static final n f18319d = new n(0, 0, 0);
    private static final long serialVersionUID = -3587258372562876L;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final int f18320a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final int f18321b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final int f18322c;

    static {
        Pattern.compile("([-+]?)P(?:([-+]?[0-9]+)Y)?(?:([-+]?[0-9]+)M)?(?:([-+]?[0-9]+)W)?(?:([-+]?[0-9]+)D)?", 2);
        j$.com.android.tools.r8.a.M(new Object[]{ChronoUnit.YEARS, ChronoUnit.MONTHS, ChronoUnit.DAYS});
    }

    public static n a(int i10, int i11, int i12) {
        if ((i10 | i11 | i12) == 0) {
            return f18319d;
        }
        return new n(i10, i11, i12);
    }

    public n(int i10, int i11, int i12) {
        this.f18320a = i10;
        this.f18321b = i11;
        this.f18322c = i12;
    }

    @Override // j$.time.temporal.n
    public final Temporal l(Temporal temporal) {
        Objects.requireNonNull(temporal, "temporal");
        Chronology chronology = (Chronology) temporal.G(j$.time.temporal.p.f18367b);
        if (chronology == null || j$.time.chrono.p.f18175d.equals(chronology)) {
            int i10 = this.f18321b;
            if (i10 != 0) {
                long j10 = (((long) this.f18320a) * 12) + ((long) i10);
                if (j10 != 0) {
                    temporal = temporal.d(j10, ChronoUnit.MONTHS);
                }
            } else {
                int i11 = this.f18320a;
                if (i11 != 0) {
                    temporal = temporal.d(i11, ChronoUnit.YEARS);
                }
            }
            int i12 = this.f18322c;
            return i12 != 0 ? temporal.d(i12, ChronoUnit.DAYS) : temporal;
        }
        throw new DateTimeException("Chronology mismatch, expected: ISO, actual: " + chronology.k());
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof n) {
            n nVar = (n) obj;
            if (this.f18320a == nVar.f18320a && this.f18321b == nVar.f18321b && this.f18322c == nVar.f18322c) {
                return true;
            }
        }
        return false;
    }

    public final int hashCode() {
        return Integer.rotateLeft(this.f18322c, 16) + Integer.rotateLeft(this.f18321b, 8) + this.f18320a;
    }

    public final String toString() {
        if (this == f18319d) {
            return "P0D";
        }
        StringBuilder sb2 = new StringBuilder("P");
        int i10 = this.f18320a;
        if (i10 != 0) {
            sb2.append(i10);
            sb2.append(GMTDateParser.YEAR);
        }
        int i11 = this.f18321b;
        if (i11 != 0) {
            sb2.append(i11);
            sb2.append(GMTDateParser.MONTH);
        }
        int i12 = this.f18322c;
        if (i12 != 0) {
            sb2.append(i12);
            sb2.append('D');
        }
        return sb2.toString();
    }

    private Object writeReplace() {
        return new o((byte) 14, this);
    }

    private void readObject(ObjectInputStream objectInputStream) throws InvalidObjectException {
        throw new InvalidObjectException("Deserialization via serialization delegate");
    }
}
