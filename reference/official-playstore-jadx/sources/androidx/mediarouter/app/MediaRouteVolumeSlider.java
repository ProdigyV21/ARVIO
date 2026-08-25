package androidx.mediarouter.app;

import android.R;
import android.content.Context;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.LayerDrawable;
import android.util.AttributeSet;
import android.util.TypedValue;
import androidx.appcompat.widget.n0;

/* JADX INFO: loaded from: classes3.dex */
class MediaRouteVolumeSlider extends n0 {

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final float f4270l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public Drawable f4271m;

    public MediaRouteVolumeSlider(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        TypedValue typedValue = new TypedValue();
        this.f4270l = context.getTheme().resolveAttribute(R.attr.disabledAlpha, typedValue, true) ? typedValue.getFloat() : 0.5f;
    }

    @Override // androidx.appcompat.widget.n0, android.widget.AbsSeekBar, android.widget.ProgressBar, android.view.View
    public final void drawableStateChanged() {
        super.drawableStateChanged();
        int i10 = isEnabled() ? 255 : (int) (this.f4270l * 255.0f);
        Drawable drawable = this.f4271m;
        PorterDuff.Mode mode = PorterDuff.Mode.SRC_IN;
        drawable.setColorFilter(0, mode);
        this.f4271m.setAlpha(i10);
        Drawable progressDrawable = getProgressDrawable();
        if (progressDrawable instanceof LayerDrawable) {
            LayerDrawable layerDrawable = (LayerDrawable) getProgressDrawable();
            Drawable drawableFindDrawableByLayerId = layerDrawable.findDrawableByLayerId(R.id.progress);
            layerDrawable.findDrawableByLayerId(R.id.background).setColorFilter(0, mode);
            progressDrawable = drawableFindDrawableByLayerId;
        }
        progressDrawable.setColorFilter(0, mode);
        progressDrawable.setAlpha(i10);
    }

    @Override // android.widget.AbsSeekBar
    public final void setThumb(Drawable drawable) {
        this.f4271m = drawable;
        super.setThumb(drawable);
    }
}
