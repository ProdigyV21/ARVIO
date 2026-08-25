package android.support.v4.media;

import android.media.MediaMetadata;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import android.support.v4.media.session.v;

/* JADX INFO: loaded from: classes.dex */
public final class MediaMetadataCompat implements Parcelable {
    public static final Parcelable.Creator<MediaMetadataCompat> CREATOR;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public static final t.e f838m;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final Bundle f839i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public MediaMetadata f840l;

    static {
        t.e eVar = new t.e(0);
        f838m = eVar;
        eVar.put(androidx.media3.session.legacy.MediaMetadataCompat.METADATA_KEY_TITLE, 1);
        eVar.put(androidx.media3.session.legacy.MediaMetadataCompat.METADATA_KEY_ARTIST, 1);
        eVar.put(androidx.media3.session.legacy.MediaMetadataCompat.METADATA_KEY_DURATION, 0);
        eVar.put(androidx.media3.session.legacy.MediaMetadataCompat.METADATA_KEY_ALBUM, 1);
        eVar.put(androidx.media3.session.legacy.MediaMetadataCompat.METADATA_KEY_AUTHOR, 1);
        eVar.put(androidx.media3.session.legacy.MediaMetadataCompat.METADATA_KEY_WRITER, 1);
        eVar.put(androidx.media3.session.legacy.MediaMetadataCompat.METADATA_KEY_COMPOSER, 1);
        eVar.put(androidx.media3.session.legacy.MediaMetadataCompat.METADATA_KEY_COMPILATION, 1);
        eVar.put(androidx.media3.session.legacy.MediaMetadataCompat.METADATA_KEY_DATE, 1);
        eVar.put(androidx.media3.session.legacy.MediaMetadataCompat.METADATA_KEY_YEAR, 0);
        eVar.put(androidx.media3.session.legacy.MediaMetadataCompat.METADATA_KEY_GENRE, 1);
        eVar.put(androidx.media3.session.legacy.MediaMetadataCompat.METADATA_KEY_TRACK_NUMBER, 0);
        eVar.put(androidx.media3.session.legacy.MediaMetadataCompat.METADATA_KEY_NUM_TRACKS, 0);
        eVar.put(androidx.media3.session.legacy.MediaMetadataCompat.METADATA_KEY_DISC_NUMBER, 0);
        eVar.put(androidx.media3.session.legacy.MediaMetadataCompat.METADATA_KEY_ALBUM_ARTIST, 1);
        eVar.put(androidx.media3.session.legacy.MediaMetadataCompat.METADATA_KEY_ART, 2);
        eVar.put(androidx.media3.session.legacy.MediaMetadataCompat.METADATA_KEY_ART_URI, 1);
        eVar.put(androidx.media3.session.legacy.MediaMetadataCompat.METADATA_KEY_ALBUM_ART, 2);
        eVar.put(androidx.media3.session.legacy.MediaMetadataCompat.METADATA_KEY_ALBUM_ART_URI, 1);
        eVar.put(androidx.media3.session.legacy.MediaMetadataCompat.METADATA_KEY_USER_RATING, 3);
        eVar.put(androidx.media3.session.legacy.MediaMetadataCompat.METADATA_KEY_RATING, 3);
        eVar.put(androidx.media3.session.legacy.MediaMetadataCompat.METADATA_KEY_DISPLAY_TITLE, 1);
        eVar.put(androidx.media3.session.legacy.MediaMetadataCompat.METADATA_KEY_DISPLAY_SUBTITLE, 1);
        eVar.put(androidx.media3.session.legacy.MediaMetadataCompat.METADATA_KEY_DISPLAY_DESCRIPTION, 1);
        eVar.put(androidx.media3.session.legacy.MediaMetadataCompat.METADATA_KEY_DISPLAY_ICON, 2);
        eVar.put(androidx.media3.session.legacy.MediaMetadataCompat.METADATA_KEY_DISPLAY_ICON_URI, 1);
        eVar.put(androidx.media3.session.legacy.MediaMetadataCompat.METADATA_KEY_MEDIA_ID, 1);
        eVar.put(androidx.media3.session.legacy.MediaMetadataCompat.METADATA_KEY_BT_FOLDER_TYPE, 0);
        eVar.put(androidx.media3.session.legacy.MediaMetadataCompat.METADATA_KEY_MEDIA_URI, 1);
        eVar.put("android.media.metadata.ADVERTISEMENT", 0);
        eVar.put(androidx.media3.session.legacy.MediaMetadataCompat.METADATA_KEY_DOWNLOAD_STATUS, 0);
        CREATOR = new d(2);
    }

    public MediaMetadataCompat(Bundle bundle) {
        Bundle bundle2 = new Bundle(bundle);
        this.f839i = bundle2;
        v.a(bundle2);
    }

    @Override // android.os.Parcelable
    public final int describeContents() {
        return 0;
    }

    @Override // android.os.Parcelable
    public final void writeToParcel(Parcel parcel, int i10) {
        parcel.writeBundle(this.f839i);
    }

    public MediaMetadataCompat(Parcel parcel) {
        this.f839i = parcel.readBundle(v.class.getClassLoader());
    }
}
