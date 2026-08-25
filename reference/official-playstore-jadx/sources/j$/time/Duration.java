package j$.time;

import androidx.media3.common.C;
import io.ktor.util.date.GMTDateParser;
import j$.time.temporal.ChronoUnit;
import j$.time.temporal.Temporal;
import java.io.InvalidObjectException;
import java.io.ObjectInputStream;
import java.io.Serializable;
import java.math.BigInteger;

/* JADX INFO: loaded from: classes2.dex */
public final class Duration implements j$.time.temporal.n, Comparable<Duration>, Serializable {

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public static final Duration f18095c = new Duration(0, 0);
    private static final long serialVersionUID = 3078945930695997490L;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final long f18096a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final int f18097b;

    @Override // java.lang.Comparable
    public final int compareTo(Duration duration) {
        Duration duration2 = duration;
        int iCompare = Long.compare(this.f18096a, duration2.f18096a);
        return iCompare != 0 ? iCompare : this.f18097b - duration2.f18097b;
    }

    static {
        BigInteger.valueOf(C.NANOS_PER_SECOND);
    }

    public static Duration ofMillis(long j10) {
        long j11 = j10 / 1000;
        int i10 = (int) (j10 % 1000);
        if (i10 < 0) {
            i10 += 1000;
            j11--;
        }
        return m(j11, i10 * 1000000);
    }

    public static Duration n(long j10) {
        long j11 = j10 / C.NANOS_PER_SECOND;
        int i10 = (int) (j10 % C.NANOS_PER_SECOND);
        if (i10 < 0) {
            i10 = (int) (((long) i10) + C.NANOS_PER_SECOND);
            j11--;
        }
        return m(j11, i10);
    }

    public static Duration m(long j10, int i10) {
        if ((((long) i10) | j10) == 0) {
            return f18095c;
        }
        return new Duration(j10, i10);
    }

    public Duration(long j10, int i10) {
        this.f18096a = j10;
        this.f18097b = i10;
    }

    @Override // j$.time.temporal.n
    public final Temporal l(Temporal temporal) {
        long j10 = this.f18096a;
        if (j10 != 0) {
            temporal = temporal.d(j10, ChronoUnit.SECONDS);
        }
        int i10 = this.f18097b;
        return i10 != 0 ? temporal.d(i10, ChronoUnit.NANOS) : temporal;
    }

    public long toMillis() {
        long j10 = this.f18096a;
        long j11 = this.f18097b;
        if (j10 < 0) {
            j10++;
            j11 -= C.NANOS_PER_SECOND;
        }
        return j$.com.android.tools.r8.a.L(j$.com.android.tools.r8.a.R(j10, 1000), j11 / 1000000);
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof Duration) {
            Duration duration = (Duration) obj;
            if (this.f18096a == duration.f18096a && this.f18097b == duration.f18097b) {
                return true;
            }
        }
        return false;
    }

    public final int hashCode() {
        long j10 = this.f18096a;
        return (this.f18097b * 51) + ((int) (j10 ^ (j10 >>> 32)));
    }

    public final String toString() {
        if (this == f18095c) {
            return "PT0S";
        }
        long j10 = this.f18096a;
        if (j10 < 0 && this.f18097b > 0) {
            j10++;
        }
        long j11 = j10 / 3600;
        int i10 = (int) ((j10 % 3600) / 60);
        int i11 = (int) (j10 % 60);
        StringBuilder sb2 = new StringBuilder(24);
        sb2.append("PT");
        if (j11 != 0) {
            sb2.append(j11);
            sb2.append('H');
        }
        if (i10 != 0) {
            sb2.append(i10);
            sb2.append(GMTDateParser.MONTH);
        }
        if (i11 == 0 && this.f18097b == 0 && sb2.length() > 2) {
            return sb2.toString();
        }
        if (this.f18096a < 0 && this.f18097b > 0 && i11 == 0) {
            sb2.append("-0");
        } else {
            sb2.append(i11);
        }
        if (this.f18097b > 0) {
            int length = sb2.length();
            if (this.f18096a < 0) {
                sb2.append(2000000000 - ((long) this.f18097b));
            } else {
                sb2.append(((long) this.f18097b) + C.NANOS_PER_SECOND);
            }
            while (sb2.charAt(sb2.length() - 1) == '0') {
                sb2.setLength(sb2.length() - 1);
            }
            sb2.setCharAt(length, '.');
        }
        sb2.append('S');
        return sb2.toString();
    }

    private Object writeReplace() {
        return new o((byte) 1, this);
    }

    private void readObject(ObjectInputStream objectInputStream) throws InvalidObjectException {
        throw new InvalidObjectException("Deserialization via serialization delegate");
    }
}
