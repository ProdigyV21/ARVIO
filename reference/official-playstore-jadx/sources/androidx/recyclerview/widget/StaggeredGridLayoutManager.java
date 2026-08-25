package androidx.recyclerview.widget;

import android.content.Context;
import android.graphics.Rect;
import android.os.Parcelable;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.view.accessibility.AccessibilityEvent;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.BitSet;
import java.util.WeakHashMap;

/* JADX INFO: loaded from: classes3.dex */
public class StaggeredGridLayoutManager extends z0 implements k1 {
    public final c2 B;
    public final int C;
    public boolean D;
    public boolean E;
    public w1 F;
    public final Rect G;
    public final t1 H;
    public final boolean I;
    public int[] J;
    public final n K;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public final int f4862p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public final x1[] f4863q;

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    public final androidx.emoji2.text.h f4864r;

    /* JADX INFO: renamed from: s, reason: collision with root package name */
    public final androidx.emoji2.text.h f4865s;

    /* JADX INFO: renamed from: t, reason: collision with root package name */
    public final int f4866t;

    /* JADX INFO: renamed from: u, reason: collision with root package name */
    public int f4867u;

    /* JADX INFO: renamed from: v, reason: collision with root package name */
    public final y f4868v;

    /* JADX INFO: renamed from: w, reason: collision with root package name */
    public boolean f4869w;

    /* JADX INFO: renamed from: y, reason: collision with root package name */
    public final BitSet f4870y;
    public boolean x = false;
    public int z = -1;
    public int A = Integer.MIN_VALUE;

    public StaggeredGridLayoutManager(Context context, AttributeSet attributeSet, int i10, int i11) {
        this.f4862p = -1;
        this.f4869w = false;
        c2 c2Var = new c2();
        this.B = c2Var;
        this.C = 2;
        this.G = new Rect();
        this.H = new t1(this);
        this.I = true;
        this.K = new n(this, 1);
        y0 y0VarM = z0.M(context, attributeSet, i10, i11);
        int i12 = y0VarM.f5104a;
        if (i12 != 0 && i12 != 1) {
            throw new IllegalArgumentException("invalid orientation.");
        }
        b(null);
        if (i12 != this.f4866t) {
            this.f4866t = i12;
            androidx.emoji2.text.h hVar = this.f4864r;
            this.f4864r = this.f4865s;
            this.f4865s = hVar;
            y0();
        }
        int i13 = y0VarM.f5105b;
        b(null);
        if (i13 != this.f4862p) {
            c2Var.b();
            y0();
            this.f4862p = i13;
            this.f4870y = new BitSet(this.f4862p);
            this.f4863q = new x1[this.f4862p];
            for (int i14 = 0; i14 < this.f4862p; i14++) {
                this.f4863q[i14] = new x1(this, i14);
            }
            y0();
        }
        boolean z = y0VarM.f5106c;
        b(null);
        w1 w1Var = this.F;
        if (w1Var != null && w1Var.f5086r != z) {
            w1Var.f5086r = z;
        }
        this.f4869w = z;
        y0();
        y yVar = new y();
        yVar.f5095a = true;
        yVar.f5100f = 0;
        yVar.f5101g = 0;
        this.f4868v = yVar;
        this.f4864r = androidx.emoji2.text.h.a(this, this.f4866t);
        this.f4865s = androidx.emoji2.text.h.a(this, 1 - this.f4866t);
    }

    public static int m1(int i10, int i11, int i12) {
        int mode;
        return (!(i11 == 0 && i12 == 0) && ((mode = View.MeasureSpec.getMode(i10)) == Integer.MIN_VALUE || mode == 1073741824)) ? View.MeasureSpec.makeMeasureSpec(Math.max(0, (View.MeasureSpec.getSize(i10) - i11) - i12), mode) : i10;
    }

    @Override // androidx.recyclerview.widget.z0
    public final int A0(int i10, f1 f1Var, l1 l1Var) {
        return i1(i10, f1Var, l1Var);
    }

    @Override // androidx.recyclerview.widget.z0
    public final void B0(int i10) {
        w1 w1Var = this.F;
        if (w1Var != null && w1Var.f5079i != i10) {
            w1Var.f5082n = null;
            w1Var.f5081m = 0;
            w1Var.f5079i = -1;
            w1Var.f5080l = -1;
        }
        this.z = i10;
        this.A = Integer.MIN_VALUE;
        y0();
    }

    @Override // androidx.recyclerview.widget.z0
    public final int C0(int i10, f1 f1Var, l1 l1Var) {
        return i1(i10, f1Var, l1Var);
    }

    @Override // androidx.recyclerview.widget.z0
    public final void F0(Rect rect, int i10, int i11) {
        int iG;
        int iG2;
        int iJ = J() + I();
        int iH = H() + K();
        int i12 = this.f4866t;
        int i13 = this.f4862p;
        if (i12 == 1) {
            int iHeight = rect.height() + iH;
            RecyclerView recyclerView = this.f5114b;
            WeakHashMap weakHashMap = androidx.core.view.b2.f2200a;
            iG2 = z0.g(i11, iHeight, recyclerView.getMinimumHeight());
            iG = z0.g(i10, (this.f4867u * i13) + iJ, this.f5114b.getMinimumWidth());
        } else {
            int iWidth = rect.width() + iJ;
            RecyclerView recyclerView2 = this.f5114b;
            WeakHashMap weakHashMap2 = androidx.core.view.b2.f2200a;
            iG = z0.g(i10, iWidth, recyclerView2.getMinimumWidth());
            iG2 = z0.g(i11, (this.f4867u * i13) + iH, this.f5114b.getMinimumHeight());
        }
        this.f5114b.setMeasuredDimension(iG, iG2);
    }

    @Override // androidx.recyclerview.widget.z0
    public final boolean L0() {
        return this.F == null;
    }

    public final boolean M0() {
        int iT0;
        if (w() != 0 && this.C != 0 && this.f5119g) {
            if (this.x) {
                iT0 = U0();
                T0();
            } else {
                iT0 = T0();
                U0();
            }
            if (iT0 == 0 && Y0() != null) {
                this.B.b();
                this.f5118f = true;
                y0();
                return true;
            }
        }
        return false;
    }

    public final int N0(l1 l1Var) {
        if (w() == 0) {
            return 0;
        }
        boolean z = !this.I;
        return qb.l.h(l1Var, this.f4864r, Q0(z), P0(z), this, this.I, this.x);
    }

    /* JADX WARN: Type inference failed for: r8v2 */
    /* JADX WARN: Type inference failed for: r8v24 */
    /* JADX WARN: Type inference failed for: r8v3, types: [boolean, int] */
    public final int O0(f1 f1Var, y yVar, l1 l1Var) {
        x1 x1Var;
        ?? r82;
        int iH;
        int iC;
        int iK;
        int iC2;
        int i10;
        int i11;
        int i12;
        int i13 = 0;
        int i14 = 1;
        this.f4870y.set(0, this.f4862p, true);
        y yVar2 = this.f4868v;
        int i15 = yVar2.f5103i ? yVar.f5099e == 1 ? Integer.MAX_VALUE : Integer.MIN_VALUE : yVar.f5099e == 1 ? yVar.f5101g + yVar.f5096b : yVar.f5100f - yVar.f5096b;
        int i16 = yVar.f5099e;
        for (int i17 = 0; i17 < this.f4862p; i17++) {
            if (!this.f4863q[i17].f5089a.isEmpty()) {
                l1(this.f4863q[i17], i16, i15);
            }
        }
        int iG = this.x ? this.f4864r.g() : this.f4864r.k();
        boolean z = false;
        while (true) {
            int i18 = yVar.f5097c;
            if (i18 < 0 || i18 >= l1Var.b() || (!yVar2.f5103i && this.f4870y.isEmpty())) {
                break;
            }
            View view = f1Var.k(yVar.f5097c, Long.MAX_VALUE).itemView;
            yVar.f5097c += yVar.f5098d;
            u1 u1Var = (u1) view.getLayoutParams();
            int layoutPosition = u1Var.f4875a.getLayoutPosition();
            c2 c2Var = this.B;
            int[] iArr = (int[]) c2Var.f4903a;
            int i19 = (iArr == null || layoutPosition >= iArr.length) ? -1 : iArr[layoutPosition];
            if (i19 == -1) {
                if (c1(yVar.f5099e)) {
                    i12 = this.f4862p - i14;
                    i11 = -1;
                    i10 = -1;
                } else {
                    i10 = i14;
                    i11 = this.f4862p;
                    i12 = i13;
                }
                x1 x1Var2 = null;
                if (yVar.f5099e == i14) {
                    int iK2 = this.f4864r.k();
                    int i20 = Integer.MAX_VALUE;
                    while (i12 != i11) {
                        x1 x1Var3 = this.f4863q[i12];
                        int iF = x1Var3.f(iK2);
                        if (iF < i20) {
                            i20 = iF;
                            x1Var2 = x1Var3;
                        }
                        i12 += i10;
                    }
                } else {
                    int iG2 = this.f4864r.g();
                    int i21 = Integer.MIN_VALUE;
                    while (i12 != i11) {
                        x1 x1Var4 = this.f4863q[i12];
                        int iH2 = x1Var4.h(iG2);
                        if (iH2 > i21) {
                            x1Var2 = x1Var4;
                            i21 = iH2;
                        }
                        i12 += i10;
                    }
                }
                x1Var = x1Var2;
                c2Var.c(layoutPosition);
                ((int[]) c2Var.f4903a)[layoutPosition] = x1Var.f5093e;
            } else {
                x1Var = this.f4863q[i19];
            }
            u1Var.f5070e = x1Var;
            if (yVar.f5099e == 1) {
                r82 = 0;
                a(view, -1, false);
            } else {
                r82 = 0;
                a(view, 0, false);
            }
            if (this.f4866t == 1) {
                a1(view, z0.x(this.f4867u, this.f5123l, r82, ((ViewGroup.MarginLayoutParams) u1Var).width, r82), z0.x(this.f5126o, this.f5124m, H() + K(), ((ViewGroup.MarginLayoutParams) u1Var).height, true));
            } else {
                a1(view, z0.x(this.f5125n, this.f5123l, J() + I(), ((ViewGroup.MarginLayoutParams) u1Var).width, true), z0.x(this.f4867u, this.f5124m, 0, ((ViewGroup.MarginLayoutParams) u1Var).height, false));
            }
            if (yVar.f5099e == 1) {
                iC = x1Var.f(iG);
                iH = this.f4864r.c(view) + iC;
            } else {
                iH = x1Var.h(iG);
                iC = iH - this.f4864r.c(view);
            }
            if (yVar.f5099e == 1) {
                x1 x1Var5 = u1Var.f5070e;
                x1Var5.getClass();
                u1 u1Var2 = (u1) view.getLayoutParams();
                u1Var2.f5070e = x1Var5;
                ArrayList arrayList = x1Var5.f5089a;
                arrayList.add(view);
                x1Var5.f5091c = Integer.MIN_VALUE;
                if (arrayList.size() == 1) {
                    x1Var5.f5090b = Integer.MIN_VALUE;
                }
                if (u1Var2.f4875a.isRemoved() || u1Var2.f4875a.isUpdated()) {
                    x1Var5.f5092d = x1Var5.f5094f.f4864r.c(view) + x1Var5.f5092d;
                }
            } else {
                x1 x1Var6 = u1Var.f5070e;
                x1Var6.getClass();
                u1 u1Var3 = (u1) view.getLayoutParams();
                u1Var3.f5070e = x1Var6;
                ArrayList arrayList2 = x1Var6.f5089a;
                arrayList2.add(0, view);
                x1Var6.f5090b = Integer.MIN_VALUE;
                if (arrayList2.size() == 1) {
                    x1Var6.f5091c = Integer.MIN_VALUE;
                }
                if (u1Var3.f4875a.isRemoved() || u1Var3.f4875a.isUpdated()) {
                    x1Var6.f5092d = x1Var6.f5094f.f4864r.c(view) + x1Var6.f5092d;
                }
            }
            if (Z0() && this.f4866t == 1) {
                iC2 = this.f4865s.g() - (((this.f4862p - 1) - x1Var.f5093e) * this.f4867u);
                iK = iC2 - this.f4865s.c(view);
            } else {
                iK = this.f4865s.k() + (x1Var.f5093e * this.f4867u);
                iC2 = this.f4865s.c(view) + iK;
            }
            if (this.f4866t == 1) {
                z0.S(view, iK, iC, iC2, iH);
            } else {
                z0.S(view, iC, iK, iH, iC2);
            }
            l1(x1Var, yVar2.f5099e, i15);
            e1(f1Var, yVar2);
            if (yVar2.f5102h && view.hasFocusable()) {
                this.f4870y.set(x1Var.f5093e, false);
            }
            i14 = 1;
            z = true;
            i13 = 0;
        }
        if (!z) {
            e1(f1Var, yVar2);
        }
        int iK3 = yVar2.f5099e == -1 ? this.f4864r.k() - W0(this.f4864r.k()) : V0(this.f4864r.g()) - this.f4864r.g();
        if (iK3 > 0) {
            return Math.min(yVar.f5096b, iK3);
        }
        return 0;
    }

    public final View P0(boolean z) {
        int iK = this.f4864r.k();
        int iG = this.f4864r.g();
        View view = null;
        for (int iW = w() - 1; iW >= 0; iW--) {
            View viewV = v(iW);
            int iE = this.f4864r.e(viewV);
            int iB = this.f4864r.b(viewV);
            if (iB > iK && iE < iG) {
                if (iB <= iG || !z) {
                    return viewV;
                }
                if (view == null) {
                    view = viewV;
                }
            }
        }
        return view;
    }

    @Override // androidx.recyclerview.widget.z0
    public final boolean Q() {
        return this.C != 0;
    }

    public final View Q0(boolean z) {
        int iK = this.f4864r.k();
        int iG = this.f4864r.g();
        int iW = w();
        View view = null;
        for (int i10 = 0; i10 < iW; i10++) {
            View viewV = v(i10);
            int iE = this.f4864r.e(viewV);
            if (this.f4864r.b(viewV) > iK && iE < iG) {
                if (iE >= iK || !z) {
                    return viewV;
                }
                if (view == null) {
                    view = viewV;
                }
            }
        }
        return view;
    }

    public final void R0(f1 f1Var, l1 l1Var, boolean z) {
        int iG;
        int iV0 = V0(Integer.MIN_VALUE);
        if (iV0 != Integer.MIN_VALUE && (iG = this.f4864r.g() - iV0) > 0) {
            int i10 = iG - (-i1(-iG, f1Var, l1Var));
            if (!z || i10 <= 0) {
                return;
            }
            this.f4864r.o(i10);
        }
    }

    public final void S0(f1 f1Var, l1 l1Var, boolean z) {
        int iK;
        int iW0 = W0(Integer.MAX_VALUE);
        if (iW0 != Integer.MAX_VALUE && (iK = iW0 - this.f4864r.k()) > 0) {
            int iI1 = iK - i1(iK, f1Var, l1Var);
            if (!z || iI1 <= 0) {
                return;
            }
            this.f4864r.o(-iI1);
        }
    }

    @Override // androidx.recyclerview.widget.z0
    public final void T(int i10) {
        super.T(i10);
        for (int i11 = 0; i11 < this.f4862p; i11++) {
            x1 x1Var = this.f4863q[i11];
            int i12 = x1Var.f5090b;
            if (i12 != Integer.MIN_VALUE) {
                x1Var.f5090b = i12 + i10;
            }
            int i13 = x1Var.f5091c;
            if (i13 != Integer.MIN_VALUE) {
                x1Var.f5091c = i13 + i10;
            }
        }
    }

    public final int T0() {
        if (w() == 0) {
            return 0;
        }
        return z0.L(v(0));
    }

    @Override // androidx.recyclerview.widget.z0
    public final void U(int i10) {
        super.U(i10);
        for (int i11 = 0; i11 < this.f4862p; i11++) {
            x1 x1Var = this.f4863q[i11];
            int i12 = x1Var.f5090b;
            if (i12 != Integer.MIN_VALUE) {
                x1Var.f5090b = i12 + i10;
            }
            int i13 = x1Var.f5091c;
            if (i13 != Integer.MIN_VALUE) {
                x1Var.f5091c = i13 + i10;
            }
        }
    }

    public final int U0() {
        int iW = w();
        if (iW == 0) {
            return 0;
        }
        return z0.L(v(iW - 1));
    }

    @Override // androidx.recyclerview.widget.z0
    public final void V(l0 l0Var, l0 l0Var2) {
        this.B.b();
        for (int i10 = 0; i10 < this.f4862p; i10++) {
            this.f4863q[i10].b();
        }
    }

    public final int V0(int i10) {
        int iF = this.f4863q[0].f(i10);
        for (int i11 = 1; i11 < this.f4862p; i11++) {
            int iF2 = this.f4863q[i11].f(i10);
            if (iF2 > iF) {
                iF = iF2;
            }
        }
        return iF;
    }

    public final int W0(int i10) {
        int iH = this.f4863q[0].h(i10);
        for (int i11 = 1; i11 < this.f4862p; i11++) {
            int iH2 = this.f4863q[i11].h(i10);
            if (iH2 < iH) {
                iH = iH2;
            }
        }
        return iH;
    }

    @Override // androidx.recyclerview.widget.z0
    public final void X(RecyclerView recyclerView) {
        RecyclerView recyclerView2 = this.f5114b;
        if (recyclerView2 != null) {
            recyclerView2.removeCallbacks(this.K);
        }
        for (int i10 = 0; i10 < this.f4862p; i10++) {
            this.f4863q[i10].b();
        }
        recyclerView.requestLayout();
    }

    /* JADX WARN: Removed duplicated region for block: B:21:0x0034  */
    /* JADX WARN: Removed duplicated region for block: B:22:0x0036  */
    /* JADX WARN: Removed duplicated region for block: B:32:0x0056  */
    /* JADX WARN: Removed duplicated region for block: B:35:0x0068  */
    /* JADX WARN: Removed duplicated region for block: B:41:0x007d  */
    /* JADX WARN: Removed duplicated region for block: B:43:0x0092  */
    /* JADX WARN: Removed duplicated region for block: B:44:0x00a0  */
    /* JADX WARN: Removed duplicated region for block: B:47:0x00b5  */
    /* JADX WARN: Removed duplicated region for block: B:53:0x00c6  */
    /* JADX WARN: Removed duplicated region for block: B:56:0x00cc  */
    /* JADX WARN: Removed duplicated region for block: B:65:0x007a A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:68:? A[RETURN, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final void X0(int r10, int r11, int r12) {
        /*
            Method dump skipped, instruction units count: 223
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.recyclerview.widget.StaggeredGridLayoutManager.X0(int, int, int):void");
    }

    /* JADX WARN: Removed duplicated region for block: B:23:0x0032  */
    /* JADX WARN: Removed duplicated region for block: B:29:0x003d  */
    @Override // androidx.recyclerview.widget.z0
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final android.view.View Y(android.view.View r8, int r9, androidx.recyclerview.widget.f1 r10, androidx.recyclerview.widget.l1 r11) {
        /*
            Method dump skipped, instruction units count: 331
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.recyclerview.widget.StaggeredGridLayoutManager.Y(android.view.View, int, androidx.recyclerview.widget.f1, androidx.recyclerview.widget.l1):android.view.View");
    }

    /* JADX WARN: Removed duplicated region for block: B:51:0x00ee  */
    /* JADX WARN: Removed duplicated region for block: B:52:0x00f0  */
    /* JADX WARN: Removed duplicated region for block: B:54:0x00f3  */
    /* JADX WARN: Removed duplicated region for block: B:55:0x00f5  */
    /* JADX WARN: Removed duplicated region for block: B:68:0x00f8 A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:74:0x002c A[SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final android.view.View Y0() {
        /*
            Method dump skipped, instruction units count: 251
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.recyclerview.widget.StaggeredGridLayoutManager.Y0():android.view.View");
    }

    @Override // androidx.recyclerview.widget.z0
    public final void Z(AccessibilityEvent accessibilityEvent) {
        super.Z(accessibilityEvent);
        if (w() > 0) {
            View viewQ0 = Q0(false);
            View viewP0 = P0(false);
            if (viewQ0 == null || viewP0 == null) {
                return;
            }
            int iL = z0.L(viewQ0);
            int iL2 = z0.L(viewP0);
            if (iL < iL2) {
                accessibilityEvent.setFromIndex(iL);
                accessibilityEvent.setToIndex(iL2);
            } else {
                accessibilityEvent.setFromIndex(iL2);
                accessibilityEvent.setToIndex(iL);
            }
        }
    }

    public final boolean Z0() {
        return G() == 1;
    }

    public final void a1(View view, int i10, int i11) {
        Rect rect = this.G;
        c(view, rect);
        u1 u1Var = (u1) view.getLayoutParams();
        int iM1 = m1(i10, ((ViewGroup.MarginLayoutParams) u1Var).leftMargin + rect.left, ((ViewGroup.MarginLayoutParams) u1Var).rightMargin + rect.right);
        int iM12 = m1(i11, ((ViewGroup.MarginLayoutParams) u1Var).topMargin + rect.top, ((ViewGroup.MarginLayoutParams) u1Var).bottomMargin + rect.bottom);
        if (I0(view, iM1, iM12, u1Var)) {
            view.measure(iM1, iM12);
        }
    }

    @Override // androidx.recyclerview.widget.z0
    public final void b(String str) {
        if (this.F == null) {
            super.b(str);
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:108:0x01a8  */
    /* JADX WARN: Removed duplicated region for block: B:109:0x01aa  */
    /* JADX WARN: Removed duplicated region for block: B:123:0x01e1  */
    /* JADX WARN: Removed duplicated region for block: B:131:0x01fe  */
    /* JADX WARN: Removed duplicated region for block: B:254:0x0417  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final void b1(androidx.recyclerview.widget.f1 r17, androidx.recyclerview.widget.l1 r18, boolean r19) {
        /*
            Method dump skipped, instruction units count: 1074
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.recyclerview.widget.StaggeredGridLayoutManager.b1(androidx.recyclerview.widget.f1, androidx.recyclerview.widget.l1, boolean):void");
    }

    public final boolean c1(int i10) {
        if (this.f4866t == 0) {
            return (i10 == -1) != this.x;
        }
        return ((i10 == -1) == this.x) == Z0();
    }

    @Override // androidx.recyclerview.widget.z0
    public final boolean d() {
        return this.f4866t == 0;
    }

    public final void d1(int i10, l1 l1Var) {
        int iT0;
        int i11;
        if (i10 > 0) {
            iT0 = U0();
            i11 = 1;
        } else {
            iT0 = T0();
            i11 = -1;
        }
        y yVar = this.f4868v;
        yVar.f5095a = true;
        k1(iT0, l1Var);
        j1(i11);
        yVar.f5097c = iT0 + yVar.f5098d;
        yVar.f5096b = Math.abs(i10);
    }

    @Override // androidx.recyclerview.widget.z0
    public final boolean e() {
        return this.f4866t == 1;
    }

    @Override // androidx.recyclerview.widget.z0
    public final void e0(int i10, int i11) {
        X0(i10, i11, 1);
    }

    public final void e1(f1 f1Var, y yVar) {
        if (!yVar.f5095a || yVar.f5103i) {
            return;
        }
        if (yVar.f5096b == 0) {
            if (yVar.f5099e == -1) {
                f1(f1Var, yVar.f5101g);
                return;
            } else {
                g1(f1Var, yVar.f5100f);
                return;
            }
        }
        int i10 = 1;
        if (yVar.f5099e == -1) {
            int i11 = yVar.f5100f;
            int iH = this.f4863q[0].h(i11);
            while (i10 < this.f4862p) {
                int iH2 = this.f4863q[i10].h(i11);
                if (iH2 > iH) {
                    iH = iH2;
                }
                i10++;
            }
            int i12 = i11 - iH;
            f1(f1Var, i12 < 0 ? yVar.f5101g : yVar.f5101g - Math.min(i12, yVar.f5096b));
            return;
        }
        int i13 = yVar.f5101g;
        int iF = this.f4863q[0].f(i13);
        while (i10 < this.f4862p) {
            int iF2 = this.f4863q[i10].f(i13);
            if (iF2 < iF) {
                iF = iF2;
            }
            i10++;
        }
        int i14 = iF - yVar.f5101g;
        g1(f1Var, i14 < 0 ? yVar.f5100f : Math.min(i14, yVar.f5096b) + yVar.f5100f);
    }

    @Override // androidx.recyclerview.widget.z0
    public final boolean f(a1 a1Var) {
        return a1Var instanceof u1;
    }

    @Override // androidx.recyclerview.widget.z0
    public final void f0() {
        this.B.b();
        y0();
    }

    public final void f1(f1 f1Var, int i10) {
        for (int iW = w() - 1; iW >= 0; iW--) {
            View viewV = v(iW);
            if (this.f4864r.e(viewV) < i10 || this.f4864r.n(viewV) < i10) {
                return;
            }
            u1 u1Var = (u1) viewV.getLayoutParams();
            u1Var.getClass();
            if (u1Var.f5070e.f5089a.size() == 1) {
                return;
            }
            x1 x1Var = u1Var.f5070e;
            ArrayList arrayList = x1Var.f5089a;
            int size = arrayList.size();
            View view = (View) arrayList.remove(size - 1);
            u1 u1Var2 = (u1) view.getLayoutParams();
            u1Var2.f5070e = null;
            if (u1Var2.f4875a.isRemoved() || u1Var2.f4875a.isUpdated()) {
                x1Var.f5092d -= x1Var.f5094f.f4864r.c(view);
            }
            if (size == 1) {
                x1Var.f5090b = Integer.MIN_VALUE;
            }
            x1Var.f5091c = Integer.MIN_VALUE;
            u0(viewV, f1Var);
        }
    }

    @Override // androidx.recyclerview.widget.z0
    public final void g0(int i10, int i11) {
        X0(i10, i11, 8);
    }

    public final void g1(f1 f1Var, int i10) {
        while (w() > 0) {
            View viewV = v(0);
            if (this.f4864r.b(viewV) > i10 || this.f4864r.m(viewV) > i10) {
                return;
            }
            u1 u1Var = (u1) viewV.getLayoutParams();
            u1Var.getClass();
            if (u1Var.f5070e.f5089a.size() == 1) {
                return;
            }
            x1 x1Var = u1Var.f5070e;
            ArrayList arrayList = x1Var.f5089a;
            View view = (View) arrayList.remove(0);
            u1 u1Var2 = (u1) view.getLayoutParams();
            u1Var2.f5070e = null;
            if (arrayList.size() == 0) {
                x1Var.f5091c = Integer.MIN_VALUE;
            }
            if (u1Var2.f4875a.isRemoved() || u1Var2.f4875a.isUpdated()) {
                x1Var.f5092d -= x1Var.f5094f.f4864r.c(view);
            }
            x1Var.f5090b = Integer.MIN_VALUE;
            u0(viewV, f1Var);
        }
    }

    @Override // androidx.recyclerview.widget.z0
    public final void h(int i10, int i11, l1 l1Var, s sVar) {
        y yVar;
        int iF;
        int iH;
        if (this.f4866t != 0) {
            i10 = i11;
        }
        if (w() == 0 || i10 == 0) {
            return;
        }
        d1(i10, l1Var);
        int[] iArr = this.J;
        if (iArr == null || iArr.length < this.f4862p) {
            this.J = new int[this.f4862p];
        }
        int i12 = 0;
        int i13 = 0;
        while (true) {
            int i14 = this.f4862p;
            yVar = this.f4868v;
            if (i12 >= i14) {
                break;
            }
            if (yVar.f5098d == -1) {
                iF = yVar.f5100f;
                iH = this.f4863q[i12].h(iF);
            } else {
                iF = this.f4863q[i12].f(yVar.f5101g);
                iH = yVar.f5101g;
            }
            int i15 = iF - iH;
            if (i15 >= 0) {
                this.J[i13] = i15;
                i13++;
            }
            i12++;
        }
        Arrays.sort(this.J, 0, i13);
        for (int i16 = 0; i16 < i13; i16++) {
            int i17 = yVar.f5097c;
            if (i17 < 0 || i17 >= l1Var.b()) {
                return;
            }
            sVar.b(yVar.f5097c, this.J[i16]);
            yVar.f5097c += yVar.f5098d;
        }
    }

    @Override // androidx.recyclerview.widget.z0
    public final void h0(int i10, int i11) {
        X0(i10, i11, 2);
    }

    public final void h1() {
        if (this.f4866t == 1 || !Z0()) {
            this.x = this.f4869w;
        } else {
            this.x = !this.f4869w;
        }
    }

    public final int i1(int i10, f1 f1Var, l1 l1Var) {
        if (w() == 0 || i10 == 0) {
            return 0;
        }
        d1(i10, l1Var);
        y yVar = this.f4868v;
        int iO0 = O0(f1Var, yVar, l1Var);
        if (yVar.f5096b >= iO0) {
            i10 = i10 < 0 ? -iO0 : iO0;
        }
        this.f4864r.o(-i10);
        this.D = this.x;
        yVar.f5096b = 0;
        e1(f1Var, yVar);
        return i10;
    }

    @Override // androidx.recyclerview.widget.z0
    public final int j(l1 l1Var) {
        if (w() == 0) {
            return 0;
        }
        boolean z = !this.I;
        return qb.l.g(l1Var, this.f4864r, Q0(z), P0(z), this, this.I);
    }

    @Override // androidx.recyclerview.widget.z0
    public final void j0(RecyclerView recyclerView, int i10, int i11) {
        X0(i10, i11, 4);
    }

    public final void j1(int i10) {
        y yVar = this.f4868v;
        yVar.f5099e = i10;
        yVar.f5098d = this.x != (i10 == -1) ? -1 : 1;
    }

    @Override // androidx.recyclerview.widget.z0
    public final int k(l1 l1Var) {
        return N0(l1Var);
    }

    @Override // androidx.recyclerview.widget.z0
    public final void k0(f1 f1Var, l1 l1Var) {
        b1(f1Var, l1Var, true);
    }

    public final void k1(int i10, l1 l1Var) {
        int iL;
        int iL2;
        int i11;
        y yVar = this.f4868v;
        boolean z = false;
        yVar.f5096b = 0;
        yVar.f5097c = i10;
        androidx.leanback.widget.w wVar = this.f5117e;
        if (wVar == null || !wVar.f3156e || (i11 = l1Var.f4973a) == -1) {
            iL = 0;
            iL2 = 0;
        } else {
            if (this.x == (i11 < i10)) {
                iL = this.f4864r.l();
                iL2 = 0;
            } else {
                iL2 = this.f4864r.l();
                iL = 0;
            }
        }
        RecyclerView recyclerView = this.f5114b;
        if (recyclerView == null || !recyclerView.f4847r) {
            yVar.f5101g = this.f4864r.f() + iL;
            yVar.f5100f = -iL2;
        } else {
            yVar.f5100f = this.f4864r.k() - iL2;
            yVar.f5101g = this.f4864r.g() + iL;
        }
        yVar.f5102h = false;
        yVar.f5095a = true;
        if (this.f4864r.i() == 0 && this.f4864r.f() == 0) {
            z = true;
        }
        yVar.f5103i = z;
    }

    @Override // androidx.recyclerview.widget.z0
    public final int l(l1 l1Var) {
        if (w() == 0) {
            return 0;
        }
        boolean z = !this.I;
        return qb.l.i(l1Var, this.f4864r, Q0(z), P0(z), this, this.I);
    }

    @Override // androidx.recyclerview.widget.z0
    public final void l0(l1 l1Var) {
        this.z = -1;
        this.A = Integer.MIN_VALUE;
        this.F = null;
        this.H.a();
    }

    public final void l1(x1 x1Var, int i10, int i11) {
        int i12 = x1Var.f5092d;
        int i13 = x1Var.f5093e;
        if (i10 != -1) {
            int i14 = x1Var.f5091c;
            if (i14 == Integer.MIN_VALUE) {
                x1Var.a();
                i14 = x1Var.f5091c;
            }
            if (i14 - i12 >= i11) {
                this.f4870y.set(i13, false);
                return;
            }
            return;
        }
        int i15 = x1Var.f5090b;
        if (i15 == Integer.MIN_VALUE) {
            View view = (View) x1Var.f5089a.get(0);
            u1 u1Var = (u1) view.getLayoutParams();
            x1Var.f5090b = x1Var.f5094f.f4864r.e(view);
            u1Var.getClass();
            i15 = x1Var.f5090b;
        }
        if (i15 + i12 <= i11) {
            this.f4870y.set(i13, false);
        }
    }

    @Override // androidx.recyclerview.widget.z0
    public final int m(l1 l1Var) {
        if (w() == 0) {
            return 0;
        }
        boolean z = !this.I;
        return qb.l.g(l1Var, this.f4864r, Q0(z), P0(z), this, this.I);
    }

    @Override // androidx.recyclerview.widget.z0
    public final int n(l1 l1Var) {
        return N0(l1Var);
    }

    @Override // androidx.recyclerview.widget.z0
    public final int o(l1 l1Var) {
        if (w() == 0) {
            return 0;
        }
        boolean z = !this.I;
        return qb.l.i(l1Var, this.f4864r, Q0(z), P0(z), this, this.I);
    }

    @Override // androidx.recyclerview.widget.z0
    public final void o0(Parcelable parcelable) {
        if (parcelable instanceof w1) {
            w1 w1Var = (w1) parcelable;
            this.F = w1Var;
            if (this.z != -1) {
                w1Var.f5079i = -1;
                w1Var.f5080l = -1;
                w1Var.f5082n = null;
                w1Var.f5081m = 0;
                w1Var.f5083o = 0;
                w1Var.f5084p = null;
                w1Var.f5085q = null;
            }
            y0();
        }
    }

    @Override // androidx.recyclerview.widget.z0
    public final Parcelable p0() {
        int iH;
        int iK;
        int[] iArr;
        w1 w1Var = this.F;
        if (w1Var != null) {
            w1 w1Var2 = new w1();
            w1Var2.f5081m = w1Var.f5081m;
            w1Var2.f5079i = w1Var.f5079i;
            w1Var2.f5080l = w1Var.f5080l;
            w1Var2.f5082n = w1Var.f5082n;
            w1Var2.f5083o = w1Var.f5083o;
            w1Var2.f5084p = w1Var.f5084p;
            w1Var2.f5086r = w1Var.f5086r;
            w1Var2.f5087s = w1Var.f5087s;
            w1Var2.f5088t = w1Var.f5088t;
            w1Var2.f5085q = w1Var.f5085q;
            return w1Var2;
        }
        w1 w1Var3 = new w1();
        w1Var3.f5086r = this.f4869w;
        w1Var3.f5087s = this.D;
        w1Var3.f5088t = this.E;
        c2 c2Var = this.B;
        if (c2Var == null || (iArr = (int[]) c2Var.f4903a) == null) {
            w1Var3.f5083o = 0;
        } else {
            w1Var3.f5084p = iArr;
            w1Var3.f5083o = iArr.length;
            w1Var3.f5085q = (ArrayList) c2Var.f4904b;
        }
        if (w() <= 0) {
            w1Var3.f5079i = -1;
            w1Var3.f5080l = -1;
            w1Var3.f5081m = 0;
            return w1Var3;
        }
        w1Var3.f5079i = this.D ? U0() : T0();
        View viewP0 = this.x ? P0(true) : Q0(true);
        w1Var3.f5080l = viewP0 != null ? z0.L(viewP0) : -1;
        int i10 = this.f4862p;
        w1Var3.f5081m = i10;
        w1Var3.f5082n = new int[i10];
        for (int i11 = 0; i11 < this.f4862p; i11++) {
            if (this.D) {
                iH = this.f4863q[i11].f(Integer.MIN_VALUE);
                if (iH != Integer.MIN_VALUE) {
                    iK = this.f4864r.g();
                    iH -= iK;
                }
            } else {
                iH = this.f4863q[i11].h(Integer.MIN_VALUE);
                if (iH != Integer.MIN_VALUE) {
                    iK = this.f4864r.k();
                    iH -= iK;
                }
            }
            w1Var3.f5082n[i11] = iH;
        }
        return w1Var3;
    }

    @Override // androidx.recyclerview.widget.z0
    public final void q0(int i10) {
        if (i10 == 0) {
            M0();
        }
    }

    @Override // androidx.recyclerview.widget.z0
    public final a1 s() {
        return this.f4866t == 0 ? new u1(-2, -1) : new u1(-1, -2);
    }

    @Override // androidx.recyclerview.widget.z0
    public final a1 t(Context context, AttributeSet attributeSet) {
        return new u1(context, attributeSet);
    }

    @Override // androidx.recyclerview.widget.z0
    public final a1 u(ViewGroup.LayoutParams layoutParams) {
        return layoutParams instanceof ViewGroup.MarginLayoutParams ? new u1((ViewGroup.MarginLayoutParams) layoutParams) : new u1(layoutParams);
    }
}
