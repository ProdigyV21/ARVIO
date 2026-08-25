package androidx.recyclerview.widget;

import android.view.animation.Interpolator;

/* JADX INFO: loaded from: classes3.dex */
public final class i0 implements Interpolator {
    @Override // android.animation.TimeInterpolator
    public final float getInterpolation(float f10) {
        float f11 = f10 - 1.0f;
        return (f11 * f11 * f11 * f11 * f11) + 1.0f;
    }
}
