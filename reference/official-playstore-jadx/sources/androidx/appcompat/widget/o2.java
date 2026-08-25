package androidx.appcompat.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.view.accessibility.AccessibilityEvent;
import android.view.accessibility.AccessibilityNodeInfo;
import android.widget.LinearLayout;

/* JADX INFO: loaded from: classes.dex */
public abstract class o2 extends ViewGroup {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public boolean f1692i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public int f1693l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public int f1694m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public int f1695n;

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    public int f1696o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public int f1697p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public float f1698q;

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    public boolean f1699r;

    /* JADX INFO: renamed from: s, reason: collision with root package name */
    public int[] f1700s;

    /* JADX INFO: renamed from: t, reason: collision with root package name */
    public int[] f1701t;

    /* JADX INFO: renamed from: u, reason: collision with root package name */
    public Drawable f1702u;

    /* JADX INFO: renamed from: v, reason: collision with root package name */
    public int f1703v;

    /* JADX INFO: renamed from: w, reason: collision with root package name */
    public int f1704w;
    public int x;

    /* JADX INFO: renamed from: y, reason: collision with root package name */
    public int f1705y;

    public o2(Context context, AttributeSet attributeSet, int i10) {
        super(context, attributeSet, i10);
        this.f1692i = true;
        this.f1693l = -1;
        this.f1694m = 0;
        this.f1696o = 8388659;
        int[] iArr = j.a.f19029n;
        androidx.appcompat.app.i1 i1VarZ = androidx.appcompat.app.i1.Z(context, attributeSet, iArr, i10);
        androidx.core.view.b2.g(this, context, iArr, attributeSet, (TypedArray) i1VarZ.f1060m, i10);
        TypedArray typedArray = (TypedArray) i1VarZ.f1060m;
        int i11 = typedArray.getInt(1, -1);
        if (i11 >= 0) {
            setOrientation(i11);
        }
        int i12 = typedArray.getInt(0, -1);
        if (i12 >= 0) {
            setGravity(i12);
        }
        boolean z = typedArray.getBoolean(2, true);
        if (!z) {
            setBaselineAligned(z);
        }
        this.f1698q = typedArray.getFloat(4, -1.0f);
        this.f1693l = typedArray.getInt(3, -1);
        this.f1699r = typedArray.getBoolean(7, false);
        setDividerDrawable(i1VarZ.T(5));
        this.x = typedArray.getInt(8, 0);
        this.f1705y = typedArray.getDimensionPixelSize(6, 0);
        i1VarZ.b0();
    }

    public final void c(Canvas canvas, int i10) {
        this.f1702u.setBounds(getPaddingLeft() + this.f1705y, i10, (getWidth() - getPaddingRight()) - this.f1705y, this.f1704w + i10);
        this.f1702u.draw(canvas);
    }

    @Override // android.view.ViewGroup
    public boolean checkLayoutParams(ViewGroup.LayoutParams layoutParams) {
        return layoutParams instanceof m2;
    }

    public final void d(Canvas canvas, int i10) {
        this.f1702u.setBounds(i10, getPaddingTop() + this.f1705y, this.f1703v + i10, (getHeight() - getPaddingBottom()) - this.f1705y);
        this.f1702u.draw(canvas);
    }

    @Override // android.view.ViewGroup
    /* JADX INFO: renamed from: e, reason: merged with bridge method [inline-methods] */
    public m2 generateDefaultLayoutParams() {
        int i10 = this.f1695n;
        if (i10 == 0) {
            return new m2(-2, -2);
        }
        if (i10 == 1) {
            return new m2(-1, -2);
        }
        return null;
    }

    @Override // android.view.ViewGroup
    /* JADX INFO: renamed from: f, reason: merged with bridge method [inline-methods] */
    public m2 generateLayoutParams(AttributeSet attributeSet) {
        return new m2(getContext(), attributeSet);
    }

    @Override // android.view.ViewGroup
    /* JADX INFO: renamed from: g, reason: merged with bridge method [inline-methods] */
    public m2 generateLayoutParams(ViewGroup.LayoutParams layoutParams) {
        return new m2(layoutParams);
    }

    @Override // android.view.View
    public int getBaseline() {
        int i10;
        if (this.f1693l < 0) {
            return super.getBaseline();
        }
        int childCount = getChildCount();
        int i11 = this.f1693l;
        if (childCount <= i11) {
            throw new RuntimeException("mBaselineAlignedChildIndex of LinearLayout set to an index that is out of bounds.");
        }
        View childAt = getChildAt(i11);
        int baseline = childAt.getBaseline();
        if (baseline == -1) {
            if (this.f1693l == 0) {
                return -1;
            }
            throw new RuntimeException("mBaselineAlignedChildIndex of LinearLayout points to a View that doesn't know how to get its baseline.");
        }
        int bottom = this.f1694m;
        if (this.f1695n == 1 && (i10 = this.f1696o & 112) != 48) {
            if (i10 == 16) {
                bottom += ((((getBottom() - getTop()) - getPaddingTop()) - getPaddingBottom()) - this.f1697p) / 2;
            } else if (i10 == 80) {
                bottom = ((getBottom() - getTop()) - getPaddingBottom()) - this.f1697p;
            }
        }
        return bottom + ((LinearLayout.LayoutParams) ((m2) childAt.getLayoutParams())).topMargin + baseline;
    }

    public int getBaselineAlignedChildIndex() {
        return this.f1693l;
    }

    public Drawable getDividerDrawable() {
        return this.f1702u;
    }

    public int getDividerPadding() {
        return this.f1705y;
    }

    public int getDividerWidth() {
        return this.f1703v;
    }

    public int getGravity() {
        return this.f1696o;
    }

    public int getOrientation() {
        return this.f1695n;
    }

    public int getShowDividers() {
        return this.x;
    }

    public int getVirtualChildCount() {
        return getChildCount();
    }

    public float getWeightSum() {
        return this.f1698q;
    }

    public final boolean h(int i10) {
        if (i10 == 0) {
            return (this.x & 1) != 0;
        }
        if (i10 == getChildCount()) {
            return (this.x & 4) != 0;
        }
        if ((this.x & 2) != 0) {
            for (int i11 = i10 - 1; i11 >= 0; i11--) {
                if (getChildAt(i11).getVisibility() != 8) {
                    return true;
                }
            }
        }
        return false;
    }

    @Override // android.view.View
    public final void onDraw(Canvas canvas) {
        int right;
        int left;
        int i10;
        if (this.f1702u == null) {
            return;
        }
        int i11 = 0;
        if (this.f1695n == 1) {
            int virtualChildCount = getVirtualChildCount();
            while (i11 < virtualChildCount) {
                View childAt = getChildAt(i11);
                if (childAt != null && childAt.getVisibility() != 8 && h(i11)) {
                    c(canvas, (childAt.getTop() - ((LinearLayout.LayoutParams) ((m2) childAt.getLayoutParams())).topMargin) - this.f1704w);
                }
                i11++;
            }
            if (h(virtualChildCount)) {
                View childAt2 = getChildAt(virtualChildCount - 1);
                c(canvas, childAt2 == null ? (getHeight() - getPaddingBottom()) - this.f1704w : childAt2.getBottom() + ((LinearLayout.LayoutParams) ((m2) childAt2.getLayoutParams())).bottomMargin);
                return;
            }
            return;
        }
        int virtualChildCount2 = getVirtualChildCount();
        boolean zA = w4.a(this);
        while (i11 < virtualChildCount2) {
            View childAt3 = getChildAt(i11);
            if (childAt3 != null && childAt3.getVisibility() != 8 && h(i11)) {
                m2 m2Var = (m2) childAt3.getLayoutParams();
                d(canvas, zA ? childAt3.getRight() + ((LinearLayout.LayoutParams) m2Var).rightMargin : (childAt3.getLeft() - ((LinearLayout.LayoutParams) m2Var).leftMargin) - this.f1703v);
            }
            i11++;
        }
        if (h(virtualChildCount2)) {
            View childAt4 = getChildAt(virtualChildCount2 - 1);
            if (childAt4 != null) {
                m2 m2Var2 = (m2) childAt4.getLayoutParams();
                if (zA) {
                    left = childAt4.getLeft() - ((LinearLayout.LayoutParams) m2Var2).leftMargin;
                    i10 = this.f1703v;
                    right = left - i10;
                } else {
                    right = childAt4.getRight() + ((LinearLayout.LayoutParams) m2Var2).rightMargin;
                }
            } else if (zA) {
                right = getPaddingLeft();
            } else {
                left = getWidth() - getPaddingRight();
                i10 = this.f1703v;
                right = left - i10;
            }
            d(canvas, right);
        }
    }

    @Override // android.view.View
    public final void onInitializeAccessibilityEvent(AccessibilityEvent accessibilityEvent) {
        super.onInitializeAccessibilityEvent(accessibilityEvent);
        accessibilityEvent.setClassName("androidx.appcompat.widget.LinearLayoutCompat");
    }

    @Override // android.view.View
    public final void onInitializeAccessibilityNodeInfo(AccessibilityNodeInfo accessibilityNodeInfo) {
        super.onInitializeAccessibilityNodeInfo(accessibilityNodeInfo);
        accessibilityNodeInfo.setClassName("androidx.appcompat.widget.LinearLayoutCompat");
    }

    /* JADX WARN: Removed duplicated region for block: B:29:0x009f  */
    /* JADX WARN: Removed duplicated region for block: B:58:0x0157  */
    /* JADX WARN: Removed duplicated region for block: B:61:0x0160  */
    /* JADX WARN: Removed duplicated region for block: B:73:0x018e  */
    /* JADX WARN: Removed duplicated region for block: B:76:0x01a1  */
    /* JADX WARN: Removed duplicated region for block: B:77:0x01a6  */
    @Override // android.view.ViewGroup, android.view.View
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public void onLayout(boolean r23, int r24, int r25, int r26, int r27) {
        /*
            Method dump skipped, instruction units count: 458
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.appcompat.widget.o2.onLayout(boolean, int, int, int, int):void");
    }

    /* JADX WARN: Removed duplicated region for block: B:228:0x04e3  */
    /* JADX WARN: Removed duplicated region for block: B:231:0x04f8  */
    /* JADX WARN: Removed duplicated region for block: B:237:0x0526  */
    /* JADX WARN: Removed duplicated region for block: B:243:0x0536  */
    /* JADX WARN: Removed duplicated region for block: B:246:0x053d  */
    /* JADX WARN: Removed duplicated region for block: B:250:0x0547  */
    /* JADX WARN: Removed duplicated region for block: B:366:0x079c  */
    /* JADX WARN: Removed duplicated region for block: B:64:0x013f  */
    /* JADX WARN: Removed duplicated region for block: B:68:0x0148  */
    @Override // android.view.View
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public void onMeasure(int r39, int r40) {
        /*
            Method dump skipped, instruction units count: 2150
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.appcompat.widget.o2.onMeasure(int, int):void");
    }

    public void setBaselineAligned(boolean z) {
        this.f1692i = z;
    }

    public void setBaselineAlignedChildIndex(int i10) {
        if (i10 >= 0 && i10 < getChildCount()) {
            this.f1693l = i10;
            return;
        }
        throw new IllegalArgumentException("base aligned child index out of range (0, " + getChildCount() + ")");
    }

    public void setDividerDrawable(Drawable drawable) {
        if (drawable == this.f1702u) {
            return;
        }
        this.f1702u = drawable;
        if (drawable != null) {
            this.f1703v = drawable.getIntrinsicWidth();
            this.f1704w = drawable.getIntrinsicHeight();
        } else {
            this.f1703v = 0;
            this.f1704w = 0;
        }
        setWillNotDraw(drawable == null);
        requestLayout();
    }

    public void setDividerPadding(int i10) {
        this.f1705y = i10;
    }

    public void setGravity(int i10) {
        if (this.f1696o != i10) {
            if ((8388615 & i10) == 0) {
                i10 |= 8388611;
            }
            if ((i10 & 112) == 0) {
                i10 |= 48;
            }
            this.f1696o = i10;
            requestLayout();
        }
    }

    public void setHorizontalGravity(int i10) {
        int i11 = i10 & 8388615;
        int i12 = this.f1696o;
        if ((8388615 & i12) != i11) {
            this.f1696o = i11 | ((-8388616) & i12);
            requestLayout();
        }
    }

    public void setMeasureWithLargestChildEnabled(boolean z) {
        this.f1699r = z;
    }

    public void setOrientation(int i10) {
        if (this.f1695n != i10) {
            this.f1695n = i10;
            requestLayout();
        }
    }

    public void setShowDividers(int i10) {
        if (i10 != this.x) {
            requestLayout();
        }
        this.x = i10;
    }

    public void setVerticalGravity(int i10) {
        int i11 = i10 & 112;
        int i12 = this.f1696o;
        if ((i12 & 112) != i11) {
            this.f1696o = i11 | (i12 & (-113));
            requestLayout();
        }
    }

    public void setWeightSum(float f10) {
        this.f1698q = Math.max(0.0f, f10);
    }

    @Override // android.view.ViewGroup
    public final boolean shouldDelayChildPressedState() {
        return false;
    }
}
