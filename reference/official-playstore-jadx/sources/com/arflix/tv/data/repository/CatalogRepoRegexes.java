package com.arflix.tv.data.repository;

import kotlin.Metadata;

/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\t\bÂ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003R\u0017\u0010\u0005\u001a\u00020\u00048\u0006¢\u0006\f\n\u0004\b\u0005\u0010\u0006\u001a\u0004\b\u0007\u0010\bR\u0017\u0010\t\u001a\u00020\u00048\u0006¢\u0006\f\n\u0004\b\t\u0010\u0006\u001a\u0004\b\n\u0010\bR\u0017\u0010\u000b\u001a\u00020\u00048\u0006¢\u0006\f\n\u0004\b\u000b\u0010\u0006\u001a\u0004\b\f\u0010\b¨\u0006\r"}, d2 = {"Lcom/arflix/tv/data/repository/CatalogRepoRegexes;", "", "<init>", "()V", "Lkotlin/text/m;", "TITLE_FROM_META_REGEX", "Lkotlin/text/m;", "getTITLE_FROM_META_REGEX", "()Lkotlin/text/m;", "TITLE_FROM_TAG_REGEX", "getTITLE_FROM_TAG_REGEX", "TRAKT_URL_REGEX", "getTRAKT_URL_REGEX", "app_playRelease"}, k = 1, mv = {2, 3, 0}, xi = 48)
final class CatalogRepoRegexes {
    public static final CatalogRepoRegexes INSTANCE = new CatalogRepoRegexes();
    private static final kotlin.text.m TITLE_FROM_META_REGEX = new kotlin.text.m("<meta\\s+property=[\"']og:title[\"']\\s+content=[\"']([^\"']+)[\"']", 0);
    private static final kotlin.text.m TITLE_FROM_TAG_REGEX = new kotlin.text.m("<title>([^<]+)</title>", 0);
    private static final kotlin.text.m TRAKT_URL_REGEX = new kotlin.text.m("https?://(?:www\\.)?trakt\\.tv/users/[^\"'\\s<]+/lists/[^\"'\\s<]+", 0);

    private CatalogRepoRegexes() {
    }

    public final kotlin.text.m getTITLE_FROM_META_REGEX() {
        return TITLE_FROM_META_REGEX;
    }

    public final kotlin.text.m getTITLE_FROM_TAG_REGEX() {
        return TITLE_FROM_TAG_REGEX;
    }

    public final kotlin.text.m getTRAKT_URL_REGEX() {
        return TRAKT_URL_REGEX;
    }
}
