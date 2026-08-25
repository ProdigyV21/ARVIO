package android.support.v4.media.session;

import android.os.Parcel;
import android.os.Parcelable;
import android.support.v4.media.MediaDescriptionCompat;

/* JADX INFO: loaded from: classes.dex */
public final class MediaSessionCompat$QueueItem implements Parcelable {
    public static final Parcelable.Creator<MediaSessionCompat$QueueItem> CREATOR = new u(1);

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final MediaDescriptionCompat f846i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final long f847l;

    public MediaSessionCompat$QueueItem(Parcel parcel) {
        this.f846i = MediaDescriptionCompat.CREATOR.createFromParcel(parcel);
        this.f847l = parcel.readLong();
    }

    @Override // android.os.Parcelable
    public final int describeContents() {
        return 0;
    }

    public final String toString() {
        StringBuilder sb2 = new StringBuilder("MediaSession.QueueItem {Description=");
        sb2.append(this.f846i);
        sb2.append(", Id=");
        return a0.c.j(this.f847l, " }", sb2);
    }

    @Override // android.os.Parcelable
    public final void writeToParcel(Parcel parcel, int i10) {
        this.f846i.writeToParcel(parcel, i10);
        parcel.writeLong(this.f847l);
    }
}
