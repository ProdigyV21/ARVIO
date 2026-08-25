package com.arflix.tv.data.repository;

import kotlin.Metadata;

/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\r\bÂ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003R\u0017\u0010\u0005\u001a\u00020\u00048\u0006¢\u0006\f\n\u0004\b\u0005\u0010\u0006\u001a\u0004\b\u0007\u0010\bR\u0017\u0010\t\u001a\u00020\u00048\u0006¢\u0006\f\n\u0004\b\t\u0010\u0006\u001a\u0004\b\n\u0010\bR\u0017\u0010\u000b\u001a\u00020\u00048\u0006¢\u0006\f\n\u0004\b\u000b\u0010\u0006\u001a\u0004\b\f\u0010\bR\u0017\u0010\r\u001a\u00020\u00048\u0006¢\u0006\f\n\u0004\b\r\u0010\u0006\u001a\u0004\b\u000e\u0010\bR\u0017\u0010\u000f\u001a\u00020\u00048\u0006¢\u0006\f\n\u0004\b\u000f\u0010\u0006\u001a\u0004\b\u0010\u0010\b¨\u0006\u0011"}, d2 = {"Lcom/arflix/tv/data/repository/MediaRegexes;", "", "<init>", "()V", "Lkotlin/text/m;", "YEAR_SUFFIX_REGEX", "Lkotlin/text/m;", "getYEAR_SUFFIX_REGEX", "()Lkotlin/text/m;", "DIGITS_REGEX", "getDIGITS_REGEX", "TYPED_ID_REGEX", "getTYPED_ID_REGEX", "IMDB_ID_REGEX", "getIMDB_ID_REGEX", "TRAKT_URL_REGEX", "getTRAKT_URL_REGEX", "app_playRelease"}, k = 1, mv = {2, 3, 0}, xi = 48)
final class MediaRegexes {
    public static final MediaRegexes INSTANCE = new MediaRegexes();
    private static final kotlin.text.m YEAR_SUFFIX_REGEX = new kotlin.text.m("\\s+\\(\\d{4}\\)$");
    private static final kotlin.text.m DIGITS_REGEX = new kotlin.text.m("\\d+");
    private static final kotlin.text.m TYPED_ID_REGEX = new kotlin.text.m("^(movie|series|tv|show|shows):(\\d+)$", 0);
    private static final kotlin.text.m IMDB_ID_REGEX = new kotlin.text.m("tt\\d{5,}", 0);
    private static final kotlin.text.m TRAKT_URL_REGEX = new kotlin.text.m("https?://(?:www\\.)?trakt\\.tv/users/[^\"'\\s<]+/lists/[^\"'\\s<]+", 0);

    private MediaRegexes() {
    }

    public final kotlin.text.m getDIGITS_REGEX() {
        return DIGITS_REGEX;
    }

    public final kotlin.text.m getIMDB_ID_REGEX() {
        return IMDB_ID_REGEX;
    }

    public final kotlin.text.m getTRAKT_URL_REGEX() {
        return TRAKT_URL_REGEX;
    }

    public final kotlin.text.m getTYPED_ID_REGEX() {
        return TYPED_ID_REGEX;
    }

    public final kotlin.text.m getYEAR_SUFFIX_REGEX() {
        return YEAR_SUFFIX_REGEX;
    }
}
