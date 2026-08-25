package androidx.vectordrawable.graphics.drawable;

import android.graphics.drawable.Animatable2;
import android.graphics.drawable.Drawable;

/* JADX INFO: loaded from: classes3.dex */
public abstract class c {
    Animatable2.AnimationCallback mPlatformCallback;

    public Animatable2.AnimationCallback getPlatformCallback() {
        if (this.mPlatformCallback == null) {
            this.mPlatformCallback = new b(this);
        }
        return this.mPlatformCallback;
    }

    public abstract void onAnimationEnd(Drawable drawable);

    public abstract void onAnimationStart(Drawable drawable);
}
