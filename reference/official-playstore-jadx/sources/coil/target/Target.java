package coil.target;

import android.graphics.drawable.Drawable;
import kotlin.Metadata;
import r2.a;

/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\bf\u0018\u00002\u00020\u0001J\u0019\u0010\u0005\u001a\u00020\u00042\b\u0010\u0003\u001a\u0004\u0018\u00010\u0002H\u0017¢\u0006\u0004\b\u0005\u0010\u0006J\u0019\u0010\b\u001a\u00020\u00042\b\u0010\u0007\u001a\u0004\u0018\u00010\u0002H\u0017¢\u0006\u0004\b\b\u0010\u0006J\u0017\u0010\n\u001a\u00020\u00042\u0006\u0010\t\u001a\u00020\u0002H\u0017¢\u0006\u0004\b\n\u0010\u0006ø\u0001\u0000\u0082\u0002\u0006\n\u0004\b!0\u0001¨\u0006\u000bÀ\u0006\u0003"}, d2 = {"Lcoil/target/Target;", "", "Landroid/graphics/drawable/Drawable;", "placeholder", "Lx6/t0;", "onStart", "(Landroid/graphics/drawable/Drawable;)V", "error", "onError", "result", "onSuccess", "coil-base_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
public interface Target {

    @Metadata(k = 3, mv = {1, 9, 0}, xi = 48)
    public static final class DefaultImpls {
        @Deprecated
        public static void onError(Target target, Drawable drawable) {
            a.a(target, drawable);
        }

        @Deprecated
        public static void onStart(Target target, Drawable drawable) {
            a.b(target, drawable);
        }

        @Deprecated
        public static void onSuccess(Target target, Drawable drawable) {
            a.c(target, drawable);
        }
    }

    void onError(Drawable error);

    void onStart(Drawable placeholder);

    void onSuccess(Drawable result);
}
