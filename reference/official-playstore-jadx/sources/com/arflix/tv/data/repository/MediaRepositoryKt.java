package com.arflix.tv.data.repository;

import androidx.fragment.app.a2;
import androidx.media3.exoplayer.analytics.AnalyticsListener;
import androidx.media3.exoplayer.upstream.CmcdData;
import com.arflix.tv.data.api.TmdbCastMember;
import com.arflix.tv.data.api.TmdbCombinedCredits;
import com.arflix.tv.data.api.TmdbCrewMember;
import com.arflix.tv.data.api.TmdbEpisode;
import com.arflix.tv.data.api.TmdbGenre;
import com.arflix.tv.data.api.TmdbMediaItem;
import com.arflix.tv.data.api.TmdbMovieDetails;
import com.arflix.tv.data.api.TmdbPersonDetails;
import com.arflix.tv.data.api.TmdbTvDetails;
import com.arflix.tv.data.api.TmdbTvSeason;
import com.arflix.tv.data.model.CastMember;
import com.arflix.tv.data.model.Episode;
import com.arflix.tv.data.model.MediaItem;
import com.arflix.tv.data.model.MediaType;
import com.arflix.tv.data.model.PersonDetails;
import com.arflix.tv.util.Constants;
import com.google.common.util.concurrent.r0;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.CancellationException;
import kotlin.Metadata;

/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u0000Z\n\u0000\n\u0002\u0010\b\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\u0007\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\u001a\u0015\u0010\u0000\u001a\u0004\u0018\u00010\u0001*\u0004\u0018\u00010\u0002H\u0002¢\u0006\u0002\u0010\u0003\u001a\u0012\u0010\u0004\u001a\u0004\u0018\u00010\u00052\u0006\u0010\u0006\u001a\u00020\u0005H\u0002\u001a\u0010\u0010\u0007\u001a\u00020\u00052\u0006\u0010\b\u001a\u00020\tH\u0002\u001a\u0014\u0010\n\u001a\u00020\u000b*\u00020\f2\u0006\u0010\r\u001a\u00020\u000eH\u0002\u001a\f\u0010\n\u001a\u00020\u000b*\u00020\u000fH\u0002\u001a\f\u0010\n\u001a\u00020\u000b*\u00020\u0010H\u0002\u001a\f\u0010\u0011\u001a\u00020\u0012*\u00020\u0013H\u0002\u001a\f\u0010\u0014\u001a\u00020\u0015*\u00020\u0016H\u0002\u001a\f\u0010\u0017\u001a\u00020\u0015*\u00020\u0018H\u0002\u001a\f\u0010\u0019\u001a\u00020\u001a*\u00020\u001bH\u0002\u001a\u0010\u0010\u001c\u001a\u00020\u00052\u0006\u0010\u001d\u001a\u00020\u0005H\u0002¨\u0006\u001e"}, d2 = {"toIntSafe", "", "", "(Ljava/lang/Object;)Ljava/lang/Integer;", "normalizeRating", "", "raw", "formatTmdbRating", "voteAverage", "", "toMediaItem", "Lcom/arflix/tv/data/model/MediaItem;", "Lcom/arflix/tv/data/api/TmdbMediaItem;", "defaultType", "Lcom/arflix/tv/data/model/MediaType;", "Lcom/arflix/tv/data/api/TmdbMovieDetails;", "Lcom/arflix/tv/data/api/TmdbTvDetails;", "toEpisode", "Lcom/arflix/tv/data/model/Episode;", "Lcom/arflix/tv/data/api/TmdbEpisode;", "toCastMember", "Lcom/arflix/tv/data/model/CastMember;", "Lcom/arflix/tv/data/api/TmdbCastMember;", "toDirectorCastMember", "Lcom/arflix/tv/data/api/TmdbCrewMember;", "toPersonDetails", "Lcom/arflix/tv/data/model/PersonDetails;", "Lcom/arflix/tv/data/api/TmdbPersonDetails;", "formatDate", "dateStr", "app_playRelease"}, k = 2, mv = {2, 3, 0}, xi = 48)
public final class MediaRepositoryKt {
    private static final String formatDate(String str) throws Exception {
        if (str.length() == 0) {
            return "";
        }
        try {
            Locale locale = Locale.US;
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd", locale);
            SimpleDateFormat simpleDateFormat2 = new SimpleDateFormat("d MMM yyyy", locale);
            Date date = simpleDateFormat.parse(str);
            if (date != null) {
                String str2 = simpleDateFormat2.format(date);
                if (str2 != null) {
                    return str2;
                }
            }
        } catch (Exception e5) {
            if (e5 instanceof CancellationException) {
                throw e5;
            }
        }
        return str;
    }

    private static final String formatTmdbRating(float f10) {
        String strNormalizeRating = normalizeRating(String.valueOf(f10));
        return strNormalizeRating == null ? "" : strNormalizeRating;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final String normalizeRating(String str) {
        Float fH = kotlin.text.t.H(kotlin.text.o.L0(str).toString().replace(',', '.'));
        if (fH == null) {
            return null;
        }
        float fFloatValue = fH.floatValue();
        if (fFloatValue <= 0.0f || fFloatValue > 10.0f) {
            return null;
        }
        return String.format(Locale.US, "%.1f", Arrays.copyOf(new Object[]{fH}, 1));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final CastMember toCastMember(TmdbCastMember tmdbCastMember) {
        int id = tmdbCastMember.getId();
        String name = tmdbCastMember.getName();
        String character = tmdbCastMember.getCharacter();
        if (character == null) {
            character = "";
        }
        String profilePath = tmdbCastMember.getProfilePath();
        return new CastMember(id, name, character, profilePath != null ? Constants.IMAGE_BASE.concat(profilePath) : null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final CastMember toDirectorCastMember(TmdbCrewMember tmdbCrewMember) {
        int id = tmdbCrewMember.getId();
        String name = tmdbCrewMember.getName();
        String job = tmdbCrewMember.getJob();
        String profilePath = tmdbCrewMember.getProfilePath();
        return new CastMember(id, name, job, profilePath != null ? Constants.IMAGE_BASE.concat(profilePath) : null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Episode toEpisode(TmdbEpisode tmdbEpisode) {
        int id = tmdbEpisode.getId();
        int episodeNumber = tmdbEpisode.getEpisodeNumber();
        int seasonNumber = tmdbEpisode.getSeasonNumber();
        String name = tmdbEpisode.getName();
        String overview = tmdbEpisode.getOverview();
        if (overview == null) {
            overview = "";
        }
        String stillPath = tmdbEpisode.getStillPath();
        String strConcat = stillPath != null ? Constants.IMAGE_BASE.concat(stillPath) : null;
        float voteAverage = tmdbEpisode.getVoteAverage();
        Integer runtime = tmdbEpisode.getRuntime();
        int iIntValue = runtime != null ? runtime.intValue() : 0;
        String airDate = tmdbEpisode.getAirDate();
        return new Episode(id, episodeNumber, seasonNumber, name, overview, strConcat, voteAverage, null, iIntValue, airDate == null ? "" : airDate, false, null, 3200, null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Integer toIntSafe(Object obj) {
        if (obj instanceof Number) {
            return Integer.valueOf(((Number) obj).intValue());
        }
        if (obj instanceof String) {
            return kotlin.text.u.R((String) obj);
        }
        return null;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Removed duplicated region for block: B:17:0x003d  */
    /* JADX WARN: Removed duplicated region for block: B:22:0x0047  */
    /* JADX WARN: Removed duplicated region for block: B:46:0x007d  */
    /* JADX WARN: Removed duplicated region for block: B:47:0x0080  */
    /* JADX WARN: Removed duplicated region for block: B:50:0x0088  */
    /* JADX WARN: Removed duplicated region for block: B:57:0x00a6  */
    /* JADX WARN: Removed duplicated region for block: B:59:0x00ac  */
    /* JADX WARN: Removed duplicated region for block: B:60:0x00b3  */
    /* JADX WARN: Removed duplicated region for block: B:63:0x00ba  */
    /* JADX WARN: Removed duplicated region for block: B:66:0x00cf  */
    /* JADX WARN: Removed duplicated region for block: B:67:0x00d2  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static final com.arflix.tv.data.model.MediaItem toMediaItem(com.arflix.tv.data.api.TmdbMediaItem r48, com.arflix.tv.data.model.MediaType r49) throws java.lang.Exception {
        /*
            Method dump skipped, instruction units count: 285
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.arflix.tv.data.repository.MediaRepositoryKt.toMediaItem(com.arflix.tv.data.api.TmdbMediaItem, com.arflix.tv.data.model.MediaType):com.arflix.tv.data.model.MediaItem");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final boolean toMediaItem$lambda$13(TmdbTvSeason tmdbTvSeason) {
        return tmdbTvSeason.getSeasonNumber() > 0 && tmdbTvSeason.getEpisodeCount() > 0;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final PersonDetails toPersonDetails(TmdbPersonDetails tmdbPersonDetails) {
        List arrayList;
        List<TmdbMediaItem> cast;
        TmdbCombinedCredits combinedCredits = tmdbPersonDetails.getCombinedCredits();
        if (combinedCredits == null || (cast = combinedCredits.getCast()) == null) {
            arrayList = kotlin.collections.z.f19728i;
        } else {
            ArrayList arrayList2 = new ArrayList();
            for (Object obj : cast) {
                TmdbMediaItem tmdbMediaItem = (TmdbMediaItem) obj;
                if (tmdbMediaItem.getPosterPath() != null && (kotlin.jvm.internal.p.a(tmdbMediaItem.getMediaType(), "movie") || kotlin.jvm.internal.p.a(tmdbMediaItem.getMediaType(), "tv"))) {
                    arrayList2.add(obj);
                }
            }
            List<TmdbMediaItem> listX0 = kotlin.collections.x.X0(kotlin.collections.x.W0(arrayList2, new Comparator() { // from class: com.arflix.tv.data.repository.MediaRepositoryKt$toPersonDetails$$inlined$sortedByDescending$1
                /* JADX WARN: Multi-variable type inference failed */
                @Override // java.util.Comparator
                public final int compare(T t2, T t10) {
                    return r0.e(Integer.valueOf(((TmdbMediaItem) t10).getVoteCount()), Integer.valueOf(((TmdbMediaItem) t2).getVoteCount()));
                }
            }), 20);
            arrayList = new ArrayList(kotlin.collections.s.U(listX0, 10));
            for (TmdbMediaItem tmdbMediaItem2 : listX0) {
                arrayList.add(toMediaItem(tmdbMediaItem2, kotlin.jvm.internal.p.a(tmdbMediaItem2.getMediaType(), "tv") ? MediaType.TV : MediaType.MOVIE));
            }
        }
        List list = arrayList;
        int id = tmdbPersonDetails.getId();
        String name = tmdbPersonDetails.getName();
        String biography = tmdbPersonDetails.getBiography();
        if (biography == null) {
            biography = "";
        }
        String str = biography;
        String placeOfBirth = tmdbPersonDetails.getPlaceOfBirth();
        String birthday = tmdbPersonDetails.getBirthday();
        String profilePath = tmdbPersonDetails.getProfilePath();
        return new PersonDetails(id, name, str, placeOfBirth, birthday, profilePath != null ? Constants.IMAGE_BASE.concat(profilePath) : null, list);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final MediaItem toMediaItem(TmdbMovieDetails tmdbMovieDetails) throws Exception {
        String strI;
        String strConcat;
        String strConcat2;
        String releaseDate = tmdbMovieDetails.getReleaseDate();
        String strI0 = releaseDate != null ? kotlin.text.o.I0(4, releaseDate) : "";
        Integer runtime = tmdbMovieDetails.getRuntime();
        int iIntValue = (runtime != null ? runtime.intValue() : 0) / 60;
        Integer runtime2 = tmdbMovieDetails.getRuntime();
        int iIntValue2 = (runtime2 != null ? runtime2.intValue() : 0) % 60;
        if (iIntValue > 0) {
            strI = iIntValue + "h " + iIntValue2 + CmcdData.OBJECT_TYPE_MANIFEST;
        } else {
            strI = a2.i(iIntValue2, CmcdData.OBJECT_TYPE_MANIFEST);
        }
        String str = strI;
        int id = tmdbMovieDetails.getId();
        String title = tmdbMovieDetails.getTitle();
        if (kotlin.text.o.h0(title)) {
            title = null;
        }
        if (title == null) {
            title = tmdbMovieDetails.getOriginalTitle();
            if (title == null || kotlin.text.o.h0(title)) {
                title = null;
            }
            if (title == null) {
                title = "Unknown";
            }
        }
        String overview = tmdbMovieDetails.getOverview();
        String str2 = overview == null ? "" : overview;
        String releaseDate2 = tmdbMovieDetails.getReleaseDate();
        if (releaseDate2 == null) {
            releaseDate2 = "";
        }
        String date = formatDate(releaseDate2);
        String str3 = tmdbMovieDetails.getAdult() ? "R" : "PG-13";
        String tmdbRating = formatTmdbRating(tmdbMovieDetails.getVoteAverage());
        MediaType mediaType = MediaType.MOVIE;
        String posterPath = tmdbMovieDetails.getPosterPath();
        if (posterPath == null || (strConcat2 = Constants.IMAGE_BASE.concat(posterPath)) == null) {
            String backdropPath = tmdbMovieDetails.getBackdropPath();
            strConcat = backdropPath != null ? "https://image.tmdb.org/t/p/w1280".concat(backdropPath) : "";
        } else {
            strConcat = strConcat2;
        }
        String backdropPath2 = tmdbMovieDetails.getBackdropPath();
        String strConcat3 = backdropPath2 != null ? "https://image.tmdb.org/t/p/original".concat(backdropPath2) : null;
        String originalLanguage = tmdbMovieDetails.getOriginalLanguage();
        long budget = tmdbMovieDetails.getBudget();
        List<TmdbGenre> genres = tmdbMovieDetails.getGenres();
        ArrayList arrayList = new ArrayList(kotlin.collections.s.U(genres, 10));
        Iterator<T> it = genres.iterator();
        while (it.hasNext()) {
            arrayList.add(Integer.valueOf(((TmdbGenre) it.next()).getId()));
        }
        return new MediaItem(id, title, "Movie", str2, strI0, date, str3, str, "", tmdbRating, mediaType, strConcat, strConcat3, 0, false, null, null, arrayList, originalLanguage, null, false, null, null, null, Long.valueOf(budget), null, null, null, null, false, null, 0.0f, 0L, 0, false, null, false, false, null, null, null, null, -17178624, AnalyticsListener.EVENT_DRM_KEYS_LOADED, null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final MediaItem toMediaItem(TmdbTvDetails tmdbTvDetails) throws Exception {
        int numberOfSeasons;
        String strConcat;
        String strConcat2;
        String firstAirDate = tmdbTvDetails.getFirstAirDate();
        String strI0 = firstAirDate != null ? kotlin.text.o.I0(4, firstAirDate) : "";
        Integer num = (Integer) kotlin.collections.x.o0(tmdbTvDetails.getEpisodeRunTime());
        String strI = a2.i(num != null ? num.intValue() : 45, CmcdData.OBJECT_TYPE_MANIFEST);
        int iF = ga.r.F(ga.r.G(new ga.t(ga.r.I(new ga.p(tmdbTvDetails.getSeasons(), 3), new o(17)), new o(18))));
        Integer numValueOf = Integer.valueOf(iF);
        if (iF <= 0) {
            numValueOf = null;
        }
        if (numValueOf != null) {
            numberOfSeasons = numValueOf.intValue();
        } else {
            numberOfSeasons = tmdbTvDetails.getNumberOfSeasons();
            if (numberOfSeasons < 1) {
                numberOfSeasons = 1;
            }
        }
        int id = tmdbTvDetails.getId();
        String name = tmdbTvDetails.getName();
        if (kotlin.text.o.h0(name)) {
            name = null;
        }
        if (name == null) {
            name = tmdbTvDetails.getOriginalName();
            if (name == null || kotlin.text.o.h0(name)) {
                name = null;
            }
            if (name == null) {
                name = "Unknown";
            }
        }
        String overview = tmdbTvDetails.getOverview();
        String str = overview == null ? "" : overview;
        String firstAirDate2 = tmdbTvDetails.getFirstAirDate();
        if (firstAirDate2 == null) {
            firstAirDate2 = "";
        }
        String date = formatDate(firstAirDate2);
        String tmdbRating = formatTmdbRating(tmdbTvDetails.getVoteAverage());
        MediaType mediaType = MediaType.TV;
        String posterPath = tmdbTvDetails.getPosterPath();
        if (posterPath == null || (strConcat2 = Constants.IMAGE_BASE.concat(posterPath)) == null) {
            String backdropPath = tmdbTvDetails.getBackdropPath();
            strConcat = backdropPath != null ? "https://image.tmdb.org/t/p/w1280".concat(backdropPath) : "";
        } else {
            strConcat = strConcat2;
        }
        String backdropPath2 = tmdbTvDetails.getBackdropPath();
        String strConcat3 = backdropPath2 != null ? "https://image.tmdb.org/t/p/original".concat(backdropPath2) : null;
        String originalLanguage = tmdbTvDetails.getOriginalLanguage();
        boolean zA = kotlin.jvm.internal.p.a(tmdbTvDetails.getStatus(), "Returning Series");
        String status = tmdbTvDetails.getStatus();
        List<TmdbGenre> genres = tmdbTvDetails.getGenres();
        ArrayList arrayList = new ArrayList(kotlin.collections.s.U(genres, 10));
        Iterator<T> it = genres.iterator();
        while (it.hasNext()) {
            arrayList.add(Integer.valueOf(((TmdbGenre) it.next()).getId()));
        }
        return new MediaItem(id, name, "TV Series", str, strI0, date, null, strI, "", tmdbRating, mediaType, strConcat, strConcat3, 0, false, null, null, arrayList, originalLanguage, null, zA, Integer.valueOf(numberOfSeasons), null, null, null, null, status, null, null, false, null, 0.0f, 0L, 0, false, null, false, false, null, null, null, null, -70655936, AnalyticsListener.EVENT_DRM_KEYS_LOADED, null);
    }
}
