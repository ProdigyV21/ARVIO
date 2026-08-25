package com.arflix.tv.data.api;

import androidx.media3.extractor.text.ttml.TtmlNode;
import com.google.gson.JsonElement;
import io.ktor.http.LinkHeader;
import java.util.List;
import kotlin.Metadata;
import retrofit2.r0;
import zc.i;
import zc.o;
import zc.s;
import zc.t;

/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u0000n\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0003\bf\u0018\u00002\u00020\u0001J\u001a\u0010\u0005\u001a\u00020\u00042\b\b\u0001\u0010\u0003\u001a\u00020\u0002H§@¢\u0006\u0004\b\u0005\u0010\u0006J$\u0010\t\u001a\u00020\b2\b\b\u0001\u0010\u0007\u001a\u00020\u00022\b\b\u0001\u0010\u0003\u001a\u00020\u0002H§@¢\u0006\u0004\b\t\u0010\nJ4\u0010\u0010\u001a\b\u0012\u0004\u0012\u00020\u000f0\u000e2\b\b\u0001\u0010\u000b\u001a\u00020\u00022\b\b\u0001\u0010\u0003\u001a\u00020\u00022\b\b\u0001\u0010\r\u001a\u00020\fH§@¢\u0006\u0004\b\u0010\u0010\u0011J4\u0010\u0012\u001a\b\u0012\u0004\u0012\u00020\u000f0\u000e2\b\b\u0001\u0010\u000b\u001a\u00020\u00022\b\b\u0001\u0010\u0003\u001a\u00020\u00022\b\b\u0001\u0010\r\u001a\u00020\fH§@¢\u0006\u0004\b\u0012\u0010\u0011J4\u0010\u0013\u001a\b\u0012\u0004\u0012\u00020\u000f0\u000e2\b\b\u0001\u0010\u000b\u001a\u00020\u00022\b\b\u0001\u0010\u0003\u001a\u00020\u00022\b\b\u0001\u0010\r\u001a\u00020\fH§@¢\u0006\u0004\b\u0013\u0010\u0011J$\u0010\u0015\u001a\u00020\u00142\b\b\u0001\u0010\u000b\u001a\u00020\u00022\b\b\u0001\u0010\u0003\u001a\u00020\u0002H§@¢\u0006\u0004\b\u0015\u0010\nJ$\u0010\u0017\u001a\u00020\u00162\b\b\u0001\u0010\u000b\u001a\u00020\u00022\b\b\u0001\u0010\u0003\u001a\u00020\u0002H§@¢\u0006\u0004\b\u0017\u0010\nJl\u0010 \u001a\u00020\u001f2\b\b\u0001\u0010\u000b\u001a\u00020\u00022\b\b\u0001\u0010\u0003\u001a\u00020\u00022\b\b\u0001\u0010\u0018\u001a\u00020\u00022\b\b\u0003\u0010\u0019\u001a\u00020\u00022\n\b\u0003\u0010\u001a\u001a\u0004\u0018\u00010\u00022\b\b\u0003\u0010\u001b\u001a\u00020\u00022\b\b\u0003\u0010\u001c\u001a\u00020\u00022\b\b\u0003\u0010\u001d\u001a\u00020\u00022\b\b\u0003\u0010\u001e\u001a\u00020\u0002H§@¢\u0006\u0004\b \u0010!J*\u0010$\u001a\b\u0012\u0004\u0012\u00020#0\"2\b\b\u0001\u0010\u000b\u001a\u00020\u00022\b\b\u0001\u0010\u0003\u001a\u00020\u0002H§@¢\u0006\u0004\b$\u0010\nJ4\u0010(\u001a\b\u0012\u0004\u0012\u00020'0\u000e2\b\b\u0001\u0010\u000b\u001a\u00020\u00022\b\b\u0001\u0010\u0003\u001a\u00020\u00022\b\b\u0001\u0010&\u001a\u00020%H§@¢\u0006\u0004\b(\u0010)J@\u0010,\u001a\b\u0012\u0004\u0012\u00020'0\u000e2\b\b\u0001\u0010\u000b\u001a\u00020\u00022\b\b\u0001\u0010\u0003\u001a\u00020\u00022\b\b\u0001\u0010\r\u001a\u00020*2\n\b\u0003\u0010+\u001a\u0004\u0018\u00010\u0002H§@¢\u0006\u0004\b,\u0010-J4\u0010.\u001a\b\u0012\u0004\u0012\u00020'0\u000e2\b\b\u0001\u0010\u000b\u001a\u00020\u00022\b\b\u0001\u0010\u0003\u001a\u00020\u00022\b\b\u0001\u0010\r\u001a\u00020*H§@¢\u0006\u0004\b.\u0010/J4\u00101\u001a\b\u0012\u0004\u0012\u00020'0\u000e2\b\b\u0001\u0010\u000b\u001a\u00020\u00022\b\b\u0001\u0010\u0003\u001a\u00020\u00022\b\b\u0001\u0010\r\u001a\u000200H§@¢\u0006\u0004\b1\u00102¨\u00063À\u0006\u0003"}, d2 = {"Lcom/arflix/tv/data/api/SimklApi;", "", "", "clientId", "Lcom/arflix/tv/data/api/SimklPinResponse;", "getPinCode", "(Ljava/lang/String;Ld7/d;)Ljava/lang/Object;", "code", "Lcom/arflix/tv/data/api/SimklPinPollResponse;", "pollPinToken", "(Ljava/lang/String;Ljava/lang/String;Ld7/d;)Ljava/lang/Object;", "auth", "Lcom/arflix/tv/data/api/SimklScrobbleBody;", TtmlNode.TAG_BODY, "Lretrofit2/r0;", "Lcom/arflix/tv/data/api/SimklScrobbleResponse;", "scrobbleStart", "(Ljava/lang/String;Ljava/lang/String;Lcom/arflix/tv/data/api/SimklScrobbleBody;Ld7/d;)Ljava/lang/Object;", "scrobblePause", "scrobbleStop", "Lcom/arflix/tv/data/api/SimklUserSettingsResponse;", "getUserSettings", "Lcom/arflix/tv/data/api/SimklActivitiesResponse;", "getActivities", LinkHeader.Parameters.Type, "status", "dateFrom", "extended", "episodeWatchedAt", "includeAllEpisodes", "nextWatchInfo", "Lcom/google/gson/JsonElement;", "getAllItems", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ld7/d;)Ljava/lang/Object;", "", "Lcom/arflix/tv/data/api/SimklPlaybackItem;", "getPlayback", "", TtmlNode.ATTR_ID, "Lgb/s0;", "deletePlayback", "(Ljava/lang/String;Ljava/lang/String;JLd7/d;)Ljava/lang/Object;", "Lcom/arflix/tv/data/api/SimklSyncHistoryBody;", "allowRewatch", "addToHistory", "(Ljava/lang/String;Ljava/lang/String;Lcom/arflix/tv/data/api/SimklSyncHistoryBody;Ljava/lang/String;Ld7/d;)Ljava/lang/Object;", "removeFromHistory", "(Ljava/lang/String;Ljava/lang/String;Lcom/arflix/tv/data/api/SimklSyncHistoryBody;Ld7/d;)Ljava/lang/Object;", "Lcom/arflix/tv/data/api/SimklAddToListBody;", "addToList", "(Ljava/lang/String;Ljava/lang/String;Lcom/arflix/tv/data/api/SimklAddToListBody;Ld7/d;)Ljava/lang/Object;", "app_playRelease"}, k = 1, mv = {2, 3, 0}, xi = 48)
public interface SimklApi {

    @Metadata(k = 3, mv = {2, 3, 0}, xi = 48)
    public static final class DefaultImpls {
    }

    @o("sync/history")
    Object addToHistory(@i("Authorization") String str, @i("simkl-api-key") String str2, @zc.a SimklSyncHistoryBody simklSyncHistoryBody, @t("allow_rewatch") String str3, d7.d<? super r0> dVar);

    @o("sync/add-to-list")
    Object addToList(@i("Authorization") String str, @i("simkl-api-key") String str2, @zc.a SimklAddToListBody simklAddToListBody, d7.d<? super r0> dVar);

    @zc.h(hasBody = false, method = "DELETE", path = "sync/playback/{id}")
    Object deletePlayback(@i("Authorization") String str, @i("simkl-api-key") String str2, @s(TtmlNode.ATTR_ID) long j10, d7.d<? super r0> dVar);

    @zc.f("sync/activities")
    Object getActivities(@i("Authorization") String str, @i("simkl-api-key") String str2, d7.d<? super SimklActivitiesResponse> dVar);

    @zc.f("sync/all-items/{type}/{status}")
    Object getAllItems(@i("Authorization") String str, @i("simkl-api-key") String str2, @s(LinkHeader.Parameters.Type) String str3, @s("status") String str4, @t("date_from") String str5, @t("extended") String str6, @t("episode_watched_at") String str7, @t("include_all_episodes") String str8, @t("next_watch_info") String str9, d7.d<? super JsonElement> dVar);

    @zc.f("oauth/pin")
    Object getPinCode(@t("client_id") String str, d7.d<? super SimklPinResponse> dVar);

    @zc.f("sync/playback")
    Object getPlayback(@i("Authorization") String str, @i("simkl-api-key") String str2, d7.d<? super List<SimklPlaybackItem>> dVar);

    @o("users/settings")
    Object getUserSettings(@i("Authorization") String str, @i("simkl-api-key") String str2, d7.d<? super SimklUserSettingsResponse> dVar);

    @zc.f("oauth/pin/{code}")
    Object pollPinToken(@s("code") String str, @t("client_id") String str2, d7.d<? super SimklPinPollResponse> dVar);

    @o("sync/history/remove")
    Object removeFromHistory(@i("Authorization") String str, @i("simkl-api-key") String str2, @zc.a SimklSyncHistoryBody simklSyncHistoryBody, d7.d<? super r0> dVar);

    @o("scrobble/pause")
    Object scrobblePause(@i("Authorization") String str, @i("simkl-api-key") String str2, @zc.a SimklScrobbleBody simklScrobbleBody, d7.d<? super r0> dVar);

    @o("scrobble/start")
    Object scrobbleStart(@i("Authorization") String str, @i("simkl-api-key") String str2, @zc.a SimklScrobbleBody simklScrobbleBody, d7.d<? super r0> dVar);

    @o("scrobble/stop")
    Object scrobbleStop(@i("Authorization") String str, @i("simkl-api-key") String str2, @zc.a SimklScrobbleBody simklScrobbleBody, d7.d<? super r0> dVar);
}
