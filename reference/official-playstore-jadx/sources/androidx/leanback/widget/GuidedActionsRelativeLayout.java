package androidx.leanback.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import com.arvio.tv.R;

/* JADX INFO: loaded from: classes3.dex */
class GuidedActionsRelativeLayout extends RelativeLayout {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final float f2949i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public boolean f2950l;

    public interface a {
    }

    public GuidedActionsRelativeLayout(Context context, AttributeSet attributeSet) {
        super(context, attributeSet, 0);
        this.f2950l = false;
        TypedArray typedArrayObtainStyledAttributes = context.getTheme().obtainStyledAttributes(k1.a.f19451a);
        float f10 = typedArrayObtainStyledAttributes.getFloat(45, 40.0f);
        typedArrayObtainStyledAttributes.recycle();
        this.f2949i = f10;
    }

    @Override // android.widget.RelativeLayout, android.view.ViewGroup, android.view.View
    public final void onLayout(boolean z, int i10, int i11, int i12, int i13) {
        super.onLayout(z, i10, i11, i12, i13);
        this.f2950l = false;
    }

    @Override // android.widget.RelativeLayout, android.view.View
    public final void onMeasure(int i10, int i11) {
        View viewFindViewById;
        int size = View.MeasureSpec.getSize(i11);
        if (size > 0 && (viewFindViewById = findViewById(R.id.guidedactions_sub_list)) != null) {
            ViewGroup.MarginLayoutParams marginLayoutParams = (ViewGroup.MarginLayoutParams) viewFindViewById.getLayoutParams();
            if (marginLayoutParams.topMargin < 0 && !this.f2950l) {
                this.f2950l = true;
            }
            if (this.f2950l) {
                marginLayoutParams.topMargin = (int) ((this.f2949i * size) / 100.0f);
            }
        }
        super.onMeasure(i10, i11);
    }
}
