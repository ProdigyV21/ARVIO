package com.arflix.tv.ui.screens.tv.live;

import androidx.media3.exoplayer.analytics.AnalyticsListener;
import androidx.media3.exoplayer.hls.playlist.HlsMediaPlaylist;
import androidx.media3.extractor.text.ttml.TtmlNode;
import androidx.media3.extractor.ts.PsExtractor;
import com.arflix.tv.data.model.IptvChannel;
import com.arflix.tv.data.model.PlaylistGroupKey;
import com.arflix.tv.data.model.SportsAddonCapabilities;
import com.arflix.tv.ui.screens.tv.live.LiveCategoryKt;
import com.arflix.tv.ui.screens.tv.live.LiveColors;
import io.ktor.http.ContentDisposition;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;
import kotlin.Metadata;
import kotlin.NoWhenBranchMatchedException;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000\u009a\u0001\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010 \n\u0002\b\r\n\u0002\u0010\"\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010&\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0011\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010$\n\u0002\b\u0005\u001a\u0019\u0010\u0004\u001a\u00020\u0003*\u00020\u00002\u0006\u0010\u0002\u001a\u00020\u0001¢\u0006\u0004\b\u0004\u0010\u0005\u001a\u0017\u0010\u0007\u001a\u00020\u00012\b\u0010\u0006\u001a\u0004\u0018\u00010\u0001¢\u0006\u0004\b\u0007\u0010\b\u001a\u0015\u0010\t\u001a\u00020\u00012\u0006\u0010\u0006\u001a\u00020\u0001¢\u0006\u0004\b\t\u0010\b\u001a\u0015\u0010\f\u001a\u00020\u000b2\u0006\u0010\n\u001a\u00020\u0001¢\u0006\u0004\b\f\u0010\r\u001a\u0015\u0010\u000f\u001a\u00020\u000e2\u0006\u0010\n\u001a\u00020\u0001¢\u0006\u0004\b\u000f\u0010\u0010\u001a\u001b\u0010\u0012\u001a\u0004\u0018\u00010\u000e2\b\u0010\u0011\u001a\u0004\u0018\u00010\u0001H\u0002¢\u0006\u0004\b\u0012\u0010\u0010\u001a\u0017\u0010\u0013\u001a\u0004\u0018\u00010\u00012\u0006\u0010\n\u001a\u00020\u0001¢\u0006\u0004\b\u0013\u0010\b\u001a\u001d\u0010\u0016\u001a\u00020\u00032\u0006\u0010\u0014\u001a\u00020\u00012\u0006\u0010\u0015\u001a\u00020\u0001¢\u0006\u0004\b\u0016\u0010\u0017\u001a\u0015\u0010\u001a\u001a\u00020\u00192\u0006\u0010\u0018\u001a\u00020\u000b¢\u0006\u0004\b\u001a\u0010\u001b\u001a\u0013\u0010\u001e\u001a\u00020\u001d*\u00020\u001cH\u0002¢\u0006\u0004\b\u001e\u0010\u001f\u001a\u0019\u0010#\u001a\u00020\"*\u00020\u001c2\u0006\u0010!\u001a\u00020 ¢\u0006\u0004\b#\u0010$\u001a\u0019\u0010%\u001a\u00020\"*\u00020\u001c2\u0006\u0010!\u001a\u00020 ¢\u0006\u0004\b%\u0010$\u001a+\u0010)\u001a\b\u0012\u0004\u0012\u00020\"0&2\f\u0010'\u001a\b\u0012\u0004\u0012\u00020\"0&2\u0006\u0010(\u001a\u00020\u0001H\u0000¢\u0006\u0004\b)\u0010*\u001a\u0017\u0010,\u001a\u00020\u00012\u0006\u0010+\u001a\u00020\u0001H\u0007¢\u0006\u0004\b,\u0010-\u001a\u0017\u0010.\u001a\u00020\u00012\u0006\u0010+\u001a\u00020\u0001H\u0007¢\u0006\u0004\b.\u0010-\u001a\u0017\u0010/\u001a\u00020\u00012\u0006\u0010\u0014\u001a\u00020\u0001H\u0002¢\u0006\u0004\b/\u0010\b\u001a\u001f\u00101\u001a\u00020\u00012\u0006\u00100\u001a\u00020\u00012\u0006\u0010\u0014\u001a\u00020\u0001H\u0002¢\u0006\u0004\b1\u00102\u001a#\u00106\u001a\u00020\u00032\u0006\u00103\u001a\u00020\"2\f\u00105\u001a\b\u0012\u0004\u0012\u00020\u000104¢\u0006\u0004\b6\u00107\u001a%\u00106\u001a\u00020\u00032\u0006\u00103\u001a\u00020\u001c2\f\u00105\u001a\b\u0012\u0004\u0012\u00020\u000104H\u0002¢\u0006\u0004\b6\u00108\u001a\u001d\u00109\u001a\u00020\u00012\u0006\u00100\u001a\u00020\u00012\u0006\u0010\u0014\u001a\u00020\u0001¢\u0006\u0004\b9\u00102\u001aK\u0010>\u001a\u00020=2\f\u0010'\u001a\b\u0012\u0004\u0012\u00020\"0&2\u0006\u0010:\u001a\u00020 2\u0006\u0010;\u001a\u00020 2\u000e\b\u0002\u00105\u001a\b\u0012\u0004\u0012\u00020\u0001042\u000e\b\u0002\u0010<\u001a\b\u0012\u0004\u0012\u00020\u00010&¢\u0006\u0004\b>\u0010?\u001aW\u0010>\u001a\u00020=2\f\u0010'\u001a\b\u0012\u0004\u0012\u00020\u001c0&2\f\u0010@\u001a\b\u0012\u0004\u0012\u00020\u0001042\f\u0010A\u001a\b\u0012\u0004\u0012\u00020\u0001042\u000e\b\u0002\u00105\u001a\b\u0012\u0004\u0012\u00020\u0001042\u000e\b\u0002\u0010<\u001a\b\u0012\u0004\u0012\u00020\u00010&¢\u0006\u0004\b>\u0010B\u001a\u0019\u0010D\u001a\u0004\u0018\u00010\u00012\u0006\u0010C\u001a\u00020\u0001H\u0002¢\u0006\u0004\bD\u0010\b\u001aw\u0010J\u001a \u0012\u001c\u0012\u001a\u0012\u0004\u0012\u00020\u0001\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020\u0001\u0012\u0004\u0012\u00020 0F0I0&2:\u0010H\u001a6\u0012\u0004\u0012\u00020\u0001\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020\u0001\u0012\u0004\u0012\u00020 0F0Ej\u001a\u0012\u0004\u0012\u00020\u0001\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020\u0001\u0012\u0004\u0012\u00020 0F`G2\f\u0010<\u001a\b\u0012\u0004\u0012\u00020\u00010&H\u0002¢\u0006\u0004\bJ\u0010K\u001a?\u0010N\u001a\u000e\u0012\u0004\u0012\u00020\u001c\u0012\u0004\u0012\u00020\u00030M2\u0006\u0010L\u001a\u00020\u00012\f\u0010@\u001a\b\u0012\u0004\u0012\u00020\u0001042\f\u0010A\u001a\b\u0012\u0004\u0012\u00020\u000104H\u0002¢\u0006\u0004\bN\u0010O\u001aW\u0010Q\u001a\u00020P2\f\u0010'\u001a\b\u0012\u0004\u0012\u00020\u001c0&2\f\u0010@\u001a\b\u0012\u0004\u0012\u00020\u0001042\f\u0010A\u001a\b\u0012\u0004\u0012\u00020\u0001042\u000e\b\u0002\u00105\u001a\b\u0012\u0004\u0012\u00020\u0001042\u000e\b\u0002\u0010<\u001a\b\u0012\u0004\u0012\u00020\u00010&¢\u0006\u0004\bQ\u0010R\u001a\u0089\u0001\u0010W\u001a\u00020P2\f\u0010'\u001a\b\u0012\u0004\u0012\u00020\u001c0&2\u0006\u0010S\u001a\u00020 2\u001e\u0010U\u001a\u001a\u0012\u0016\u0012\u0014\u0012\u0004\u0012\u00020\u0001\u0012\u0004\u0012\u00020\u0001\u0012\u0004\u0012\u00020 0T0&2\f\u0010@\u001a\b\u0012\u0004\u0012\u00020\u0001042\f\u0010A\u001a\b\u0012\u0004\u0012\u00020\u0001042\u000e\b\u0002\u00105\u001a\b\u0012\u0004\u0012\u00020\u0001042\u000e\b\u0002\u0010<\u001a\b\u0012\u0004\u0012\u00020\u00010&2\b\b\u0002\u0010V\u001a\u00020 ¢\u0006\u0004\bW\u0010X\u001a]\u0010Z\u001a\b\u0012\u0004\u0012\u00020\"0&2\f\u0010'\u001a\b\u0012\u0004\u0012\u00020\u001c0&2\u0006\u0010L\u001a\u00020\u00012\f\u0010@\u001a\b\u0012\u0004\u0012\u00020\u0001042\f\u0010A\u001a\b\u0012\u0004\u0012\u00020\u0001042\u000e\b\u0002\u00105\u001a\b\u0012\u0004\u0012\u00020\u0001042\u0006\u0010Y\u001a\u00020 ¢\u0006\u0004\bZ\u0010[\u001a+\u0010\\\u001a\u00020\u00002\f\u0010'\u001a\b\u0012\u0004\u0012\u00020\"0&2\u000e\b\u0002\u00105\u001a\b\u0012\u0004\u0012\u00020\u000104¢\u0006\u0004\b\\\u0010]\u001a\u001d\u0010_\u001a\u00020\u00012\u0006\u00103\u001a\u00020\"2\u0006\u0010^\u001a\u00020=¢\u0006\u0004\b_\u0010`\u001a=\u0010a\u001a\u000e\u0012\u0004\u0012\u00020\"\u0012\u0004\u0012\u00020\u00030M2\u0006\u0010L\u001a\u00020\u00012\f\u0010@\u001a\b\u0012\u0004\u0012\u00020\u0001042\f\u0010A\u001a\b\u0012\u0004\u0012\u00020\u000104¢\u0006\u0004\ba\u0010O\u001a\u001f\u0010b\u001a\u00020\u00032\u0006\u00103\u001a\u00020\"2\u0006\u0010L\u001a\u00020\u0001H\u0002¢\u0006\u0004\bb\u0010c\"\u001a\u0010d\u001a\b\u0012\u0004\u0012\u00020\u00010&8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\bd\u0010e\"\u0014\u0010g\u001a\u00020f8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\bg\u0010h\"\u0014\u0010i\u001a\u00020f8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\bi\u0010h\"\u001a\u0010j\u001a\b\u0012\u0004\u0012\u00020\u0001048\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\bj\u0010k\" \u0010m\u001a\u000e\u0012\u0004\u0012\u00020\u0001\u0012\u0004\u0012\u00020\u00010l8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\bm\u0010n\" \u0010o\u001a\u000e\u0012\u0004\u0012\u00020\u0001\u0012\u0004\u0012\u00020\u00010l8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\bo\u0010n\" \u0010p\u001a\u000e\u0012\u0004\u0012\u00020\u0001\u0012\u0004\u0012\u00020\u00010l8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\bp\u0010n¨\u0006q"}, d2 = {"Lcom/arflix/tv/ui/screens/tv/live/LiveCategoryIndex;", "", "channelId", "", "isVisibleNonAdultChannel", "(Lcom/arflix/tv/ui/screens/tv/live/LiveCategoryIndex;Ljava/lang/String;)Z", "code", "countryFlag", "(Ljava/lang/String;)Ljava/lang/String;", "countryName", "text", "Lcom/arflix/tv/ui/screens/tv/live/Genre;", "genreFromText", "(Ljava/lang/String;)Lcom/arflix/tv/ui/screens/tv/live/Genre;", "Lcom/arflix/tv/ui/screens/tv/live/Quality;", "qualityFromText", "(Ljava/lang/String;)Lcom/arflix/tv/ui/screens/tv/live/Quality;", "label", "qualityFromLabel", "countryFromText", "group", ContentDisposition.Parameters.Name, "isAdultGroup", "(Ljava/lang/String;Ljava/lang/String;)Z", "genre", "Lcom/arflix/tv/ui/screens/tv/live/LiveColors$Brand;", "brandForGenre", "(Lcom/arflix/tv/ui/screens/tv/live/Genre;)Lcom/arflix/tv/ui/screens/tv/live/LiveColors$Brand;", "Lcom/arflix/tv/data/model/IptvChannel;", "Lcom/arflix/tv/ui/screens/tv/live/ChannelTraits;", "traits", "(Lcom/arflix/tv/data/model/IptvChannel;)Lcom/arflix/tv/ui/screens/tv/live/ChannelTraits;", "", "number", "Lcom/arflix/tv/ui/screens/tv/live/EnrichedChannel;", "enrich", "(Lcom/arflix/tv/data/model/IptvChannel;I)Lcom/arflix/tv/ui/screens/tv/live/EnrichedChannel;", "enrichForFastStartup", "", "channels", "sortOrder", "sortChannelsByConfiguredOrder", "(Ljava/util/List;Ljava/lang/String;)Ljava/util/List;", "raw", "liveCategoryLabel", "(Ljava/lang/String;Landroidx/compose/runtime/Composer;I)Ljava/lang/String;", "liveSectionLabel", "playlistGroupLabel", "playlistId", "playlistGroupKey", "(Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;", "channel", "", "hiddenGroups", "isHiddenPlaylistGroup", "(Lcom/arflix/tv/ui/screens/tv/live/EnrichedChannel;Ljava/util/Set;)Z", "(Lcom/arflix/tv/data/model/IptvChannel;Ljava/util/Set;)Z", "playlistGroupCategoryId", "favoritesCount", "recentCount", "groupOrder", "Lcom/arflix/tv/ui/screens/tv/live/LiveCategoryTree;", "buildCategoryTree", "(Ljava/util/List;IILjava/util/Set;Ljava/util/List;)Lcom/arflix/tv/ui/screens/tv/live/LiveCategoryTree;", "favorites", "recents", "(Ljava/util/List;Ljava/util/Set;Ljava/util/Set;Ljava/util/Set;Ljava/util/List;)Lcom/arflix/tv/ui/screens/tv/live/LiveCategoryTree;", TtmlNode.ATTR_ID, "playlistIdFromGroupCategoryId", "Ljava/util/LinkedHashMap;", "Lx6/x;", "Lkotlin/collections/LinkedHashMap;", "groups", "", "orderPlaylistGroups", "(Ljava/util/LinkedHashMap;Ljava/util/List;)Ljava/util/List;", "categoryId", "Lkotlin/Function1;", "rawCategoryMatcher", "(Ljava/lang/String;Ljava/util/Set;Ljava/util/Set;)Lr7/l;", "Lcom/arflix/tv/ui/screens/tv/live/EnrichedChannels;", "buildFastStartupChannelState", "(Ljava/util/List;Ljava/util/Set;Ljava/util/Set;Ljava/util/Set;Ljava/util/List;)Lcom/arflix/tv/ui/screens/tv/live/EnrichedChannels;", "totalChannelCount", "Lx6/j0;", "playlistGroupCounts", "windowOffset", "buildPagedStartupChannelState", "(Ljava/util/List;ILjava/util/List;Ljava/util/Set;Ljava/util/Set;Ljava/util/Set;Ljava/util/List;I)Lcom/arflix/tv/ui/screens/tv/live/EnrichedChannels;", "limit", "buildInitialCategoryChannels", "(Ljava/util/List;Ljava/lang/String;Ljava/util/Set;Ljava/util/Set;Ljava/util/Set;I)Ljava/util/List;", "buildCategoryIndex", "(Ljava/util/List;Ljava/util/Set;)Lcom/arflix/tv/ui/screens/tv/live/LiveCategoryIndex;", "tree", "bestCategoryIdForChannel", "(Lcom/arflix/tv/ui/screens/tv/live/EnrichedChannel;Lcom/arflix/tv/ui/screens/tv/live/LiveCategoryTree;)Ljava/lang/String;", "categoryMatcher", "matchesCategoryId", "(Lcom/arflix/tv/ui/screens/tv/live/EnrichedChannel;Ljava/lang/String;)Z", "ADULT_KEYWORDS", "Ljava/util/List;", "Lkotlin/text/m;", "TAG_RE", "Lkotlin/text/m;", "TRIM_PUNCT", "KNOWN_COUNTRIES", "Ljava/util/Set;", "", "COUNTRY_ALIASES", "Ljava/util/Map;", "FLAG_SUBSTITUTES", "COUNTRY_NAMES", "app_playRelease"}, k = 2, mv = {2, 3, 0}, xi = 48)
public final class LiveCategoryKt {
    private static final List<String> ADULT_KEYWORDS = t7.a.E("adult", "xxx", "18+", "erot", "nsfw");
    private static final kotlin.text.m TAG_RE = new kotlin.text.m("[|\\-:/,]+");
    private static final kotlin.text.m TRIM_PUNCT = new kotlin.text.m("^[\\s\\-|:•\\u2022]+|[\\s\\-|:•\\u2022]+$");
    private static final Set<String> KNOWN_COUNTRIES = kotlin.collections.r.p0(new String[]{"NL", "UK", "GB", "US", "USA", "DE", "FR", "IT", "ES", "PT", "BE", "TR", "AR", HlsMediaPlaylist.Interstitial.SNAP_TYPE_IN, "BR", "PL", "EX", "SE", "DK", "NO", "FI", "RU", "GR", "RO", "HU", "CZ", "AT", "CH", "IE", "JP", "KR", "CN", "TW", "HK", "MX", "CA", "AU", "NZ", "ZA", "AE", "SA", "EG", "MA", "UA", "BG", "HR", "RS", "SK", "SI", "LT", "LV", "EE", "IL", "EN", "JA", "KO", "ZH", "SV", "DA", "EL", "CS", "HI", "HE", "FA", "AF"});
    private static final Map<String, String> COUNTRY_ALIASES = kotlin.collections.h0.t0(new x6.x("GB", "UK"), new x6.x("USA", "US"));
    private static final Map<String, String> FLAG_SUBSTITUTES = kotlin.collections.h0.t0(new x6.x("EN", "GB"), new x6.x("JA", "JP"), new x6.x("KO", "KR"), new x6.x("ZH", "CN"), new x6.x("SV", "SE"), new x6.x("DA", "DK"), new x6.x("EL", "GR"), new x6.x("CS", "CZ"), new x6.x("HI", HlsMediaPlaylist.Interstitial.SNAP_TYPE_IN), new x6.x("HE", "IL"), new x6.x("FA", "IR"), new x6.x("AR", "SA"), new x6.x("AF", "ZA"));
    private static final Map<String, String> COUNTRY_NAMES = kotlin.collections.h0.t0(new x6.x("NL", "Netherlands"), new x6.x("UK", "UK"), new x6.x("GB", "UK"), new x6.x("US", "USA"), new x6.x("DE", "Germany"), new x6.x("FR", "France"), new x6.x("IT", "Italy"), new x6.x("ES", "Spain"), new x6.x("PT", "Portugal"), new x6.x("BE", "Belgium"), new x6.x("TR", "Turkey"), new x6.x(HlsMediaPlaylist.Interstitial.SNAP_TYPE_IN, "India"), new x6.x("BR", "Brazil"), new x6.x("PL", "Poland"), new x6.x("SE", "Sweden"), new x6.x("DK", "Denmark"), new x6.x("NO", "Norway"), new x6.x("FI", "Finland"), new x6.x("RU", "Russia"), new x6.x("GR", "Greece"), new x6.x("RO", "Romania"), new x6.x("HU", "Hungary"), new x6.x("CZ", "Czechia"), new x6.x("AT", "Austria"), new x6.x("CH", "Switzerland"), new x6.x("IE", "Ireland"), new x6.x("JP", "Japan"), new x6.x("KR", "South Korea"), new x6.x("CN", "China"), new x6.x("TW", "Taiwan"), new x6.x("HK", "Hong Kong"), new x6.x("MX", "Mexico"), new x6.x("CA", "Canada"), new x6.x("AU", "Australia"), new x6.x("NZ", "New Zealand"), new x6.x("ZA", "South Africa"), new x6.x("AE", "UAE"), new x6.x("SA", "Saudi Arabia"), new x6.x("EG", "Egypt"), new x6.x("MA", "Morocco"), new x6.x("UA", "Ukraine"), new x6.x("BG", "Bulgaria"), new x6.x("HR", "Croatia"), new x6.x("RS", "Serbia"), new x6.x("SK", "Slovakia"), new x6.x("SI", "Slovenia"), new x6.x("LT", "Lithuania"), new x6.x("LV", "Latvia"), new x6.x("EE", "Estonia"), new x6.x("IL", "Israel"), new x6.x("EN", "English"), new x6.x("JA", "Japanese"), new x6.x("KO", "Korean"), new x6.x("ZH", "Chinese"), new x6.x("AR", "Arabic"), new x6.x("SV", "Swedish"), new x6.x("DA", "Danish"), new x6.x("EL", "Greek"), new x6.x("CS", "Czech"), new x6.x("HI", "Hindi"), new x6.x("HE", "Hebrew"), new x6.x("FA", "Persian"), new x6.x("AF", "South Africa"));

    @Metadata(k = 3, mv = {2, 3, 0}, xi = 48)
    public static final /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;
        public static final /* synthetic */ int[] $EnumSwitchMapping$1;

        static {
            int[] iArr = new int[Genre.values().length];
            try {
                iArr[Genre.News.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                iArr[Genre.Sports.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                iArr[Genre.Movies.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                iArr[Genre.Series.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                iArr[Genre.Kids.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                iArr[Genre.Music.ordinal()] = 6;
            } catch (NoSuchFieldError unused6) {
            }
            try {
                iArr[Genre.Docs.ordinal()] = 7;
            } catch (NoSuchFieldError unused7) {
            }
            try {
                iArr[Genre.General.ordinal()] = 8;
            } catch (NoSuchFieldError unused8) {
            }
            $EnumSwitchMapping$0 = iArr;
            int[] iArr2 = new int[Quality.values().length];
            try {
                iArr2[Quality.K4.ordinal()] = 1;
            } catch (NoSuchFieldError unused9) {
            }
            try {
                iArr2[Quality.FHD.ordinal()] = 2;
            } catch (NoSuchFieldError unused10) {
            }
            $EnumSwitchMapping$1 = iArr2;
        }
    }

    public static final String bestCategoryIdForChannel(EnrichedChannel enrichedChannel, LiveCategoryTree liveCategoryTree) {
        Object next;
        Object next2;
        List<LiveCategory> children;
        Object next3;
        String id = enrichedChannel.getSource().getId();
        String strPlaylistGroupCategoryId = playlistGroupCategoryId(kotlin.text.o.G0(':', id, id), enrichedChannel.getSource().getGroup());
        if (liveCategoryTree.byId(strPlaylistGroupCategoryId) != null) {
            return strPlaylistGroupCategoryId;
        }
        if (enrichedChannel.isAdult()) {
            return "adult";
        }
        String country = enrichedChannel.getCountry();
        if (country == null || kotlin.text.o.h0(country)) {
            Iterator<T> it = liveCategoryTree.getGlobal().getCategories().iterator();
            while (true) {
                if (!it.hasNext()) {
                    next = null;
                    break;
                }
                next = it.next();
                if (matchesCategoryId(enrichedChannel, ((LiveCategory) next).getId())) {
                    break;
                }
            }
            LiveCategory liveCategory = (LiveCategory) next;
            id = liveCategory != null ? liveCategory.getId() : null;
            return id == null ? TtmlNode.COMBINE_ALL : id;
        }
        Iterator<T> it2 = liveCategoryTree.getCountries().getCategories().iterator();
        while (true) {
            if (!it2.hasNext()) {
                next2 = null;
                break;
            }
            next2 = it2.next();
            if (kotlin.jvm.internal.p.a(((LiveCategory) next2).getId(), country)) {
                break;
            }
        }
        LiveCategory liveCategory2 = (LiveCategory) next2;
        if (liveCategory2 != null && (children = liveCategory2.getChildren()) != null) {
            Iterator<T> it3 = children.iterator();
            while (true) {
                if (!it3.hasNext()) {
                    next3 = null;
                    break;
                }
                next3 = it3.next();
                if (matchesCategoryId(enrichedChannel, ((LiveCategory) next3).getId())) {
                    break;
                }
            }
            LiveCategory liveCategory3 = (LiveCategory) next3;
            if (liveCategory3 != null) {
                id = liveCategory3.getId();
            }
        }
        return id != null ? id : country;
    }

    public static final LiveColors.Brand brandForGenre(Genre genre) {
        switch (WhenMappings.$EnumSwitchMapping$0[genre.ordinal()]) {
            case 1:
                return LiveColors.INSTANCE.getBrandNews();
            case 2:
                return LiveColors.INSTANCE.getBrandSport();
            case 3:
                return LiveColors.INSTANCE.getBrandMovies();
            case 4:
                return LiveColors.INSTANCE.getBrandSeries();
            case 5:
                return LiveColors.INSTANCE.getBrandKids();
            case 6:
                return LiveColors.INSTANCE.getBrandMusic();
            case 7:
                return LiveColors.INSTANCE.getBrandDocs();
            case 8:
                return LiveColors.INSTANCE.getBrandGeneral();
            default:
                throw new NoWhenBranchMatchedException();
        }
    }

    public static final LiveCategoryIndex buildCategoryIndex(List<EnrichedChannel> list, Set<String> set) {
        if (list.isEmpty()) {
            return LiveCategoryIndex.INSTANCE.getEmpty();
        }
        LinkedHashMap linkedHashMap = new LinkedHashMap(list.size());
        LinkedHashMap linkedHashMap2 = new LinkedHashMap();
        LinkedHashSet linkedHashSet = new LinkedHashSet();
        for (EnrichedChannel enrichedChannel : list) {
            linkedHashMap.put(enrichedChannel.getId(), enrichedChannel);
            String id = enrichedChannel.getSource().getId();
            buildCategoryIndex$add(linkedHashMap2, playlistGroupCategoryId(kotlin.text.o.G0(':', id, id), enrichedChannel.getSource().getGroup()), enrichedChannel);
            if (isHiddenPlaylistGroup(enrichedChannel, set)) {
                linkedHashSet.add(enrichedChannel.getId());
            } else if (enrichedChannel.isAdult()) {
                buildCategoryIndex$add(linkedHashMap2, "adult", enrichedChannel);
            } else {
                buildCategoryIndex$add(linkedHashMap2, TtmlNode.COMBINE_ALL, enrichedChannel);
                if (enrichedChannel.getQuality() == Quality.K4) {
                    buildCategoryIndex$add(linkedHashMap2, "g-4k", enrichedChannel);
                }
                Genre genre = enrichedChannel.getGenre();
                int[] iArr = WhenMappings.$EnumSwitchMapping$0;
                int i10 = iArr[genre.ordinal()];
                if (i10 == 1) {
                    buildCategoryIndex$add(linkedHashMap2, "g-news", enrichedChannel);
                } else if (i10 == 2) {
                    buildCategoryIndex$add(linkedHashMap2, "g-sports", enrichedChannel);
                } else if (i10 == 3) {
                    buildCategoryIndex$add(linkedHashMap2, "g-movies", enrichedChannel);
                } else if (i10 == 5) {
                    buildCategoryIndex$add(linkedHashMap2, "g-kids", enrichedChannel);
                } else if (i10 == 6) {
                    buildCategoryIndex$add(linkedHashMap2, "g-music", enrichedChannel);
                } else if (i10 == 7) {
                    buildCategoryIndex$add(linkedHashMap2, "g-docs", enrichedChannel);
                }
                String country = enrichedChannel.getCountry();
                if (country != null && !kotlin.text.o.h0(country)) {
                    buildCategoryIndex$add(linkedHashMap2, country, enrichedChannel);
                    switch (iArr[enrichedChannel.getGenre().ordinal()]) {
                        case 1:
                            buildCategoryIndex$add(linkedHashMap2, country.concat("-news"), enrichedChannel);
                            break;
                        case 2:
                            buildCategoryIndex$add(linkedHashMap2, country.concat("-sports"), enrichedChannel);
                            break;
                        case 3:
                            buildCategoryIndex$add(linkedHashMap2, country.concat("-movies"), enrichedChannel);
                            break;
                        case 4:
                            buildCategoryIndex$add(linkedHashMap2, country.concat("-entertainment"), enrichedChannel);
                            break;
                        case 5:
                            buildCategoryIndex$add(linkedHashMap2, country.concat("-kids"), enrichedChannel);
                            break;
                        case 7:
                            buildCategoryIndex$add(linkedHashMap2, country.concat("-documentary"), enrichedChannel);
                            break;
                        case 8:
                            buildCategoryIndex$add(linkedHashMap2, country.concat("-general"), enrichedChannel);
                            break;
                    }
                    int i11 = WhenMappings.$EnumSwitchMapping$1[enrichedChannel.getQuality().ordinal()];
                    if (i11 == 1) {
                        buildCategoryIndex$add(linkedHashMap2, country.concat("-4k"), enrichedChannel);
                    } else if (i11 == 2) {
                        buildCategoryIndex$add(linkedHashMap2, country.concat("-fhd"), enrichedChannel);
                    }
                }
            }
        }
        LinkedHashMap linkedHashMap3 = new LinkedHashMap(kotlin.collections.i0.q0(linkedHashMap2.size()));
        for (Map.Entry entry : linkedHashMap2.entrySet()) {
            linkedHashMap3.put(entry.getKey(), kotlin.collections.x.c1((List) entry.getValue()));
        }
        return new LiveCategoryIndex(linkedHashMap3, linkedHashMap, linkedHashSet);
    }

    private static final void buildCategoryIndex$add(LinkedHashMap<String, List<EnrichedChannel>> linkedHashMap, String str, EnrichedChannel enrichedChannel) {
        List<EnrichedChannel> listT = linkedHashMap.get(str);
        if (listT == null) {
            listT = androidx.compose.material3.d.t(linkedHashMap, str);
        }
        listT.add(enrichedChannel);
    }

    public static /* synthetic */ LiveCategoryIndex buildCategoryIndex$default(List list, Set set, int i10, Object obj) {
        if ((i10 & 2) != 0) {
            set = kotlin.collections.b0.f19686i;
        }
        return buildCategoryIndex(list, set);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static final LiveCategoryTree buildCategoryTree(List<EnrichedChannel> list, int i10, int i11, Set<String> set, List<String> list2) {
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        LinkedHashMap linkedHashMap2 = new LinkedHashMap();
        LinkedHashMap linkedHashMap3 = new LinkedHashMap();
        HashSet hashSetA1 = kotlin.collections.x.a1(set);
        int i12 = 0;
        int i13 = 0;
        int i14 = 0;
        int i15 = 0;
        int i16 = 0;
        int i17 = 0;
        int i18 = 0;
        int i19 = 0;
        int i20 = 0;
        for (EnrichedChannel enrichedChannel : list) {
            String id = enrichedChannel.getId();
            String strG0 = kotlin.text.o.G0(':', id, id);
            String strPlaylistGroupLabel = playlistGroupLabel(enrichedChannel.getSource().getGroup());
            String strPlaylistGroupKey = playlistGroupKey(strG0, strPlaylistGroupLabel);
            String strPlaylistGroupCategoryId = playlistGroupCategoryId(strG0, enrichedChannel.getSource().getGroup());
            LinkedHashMap linkedHashMap4 = hashSetA1.contains(strPlaylistGroupKey) ? linkedHashMap3 : linkedHashMap2;
            x6.x xVar = (x6.x) linkedHashMap4.get(strPlaylistGroupCategoryId);
            linkedHashMap4.put(strPlaylistGroupCategoryId, new x6.x(strPlaylistGroupLabel, Integer.valueOf((xVar != null ? ((Number) xVar.f22609l).intValue() : 0) + 1)));
            if (!hashSetA1.contains(strPlaylistGroupKey)) {
                if (enrichedChannel.isAdult()) {
                    i19++;
                } else {
                    i20++;
                    Quality quality = enrichedChannel.getQuality();
                    int[] iArr = WhenMappings.$EnumSwitchMapping$1;
                    if (iArr[quality.ordinal()] == 1) {
                        i12++;
                    }
                    Genre genre = enrichedChannel.getGenre();
                    int[] iArr2 = WhenMappings.$EnumSwitchMapping$0;
                    int i21 = iArr2[genre.ordinal()];
                    if (i21 == 1) {
                        i15++;
                    } else if (i21 == 2) {
                        i13++;
                    } else if (i21 == 3) {
                        i14++;
                    } else if (i21 == 5) {
                        i16++;
                    } else if (i21 == 6) {
                        i18++;
                    } else if (i21 == 7) {
                        i17++;
                    }
                    String country = enrichedChannel.getCountry();
                    if (country != null && !kotlin.text.o.h0(country)) {
                        Object countryAccumulator = linkedHashMap.get(country);
                        if (countryAccumulator == null) {
                            countryAccumulator = new CountryAccumulator(0, 0, 0, 0, 0, 0, 0, 0, 0, 0, AnalyticsListener.EVENT_DRM_KEYS_LOADED, null);
                            linkedHashMap.put(country, countryAccumulator);
                        }
                        CountryAccumulator countryAccumulator2 = (CountryAccumulator) countryAccumulator;
                        countryAccumulator2.setTotal(countryAccumulator2.getTotal() + 1);
                        switch (iArr2[enrichedChannel.getGenre().ordinal()]) {
                            case 1:
                                countryAccumulator2.setNews(countryAccumulator2.getNews() + 1);
                                break;
                            case 2:
                                countryAccumulator2.setSports(countryAccumulator2.getSports() + 1);
                                break;
                            case 3:
                                countryAccumulator2.setMovies(countryAccumulator2.getMovies() + 1);
                                break;
                            case 4:
                                countryAccumulator2.setSeries(countryAccumulator2.getSeries() + 1);
                                break;
                            case 5:
                                countryAccumulator2.setKids(countryAccumulator2.getKids() + 1);
                                break;
                            case 7:
                                countryAccumulator2.setDocs(countryAccumulator2.getDocs() + 1);
                                break;
                            case 8:
                                countryAccumulator2.setGeneral(countryAccumulator2.getGeneral() + 1);
                                break;
                        }
                        int i22 = iArr[enrichedChannel.getQuality().ordinal()];
                        if (i22 == 1) {
                            countryAccumulator2.setK4(countryAccumulator2.getK4() + 1);
                        } else if (i22 == 2) {
                            countryAccumulator2.setFhd(countryAccumulator2.getFhd() + 1);
                        }
                    }
                }
            }
        }
        List listE = t7.a.E(new LiveCategory("g-4k", "4K | Ultra HD", i12, CategoryIcon.Grid, null, null, null, null, PsExtractor.VIDEO_STREAM_MASK, null), new LiveCategory("g-sports", "Sports · Global", i13, CategoryIcon.Sport, null, 0 == true ? 1 : 0, null, null, PsExtractor.VIDEO_STREAM_MASK, null), new LiveCategory("g-movies", "Movies · Global", i14, CategoryIcon.Movie, null, null, null, null, PsExtractor.VIDEO_STREAM_MASK, null), new LiveCategory("g-news", "News · Global", i15, CategoryIcon.News, null, 0 == true ? 1 : 0, null, null, PsExtractor.VIDEO_STREAM_MASK, null), new LiveCategory("g-kids", "Kids · Global", i16, CategoryIcon.Kids, null, null, null, null, PsExtractor.VIDEO_STREAM_MASK, null), new LiveCategory("g-docs", "Documentary", i17, CategoryIcon.Docs, null, 0 == true ? 1 : 0, null, null, PsExtractor.VIDEO_STREAM_MASK, null), new LiveCategory("g-music", "Music", i18, CategoryIcon.Music, null, null, null, null, PsExtractor.VIDEO_STREAM_MASK, null));
        ArrayList arrayList = new ArrayList();
        for (Object obj : listE) {
            if (((LiveCategory) obj).getCount() > 0) {
                arrayList.add(obj);
            }
        }
        List<Map.Entry> listW0 = kotlin.collections.x.W0(linkedHashMap.entrySet(), new Comparator() { // from class: com.arflix.tv.ui.screens.tv.live.LiveCategoryKt$buildCategoryTree$$inlined$sortedByDescending$1
            @Override // java.util.Comparator
            public final int compare(T t2, T t10) {
                return com.google.common.util.concurrent.r0.e(Integer.valueOf(((LiveCategoryKt.CountryAccumulator) ((Map.Entry) t10).getValue()).getTotal()), Integer.valueOf(((LiveCategoryKt.CountryAccumulator) ((Map.Entry) t2).getValue()).getTotal()));
            }
        });
        ArrayList arrayList2 = new ArrayList(kotlin.collections.s.U(listW0, 10));
        for (Map.Entry entry : listW0) {
            String str = (String) entry.getKey();
            CountryAccumulator countryAccumulator3 = (CountryAccumulator) entry.getValue();
            z6.c cVarN = t7.a.n();
            buildCategoryTree$lambda$3$0$addChild(cVarN, str, "general", countryAccumulator3.getGeneral());
            buildCategoryTree$lambda$3$0$addChild(cVarN, str, "4k", countryAccumulator3.getK4());
            buildCategoryTree$lambda$3$0$addChild(cVarN, str, "fhd", countryAccumulator3.getFhd());
            buildCategoryTree$lambda$3$0$addChild(cVarN, str, SportsAddonCapabilities.SPORTS_CATEGORY_ROW_ID, countryAccumulator3.getSports());
            buildCategoryTree$lambda$3$0$addChild(cVarN, str, "movies", countryAccumulator3.getMovies());
            buildCategoryTree$lambda$3$0$addChild(cVarN, str, "news", countryAccumulator3.getNews());
            buildCategoryTree$lambda$3$0$addChild(cVarN, str, "kids", countryAccumulator3.getKids());
            buildCategoryTree$lambda$3$0$addChild(cVarN, str, "entertainment", countryAccumulator3.getSeries());
            buildCategoryTree$lambda$3$0$addChild(cVarN, str, "documentary", countryAccumulator3.getDocs());
            arrayList2.add(new LiveCategory(str, countryName(str), countryAccumulator3.getTotal(), CategoryIcon.Country, countryFlag(str), t7.a.e(cVarN), null, null, PsExtractor.AUDIO_STREAM, null));
        }
        List listSingletonList = Collections.singletonList(new LiveCategory("adult", "Adult", i19, CategoryIcon.Lock, null, null, null, null, PsExtractor.VIDEO_STREAM_MASK, null));
        ArrayList arrayList3 = new ArrayList();
        for (Object obj2 : listSingletonList) {
            if (((LiveCategory) obj2).getCount() > 0) {
                arrayList3.add(obj2);
            }
        }
        String str2 = null;
        List listE2 = t7.a.E(new LiveCategory("fav", "Favorites", i10, CategoryIcon.Favorite, null, null, null, str2, PsExtractor.VIDEO_STREAM_MASK, null), new LiveCategory("recent", "Recently Watched", i11, CategoryIcon.Recent, 0 == true ? 1 : 0, 0 == true ? 1 : 0, str2, null, PsExtractor.VIDEO_STREAM_MASK, null), new LiveCategory(TtmlNode.COMBINE_ALL, "All Channels", i20, CategoryIcon.All, null, kotlin.collections.x.I0(kotlin.collections.x.I0(arrayList, arrayList2), arrayList3), null, null, 208, null));
        List<Map.Entry<String, x6.x>> listOrderPlaylistGroups = orderPlaylistGroups(linkedHashMap2, list2);
        ArrayList arrayList4 = new ArrayList(kotlin.collections.s.U(listOrderPlaylistGroups, 10));
        Iterator<T> it = listOrderPlaylistGroups.iterator();
        while (it.hasNext()) {
            Map.Entry entry2 = (Map.Entry) it.next();
            String str3 = (String) entry2.getKey();
            x6.x xVar2 = (x6.x) entry2.getValue();
            arrayList4.add(new LiveCategory(str3, (String) xVar2.f22608i, ((Number) xVar2.f22609l).intValue(), CategoryIcon.Grid, null, null, (String) xVar2.f22608i, playlistIdFromGroupCategoryId(str3), 48, null));
        }
        List<Map.Entry<String, x6.x>> listOrderPlaylistGroups2 = orderPlaylistGroups(linkedHashMap3, list2);
        ArrayList arrayList5 = new ArrayList(kotlin.collections.s.U(listOrderPlaylistGroups2, 10));
        Iterator<T> it2 = listOrderPlaylistGroups2.iterator();
        while (it2.hasNext()) {
            Map.Entry entry3 = (Map.Entry) it2.next();
            String str4 = (String) entry3.getKey();
            x6.x xVar3 = (x6.x) entry3.getValue();
            arrayList5.add(new LiveCategory(str4, (String) xVar3.f22608i, ((Number) xVar3.f22609l).intValue(), CategoryIcon.Grid, null, null, (String) xVar3.f22608i, playlistIdFromGroupCategoryId(str4), 48, null));
        }
        LiveSection liveSection = new LiveSection("playlist", "PLAYLIST", arrayList4);
        kotlin.collections.z zVar = kotlin.collections.z.f19728i;
        return new LiveCategoryTree(listE2, liveSection, new LiveSection("matched", "MATCHED", zVar), new LiveSection("adult", "ADULT", zVar), new LiveSection("hidden", "HIDDEN", arrayList5));
    }

    public static /* synthetic */ LiveCategoryTree buildCategoryTree$default(List list, int i10, int i11, Set set, List list2, int i12, Object obj) {
        if ((i12 & 8) != 0) {
            set = kotlin.collections.b0.f19686i;
        }
        if ((i12 & 16) != 0) {
            list2 = kotlin.collections.z.f19728i;
        }
        return buildCategoryTree((List<EnrichedChannel>) list, i10, i11, (Set<String>) set, (List<String>) list2);
    }

    private static final void buildCategoryTree$lambda$10$0$addChild(List<LiveCategory> list, String str, String str2, int i10) {
        if (i10 <= 0) {
            return;
        }
        String strT = androidx.compose.foundation.c.t(str, "-", str2);
        if (str2.length() > 0) {
            str2 = ((Object) String.valueOf(str2.charAt(0)).toUpperCase(Locale.ROOT)) + str2.substring(1);
        }
        list.add(new LiveCategory(strT, androidx.compose.foundation.c.t(str, " | ", str2), i10, CategoryIcon.SubEntry, null, null, null, null, PsExtractor.VIDEO_STREAM_MASK, null));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final boolean buildCategoryTree$lambda$12(Set set, IptvChannel iptvChannel) {
        return isAdultGroup(iptvChannel.getGroup(), iptvChannel.getName()) || isHiddenPlaylistGroup(iptvChannel, (Set<String>) set);
    }

    private static final void buildCategoryTree$lambda$3$0$addChild(List<LiveCategory> list, String str, String str2, int i10) {
        if (i10 <= 0) {
            return;
        }
        String strT = androidx.compose.foundation.c.t(str, "-", str2);
        if (str2.length() > 0) {
            str2 = ((Object) String.valueOf(str2.charAt(0)).toUpperCase(Locale.ROOT)) + str2.substring(1);
        }
        list.add(new LiveCategory(strT, androidx.compose.foundation.c.t(str, " | ", str2), i10, CategoryIcon.SubEntry, null, null, null, null, PsExtractor.VIDEO_STREAM_MASK, null));
    }

    public static final EnrichedChannels buildFastStartupChannelState(List<IptvChannel> list, Set<String> set, Set<String> set2, Set<String> set3, List<String> list2) {
        if (list.isEmpty()) {
            return EnrichedChannels.INSTANCE.getEmpty();
        }
        ArrayList arrayList = new ArrayList(list.size());
        LinkedHashMap linkedHashMap = new LinkedHashMap(list.size());
        LinkedHashMap linkedHashMap2 = new LinkedHashMap();
        LinkedHashSet linkedHashSet = new LinkedHashSet();
        LinkedHashMap linkedHashMap3 = new LinkedHashMap();
        LinkedHashMap linkedHashMap4 = new LinkedHashMap();
        HashSet hashSetA1 = kotlin.collections.x.a1(set3);
        HashSet hashSetA12 = kotlin.collections.x.a1(set);
        HashSet hashSetA13 = kotlin.collections.x.a1(set2);
        Iterator it = list.iterator();
        int i10 = 0;
        int i11 = 0;
        int i12 = 0;
        int i13 = 0;
        int i14 = 0;
        int i15 = 0;
        while (it.hasNext()) {
            Object next = it.next();
            int i16 = i10 + 1;
            if (i10 < 0) {
                t7.a.Q();
                throw null;
            }
            IptvChannel iptvChannel = (IptvChannel) next;
            EnrichedChannel enrichedChannelEnrichForFastStartup = enrichForFastStartup(iptvChannel, i16);
            Iterator it2 = it;
            String id = iptvChannel.getId();
            String strG0 = kotlin.text.o.G0(':', id, id);
            String strPlaylistGroupLabel = playlistGroupLabel(iptvChannel.getGroup());
            String strPlaylistGroupKey = playlistGroupKey(strG0, strPlaylistGroupLabel);
            int i17 = i11;
            String strPlaylistGroupCategoryId = playlistGroupCategoryId(strG0, iptvChannel.getGroup());
            boolean zContains = hashSetA1.contains(strPlaylistGroupKey);
            arrayList.add(enrichedChannelEnrichForFastStartup);
            linkedHashMap.put(enrichedChannelEnrichForFastStartup.getId(), enrichedChannelEnrichForFastStartup);
            buildFastStartupChannelState$add(linkedHashMap2, strPlaylistGroupCategoryId, enrichedChannelEnrichForFastStartup);
            LinkedHashMap linkedHashMap5 = zContains ? linkedHashMap4 : linkedHashMap3;
            HashSet hashSet = hashSetA1;
            x6.x xVar = (x6.x) linkedHashMap5.get(strPlaylistGroupCategoryId);
            linkedHashMap5.put(strPlaylistGroupCategoryId, new x6.x(strPlaylistGroupLabel, Integer.valueOf((xVar != null ? ((Number) xVar.f22609l).intValue() : 0) + 1)));
            if (zContains) {
                linkedHashSet.add(enrichedChannelEnrichForFastStartup.getId());
            } else if (enrichedChannelEnrichForFastStartup.isAdult()) {
                i12++;
                buildFastStartupChannelState$add(linkedHashMap2, "adult", enrichedChannelEnrichForFastStartup);
            } else {
                i14++;
                buildFastStartupChannelState$add(linkedHashMap2, TtmlNode.COMBINE_ALL, enrichedChannelEnrichForFastStartup);
                if (hashSetA12.contains(enrichedChannelEnrichForFastStartup.getId())) {
                    i15++;
                }
                if (hashSetA13.contains(enrichedChannelEnrichForFastStartup.getId())) {
                    i13++;
                }
                if (enrichedChannelEnrichForFastStartup.getQuality() == Quality.K4) {
                    i11 = i17 + 1;
                    buildFastStartupChannelState$add(linkedHashMap2, "g-4k", enrichedChannelEnrichForFastStartup);
                } else {
                    i11 = i17;
                }
                String country = enrichedChannelEnrichForFastStartup.getCountry();
                if (country != null) {
                    if (kotlin.text.o.h0(country)) {
                        country = null;
                    }
                    if (country != null) {
                        buildFastStartupChannelState$add(linkedHashMap2, country, enrichedChannelEnrichForFastStartup);
                    }
                }
                it = it2;
                i10 = i16;
                hashSetA1 = hashSet;
            }
            i11 = i17;
            it = it2;
            i10 = i16;
            hashSetA1 = hashSet;
        }
        List listSingletonList = Collections.singletonList(new LiveCategory("g-4k", "4K | Ultra HD", i11, CategoryIcon.Grid, null, null, null, null, PsExtractor.VIDEO_STREAM_MASK, null));
        ArrayList arrayList2 = new ArrayList();
        for (Object obj : listSingletonList) {
            if (((LiveCategory) obj).getCount() > 0) {
                arrayList2.add(obj);
            }
        }
        List listSingletonList2 = Collections.singletonList(new LiveCategory("adult", "Adult", i12, CategoryIcon.Lock, null, null, null, null, PsExtractor.VIDEO_STREAM_MASK, null));
        ArrayList arrayList3 = new ArrayList();
        for (Object obj2 : listSingletonList2) {
            if (((LiveCategory) obj2).getCount() > 0) {
                arrayList3.add(obj2);
            }
        }
        List listE = t7.a.E(new LiveCategory("fav", "Favorites", i15, CategoryIcon.Favorite, null, null, null, null, PsExtractor.VIDEO_STREAM_MASK, null), new LiveCategory("recent", "Recently Watched", i13, CategoryIcon.Recent, null, null, null, null, PsExtractor.VIDEO_STREAM_MASK, null), new LiveCategory(TtmlNode.COMBINE_ALL, "All Channels", i14, CategoryIcon.All, null, kotlin.collections.x.I0(arrayList2, arrayList3), null, null, 208, null));
        List<Map.Entry<String, x6.x>> listOrderPlaylistGroups = orderPlaylistGroups(linkedHashMap3, list2);
        ArrayList arrayList4 = new ArrayList(kotlin.collections.s.U(listOrderPlaylistGroups, 10));
        Iterator<T> it3 = listOrderPlaylistGroups.iterator();
        while (it3.hasNext()) {
            Map.Entry entry = (Map.Entry) it3.next();
            String str = (String) entry.getKey();
            x6.x xVar2 = (x6.x) entry.getValue();
            arrayList4.add(new LiveCategory(str, (String) xVar2.f22608i, ((Number) xVar2.f22609l).intValue(), CategoryIcon.Grid, null, null, (String) xVar2.f22608i, playlistIdFromGroupCategoryId(str), 48, null));
        }
        List<Map.Entry<String, x6.x>> listOrderPlaylistGroups2 = orderPlaylistGroups(linkedHashMap4, list2);
        ArrayList arrayList5 = new ArrayList(kotlin.collections.s.U(listOrderPlaylistGroups2, 10));
        Iterator<T> it4 = listOrderPlaylistGroups2.iterator();
        while (it4.hasNext()) {
            Map.Entry entry2 = (Map.Entry) it4.next();
            String str2 = (String) entry2.getKey();
            x6.x xVar3 = (x6.x) entry2.getValue();
            arrayList5.add(new LiveCategory(str2, (String) xVar3.f22608i, ((Number) xVar3.f22609l).intValue(), CategoryIcon.Grid, null, null, (String) xVar3.f22608i, playlistIdFromGroupCategoryId(str2), 48, null));
        }
        LiveSection liveSection = new LiveSection("playlist", "PLAYLIST", arrayList4);
        kotlin.collections.z zVar = kotlin.collections.z.f19728i;
        LiveCategoryTree liveCategoryTree = new LiveCategoryTree(listE, liveSection, new LiveSection("matched", "MATCHED", zVar), new LiveSection("adult", "ADULT", zVar), new LiveSection("hidden", "HIDDEN", arrayList5));
        LinkedHashMap linkedHashMap6 = new LinkedHashMap(kotlin.collections.i0.q0(linkedHashMap2.size()));
        for (Map.Entry entry3 : linkedHashMap2.entrySet()) {
            linkedHashMap6.put(entry3.getKey(), kotlin.collections.x.c1((List) entry3.getValue()));
        }
        return new EnrichedChannels(arrayList, liveCategoryTree, new LiveCategoryIndex(linkedHashMap6, linkedHashMap, linkedHashSet));
    }

    private static final void buildFastStartupChannelState$add(LinkedHashMap<String, List<EnrichedChannel>> linkedHashMap, String str, EnrichedChannel enrichedChannel) {
        List<EnrichedChannel> listT = linkedHashMap.get(str);
        if (listT == null) {
            listT = androidx.compose.material3.d.t(linkedHashMap, str);
        }
        listT.add(enrichedChannel);
    }

    public static /* synthetic */ EnrichedChannels buildFastStartupChannelState$default(List list, Set set, Set set2, Set set3, List list2, int i10, Object obj) {
        if ((i10 & 8) != 0) {
            set3 = kotlin.collections.b0.f19686i;
        }
        if ((i10 & 16) != 0) {
            list2 = kotlin.collections.z.f19728i;
        }
        return buildFastStartupChannelState(list, set, set2, set3, list2);
    }

    /* JADX WARN: Removed duplicated region for block: B:94:0x01c0  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static final java.util.List<com.arflix.tv.ui.screens.tv.live.EnrichedChannel> buildInitialCategoryChannels(java.util.List<com.arflix.tv.data.model.IptvChannel> r7, java.lang.String r8, java.util.Set<java.lang.String> r9, java.util.Set<java.lang.String> r10, java.util.Set<java.lang.String> r11, int r12) {
        /*
            Method dump skipped, instruction units count: 496
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.arflix.tv.ui.screens.tv.live.LiveCategoryKt.buildInitialCategoryChannels(java.util.List, java.lang.String, java.util.Set, java.util.Set, java.util.Set, int):java.util.List");
    }

    public static /* synthetic */ List buildInitialCategoryChannels$default(List list, String str, Set set, Set set2, Set set3, int i10, int i11, Object obj) {
        if ((i11 & 16) != 0) {
            set3 = kotlin.collections.b0.f19686i;
        }
        return buildInitialCategoryChannels(list, str, set, set2, set3, i10);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static final EnrichedChannels buildPagedStartupChannelState(List<IptvChannel> list, int i10, List<x6.j0> list2, Set<String> set, Set<String> set2, Set<String> set3, List<String> list3, int i11) {
        ArrayList<EnrichedChannel> arrayList = new ArrayList(kotlin.collections.s.U(list, 10));
        Iterator<T> it = list.iterator();
        int i12 = 0;
        while (true) {
            boolean z = true;
            if (!it.hasNext()) {
                LinkedHashMap linkedHashMap = new LinkedHashMap(arrayList.size());
                LinkedHashMap linkedHashMap2 = new LinkedHashMap();
                LinkedHashSet linkedHashSet = new LinkedHashSet();
                HashSet hashSetA1 = kotlin.collections.x.a1(set3);
                for (EnrichedChannel enrichedChannel : arrayList) {
                    linkedHashMap.put(enrichedChannel.getId(), enrichedChannel);
                    if (set.contains(enrichedChannel.getId())) {
                        buildPagedStartupChannelState$add(linkedHashMap2, "fav", enrichedChannel);
                    }
                    if (set2.contains(enrichedChannel.getId())) {
                        buildPagedStartupChannelState$add(linkedHashMap2, "recent", enrichedChannel);
                    }
                    String id = enrichedChannel.getSource().getId();
                    String strG0 = kotlin.text.o.G0(':', id, id);
                    String strPlaylistGroupKey = playlistGroupKey(strG0, enrichedChannel.getSource().getGroup());
                    boolean z5 = z;
                    buildPagedStartupChannelState$add(linkedHashMap2, playlistGroupCategoryId(strG0, enrichedChannel.getSource().getGroup()), enrichedChannel);
                    if (hashSetA1.contains(strPlaylistGroupKey)) {
                        linkedHashSet.add(enrichedChannel.getId());
                    } else if (enrichedChannel.isAdult()) {
                        buildPagedStartupChannelState$add(linkedHashMap2, "adult", enrichedChannel);
                    } else {
                        buildPagedStartupChannelState$add(linkedHashMap2, TtmlNode.COMBINE_ALL, enrichedChannel);
                        if (enrichedChannel.getQuality() == Quality.K4) {
                            buildPagedStartupChannelState$add(linkedHashMap2, "g-4k", enrichedChannel);
                        }
                        String country = enrichedChannel.getCountry();
                        if (country != null) {
                            if (kotlin.text.o.h0(country)) {
                                country = null;
                            }
                            if (country != null) {
                                buildPagedStartupChannelState$add(linkedHashMap2, country, enrichedChannel);
                            }
                        }
                    }
                    z = z5;
                }
                boolean z10 = z;
                LinkedHashMap linkedHashMap3 = new LinkedHashMap();
                LinkedHashMap linkedHashMap4 = new LinkedHashMap();
                int iIntValue = 0;
                int i13 = 0;
                for (x6.j0 j0Var : list2) {
                    String str = (String) j0Var.f22587i;
                    String str2 = (String) j0Var.f22588l;
                    int iIntValue2 = ((Number) j0Var.f22589m).intValue();
                    String strPlaylistGroupLabel = playlistGroupLabel(str2);
                    LinkedHashMap linkedHashMap5 = linkedHashMap2;
                    String strPlaylistGroupKey2 = playlistGroupKey(str, strPlaylistGroupLabel);
                    String strPlaylistGroupCategoryId = playlistGroupCategoryId(str, strPlaylistGroupLabel);
                    if (hashSetA1.contains(strPlaylistGroupKey2)) {
                        i13 += iIntValue2;
                        linkedHashMap4.put(strPlaylistGroupCategoryId, new x6.x(strPlaylistGroupLabel, Integer.valueOf(iIntValue2)));
                    } else {
                        iIntValue += iIntValue2;
                        linkedHashMap3.put(strPlaylistGroupCategoryId, new x6.x(strPlaylistGroupLabel, Integer.valueOf(iIntValue2)));
                    }
                    linkedHashMap2 = linkedHashMap5;
                }
                LinkedHashMap linkedHashMap6 = linkedHashMap2;
                Integer numValueOf = i10 > 0 ? Integer.valueOf(i10) : null;
                if (numValueOf != null) {
                    iIntValue = numValueOf.intValue();
                }
                int i14 = iIntValue;
                int size = set.size();
                CategoryIcon categoryIcon = CategoryIcon.Favorite;
                int i15 = PsExtractor.VIDEO_STREAM_MASK;
                kotlin.jvm.internal.h hVar = null;
                String str3 = null;
                String str4 = null;
                LiveCategory liveCategory = new LiveCategory("fav", "Favorites", size, categoryIcon, str3, null, null, str4, i15, hVar);
                LiveCategory liveCategory2 = new LiveCategory("recent", "Recently Watched", set2.size(), CategoryIcon.Recent, str3, 0 == true ? 1 : 0, 0 == true ? 1 : 0, str4, i15, hVar);
                CategoryIcon categoryIcon2 = CategoryIcon.All;
                LiveCategory liveCategory3 = new LiveCategory("g-4k", "4K | Ultra HD", 0, CategoryIcon.Grid, 0 == true ? 1 : 0, 0 == true ? 1 : 0, str4, null, PsExtractor.VIDEO_STREAM_MASK, null);
                LiveCategory liveCategory4 = new LiveCategory("adult", "Adult", i13, CategoryIcon.Lock, null, null, null, null, PsExtractor.VIDEO_STREAM_MASK, null);
                LiveCategory[] liveCategoryArr = new LiveCategory[2];
                liveCategoryArr[0] = liveCategory3;
                liveCategoryArr[z10 ? 1 : 0] = liveCategory4;
                List listE = t7.a.E(liveCategoryArr);
                ArrayList arrayList2 = new ArrayList();
                for (Object obj : listE) {
                    if (((LiveCategory) obj).getCount() > 0) {
                        arrayList2.add(obj);
                    }
                }
                LiveCategory liveCategory5 = new LiveCategory(TtmlNode.COMBINE_ALL, "All Channels", i14, categoryIcon2, null, arrayList2, null, null, 208, null);
                LiveCategory[] liveCategoryArr2 = new LiveCategory[3];
                liveCategoryArr2[0] = liveCategory;
                liveCategoryArr2[z10 ? 1 : 0] = liveCategory2;
                liveCategoryArr2[2] = liveCategory5;
                List listE2 = t7.a.E(liveCategoryArr2);
                List<Map.Entry<String, x6.x>> listOrderPlaylistGroups = orderPlaylistGroups(linkedHashMap3, list3);
                ArrayList arrayList3 = new ArrayList(kotlin.collections.s.U(listOrderPlaylistGroups, 10));
                Iterator<T> it2 = listOrderPlaylistGroups.iterator();
                while (it2.hasNext()) {
                    Map.Entry entry = (Map.Entry) it2.next();
                    String str5 = (String) entry.getKey();
                    x6.x xVar = (x6.x) entry.getValue();
                    arrayList3.add(new LiveCategory(str5, (String) xVar.f22608i, ((Number) xVar.f22609l).intValue(), CategoryIcon.Grid, null, null, (String) xVar.f22608i, playlistIdFromGroupCategoryId(str5), 48, null));
                }
                List<Map.Entry<String, x6.x>> listOrderPlaylistGroups2 = orderPlaylistGroups(linkedHashMap4, list3);
                ArrayList arrayList4 = new ArrayList(kotlin.collections.s.U(listOrderPlaylistGroups2, 10));
                Iterator<T> it3 = listOrderPlaylistGroups2.iterator();
                while (it3.hasNext()) {
                    Map.Entry entry2 = (Map.Entry) it3.next();
                    String str6 = (String) entry2.getKey();
                    x6.x xVar2 = (x6.x) entry2.getValue();
                    arrayList4.add(new LiveCategory(str6, (String) xVar2.f22608i, ((Number) xVar2.f22609l).intValue(), CategoryIcon.Grid, null, null, (String) xVar2.f22608i, playlistIdFromGroupCategoryId(str6), 48, null));
                }
                LiveSection liveSection = new LiveSection("playlist", "PLAYLIST", arrayList3);
                kotlin.collections.z zVar = kotlin.collections.z.f19728i;
                LiveCategoryTree liveCategoryTree = new LiveCategoryTree(listE2, liveSection, new LiveSection("matched", "MATCHED", zVar), new LiveSection("adult", "ADULT", zVar), new LiveSection("hidden", "HIDDEN", arrayList4));
                LinkedHashMap linkedHashMap7 = new LinkedHashMap(kotlin.collections.i0.q0(linkedHashMap6.size()));
                for (Map.Entry entry3 : linkedHashMap6.entrySet()) {
                    linkedHashMap7.put(entry3.getKey(), kotlin.collections.x.c1((List) entry3.getValue()));
                }
                return new EnrichedChannels(arrayList, liveCategoryTree, new LiveCategoryIndex(linkedHashMap7, linkedHashMap, linkedHashSet));
            }
            Object next = it.next();
            int i16 = i12 + 1;
            if (i12 < 0) {
                t7.a.Q();
                throw null;
            }
            arrayList.add(enrichForFastStartup((IptvChannel) next, i11 + i12 + 1));
            i12 = i16;
        }
    }

    private static final void buildPagedStartupChannelState$add(LinkedHashMap<String, List<EnrichedChannel>> linkedHashMap, String str, EnrichedChannel enrichedChannel) {
        List<EnrichedChannel> listT = linkedHashMap.get(str);
        if (listT == null) {
            listT = androidx.compose.material3.d.t(linkedHashMap, str);
        }
        listT.add(enrichedChannel);
    }

    public static /* synthetic */ EnrichedChannels buildPagedStartupChannelState$default(List list, int i10, List list2, Set set, Set set2, Set set3, List list3, int i11, int i12, Object obj) {
        if ((i12 & 32) != 0) {
            set3 = kotlin.collections.b0.f19686i;
        }
        return buildPagedStartupChannelState(list, i10, list2, set, set2, set3, (i12 & 64) != 0 ? kotlin.collections.z.f19728i : list3, (i12 & 128) != 0 ? 0 : i11);
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
    /* JADX WARN: Removed duplicated region for block: B:100:0x0198  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static final r7.l<com.arflix.tv.ui.screens.tv.live.EnrichedChannel, java.lang.Boolean> categoryMatcher(java.lang.String r2, java.util.Set<java.lang.String> r3, java.util.Set<java.lang.String> r4) {
        /*
            Method dump skipped, instruction units count: 482
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.arflix.tv.ui.screens.tv.live.LiveCategoryKt.categoryMatcher(java.lang.String, java.util.Set, java.util.Set):r7.l");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final boolean categoryMatcher$lambda$0(EnrichedChannel enrichedChannel) {
        return !enrichedChannel.isAdult();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final boolean categoryMatcher$lambda$1(Set set, EnrichedChannel enrichedChannel) {
        return set.contains(enrichedChannel.getId()) && !enrichedChannel.isAdult();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final boolean categoryMatcher$lambda$10(EnrichedChannel enrichedChannel) {
        return enrichedChannel.getGenre() == Genre.Docs && !enrichedChannel.isAdult();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final boolean categoryMatcher$lambda$11(EnrichedChannel enrichedChannel) {
        return enrichedChannel.getGenre() == Genre.Music && !enrichedChannel.isAdult();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final boolean categoryMatcher$lambda$13(String str, EnrichedChannel enrichedChannel) {
        return kotlin.jvm.internal.p.a(enrichedChannel.getCountry(), str) && !enrichedChannel.isAdult();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final boolean categoryMatcher$lambda$14(String str, Genre genre, Quality quality, EnrichedChannel enrichedChannel) {
        if (!kotlin.jvm.internal.p.a(enrichedChannel.getCountry(), str) || enrichedChannel.isAdult()) {
            return false;
        }
        if (genre == null || enrichedChannel.getGenre() == genre) {
            return quality == null || enrichedChannel.getQuality() == quality;
        }
        return false;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final boolean categoryMatcher$lambda$15(EnrichedChannel enrichedChannel) {
        return false;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final boolean categoryMatcher$lambda$2(Set set, EnrichedChannel enrichedChannel) {
        return set.contains(enrichedChannel.getId()) && !enrichedChannel.isAdult();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final boolean categoryMatcher$lambda$4(String str, EnrichedChannel enrichedChannel) {
        String id = enrichedChannel.getSource().getId();
        return kotlin.jvm.internal.p.a(playlistGroupCategoryId(kotlin.text.o.G0(':', id, id), enrichedChannel.getSource().getGroup()), str);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final boolean categoryMatcher$lambda$5(EnrichedChannel enrichedChannel) {
        return enrichedChannel.getQuality() == Quality.K4 && !enrichedChannel.isAdult();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final boolean categoryMatcher$lambda$6(EnrichedChannel enrichedChannel) {
        return enrichedChannel.getGenre() == Genre.Sports && !enrichedChannel.isAdult();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final boolean categoryMatcher$lambda$7(EnrichedChannel enrichedChannel) {
        return enrichedChannel.getGenre() == Genre.Movies && !enrichedChannel.isAdult();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final boolean categoryMatcher$lambda$8(EnrichedChannel enrichedChannel) {
        return enrichedChannel.getGenre() == Genre.News && !enrichedChannel.isAdult();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final boolean categoryMatcher$lambda$9(EnrichedChannel enrichedChannel) {
        return enrichedChannel.getGenre() == Genre.Kids && !enrichedChannel.isAdult();
    }

    public static final String countryFlag(String str) {
        if (str == null || kotlin.text.o.h0(str) || str.length() != 2) {
            return "🌐";
        }
        Map<String, String> map = FLAG_SUBSTITUTES;
        Locale locale = Locale.ROOT;
        String upperCase = map.get(str.toUpperCase(locale));
        if (upperCase == null) {
            upperCase = str.toUpperCase(locale);
        }
        ArrayList arrayList = new ArrayList(upperCase.length());
        for (int i10 = 0; i10 < upperCase.length(); i10++) {
            arrayList.add(new String(Character.toChars(upperCase.charAt(i10) - 3675)));
        }
        return kotlin.collections.x.u0(arrayList, "", null, null, null, 62);
    }

    public static final String countryFromText(String str) {
        List listH = TAG_RE.h(str);
        ArrayList<String> arrayList = new ArrayList(kotlin.collections.s.U(listH, 10));
        Iterator it = listH.iterator();
        while (it.hasNext()) {
            arrayList.add(kotlin.text.o.M0(kotlin.text.o.L0((String) it.next()).toString(), '[', ']', '(', ')'));
        }
        for (String str2 : arrayList) {
            int length = str2.length();
            if (2 <= length && length < 4) {
                String upperCase = str2.toUpperCase(Locale.ROOT);
                String str3 = COUNTRY_ALIASES.get(upperCase);
                if (str3 != null) {
                    upperCase = str3;
                }
                if (upperCase.length() == 2 && KNOWN_COUNTRIES.contains(upperCase)) {
                    return upperCase;
                }
            }
        }
        String upperCase2 = kotlin.text.o.I0(3, kotlin.text.o.L0(str).toString()).toUpperCase(Locale.ROOT);
        String strI0 = kotlin.text.o.I0(2, upperCase2);
        if (KNOWN_COUNTRIES.contains(strI0)) {
            String str4 = COUNTRY_ALIASES.get(strI0);
            return str4 == null ? strI0 : str4;
        }
        Map<String, String> map = COUNTRY_ALIASES;
        if (map.containsKey(upperCase2)) {
            return map.get(upperCase2);
        }
        return null;
    }

    public static final String countryName(String str) {
        String str2 = COUNTRY_NAMES.get(str.toUpperCase(Locale.ROOT));
        return str2 == null ? str : str2;
    }

    public static final EnrichedChannel enrich(IptvChannel iptvChannel, int i10) {
        ChannelTraits channelTraitsTraits = traits(iptvChannel);
        return new EnrichedChannel(iptvChannel, i10, channelTraitsTraits.getCountry(), channelTraitsTraits.getGenre(), channelTraitsTraits.getQuality(), channelTraitsTraits.getLang(), channelTraitsTraits.m6375getBrandBg0d7_KjU(), channelTraitsTraits.m6376getBrandFg0d7_KjU(), channelTraitsTraits.isAdult(), null);
    }

    /* JADX WARN: Removed duplicated region for block: B:13:0x002d  */
    /* JADX WARN: Removed duplicated region for block: B:23:0x0054  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static final com.arflix.tv.ui.screens.tv.live.EnrichedChannel enrichForFastStartup(com.arflix.tv.data.model.IptvChannel r16, int r17) {
        /*
            java.lang.String r0 = r16.getCountry()
            r1 = 0
            if (r0 == 0) goto L2d
            java.lang.CharSequence r0 = kotlin.text.o.L0(r0)
            java.lang.String r0 = r0.toString()
            if (r0 == 0) goto L2d
            java.util.Locale r2 = java.util.Locale.ROOT
            java.lang.String r0 = r0.toUpperCase(r2)
            java.util.Map<java.lang.String, java.lang.String> r2 = com.arflix.tv.ui.screens.tv.live.LiveCategoryKt.COUNTRY_ALIASES
            java.lang.Object r2 = r2.get(r0)
            java.lang.String r2 = (java.lang.String) r2
            if (r2 != 0) goto L22
            goto L23
        L22:
            r0 = r2
        L23:
            java.util.Set<java.lang.String> r2 = com.arflix.tv.ui.screens.tv.live.LiveCategoryKt.KNOWN_COUNTRIES
            boolean r2 = r2.contains(r0)
            if (r2 == 0) goto L2d
            r6 = r0
            goto L2e
        L2d:
            r6 = r1
        L2e:
            java.lang.String r0 = r16.getLanguage()
            if (r0 == 0) goto L54
            java.lang.CharSequence r0 = kotlin.text.o.L0(r0)
            java.lang.String r0 = r0.toString()
            if (r0 == 0) goto L54
            java.util.Locale r2 = java.util.Locale.ROOT
            java.lang.String r0 = r0.toUpperCase(r2)
            r2 = 2
            java.lang.String r0 = kotlin.text.o.I0(r2, r0)
            int r3 = r0.length()
            if (r3 != r2) goto L50
            r1 = r0
        L50:
            if (r1 == 0) goto L54
        L52:
            r9 = r1
            goto L5a
        L54:
            if (r6 != 0) goto L59
            java.lang.String r1 = "EN"
            goto L52
        L59:
            r9 = r6
        L5a:
            java.lang.String r0 = r16.getQualityLabel()
            com.arflix.tv.ui.screens.tv.live.Quality r0 = qualityFromLabel(r0)
            if (r0 != 0) goto L6c
            java.lang.String r0 = r16.getName()
            com.arflix.tv.ui.screens.tv.live.Quality r0 = qualityFromText(r0)
        L6c:
            r8 = r0
            com.arflix.tv.ui.screens.tv.live.LiveColors r0 = com.arflix.tv.ui.screens.tv.live.LiveColors.INSTANCE
            com.arflix.tv.ui.screens.tv.live.LiveColors$Brand r0 = r0.getBrandGeneral()
            com.arflix.tv.ui.screens.tv.live.EnrichedChannel r3 = new com.arflix.tv.ui.screens.tv.live.EnrichedChannel
            com.arflix.tv.ui.screens.tv.live.Genre r7 = com.arflix.tv.ui.screens.tv.live.Genre.General
            long r10 = r0.m6417getBg0d7_KjU()
            long r12 = r0.m6418getFg0d7_KjU()
            java.lang.String r0 = r16.getGroup()
            java.lang.String r1 = r16.getName()
            boolean r14 = isAdultGroup(r0, r1)
            r15 = 0
            r4 = r16
            r5 = r17
            r3.<init>(r4, r5, r6, r7, r8, r9, r10, r12, r14, r15)
            return r3
        */
        throw new UnsupportedOperationException("Method not decompiled: com.arflix.tv.ui.screens.tv.live.LiveCategoryKt.enrichForFastStartup(com.arflix.tv.data.model.IptvChannel, int):com.arflix.tv.ui.screens.tv.live.EnrichedChannel");
    }

    public static final Genre genreFromText(String str) {
        String lowerCase = str.toLowerCase(Locale.ROOT);
        return (kotlin.text.o.T(lowerCase, "sport", false) || kotlin.text.o.T(lowerCase, "espn", false) || kotlin.text.o.T(lowerCase, "uefa", false)) ? Genre.Sports : (kotlin.text.o.T(lowerCase, "movie", false) || kotlin.text.o.T(lowerCase, "cinema", false) || kotlin.text.o.T(lowerCase, "film", false)) ? Genre.Movies : (kotlin.text.o.T(lowerCase, "series", false) || kotlin.text.o.T(lowerCase, "show", false)) ? Genre.Series : (kotlin.text.o.T(lowerCase, "news", false) || kotlin.text.o.T(lowerCase, "cnn", false) || kotlin.text.o.T(lowerCase, "bbc news", false)) ? Genre.News : (kotlin.text.o.T(lowerCase, "kids", false) || kotlin.text.o.T(lowerCase, "cartoon", false) || kotlin.text.o.T(lowerCase, "child", false) || kotlin.text.o.T(lowerCase, "family", false)) ? Genre.Kids : (kotlin.text.o.T(lowerCase, "music", false) || kotlin.text.o.T(lowerCase, "mtv", false) || kotlin.text.o.T(lowerCase, "hits", false)) ? Genre.Music : (kotlin.text.o.T(lowerCase, "doc", false) || kotlin.text.o.T(lowerCase, "history", false) || kotlin.text.o.T(lowerCase, "discovery", false) || kotlin.text.o.T(lowerCase, "nat geo", false)) ? Genre.Docs : Genre.General;
    }

    public static final boolean isAdultGroup(String str, String str2) {
        String lowerCase = androidx.compose.foundation.c.t(str, " ", str2).toLowerCase(Locale.ROOT);
        List<String> list = ADULT_KEYWORDS;
        if (list == null || !list.isEmpty()) {
            Iterator<T> it = list.iterator();
            while (it.hasNext()) {
                if (kotlin.text.o.T(lowerCase, (String) it.next(), false)) {
                    return true;
                }
            }
        }
        return false;
    }

    public static final boolean isHiddenPlaylistGroup(EnrichedChannel enrichedChannel, Set<String> set) {
        if (set.isEmpty()) {
            return false;
        }
        String id = enrichedChannel.getId();
        return set.contains(playlistGroupKey(kotlin.text.o.G0(':', id, id), enrichedChannel.getSource().getGroup()));
    }

    public static final boolean isVisibleNonAdultChannel(LiveCategoryIndex liveCategoryIndex, String str) {
        EnrichedChannel enrichedChannel = liveCategoryIndex.getById().get(str);
        return (enrichedChannel == null || enrichedChannel.isAdult() || liveCategoryIndex.getHiddenIds().contains(str)) ? false : true;
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
    /* JADX WARN: Removed duplicated region for block: B:25:0x0085  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static final java.lang.String liveCategoryLabel(java.lang.String r3, androidx.compose.runtime.Composer r4, int r5) {
        /*
            boolean r0 = androidx.compose.runtime.ComposerKt.isTraceInProgress()
            if (r0 == 0) goto Lf
            r0 = -1
            java.lang.String r1 = "com.arflix.tv.ui.screens.tv.live.liveCategoryLabel (LiveCategory.kt:344)"
            r2 = -2136124912(0xffffffff80ad5210, float:-1.591698E-38)
            androidx.compose.runtime.ComposerKt.traceEventStart(r2, r5, r0, r1)
        Lf:
            int r5 = r3.hashCode()
            r0 = 0
            switch(r5) {
                case -1299852618: goto L7d;
                case -1252096081: goto L64;
                case -285769083: goto L4b;
                case 63123866: goto L32;
                case 218729015: goto L19;
                default: goto L17;
            }
        L17:
            goto L85
        L19:
            java.lang.String r5 = "Favorites"
            boolean r5 = r3.equals(r5)
            if (r5 == 0) goto L85
            r3 = 178329563(0xaa117db, float:1.551271E-32)
            r4.startReplaceGroup(r3)
            int r3 = com.arflix.tv.R.string.live_cat_favorites
            java.lang.String r3 = androidx.compose.ui.res.StringResources_androidKt.stringResource(r3, r4, r0)
            r4.endReplaceGroup()
            goto L9e
        L32:
            java.lang.String r5 = "Adult"
            boolean r5 = r3.equals(r5)
            if (r5 != 0) goto L3b
            goto L85
        L3b:
            r3 = 178336183(0xaa131b7, float:1.5522437E-32)
            r4.startReplaceGroup(r3)
            int r3 = com.arflix.tv.R.string.live_cat_adult
            java.lang.String r3 = androidx.compose.ui.res.StringResources_androidKt.stringResource(r3, r4, r0)
            r4.endReplaceGroup()
            goto L9e
        L4b:
            java.lang.String r5 = "Ungrouped"
            boolean r5 = r3.equals(r5)
            if (r5 != 0) goto L54
            goto L85
        L54:
            r3 = 178338075(0xaa1391b, float:1.5525217E-32)
            r4.startReplaceGroup(r3)
            int r3 = com.arflix.tv.R.string.live_cat_ungrouped
            java.lang.String r3 = androidx.compose.ui.res.StringResources_androidKt.stringResource(r3, r4, r0)
            r4.endReplaceGroup()
            goto L9e
        L64:
            java.lang.String r5 = "All Channels"
            boolean r5 = r3.equals(r5)
            if (r5 != 0) goto L6d
            goto L85
        L6d:
            r3 = 178334144(0xaa129c0, float:1.551944E-32)
            r4.startReplaceGroup(r3)
            int r3 = com.arflix.tv.R.string.live_label_all_channels
            java.lang.String r3 = androidx.compose.ui.res.StringResources_androidKt.stringResource(r3, r4, r0)
            r4.endReplaceGroup()
            goto L9e
        L7d:
            java.lang.String r5 = "Recently Watched"
            boolean r5 = r3.equals(r5)
            if (r5 != 0) goto L8f
        L85:
            r5 = 178339827(0xaa13ff3, float:1.5527791E-32)
            r4.startReplaceGroup(r5)
            r4.endReplaceGroup()
            goto L9e
        L8f:
            r3 = 178331810(0xaa120a2, float:1.5516011E-32)
            r4.startReplaceGroup(r3)
            int r3 = com.arflix.tv.R.string.live_cat_recently_watched
            java.lang.String r3 = androidx.compose.ui.res.StringResources_androidKt.stringResource(r3, r4, r0)
            r4.endReplaceGroup()
        L9e:
            boolean r4 = androidx.compose.runtime.ComposerKt.isTraceInProgress()
            if (r4 == 0) goto La7
            androidx.compose.runtime.ComposerKt.traceEventEnd()
        La7:
            return r3
        */
        throw new UnsupportedOperationException("Method not decompiled: com.arflix.tv.ui.screens.tv.live.LiveCategoryKt.liveCategoryLabel(java.lang.String, androidx.compose.runtime.Composer, int):java.lang.String");
    }

    /* JADX WARN: Removed duplicated region for block: B:22:0x005e  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static final java.lang.String liveSectionLabel(java.lang.String r3, androidx.compose.runtime.Composer r4, int r5) {
        /*
            boolean r0 = androidx.compose.runtime.ComposerKt.isTraceInProgress()
            if (r0 == 0) goto Lf
            r0 = -1
            java.lang.String r1 = "com.arflix.tv.ui.screens.tv.live.liveSectionLabel (LiveCategory.kt:355)"
            r2 = -718445699(0xffffffffd52d637d, float:-1.1915176E13)
            androidx.compose.runtime.ComposerKt.traceEventStart(r2, r5, r0, r1)
        Lf:
            int r5 = r3.hashCode()
            r0 = -1632865838(0xffffffff9eac71d2, float:-1.8258283E-20)
            r1 = 0
            if (r5 == r0) goto L56
            r0 = 62138778(0x3b4299a, float:1.0589E-36)
            if (r5 == r0) goto L3d
            r0 = 2130809258(0x7f0191aa, float:1.7222674E38)
            if (r5 == r0) goto L24
            goto L5e
        L24:
            java.lang.String r5 = "HIDDEN"
            boolean r5 = r3.equals(r5)
            if (r5 != 0) goto L2d
            goto L5e
        L2d:
            r3 = 1063424265(0x3f629109, float:0.88502556)
            r4.startReplaceGroup(r3)
            int r3 = com.arflix.tv.R.string.live_section_hidden
            java.lang.String r3 = androidx.compose.ui.res.StringResources_androidKt.stringResource(r3, r4, r1)
            r4.endReplaceGroup()
            goto L77
        L3d:
            java.lang.String r5 = "ADULT"
            boolean r5 = r3.equals(r5)
            if (r5 != 0) goto L46
            goto L5e
        L46:
            r3 = 1063422344(0x3f628988, float:0.88491106)
            r4.startReplaceGroup(r3)
            int r3 = com.arflix.tv.R.string.live_section_adult
            java.lang.String r3 = androidx.compose.ui.res.StringResources_androidKt.stringResource(r3, r4, r1)
            r4.endReplaceGroup()
            goto L77
        L56:
            java.lang.String r5 = "PLAYLIST"
            boolean r5 = r3.equals(r5)
            if (r5 != 0) goto L68
        L5e:
            r5 = 1063426048(0x3f629800, float:0.88513184)
            r4.startReplaceGroup(r5)
            r4.endReplaceGroup()
            goto L77
        L68:
            r3 = 1063420363(0x3f6281cb, float:0.884793)
            r4.startReplaceGroup(r3)
            int r3 = com.arflix.tv.R.string.live_section_playlist
            java.lang.String r3 = androidx.compose.ui.res.StringResources_androidKt.stringResource(r3, r4, r1)
            r4.endReplaceGroup()
        L77:
            boolean r4 = androidx.compose.runtime.ComposerKt.isTraceInProgress()
            if (r4 == 0) goto L80
            androidx.compose.runtime.ComposerKt.traceEventEnd()
        L80:
            return r3
        */
        throw new UnsupportedOperationException("Method not decompiled: com.arflix.tv.ui.screens.tv.live.LiveCategoryKt.liveSectionLabel(java.lang.String, androidx.compose.runtime.Composer, int):java.lang.String");
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
    /* JADX WARN: Removed duplicated region for block: B:100:0x0153  */
    /* JADX WARN: Removed duplicated region for block: B:106:0x0165  */
    /* JADX WARN: Removed duplicated region for block: B:112:0x0177  */
    /* JADX WARN: Removed duplicated region for block: B:118:0x018a  */
    /* JADX WARN: Removed duplicated region for block: B:128:0x01aa A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:129:0x01ab A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:62:0x00cb  */
    /* JADX WARN: Removed duplicated region for block: B:70:0x00f0  */
    /* JADX WARN: Removed duplicated region for block: B:76:0x0104  */
    /* JADX WARN: Removed duplicated region for block: B:82:0x0118  */
    /* JADX WARN: Removed duplicated region for block: B:88:0x012c  */
    /* JADX WARN: Removed duplicated region for block: B:94:0x0140  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private static final boolean matchesCategoryId(com.arflix.tv.ui.screens.tv.live.EnrichedChannel r4, java.lang.String r5) {
        /*
            Method dump skipped, instruction units count: 500
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.arflix.tv.ui.screens.tv.live.LiveCategoryKt.matchesCategoryId(com.arflix.tv.ui.screens.tv.live.EnrichedChannel, java.lang.String):boolean");
    }

    private static final List<Map.Entry<String, x6.x>> orderPlaylistGroups(final LinkedHashMap<String, x6.x> linkedHashMap, List<String> list) {
        if (linkedHashMap.isEmpty()) {
            return kotlin.collections.z.f19728i;
        }
        if (list.isEmpty()) {
            return kotlin.collections.x.c1(linkedHashMap.entrySet());
        }
        ga.c cVarG = ga.r.G(ga.r.L(new ga.p(list, 3), new w0(8)));
        final LinkedHashMap linkedHashMap2 = new LinkedHashMap();
        Iterator it = cVarG.iterator();
        int i10 = 0;
        while (true) {
            kotlin.collections.b bVar = (kotlin.collections.b) it;
            if (!bVar.hasNext()) {
                Set<Map.Entry<String, x6.x>> setEntrySet = linkedHashMap.entrySet();
                final Comparator comparator = new Comparator() { // from class: com.arflix.tv.ui.screens.tv.live.LiveCategoryKt$orderPlaylistGroups$$inlined$compareBy$1
                    @Override // java.util.Comparator
                    public final int compare(T t2, T t10) {
                        Integer num = Integer.MAX_VALUE;
                        Map.Entry entry = (Map.Entry) t2;
                        String strPlaylistIdFromGroupCategoryId = LiveCategoryKt.playlistIdFromGroupCategoryId((String) entry.getKey());
                        if (strPlaylistIdFromGroupCategoryId == null) {
                            strPlaylistIdFromGroupCategoryId = "";
                        }
                        String strPlaylistGroupLabel = LiveCategoryKt.playlistGroupLabel((String) ((x6.x) entry.getValue()).f22608i);
                        Integer num2 = (Integer) linkedHashMap2.get(LiveCategoryKt.playlistGroupKey(strPlaylistIdFromGroupCategoryId, strPlaylistGroupLabel));
                        if (num2 == null && (num2 = (Integer) linkedHashMap2.get(strPlaylistGroupLabel)) == null) {
                            num2 = num;
                        }
                        Map.Entry entry2 = (Map.Entry) t10;
                        String strPlaylistIdFromGroupCategoryId2 = LiveCategoryKt.playlistIdFromGroupCategoryId((String) entry2.getKey());
                        String str = strPlaylistIdFromGroupCategoryId2 != null ? strPlaylistIdFromGroupCategoryId2 : "";
                        String strPlaylistGroupLabel2 = LiveCategoryKt.playlistGroupLabel((String) ((x6.x) entry2.getValue()).f22608i);
                        Integer num3 = (Integer) linkedHashMap2.get(LiveCategoryKt.playlistGroupKey(str, strPlaylistGroupLabel2));
                        if (num3 != null) {
                            num = num3;
                        } else {
                            Integer num4 = (Integer) linkedHashMap2.get(strPlaylistGroupLabel2);
                            if (num4 != null) {
                                num = num4;
                            }
                        }
                        return com.google.common.util.concurrent.r0.e(num2, num);
                    }
                };
                return kotlin.collections.x.W0(setEntrySet, new Comparator() { // from class: com.arflix.tv.ui.screens.tv.live.LiveCategoryKt$orderPlaylistGroups$$inlined$thenBy$1
                    @Override // java.util.Comparator
                    public final int compare(T t2, T t10) {
                        int iCompare = comparator.compare(t2, t10);
                        return iCompare != 0 ? iCompare : com.google.common.util.concurrent.r0.e(Integer.valueOf(kotlin.collections.x.q0(linkedHashMap.keySet(), ((Map.Entry) t2).getKey())), Integer.valueOf(kotlin.collections.x.q0(linkedHashMap.keySet(), ((Map.Entry) t10).getKey())));
                    }
                });
            }
            int i11 = i10 + 1;
            if (i10 < 0) {
                t7.a.Q();
                throw null;
            }
            linkedHashMap2.put((String) bVar.next(), Integer.valueOf(i10));
            i10 = i11;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final ga.m orderPlaylistGroups$lambda$0(String str) {
        String string = kotlin.text.o.L0(str).toString();
        return kotlin.collections.r.G(new String[]{string, playlistGroupLabel(PlaylistGroupKey.m6071getGroupNameimpl(PlaylistGroupKey.m6068constructorimpl(string)))});
    }

    public static final String playlistGroupCategoryId(String str, String str2) {
        int iHashCode = playlistGroupLabel(str2).toLowerCase(Locale.ROOT).hashCode();
        com.google.common.util.concurrent.r0.c(16);
        return androidx.compose.material3.d.n("grp:", str, ":", m2.f0.V(16, ((long) iHashCode) & 4294967295L));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final String playlistGroupKey(String str, String str2) {
        return PlaylistGroupKey.INSTANCE.build(str, playlistGroupLabel(str2));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final String playlistGroupLabel(String str) {
        String string = kotlin.text.o.L0(str).toString();
        return kotlin.text.o.h0(string) ? "Ungrouped" : string;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final String playlistIdFromGroupCategoryId(String str) {
        if (!kotlin.text.u.P(str, "grp:", false)) {
            return null;
        }
        String strG0 = kotlin.text.o.G0(':', kotlin.text.o.r0(str, "grp:"), "");
        if (kotlin.text.o.h0(strG0)) {
            return null;
        }
        return strG0;
    }

    private static final Quality qualityFromLabel(String str) {
        String string;
        if (str != null && (string = kotlin.text.o.L0(str).toString()) != null) {
            if (kotlin.text.o.h0(string)) {
                string = null;
            }
            if (string != null) {
                Quality qualityQualityFromText = qualityFromText(string);
                if (qualityQualityFromText != Quality.SD || string.equalsIgnoreCase("SD")) {
                    return qualityQualityFromText;
                }
                return null;
            }
        }
        return null;
    }

    public static final Quality qualityFromText(String str) {
        String upperCase = str.toUpperCase(Locale.ROOT);
        return (kotlin.text.o.T(upperCase, "4K", false) || kotlin.text.o.T(upperCase, "UHD", false) || kotlin.text.o.T(upperCase, "2160", false)) ? Quality.K4 : (kotlin.text.o.T(upperCase, "FHD", false) || kotlin.text.o.T(upperCase, "1080", false)) ? Quality.FHD : (kotlin.text.o.T(upperCase, "HD", false) || kotlin.text.o.T(upperCase, "720", false)) ? Quality.HD : Quality.SD;
    }

    private static final r7.l<IptvChannel, Boolean> rawCategoryMatcher(String str, Set<String> set, Set<String> set2) {
        if (kotlin.jvm.internal.p.a(str, TtmlNode.COMBINE_ALL)) {
            return new w0(9);
        }
        if (kotlin.jvm.internal.p.a(str, "fav")) {
            return new o2(set, 2);
        }
        if (kotlin.jvm.internal.p.a(str, "recent")) {
            return new o2(set2, 3);
        }
        if (kotlin.jvm.internal.p.a(str, "adult")) {
            return new n2(21);
        }
        if (kotlin.text.u.P(str, "grp:", false)) {
            return new r(str, 1);
        }
        if (str.equals("g-4k")) {
            return new n2(22);
        }
        if (str.equals("g-sports")) {
            return new n2(23);
        }
        if (str.equals("g-movies")) {
            return new n2(24);
        }
        if (str.equals("g-news")) {
            return new n2(25);
        }
        if (str.equals("g-kids")) {
            return new n2(27);
        }
        if (str.equals("g-docs")) {
            return new w0(10);
        }
        if (str.equals("g-music")) {
            return new w0(11);
        }
        if (str.length() == 2) {
            for (int i10 = 0; i10 < str.length(); i10++) {
                if (Character.isUpperCase(str.charAt(i10))) {
                }
            }
            return new r(str, 4);
        }
        if (!kotlin.text.o.T(str, "-", false)) {
            return new n2(20);
        }
        List listY0 = kotlin.text.o.y0(str, new String[]{"-"}, 2, 2);
        String str2 = (String) kotlin.collections.x.p0(0, listY0);
        if (str2 == null) {
            str2 = "";
        }
        String str3 = (String) kotlin.collections.x.p0(1, listY0);
        return new x2(str2, str3 != null ? str3 : "", 2);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final boolean rawCategoryMatcher$lambda$0(IptvChannel iptvChannel) {
        return !traits(iptvChannel).isAdult();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final boolean rawCategoryMatcher$lambda$1(Set set, IptvChannel iptvChannel) {
        return set.contains(iptvChannel.getId()) && !traits(iptvChannel).isAdult();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final boolean rawCategoryMatcher$lambda$10(IptvChannel iptvChannel) {
        ChannelTraits channelTraitsTraits = traits(iptvChannel);
        return !channelTraitsTraits.isAdult() && channelTraitsTraits.getGenre() == Genre.Docs;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final boolean rawCategoryMatcher$lambda$11(IptvChannel iptvChannel) {
        ChannelTraits channelTraitsTraits = traits(iptvChannel);
        return !channelTraitsTraits.isAdult() && channelTraitsTraits.getGenre() == Genre.Music;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final boolean rawCategoryMatcher$lambda$13(String str, IptvChannel iptvChannel) {
        ChannelTraits channelTraitsTraits = traits(iptvChannel);
        return !channelTraitsTraits.isAdult() && kotlin.jvm.internal.p.a(channelTraitsTraits.getCountry(), str);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
    public static final boolean rawCategoryMatcher$lambda$14(String str, String str2, IptvChannel iptvChannel) {
        ChannelTraits channelTraitsTraits = traits(iptvChannel);
        if (kotlin.jvm.internal.p.a(channelTraitsTraits.getCountry(), str) && !channelTraitsTraits.isAdult()) {
            switch (str2.hashCode()) {
                case -1068259517:
                    if (str2.equals("movies") && channelTraitsTraits.getGenre() == Genre.Movies) {
                        return true;
                    }
                    break;
                case -895760513:
                    return str2.equals(SportsAddonCapabilities.SPORTS_CATEGORY_ROW_ID) && channelTraitsTraits.getGenre() == Genre.Sports;
                case -80148248:
                    return str2.equals("general") && channelTraitsTraits.getGenre() == Genre.General;
                case 1719:
                    return str2.equals("4k") && channelTraitsTraits.getQuality() == Quality.K4;
                case 101346:
                    return str2.equals("fhd") && channelTraitsTraits.getQuality() == Quality.FHD;
                case 3291757:
                    return str2.equals("kids") && channelTraitsTraits.getGenre() == Genre.Kids;
                case 3377875:
                    return str2.equals("news") && channelTraitsTraits.getGenre() == Genre.News;
                case 500006792:
                    return str2.equals("entertainment") && channelTraitsTraits.getGenre() == Genre.Series;
                case 506679149:
                    return str2.equals("documentary") && channelTraitsTraits.getGenre() == Genre.Docs;
                default:
                    return false;
            }
        }
        return false;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final boolean rawCategoryMatcher$lambda$15(IptvChannel iptvChannel) {
        return !traits(iptvChannel).isAdult();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final boolean rawCategoryMatcher$lambda$2(Set set, IptvChannel iptvChannel) {
        return set.contains(iptvChannel.getId()) && !traits(iptvChannel).isAdult();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final boolean rawCategoryMatcher$lambda$3(IptvChannel iptvChannel) {
        return traits(iptvChannel).isAdult();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final boolean rawCategoryMatcher$lambda$4(String str, IptvChannel iptvChannel) {
        String id = iptvChannel.getId();
        return kotlin.jvm.internal.p.a(playlistGroupCategoryId(kotlin.text.o.G0(':', id, id), iptvChannel.getGroup()), str);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final boolean rawCategoryMatcher$lambda$5(IptvChannel iptvChannel) {
        ChannelTraits channelTraitsTraits = traits(iptvChannel);
        return !channelTraitsTraits.isAdult() && channelTraitsTraits.getQuality() == Quality.K4;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final boolean rawCategoryMatcher$lambda$6(IptvChannel iptvChannel) {
        ChannelTraits channelTraitsTraits = traits(iptvChannel);
        return !channelTraitsTraits.isAdult() && channelTraitsTraits.getGenre() == Genre.Sports;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final boolean rawCategoryMatcher$lambda$7(IptvChannel iptvChannel) {
        ChannelTraits channelTraitsTraits = traits(iptvChannel);
        return !channelTraitsTraits.isAdult() && channelTraitsTraits.getGenre() == Genre.Movies;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final boolean rawCategoryMatcher$lambda$8(IptvChannel iptvChannel) {
        ChannelTraits channelTraitsTraits = traits(iptvChannel);
        return !channelTraitsTraits.isAdult() && channelTraitsTraits.getGenre() == Genre.News;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final boolean rawCategoryMatcher$lambda$9(IptvChannel iptvChannel) {
        ChannelTraits channelTraitsTraits = traits(iptvChannel);
        return !channelTraitsTraits.isAdult() && channelTraitsTraits.getGenre() == Genre.Kids;
    }

    public static final List<EnrichedChannel> sortChannelsByConfiguredOrder(List<EnrichedChannel> list, String str) {
        if (str.equals("number")) {
            final l0 l0Var = new l0(15);
            final int i10 = 0;
            return kotlin.collections.x.W0(list, new Comparator() { // from class: com.arflix.tv.ui.screens.tv.live.x0
                @Override // java.util.Comparator
                public final int compare(Object obj, Object obj2) {
                    switch (i10) {
                        case 0:
                            return LiveCategoryKt.sortChannelsByConfiguredOrder$lambda$1((l0) l0Var, obj, obj2);
                        default:
                            return LiveCategoryKt.sortChannelsByConfiguredOrder$lambda$3((l0) l0Var, obj, obj2);
                    }
                }
            });
        }
        if (!str.equals(ContentDisposition.Parameters.Name)) {
            return list;
        }
        final l0 l0Var2 = new l0(16);
        final int i11 = 1;
        return kotlin.collections.x.W0(list, new Comparator() { // from class: com.arflix.tv.ui.screens.tv.live.x0
            @Override // java.util.Comparator
            public final int compare(Object obj, Object obj2) {
                switch (i11) {
                    case 0:
                        return LiveCategoryKt.sortChannelsByConfiguredOrder$lambda$1((l0) l0Var2, obj, obj2);
                    default:
                        return LiveCategoryKt.sortChannelsByConfiguredOrder$lambda$3((l0) l0Var2, obj, obj2);
                }
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Removed duplicated region for block: B:10:0x0021  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static final int sortChannelsByConfiguredOrder$lambda$0(com.arflix.tv.ui.screens.tv.live.EnrichedChannel r2, com.arflix.tv.ui.screens.tv.live.EnrichedChannel r3) {
        /*
            com.arflix.tv.data.model.IptvChannel r2 = r2.getSource()
            java.lang.String r2 = r2.getProviderChannelNumber()
            r0 = 0
            if (r2 == 0) goto L21
            java.lang.CharSequence r2 = kotlin.text.o.L0(r2)
            java.lang.String r2 = r2.toString()
            if (r2 == 0) goto L21
            boolean r1 = kotlin.text.t.E(r2)     // Catch: java.lang.NumberFormatException -> L21
            if (r1 == 0) goto L21
            java.math.BigDecimal r1 = new java.math.BigDecimal     // Catch: java.lang.NumberFormatException -> L21
            r1.<init>(r2)     // Catch: java.lang.NumberFormatException -> L21
            goto L22
        L21:
            r1 = r0
        L22:
            com.arflix.tv.data.model.IptvChannel r2 = r3.getSource()
            java.lang.String r2 = r2.getProviderChannelNumber()
            if (r2 == 0) goto L42
            java.lang.CharSequence r2 = kotlin.text.o.L0(r2)
            java.lang.String r2 = r2.toString()
            if (r2 == 0) goto L42
            boolean r3 = kotlin.text.t.E(r2)     // Catch: java.lang.NumberFormatException -> L42
            if (r3 == 0) goto L42
            java.math.BigDecimal r3 = new java.math.BigDecimal     // Catch: java.lang.NumberFormatException -> L42
            r3.<init>(r2)     // Catch: java.lang.NumberFormatException -> L42
            r0 = r3
        L42:
            if (r1 == 0) goto L4b
            if (r0 == 0) goto L4b
            int r2 = r1.compareTo(r0)
            return r2
        L4b:
            if (r1 == 0) goto L4f
            r2 = -1
            return r2
        L4f:
            if (r0 == 0) goto L53
            r2 = 1
            return r2
        L53:
            r2 = 0
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: com.arflix.tv.ui.screens.tv.live.LiveCategoryKt.sortChannelsByConfiguredOrder$lambda$0(com.arflix.tv.ui.screens.tv.live.EnrichedChannel, com.arflix.tv.ui.screens.tv.live.EnrichedChannel):int");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final int sortChannelsByConfiguredOrder$lambda$1(r7.p pVar, Object obj, Object obj2) {
        return ((Number) pVar.invoke(obj, obj2)).intValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final int sortChannelsByConfiguredOrder$lambda$2(EnrichedChannel enrichedChannel, EnrichedChannel enrichedChannel2) {
        return enrichedChannel.getName().compareToIgnoreCase(enrichedChannel2.getName());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final int sortChannelsByConfiguredOrder$lambda$3(r7.p pVar, Object obj, Object obj2) {
        return ((Number) pVar.invoke(obj, obj2)).intValue();
    }

    /* JADX WARN: Removed duplicated region for block: B:16:0x003f  */
    /* JADX WARN: Removed duplicated region for block: B:35:0x009e  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private static final com.arflix.tv.ui.screens.tv.live.ChannelTraits traits(com.arflix.tv.data.model.IptvChannel r14) {
        /*
            java.lang.String r0 = r14.getGroup()
            java.lang.String r1 = r14.getName()
            java.lang.String r2 = " | "
            java.lang.String r0 = androidx.compose.foundation.c.t(r0, r2, r1)
            java.lang.String r1 = r14.getCountry()
            r2 = 0
            if (r1 == 0) goto L3f
            java.lang.CharSequence r1 = kotlin.text.o.L0(r1)
            java.lang.String r1 = r1.toString()
            if (r1 == 0) goto L3f
            java.util.Locale r3 = java.util.Locale.ROOT
            java.lang.String r1 = r1.toUpperCase(r3)
            java.util.Map<java.lang.String, java.lang.String> r3 = com.arflix.tv.ui.screens.tv.live.LiveCategoryKt.COUNTRY_ALIASES
            java.lang.Object r3 = r3.get(r1)
            java.lang.String r3 = (java.lang.String) r3
            if (r3 != 0) goto L30
            goto L31
        L30:
            r1 = r3
        L31:
            java.util.Set<java.lang.String> r3 = com.arflix.tv.ui.screens.tv.live.LiveCategoryKt.KNOWN_COUNTRIES
            boolean r3 = r3.contains(r1)
            if (r3 == 0) goto L3a
            goto L3b
        L3a:
            r1 = r2
        L3b:
            if (r1 == 0) goto L3f
        L3d:
            r4 = r1
            goto L52
        L3f:
            java.lang.String r1 = r14.getGroup()
            java.lang.String r1 = countryFromText(r1)
            if (r1 != 0) goto L3d
            java.lang.String r1 = r14.getName()
            java.lang.String r1 = countryFromText(r1)
            goto L3d
        L52:
            com.arflix.tv.ui.screens.tv.live.Genre r5 = genreFromText(r0)
            java.lang.String r0 = r14.getQualityLabel()
            com.arflix.tv.ui.screens.tv.live.Quality r0 = qualityFromLabel(r0)
            if (r0 != 0) goto L77
            java.lang.String r0 = r14.getName()
            com.arflix.tv.ui.screens.tv.live.Quality r0 = qualityFromText(r0)
            com.arflix.tv.ui.screens.tv.live.Quality r1 = com.arflix.tv.ui.screens.tv.live.Quality.SD
            if (r0 != r1) goto L6d
            r0 = r2
        L6d:
            if (r0 != 0) goto L77
            java.lang.String r0 = r14.getGroup()
            com.arflix.tv.ui.screens.tv.live.Quality r0 = qualityFromText(r0)
        L77:
            r6 = r0
            java.lang.String r0 = r14.getLanguage()
            if (r0 == 0) goto L9e
            java.lang.CharSequence r0 = kotlin.text.o.L0(r0)
            java.lang.String r0 = r0.toString()
            if (r0 == 0) goto L9e
            java.util.Locale r1 = java.util.Locale.ROOT
            java.lang.String r0 = r0.toUpperCase(r1)
            r1 = 2
            java.lang.String r0 = kotlin.text.o.I0(r1, r0)
            int r3 = r0.length()
            if (r3 != r1) goto L9a
            r2 = r0
        L9a:
            if (r2 == 0) goto L9e
        L9c:
            r7 = r2
            goto La4
        L9e:
            if (r4 != 0) goto La3
            java.lang.String r2 = "EN"
            goto L9c
        La3:
            r7 = r4
        La4:
            com.arflix.tv.ui.screens.tv.live.LiveColors$Brand r0 = brandForGenre(r5)
            com.arflix.tv.ui.screens.tv.live.ChannelTraits r3 = new com.arflix.tv.ui.screens.tv.live.ChannelTraits
            long r8 = r0.m6417getBg0d7_KjU()
            long r10 = r0.m6418getFg0d7_KjU()
            java.lang.String r0 = r14.getGroup()
            java.lang.String r14 = r14.getName()
            boolean r12 = isAdultGroup(r0, r14)
            r13 = 0
            r3.<init>(r4, r5, r6, r7, r8, r10, r12, r13)
            return r3
        */
        throw new UnsupportedOperationException("Method not decompiled: com.arflix.tv.ui.screens.tv.live.LiveCategoryKt.traits(com.arflix.tv.data.model.IptvChannel):com.arflix.tv.ui.screens.tv.live.ChannelTraits");
    }

    public static /* synthetic */ LiveCategoryTree buildCategoryTree$default(List list, Set set, Set set2, Set set3, List list2, int i10, Object obj) {
        if ((i10 & 8) != 0) {
            set3 = kotlin.collections.b0.f19686i;
        }
        if ((i10 & 16) != 0) {
            list2 = kotlin.collections.z.f19728i;
        }
        return buildCategoryTree((List<IptvChannel>) list, (Set<String>) set, (Set<String>) set2, (Set<String>) set3, (List<String>) list2);
    }

    private static final boolean isHiddenPlaylistGroup(IptvChannel iptvChannel, Set<String> set) {
        if (set.isEmpty()) {
            return false;
        }
        String id = iptvChannel.getId();
        return set.contains(playlistGroupKey(kotlin.text.o.G0(':', id, id), iptvChannel.getGroup()));
    }

    @Metadata(d1 = {"\u0000!\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b.\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000*\u0001\u0000\b\u008a\b\u0018\u00002\u00020\u0001Bk\u0012\b\b\u0002\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0004\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0005\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0006\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0007\u001a\u00020\u0003\u0012\b\b\u0002\u0010\b\u001a\u00020\u0003\u0012\b\b\u0002\u0010\t\u001a\u00020\u0003\u0012\b\b\u0002\u0010\n\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u000b\u001a\u00020\u0003\u0012\b\b\u0002\u0010\f\u001a\u00020\u0003¢\u0006\u0004\b\r\u0010\u000eJ\t\u0010%\u001a\u00020\u0003HÆ\u0003J\t\u0010&\u001a\u00020\u0003HÆ\u0003J\t\u0010'\u001a\u00020\u0003HÆ\u0003J\t\u0010(\u001a\u00020\u0003HÆ\u0003J\t\u0010)\u001a\u00020\u0003HÆ\u0003J\t\u0010*\u001a\u00020\u0003HÆ\u0003J\t\u0010+\u001a\u00020\u0003HÆ\u0003J\t\u0010,\u001a\u00020\u0003HÆ\u0003J\t\u0010-\u001a\u00020\u0003HÆ\u0003J\t\u0010.\u001a\u00020\u0003HÆ\u0003Jr\u0010/\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u00032\b\b\u0002\u0010\u0006\u001a\u00020\u00032\b\b\u0002\u0010\u0007\u001a\u00020\u00032\b\b\u0002\u0010\b\u001a\u00020\u00032\b\b\u0002\u0010\t\u001a\u00020\u00032\b\b\u0002\u0010\n\u001a\u00020\u00032\b\b\u0002\u0010\u000b\u001a\u00020\u00032\b\b\u0002\u0010\f\u001a\u00020\u0003HÆ\u0001¢\u0006\u0002\u00100J\u0014\u00101\u001a\u0002022\b\u00103\u001a\u0004\u0018\u00010\u0001HÖ\u0083\u0004J\n\u00104\u001a\u00020\u0003HÖ\u0081\u0004J\n\u00105\u001a\u000206HÖ\u0081\u0004R\u001a\u0010\u0002\u001a\u00020\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000f\u0010\u0010\"\u0004\b\u0011\u0010\u0012R\u001a\u0010\u0004\u001a\u00020\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0013\u0010\u0010\"\u0004\b\u0014\u0010\u0012R\u001a\u0010\u0005\u001a\u00020\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0015\u0010\u0010\"\u0004\b\u0016\u0010\u0012R\u001a\u0010\u0006\u001a\u00020\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0017\u0010\u0010\"\u0004\b\u0018\u0010\u0012R\u001a\u0010\u0007\u001a\u00020\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0019\u0010\u0010\"\u0004\b\u001a\u0010\u0012R\u001a\u0010\b\u001a\u00020\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001b\u0010\u0010\"\u0004\b\u001c\u0010\u0012R\u001a\u0010\t\u001a\u00020\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001d\u0010\u0010\"\u0004\b\u001e\u0010\u0012R\u001a\u0010\n\u001a\u00020\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001f\u0010\u0010\"\u0004\b \u0010\u0012R\u001a\u0010\u000b\u001a\u00020\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b!\u0010\u0010\"\u0004\b\"\u0010\u0012R\u001a\u0010\f\u001a\u00020\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b#\u0010\u0010\"\u0004\b$\u0010\u0012¨\u00067"}, d2 = {"com/arflix/tv/ui/screens/tv/live/LiveCategoryKt$buildCategoryTree$CountryAccumulator", "", "total", "", "general", "k4", "fhd", SportsAddonCapabilities.SPORTS_CATEGORY_ROW_ID, "movies", "news", "kids", "series", "docs", "<init>", "(IIIIIIIIII)V", "getTotal", "()I", "setTotal", "(I)V", "getGeneral", "setGeneral", "getK4", "setK4", "getFhd", "setFhd", "getSports", "setSports", "getMovies", "setMovies", "getNews", "setNews", "getKids", "setKids", "getSeries", "setSeries", "getDocs", "setDocs", "component1", "component2", "component3", "component4", "component5", "component6", "component7", "component8", "component9", "component10", "copy", "(IIIIIIIIII)Lcom/arflix/tv/ui/screens/tv/live/LiveCategoryKt$buildCategoryTree$CountryAccumulator;", "equals", "", "other", "hashCode", "toString", "", "app_playRelease"}, k = 1, mv = {2, 3, 0}, xi = 48)
    public static final /* data */ class CountryAccumulator {
        private int docs;
        private int fhd;
        private int general;
        private int k4;
        private int kids;
        private int movies;
        private int news;
        private int series;
        private int sports;
        private int total;

        public CountryAccumulator(int i10, int i11, int i12, int i13, int i14, int i15, int i16, int i17, int i18, int i19) {
            this.total = i10;
            this.general = i11;
            this.k4 = i12;
            this.fhd = i13;
            this.sports = i14;
            this.movies = i15;
            this.news = i16;
            this.kids = i17;
            this.series = i18;
            this.docs = i19;
        }

        public static /* synthetic */ CountryAccumulator copy$default(CountryAccumulator countryAccumulator, int i10, int i11, int i12, int i13, int i14, int i15, int i16, int i17, int i18, int i19, int i20, Object obj) {
            if ((i20 & 1) != 0) {
                i10 = countryAccumulator.total;
            }
            if ((i20 & 2) != 0) {
                i11 = countryAccumulator.general;
            }
            if ((i20 & 4) != 0) {
                i12 = countryAccumulator.k4;
            }
            if ((i20 & 8) != 0) {
                i13 = countryAccumulator.fhd;
            }
            if ((i20 & 16) != 0) {
                i14 = countryAccumulator.sports;
            }
            if ((i20 & 32) != 0) {
                i15 = countryAccumulator.movies;
            }
            if ((i20 & 64) != 0) {
                i16 = countryAccumulator.news;
            }
            if ((i20 & 128) != 0) {
                i17 = countryAccumulator.kids;
            }
            if ((i20 & 256) != 0) {
                i18 = countryAccumulator.series;
            }
            if ((i20 & 512) != 0) {
                i19 = countryAccumulator.docs;
            }
            int i21 = i18;
            int i22 = i19;
            int i23 = i16;
            int i24 = i17;
            int i25 = i14;
            int i26 = i15;
            return countryAccumulator.copy(i10, i11, i12, i13, i25, i26, i23, i24, i21, i22);
        }

        /* JADX INFO: renamed from: component1, reason: from getter */
        public final int getTotal() {
            return this.total;
        }

        /* JADX INFO: renamed from: component10, reason: from getter */
        public final int getDocs() {
            return this.docs;
        }

        /* JADX INFO: renamed from: component2, reason: from getter */
        public final int getGeneral() {
            return this.general;
        }

        /* JADX INFO: renamed from: component3, reason: from getter */
        public final int getK4() {
            return this.k4;
        }

        /* JADX INFO: renamed from: component4, reason: from getter */
        public final int getFhd() {
            return this.fhd;
        }

        /* JADX INFO: renamed from: component5, reason: from getter */
        public final int getSports() {
            return this.sports;
        }

        /* JADX INFO: renamed from: component6, reason: from getter */
        public final int getMovies() {
            return this.movies;
        }

        /* JADX INFO: renamed from: component7, reason: from getter */
        public final int getNews() {
            return this.news;
        }

        /* JADX INFO: renamed from: component8, reason: from getter */
        public final int getKids() {
            return this.kids;
        }

        /* JADX INFO: renamed from: component9, reason: from getter */
        public final int getSeries() {
            return this.series;
        }

        public final CountryAccumulator copy(int total, int general, int k42, int fhd, int sports, int movies, int news, int kids, int series, int docs) {
            return new CountryAccumulator(total, general, k42, fhd, sports, movies, news, kids, series, docs);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof CountryAccumulator)) {
                return false;
            }
            CountryAccumulator countryAccumulator = (CountryAccumulator) other;
            return this.total == countryAccumulator.total && this.general == countryAccumulator.general && this.k4 == countryAccumulator.k4 && this.fhd == countryAccumulator.fhd && this.sports == countryAccumulator.sports && this.movies == countryAccumulator.movies && this.news == countryAccumulator.news && this.kids == countryAccumulator.kids && this.series == countryAccumulator.series && this.docs == countryAccumulator.docs;
        }

        public final int getDocs() {
            return this.docs;
        }

        public final int getFhd() {
            return this.fhd;
        }

        public final int getGeneral() {
            return this.general;
        }

        public final int getK4() {
            return this.k4;
        }

        public final int getKids() {
            return this.kids;
        }

        public final int getMovies() {
            return this.movies;
        }

        public final int getNews() {
            return this.news;
        }

        public final int getSeries() {
            return this.series;
        }

        public final int getSports() {
            return this.sports;
        }

        public final int getTotal() {
            return this.total;
        }

        public int hashCode() {
            return (((((((((((((((((this.total * 31) + this.general) * 31) + this.k4) * 31) + this.fhd) * 31) + this.sports) * 31) + this.movies) * 31) + this.news) * 31) + this.kids) * 31) + this.series) * 31) + this.docs;
        }

        public final void setDocs(int i10) {
            this.docs = i10;
        }

        public final void setFhd(int i10) {
            this.fhd = i10;
        }

        public final void setGeneral(int i10) {
            this.general = i10;
        }

        public final void setK4(int i10) {
            this.k4 = i10;
        }

        public final void setKids(int i10) {
            this.kids = i10;
        }

        public final void setMovies(int i10) {
            this.movies = i10;
        }

        public final void setNews(int i10) {
            this.news = i10;
        }

        public final void setSeries(int i10) {
            this.series = i10;
        }

        public final void setSports(int i10) {
            this.sports = i10;
        }

        public final void setTotal(int i10) {
            this.total = i10;
        }

        public String toString() {
            int i10 = this.total;
            int i11 = this.general;
            int i12 = this.k4;
            int i13 = this.fhd;
            int i14 = this.sports;
            int i15 = this.movies;
            int i16 = this.news;
            int i17 = this.kids;
            int i18 = this.series;
            int i19 = this.docs;
            StringBuilder sbV = androidx.compose.foundation.c.v("CountryAccumulator(total=", i10, ", general=", i11, ", k4=");
            androidx.fragment.app.a2.y(sbV, i12, ", fhd=", i13, ", sports=");
            androidx.fragment.app.a2.y(sbV, i14, ", movies=", i15, ", news=");
            androidx.fragment.app.a2.y(sbV, i16, ", kids=", i17, ", series=");
            sbV.append(i18);
            sbV.append(", docs=");
            sbV.append(i19);
            sbV.append(")");
            return sbV.toString();
        }

        public /* synthetic */ CountryAccumulator(int i10, int i11, int i12, int i13, int i14, int i15, int i16, int i17, int i18, int i19, int i20, kotlin.jvm.internal.h hVar) {
            this((i20 & 1) != 0 ? 0 : i10, (i20 & 2) != 0 ? 0 : i11, (i20 & 4) != 0 ? 0 : i12, (i20 & 8) != 0 ? 0 : i13, (i20 & 16) != 0 ? 0 : i14, (i20 & 32) != 0 ? 0 : i15, (i20 & 64) != 0 ? 0 : i16, (i20 & 128) != 0 ? 0 : i17, (i20 & 256) != 0 ? 0 : i18, (i20 & 512) != 0 ? 0 : i19);
        }
    }

    @Metadata(d1 = {"\u0000!\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b.\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000*\u0001\u0000\b\u008a\b\u0018\u00002\u00020\u0001Bk\u0012\b\b\u0002\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0004\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0005\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0006\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0007\u001a\u00020\u0003\u0012\b\b\u0002\u0010\b\u001a\u00020\u0003\u0012\b\b\u0002\u0010\t\u001a\u00020\u0003\u0012\b\b\u0002\u0010\n\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u000b\u001a\u00020\u0003\u0012\b\b\u0002\u0010\f\u001a\u00020\u0003¢\u0006\u0004\b\r\u0010\u000eJ\t\u0010%\u001a\u00020\u0003HÆ\u0003J\t\u0010&\u001a\u00020\u0003HÆ\u0003J\t\u0010'\u001a\u00020\u0003HÆ\u0003J\t\u0010(\u001a\u00020\u0003HÆ\u0003J\t\u0010)\u001a\u00020\u0003HÆ\u0003J\t\u0010*\u001a\u00020\u0003HÆ\u0003J\t\u0010+\u001a\u00020\u0003HÆ\u0003J\t\u0010,\u001a\u00020\u0003HÆ\u0003J\t\u0010-\u001a\u00020\u0003HÆ\u0003J\t\u0010.\u001a\u00020\u0003HÆ\u0003Jr\u0010/\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u00032\b\b\u0002\u0010\u0006\u001a\u00020\u00032\b\b\u0002\u0010\u0007\u001a\u00020\u00032\b\b\u0002\u0010\b\u001a\u00020\u00032\b\b\u0002\u0010\t\u001a\u00020\u00032\b\b\u0002\u0010\n\u001a\u00020\u00032\b\b\u0002\u0010\u000b\u001a\u00020\u00032\b\b\u0002\u0010\f\u001a\u00020\u0003HÆ\u0001¢\u0006\u0002\u00100J\u0014\u00101\u001a\u0002022\b\u00103\u001a\u0004\u0018\u00010\u0001HÖ\u0083\u0004J\n\u00104\u001a\u00020\u0003HÖ\u0081\u0004J\n\u00105\u001a\u000206HÖ\u0081\u0004R\u001a\u0010\u0002\u001a\u00020\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000f\u0010\u0010\"\u0004\b\u0011\u0010\u0012R\u001a\u0010\u0004\u001a\u00020\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0013\u0010\u0010\"\u0004\b\u0014\u0010\u0012R\u001a\u0010\u0005\u001a\u00020\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0015\u0010\u0010\"\u0004\b\u0016\u0010\u0012R\u001a\u0010\u0006\u001a\u00020\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0017\u0010\u0010\"\u0004\b\u0018\u0010\u0012R\u001a\u0010\u0007\u001a\u00020\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0019\u0010\u0010\"\u0004\b\u001a\u0010\u0012R\u001a\u0010\b\u001a\u00020\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001b\u0010\u0010\"\u0004\b\u001c\u0010\u0012R\u001a\u0010\t\u001a\u00020\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001d\u0010\u0010\"\u0004\b\u001e\u0010\u0012R\u001a\u0010\n\u001a\u00020\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001f\u0010\u0010\"\u0004\b \u0010\u0012R\u001a\u0010\u000b\u001a\u00020\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b!\u0010\u0010\"\u0004\b\"\u0010\u0012R\u001a\u0010\f\u001a\u00020\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b#\u0010\u0010\"\u0004\b$\u0010\u0012¨\u00067"}, d2 = {"com/arflix/tv/ui/screens/tv/live/LiveCategoryKt$buildCategoryTree$RawCountryAccumulator", "", "total", "", "general", "k4", "fhd", SportsAddonCapabilities.SPORTS_CATEGORY_ROW_ID, "movies", "news", "kids", "series", "docs", "<init>", "(IIIIIIIIII)V", "getTotal", "()I", "setTotal", "(I)V", "getGeneral", "setGeneral", "getK4", "setK4", "getFhd", "setFhd", "getSports", "setSports", "getMovies", "setMovies", "getNews", "setNews", "getKids", "setKids", "getSeries", "setSeries", "getDocs", "setDocs", "component1", "component2", "component3", "component4", "component5", "component6", "component7", "component8", "component9", "component10", "copy", "(IIIIIIIIII)Lcom/arflix/tv/ui/screens/tv/live/LiveCategoryKt$buildCategoryTree$RawCountryAccumulator;", "equals", "", "other", "hashCode", "toString", "", "app_playRelease"}, k = 1, mv = {2, 3, 0}, xi = 48)
    public static final /* data */ class RawCountryAccumulator {
        private int docs;
        private int fhd;
        private int general;
        private int k4;
        private int kids;
        private int movies;
        private int news;
        private int series;
        private int sports;
        private int total;

        public RawCountryAccumulator(int i10, int i11, int i12, int i13, int i14, int i15, int i16, int i17, int i18, int i19) {
            this.total = i10;
            this.general = i11;
            this.k4 = i12;
            this.fhd = i13;
            this.sports = i14;
            this.movies = i15;
            this.news = i16;
            this.kids = i17;
            this.series = i18;
            this.docs = i19;
        }

        public static /* synthetic */ RawCountryAccumulator copy$default(RawCountryAccumulator rawCountryAccumulator, int i10, int i11, int i12, int i13, int i14, int i15, int i16, int i17, int i18, int i19, int i20, Object obj) {
            if ((i20 & 1) != 0) {
                i10 = rawCountryAccumulator.total;
            }
            if ((i20 & 2) != 0) {
                i11 = rawCountryAccumulator.general;
            }
            if ((i20 & 4) != 0) {
                i12 = rawCountryAccumulator.k4;
            }
            if ((i20 & 8) != 0) {
                i13 = rawCountryAccumulator.fhd;
            }
            if ((i20 & 16) != 0) {
                i14 = rawCountryAccumulator.sports;
            }
            if ((i20 & 32) != 0) {
                i15 = rawCountryAccumulator.movies;
            }
            if ((i20 & 64) != 0) {
                i16 = rawCountryAccumulator.news;
            }
            if ((i20 & 128) != 0) {
                i17 = rawCountryAccumulator.kids;
            }
            if ((i20 & 256) != 0) {
                i18 = rawCountryAccumulator.series;
            }
            if ((i20 & 512) != 0) {
                i19 = rawCountryAccumulator.docs;
            }
            int i21 = i18;
            int i22 = i19;
            int i23 = i16;
            int i24 = i17;
            int i25 = i14;
            int i26 = i15;
            return rawCountryAccumulator.copy(i10, i11, i12, i13, i25, i26, i23, i24, i21, i22);
        }

        /* JADX INFO: renamed from: component1, reason: from getter */
        public final int getTotal() {
            return this.total;
        }

        /* JADX INFO: renamed from: component10, reason: from getter */
        public final int getDocs() {
            return this.docs;
        }

        /* JADX INFO: renamed from: component2, reason: from getter */
        public final int getGeneral() {
            return this.general;
        }

        /* JADX INFO: renamed from: component3, reason: from getter */
        public final int getK4() {
            return this.k4;
        }

        /* JADX INFO: renamed from: component4, reason: from getter */
        public final int getFhd() {
            return this.fhd;
        }

        /* JADX INFO: renamed from: component5, reason: from getter */
        public final int getSports() {
            return this.sports;
        }

        /* JADX INFO: renamed from: component6, reason: from getter */
        public final int getMovies() {
            return this.movies;
        }

        /* JADX INFO: renamed from: component7, reason: from getter */
        public final int getNews() {
            return this.news;
        }

        /* JADX INFO: renamed from: component8, reason: from getter */
        public final int getKids() {
            return this.kids;
        }

        /* JADX INFO: renamed from: component9, reason: from getter */
        public final int getSeries() {
            return this.series;
        }

        public final RawCountryAccumulator copy(int total, int general, int k42, int fhd, int sports, int movies, int news, int kids, int series, int docs) {
            return new RawCountryAccumulator(total, general, k42, fhd, sports, movies, news, kids, series, docs);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof RawCountryAccumulator)) {
                return false;
            }
            RawCountryAccumulator rawCountryAccumulator = (RawCountryAccumulator) other;
            return this.total == rawCountryAccumulator.total && this.general == rawCountryAccumulator.general && this.k4 == rawCountryAccumulator.k4 && this.fhd == rawCountryAccumulator.fhd && this.sports == rawCountryAccumulator.sports && this.movies == rawCountryAccumulator.movies && this.news == rawCountryAccumulator.news && this.kids == rawCountryAccumulator.kids && this.series == rawCountryAccumulator.series && this.docs == rawCountryAccumulator.docs;
        }

        public final int getDocs() {
            return this.docs;
        }

        public final int getFhd() {
            return this.fhd;
        }

        public final int getGeneral() {
            return this.general;
        }

        public final int getK4() {
            return this.k4;
        }

        public final int getKids() {
            return this.kids;
        }

        public final int getMovies() {
            return this.movies;
        }

        public final int getNews() {
            return this.news;
        }

        public final int getSeries() {
            return this.series;
        }

        public final int getSports() {
            return this.sports;
        }

        public final int getTotal() {
            return this.total;
        }

        public int hashCode() {
            return (((((((((((((((((this.total * 31) + this.general) * 31) + this.k4) * 31) + this.fhd) * 31) + this.sports) * 31) + this.movies) * 31) + this.news) * 31) + this.kids) * 31) + this.series) * 31) + this.docs;
        }

        public final void setDocs(int i10) {
            this.docs = i10;
        }

        public final void setFhd(int i10) {
            this.fhd = i10;
        }

        public final void setGeneral(int i10) {
            this.general = i10;
        }

        public final void setK4(int i10) {
            this.k4 = i10;
        }

        public final void setKids(int i10) {
            this.kids = i10;
        }

        public final void setMovies(int i10) {
            this.movies = i10;
        }

        public final void setNews(int i10) {
            this.news = i10;
        }

        public final void setSeries(int i10) {
            this.series = i10;
        }

        public final void setSports(int i10) {
            this.sports = i10;
        }

        public final void setTotal(int i10) {
            this.total = i10;
        }

        public String toString() {
            int i10 = this.total;
            int i11 = this.general;
            int i12 = this.k4;
            int i13 = this.fhd;
            int i14 = this.sports;
            int i15 = this.movies;
            int i16 = this.news;
            int i17 = this.kids;
            int i18 = this.series;
            int i19 = this.docs;
            StringBuilder sbV = androidx.compose.foundation.c.v("RawCountryAccumulator(total=", i10, ", general=", i11, ", k4=");
            androidx.fragment.app.a2.y(sbV, i12, ", fhd=", i13, ", sports=");
            androidx.fragment.app.a2.y(sbV, i14, ", movies=", i15, ", news=");
            androidx.fragment.app.a2.y(sbV, i16, ", kids=", i17, ", series=");
            sbV.append(i18);
            sbV.append(", docs=");
            sbV.append(i19);
            sbV.append(")");
            return sbV.toString();
        }

        public /* synthetic */ RawCountryAccumulator(int i10, int i11, int i12, int i13, int i14, int i15, int i16, int i17, int i18, int i19, int i20, kotlin.jvm.internal.h hVar) {
            this((i20 & 1) != 0 ? 0 : i10, (i20 & 2) != 0 ? 0 : i11, (i20 & 4) != 0 ? 0 : i12, (i20 & 8) != 0 ? 0 : i13, (i20 & 16) != 0 ? 0 : i14, (i20 & 32) != 0 ? 0 : i15, (i20 & 64) != 0 ? 0 : i16, (i20 & 128) != 0 ? 0 : i17, (i20 & 256) != 0 ? 0 : i18, (i20 & 512) != 0 ? 0 : i19);
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static final LiveCategoryTree buildCategoryTree(List<IptvChannel> list, Set<String> set, Set<String> set2, Set<String> set3, List<String> list2) {
        int i10;
        int i11;
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        LinkedHashMap linkedHashMap2 = new LinkedHashMap();
        LinkedHashMap linkedHashMap3 = new LinkedHashMap();
        HashSet hashSetA1 = kotlin.collections.x.a1(set3);
        Iterator<T> it = list.iterator();
        int i12 = 0;
        int i13 = 0;
        int i14 = 0;
        int i15 = 0;
        int i16 = 0;
        int i17 = 0;
        int i18 = 0;
        int i19 = 0;
        int i20 = 0;
        while (true) {
            int i21 = 1;
            if (it.hasNext()) {
                IptvChannel iptvChannel = (IptvChannel) it.next();
                String id = iptvChannel.getId();
                String strG0 = kotlin.text.o.G0(':', id, id);
                ChannelTraits channelTraitsTraits = traits(iptvChannel);
                String strPlaylistGroupLabel = playlistGroupLabel(iptvChannel.getGroup());
                String strPlaylistGroupKey = playlistGroupKey(strG0, strPlaylistGroupLabel);
                String strPlaylistGroupCategoryId = playlistGroupCategoryId(strG0, iptvChannel.getGroup());
                LinkedHashMap linkedHashMap4 = hashSetA1.contains(strPlaylistGroupKey) ? linkedHashMap3 : linkedHashMap2;
                x6.x xVar = (x6.x) linkedHashMap4.get(strPlaylistGroupCategoryId);
                linkedHashMap4.put(strPlaylistGroupCategoryId, new x6.x(strPlaylistGroupLabel, Integer.valueOf((xVar != null ? ((Number) xVar.f22609l).intValue() : 0) + 1)));
                if (!hashSetA1.contains(strPlaylistGroupKey)) {
                    if (channelTraitsTraits.isAdult()) {
                        i19++;
                    } else {
                        i20++;
                        if (channelTraitsTraits.getQuality() == Quality.K4) {
                            i12++;
                        }
                        Genre genre = channelTraitsTraits.getGenre();
                        int[] iArr = WhenMappings.$EnumSwitchMapping$0;
                        int i22 = iArr[genre.ordinal()];
                        if (i22 == 1) {
                            i15++;
                        } else if (i22 == 2) {
                            i13++;
                        } else if (i22 == 3) {
                            i14++;
                        } else if (i22 == 5) {
                            i16++;
                        } else if (i22 == 6) {
                            i18++;
                        } else if (i22 == 7) {
                            i17++;
                        }
                        String country = channelTraitsTraits.getCountry();
                        if (country != null && !kotlin.text.o.h0(country)) {
                            Object rawCountryAccumulator = linkedHashMap.get(country);
                            if (rawCountryAccumulator == null) {
                                rawCountryAccumulator = new RawCountryAccumulator(0, 0, 0, 0, 0, 0, 0, 0, 0, 0, AnalyticsListener.EVENT_DRM_KEYS_LOADED, null);
                                linkedHashMap.put(country, rawCountryAccumulator);
                            }
                            RawCountryAccumulator rawCountryAccumulator2 = (RawCountryAccumulator) rawCountryAccumulator;
                            rawCountryAccumulator2.setTotal(rawCountryAccumulator2.getTotal() + 1);
                            switch (iArr[channelTraitsTraits.getGenre().ordinal()]) {
                                case 1:
                                    rawCountryAccumulator2.setNews(rawCountryAccumulator2.getNews() + 1);
                                    break;
                                case 2:
                                    rawCountryAccumulator2.setSports(rawCountryAccumulator2.getSports() + 1);
                                    break;
                                case 3:
                                    rawCountryAccumulator2.setMovies(rawCountryAccumulator2.getMovies() + 1);
                                    break;
                                case 4:
                                    rawCountryAccumulator2.setSeries(rawCountryAccumulator2.getSeries() + 1);
                                    break;
                                case 5:
                                    rawCountryAccumulator2.setKids(rawCountryAccumulator2.getKids() + 1);
                                    break;
                                case 7:
                                    rawCountryAccumulator2.setDocs(rawCountryAccumulator2.getDocs() + 1);
                                    break;
                                case 8:
                                    rawCountryAccumulator2.setGeneral(rawCountryAccumulator2.getGeneral() + 1);
                                    break;
                            }
                            int i23 = WhenMappings.$EnumSwitchMapping$1[channelTraitsTraits.getQuality().ordinal()];
                            if (i23 == 1) {
                                rawCountryAccumulator2.setK4(rawCountryAccumulator2.getK4() + 1);
                            } else if (i23 == 2) {
                                rawCountryAccumulator2.setFhd(rawCountryAccumulator2.getFhd() + 1);
                            }
                        }
                    }
                }
            } else {
                List listE = t7.a.E(new LiveCategory("g-4k", "4K | Ultra HD", i12, CategoryIcon.Grid, null, null, null, null, PsExtractor.VIDEO_STREAM_MASK, null), new LiveCategory("g-sports", "Sports · Global", i13, CategoryIcon.Sport, null, 0 == true ? 1 : 0, null, null, PsExtractor.VIDEO_STREAM_MASK, null), new LiveCategory("g-movies", "Movies · Global", i14, CategoryIcon.Movie, null, null, null, null, PsExtractor.VIDEO_STREAM_MASK, null), new LiveCategory("g-news", "News · Global", i15, CategoryIcon.News, null, 0 == true ? 1 : 0, null, null, PsExtractor.VIDEO_STREAM_MASK, null), new LiveCategory("g-kids", "Kids · Global", i16, CategoryIcon.Kids, null, null, null, null, PsExtractor.VIDEO_STREAM_MASK, null), new LiveCategory("g-docs", "Documentary", i17, CategoryIcon.Docs, null, 0 == true ? 1 : 0, null, null, PsExtractor.VIDEO_STREAM_MASK, null), new LiveCategory("g-music", "Music", i18, CategoryIcon.Music, null, null, null, null, PsExtractor.VIDEO_STREAM_MASK, null));
                ArrayList arrayList = new ArrayList();
                for (Object obj : listE) {
                    if (((LiveCategory) obj).getCount() > 0) {
                        arrayList.add(obj);
                    }
                }
                List<Map.Entry> listW0 = kotlin.collections.x.W0(linkedHashMap.entrySet(), new Comparator() { // from class: com.arflix.tv.ui.screens.tv.live.LiveCategoryKt$buildCategoryTree$$inlined$sortedByDescending$2
                    @Override // java.util.Comparator
                    public final int compare(T t2, T t10) {
                        return com.google.common.util.concurrent.r0.e(Integer.valueOf(((LiveCategoryKt.RawCountryAccumulator) ((Map.Entry) t10).getValue()).getTotal()), Integer.valueOf(((LiveCategoryKt.RawCountryAccumulator) ((Map.Entry) t2).getValue()).getTotal()));
                    }
                });
                ArrayList arrayList2 = new ArrayList(kotlin.collections.s.U(listW0, 10));
                for (Map.Entry entry : listW0) {
                    String str = (String) entry.getKey();
                    RawCountryAccumulator rawCountryAccumulator3 = (RawCountryAccumulator) entry.getValue();
                    z6.c cVarN = t7.a.n();
                    buildCategoryTree$lambda$10$0$addChild(cVarN, str, "general", rawCountryAccumulator3.getGeneral());
                    buildCategoryTree$lambda$10$0$addChild(cVarN, str, "4k", rawCountryAccumulator3.getK4());
                    buildCategoryTree$lambda$10$0$addChild(cVarN, str, "fhd", rawCountryAccumulator3.getFhd());
                    buildCategoryTree$lambda$10$0$addChild(cVarN, str, SportsAddonCapabilities.SPORTS_CATEGORY_ROW_ID, rawCountryAccumulator3.getSports());
                    buildCategoryTree$lambda$10$0$addChild(cVarN, str, "movies", rawCountryAccumulator3.getMovies());
                    buildCategoryTree$lambda$10$0$addChild(cVarN, str, "news", rawCountryAccumulator3.getNews());
                    buildCategoryTree$lambda$10$0$addChild(cVarN, str, "kids", rawCountryAccumulator3.getKids());
                    buildCategoryTree$lambda$10$0$addChild(cVarN, str, "entertainment", rawCountryAccumulator3.getSeries());
                    buildCategoryTree$lambda$10$0$addChild(cVarN, str, "documentary", rawCountryAccumulator3.getDocs());
                    arrayList2.add(new LiveCategory(str, countryName(str), rawCountryAccumulator3.getTotal(), CategoryIcon.Country, countryFlag(str), t7.a.e(cVarN), null, null, PsExtractor.AUDIO_STREAM, null));
                }
                List listSingletonList = Collections.singletonList(new LiveCategory("adult", "Adult", i19, CategoryIcon.Lock, null, null, null, null, PsExtractor.VIDEO_STREAM_MASK, null));
                ArrayList arrayList3 = new ArrayList();
                for (Object obj2 : listSingletonList) {
                    if (((LiveCategory) obj2).getCount() > 0) {
                        arrayList3.add(obj2);
                    }
                }
                ga.t tVar = new ga.t(ga.r.J(new ga.p(list, 3), new o2(set3, i21)), new n2(26));
                HashSet hashSet = new HashSet();
                ga.r.T(tVar, hashSet);
                Set<String> set4 = set;
                if ((set4 instanceof Collection) && set4.isEmpty()) {
                    i10 = 0;
                } else {
                    Iterator<T> it2 = set4.iterator();
                    int i24 = 0;
                    while (it2.hasNext()) {
                        if (hashSet.contains((String) it2.next()) && (i24 = i24 + 1) < 0) {
                            t7.a.P();
                            throw null;
                        }
                    }
                    i10 = i24;
                }
                LiveCategory liveCategory = new LiveCategory("fav", "Favorites", i10, CategoryIcon.Favorite, null, null, null, null, PsExtractor.VIDEO_STREAM_MASK, null);
                Set<String> set5 = set2;
                if ((set5 instanceof Collection) && set5.isEmpty()) {
                    i11 = 0;
                } else {
                    Iterator<T> it3 = set5.iterator();
                    int i25 = 0;
                    while (it3.hasNext()) {
                        if (hashSet.contains((String) it3.next()) && (i25 = i25 + 1) < 0) {
                            t7.a.P();
                            throw null;
                        }
                    }
                    i11 = i25;
                }
                List listE2 = t7.a.E(liveCategory, new LiveCategory("recent", "Recently Watched", i11, CategoryIcon.Recent, null, null, null, null, PsExtractor.VIDEO_STREAM_MASK, null), new LiveCategory(TtmlNode.COMBINE_ALL, "All Channels", i20, CategoryIcon.All, null, kotlin.collections.x.I0(kotlin.collections.x.I0(arrayList, arrayList2), arrayList3), null, null, 208, null));
                List<Map.Entry<String, x6.x>> listOrderPlaylistGroups = orderPlaylistGroups(linkedHashMap2, list2);
                ArrayList arrayList4 = new ArrayList(kotlin.collections.s.U(listOrderPlaylistGroups, 10));
                Iterator<T> it4 = listOrderPlaylistGroups.iterator();
                while (it4.hasNext()) {
                    Map.Entry entry2 = (Map.Entry) it4.next();
                    String str2 = (String) entry2.getKey();
                    x6.x xVar2 = (x6.x) entry2.getValue();
                    arrayList4.add(new LiveCategory(str2, (String) xVar2.f22608i, ((Number) xVar2.f22609l).intValue(), CategoryIcon.Grid, null, null, (String) xVar2.f22608i, playlistIdFromGroupCategoryId(str2), 48, null));
                }
                List<Map.Entry<String, x6.x>> listOrderPlaylistGroups2 = orderPlaylistGroups(linkedHashMap3, list2);
                ArrayList arrayList5 = new ArrayList(kotlin.collections.s.U(listOrderPlaylistGroups2, 10));
                Iterator<T> it5 = listOrderPlaylistGroups2.iterator();
                while (it5.hasNext()) {
                    Map.Entry entry3 = (Map.Entry) it5.next();
                    String str3 = (String) entry3.getKey();
                    x6.x xVar3 = (x6.x) entry3.getValue();
                    arrayList5.add(new LiveCategory(str3, (String) xVar3.f22608i, ((Number) xVar3.f22609l).intValue(), CategoryIcon.Grid, null, null, (String) xVar3.f22608i, playlistIdFromGroupCategoryId(str3), 48, null));
                }
                LiveSection liveSection = new LiveSection("playlist", "PLAYLIST", arrayList4);
                kotlin.collections.z zVar = kotlin.collections.z.f19728i;
                return new LiveCategoryTree(listE2, liveSection, new LiveSection("matched", "MATCHED", zVar), new LiveSection("adult", "ADULT", zVar), new LiveSection("hidden", "HIDDEN", arrayList5));
            }
        }
    }
}
