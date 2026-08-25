package android.support.v4.media;

import android.os.Parcel;
import android.os.Parcelable;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/* JADX INFO: loaded from: classes.dex */
public final class RatingCompat implements Parcelable {
    public static final Parcelable.Creator<RatingCompat> CREATOR = new d(3);

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final int f841i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final float f842l;

    @Retention(RetentionPolicy.SOURCE)
    public @interface a {
    }

    @Retention(RetentionPolicy.SOURCE)
    public @interface b {
    }

    public RatingCompat(int i10, float f10) {
        this.f841i = i10;
        this.f842l = f10;
    }

    @Override // android.os.Parcelable
    public final int describeContents() {
        return this.f841i;
    }

    public final String toString() {
        StringBuilder sb2 = new StringBuilder("Rating:style=");
        sb2.append(this.f841i);
        sb2.append(" rating=");
        float f10 = this.f842l;
        sb2.append(f10 < 0.0f ? "unrated" : String.valueOf(f10));
        return sb2.toString();
    }

    @Override // android.os.Parcelable
    public final void writeToParcel(Parcel parcel, int i10) {
        parcel.writeInt(this.f841i);
        parcel.writeFloat(this.f842l);
    }
}
