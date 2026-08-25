package android.support.v4.media;

import android.os.Parcel;
import android.os.Parcelable;

/* JADX INFO: loaded from: classes.dex */
public class MediaBrowserCompat$MediaItem implements Parcelable {
    public static final Parcelable.Creator<MediaBrowserCompat$MediaItem> CREATOR = new d(0);

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final int f827i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final MediaDescriptionCompat f828l;

    public MediaBrowserCompat$MediaItem(Parcel parcel) {
        this.f827i = parcel.readInt();
        this.f828l = MediaDescriptionCompat.CREATOR.createFromParcel(parcel);
    }

    @Override // android.os.Parcelable
    public final int describeContents() {
        return 0;
    }

    public final String toString() {
        return "MediaItem{mFlags=" + this.f827i + ", mDescription=" + this.f828l + '}';
    }

    @Override // android.os.Parcelable
    public final void writeToParcel(Parcel parcel, int i10) {
        parcel.writeInt(this.f827i);
        this.f828l.writeToParcel(parcel, i10);
    }
}
