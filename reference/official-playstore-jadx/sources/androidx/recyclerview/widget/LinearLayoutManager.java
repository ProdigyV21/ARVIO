package androidx.recyclerview.widget;

import android.content.Context;
import android.graphics.Rect;
import android.os.Parcelable;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.view.accessibility.AccessibilityEvent;

/* JADX INFO: loaded from: classes3.dex */
public class LinearLayoutManager extends z0 implements w, k1 {
    public final z A;
    public final a0 B;
    public final int C;
    public final int[] D;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public int f4816p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public b0 f4817q;

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    public androidx.emoji2.text.h f4818r;

    /* JADX INFO: renamed from: s, reason: collision with root package name */
    public boolean f4819s;

    /* JADX INFO: renamed from: t, reason: collision with root package name */
    public final boolean f4820t;

    /* JADX INFO: renamed from: u, reason: collision with root package name */
    public boolean f4821u;

    /* JADX INFO: renamed from: v, reason: collision with root package name */
    public boolean f4822v;

    /* JADX INFO: renamed from: w, reason: collision with root package name */
    public final boolean f4823w;
    public int x;

    /* JADX INFO: renamed from: y, reason: collision with root package name */
    public int f4824y;
    public c0 z;

    public LinearLayoutManager() {
        this.f4816p = 1;
        this.f4820t = false;
        this.f4821u = false;
        this.f4822v = false;
        this.f4823w = true;
        this.x = -1;
        this.f4824y = Integer.MIN_VALUE;
        this.z = null;
        this.A = new z();
        this.B = new a0();
        this.C = 2;
        this.D = new int[2];
        j1(1);
        b(null);
        if (this.f4820t) {
            this.f4820t = false;
            y0();
        }
    }

    @Override // androidx.recyclerview.widget.z0
    public int A0(int i10, f1 f1Var, l1 l1Var) {
        if (this.f4816p == 1) {
            return 0;
        }
        return i1(i10, f1Var, l1Var);
    }

    @Override // androidx.recyclerview.widget.z0
    public final void B0(int i10) {
        this.x = i10;
        this.f4824y = Integer.MIN_VALUE;
        c0 c0Var = this.z;
        if (c0Var != null) {
            c0Var.f4900i = -1;
        }
        y0();
    }

    @Override // androidx.recyclerview.widget.z0
    public int C0(int i10, f1 f1Var, l1 l1Var) {
        if (this.f4816p == 0) {
            return 0;
        }
        return i1(i10, f1Var, l1Var);
    }

    @Override // androidx.recyclerview.widget.z0
    public final boolean J0() {
        if (this.f5124m != 1073741824 && this.f5123l != 1073741824) {
            int iW = w();
            for (int i10 = 0; i10 < iW; i10++) {
                ViewGroup.LayoutParams layoutParams = v(i10).getLayoutParams();
                if (layoutParams.width < 0 && layoutParams.height < 0) {
                    return true;
                }
            }
        }
        return false;
    }

    @Override // androidx.recyclerview.widget.z0
    public boolean L0() {
        return this.z == null && this.f4819s == this.f4822v;
    }

    public void M0(l1 l1Var, b0 b0Var, s sVar) {
        int i10 = b0Var.f4886d;
        if (i10 < 0 || i10 >= l1Var.b()) {
            return;
        }
        sVar.b(i10, Math.max(0, b0Var.f4889g));
    }

    public final int N0(l1 l1Var) {
        if (w() == 0) {
            return 0;
        }
        R0();
        androidx.emoji2.text.h hVar = this.f4818r;
        boolean z = !this.f4823w;
        return qb.l.g(l1Var, hVar, U0(z), T0(z), this, this.f4823w);
    }

    public final int O0(l1 l1Var) {
        if (w() == 0) {
            return 0;
        }
        R0();
        androidx.emoji2.text.h hVar = this.f4818r;
        boolean z = !this.f4823w;
        return qb.l.h(l1Var, hVar, U0(z), T0(z), this, this.f4823w, this.f4821u);
    }

    public final int P0(l1 l1Var) {
        if (w() == 0) {
            return 0;
        }
        R0();
        androidx.emoji2.text.h hVar = this.f4818r;
        boolean z = !this.f4823w;
        return qb.l.i(l1Var, hVar, U0(z), T0(z), this, this.f4823w);
    }

    @Override // androidx.recyclerview.widget.z0
    public final boolean Q() {
        return true;
    }

    public final int Q0(int i10) {
        return i10 != 1 ? i10 != 2 ? i10 != 17 ? i10 != 33 ? i10 != 66 ? (i10 == 130 && this.f4816p == 1) ? 1 : Integer.MIN_VALUE : this.f4816p == 0 ? 1 : Integer.MIN_VALUE : this.f4816p == 1 ? -1 : Integer.MIN_VALUE : this.f4816p == 0 ? -1 : Integer.MIN_VALUE : (this.f4816p != 1 && c1()) ? -1 : 1 : (this.f4816p != 1 && c1()) ? 1 : -1;
    }

    public final void R0() {
        if (this.f4817q == null) {
            b0 b0Var = new b0();
            b0Var.f4883a = true;
            b0Var.f4890h = 0;
            b0Var.f4891i = 0;
            b0Var.k = null;
            this.f4817q = b0Var;
        }
    }

    public final int S0(f1 f1Var, b0 b0Var, l1 l1Var, boolean z) {
        int i10;
        int i11 = b0Var.f4885c;
        int i12 = b0Var.f4889g;
        if (i12 != Integer.MIN_VALUE) {
            if (i11 < 0) {
                b0Var.f4889g = i12 + i11;
            }
            f1(f1Var, b0Var);
        }
        int i13 = b0Var.f4885c + b0Var.f4890h;
        while (true) {
            if ((!b0Var.f4893l && i13 <= 0) || (i10 = b0Var.f4886d) < 0 || i10 >= l1Var.b()) {
                break;
            }
            a0 a0Var = this.B;
            a0Var.f4871a = 0;
            a0Var.f4872b = false;
            a0Var.f4873c = false;
            a0Var.f4874d = false;
            d1(f1Var, l1Var, b0Var, a0Var);
            if (!a0Var.f4872b) {
                int i14 = b0Var.f4884b;
                int i15 = a0Var.f4871a;
                b0Var.f4884b = (b0Var.f4888f * i15) + i14;
                if (!a0Var.f4873c || b0Var.k != null || !l1Var.f4979g) {
                    b0Var.f4885c -= i15;
                    i13 -= i15;
                }
                int i16 = b0Var.f4889g;
                if (i16 != Integer.MIN_VALUE) {
                    int i17 = i16 + i15;
                    b0Var.f4889g = i17;
                    int i18 = b0Var.f4885c;
                    if (i18 < 0) {
                        b0Var.f4889g = i17 + i18;
                    }
                    f1(f1Var, b0Var);
                }
                if (z && a0Var.f4874d) {
                    break;
                }
            } else {
                break;
            }
        }
        return i11 - b0Var.f4885c;
    }

    public final View T0(boolean z) {
        return this.f4821u ? W0(0, w(), z) : W0(w() - 1, -1, z);
    }

    public final View U0(boolean z) {
        return this.f4821u ? W0(w() - 1, -1, z) : W0(0, w(), z);
    }

    public final View V0(int i10, int i11) {
        int i12;
        int i13;
        R0();
        if (i11 <= i10 && i11 >= i10) {
            return v(i10);
        }
        if (this.f4818r.e(v(i10)) < this.f4818r.k()) {
            i12 = 16644;
            i13 = 16388;
        } else {
            i12 = 4161;
            i13 = 4097;
        }
        return this.f4816p == 0 ? this.f5115c.d(i10, i11, i12, i13) : this.f5116d.d(i10, i11, i12, i13);
    }

    public final View W0(int i10, int i11, boolean z) {
        R0();
        int i12 = z ? 24579 : 320;
        return this.f4816p == 0 ? this.f5115c.d(i10, i11, i12, 320) : this.f5116d.d(i10, i11, i12, 320);
    }

    /* JADX WARN: Removed duplicated region for block: B:33:0x0075  */
    /* JADX WARN: Removed duplicated region for block: B:35:0x0079  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public android.view.View X0(androidx.recyclerview.widget.f1 r17, androidx.recyclerview.widget.l1 r18, boolean r19, boolean r20) {
        /*
            r16 = this;
            r0 = r16
            r0.R0()
            int r1 = r0.w()
            r2 = 0
            r3 = 1
            if (r20 == 0) goto L15
            int r1 = r0.w()
            int r1 = r1 - r3
            r4 = -1
            r5 = r4
            goto L18
        L15:
            r4 = r1
            r1 = r2
            r5 = r3
        L18:
            int r6 = r18.b()
            androidx.emoji2.text.h r7 = r0.f4818r
            int r7 = r7.k()
            androidx.emoji2.text.h r8 = r0.f4818r
            int r8 = r8.g()
            r9 = 0
            r10 = r9
            r11 = r10
        L2b:
            if (r1 == r4) goto L7c
            android.view.View r12 = r0.v(r1)
            int r13 = androidx.recyclerview.widget.z0.L(r12)
            androidx.emoji2.text.h r14 = r0.f4818r
            int r14 = r14.e(r12)
            androidx.emoji2.text.h r15 = r0.f4818r
            int r15 = r15.b(r12)
            if (r13 < 0) goto L7a
            if (r13 >= r6) goto L7a
            android.view.ViewGroup$LayoutParams r13 = r12.getLayoutParams()
            androidx.recyclerview.widget.a1 r13 = (androidx.recyclerview.widget.a1) r13
            androidx.recyclerview.widget.p1 r13 = r13.f4875a
            boolean r13 = r13.isRemoved()
            if (r13 == 0) goto L57
            if (r11 != 0) goto L7a
            r11 = r12
            goto L7a
        L57:
            if (r15 > r7) goto L5d
            if (r14 >= r7) goto L5d
            r13 = r3
            goto L5e
        L5d:
            r13 = r2
        L5e:
            if (r14 < r8) goto L64
            if (r15 <= r8) goto L64
            r14 = r3
            goto L65
        L64:
            r14 = r2
        L65:
            if (r13 != 0) goto L6b
            if (r14 == 0) goto L6a
            goto L6b
        L6a:
            return r12
        L6b:
            if (r19 == 0) goto L73
            if (r14 == 0) goto L70
            goto L75
        L70:
            if (r9 != 0) goto L7a
            goto L79
        L73:
            if (r13 == 0) goto L77
        L75:
            r10 = r12
            goto L7a
        L77:
            if (r9 != 0) goto L7a
        L79:
            r9 = r12
        L7a:
            int r1 = r1 + r5
            goto L2b
        L7c:
            if (r9 == 0) goto L7f
            return r9
        L7f:
            if (r10 == 0) goto L82
            return r10
        L82:
            return r11
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.recyclerview.widget.LinearLayoutManager.X0(androidx.recyclerview.widget.f1, androidx.recyclerview.widget.l1, boolean, boolean):android.view.View");
    }

    @Override // androidx.recyclerview.widget.z0
    public View Y(View view, int i10, f1 f1Var, l1 l1Var) {
        int iQ0;
        h1();
        if (w() != 0 && (iQ0 = Q0(i10)) != Integer.MIN_VALUE) {
            R0();
            l1(iQ0, (int) (this.f4818r.l() * 0.33333334f), false, l1Var);
            b0 b0Var = this.f4817q;
            b0Var.f4889g = Integer.MIN_VALUE;
            b0Var.f4883a = false;
            S0(f1Var, b0Var, l1Var, true);
            View viewV0 = iQ0 == -1 ? this.f4821u ? V0(w() - 1, -1) : V0(0, w()) : this.f4821u ? V0(0, w()) : V0(w() - 1, -1);
            View viewB1 = iQ0 == -1 ? b1() : a1();
            if (!viewB1.hasFocusable()) {
                return viewV0;
            }
            if (viewV0 != null) {
                return viewB1;
            }
        }
        return null;
    }

    public final int Y0(int i10, f1 f1Var, l1 l1Var, boolean z) {
        int iG;
        int iG2 = this.f4818r.g() - i10;
        if (iG2 <= 0) {
            return 0;
        }
        int i11 = -i1(-iG2, f1Var, l1Var);
        int i12 = i10 + i11;
        if (!z || (iG = this.f4818r.g() - i12) <= 0) {
            return i11;
        }
        this.f4818r.o(iG);
        return iG + i11;
    }

    @Override // androidx.recyclerview.widget.z0
    public final void Z(AccessibilityEvent accessibilityEvent) {
        super.Z(accessibilityEvent);
        if (w() > 0) {
            View viewW0 = W0(0, w(), false);
            accessibilityEvent.setFromIndex(viewW0 == null ? -1 : z0.L(viewW0));
            View viewW02 = W0(w() - 1, -1, false);
            accessibilityEvent.setToIndex(viewW02 != null ? z0.L(viewW02) : -1);
        }
    }

    public final int Z0(int i10, f1 f1Var, l1 l1Var, boolean z) {
        int iK;
        int iK2 = i10 - this.f4818r.k();
        if (iK2 <= 0) {
            return 0;
        }
        int i11 = -i1(iK2, f1Var, l1Var);
        int i12 = i10 + i11;
        if (!z || (iK = i12 - this.f4818r.k()) <= 0) {
            return i11;
        }
        this.f4818r.o(-iK);
        return i11 - iK;
    }

    public final View a1() {
        return v(this.f4821u ? 0 : w() - 1);
    }

    @Override // androidx.recyclerview.widget.z0
    public final void b(String str) {
        if (this.z == null) {
            super.b(str);
        }
    }

    public final View b1() {
        return v(this.f4821u ? w() - 1 : 0);
    }

    public final boolean c1() {
        return G() == 1;
    }

    @Override // androidx.recyclerview.widget.z0
    public final boolean d() {
        return this.f4816p == 0;
    }

    public void d1(f1 f1Var, l1 l1Var, b0 b0Var, a0 a0Var) {
        int I;
        int i10;
        int i11;
        int iD;
        View viewB = b0Var.b(f1Var);
        if (viewB == null) {
            a0Var.f4872b = true;
            return;
        }
        a1 a1Var = (a1) viewB.getLayoutParams();
        if (b0Var.k == null) {
            if (this.f4821u == (b0Var.f4888f == -1)) {
                a(viewB, -1, false);
            } else {
                a(viewB, 0, false);
            }
        } else {
            if (this.f4821u == (b0Var.f4888f == -1)) {
                a(viewB, -1, true);
            } else {
                a(viewB, 0, true);
            }
        }
        a1 a1Var2 = (a1) viewB.getLayoutParams();
        Rect rectH = this.f5114b.H(viewB);
        int i12 = rectH.left + rectH.right;
        int i13 = rectH.top + rectH.bottom;
        int iX = z0.x(this.f5125n, this.f5123l, J() + I() + ((ViewGroup.MarginLayoutParams) a1Var2).leftMargin + ((ViewGroup.MarginLayoutParams) a1Var2).rightMargin + i12, ((ViewGroup.MarginLayoutParams) a1Var2).width, d());
        int iX2 = z0.x(this.f5126o, this.f5124m, H() + K() + ((ViewGroup.MarginLayoutParams) a1Var2).topMargin + ((ViewGroup.MarginLayoutParams) a1Var2).bottomMargin + i13, ((ViewGroup.MarginLayoutParams) a1Var2).height, e());
        if (I0(viewB, iX, iX2, a1Var2)) {
            viewB.measure(iX, iX2);
        }
        a0Var.f4871a = this.f4818r.c(viewB);
        if (this.f4816p == 1) {
            if (c1()) {
                iD = this.f5125n - J();
                I = iD - this.f4818r.d(viewB);
            } else {
                I = I();
                iD = this.f4818r.d(viewB) + I;
            }
            if (b0Var.f4888f == -1) {
                i10 = b0Var.f4884b;
                i11 = i10 - a0Var.f4871a;
            } else {
                i11 = b0Var.f4884b;
                i10 = a0Var.f4871a + i11;
            }
        } else {
            int iK = K();
            int iD2 = this.f4818r.d(viewB) + iK;
            if (b0Var.f4888f == -1) {
                int i14 = b0Var.f4884b;
                int i15 = i14 - a0Var.f4871a;
                iD = i14;
                i10 = iD2;
                I = i15;
                i11 = iK;
            } else {
                int i16 = b0Var.f4884b;
                int i17 = a0Var.f4871a + i16;
                I = i16;
                i10 = iD2;
                i11 = iK;
                iD = i17;
            }
        }
        z0.S(viewB, I, i11, iD, i10);
        if (a1Var.f4875a.isRemoved() || a1Var.f4875a.isUpdated()) {
            a0Var.f4873c = true;
        }
        a0Var.f4874d = viewB.hasFocusable();
    }

    @Override // androidx.recyclerview.widget.z0
    public final boolean e() {
        return this.f4816p == 1;
    }

    public void e1(f1 f1Var, l1 l1Var, z zVar, int i10) {
    }

    public final void f1(f1 f1Var, b0 b0Var) {
        if (!b0Var.f4883a || b0Var.f4893l) {
            return;
        }
        int i10 = b0Var.f4889g;
        int i11 = b0Var.f4891i;
        if (b0Var.f4888f == -1) {
            int iW = w();
            if (i10 < 0) {
                return;
            }
            int iF = (this.f4818r.f() - i10) + i11;
            if (this.f4821u) {
                for (int i12 = 0; i12 < iW; i12++) {
                    View viewV = v(i12);
                    if (this.f4818r.e(viewV) < iF || this.f4818r.n(viewV) < iF) {
                        g1(f1Var, 0, i12);
                        return;
                    }
                }
                return;
            }
            int i13 = iW - 1;
            for (int i14 = i13; i14 >= 0; i14--) {
                View viewV2 = v(i14);
                if (this.f4818r.e(viewV2) < iF || this.f4818r.n(viewV2) < iF) {
                    g1(f1Var, i13, i14);
                    return;
                }
            }
            return;
        }
        if (i10 < 0) {
            return;
        }
        int i15 = i10 - i11;
        int iW2 = w();
        if (!this.f4821u) {
            for (int i16 = 0; i16 < iW2; i16++) {
                View viewV3 = v(i16);
                if (this.f4818r.b(viewV3) > i15 || this.f4818r.m(viewV3) > i15) {
                    g1(f1Var, 0, i16);
                    return;
                }
            }
            return;
        }
        int i17 = iW2 - 1;
        for (int i18 = i17; i18 >= 0; i18--) {
            View viewV4 = v(i18);
            if (this.f4818r.b(viewV4) > i15 || this.f4818r.m(viewV4) > i15) {
                g1(f1Var, i17, i18);
                return;
            }
        }
    }

    public final void g1(f1 f1Var, int i10, int i11) {
        if (i10 == i11) {
            return;
        }
        if (i11 <= i10) {
            while (i10 > i11) {
                View viewV = v(i10);
                v0(i10);
                f1Var.h(viewV);
                i10--;
            }
            return;
        }
        for (int i12 = i11 - 1; i12 >= i10; i12--) {
            View viewV2 = v(i12);
            v0(i12);
            f1Var.h(viewV2);
        }
    }

    @Override // androidx.recyclerview.widget.z0
    public final void h(int i10, int i11, l1 l1Var, s sVar) {
        if (this.f4816p != 0) {
            i10 = i11;
        }
        if (w() == 0 || i10 == 0) {
            return;
        }
        R0();
        l1(i10 > 0 ? 1 : -1, Math.abs(i10), true, l1Var);
        M0(l1Var, this.f4817q, sVar);
    }

    public final void h1() {
        if (this.f4816p == 1 || !c1()) {
            this.f4821u = this.f4820t;
        } else {
            this.f4821u = !this.f4820t;
        }
    }

    @Override // androidx.recyclerview.widget.z0
    public final void i(int i10, s sVar) {
        boolean z;
        int i11;
        c0 c0Var = this.z;
        if (c0Var == null || (i11 = c0Var.f4900i) < 0) {
            h1();
            z = this.f4821u;
            i11 = this.x;
            if (i11 == -1) {
                i11 = z ? i10 - 1 : 0;
            }
        } else {
            z = c0Var.f4902m;
        }
        int i12 = z ? -1 : 1;
        for (int i13 = 0; i13 < this.C && i11 >= 0 && i11 < i10; i13++) {
            sVar.b(i11, 0);
            i11 += i12;
        }
    }

    public final int i1(int i10, f1 f1Var, l1 l1Var) {
        if (w() != 0 && i10 != 0) {
            R0();
            this.f4817q.f4883a = true;
            int i11 = i10 > 0 ? 1 : -1;
            int iAbs = Math.abs(i10);
            l1(i11, iAbs, true, l1Var);
            b0 b0Var = this.f4817q;
            int iS0 = S0(f1Var, b0Var, l1Var, false) + b0Var.f4889g;
            if (iS0 >= 0) {
                if (iAbs > iS0) {
                    i10 = i11 * iS0;
                }
                this.f4818r.o(-i10);
                this.f4817q.f4892j = i10;
                return i10;
            }
        }
        return 0;
    }

    @Override // androidx.recyclerview.widget.z0
    public final int j(l1 l1Var) {
        return N0(l1Var);
    }

    public final void j1(int i10) {
        if (i10 != 0 && i10 != 1) {
            throw new IllegalArgumentException(a0.c.i(i10, "invalid orientation:"));
        }
        b(null);
        if (i10 != this.f4816p || this.f4818r == null) {
            androidx.emoji2.text.h hVarA = androidx.emoji2.text.h.a(this, i10);
            this.f4818r = hVarA;
            this.A.f5108a = hVarA;
            this.f4816p = i10;
            y0();
        }
    }

    @Override // androidx.recyclerview.widget.z0
    public int k(l1 l1Var) {
        return O0(l1Var);
    }

    /* JADX WARN: Removed duplicated region for block: B:111:0x01d9  */
    /* JADX WARN: Removed duplicated region for block: B:135:0x022b  */
    /* JADX WARN: Removed duplicated region for block: B:95:0x0194  */
    @Override // androidx.recyclerview.widget.z0
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public void k0(androidx.recyclerview.widget.f1 r18, androidx.recyclerview.widget.l1 r19) {
        /*
            Method dump skipped, instruction units count: 1108
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.recyclerview.widget.LinearLayoutManager.k0(androidx.recyclerview.widget.f1, androidx.recyclerview.widget.l1):void");
    }

    public void k1(boolean z) {
        b(null);
        if (this.f4822v == z) {
            return;
        }
        this.f4822v = z;
        y0();
    }

    @Override // androidx.recyclerview.widget.z0
    public int l(l1 l1Var) {
        return P0(l1Var);
    }

    @Override // androidx.recyclerview.widget.z0
    public void l0(l1 l1Var) {
        this.z = null;
        this.x = -1;
        this.f4824y = Integer.MIN_VALUE;
        this.A.d();
    }

    public final void l1(int i10, int i11, boolean z, l1 l1Var) {
        int i12;
        int iK;
        this.f4817q.f4893l = this.f4818r.i() == 0 && this.f4818r.f() == 0;
        this.f4817q.f4888f = i10;
        int[] iArr = this.D;
        iArr[0] = 0;
        iArr[1] = 0;
        int iL = l1Var.f4973a != -1 ? this.f4818r.l() : 0;
        if (this.f4817q.f4888f == -1) {
            i12 = 0;
        } else {
            i12 = iL;
            iL = 0;
        }
        iArr[0] = iL;
        iArr[1] = i12;
        int iMax = Math.max(0, iL);
        int iMax2 = Math.max(0, iArr[1]);
        boolean z5 = i10 == 1;
        b0 b0Var = this.f4817q;
        int i13 = z5 ? iMax2 : iMax;
        b0Var.f4890h = i13;
        if (!z5) {
            iMax = iMax2;
        }
        b0Var.f4891i = iMax;
        if (z5) {
            b0Var.f4890h = this.f4818r.h() + i13;
            View viewA1 = a1();
            b0 b0Var2 = this.f4817q;
            b0Var2.f4887e = this.f4821u ? -1 : 1;
            int iL2 = z0.L(viewA1);
            b0 b0Var3 = this.f4817q;
            b0Var2.f4886d = iL2 + b0Var3.f4887e;
            b0Var3.f4884b = this.f4818r.b(viewA1);
            iK = this.f4818r.b(viewA1) - this.f4818r.g();
        } else {
            View viewB1 = b1();
            b0 b0Var4 = this.f4817q;
            b0Var4.f4890h = this.f4818r.k() + b0Var4.f4890h;
            b0 b0Var5 = this.f4817q;
            b0Var5.f4887e = this.f4821u ? 1 : -1;
            int iL3 = z0.L(viewB1);
            b0 b0Var6 = this.f4817q;
            b0Var5.f4886d = iL3 + b0Var6.f4887e;
            b0Var6.f4884b = this.f4818r.e(viewB1);
            iK = (-this.f4818r.e(viewB1)) + this.f4818r.k();
        }
        b0 b0Var7 = this.f4817q;
        b0Var7.f4885c = i11;
        if (z) {
            b0Var7.f4885c = i11 - iK;
        }
        b0Var7.f4889g = iK;
    }

    @Override // androidx.recyclerview.widget.z0
    public final int m(l1 l1Var) {
        return N0(l1Var);
    }

    public final void m1(int i10, int i11) {
        this.f4817q.f4885c = this.f4818r.g() - i11;
        b0 b0Var = this.f4817q;
        b0Var.f4887e = this.f4821u ? -1 : 1;
        b0Var.f4886d = i10;
        b0Var.f4888f = 1;
        b0Var.f4884b = i11;
        b0Var.f4889g = Integer.MIN_VALUE;
    }

    @Override // androidx.recyclerview.widget.z0
    public int n(l1 l1Var) {
        return O0(l1Var);
    }

    public final void n1(int i10, int i11) {
        this.f4817q.f4885c = i11 - this.f4818r.k();
        b0 b0Var = this.f4817q;
        b0Var.f4886d = i10;
        b0Var.f4887e = this.f4821u ? 1 : -1;
        b0Var.f4888f = -1;
        b0Var.f4884b = i11;
        b0Var.f4889g = Integer.MIN_VALUE;
    }

    @Override // androidx.recyclerview.widget.z0
    public int o(l1 l1Var) {
        return P0(l1Var);
    }

    @Override // androidx.recyclerview.widget.z0
    public final void o0(Parcelable parcelable) {
        if (parcelable instanceof c0) {
            c0 c0Var = (c0) parcelable;
            this.z = c0Var;
            if (this.x != -1) {
                c0Var.f4900i = -1;
            }
            y0();
        }
    }

    @Override // androidx.recyclerview.widget.z0
    public final Parcelable p0() {
        c0 c0Var = this.z;
        if (c0Var != null) {
            c0 c0Var2 = new c0();
            c0Var2.f4900i = c0Var.f4900i;
            c0Var2.f4901l = c0Var.f4901l;
            c0Var2.f4902m = c0Var.f4902m;
            return c0Var2;
        }
        c0 c0Var3 = new c0();
        if (w() <= 0) {
            c0Var3.f4900i = -1;
            return c0Var3;
        }
        R0();
        boolean z = this.f4819s ^ this.f4821u;
        c0Var3.f4902m = z;
        if (z) {
            View viewA1 = a1();
            c0Var3.f4901l = this.f4818r.g() - this.f4818r.b(viewA1);
            c0Var3.f4900i = z0.L(viewA1);
            return c0Var3;
        }
        View viewB1 = b1();
        c0Var3.f4900i = z0.L(viewB1);
        c0Var3.f4901l = this.f4818r.e(viewB1) - this.f4818r.k();
        return c0Var3;
    }

    @Override // androidx.recyclerview.widget.z0
    public final View r(int i10) {
        int iW = w();
        if (iW == 0) {
            return null;
        }
        int iL = i10 - z0.L(v(0));
        if (iL >= 0 && iL < iW) {
            View viewV = v(iL);
            if (z0.L(viewV) == i10) {
                return viewV;
            }
        }
        return super.r(i10);
    }

    @Override // androidx.recyclerview.widget.z0
    public a1 s() {
        return new a1(-2, -2);
    }

    public LinearLayoutManager(Context context, AttributeSet attributeSet, int i10, int i11) {
        this.f4816p = 1;
        this.f4820t = false;
        this.f4821u = false;
        this.f4822v = false;
        this.f4823w = true;
        this.x = -1;
        this.f4824y = Integer.MIN_VALUE;
        this.z = null;
        this.A = new z();
        this.B = new a0();
        this.C = 2;
        this.D = new int[2];
        y0 y0VarM = z0.M(context, attributeSet, i10, i11);
        j1(y0VarM.f5104a);
        boolean z = y0VarM.f5106c;
        b(null);
        if (z != this.f4820t) {
            this.f4820t = z;
            y0();
        }
        k1(y0VarM.f5107d);
    }

    @Override // androidx.recyclerview.widget.z0
    public final void X(RecyclerView recyclerView) {
    }
}
