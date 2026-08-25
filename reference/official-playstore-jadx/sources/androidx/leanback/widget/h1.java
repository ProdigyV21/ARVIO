package androidx.leanback.widget;

/* JADX INFO: loaded from: classes3.dex */
public final class h1 extends t {

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public final r f3092j = new r(0);

    public h1() {
        n(1);
    }

    @Override // androidx.leanback.widget.t
    public final boolean b(int i10, boolean z) {
        int iMin;
        int iL;
        if (this.f3126b.H() == 0 || (!z && c(i10))) {
            return false;
        }
        int i11 = this.f3131g;
        if (i11 >= 0) {
            iMin = i11 + 1;
        } else {
            int i12 = this.f3133i;
            iMin = i12 != -1 ? Math.min(i12, this.f3126b.H() - 1) : 0;
        }
        int i13 = iMin;
        boolean z5 = false;
        while (i13 < this.f3126b.H()) {
            a8.e eVar = this.f3126b;
            Object[] objArr = this.f3125a;
            int iG = eVar.G(i13, true, objArr, false);
            if (this.f3130f < 0 || this.f3131g < 0) {
                iL = this.f3127c ? Integer.MAX_VALUE : Integer.MIN_VALUE;
                this.f3130f = i13;
                this.f3131g = i13;
            } else {
                if (this.f3127c) {
                    int i14 = i13 - 1;
                    iL = (this.f3126b.I(i14) - this.f3126b.L(i14)) - this.f3128d;
                } else {
                    int i15 = i13 - 1;
                    iL = this.f3128d + this.f3126b.L(i15) + this.f3126b.I(i15);
                }
                this.f3131g = i13;
            }
            this.f3126b.D(i13, objArr[0], iG, 0, iL);
            if (z || c(i10)) {
                return true;
            }
            i13++;
            z5 = true;
        }
        return z5;
    }

    @Override // androidx.leanback.widget.t
    public final void e(int i10, int i11, androidx.recyclerview.widget.s sVar) {
        int iO;
        int I;
        if (!this.f3127c ? i11 < 0 : i11 > 0) {
            if (this.f3131g == this.f3126b.H() - 1) {
                return;
            }
            int i12 = this.f3131g;
            if (i12 >= 0) {
                iO = i12 + 1;
            } else {
                int i13 = this.f3133i;
                iO = i13 != -1 ? Math.min(i13, this.f3126b.H() - 1) : 0;
            }
            int iL = this.f3126b.L(this.f3131g) + this.f3128d;
            int I2 = this.f3126b.I(this.f3131g);
            if (this.f3127c) {
                iL = -iL;
            }
            I = iL + I2;
        } else {
            if (this.f3130f == 0) {
                return;
            }
            iO = o();
            I = this.f3126b.I(this.f3130f) + (this.f3127c ? this.f3128d : -this.f3128d);
        }
        sVar.b(iO, Math.abs(I - i10));
    }

    @Override // androidx.leanback.widget.t
    public final int f(int i10, int[] iArr, boolean z) {
        if (iArr != null) {
            iArr[0] = 0;
            iArr[1] = i10;
        }
        if (this.f3127c) {
            return this.f3126b.I(i10);
        }
        return this.f3126b.L(i10) + this.f3126b.I(i10);
    }

    @Override // androidx.leanback.widget.t
    public final int h(int i10, int[] iArr, boolean z) {
        if (iArr != null) {
            iArr[0] = 0;
            iArr[1] = i10;
        }
        return this.f3127c ? this.f3126b.I(i10) - this.f3126b.L(i10) : this.f3126b.I(i10);
    }

    @Override // androidx.leanback.widget.t
    public final t.g[] j(int i10, int i11) {
        t.g gVar = this.f3132h[0];
        gVar.f21815c = gVar.f21814b;
        gVar.a(i10);
        this.f3132h[0].a(i11);
        return this.f3132h;
    }

    @Override // androidx.leanback.widget.t
    public final r k(int i10) {
        return this.f3092j;
    }

    @Override // androidx.leanback.widget.t
    public final boolean m(int i10, boolean z) {
        int I;
        if (this.f3126b.H() == 0 || (!z && d(i10))) {
            return false;
        }
        int i11 = ((a0) this.f3126b.f183l).f3073w;
        boolean z5 = false;
        for (int iO = o(); iO >= i11; iO--) {
            a8.e eVar = this.f3126b;
            Object[] objArr = this.f3125a;
            int iG = eVar.G(iO, false, objArr, false);
            if (this.f3130f < 0 || this.f3131g < 0) {
                I = this.f3127c ? Integer.MIN_VALUE : Integer.MAX_VALUE;
                this.f3130f = iO;
                this.f3131g = iO;
            } else {
                I = this.f3127c ? this.f3126b.I(iO + 1) + this.f3128d + iG : (this.f3126b.I(iO + 1) - this.f3128d) - iG;
                this.f3130f = iO;
            }
            this.f3126b.D(iO, objArr[0], iG, 0, I);
            z5 = true;
            if (z || d(i10)) {
                break;
            }
        }
        return z5;
    }

    public final int o() {
        int i10 = this.f3130f;
        if (i10 >= 0) {
            return i10 - 1;
        }
        int i11 = this.f3133i;
        return i11 != -1 ? Math.min(i11, this.f3126b.H() - 1) : this.f3126b.H() - 1;
    }
}
