package androidx.leanback.transition;

import android.content.Context;
import android.transition.Slide;
import android.util.AttributeSet;

/* JADX INFO: loaded from: classes3.dex */
public class SlideNoPropagation extends Slide {
    public SlideNoPropagation(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    @Override // android.transition.Slide
    public final void setSlideEdge(int i10) {
        super.setSlideEdge(i10);
        setPropagation(null);
    }
}
