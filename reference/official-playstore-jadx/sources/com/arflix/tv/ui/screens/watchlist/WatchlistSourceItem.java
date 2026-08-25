package com.arflix.tv.ui.screens.watchlist;

import androidx.media3.extractor.text.ttml.TtmlNode;
import com.arflix.tv.data.model.CatalogConfig;
import com.arflix.tv.data.model.CatalogSourceType;
import com.arflix.tv.data.repository.HomeServerCatalogCandidate;
import com.google.android.gms.cast.MediaTrack;
import io.ktor.http.LinkHeader;
import java.util.Locale;
import kotlin.Metadata;
import kotlin.jvm.internal.p;
import kotlin.text.u;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\bv\u0018\u00002\u00020\u0001:\u0004\f\r\u000e\u000fR\u0012\u0010\u0002\u001a\u00020\u0003X¦\u0004¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005R\u0012\u0010\u0006\u001a\u00020\u0003X¦\u0004¢\u0006\u0006\u001a\u0004\b\u0007\u0010\u0005R\u0014\u0010\b\u001a\u0004\u0018\u00010\u0003X¦\u0004¢\u0006\u0006\u001a\u0004\b\t\u0010\u0005R\u0012\u0010\n\u001a\u00020\u0003X¦\u0004¢\u0006\u0006\u001a\u0004\b\u000b\u0010\u0005\u0082\u0001\u0004\u0010\u0011\u0012\u0013¨\u0006\u0014À\u0006\u0003"}, d2 = {"Lcom/arflix/tv/ui/screens/watchlist/WatchlistSourceItem;", "", TtmlNode.ATTR_ID, "", "getId", "()Ljava/lang/String;", LinkHeader.Parameters.Title, "getTitle", MediaTrack.ROLE_SUBTITLE, "getSubtitle", "displayLabel", "getDisplayLabel", "MyWatchlist", "Catalog", "HomeServer", "TrackerList", "Lcom/arflix/tv/ui/screens/watchlist/WatchlistSourceItem$Catalog;", "Lcom/arflix/tv/ui/screens/watchlist/WatchlistSourceItem$HomeServer;", "Lcom/arflix/tv/ui/screens/watchlist/WatchlistSourceItem$MyWatchlist;", "Lcom/arflix/tv/ui/screens/watchlist/WatchlistSourceItem$TrackerList;", "app_playRelease"}, k = 1, mv = {2, 3, 0}, xi = 48)
public interface WatchlistSourceItem {

    @Metadata(d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000e\n\u0002\b\u000b\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\b\u0087\b\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\t\u0010\u0012\u001a\u00020\u0003HÆ\u0003J\u0013\u0010\u0013\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u0003HÆ\u0001J\u0014\u0010\u0014\u001a\u00020\u00152\b\u0010\u0016\u001a\u0004\u0018\u00010\u0017HÖ\u0083\u0004J\n\u0010\u0018\u001a\u00020\u0019HÖ\u0081\u0004J\n\u0010\u001a\u001a\u00020\tHÖ\u0081\u0004R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007R\u0014\u0010\b\u001a\u00020\tX\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR\u0014\u0010\f\u001a\u00020\tX\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000bR\u0016\u0010\u000e\u001a\u0004\u0018\u00010\tX\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u000bR\u0014\u0010\u0010\u001a\u00020\tX\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u000b¨\u0006\u001b"}, d2 = {"Lcom/arflix/tv/ui/screens/watchlist/WatchlistSourceItem$Catalog;", "Lcom/arflix/tv/ui/screens/watchlist/WatchlistSourceItem;", "config", "Lcom/arflix/tv/data/model/CatalogConfig;", "<init>", "(Lcom/arflix/tv/data/model/CatalogConfig;)V", "getConfig", "()Lcom/arflix/tv/data/model/CatalogConfig;", TtmlNode.ATTR_ID, "", "getId", "()Ljava/lang/String;", LinkHeader.Parameters.Title, "getTitle", MediaTrack.ROLE_SUBTITLE, "getSubtitle", "displayLabel", "getDisplayLabel", "component1", "copy", "equals", "", "other", "", "hashCode", "", "toString", "app_playRelease"}, k = 1, mv = {2, 3, 0}, xi = 48)
    public static final /* data */ class Catalog implements WatchlistSourceItem {
        public static final int $stable = 0;
        private final CatalogConfig config;
        private final String displayLabel;
        private final String id;
        private final String subtitle;
        private final String title;

        public Catalog(CatalogConfig catalogConfig) {
            String addonName;
            this.config = catalogConfig;
            this.id = androidx.compose.material3.d.C("catalog_", catalogConfig.getId());
            this.title = catalogConfig.getTitle();
            if (catalogConfig.getSourceType() == CatalogSourceType.TRAKT) {
                addonName = "Trakt";
            } else if (catalogConfig.getSourceType() == CatalogSourceType.MDBLIST) {
                addonName = "MDBList";
            } else if (catalogConfig.getSourceType() == CatalogSourceType.ADDON) {
                addonName = catalogConfig.getAddonName();
                if (addonName == null) {
                    addonName = "Addon";
                }
            } else {
                String sourceUrl = catalogConfig.getSourceUrl();
                addonName = (sourceUrl == null || !kotlin.text.o.T(sourceUrl, "simkl", true)) ? null : "Simkl";
            }
            this.subtitle = addonName;
            this.displayLabel = (getSubtitle() == null || u.P(getTitle(), getSubtitle(), true)) ? getTitle() : androidx.compose.foundation.c.t(getSubtitle(), " / ", getTitle());
        }

        public static /* synthetic */ Catalog copy$default(Catalog catalog, CatalogConfig catalogConfig, int i10, Object obj) {
            if ((i10 & 1) != 0) {
                catalogConfig = catalog.config;
            }
            return catalog.copy(catalogConfig);
        }

        /* JADX INFO: renamed from: component1, reason: from getter */
        public final CatalogConfig getConfig() {
            return this.config;
        }

        public final Catalog copy(CatalogConfig config) {
            return new Catalog(config);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            return (other instanceof Catalog) && p.a(this.config, ((Catalog) other).config);
        }

        public final CatalogConfig getConfig() {
            return this.config;
        }

        @Override // com.arflix.tv.ui.screens.watchlist.WatchlistSourceItem
        public String getDisplayLabel() {
            return this.displayLabel;
        }

        @Override // com.arflix.tv.ui.screens.watchlist.WatchlistSourceItem
        public String getId() {
            return this.id;
        }

        @Override // com.arflix.tv.ui.screens.watchlist.WatchlistSourceItem
        public String getSubtitle() {
            return this.subtitle;
        }

        @Override // com.arflix.tv.ui.screens.watchlist.WatchlistSourceItem
        public String getTitle() {
            return this.title;
        }

        public int hashCode() {
            return this.config.hashCode();
        }

        public String toString() {
            return "Catalog(config=" + this.config + ")";
        }
    }

    @Metadata(d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000e\n\u0002\b\u000b\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\b\u0087\b\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\t\u0010\u0012\u001a\u00020\u0003HÆ\u0003J\u0013\u0010\u0013\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u0003HÆ\u0001J\u0014\u0010\u0014\u001a\u00020\u00152\b\u0010\u0016\u001a\u0004\u0018\u00010\u0017HÖ\u0083\u0004J\n\u0010\u0018\u001a\u00020\u0019HÖ\u0081\u0004J\n\u0010\u001a\u001a\u00020\tHÖ\u0081\u0004R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007R\u0014\u0010\b\u001a\u00020\tX\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR\u0014\u0010\f\u001a\u00020\tX\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000bR\u0014\u0010\u000e\u001a\u00020\tX\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u000bR\u0014\u0010\u0010\u001a\u00020\tX\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u000b¨\u0006\u001b"}, d2 = {"Lcom/arflix/tv/ui/screens/watchlist/WatchlistSourceItem$HomeServer;", "Lcom/arflix/tv/ui/screens/watchlist/WatchlistSourceItem;", "candidate", "Lcom/arflix/tv/data/repository/HomeServerCatalogCandidate;", "<init>", "(Lcom/arflix/tv/data/repository/HomeServerCatalogCandidate;)V", "getCandidate", "()Lcom/arflix/tv/data/repository/HomeServerCatalogCandidate;", TtmlNode.ATTR_ID, "", "getId", "()Ljava/lang/String;", LinkHeader.Parameters.Title, "getTitle", MediaTrack.ROLE_SUBTITLE, "getSubtitle", "displayLabel", "getDisplayLabel", "component1", "copy", "equals", "", "other", "", "hashCode", "", "toString", "app_playRelease"}, k = 1, mv = {2, 3, 0}, xi = 48)
    public static final /* data */ class HomeServer implements WatchlistSourceItem {
        public static final int $stable = 0;
        private final HomeServerCatalogCandidate candidate;
        private final String displayLabel;
        private final String id;
        private final String subtitle;
        private final String title;

        public HomeServer(HomeServerCatalogCandidate homeServerCatalogCandidate) {
            this.candidate = homeServerCatalogCandidate;
            this.id = androidx.compose.material3.d.C("server_", homeServerCatalogCandidate.getSourceRef());
            String collectionName = homeServerCatalogCandidate.getCollectionName();
            this.title = kotlin.text.o.h0(collectionName) ? homeServerCatalogCandidate.getTitle() : collectionName;
            this.subtitle = homeServerCatalogCandidate.getServerName();
            this.displayLabel = (kotlin.text.o.h0(getSubtitle()) || u.P(getTitle(), getSubtitle(), true)) ? getTitle() : androidx.compose.foundation.c.t(getSubtitle(), " / ", getTitle());
        }

        public static /* synthetic */ HomeServer copy$default(HomeServer homeServer, HomeServerCatalogCandidate homeServerCatalogCandidate, int i10, Object obj) {
            if ((i10 & 1) != 0) {
                homeServerCatalogCandidate = homeServer.candidate;
            }
            return homeServer.copy(homeServerCatalogCandidate);
        }

        /* JADX INFO: renamed from: component1, reason: from getter */
        public final HomeServerCatalogCandidate getCandidate() {
            return this.candidate;
        }

        public final HomeServer copy(HomeServerCatalogCandidate candidate) {
            return new HomeServer(candidate);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            return (other instanceof HomeServer) && p.a(this.candidate, ((HomeServer) other).candidate);
        }

        public final HomeServerCatalogCandidate getCandidate() {
            return this.candidate;
        }

        @Override // com.arflix.tv.ui.screens.watchlist.WatchlistSourceItem
        public String getDisplayLabel() {
            return this.displayLabel;
        }

        @Override // com.arflix.tv.ui.screens.watchlist.WatchlistSourceItem
        public String getId() {
            return this.id;
        }

        @Override // com.arflix.tv.ui.screens.watchlist.WatchlistSourceItem
        public String getSubtitle() {
            return this.subtitle;
        }

        @Override // com.arflix.tv.ui.screens.watchlist.WatchlistSourceItem
        public String getTitle() {
            return this.title;
        }

        public int hashCode() {
            return this.candidate.hashCode();
        }

        public String toString() {
            return "HomeServer(candidate=" + this.candidate + ")";
        }
    }

    @Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\t\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\bÇ\n\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0014\u0010\u000e\u001a\u00020\u000f2\b\u0010\u0010\u001a\u0004\u0018\u00010\u0011HÖ\u0083\u0004J\n\u0010\u0012\u001a\u00020\u0013HÖ\u0081\u0004J\n\u0010\u0014\u001a\u00020\u0005HÖ\u0081\u0004R\u0014\u0010\u0004\u001a\u00020\u0005X\u0096D¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007R\u0014\u0010\b\u001a\u00020\u0005X\u0096D¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\u0007R\u0016\u0010\n\u001a\u0004\u0018\u00010\u0005X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\u0007R\u0014\u0010\f\u001a\u00020\u0005X\u0096D¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u0007¨\u0006\u0015"}, d2 = {"Lcom/arflix/tv/ui/screens/watchlist/WatchlistSourceItem$MyWatchlist;", "Lcom/arflix/tv/ui/screens/watchlist/WatchlistSourceItem;", "<init>", "()V", TtmlNode.ATTR_ID, "", "getId", "()Ljava/lang/String;", LinkHeader.Parameters.Title, "getTitle", MediaTrack.ROLE_SUBTITLE, "getSubtitle", "displayLabel", "getDisplayLabel", "equals", "", "other", "", "hashCode", "", "toString", "app_playRelease"}, k = 1, mv = {2, 3, 0}, xi = 48)
    public static final /* data */ class MyWatchlist implements WatchlistSourceItem {
        public static final int $stable = 0;
        private static final String subtitle = null;
        public static final MyWatchlist INSTANCE = new MyWatchlist();
        private static final String id = "my_watchlist";
        private static final String title = "My watchlist";
        private static final String displayLabel = "My watchlist";

        private MyWatchlist() {
        }

        public boolean equals(Object other) {
            return this == other || (other instanceof MyWatchlist);
        }

        @Override // com.arflix.tv.ui.screens.watchlist.WatchlistSourceItem
        public String getDisplayLabel() {
            return displayLabel;
        }

        @Override // com.arflix.tv.ui.screens.watchlist.WatchlistSourceItem
        public String getId() {
            return id;
        }

        @Override // com.arflix.tv.ui.screens.watchlist.WatchlistSourceItem
        public String getSubtitle() {
            return subtitle;
        }

        @Override // com.arflix.tv.ui.screens.watchlist.WatchlistSourceItem
        public String getTitle() {
            return title;
        }

        public int hashCode() {
            return 1001236285;
        }

        public String toString() {
            return "MyWatchlist";
        }
    }

    @Metadata(d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0013\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\b\u0087\b\u0018\u00002\u00020\u0001B\u001f\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0005¢\u0006\u0004\b\u0007\u0010\bJ\t\u0010\u0014\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0015\u001a\u00020\u0005HÆ\u0003J\t\u0010\u0016\u001a\u00020\u0005HÆ\u0003J'\u0010\u0017\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u0005HÆ\u0001J\u0014\u0010\u0018\u001a\u00020\u00192\b\u0010\u001a\u001a\u0004\u0018\u00010\u001bHÖ\u0083\u0004J\n\u0010\u001c\u001a\u00020\u001dHÖ\u0081\u0004J\n\u0010\u001e\u001a\u00020\u0005HÖ\u0081\u0004R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\nR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u0014\u0010\u0006\u001a\u00020\u0005X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\fR\u0014\u0010\u000e\u001a\u00020\u0005X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\fR\u0014\u0010\u0010\u001a\u00020\u0005X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\fR\u0014\u0010\u0012\u001a\u00020\u0005X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\f¨\u0006\u001f"}, d2 = {"Lcom/arflix/tv/ui/screens/watchlist/WatchlistSourceItem$TrackerList;", "Lcom/arflix/tv/ui/screens/watchlist/WatchlistSourceItem;", "provider", "Lcom/arflix/tv/ui/screens/watchlist/TrackerLibraryProvider;", "listKey", "", LinkHeader.Parameters.Title, "<init>", "(Lcom/arflix/tv/ui/screens/watchlist/TrackerLibraryProvider;Ljava/lang/String;Ljava/lang/String;)V", "getProvider", "()Lcom/arflix/tv/ui/screens/watchlist/TrackerLibraryProvider;", "getListKey", "()Ljava/lang/String;", "getTitle", TtmlNode.ATTR_ID, "getId", MediaTrack.ROLE_SUBTITLE, "getSubtitle", "displayLabel", "getDisplayLabel", "component1", "component2", "component3", "copy", "equals", "", "other", "", "hashCode", "", "toString", "app_playRelease"}, k = 1, mv = {2, 3, 0}, xi = 48)
    public static final /* data */ class TrackerList implements WatchlistSourceItem {
        public static final int $stable = 0;
        private final String displayLabel = getTitle();
        private final String id;
        private final String listKey;
        private final TrackerLibraryProvider provider;
        private final String subtitle;
        private final String title;

        public TrackerList(TrackerLibraryProvider trackerLibraryProvider, String str, String str2) {
            this.provider = trackerLibraryProvider;
            this.listKey = str;
            this.title = str2;
            this.id = androidx.compose.material3.d.n("tracker_", trackerLibraryProvider.name().toLowerCase(Locale.ROOT), "_", str);
            this.subtitle = trackerLibraryProvider.getDisplayName();
        }

        public static /* synthetic */ TrackerList copy$default(TrackerList trackerList, TrackerLibraryProvider trackerLibraryProvider, String str, String str2, int i10, Object obj) {
            if ((i10 & 1) != 0) {
                trackerLibraryProvider = trackerList.provider;
            }
            if ((i10 & 2) != 0) {
                str = trackerList.listKey;
            }
            if ((i10 & 4) != 0) {
                str2 = trackerList.title;
            }
            return trackerList.copy(trackerLibraryProvider, str, str2);
        }

        /* JADX INFO: renamed from: component1, reason: from getter */
        public final TrackerLibraryProvider getProvider() {
            return this.provider;
        }

        /* JADX INFO: renamed from: component2, reason: from getter */
        public final String getListKey() {
            return this.listKey;
        }

        /* JADX INFO: renamed from: component3, reason: from getter */
        public final String getTitle() {
            return this.title;
        }

        public final TrackerList copy(TrackerLibraryProvider provider, String listKey, String title) {
            return new TrackerList(provider, listKey, title);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof TrackerList)) {
                return false;
            }
            TrackerList trackerList = (TrackerList) other;
            return this.provider == trackerList.provider && p.a(this.listKey, trackerList.listKey) && p.a(this.title, trackerList.title);
        }

        @Override // com.arflix.tv.ui.screens.watchlist.WatchlistSourceItem
        public String getDisplayLabel() {
            return this.displayLabel;
        }

        @Override // com.arflix.tv.ui.screens.watchlist.WatchlistSourceItem
        public String getId() {
            return this.id;
        }

        public final String getListKey() {
            return this.listKey;
        }

        public final TrackerLibraryProvider getProvider() {
            return this.provider;
        }

        @Override // com.arflix.tv.ui.screens.watchlist.WatchlistSourceItem
        public String getSubtitle() {
            return this.subtitle;
        }

        @Override // com.arflix.tv.ui.screens.watchlist.WatchlistSourceItem
        public String getTitle() {
            return this.title;
        }

        public int hashCode() {
            return this.title.hashCode() + androidx.compose.foundation.c.c(this.provider.hashCode() * 31, 31, this.listKey);
        }

        public String toString() {
            TrackerLibraryProvider trackerLibraryProvider = this.provider;
            String str = this.listKey;
            String str2 = this.title;
            StringBuilder sb2 = new StringBuilder("TrackerList(provider=");
            sb2.append(trackerLibraryProvider);
            sb2.append(", listKey=");
            sb2.append(str);
            sb2.append(", title=");
            return a0.c.p(sb2, str2, ")");
        }
    }

    String getDisplayLabel();

    String getId();

    String getSubtitle();

    String getTitle();
}
