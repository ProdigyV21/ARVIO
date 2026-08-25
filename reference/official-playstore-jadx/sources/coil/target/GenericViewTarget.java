package coil.target;

import android.graphics.drawable.Animatable;
import android.graphics.drawable.Drawable;
import android.view.View;
import androidx.lifecycle.DefaultLifecycleObserver;
import androidx.lifecycle.e;
import androidx.lifecycle.y;
import coil.transition.TransitionTarget;
import kotlin.Metadata;

/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u00008\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0002\b\u0006\b&\u0018\u0000*\b\b\u0000\u0010\u0002*\u00020\u00012\b\u0012\u0004\u0012\u00028\u00000\u00032\u00020\u00042\u00020\u0005B\u0007¢\u0006\u0004\b\u0006\u0010\u0007J\u0019\u0010\u000b\u001a\u00020\n2\b\u0010\t\u001a\u0004\u0018\u00010\bH\u0016¢\u0006\u0004\b\u000b\u0010\fJ\u0019\u0010\u000e\u001a\u00020\n2\b\u0010\r\u001a\u0004\u0018\u00010\bH\u0016¢\u0006\u0004\b\u000e\u0010\fJ\u0017\u0010\u0010\u001a\u00020\n2\u0006\u0010\u000f\u001a\u00020\bH\u0016¢\u0006\u0004\b\u0010\u0010\fJ\u0017\u0010\u000b\u001a\u00020\n2\u0006\u0010\u0012\u001a\u00020\u0011H\u0016¢\u0006\u0004\b\u000b\u0010\u0013J\u0017\u0010\u0014\u001a\u00020\n2\u0006\u0010\u0012\u001a\u00020\u0011H\u0016¢\u0006\u0004\b\u0014\u0010\u0013J\u0019\u0010\u0016\u001a\u00020\n2\b\u0010\u0015\u001a\u0004\u0018\u00010\bH\u0004¢\u0006\u0004\b\u0016\u0010\fJ\u000f\u0010\u0017\u001a\u00020\nH\u0004¢\u0006\u0004\b\u0017\u0010\u0007R\u0016\u0010\u0019\u001a\u00020\u00188\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b\u0019\u0010\u001aR\u001e\u0010\u0015\u001a\u0004\u0018\u00010\b8&@&X¦\u000e¢\u0006\f\u001a\u0004\b\u001b\u0010\u001c\"\u0004\b\u001d\u0010\f¨\u0006\u001e"}, d2 = {"Lcoil/target/GenericViewTarget;", "Landroid/view/View;", "T", "Lcoil/target/ViewTarget;", "Lcoil/transition/TransitionTarget;", "Landroidx/lifecycle/DefaultLifecycleObserver;", "<init>", "()V", "Landroid/graphics/drawable/Drawable;", "placeholder", "Lx6/t0;", "onStart", "(Landroid/graphics/drawable/Drawable;)V", "error", "onError", "result", "onSuccess", "Landroidx/lifecycle/y;", "owner", "(Landroidx/lifecycle/y;)V", "onStop", "drawable", "updateDrawable", "updateAnimation", "", "isStarted", "Z", "getDrawable", "()Landroid/graphics/drawable/Drawable;", "setDrawable", "coil-base_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
public abstract class GenericViewTarget<T extends View> implements ViewTarget<T>, TransitionTarget, DefaultLifecycleObserver {
    private boolean isStarted;

    @Override // coil.transition.TransitionTarget
    public abstract Drawable getDrawable();

    @Override // androidx.lifecycle.DefaultLifecycleObserver
    public final /* synthetic */ void onCreate(y yVar) {
        e.a(this, yVar);
    }

    @Override // androidx.lifecycle.DefaultLifecycleObserver
    public final /* synthetic */ void onDestroy(y yVar) {
        e.b(this, yVar);
    }

    @Override // coil.target.Target
    public void onError(Drawable error) {
        updateDrawable(error);
    }

    @Override // androidx.lifecycle.DefaultLifecycleObserver
    public final /* synthetic */ void onPause(y yVar) {
        e.c(this, yVar);
    }

    @Override // androidx.lifecycle.DefaultLifecycleObserver
    public final /* synthetic */ void onResume(y yVar) {
        e.d(this, yVar);
    }

    @Override // coil.target.Target
    public void onStart(Drawable placeholder) {
        updateDrawable(placeholder);
    }

    @Override // androidx.lifecycle.DefaultLifecycleObserver
    public void onStop(y owner) {
        this.isStarted = false;
        updateAnimation();
    }

    @Override // coil.target.Target
    public void onSuccess(Drawable result) {
        updateDrawable(result);
    }

    public abstract void setDrawable(Drawable drawable);

    public final void updateAnimation() {
        Object drawable = getDrawable();
        Animatable animatable = drawable instanceof Animatable ? (Animatable) drawable : null;
        if (animatable == null) {
            return;
        }
        if (this.isStarted) {
            animatable.start();
        } else {
            animatable.stop();
        }
    }

    public final void updateDrawable(Drawable drawable) {
        Object drawable2 = getDrawable();
        Animatable animatable = drawable2 instanceof Animatable ? (Animatable) drawable2 : null;
        if (animatable != null) {
            animatable.stop();
        }
        setDrawable(drawable);
        updateAnimation();
    }

    @Override // androidx.lifecycle.DefaultLifecycleObserver
    public void onStart(y owner) {
        this.isStarted = true;
        updateAnimation();
    }
}
