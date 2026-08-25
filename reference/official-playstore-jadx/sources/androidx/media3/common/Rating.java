package androidx.media3.common;

import a0.c;
import android.os.Bundle;
import androidx.media3.common.util.Util;

/* JADX INFO: loaded from: classes3.dex */
public abstract class Rating {
    static final String FIELD_RATING_TYPE = Util.intToStringMaxRadix(0);
    static final int RATING_TYPE_HEART = 0;
    static final int RATING_TYPE_PERCENTAGE = 1;
    static final int RATING_TYPE_STAR = 2;
    static final int RATING_TYPE_THUMB = 3;
    static final int RATING_TYPE_UNSET = -1;
    static final float RATING_UNSET = -1.0f;

    public static Rating fromBundle(Bundle bundle) {
        int i10 = bundle.getInt(FIELD_RATING_TYPE, -1);
        if (i10 == 0) {
            return HeartRating.fromBundle(bundle);
        }
        if (i10 == 1) {
            return PercentageRating.fromBundle(bundle);
        }
        if (i10 == 2) {
            return StarRating.fromBundle(bundle);
        }
        if (i10 == 3) {
            return ThumbRating.fromBundle(bundle);
        }
        throw new IllegalArgumentException(c.i(i10, "Unknown RatingType: "));
    }

    public abstract boolean isRated();

    public abstract Bundle toBundle();
}
