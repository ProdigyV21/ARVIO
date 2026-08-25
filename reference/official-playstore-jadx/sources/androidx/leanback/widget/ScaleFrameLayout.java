package androidx.leanback.widget;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

/* JADX INFO: loaded from: classes3.dex */
public class ScaleFrameLayout extends FrameLayout {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public float f3000i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public float f3001l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public float f3002m;

    public ScaleFrameLayout(Context context, AttributeSet attributeSet) {
        super(context, attributeSet, 0);
        this.f3000i = 1.0f;
        this.f3001l = 1.0f;
        this.f3002m = 1.0f;
    }

    @Override // android.view.ViewGroup
    public final void addView(View view, int i10, ViewGroup.LayoutParams layoutParams) {
        super.addView(view, i10, layoutParams);
        view.setScaleX(this.f3002m);
        view.setScaleY(this.f3002m);
    }

    @Override // android.view.ViewGroup
    public final boolean addViewInLayout(View view, int i10, ViewGroup.LayoutParams layoutParams, boolean z) {
        boolean zAddViewInLayout = super.addViewInLayout(view, i10, layoutParams, z);
        if (zAddViewInLayout) {
            view.setScaleX(this.f3002m);
            view.setScaleY(this.f3002m);
        }
        return zAddViewInLayout;
    }

    /* JADX WARN: Removed duplicated region for block: B:33:0x00c9  */
    /* JADX WARN: Removed duplicated region for block: B:42:0x00de  */
    @Override // android.widget.FrameLayout, android.view.ViewGroup, android.view.View
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final void onLayout(boolean r17, int r18, int r19, int r20, int r21) {
        /*
            Method dump skipped, instruction units count: 259
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.leanback.widget.ScaleFrameLayout.onLayout(boolean, int, int, int, int):void");
    }

    @Override // android.widget.FrameLayout, android.view.View
    public final void onMeasure(int i10, int i11) {
        float f10 = this.f3000i;
        if (f10 == 1.0f && this.f3001l == 1.0f) {
            super.onMeasure(i10, i11);
            return;
        }
        if (f10 != 1.0f) {
            i10 = View.MeasureSpec.makeMeasureSpec((int) ((View.MeasureSpec.getSize(i10) / f10) + 0.5f), View.MeasureSpec.getMode(i10));
        }
        float f11 = this.f3001l;
        if (f11 != 1.0f) {
            i11 = View.MeasureSpec.makeMeasureSpec((int) ((View.MeasureSpec.getSize(i11) / f11) + 0.5f), View.MeasureSpec.getMode(i11));
        }
        super.onMeasure(i10, i11);
        setMeasuredDimension((int) ((getMeasuredWidth() * this.f3000i) + 0.5f), (int) ((getMeasuredHeight() * this.f3001l) + 0.5f));
    }

    public void setChildScale(float f10) {
        if (this.f3002m != f10) {
            this.f3002m = f10;
            for (int i10 = 0; i10 < getChildCount(); i10++) {
                getChildAt(i10).setScaleX(f10);
                getChildAt(i10).setScaleY(f10);
            }
        }
    }

    @Override // android.view.View
    public void setForeground(Drawable drawable) {
        throw new UnsupportedOperationException();
    }

    public void setLayoutScaleX(float f10) {
        if (f10 != this.f3000i) {
            this.f3000i = f10;
            requestLayout();
        }
    }

    public void setLayoutScaleY(float f10) {
        if (f10 != this.f3001l) {
            this.f3001l = f10;
            requestLayout();
        }
    }
}
