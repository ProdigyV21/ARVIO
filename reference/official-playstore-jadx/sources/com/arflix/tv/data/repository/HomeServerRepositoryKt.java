package com.arflix.tv.data.repository;

import com.arflix.tv.data.model.MediaType;
import java.util.Locale;
import kotlin.Metadata;

/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\u001a\u001e\u0010\u0000\u001a\u0004\u0018\u00010\u00012\u0006\u0010\u0002\u001a\u00020\u00032\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0001H\u0000¨\u0006\u0005"}, d2 = {"homeServerCatalogMediaType", "Lcom/arflix/tv/data/model/MediaType;", "collectionType", "", "explicitMediaType", "app_playRelease"}, k = 2, mv = {2, 3, 0}, xi = 48)
public final class HomeServerRepositoryKt {
    /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
    public static final MediaType homeServerCatalogMediaType(String str, MediaType mediaType) {
        if (mediaType != null) {
            return mediaType;
        }
        String lowerCase = kotlin.text.o.L0(str).toString().toLowerCase(Locale.US);
        switch (lowerCase.hashCode()) {
            case -1068259517:
                if (!lowerCase.equals("movies")) {
                    return null;
                }
                return MediaType.MOVIE;
            case -936101932:
                if (!lowerCase.equals("tvshows")) {
                    return null;
                }
                return MediaType.TV;
            case -905838985:
                if (!lowerCase.equals("series")) {
                    return null;
                }
                return MediaType.TV;
            case -861480833:
                if (!lowerCase.equals("tvshow")) {
                    return null;
                }
                return MediaType.TV;
            case 3714:
                if (!lowerCase.equals("tv")) {
                    return null;
                }
                return MediaType.TV;
            case 3143044:
                if (!lowerCase.equals("film")) {
                    return null;
                }
                return MediaType.MOVIE;
            case 3529469:
                if (!lowerCase.equals("show")) {
                    return null;
                }
                return MediaType.TV;
            case 97434479:
                if (!lowerCase.equals("films")) {
                    return null;
                }
                return MediaType.MOVIE;
            case 104087344:
                if (!lowerCase.equals("movie")) {
                    return null;
                }
                return MediaType.MOVIE;
            case 109413654:
                if (!lowerCase.equals("shows")) {
                    return null;
                }
                return MediaType.TV;
            default:
                return null;
        }
    }

    public static /* synthetic */ MediaType homeServerCatalogMediaType$default(String str, MediaType mediaType, int i10, Object obj) {
        if ((i10 & 2) != 0) {
            mediaType = null;
        }
        return homeServerCatalogMediaType(str, mediaType);
    }
}
