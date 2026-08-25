package com.arflix.tv.ui.screens.watchlist;

import com.arflix.tv.data.model.MediaItem;
import com.arflix.tv.data.repository.HomeServerCatalogCandidate;
import com.arflix.tv.data.repository.HomeServerKind;
import com.arflix.tv.data.repository.HomeServerLibrarySort;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.z;
import kotlin.jvm.internal.p;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000@\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b!\n\u0002\u0010\b\n\u0002\b\u0002\b\u0087\b\u0018\u00002\u00020\u0001B\u008d\u0001\u0012\u000e\b\u0002\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003\u0012\u000e\b\u0002\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00060\u0003\u0012\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\u0004\u0012\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\t\u0012\u000e\b\u0002\u0010\n\u001a\b\u0012\u0004\u0012\u00020\u000b0\u0003\u0012\b\b\u0002\u0010\f\u001a\u00020\r\u0012\b\b\u0002\u0010\u000e\u001a\u00020\r\u0012\b\b\u0002\u0010\u000f\u001a\u00020\r\u0012\b\b\u0002\u0010\u0010\u001a\u00020\u0011\u0012\b\b\u0002\u0010\u0012\u001a\u00020\t\u0012\n\b\u0002\u0010\u0013\u001a\u0004\u0018\u00010\t¢\u0006\u0004\b\u0014\u0010\u0015J\u000f\u0010$\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003HÆ\u0003J\u000f\u0010%\u001a\b\u0012\u0004\u0012\u00020\u00060\u0003HÆ\u0003J\u000b\u0010&\u001a\u0004\u0018\u00010\u0004HÆ\u0003J\u000b\u0010'\u001a\u0004\u0018\u00010\tHÆ\u0003J\u000f\u0010(\u001a\b\u0012\u0004\u0012\u00020\u000b0\u0003HÆ\u0003J\t\u0010)\u001a\u00020\rHÆ\u0003J\t\u0010*\u001a\u00020\rHÆ\u0003J\t\u0010+\u001a\u00020\rHÆ\u0003J\t\u0010,\u001a\u00020\u0011HÆ\u0003J\t\u0010-\u001a\u00020\tHÆ\u0003J\u000b\u0010.\u001a\u0004\u0018\u00010\tHÆ\u0003J\u008f\u0001\u0010/\u001a\u00020\u00002\u000e\b\u0002\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u00032\u000e\b\u0002\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00060\u00032\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\u00042\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\t2\u000e\b\u0002\u0010\n\u001a\b\u0012\u0004\u0012\u00020\u000b0\u00032\b\b\u0002\u0010\f\u001a\u00020\r2\b\b\u0002\u0010\u000e\u001a\u00020\r2\b\b\u0002\u0010\u000f\u001a\u00020\r2\b\b\u0002\u0010\u0010\u001a\u00020\u00112\b\b\u0002\u0010\u0012\u001a\u00020\t2\n\b\u0002\u0010\u0013\u001a\u0004\u0018\u00010\tHÆ\u0001J\u0014\u00100\u001a\u00020\r2\b\u00101\u001a\u0004\u0018\u00010\u0001HÖ\u0083\u0004J\n\u00102\u001a\u000203HÖ\u0081\u0004J\n\u00104\u001a\u00020\tHÖ\u0081\u0004R\u0017\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0016\u0010\u0017R\u0017\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00060\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0018\u0010\u0017R\u0013\u0010\u0007\u001a\u0004\u0018\u00010\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0019\u0010\u001aR\u0013\u0010\b\u001a\u0004\u0018\u00010\t¢\u0006\b\n\u0000\u001a\u0004\b\u001b\u0010\u001cR\u0017\u0010\n\u001a\b\u0012\u0004\u0012\u00020\u000b0\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u001d\u0010\u0017R\u0011\u0010\f\u001a\u00020\r¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\u001eR\u0011\u0010\u000e\u001a\u00020\r¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u001eR\u0011\u0010\u000f\u001a\u00020\r¢\u0006\b\n\u0000\u001a\u0004\b\u001f\u0010\u001eR\u0011\u0010\u0010\u001a\u00020\u0011¢\u0006\b\n\u0000\u001a\u0004\b \u0010!R\u0011\u0010\u0012\u001a\u00020\t¢\u0006\b\n\u0000\u001a\u0004\b\"\u0010\u001cR\u0013\u0010\u0013\u001a\u0004\u0018\u00010\t¢\u0006\b\n\u0000\u001a\u0004\b#\u0010\u001c¨\u00065"}, d2 = {"Lcom/arflix/tv/ui/screens/watchlist/HomeLibraryUiState;", "", "providers", "", "Lcom/arflix/tv/data/repository/HomeServerKind;", "libraries", "Lcom/arflix/tv/data/repository/HomeServerCatalogCandidate;", "selectedProvider", "selectedSourceRef", "", "items", "Lcom/arflix/tv/data/model/MediaItem;", "isLoading", "", "isLoadingMore", "hasMore", "sort", "Lcom/arflix/tv/data/repository/HomeServerLibrarySort;", "searchQuery", "error", "<init>", "(Ljava/util/List;Ljava/util/List;Lcom/arflix/tv/data/repository/HomeServerKind;Ljava/lang/String;Ljava/util/List;ZZZLcom/arflix/tv/data/repository/HomeServerLibrarySort;Ljava/lang/String;Ljava/lang/String;)V", "getProviders", "()Ljava/util/List;", "getLibraries", "getSelectedProvider", "()Lcom/arflix/tv/data/repository/HomeServerKind;", "getSelectedSourceRef", "()Ljava/lang/String;", "getItems", "()Z", "getHasMore", "getSort", "()Lcom/arflix/tv/data/repository/HomeServerLibrarySort;", "getSearchQuery", "getError", "component1", "component2", "component3", "component4", "component5", "component6", "component7", "component8", "component9", "component10", "component11", "copy", "equals", "other", "hashCode", "", "toString", "app_playRelease"}, k = 1, mv = {2, 3, 0}, xi = 48)
public final /* data */ class HomeLibraryUiState {
    public static final int $stable = 0;
    private final String error;
    private final boolean hasMore;
    private final boolean isLoading;
    private final boolean isLoadingMore;
    private final List<MediaItem> items;
    private final List<HomeServerCatalogCandidate> libraries;
    private final List<HomeServerKind> providers;
    private final String searchQuery;
    private final HomeServerKind selectedProvider;
    private final String selectedSourceRef;
    private final HomeServerLibrarySort sort;

    public HomeLibraryUiState() {
        this(null, null, null, null, null, false, false, false, null, null, null, 2047, null);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static /* synthetic */ HomeLibraryUiState copy$default(HomeLibraryUiState homeLibraryUiState, List list, List list2, HomeServerKind homeServerKind, String str, List list3, boolean z, boolean z5, boolean z10, HomeServerLibrarySort homeServerLibrarySort, String str2, String str3, int i10, Object obj) {
        if ((i10 & 1) != 0) {
            list = homeLibraryUiState.providers;
        }
        if ((i10 & 2) != 0) {
            list2 = homeLibraryUiState.libraries;
        }
        if ((i10 & 4) != 0) {
            homeServerKind = homeLibraryUiState.selectedProvider;
        }
        if ((i10 & 8) != 0) {
            str = homeLibraryUiState.selectedSourceRef;
        }
        if ((i10 & 16) != 0) {
            list3 = homeLibraryUiState.items;
        }
        if ((i10 & 32) != 0) {
            z = homeLibraryUiState.isLoading;
        }
        if ((i10 & 64) != 0) {
            z5 = homeLibraryUiState.isLoadingMore;
        }
        if ((i10 & 128) != 0) {
            z10 = homeLibraryUiState.hasMore;
        }
        if ((i10 & 256) != 0) {
            homeServerLibrarySort = homeLibraryUiState.sort;
        }
        if ((i10 & 512) != 0) {
            str2 = homeLibraryUiState.searchQuery;
        }
        if ((i10 & 1024) != 0) {
            str3 = homeLibraryUiState.error;
        }
        String str4 = str2;
        String str5 = str3;
        boolean z11 = z10;
        HomeServerLibrarySort homeServerLibrarySort2 = homeServerLibrarySort;
        boolean z12 = z;
        boolean z13 = z5;
        List list4 = list3;
        HomeServerKind homeServerKind2 = homeServerKind;
        return homeLibraryUiState.copy(list, list2, homeServerKind2, str, list4, z12, z13, z11, homeServerLibrarySort2, str4, str5);
    }

    public final List<HomeServerKind> component1() {
        return this.providers;
    }

    /* JADX INFO: renamed from: component10, reason: from getter */
    public final String getSearchQuery() {
        return this.searchQuery;
    }

    /* JADX INFO: renamed from: component11, reason: from getter */
    public final String getError() {
        return this.error;
    }

    public final List<HomeServerCatalogCandidate> component2() {
        return this.libraries;
    }

    /* JADX INFO: renamed from: component3, reason: from getter */
    public final HomeServerKind getSelectedProvider() {
        return this.selectedProvider;
    }

    /* JADX INFO: renamed from: component4, reason: from getter */
    public final String getSelectedSourceRef() {
        return this.selectedSourceRef;
    }

    public final List<MediaItem> component5() {
        return this.items;
    }

    /* JADX INFO: renamed from: component6, reason: from getter */
    public final boolean getIsLoading() {
        return this.isLoading;
    }

    /* JADX INFO: renamed from: component7, reason: from getter */
    public final boolean getIsLoadingMore() {
        return this.isLoadingMore;
    }

    /* JADX INFO: renamed from: component8, reason: from getter */
    public final boolean getHasMore() {
        return this.hasMore;
    }

    /* JADX INFO: renamed from: component9, reason: from getter */
    public final HomeServerLibrarySort getSort() {
        return this.sort;
    }

    public final HomeLibraryUiState copy(List<? extends HomeServerKind> providers, List<HomeServerCatalogCandidate> libraries, HomeServerKind selectedProvider, String selectedSourceRef, List<MediaItem> items, boolean isLoading, boolean isLoadingMore, boolean hasMore, HomeServerLibrarySort sort, String searchQuery, String error) {
        return new HomeLibraryUiState(providers, libraries, selectedProvider, selectedSourceRef, items, isLoading, isLoadingMore, hasMore, sort, searchQuery, error);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof HomeLibraryUiState)) {
            return false;
        }
        HomeLibraryUiState homeLibraryUiState = (HomeLibraryUiState) other;
        return p.a(this.providers, homeLibraryUiState.providers) && p.a(this.libraries, homeLibraryUiState.libraries) && this.selectedProvider == homeLibraryUiState.selectedProvider && p.a(this.selectedSourceRef, homeLibraryUiState.selectedSourceRef) && p.a(this.items, homeLibraryUiState.items) && this.isLoading == homeLibraryUiState.isLoading && this.isLoadingMore == homeLibraryUiState.isLoadingMore && this.hasMore == homeLibraryUiState.hasMore && this.sort == homeLibraryUiState.sort && p.a(this.searchQuery, homeLibraryUiState.searchQuery) && p.a(this.error, homeLibraryUiState.error);
    }

    public final String getError() {
        return this.error;
    }

    public final boolean getHasMore() {
        return this.hasMore;
    }

    public final List<MediaItem> getItems() {
        return this.items;
    }

    public final List<HomeServerCatalogCandidate> getLibraries() {
        return this.libraries;
    }

    public final List<HomeServerKind> getProviders() {
        return this.providers;
    }

    public final String getSearchQuery() {
        return this.searchQuery;
    }

    public final HomeServerKind getSelectedProvider() {
        return this.selectedProvider;
    }

    public final String getSelectedSourceRef() {
        return this.selectedSourceRef;
    }

    public final HomeServerLibrarySort getSort() {
        return this.sort;
    }

    public int hashCode() {
        int iE = androidx.compose.material3.d.e(this.libraries, this.providers.hashCode() * 31, 31);
        HomeServerKind homeServerKind = this.selectedProvider;
        int iHashCode = (iE + (homeServerKind == null ? 0 : homeServerKind.hashCode())) * 31;
        String str = this.selectedSourceRef;
        int iC = androidx.compose.foundation.c.c((this.sort.hashCode() + ((((((androidx.compose.material3.d.e(this.items, (iHashCode + (str == null ? 0 : str.hashCode())) * 31, 31) + (this.isLoading ? 1231 : 1237)) * 31) + (this.isLoadingMore ? 1231 : 1237)) * 31) + (this.hasMore ? 1231 : 1237)) * 31)) * 31, 31, this.searchQuery);
        String str2 = this.error;
        return iC + (str2 != null ? str2.hashCode() : 0);
    }

    public final boolean isLoading() {
        return this.isLoading;
    }

    public final boolean isLoadingMore() {
        return this.isLoadingMore;
    }

    public String toString() {
        List<HomeServerKind> list = this.providers;
        List<HomeServerCatalogCandidate> list2 = this.libraries;
        HomeServerKind homeServerKind = this.selectedProvider;
        String str = this.selectedSourceRef;
        List<MediaItem> list3 = this.items;
        boolean z = this.isLoading;
        boolean z5 = this.isLoadingMore;
        boolean z10 = this.hasMore;
        HomeServerLibrarySort homeServerLibrarySort = this.sort;
        String str2 = this.searchQuery;
        String str3 = this.error;
        StringBuilder sb2 = new StringBuilder("HomeLibraryUiState(providers=");
        sb2.append(list);
        sb2.append(", libraries=");
        sb2.append(list2);
        sb2.append(", selectedProvider=");
        sb2.append(homeServerKind);
        sb2.append(", selectedSourceRef=");
        sb2.append(str);
        sb2.append(", items=");
        sb2.append(list3);
        sb2.append(", isLoading=");
        sb2.append(z);
        sb2.append(", isLoadingMore=");
        v.f.h(sb2, z5, ", hasMore=", z10, ", sort=");
        sb2.append(homeServerLibrarySort);
        sb2.append(", searchQuery=");
        sb2.append(str2);
        sb2.append(", error=");
        return a0.c.p(sb2, str3, ")");
    }

    /* JADX WARN: Multi-variable type inference failed */
    public HomeLibraryUiState(List<? extends HomeServerKind> list, List<HomeServerCatalogCandidate> list2, HomeServerKind homeServerKind, String str, List<MediaItem> list3, boolean z, boolean z5, boolean z10, HomeServerLibrarySort homeServerLibrarySort, String str2, String str3) {
        this.providers = list;
        this.libraries = list2;
        this.selectedProvider = homeServerKind;
        this.selectedSourceRef = str;
        this.items = list3;
        this.isLoading = z;
        this.isLoadingMore = z5;
        this.hasMore = z10;
        this.sort = homeServerLibrarySort;
        this.searchQuery = str2;
        this.error = str3;
    }

    /* JADX WARN: Illegal instructions before constructor call */
    public /* synthetic */ HomeLibraryUiState(List list, List list2, HomeServerKind homeServerKind, String str, List list3, boolean z, boolean z5, boolean z10, HomeServerLibrarySort homeServerLibrarySort, String str2, String str3, int i10, kotlin.jvm.internal.h hVar) {
        int i11 = i10 & 1;
        z zVar = z.f19728i;
        this(i11 != 0 ? zVar : list, (i10 & 2) != 0 ? zVar : list2, (i10 & 4) != 0 ? null : homeServerKind, (i10 & 8) != 0 ? null : str, (i10 & 16) != 0 ? zVar : list3, (i10 & 32) != 0 ? false : z, (i10 & 64) != 0 ? false : z5, (i10 & 128) != 0 ? false : z10, (i10 & 256) != 0 ? HomeServerLibrarySort.RECENTLY_ADDED : homeServerLibrarySort, (i10 & 512) != 0 ? "" : str2, (i10 & 1024) != 0 ? null : str3);
    }
}
