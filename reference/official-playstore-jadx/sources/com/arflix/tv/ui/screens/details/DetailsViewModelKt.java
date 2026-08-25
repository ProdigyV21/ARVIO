package com.arflix.tv.ui.screens.details;

import androidx.media3.common.C;
import androidx.media3.extractor.text.ttml.TtmlNode;
import com.arflix.tv.data.model.Addon;
import com.arflix.tv.data.model.AddonType;
import com.arflix.tv.data.model.ProxyHeaders;
import com.arflix.tv.data.model.SportsAddonCapabilities;
import com.arflix.tv.data.model.StreamBehaviorHints;
import com.arflix.tv.data.model.StreamSource;
import com.arflix.tv.data.repository.HomeServerRepository;
import com.arflix.tv.domain.model.LocalScraperResult;
import java.util.Locale;
import java.util.Map;
import kotlin.Metadata;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u00000\n\u0000\n\u0002\u0010$\n\u0002\u0010\b\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0010\t\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u001a\u0010\u0010\u0006\u001a\u00020\u00032\u0006\u0010\u0007\u001a\u00020\bH\u0002\u001a\u0010\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\fH\u0002\u001a\f\u0010\r\u001a\u00020\n*\u00020\u000eH\u0002\u001a\f\u0010\u000f\u001a\u00020\f*\u00020\u0010H\u0002\"\u001a\u0010\u0000\u001a\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00030\u0001X\u0082\u0004¢\u0006\u0002\n\u0000\"\u001a\u0010\u0004\u001a\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00030\u0001X\u0082\u0004¢\u0006\u0002\n\u0000\"\u001a\u0010\u0005\u001a\u000e\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u00030\u0001X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0011"}, d2 = {"movieGenres", "", "", "", "tvGenres", "languages", "formatBudget", "budget", "", "isSupplementalStream", "", "stream", "Lcom/arflix/tv/data/model/StreamSource;", "isVodStreamingAddon", "Lcom/arflix/tv/data/model/Addon;", "toStreamSource", "Lcom/arflix/tv/domain/model/LocalScraperResult;", "app_playRelease"}, k = 2, mv = {2, 3, 0}, xi = 48)
public final class DetailsViewModelKt {
    private static final Map<Integer, String> movieGenres = kotlin.collections.h0.t0(new x6.x(28, "Action"), new x6.x(12, "Adventure"), new x6.x(16, "Animation"), new x6.x(35, "Comedy"), new x6.x(80, "Crime"), new x6.x(99, "Documentary"), new x6.x(18, "Drama"), new x6.x(10751, "Family"), new x6.x(14, "Fantasy"), new x6.x(36, "History"), new x6.x(27, "Horror"), new x6.x(10402, "Music"), new x6.x(9648, "Mystery"), new x6.x(10749, "Romance"), new x6.x(878, "Sci-Fi"), new x6.x(10770, "TV Movie"), new x6.x(53, "Thriller"), new x6.x(10752, "War"), new x6.x(37, "Western"));
    private static final Map<Integer, String> tvGenres = kotlin.collections.h0.t0(new x6.x(10759, "Action & Adventure"), new x6.x(16, "Animation"), new x6.x(35, "Comedy"), new x6.x(80, "Crime"), new x6.x(99, "Documentary"), new x6.x(18, "Drama"), new x6.x(10751, "Family"), new x6.x(10762, "Kids"), new x6.x(9648, "Mystery"), new x6.x(10763, "News"), new x6.x(10764, "Reality"), new x6.x(10765, "Sci-Fi & Fantasy"), new x6.x(10766, "Soap"), new x6.x(10767, "Talk"), new x6.x(10768, "War & Politics"), new x6.x(37, "Western"));
    private static final Map<String, String> languages = kotlin.collections.h0.t0(new x6.x("en", "English"), new x6.x("es", "Spanish"), new x6.x("fr", "French"), new x6.x("de", "German"), new x6.x("it", "Italian"), new x6.x("pt", "Portuguese"), new x6.x("ja", "Japanese"), new x6.x("ko", "Korean"), new x6.x("zh", "Chinese"), new x6.x("hi", "Hindi"), new x6.x("ru", "Russian"), new x6.x("ar", "Arabic"), new x6.x("nl", "Dutch"), new x6.x("sv", "Swedish"), new x6.x("pl", "Polish"), new x6.x("tr", "Turkish"), new x6.x("th", "Thai"), new x6.x("vi", "Vietnamese"), new x6.x(TtmlNode.ATTR_ID, "Indonesian"), new x6.x("tl", "Tagalog"));

    /* JADX INFO: Access modifiers changed from: private */
    public static final String formatBudget(long j10) {
        if (j10 < C.NANOS_PER_SECOND) {
            return j10 >= 1000000 ? androidx.compose.material3.d.k(j10 / ((long) 1000000), "$", "M") : j10 >= 1000 ? androidx.compose.material3.d.k(j10 / ((long) 1000), "$", "K") : androidx.compose.foundation.c.q(j10, "$");
        }
        return "$" + (j10 / 1.0E9d) + "B";
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final boolean isSupplementalStream(StreamSource streamSource) {
        return kotlin.jvm.internal.p.a(streamSource.getAddonId(), "iptv_xtream_vod") || kotlin.jvm.internal.p.a(streamSource.getAddonId(), HomeServerRepository.ADDON_ID);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final boolean isVodStreamingAddon(Addon addon) {
        return (!addon.isEnabled() || addon.getType() == AddonType.SUBTITLE || SportsAddonCapabilities.INSTANCE.isSportsOnlyLiveTvAddon(addon)) ? false : true;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final StreamSource toStreamSource(LocalScraperResult localScraperResult) {
        String title = localScraperResult.getTitle();
        String provider = localScraperResult.getProvider();
        if (provider == null && (provider = localScraperResult.getName()) == null) {
            provider = "Plugin";
        }
        String str = provider;
        String provider2 = localScraperResult.getProvider();
        String strConcat = "plugin_".concat(provider2 != null ? kotlin.text.u.O(provider2.toLowerCase(Locale.ROOT), " ", "_", false) : "unknown");
        String quality = localScraperResult.getQuality();
        if (quality == null) {
            quality = "Unknown";
        }
        String str2 = quality;
        String size = localScraperResult.getSize();
        if (size == null) {
            size = "";
        }
        String str3 = size;
        String url = localScraperResult.getUrl();
        String infoHash = localScraperResult.getInfoHash();
        Map<String, String> headers = localScraperResult.getHeaders();
        StreamBehaviorHints streamBehaviorHints = headers != null ? new StreamBehaviorHints(false, null, null, null, new ProxyHeaders(headers, null, 2, null), null, null, null, null, null, null, null, null, null, 16366, null) : null;
        kotlin.collections.z zVar = kotlin.collections.z.f19728i;
        return new StreamSource(title, str, strConcat, str2, str3, null, url, infoHash, null, streamBehaviorHints, zVar, zVar, null, null, null, 24576, null);
    }
}
