package androidx.leanback.widget;

/* JADX INFO: loaded from: classes3.dex */
public final class k1 extends t {

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public androidx.recyclerview.widget.s f3096j;
    public int k;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public Object f3097l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public int f3098m;

    @Override // androidx.leanback.widget.t
    public final boolean b(int i10, boolean z) {
        Object[] objArr = this.f3125a;
        if (this.f3126b.H() == 0 || (!z && c(i10))) {
            return false;
        }
        try {
            if (!o(i10, z)) {
                return q(i10, z);
            }
            objArr[0] = null;
            this.f3097l = null;
            return true;
        } finally {
            objArr[0] = null;
            this.f3097l = null;
        }
    }

    @Override // androidx.leanback.widget.t
    public final int f(int i10, int[] iArr, boolean z) {
        int i11;
        int I = this.f3126b.I(i10);
        j1 j1VarK = k(i10);
        int i12 = j1VarK.f3109a;
        if (this.f3127c) {
            i11 = i12;
            int i13 = i11;
            int i14 = 1;
            int i15 = I;
            for (int i16 = i10 + 1; i14 < this.f3129e && i16 <= this.f3131g; i16++) {
                j1 j1VarK2 = k(i16);
                i15 += j1VarK2.f3094b;
                int i17 = j1VarK2.f3109a;
                if (i17 != i13) {
                    i14++;
                    if (!z ? i15 >= I : i15 <= I) {
                        i13 = i17;
                    } else {
                        I = i15;
                        i10 = i16;
                        i11 = i17;
                        i13 = i11;
                    }
                }
            }
        } else {
            int i18 = 1;
            int i19 = i12;
            j1 j1VarK3 = j1VarK;
            int i20 = I;
            I = this.f3126b.L(i10) + I;
            i11 = i19;
            for (int i21 = i10 - 1; i18 < this.f3129e && i21 >= this.f3130f; i21--) {
                i20 -= j1VarK3.f3094b;
                j1VarK3 = k(i21);
                int i22 = j1VarK3.f3109a;
                if (i22 != i19) {
                    i18++;
                    int iL = this.f3126b.L(i21) + i20;
                    if (!z ? iL >= I : iL <= I) {
                        i19 = i22;
                    } else {
                        I = iL;
                        i10 = i21;
                        i11 = i22;
                        i19 = i11;
                    }
                }
            }
        }
        if (iArr != null) {
            iArr[0] = i11;
            iArr[1] = i10;
        }
        return I;
    }

    @Override // androidx.leanback.widget.t
    public final int h(int i10, int[] iArr, boolean z) {
        int iL;
        int I = this.f3126b.I(i10);
        j1 j1VarK = k(i10);
        int i11 = j1VarK.f3109a;
        if (this.f3127c) {
            int i12 = 1;
            iL = I - this.f3126b.L(i10);
            int i13 = i11;
            for (int i14 = i10 - 1; i12 < this.f3129e && i14 >= this.f3130f; i14--) {
                I -= j1VarK.f3094b;
                j1VarK = k(i14);
                int i15 = j1VarK.f3109a;
                if (i15 != i13) {
                    i12++;
                    int iL2 = I - this.f3126b.L(i14);
                    if (!z ? iL2 >= iL : iL2 <= iL) {
                        i13 = i15;
                    } else {
                        iL = iL2;
                        i10 = i14;
                        i11 = i15;
                        i13 = i11;
                    }
                }
            }
        } else {
            int i16 = i11;
            int i17 = i16;
            int i18 = 1;
            int i19 = I;
            for (int i20 = i10 + 1; i18 < this.f3129e && i20 <= this.f3131g; i20++) {
                j1 j1VarK2 = k(i20);
                i19 += j1VarK2.f3094b;
                int i21 = j1VarK2.f3109a;
                if (i21 != i17) {
                    i18++;
                    if (!z ? i19 >= I : i19 <= I) {
                        i17 = i21;
                    } else {
                        I = i19;
                        i10 = i20;
                        i16 = i21;
                        i17 = i16;
                    }
                }
            }
            iL = I;
            i11 = i16;
        }
        if (iArr != null) {
            iArr[0] = i11;
            iArr[1] = i10;
        }
        return iL;
    }

    /* JADX WARN: Removed duplicated region for block: B:20:0x0051  */
    @Override // androidx.leanback.widget.t
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final t.g[] j(int r8, int r9) {
        /*
            r7 = this;
            r0 = 0
        L1:
            int r1 = r7.f3129e
            if (r0 >= r1) goto L10
            t.g[] r1 = r7.f3132h
            r1 = r1[r0]
            int r2 = r1.f21814b
            r1.f21815c = r2
            int r0 = r0 + 1
            goto L1
        L10:
            if (r8 < 0) goto L5a
        L12:
            if (r8 > r9) goto L5a
            t.g[] r0 = r7.f3132h
            androidx.leanback.widget.j1 r1 = r7.k(r8)
            int r1 = r1.f3109a
            r0 = r0[r1]
            int r1 = r0.c()
            if (r1 <= 0) goto L51
            int r1 = r0.f21814b
            int r2 = r0.f21815c
            if (r1 == r2) goto L4b
            int[] r3 = r0.f21813a
            int r4 = r2 + (-1)
            int r5 = r0.f21816d
            r4 = r4 & r5
            r4 = r3[r4]
            int r6 = r8 + (-1)
            if (r4 != r6) goto L51
            if (r1 == r2) goto L45
            int r2 = r2 + (-1)
            r1 = r2 & r5
            r2 = r3[r1]
            r0.f21815c = r1
            r0.a(r8)
            goto L57
        L45:
            java.lang.ArrayIndexOutOfBoundsException r8 = new java.lang.ArrayIndexOutOfBoundsException
            r8.<init>()
            throw r8
        L4b:
            java.lang.ArrayIndexOutOfBoundsException r8 = new java.lang.ArrayIndexOutOfBoundsException
            r8.<init>()
            throw r8
        L51:
            r0.a(r8)
            r0.a(r8)
        L57:
            int r8 = r8 + 1
            goto L12
        L5a:
            t.g[] r8 = r7.f3132h
            return r8
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.leanback.widget.k1.j(int, int):t.g[]");
    }

    @Override // androidx.leanback.widget.t
    public final void l(int i10) {
        super.l(i10);
        androidx.recyclerview.widget.s sVar = this.f3096j;
        sVar.e((s() - i10) + 1);
        if (sVar.g() == 0) {
            this.k = -1;
        }
    }

    @Override // androidx.leanback.widget.t
    public final boolean m(int i10, boolean z) {
        Object[] objArr = this.f3125a;
        if (this.f3126b.H() == 0 || (!z && d(i10))) {
            return false;
        }
        try {
            if (!w(i10, z)) {
                return y(i10, z);
            }
            objArr[0] = null;
            this.f3097l = null;
            return true;
        } finally {
            objArr[0] = null;
            this.f3097l = null;
        }
    }

    public final boolean o(int i10, boolean z) {
        int i11;
        int I;
        int i12;
        androidx.recyclerview.widget.s sVar = this.f3096j;
        if (sVar.g() != 0) {
            int iH = this.f3126b.H();
            int i13 = this.f3131g;
            if (i13 >= 0) {
                i11 = i13 + 1;
                I = this.f3126b.I(i13);
            } else {
                int i14 = this.f3133i;
                i11 = i14 != -1 ? i14 : 0;
                if (i11 > s() + 1 || i11 < this.k) {
                    sVar.f(sVar.g());
                    return false;
                }
                if (i11 <= s()) {
                    I = Integer.MAX_VALUE;
                }
            }
            int i15 = i11;
            int iS = s();
            while (i15 < iH && i15 <= iS) {
                j1 j1VarK = k(i15);
                if (I != Integer.MAX_VALUE) {
                    I += j1VarK.f3094b;
                }
                int i16 = I;
                int i17 = j1VarK.f3109a;
                a8.e eVar = this.f3126b;
                Object[] objArr = this.f3125a;
                int iG = eVar.G(i15, true, objArr, false);
                if (iG != j1VarK.f3095c) {
                    j1VarK.f3095c = iG;
                    sVar.e(iS - i15);
                    i12 = i15;
                } else {
                    i12 = iS;
                }
                this.f3131g = i15;
                if (this.f3130f < 0) {
                    this.f3130f = i15;
                }
                this.f3126b.D(i15, objArr[0], iG, i17, i16);
                if (z || !c(i10)) {
                    I = i16 == Integer.MAX_VALUE ? this.f3126b.I(i15) : i16;
                    if (i17 != this.f3129e - 1 || !z) {
                        i15++;
                        iS = i12;
                    }
                }
                return true;
            }
        }
        return false;
    }

    public final int p(int i10, int i11, int i12) {
        int I;
        androidx.recyclerview.widget.s sVar = this.f3096j;
        int i13 = this.f3131g;
        if (i13 >= 0 && (i13 != s() || this.f3131g != i10 - 1)) {
            throw new IllegalStateException();
        }
        int i14 = this.f3131g;
        if (i14 >= 0) {
            I = i12 - this.f3126b.I(i14);
        } else if (sVar.g() <= 0 || i10 != s() + 1) {
            I = 0;
        } else {
            int iS = s();
            while (true) {
                if (iS < this.k) {
                    iS = s();
                    break;
                }
                if (k(iS).f3109a == i11) {
                    break;
                }
                iS--;
            }
            I = this.f3127c ? (-k(iS).f3095c) - this.f3128d : k(iS).f3095c + this.f3128d;
            for (int i15 = iS + 1; i15 <= s(); i15++) {
                I -= k(i15).f3094b;
            }
        }
        j1 j1Var = new j1(i11, I);
        Object[] objArr = (Object[]) sVar.f5043d;
        int i16 = sVar.f5041b;
        objArr[i16] = j1Var;
        int i17 = sVar.f5042c & (i16 + 1);
        sVar.f5041b = i17;
        if (i17 == sVar.f5040a) {
            sVar.d();
        }
        Object obj = this.f3097l;
        if (obj != null) {
            j1Var.f3095c = this.f3098m;
            this.f3097l = null;
        } else {
            a8.e eVar = this.f3126b;
            Object[] objArr2 = this.f3125a;
            j1Var.f3095c = eVar.G(i10, true, objArr2, false);
            obj = objArr2[0];
        }
        Object obj2 = obj;
        if (sVar.g() == 1) {
            this.f3131g = i10;
            this.f3130f = i10;
            this.k = i10;
        } else {
            int i18 = this.f3131g;
            if (i18 < 0) {
                this.f3131g = i10;
                this.f3130f = i10;
            } else {
                this.f3131g = i18 + 1;
            }
        }
        this.f3126b.D(i10, obj2, j1Var.f3095c, i11, i12);
        return j1Var.f3095c;
    }

    /* JADX WARN: Code restructure failed: missing block: B:105:0x0136, code lost:
    
        return true;
     */
    /* JADX WARN: Removed duplicated region for block: B:107:0x0139  */
    /* JADX WARN: Removed duplicated region for block: B:88:0x0107 A[LOOP:2: B:88:0x0107->B:104:0x012b, LOOP_START, PHI: r6 r9 r10
      0x0107: PHI (r6v12 int) = (r6v6 int), (r6v16 int) binds: [B:87:0x0105, B:104:0x012b] A[DONT_GENERATE, DONT_INLINE]
      0x0107: PHI (r9v20 int) = (r9v18 int), (r9v21 int) binds: [B:87:0x0105, B:104:0x012b] A[DONT_GENERATE, DONT_INLINE]
      0x0107: PHI (r10v7 int) = (r10v5 int), (r10v9 int) binds: [B:87:0x0105, B:104:0x012b] A[DONT_GENERATE, DONT_INLINE]] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final boolean q(int r14, boolean r15) {
        /*
            Method dump skipped, instruction units count: 355
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.leanback.widget.k1.q(int, boolean):boolean");
    }

    public final int r(boolean z) {
        boolean z5 = false;
        if (z) {
            for (int i10 = this.f3131g; i10 >= this.f3130f; i10--) {
                int i11 = k(i10).f3109a;
                if (i11 == 0) {
                    z5 = true;
                } else if (z5 && i11 == this.f3129e - 1) {
                    return i10;
                }
            }
            return -1;
        }
        for (int i12 = this.f3130f; i12 <= this.f3131g; i12++) {
            int i13 = k(i12).f3109a;
            if (i13 == this.f3129e - 1) {
                z5 = true;
            } else if (z5 && i13 == 0) {
                return i12;
            }
        }
        return -1;
    }

    public final int s() {
        return (this.f3096j.g() + this.k) - 1;
    }

    @Override // androidx.leanback.widget.t
    /* JADX INFO: renamed from: t, reason: merged with bridge method [inline-methods] */
    public final j1 k(int i10) {
        androidx.recyclerview.widget.s sVar = this.f3096j;
        int i11 = i10 - this.k;
        if (i11 < 0 || i11 >= sVar.g()) {
            return null;
        }
        if (i11 < 0) {
            sVar.getClass();
        } else if (i11 < sVar.g()) {
            return (j1) ((Object[]) sVar.f5043d)[sVar.f5042c & (sVar.f5040a + i11)];
        }
        throw new ArrayIndexOutOfBoundsException();
    }

    public final int u(int i10) {
        int i11;
        j1 j1VarK;
        int i12 = this.f3130f;
        if (i12 < 0) {
            return Integer.MIN_VALUE;
        }
        if (this.f3127c) {
            int I = this.f3126b.I(i12);
            if (k(this.f3130f).f3109a == i10) {
                return I;
            }
            int i13 = this.f3130f;
            do {
                i13++;
                if (i13 > s()) {
                    return Integer.MIN_VALUE;
                }
                j1VarK = k(i13);
                I += j1VarK.f3094b;
            } while (j1VarK.f3109a != i10);
            return I;
        }
        int I2 = this.f3126b.I(this.f3131g);
        j1 j1VarK2 = k(this.f3131g);
        if (j1VarK2.f3109a == i10) {
            i11 = j1VarK2.f3095c;
        } else {
            int i14 = this.f3131g;
            do {
                i14--;
                if (i14 < this.k) {
                    return Integer.MIN_VALUE;
                }
                I2 -= j1VarK2.f3094b;
                j1VarK2 = k(i14);
            } while (j1VarK2.f3109a != i10);
            i11 = j1VarK2.f3095c;
        }
        return I2 + i11;
    }

    public final int v(int i10) {
        j1 j1VarK;
        int i11;
        int i12 = this.f3130f;
        if (i12 < 0) {
            return Integer.MAX_VALUE;
        }
        if (!this.f3127c) {
            int I = this.f3126b.I(i12);
            if (k(this.f3130f).f3109a == i10) {
                return I;
            }
            int i13 = this.f3130f;
            do {
                i13++;
                if (i13 > s()) {
                    return Integer.MAX_VALUE;
                }
                j1VarK = k(i13);
                I += j1VarK.f3094b;
            } while (j1VarK.f3109a != i10);
            return I;
        }
        int I2 = this.f3126b.I(this.f3131g);
        j1 j1VarK2 = k(this.f3131g);
        if (j1VarK2.f3109a == i10) {
            i11 = j1VarK2.f3095c;
        } else {
            int i14 = this.f3131g;
            do {
                i14--;
                if (i14 < this.k) {
                    return Integer.MAX_VALUE;
                }
                I2 -= j1VarK2.f3094b;
                j1VarK2 = k(i14);
            } while (j1VarK2.f3109a != i10);
            i11 = j1VarK2.f3095c;
        }
        return I2 - i11;
    }

    public final boolean w(int i10, boolean z) {
        int i11;
        int I;
        int i12;
        androidx.recyclerview.widget.s sVar = this.f3096j;
        if (sVar.g() != 0) {
            int i13 = this.f3130f;
            if (i13 < 0) {
                int i14 = this.f3133i;
                i11 = i14 != -1 ? i14 : 0;
                if (i11 <= s()) {
                    int i15 = this.k;
                    if (i11 >= i15 - 1) {
                        if (i11 >= i15) {
                            I = Integer.MAX_VALUE;
                            i12 = 0;
                        }
                    }
                }
                sVar.f(sVar.g());
                return false;
            }
            I = this.f3126b.I(i13);
            i12 = k(this.f3130f).f3094b;
            i11 = this.f3130f - 1;
            int iMax = Math.max(((a0) this.f3126b.f183l).f3073w, this.k);
            for (int i16 = i11; i16 >= iMax; i16--) {
                j1 j1VarK = k(i16);
                int i17 = j1VarK.f3109a;
                a8.e eVar = this.f3126b;
                Object[] objArr = this.f3125a;
                int iG = eVar.G(i16, false, objArr, false);
                if (iG != j1VarK.f3095c) {
                    sVar.f((i16 + 1) - this.k);
                    this.k = this.f3130f;
                    this.f3097l = objArr[0];
                    this.f3098m = iG;
                    return false;
                }
                this.f3130f = i16;
                if (this.f3131g < 0) {
                    this.f3131g = i16;
                }
                this.f3126b.D(i16, objArr[0], iG, i17, I - i12);
                if (z || !d(i10)) {
                    I = this.f3126b.I(i16);
                    i12 = j1VarK.f3094b;
                    if (i17 != 0 || !z) {
                    }
                }
                return true;
            }
        }
        return false;
    }

    public final int x(int i10, int i11, int i12) {
        int i13 = this.f3130f;
        if (i13 >= 0 && (i13 != this.k || i13 != i10 + 1)) {
            throw new IllegalStateException();
        }
        int i14 = this.k;
        j1 j1VarK = i14 >= 0 ? k(i14) : null;
        int I = this.f3126b.I(this.k);
        j1 j1Var = new j1(i11, 0);
        androidx.recyclerview.widget.s sVar = this.f3096j;
        int i15 = (sVar.f5040a - 1) & sVar.f5042c;
        sVar.f5040a = i15;
        ((Object[]) sVar.f5043d)[i15] = j1Var;
        if (i15 == sVar.f5041b) {
            sVar.d();
        }
        Object obj = this.f3097l;
        if (obj != null) {
            j1Var.f3095c = this.f3098m;
            this.f3097l = null;
        } else {
            a8.e eVar = this.f3126b;
            Object[] objArr = this.f3125a;
            j1Var.f3095c = eVar.G(i10, false, objArr, false);
            obj = objArr[0];
        }
        Object obj2 = obj;
        this.f3130f = i10;
        this.k = i10;
        if (this.f3131g < 0) {
            this.f3131g = i10;
        }
        int i16 = !this.f3127c ? i12 - j1Var.f3095c : i12 + j1Var.f3095c;
        if (j1VarK != null) {
            j1VarK.f3094b = I - i16;
        }
        this.f3126b.D(i10, obj2, j1Var.f3095c, i11, i16);
        return j1Var.f3095c;
    }

    /* JADX WARN: Code restructure failed: missing block: B:103:0x012a, code lost:
    
        return true;
     */
    /* JADX WARN: Removed duplicated region for block: B:105:0x012d  */
    /* JADX WARN: Removed duplicated region for block: B:86:0x00fb A[LOOP:2: B:86:0x00fb->B:102:0x011f, LOOP_START, PHI: r5 r8 r9
      0x00fb: PHI (r5v12 int) = (r5v6 int), (r5v17 int) binds: [B:85:0x00f9, B:102:0x011f] A[DONT_GENERATE, DONT_INLINE]
      0x00fb: PHI (r8v19 int) = (r8v17 int), (r8v20 int) binds: [B:85:0x00f9, B:102:0x011f] A[DONT_GENERATE, DONT_INLINE]
      0x00fb: PHI (r9v8 int) = (r9v6 int), (r9v10 int) binds: [B:85:0x00f9, B:102:0x011f] A[DONT_GENERATE, DONT_INLINE]] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final boolean y(int r13, boolean r14) {
        /*
            Method dump skipped, instruction units count: 345
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.leanback.widget.k1.y(int, boolean):boolean");
    }
}
