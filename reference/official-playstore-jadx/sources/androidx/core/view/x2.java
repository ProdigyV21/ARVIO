package androidx.core.view;

import android.graphics.Insets;
import android.view.WindowInsetsAnimation;
import android.view.animation.Interpolator;

/* JADX INFO: loaded from: classes3.dex */
public abstract /* synthetic */ class x2 {
    public static /* synthetic */ WindowInsetsAnimation.Bounds a(Insets insets, Insets insets2) {
        return new WindowInsetsAnimation.Bounds(insets, insets2);
    }

    public static /* synthetic */ WindowInsetsAnimation b(int i10, Interpolator interpolator, long j10) {
        return new WindowInsetsAnimation(i10, interpolator, j10);
    }

    public static /* synthetic */ void c() {
    }
}
