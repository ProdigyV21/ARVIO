package androidx.appcompat.widget;

import android.content.Context;
import android.content.res.Configuration;
import android.graphics.drawable.Drawable;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.ContextThemeWrapper;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.accessibility.AccessibilityEvent;
import android.widget.LinearLayout;
import androidx.appcompat.view.menu.ActionMenuItemView;
import androidx.appcompat.view.menu.d0;

/* JADX INFO: loaded from: classes.dex */
public class ActionMenuView extends o2 implements androidx.appcompat.view.menu.p, androidx.appcompat.view.menu.e0 {
    public Context A;
    public int B;
    public boolean C;
    public m D;
    public androidx.appcompat.app.d1 E;
    public androidx.appcompat.view.menu.o F;
    public boolean G;
    public int H;
    public final int I;
    public final int J;
    public b K;
    public androidx.appcompat.view.menu.q z;

    public interface a {
        boolean a();

        boolean b();
    }

    public interface b {
        boolean onMenuItemClick(MenuItem menuItem);
    }

    public ActionMenuView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet, 0);
        setBaselineAligned(false);
        float f10 = context.getResources().getDisplayMetrics().density;
        this.I = (int) (56.0f * f10);
        this.J = (int) (f10 * 4.0f);
        this.A = context;
        this.B = 0;
    }

    public static o i() {
        o oVar = new o(-2, -2);
        oVar.f1673a = false;
        ((LinearLayout.LayoutParams) oVar).gravity = 16;
        return oVar;
    }

    public static o j(ViewGroup.LayoutParams layoutParams) {
        o oVar;
        if (layoutParams == null) {
            return i();
        }
        if (layoutParams instanceof o) {
            o oVar2 = (o) layoutParams;
            oVar = new o(oVar2);
            oVar.f1673a = oVar2.f1673a;
        } else {
            oVar = new o(layoutParams);
        }
        if (((LinearLayout.LayoutParams) oVar).gravity <= 0) {
            ((LinearLayout.LayoutParams) oVar).gravity = 16;
        }
        return oVar;
    }

    @Override // androidx.appcompat.view.menu.e0
    public final void a(androidx.appcompat.view.menu.q qVar) {
        this.z = qVar;
    }

    @Override // androidx.appcompat.view.menu.p
    public final boolean b(androidx.appcompat.view.menu.t tVar) {
        return this.z.q(tVar, null, 0);
    }

    @Override // androidx.appcompat.widget.o2, android.view.ViewGroup
    public final boolean checkLayoutParams(ViewGroup.LayoutParams layoutParams) {
        return layoutParams instanceof o;
    }

    @Override // android.view.View
    public final boolean dispatchPopulateAccessibilityEvent(AccessibilityEvent accessibilityEvent) {
        return false;
    }

    @Override // androidx.appcompat.widget.o2
    /* JADX INFO: renamed from: e */
    public final /* bridge */ /* synthetic */ m2 generateDefaultLayoutParams() {
        return i();
    }

    @Override // androidx.appcompat.widget.o2
    /* JADX INFO: renamed from: f */
    public final m2 generateLayoutParams(AttributeSet attributeSet) {
        return new o(getContext(), attributeSet);
    }

    @Override // androidx.appcompat.widget.o2
    /* JADX INFO: renamed from: g */
    public final /* bridge */ /* synthetic */ m2 generateLayoutParams(ViewGroup.LayoutParams layoutParams) {
        return j(layoutParams);
    }

    @Override // androidx.appcompat.widget.o2, android.view.ViewGroup
    public final /* bridge */ /* synthetic */ ViewGroup.LayoutParams generateDefaultLayoutParams() {
        return i();
    }

    @Override // androidx.appcompat.widget.o2, android.view.ViewGroup
    public final /* bridge */ /* synthetic */ ViewGroup.LayoutParams generateLayoutParams(ViewGroup.LayoutParams layoutParams) {
        return j(layoutParams);
    }

    public Menu getMenu() {
        if (this.z == null) {
            Context context = getContext();
            androidx.appcompat.view.menu.q qVar = new androidx.appcompat.view.menu.q(context);
            this.z = qVar;
            qVar.f1346e = new p(this);
            m mVar = new m(context);
            this.D = mVar;
            mVar.f1662v = true;
            mVar.f1663w = true;
            d0.a nVar = this.E;
            if (nVar == null) {
                nVar = new n();
            }
            mVar.f1279o = nVar;
            this.z.b(mVar, this.A);
            m mVar2 = this.D;
            mVar2.f1282r = this;
            this.z = mVar2.f1277m;
        }
        return this.z;
    }

    public Drawable getOverflowIcon() {
        getMenu();
        m mVar = this.D;
        j jVar = mVar.f1659s;
        if (jVar != null) {
            return jVar.getDrawable();
        }
        if (mVar.f1661u) {
            return mVar.f1660t;
        }
        return null;
    }

    public int getPopupTheme() {
        return this.B;
    }

    public int getWindowAnimations() {
        return 0;
    }

    public final boolean k(int i10) {
        boolean zA = false;
        if (i10 == 0) {
            return false;
        }
        KeyEvent.Callback childAt = getChildAt(i10 - 1);
        KeyEvent.Callback childAt2 = getChildAt(i10);
        if (i10 < getChildCount() && (childAt instanceof a)) {
            zA = ((a) childAt).a();
        }
        return (i10 <= 0 || !(childAt2 instanceof a)) ? zA : ((a) childAt2).b() | zA;
    }

    @Override // android.view.View
    public final void onConfigurationChanged(Configuration configuration) {
        super.onConfigurationChanged(configuration);
        m mVar = this.D;
        if (mVar != null) {
            mVar.e();
            if (this.D.k()) {
                this.D.j();
                this.D.l();
            }
        }
    }

    @Override // android.view.ViewGroup, android.view.View
    public final void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        m mVar = this.D;
        if (mVar != null) {
            mVar.j();
            g gVar = mVar.D;
            if (gVar == null || !gVar.b()) {
                return;
            }
            gVar.f1273i.dismiss();
        }
    }

    @Override // androidx.appcompat.widget.o2, android.view.ViewGroup, android.view.View
    public final void onLayout(boolean z, int i10, int i11, int i12, int i13) {
        int width;
        int paddingLeft;
        if (!this.G) {
            super.onLayout(z, i10, i11, i12, i13);
            return;
        }
        int childCount = getChildCount();
        int i14 = (i13 - i11) / 2;
        int dividerWidth = getDividerWidth();
        int i15 = i12 - i10;
        int paddingRight = (i15 - getPaddingRight()) - getPaddingLeft();
        boolean zA = w4.a(this);
        int i16 = 0;
        int i17 = 0;
        for (int i18 = 0; i18 < childCount; i18++) {
            View childAt = getChildAt(i18);
            if (childAt.getVisibility() != 8) {
                o oVar = (o) childAt.getLayoutParams();
                if (oVar.f1673a) {
                    int measuredWidth = childAt.getMeasuredWidth();
                    if (k(i18)) {
                        measuredWidth += dividerWidth;
                    }
                    int measuredHeight = childAt.getMeasuredHeight();
                    if (zA) {
                        paddingLeft = getPaddingLeft() + ((LinearLayout.LayoutParams) oVar).leftMargin;
                        width = paddingLeft + measuredWidth;
                    } else {
                        width = (getWidth() - getPaddingRight()) - ((LinearLayout.LayoutParams) oVar).rightMargin;
                        paddingLeft = width - measuredWidth;
                    }
                    int i19 = i14 - (measuredHeight / 2);
                    childAt.layout(paddingLeft, i19, width, measuredHeight + i19);
                    paddingRight -= measuredWidth;
                    i16 = 1;
                } else {
                    paddingRight -= (childAt.getMeasuredWidth() + ((LinearLayout.LayoutParams) oVar).leftMargin) + ((LinearLayout.LayoutParams) oVar).rightMargin;
                    k(i18);
                    i17++;
                }
            }
        }
        if (childCount == 1 && i16 == 0) {
            View childAt2 = getChildAt(0);
            int measuredWidth2 = childAt2.getMeasuredWidth();
            int measuredHeight2 = childAt2.getMeasuredHeight();
            int i20 = (i15 / 2) - (measuredWidth2 / 2);
            int i21 = i14 - (measuredHeight2 / 2);
            childAt2.layout(i20, i21, measuredWidth2 + i20, measuredHeight2 + i21);
            return;
        }
        int i22 = i17 - (i16 ^ 1);
        int iMax = Math.max(0, i22 > 0 ? paddingRight / i22 : 0);
        if (zA) {
            int width2 = getWidth() - getPaddingRight();
            for (int i23 = 0; i23 < childCount; i23++) {
                View childAt3 = getChildAt(i23);
                o oVar2 = (o) childAt3.getLayoutParams();
                if (childAt3.getVisibility() != 8 && !oVar2.f1673a) {
                    int i24 = width2 - ((LinearLayout.LayoutParams) oVar2).rightMargin;
                    int measuredWidth3 = childAt3.getMeasuredWidth();
                    int measuredHeight3 = childAt3.getMeasuredHeight();
                    int i25 = i14 - (measuredHeight3 / 2);
                    childAt3.layout(i24 - measuredWidth3, i25, i24, measuredHeight3 + i25);
                    width2 = i24 - ((measuredWidth3 + ((LinearLayout.LayoutParams) oVar2).leftMargin) + iMax);
                }
            }
            return;
        }
        int paddingLeft2 = getPaddingLeft();
        for (int i26 = 0; i26 < childCount; i26++) {
            View childAt4 = getChildAt(i26);
            o oVar3 = (o) childAt4.getLayoutParams();
            if (childAt4.getVisibility() != 8 && !oVar3.f1673a) {
                int i27 = paddingLeft2 + ((LinearLayout.LayoutParams) oVar3).leftMargin;
                int measuredWidth4 = childAt4.getMeasuredWidth();
                int measuredHeight4 = childAt4.getMeasuredHeight();
                int i28 = i14 - (measuredHeight4 / 2);
                childAt4.layout(i27, i28, i27 + measuredWidth4, measuredHeight4 + i28);
                paddingLeft2 = measuredWidth4 + ((LinearLayout.LayoutParams) oVar3).rightMargin + iMax + i27;
            }
        }
    }

    /* JADX WARN: Type inference failed for: r11v15 */
    /* JADX WARN: Type inference failed for: r11v16, types: [boolean, int] */
    /* JADX WARN: Type inference failed for: r11v18 */
    /* JADX WARN: Type inference failed for: r11v41 */
    @Override // androidx.appcompat.widget.o2, android.view.View
    public final void onMeasure(int i10, int i11) {
        int i12;
        int i13;
        ?? r11;
        int i14;
        int i15;
        androidx.appcompat.view.menu.q qVar;
        boolean z = this.G;
        boolean z5 = View.MeasureSpec.getMode(i10) == 1073741824;
        this.G = z5;
        if (z != z5) {
            this.H = 0;
        }
        int size = View.MeasureSpec.getSize(i10);
        if (this.G && (qVar = this.z) != null && size != this.H) {
            this.H = size;
            qVar.p(true);
        }
        int childCount = getChildCount();
        if (!this.G || childCount <= 0) {
            for (int i16 = 0; i16 < childCount; i16++) {
                o oVar = (o) getChildAt(i16).getLayoutParams();
                ((LinearLayout.LayoutParams) oVar).rightMargin = 0;
                ((LinearLayout.LayoutParams) oVar).leftMargin = 0;
            }
            super.onMeasure(i10, i11);
            return;
        }
        int mode = View.MeasureSpec.getMode(i11);
        int size2 = View.MeasureSpec.getSize(i10);
        int size3 = View.MeasureSpec.getSize(i11);
        int paddingRight = getPaddingRight() + getPaddingLeft();
        int paddingBottom = getPaddingBottom() + getPaddingTop();
        int childMeasureSpec = ViewGroup.getChildMeasureSpec(i11, paddingBottom, -2);
        int i17 = size2 - paddingRight;
        int i18 = this.I;
        int i19 = i17 / i18;
        int i20 = i17 % i18;
        if (i19 == 0) {
            setMeasuredDimension(i17, 0);
            return;
        }
        int i21 = (i20 / i19) + i18;
        int childCount2 = getChildCount();
        int iMax = 0;
        int i22 = 0;
        int iMax2 = 0;
        int i23 = 0;
        boolean z10 = false;
        int i24 = 0;
        long j10 = 0;
        while (true) {
            i12 = this.J;
            if (i23 >= childCount2) {
                break;
            }
            View childAt = getChildAt(i23);
            int i25 = size3;
            int i26 = paddingBottom;
            if (childAt.getVisibility() == 8) {
                i14 = i21;
            } else {
                boolean z11 = childAt instanceof ActionMenuItemView;
                i22++;
                if (z11) {
                    childAt.setPadding(i12, 0, i12, 0);
                }
                o oVar2 = (o) childAt.getLayoutParams();
                oVar2.f1678f = false;
                oVar2.f1675c = 0;
                oVar2.f1674b = 0;
                oVar2.f1676d = false;
                ((LinearLayout.LayoutParams) oVar2).leftMargin = 0;
                ((LinearLayout.LayoutParams) oVar2).rightMargin = 0;
                oVar2.f1677e = z11 && !TextUtils.isEmpty(((ActionMenuItemView) childAt).getText());
                int i27 = oVar2.f1673a ? 1 : i19;
                o oVar3 = (o) childAt.getLayoutParams();
                int i28 = i19;
                i14 = i21;
                int iMakeMeasureSpec = View.MeasureSpec.makeMeasureSpec(View.MeasureSpec.getSize(childMeasureSpec) - i26, View.MeasureSpec.getMode(childMeasureSpec));
                ActionMenuItemView actionMenuItemView = z11 ? (ActionMenuItemView) childAt : null;
                boolean z12 = (actionMenuItemView == null || TextUtils.isEmpty(actionMenuItemView.getText())) ? false : true;
                boolean z13 = z12;
                if (i27 <= 0 || (z12 && i27 < 2)) {
                    i15 = 0;
                } else {
                    childAt.measure(View.MeasureSpec.makeMeasureSpec(i14 * i27, Integer.MIN_VALUE), iMakeMeasureSpec);
                    int measuredWidth = childAt.getMeasuredWidth();
                    i15 = measuredWidth / i14;
                    if (measuredWidth % i14 != 0) {
                        i15++;
                    }
                    if (z13 && i15 < 2) {
                        i15 = 2;
                    }
                }
                oVar3.f1676d = !oVar3.f1673a && z13;
                oVar3.f1674b = i15;
                childAt.measure(View.MeasureSpec.makeMeasureSpec(i15 * i14, 1073741824), iMakeMeasureSpec);
                iMax2 = Math.max(iMax2, i15);
                if (oVar2.f1676d) {
                    i24++;
                }
                if (oVar2.f1673a) {
                    z10 = true;
                }
                i19 = i28 - i15;
                iMax = Math.max(iMax, childAt.getMeasuredHeight());
                if (i15 == 1) {
                    j10 |= (long) (1 << i23);
                }
            }
            i23++;
            size3 = i25;
            paddingBottom = i26;
            i21 = i14;
        }
        int i29 = size3;
        int i30 = i19;
        int i31 = i21;
        boolean z14 = z10 && i22 == 2;
        int i32 = i30;
        boolean z15 = false;
        while (i24 > 0 && i32 > 0) {
            int i33 = Integer.MAX_VALUE;
            long j11 = 0;
            int i34 = 0;
            int i35 = 0;
            while (i35 < childCount2) {
                int i36 = iMax;
                o oVar4 = (o) getChildAt(i35).getLayoutParams();
                boolean z16 = z14;
                if (oVar4.f1676d) {
                    int i37 = oVar4.f1674b;
                    if (i37 < i33) {
                        j11 = 1 << i35;
                        i33 = i37;
                        i34 = 1;
                    } else if (i37 == i33) {
                        j11 |= 1 << i35;
                        i34++;
                    }
                }
                i35++;
                z14 = z16;
                iMax = i36;
            }
            i13 = iMax;
            boolean z17 = z14;
            j10 |= j11;
            if (i34 > i32) {
                break;
            }
            int i38 = i33 + 1;
            int i39 = 0;
            while (i39 < childCount2) {
                View childAt2 = getChildAt(i39);
                o oVar5 = (o) childAt2.getLayoutParams();
                boolean z18 = z10;
                long j12 = 1 << i39;
                if ((j11 & j12) != 0) {
                    if (z17 && oVar5.f1677e) {
                        r11 = 1;
                        r11 = 1;
                        if (i32 == 1) {
                            childAt2.setPadding(i12 + i31, 0, i12, 0);
                        }
                    } else {
                        r11 = 1;
                    }
                    oVar5.f1674b += r11;
                    oVar5.f1678f = r11;
                    i32--;
                } else if (oVar5.f1674b == i38) {
                    j10 |= j12;
                }
                i39++;
                z10 = z18;
            }
            z14 = z17;
            iMax = i13;
            z15 = true;
        }
        i13 = iMax;
        boolean z19 = !z10 && i22 == 1;
        if (i32 > 0 && j10 != 0 && (i32 < i22 - 1 || z19 || iMax2 > 1)) {
            float fBitCount = Long.bitCount(j10);
            if (!z19) {
                if ((j10 & 1) != 0 && !((o) getChildAt(0).getLayoutParams()).f1677e) {
                    fBitCount -= 0.5f;
                }
                int i40 = childCount2 - 1;
                if ((j10 & ((long) (1 << i40))) != 0 && !((o) getChildAt(i40).getLayoutParams()).f1677e) {
                    fBitCount -= 0.5f;
                }
            }
            int i41 = fBitCount > 0.0f ? (int) ((i32 * i31) / fBitCount) : 0;
            boolean z20 = z15;
            for (int i42 = 0; i42 < childCount2; i42++) {
                if ((j10 & ((long) (1 << i42))) != 0) {
                    View childAt3 = getChildAt(i42);
                    o oVar6 = (o) childAt3.getLayoutParams();
                    if (childAt3 instanceof ActionMenuItemView) {
                        oVar6.f1675c = i41;
                        oVar6.f1678f = true;
                        if (i42 == 0 && !oVar6.f1677e) {
                            ((LinearLayout.LayoutParams) oVar6).leftMargin = (-i41) / 2;
                        }
                        z20 = true;
                    } else if (oVar6.f1673a) {
                        oVar6.f1675c = i41;
                        oVar6.f1678f = true;
                        ((LinearLayout.LayoutParams) oVar6).rightMargin = (-i41) / 2;
                        z20 = true;
                    } else {
                        if (i42 != 0) {
                            ((LinearLayout.LayoutParams) oVar6).leftMargin = i41 / 2;
                        }
                        if (i42 != childCount2 - 1) {
                            ((LinearLayout.LayoutParams) oVar6).rightMargin = i41 / 2;
                        }
                    }
                }
            }
            z15 = z20;
        }
        if (z15) {
            for (int i43 = 0; i43 < childCount2; i43++) {
                View childAt4 = getChildAt(i43);
                o oVar7 = (o) childAt4.getLayoutParams();
                if (oVar7.f1678f) {
                    childAt4.measure(View.MeasureSpec.makeMeasureSpec((oVar7.f1674b * i31) + oVar7.f1675c, 1073741824), childMeasureSpec);
                }
            }
        }
        setMeasuredDimension(i17, mode != 1073741824 ? i13 : i29);
    }

    public void setExpandedActionViewsExclusive(boolean z) {
        this.D.A = z;
    }

    public void setOnMenuItemClickListener(b bVar) {
        this.K = bVar;
    }

    public void setOverflowIcon(Drawable drawable) {
        getMenu();
        m mVar = this.D;
        j jVar = mVar.f1659s;
        if (jVar != null) {
            jVar.setImageDrawable(drawable);
        } else {
            mVar.f1661u = true;
            mVar.f1660t = drawable;
        }
    }

    public void setOverflowReserved(boolean z) {
        this.C = z;
    }

    public void setPopupTheme(int i10) {
        if (this.B != i10) {
            this.B = i10;
            if (i10 == 0) {
                this.A = getContext();
            } else {
                this.A = new ContextThemeWrapper(getContext(), i10);
            }
        }
    }

    public void setPresenter(m mVar) {
        this.D = mVar;
        mVar.f1282r = this;
        this.z = mVar.f1277m;
    }

    @Override // androidx.appcompat.widget.o2, android.view.ViewGroup
    public final ViewGroup.LayoutParams generateLayoutParams(AttributeSet attributeSet) {
        return new o(getContext(), attributeSet);
    }
}
