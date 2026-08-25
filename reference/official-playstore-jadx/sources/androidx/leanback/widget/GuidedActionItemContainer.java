package androidx.leanback.widget;

import android.R;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.View;

/* JADX INFO: loaded from: classes3.dex */
class GuidedActionItemContainer extends k0 {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final boolean f2948i;

    public GuidedActionItemContainer(Context context, AttributeSet attributeSet) {
        super(context, attributeSet, 0);
        new Rect();
        if (context.getApplicationInfo().targetSdkVersion < 23) {
            TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(attributeSet, new int[]{R.attr.foreground});
            Drawable drawable = typedArrayObtainStyledAttributes.getDrawable(0);
            if (drawable != null) {
                setForeground(drawable);
            }
            typedArrayObtainStyledAttributes.recycle();
        }
        this.f2948i = true;
    }

    @Override // android.view.ViewGroup, android.view.ViewParent
    public final View focusSearch(View view, int i10) {
        if (this.f2948i || !ac.b.P(this, view)) {
            return super.focusSearch(view, i10);
        }
        View viewFocusSearch = super.focusSearch(view, i10);
        if (ac.b.P(this, viewFocusSearch)) {
            return viewFocusSearch;
        }
        return null;
    }
}
