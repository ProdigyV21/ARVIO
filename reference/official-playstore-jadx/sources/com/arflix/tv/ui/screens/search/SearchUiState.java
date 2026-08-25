package com.arflix.tv.ui.screens.search;

import androidx.fragment.app.a2;
import com.arflix.tv.data.model.Category;
import com.arflix.tv.data.model.MediaItem;
import java.util.List;
import java.util.Map;
import kotlin.Metadata;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000L\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010$\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b/\n\u0002\u0010\b\n\u0002\b\u0002\b\u0087\b\u0018\u00002\u00020\u0001Bõ\u0001\u0012\b\b\u0002\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0004\u001a\u00020\u0005\u0012\u000e\b\u0002\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\b0\u0007\u0012\u000e\b\u0002\u0010\t\u001a\b\u0012\u0004\u0012\u00020\b0\u0007\u0012\u000e\b\u0002\u0010\n\u001a\b\u0012\u0004\u0012\u00020\b0\u0007\u0012\u000e\b\u0002\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\f0\u0007\u0012\u0014\b\u0002\u0010\r\u001a\u000e\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u00030\u000e\u0012\n\b\u0002\u0010\u000f\u001a\u0004\u0018\u00010\u0003\u0012\u000e\b\u0002\u0010\u0010\u001a\b\u0012\u0004\u0012\u00020\f0\u0007\u0012\u0014\b\u0002\u0010\u0011\u001a\u000e\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u00030\u000e\u0012\b\b\u0002\u0010\u0012\u001a\u00020\u0005\u0012\b\b\u0002\u0010\u0013\u001a\u00020\u0014\u0012\n\b\u0002\u0010\u0015\u001a\u0004\u0018\u00010\u0016\u0012\n\b\u0002\u0010\u0017\u001a\u0004\u0018\u00010\u0018\u0012\n\b\u0002\u0010\u0019\u001a\u0004\u0018\u00010\u0003\u0012\u000e\b\u0002\u0010\u001a\u001a\b\u0012\u0004\u0012\u00020\b0\u0007\u0012\b\b\u0002\u0010\u001b\u001a\u00020\u0005¢\u0006\u0004\b\u001c\u0010\u001dJ\t\u00103\u001a\u00020\u0003HÆ\u0003J\t\u00104\u001a\u00020\u0005HÆ\u0003J\u000f\u00105\u001a\b\u0012\u0004\u0012\u00020\b0\u0007HÆ\u0003J\u000f\u00106\u001a\b\u0012\u0004\u0012\u00020\b0\u0007HÆ\u0003J\u000f\u00107\u001a\b\u0012\u0004\u0012\u00020\b0\u0007HÆ\u0003J\u000f\u00108\u001a\b\u0012\u0004\u0012\u00020\f0\u0007HÆ\u0003J\u0015\u00109\u001a\u000e\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u00030\u000eHÆ\u0003J\u000b\u0010:\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000f\u0010;\u001a\b\u0012\u0004\u0012\u00020\f0\u0007HÆ\u0003J\u0015\u0010<\u001a\u000e\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u00030\u000eHÆ\u0003J\t\u0010=\u001a\u00020\u0005HÆ\u0003J\t\u0010>\u001a\u00020\u0014HÆ\u0003J\u000b\u0010?\u001a\u0004\u0018\u00010\u0016HÆ\u0003J\u000b\u0010@\u001a\u0004\u0018\u00010\u0018HÆ\u0003J\u000b\u0010A\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000f\u0010B\u001a\b\u0012\u0004\u0012\u00020\b0\u0007HÆ\u0003J\t\u0010C\u001a\u00020\u0005HÆ\u0003J÷\u0001\u0010D\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\u000e\b\u0002\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\b0\u00072\u000e\b\u0002\u0010\t\u001a\b\u0012\u0004\u0012\u00020\b0\u00072\u000e\b\u0002\u0010\n\u001a\b\u0012\u0004\u0012\u00020\b0\u00072\u000e\b\u0002\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\f0\u00072\u0014\b\u0002\u0010\r\u001a\u000e\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u00030\u000e2\n\b\u0002\u0010\u000f\u001a\u0004\u0018\u00010\u00032\u000e\b\u0002\u0010\u0010\u001a\b\u0012\u0004\u0012\u00020\f0\u00072\u0014\b\u0002\u0010\u0011\u001a\u000e\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u00030\u000e2\b\b\u0002\u0010\u0012\u001a\u00020\u00052\b\b\u0002\u0010\u0013\u001a\u00020\u00142\n\b\u0002\u0010\u0015\u001a\u0004\u0018\u00010\u00162\n\b\u0002\u0010\u0017\u001a\u0004\u0018\u00010\u00182\n\b\u0002\u0010\u0019\u001a\u0004\u0018\u00010\u00032\u000e\b\u0002\u0010\u001a\u001a\b\u0012\u0004\u0012\u00020\b0\u00072\b\b\u0002\u0010\u001b\u001a\u00020\u0005HÆ\u0001J\u0014\u0010E\u001a\u00020\u00052\b\u0010F\u001a\u0004\u0018\u00010\u0001HÖ\u0083\u0004J\n\u0010G\u001a\u00020HHÖ\u0081\u0004J\n\u0010I\u001a\u00020\u0003HÖ\u0081\u0004R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u001e\u0010\u001fR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0004\u0010 R\u0017\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\b0\u0007¢\u0006\b\n\u0000\u001a\u0004\b!\u0010\"R\u0017\u0010\t\u001a\b\u0012\u0004\u0012\u00020\b0\u0007¢\u0006\b\n\u0000\u001a\u0004\b#\u0010\"R\u0017\u0010\n\u001a\b\u0012\u0004\u0012\u00020\b0\u0007¢\u0006\b\n\u0000\u001a\u0004\b$\u0010\"R\u0017\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\f0\u0007¢\u0006\b\n\u0000\u001a\u0004\b%\u0010\"R\u001d\u0010\r\u001a\u000e\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u00030\u000e¢\u0006\b\n\u0000\u001a\u0004\b&\u0010'R\u0013\u0010\u000f\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b(\u0010\u001fR\u0017\u0010\u0010\u001a\b\u0012\u0004\u0012\u00020\f0\u0007¢\u0006\b\n\u0000\u001a\u0004\b)\u0010\"R\u001d\u0010\u0011\u001a\u000e\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u00030\u000e¢\u0006\b\n\u0000\u001a\u0004\b*\u0010'R\u0011\u0010\u0012\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010 R\u0011\u0010\u0013\u001a\u00020\u0014¢\u0006\b\n\u0000\u001a\u0004\b+\u0010,R\u0013\u0010\u0015\u001a\u0004\u0018\u00010\u0016¢\u0006\b\n\u0000\u001a\u0004\b-\u0010.R\u0013\u0010\u0017\u001a\u0004\u0018\u00010\u0018¢\u0006\b\n\u0000\u001a\u0004\b/\u00100R\u0013\u0010\u0019\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b1\u0010\u001fR\u0017\u0010\u001a\u001a\b\u0012\u0004\u0012\u00020\b0\u0007¢\u0006\b\n\u0000\u001a\u0004\b2\u0010\"R\u0011\u0010\u001b\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u001b\u0010 ¨\u0006J"}, d2 = {"Lcom/arflix/tv/ui/screens/search/SearchUiState;", "", "query", "", "isLoading", "", "results", "", "Lcom/arflix/tv/data/model/MediaItem;", "movieResults", "tvResults", "personResults", "Lcom/arflix/tv/data/model/Category;", "cardLogoUrls", "", "error", "discoverCategories", "discoverLogoUrls", "isDiscoverLoading", "selectedType", "Lcom/arflix/tv/ui/screens/search/DiscoverType;", "selectedGenre", "Lcom/arflix/tv/ui/screens/search/Genre;", "selectedCountry", "Lcom/arflix/tv/ui/screens/search/Country;", "aiInterpretation", "aiResults", "isAiSearch", "<init>", "(Ljava/lang/String;ZLjava/util/List;Ljava/util/List;Ljava/util/List;Ljava/util/List;Ljava/util/Map;Ljava/lang/String;Ljava/util/List;Ljava/util/Map;ZLcom/arflix/tv/ui/screens/search/DiscoverType;Lcom/arflix/tv/ui/screens/search/Genre;Lcom/arflix/tv/ui/screens/search/Country;Ljava/lang/String;Ljava/util/List;Z)V", "getQuery", "()Ljava/lang/String;", "()Z", "getResults", "()Ljava/util/List;", "getMovieResults", "getTvResults", "getPersonResults", "getCardLogoUrls", "()Ljava/util/Map;", "getError", "getDiscoverCategories", "getDiscoverLogoUrls", "getSelectedType", "()Lcom/arflix/tv/ui/screens/search/DiscoverType;", "getSelectedGenre", "()Lcom/arflix/tv/ui/screens/search/Genre;", "getSelectedCountry", "()Lcom/arflix/tv/ui/screens/search/Country;", "getAiInterpretation", "getAiResults", "component1", "component2", "component3", "component4", "component5", "component6", "component7", "component8", "component9", "component10", "component11", "component12", "component13", "component14", "component15", "component16", "component17", "copy", "equals", "other", "hashCode", "", "toString", "app_playRelease"}, k = 1, mv = {2, 3, 0}, xi = 48)
public final /* data */ class SearchUiState {
    public static final int $stable = 0;
    private final String aiInterpretation;
    private final List<MediaItem> aiResults;
    private final Map<String, String> cardLogoUrls;
    private final List<Category> discoverCategories;
    private final Map<String, String> discoverLogoUrls;
    private final String error;
    private final boolean isAiSearch;
    private final boolean isDiscoverLoading;
    private final boolean isLoading;
    private final List<MediaItem> movieResults;
    private final List<Category> personResults;
    private final String query;
    private final List<MediaItem> results;
    private final Country selectedCountry;
    private final Genre selectedGenre;
    private final DiscoverType selectedType;
    private final List<MediaItem> tvResults;

    public SearchUiState() {
        this(null, false, null, null, null, null, null, null, null, null, false, null, null, null, null, null, false, 131071, null);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static /* synthetic */ SearchUiState copy$default(SearchUiState searchUiState, String str, boolean z, List list, List list2, List list3, List list4, Map map, String str2, List list5, Map map2, boolean z5, DiscoverType discoverType, Genre genre, Country country, String str3, List list6, boolean z10, int i10, Object obj) {
        boolean z11;
        List list7;
        String str4;
        SearchUiState searchUiState2;
        String str5;
        boolean z12;
        List list8;
        List list9;
        List list10;
        List list11;
        Map map3;
        String str6;
        List list12;
        Map map4;
        boolean z13;
        DiscoverType discoverType2;
        Genre genre2;
        Country country2;
        String str7 = (i10 & 1) != 0 ? searchUiState.query : str;
        boolean z14 = (i10 & 2) != 0 ? searchUiState.isLoading : z;
        List list13 = (i10 & 4) != 0 ? searchUiState.results : list;
        List list14 = (i10 & 8) != 0 ? searchUiState.movieResults : list2;
        List list15 = (i10 & 16) != 0 ? searchUiState.tvResults : list3;
        List list16 = (i10 & 32) != 0 ? searchUiState.personResults : list4;
        Map map5 = (i10 & 64) != 0 ? searchUiState.cardLogoUrls : map;
        String str8 = (i10 & 128) != 0 ? searchUiState.error : str2;
        List list17 = (i10 & 256) != 0 ? searchUiState.discoverCategories : list5;
        Map map6 = (i10 & 512) != 0 ? searchUiState.discoverLogoUrls : map2;
        boolean z15 = (i10 & 1024) != 0 ? searchUiState.isDiscoverLoading : z5;
        DiscoverType discoverType3 = (i10 & 2048) != 0 ? searchUiState.selectedType : discoverType;
        Genre genre3 = (i10 & 4096) != 0 ? searchUiState.selectedGenre : genre;
        Country country3 = (i10 & 8192) != 0 ? searchUiState.selectedCountry : country;
        String str9 = str7;
        String str10 = (i10 & 16384) != 0 ? searchUiState.aiInterpretation : str3;
        List list18 = (i10 & 32768) != 0 ? searchUiState.aiResults : list6;
        if ((i10 & 65536) != 0) {
            list7 = list18;
            z11 = searchUiState.isAiSearch;
            str5 = str10;
            z12 = z14;
            list8 = list13;
            list9 = list14;
            list10 = list15;
            list11 = list16;
            map3 = map5;
            str6 = str8;
            list12 = list17;
            map4 = map6;
            z13 = z15;
            discoverType2 = discoverType3;
            genre2 = genre3;
            country2 = country3;
            str4 = str9;
            searchUiState2 = searchUiState;
        } else {
            z11 = z10;
            list7 = list18;
            str4 = str9;
            searchUiState2 = searchUiState;
            str5 = str10;
            z12 = z14;
            list8 = list13;
            list9 = list14;
            list10 = list15;
            list11 = list16;
            map3 = map5;
            str6 = str8;
            list12 = list17;
            map4 = map6;
            z13 = z15;
            discoverType2 = discoverType3;
            genre2 = genre3;
            country2 = country3;
        }
        return searchUiState2.copy(str4, z12, list8, list9, list10, list11, map3, str6, list12, map4, z13, discoverType2, genre2, country2, str5, list7, z11);
    }

    /* JADX INFO: renamed from: component1, reason: from getter */
    public final String getQuery() {
        return this.query;
    }

    public final Map<String, String> component10() {
        return this.discoverLogoUrls;
    }

    /* JADX INFO: renamed from: component11, reason: from getter */
    public final boolean getIsDiscoverLoading() {
        return this.isDiscoverLoading;
    }

    /* JADX INFO: renamed from: component12, reason: from getter */
    public final DiscoverType getSelectedType() {
        return this.selectedType;
    }

    /* JADX INFO: renamed from: component13, reason: from getter */
    public final Genre getSelectedGenre() {
        return this.selectedGenre;
    }

    /* JADX INFO: renamed from: component14, reason: from getter */
    public final Country getSelectedCountry() {
        return this.selectedCountry;
    }

    /* JADX INFO: renamed from: component15, reason: from getter */
    public final String getAiInterpretation() {
        return this.aiInterpretation;
    }

    public final List<MediaItem> component16() {
        return this.aiResults;
    }

    /* JADX INFO: renamed from: component17, reason: from getter */
    public final boolean getIsAiSearch() {
        return this.isAiSearch;
    }

    /* JADX INFO: renamed from: component2, reason: from getter */
    public final boolean getIsLoading() {
        return this.isLoading;
    }

    public final List<MediaItem> component3() {
        return this.results;
    }

    public final List<MediaItem> component4() {
        return this.movieResults;
    }

    public final List<MediaItem> component5() {
        return this.tvResults;
    }

    public final List<Category> component6() {
        return this.personResults;
    }

    public final Map<String, String> component7() {
        return this.cardLogoUrls;
    }

    /* JADX INFO: renamed from: component8, reason: from getter */
    public final String getError() {
        return this.error;
    }

    public final List<Category> component9() {
        return this.discoverCategories;
    }

    public final SearchUiState copy(String query, boolean isLoading, List<MediaItem> results, List<MediaItem> movieResults, List<MediaItem> tvResults, List<Category> personResults, Map<String, String> cardLogoUrls, String error, List<Category> discoverCategories, Map<String, String> discoverLogoUrls, boolean isDiscoverLoading, DiscoverType selectedType, Genre selectedGenre, Country selectedCountry, String aiInterpretation, List<MediaItem> aiResults, boolean isAiSearch) {
        return new SearchUiState(query, isLoading, results, movieResults, tvResults, personResults, cardLogoUrls, error, discoverCategories, discoverLogoUrls, isDiscoverLoading, selectedType, selectedGenre, selectedCountry, aiInterpretation, aiResults, isAiSearch);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof SearchUiState)) {
            return false;
        }
        SearchUiState searchUiState = (SearchUiState) other;
        return kotlin.jvm.internal.p.a(this.query, searchUiState.query) && this.isLoading == searchUiState.isLoading && kotlin.jvm.internal.p.a(this.results, searchUiState.results) && kotlin.jvm.internal.p.a(this.movieResults, searchUiState.movieResults) && kotlin.jvm.internal.p.a(this.tvResults, searchUiState.tvResults) && kotlin.jvm.internal.p.a(this.personResults, searchUiState.personResults) && kotlin.jvm.internal.p.a(this.cardLogoUrls, searchUiState.cardLogoUrls) && kotlin.jvm.internal.p.a(this.error, searchUiState.error) && kotlin.jvm.internal.p.a(this.discoverCategories, searchUiState.discoverCategories) && kotlin.jvm.internal.p.a(this.discoverLogoUrls, searchUiState.discoverLogoUrls) && this.isDiscoverLoading == searchUiState.isDiscoverLoading && this.selectedType == searchUiState.selectedType && kotlin.jvm.internal.p.a(this.selectedGenre, searchUiState.selectedGenre) && kotlin.jvm.internal.p.a(this.selectedCountry, searchUiState.selectedCountry) && kotlin.jvm.internal.p.a(this.aiInterpretation, searchUiState.aiInterpretation) && kotlin.jvm.internal.p.a(this.aiResults, searchUiState.aiResults) && this.isAiSearch == searchUiState.isAiSearch;
    }

    public final String getAiInterpretation() {
        return this.aiInterpretation;
    }

    public final List<MediaItem> getAiResults() {
        return this.aiResults;
    }

    public final Map<String, String> getCardLogoUrls() {
        return this.cardLogoUrls;
    }

    public final List<Category> getDiscoverCategories() {
        return this.discoverCategories;
    }

    public final Map<String, String> getDiscoverLogoUrls() {
        return this.discoverLogoUrls;
    }

    public final String getError() {
        return this.error;
    }

    public final List<MediaItem> getMovieResults() {
        return this.movieResults;
    }

    public final List<Category> getPersonResults() {
        return this.personResults;
    }

    public final String getQuery() {
        return this.query;
    }

    public final List<MediaItem> getResults() {
        return this.results;
    }

    public final Country getSelectedCountry() {
        return this.selectedCountry;
    }

    public final Genre getSelectedGenre() {
        return this.selectedGenre;
    }

    public final DiscoverType getSelectedType() {
        return this.selectedType;
    }

    public final List<MediaItem> getTvResults() {
        return this.tvResults;
    }

    public int hashCode() {
        int iG = a2.g(this.cardLogoUrls, androidx.compose.material3.d.e(this.personResults, androidx.compose.material3.d.e(this.tvResults, androidx.compose.material3.d.e(this.movieResults, androidx.compose.material3.d.e(this.results, ((this.query.hashCode() * 31) + (this.isLoading ? 1231 : 1237)) * 31, 31), 31), 31), 31), 31);
        String str = this.error;
        int iHashCode = (this.selectedType.hashCode() + ((a2.g(this.discoverLogoUrls, androidx.compose.material3.d.e(this.discoverCategories, (iG + (str == null ? 0 : str.hashCode())) * 31, 31), 31) + (this.isDiscoverLoading ? 1231 : 1237)) * 31)) * 31;
        Genre genre = this.selectedGenre;
        int iHashCode2 = (iHashCode + (genre == null ? 0 : genre.hashCode())) * 31;
        Country country = this.selectedCountry;
        int iHashCode3 = (iHashCode2 + (country == null ? 0 : country.hashCode())) * 31;
        String str2 = this.aiInterpretation;
        return androidx.compose.material3.d.e(this.aiResults, (iHashCode3 + (str2 != null ? str2.hashCode() : 0)) * 31, 31) + (this.isAiSearch ? 1231 : 1237);
    }

    public final boolean isAiSearch() {
        return this.isAiSearch;
    }

    public final boolean isDiscoverLoading() {
        return this.isDiscoverLoading;
    }

    public final boolean isLoading() {
        return this.isLoading;
    }

    public String toString() {
        String str = this.query;
        boolean z = this.isLoading;
        List<MediaItem> list = this.results;
        List<MediaItem> list2 = this.movieResults;
        List<MediaItem> list3 = this.tvResults;
        List<Category> list4 = this.personResults;
        Map<String, String> map = this.cardLogoUrls;
        String str2 = this.error;
        List<Category> list5 = this.discoverCategories;
        Map<String, String> map2 = this.discoverLogoUrls;
        boolean z5 = this.isDiscoverLoading;
        DiscoverType discoverType = this.selectedType;
        Genre genre = this.selectedGenre;
        Country country = this.selectedCountry;
        String str3 = this.aiInterpretation;
        List<MediaItem> list6 = this.aiResults;
        boolean z10 = this.isAiSearch;
        StringBuilder sb2 = new StringBuilder("SearchUiState(query=");
        sb2.append(str);
        sb2.append(", isLoading=");
        sb2.append(z);
        sb2.append(", results=");
        a2.A(sb2, list, ", movieResults=", list2, ", tvResults=");
        a2.A(sb2, list3, ", personResults=", list4, ", cardLogoUrls=");
        sb2.append(map);
        sb2.append(", error=");
        sb2.append(str2);
        sb2.append(", discoverCategories=");
        sb2.append(list5);
        sb2.append(", discoverLogoUrls=");
        sb2.append(map2);
        sb2.append(", isDiscoverLoading=");
        sb2.append(z5);
        sb2.append(", selectedType=");
        sb2.append(discoverType);
        sb2.append(", selectedGenre=");
        sb2.append(genre);
        sb2.append(", selectedCountry=");
        sb2.append(country);
        sb2.append(", aiInterpretation=");
        sb2.append(str3);
        sb2.append(", aiResults=");
        sb2.append(list6);
        sb2.append(", isAiSearch=");
        return a0.c.m(")", z10, sb2);
    }

    public SearchUiState(String str, boolean z, List<MediaItem> list, List<MediaItem> list2, List<MediaItem> list3, List<Category> list4, Map<String, String> map, String str2, List<Category> list5, Map<String, String> map2, boolean z5, DiscoverType discoverType, Genre genre, Country country, String str3, List<MediaItem> list6, boolean z10) {
        this.query = str;
        this.isLoading = z;
        this.results = list;
        this.movieResults = list2;
        this.tvResults = list3;
        this.personResults = list4;
        this.cardLogoUrls = map;
        this.error = str2;
        this.discoverCategories = list5;
        this.discoverLogoUrls = map2;
        this.isDiscoverLoading = z5;
        this.selectedType = discoverType;
        this.selectedGenre = genre;
        this.selectedCountry = country;
        this.aiInterpretation = str3;
        this.aiResults = list6;
        this.isAiSearch = z10;
    }

    public /* synthetic */ SearchUiState(String str, boolean z, List list, List list2, List list3, List list4, Map map, String str2, List list5, Map map2, boolean z5, DiscoverType discoverType, Genre genre, Country country, String str3, List list6, boolean z10, int i10, kotlin.jvm.internal.h hVar) {
        this((i10 & 1) != 0 ? "" : str, (i10 & 2) != 0 ? false : z, (i10 & 4) != 0 ? SearchViewModelKt.EMPTY_MEDIA_ITEMS : list, (i10 & 8) != 0 ? SearchViewModelKt.EMPTY_MEDIA_ITEMS : list2, (i10 & 16) != 0 ? SearchViewModelKt.EMPTY_MEDIA_ITEMS : list3, (i10 & 32) != 0 ? SearchViewModelKt.EMPTY_CATEGORIES : list4, (i10 & 64) != 0 ? SearchViewModelKt.EMPTY_LOGO_URLS : map, (i10 & 128) != 0 ? null : str2, (i10 & 256) != 0 ? SearchViewModelKt.EMPTY_CATEGORIES : list5, (i10 & 512) != 0 ? SearchViewModelKt.EMPTY_LOGO_URLS : map2, (i10 & 1024) != 0 ? false : z5, (i10 & 2048) != 0 ? DiscoverType.ALL : discoverType, (i10 & 4096) != 0 ? null : genre, (i10 & 8192) != 0 ? null : country, (i10 & 16384) != 0 ? null : str3, (i10 & 32768) != 0 ? SearchViewModelKt.EMPTY_MEDIA_ITEMS : list6, (i10 & 65536) != 0 ? false : z10);
    }
}
