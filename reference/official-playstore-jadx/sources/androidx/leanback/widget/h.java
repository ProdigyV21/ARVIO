package androidx.leanback.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import androidx.media3.session.MediaUtils;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;

/* JADX INFO: loaded from: classes3.dex */
public abstract class h extends RecyclerView {
    public final a0 S0;
    public g T0;
    public boolean U0;
    public boolean V0;
    public androidx.recyclerview.widget.u0 W0;
    public e X0;
    public d Y0;
    public b Z0;

    /* JADX INFO: renamed from: a1, reason: collision with root package name */
    public f f3084a1;

    /* JADX INFO: renamed from: b1, reason: collision with root package name */
    public int f3085b1;

    /* JADX INFO: renamed from: c1, reason: collision with root package name */
    public int f3086c1;

    public h(Context context, AttributeSet attributeSet) {
        super(context, attributeSet, 0);
        this.U0 = true;
        this.V0 = true;
        this.f3085b1 = 4;
        a0 a0Var = new a0(this);
        this.S0 = a0Var;
        setLayoutManager(a0Var);
        setPreserveFocusAfterLayout(false);
        setDescendantFocusability(MediaUtils.TRANSACTION_SIZE_LIMIT_IN_BYTES);
        setHasFixedSize(true);
        setChildrenDrawingOrderEnabled(true);
        setWillNotDraw(true);
        setOverScrollMode(2);
        ((androidx.recyclerview.widget.m) getItemAnimator()).f4989g = false;
        this.z.add(new a(this));
    }

    @Override // androidx.recyclerview.widget.RecyclerView
    public final void c0(int i10, int i11) {
        g gVar = this.T0;
        if (gVar != null) {
            d0(i10, i11, gVar.a(), this.T0.b(), false);
        } else {
            d0(i10, i11, null, Integer.MIN_VALUE, false);
        }
    }

    @Override // android.view.ViewGroup, android.view.View
    public final boolean dispatchGenericFocusedEvent(MotionEvent motionEvent) {
        d dVar = this.Y0;
        if (dVar == null || !dVar.a()) {
            return super.dispatchGenericFocusedEvent(motionEvent);
        }
        return true;
    }

    @Override // android.view.ViewGroup, android.view.View
    public final boolean dispatchKeyEvent(KeyEvent keyEvent) {
        b bVar = this.Z0;
        if ((bVar != null && bVar.a()) || super.dispatchKeyEvent(keyEvent)) {
            return true;
        }
        f fVar = this.f3084a1;
        return fVar != null && fVar.a();
    }

    @Override // android.view.ViewGroup, android.view.View
    public final boolean dispatchTouchEvent(MotionEvent motionEvent) {
        e eVar = this.X0;
        if (eVar == null || !eVar.a()) {
            return super.dispatchTouchEvent(motionEvent);
        }
        return true;
    }

    @Override // androidx.recyclerview.widget.RecyclerView
    public final void e0(int i10, int i11) {
        g gVar = this.T0;
        if (gVar != null) {
            d0(i10, i11, null, gVar.b(), false);
        } else {
            d0(i10, i11, null, Integer.MIN_VALUE, false);
        }
    }

    @Override // android.view.View
    public final View focusSearch(int i10) {
        if (isFocused()) {
            a0 a0Var = this.S0;
            View viewR = a0Var.r(a0Var.F);
            if (viewR != null) {
                return focusSearch(viewR, i10);
            }
        }
        return super.focusSearch(i10);
    }

    @Override // androidx.recyclerview.widget.RecyclerView, android.view.ViewGroup
    public final int getChildDrawingOrder(int i10, int i11) {
        int iIndexOfChild;
        a0 a0Var = this.S0;
        View viewR = a0Var.r(a0Var.F);
        return (viewR != null && i11 >= (iIndexOfChild = indexOfChild(viewR))) ? i11 < i10 + (-1) ? ((iIndexOfChild + i10) - 1) - i11 : iIndexOfChild : i11;
    }

    public int getExtraLayoutSpace() {
        return this.S0.b0;
    }

    public int getFocusScrollStrategy() {
        return this.S0.X;
    }

    @Deprecated
    public int getHorizontalMargin() {
        return this.S0.P;
    }

    public int getHorizontalSpacing() {
        return this.S0.P;
    }

    public int getInitialPrefetchItemCount() {
        return this.f3085b1;
    }

    public int getItemAlignmentOffset() {
        return ((h0) this.S0.Z.f1061n).f3088b;
    }

    public float getItemAlignmentOffsetPercent() {
        return ((h0) this.S0.Z.f1061n).f3089c;
    }

    public int getItemAlignmentViewId() {
        return ((h0) this.S0.Z.f1061n).f3087a;
    }

    public f getOnUnhandledKeyListener() {
        return this.f3084a1;
    }

    public final int getSaveChildrenLimitNumber() {
        return this.S0.f3062d0.f3113c;
    }

    public final int getSaveChildrenPolicy() {
        return this.S0.f3062d0.f3112b;
    }

    public int getSelectedPosition() {
        return this.S0.F;
    }

    public int getSelectedSubPosition() {
        this.S0.getClass();
        return 0;
    }

    public g getSmoothScrollByBehavior() {
        return this.T0;
    }

    public final int getSmoothScrollMaxPendingMoves() {
        return this.S0.f3067q;
    }

    public final float getSmoothScrollSpeedFactor() {
        return this.S0.f3066p;
    }

    @Deprecated
    public int getVerticalMargin() {
        return this.S0.Q;
    }

    public int getVerticalSpacing() {
        return this.S0.Q;
    }

    public int getWindowAlignment() {
        return ((t1) this.S0.Y.f2602n).f3139f;
    }

    public int getWindowAlignmentOffset() {
        return ((t1) this.S0.Y.f2602n).f3140g;
    }

    public float getWindowAlignmentOffsetPercent() {
        return ((t1) this.S0.Y.f2602n).f3141h;
    }

    @Override // android.view.View
    public final boolean hasOverlappingRendering() {
        return this.V0;
    }

    public final void i0(Context context, AttributeSet attributeSet) {
        TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(attributeSet, k1.a.f19453c);
        boolean z = typedArrayObtainStyledAttributes.getBoolean(4, false);
        boolean z5 = typedArrayObtainStyledAttributes.getBoolean(3, false);
        a0 a0Var = this.S0;
        a0Var.B = (z ? 2048 : 0) | (a0Var.B & (-6145)) | (z5 ? 4096 : 0);
        boolean z10 = typedArrayObtainStyledAttributes.getBoolean(6, true);
        boolean z11 = typedArrayObtainStyledAttributes.getBoolean(5, true);
        a0Var.B = (z10 ? 8192 : 0) | (a0Var.B & (-24577)) | (z11 ? 16384 : 0);
        int dimensionPixelSize = typedArrayObtainStyledAttributes.getDimensionPixelSize(2, typedArrayObtainStyledAttributes.getDimensionPixelSize(8, 0));
        if (a0Var.f3069s == 1) {
            a0Var.Q = dimensionPixelSize;
            a0Var.R = dimensionPixelSize;
        } else {
            a0Var.Q = dimensionPixelSize;
            a0Var.S = dimensionPixelSize;
        }
        int dimensionPixelSize2 = typedArrayObtainStyledAttributes.getDimensionPixelSize(1, typedArrayObtainStyledAttributes.getDimensionPixelSize(7, 0));
        if (a0Var.f3069s == 0) {
            a0Var.P = dimensionPixelSize2;
            a0Var.R = dimensionPixelSize2;
        } else {
            a0Var.P = dimensionPixelSize2;
            a0Var.S = dimensionPixelSize2;
        }
        if (typedArrayObtainStyledAttributes.hasValue(0)) {
            setGravity(typedArrayObtainStyledAttributes.getInt(0, 0));
        }
        typedArrayObtainStyledAttributes.recycle();
    }

    @Override // android.view.View
    public final void onFocusChanged(boolean z, int i10, Rect rect) {
        super.onFocusChanged(z, i10, rect);
        a0 a0Var = this.S0;
        if (!z) {
            a0Var.getClass();
            return;
        }
        int i11 = a0Var.F;
        while (true) {
            View viewR = a0Var.r(i11);
            if (viewR == null) {
                return;
            }
            if (viewR.getVisibility() == 0 && viewR.hasFocusable()) {
                viewR.requestFocus();
                return;
            }
            i11++;
        }
    }

    @Override // androidx.recyclerview.widget.RecyclerView, android.view.ViewGroup
    public final boolean onRequestFocusInDescendants(int i10, Rect rect) {
        int i11;
        int i12;
        int i13;
        if ((this.f3086c1 & 1) != 1) {
            a0 a0Var = this.S0;
            int i14 = a0Var.X;
            if (i14 == 1 || i14 == 2) {
                int iW = a0Var.w();
                if ((i10 & 2) != 0) {
                    i13 = 1;
                    i12 = iW;
                    i11 = 0;
                } else {
                    i11 = iW - 1;
                    i12 = -1;
                    i13 = -1;
                }
                t1 t1Var = (t1) a0Var.Y.f2602n;
                int i15 = t1Var.f3143j;
                int i16 = ((t1Var.f3142i - i15) - t1Var.k) + i15;
                while (i11 != i12) {
                    View viewV = a0Var.v(i11);
                    if (viewV.getVisibility() == 0 && a0Var.f3070t.e(viewV) >= i15 && a0Var.f3070t.b(viewV) <= i16 && viewV.requestFocus(i10, rect)) {
                        return true;
                    }
                    i11 += i13;
                }
            } else {
                View viewR = a0Var.r(a0Var.F);
                if (viewR != null) {
                    return viewR.requestFocus(i10, rect);
                }
            }
        }
        return false;
    }

    /* JADX WARN: Removed duplicated region for block: B:6:0x000d  */
    @Override // android.view.View
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final void onRtlPropertiesChanged(int r7) {
        /*
            r6 = this;
            androidx.leanback.widget.a0 r0 = r6.S0
            int r1 = r0.f3069s
            r2 = 0
            r3 = 1
            if (r1 != 0) goto Lf
            if (r7 != r3) goto Ld
            r1 = 262144(0x40000, float:3.67342E-40)
            goto L13
        Ld:
            r1 = r2
            goto L13
        Lf:
            if (r7 != r3) goto Ld
            r1 = 524288(0x80000, float:7.34684E-40)
        L13:
            int r4 = r0.B
            r5 = 786432(0xc0000, float:1.102026E-39)
            r5 = r5 & r4
            if (r5 != r1) goto L1b
            return
        L1b:
            r5 = -786433(0xfffffffffff3ffff, float:NaN)
            r4 = r4 & r5
            r1 = r1 | r4
            r1 = r1 | 256(0x100, float:3.59E-43)
            r0.B = r1
            androidx.emoji2.text.e0 r0 = r0.Y
            java.lang.Object r0 = r0.f2601m
            androidx.leanback.widget.t1 r0 = (androidx.leanback.widget.t1) r0
            if (r7 != r3) goto L2d
            r2 = r3
        L2d:
            r0.f3144l = r2
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.leanback.widget.h.onRtlPropertiesChanged(int):void");
    }

    @Override // android.view.ViewGroup, android.view.ViewManager
    public final void removeView(View view) {
        boolean z = view.hasFocus() && isFocusable();
        if (z) {
            this.f3086c1 = 1 | this.f3086c1;
            requestFocus();
        }
        super.removeView(view);
        if (z) {
            this.f3086c1 ^= -2;
        }
    }

    @Override // android.view.ViewGroup
    public final void removeViewAt(int i10) {
        boolean zHasFocus = getChildAt(i10).hasFocus();
        if (zHasFocus) {
            this.f3086c1 |= 1;
            requestFocus();
        }
        super.removeViewAt(i10);
        if (zHasFocus) {
            this.f3086c1 ^= -2;
        }
    }

    public void setAnimateChildLayout(boolean z) {
        if (this.U0 != z) {
            this.U0 = z;
            if (z) {
                super.setItemAnimator(this.W0);
            } else {
                this.W0 = getItemAnimator();
                super.setItemAnimator(null);
            }
        }
    }

    public void setChildrenVisibility(int i10) {
        a0 a0Var = this.S0;
        a0Var.J = i10;
        if (i10 != -1) {
            int iW = a0Var.w();
            for (int i11 = 0; i11 < iW; i11++) {
                a0Var.v(i11).setVisibility(a0Var.J);
            }
        }
    }

    public void setExtraLayoutSpace(int i10) {
        a0 a0Var = this.S0;
        int i11 = a0Var.b0;
        if (i11 == i10) {
            return;
        }
        if (i11 < 0) {
            throw new IllegalArgumentException("ExtraLayoutSpace must >= 0");
        }
        a0Var.b0 = i10;
        a0Var.y0();
    }

    public void setFocusDrawingOrderEnabled(boolean z) {
        super.setChildrenDrawingOrderEnabled(z);
    }

    public void setFocusScrollStrategy(int i10) {
        if (i10 != 0 && i10 != 1 && i10 != 2) {
            throw new IllegalArgumentException("Invalid scrollStrategy");
        }
        this.S0.X = i10;
        requestLayout();
    }

    public final void setFocusSearchDisabled(boolean z) {
        setDescendantFocusability(z ? 393216 : MediaUtils.TRANSACTION_SIZE_LIMIT_IN_BYTES);
        a0 a0Var = this.S0;
        a0Var.B = (z ? 32768 : 0) | (a0Var.B & (-32769));
    }

    public void setGravity(int i10) {
        this.S0.T = i10;
        requestLayout();
    }

    public void setHasOverlappingRendering(boolean z) {
        this.V0 = z;
    }

    @Deprecated
    public void setHorizontalMargin(int i10) {
        setHorizontalSpacing(i10);
    }

    public void setHorizontalSpacing(int i10) {
        a0 a0Var = this.S0;
        if (a0Var.f3069s == 0) {
            a0Var.P = i10;
            a0Var.R = i10;
        } else {
            a0Var.P = i10;
            a0Var.S = i10;
        }
        requestLayout();
    }

    public void setInitialPrefetchItemCount(int i10) {
        this.f3085b1 = i10;
    }

    public void setItemAlignmentOffset(int i10) {
        a0 a0Var = this.S0;
        ((h0) a0Var.Z.f1061n).f3088b = i10;
        a0Var.t1();
        requestLayout();
    }

    public void setItemAlignmentOffsetPercent(float f10) {
        a0 a0Var = this.S0;
        h0 h0Var = (h0) a0Var.Z.f1061n;
        h0Var.getClass();
        if ((f10 < 0.0f || f10 > 100.0f) && f10 != -1.0f) {
            throw new IllegalArgumentException();
        }
        h0Var.f3089c = f10;
        a0Var.t1();
        requestLayout();
    }

    public void setItemAlignmentOffsetWithPadding(boolean z) {
        a0 a0Var = this.S0;
        ((h0) a0Var.Z.f1061n).f3090d = z;
        a0Var.t1();
        requestLayout();
    }

    public void setItemAlignmentViewId(int i10) {
        a0 a0Var = this.S0;
        ((h0) a0Var.Z.f1061n).f3087a = i10;
        a0Var.t1();
    }

    @Deprecated
    public void setItemMargin(int i10) {
        setItemSpacing(i10);
    }

    public void setItemSpacing(int i10) {
        a0 a0Var = this.S0;
        a0Var.P = i10;
        a0Var.Q = i10;
        a0Var.S = i10;
        a0Var.R = i10;
        requestLayout();
    }

    public void setLayoutEnabled(boolean z) {
        a0 a0Var = this.S0;
        int i10 = a0Var.B;
        if (((i10 & 512) != 0) != z) {
            a0Var.B = (i10 & (-513)) | (z ? 512 : 0);
            a0Var.y0();
        }
    }

    public void setOnChildLaidOutListener(m0 m0Var) {
        this.S0.E = m0Var;
    }

    public void setOnChildSelectedListener(n0 n0Var) {
        this.S0.C = n0Var;
    }

    public void setOnChildViewHolderSelectedListener(o0 o0Var) {
        a0 a0Var = this.S0;
        if (o0Var == null) {
            a0Var.D = null;
            return;
        }
        ArrayList arrayList = a0Var.D;
        if (arrayList == null) {
            a0Var.D = new ArrayList();
        } else {
            arrayList.clear();
        }
        a0Var.D.add(o0Var);
    }

    public void setOnKeyInterceptListener(b bVar) {
        this.Z0 = bVar;
    }

    public void setOnMotionInterceptListener(d dVar) {
        this.Y0 = dVar;
    }

    public void setOnTouchInterceptListener(e eVar) {
        this.X0 = eVar;
    }

    public void setOnUnhandledKeyListener(f fVar) {
        this.f3084a1 = fVar;
    }

    public void setPruneChild(boolean z) {
        a0 a0Var = this.S0;
        int i10 = a0Var.B;
        if (((i10 & 65536) != 0) != z) {
            a0Var.B = (i10 & (-65537)) | (z ? 65536 : 0);
            if (z) {
                a0Var.y0();
            }
        }
    }

    public final void setSaveChildrenLimitNumber(int i10) {
        r1 r1Var = this.S0.f3062d0;
        r1Var.f3113c = i10;
        r1Var.a();
    }

    public final void setSaveChildrenPolicy(int i10) {
        r1 r1Var = this.S0.f3062d0;
        r1Var.f3112b = i10;
        r1Var.a();
    }

    public void setScrollEnabled(boolean z) {
        int i10;
        a0 a0Var = this.S0;
        int i11 = a0Var.B;
        if (((i11 & 131072) != 0) != z) {
            int i12 = (i11 & (-131073)) | (z ? 131072 : 0);
            a0Var.B = i12;
            if ((i12 & 131072) == 0 || a0Var.X != 0 || (i10 = a0Var.F) == -1) {
                return;
            }
            a0Var.n1(i10, true);
        }
    }

    public void setSelectedPosition(int i10) {
        this.S0.r1(i10, false);
    }

    public void setSelectedPositionSmooth(int i10) {
        this.S0.r1(i10, true);
    }

    public final void setSmoothScrollByBehavior(g gVar) {
        this.T0 = gVar;
    }

    public final void setSmoothScrollMaxPendingMoves(int i10) {
        this.S0.f3067q = i10;
    }

    public final void setSmoothScrollSpeedFactor(float f10) {
        this.S0.f3066p = f10;
    }

    @Deprecated
    public void setVerticalMargin(int i10) {
        setVerticalSpacing(i10);
    }

    public void setVerticalSpacing(int i10) {
        a0 a0Var = this.S0;
        if (a0Var.f3069s == 1) {
            a0Var.Q = i10;
            a0Var.R = i10;
        } else {
            a0Var.Q = i10;
            a0Var.S = i10;
        }
        requestLayout();
    }

    public void setWindowAlignment(int i10) {
        ((t1) this.S0.Y.f2602n).f3139f = i10;
        requestLayout();
    }

    public void setWindowAlignmentOffset(int i10) {
        ((t1) this.S0.Y.f2602n).f3140g = i10;
        requestLayout();
    }

    public void setWindowAlignmentOffsetPercent(float f10) {
        t1 t1Var = (t1) this.S0.Y.f2602n;
        t1Var.getClass();
        if ((f10 < 0.0f || f10 > 100.0f) && f10 != -1.0f) {
            throw new IllegalArgumentException();
        }
        t1Var.f3141h = f10;
        requestLayout();
    }

    public void setWindowAlignmentPreferKeyLineOverHighEdge(boolean z) {
        t1 t1Var = (t1) this.S0.Y.f2602n;
        t1Var.f3138e = z ? t1Var.f3138e | 2 : t1Var.f3138e & (-3);
        requestLayout();
    }

    public void setWindowAlignmentPreferKeyLineOverLowEdge(boolean z) {
        t1 t1Var = (t1) this.S0.Y.f2602n;
        t1Var.f3138e = z ? t1Var.f3138e | 1 : t1Var.f3138e & (-2);
        requestLayout();
    }
}
