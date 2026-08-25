package androidx.recyclerview.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Matrix;
import android.graphics.Rect;
import android.graphics.RectF;
import android.os.Parcelable;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.view.accessibility.AccessibilityEvent;
import android.view.accessibility.AccessibilityNodeInfo;
import java.util.ArrayList;
import java.util.WeakHashMap;

/* JADX INFO: loaded from: classes3.dex */
public abstract class z0 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public androidx.appcompat.app.i1 f5113a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public RecyclerView f5114b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final c2 f5115c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final c2 f5116d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public androidx.leanback.widget.w f5117e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public boolean f5118f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public boolean f5119g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public final boolean f5120h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public boolean f5121i;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public int f5122j;
    public boolean k;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public int f5123l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public int f5124m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public int f5125n;

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    public int f5126o;

    public z0() {
        w0 w0Var = new w0(this, 0);
        w0 w0Var2 = new w0(this, 1);
        this.f5115c = new c2(w0Var);
        this.f5116d = new c2(w0Var2);
        this.f5118f = false;
        this.f5119g = false;
        this.f5120h = true;
        this.f5121i = true;
    }

    public static int C(View view) {
        Rect rect = ((a1) view.getLayoutParams()).f4876b;
        return view.getMeasuredHeight() + rect.top + rect.bottom;
    }

    public static int D(View view) {
        Rect rect = ((a1) view.getLayoutParams()).f4876b;
        return view.getMeasuredWidth() + rect.left + rect.right;
    }

    public static int L(View view) {
        return ((a1) view.getLayoutParams()).f4875a.getLayoutPosition();
    }

    public static y0 M(Context context, AttributeSet attributeSet, int i10, int i11) {
        y0 y0Var = new y0();
        TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(attributeSet, w1.a.f22338a, i10, i11);
        y0Var.f5104a = typedArrayObtainStyledAttributes.getInt(0, 1);
        y0Var.f5105b = typedArrayObtainStyledAttributes.getInt(10, 1);
        y0Var.f5106c = typedArrayObtainStyledAttributes.getBoolean(9, false);
        y0Var.f5107d = typedArrayObtainStyledAttributes.getBoolean(11, false);
        typedArrayObtainStyledAttributes.recycle();
        return y0Var;
    }

    public static boolean R(int i10, int i11, int i12) {
        int mode = View.MeasureSpec.getMode(i11);
        int size = View.MeasureSpec.getSize(i11);
        if (i12 > 0 && i10 != i12) {
            return false;
        }
        if (mode == Integer.MIN_VALUE) {
            return size >= i10;
        }
        if (mode != 0) {
            return mode == 1073741824 && size == i10;
        }
        return true;
    }

    public static void S(View view, int i10, int i11, int i12, int i13) {
        a1 a1Var = (a1) view.getLayoutParams();
        Rect rect = a1Var.f4876b;
        view.layout(i10 + rect.left + ((ViewGroup.MarginLayoutParams) a1Var).leftMargin, i11 + rect.top + ((ViewGroup.MarginLayoutParams) a1Var).topMargin, (i12 - rect.right) - ((ViewGroup.MarginLayoutParams) a1Var).rightMargin, (i13 - rect.bottom) - ((ViewGroup.MarginLayoutParams) a1Var).bottomMargin);
    }

    public static int g(int i10, int i11, int i12) {
        int mode = View.MeasureSpec.getMode(i10);
        int size = View.MeasureSpec.getSize(i10);
        return mode != Integer.MIN_VALUE ? mode != 1073741824 ? Math.max(i11, i12) : size : Math.min(size, Math.max(i11, i12));
    }

    /* JADX WARN: Removed duplicated region for block: B:10:0x001a  */
    /* JADX WARN: Removed duplicated region for block: B:14:0x0022  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static int x(int r4, int r5, int r6, int r7, boolean r8) {
        /*
            int r4 = r4 - r6
            r6 = 0
            int r4 = java.lang.Math.max(r6, r4)
            r0 = -2
            r1 = -1
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = 1073741824(0x40000000, float:2.0)
            if (r8 == 0) goto L1d
            if (r7 < 0) goto L12
        L10:
            r5 = r3
            goto L30
        L12:
            if (r7 != r1) goto L1a
            if (r5 == r2) goto L22
            if (r5 == 0) goto L1a
            if (r5 == r3) goto L22
        L1a:
            r5 = r6
            r7 = r5
            goto L30
        L1d:
            if (r7 < 0) goto L20
            goto L10
        L20:
            if (r7 != r1) goto L24
        L22:
            r7 = r4
            goto L30
        L24:
            if (r7 != r0) goto L1a
            if (r5 == r2) goto L2e
            if (r5 != r3) goto L2b
            goto L2e
        L2b:
            r7 = r4
            r5 = r6
            goto L30
        L2e:
            r7 = r4
            r5 = r2
        L30:
            int r4 = android.view.View.MeasureSpec.makeMeasureSpec(r7, r5)
            return r4
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.recyclerview.widget.z0.x(int, int, int, int, boolean):int");
    }

    public void A(View view, Rect rect) {
        int[] iArr = RecyclerView.K0;
        a1 a1Var = (a1) view.getLayoutParams();
        Rect rect2 = a1Var.f4876b;
        rect.set((view.getLeft() - rect2.left) - ((ViewGroup.MarginLayoutParams) a1Var).leftMargin, (view.getTop() - rect2.top) - ((ViewGroup.MarginLayoutParams) a1Var).topMargin, view.getRight() + rect2.right + ((ViewGroup.MarginLayoutParams) a1Var).rightMargin, view.getBottom() + rect2.bottom + ((ViewGroup.MarginLayoutParams) a1Var).bottomMargin);
    }

    public abstract int A0(int i10, f1 f1Var, l1 l1Var);

    public int B(View view) {
        return view.getLeft() - ((a1) view.getLayoutParams()).f4876b.left;
    }

    public abstract void B0(int i10);

    public abstract int C0(int i10, f1 f1Var, l1 l1Var);

    public final void D0(RecyclerView recyclerView) {
        E0(View.MeasureSpec.makeMeasureSpec(recyclerView.getWidth(), 1073741824), View.MeasureSpec.makeMeasureSpec(recyclerView.getHeight(), 1073741824));
    }

    public int E(View view) {
        return view.getRight() + ((a1) view.getLayoutParams()).f4876b.right;
    }

    public final void E0(int i10, int i11) {
        this.f5125n = View.MeasureSpec.getSize(i10);
        int mode = View.MeasureSpec.getMode(i10);
        this.f5123l = mode;
        if (mode == 0 && !RecyclerView.M0) {
            this.f5125n = 0;
        }
        this.f5126o = View.MeasureSpec.getSize(i11);
        int mode2 = View.MeasureSpec.getMode(i11);
        this.f5124m = mode2;
        if (mode2 != 0 || RecyclerView.M0) {
            return;
        }
        this.f5126o = 0;
    }

    public int F(View view) {
        return view.getTop() - ((a1) view.getLayoutParams()).f4876b.top;
    }

    public void F0(Rect rect, int i10, int i11) {
        int iJ = J() + I() + rect.width();
        int iH = H() + K() + rect.height();
        RecyclerView recyclerView = this.f5114b;
        WeakHashMap weakHashMap = androidx.core.view.b2.f2200a;
        this.f5114b.setMeasuredDimension(g(i10, iJ, recyclerView.getMinimumWidth()), g(i11, iH, this.f5114b.getMinimumHeight()));
    }

    public final int G() {
        RecyclerView recyclerView = this.f5114b;
        WeakHashMap weakHashMap = androidx.core.view.b2.f2200a;
        return recyclerView.getLayoutDirection();
    }

    public final void G0(int i10, int i11) {
        int iW = w();
        if (iW == 0) {
            this.f5114b.l(i10, i11);
            return;
        }
        int i12 = Integer.MIN_VALUE;
        int i13 = Integer.MAX_VALUE;
        int i14 = Integer.MIN_VALUE;
        int i15 = Integer.MAX_VALUE;
        for (int i16 = 0; i16 < iW; i16++) {
            View viewV = v(i16);
            Rect rect = this.f5114b.f4851t;
            A(viewV, rect);
            int i17 = rect.left;
            if (i17 < i15) {
                i15 = i17;
            }
            int i18 = rect.right;
            if (i18 > i12) {
                i12 = i18;
            }
            int i19 = rect.top;
            if (i19 < i13) {
                i13 = i19;
            }
            int i20 = rect.bottom;
            if (i20 > i14) {
                i14 = i20;
            }
        }
        this.f5114b.f4851t.set(i15, i13, i12, i14);
        F0(this.f5114b.f4851t, i10, i11);
    }

    public final int H() {
        RecyclerView recyclerView = this.f5114b;
        if (recyclerView != null) {
            return recyclerView.getPaddingBottom();
        }
        return 0;
    }

    public final void H0(RecyclerView recyclerView) {
        if (recyclerView == null) {
            this.f5114b = null;
            this.f5113a = null;
            this.f5125n = 0;
            this.f5126o = 0;
        } else {
            this.f5114b = recyclerView;
            this.f5113a = recyclerView.f4843p;
            this.f5125n = recyclerView.getWidth();
            this.f5126o = recyclerView.getHeight();
        }
        this.f5123l = 1073741824;
        this.f5124m = 1073741824;
    }

    public final int I() {
        RecyclerView recyclerView = this.f5114b;
        if (recyclerView != null) {
            return recyclerView.getPaddingLeft();
        }
        return 0;
    }

    public final boolean I0(View view, int i10, int i11, a1 a1Var) {
        return (!view.isLayoutRequested() && this.f5120h && R(view.getWidth(), i10, ((ViewGroup.MarginLayoutParams) a1Var).width) && R(view.getHeight(), i11, ((ViewGroup.MarginLayoutParams) a1Var).height)) ? false : true;
    }

    public final int J() {
        RecyclerView recyclerView = this.f5114b;
        if (recyclerView != null) {
            return recyclerView.getPaddingRight();
        }
        return 0;
    }

    public boolean J0() {
        return false;
    }

    public final int K() {
        RecyclerView recyclerView = this.f5114b;
        if (recyclerView != null) {
            return recyclerView.getPaddingTop();
        }
        return 0;
    }

    public final boolean K0(View view, int i10, int i11, a1 a1Var) {
        return (this.f5120h && R(view.getMeasuredWidth(), i10, ((ViewGroup.MarginLayoutParams) a1Var).width) && R(view.getMeasuredHeight(), i11, ((ViewGroup.MarginLayoutParams) a1Var).height)) ? false : true;
    }

    public abstract boolean L0();

    public int N(f1 f1Var, l1 l1Var) {
        return -1;
    }

    public final void O(View view, Rect rect) {
        Matrix matrix;
        Rect rect2 = ((a1) view.getLayoutParams()).f4876b;
        rect.set(-rect2.left, -rect2.top, view.getWidth() + rect2.right, view.getHeight() + rect2.bottom);
        if (this.f5114b != null && (matrix = view.getMatrix()) != null && !matrix.isIdentity()) {
            RectF rectF = this.f5114b.f4855v;
            rectF.set(rect);
            matrix.mapRect(rectF);
            rect.set((int) Math.floor(rectF.left), (int) Math.floor(rectF.top), (int) Math.ceil(rectF.right), (int) Math.ceil(rectF.bottom));
        }
        rect.offset(view.getLeft(), view.getTop());
    }

    public final boolean P() {
        RecyclerView recyclerView = this.f5114b;
        return recyclerView != null && recyclerView.hasFocus();
    }

    public boolean Q() {
        return false;
    }

    public void T(int i10) {
        RecyclerView recyclerView = this.f5114b;
        if (recyclerView != null) {
            int iR = recyclerView.f4843p.R();
            for (int i11 = 0; i11 < iR; i11++) {
                recyclerView.f4843p.Q(i11).offsetLeftAndRight(i10);
            }
        }
    }

    public void U(int i10) {
        RecyclerView recyclerView = this.f5114b;
        if (recyclerView != null) {
            int iR = recyclerView.f4843p.R();
            for (int i11 = 0; i11 < iR; i11++) {
                recyclerView.f4843p.Q(i11).offsetTopAndBottom(i10);
            }
        }
    }

    public void V(l0 l0Var, l0 l0Var2) {
    }

    public boolean W(RecyclerView recyclerView, ArrayList arrayList, int i10, int i11) {
        return false;
    }

    public View Y(View view, int i10, f1 f1Var, l1 l1Var) {
        return null;
    }

    public void Z(AccessibilityEvent accessibilityEvent) {
        RecyclerView recyclerView = this.f5114b;
        f1 f1Var = recyclerView.f4837m;
        l1 l1Var = recyclerView.f4846q0;
        if (recyclerView == null || accessibilityEvent == null) {
            return;
        }
        boolean z = true;
        if (!recyclerView.canScrollVertically(1) && !this.f5114b.canScrollVertically(-1) && !this.f5114b.canScrollHorizontally(-1) && !this.f5114b.canScrollHorizontally(1)) {
            z = false;
        }
        accessibilityEvent.setScrollable(z);
        l0 l0Var = this.f5114b.f4856w;
        if (l0Var != null) {
            accessibilityEvent.setItemCount(l0Var.getItemCount());
        }
    }

    public final void a(View view, int i10, boolean z) {
        p1 p1VarG;
        p1 p1VarG2 = RecyclerView.G(view);
        if (z || p1VarG2.isRemoved()) {
            t.l0 l0Var = (t.l0) this.f5114b.f4845q.f4903a;
            d2 d2VarA = (d2) l0Var.get(p1VarG2);
            if (d2VarA == null) {
                d2VarA = d2.a();
                l0Var.put(p1VarG2, d2VarA);
            }
            d2VarA.f4910a |= 1;
        } else {
            this.f5114b.f4845q.k(p1VarG2);
        }
        a1 a1Var = (a1) view.getLayoutParams();
        if (p1VarG2.wasReturnedFromScrap() || p1VarG2.isScrap()) {
            if (p1VarG2.isScrap()) {
                p1VarG2.unScrap();
            } else {
                p1VarG2.clearReturnedFromScrapFlag();
            }
            this.f5113a.c(view, i10, view.getLayoutParams(), false);
        } else {
            if (view.getParent() == this.f5114b) {
                int iY = this.f5113a.Y(view);
                if (i10 == -1) {
                    i10 = this.f5113a.R();
                }
                if (iY == -1) {
                    StringBuilder sb2 = new StringBuilder("Added View has RecyclerView as parent but view is not a real child. Unfiltered index:");
                    sb2.append(this.f5114b.indexOfChild(view));
                    throw new IllegalStateException(androidx.fragment.app.a2.k(this.f5114b, sb2));
                }
                if (iY != i10) {
                    z0 z0Var = this.f5114b.x;
                    View viewV = z0Var.v(iY);
                    if (viewV == null) {
                        throw new IllegalArgumentException("Cannot move a child from non-existing index:" + iY + z0Var.f5114b.toString());
                    }
                    z0Var.v(iY);
                    androidx.appcompat.app.i1 i1Var = z0Var.f5113a;
                    int iW = i1Var.W(iY);
                    ((e) i1Var.f1060m).i(iW);
                    RecyclerView recyclerView = ((j0) i1Var.f1059l).f4952a;
                    View childAt = recyclerView.getChildAt(iW);
                    if (childAt != null && (p1VarG = RecyclerView.G(childAt)) != null) {
                        if (p1VarG.isTmpDetached() && !p1VarG.shouldIgnore()) {
                            StringBuilder sb3 = new StringBuilder("called detach on an already detached child ");
                            sb3.append(p1VarG);
                            throw new IllegalArgumentException(androidx.fragment.app.a2.k(recyclerView, sb3));
                        }
                        p1VarG.addFlags(256);
                    }
                    recyclerView.detachViewFromParent(iW);
                    a1 a1Var2 = (a1) viewV.getLayoutParams();
                    p1 p1VarG3 = RecyclerView.G(viewV);
                    if (p1VarG3.isRemoved()) {
                        t.l0 l0Var2 = (t.l0) z0Var.f5114b.f4845q.f4903a;
                        d2 d2VarA2 = (d2) l0Var2.get(p1VarG3);
                        if (d2VarA2 == null) {
                            d2VarA2 = d2.a();
                            l0Var2.put(p1VarG3, d2VarA2);
                        }
                        d2VarA2.f4910a = 1 | d2VarA2.f4910a;
                    } else {
                        z0Var.f5114b.f4845q.k(p1VarG3);
                    }
                    z0Var.f5113a.c(viewV, i10, a1Var2, p1VarG3.isRemoved());
                }
            } else {
                androidx.appcompat.app.i1 i1Var2 = this.f5113a;
                RecyclerView recyclerView2 = ((j0) i1Var2.f1059l).f4952a;
                int childCount = i10 < 0 ? recyclerView2.getChildCount() : i1Var2.W(i10);
                ((e) i1Var2.f1060m).h(childCount, false);
                recyclerView2.addView(view, childCount);
                p1 p1VarG4 = RecyclerView.G(view);
                l0 l0Var3 = recyclerView2.f4856w;
                if (l0Var3 != null && p1VarG4 != null) {
                    l0Var3.onViewAttachedToWindow(p1VarG4);
                }
                a1Var.f4877c = true;
                androidx.leanback.widget.w wVar = this.f5117e;
                if (wVar != null && wVar.f3156e) {
                    wVar.f3153b.getClass();
                    p1 p1VarG5 = RecyclerView.G(view);
                    if ((p1VarG5 != null ? p1VarG5.getLayoutPosition() : -1) == wVar.f3152a) {
                        wVar.f3157f = view;
                    }
                }
            }
        }
        if (a1Var.f4878d) {
            p1VarG2.itemView.invalidate();
            a1Var.f4878d = false;
        }
    }

    public void a0(f1 f1Var, l1 l1Var, q0.k kVar) {
        if (this.f5114b.canScrollVertically(-1) || this.f5114b.canScrollHorizontally(-1)) {
            kVar.a(8192);
            kVar.m();
        }
        if (this.f5114b.canScrollVertically(1) || this.f5114b.canScrollHorizontally(1)) {
            kVar.a(4096);
            kVar.m();
        }
        kVar.f21319a.setCollectionInfo(AccessibilityNodeInfo.CollectionInfo.obtain(N(f1Var, l1Var), y(f1Var, l1Var), false, 0));
    }

    public void b(String str) {
        RecyclerView recyclerView = this.f5114b;
        if (recyclerView != null) {
            recyclerView.f(str);
        }
    }

    public final void b0(View view, q0.k kVar) {
        p1 p1VarG = RecyclerView.G(view);
        if (p1VarG == null || p1VarG.isRemoved()) {
            return;
        }
        androidx.appcompat.app.i1 i1Var = this.f5113a;
        if (((ArrayList) i1Var.f1061n).contains(p1VarG.itemView)) {
            return;
        }
        RecyclerView recyclerView = this.f5114b;
        c0(recyclerView.f4837m, recyclerView.f4846q0, view, kVar);
    }

    public final void c(View view, Rect rect) {
        RecyclerView recyclerView = this.f5114b;
        if (recyclerView == null) {
            rect.set(0, 0, 0, 0);
        } else {
            rect.set(recyclerView.H(view));
        }
    }

    public abstract boolean d();

    public View d0(int i10, View view) {
        return null;
    }

    public abstract boolean e();

    public boolean f(a1 a1Var) {
        return a1Var != null;
    }

    public abstract void h(int i10, int i11, l1 l1Var, s sVar);

    public int j(l1 l1Var) {
        return 0;
    }

    public void j0(RecyclerView recyclerView, int i10, int i11) {
        i0(i10, i11);
    }

    public int k(l1 l1Var) {
        return 0;
    }

    public abstract void k0(f1 f1Var, l1 l1Var);

    public int l(l1 l1Var) {
        return 0;
    }

    public abstract void l0(l1 l1Var);

    public int m(l1 l1Var) {
        return 0;
    }

    public void m0(f1 f1Var, l1 l1Var, int i10, int i11) {
        this.f5114b.l(i10, i11);
    }

    public int n(l1 l1Var) {
        return 0;
    }

    public boolean n0(RecyclerView recyclerView, View view, View view2) {
        androidx.leanback.widget.w wVar = this.f5117e;
        return (wVar != null && wVar.f3156e) || recyclerView.K();
    }

    public int o(l1 l1Var) {
        return 0;
    }

    public abstract void o0(Parcelable parcelable);

    public final void p(f1 f1Var) {
        for (int iW = w() - 1; iW >= 0; iW--) {
            z0(f1Var, iW, v(iW));
        }
    }

    public abstract Parcelable p0();

    public final View q(View view) {
        View viewY;
        RecyclerView recyclerView = this.f5114b;
        if (recyclerView == null || (viewY = recyclerView.y(view)) == null || ((ArrayList) this.f5113a.f1061n).contains(viewY)) {
            return null;
        }
        return viewY;
    }

    public void q0(int i10) {
    }

    public View r(int i10) {
        int iW = w();
        for (int i11 = 0; i11 < iW; i11++) {
            View viewV = v(i11);
            p1 p1VarG = RecyclerView.G(viewV);
            if (p1VarG != null && p1VarG.getLayoutPosition() == i10 && !p1VarG.shouldIgnore() && (this.f5114b.f4846q0.f4979g || !p1VarG.isRemoved())) {
                return viewV;
            }
        }
        return null;
    }

    public boolean r0(int i10, f1 f1Var, l1 l1Var) {
        int iK;
        int I;
        int i11;
        int i12;
        if (this.f5114b != null) {
            int iHeight = this.f5126o;
            int iWidth = this.f5125n;
            Rect rect = new Rect();
            if (this.f5114b.getMatrix().isIdentity() && this.f5114b.getGlobalVisibleRect(rect)) {
                iHeight = rect.height();
                iWidth = rect.width();
            }
            if (i10 == 4096) {
                iK = this.f5114b.canScrollVertically(1) ? (iHeight - K()) - H() : 0;
                if (this.f5114b.canScrollHorizontally(1)) {
                    I = (iWidth - I()) - J();
                    i11 = iK;
                    i12 = I;
                }
                i11 = iK;
                i12 = 0;
            } else if (i10 != 8192) {
                i12 = 0;
                i11 = 0;
            } else {
                iK = this.f5114b.canScrollVertically(-1) ? -((iHeight - K()) - H()) : 0;
                if (this.f5114b.canScrollHorizontally(-1)) {
                    I = -((iWidth - I()) - J());
                    i11 = iK;
                    i12 = I;
                }
                i11 = iK;
                i12 = 0;
            }
            if (i11 != 0 || i12 != 0) {
                this.f5114b.d0(i12, i11, null, Integer.MIN_VALUE, true);
                return true;
            }
        }
        return false;
    }

    public abstract a1 s();

    public void s0(f1 f1Var) {
        for (int iW = w() - 1; iW >= 0; iW--) {
            if (!RecyclerView.G(v(iW)).shouldIgnore()) {
                View viewV = v(iW);
                v0(iW);
                f1Var.h(viewV);
            }
        }
    }

    public a1 t(Context context, AttributeSet attributeSet) {
        return new a1(context, attributeSet);
    }

    public final void t0(f1 f1Var) {
        ArrayList arrayList = f1Var.f4919a;
        int size = arrayList.size();
        for (int i10 = size - 1; i10 >= 0; i10--) {
            View view = ((p1) arrayList.get(i10)).itemView;
            p1 p1VarG = RecyclerView.G(view);
            if (!p1VarG.shouldIgnore()) {
                p1VarG.setIsRecyclable(false);
                if (p1VarG.isTmpDetached()) {
                    this.f5114b.removeDetachedView(view, false);
                }
                u0 u0Var = this.f5114b.W;
                if (u0Var != null) {
                    u0Var.d(p1VarG);
                }
                p1VarG.setIsRecyclable(true);
                p1 p1VarG2 = RecyclerView.G(view);
                p1VarG2.mScrapContainer = null;
                p1VarG2.mInChangeScrap = false;
                p1VarG2.clearReturnedFromScrapFlag();
                f1Var.i(p1VarG2);
            }
        }
        arrayList.clear();
        ArrayList arrayList2 = f1Var.f4920b;
        if (arrayList2 != null) {
            arrayList2.clear();
        }
        if (size > 0) {
            this.f5114b.invalidate();
        }
    }

    public a1 u(ViewGroup.LayoutParams layoutParams) {
        return layoutParams instanceof a1 ? new a1((a1) layoutParams) : layoutParams instanceof ViewGroup.MarginLayoutParams ? new a1((ViewGroup.MarginLayoutParams) layoutParams) : new a1(layoutParams);
    }

    public final void u0(View view, f1 f1Var) {
        androidx.appcompat.app.i1 i1Var = this.f5113a;
        j0 j0Var = (j0) i1Var.f1059l;
        int iIndexOfChild = j0Var.f4952a.indexOfChild(view);
        if (iIndexOfChild >= 0) {
            if (((e) i1Var.f1060m).i(iIndexOfChild)) {
                i1Var.e0(view);
            }
            j0Var.j(iIndexOfChild);
        }
        f1Var.h(view);
    }

    public final View v(int i10) {
        androidx.appcompat.app.i1 i1Var = this.f5113a;
        if (i1Var != null) {
            return i1Var.Q(i10);
        }
        return null;
    }

    public final void v0(int i10) {
        if (v(i10) != null) {
            androidx.appcompat.app.i1 i1Var = this.f5113a;
            int iW = i1Var.W(i10);
            j0 j0Var = (j0) i1Var.f1059l;
            View childAt = j0Var.f4952a.getChildAt(iW);
            if (childAt == null) {
                return;
            }
            if (((e) i1Var.f1060m).i(iW)) {
                i1Var.e0(childAt);
            }
            j0Var.j(iW);
        }
    }

    public final int w() {
        androidx.appcompat.app.i1 i1Var = this.f5113a;
        if (i1Var != null) {
            return i1Var.R();
        }
        return 0;
    }

    public boolean w0(RecyclerView recyclerView, View view, Rect rect, boolean z) {
        return x0(recyclerView, view, rect, z, false);
    }

    /* JADX WARN: Removed duplicated region for block: B:28:0x00ae  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final boolean x0(androidx.recyclerview.widget.RecyclerView r9, android.view.View r10, android.graphics.Rect r11, boolean r12, boolean r13) {
        /*
            r8 = this;
            int r0 = r8.I()
            int r1 = r8.K()
            int r2 = r8.f5125n
            int r3 = r8.J()
            int r2 = r2 - r3
            int r3 = r8.f5126o
            int r4 = r8.H()
            int r3 = r3 - r4
            int r4 = r10.getLeft()
            int r5 = r11.left
            int r4 = r4 + r5
            int r5 = r10.getScrollX()
            int r4 = r4 - r5
            int r5 = r10.getTop()
            int r6 = r11.top
            int r5 = r5 + r6
            int r10 = r10.getScrollY()
            int r5 = r5 - r10
            int r10 = r11.width()
            int r10 = r10 + r4
            int r11 = r11.height()
            int r11 = r11 + r5
            int r4 = r4 - r0
            r0 = 0
            int r6 = java.lang.Math.min(r0, r4)
            int r5 = r5 - r1
            int r1 = java.lang.Math.min(r0, r5)
            int r10 = r10 - r2
            int r2 = java.lang.Math.max(r0, r10)
            int r11 = r11 - r3
            int r11 = java.lang.Math.max(r0, r11)
            int r3 = r8.G()
            r7 = 1
            if (r3 != r7) goto L5c
            if (r2 == 0) goto L57
            goto L64
        L57:
            int r2 = java.lang.Math.max(r6, r10)
            goto L64
        L5c:
            if (r6 == 0) goto L5f
            goto L63
        L5f:
            int r6 = java.lang.Math.min(r4, r2)
        L63:
            r2 = r6
        L64:
            if (r1 == 0) goto L67
            goto L6b
        L67:
            int r1 = java.lang.Math.min(r5, r11)
        L6b:
            int[] r10 = new int[]{r2, r1}
            r11 = r10[r0]
            r10 = r10[r7]
            if (r13 == 0) goto Lae
            android.view.View r13 = r9.getFocusedChild()
            if (r13 != 0) goto L7c
            goto Lb3
        L7c:
            int r1 = r8.I()
            int r2 = r8.K()
            int r3 = r8.f5125n
            int r4 = r8.J()
            int r3 = r3 - r4
            int r4 = r8.f5126o
            int r5 = r8.H()
            int r4 = r4 - r5
            androidx.recyclerview.widget.RecyclerView r5 = r8.f5114b
            android.graphics.Rect r5 = r5.f4851t
            r8.A(r13, r5)
            int r13 = r5.left
            int r13 = r13 - r11
            if (r13 >= r3) goto Lb3
            int r13 = r5.right
            int r13 = r13 - r11
            if (r13 <= r1) goto Lb3
            int r13 = r5.top
            int r13 = r13 - r10
            if (r13 >= r4) goto Lb3
            int r13 = r5.bottom
            int r13 = r13 - r10
            if (r13 > r2) goto Lae
            goto Lb3
        Lae:
            if (r11 != 0) goto Lb4
            if (r10 == 0) goto Lb3
            goto Lb4
        Lb3:
            return r0
        Lb4:
            if (r12 == 0) goto Lba
            r9.scrollBy(r11, r10)
            return r7
        Lba:
            r9.c0(r11, r10)
            return r7
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.recyclerview.widget.z0.x0(androidx.recyclerview.widget.RecyclerView, android.view.View, android.graphics.Rect, boolean, boolean):boolean");
    }

    public int y(f1 f1Var, l1 l1Var) {
        return -1;
    }

    public final void y0() {
        RecyclerView recyclerView = this.f5114b;
        if (recyclerView != null) {
            recyclerView.requestLayout();
        }
    }

    public int z(View view) {
        return view.getBottom() + ((a1) view.getLayoutParams()).f4876b.bottom;
    }

    public final void z0(f1 f1Var, int i10, View view) {
        p1 p1VarG;
        p1 p1VarG2 = RecyclerView.G(view);
        if (p1VarG2.shouldIgnore()) {
            return;
        }
        if (p1VarG2.isInvalid() && !p1VarG2.isRemoved() && !this.f5114b.f4856w.hasStableIds()) {
            v0(i10);
            f1Var.i(p1VarG2);
            return;
        }
        v(i10);
        androidx.appcompat.app.i1 i1Var = this.f5113a;
        int iW = i1Var.W(i10);
        ((e) i1Var.f1060m).i(iW);
        RecyclerView recyclerView = ((j0) i1Var.f1059l).f4952a;
        View childAt = recyclerView.getChildAt(iW);
        if (childAt != null && (p1VarG = RecyclerView.G(childAt)) != null) {
            if (p1VarG.isTmpDetached() && !p1VarG.shouldIgnore()) {
                StringBuilder sb2 = new StringBuilder("called detach on an already detached child ");
                sb2.append(p1VarG);
                throw new IllegalArgumentException(androidx.fragment.app.a2.k(recyclerView, sb2));
            }
            p1VarG.addFlags(256);
        }
        recyclerView.detachViewFromParent(iW);
        f1Var.j(view);
        this.f5114b.f4845q.k(p1VarG2);
    }

    public void f0() {
    }

    public void X(RecyclerView recyclerView) {
    }

    public void e0(int i10, int i11) {
    }

    public void g0(int i10, int i11) {
    }

    public void h0(int i10, int i11) {
    }

    public void i(int i10, s sVar) {
    }

    public void i0(int i10, int i11) {
    }

    public void c0(f1 f1Var, l1 l1Var, View view, q0.k kVar) {
    }
}
