package com.google.android.gms.cast.framework.media;

import android.net.Uri;
import com.google.android.gms.cast.MediaInfo;
import com.google.android.gms.cast.MediaMetadata;
import com.google.android.gms.cast.MediaTrack;
import java.util.Locale;

/* JADX INFO: loaded from: classes4.dex */
public class MediaUtils {
    private MediaUtils() {
    }

    public static Uri getImageUri(MediaInfo mediaInfo, int i10) {
        MediaMetadata metadata;
        if (mediaInfo == null || (metadata = mediaInfo.getMetadata()) == null || metadata.getImages() == null || metadata.getImages().size() <= i10) {
            return null;
        }
        return metadata.getImages().get(i10).f21388l;
    }

    public static String getImageUrl(MediaInfo mediaInfo, int i10) {
        Uri imageUri = getImageUri(mediaInfo, i10);
        if (imageUri == null) {
            return null;
        }
        return imageUri.toString();
    }

    @Deprecated
    public static Locale getTrackLanguage(MediaTrack mediaTrack) {
        String language = mediaTrack.getLanguage();
        if (language != null) {
            return Locale.forLanguageTag(language);
        }
        return null;
    }
}
