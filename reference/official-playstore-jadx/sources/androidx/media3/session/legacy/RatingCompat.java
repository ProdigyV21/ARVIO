package androidx.media3.session.legacy;

import android.media.Rating;
import android.os.Parcel;
import android.os.Parcelable;
import androidx.media3.common.util.Log;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/* JADX INFO: loaded from: classes3.dex */
public final class RatingCompat implements Parcelable {
    public static final Parcelable.Creator<RatingCompat> CREATOR = new Parcelable.Creator<RatingCompat>() { // from class: androidx.media3.session.legacy.RatingCompat.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public RatingCompat createFromParcel(Parcel parcel) {
            return new RatingCompat(parcel.readInt(), parcel.readFloat());
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public RatingCompat[] newArray(int i10) {
            return new RatingCompat[i10];
        }
    };
    public static final int RATING_3_STARS = 3;
    public static final int RATING_4_STARS = 4;
    public static final int RATING_5_STARS = 5;
    public static final int RATING_HEART = 1;
    public static final int RATING_NONE = 0;
    private static final float RATING_NOT_RATED = -1.0f;
    public static final int RATING_PERCENTAGE = 6;
    public static final int RATING_THUMB_UP_DOWN = 2;
    private static final String TAG = "Rating";
    private Object ratingObj;
    private final int ratingStyle;
    private final float ratingValue;

    @Retention(RetentionPolicy.SOURCE)
    public @interface StarStyle {
    }

    @Retention(RetentionPolicy.SOURCE)
    public @interface Style {
    }

    public RatingCompat(int i10, float f10) {
        this.ratingStyle = i10;
        this.ratingValue = f10;
    }

    public static RatingCompat fromRating(Object obj) {
        RatingCompat ratingCompatNewUnratedRating = null;
        if (obj != null) {
            Rating rating = (Rating) obj;
            int ratingStyle = rating.getRatingStyle();
            if (rating.isRated()) {
                switch (ratingStyle) {
                    case 1:
                        ratingCompatNewUnratedRating = newHeartRating(rating.hasHeart());
                        break;
                    case 2:
                        ratingCompatNewUnratedRating = newThumbRating(rating.isThumbUp());
                        break;
                    case 3:
                    case 4:
                    case 5:
                        ratingCompatNewUnratedRating = newStarRating(ratingStyle, rating.getStarRating());
                        break;
                    case 6:
                        ratingCompatNewUnratedRating = newPercentageRating(rating.getPercentRating());
                        break;
                    default:
                        return null;
                }
            } else {
                ratingCompatNewUnratedRating = newUnratedRating(ratingStyle);
            }
            ratingCompatNewUnratedRating.getClass();
            ratingCompatNewUnratedRating.ratingObj = obj;
        }
        return ratingCompatNewUnratedRating;
    }

    public static RatingCompat newHeartRating(boolean z) {
        return new RatingCompat(1, z ? 1.0f : 0.0f);
    }

    public static RatingCompat newPercentageRating(float f10) {
        if (f10 >= 0.0f && f10 <= 100.0f) {
            return new RatingCompat(6, f10);
        }
        Log.e(TAG, "Invalid percentage-based rating value");
        return null;
    }

    public static RatingCompat newStarRating(int i10, float f10) {
        float f11;
        if (i10 == 3) {
            f11 = 3.0f;
        } else if (i10 == 4) {
            f11 = 4.0f;
        } else {
            if (i10 != 5) {
                Log.e(TAG, "Invalid rating style (" + i10 + ") for a star rating");
                return null;
            }
            f11 = 5.0f;
        }
        if (f10 >= 0.0f && f10 <= f11) {
            return new RatingCompat(i10, f10);
        }
        Log.e(TAG, "Trying to set out of range star-based rating");
        return null;
    }

    public static RatingCompat newThumbRating(boolean z) {
        return new RatingCompat(2, z ? 1.0f : 0.0f);
    }

    public static RatingCompat newUnratedRating(int i10) {
        switch (i10) {
            case 1:
            case 2:
            case 3:
            case 4:
            case 5:
            case 6:
                return new RatingCompat(i10, RATING_NOT_RATED);
            default:
                return null;
        }
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return this.ratingStyle;
    }

    public float getPercentRating() {
        return (this.ratingStyle == 6 && isRated()) ? this.ratingValue : RATING_NOT_RATED;
    }

    public Object getRating() {
        if (this.ratingObj == null) {
            if (isRated()) {
                int i10 = this.ratingStyle;
                switch (i10) {
                    case 1:
                        this.ratingObj = Rating.newHeartRating(hasHeart());
                        break;
                    case 2:
                        this.ratingObj = Rating.newThumbRating(isThumbUp());
                        break;
                    case 3:
                    case 4:
                    case 5:
                        this.ratingObj = Rating.newStarRating(i10, getStarRating());
                        break;
                    case 6:
                        this.ratingObj = Rating.newPercentageRating(getPercentRating());
                        break;
                    default:
                        return null;
                }
            } else {
                this.ratingObj = Rating.newUnratedRating(this.ratingStyle);
            }
        }
        return this.ratingObj;
    }

    public int getRatingStyle() {
        return this.ratingStyle;
    }

    public float getStarRating() {
        int i10 = this.ratingStyle;
        return ((i10 == 3 || i10 == 4 || i10 == 5) && isRated()) ? this.ratingValue : RATING_NOT_RATED;
    }

    public boolean hasHeart() {
        return this.ratingStyle == 1 && this.ratingValue == 1.0f;
    }

    public boolean isRated() {
        return this.ratingValue >= 0.0f;
    }

    public boolean isThumbUp() {
        return this.ratingStyle == 2 && this.ratingValue == 1.0f;
    }

    public String toString() {
        StringBuilder sb2 = new StringBuilder("Rating:style=");
        sb2.append(this.ratingStyle);
        sb2.append(" rating=");
        float f10 = this.ratingValue;
        sb2.append(f10 < 0.0f ? "unrated" : String.valueOf(f10));
        return sb2.toString();
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i10) {
        parcel.writeInt(this.ratingStyle);
        parcel.writeFloat(this.ratingValue);
    }
}
