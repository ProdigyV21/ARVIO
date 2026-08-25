package j$.time.format;

import androidx.media3.common.C;
import androidx.media3.exoplayer.audio.SilenceSkippingAudioProcessor;
import j$.time.DateTimeException;

/* JADX INFO: loaded from: classes2.dex */
public class i implements e {

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public static final long[] f18247f = {0, 10, 100, 1000, 10000, SilenceSkippingAudioProcessor.DEFAULT_MINIMUM_SILENCE_DURATION_US, 1000000, 10000000, 100000000, C.NANOS_PER_SECOND, 10000000000L};

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final j$.time.temporal.o f18248a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final int f18249b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final int f18250c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final c0 f18251d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final int f18252e;

    public long a(w wVar, long j10) {
        return j10;
    }

    public i(j$.time.temporal.o oVar, int i10, int i11, c0 c0Var) {
        this.f18248a = oVar;
        this.f18249b = i10;
        this.f18250c = i11;
        this.f18251d = c0Var;
        this.f18252e = 0;
    }

    public i(j$.time.temporal.o oVar, int i10, int i11, c0 c0Var, int i12) {
        this.f18248a = oVar;
        this.f18249b = i10;
        this.f18250c = i11;
        this.f18251d = c0Var;
        this.f18252e = i12;
    }

    public i d() {
        if (this.f18252e == -1) {
            return this;
        }
        return new i(this.f18248a, this.f18249b, this.f18250c, this.f18251d, -1);
    }

    public i e(int i10) {
        return new i(this.f18248a, this.f18249b, this.f18250c, this.f18251d, this.f18252e + i10);
    }

    @Override // j$.time.format.e
    public boolean l(w wVar, StringBuilder sb2) {
        j$.time.temporal.o oVar = this.f18248a;
        Long lA = wVar.a(oVar);
        if (lA == null) {
            return false;
        }
        long jA = a(wVar, lA.longValue());
        DecimalStyle decimalStyle = wVar.f18300b.f18201c;
        String string = jA == Long.MIN_VALUE ? "9223372036854775808" : Long.toString(Math.abs(jA));
        int length = string.length();
        int i10 = this.f18250c;
        if (length > i10) {
            throw new DateTimeException("Field " + oVar + " cannot be printed as the value " + jA + " exceeds the maximum print width of " + i10);
        }
        String strA = decimalStyle.a(string);
        int i11 = this.f18249b;
        c0 c0Var = this.f18251d;
        if (jA >= 0) {
            int i12 = b.f18231a[c0Var.ordinal()];
            if (i12 != 1) {
                if (i12 == 2) {
                    sb2.append('+');
                }
            } else if (i11 < 19 && jA >= f18247f[i11]) {
                sb2.append('+');
            }
        } else {
            int i13 = b.f18231a[c0Var.ordinal()];
            if (i13 == 1 || i13 == 2 || i13 == 3) {
                sb2.append(decimalStyle.f18217b);
            } else if (i13 == 4) {
                throw new DateTimeException("Field " + oVar + " cannot be printed as the value " + jA + " cannot be negative according to the SignStyle");
            }
        }
        for (int i14 = 0; i14 < i11 - strA.length(); i14++) {
            sb2.append(decimalStyle.f18216a);
        }
        sb2.append(strA);
        return true;
    }

    public boolean b(u uVar) {
        int i10 = this.f18252e;
        if (i10 != -1) {
            return i10 > 0 && this.f18249b == this.f18250c && this.f18251d == c0.NOT_NEGATIVE;
        }
        return true;
    }

    /* JADX WARN: Code restructure failed: missing block: B:113:0x0169, code lost:
    
        if (r7 <= r11) goto L98;
     */
    /* JADX WARN: Removed duplicated region for block: B:119:0x0172  */
    /* JADX WARN: Removed duplicated region for block: B:124:0x0192  */
    @Override // j$.time.format.e
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public int m(j$.time.format.u r29, java.lang.CharSequence r30, int r31) {
        /*
            Method dump skipped, instruction units count: 414
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: j$.time.format.i.m(j$.time.format.u, java.lang.CharSequence, int):int");
    }

    public int c(u uVar, long j10, int i10, int i11) {
        return uVar.f(this.f18248a, j10, i10, i11);
    }

    public String toString() {
        int i10 = this.f18250c;
        j$.time.temporal.o oVar = this.f18248a;
        c0 c0Var = this.f18251d;
        int i11 = this.f18249b;
        if (i11 == 1 && i10 == 19 && c0Var == c0.NORMAL) {
            return "Value(" + oVar + ")";
        }
        if (i11 == i10 && c0Var == c0.NOT_NEGATIVE) {
            return "Value(" + oVar + "," + i11 + ")";
        }
        return "Value(" + oVar + "," + i11 + "," + i10 + "," + c0Var + ")";
    }
}
