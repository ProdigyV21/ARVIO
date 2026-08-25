package com.arflix.tv.ui.screens.search;

import androidx.fragment.app.a2;
import androidx.lifecycle.d1;
import androidx.lifecycle.z0;
import androidx.media3.exoplayer.upstream.CmcdData;
import com.arflix.tv.data.model.Category;
import com.arflix.tv.data.model.MediaItem;
import com.arflix.tv.data.model.MediaType;
import com.arflix.tv.data.repository.MediaRepository;
import com.google.common.util.concurrent.r0;
import io.ktor.http.LinkHeader;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Comparator;
import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.concurrent.CancellationException;
import javax.inject.Inject;
import ka.k0;
import ka.l0;
import ka.m0;
import ka.p0;
import ka.s0;
import ka.v1;
import ka.x0;
import kotlin.Metadata;
import kotlin.NoWhenBranchMatchedException;
import kotlin.collections.h0;
import kotlin.collections.n0;
import na.h1;
import na.j1;
import na.q0;
import na.y0;
import x6.t0;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000\u0080\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\u000e\n\u0002\b\t\n\u0002\u0010 \n\u0002\b\u0005\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u000b\b\u0007\u0018\u00002\u00020\u0001:\u0001ZB\u0011\b\u0007\u0012\u0006\u0010\u0003\u001a\u00020\u0002¢\u0006\u0004\b\u0004\u0010\u0005J\u0015\u0010\t\u001a\u00020\b2\u0006\u0010\u0007\u001a\u00020\u0006¢\u0006\u0004\b\t\u0010\nJ)\u0010\u000f\u001a\u00020\b2\u0006\u0010\u0007\u001a\u00020\u00062\b\u0010\f\u001a\u0004\u0018\u00010\u000b2\b\u0010\u000e\u001a\u0004\u0018\u00010\r¢\u0006\u0004\b\u000f\u0010\u0010J\u0017\u0010\u0011\u001a\u00020\b2\b\u0010\f\u001a\u0004\u0018\u00010\u000b¢\u0006\u0004\b\u0011\u0010\u0012J\u0017\u0010\u0013\u001a\u00020\b2\b\u0010\u000e\u001a\u0004\u0018\u00010\r¢\u0006\u0004\b\u0013\u0010\u0014J\u0015\u0010\u0017\u001a\u00020\b2\u0006\u0010\u0016\u001a\u00020\u0015¢\u0006\u0004\b\u0017\u0010\u0018J\r\u0010\u0019\u001a\u00020\b¢\u0006\u0004\b\u0019\u0010\u001aJ\u0015\u0010\u001c\u001a\u00020\b2\u0006\u0010\u001b\u001a\u00020\u0015¢\u0006\u0004\b\u001c\u0010\u0018J\r\u0010\u001d\u001a\u00020\b¢\u0006\u0004\b\u001d\u0010\u001aJ\r\u0010\u001e\u001a\u00020\b¢\u0006\u0004\b\u001e\u0010\u001aJ\u0013\u0010 \u001a\b\u0012\u0004\u0012\u00020\u000b0\u001f¢\u0006\u0004\b \u0010!J\u000f\u0010\"\u001a\u00020\bH\u0002¢\u0006\u0004\b\"\u0010\u001aJp\u0010.\u001a\u0004\u0018\u00010-2\u0006\u0010#\u001a\u00020\u00152\u0006\u0010\u0007\u001a\u00020\u00062\b\u0010\f\u001a\u0004\u0018\u00010\u00152\u0006\u0010$\u001a\u00020\u00152\b\u0010&\u001a\u0004\u0018\u00010%2\b\u0010'\u001a\u0004\u0018\u00010\u00152\u0006\u0010)\u001a\u00020(2\u0006\u0010*\u001a\u00020%2\n\b\u0002\u0010+\u001a\u0004\u0018\u00010\u00152\n\b\u0002\u0010,\u001a\u0004\u0018\u00010\u0015H\u0082@¢\u0006\u0004\b.\u0010/J\u001b\u00100\u001a\u0004\u0018\u00010\u00152\b\u0010\f\u001a\u0004\u0018\u00010\u0015H\u0002¢\u0006\u0004\b0\u00101J\u0019\u00102\u001a\u00020\u00152\b\u0010\f\u001a\u0004\u0018\u00010\u0015H\u0002¢\u0006\u0004\b2\u00101J\u0019\u00105\u001a\u0004\u0018\u0001042\u0006\u00103\u001a\u00020\u0015H\u0002¢\u0006\u0004\b5\u00106J\u0017\u00108\u001a\u00020\b2\u0006\u00107\u001a\u000204H\u0002¢\u0006\u0004\b8\u00109J\u000f\u0010:\u001a\u00020\bH\u0002¢\u0006\u0004\b:\u0010\u001aJ\u0017\u0010<\u001a\u00020\u00152\u0006\u0010;\u001a\u00020\u0015H\u0002¢\u0006\u0004\b<\u00101J+\u0010@\u001a\b\u0012\u0004\u0012\u00020>0\u001f2\u0006\u0010=\u001a\u00020\u00152\f\u0010?\u001a\b\u0012\u0004\u0012\u00020>0\u001fH\u0002¢\u0006\u0004\b@\u0010AJ1\u0010D\u001a\b\u0012\u0004\u0012\u00020>0\u001f2\f\u0010B\u001a\b\u0012\u0004\u0012\u00020>0\u001f2\f\u0010C\u001a\b\u0012\u0004\u0012\u00020>0\u001fH\u0002¢\u0006\u0004\bD\u0010ER\u0014\u0010\u0003\u001a\u00020\u00028\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u0003\u0010FR\u001a\u0010I\u001a\b\u0012\u0004\u0012\u00020H0G8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\bI\u0010JR\u001d\u0010L\u001a\b\u0012\u0004\u0012\u00020H0K8\u0006¢\u0006\f\n\u0004\bL\u0010M\u001a\u0004\bN\u0010OR\u0018\u0010Q\u001a\u0004\u0018\u00010P8\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\bQ\u0010RR\u0018\u0010S\u001a\u0004\u0018\u00010P8\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\bS\u0010RR\u0016\u0010T\u001a\u00020\u00158\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\bT\u0010UR\u001c\u0010V\u001a\b\u0012\u0004\u0012\u00020>0\u001f8\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\bV\u0010WR\u0016\u0010X\u001a\u00020\u00158\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\bX\u0010UR\u001c\u0010Y\u001a\b\u0012\u0004\u0012\u00020-0\u001f8\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\bY\u0010W¨\u0006["}, d2 = {"Lcom/arflix/tv/ui/screens/search/SearchViewModel;", "Landroidx/lifecycle/d1;", "Lcom/arflix/tv/data/repository/MediaRepository;", "mediaRepository", "<init>", "(Lcom/arflix/tv/data/repository/MediaRepository;)V", "Lcom/arflix/tv/ui/screens/search/DiscoverType;", LinkHeader.Parameters.Type, "Lx6/t0;", "selectType", "(Lcom/arflix/tv/ui/screens/search/DiscoverType;)V", "Lcom/arflix/tv/ui/screens/search/Genre;", "genre", "Lcom/arflix/tv/ui/screens/search/Country;", "country", "setDiscoverFilters", "(Lcom/arflix/tv/ui/screens/search/DiscoverType;Lcom/arflix/tv/ui/screens/search/Genre;Lcom/arflix/tv/ui/screens/search/Country;)V", "selectGenre", "(Lcom/arflix/tv/ui/screens/search/Genre;)V", "selectCountry", "(Lcom/arflix/tv/ui/screens/search/Country;)V", "", "char", "addChar", "(Ljava/lang/String;)V", "deleteChar", "()V", "newQuery", "updateQuery", "search", "clearSearch", "", "getGenresForType", "()Ljava/util/List;", "loadDiscoverRows", LinkHeader.Parameters.Title, "sort", "", "minVotes", "lang", "", "isAnime", "page", "releaseDateGte", "releaseDateLte", "Lcom/arflix/tv/data/model/Category;", "buildRow", "(Ljava/lang/String;Lcom/arflix/tv/ui/screens/search/DiscoverType;Ljava/lang/String;Ljava/lang/String;Ljava/lang/Integer;Ljava/lang/String;ZILjava/lang/String;Ljava/lang/String;Ld7/d;)Ljava/lang/Object;", "mapMovieGenreToTvGenre", "(Ljava/lang/String;)Ljava/lang/String;", "buildAnimeGenre", "raw", "Lcom/arflix/tv/ui/screens/search/SearchViewModel$SmartQuery;", "parseSmartQuery", "(Ljava/lang/String;)Lcom/arflix/tv/ui/screens/search/SearchViewModel$SmartQuery;", "sq", "executeSmartSearch", "(Lcom/arflix/tv/ui/screens/search/SearchViewModel$SmartQuery;)V", "debounceSearch", "text", "normalizeForSearch", "query", "Lcom/arflix/tv/data/model/MediaItem;", "results", "sortResults", "(Ljava/lang/String;Ljava/util/List;)Ljava/util/List;", CmcdData.OBJECT_TYPE_AUDIO_ONLY, "b", "interleave", "(Ljava/util/List;Ljava/util/List;)Ljava/util/List;", "Lcom/arflix/tv/data/repository/MediaRepository;", "Lna/q0;", "Lcom/arflix/tv/ui/screens/search/SearchUiState;", "_uiState", "Lna/q0;", "Lna/h1;", "uiState", "Lna/h1;", "getUiState", "()Lna/h1;", "Lka/v1;", "searchJob", "Lka/v1;", "discoverJob", "cachedSuggestionQuery", "Ljava/lang/String;", "cachedSuggestionResults", "Ljava/util/List;", "cachedPeopleQuery", "cachedPeopleResults", "SmartQuery", "app_playRelease"}, k = 1, mv = {2, 3, 0}, xi = 48)
public final class SearchViewModel extends d1 {
    public static final int $stable = 8;
    private final q0<SearchUiState> _uiState;
    private String cachedPeopleQuery;
    private List<Category> cachedPeopleResults;
    private String cachedSuggestionQuery;
    private List<MediaItem> cachedSuggestionResults;
    private v1 discoverJob;
    private final MediaRepository mediaRepository;
    private v1 searchJob;
    private final h1<SearchUiState> uiState;

    @Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0019\n\u0002\u0010\u000b\n\u0002\b\u0004\b\u0082\b\u0018\u00002\u00020\u0001BG\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\b\u0010\u0006\u001a\u0004\u0018\u00010\u0003\u0012\u0006\u0010\u0007\u001a\u00020\u0003\u0012\b\u0010\b\u001a\u0004\u0018\u00010\t\u0012\b\u0010\n\u001a\u0004\u0018\u00010\t\u0012\b\u0010\u000b\u001a\u0004\u0018\u00010\u0003¢\u0006\u0004\b\f\u0010\rJ\t\u0010\u0019\u001a\u00020\u0003HÆ\u0003J\t\u0010\u001a\u001a\u00020\u0005HÆ\u0003J\u000b\u0010\u001b\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\t\u0010\u001c\u001a\u00020\u0003HÆ\u0003J\u0010\u0010\u001d\u001a\u0004\u0018\u00010\tHÆ\u0003¢\u0006\u0002\u0010\u0015J\u0010\u0010\u001e\u001a\u0004\u0018\u00010\tHÆ\u0003¢\u0006\u0002\u0010\u0015J\u000b\u0010\u001f\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\\\u0010 \u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u00032\b\b\u0002\u0010\u0007\u001a\u00020\u00032\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\t2\n\b\u0002\u0010\n\u001a\u0004\u0018\u00010\t2\n\b\u0002\u0010\u000b\u001a\u0004\u0018\u00010\u0003HÆ\u0001¢\u0006\u0002\u0010!J\u0014\u0010\"\u001a\u00020#2\b\u0010$\u001a\u0004\u0018\u00010\u0001HÖ\u0083\u0004J\n\u0010%\u001a\u00020\tHÖ\u0081\u0004J\n\u0010&\u001a\u00020\u0003HÖ\u0081\u0004R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000fR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u0011R\u0013\u0010\u0006\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u000fR\u0011\u0010\u0007\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u000fR\u0015\u0010\b\u001a\u0004\u0018\u00010\t¢\u0006\n\n\u0002\u0010\u0016\u001a\u0004\b\u0014\u0010\u0015R\u0015\u0010\n\u001a\u0004\u0018\u00010\t¢\u0006\n\n\u0002\u0010\u0016\u001a\u0004\b\u0017\u0010\u0015R\u0013\u0010\u000b\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0018\u0010\u000f¨\u0006'"}, d2 = {"Lcom/arflix/tv/ui/screens/search/SearchViewModel$SmartQuery;", "", "interpretation", "", LinkHeader.Parameters.Type, "Lcom/arflix/tv/ui/screens/search/DiscoverType;", "genreId", "sort", "minVotes", "", "limit", "similarTo", "<init>", "(Ljava/lang/String;Lcom/arflix/tv/ui/screens/search/DiscoverType;Ljava/lang/String;Ljava/lang/String;Ljava/lang/Integer;Ljava/lang/Integer;Ljava/lang/String;)V", "getInterpretation", "()Ljava/lang/String;", "getType", "()Lcom/arflix/tv/ui/screens/search/DiscoverType;", "getGenreId", "getSort", "getMinVotes", "()Ljava/lang/Integer;", "Ljava/lang/Integer;", "getLimit", "getSimilarTo", "component1", "component2", "component3", "component4", "component5", "component6", "component7", "copy", "(Ljava/lang/String;Lcom/arflix/tv/ui/screens/search/DiscoverType;Ljava/lang/String;Ljava/lang/String;Ljava/lang/Integer;Ljava/lang/Integer;Ljava/lang/String;)Lcom/arflix/tv/ui/screens/search/SearchViewModel$SmartQuery;", "equals", "", "other", "hashCode", "toString", "app_playRelease"}, k = 1, mv = {2, 3, 0}, xi = 48)
    public static final /* data */ class SmartQuery {
        private final String genreId;
        private final String interpretation;
        private final Integer limit;
        private final Integer minVotes;
        private final String similarTo;
        private final String sort;
        private final DiscoverType type;

        public SmartQuery(String str, DiscoverType discoverType, String str2, String str3, Integer num, Integer num2, String str4) {
            this.interpretation = str;
            this.type = discoverType;
            this.genreId = str2;
            this.sort = str3;
            this.minVotes = num;
            this.limit = num2;
            this.similarTo = str4;
        }

        public static /* synthetic */ SmartQuery copy$default(SmartQuery smartQuery, String str, DiscoverType discoverType, String str2, String str3, Integer num, Integer num2, String str4, int i10, Object obj) {
            if ((i10 & 1) != 0) {
                str = smartQuery.interpretation;
            }
            if ((i10 & 2) != 0) {
                discoverType = smartQuery.type;
            }
            if ((i10 & 4) != 0) {
                str2 = smartQuery.genreId;
            }
            if ((i10 & 8) != 0) {
                str3 = smartQuery.sort;
            }
            if ((i10 & 16) != 0) {
                num = smartQuery.minVotes;
            }
            if ((i10 & 32) != 0) {
                num2 = smartQuery.limit;
            }
            if ((i10 & 64) != 0) {
                str4 = smartQuery.similarTo;
            }
            Integer num3 = num2;
            String str5 = str4;
            Integer num4 = num;
            String str6 = str2;
            return smartQuery.copy(str, discoverType, str6, str3, num4, num3, str5);
        }

        /* JADX INFO: renamed from: component1, reason: from getter */
        public final String getInterpretation() {
            return this.interpretation;
        }

        /* JADX INFO: renamed from: component2, reason: from getter */
        public final DiscoverType getType() {
            return this.type;
        }

        /* JADX INFO: renamed from: component3, reason: from getter */
        public final String getGenreId() {
            return this.genreId;
        }

        /* JADX INFO: renamed from: component4, reason: from getter */
        public final String getSort() {
            return this.sort;
        }

        /* JADX INFO: renamed from: component5, reason: from getter */
        public final Integer getMinVotes() {
            return this.minVotes;
        }

        /* JADX INFO: renamed from: component6, reason: from getter */
        public final Integer getLimit() {
            return this.limit;
        }

        /* JADX INFO: renamed from: component7, reason: from getter */
        public final String getSimilarTo() {
            return this.similarTo;
        }

        public final SmartQuery copy(String interpretation, DiscoverType type, String genreId, String sort, Integer minVotes, Integer limit, String similarTo) {
            return new SmartQuery(interpretation, type, genreId, sort, minVotes, limit, similarTo);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof SmartQuery)) {
                return false;
            }
            SmartQuery smartQuery = (SmartQuery) other;
            return kotlin.jvm.internal.p.a(this.interpretation, smartQuery.interpretation) && this.type == smartQuery.type && kotlin.jvm.internal.p.a(this.genreId, smartQuery.genreId) && kotlin.jvm.internal.p.a(this.sort, smartQuery.sort) && kotlin.jvm.internal.p.a(this.minVotes, smartQuery.minVotes) && kotlin.jvm.internal.p.a(this.limit, smartQuery.limit) && kotlin.jvm.internal.p.a(this.similarTo, smartQuery.similarTo);
        }

        public final String getGenreId() {
            return this.genreId;
        }

        public final String getInterpretation() {
            return this.interpretation;
        }

        public final Integer getLimit() {
            return this.limit;
        }

        public final Integer getMinVotes() {
            return this.minVotes;
        }

        public final String getSimilarTo() {
            return this.similarTo;
        }

        public final String getSort() {
            return this.sort;
        }

        public final DiscoverType getType() {
            return this.type;
        }

        public int hashCode() {
            int iHashCode = (this.type.hashCode() + (this.interpretation.hashCode() * 31)) * 31;
            String str = this.genreId;
            int iC = androidx.compose.foundation.c.c((iHashCode + (str == null ? 0 : str.hashCode())) * 31, 31, this.sort);
            Integer num = this.minVotes;
            int iHashCode2 = (iC + (num == null ? 0 : num.hashCode())) * 31;
            Integer num2 = this.limit;
            int iHashCode3 = (iHashCode2 + (num2 == null ? 0 : num2.hashCode())) * 31;
            String str2 = this.similarTo;
            return iHashCode3 + (str2 != null ? str2.hashCode() : 0);
        }

        public String toString() {
            String str = this.interpretation;
            DiscoverType discoverType = this.type;
            String str2 = this.genreId;
            String str3 = this.sort;
            Integer num = this.minVotes;
            Integer num2 = this.limit;
            String str4 = this.similarTo;
            StringBuilder sb2 = new StringBuilder("SmartQuery(interpretation=");
            sb2.append(str);
            sb2.append(", type=");
            sb2.append(discoverType);
            sb2.append(", genreId=");
            y.a.i(sb2, str2, ", sort=", str3, ", minVotes=");
            a2.z(sb2, num, ", limit=", num2, ", similarTo=");
            return a0.c.p(sb2, str4, ")");
        }
    }

    @Metadata(k = 3, mv = {2, 3, 0}, xi = 48)
    public static final /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        static {
            int[] iArr = new int[DiscoverType.values().length];
            try {
                iArr[DiscoverType.MOVIES.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                iArr[DiscoverType.TV_SHOWS.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                iArr[DiscoverType.ANIME.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                iArr[DiscoverType.ALL.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            $EnumSwitchMapping$0 = iArr;
        }
    }

    /* JADX INFO: renamed from: com.arflix.tv.ui.screens.search.SearchViewModel$buildRow$1, reason: invalid class name */
    @Metadata(k = 3, mv = {2, 3, 0}, xi = 48)
    @f7.e(c = "com.arflix.tv.ui.screens.search.SearchViewModel", f = "SearchViewModel.kt", l = {176, 177, 180, 183}, m = "buildRow", v = 2)
    public static final class AnonymousClass1 extends f7.c {
        int I$0;
        Object L$0;
        Object L$1;
        Object L$10;
        Object L$2;
        Object L$3;
        Object L$4;
        Object L$5;
        Object L$6;
        Object L$7;
        Object L$8;
        Object L$9;
        boolean Z$0;
        int label;
        /* synthetic */ Object result;

        public AnonymousClass1(d7.d<? super AnonymousClass1> dVar) {
            super(dVar);
        }

        @Override // f7.a
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return SearchViewModel.this.buildRow(null, null, null, null, null, null, false, 0, null, null, this);
        }
    }

    /* JADX INFO: renamed from: com.arflix.tv.ui.screens.search.SearchViewModel$debounceSearch$1, reason: invalid class name and case insensitive filesystem */
    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0002\u001a\u00020\u0001*\u00020\u0000H\n¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"Lka/k0;", "Lx6/t0;", "<anonymous>", "(Lka/k0;)V"}, k = 3, mv = {2, 3, 0})
    @f7.e(c = "com.arflix.tv.ui.screens.search.SearchViewModel$debounceSearch$1", f = "SearchViewModel.kt", l = {360}, m = "invokeSuspend", v = 2)
    public static final class C14621 extends f7.j implements r7.p<k0, d7.d<? super t0>, Object> {
        int label;

        public C14621(d7.d<? super C14621> dVar) {
            super(2, dVar);
        }

        @Override // f7.a
        public final d7.d<t0> create(Object obj, d7.d<?> dVar) {
            return SearchViewModel.this.new C14621(dVar);
        }

        @Override // f7.a
        public final Object invokeSuspend(Object obj) {
            int i10 = this.label;
            if (i10 == 0) {
                k2.c.G(obj);
                this.label = 1;
                Object objA = s0.a(450L, this);
                e7.a aVar = e7.a.f15033i;
                if (objA == aVar) {
                    return aVar;
                }
            } else {
                if (i10 != 1) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                k2.c.G(obj);
            }
            if (((SearchUiState) SearchViewModel.this._uiState.getValue()).getQuery().length() >= 2) {
                SearchViewModel.this.search();
            }
            return t0.f22605a;
        }

        @Override // r7.p
        public final Object invoke(k0 k0Var, d7.d<? super t0> dVar) {
            return ((C14621) create(k0Var, dVar)).invokeSuspend(t0.f22605a);
        }
    }

    /* JADX INFO: renamed from: com.arflix.tv.ui.screens.search.SearchViewModel$executeSmartSearch$1, reason: invalid class name and case insensitive filesystem */
    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0002\u001a\u00020\u0001*\u00020\u0000H\n¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"Lka/k0;", "Lx6/t0;", "<anonymous>", "(Lka/k0;)V"}, k = 3, mv = {2, 3, 0})
    @f7.e(c = "com.arflix.tv.ui.screens.search.SearchViewModel$executeSmartSearch$1", f = "SearchViewModel.kt", l = {336}, m = "invokeSuspend", v = 2)
    public static final class C14631 extends f7.j implements r7.p<k0, d7.d<? super t0>, Object> {
        final /* synthetic */ SmartQuery $sq;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public C14631(SmartQuery smartQuery, d7.d<? super C14631> dVar) {
            super(2, dVar);
            this.$sq = smartQuery;
        }

        @Override // f7.a
        public final d7.d<t0> create(Object obj, d7.d<?> dVar) {
            return SearchViewModel.this.new C14631(this.$sq, dVar);
        }

        @Override // f7.a
        public final Object invokeSuspend(Object obj) throws Exception {
            Object objY;
            int i10 = this.label;
            try {
                if (i10 == 0) {
                    k2.c.G(obj);
                    SearchViewModel.this._uiState.setValue(SearchUiState.copy$default((SearchUiState) SearchViewModel.this._uiState.getValue(), null, true, null, SearchViewModelKt.EMPTY_MEDIA_ITEMS, SearchViewModelKt.EMPTY_MEDIA_ITEMS, SearchViewModelKt.EMPTY_CATEGORIES, null, null, null, null, false, null, null, null, this.$sq.getInterpretation(), null, true, 48965, null));
                    ra.c cVar = x0.f19655d;
                    SearchViewModel$executeSmartSearch$1$items$1 searchViewModel$executeSmartSearch$1$items$1 = new SearchViewModel$executeSmartSearch$1$items$1(this.$sq, SearchViewModel.this, null);
                    this.label = 1;
                    objY = m0.y(cVar, searchViewModel$executeSmartSearch$1$items$1, this);
                    e7.a aVar = e7.a.f15033i;
                    if (objY == aVar) {
                        return aVar;
                    }
                } else {
                    if (i10 != 1) {
                        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                    }
                    k2.c.G(obj);
                    objY = obj;
                }
                List listX0 = (List) objY;
                q0 q0Var = SearchViewModel.this._uiState;
                SearchUiState searchUiState = (SearchUiState) SearchViewModel.this._uiState.getValue();
                if (this.$sq.getLimit() != null) {
                    listX0 = kotlin.collections.x.X0(listX0, this.$sq.getLimit().intValue());
                }
                q0Var.setValue(SearchUiState.copy$default(searchUiState, null, false, null, null, null, null, null, null, null, null, false, null, null, null, null, listX0, false, 98301, null));
            } catch (Exception e5) {
                if (e5 instanceof CancellationException) {
                    throw e5;
                }
                SearchViewModel.this._uiState.setValue(SearchUiState.copy$default((SearchUiState) SearchViewModel.this._uiState.getValue(), null, false, null, null, null, null, null, e5.getMessage(), null, null, false, null, null, null, null, null, false, 130941, null));
            }
            return t0.f22605a;
        }

        @Override // r7.p
        public final Object invoke(k0 k0Var, d7.d<? super t0> dVar) {
            return ((C14631) create(k0Var, dVar)).invokeSuspend(t0.f22605a);
        }
    }

    /* JADX INFO: renamed from: com.arflix.tv.ui.screens.search.SearchViewModel$loadDiscoverRows$1, reason: invalid class name and case insensitive filesystem */
    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0002\u001a\u00020\u0001*\u00020\u0000H\n¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"Lka/k0;", "Lx6/t0;", "<anonymous>", "(Lka/k0;)V"}, k = 3, mv = {2, 3, 0})
    @f7.e(c = "com.arflix.tv.ui.screens.search.SearchViewModel$loadDiscoverRows$1", f = "SearchViewModel.kt", l = {133}, m = "invokeSuspend", v = 2)
    public static final class C14641 extends f7.j implements r7.p<k0, d7.d<? super t0>, Object> {
        final /* synthetic */ SearchUiState $state;
        int I$0;
        private /* synthetic */ Object L$0;
        Object L$1;
        Object L$2;
        Object L$3;
        Object L$4;
        Object L$5;
        Object L$6;
        Object L$7;
        int label;
        final /* synthetic */ SearchViewModel this$0;

        /* JADX INFO: renamed from: com.arflix.tv.ui.screens.search.SearchViewModel$loadDiscoverRows$1$1, reason: invalid class name and collision with other inner class name */
        @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0002\u001a\u00020\u0001*\u00020\u0000H\n¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"Lka/k0;", "Lx6/t0;", "<anonymous>", "(Lka/k0;)V"}, k = 3, mv = {2, 3, 0})
        @f7.e(c = "com.arflix.tv.ui.screens.search.SearchViewModel$loadDiscoverRows$1$1", f = "SearchViewModel.kt", l = {158}, m = "invokeSuspend", v = 2)
        public static final class C01561 extends f7.j implements r7.p<k0, d7.d<? super t0>, Object> {
            final /* synthetic */ List<Category> $categories;
            private /* synthetic */ Object L$0;
            Object L$1;
            int label;
            final /* synthetic */ SearchViewModel this$0;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            public C01561(List<Category> list, SearchViewModel searchViewModel, d7.d<? super C01561> dVar) {
                super(2, dVar);
                this.$categories = list;
                this.this$0 = searchViewModel;
            }

            @Override // f7.a
            public final d7.d<t0> create(Object obj, d7.d<?> dVar) {
                C01561 c01561 = new C01561(this.$categories, this.this$0, dVar);
                c01561.L$0 = obj;
                return c01561;
            }

            @Override // f7.a
            public final Object invokeSuspend(Object obj) {
                Object objF;
                k0 k0Var = (k0) this.L$0;
                int i10 = this.label;
                if (i10 == 0) {
                    k2.c.G(obj);
                    List<Category> list = this.$categories;
                    ArrayList arrayList = new ArrayList();
                    Iterator<T> it = list.iterator();
                    while (it.hasNext()) {
                        kotlin.collections.x.b0(arrayList, ((Category) it.next()).getItems());
                    }
                    HashSet hashSet = new HashSet();
                    ArrayList arrayList2 = new ArrayList();
                    for (Object obj2 : arrayList) {
                        MediaItem mediaItem = (MediaItem) obj2;
                        if (hashSet.add(mediaItem.getMediaType() + "_" + mediaItem.getId())) {
                            arrayList2.add(obj2);
                        }
                    }
                    List listX0 = kotlin.collections.x.X0(arrayList2, 60);
                    SearchViewModel searchViewModel = this.this$0;
                    ArrayList arrayList3 = new ArrayList(kotlin.collections.s.U(listX0, 10));
                    Iterator it2 = listX0.iterator();
                    while (it2.hasNext()) {
                        arrayList3.add(m0.e(3, null, k0Var, new SearchViewModel$loadDiscoverRows$1$1$logos$1$1((MediaItem) it2.next(), searchViewModel, null)));
                    }
                    this.L$0 = null;
                    this.L$1 = null;
                    this.label = 1;
                    objF = m0.f(arrayList3, this);
                    e7.a aVar = e7.a.f15033i;
                    if (objF == aVar) {
                        return aVar;
                    }
                } else {
                    if (i10 != 1) {
                        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                    }
                    k2.c.G(obj);
                    objF = obj;
                }
                this.this$0._uiState.setValue(SearchUiState.copy$default((SearchUiState) this.this$0._uiState.getValue(), null, false, null, null, null, null, null, null, null, h0.v0(((SearchUiState) this.this$0._uiState.getValue()).getDiscoverLogoUrls(), h0.A0(kotlin.collections.x.k0((Iterable) objF))), false, null, null, null, null, null, false, 130559, null));
                return t0.f22605a;
            }

            @Override // r7.p
            public final Object invoke(k0 k0Var, d7.d<? super t0> dVar) {
                return ((C01561) create(k0Var, dVar)).invokeSuspend(t0.f22605a);
            }
        }

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public C14641(SearchUiState searchUiState, SearchViewModel searchViewModel, d7.d<? super C14641> dVar) {
            super(2, dVar);
            this.$state = searchUiState;
            this.this$0 = searchViewModel;
        }

        @Override // f7.a
        public final d7.d<t0> create(Object obj, d7.d<?> dVar) {
            C14641 c14641 = new C14641(this.$state, this.this$0, dVar);
            c14641.L$0 = obj;
            return c14641;
        }

        /* JADX WARN: Type inference failed for: r12v1 */
        /* JADX WARN: Type inference failed for: r12v2, types: [boolean, int] */
        /* JADX WARN: Type inference failed for: r12v3 */
        @Override // f7.a
        public final Object invokeSuspend(Object obj) throws Throwable {
            Object objY;
            k0 k0Var = (k0) this.L$0;
            int i10 = this.label;
            try {
                if (i10 == 0) {
                    k2.c.G(obj);
                    DiscoverType selectedType = this.$state.getSelectedType();
                    Genre selectedGenre = this.$state.getSelectedGenre();
                    String strValueOf = selectedGenre != null ? String.valueOf(selectedGenre.getId()) : null;
                    Country selectedCountry = this.$state.getSelectedCountry();
                    String code = selectedCountry != null ? selectedCountry.getCode() : null;
                    ?? r12 = selectedType == DiscoverType.ANIME ? 1 : 0;
                    Locale locale = Locale.US;
                    String str = new SimpleDateFormat("yyyy-MM-dd", locale).format(new Date());
                    Calendar calendar = Calendar.getInstance();
                    calendar.add(6, -90);
                    String str2 = new SimpleDateFormat("yyyy-MM-dd", locale).format(calendar.getTime());
                    calendar.setTime(new Date());
                    calendar.add(1, -1);
                    String str3 = new SimpleDateFormat("yyyy-MM-dd", locale).format(calendar.getTime());
                    ra.c cVar = x0.f19655d;
                    SearchViewModel$loadDiscoverRows$1$categories$1 searchViewModel$loadDiscoverRows$1$categories$1 = new SearchViewModel$loadDiscoverRows$1$categories$1(this.this$0, selectedType, strValueOf, code, r12, str, str3, str2, null);
                    this.L$0 = k0Var;
                    this.L$1 = null;
                    this.L$2 = null;
                    this.L$3 = null;
                    this.L$4 = null;
                    this.L$5 = null;
                    this.L$6 = null;
                    this.L$7 = null;
                    this.I$0 = r12;
                    this.label = 1;
                    objY = m0.y(cVar, searchViewModel$loadDiscoverRows$1$categories$1, this);
                    e7.a aVar = e7.a.f15033i;
                    if (objY == aVar) {
                        return aVar;
                    }
                } else {
                    if (i10 != 1) {
                        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                    }
                    k2.c.G(obj);
                    objY = obj;
                }
                List list = (List) objY;
                this.this$0._uiState.setValue(SearchUiState.copy$default((SearchUiState) this.this$0._uiState.getValue(), null, false, null, null, null, null, null, null, list, null, false, null, null, null, null, null, false, 129791, null));
                m0.p(k0Var, x0.f19655d, 0, new C01561(list, this.this$0, null), 2);
            } catch (Exception unused) {
                this.this$0._uiState.setValue(SearchUiState.copy$default((SearchUiState) this.this$0._uiState.getValue(), null, false, null, null, null, null, null, null, null, null, false, null, null, null, null, null, false, 130047, null));
            }
            return t0.f22605a;
        }

        @Override // r7.p
        public final Object invoke(k0 k0Var, d7.d<? super t0> dVar) {
            return ((C14641) create(k0Var, dVar)).invokeSuspend(t0.f22605a);
        }
    }

    /* JADX INFO: renamed from: com.arflix.tv.ui.screens.search.SearchViewModel$search$1, reason: invalid class name and case insensitive filesystem */
    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0002\u001a\u00020\u0001*\u00020\u0000H\n¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"Lka/k0;", "Lx6/t0;", "<anonymous>", "(Lka/k0;)V"}, k = 3, mv = {2, 3, 0})
    @f7.e(c = "com.arflix.tv.ui.screens.search.SearchViewModel$search$1", f = "SearchViewModel.kt", l = {270, 306}, m = "invokeSuspend", v = 2)
    public static final class C14651 extends f7.j implements r7.p<k0, d7.d<? super t0>, Object> {
        final /* synthetic */ String $query;
        Object L$0;
        Object L$1;
        Object L$2;
        Object L$3;
        Object L$4;
        Object L$5;
        int label;

        /* JADX INFO: renamed from: com.arflix.tv.ui.screens.search.SearchViewModel$search$1$1, reason: invalid class name and collision with other inner class name */
        @Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0005\u001a\u001a\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00030\u0002\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00040\u00020\u0001*\u00020\u0000H\n¢\u0006\u0004\b\u0005\u0010\u0006"}, d2 = {"Lka/k0;", "Lx6/x;", "", "Lcom/arflix/tv/data/model/MediaItem;", "Lcom/arflix/tv/data/model/Category;", "<anonymous>", "(Lka/k0;)Lx6/x;"}, k = 3, mv = {2, 3, 0})
        @f7.e(c = "com.arflix.tv.ui.screens.search.SearchViewModel$search$1$1", f = "SearchViewModel.kt", l = {271}, m = "invokeSuspend", v = 2)
        public static final class C01571 extends f7.j implements r7.p<k0, d7.d<? super x6.x>, Object> {
            final /* synthetic */ String $query;
            int label;
            final /* synthetic */ SearchViewModel this$0;

            /* JADX INFO: renamed from: com.arflix.tv.ui.screens.search.SearchViewModel$search$1$1$1, reason: invalid class name and collision with other inner class name */
            @Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0005\u001a\u001a\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00030\u0002\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00040\u00020\u0001*\u00020\u0000H\n¢\u0006\u0004\b\u0005\u0010\u0006"}, d2 = {"Lka/k0;", "Lx6/x;", "", "Lcom/arflix/tv/data/model/MediaItem;", "Lcom/arflix/tv/data/model/Category;", "<anonymous>", "(Lka/k0;)Lx6/x;"}, k = 3, mv = {2, 3, 0})
            @f7.e(c = "com.arflix.tv.ui.screens.search.SearchViewModel$search$1$1$1", f = "SearchViewModel.kt", l = {300, 300}, m = "invokeSuspend", v = 2)
            public static final class C01581 extends f7.j implements r7.p<k0, d7.d<? super x6.x>, Object> {
                final /* synthetic */ String $query;
                private /* synthetic */ Object L$0;
                Object L$1;
                Object L$2;
                Object L$3;
                int label;
                final /* synthetic */ SearchViewModel this$0;

                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                public C01581(SearchViewModel searchViewModel, String str, d7.d<? super C01581> dVar) {
                    super(2, dVar);
                    this.this$0 = searchViewModel;
                    this.$query = str;
                }

                @Override // f7.a
                public final d7.d<t0> create(Object obj, d7.d<?> dVar) {
                    C01581 c01581 = new C01581(this.this$0, this.$query, dVar);
                    c01581.L$0 = obj;
                    return c01581;
                }

                @Override // f7.a
                public final Object invokeSuspend(Object obj) {
                    p0 p0VarE;
                    Object obj2;
                    k0 k0Var = (k0) this.L$0;
                    int i10 = this.label;
                    e7.a aVar = e7.a.f15033i;
                    if (i10 == 0) {
                        k2.c.G(obj);
                        ka.q0 q0VarE = m0.e(3, null, k0Var, new SearchViewModel$search$1$1$1$mediaDeferred$1(this.this$0, this.$query, null));
                        p0VarE = m0.e(3, null, k0Var, new SearchViewModel$search$1$1$1$peopleDeferred$1(this.this$0, this.$query, null));
                        this.L$0 = null;
                        this.L$1 = null;
                        this.L$2 = p0VarE;
                        this.label = 1;
                        obj = q0VarE.j(this);
                        if (obj != aVar) {
                        }
                        return aVar;
                    }
                    if (i10 != 1) {
                        if (i10 != 2) {
                            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                        }
                        obj2 = this.L$3;
                        k2.c.G(obj);
                        return new x6.x(obj2, obj);
                    }
                    p0VarE = (p0) this.L$2;
                    k2.c.G(obj);
                    this.L$0 = null;
                    this.L$1 = null;
                    this.L$2 = null;
                    this.L$3 = obj;
                    this.label = 2;
                    Object objO = p0VarE.o(this);
                    if (objO != aVar) {
                        obj2 = obj;
                        obj = objO;
                        return new x6.x(obj2, obj);
                    }
                    return aVar;
                }

                @Override // r7.p
                public final Object invoke(k0 k0Var, d7.d<? super x6.x> dVar) {
                    return ((C01581) create(k0Var, dVar)).invokeSuspend(t0.f22605a);
                }
            }

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            public C01571(SearchViewModel searchViewModel, String str, d7.d<? super C01571> dVar) {
                super(2, dVar);
                this.this$0 = searchViewModel;
                this.$query = str;
            }

            @Override // f7.a
            public final d7.d<t0> create(Object obj, d7.d<?> dVar) {
                return new C01571(this.this$0, this.$query, dVar);
            }

            @Override // f7.a
            public final Object invokeSuspend(Object obj) {
                int i10 = this.label;
                if (i10 != 0) {
                    if (i10 != 1) {
                        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                    }
                    k2.c.G(obj);
                    return obj;
                }
                k2.c.G(obj);
                C01581 c01581 = new C01581(this.this$0, this.$query, null);
                this.label = 1;
                Object objC = l0.c(c01581, this);
                e7.a aVar = e7.a.f15033i;
                return objC == aVar ? aVar : objC;
            }

            @Override // r7.p
            public final Object invoke(k0 k0Var, d7.d<? super x6.x> dVar) {
                return ((C01571) create(k0Var, dVar)).invokeSuspend(t0.f22605a);
            }
        }

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public C14651(String str, d7.d<? super C14651> dVar) {
            super(2, dVar);
            this.$query = str;
        }

        @Override // f7.a
        public final d7.d<t0> create(Object obj, d7.d<?> dVar) {
            return SearchViewModel.this.new C14651(this.$query, dVar);
        }

        @Override // f7.a
        public final Object invokeSuspend(Object obj) throws Exception {
            Object objY;
            Object objY2;
            List list;
            List list2;
            List list3;
            List list4;
            int i10 = this.label;
            e7.a aVar = e7.a.f15033i;
            try {
            } catch (Exception e5) {
                if (e5 instanceof CancellationException) {
                    throw e5;
                }
                SearchViewModel.this._uiState.setValue(SearchUiState.copy$default((SearchUiState) SearchViewModel.this._uiState.getValue(), null, false, null, null, null, null, null, e5.getMessage(), null, null, false, null, null, null, null, null, false, 130941, null));
            }
            if (i10 == 0) {
                k2.c.G(obj);
                SearchViewModel.this._uiState.setValue(SearchUiState.copy$default((SearchUiState) SearchViewModel.this._uiState.getValue(), null, true, null, null, null, SearchViewModelKt.EMPTY_CATEGORIES, null, null, null, null, false, null, null, null, null, null, false, 65373, null));
                ra.c cVar = x0.f19655d;
                C01571 c01571 = new C01571(SearchViewModel.this, this.$query, null);
                this.label = 1;
                objY = m0.y(cVar, c01571, this);
                if (objY == aVar) {
                }
                return aVar;
            }
            if (i10 != 1) {
                if (i10 != 2) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                List list5 = (List) this.L$3;
                List list6 = (List) this.L$2;
                List list7 = (List) this.L$1;
                List list8 = (List) this.L$0;
                k2.c.G(obj);
                list2 = list5;
                list3 = list6;
                list = list7;
                list4 = list8;
                objY2 = obj;
                SearchViewModel.this._uiState.setValue(SearchUiState.copy$default((SearchUiState) SearchViewModel.this._uiState.getValue(), null, false, list4, list3, list2, list, (Map) objY2, null, null, null, false, null, null, null, null, null, false, 130945, null));
                return t0.f22605a;
            }
            k2.c.G(obj);
            objY = obj;
            x6.x xVar = (x6.x) objY;
            List list9 = (List) xVar.f22608i;
            List list10 = (List) xVar.f22609l;
            ArrayList arrayList = new ArrayList();
            for (Object obj2 : list9) {
                if (((MediaItem) obj2).getMediaType() == MediaType.MOVIE) {
                    arrayList.add(obj2);
                }
            }
            ArrayList arrayList2 = new ArrayList();
            for (Object obj3 : list9) {
                if (((MediaItem) obj3).getMediaType() == MediaType.TV) {
                    arrayList2.add(obj3);
                }
            }
            ArrayList arrayList3 = new ArrayList();
            Iterator it = list10.iterator();
            while (it.hasNext()) {
                kotlin.collections.x.b0(arrayList3, ((Category) it.next()).getItems());
            }
            ArrayList arrayListI0 = kotlin.collections.x.I0(kotlin.collections.x.I0(kotlin.collections.x.X0(arrayList3, 24), kotlin.collections.x.X0(arrayList, 16)), kotlin.collections.x.X0(arrayList2, 16));
            HashSet hashSet = new HashSet();
            ArrayList arrayList4 = new ArrayList();
            for (Object obj4 : arrayListI0) {
                MediaItem mediaItem = (MediaItem) obj4;
                if (hashSet.add(mediaItem.getMediaType() + "_" + mediaItem.getId())) {
                    arrayList4.add(obj4);
                }
            }
            ra.c cVar2 = x0.f19655d;
            SearchViewModel$search$1$logos$1 searchViewModel$search$1$logos$1 = new SearchViewModel$search$1$logos$1(arrayList4, SearchViewModel.this, null);
            this.L$0 = list9;
            this.L$1 = list10;
            this.L$2 = arrayList;
            this.L$3 = arrayList2;
            this.L$4 = null;
            this.L$5 = null;
            this.label = 2;
            objY2 = m0.y(cVar2, searchViewModel$search$1$logos$1, this);
            if (objY2 != aVar) {
                list = list10;
                list2 = arrayList2;
                list3 = arrayList;
                list4 = list9;
                SearchViewModel.this._uiState.setValue(SearchUiState.copy$default((SearchUiState) SearchViewModel.this._uiState.getValue(), null, false, list4, list3, list2, list, (Map) objY2, null, null, null, false, null, null, null, null, null, false, 130945, null));
                return t0.f22605a;
            }
            return aVar;
        }

        @Override // r7.p
        public final Object invoke(k0 k0Var, d7.d<? super t0> dVar) {
            return ((C14651) create(k0Var, dVar)).invokeSuspend(t0.f22605a);
        }
    }

    @Inject
    public SearchViewModel(MediaRepository mediaRepository) {
        this.mediaRepository = mediaRepository;
        j1 j1VarB = y0.b(new SearchUiState(null, false, null, null, null, null, null, null, null, null, false, null, null, null, null, null, false, 131071, null));
        this._uiState = j1VarB;
        this.uiState = y0.e(j1VarB);
        this.cachedSuggestionQuery = "";
        this.cachedSuggestionResults = SearchViewModelKt.EMPTY_MEDIA_ITEMS;
        this.cachedPeopleQuery = "";
        this.cachedPeopleResults = SearchViewModelKt.EMPTY_CATEGORIES;
        loadDiscoverRows();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final String buildAnimeGenre(String genre) {
        return (genre == null || genre.equals("16")) ? "16" : "16,".concat(genre);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:8:0x0022  */
    /* JADX WARN: Type inference failed for: r3v0, types: [int] */
    /* JADX WARN: Type inference failed for: r3v16, types: [java.lang.Object] */
    /* JADX WARN: Type inference failed for: r3v17 */
    /* JADX WARN: Type inference failed for: r3v27 */
    /* JADX WARN: Type inference failed for: r3v28 */
    /* JADX WARN: Type inference failed for: r3v29 */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object buildRow(java.lang.String r28, com.arflix.tv.ui.screens.search.DiscoverType r29, java.lang.String r30, java.lang.String r31, java.lang.Integer r32, java.lang.String r33, boolean r34, int r35, java.lang.String r36, java.lang.String r37, d7.d<? super com.arflix.tv.data.model.Category> r38) {
        /*
            Method dump skipped, instruction units count: 702
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.arflix.tv.ui.screens.search.SearchViewModel.buildRow(java.lang.String, com.arflix.tv.ui.screens.search.DiscoverType, java.lang.String, java.lang.String, java.lang.Integer, java.lang.String, boolean, int, java.lang.String, java.lang.String, d7.d):java.lang.Object");
    }

    public static /* synthetic */ Object buildRow$default(SearchViewModel searchViewModel, String str, DiscoverType discoverType, String str2, String str3, Integer num, String str4, boolean z, int i10, String str5, String str6, d7.d dVar, int i11, Object obj) {
        if ((i11 & 256) != 0) {
            str5 = null;
        }
        if ((i11 & 512) != 0) {
            str6 = null;
        }
        return searchViewModel.buildRow(str, discoverType, str2, str3, num, str4, z, i10, str5, str6, dVar);
    }

    private final void debounceSearch() {
        v1 v1Var = this.searchJob;
        if (v1Var != null) {
            v1Var.cancel((CancellationException) null);
        }
        this.searchJob = m0.p(z0.h(this), null, 0, new C14621(null), 3);
    }

    private final void executeSmartSearch(SmartQuery sq) {
        v1 v1Var = this.searchJob;
        if (v1Var != null) {
            v1Var.cancel((CancellationException) null);
        }
        this.searchJob = m0.p(z0.h(this), null, 0, new C14631(sq, null), 3);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final List<MediaItem> interleave(List<MediaItem> a10, List<MediaItem> b10) {
        ArrayList arrayList = new ArrayList();
        int iMax = Math.max(a10.size(), b10.size());
        for (int i10 = 0; i10 < iMax; i10++) {
            if (i10 < a10.size()) {
                arrayList.add(a10.get(i10));
            }
            if (i10 < b10.size()) {
                arrayList.add(b10.get(i10));
            }
        }
        return arrayList;
    }

    private final void loadDiscoverRows() {
        v1 v1Var = this.discoverJob;
        if (v1Var != null) {
            v1Var.cancel((CancellationException) null);
        }
        SearchUiState searchUiState = (SearchUiState) this._uiState.getValue();
        this._uiState.setValue(SearchUiState.copy$default(searchUiState, null, false, null, null, null, null, null, null, null, null, true, null, null, null, null, null, false, 130047, null));
        this.discoverJob = m0.p(z0.h(this), null, 0, new C14641(searchUiState, this, null), 3);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final String mapMovieGenreToTvGenre(String genre) {
        if (genre == null) {
            return genre;
        }
        int iHashCode = genre.hashCode();
        if (iHashCode != 1571) {
            if (iHashCode == 1606) {
                return !genre.equals("28") ? genre : "10759";
            }
            if (iHashCode != 55577) {
                return (iHashCode == 46737045 && genre.equals("10752")) ? "10768" : genre;
            }
            if (!genre.equals("878")) {
                return genre;
            }
        } else if (!genre.equals("14")) {
            return genre;
        }
        return "10765";
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final String normalizeForSearch(String text) {
        return kotlin.text.o.L0(kotlin.text.u.O(kotlin.text.u.O(kotlin.text.u.O(kotlin.text.u.O(text.toLowerCase(Locale.ROOT), "&", "and", false), "'", "", false), ":", " ", false), "  ", " ", false)).toString();
    }

    /* JADX WARN: Multi-variable type inference failed */
    private final SmartQuery parseSmartQuery(String raw) {
        SmartQuery smartQuery;
        String string;
        String str;
        String str2;
        List listB;
        String str3;
        String str4;
        Locale locale = Locale.ROOT;
        String string2 = kotlin.text.o.L0(raw.toLowerCase(locale)).toString();
        Map mapT0 = h0.t0(new x6.x("horror", "27"), new x6.x("comedy", "35"), new x6.x("action", "28"), new x6.x("drama", "18"), new x6.x("thriller", "53"), new x6.x("sci-fi", "878"), new x6.x("science fiction", "878"), new x6.x("romance", "10749"), new x6.x("animation", "16"), new x6.x("anime", "16"), new x6.x("documentary", "99"), new x6.x("crime", "80"), new x6.x("fantasy", "14"), new x6.x("adventure", "12"), new x6.x("mystery", "9648"), new x6.x("war", "10752"), new x6.x("western", "37"), new x6.x("family", "10751"), new x6.x("history", "36"));
        kotlin.text.k kVarB = kotlin.text.m.b(SearchRegexes.INSTANCE.getLIKE_MATCH_REGEX(), string2);
        if (kVarB != null) {
            String string3 = kotlin.text.o.L0((String) ((n0) kVarB.b()).get(1)).toString();
            if (string3.length() > 0) {
                str4 = ((Object) String.valueOf(string3.charAt(0)).toUpperCase(locale)) + string3.substring(1);
            } else {
                str4 = string3;
            }
            return new SmartQuery(a0.c.l("Similar to \"", str4, "\""), (kotlin.text.o.T(string2, "show", false) || kotlin.text.o.T(string2, "series", false)) ? DiscoverType.TV_SHOWS : DiscoverType.MOVIES, null, "popularity.desc", null, null, string3);
        }
        if (!kotlin.text.o.T(string2, "top", false) && !kotlin.text.o.T(string2, "best", false) && !kotlin.text.o.T(string2, "popular", false) && !kotlin.text.o.T(string2, "trending", false) && !kotlin.text.o.T(string2, "new", false) && !kotlin.text.o.T(string2, "latest", false)) {
            return null;
        }
        Iterator it = mapT0.entrySet().iterator();
        while (true) {
            if (!it.hasNext()) {
                smartQuery = null;
                string = null;
                str = null;
                break;
            }
            Map.Entry entry = (Map.Entry) it.next();
            string = (String) entry.getKey();
            String str5 = (String) entry.getValue();
            if (kotlin.text.o.T(string2, string, false)) {
                if (string.length() > 0) {
                    StringBuilder sb2 = new StringBuilder();
                    smartQuery = null;
                    sb2.append((Object) String.valueOf(string.charAt(0)).toUpperCase(Locale.ROOT));
                    sb2.append(string.substring(1));
                    string = sb2.toString();
                } else {
                    smartQuery = null;
                }
                str = str5;
            }
        }
        if (str == null && !kotlin.text.o.T(string2, "movie", false) && !kotlin.text.o.T(string2, "show", false) && !kotlin.text.o.T(string2, "series", false) && !kotlin.text.o.T(string2, "film", false) && !kotlin.text.o.T(string2, "trending", false) && !kotlin.text.o.T(string2, "anime", false)) {
            return smartQuery;
        }
        boolean zT = kotlin.text.o.T(string2, "anime", false);
        boolean z = kotlin.text.o.T(string2, "show", false) || kotlin.text.o.T(string2, "series", false);
        boolean z5 = kotlin.text.o.T(string2, "movie", false) || kotlin.text.o.T(string2, "film", false);
        DiscoverType discoverType = zT ? DiscoverType.ANIME : (!z || z5) ? (!z5 || z) ? DiscoverType.ALL : DiscoverType.MOVIES : DiscoverType.TV_SHOWS;
        kotlin.text.k kVarB2 = kotlin.text.m.b(SearchRegexes.INSTANCE.getLIMIT_MATCH_REGEX(), string2);
        Object objR = (kVarB2 == null || (listB = kVarB2.b()) == null || (str3 = (String) ((n0) listB).get(1)) == null) ? smartQuery : kotlin.text.u.R(str3);
        String str6 = (kotlin.text.o.T(string2, "best", false) || kotlin.text.o.T(string2, "top rated", false) || objR != null) ? "vote_average.desc" : (kotlin.text.o.T(string2, "new", false) || kotlin.text.o.T(string2, "latest", false)) ? (z || zT) ? "first_air_date.desc" : "primary_release_date.desc" : "popularity.desc";
        ArrayList arrayList = new ArrayList();
        if (objR != null) {
            arrayList.add("Top " + objR);
        }
        arrayList.add((str6.equals("vote_average.desc") && objR == null) ? "Best" : kotlin.text.o.T(str6, "date", false) ? "Newest" : "Popular");
        if (string != null) {
            arrayList.add(string);
        }
        int i10 = WhenMappings.$EnumSwitchMapping$0[discoverType.ordinal()];
        if (i10 == 1) {
            str2 = "Movies";
        } else if (i10 == 2) {
            str2 = "Series";
        } else if (i10 == 3) {
            str2 = "Anime";
        } else {
            if (i10 != 4) {
                throw new NoWhenBranchMatchedException();
            }
            str2 = "Movies & Series";
        }
        arrayList.add(str2);
        return new SmartQuery(kotlin.collections.x.u0(arrayList, " ", null, null, null, 62), discoverType, str, str6, str6.equals("vote_average.desc") ? 500 : smartQuery, objR, null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final List<MediaItem> sortResults(String query, List<MediaItem> results) {
        final String strNormalizeForSearch = normalizeForSearch(query);
        final Comparator comparator = new Comparator() { // from class: com.arflix.tv.ui.screens.search.SearchViewModel$sortResults$$inlined$compareBy$1
            /* JADX WARN: Multi-variable type inference failed */
            /* JADX WARN: Removed duplicated region for block: B:10:0x003f  */
            /* JADX WARN: Removed duplicated region for block: B:28:0x0091  */
            @Override // java.util.Comparator
            /*
                Code decompiled incorrectly, please refer to instructions dump.
                To view partially-correct add '--show-bad-code' argument
            */
            public final int compare(T r10, T r11) {
                /*
                    r9 = this;
                    r0 = 3
                    java.lang.Integer r0 = java.lang.Integer.valueOf(r0)
                    r1 = 1
                    java.lang.Integer r1 = java.lang.Integer.valueOf(r1)
                    r2 = 0
                    java.lang.Integer r3 = java.lang.Integer.valueOf(r2)
                    r4 = 2
                    java.lang.Integer r4 = java.lang.Integer.valueOf(r4)
                    com.arflix.tv.data.model.MediaItem r10 = (com.arflix.tv.data.model.MediaItem) r10
                    com.arflix.tv.ui.screens.search.SearchViewModel r5 = r9.this$0
                    java.lang.String r10 = r10.getTitle()
                    java.lang.String r10 = com.arflix.tv.ui.screens.search.SearchViewModel.access$normalizeForSearch(r5, r10)
                    java.lang.String r5 = r2
                    boolean r5 = kotlin.jvm.internal.p.a(r10, r5)
                    r6 = 6
                    java.lang.String r7 = " "
                    if (r5 == 0) goto L2d
                    r10 = r3
                    goto L69
                L2d:
                    java.lang.String r5 = r2
                    boolean r5 = kotlin.text.u.P(r10, r5, r2)
                    if (r5 == 0) goto L37
                    r10 = r1
                    goto L69
                L37:
                    java.lang.String r5 = r2
                    boolean r5 = kotlin.text.o.T(r10, r5, r2)
                    if (r5 == 0) goto L41
                L3f:
                    r10 = r4
                    goto L69
                L41:
                    java.lang.String r5 = r2
                    java.lang.String[] r8 = new java.lang.String[]{r7}
                    java.util.List r5 = kotlin.text.o.y0(r5, r8, r2, r6)
                    boolean r8 = r5.isEmpty()
                    if (r8 == 0) goto L52
                    goto L3f
                L52:
                    java.util.Iterator r5 = r5.iterator()
                L56:
                    boolean r8 = r5.hasNext()
                    if (r8 == 0) goto L3f
                    java.lang.Object r8 = r5.next()
                    java.lang.String r8 = (java.lang.String) r8
                    boolean r8 = kotlin.text.o.T(r10, r8, r2)
                    if (r8 != 0) goto L56
                    r10 = r0
                L69:
                    com.arflix.tv.data.model.MediaItem r11 = (com.arflix.tv.data.model.MediaItem) r11
                    com.arflix.tv.ui.screens.search.SearchViewModel r5 = r9.this$0
                    java.lang.String r11 = r11.getTitle()
                    java.lang.String r11 = com.arflix.tv.ui.screens.search.SearchViewModel.access$normalizeForSearch(r5, r11)
                    java.lang.String r5 = r2
                    boolean r5 = kotlin.jvm.internal.p.a(r11, r5)
                    if (r5 == 0) goto L7f
                    r0 = r3
                    goto Lba
                L7f:
                    java.lang.String r3 = r2
                    boolean r3 = kotlin.text.u.P(r11, r3, r2)
                    if (r3 == 0) goto L89
                    r0 = r1
                    goto Lba
                L89:
                    java.lang.String r1 = r2
                    boolean r1 = kotlin.text.o.T(r11, r1, r2)
                    if (r1 == 0) goto L93
                L91:
                    r0 = r4
                    goto Lba
                L93:
                    java.lang.String r1 = r2
                    java.lang.String[] r3 = new java.lang.String[]{r7}
                    java.util.List r1 = kotlin.text.o.y0(r1, r3, r2, r6)
                    boolean r3 = r1.isEmpty()
                    if (r3 == 0) goto La4
                    goto L91
                La4:
                    java.util.Iterator r1 = r1.iterator()
                La8:
                    boolean r3 = r1.hasNext()
                    if (r3 == 0) goto L91
                    java.lang.Object r3 = r1.next()
                    java.lang.String r3 = (java.lang.String) r3
                    boolean r3 = kotlin.text.o.T(r11, r3, r2)
                    if (r3 != 0) goto La8
                Lba:
                    int r10 = com.google.common.util.concurrent.r0.e(r10, r0)
                    return r10
                */
                throw new UnsupportedOperationException("Method not decompiled: com.arflix.tv.ui.screens.search.SearchViewModel$sortResults$$inlined$compareBy$1.compare(java.lang.Object, java.lang.Object):int");
            }
        };
        final Comparator comparator2 = new Comparator() { // from class: com.arflix.tv.ui.screens.search.SearchViewModel$sortResults$$inlined$thenByDescending$1
            /* JADX WARN: Multi-variable type inference failed */
            @Override // java.util.Comparator
            public final int compare(T t2, T t10) {
                int iCompare = comparator.compare(t2, t10);
                if (iCompare != 0) {
                    return iCompare;
                }
                MediaItem mediaItem = (MediaItem) t10;
                boolean z = true;
                boolean z5 = mediaItem.getGenreIds().contains(99) || mediaItem.getGenreIds().contains(10763);
                String title = mediaItem.getTitle();
                Locale locale = Locale.ROOT;
                String lowerCase = title.toLowerCase(locale);
                Float fValueOf = Float.valueOf((z5 || (kotlin.text.o.T(lowerCase, "making of", false) || kotlin.text.o.T(lowerCase, "behind the", false) || kotlin.text.o.T(lowerCase, "featurette", false) || kotlin.text.o.T(lowerCase, "special", false))) ? mediaItem.getPopularity() * 0.05f : mediaItem.getPopularity());
                MediaItem mediaItem2 = (MediaItem) t2;
                boolean z10 = mediaItem2.getGenreIds().contains(99) || mediaItem2.getGenreIds().contains(10763);
                String lowerCase2 = mediaItem2.getTitle().toLowerCase(locale);
                if (!kotlin.text.o.T(lowerCase2, "making of", false) && !kotlin.text.o.T(lowerCase2, "behind the", false) && !kotlin.text.o.T(lowerCase2, "featurette", false) && !kotlin.text.o.T(lowerCase2, "special", false)) {
                    z = false;
                }
                return r0.e(fValueOf, Float.valueOf((z10 || z) ? mediaItem2.getPopularity() * 0.05f : mediaItem2.getPopularity()));
            }
        };
        return kotlin.collections.x.W0(results, new Comparator() { // from class: com.arflix.tv.ui.screens.search.SearchViewModel$sortResults$$inlined$thenByDescending$2
            /* JADX WARN: Multi-variable type inference failed */
            @Override // java.util.Comparator
            public final int compare(T t2, T t10) {
                int iCompare = comparator2.compare(t2, t10);
                if (iCompare != 0) {
                    return iCompare;
                }
                Integer numR = kotlin.text.u.R(((MediaItem) t10).getYear());
                if (numR == null) {
                    numR = num;
                }
                Integer numR2 = kotlin.text.u.R(((MediaItem) t2).getYear());
                return r0.e(numR, numR2 != null ? numR2 : 0);
            }
        });
    }

    public final void addChar(String str) {
        updateQuery(((SearchUiState) this._uiState.getValue()).getQuery() + str);
    }

    public final void clearSearch() {
        v1 v1Var = this.searchJob;
        if (v1Var != null) {
            v1Var.cancel((CancellationException) null);
        }
        this.cachedSuggestionQuery = "";
        this.cachedSuggestionResults = SearchViewModelKt.EMPTY_MEDIA_ITEMS;
        this.cachedPeopleQuery = "";
        this.cachedPeopleResults = SearchViewModelKt.EMPTY_CATEGORIES;
        q0<SearchUiState> q0Var = this._uiState;
        q0Var.setValue(SearchUiState.copy$default((SearchUiState) q0Var.getValue(), "", false, SearchViewModelKt.EMPTY_MEDIA_ITEMS, SearchViewModelKt.EMPTY_MEDIA_ITEMS, SearchViewModelKt.EMPTY_MEDIA_ITEMS, SearchViewModelKt.EMPTY_CATEGORIES, SearchViewModelKt.EMPTY_LOGO_URLS, null, null, null, false, null, null, null, null, SearchViewModelKt.EMPTY_MEDIA_ITEMS, false, 16128, null));
    }

    public final void deleteChar() {
        if (((SearchUiState) this._uiState.getValue()).getQuery().length() > 0) {
            updateQuery(kotlin.text.o.W(1, ((SearchUiState) this._uiState.getValue()).getQuery()));
        }
    }

    public final List<Genre> getGenresForType() {
        int i10 = WhenMappings.$EnumSwitchMapping$0[((SearchUiState) this._uiState.getValue()).getSelectedType().ordinal()];
        if (i10 == 1) {
            return SearchViewModelKt.getMOVIE_GENRES();
        }
        if (i10 == 2) {
            return SearchViewModelKt.getTV_GENRES();
        }
        if (i10 == 3) {
            return SearchViewModelKt.getANIME_GENRES();
        }
        if (i10 == 4) {
            return SearchViewModelKt.getALL_GENRES();
        }
        throw new NoWhenBranchMatchedException();
    }

    public final h1<SearchUiState> getUiState() {
        return this.uiState;
    }

    public final void search() {
        String string = kotlin.text.o.L0(((SearchUiState) this._uiState.getValue()).getQuery()).toString();
        if (string.length() == 0) {
            return;
        }
        SmartQuery smartQuery = parseSmartQuery(string);
        if (smartQuery != null) {
            executeSmartSearch(smartQuery);
            return;
        }
        v1 v1Var = this.searchJob;
        if (v1Var != null) {
            v1Var.cancel((CancellationException) null);
        }
        this.searchJob = m0.p(z0.h(this), null, 0, new C14651(string, null), 3);
    }

    public final void selectCountry(Country country) {
        q0<SearchUiState> q0Var = this._uiState;
        q0Var.setValue(SearchUiState.copy$default((SearchUiState) q0Var.getValue(), null, false, null, null, null, null, null, null, SearchViewModelKt.EMPTY_CATEGORIES, SearchViewModelKt.EMPTY_LOGO_URLS, false, null, null, country, null, null, false, 122111, null));
        loadDiscoverRows();
    }

    public final void selectGenre(Genre genre) {
        q0<SearchUiState> q0Var = this._uiState;
        q0Var.setValue(SearchUiState.copy$default((SearchUiState) q0Var.getValue(), null, false, null, null, null, null, null, null, SearchViewModelKt.EMPTY_CATEGORIES, SearchViewModelKt.EMPTY_LOGO_URLS, false, null, genre, null, null, null, false, 126207, null));
        loadDiscoverRows();
    }

    public final void selectType(DiscoverType type) {
        q0<SearchUiState> q0Var = this._uiState;
        q0Var.setValue(SearchUiState.copy$default((SearchUiState) q0Var.getValue(), null, false, null, null, null, null, null, null, SearchViewModelKt.EMPTY_CATEGORIES, SearchViewModelKt.EMPTY_LOGO_URLS, false, type, null, null, null, null, false, 124159, null));
        loadDiscoverRows();
    }

    public final void setDiscoverFilters(DiscoverType type, Genre genre, Country country) {
        q0<SearchUiState> q0Var = this._uiState;
        q0Var.setValue(SearchUiState.copy$default((SearchUiState) q0Var.getValue(), null, false, null, null, null, null, null, null, SearchViewModelKt.EMPTY_CATEGORIES, SearchViewModelKt.EMPTY_LOGO_URLS, false, type, genre, country, null, null, false, 115967, null));
        loadDiscoverRows();
    }

    public final void updateQuery(String newQuery) {
        q0<SearchUiState> q0Var = this._uiState;
        q0Var.setValue(SearchUiState.copy$default((SearchUiState) q0Var.getValue(), newQuery, false, null, null, null, null, null, null, null, null, false, null, null, null, null, SearchViewModelKt.EMPTY_MEDIA_ITEMS, false, 16382, null));
        if (kotlin.text.o.L0(newQuery).toString().length() != 0) {
            debounceSearch();
            return;
        }
        this.cachedSuggestionQuery = "";
        this.cachedSuggestionResults = SearchViewModelKt.EMPTY_MEDIA_ITEMS;
        this.cachedPeopleQuery = "";
        this.cachedPeopleResults = SearchViewModelKt.EMPTY_CATEGORIES;
        q0<SearchUiState> q0Var2 = this._uiState;
        q0Var2.setValue(SearchUiState.copy$default((SearchUiState) q0Var2.getValue(), "", false, SearchViewModelKt.EMPTY_MEDIA_ITEMS, SearchViewModelKt.EMPTY_MEDIA_ITEMS, SearchViewModelKt.EMPTY_MEDIA_ITEMS, SearchViewModelKt.EMPTY_CATEGORIES, SearchViewModelKt.EMPTY_LOGO_URLS, null, null, null, false, null, null, null, null, SearchViewModelKt.EMPTY_MEDIA_ITEMS, false, 16128, null));
        v1 v1Var = this.searchJob;
        if (v1Var != null) {
            v1Var.cancel((CancellationException) null);
        }
    }
}
