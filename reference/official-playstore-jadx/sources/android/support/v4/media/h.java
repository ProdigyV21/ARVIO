package android.support.v4.media;

import android.media.Rating;

/* JADX INFO: loaded from: classes.dex */
public abstract class h {
    public static float a(Rating rating) {
        return rating.getPercentRating();
    }

    public static int b(Rating rating) {
        return rating.getRatingStyle();
    }

    public static float c(Rating rating) {
        return rating.getStarRating();
    }

    public static boolean d(Rating rating) {
        return rating.hasHeart();
    }

    public static boolean e(Rating rating) {
        return rating.isRated();
    }

    public static boolean f(Rating rating) {
        return rating.isThumbUp();
    }

    public static Rating g(boolean z) {
        return Rating.newHeartRating(z);
    }

    public static Rating h(float f10) {
        return Rating.newPercentageRating(f10);
    }

    public static Rating i(int i10, float f10) {
        return Rating.newStarRating(i10, f10);
    }

    public static Rating j(boolean z) {
        return Rating.newThumbRating(z);
    }

    public static Rating k(int i10) {
        return Rating.newUnratedRating(i10);
    }
}
