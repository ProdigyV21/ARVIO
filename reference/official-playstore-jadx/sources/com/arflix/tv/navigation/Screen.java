package com.arflix.tv.navigation;

import android.net.Uri;
import androidx.compose.foundation.c;
import androidx.compose.material3.d;
import androidx.credentials.exceptions.publickeycredential.DomExceptionUtils;
import com.arflix.tv.data.model.MediaType;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Locale;
import kotlin.Metadata;
import kotlin.collections.x;
import kotlin.jvm.internal.h;

/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u0000@\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u000f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b7\u0018\u00002\u00020\u0001:\u000b\b\t\n\u000b\f\r\u000e\u000f\u0010\u0011\u0012B\u0011\b\u0004\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007\u0082\u0001\u000b\u0013\u0014\u0015\u0016\u0017\u0018\u0019\u001a\u001b\u001c\u001d¨\u0006\u001e"}, d2 = {"Lcom/arflix/tv/navigation/Screen;", "", "route", "", "<init>", "(Ljava/lang/String;)V", "getRoute", "()Ljava/lang/String;", "Login", "Home", "Search", "Watchlist", "CollectionDetails", "Tv", "Settings", "TelegramSettings", "ProfileSelection", "Details", "Player", "Lcom/arflix/tv/navigation/Screen$CollectionDetails;", "Lcom/arflix/tv/navigation/Screen$Details;", "Lcom/arflix/tv/navigation/Screen$Home;", "Lcom/arflix/tv/navigation/Screen$Login;", "Lcom/arflix/tv/navigation/Screen$Player;", "Lcom/arflix/tv/navigation/Screen$ProfileSelection;", "Lcom/arflix/tv/navigation/Screen$Search;", "Lcom/arflix/tv/navigation/Screen$Settings;", "Lcom/arflix/tv/navigation/Screen$TelegramSettings;", "Lcom/arflix/tv/navigation/Screen$Tv;", "Lcom/arflix/tv/navigation/Screen$Watchlist;", "app_playRelease"}, k = 1, mv = {2, 3, 0}, xi = 48)
public abstract class Screen {
    public static final int $stable = 0;
    private final String route;

    @Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\bÇ\n\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u000e\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0005J\u0014\u0010\u0007\u001a\u00020\b2\b\u0010\t\u001a\u0004\u0018\u00010\nHÖ\u0083\u0004J\n\u0010\u000b\u001a\u00020\fHÖ\u0081\u0004J\n\u0010\r\u001a\u00020\u0005HÖ\u0081\u0004¨\u0006\u000e"}, d2 = {"Lcom/arflix/tv/navigation/Screen$CollectionDetails;", "Lcom/arflix/tv/navigation/Screen;", "<init>", "()V", "createRoute", "", "catalogId", "equals", "", "other", "", "hashCode", "", "toString", "app_playRelease"}, k = 1, mv = {2, 3, 0}, xi = 48)
    public static final /* data */ class CollectionDetails extends Screen {
        public static final int $stable = 0;
        public static final CollectionDetails INSTANCE = new CollectionDetails();

        private CollectionDetails() {
            super("collections/{catalogId}", null);
        }

        public final String createRoute(String catalogId) {
            return d.C("collections/", Uri.encode(catalogId));
        }

        public boolean equals(Object other) {
            return this == other || (other instanceof CollectionDetails);
        }

        public int hashCode() {
            return 1770688267;
        }

        public String toString() {
            return "CollectionDetails";
        }
    }

    @Metadata(d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0003\bÇ\n\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J3\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\t2\n\b\u0002\u0010\n\u001a\u0004\u0018\u00010\t2\n\b\u0002\u0010\u000b\u001a\u0004\u0018\u00010\t¢\u0006\u0002\u0010\fJ\u0014\u0010\r\u001a\u00020\u000e2\b\u0010\u000f\u001a\u0004\u0018\u00010\u0010HÖ\u0083\u0004J\n\u0010\u0011\u001a\u00020\tHÖ\u0081\u0004J\n\u0010\u0012\u001a\u00020\u0005HÖ\u0081\u0004¨\u0006\u0013"}, d2 = {"Lcom/arflix/tv/navigation/Screen$Details;", "Lcom/arflix/tv/navigation/Screen;", "<init>", "()V", "createRoute", "", "mediaType", "Lcom/arflix/tv/data/model/MediaType;", "mediaId", "", "initialSeason", "initialEpisode", "(Lcom/arflix/tv/data/model/MediaType;ILjava/lang/Integer;Ljava/lang/Integer;)Ljava/lang/String;", "equals", "", "other", "", "hashCode", "toString", "app_playRelease"}, k = 1, mv = {2, 3, 0}, xi = 48)
    public static final /* data */ class Details extends Screen {
        public static final int $stable = 0;
        public static final Details INSTANCE = new Details();

        private Details() {
            super("details/{mediaType}/{mediaId}?initialSeason={initialSeason}&initialEpisode={initialEpisode}", null);
        }

        public static /* synthetic */ String createRoute$default(Details details, MediaType mediaType, int i10, Integer num, Integer num2, int i11, Object obj) {
            if ((i11 & 4) != 0) {
                num = null;
            }
            if ((i11 & 8) != 0) {
                num2 = null;
            }
            return details.createRoute(mediaType, i10, num, num2);
        }

        public final String createRoute(MediaType mediaType, int mediaId, Integer initialSeason, Integer initialEpisode) {
            String str = "details/" + mediaType.name().toLowerCase(Locale.ROOT) + DomExceptionUtils.SEPARATOR + mediaId;
            ArrayList arrayList = new ArrayList();
            if (initialSeason != null) {
                arrayList.add("initialSeason=" + initialSeason.intValue());
            }
            if (initialEpisode != null) {
                arrayList.add("initialEpisode=" + initialEpisode.intValue());
            }
            return !arrayList.isEmpty() ? c.t(str, "?", x.u0(arrayList, "&", null, null, null, 62)) : str;
        }

        public boolean equals(Object other) {
            return this == other || (other instanceof Details);
        }

        public int hashCode() {
            return -1475719095;
        }

        public String toString() {
            return "Details";
        }
    }

    @Metadata(d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\bÇ\n\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0014\u0010\u0004\u001a\u00020\u00052\b\u0010\u0006\u001a\u0004\u0018\u00010\u0007HÖ\u0083\u0004J\n\u0010\b\u001a\u00020\tHÖ\u0081\u0004J\n\u0010\n\u001a\u00020\u000bHÖ\u0081\u0004¨\u0006\f"}, d2 = {"Lcom/arflix/tv/navigation/Screen$Home;", "Lcom/arflix/tv/navigation/Screen;", "<init>", "()V", "equals", "", "other", "", "hashCode", "", "toString", "", "app_playRelease"}, k = 1, mv = {2, 3, 0}, xi = 48)
    public static final /* data */ class Home extends Screen {
        public static final int $stable = 0;
        public static final Home INSTANCE = new Home();

        private Home() {
            super("home", null);
        }

        public boolean equals(Object other) {
            return this == other || (other instanceof Home);
        }

        public int hashCode() {
            return -1090999240;
        }

        public String toString() {
            return "Home";
        }
    }

    @Metadata(d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\bÇ\n\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0014\u0010\u0004\u001a\u00020\u00052\b\u0010\u0006\u001a\u0004\u0018\u00010\u0007HÖ\u0083\u0004J\n\u0010\b\u001a\u00020\tHÖ\u0081\u0004J\n\u0010\n\u001a\u00020\u000bHÖ\u0081\u0004¨\u0006\f"}, d2 = {"Lcom/arflix/tv/navigation/Screen$Login;", "Lcom/arflix/tv/navigation/Screen;", "<init>", "()V", "equals", "", "other", "", "hashCode", "", "toString", "", "app_playRelease"}, k = 1, mv = {2, 3, 0}, xi = 48)
    public static final /* data */ class Login extends Screen {
        public static final int $stable = 0;
        public static final Login INSTANCE = new Login();

        private Login() {
            super("login", null);
        }

        public boolean equals(Object other) {
            return this == other || (other instanceof Login);
        }

        public int hashCode() {
            return 542450480;
        }

        public String toString() {
            return "Login";
        }
    }

    @Metadata(d1 = {"\u00006\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\f\n\u0002\u0010\t\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\u0000\n\u0002\b\u0003\bÇ\n\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003Jµ\u0001\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\t2\n\b\u0002\u0010\n\u001a\u0004\u0018\u00010\t2\n\b\u0002\u0010\u000b\u001a\u0004\u0018\u00010\t2\n\b\u0002\u0010\f\u001a\u0004\u0018\u00010\t2\n\b\u0002\u0010\r\u001a\u0004\u0018\u00010\t2\n\b\u0002\u0010\u000e\u001a\u0004\u0018\u00010\t2\n\b\u0002\u0010\u000f\u001a\u0004\u0018\u00010\t2\n\b\u0002\u0010\u0010\u001a\u0004\u0018\u00010\u00052\n\b\u0002\u0010\u0011\u001a\u0004\u0018\u00010\u00052\n\b\u0002\u0010\u0012\u001a\u0004\u0018\u00010\u00052\n\b\u0002\u0010\u0013\u001a\u0004\u0018\u00010\u00052\n\b\u0002\u0010\u0014\u001a\u0004\u0018\u00010\u00052\n\b\u0002\u0010\u0015\u001a\u0004\u0018\u00010\u00162\b\b\u0002\u0010\u0017\u001a\u00020\u0018¢\u0006\u0002\u0010\u0019J\u0014\u0010\u001a\u001a\u00020\u00182\b\u0010\u001b\u001a\u0004\u0018\u00010\u001cHÖ\u0083\u0004J\n\u0010\u001d\u001a\u00020\tHÖ\u0081\u0004J\n\u0010\u001e\u001a\u00020\u0005HÖ\u0081\u0004¨\u0006\u001f"}, d2 = {"Lcom/arflix/tv/navigation/Screen$Player;", "Lcom/arflix/tv/navigation/Screen;", "<init>", "()V", "createRoute", "", "mediaType", "Lcom/arflix/tv/data/model/MediaType;", "mediaId", "", "seasonNumber", "episodeNumber", "tmdbSeasonNumber", "tmdbEpisodeNumber", "kitsuId", "kitsuEpisodeNumber", "imdbId", "streamUrl", "preferredAddonId", "preferredSourceName", "preferredBingeGroup", "startPositionMs", "", "isLiveStream", "", "(Lcom/arflix/tv/data/model/MediaType;ILjava/lang/Integer;Ljava/lang/Integer;Ljava/lang/Integer;Ljava/lang/Integer;Ljava/lang/Integer;Ljava/lang/Integer;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/Long;Z)Ljava/lang/String;", "equals", "other", "", "hashCode", "toString", "app_playRelease"}, k = 1, mv = {2, 3, 0}, xi = 48)
    public static final /* data */ class Player extends Screen {
        public static final int $stable = 0;
        public static final Player INSTANCE = new Player();

        private Player() {
            super("player/{mediaType}/{mediaId}?seasonNumber={seasonNumber}&episodeNumber={episodeNumber}&tmdbSeasonNumber={tmdbSeasonNumber}&tmdbEpisodeNumber={tmdbEpisodeNumber}&kitsuId={kitsuId}&kitsuEpisodeNumber={kitsuEpisodeNumber}&imdbId={imdbId}&streamUrl={streamUrl}&preferredAddonId={preferredAddonId}&preferredSourceName={preferredSourceName}&preferredBingeGroup={preferredBingeGroup}&startPositionMs={startPositionMs}&isLiveStream={isLiveStream}", null);
        }

        public static /* synthetic */ String createRoute$default(Player player, MediaType mediaType, int i10, Integer num, Integer num2, Integer num3, Integer num4, Integer num5, Integer num6, String str, String str2, String str3, String str4, String str5, Long l10, boolean z, int i11, Object obj) {
            Integer num7 = (i11 & 4) != 0 ? null : num;
            Integer num8 = (i11 & 8) != 0 ? null : num2;
            return player.createRoute(mediaType, i10, num7, num8, (i11 & 16) != 0 ? num7 : num3, (i11 & 32) != 0 ? num8 : num4, (i11 & 64) != 0 ? null : num5, (i11 & 128) != 0 ? null : num6, (i11 & 256) != 0 ? null : str, (i11 & 512) != 0 ? null : str2, (i11 & 1024) != 0 ? null : str3, (i11 & 2048) != 0 ? null : str4, (i11 & 4096) != 0 ? null : str5, (i11 & 8192) != 0 ? null : l10, (i11 & 16384) != 0 ? false : z);
        }

        public final String createRoute(MediaType mediaType, int mediaId, Integer seasonNumber, Integer episodeNumber, Integer tmdbSeasonNumber, Integer tmdbEpisodeNumber, Integer kitsuId, Integer kitsuEpisodeNumber, String imdbId, String streamUrl, String preferredAddonId, String preferredSourceName, String preferredBingeGroup, Long startPositionMs, boolean isLiveStream) throws UnsupportedEncodingException {
            String str = "player/" + mediaType.name().toLowerCase(Locale.ROOT) + DomExceptionUtils.SEPARATOR + mediaId;
            ArrayList arrayList = new ArrayList();
            if (seasonNumber != null) {
                arrayList.add("seasonNumber=" + seasonNumber.intValue());
            }
            if (episodeNumber != null) {
                arrayList.add("episodeNumber=" + episodeNumber.intValue());
            }
            if (tmdbSeasonNumber != null) {
                arrayList.add("tmdbSeasonNumber=" + tmdbSeasonNumber.intValue());
            }
            if (tmdbEpisodeNumber != null) {
                arrayList.add("tmdbEpisodeNumber=" + tmdbEpisodeNumber.intValue());
            }
            if (kitsuId != null) {
                arrayList.add("kitsuId=" + kitsuId.intValue());
            }
            if (kitsuEpisodeNumber != null) {
                arrayList.add("kitsuEpisodeNumber=" + kitsuEpisodeNumber.intValue());
            }
            if (imdbId != null) {
                arrayList.add("imdbId=" + URLEncoder.encode(imdbId, "UTF-8"));
            }
            if (streamUrl != null) {
                arrayList.add("streamUrl=" + URLEncoder.encode(streamUrl, "UTF-8"));
            }
            if (preferredAddonId != null) {
                arrayList.add("preferredAddonId=" + URLEncoder.encode(preferredAddonId, "UTF-8"));
            }
            if (preferredSourceName != null) {
                arrayList.add("preferredSourceName=" + URLEncoder.encode(preferredSourceName, "UTF-8"));
            }
            if (preferredBingeGroup != null) {
                arrayList.add("preferredBingeGroup=" + URLEncoder.encode(preferredBingeGroup, "UTF-8"));
            }
            if (startPositionMs != null) {
                arrayList.add("startPositionMs=" + startPositionMs.longValue());
            }
            if (isLiveStream) {
                arrayList.add("isLiveStream=true");
            }
            return !arrayList.isEmpty() ? c.t(str, "?", x.u0(arrayList, "&", null, null, null, 62)) : str;
        }

        public boolean equals(Object other) {
            return this == other || (other instanceof Player);
        }

        public int hashCode() {
            return -252321798;
        }

        public String toString() {
            return "Player";
        }
    }

    @Metadata(d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\bÇ\n\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0014\u0010\u0004\u001a\u00020\u00052\b\u0010\u0006\u001a\u0004\u0018\u00010\u0007HÖ\u0083\u0004J\n\u0010\b\u001a\u00020\tHÖ\u0081\u0004J\n\u0010\n\u001a\u00020\u000bHÖ\u0081\u0004¨\u0006\f"}, d2 = {"Lcom/arflix/tv/navigation/Screen$ProfileSelection;", "Lcom/arflix/tv/navigation/Screen;", "<init>", "()V", "equals", "", "other", "", "hashCode", "", "toString", "", "app_playRelease"}, k = 1, mv = {2, 3, 0}, xi = 48)
    public static final /* data */ class ProfileSelection extends Screen {
        public static final int $stable = 0;
        public static final ProfileSelection INSTANCE = new ProfileSelection();

        private ProfileSelection() {
            super("profile_selection", null);
        }

        public boolean equals(Object other) {
            return this == other || (other instanceof ProfileSelection);
        }

        public int hashCode() {
            return 1994149820;
        }

        public String toString() {
            return "ProfileSelection";
        }
    }

    @Metadata(d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\bÇ\n\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0014\u0010\u0004\u001a\u00020\u00052\b\u0010\u0006\u001a\u0004\u0018\u00010\u0007HÖ\u0083\u0004J\n\u0010\b\u001a\u00020\tHÖ\u0081\u0004J\n\u0010\n\u001a\u00020\u000bHÖ\u0081\u0004¨\u0006\f"}, d2 = {"Lcom/arflix/tv/navigation/Screen$Search;", "Lcom/arflix/tv/navigation/Screen;", "<init>", "()V", "equals", "", "other", "", "hashCode", "", "toString", "", "app_playRelease"}, k = 1, mv = {2, 3, 0}, xi = 48)
    public static final /* data */ class Search extends Screen {
        public static final int $stable = 0;
        public static final Search INSTANCE = new Search();

        private Search() {
            super("search", null);
        }

        public boolean equals(Object other) {
            return this == other || (other instanceof Search);
        }

        public int hashCode() {
            return -172905791;
        }

        public String toString() {
            return "Search";
        }
    }

    @Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\bÇ\n\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u001c\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00072\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\u0005J\u0014\u0010\t\u001a\u00020\u00072\b\u0010\n\u001a\u0004\u0018\u00010\u000bHÖ\u0083\u0004J\n\u0010\f\u001a\u00020\rHÖ\u0081\u0004J\n\u0010\u000e\u001a\u00020\u0005HÖ\u0081\u0004¨\u0006\u000f"}, d2 = {"Lcom/arflix/tv/navigation/Screen$Settings;", "Lcom/arflix/tv/navigation/Screen;", "<init>", "()V", "createRoute", "", "autoCloudAuth", "", "initialSection", "equals", "other", "", "hashCode", "", "toString", "app_playRelease"}, k = 1, mv = {2, 3, 0}, xi = 48)
    public static final /* data */ class Settings extends Screen {
        public static final int $stable = 0;
        public static final Settings INSTANCE = new Settings();

        private Settings() {
            super("settings", null);
        }

        public static /* synthetic */ String createRoute$default(Settings settings, boolean z, String str, int i10, Object obj) {
            if ((i10 & 1) != 0) {
                z = false;
            }
            if ((i10 & 2) != 0) {
                str = null;
            }
            return settings.createRoute(z, str);
        }

        public final String createRoute(boolean autoCloudAuth, String initialSection) {
            ArrayList arrayList = new ArrayList();
            if (autoCloudAuth) {
                arrayList.add("autoCloudAuth=true");
            }
            if (initialSection != null) {
                arrayList.add("initialSection=".concat(initialSection));
            }
            return !arrayList.isEmpty() ? d.C("settings?", x.u0(arrayList, "&", null, null, null, 62)) : "settings";
        }

        public boolean equals(Object other) {
            return this == other || (other instanceof Settings);
        }

        public int hashCode() {
            return 1887248124;
        }

        public String toString() {
            return "Settings";
        }
    }

    @Metadata(d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\bÇ\n\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0014\u0010\u0004\u001a\u00020\u00052\b\u0010\u0006\u001a\u0004\u0018\u00010\u0007HÖ\u0083\u0004J\n\u0010\b\u001a\u00020\tHÖ\u0081\u0004J\n\u0010\n\u001a\u00020\u000bHÖ\u0081\u0004¨\u0006\f"}, d2 = {"Lcom/arflix/tv/navigation/Screen$TelegramSettings;", "Lcom/arflix/tv/navigation/Screen;", "<init>", "()V", "equals", "", "other", "", "hashCode", "", "toString", "", "app_playRelease"}, k = 1, mv = {2, 3, 0}, xi = 48)
    public static final /* data */ class TelegramSettings extends Screen {
        public static final int $stable = 0;
        public static final TelegramSettings INSTANCE = new TelegramSettings();

        private TelegramSettings() {
            super("telegram_settings", null);
        }

        public boolean equals(Object other) {
            return this == other || (other instanceof TelegramSettings);
        }

        public int hashCode() {
            return -552865251;
        }

        public String toString() {
            return "TelegramSettings";
        }
    }

    @Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\bÇ\n\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u001e\u0010\u0004\u001a\u00020\u00052\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u00052\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\u0005J\u0014\u0010\b\u001a\u00020\t2\b\u0010\n\u001a\u0004\u0018\u00010\u000bHÖ\u0083\u0004J\n\u0010\f\u001a\u00020\rHÖ\u0081\u0004J\n\u0010\u000e\u001a\u00020\u0005HÖ\u0081\u0004¨\u0006\u000f"}, d2 = {"Lcom/arflix/tv/navigation/Screen$Tv;", "Lcom/arflix/tv/navigation/Screen;", "<init>", "()V", "createRoute", "", "channelId", "streamUrl", "equals", "", "other", "", "hashCode", "", "toString", "app_playRelease"}, k = 1, mv = {2, 3, 0}, xi = 48)
    public static final /* data */ class Tv extends Screen {
        public static final int $stable = 0;
        public static final Tv INSTANCE = new Tv();

        private Tv() {
            super("tv?channelId={channelId}&streamUrl={streamUrl}", null);
        }

        public static /* synthetic */ String createRoute$default(Tv tv, String str, String str2, int i10, Object obj) {
            if ((i10 & 1) != 0) {
                str = null;
            }
            if ((i10 & 2) != 0) {
                str2 = null;
            }
            return tv.createRoute(str, str2);
        }

        public final String createRoute(String channelId, String streamUrl) throws UnsupportedEncodingException {
            if (channelId == null) {
                return "tv";
            }
            String strEncode = URLEncoder.encode(channelId, "UTF-8");
            String strEncode2 = streamUrl != null ? URLEncoder.encode(streamUrl, "UTF-8") : null;
            return strEncode2 != null ? d.n("tv?channelId=", strEncode, "&streamUrl=", strEncode2) : d.C("tv?channelId=", strEncode);
        }

        public boolean equals(Object other) {
            return this == other || (other instanceof Tv);
        }

        public int hashCode() {
            return 1942997019;
        }

        public String toString() {
            return "Tv";
        }
    }

    @Metadata(d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\bÇ\n\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0014\u0010\u0004\u001a\u00020\u00052\b\u0010\u0006\u001a\u0004\u0018\u00010\u0007HÖ\u0083\u0004J\n\u0010\b\u001a\u00020\tHÖ\u0081\u0004J\n\u0010\n\u001a\u00020\u000bHÖ\u0081\u0004¨\u0006\f"}, d2 = {"Lcom/arflix/tv/navigation/Screen$Watchlist;", "Lcom/arflix/tv/navigation/Screen;", "<init>", "()V", "equals", "", "other", "", "hashCode", "", "toString", "", "app_playRelease"}, k = 1, mv = {2, 3, 0}, xi = 48)
    public static final /* data */ class Watchlist extends Screen {
        public static final int $stable = 0;
        public static final Watchlist INSTANCE = new Watchlist();

        private Watchlist() {
            super("watchlist", null);
        }

        public boolean equals(Object other) {
            return this == other || (other instanceof Watchlist);
        }

        public int hashCode() {
            return 866283060;
        }

        public String toString() {
            return "Watchlist";
        }
    }

    public /* synthetic */ Screen(String str, h hVar) {
        this(str);
    }

    public final String getRoute() {
        return this.route;
    }

    private Screen(String str) {
        this.route = str;
    }
}
