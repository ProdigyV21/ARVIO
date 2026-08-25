package com.arflix.tv.data.api;

import androidx.media3.extractor.text.ttml.TtmlNode;
import java.util.List;
import kotlin.Metadata;
import zc.k;
import zc.o;
import zc.s;
import zc.t;

/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u0000l\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\bf\u0018\u00002\u00020\u0001J\u001a\u0010\u0005\u001a\u00020\u00042\b\b\u0001\u0010\u0003\u001a\u00020\u0002H§@¢\u0006\u0004\b\u0005\u0010\u0006J\u001a\u0010\b\u001a\u00020\u00072\b\b\u0001\u0010\u0003\u001a\u00020\u0002H§@¢\u0006\u0004\b\b\u0010\u0006J>\u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\u000e0\r2\b\b\u0001\u0010\u0003\u001a\u00020\u00022\b\b\u0003\u0010\n\u001a\u00020\t2\b\b\u0003\u0010\u000b\u001a\u00020\t2\b\b\u0003\u0010\f\u001a\u00020\u0002H§@¢\u0006\u0004\b\u000f\u0010\u0010J.\u0010\u0015\u001a\u00020\u00142\b\b\u0001\u0010\u0011\u001a\u00020\u00022\b\b\u0001\u0010\u0003\u001a\u00020\u00022\b\b\u0001\u0010\u0013\u001a\u00020\u0012H§@¢\u0006\u0004\b\u0015\u0010\u0016J.\u0010\u0018\u001a\u00020\u00172\b\b\u0001\u0010\u0003\u001a\u00020\u00022\b\b\u0003\u0010\n\u001a\u00020\t2\b\b\u0003\u0010\u000b\u001a\u00020\tH§@¢\u0006\u0004\b\u0018\u0010\u0019J$\u0010\u001b\u001a\u00020\u00142\b\b\u0001\u0010\u0003\u001a\u00020\u00022\b\b\u0001\u0010\u0013\u001a\u00020\u001aH§@¢\u0006\u0004\b\u001b\u0010\u001cJ$\u0010\u001d\u001a\u00020\u00142\b\b\u0001\u0010\u0003\u001a\u00020\u00022\b\b\u0001\u0010\u0013\u001a\u00020\u001aH§@¢\u0006\u0004\b\u001d\u0010\u001cJ \u0010\u001f\u001a\b\u0012\u0004\u0012\u00020\u001e0\r2\b\b\u0001\u0010\u0003\u001a\u00020\u0002H§@¢\u0006\u0004\b\u001f\u0010\u0006J.\u0010\"\u001a\u00020!2\b\b\u0001\u0010\u0011\u001a\u00020\u00022\b\b\u0001\u0010\u0003\u001a\u00020\u00022\b\b\u0001\u0010\u0013\u001a\u00020 H§@¢\u0006\u0004\b\"\u0010#J$\u0010&\u001a\u00020%2\b\b\u0001\u0010\u0003\u001a\u00020\u00022\b\b\u0001\u0010\u0013\u001a\u00020$H§@¢\u0006\u0004\b&\u0010'¨\u0006(À\u0006\u0003"}, d2 = {"Lcom/arflix/tv/data/api/MdbListApi;", "", "", "apiKey", "Lcom/arflix/tv/data/api/MdbUser;", "getUser", "(Ljava/lang/String;Ld7/d;)Ljava/lang/Object;", "Lcom/arflix/tv/data/api/MdbLastActivities;", "getLastActivities", "", "limit", "offset", "unified", "", "Lcom/arflix/tv/data/api/MdbWatchlistItem;", "getWatchlistItems", "(Ljava/lang/String;IILjava/lang/String;Ld7/d;)Ljava/lang/Object;", "action", "Lcom/arflix/tv/data/api/MdbWatchlistModifyBody;", TtmlNode.TAG_BODY, "Lcom/arflix/tv/data/api/MdbCountResponse;", "modifyWatchlist", "(Ljava/lang/String;Ljava/lang/String;Lcom/arflix/tv/data/api/MdbWatchlistModifyBody;Ld7/d;)Ljava/lang/Object;", "Lcom/arflix/tv/data/api/MdbWatchedResponse;", "getWatched", "(Ljava/lang/String;IILd7/d;)Ljava/lang/Object;", "Lcom/arflix/tv/data/api/MdbWatchedBody;", "addWatched", "(Ljava/lang/String;Lcom/arflix/tv/data/api/MdbWatchedBody;Ld7/d;)Ljava/lang/Object;", "removeWatched", "Lcom/arflix/tv/data/api/MdbPlaybackItem;", "getPlayback", "Lcom/arflix/tv/data/api/MdbScrobbleBody;", "Lcom/arflix/tv/data/api/MdbScrobbleResponse;", "scrobble", "(Ljava/lang/String;Ljava/lang/String;Lcom/arflix/tv/data/api/MdbScrobbleBody;Ld7/d;)Ljava/lang/Object;", "Lcom/arflix/tv/data/api/MdbScrobbleClearBody;", "Lcom/arflix/tv/data/api/MdbScrobbleClearResponse;", "scrobbleClear", "(Ljava/lang/String;Lcom/arflix/tv/data/api/MdbScrobbleClearBody;Ld7/d;)Ljava/lang/Object;", "app_playRelease"}, k = 1, mv = {2, 3, 0}, xi = 48)
public interface MdbListApi {

    @Metadata(k = 3, mv = {2, 3, 0}, xi = 48)
    public static final class DefaultImpls {
    }

    @k({"Content-Type: application/json"})
    @o("sync/watched")
    Object addWatched(@t("apikey") String str, @zc.a MdbWatchedBody mdbWatchedBody, d7.d<? super MdbCountResponse> dVar);

    @zc.f("sync/last_activities")
    Object getLastActivities(@t("apikey") String str, d7.d<? super MdbLastActivities> dVar);

    @zc.f("sync/playback")
    Object getPlayback(@t("apikey") String str, d7.d<? super List<MdbPlaybackItem>> dVar);

    @zc.f("user")
    Object getUser(@t("apikey") String str, d7.d<? super MdbUser> dVar);

    @zc.f("sync/watched")
    Object getWatched(@t("apikey") String str, @t("limit") int i10, @t("offset") int i11, d7.d<? super MdbWatchedResponse> dVar);

    @zc.f("watchlist/items")
    Object getWatchlistItems(@t("apikey") String str, @t("limit") int i10, @t("offset") int i11, @t("unified") String str2, d7.d<? super List<MdbWatchlistItem>> dVar);

    @k({"Content-Type: application/json"})
    @o("watchlist/items/{action}")
    Object modifyWatchlist(@s("action") String str, @t("apikey") String str2, @zc.a MdbWatchlistModifyBody mdbWatchlistModifyBody, d7.d<? super MdbCountResponse> dVar);

    @k({"Content-Type: application/json"})
    @o("sync/watched/remove")
    Object removeWatched(@t("apikey") String str, @zc.a MdbWatchedBody mdbWatchedBody, d7.d<? super MdbCountResponse> dVar);

    @k({"Content-Type: application/json"})
    @o("scrobble/{action}")
    Object scrobble(@s("action") String str, @t("apikey") String str2, @zc.a MdbScrobbleBody mdbScrobbleBody, d7.d<? super MdbScrobbleResponse> dVar);

    @k({"Content-Type: application/json"})
    @o("scrobble/clear")
    Object scrobbleClear(@t("apikey") String str, @zc.a MdbScrobbleClearBody mdbScrobbleClearBody, d7.d<? super MdbScrobbleClearResponse> dVar);
}
