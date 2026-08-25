package com.arflix.tv.data.repository;

import android.net.Uri;
import com.arflix.tv.data.model.MediaType;
import java.util.Locale;
import kotlin.Metadata;

/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u0000\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u001a\n\u0010\u0000\u001a\u00020\u0001*\u00020\u0002\u001a\f\u0010\u0003\u001a\u0004\u0018\u00010\u0002*\u00020\u0001¨\u0006\u0004"}, d2 = {"toUri", "Landroid/net/Uri;", "Lcom/arflix/tv/data/repository/LauncherContinueWatchingRequest;", "toLauncherContinueWatchingRequest", "app_playRelease"}, k = 2, mv = {2, 3, 0}, xi = 48)
public final class LauncherContinueWatchingRepositoryKt {
    public static final LauncherContinueWatchingRequest toLauncherContinueWatchingRequest(Uri uri) {
        String str;
        String str2;
        Integer numR;
        if (!kotlin.jvm.internal.p.a(uri.getScheme(), "arvio") || !kotlin.jvm.internal.p.a(uri.getAuthority(), "continue") || (str = (String) kotlin.collections.x.p0(0, uri.getPathSegments())) == null || (str2 = (String) kotlin.collections.x.p0(1, uri.getPathSegments())) == null || (numR = kotlin.text.u.R(str2)) == null) {
            return null;
        }
        int iIntValue = numR.intValue();
        MediaType mediaType = str.equalsIgnoreCase("tv") ? MediaType.TV : MediaType.MOVIE;
        String queryParameter = uri.getQueryParameter("season");
        Integer numR2 = queryParameter != null ? kotlin.text.u.R(queryParameter) : null;
        String queryParameter2 = uri.getQueryParameter("episode");
        Integer numR3 = queryParameter2 != null ? kotlin.text.u.R(queryParameter2) : null;
        String queryParameter3 = uri.getQueryParameter("startPositionMs");
        return new LauncherContinueWatchingRequest(mediaType, iIntValue, numR2, numR3, queryParameter3 != null ? kotlin.text.u.S(queryParameter3) : null);
    }

    public static final Uri toUri(LauncherContinueWatchingRequest launcherContinueWatchingRequest) {
        Uri.Builder builderAppendPath = new Uri.Builder().scheme("arvio").authority("continue").appendPath(launcherContinueWatchingRequest.getMediaType().name().toLowerCase(Locale.ROOT)).appendPath(String.valueOf(launcherContinueWatchingRequest.getMediaId()));
        Integer season = launcherContinueWatchingRequest.getSeason();
        if (season != null) {
            builderAppendPath.appendQueryParameter("season", String.valueOf(season.intValue()));
        }
        Integer episode = launcherContinueWatchingRequest.getEpisode();
        if (episode != null) {
            builderAppendPath.appendQueryParameter("episode", String.valueOf(episode.intValue()));
        }
        Long startPositionMs = launcherContinueWatchingRequest.getStartPositionMs();
        if (startPositionMs != null) {
            builderAppendPath.appendQueryParameter("startPositionMs", String.valueOf(startPositionMs.longValue()));
        }
        return builderAppendPath.build();
    }
}
