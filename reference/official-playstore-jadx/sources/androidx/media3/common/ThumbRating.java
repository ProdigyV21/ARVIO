package androidx.media3.common;

import android.os.Bundle;
import androidx.media3.common.util.Util;
import j$.util.Objects;

/* JADX INFO: loaded from: classes3.dex */
public final class ThumbRating extends Rating {
    private static final int TYPE = 3;
    private final boolean isThumbsUp;
    private final boolean rated;
    private static final String FIELD_RATED = Util.intToStringMaxRadix(1);
    private static final String FIELD_IS_THUMBS_UP = Util.intToStringMaxRadix(2);

    public ThumbRating() {
        this.rated = false;
        this.isThumbsUp = false;
    }

    public static ThumbRating fromBundle(Bundle bundle) {
        ac.b.j(bundle.getInt(Rating.FIELD_RATING_TYPE, -1) == 3);
        return bundle.getBoolean(FIELD_RATED, false) ? new ThumbRating(bundle.getBoolean(FIELD_IS_THUMBS_UP, false)) : new ThumbRating();
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof ThumbRating)) {
            return false;
        }
        ThumbRating thumbRating = (ThumbRating) obj;
        return this.isThumbsUp == thumbRating.isThumbsUp && this.rated == thumbRating.rated;
    }

    public int hashCode() {
        return Objects.hash(Boolean.valueOf(this.rated), Boolean.valueOf(this.isThumbsUp));
    }

    @Override // androidx.media3.common.Rating
    public boolean isRated() {
        return this.rated;
    }

    public boolean isThumbsUp() {
        return this.isThumbsUp;
    }

    @Override // androidx.media3.common.Rating
    public Bundle toBundle() {
        Bundle bundle = new Bundle();
        bundle.putInt(Rating.FIELD_RATING_TYPE, 3);
        bundle.putBoolean(FIELD_RATED, this.rated);
        bundle.putBoolean(FIELD_IS_THUMBS_UP, this.isThumbsUp);
        return bundle;
    }

    public ThumbRating(boolean z) {
        this.rated = true;
        this.isThumbsUp = z;
    }
}
