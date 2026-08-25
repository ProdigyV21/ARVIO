package com.arflix.tv.ui.screens.watchlist;

import androidx.fragment.app.a2;
import com.arflix.tv.data.model.CatalogConfig;
import com.arflix.tv.data.model.CatalogKind;
import com.arflix.tv.data.model.CatalogSourceType;
import com.arflix.tv.data.model.MediaItem;
import com.arflix.tv.data.repository.HomeServerCatalogCandidate;
import com.arflix.tv.ui.screens.watchlist.WatchlistSourceItem;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.s;
import kotlin.collections.x;
import kotlin.collections.z;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u00002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u001a\u0010\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0003H\u0000\u001a\u0018\u0010\u0004\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0005\u001a\u00020\u0006H\u0000\u001a:\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\t0\b2\f\u0010\n\u001a\b\u0012\u0004\u0012\u00020\u000b0\b2\f\u0010\f\u001a\b\u0012\u0004\u0012\u00020\r0\b2\u000e\b\u0002\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\u000f0\bH\u0000¨\u0006\u0010"}, d2 = {"watchlistLogoKey", "", "item", "Lcom/arflix/tv/data/model/MediaItem;", "watchlistItemKey", "index", "", "buildWatchlistSources", "", "Lcom/arflix/tv/ui/screens/watchlist/WatchlistSourceItem;", "catalogs", "Lcom/arflix/tv/data/model/CatalogConfig;", "homeServerCandidates", "Lcom/arflix/tv/data/repository/HomeServerCatalogCandidate;", "trackerLists", "Lcom/arflix/tv/ui/screens/watchlist/WatchlistSourceItem$TrackerList;", "app_playRelease"}, k = 2, mv = {2, 3, 0}, xi = 48)
public final class WatchlistViewModelKt {
    public static final List<WatchlistSourceItem> buildWatchlistSources(List<CatalogConfig> list, List<HomeServerCatalogCandidate> list2, List<WatchlistSourceItem.TrackerList> list3) {
        ArrayList arrayList = new ArrayList();
        for (Object obj : list) {
            CatalogConfig catalogConfig = (CatalogConfig) obj;
            if (!catalogConfig.isPreinstalled() && catalogConfig.getKind() != CatalogKind.COLLECTION && catalogConfig.getKind() != CatalogKind.COLLECTION_RAIL && catalogConfig.getSourceType() != CatalogSourceType.PREINSTALLED && catalogConfig.getSourceType() != CatalogSourceType.HOME_SERVER) {
                arrayList.add(obj);
            }
        }
        ArrayList arrayList2 = new ArrayList(s.U(arrayList, 10));
        Iterator it = arrayList.iterator();
        while (it.hasNext()) {
            arrayList2.add(new WatchlistSourceItem.Catalog((CatalogConfig) it.next()));
        }
        ArrayList arrayList3 = new ArrayList(s.U(list2, 10));
        Iterator<T> it2 = list2.iterator();
        while (it2.hasNext()) {
            arrayList3.add(new WatchlistSourceItem.HomeServer((HomeServerCatalogCandidate) it2.next()));
        }
        ArrayList arrayListI0 = x.I0(x.I0(x.I0(Collections.singletonList(WatchlistSourceItem.MyWatchlist.INSTANCE), arrayList3), list3), arrayList2);
        HashSet hashSet = new HashSet();
        ArrayList arrayList4 = new ArrayList();
        for (Object obj2 : arrayListI0) {
            if (hashSet.add(((WatchlistSourceItem) obj2).getId())) {
                arrayList4.add(obj2);
            }
        }
        return arrayList4;
    }

    public static /* synthetic */ List buildWatchlistSources$default(List list, List list2, List list3, int i10, Object obj) {
        if ((i10 & 4) != 0) {
            list3 = z.f19728i;
        }
        return buildWatchlistSources(list, list2, list3);
    }

    public static final String watchlistItemKey(MediaItem mediaItem, int i10) {
        String strN;
        String homeServerItemId;
        if (mediaItem.isHomeServer() || !((homeServerItemId = mediaItem.getHomeServerItemId()) == null || kotlin.text.o.h0(homeServerItemId))) {
            String homeServerSourceRef = mediaItem.getHomeServerSourceRef();
            if (homeServerSourceRef == null) {
                homeServerSourceRef = "";
            }
            String homeServerItemId2 = mediaItem.getHomeServerItemId();
            strN = androidx.compose.material3.d.n("home:", homeServerSourceRef, ":", homeServerItemId2 != null ? homeServerItemId2 : "");
        } else {
            strN = "media:" + mediaItem.getMediaType().name() + ":" + mediaItem.getId();
        }
        return a2.j(i10, strN, ":");
    }

    public static final String watchlistLogoKey(MediaItem mediaItem) {
        if (mediaItem.getId() > 0) {
            return "tmdb:" + mediaItem.getMediaType().name() + ":" + mediaItem.getId();
        }
        String homeServerSourceRef = mediaItem.getHomeServerSourceRef();
        if (homeServerSourceRef == null) {
            homeServerSourceRef = "";
        }
        String homeServerItemId = mediaItem.getHomeServerItemId();
        String str = homeServerItemId != null ? homeServerItemId : "";
        String strName = mediaItem.getMediaType().name();
        StringBuilder sbR = a2.r("home:", homeServerSourceRef, ":", str, ":");
        sbR.append(strName);
        return sbR.toString();
    }
}
