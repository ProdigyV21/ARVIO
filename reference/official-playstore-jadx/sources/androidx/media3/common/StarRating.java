package androidx.media3.common;

import android.os.Bundle;
import androidx.media3.common.util.Util;
import j$.util.Objects;

/* JADX INFO: loaded from: classes3.dex */
public final class StarRating extends Rating {
    private static final String FIELD_MAX_STARS = Util.intToStringMaxRadix(1);
    private static final String FIELD_STAR_RATING = Util.intToStringMaxRadix(2);
    private static final int MAX_STARS_DEFAULT = 5;
    private static final int TYPE = 2;
    private final int maxStars;
    private final float starRating;

    public StarRating(int i10) {
        ac.b.k(i10 > 0, "maxStars must be a positive integer");
        this.maxStars = i10;
        this.starRating = -1.0f;
    }

    public static StarRating fromBundle(Bundle bundle) {
        ac.b.j(bundle.getInt(Rating.FIELD_RATING_TYPE, -1) == 2);
        int i10 = bundle.getInt(FIELD_MAX_STARS, 5);
        float f10 = bundle.getFloat(FIELD_STAR_RATING, -1.0f);
        return f10 == -1.0f ? new StarRating(i10) : new StarRating(i10, f10);
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof StarRating)) {
            return false;
        }
        StarRating starRating = (StarRating) obj;
        return this.maxStars == starRating.maxStars && this.starRating == starRating.starRating;
    }

    public int getMaxStars() {
        return this.maxStars;
    }

    public float getStarRating() {
        return this.starRating;
    }

    public int hashCode() {
        return Objects.hash(Integer.valueOf(this.maxStars), Float.valueOf(this.starRating));
    }

    @Override // androidx.media3.common.Rating
    public boolean isRated() {
        return this.starRating != -1.0f;
    }

    @Override // androidx.media3.common.Rating
    public Bundle toBundle() {
        Bundle bundle = new Bundle();
        bundle.putInt(Rating.FIELD_RATING_TYPE, 2);
        bundle.putInt(FIELD_MAX_STARS, this.maxStars);
        bundle.putFloat(FIELD_STAR_RATING, this.starRating);
        return bundle;
    }

    public StarRating(int i10, float f10) {
        boolean z = false;
        ac.b.k(i10 > 0, "maxStars must be a positive integer");
        if (f10 >= 0.0f && f10 <= i10) {
            z = true;
        }
        ac.b.k(z, "starRating is out of range [0, maxStars]");
        this.maxStars = i10;
        this.starRating = f10;
    }
}
