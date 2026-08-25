package androidx.leanback.transition;

import android.graphics.Rect;
import android.view.View;
import android.view.ViewGroup;

/* JADX INFO: loaded from: classes3.dex */
public final class b extends xc.d {

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final /* synthetic */ FadeAndShortSlide f2917c;

    public b(FadeAndShortSlide fadeAndShortSlide) {
        this.f2917c = fadeAndShortSlide;
    }

    @Override // xc.d
    public final float l0(FadeAndShortSlide fadeAndShortSlide, ViewGroup viewGroup, View view, int[] iArr) {
        int iCenterY;
        int height = (view.getHeight() / 2) + iArr[1];
        viewGroup.getLocationOnScreen(iArr);
        Rect epicenter = this.f2917c.getEpicenter();
        if (epicenter == null) {
            iCenterY = (viewGroup.getHeight() / 2) + iArr[1];
        } else {
            iCenterY = epicenter.centerY();
        }
        if (height < iCenterY) {
            return view.getTranslationY() - fadeAndShortSlide.b(viewGroup);
        }
        return fadeAndShortSlide.b(viewGroup) + view.getTranslationY();
    }
}
