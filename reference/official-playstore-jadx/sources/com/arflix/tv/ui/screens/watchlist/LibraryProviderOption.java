package com.arflix.tv.ui.screens.watchlist;

import androidx.fragment.app.a2;
import androidx.media3.extractor.text.ttml.TtmlNode;
import com.arflix.tv.data.repository.HomeServerKind;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.z;
import kotlin.jvm.internal.p;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0010\u000b\n\u0002\b\u000b\n\u0002\u0010\b\n\u0002\b\u0002\b\u0082\b\u0018\u00002\u00020\u0001B3\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u0006\u0012\u000e\b\u0002\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\t0\b¢\u0006\u0004\b\n\u0010\u000bJ\t\u0010\u0018\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0019\u001a\u00020\u0003HÆ\u0003J\u000b\u0010\u001a\u001a\u0004\u0018\u00010\u0006HÆ\u0003J\u000f\u0010\u001b\u001a\b\u0012\u0004\u0012\u00020\t0\bHÆ\u0003J9\u0010\u001c\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u00062\u000e\b\u0002\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\t0\bHÆ\u0001J\u0014\u0010\u001d\u001a\u00020\u00142\b\u0010\u001e\u001a\u0004\u0018\u00010\u0001HÖ\u0083\u0004J\n\u0010\u001f\u001a\u00020 HÖ\u0081\u0004J\n\u0010!\u001a\u00020\u0003HÖ\u0081\u0004R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\rR\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\rR\u0013\u0010\u0005\u001a\u0004\u0018\u00010\u0006¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u0010R\u0017\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\t0\b¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u0012R\u0011\u0010\u0013\u001a\u00020\u00148F¢\u0006\u0006\u001a\u0004\b\u0013\u0010\u0015R\u0011\u0010\u0016\u001a\u00020\u00148F¢\u0006\u0006\u001a\u0004\b\u0016\u0010\u0015R\u0011\u0010\u0017\u001a\u00020\u00148F¢\u0006\u0006\u001a\u0004\b\u0017\u0010\u0015¨\u0006\""}, d2 = {"Lcom/arflix/tv/ui/screens/watchlist/LibraryProviderOption;", "", TtmlNode.ATTR_ID, "", "label", "homeServerKind", "Lcom/arflix/tv/data/repository/HomeServerKind;", "trackerSources", "", "Lcom/arflix/tv/ui/screens/watchlist/WatchlistSourceItem;", "<init>", "(Ljava/lang/String;Ljava/lang/String;Lcom/arflix/tv/data/repository/HomeServerKind;Ljava/util/List;)V", "getId", "()Ljava/lang/String;", "getLabel", "getHomeServerKind", "()Lcom/arflix/tv/data/repository/HomeServerKind;", "getTrackerSources", "()Ljava/util/List;", "isWatchlist", "", "()Z", "isHomeServer", "isTracker", "component1", "component2", "component3", "component4", "copy", "equals", "other", "hashCode", "", "toString", "app_playRelease"}, k = 1, mv = {2, 3, 0}, xi = 48)
final /* data */ class LibraryProviderOption {
    private final HomeServerKind homeServerKind;
    private final String id;
    private final String label;
    private final List<WatchlistSourceItem> trackerSources;

    /* JADX WARN: Multi-variable type inference failed */
    public LibraryProviderOption(String str, String str2, HomeServerKind homeServerKind, List<? extends WatchlistSourceItem> list) {
        this.id = str;
        this.label = str2;
        this.homeServerKind = homeServerKind;
        this.trackerSources = list;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static /* synthetic */ LibraryProviderOption copy$default(LibraryProviderOption libraryProviderOption, String str, String str2, HomeServerKind homeServerKind, List list, int i10, Object obj) {
        if ((i10 & 1) != 0) {
            str = libraryProviderOption.id;
        }
        if ((i10 & 2) != 0) {
            str2 = libraryProviderOption.label;
        }
        if ((i10 & 4) != 0) {
            homeServerKind = libraryProviderOption.homeServerKind;
        }
        if ((i10 & 8) != 0) {
            list = libraryProviderOption.trackerSources;
        }
        return libraryProviderOption.copy(str, str2, homeServerKind, list);
    }

    /* JADX INFO: renamed from: component1, reason: from getter */
    public final String getId() {
        return this.id;
    }

    /* JADX INFO: renamed from: component2, reason: from getter */
    public final String getLabel() {
        return this.label;
    }

    /* JADX INFO: renamed from: component3, reason: from getter */
    public final HomeServerKind getHomeServerKind() {
        return this.homeServerKind;
    }

    public final List<WatchlistSourceItem> component4() {
        return this.trackerSources;
    }

    public final LibraryProviderOption copy(String id, String label, HomeServerKind homeServerKind, List<? extends WatchlistSourceItem> trackerSources) {
        return new LibraryProviderOption(id, label, homeServerKind, trackerSources);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof LibraryProviderOption)) {
            return false;
        }
        LibraryProviderOption libraryProviderOption = (LibraryProviderOption) other;
        return p.a(this.id, libraryProviderOption.id) && p.a(this.label, libraryProviderOption.label) && this.homeServerKind == libraryProviderOption.homeServerKind && p.a(this.trackerSources, libraryProviderOption.trackerSources);
    }

    public final HomeServerKind getHomeServerKind() {
        return this.homeServerKind;
    }

    public final String getId() {
        return this.id;
    }

    public final String getLabel() {
        return this.label;
    }

    public final List<WatchlistSourceItem> getTrackerSources() {
        return this.trackerSources;
    }

    public int hashCode() {
        int iC = androidx.compose.foundation.c.c(this.id.hashCode() * 31, 31, this.label);
        HomeServerKind homeServerKind = this.homeServerKind;
        return this.trackerSources.hashCode() + ((iC + (homeServerKind == null ? 0 : homeServerKind.hashCode())) * 31);
    }

    public final boolean isHomeServer() {
        return this.homeServerKind != null;
    }

    public final boolean isTracker() {
        return !this.trackerSources.isEmpty();
    }

    public final boolean isWatchlist() {
        return p.a(this.id, "provider:watchlist");
    }

    public String toString() {
        String str = this.id;
        String str2 = this.label;
        HomeServerKind homeServerKind = this.homeServerKind;
        List<WatchlistSourceItem> list = this.trackerSources;
        StringBuilder sbR = a2.r("LibraryProviderOption(id=", str, ", label=", str2, ", homeServerKind=");
        sbR.append(homeServerKind);
        sbR.append(", trackerSources=");
        sbR.append(list);
        sbR.append(")");
        return sbR.toString();
    }

    public /* synthetic */ LibraryProviderOption(String str, String str2, HomeServerKind homeServerKind, List list, int i10, kotlin.jvm.internal.h hVar) {
        this(str, str2, (i10 & 4) != 0 ? null : homeServerKind, (i10 & 8) != 0 ? z.f19728i : list);
    }
}
