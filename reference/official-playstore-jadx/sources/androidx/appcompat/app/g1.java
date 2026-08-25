package androidx.appcompat.app;

/* JADX INFO: loaded from: classes.dex */
public final class g1 {

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public static g1 f1050d;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public int f1051a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public long f1052b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public long f1053c;

    public void a(long j10, double d4, double d10) {
        double d11 = (0.01720197f * ((j10 - 946728000000L) / 8.64E7f)) + 6.24006f;
        double dSin = (Math.sin(r3 * 3.0f) * 5.236000106378924E-6d) + (Math.sin(2.0f * r3) * 3.4906598739326E-4d) + (Math.sin(d11) * 0.03341960161924362d) + d11 + 1.796593063d + 3.141592653589793d;
        double dSin2 = (Math.sin(2.0d * dSin) * (-0.0069d)) + (Math.sin(d11) * 0.0053d) + ((double) (Math.round(((double) (r2 - 9.0E-4f)) - r6) + 9.0E-4f)) + ((-d10) / 360.0d);
        double dAsin = Math.asin(Math.sin(0.4092797040939331d) * Math.sin(dSin));
        double d12 = 0.01745329238474369d * d4;
        double dSin3 = (Math.sin(-0.10471975803375244d) - (Math.sin(dAsin) * Math.sin(d12))) / (Math.cos(dAsin) * Math.cos(d12));
        if (dSin3 >= 1.0d) {
            this.f1051a = 1;
            this.f1052b = -1L;
            this.f1053c = -1L;
        } else {
            if (dSin3 <= -1.0d) {
                this.f1051a = 0;
                this.f1052b = -1L;
                this.f1053c = -1L;
                return;
            }
            double dAcos = (float) (Math.acos(dSin3) / 6.283185307179586d);
            this.f1052b = Math.round((dSin2 + dAcos) * 8.64E7d) + 946728000000L;
            long jRound = Math.round((dSin2 - dAcos) * 8.64E7d) + 946728000000L;
            this.f1053c = jRound;
            if (jRound >= j10 || this.f1052b <= j10) {
                this.f1051a = 1;
            } else {
                this.f1051a = 0;
            }
        }
    }
}
