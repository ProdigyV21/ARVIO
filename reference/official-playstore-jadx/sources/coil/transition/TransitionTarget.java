package coil.transition;

import android.graphics.drawable.Drawable;
import android.view.View;
import coil.target.Target;
import kotlin.Metadata;

/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\bf\u0018\u00002\u00020\u0001R\u0014\u0010\u0002\u001a\u0004\u0018\u00010\u0003X¦\u0004¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005R\u0012\u0010\u0006\u001a\u00020\u0007X¦\u0004¢\u0006\u0006\u001a\u0004\b\b\u0010\tø\u0001\u0000\u0082\u0002\u0006\n\u0004\b!0\u0001¨\u0006\nÀ\u0006\u0003"}, d2 = {"Lcoil/transition/TransitionTarget;", "Lcoil/target/Target;", "drawable", "Landroid/graphics/drawable/Drawable;", "getDrawable", "()Landroid/graphics/drawable/Drawable;", "view", "Landroid/view/View;", "getView", "()Landroid/view/View;", "coil-base_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
public interface TransitionTarget extends Target {

    @Metadata(k = 3, mv = {1, 9, 0}, xi = 48)
    public static final class DefaultImpls {
        @Deprecated
        public static void onError(TransitionTarget transitionTarget, Drawable drawable) {
            r2.a.a(transitionTarget, drawable);
        }

        @Deprecated
        public static void onStart(TransitionTarget transitionTarget, Drawable drawable) {
            r2.a.b(transitionTarget, drawable);
        }

        @Deprecated
        public static void onSuccess(TransitionTarget transitionTarget, Drawable drawable) {
            r2.a.c(transitionTarget, drawable);
        }
    }

    Drawable getDrawable();

    View getView();
}
