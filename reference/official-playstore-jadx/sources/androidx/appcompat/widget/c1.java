package androidx.appcompat.widget;

import android.R;
import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.graphics.PorterDuff;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.os.Parcelable;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.ListAdapter;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;
import android.widget.ThemedSpinnerAdapter;

/* JADX INFO: loaded from: classes.dex */
public final class c1 extends Spinner implements androidx.core.view.y0 {

    /* JADX INFO: renamed from: s, reason: collision with root package name */
    public static final int[] f1527s = {R.attr.spinnerMode};

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final u f1528i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final Context f1529l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final p0 f1530m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public SpinnerAdapter f1531n;

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    public final boolean f1532o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public final b1 f1533p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public int f1534q;

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    public final Rect f1535r;

    /* JADX WARN: Removed duplicated region for block: B:26:0x0067  */
    /* JADX WARN: Removed duplicated region for block: B:29:0x009d  */
    /* JADX WARN: Removed duplicated region for block: B:32:0x00b0  */
    /* JADX WARN: Removed duplicated region for block: B:35:0x00ca  */
    /* JADX WARN: Removed duplicated region for block: B:39:0x00d7  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public c1(android.content.Context r13, android.util.AttributeSet r14) throws java.lang.Throwable {
        /*
            Method dump skipped, instruction units count: 219
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.appcompat.widget.c1.<init>(android.content.Context, android.util.AttributeSet):void");
    }

    public final int a(SpinnerAdapter spinnerAdapter, Drawable drawable) {
        int i10 = 0;
        if (spinnerAdapter == null) {
            return 0;
        }
        int iMakeMeasureSpec = View.MeasureSpec.makeMeasureSpec(getMeasuredWidth(), 0);
        int iMakeMeasureSpec2 = View.MeasureSpec.makeMeasureSpec(getMeasuredHeight(), 0);
        int iMax = Math.max(0, getSelectedItemPosition());
        int iMin = Math.min(spinnerAdapter.getCount(), iMax + 15);
        View view = null;
        int iMax2 = 0;
        for (int iMax3 = Math.max(0, iMax - (15 - (iMin - iMax))); iMax3 < iMin; iMax3++) {
            int itemViewType = spinnerAdapter.getItemViewType(iMax3);
            if (itemViewType != i10) {
                view = null;
                i10 = itemViewType;
            }
            view = spinnerAdapter.getView(iMax3, view, this);
            if (view.getLayoutParams() == null) {
                view.setLayoutParams(new ViewGroup.LayoutParams(-2, -2));
            }
            view.measure(iMakeMeasureSpec, iMakeMeasureSpec2);
            iMax2 = Math.max(iMax2, view.getMeasuredWidth());
        }
        if (drawable == null) {
            return iMax2;
        }
        Rect rect = this.f1535r;
        drawable.getPadding(rect);
        return rect.left + rect.right + iMax2;
    }

    @Override // android.view.ViewGroup, android.view.View
    public final void drawableStateChanged() {
        super.drawableStateChanged();
        u uVar = this.f1528i;
        if (uVar != null) {
            uVar.i();
        }
    }

    @Override // android.widget.Spinner
    public int getDropDownHorizontalOffset() {
        b1 b1Var = this.f1533p;
        return b1Var != null ? b1Var.b() : super.getDropDownHorizontalOffset();
    }

    @Override // android.widget.Spinner
    public int getDropDownVerticalOffset() {
        b1 b1Var = this.f1533p;
        return b1Var != null ? b1Var.getVerticalOffset() : super.getDropDownVerticalOffset();
    }

    @Override // android.widget.Spinner
    public int getDropDownWidth() {
        return this.f1533p != null ? this.f1534q : super.getDropDownWidth();
    }

    public final b1 getInternalPopup() {
        return this.f1533p;
    }

    @Override // android.widget.Spinner
    public Drawable getPopupBackground() {
        b1 b1Var = this.f1533p;
        return b1Var != null ? b1Var.f() : super.getPopupBackground();
    }

    @Override // android.widget.Spinner
    public Context getPopupContext() {
        return this.f1529l;
    }

    @Override // android.widget.Spinner
    public CharSequence getPrompt() {
        b1 b1Var = this.f1533p;
        return b1Var != null ? b1Var.e() : super.getPrompt();
    }

    public ColorStateList getSupportBackgroundTintList() {
        u uVar = this.f1528i;
        if (uVar != null) {
            return uVar.j();
        }
        return null;
    }

    public PorterDuff.Mode getSupportBackgroundTintMode() {
        u uVar = this.f1528i;
        if (uVar != null) {
            return uVar.k();
        }
        return null;
    }

    @Override // android.widget.Spinner, android.widget.AdapterView, android.view.ViewGroup, android.view.View
    public final void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        b1 b1Var = this.f1533p;
        if (b1Var == null || !b1Var.a()) {
            return;
        }
        b1Var.dismiss();
    }

    @Override // android.widget.Spinner, android.widget.AbsSpinner, android.view.View
    public final void onMeasure(int i10, int i11) {
        super.onMeasure(i10, i11);
        if (this.f1533p == null || View.MeasureSpec.getMode(i10) != Integer.MIN_VALUE) {
            return;
        }
        setMeasuredDimension(Math.min(Math.max(getMeasuredWidth(), a(getAdapter(), getBackground())), View.MeasureSpec.getSize(i10)), getMeasuredHeight());
    }

    @Override // android.widget.Spinner, android.widget.AbsSpinner, android.view.View
    public final void onRestoreInstanceState(Parcelable parcelable) {
        ViewTreeObserver viewTreeObserver;
        a1 a1Var = (a1) parcelable;
        super.onRestoreInstanceState(a1Var.getSuperState());
        if (!a1Var.f1505i || (viewTreeObserver = getViewTreeObserver()) == null) {
            return;
        }
        viewTreeObserver.addOnGlobalLayoutListener(new q0(this, 0));
    }

    @Override // android.widget.Spinner, android.widget.AbsSpinner, android.view.View
    public final Parcelable onSaveInstanceState() {
        a1 a1Var = new a1(super.onSaveInstanceState());
        b1 b1Var = this.f1533p;
        a1Var.f1505i = b1Var != null && b1Var.a();
        return a1Var;
    }

    @Override // android.widget.Spinner, android.view.View
    public final boolean onTouchEvent(MotionEvent motionEvent) {
        p0 p0Var = this.f1530m;
        if (p0Var == null || !p0Var.onTouch(this, motionEvent)) {
            return super.onTouchEvent(motionEvent);
        }
        return true;
    }

    @Override // android.widget.Spinner, android.view.View
    public final boolean performClick() {
        b1 b1Var = this.f1533p;
        if (b1Var == null) {
            return super.performClick();
        }
        if (b1Var.a()) {
            return true;
        }
        b1Var.j(s0.b(this), s0.a(this));
        return true;
    }

    @Override // android.view.View
    public void setBackgroundDrawable(Drawable drawable) {
        super.setBackgroundDrawable(drawable);
        u uVar = this.f1528i;
        if (uVar != null) {
            uVar.n();
        }
    }

    @Override // android.view.View
    public void setBackgroundResource(int i10) {
        super.setBackgroundResource(i10);
        u uVar = this.f1528i;
        if (uVar != null) {
            uVar.o(i10);
        }
    }

    @Override // android.widget.Spinner
    public void setDropDownHorizontalOffset(int i10) {
        b1 b1Var = this.f1533p;
        if (b1Var == null) {
            super.setDropDownHorizontalOffset(i10);
        } else {
            b1Var.i(i10);
            b1Var.d(i10);
        }
    }

    @Override // android.widget.Spinner
    public void setDropDownVerticalOffset(int i10) {
        b1 b1Var = this.f1533p;
        if (b1Var != null) {
            b1Var.h(i10);
        } else {
            super.setDropDownVerticalOffset(i10);
        }
    }

    @Override // android.widget.Spinner
    public void setDropDownWidth(int i10) {
        if (this.f1533p != null) {
            this.f1534q = i10;
        } else {
            super.setDropDownWidth(i10);
        }
    }

    @Override // android.widget.Spinner
    public void setPopupBackgroundDrawable(Drawable drawable) {
        b1 b1Var = this.f1533p;
        if (b1Var != null) {
            b1Var.m(drawable);
        } else {
            super.setPopupBackgroundDrawable(drawable);
        }
    }

    @Override // android.widget.Spinner
    public void setPopupBackgroundResource(int i10) {
        setPopupBackgroundDrawable(m2.f0.o(getPopupContext(), i10));
    }

    @Override // android.widget.Spinner
    public void setPrompt(CharSequence charSequence) {
        b1 b1Var = this.f1533p;
        if (b1Var != null) {
            b1Var.g(charSequence);
        } else {
            super.setPrompt(charSequence);
        }
    }

    public void setSupportBackgroundTintList(ColorStateList colorStateList) {
        u uVar = this.f1528i;
        if (uVar != null) {
            uVar.q(colorStateList);
        }
    }

    public void setSupportBackgroundTintMode(PorterDuff.Mode mode) {
        u uVar = this.f1528i;
        if (uVar != null) {
            uVar.r(mode);
        }
    }

    @Override // android.widget.AdapterView
    public void setAdapter(SpinnerAdapter spinnerAdapter) {
        if (!this.f1532o) {
            this.f1531n = spinnerAdapter;
            return;
        }
        super.setAdapter(spinnerAdapter);
        b1 b1Var = this.f1533p;
        if (b1Var != null) {
            Context context = this.f1529l;
            if (context == null) {
                context = getContext();
            }
            Resources.Theme theme = context.getTheme();
            v0 v0Var = new v0();
            v0Var.f1783i = spinnerAdapter;
            if (spinnerAdapter instanceof ListAdapter) {
                v0Var.f1784l = (ListAdapter) spinnerAdapter;
            }
            if (theme != null) {
                if (spinnerAdapter instanceof ThemedSpinnerAdapter) {
                    t0.a((ThemedSpinnerAdapter) spinnerAdapter, theme);
                } else if (spinnerAdapter instanceof e4) {
                    e4 e4Var = (e4) spinnerAdapter;
                    if (e4Var.getDropDownViewTheme() == null) {
                        e4Var.a();
                    }
                }
            }
            b1Var.k(v0Var);
        }
    }
}
