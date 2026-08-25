package androidx.media3.session.legacy;

import a0.c;
import android.graphics.Bitmap;
import android.media.MediaMetadata;
import android.media.Rating;
import android.net.Uri;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import androidx.media3.common.util.Log;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import t.e;

/* JADX INFO: loaded from: classes3.dex */
public final class MediaMetadataCompat implements Parcelable {
    public static final Parcelable.Creator<MediaMetadataCompat> CREATOR;
    static final e METADATA_KEYS_TYPE;
    public static final String METADATA_KEY_ADVERTISEMENT = "android.media.metadata.ADVERTISEMENT";
    public static final String METADATA_KEY_ALBUM = "android.media.metadata.ALBUM";
    public static final String METADATA_KEY_ALBUM_ART = "android.media.metadata.ALBUM_ART";
    public static final String METADATA_KEY_ALBUM_ARTIST = "android.media.metadata.ALBUM_ARTIST";
    public static final String METADATA_KEY_ALBUM_ART_URI = "android.media.metadata.ALBUM_ART_URI";
    public static final String METADATA_KEY_ART = "android.media.metadata.ART";
    public static final String METADATA_KEY_ARTIST = "android.media.metadata.ARTIST";
    public static final String METADATA_KEY_ART_URI = "android.media.metadata.ART_URI";
    public static final String METADATA_KEY_AUTHOR = "android.media.metadata.AUTHOR";
    public static final String METADATA_KEY_BT_FOLDER_TYPE = "android.media.metadata.BT_FOLDER_TYPE";
    public static final String METADATA_KEY_COMPILATION = "android.media.metadata.COMPILATION";
    public static final String METADATA_KEY_COMPOSER = "android.media.metadata.COMPOSER";
    public static final String METADATA_KEY_DATE = "android.media.metadata.DATE";
    public static final String METADATA_KEY_DISC_NUMBER = "android.media.metadata.DISC_NUMBER";
    public static final String METADATA_KEY_DISPLAY_DESCRIPTION = "android.media.metadata.DISPLAY_DESCRIPTION";
    public static final String METADATA_KEY_DISPLAY_ICON = "android.media.metadata.DISPLAY_ICON";
    public static final String METADATA_KEY_DISPLAY_ICON_URI = "android.media.metadata.DISPLAY_ICON_URI";
    public static final String METADATA_KEY_DISPLAY_SUBTITLE = "android.media.metadata.DISPLAY_SUBTITLE";
    public static final String METADATA_KEY_DISPLAY_TITLE = "android.media.metadata.DISPLAY_TITLE";
    public static final String METADATA_KEY_DOWNLOAD_STATUS = "android.media.metadata.DOWNLOAD_STATUS";
    public static final String METADATA_KEY_DURATION = "android.media.metadata.DURATION";
    public static final String METADATA_KEY_GENRE = "android.media.metadata.GENRE";
    public static final String METADATA_KEY_MEDIA_ID = "android.media.metadata.MEDIA_ID";
    public static final String METADATA_KEY_MEDIA_URI = "android.media.metadata.MEDIA_URI";
    public static final String METADATA_KEY_NUM_TRACKS = "android.media.metadata.NUM_TRACKS";
    public static final String METADATA_KEY_RATING = "android.media.metadata.RATING";
    public static final String METADATA_KEY_TITLE = "android.media.metadata.TITLE";
    public static final String METADATA_KEY_TRACK_NUMBER = "android.media.metadata.TRACK_NUMBER";
    public static final String METADATA_KEY_USER_RATING = "android.media.metadata.USER_RATING";
    public static final String METADATA_KEY_WRITER = "android.media.metadata.WRITER";
    public static final String METADATA_KEY_YEAR = "android.media.metadata.YEAR";
    static final int METADATA_TYPE_BITMAP = 2;
    static final int METADATA_TYPE_LONG = 0;
    static final int METADATA_TYPE_RATING = 3;
    static final int METADATA_TYPE_TEXT = 1;
    public static final String[] PREFERRED_DESCRIPTION_ORDER;
    private static final String TAG = "MediaMetadata";
    private final Bundle bundle;
    private byte[] compressedArtworkData;
    private MediaMetadata metadataFwk;

    public static final class Builder {
        private final Bundle bundle = new Bundle();

        public MediaMetadataCompat build() {
            return new MediaMetadataCompat(this.bundle);
        }

        public Builder putBitmap(String str, Bitmap bitmap) {
            Integer num = (Integer) MediaMetadataCompat.METADATA_KEYS_TYPE.get(str);
            if (num != null && num.intValue() != 2) {
                throw new IllegalArgumentException(c.l("The ", str, " key cannot be used to put a Bitmap"));
            }
            this.bundle.putParcelable(str, bitmap);
            return this;
        }

        public Builder putLong(String str, long j10) {
            Integer num = (Integer) MediaMetadataCompat.METADATA_KEYS_TYPE.get(str);
            if (num != null && num.intValue() != 0) {
                throw new IllegalArgumentException(c.l("The ", str, " key cannot be used to put a long"));
            }
            this.bundle.putLong(str, j10);
            return this;
        }

        public Builder putRating(String str, RatingCompat ratingCompat) {
            Integer num = (Integer) MediaMetadataCompat.METADATA_KEYS_TYPE.get(str);
            if (num != null && num.intValue() != 3) {
                throw new IllegalArgumentException(c.l("The ", str, " key cannot be used to put a Rating"));
            }
            this.bundle.putParcelable(str, (Parcelable) ratingCompat.getRating());
            return this;
        }

        public Builder putString(String str, String str2) {
            Integer num = (Integer) MediaMetadataCompat.METADATA_KEYS_TYPE.get(str);
            if (num != null && num.intValue() != 1) {
                throw new IllegalArgumentException(c.l("The ", str, " key cannot be used to put a String"));
            }
            this.bundle.putCharSequence(str, str2);
            return this;
        }

        public Builder putText(String str, CharSequence charSequence) {
            Integer num = (Integer) MediaMetadataCompat.METADATA_KEYS_TYPE.get(str);
            if (num != null && num.intValue() != 1) {
                throw new IllegalArgumentException(c.l("The ", str, " key cannot be used to put a CharSequence"));
            }
            this.bundle.putCharSequence(str, charSequence);
            return this;
        }
    }

    static {
        e eVar = new e(0);
        METADATA_KEYS_TYPE = eVar;
        eVar.put(METADATA_KEY_TITLE, 1);
        eVar.put(METADATA_KEY_ARTIST, 1);
        eVar.put(METADATA_KEY_DURATION, 0);
        eVar.put(METADATA_KEY_ALBUM, 1);
        eVar.put(METADATA_KEY_AUTHOR, 1);
        eVar.put(METADATA_KEY_WRITER, 1);
        eVar.put(METADATA_KEY_COMPOSER, 1);
        eVar.put(METADATA_KEY_COMPILATION, 1);
        eVar.put(METADATA_KEY_DATE, 1);
        eVar.put(METADATA_KEY_YEAR, 0);
        eVar.put(METADATA_KEY_GENRE, 1);
        eVar.put(METADATA_KEY_TRACK_NUMBER, 0);
        eVar.put(METADATA_KEY_NUM_TRACKS, 0);
        eVar.put(METADATA_KEY_DISC_NUMBER, 0);
        eVar.put(METADATA_KEY_ALBUM_ARTIST, 1);
        eVar.put(METADATA_KEY_ART, 2);
        eVar.put(METADATA_KEY_ART_URI, 1);
        eVar.put(METADATA_KEY_ALBUM_ART, 2);
        eVar.put(METADATA_KEY_ALBUM_ART_URI, 1);
        eVar.put(METADATA_KEY_USER_RATING, 3);
        eVar.put(METADATA_KEY_RATING, 3);
        eVar.put(METADATA_KEY_DISPLAY_TITLE, 1);
        eVar.put(METADATA_KEY_DISPLAY_SUBTITLE, 1);
        eVar.put(METADATA_KEY_DISPLAY_DESCRIPTION, 1);
        eVar.put(METADATA_KEY_DISPLAY_ICON, 2);
        eVar.put(METADATA_KEY_DISPLAY_ICON_URI, 1);
        eVar.put(METADATA_KEY_MEDIA_ID, 1);
        eVar.put(METADATA_KEY_BT_FOLDER_TYPE, 0);
        eVar.put(METADATA_KEY_MEDIA_URI, 1);
        eVar.put("android.media.metadata.ADVERTISEMENT", 0);
        eVar.put(METADATA_KEY_DOWNLOAD_STATUS, 0);
        PREFERRED_DESCRIPTION_ORDER = new String[]{METADATA_KEY_TITLE, METADATA_KEY_ARTIST, METADATA_KEY_ALBUM, METADATA_KEY_ALBUM_ARTIST, METADATA_KEY_WRITER, METADATA_KEY_AUTHOR, METADATA_KEY_COMPOSER, METADATA_KEY_DISPLAY_SUBTITLE, METADATA_KEY_DISPLAY_DESCRIPTION};
        CREATOR = new Parcelable.Creator<MediaMetadataCompat>() { // from class: androidx.media3.session.legacy.MediaMetadataCompat.1
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // android.os.Parcelable.Creator
            public MediaMetadataCompat createFromParcel(Parcel parcel) {
                return new MediaMetadataCompat(parcel);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // android.os.Parcelable.Creator
            public MediaMetadataCompat[] newArray(int i10) {
                return new MediaMetadataCompat[i10];
            }
        };
    }

    public MediaMetadataCompat(Bundle bundle) {
        Bundle bundle2 = new Bundle(bundle);
        this.bundle = bundle2;
        MediaSessionCompat.ensureClassLoader(bundle2);
    }

    public static MediaMetadataCompat fromMediaMetadata(Object obj) {
        if (obj == null) {
            return null;
        }
        Parcel parcelObtain = Parcel.obtain();
        MediaMetadata mediaMetadata = (MediaMetadata) obj;
        mediaMetadata.writeToParcel(parcelObtain, 0);
        parcelObtain.setDataPosition(0);
        MediaMetadataCompat mediaMetadataCompatCreateFromParcel = CREATOR.createFromParcel(parcelObtain);
        parcelObtain.recycle();
        mediaMetadataCompatCreateFromParcel.metadataFwk = mediaMetadata;
        return mediaMetadataCompatCreateFromParcel;
    }

    private Bitmap getBitmap(String str) {
        try {
            return (Bitmap) this.bundle.getParcelable(str);
        } catch (Exception e5) {
            Log.w(TAG, "Failed to retrieve a key as Bitmap.", e5);
            return null;
        }
    }

    private Bitmap getFirstBitmap(String... strArr) {
        for (String str : strArr) {
            if (containsKey(str)) {
                return getBitmap(str);
            }
        }
        return null;
    }

    private String getFirstString(String... strArr) {
        for (String str : strArr) {
            if (containsKey(str)) {
                return getString(str);
            }
        }
        return null;
    }

    private Bitmap getMostRelevantArtworkBitmap() {
        return getFirstBitmap(METADATA_KEY_DISPLAY_ICON, METADATA_KEY_ALBUM_ART, METADATA_KEY_ART);
    }

    public boolean containsKey(String str) {
        return this.bundle.containsKey(str);
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public Bundle getBundle() {
        return new Bundle(this.bundle);
    }

    public long getLong(String str) {
        return this.bundle.getLong(str, 0L);
    }

    public MediaMetadata getMediaMetadata() {
        if (this.metadataFwk == null) {
            MediaMetadata.Builder builder = new MediaMetadata.Builder();
            for (String str : this.bundle.keySet()) {
                Integer num = (Integer) METADATA_KEYS_TYPE.get(str);
                if (num == null) {
                    num = -1;
                }
                int iIntValue = num.intValue();
                if (iIntValue == 0) {
                    builder.putLong(str, this.bundle.getLong(str));
                } else if (iIntValue == 1) {
                    builder.putText(str, this.bundle.getCharSequence(str));
                } else if (iIntValue == 2) {
                    builder.putBitmap(str, (Bitmap) this.bundle.getParcelable(str));
                } else if (iIntValue != 3) {
                    Object obj = this.bundle.get(str);
                    if (obj == null || (obj instanceof CharSequence)) {
                        builder.putText(str, (CharSequence) obj);
                    } else if (obj instanceof Long) {
                        builder.putLong(str, ((Long) obj).longValue());
                    }
                } else {
                    builder.putRating(str, (Rating) this.bundle.getParcelable(str));
                }
            }
            this.metadataFwk = builder.build();
        }
        return this.metadataFwk;
    }

    public byte[] getMostRelevantArtworkBitmapData() {
        Bitmap mostRelevantArtworkBitmap = getMostRelevantArtworkBitmap();
        if (mostRelevantArtworkBitmap == null) {
            return null;
        }
        if (this.compressedArtworkData == null) {
            try {
                ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                try {
                    mostRelevantArtworkBitmap.compress(Bitmap.CompressFormat.PNG, 0, byteArrayOutputStream);
                    this.compressedArtworkData = byteArrayOutputStream.toByteArray();
                    byteArrayOutputStream.close();
                } finally {
                }
            } catch (IOException e5) {
                Log.w(TAG, "Failed to compress MediaMetadataCompat artwork", e5);
            }
        }
        return this.compressedArtworkData;
    }

    public Uri getMostRelevantArtworkUri() {
        String firstString = getFirstString(METADATA_KEY_DISPLAY_ICON_URI, METADATA_KEY_ALBUM_ART_URI, METADATA_KEY_ART_URI);
        if (firstString != null) {
            return Uri.parse(firstString);
        }
        return null;
    }

    public RatingCompat getRating(String str) {
        try {
            return RatingCompat.fromRating(this.bundle.getParcelable(str));
        } catch (Exception e5) {
            Log.w(TAG, "Failed to retrieve a key as Rating.", e5);
            return null;
        }
    }

    public String getString(String str) {
        CharSequence charSequence = this.bundle.getCharSequence(str);
        if (charSequence != null) {
            return charSequence.toString();
        }
        return null;
    }

    public CharSequence getText(String str) {
        return this.bundle.getCharSequence(str);
    }

    public void preserveArtworkBitmapData(MediaMetadataCompat mediaMetadataCompat) {
        Bitmap mostRelevantArtworkBitmap;
        Bitmap mostRelevantArtworkBitmap2;
        if (mediaMetadataCompat.compressedArtworkData == null || (mostRelevantArtworkBitmap = getMostRelevantArtworkBitmap()) == null || (mostRelevantArtworkBitmap2 = mediaMetadataCompat.getMostRelevantArtworkBitmap()) == null || !mostRelevantArtworkBitmap.sameAs(mostRelevantArtworkBitmap2)) {
            return;
        }
        this.compressedArtworkData = mediaMetadataCompat.compressedArtworkData;
    }

    public int size() {
        return this.bundle.size();
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i10) {
        parcel.writeBundle(this.bundle);
    }

    public MediaMetadataCompat(Parcel parcel) {
        Bundle bundle = parcel.readBundle(MediaSessionCompat.class.getClassLoader());
        bundle.getClass();
        this.bundle = bundle;
    }
}
