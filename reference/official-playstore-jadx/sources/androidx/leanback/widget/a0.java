package androidx.leanback.widget;

import android.content.Context;
import android.graphics.Rect;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.AttributeSet;
import android.util.Log;
import android.util.SparseArray;
import android.util.SparseIntArray;
import android.view.FocusFinder;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.accessibility.AccessibilityNodeInfo;
import androidx.core.view.b2;
import androidx.leanback.widget.picker.DatePicker;
import androidx.media3.extractor.ts.TsExtractor;
import androidx.media3.session.MediaUtils;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import java.util.Map;
import java.util.WeakHashMap;

/* JADX INFO: loaded from: classes3.dex */
public final class a0 extends androidx.recyclerview.widget.z0 {

    /* JADX INFO: renamed from: h0, reason: collision with root package name */
    public static final Rect f3059h0 = new Rect();

    /* JADX INFO: renamed from: i0, reason: collision with root package name */
    public static final int[] f3060i0 = new int[2];
    public androidx.recyclerview.widget.f1 A;
    public w G;
    public y H;
    public int J;
    public int K;
    public int L;
    public int M;
    public int[] N;
    public int O;
    public int P;
    public int Q;
    public int R;
    public int S;
    public int U;
    public t W;

    /* JADX INFO: renamed from: a0, reason: collision with root package name */
    public int f3061a0;
    public int b0;

    /* JADX INFO: renamed from: d0, reason: collision with root package name */
    public final r1 f3062d0;

    /* JADX INFO: renamed from: e0, reason: collision with root package name */
    public n f3063e0;

    /* JADX INFO: renamed from: f0, reason: collision with root package name */
    public final u f3064f0;

    /* JADX INFO: renamed from: g0, reason: collision with root package name */
    public final a8.e f3065g0;

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    public final h f3068r;

    /* JADX INFO: renamed from: u, reason: collision with root package name */
    public int f3071u;

    /* JADX INFO: renamed from: v, reason: collision with root package name */
    public androidx.recyclerview.widget.l1 f3072v;

    /* JADX INFO: renamed from: w, reason: collision with root package name */
    public int f3073w;
    public int x;
    public int[] z;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public float f3066p = 1.0f;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public int f3067q = 10;

    /* JADX INFO: renamed from: s, reason: collision with root package name */
    public int f3069s = 0;

    /* JADX INFO: renamed from: t, reason: collision with root package name */
    public androidx.emoji2.text.h f3070t = new androidx.recyclerview.widget.g0(this, 0);

    /* JADX INFO: renamed from: y, reason: collision with root package name */
    public final SparseIntArray f3074y = new SparseIntArray();
    public int B = 221696;
    public n0 C = null;
    public ArrayList D = null;
    public m0 E = null;
    public int F = -1;
    public int I = 0;
    public int T = 8388659;
    public int V = 1;
    public int X = 0;
    public final androidx.emoji2.text.e0 Y = new androidx.emoji2.text.e0(1);
    public final androidx.appcompat.app.i1 Z = new androidx.appcompat.app.i1(3);
    public final int[] c0 = new int[2];

    public a0(h hVar) {
        r1 r1Var = new r1(0);
        r1Var.f3112b = 0;
        r1Var.f3113c = 100;
        this.f3062d0 = r1Var;
        this.f3064f0 = new u(this, 0);
        this.f3065g0 = new a8.e(this, 4);
        this.f3068r = hVar;
        this.J = -1;
        if (this.f5121i) {
            this.f5121i = false;
            this.f5122j = 0;
            RecyclerView recyclerView = this.f5114b;
            if (recyclerView != null) {
                recyclerView.f4837m.m();
            }
        }
    }

    public static int Q0(View view) {
        x xVar;
        if (view == null || (xVar = (x) view.getLayoutParams()) == null || xVar.f4875a.isRemoved()) {
            return -1;
        }
        return xVar.f4875a.getAbsoluteAdapterPosition();
    }

    public static int R0(View view) {
        x xVar = (x) view.getLayoutParams();
        return androidx.recyclerview.widget.z0.C(view) + ((ViewGroup.MarginLayoutParams) xVar).topMargin + ((ViewGroup.MarginLayoutParams) xVar).bottomMargin;
    }

    public static int S0(View view) {
        x xVar = (x) view.getLayoutParams();
        return androidx.recyclerview.widget.z0.D(view) + ((ViewGroup.MarginLayoutParams) xVar).leftMargin + ((ViewGroup.MarginLayoutParams) xVar).rightMargin;
    }

    @Override // androidx.recyclerview.widget.z0
    public final void A(View view, Rect rect) {
        super.A(view, rect);
        x xVar = (x) view.getLayoutParams();
        rect.left += xVar.f3170e;
        rect.top += xVar.f3171f;
        rect.right -= xVar.f3172g;
        rect.bottom -= xVar.f3173h;
    }

    @Override // androidx.recyclerview.widget.z0
    public final int A0(int i10, androidx.recyclerview.widget.f1 f1Var, androidx.recyclerview.widget.l1 l1Var) {
        if ((this.B & 512) == 0 || this.W == null) {
            return 0;
        }
        k1(f1Var, l1Var);
        this.B = (this.B & (-4)) | 2;
        int iL1 = this.f3069s == 0 ? l1(i10) : m1(i10);
        c1();
        this.B &= -4;
        return iL1;
    }

    @Override // androidx.recyclerview.widget.z0
    public final int B(View view) {
        return super.B(view) + ((x) view.getLayoutParams()).f3170e;
    }

    @Override // androidx.recyclerview.widget.z0
    public final void B0(int i10) {
        r1(i10, false);
    }

    @Override // androidx.recyclerview.widget.z0
    public final int C0(int i10, androidx.recyclerview.widget.f1 f1Var, androidx.recyclerview.widget.l1 l1Var) {
        int i11 = this.B;
        if ((i11 & 512) == 0 || this.W == null) {
            return 0;
        }
        this.B = (i11 & (-4)) | 2;
        k1(f1Var, l1Var);
        int iL1 = this.f3069s == 1 ? l1(i10) : m1(i10);
        c1();
        this.B &= -4;
        return iL1;
    }

    @Override // androidx.recyclerview.widget.z0
    public final int E(View view) {
        return super.E(view) - ((x) view.getLayoutParams()).f3172g;
    }

    @Override // androidx.recyclerview.widget.z0
    public final int F(View view) {
        return super.F(view) + ((x) view.getLayoutParams()).f3171f;
    }

    @Override // androidx.recyclerview.widget.z0
    public final boolean L0() {
        return true;
    }

    public final void M0() {
        this.W.b((this.B & MediaUtils.TRANSACTION_SIZE_LIMIT_IN_BYTES) != 0 ? (-this.b0) - this.x : this.f3061a0 + this.b0 + this.x, false);
    }

    @Override // androidx.recyclerview.widget.z0
    public final int N(androidx.recyclerview.widget.f1 f1Var, androidx.recyclerview.widget.l1 l1Var) {
        t tVar;
        if (this.f3069s != 0 || (tVar = this.W) == null) {
            return -1;
        }
        return tVar.f3129e;
    }

    public final void N0() {
        ArrayList arrayList;
        if (this.C != null || ((arrayList = this.D) != null && arrayList.size() > 0)) {
            int i10 = this.F;
            View viewR = i10 == -1 ? null : r(i10);
            h hVar = this.f3068r;
            if (viewR != null) {
                androidx.recyclerview.widget.p1 p1VarF = hVar.F(viewR);
                n0 n0Var = this.C;
                if (n0Var != null) {
                    if (p1VarF != null) {
                        p1VarF.getItemId();
                    }
                    n0Var.a();
                }
                P0(hVar, p1VarF, this.F);
            } else {
                n0 n0Var2 = this.C;
                if (n0Var2 != null) {
                    n0Var2.a();
                }
                P0(hVar, null, -1);
            }
            if ((this.B & 3) == 1 || hVar.isLayoutRequested()) {
                return;
            }
            int iW = w();
            for (int i11 = 0; i11 < iW; i11++) {
                if (v(i11).isLayoutRequested()) {
                    WeakHashMap weakHashMap = b2.f2200a;
                    hVar.postOnAnimation(this.f3064f0);
                    return;
                }
            }
        }
    }

    public final void O0() {
        ArrayList arrayList = this.D;
        if (arrayList == null || arrayList.size() <= 0) {
            return;
        }
        int i10 = this.F;
        View viewR = i10 == -1 ? null : r(i10);
        if (viewR != null) {
            this.f3068r.F(viewR);
            ArrayList arrayList2 = this.D;
            if (arrayList2 == null) {
                return;
            }
            for (int size = arrayList2.size() - 1; size >= 0; size--) {
                ((o0) this.D.get(size)).getClass();
            }
            return;
        }
        n0 n0Var = this.C;
        if (n0Var != null) {
            n0Var.a();
        }
        ArrayList arrayList3 = this.D;
        if (arrayList3 == null) {
            return;
        }
        for (int size2 = arrayList3.size() - 1; size2 >= 0; size2--) {
            ((o0) this.D.get(size2)).getClass();
        }
    }

    public final void P0(RecyclerView recyclerView, androidx.recyclerview.widget.p1 p1Var, int i10) {
        ArrayList arrayList = this.D;
        if (arrayList == null) {
            return;
        }
        for (int size = arrayList.size() - 1; size >= 0; size--) {
            m1.a aVar = (m1.a) ((o0) this.D.get(size));
            aVar.getClass();
            m1.e eVar = aVar.f20145a;
            int iIndexOf = eVar.f20153l.indexOf((VerticalGridView) recyclerView);
            eVar.d(iIndexOf);
            if (p1Var != null) {
                int i11 = ((m1.f) eVar.f20154m.get(iIndexOf)).f20167b + i10;
                DatePicker datePicker = (DatePicker) eVar;
                datePicker.L.setTimeInMillis(datePicker.K.getTimeInMillis());
                ArrayList arrayList2 = datePicker.f20154m;
                int i12 = (arrayList2 == null ? null : (m1.f) arrayList2.get(iIndexOf)).f20166a;
                if (iIndexOf == datePicker.E) {
                    datePicker.L.add(5, i11 - i12);
                } else if (iIndexOf == datePicker.D) {
                    datePicker.L.add(2, i11 - i12);
                } else {
                    if (iIndexOf != datePicker.F) {
                        throw new IllegalArgumentException();
                    }
                    datePicker.L.add(1, i11 - i12);
                }
                datePicker.h(datePicker.L.get(1), datePicker.L.get(2), datePicker.L.get(5));
            }
        }
    }

    public final int T0(int i10) {
        int i11 = this.f3069s;
        if (i11 != 0) {
            if (i11 == 1) {
                if (i10 == 17) {
                    return (this.B & 524288) == 0 ? 2 : 3;
                }
                if (i10 == 33) {
                    return 0;
                }
                if (i10 == 66) {
                    return (this.B & 524288) == 0 ? 3 : 2;
                }
                if (i10 == 130) {
                    return 1;
                }
            }
        }
        if (i10 != 17) {
            if (i10 == 33) {
                return 2;
            }
            if (i10 != 66) {
                return i10 != 130 ? 17 : 3;
            }
            if ((this.B & MediaUtils.TRANSACTION_SIZE_LIMIT_IN_BYTES) != 0) {
                return 0;
            }
        } else if ((this.B & MediaUtils.TRANSACTION_SIZE_LIMIT_IN_BYTES) == 0) {
            return 0;
        }
        return 1;
    }

    public final int U0(int i10) {
        int i11 = this.M;
        if (i11 != 0) {
            return i11;
        }
        int[] iArr = this.N;
        if (iArr == null) {
            return 0;
        }
        return iArr[i10];
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // androidx.recyclerview.widget.z0
    public final void V(androidx.recyclerview.widget.l0 l0Var, androidx.recyclerview.widget.l0 l0Var2) {
        if (l0Var != null) {
            this.W = null;
            this.N = null;
            this.B &= -1025;
            this.F = -1;
            this.I = 0;
            t.r rVar = (t.r) this.f3062d0.f3114d;
            if (rVar != null) {
                rVar.evictAll();
            }
        }
        if (l0Var2 instanceof n) {
            this.f3063e0 = (n) l0Var2;
        } else {
            this.f3063e0 = null;
        }
    }

    public final int V0(int i10) {
        int iU0 = 0;
        if ((this.B & 524288) != 0) {
            for (int i11 = this.U - 1; i11 > i10; i11--) {
                iU0 += U0(i11) + this.S;
            }
            return iU0;
        }
        int iU02 = 0;
        while (iU0 < i10) {
            iU02 += U0(iU0) + this.S;
            iU0++;
        }
        return iU02;
    }

    /* JADX WARN: Removed duplicated region for block: B:26:0x0053  */
    /* JADX WARN: Removed duplicated region for block: B:27:0x0055  */
    /* JADX WARN: Removed duplicated region for block: B:29:0x005b  */
    /* JADX WARN: Removed duplicated region for block: B:43:0x007a A[ADDED_TO_REGION] */
    /* JADX WARN: Removed duplicated region for block: B:45:0x0083  */
    /* JADX WARN: Removed duplicated region for block: B:51:0x008f  */
    /* JADX WARN: Removed duplicated region for block: B:53:0x0092  */
    /* JADX WARN: Removed duplicated region for block: B:54:0x009b  */
    /* JADX WARN: Removed duplicated region for block: B:56:0x009e  */
    /* JADX WARN: Removed duplicated region for block: B:59:0x00ab  */
    /* JADX WARN: Removed duplicated region for block: B:62:0x00b1  */
    /* JADX WARN: Removed duplicated region for block: B:64:0x00b4  */
    /* JADX WARN: Removed duplicated region for block: B:65:0x00b6  */
    @Override // androidx.recyclerview.widget.z0
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final boolean W(androidx.recyclerview.widget.RecyclerView r19, java.util.ArrayList r20, int r21, int r22) {
        /*
            Method dump skipped, instruction units count: 436
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.leanback.widget.a0.W(androidx.recyclerview.widget.RecyclerView, java.util.ArrayList, int, int):boolean");
    }

    /* JADX WARN: Removed duplicated region for block: B:71:0x0154  */
    /* JADX WARN: Removed duplicated region for block: B:72:0x0156  */
    /* JADX WARN: Removed duplicated region for block: B:76:0x015d  */
    /* JADX WARN: Removed duplicated region for block: B:78:0x0171  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final boolean W0(android.view.View r13, android.view.View r14, int[] r15) {
        /*
            Method dump skipped, instruction units count: 407
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.leanback.widget.a0.W0(android.view.View, android.view.View, int[]):boolean");
    }

    public final int X0() {
        int i10 = (this.B & 524288) != 0 ? 0 : this.U - 1;
        return U0(i10) + V0(i10);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final View Y0(int i10) {
        n nVar;
        View view = this.A.k(i10, Long.MAX_VALUE).itemView;
        x xVar = (x) view.getLayoutParams();
        androidx.recyclerview.widget.p1 p1VarF = this.f3068r.F(view);
        Object objA = p1VarF instanceof m ? ((m) p1VarF).a() : null;
        if (objA == null && (nVar = this.f3063e0) != null) {
            p1VarF.getItemViewType();
            m mVarA = nVar.a();
            if (mVarA != null) {
                objA = mVarA.a();
            }
        }
        if (objA != null) {
            throw new ClassCastException();
        }
        xVar.getClass();
        return view;
    }

    public final boolean Z0() {
        RecyclerView recyclerView = this.f5114b;
        androidx.recyclerview.widget.l0 adapter = recyclerView != null ? recyclerView.getAdapter() : null;
        int itemCount = adapter != null ? adapter.getItemCount() : 0;
        return itemCount == 0 || this.f3068r.C(itemCount - 1) != null;
    }

    @Override // androidx.recyclerview.widget.z0
    public final void a0(androidx.recyclerview.widget.f1 f1Var, androidx.recyclerview.widget.l1 l1Var, q0.k kVar) {
        k1(f1Var, l1Var);
        int iB = l1Var.b();
        int i10 = this.B;
        boolean z = (262144 & i10) != 0;
        if ((i10 & 2048) == 0 || (iB > 1 && !a1(0))) {
            if (this.f3069s == 0) {
                kVar.b(z ? q0.e.f21313j : q0.e.f21311h);
            } else {
                kVar.b(q0.e.f21310g);
            }
            kVar.m();
        }
        if ((this.B & 4096) == 0 || (iB > 1 && !a1(iB - 1))) {
            if (this.f3069s == 0) {
                kVar.b(z ? q0.e.f21311h : q0.e.f21313j);
            } else {
                kVar.b(q0.e.f21312i);
            }
            kVar.m();
        }
        kVar.f21319a.setCollectionInfo(AccessibilityNodeInfo.CollectionInfo.obtain(N(f1Var, l1Var), y(f1Var, l1Var), false, 0));
        c1();
    }

    public final boolean a1(int i10) {
        h hVar = this.f3068r;
        androidx.recyclerview.widget.p1 p1VarC = hVar.C(i10);
        return p1VarC != null && p1VarC.itemView.getLeft() >= 0 && p1VarC.itemView.getRight() <= hVar.getWidth() && p1VarC.itemView.getTop() >= 0 && p1VarC.itemView.getBottom() <= hVar.getHeight();
    }

    public final void b1(View view, int i10, int i11, int i12, int i13) {
        int iU0;
        int i14;
        int iR0 = this.f3069s == 0 ? R0(view) : S0(view);
        int i15 = this.M;
        if (i15 > 0) {
            iR0 = Math.min(iR0, i15);
        }
        int i16 = this.T;
        int i17 = i16 & 112;
        int absoluteGravity = (this.B & 786432) != 0 ? Gravity.getAbsoluteGravity(i16 & 8388615, 1) : i16 & 7;
        int i18 = this.f3069s;
        if ((i18 != 0 || i17 != 48) && (i18 != 1 || absoluteGravity != 3)) {
            if ((i18 == 0 && i17 == 80) || (i18 == 1 && absoluteGravity == 5)) {
                iU0 = U0(i10) - iR0;
            } else if ((i18 == 0 && i17 == 16) || (i18 == 1 && absoluteGravity == 1)) {
                iU0 = (U0(i10) - iR0) / 2;
            }
            i13 += iU0;
        }
        if (this.f3069s == 0) {
            i14 = iR0 + i13;
        } else {
            int i19 = iR0 + i13;
            int i20 = i13;
            i13 = i11;
            i11 = i20;
            i14 = i12;
            i12 = i19;
        }
        x xVar = (x) view.getLayoutParams();
        androidx.recyclerview.widget.z0.S(view, i11, i13, i12, i14);
        Rect rect = f3059h0;
        super.A(view, rect);
        int i21 = i11 - rect.left;
        int i22 = i13 - rect.top;
        int i23 = rect.right - i12;
        int i24 = rect.bottom - i14;
        xVar.f3170e = i21;
        xVar.f3171f = i22;
        xVar.f3172g = i23;
        xVar.f3173h = i24;
        u1(view);
    }

    @Override // androidx.recyclerview.widget.z0
    public final void c0(androidx.recyclerview.widget.f1 f1Var, androidx.recyclerview.widget.l1 l1Var, View view, q0.k kVar) {
        r rVarK;
        ViewGroup.LayoutParams layoutParams = view.getLayoutParams();
        if (this.W == null || !(layoutParams instanceof x)) {
            return;
        }
        int absoluteAdapterPosition = ((x) layoutParams).f4875a.getAbsoluteAdapterPosition();
        int i10 = -1;
        if (absoluteAdapterPosition >= 0 && (rVarK = this.W.k(absoluteAdapterPosition)) != null) {
            i10 = rVarK.f3109a;
        }
        if (i10 < 0) {
            return;
        }
        int i11 = absoluteAdapterPosition / this.W.f3129e;
        if (this.f3069s == 0) {
            kVar.h(q0.j.a(i10, 1, i11, 1, false));
        } else {
            kVar.h(q0.j.a(i11, 1, i10, 1, false));
        }
    }

    public final void c1() {
        int i10 = this.f3071u - 1;
        this.f3071u = i10;
        if (i10 == 0) {
            this.A = null;
            this.f3072v = null;
            this.f3073w = 0;
            this.x = 0;
        }
    }

    @Override // androidx.recyclerview.widget.z0
    public final boolean d() {
        return this.f3069s == 0 || this.U > 1;
    }

    @Override // androidx.recyclerview.widget.z0
    public final View d0(int i10, View view) {
        View viewFindNextFocus;
        View viewFindNextFocus2;
        if ((this.B & 32768) != 0) {
            return view;
        }
        FocusFinder focusFinder = FocusFinder.getInstance();
        boolean z = true;
        h hVar = this.f3068r;
        if (i10 == 2 || i10 == 1) {
            if (e()) {
                viewFindNextFocus = focusFinder.findNextFocus(hVar, view, i10 == 2 ? TsExtractor.TS_STREAM_TYPE_HDMV_DTS : 33);
            } else {
                viewFindNextFocus = null;
            }
            if (d()) {
                viewFindNextFocus2 = focusFinder.findNextFocus(hVar, view, (G() == 1) ^ (i10 == 2) ? 66 : 17);
            } else {
                viewFindNextFocus2 = viewFindNextFocus;
            }
        } else {
            viewFindNextFocus2 = focusFinder.findNextFocus(hVar, view, i10);
        }
        if (viewFindNextFocus2 != null) {
            return viewFindNextFocus2;
        }
        if (hVar.getDescendantFocusability() == 393216) {
            return hVar.getParent().focusSearch(view, i10);
        }
        int iT0 = T0(i10);
        boolean z5 = hVar.getScrollState() != 0;
        if (iT0 == 1) {
            if (z5 || (this.B & 4096) == 0) {
                viewFindNextFocus2 = view;
            }
            if ((this.B & 131072) != 0 && !Z0()) {
                f1(true);
                viewFindNextFocus2 = view;
            }
        } else if (iT0 == 0) {
            if (z5 || (this.B & 2048) == 0) {
                viewFindNextFocus2 = view;
            }
            if ((this.B & 131072) != 0) {
                RecyclerView recyclerView = this.f5114b;
                androidx.recyclerview.widget.l0 adapter = recyclerView != null ? recyclerView.getAdapter() : null;
                if ((adapter != null ? adapter.getItemCount() : 0) != 0 && hVar.C(0) == null) {
                    z = false;
                }
                if (!z) {
                    f1(false);
                    viewFindNextFocus2 = view;
                }
            }
        } else if (iT0 == 3) {
        }
        if (viewFindNextFocus2 != null) {
            return viewFindNextFocus2;
        }
        View viewFocusSearch = hVar.getParent().focusSearch(view, i10);
        return viewFocusSearch != null ? viewFocusSearch : view != null ? view : hVar;
    }

    public final void d1(View view) {
        int childMeasureSpec;
        int childMeasureSpec2;
        x xVar = (x) view.getLayoutParams();
        Rect rect = f3059h0;
        c(view, rect);
        int i10 = ((ViewGroup.MarginLayoutParams) xVar).leftMargin + ((ViewGroup.MarginLayoutParams) xVar).rightMargin + rect.left + rect.right;
        int i11 = ((ViewGroup.MarginLayoutParams) xVar).topMargin + ((ViewGroup.MarginLayoutParams) xVar).bottomMargin + rect.top + rect.bottom;
        int iMakeMeasureSpec = this.L == -2 ? View.MeasureSpec.makeMeasureSpec(0, 0) : View.MeasureSpec.makeMeasureSpec(this.M, 1073741824);
        if (this.f3069s == 0) {
            childMeasureSpec = ViewGroup.getChildMeasureSpec(View.MeasureSpec.makeMeasureSpec(0, 0), i10, ((ViewGroup.MarginLayoutParams) xVar).width);
            childMeasureSpec2 = ViewGroup.getChildMeasureSpec(iMakeMeasureSpec, i11, ((ViewGroup.MarginLayoutParams) xVar).height);
        } else {
            int childMeasureSpec3 = ViewGroup.getChildMeasureSpec(View.MeasureSpec.makeMeasureSpec(0, 0), i11, ((ViewGroup.MarginLayoutParams) xVar).height);
            childMeasureSpec = ViewGroup.getChildMeasureSpec(iMakeMeasureSpec, i10, ((ViewGroup.MarginLayoutParams) xVar).width);
            childMeasureSpec2 = childMeasureSpec3;
        }
        view.measure(childMeasureSpec, childMeasureSpec2);
    }

    @Override // androidx.recyclerview.widget.z0
    public final boolean e() {
        return this.f3069s == 1 || this.U > 1;
    }

    @Override // androidx.recyclerview.widget.z0
    public final void e0(int i10, int i11) {
        t tVar;
        int i12;
        int i13 = this.F;
        if (i13 != -1 && (tVar = this.W) != null && tVar.f3130f >= 0 && (i12 = this.I) != Integer.MIN_VALUE && i10 <= i13 + i12) {
            this.I = i12 + i11;
        }
        t.r rVar = (t.r) this.f3062d0.f3114d;
        if (rVar != null) {
            rVar.evictAll();
        }
    }

    public final void e1() {
        this.W.m((this.B & MediaUtils.TRANSACTION_SIZE_LIMIT_IN_BYTES) != 0 ? this.f3061a0 + this.b0 + this.x : (-this.b0) - this.x, false);
    }

    @Override // androidx.recyclerview.widget.z0
    public final void f0() {
        this.I = 0;
        t.r rVar = (t.r) this.f3062d0.f3114d;
        if (rVar != null) {
            rVar.evictAll();
        }
    }

    public final void f1(boolean z) {
        if (!z) {
            RecyclerView recyclerView = this.f5114b;
            androidx.recyclerview.widget.l0 adapter = recyclerView != null ? recyclerView.getAdapter() : null;
            if ((adapter != null ? adapter.getItemCount() : 0) == 0 || this.f3068r.C(0) != null) {
                return;
            }
        } else if (Z0()) {
            return;
        }
        y yVar = this.H;
        if (yVar == null) {
            y yVar2 = new y(this, z ? 1 : -1, this.U > 1);
            this.I = 0;
            s1(yVar2);
            return;
        }
        a0 a0Var = yVar.f3179t;
        if (z) {
            int i10 = yVar.f3178s;
            if (i10 < a0Var.f3067q) {
                yVar.f3178s = i10 + 1;
                return;
            }
            return;
        }
        int i11 = yVar.f3178s;
        if (i11 > (-a0Var.f3067q)) {
            yVar.f3178s = i11 - 1;
        }
    }

    @Override // androidx.recyclerview.widget.z0
    public final void g0(int i10, int i11) {
        int i12;
        int i13 = this.F;
        if (i13 != -1 && (i12 = this.I) != Integer.MIN_VALUE) {
            int i14 = i13 + i12;
            if (i10 <= i14 && i14 < i10 + 1) {
                this.I = (i11 - i10) + i12;
            } else if (i10 < i14 && i11 > i14 - 1) {
                this.I = i12 - 1;
            } else if (i10 > i14 && i11 < i14) {
                this.I = i12 + 1;
            }
        }
        t.r rVar = (t.r) this.f3062d0.f3114d;
        if (rVar != null) {
            rVar.evictAll();
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:75:0x014b  */
    /* JADX WARN: Removed duplicated region for block: B:77:0x014f  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final boolean g1(boolean r18) {
        /*
            Method dump skipped, instruction units count: 360
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.leanback.widget.a0.g1(boolean):boolean");
    }

    @Override // androidx.recyclerview.widget.z0
    public final void h(int i10, int i11, androidx.recyclerview.widget.l1 l1Var, androidx.recyclerview.widget.s sVar) {
        try {
            k1(null, l1Var);
            if (this.f3069s != 0) {
                i10 = i11;
            }
            if (w() != 0 && i10 != 0) {
                this.W.e(i10 < 0 ? -this.b0 : this.f3061a0 + this.b0, i10, sVar);
                c1();
            }
        } finally {
            c1();
        }
    }

    @Override // androidx.recyclerview.widget.z0
    public final void h0(int i10, int i11) {
        t tVar;
        int i12;
        int i13;
        int i14 = this.F;
        if (i14 != -1 && (tVar = this.W) != null && tVar.f3130f >= 0 && (i12 = this.I) != Integer.MIN_VALUE && i10 <= (i13 = i14 + i12)) {
            if (i10 + i11 > i13) {
                this.F = (i10 - i13) + i12 + i14;
                this.I = Integer.MIN_VALUE;
            } else {
                this.I = i12 - i11;
            }
        }
        t.r rVar = (t.r) this.f3062d0.f3114d;
        if (rVar != null) {
            rVar.evictAll();
        }
    }

    public final int h1(int i10, boolean z) {
        r rVarK;
        t tVar = this.W;
        if (tVar == null) {
            return i10;
        }
        int i11 = this.F;
        int i12 = (i11 == -1 || (rVarK = tVar.k(i11)) == null) ? -1 : rVarK.f3109a;
        int iW = w();
        View view = null;
        for (int i13 = 0; i13 < iW && i10 != 0; i13++) {
            int i14 = i10 > 0 ? i13 : (iW - 1) - i13;
            View viewV = v(i14);
            if (viewV.getVisibility() == 0 && (!P() || viewV.hasFocusable())) {
                int iQ0 = Q0(v(i14));
                r rVarK2 = this.W.k(iQ0);
                int i15 = rVarK2 == null ? -1 : rVarK2.f3109a;
                if (i12 == -1) {
                    i11 = iQ0;
                    view = viewV;
                    i12 = i15;
                } else if (i15 == i12 && ((i10 > 0 && iQ0 > i11) || (i10 < 0 && iQ0 < i11))) {
                    i10 = i10 > 0 ? i10 - 1 : i10 + 1;
                    i11 = iQ0;
                    view = viewV;
                }
            }
        }
        if (view != null) {
            if (z) {
                if (P()) {
                    this.B |= 32;
                    view.requestFocus();
                    this.B &= -33;
                }
                this.F = i11;
                return i10;
            }
            o1(view, view.findFocus(), true, 0, 0);
        }
        return i10;
    }

    @Override // androidx.recyclerview.widget.z0
    public final void i(int i10, androidx.recyclerview.widget.s sVar) {
        int i11 = this.f3068r.f3085b1;
        if (i10 == 0 || i11 == 0) {
            return;
        }
        int iMax = Math.max(0, Math.min(this.F - ((i11 - 1) / 2), i10 - i11));
        for (int i12 = iMax; i12 < i10 && i12 < iMax + i11; i12++) {
            sVar.b(i12, 0);
        }
    }

    @Override // androidx.recyclerview.widget.z0
    public final void i0(int i10, int i11) {
        int i12 = i11 + i10;
        while (i10 < i12) {
            r1 r1Var = this.f3062d0;
            t.r rVar = (t.r) r1Var.f3114d;
            if (rVar != null && rVar.size() != 0) {
                ((t.r) r1Var.f3114d).remove(Integer.toString(i10));
            }
            i10++;
        }
    }

    public final void i1() {
        int i10 = this.B;
        if ((65600 & i10) == 65536) {
            t tVar = this.W;
            int i11 = this.F;
            int i12 = (i10 & MediaUtils.TRANSACTION_SIZE_LIMIT_IN_BYTES) != 0 ? -this.b0 : this.f3061a0 + this.b0;
            while (true) {
                int i13 = tVar.f3131g;
                if (i13 >= tVar.f3130f && i13 > i11) {
                    if (!tVar.f3127c) {
                        if (tVar.f3126b.I(i13) < i12) {
                            break;
                        }
                        tVar.f3126b.r0(tVar.f3131g);
                        tVar.f3131g--;
                    } else {
                        if (tVar.f3126b.I(i13) > i12) {
                            break;
                        }
                        tVar.f3126b.r0(tVar.f3131g);
                        tVar.f3131g--;
                    }
                } else {
                    break;
                }
            }
            if (tVar.f3131g < tVar.f3130f) {
                tVar.f3131g = -1;
                tVar.f3130f = -1;
            }
        }
    }

    public final void j1() {
        int i10 = this.B;
        if ((65600 & i10) == 65536) {
            t tVar = this.W;
            int i11 = this.F;
            int i12 = (i10 & MediaUtils.TRANSACTION_SIZE_LIMIT_IN_BYTES) != 0 ? this.f3061a0 + this.b0 : -this.b0;
            while (true) {
                int i13 = tVar.f3131g;
                int i14 = tVar.f3130f;
                if (i13 >= i14 && i14 < i11) {
                    int iL = tVar.f3126b.L(i14);
                    if (!tVar.f3127c) {
                        if (tVar.f3126b.I(tVar.f3130f) + iL > i12) {
                            break;
                        }
                        tVar.f3126b.r0(tVar.f3130f);
                        tVar.f3130f++;
                    } else {
                        if (tVar.f3126b.I(tVar.f3130f) - iL < i12) {
                            break;
                        }
                        tVar.f3126b.r0(tVar.f3130f);
                        tVar.f3130f++;
                    }
                } else {
                    break;
                }
            }
            if (tVar.f3131g < tVar.f3130f) {
                tVar.f3131g = -1;
                tVar.f3130f = -1;
            }
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:132:0x02bb A[LOOP:3: B:131:0x02b9->B:132:0x02bb, LOOP_END] */
    /* JADX WARN: Removed duplicated region for block: B:135:0x02d9  */
    /* JADX WARN: Removed duplicated region for block: B:141:0x02ee A[LOOP:11: B:141:0x02ee->B:361:?, LOOP_START] */
    /* JADX WARN: Removed duplicated region for block: B:159:0x0347  */
    /* JADX WARN: Removed duplicated region for block: B:174:0x03fd  */
    /* JADX WARN: Removed duplicated region for block: B:175:0x0400  */
    /* JADX WARN: Removed duplicated region for block: B:185:0x0415 A[LOOP:12: B:185:0x0415->B:362:?, LOOP_START] */
    /* JADX WARN: Removed duplicated region for block: B:310:0x060c A[PHI: r1 r2
      0x060c: PHI (r1v41 int) = (r1v37 int), (r1v44 int) binds: [B:321:0x0639, B:309:0x060a] A[DONT_GENERATE, DONT_INLINE]
      0x060c: PHI (r2v43 int) = (r2v39 int), (r2v47 int) binds: [B:321:0x0639, B:309:0x060a] A[DONT_GENERATE, DONT_INLINE]] */
    @Override // androidx.recyclerview.widget.z0
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final void k0(androidx.recyclerview.widget.f1 r26, androidx.recyclerview.widget.l1 r27) {
        /*
            Method dump skipped, instruction units count: 1609
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.leanback.widget.a0.k0(androidx.recyclerview.widget.f1, androidx.recyclerview.widget.l1):void");
    }

    public final void k1(androidx.recyclerview.widget.f1 f1Var, androidx.recyclerview.widget.l1 l1Var) {
        int i10 = this.f3071u;
        if (i10 == 0) {
            this.A = f1Var;
            this.f3072v = l1Var;
            this.f3073w = 0;
            this.x = 0;
        }
        this.f3071u = i10 + 1;
    }

    @Override // androidx.recyclerview.widget.z0
    public final void l0(androidx.recyclerview.widget.l1 l1Var) {
    }

    /* JADX WARN: Removed duplicated region for block: B:20:0x0031 A[PHI: r0
      0x0031: PHI (r0v9 int) = (r0v8 int), (r0v12 int) binds: [B:19:0x002f, B:12:0x001d] A[DONT_GENERATE, DONT_INLINE]] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final int l1(int r7) {
        /*
            r6 = this;
            int r0 = r6.B
            r1 = r0 & 64
            r2 = 1
            if (r1 != 0) goto L32
            r0 = r0 & 3
            if (r0 == r2) goto L32
            androidx.emoji2.text.e0 r0 = r6.Y
            if (r7 <= 0) goto L20
            java.lang.Object r0 = r0.f2602n
            androidx.leanback.widget.t1 r0 = (androidx.leanback.widget.t1) r0
            int r1 = r0.f3134a
            r3 = 2147483647(0x7fffffff, float:NaN)
            if (r1 != r3) goto L1b
            goto L32
        L1b:
            int r0 = r0.f3136c
            if (r7 <= r0) goto L32
            goto L31
        L20:
            if (r7 >= 0) goto L32
            java.lang.Object r0 = r0.f2602n
            androidx.leanback.widget.t1 r0 = (androidx.leanback.widget.t1) r0
            int r1 = r0.f3135b
            r3 = -2147483648(0xffffffff80000000, float:-0.0)
            if (r1 != r3) goto L2d
            goto L32
        L2d:
            int r0 = r0.f3137d
            if (r7 >= r0) goto L32
        L31:
            r7 = r0
        L32:
            r0 = 0
            if (r7 != 0) goto L36
            return r0
        L36:
            int r1 = -r7
            int r3 = r6.w()
            int r4 = r6.f3069s
            if (r4 != r2) goto L4c
            r4 = r0
        L40:
            if (r4 >= r3) goto L59
            android.view.View r5 = r6.v(r4)
            r5.offsetTopAndBottom(r1)
            int r4 = r4 + 1
            goto L40
        L4c:
            r4 = r0
        L4d:
            if (r4 >= r3) goto L59
            android.view.View r5 = r6.v(r4)
            r5.offsetLeftAndRight(r1)
            int r4 = r4 + 1
            goto L4d
        L59:
            int r1 = r6.B
            r1 = r1 & 3
            if (r1 != r2) goto L63
            r6.x1()
            return r7
        L63:
            int r1 = r6.w()
            int r3 = r6.B
            r4 = 262144(0x40000, float:3.67342E-40)
            r3 = r3 & r4
            if (r3 == 0) goto L71
            if (r7 <= 0) goto L77
            goto L73
        L71:
            if (r7 >= 0) goto L77
        L73:
            r6.e1()
            goto L7a
        L77:
            r6.M0()
        L7a:
            int r3 = r6.w()
            if (r3 <= r1) goto L82
            r1 = r2
            goto L83
        L82:
            r1 = r0
        L83:
            int r3 = r6.w()
            int r5 = r6.B
            r4 = r4 & r5
            if (r4 == 0) goto L8f
            if (r7 <= 0) goto L95
            goto L91
        L8f:
            if (r7 >= 0) goto L95
        L91:
            r6.i1()
            goto L98
        L95:
            r6.j1()
        L98:
            int r4 = r6.w()
            if (r4 >= r3) goto L9f
            goto La0
        L9f:
            r2 = r0
        La0:
            r0 = r1 | r2
            if (r0 == 0) goto La7
            r6.w1()
        La7:
            androidx.leanback.widget.h r0 = r6.f3068r
            r0.invalidate()
            r6.x1()
            return r7
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.leanback.widget.a0.l1(int):int");
    }

    @Override // androidx.recyclerview.widget.z0
    public final void m0(androidx.recyclerview.widget.f1 f1Var, androidx.recyclerview.widget.l1 l1Var, int i10, int i11) {
        int size;
        int size2;
        int mode;
        int I;
        int iJ;
        int iX0;
        k1(f1Var, l1Var);
        if (this.f3069s == 0) {
            size2 = View.MeasureSpec.getSize(i10);
            size = View.MeasureSpec.getSize(i11);
            mode = View.MeasureSpec.getMode(i11);
            I = K();
            iJ = H();
        } else {
            size = View.MeasureSpec.getSize(i10);
            size2 = View.MeasureSpec.getSize(i11);
            mode = View.MeasureSpec.getMode(i10);
            I = I();
            iJ = J();
        }
        int i12 = iJ + I;
        this.O = size;
        int i13 = this.L;
        if (i13 == -2) {
            int i14 = this.V;
            if (i14 == 0) {
                i14 = 1;
            }
            this.U = i14;
            this.M = 0;
            int[] iArr = this.N;
            if (iArr == null || iArr.length != i14) {
                this.N = new int[i14];
            }
            if (this.f3072v.f4979g) {
                v1();
            }
            g1(true);
            if (mode == Integer.MIN_VALUE) {
                size = Math.min(X0() + i12, this.O);
            } else if (mode == 0) {
                iX0 = X0();
                size = iX0 + i12;
            } else {
                if (mode != 1073741824) {
                    throw new IllegalStateException("wrong spec");
                }
                size = this.O;
            }
        } else {
            if (mode != Integer.MIN_VALUE) {
                if (mode == 0) {
                    if (i13 == 0) {
                        i13 = size - i12;
                    }
                    this.M = i13;
                    int i15 = this.V;
                    if (i15 == 0) {
                        i15 = 1;
                    }
                    this.U = i15;
                    iX0 = ((i15 - 1) * this.S) + (i13 * i15);
                    size = iX0 + i12;
                } else if (mode != 1073741824) {
                    throw new IllegalStateException("wrong spec");
                }
            }
            int i16 = this.V;
            if (i16 == 0 && i13 == 0) {
                this.U = 1;
                this.M = size - i12;
            } else if (i16 == 0) {
                this.M = i13;
                int i17 = this.S;
                this.U = (size + i17) / (i13 + i17);
            } else if (i13 == 0) {
                this.U = i16;
                this.M = ((size - i12) - ((i16 - 1) * this.S)) / i16;
            } else {
                this.U = i16;
                this.M = i13;
            }
            if (mode == Integer.MIN_VALUE) {
                int i18 = this.M;
                int i19 = this.U;
                int i20 = ((i19 - 1) * this.S) + (i18 * i19) + i12;
                if (i20 < size) {
                    size = i20;
                }
            }
        }
        if (this.f3069s == 0) {
            this.f5114b.setMeasuredDimension(size2, size);
        } else {
            this.f5114b.setMeasuredDimension(size, size2);
        }
        c1();
    }

    public final int m1(int i10) {
        int i11 = 0;
        if (i10 == 0) {
            return 0;
        }
        int i12 = -i10;
        int iW = w();
        if (this.f3069s == 0) {
            while (i11 < iW) {
                v(i11).offsetTopAndBottom(i12);
                i11++;
            }
        } else {
            while (i11 < iW) {
                v(i11).offsetLeftAndRight(i12);
                i11++;
            }
        }
        this.K += i10;
        y1();
        this.f3068r.invalidate();
        return i10;
    }

    @Override // androidx.recyclerview.widget.z0
    public final boolean n0(RecyclerView recyclerView, View view, View view2) {
        if ((this.B & 32768) == 0 && Q0(view) != -1 && (this.B & 35) == 0) {
            o1(view, view2, true, 0, 0);
        }
        return true;
    }

    public final void n1(int i10, boolean z) {
        w wVar;
        View viewR = r(i10);
        w wVar2 = this.f5117e;
        boolean z5 = wVar2 != null && wVar2.f3156e;
        h hVar = this.f3068r;
        if (!z5 && !hVar.isLayoutRequested() && viewR != null && Q0(viewR) == i10) {
            this.B |= 32;
            o1(viewR, viewR.findFocus(), z, 0, 0);
            this.B &= -33;
            return;
        }
        int i11 = this.B;
        if ((i11 & 512) == 0 || (i11 & 64) != 0) {
            this.F = i10;
            this.I = Integer.MIN_VALUE;
            return;
        }
        if (z && !hVar.isLayoutRequested()) {
            this.F = i10;
            this.I = Integer.MIN_VALUE;
            if (this.W == null) {
                Log.w("GridLayoutManager:" + hVar.getId(), "setSelectionSmooth should not be called before first layout pass");
                return;
            }
            v vVar = new v(this);
            vVar.f3152a = i10;
            s1(vVar);
            int i12 = vVar.f3152a;
            if (i12 != this.F) {
                this.F = i12;
                return;
            }
            return;
        }
        if (z5) {
            w wVar3 = this.G;
            if (wVar3 != null) {
                wVar3.f3166p = true;
            }
            hVar.setScrollState(0);
            androidx.recyclerview.widget.o1 o1Var = hVar.f4840n0;
            o1Var.f5009q.removeCallbacks(o1Var);
            o1Var.f5005m.abortAnimation();
            androidx.recyclerview.widget.z0 z0Var = hVar.x;
            if (z0Var != null && (wVar = z0Var.f5117e) != null) {
                wVar.e();
            }
        }
        if (!hVar.isLayoutRequested() && viewR != null && Q0(viewR) == i10) {
            this.B |= 32;
            o1(viewR, viewR.findFocus(), z, 0, 0);
            this.B &= -33;
        } else {
            this.F = i10;
            this.I = Integer.MIN_VALUE;
            this.B |= 256;
            y0();
        }
    }

    @Override // androidx.recyclerview.widget.z0
    public final void o0(Parcelable parcelable) {
        if (parcelable instanceof z) {
            z zVar = (z) parcelable;
            this.F = zVar.f3182i;
            this.I = 0;
            Bundle bundle = zVar.f3183l;
            r1 r1Var = this.f3062d0;
            t.r rVar = (t.r) r1Var.f3114d;
            if (rVar != null && bundle != null) {
                rVar.evictAll();
                for (String str : bundle.keySet()) {
                    ((t.r) r1Var.f3114d).put(str, bundle.getSparseParcelableArray(str));
                }
            }
            this.B |= 256;
            y0();
        }
    }

    public final void o1(View view, View view2, boolean z, int i10, int i11) {
        if ((this.B & 64) != 0) {
            return;
        }
        int iQ0 = Q0(view);
        if (view != null && view2 != null) {
            ((x) view.getLayoutParams()).getClass();
        }
        int i12 = this.F;
        h hVar = this.f3068r;
        if (iQ0 != i12) {
            this.F = iQ0;
            this.I = 0;
            if ((this.B & 3) != 1) {
                N0();
            }
            if (hVar.J()) {
                hVar.invalidate();
            }
        }
        if (view == null) {
            return;
        }
        if (!view.hasFocus() && hVar.hasFocus()) {
            view.requestFocus();
        }
        if ((this.B & 131072) == 0 && z) {
            return;
        }
        int[] iArr = f3060i0;
        if (!W0(view, view2, iArr) && i10 == 0 && i11 == 0) {
            return;
        }
        int i13 = iArr[0] + i10;
        int i14 = iArr[1] + i11;
        if ((this.B & 3) == 1) {
            l1(i13);
            m1(i14);
            return;
        }
        if (this.f3069s != 0) {
            i14 = i13;
            i13 = i14;
        }
        if (z) {
            hVar.c0(i13, i14);
        } else {
            hVar.scrollBy(i13, i14);
            O0();
        }
    }

    @Override // androidx.recyclerview.widget.z0
    public final Parcelable p0() {
        Bundle bundle;
        z zVar = new z();
        zVar.f3183l = Bundle.EMPTY;
        zVar.f3182i = this.F;
        r1 r1Var = this.f3062d0;
        t.r rVar = (t.r) r1Var.f3114d;
        if (rVar == null || rVar.size() == 0) {
            bundle = null;
        } else {
            Map<Object, Object> mapSnapshot = ((t.r) r1Var.f3114d).snapshot();
            bundle = new Bundle();
            for (Map.Entry<Object, Object> entry : mapSnapshot.entrySet()) {
                bundle.putSparseParcelableArray((String) entry.getKey(), (SparseArray) entry.getValue());
            }
        }
        int iW = w();
        for (int i10 = 0; i10 < iW; i10++) {
            View viewV = v(i10);
            int iQ0 = Q0(viewV);
            if (iQ0 != -1 && r1Var.f3112b != 0) {
                String string = Integer.toString(iQ0);
                SparseArray<Parcelable> sparseArray = new SparseArray<>();
                viewV.saveHierarchyState(sparseArray);
                if (bundle == null) {
                    bundle = new Bundle();
                }
                bundle.putSparseParcelableArray(string, sparseArray);
            }
        }
        zVar.f3183l = bundle;
        return zVar;
    }

    public final void p1(int i10) {
        if (i10 == 0 || i10 == 1) {
            this.f3069s = i10;
            this.f3070t = androidx.emoji2.text.h.a(this, i10);
            androidx.emoji2.text.e0 e0Var = this.Y;
            t1 t1Var = (t1) e0Var.f2600l;
            t1 t1Var2 = (t1) e0Var.f2601m;
            if (i10 == 0) {
                e0Var.f2602n = t1Var2;
                e0Var.f2603o = t1Var;
            } else {
                e0Var.f2602n = t1Var;
                e0Var.f2603o = t1Var2;
            }
            androidx.appcompat.app.i1 i1Var = this.Z;
            i1Var.getClass();
            if (i10 == 0) {
                i1Var.f1061n = (h0) i1Var.f1060m;
            } else {
                i1Var.f1061n = (h0) i1Var.f1059l;
            }
            this.B |= 256;
        }
    }

    public final void q1(int i10) {
        if (i10 < 0 && i10 != -2) {
            throw new IllegalArgumentException(a0.c.i(i10, "Invalid row height: "));
        }
        this.L = i10;
    }

    /* JADX WARN: Removed duplicated region for block: B:20:0x003c  */
    /* JADX WARN: Removed duplicated region for block: B:23:0x0046  */
    /* JADX WARN: Removed duplicated region for block: B:28:0x004f  */
    /* JADX WARN: Removed duplicated region for block: B:33:0x005b  */
    /* JADX WARN: Removed duplicated region for block: B:42:0x0075  */
    /* JADX WARN: Type inference fix 'apply assigned field type' failed
    java.lang.UnsupportedOperationException: ArgType.getObject(), call class: class jadx.core.dex.instructions.args.ArgType$UnknownArg
    	at jadx.core.dex.instructions.args.ArgType.getObject(ArgType.java:593)
    	at jadx.core.dex.attributes.nodes.ClassTypeVarsAttr.getTypeVarsMapFor(ClassTypeVarsAttr.java:35)
    	at jadx.core.dex.nodes.utils.TypeUtils.replaceClassGenerics(TypeUtils.java:177)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.insertExplicitUseCast(FixTypesVisitor.java:397)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.tryFieldTypeWithNewCasts(FixTypesVisitor.java:359)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.applyFieldType(FixTypesVisitor.java:309)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.visit(FixTypesVisitor.java:94)
     */
    @Override // androidx.recyclerview.widget.z0
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final boolean r0(int r6, androidx.recyclerview.widget.f1 r7, androidx.recyclerview.widget.l1 r8) {
        /*
            r5 = this;
            int r0 = r5.B
            r1 = 131072(0x20000, float:1.83671E-40)
            r0 = r0 & r1
            r1 = 1
            if (r0 == 0) goto L84
            r5.k1(r7, r8)
            int r7 = r5.B
            r0 = 262144(0x40000, float:3.67342E-40)
            r7 = r7 & r0
            r0 = 0
            if (r7 == 0) goto L15
            r7 = r1
            goto L16
        L15:
            r7 = r0
        L16:
            int r2 = r5.f3069s
            r3 = 8192(0x2000, float:1.148E-41)
            r4 = 4096(0x1000, float:5.74E-42)
            if (r2 != 0) goto L34
            q0.e r2 = q0.e.f21311h
            int r2 = r2.a()
            if (r6 != r2) goto L29
            if (r7 == 0) goto L3c
            goto L46
        L29:
            q0.e r2 = q0.e.f21313j
            int r2 = r2.a()
            if (r6 != r2) goto L47
            if (r7 == 0) goto L46
            goto L3c
        L34:
            q0.e r7 = q0.e.f21310g
            int r7 = r7.a()
            if (r6 != r7) goto L3e
        L3c:
            r6 = r3
            goto L47
        L3e:
            q0.e r7 = q0.e.f21312i
            int r7 = r7.a()
            if (r6 != r7) goto L47
        L46:
            r6 = r4
        L47:
            int r7 = r5.F
            if (r7 != 0) goto L4f
            if (r6 != r3) goto L4f
            r2 = r1
            goto L50
        L4f:
            r2 = r0
        L50:
            int r8 = r8.b()
            int r8 = r8 - r1
            if (r7 != r8) goto L5b
            if (r6 != r4) goto L5b
            r7 = r1
            goto L5c
        L5b:
            r7 = r0
        L5c:
            if (r2 != 0) goto L75
            if (r7 == 0) goto L61
            goto L75
        L61:
            if (r6 == r4) goto L6e
            if (r6 == r3) goto L66
            goto L81
        L66:
            r5.f1(r0)
            r6 = -1
            r5.h1(r6, r0)
            goto L81
        L6e:
            r5.f1(r1)
            r5.h1(r1, r0)
            goto L81
        L75:
            android.view.accessibility.AccessibilityEvent r6 = android.view.accessibility.AccessibilityEvent.obtain(r4)
            androidx.leanback.widget.h r7 = r5.f3068r
            r7.onInitializeAccessibilityEvent(r6)
            r7.requestSendAccessibilityEvent(r7, r6)
        L81:
            r5.c1()
        L84:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.leanback.widget.a0.r0(int, androidx.recyclerview.widget.f1, androidx.recyclerview.widget.l1):boolean");
    }

    public final void r1(int i10, boolean z) {
        if (this.F == i10 || i10 == -1) {
            return;
        }
        n1(i10, z);
    }

    @Override // androidx.recyclerview.widget.z0
    public final androidx.recyclerview.widget.a1 s() {
        return new x(-2, -2);
    }

    @Override // androidx.recyclerview.widget.z0
    public final void s0(androidx.recyclerview.widget.f1 f1Var) {
        for (int iW = w() - 1; iW >= 0; iW--) {
            View viewV = v(iW);
            v0(iW);
            f1Var.h(viewV);
        }
    }

    public final void s1(w wVar) {
        w wVar2 = this.G;
        if (wVar2 != null) {
            wVar2.f3166p = true;
        }
        w wVar3 = this.f5117e;
        if (wVar3 != null && wVar != wVar3 && wVar3.f3156e) {
            wVar3.e();
        }
        this.f5117e = wVar;
        RecyclerView recyclerView = this.f5114b;
        androidx.recyclerview.widget.o1 o1Var = recyclerView.f4840n0;
        o1Var.f5009q.removeCallbacks(o1Var);
        o1Var.f5005m.abortAnimation();
        if (wVar.f3159h) {
            Log.w("RecyclerView", "An instance of " + wVar.getClass().getSimpleName() + " was started more than once. Each instance of" + wVar.getClass().getSimpleName() + " is intended to only be used once. You should create a new instance for each use.");
        }
        wVar.f3153b = recyclerView;
        wVar.f3154c = this;
        int i10 = wVar.f3152a;
        if (i10 == -1) {
            throw new IllegalArgumentException("Invalid target position");
        }
        recyclerView.f4846q0.f4973a = i10;
        wVar.f3156e = true;
        wVar.f3155d = true;
        wVar.f3157f = recyclerView.x.r(i10);
        wVar.f3153b.f4840n0.b();
        wVar.f3159h = true;
        if (!wVar.f3156e) {
            this.G = null;
            this.H = null;
            return;
        }
        this.G = wVar;
        if (wVar instanceof y) {
            this.H = (y) wVar;
        } else {
            this.H = null;
        }
    }

    @Override // androidx.recyclerview.widget.z0
    public final androidx.recyclerview.widget.a1 t(Context context, AttributeSet attributeSet) {
        return new x(context, attributeSet);
    }

    public final void t1() {
        int iW = w();
        for (int i10 = 0; i10 < iW; i10++) {
            u1(v(i10));
        }
    }

    @Override // androidx.recyclerview.widget.z0
    public final androidx.recyclerview.widget.a1 u(ViewGroup.LayoutParams layoutParams) {
        return layoutParams instanceof x ? new x((x) layoutParams) : layoutParams instanceof androidx.recyclerview.widget.a1 ? new x((androidx.recyclerview.widget.a1) layoutParams) : layoutParams instanceof ViewGroup.MarginLayoutParams ? new x((ViewGroup.MarginLayoutParams) layoutParams) : new x(layoutParams);
    }

    public final void u1(View view) {
        x xVar = (x) view.getLayoutParams();
        xVar.getClass();
        androidx.appcompat.app.i1 i1Var = this.Z;
        h0 h0Var = (h0) i1Var.f1060m;
        xVar.f3174i = i0.a(view, h0Var, h0Var.f3091e);
        h0 h0Var2 = (h0) i1Var.f1059l;
        xVar.f3175j = i0.a(view, h0Var2, h0Var2.f3091e);
    }

    public final void v1() {
        if (w() <= 0) {
            this.f3073w = 0;
        } else {
            this.f3073w = this.W.f3130f - ((x) v(0).getLayoutParams()).f4875a.getLayoutPosition();
        }
    }

    @Override // androidx.recyclerview.widget.z0
    public final boolean w0(RecyclerView recyclerView, View view, Rect rect, boolean z) {
        return false;
    }

    public final void w1() {
        int i10 = (this.B & (-1025)) | (g1(false) ? 1024 : 0);
        this.B = i10;
        if ((i10 & 1024) != 0) {
            WeakHashMap weakHashMap = b2.f2200a;
            this.f3068r.postOnAnimation(this.f3064f0);
        }
    }

    public final void x1() {
        int iB;
        int i10;
        int i11;
        int i12;
        int i13;
        int i14;
        int top;
        int i15;
        int top2;
        int i16;
        if (this.f3072v.b() == 0) {
            return;
        }
        if ((this.B & MediaUtils.TRANSACTION_SIZE_LIMIT_IN_BYTES) == 0) {
            i10 = this.W.f3131g;
            int iB2 = this.f3072v.b() - 1;
            i11 = this.W.f3130f;
            i12 = iB2;
            iB = 0;
        } else {
            t tVar = this.W;
            int i17 = tVar.f3130f;
            int i18 = tVar.f3131g;
            iB = this.f3072v.b() - 1;
            i10 = i17;
            i11 = i18;
            i12 = 0;
        }
        if (i10 < 0 || i11 < 0) {
            return;
        }
        boolean z = i10 == i12;
        boolean z5 = i11 == iB;
        int i19 = Integer.MIN_VALUE;
        int iG = Integer.MAX_VALUE;
        androidx.emoji2.text.e0 e0Var = this.Y;
        if (!z) {
            t1 t1Var = (t1) e0Var.f2602n;
            if (t1Var.f3134a == Integer.MAX_VALUE && !z5 && t1Var.f3135b == Integer.MIN_VALUE) {
                return;
            }
        }
        int[] iArr = f3060i0;
        if (z) {
            iG = this.W.g(true, iArr);
            View viewR = r(iArr[1]);
            if (this.f3069s == 0) {
                x xVar = (x) viewR.getLayoutParams();
                xVar.getClass();
                top2 = viewR.getLeft() + xVar.f3170e;
                i16 = xVar.f3174i;
            } else {
                x xVar2 = (x) viewR.getLayoutParams();
                xVar2.getClass();
                top2 = viewR.getTop() + xVar2.f3171f;
                i16 = xVar2.f3175j;
            }
            i13 = top2 + i16;
            ((x) viewR.getLayoutParams()).getClass();
        } else {
            i13 = Integer.MAX_VALUE;
        }
        if (z5) {
            i19 = this.W.i(false, iArr);
            View viewR2 = r(iArr[1]);
            if (this.f3069s == 0) {
                x xVar3 = (x) viewR2.getLayoutParams();
                xVar3.getClass();
                top = viewR2.getLeft() + xVar3.f3170e;
                i15 = xVar3.f3174i;
            } else {
                x xVar4 = (x) viewR2.getLayoutParams();
                xVar4.getClass();
                top = viewR2.getTop() + xVar4.f3171f;
                i15 = xVar4.f3175j;
            }
            i14 = top + i15;
        } else {
            i14 = Integer.MIN_VALUE;
        }
        ((t1) e0Var.f2602n).c(i19, iG, i14, i13);
    }

    @Override // androidx.recyclerview.widget.z0
    public final int y(androidx.recyclerview.widget.f1 f1Var, androidx.recyclerview.widget.l1 l1Var) {
        t tVar;
        if (this.f3069s != 1 || (tVar = this.W) == null) {
            return -1;
        }
        return tVar.f3129e;
    }

    public final void y1() {
        t1 t1Var = (t1) this.Y.f2603o;
        int i10 = t1Var.f3143j - this.K;
        int iX0 = X0() + i10;
        t1Var.c(i10, iX0, i10, iX0);
    }

    @Override // androidx.recyclerview.widget.z0
    public final int z(View view) {
        return super.z(view) - ((x) view.getLayoutParams()).f3173h;
    }
}
