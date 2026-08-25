package com.arflix.tv.ui.screens.search;

import com.arflix.tv.data.model.Category;
import com.arflix.tv.data.model.MediaItem;
import java.util.List;
import java.util.Map;
import kotlin.Metadata;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000,\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010$\n\u0002\u0010\u000e\n\u0000\"\u0017\u0010\u0000\u001a\b\u0012\u0004\u0012\u00020\u00020\u0001¢\u0006\b\n\u0000\u001a\u0004\b\u0003\u0010\u0004\"\u0017\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00020\u0001¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0004\"\u0017\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\u00020\u0001¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\u0004\"\u0017\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u00020\u0001¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u0004\"\u0017\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\f0\u0001¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u0004\"\u0014\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\u000f0\u0001X\u0082\u0004¢\u0006\u0002\n\u0000\"\u0014\u0010\u0010\u001a\b\u0012\u0004\u0012\u00020\u00110\u0001X\u0082\u0004¢\u0006\u0002\n\u0000\"\u001a\u0010\u0012\u001a\u000e\u0012\u0004\u0012\u00020\u0014\u0012\u0004\u0012\u00020\u00140\u0013X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0015"}, d2 = {"MOVIE_GENRES", "", "Lcom/arflix/tv/ui/screens/search/Genre;", "getMOVIE_GENRES", "()Ljava/util/List;", "TV_GENRES", "getTV_GENRES", "ALL_GENRES", "getALL_GENRES", "ANIME_GENRES", "getANIME_GENRES", "COUNTRIES", "Lcom/arflix/tv/ui/screens/search/Country;", "getCOUNTRIES", "EMPTY_MEDIA_ITEMS", "Lcom/arflix/tv/data/model/MediaItem;", "EMPTY_CATEGORIES", "Lcom/arflix/tv/data/model/Category;", "EMPTY_LOGO_URLS", "", "", "app_playRelease"}, k = 2, mv = {2, 3, 0}, xi = 48)
public final class SearchViewModelKt {
    private static final List<Category> EMPTY_CATEGORIES;
    private static final Map<String, String> EMPTY_LOGO_URLS;
    private static final List<MediaItem> EMPTY_MEDIA_ITEMS;
    private static final List<Genre> MOVIE_GENRES = t7.a.E(new Genre(28, "Action"), new Genre(12, "Adventure"), new Genre(16, "Animation"), new Genre(35, "Comedy"), new Genre(80, "Crime"), new Genre(99, "Documentary"), new Genre(18, "Drama"), new Genre(10751, "Family"), new Genre(14, "Fantasy"), new Genre(36, "History"), new Genre(27, "Horror"), new Genre(10402, "Music"), new Genre(9648, "Mystery"), new Genre(10749, "Romance"), new Genre(878, "Sci-Fi"), new Genre(53, "Thriller"), new Genre(10752, "War"), new Genre(37, "Western"));
    private static final List<Genre> TV_GENRES = t7.a.E(new Genre(10759, "Action & Adventure"), new Genre(16, "Animation"), new Genre(35, "Comedy"), new Genre(80, "Crime"), new Genre(99, "Documentary"), new Genre(18, "Drama"), new Genre(10751, "Family"), new Genre(10762, "Kids"), new Genre(9648, "Mystery"), new Genre(10765, "Sci-Fi & Fantasy"), new Genre(10768, "War & Politics"), new Genre(37, "Western"));
    private static final List<Genre> ALL_GENRES = t7.a.E(new Genre(28, "Action"), new Genre(12, "Adventure"), new Genre(16, "Animation"), new Genre(35, "Comedy"), new Genre(80, "Crime"), new Genre(99, "Documentary"), new Genre(18, "Drama"), new Genre(10751, "Family"), new Genre(14, "Fantasy"), new Genre(27, "Horror"), new Genre(9648, "Mystery"), new Genre(10749, "Romance"), new Genre(878, "Sci-Fi"), new Genre(53, "Thriller"), new Genre(10752, "War"), new Genre(37, "Western"));
    private static final List<Genre> ANIME_GENRES = t7.a.E(new Genre(28, "Action"), new Genre(12, "Adventure"), new Genre(35, "Comedy"), new Genre(18, "Drama"), new Genre(14, "Fantasy"), new Genre(27, "Horror"), new Genre(10749, "Romance"), new Genre(878, "Sci-Fi"), new Genre(9648, "Mystery"));
    private static final List<Country> COUNTRIES = t7.a.E(new Country("en", "English"), new Country("ja", "Japanese"), new Country("ko", "Korean"), new Country("es", "Spanish"), new Country("fr", "French"), new Country("de", "German"), new Country("it", "Italian"), new Country("pt", "Portuguese"), new Country("hi", "Hindi"), new Country("zh", "Chinese"), new Country("tr", "Turkish"), new Country("ar", "Arabic"), new Country("th", "Thai"), new Country("nl", "Dutch"), new Country("ru", "Russian"));

    static {
        kotlin.collections.z zVar = kotlin.collections.z.f19728i;
        EMPTY_MEDIA_ITEMS = zVar;
        EMPTY_CATEGORIES = zVar;
        EMPTY_LOGO_URLS = kotlin.collections.a0.f19683i;
    }

    public static final List<Genre> getALL_GENRES() {
        return ALL_GENRES;
    }

    public static final List<Genre> getANIME_GENRES() {
        return ANIME_GENRES;
    }

    public static final List<Country> getCOUNTRIES() {
        return COUNTRIES;
    }

    public static final List<Genre> getMOVIE_GENRES() {
        return MOVIE_GENRES;
    }

    public static final List<Genre> getTV_GENRES() {
        return TV_GENRES;
    }
}
