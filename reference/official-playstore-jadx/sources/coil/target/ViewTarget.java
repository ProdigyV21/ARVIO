package coil.target;

import android.graphics.drawable.Drawable;
import android.view.View;
import kotlin.Metadata;
import r2.a;

/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\bf\u0018\u0000*\b\b\u0000\u0010\u0001*\u00020\u00022\u00020\u0003R\u0012\u0010\u0004\u001a\u00028\u0000X¦\u0004¢\u0006\u0006\u001a\u0004\b\u0005\u0010\u0006ø\u0001\u0000\u0082\u0002\u0006\n\u0004\b!0\u0001¨\u0006\u0007À\u0006\u0003"}, d2 = {"Lcoil/target/ViewTarget;", "T", "Landroid/view/View;", "Lcoil/target/Target;", "view", "getView", "()Landroid/view/View;", "coil-base_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
public interface ViewTarget<T extends View> extends Target {

    @Metadata(k = 3, mv = {1, 9, 0}, xi = 48)
    public static final class DefaultImpls {
        @Deprecated
        public static <T extends View> void onError(ViewTarget<T> viewTarget, Drawable drawable) {
            a.a(viewTarget, drawable);
        }

        @Deprecated
        public static <T extends View> void onStart(ViewTarget<T> viewTarget, Drawable drawable) {
            a.b(viewTarget, drawable);
        }

        @Deprecated
        public static <T extends View> void onSuccess(ViewTarget<T> viewTarget, Drawable drawable) {
            a.c(viewTarget, drawable);
        }
    }

    T getView();
}
