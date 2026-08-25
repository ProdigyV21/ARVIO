package androidx.recyclerview.widget;

import android.content.Context;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.util.Log;
import android.util.SparseIntArray;
import android.view.View;
import android.view.ViewGroup;
import java.util.Arrays;
import java.util.WeakHashMap;

/* JADX INFO: loaded from: classes3.dex */
public class GridLayoutManager extends LinearLayoutManager {
    public boolean E;
    public final int F;
    public int[] G;
    public View[] H;
    public final SparseIntArray I;
    public final SparseIntArray J;
    public final c2 K;
    public final Rect L;

    public GridLayoutManager(Context context, AttributeSet attributeSet, int i10, int i11) {
        super(context, attributeSet, i10, i11);
        this.E = false;
        this.F = -1;
        this.I = new SparseIntArray();
        this.J = new SparseIntArray();
        c2 c2Var = new c2(1);
        this.K = c2Var;
        this.L = new Rect();
        int i12 = z0.M(context, attributeSet, i10, i11).f5105b;
        if (i12 == this.F) {
            return;
        }
        this.E = true;
        if (i12 < 1) {
            throw new IllegalArgumentException(a0.c.i(i12, "Span count should be at least 1. Provided "));
        }
        this.F = i12;
        c2Var.f();
        y0();
    }

    @Override // androidx.recyclerview.widget.LinearLayoutManager, androidx.recyclerview.widget.z0
    public final int A0(int i10, f1 f1Var, l1 l1Var) {
        v1();
        p1();
        return super.A0(i10, f1Var, l1Var);
    }

    @Override // androidx.recyclerview.widget.LinearLayoutManager, androidx.recyclerview.widget.z0
    public final int C0(int i10, f1 f1Var, l1 l1Var) {
        v1();
        p1();
        return super.C0(i10, f1Var, l1Var);
    }

    @Override // androidx.recyclerview.widget.z0
    public final void F0(Rect rect, int i10, int i11) {
        int iG;
        int iG2;
        if (this.G == null) {
            super.F0(rect, i10, i11);
        }
        int iJ = J() + I();
        int iH = H() + K();
        if (this.f4816p == 1) {
            int iHeight = rect.height() + iH;
            RecyclerView recyclerView = this.f5114b;
            WeakHashMap weakHashMap = androidx.core.view.b2.f2200a;
            iG2 = z0.g(i11, iHeight, recyclerView.getMinimumHeight());
            int[] iArr = this.G;
            iG = z0.g(i10, iArr[iArr.length - 1] + iJ, this.f5114b.getMinimumWidth());
        } else {
            int iWidth = rect.width() + iJ;
            RecyclerView recyclerView2 = this.f5114b;
            WeakHashMap weakHashMap2 = androidx.core.view.b2.f2200a;
            iG = z0.g(i10, iWidth, recyclerView2.getMinimumWidth());
            int[] iArr2 = this.G;
            iG2 = z0.g(i11, iArr2[iArr2.length - 1] + iH, this.f5114b.getMinimumHeight());
        }
        this.f5114b.setMeasuredDimension(iG, iG2);
    }

    @Override // androidx.recyclerview.widget.LinearLayoutManager, androidx.recyclerview.widget.z0
    public final boolean L0() {
        return this.z == null && !this.E;
    }

    @Override // androidx.recyclerview.widget.LinearLayoutManager
    public final void M0(l1 l1Var, b0 b0Var, s sVar) {
        int i10;
        int i11 = this.F;
        for (int i12 = 0; i12 < this.F && (i10 = b0Var.f4886d) >= 0 && i10 < l1Var.b() && i11 > 0; i12++) {
            sVar.b(b0Var.f4886d, Math.max(0, b0Var.f4889g));
            this.K.getClass();
            i11--;
            b0Var.f4886d += b0Var.f4887e;
        }
    }

    @Override // androidx.recyclerview.widget.z0
    public final int N(f1 f1Var, l1 l1Var) {
        if (this.f4816p == 0) {
            return this.F;
        }
        if (l1Var.b() < 1) {
            return 0;
        }
        return r1(l1Var.b() - 1, f1Var, l1Var) + 1;
    }

    @Override // androidx.recyclerview.widget.LinearLayoutManager
    public final View X0(f1 f1Var, l1 l1Var, boolean z, boolean z5) {
        int i10;
        int iW;
        int iW2 = w();
        int i11 = 1;
        if (z5) {
            iW = w() - 1;
            i10 = -1;
            i11 = -1;
        } else {
            i10 = iW2;
            iW = 0;
        }
        int iB = l1Var.b();
        R0();
        int iK = this.f4818r.k();
        int iG = this.f4818r.g();
        View view = null;
        View view2 = null;
        while (iW != i10) {
            View viewV = v(iW);
            int iL = z0.L(viewV);
            if (iL >= 0 && iL < iB && s1(iL, f1Var, l1Var) == 0) {
                if (((a1) viewV.getLayoutParams()).f4875a.isRemoved()) {
                    if (view2 == null) {
                        view2 = viewV;
                    }
                } else {
                    if (this.f4818r.e(viewV) < iG && this.f4818r.b(viewV) >= iK) {
                        return viewV;
                    }
                    if (view == null) {
                        view = viewV;
                    }
                }
            }
            iW += i11;
        }
        return view != null ? view : view2;
    }

    /* JADX WARN: Code restructure failed: missing block: B:54:0x00c9, code lost:
    
        if (r13 == (r2 > r15)) goto L49;
     */
    /* JADX WARN: Code restructure failed: missing block: B:76:0x0129, code lost:
    
        if (r16 == null) goto L78;
     */
    /* JADX WARN: Code restructure failed: missing block: B:77:0x012b, code lost:
    
        return r16;
     */
    /* JADX WARN: Code restructure failed: missing block: B:78:0x012c, code lost:
    
        return r17;
     */
    @Override // androidx.recyclerview.widget.LinearLayoutManager, androidx.recyclerview.widget.z0
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final android.view.View Y(android.view.View r23, int r24, androidx.recyclerview.widget.f1 r25, androidx.recyclerview.widget.l1 r26) {
        /*
            Method dump skipped, instruction units count: 301
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.recyclerview.widget.GridLayoutManager.Y(android.view.View, int, androidx.recyclerview.widget.f1, androidx.recyclerview.widget.l1):android.view.View");
    }

    @Override // androidx.recyclerview.widget.z0
    public final void a0(f1 f1Var, l1 l1Var, q0.k kVar) {
        super.a0(f1Var, l1Var, kVar);
        kVar.g("android.widget.GridView");
    }

    @Override // androidx.recyclerview.widget.z0
    public final void c0(f1 f1Var, l1 l1Var, View view, q0.k kVar) {
        ViewGroup.LayoutParams layoutParams = view.getLayoutParams();
        if (!(layoutParams instanceof v)) {
            b0(view, kVar);
            return;
        }
        v vVar = (v) layoutParams;
        int iR1 = r1(vVar.f4875a.getLayoutPosition(), f1Var, l1Var);
        if (this.f4816p == 0) {
            kVar.h(q0.j.a(vVar.f5071e, vVar.f5072f, iR1, 1, false));
        } else {
            kVar.h(q0.j.a(iR1, 1, vVar.f5071e, vVar.f5072f, false));
        }
    }

    @Override // androidx.recyclerview.widget.LinearLayoutManager
    public final void d1(f1 f1Var, l1 l1Var, b0 b0Var, a0 a0Var) {
        int i10;
        int i11;
        int i12;
        int iD;
        int iK;
        int I;
        int iD2;
        int iX;
        int iX2;
        boolean z;
        int i13;
        View viewB;
        int iJ = this.f4818r.j();
        boolean z5 = iJ != 1073741824;
        int i14 = w() > 0 ? this.G[this.F] : 0;
        if (z5) {
            v1();
        }
        boolean z10 = b0Var.f4887e == 1;
        int iT1 = !z10 ? t1(b0Var.f4886d, f1Var, l1Var) + s1(b0Var.f4886d, f1Var, l1Var) : this.F;
        int i15 = 0;
        while (i15 < this.F && (i13 = b0Var.f4886d) >= 0 && i13 < l1Var.b() && iT1 > 0) {
            int i16 = b0Var.f4886d;
            int iT12 = t1(i16, f1Var, l1Var);
            if (iT12 > this.F) {
                throw new IllegalArgumentException(androidx.compose.material3.d.j(this.F, " spans.", androidx.compose.foundation.c.v("Item at position ", i16, " requires ", iT12, " spans but GridLayoutManager has only ")));
            }
            iT1 -= iT12;
            if (iT1 < 0 || (viewB = b0Var.b(f1Var)) == null) {
                break;
            }
            this.H[i15] = viewB;
            i15++;
        }
        if (i15 == 0) {
            a0Var.f4872b = true;
            return;
        }
        if (z10) {
            i12 = 1;
            i11 = i15;
            i10 = 0;
        } else {
            i10 = i15 - 1;
            i11 = -1;
            i12 = -1;
        }
        int i17 = 0;
        while (i10 != i11) {
            View view = this.H[i10];
            v vVar = (v) view.getLayoutParams();
            int iT13 = t1(z0.L(view), f1Var, l1Var);
            vVar.f5072f = iT13;
            vVar.f5071e = i17;
            i17 += iT13;
            i10 += i12;
        }
        float f10 = 0.0f;
        int i18 = 0;
        for (int i19 = 0; i19 < i15; i19++) {
            View view2 = this.H[i19];
            if (b0Var.k != null) {
                z = false;
                if (z10) {
                    a(view2, -1, true);
                } else {
                    a(view2, 0, true);
                }
            } else if (z10) {
                z = false;
                a(view2, -1, false);
            } else {
                z = false;
                a(view2, 0, false);
            }
            c(view2, this.L);
            u1(view2, iJ, z);
            int iC = this.f4818r.c(view2);
            if (iC > i18) {
                i18 = iC;
            }
            float fD = (this.f4818r.d(view2) * 1.0f) / ((v) view2.getLayoutParams()).f5072f;
            if (fD > f10) {
                f10 = fD;
            }
        }
        if (z5) {
            o1(Math.max(Math.round(f10 * this.F), i14));
            i18 = 0;
            for (int i20 = 0; i20 < i15; i20++) {
                View view3 = this.H[i20];
                u1(view3, 1073741824, true);
                int iC2 = this.f4818r.c(view3);
                if (iC2 > i18) {
                    i18 = iC2;
                }
            }
        }
        for (int i21 = 0; i21 < i15; i21++) {
            View view4 = this.H[i21];
            if (this.f4818r.c(view4) != i18) {
                v vVar2 = (v) view4.getLayoutParams();
                Rect rect = vVar2.f4876b;
                int i22 = rect.top + rect.bottom + ((ViewGroup.MarginLayoutParams) vVar2).topMargin + ((ViewGroup.MarginLayoutParams) vVar2).bottomMargin;
                int i23 = rect.left + rect.right + ((ViewGroup.MarginLayoutParams) vVar2).leftMargin + ((ViewGroup.MarginLayoutParams) vVar2).rightMargin;
                int iQ1 = q1(vVar2.f5071e, vVar2.f5072f);
                if (this.f4816p == 1) {
                    iX2 = z0.x(iQ1, 1073741824, i23, ((ViewGroup.MarginLayoutParams) vVar2).width, false);
                    iX = View.MeasureSpec.makeMeasureSpec(i18 - i22, 1073741824);
                } else {
                    int iMakeMeasureSpec = View.MeasureSpec.makeMeasureSpec(i18 - i23, 1073741824);
                    iX = z0.x(iQ1, 1073741824, i22, ((ViewGroup.MarginLayoutParams) vVar2).height, false);
                    iX2 = iMakeMeasureSpec;
                }
                if (K0(view4, iX2, iX, (a1) view4.getLayoutParams())) {
                    view4.measure(iX2, iX);
                }
            }
        }
        a0Var.f4871a = i18;
        if (this.f4816p != 1) {
            if (b0Var.f4888f == -1) {
                int i24 = b0Var.f4884b;
                I = i24 - i18;
                iK = 0;
                iD = i24;
            } else {
                int i25 = b0Var.f4884b;
                iD = i25 + i18;
                iK = 0;
                I = i25;
            }
            iD2 = iK;
        } else if (b0Var.f4888f == -1) {
            iD2 = b0Var.f4884b;
            iK = iD2 - i18;
            I = 0;
            iD = 0;
        } else {
            int i26 = b0Var.f4884b;
            iD = 0;
            iK = i26;
            iD2 = i26 + i18;
            I = 0;
        }
        for (int i27 = 0; i27 < i15; i27++) {
            View view5 = this.H[i27];
            v vVar3 = (v) view5.getLayoutParams();
            if (this.f4816p != 1) {
                iK = K() + this.G[vVar3.f5071e];
                iD2 = this.f4818r.d(view5) + iK;
            } else if (c1()) {
                int I2 = I() + this.G[this.F - vVar3.f5071e];
                iD = I2;
                I = I2 - this.f4818r.d(view5);
            } else {
                I = I() + this.G[vVar3.f5071e];
                iD = this.f4818r.d(view5) + I;
            }
            z0.S(view5, I, iK, iD, iD2);
            if (vVar3.f4875a.isRemoved() || vVar3.f4875a.isUpdated()) {
                a0Var.f4873c = true;
            }
            a0Var.f4874d = view5.hasFocusable() | a0Var.f4874d;
        }
        Arrays.fill(this.H, (Object) null);
    }

    @Override // androidx.recyclerview.widget.z0
    public final void e0(int i10, int i11) {
        c2 c2Var = this.K;
        c2Var.f();
        ((SparseIntArray) c2Var.f4904b).clear();
    }

    @Override // androidx.recyclerview.widget.LinearLayoutManager
    public final void e1(f1 f1Var, l1 l1Var, z zVar, int i10) {
        v1();
        if (l1Var.b() > 0 && !l1Var.f4979g) {
            boolean z = i10 == 1;
            int iS1 = s1(zVar.f5109b, f1Var, l1Var);
            if (z) {
                while (iS1 > 0) {
                    int i11 = zVar.f5109b;
                    if (i11 <= 0) {
                        break;
                    }
                    int i12 = i11 - 1;
                    zVar.f5109b = i12;
                    iS1 = s1(i12, f1Var, l1Var);
                }
            } else {
                int iB = l1Var.b() - 1;
                int i13 = zVar.f5109b;
                while (i13 < iB) {
                    int i14 = i13 + 1;
                    int iS12 = s1(i14, f1Var, l1Var);
                    if (iS12 <= iS1) {
                        break;
                    }
                    i13 = i14;
                    iS1 = iS12;
                }
                zVar.f5109b = i13;
            }
        }
        p1();
    }

    @Override // androidx.recyclerview.widget.z0
    public final boolean f(a1 a1Var) {
        return a1Var instanceof v;
    }

    @Override // androidx.recyclerview.widget.z0
    public final void f0() {
        c2 c2Var = this.K;
        c2Var.f();
        ((SparseIntArray) c2Var.f4904b).clear();
    }

    @Override // androidx.recyclerview.widget.z0
    public final void g0(int i10, int i11) {
        c2 c2Var = this.K;
        c2Var.f();
        ((SparseIntArray) c2Var.f4904b).clear();
    }

    @Override // androidx.recyclerview.widget.z0
    public final void h0(int i10, int i11) {
        c2 c2Var = this.K;
        c2Var.f();
        ((SparseIntArray) c2Var.f4904b).clear();
    }

    @Override // androidx.recyclerview.widget.z0
    public final void j0(RecyclerView recyclerView, int i10, int i11) {
        c2 c2Var = this.K;
        c2Var.f();
        ((SparseIntArray) c2Var.f4904b).clear();
    }

    @Override // androidx.recyclerview.widget.LinearLayoutManager, androidx.recyclerview.widget.z0
    public final int k(l1 l1Var) {
        return O0(l1Var);
    }

    @Override // androidx.recyclerview.widget.LinearLayoutManager, androidx.recyclerview.widget.z0
    public final void k0(f1 f1Var, l1 l1Var) {
        boolean z = l1Var.f4979g;
        SparseIntArray sparseIntArray = this.J;
        SparseIntArray sparseIntArray2 = this.I;
        if (z) {
            int iW = w();
            for (int i10 = 0; i10 < iW; i10++) {
                v vVar = (v) v(i10).getLayoutParams();
                int layoutPosition = vVar.f4875a.getLayoutPosition();
                sparseIntArray2.put(layoutPosition, vVar.f5072f);
                sparseIntArray.put(layoutPosition, vVar.f5071e);
            }
        }
        super.k0(f1Var, l1Var);
        sparseIntArray2.clear();
        sparseIntArray.clear();
    }

    @Override // androidx.recyclerview.widget.LinearLayoutManager
    public final void k1(boolean z) {
        if (z) {
            throw new UnsupportedOperationException("GridLayoutManager does not support stack from end. Consider using reverse layout");
        }
        super.k1(false);
    }

    @Override // androidx.recyclerview.widget.LinearLayoutManager, androidx.recyclerview.widget.z0
    public final int l(l1 l1Var) {
        return P0(l1Var);
    }

    @Override // androidx.recyclerview.widget.LinearLayoutManager, androidx.recyclerview.widget.z0
    public final void l0(l1 l1Var) {
        super.l0(l1Var);
        this.E = false;
    }

    @Override // androidx.recyclerview.widget.LinearLayoutManager, androidx.recyclerview.widget.z0
    public final int n(l1 l1Var) {
        return O0(l1Var);
    }

    @Override // androidx.recyclerview.widget.LinearLayoutManager, androidx.recyclerview.widget.z0
    public final int o(l1 l1Var) {
        return P0(l1Var);
    }

    public final void o1(int i10) {
        int i11;
        int[] iArr = this.G;
        int i12 = this.F;
        if (iArr == null || iArr.length != i12 + 1 || iArr[iArr.length - 1] != i10) {
            iArr = new int[i12 + 1];
        }
        int i13 = 0;
        iArr[0] = 0;
        int i14 = i10 / i12;
        int i15 = i10 % i12;
        int i16 = 0;
        for (int i17 = 1; i17 <= i12; i17++) {
            i13 += i15;
            if (i13 <= 0 || i12 - i13 >= i15) {
                i11 = i14;
            } else {
                i11 = i14 + 1;
                i13 -= i12;
            }
            i16 += i11;
            iArr[i17] = i16;
        }
        this.G = iArr;
    }

    public final void p1() {
        View[] viewArr = this.H;
        if (viewArr == null || viewArr.length != this.F) {
            this.H = new View[this.F];
        }
    }

    public final int q1(int i10, int i11) {
        if (this.f4816p != 1 || !c1()) {
            int[] iArr = this.G;
            return iArr[i11 + i10] - iArr[i10];
        }
        int[] iArr2 = this.G;
        int i12 = this.F;
        return iArr2[i12 - i10] - iArr2[(i12 - i10) - i11];
    }

    public final int r1(int i10, f1 f1Var, l1 l1Var) {
        boolean z = l1Var.f4979g;
        c2 c2Var = this.K;
        if (!z) {
            c2Var.getClass();
            return c2.e(i10, this.F);
        }
        int iB = f1Var.b(i10);
        if (iB != -1) {
            c2Var.getClass();
            return c2.e(iB, this.F);
        }
        Log.w("GridLayoutManager", "Cannot find span size for pre layout position. " + i10);
        return 0;
    }

    @Override // androidx.recyclerview.widget.LinearLayoutManager, androidx.recyclerview.widget.z0
    public final a1 s() {
        return this.f4816p == 0 ? new v(-2, -1) : new v(-1, -2);
    }

    public final int s1(int i10, f1 f1Var, l1 l1Var) {
        boolean z = l1Var.f4979g;
        c2 c2Var = this.K;
        if (!z) {
            c2Var.getClass();
            return i10 % this.F;
        }
        int i11 = this.J.get(i10, -1);
        if (i11 != -1) {
            return i11;
        }
        int iB = f1Var.b(i10);
        if (iB != -1) {
            c2Var.getClass();
            return iB % this.F;
        }
        Log.w("GridLayoutManager", "Cannot find span size for pre layout position. It is not cached, not in the adapter. Pos:" + i10);
        return 0;
    }

    @Override // androidx.recyclerview.widget.z0
    public final a1 t(Context context, AttributeSet attributeSet) {
        v vVar = new v(context, attributeSet);
        vVar.f5071e = -1;
        vVar.f5072f = 0;
        return vVar;
    }

    public final int t1(int i10, f1 f1Var, l1 l1Var) {
        boolean z = l1Var.f4979g;
        c2 c2Var = this.K;
        if (!z) {
            c2Var.getClass();
            return 1;
        }
        int i11 = this.I.get(i10, -1);
        if (i11 != -1) {
            return i11;
        }
        if (f1Var.b(i10) != -1) {
            c2Var.getClass();
            return 1;
        }
        Log.w("GridLayoutManager", "Cannot find span size for pre layout position. It is not cached, not in the adapter. Pos:" + i10);
        return 1;
    }

    @Override // androidx.recyclerview.widget.z0
    public final a1 u(ViewGroup.LayoutParams layoutParams) {
        if (layoutParams instanceof ViewGroup.MarginLayoutParams) {
            v vVar = new v((ViewGroup.MarginLayoutParams) layoutParams);
            vVar.f5071e = -1;
            vVar.f5072f = 0;
            return vVar;
        }
        v vVar2 = new v(layoutParams);
        vVar2.f5071e = -1;
        vVar2.f5072f = 0;
        return vVar2;
    }

    public final void u1(View view, int i10, boolean z) {
        int iX;
        int iX2;
        v vVar = (v) view.getLayoutParams();
        Rect rect = vVar.f4876b;
        int i11 = rect.top + rect.bottom + ((ViewGroup.MarginLayoutParams) vVar).topMargin + ((ViewGroup.MarginLayoutParams) vVar).bottomMargin;
        int i12 = rect.left + rect.right + ((ViewGroup.MarginLayoutParams) vVar).leftMargin + ((ViewGroup.MarginLayoutParams) vVar).rightMargin;
        int iQ1 = q1(vVar.f5071e, vVar.f5072f);
        if (this.f4816p == 1) {
            iX2 = z0.x(iQ1, i10, i12, ((ViewGroup.MarginLayoutParams) vVar).width, false);
            iX = z0.x(this.f4818r.l(), this.f5124m, i11, ((ViewGroup.MarginLayoutParams) vVar).height, true);
        } else {
            int iX3 = z0.x(iQ1, i10, i11, ((ViewGroup.MarginLayoutParams) vVar).height, false);
            int iX4 = z0.x(this.f4818r.l(), this.f5123l, i12, ((ViewGroup.MarginLayoutParams) vVar).width, true);
            iX = iX3;
            iX2 = iX4;
        }
        a1 a1Var = (a1) view.getLayoutParams();
        if (z ? K0(view, iX2, iX, a1Var) : I0(view, iX2, iX, a1Var)) {
            view.measure(iX2, iX);
        }
    }

    public final void v1() {
        int iH;
        int iK;
        if (this.f4816p == 1) {
            iH = this.f5125n - J();
            iK = I();
        } else {
            iH = this.f5126o - H();
            iK = K();
        }
        o1(iH - iK);
    }

    @Override // androidx.recyclerview.widget.z0
    public final int y(f1 f1Var, l1 l1Var) {
        if (this.f4816p == 1) {
            return this.F;
        }
        if (l1Var.b() < 1) {
            return 0;
        }
        return r1(l1Var.b() - 1, f1Var, l1Var) + 1;
    }
}
