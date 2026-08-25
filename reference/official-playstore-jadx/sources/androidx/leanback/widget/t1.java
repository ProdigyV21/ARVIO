package androidx.leanback.widget;

/* JADX INFO: loaded from: classes3.dex */
public final class t1 {

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public int f3136c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public int f3137d;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public int f3142i;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public int f3143j;
    public int k;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public boolean f3144l;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public int f3138e = 2;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public int f3139f = 3;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public int f3140g = 0;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public float f3141h = 50.0f;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public int f3135b = Integer.MIN_VALUE;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public int f3134a = Integer.MAX_VALUE;

    public final int a() {
        if (this.f3144l) {
            int i10 = this.f3140g;
            int i11 = i10 >= 0 ? this.f3142i - i10 : -i10;
            float f10 = this.f3141h;
            return f10 != -1.0f ? i11 - ((int) ((this.f3142i * f10) / 100.0f)) : i11;
        }
        int i12 = this.f3140g;
        if (i12 < 0) {
            i12 += this.f3142i;
        }
        float f11 = this.f3141h;
        return f11 != -1.0f ? i12 + ((int) ((this.f3142i * f11) / 100.0f)) : i12;
    }

    public final int b(int i10) {
        int i11;
        int i12;
        int i13 = this.f3142i;
        int iA = a();
        int i14 = this.f3135b;
        boolean z = i14 == Integer.MIN_VALUE;
        int i15 = this.f3134a;
        boolean z5 = i15 == Integer.MAX_VALUE;
        if (!z) {
            int i16 = this.f3143j;
            int i17 = iA - i16;
            if (this.f3144l ? (this.f3139f & 2) != 0 : (this.f3139f & 1) != 0) {
                if (i10 - i14 <= i17) {
                    int i18 = i14 - i16;
                    return (z5 || i18 <= (i12 = this.f3136c)) ? i18 : i12;
                }
            }
        }
        if (!z5) {
            int i19 = this.k;
            int i20 = (i13 - iA) - i19;
            if (this.f3144l ? (1 & this.f3139f) != 0 : (this.f3139f & 2) != 0) {
                if (i15 - i10 <= i20) {
                    int i21 = i15 - (i13 - i19);
                    return (z || i21 >= (i11 = this.f3137d)) ? i21 : i11;
                }
            }
        }
        return i10 - iA;
    }

    /* JADX WARN: Code restructure failed: missing block: B:17:0x0035, code lost:
    
        r6.f3137d = r0 - r6.f3143j;
     */
    /* JADX WARN: Code restructure failed: missing block: B:27:0x0051, code lost:
    
        r6.f3136c = (r4 - r6.f3143j) - r7;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final void c(int r7, int r8, int r9, int r10) {
        /*
            Method dump skipped, instruction units count: 222
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.leanback.widget.t1.c(int, int, int, int):void");
    }

    public final String toString() {
        return " min:" + this.f3135b + " " + this.f3137d + " max:" + this.f3134a + " " + this.f3136c;
    }
}
