package kotlin.time;

import androidx.media3.exoplayer.upstream.CmcdData;
import io.ktor.util.date.GMTDateParser;
import io.sentry.util.l;
import kotlin.jvm.internal.p;
import kotlin.reflect.b0;
import kotlin.text.o;

/* JADX INFO: loaded from: classes5.dex */
public final class c implements Comparable {

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public static final l f19956l = new l(3);

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public static final long f19957m = b0.o(4611686018427387903L);

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public static final long f19958n = b0.o(-4611686018427387903L);

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    public static final long f19959o = 9223372036854759646L;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final long f19960i;

    public /* synthetic */ c(long j10) {
        this.f19960i = j10;
    }

    public static final long a(long j10, long j11) {
        long j12 = 1000000;
        long j13 = j11 / j12;
        long jD = b0.d(j10, j13);
        if (-4611686018426L > jD || jD >= 4611686018427L) {
            return b0.o(jD);
        }
        long j14 = ((jD * j12) + (j11 - (j13 * j12))) << 1;
        int i10 = d.f19961a;
        return j14;
    }

    public static final void d(StringBuilder sb2, int i10, int i11, int i12, String str, boolean z) {
        sb2.append(i10);
        if (i11 != 0) {
            sb2.append('.');
            String strO0 = o.o0(i12, String.valueOf(i11));
            int i13 = -1;
            int length = strO0.length() - 1;
            if (length >= 0) {
                while (true) {
                    int i14 = length - 1;
                    if (strO0.charAt(length) != '0') {
                        i13 = length;
                        break;
                    } else if (i14 < 0) {
                        break;
                    } else {
                        length = i14;
                    }
                }
            }
            int i15 = i13 + 1;
            if (z || i15 >= 3) {
                sb2.append((CharSequence) strO0, 0, ((i13 + 3) / 3) * 3);
            } else {
                sb2.append((CharSequence) strO0, 0, i15);
            }
        }
        sb2.append(str);
    }

    public static int f(long j10, long j11) {
        long j12 = j10 ^ j11;
        if (j12 < 0 || (((int) j12) & 1) == 0) {
            return p.d(j10, j11);
        }
        int i10 = (((int) j10) & 1) - (((int) j11) & 1);
        return j10 < 0 ? -i10 : i10;
    }

    public static final long i(long j10) {
        return ((((int) j10) & 1) != 1 || k(j10)) ? m(j10, e.MILLISECONDS) : j10 >> 1;
    }

    public static final int j(long j10) {
        if (k(j10)) {
            return 0;
        }
        return (int) ((((int) j10) & 1) == 1 ? ((j10 >> 1) % ((long) 1000)) * ((long) 1000000) : (j10 >> 1) % ((long) 1000000000));
    }

    public static final boolean k(long j10) {
        return j10 == f19957m || j10 == f19958n;
    }

    public static final long l(long j10, long j11) {
        int i10 = ((int) j10) & 1;
        if (i10 != (((int) j11) & 1)) {
            return i10 == 1 ? a(j10 >> 1, j11 >> 1) : a(j11 >> 1, j10 >> 1);
        }
        if (i10 == 0) {
            long j12 = (j10 >> 1) + (j11 >> 1);
            if (-4611686018426999999L > j12 || j12 >= 4611686018427000000L) {
                return b0.o(j12 / ((long) 1000000));
            }
            long j13 = j12 << 1;
            int i11 = d.f19961a;
            return j13;
        }
        long jD = b0.d(j10 >> 1, j11 >> 1);
        if (jD == 9223372036854759646L) {
            throw new IllegalArgumentException("Summing infinite durations of different signs yields an undefined result.");
        }
        if (jD == 4611686018427387903L || jD == -4611686018427387903L) {
            return b0.o(jD);
        }
        if (-4611686018426L > jD || jD >= 4611686018427L) {
            return b0.o(qb.d.o(jD, -4611686018427387903L, 4611686018427387903L));
        }
        long j14 = (jD * ((long) 1000000)) << 1;
        int i12 = d.f19961a;
        return j14;
    }

    public static final long m(long j10, e eVar) {
        if (j10 == f19957m) {
            return Long.MAX_VALUE;
        }
        if (j10 == f19958n) {
            return Long.MIN_VALUE;
        }
        return eVar.f19969i.convert(j10 >> 1, ((((int) j10) & 1) == 0 ? e.NANOSECONDS : e.MILLISECONDS).f19969i);
    }

    public static String n(long j10) {
        long j11;
        int iM;
        long j12 = j10;
        if (j12 == 0) {
            return "0s";
        }
        if (j12 == f19957m) {
            return "Infinity";
        }
        if (j12 == f19958n) {
            return "-Infinity";
        }
        int i10 = 0;
        boolean z = j12 < 0;
        StringBuilder sb2 = new StringBuilder();
        if (z) {
            sb2.append('-');
        }
        if (j12 < 0) {
            j12 = ((long) (((int) j12) & 1)) + ((-(j12 >> 1)) << 1);
            int i11 = d.f19961a;
        }
        long jM = m(j12, e.DAYS);
        int iM2 = k(j12) ? 0 : (int) (m(j12, e.HOURS) % ((long) 24));
        if (k(j12)) {
            j11 = 0;
            iM = 0;
        } else {
            j11 = 0;
            iM = (int) (m(j12, e.MINUTES) % ((long) 60));
        }
        int iM3 = k(j12) ? 0 : (int) (m(j12, e.SECONDS) % ((long) 60));
        int iJ = j(j12);
        boolean z5 = jM != j11;
        boolean z10 = iM2 != 0;
        boolean z11 = iM != 0;
        boolean z12 = (iM3 == 0 && iJ == 0) ? false : true;
        if (z5) {
            sb2.append(jM);
            sb2.append(GMTDateParser.DAY_OF_MONTH);
            i10 = 1;
        }
        if (z10 || (z5 && (z11 || z12))) {
            int i12 = i10 + 1;
            if (i10 > 0) {
                sb2.append(' ');
            }
            sb2.append(iM2);
            sb2.append(GMTDateParser.HOURS);
            i10 = i12;
        }
        if (z11 || (z12 && (z10 || z5))) {
            int i13 = i10 + 1;
            if (i10 > 0) {
                sb2.append(' ');
            }
            sb2.append(iM);
            sb2.append(GMTDateParser.MINUTES);
            i10 = i13;
        }
        if (z12) {
            int i14 = i10 + 1;
            if (i10 > 0) {
                sb2.append(' ');
            }
            if (iM3 != 0 || z5 || z10 || z11) {
                d(sb2, iM3, iJ, 9, CmcdData.STREAMING_FORMAT_SS, false);
            } else if (iJ >= 1000000) {
                d(sb2, iJ / 1000000, iJ % 1000000, 6, "ms", false);
            } else if (iJ >= 1000) {
                d(sb2, iJ / 1000, iJ % 1000, 3, "us", false);
            } else {
                sb2.append(iJ);
                sb2.append("ns");
            }
            i10 = i14;
        }
        if (z && i10 > 1) {
            sb2.insert(1, '(').append(')');
        }
        return sb2.toString();
    }

    @Override // java.lang.Comparable
    public final int compareTo(Object obj) {
        return f(this.f19960i, ((c) obj).f19960i);
    }

    public final boolean equals(Object obj) {
        if (obj instanceof c) {
            return this.f19960i == ((c) obj).f19960i;
        }
        return false;
    }

    public final int hashCode() {
        long j10 = this.f19960i;
        return (int) (j10 ^ (j10 >>> 32));
    }

    public final String toString() {
        return n(this.f19960i);
    }
}
