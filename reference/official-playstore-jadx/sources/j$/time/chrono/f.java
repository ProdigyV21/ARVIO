package j$.time.chrono;

import io.ktor.util.date.GMTDateParser;
import j$.time.DateTimeException;
import j$.time.temporal.ChronoUnit;
import j$.time.temporal.Temporal;
import j$.util.Objects;
import java.io.InvalidObjectException;
import java.io.ObjectInputStream;
import java.io.Serializable;

/* JADX INFO: loaded from: classes2.dex */
public final class f implements j$.time.temporal.n, Serializable {

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public static final /* synthetic */ int f18147e = 0;
    private static final long serialVersionUID = 57387258289L;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final Chronology f18148a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final int f18149b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final int f18150c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final int f18151d;

    static {
        j$.com.android.tools.r8.a.M(new Object[]{ChronoUnit.YEARS, ChronoUnit.MONTHS, ChronoUnit.DAYS});
    }

    public f(Chronology chronology, int i10, int i11, int i12) {
        Objects.requireNonNull(chronology, "chrono");
        this.f18148a = chronology;
        this.f18149b = i10;
        this.f18150c = i11;
        this.f18151d = i12;
    }

    public final String toString() {
        if (this.f18149b == 0 && this.f18150c == 0 && this.f18151d == 0) {
            return this.f18148a.toString() + " P0D";
        }
        StringBuilder sb2 = new StringBuilder();
        sb2.append(this.f18148a.toString());
        sb2.append(" P");
        int i10 = this.f18149b;
        if (i10 != 0) {
            sb2.append(i10);
            sb2.append(GMTDateParser.YEAR);
        }
        int i11 = this.f18150c;
        if (i11 != 0) {
            sb2.append(i11);
            sb2.append(GMTDateParser.MONTH);
        }
        int i12 = this.f18151d;
        if (i12 != 0) {
            sb2.append(i12);
            sb2.append('D');
        }
        return sb2.toString();
    }

    @Override // j$.time.temporal.n
    public final Temporal l(Temporal temporal) {
        Objects.requireNonNull(temporal, "temporal");
        Chronology chronology = (Chronology) temporal.G(j$.time.temporal.p.f18367b);
        if (chronology == null || this.f18148a.equals(chronology)) {
            if (this.f18150c != 0) {
                j$.time.temporal.s sVarU = this.f18148a.u(j$.time.temporal.a.MONTH_OF_YEAR);
                long j10 = (sVarU.f18373a == sVarU.f18374b && sVarU.f18375c == sVarU.f18376d && sVarU.d()) ? (sVarU.f18376d - sVarU.f18373a) + 1 : -1L;
                if (j10 > 0) {
                    temporal = temporal.d((((long) this.f18149b) * j10) + ((long) this.f18150c), ChronoUnit.MONTHS);
                } else {
                    int i10 = this.f18149b;
                    if (i10 != 0) {
                        temporal = temporal.d(i10, ChronoUnit.YEARS);
                    }
                    temporal = temporal.d(this.f18150c, ChronoUnit.MONTHS);
                }
            } else {
                int i11 = this.f18149b;
                if (i11 != 0) {
                    temporal = temporal.d(i11, ChronoUnit.YEARS);
                }
            }
            int i12 = this.f18151d;
            return i12 != 0 ? temporal.d(i12, ChronoUnit.DAYS) : temporal;
        }
        throw new DateTimeException("Chronology mismatch, expected: " + this.f18148a.k() + ", actual: " + chronology.k());
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof f) {
            f fVar = (f) obj;
            if (this.f18149b == fVar.f18149b && this.f18150c == fVar.f18150c && this.f18151d == fVar.f18151d && this.f18148a.equals(fVar.f18148a)) {
                return true;
            }
        }
        return false;
    }

    public final int hashCode() {
        return (Integer.rotateLeft(this.f18151d, 16) + (Integer.rotateLeft(this.f18150c, 8) + this.f18149b)) ^ this.f18148a.hashCode();
    }

    public Object writeReplace() {
        return new b0((byte) 9, this);
    }

    private void readObject(ObjectInputStream objectInputStream) throws InvalidObjectException {
        throw new InvalidObjectException("Deserialization via serialization delegate");
    }
}
