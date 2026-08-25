package androidx.leanback.widget;

import android.R;
import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.Checkable;
import android.widget.ImageView;

/* JADX INFO: loaded from: classes3.dex */
class CheckableImageView extends ImageView implements Checkable {

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public static final int[] f2939l = {R.attr.state_checked};

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public boolean f2940i;

    public CheckableImageView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet, 0);
    }

    @Override // android.widget.Checkable
    public final boolean isChecked() {
        return this.f2940i;
    }

    @Override // android.widget.ImageView, android.view.View
    public final int[] onCreateDrawableState(int i10) {
        int[] iArrOnCreateDrawableState = super.onCreateDrawableState(i10 + 1);
        if (this.f2940i) {
            View.mergeDrawableStates(iArrOnCreateDrawableState, f2939l);
        }
        return iArrOnCreateDrawableState;
    }

    @Override // android.widget.Checkable
    public final void setChecked(boolean z) {
        if (this.f2940i != z) {
            this.f2940i = z;
            refreshDrawableState();
        }
    }

    @Override // android.widget.Checkable
    public final void toggle() {
        setChecked(!this.f2940i);
    }
}
