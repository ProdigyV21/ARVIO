package com.arflix.tv.ui.screens.collections;

import com.arflix.tv.data.model.CatalogConfig;
import com.arflix.tv.data.model.MediaItem;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.z;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\b\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b)\b\u0087\b\u0018\u00002\u00020\u0001B£\u0001\u0012\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\u000e\b\u0002\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005\u0012\u000e\b\u0002\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005\u0012\b\b\u0002\u0010\b\u001a\u00020\t\u0012\b\b\u0002\u0010\n\u001a\u00020\t\u0012\b\b\u0002\u0010\u000b\u001a\u00020\t\u0012\b\b\u0002\u0010\f\u001a\u00020\t\u0012\b\b\u0002\u0010\r\u001a\u00020\t\u0012\b\b\u0002\u0010\u000e\u001a\u00020\t\u0012\b\b\u0002\u0010\u000f\u001a\u00020\t\u0012\b\b\u0002\u0010\u0010\u001a\u00020\t\u0012\b\b\u0002\u0010\u0011\u001a\u00020\u0012\u0012\b\b\u0002\u0010\u0013\u001a\u00020\u0012\u0012\n\b\u0002\u0010\u0014\u001a\u0004\u0018\u00010\u0015¢\u0006\u0004\b\u0016\u0010\u0017J\u000b\u0010+\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000f\u0010,\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005HÆ\u0003J\u000f\u0010-\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005HÆ\u0003J\t\u0010.\u001a\u00020\tHÆ\u0003J\t\u0010/\u001a\u00020\tHÆ\u0003J\t\u00100\u001a\u00020\tHÆ\u0003J\t\u00101\u001a\u00020\tHÆ\u0003J\t\u00102\u001a\u00020\tHÆ\u0003J\t\u00103\u001a\u00020\tHÆ\u0003J\t\u00104\u001a\u00020\tHÆ\u0003J\t\u00105\u001a\u00020\tHÆ\u0003J\t\u00106\u001a\u00020\u0012HÆ\u0003J\t\u00107\u001a\u00020\u0012HÆ\u0003J\u000b\u00108\u001a\u0004\u0018\u00010\u0015HÆ\u0003J¥\u0001\u00109\u001a\u00020\u00002\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u00032\u000e\b\u0002\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u00052\u000e\b\u0002\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\u00060\u00052\b\b\u0002\u0010\b\u001a\u00020\t2\b\b\u0002\u0010\n\u001a\u00020\t2\b\b\u0002\u0010\u000b\u001a\u00020\t2\b\b\u0002\u0010\f\u001a\u00020\t2\b\b\u0002\u0010\r\u001a\u00020\t2\b\b\u0002\u0010\u000e\u001a\u00020\t2\b\b\u0002\u0010\u000f\u001a\u00020\t2\b\b\u0002\u0010\u0010\u001a\u00020\t2\b\b\u0002\u0010\u0011\u001a\u00020\u00122\b\b\u0002\u0010\u0013\u001a\u00020\u00122\n\b\u0002\u0010\u0014\u001a\u0004\u0018\u00010\u0015HÆ\u0001J\u0014\u0010:\u001a\u00020\t2\b\u0010;\u001a\u0004\u0018\u00010\u0001HÖ\u0083\u0004J\n\u0010<\u001a\u00020\u0012HÖ\u0081\u0004J\n\u0010=\u001a\u00020\u0015HÖ\u0081\u0004R\u0013\u0010\u0002\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0018\u0010\u0019R\u0017\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u001a\u0010\u001bR\u0017\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u001c\u0010\u001bR\u0011\u0010\b\u001a\u00020\t¢\u0006\b\n\u0000\u001a\u0004\b\u001d\u0010\u001eR\u0011\u0010\n\u001a\u00020\t¢\u0006\b\n\u0000\u001a\u0004\b\u001f\u0010\u001eR\u0011\u0010\u000b\u001a\u00020\t¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\u001eR\u0011\u0010\f\u001a\u00020\t¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\u001eR\u0011\u0010\r\u001a\u00020\t¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u001eR\u0011\u0010\u000e\u001a\u00020\t¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u001eR\u0011\u0010\u000f\u001a\u00020\t¢\u0006\b\n\u0000\u001a\u0004\b \u0010\u001eR\u0011\u0010\u0010\u001a\u00020\t¢\u0006\b\n\u0000\u001a\u0004\b!\u0010\u001eR\u0011\u0010\u0011\u001a\u00020\u0012¢\u0006\b\n\u0000\u001a\u0004\b\"\u0010#R\u0011\u0010\u0013\u001a\u00020\u0012¢\u0006\b\n\u0000\u001a\u0004\b$\u0010#R\u0013\u0010\u0014\u001a\u0004\u0018\u00010\u0015¢\u0006\b\n\u0000\u001a\u0004\b%\u0010&R\u0011\u0010'\u001a\u00020\t8F¢\u0006\u0006\u001a\u0004\b(\u0010\u001eR\u0011\u0010)\u001a\u00020\t8F¢\u0006\u0006\u001a\u0004\b*\u0010\u001e¨\u0006>"}, d2 = {"Lcom/arflix/tv/ui/screens/collections/CollectionDetailsUiState;", "", "catalog", "Lcom/arflix/tv/data/model/CatalogConfig;", "movieItems", "", "Lcom/arflix/tv/data/model/MediaItem;", "seriesItems", "supportsMovies", "", "supportsSeries", "isLoadingMovies", "isLoadingSeries", "isLoadingMoreMovies", "isLoadingMoreSeries", "hasMoreMovies", "hasMoreSeries", "loadedMovieOffset", "", "loadedSeriesOffset", "error", "", "<init>", "(Lcom/arflix/tv/data/model/CatalogConfig;Ljava/util/List;Ljava/util/List;ZZZZZZZZIILjava/lang/String;)V", "getCatalog", "()Lcom/arflix/tv/data/model/CatalogConfig;", "getMovieItems", "()Ljava/util/List;", "getSeriesItems", "getSupportsMovies", "()Z", "getSupportsSeries", "getHasMoreMovies", "getHasMoreSeries", "getLoadedMovieOffset", "()I", "getLoadedSeriesOffset", "getError", "()Ljava/lang/String;", "hasMovies", "getHasMovies", "hasSeries", "getHasSeries", "component1", "component2", "component3", "component4", "component5", "component6", "component7", "component8", "component9", "component10", "component11", "component12", "component13", "component14", "copy", "equals", "other", "hashCode", "toString", "app_playRelease"}, k = 1, mv = {2, 3, 0}, xi = 48)
public final /* data */ class CollectionDetailsUiState {
    public static final int $stable = 0;
    private final CatalogConfig catalog;
    private final String error;
    private final boolean hasMoreMovies;
    private final boolean hasMoreSeries;
    private final boolean isLoadingMoreMovies;
    private final boolean isLoadingMoreSeries;
    private final boolean isLoadingMovies;
    private final boolean isLoadingSeries;
    private final int loadedMovieOffset;
    private final int loadedSeriesOffset;
    private final List<MediaItem> movieItems;
    private final List<MediaItem> seriesItems;
    private final boolean supportsMovies;
    private final boolean supportsSeries;

    public CollectionDetailsUiState() {
        this(null, null, null, false, false, false, false, false, false, false, false, 0, 0, null, 16383, null);
    }

    /* JADX INFO: renamed from: component1, reason: from getter */
    public final CatalogConfig getCatalog() {
        return this.catalog;
    }

    /* JADX INFO: renamed from: component10, reason: from getter */
    public final boolean getHasMoreMovies() {
        return this.hasMoreMovies;
    }

    /* JADX INFO: renamed from: component11, reason: from getter */
    public final boolean getHasMoreSeries() {
        return this.hasMoreSeries;
    }

    /* JADX INFO: renamed from: component12, reason: from getter */
    public final int getLoadedMovieOffset() {
        return this.loadedMovieOffset;
    }

    /* JADX INFO: renamed from: component13, reason: from getter */
    public final int getLoadedSeriesOffset() {
        return this.loadedSeriesOffset;
    }

    /* JADX INFO: renamed from: component14, reason: from getter */
    public final String getError() {
        return this.error;
    }

    public final List<MediaItem> component2() {
        return this.movieItems;
    }

    public final List<MediaItem> component3() {
        return this.seriesItems;
    }

    /* JADX INFO: renamed from: component4, reason: from getter */
    public final boolean getSupportsMovies() {
        return this.supportsMovies;
    }

    /* JADX INFO: renamed from: component5, reason: from getter */
    public final boolean getSupportsSeries() {
        return this.supportsSeries;
    }

    /* JADX INFO: renamed from: component6, reason: from getter */
    public final boolean getIsLoadingMovies() {
        return this.isLoadingMovies;
    }

    /* JADX INFO: renamed from: component7, reason: from getter */
    public final boolean getIsLoadingSeries() {
        return this.isLoadingSeries;
    }

    /* JADX INFO: renamed from: component8, reason: from getter */
    public final boolean getIsLoadingMoreMovies() {
        return this.isLoadingMoreMovies;
    }

    /* JADX INFO: renamed from: component9, reason: from getter */
    public final boolean getIsLoadingMoreSeries() {
        return this.isLoadingMoreSeries;
    }

    public final CollectionDetailsUiState copy(CatalogConfig catalog, List<MediaItem> movieItems, List<MediaItem> seriesItems, boolean supportsMovies, boolean supportsSeries, boolean isLoadingMovies, boolean isLoadingSeries, boolean isLoadingMoreMovies, boolean isLoadingMoreSeries, boolean hasMoreMovies, boolean hasMoreSeries, int loadedMovieOffset, int loadedSeriesOffset, String error) {
        return new CollectionDetailsUiState(catalog, movieItems, seriesItems, supportsMovies, supportsSeries, isLoadingMovies, isLoadingSeries, isLoadingMoreMovies, isLoadingMoreSeries, hasMoreMovies, hasMoreSeries, loadedMovieOffset, loadedSeriesOffset, error);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof CollectionDetailsUiState)) {
            return false;
        }
        CollectionDetailsUiState collectionDetailsUiState = (CollectionDetailsUiState) other;
        return kotlin.jvm.internal.p.a(this.catalog, collectionDetailsUiState.catalog) && kotlin.jvm.internal.p.a(this.movieItems, collectionDetailsUiState.movieItems) && kotlin.jvm.internal.p.a(this.seriesItems, collectionDetailsUiState.seriesItems) && this.supportsMovies == collectionDetailsUiState.supportsMovies && this.supportsSeries == collectionDetailsUiState.supportsSeries && this.isLoadingMovies == collectionDetailsUiState.isLoadingMovies && this.isLoadingSeries == collectionDetailsUiState.isLoadingSeries && this.isLoadingMoreMovies == collectionDetailsUiState.isLoadingMoreMovies && this.isLoadingMoreSeries == collectionDetailsUiState.isLoadingMoreSeries && this.hasMoreMovies == collectionDetailsUiState.hasMoreMovies && this.hasMoreSeries == collectionDetailsUiState.hasMoreSeries && this.loadedMovieOffset == collectionDetailsUiState.loadedMovieOffset && this.loadedSeriesOffset == collectionDetailsUiState.loadedSeriesOffset && kotlin.jvm.internal.p.a(this.error, collectionDetailsUiState.error);
    }

    public final CatalogConfig getCatalog() {
        return this.catalog;
    }

    public final String getError() {
        return this.error;
    }

    public final boolean getHasMoreMovies() {
        return this.hasMoreMovies;
    }

    public final boolean getHasMoreSeries() {
        return this.hasMoreSeries;
    }

    public final boolean getHasMovies() {
        return !this.movieItems.isEmpty();
    }

    public final boolean getHasSeries() {
        return !this.seriesItems.isEmpty();
    }

    public final int getLoadedMovieOffset() {
        return this.loadedMovieOffset;
    }

    public final int getLoadedSeriesOffset() {
        return this.loadedSeriesOffset;
    }

    public final List<MediaItem> getMovieItems() {
        return this.movieItems;
    }

    public final List<MediaItem> getSeriesItems() {
        return this.seriesItems;
    }

    public final boolean getSupportsMovies() {
        return this.supportsMovies;
    }

    public final boolean getSupportsSeries() {
        return this.supportsSeries;
    }

    public int hashCode() {
        CatalogConfig catalogConfig = this.catalog;
        int iE = (((((((((((((((((((androidx.compose.material3.d.e(this.seriesItems, androidx.compose.material3.d.e(this.movieItems, (catalogConfig == null ? 0 : catalogConfig.hashCode()) * 31, 31), 31) + (this.supportsMovies ? 1231 : 1237)) * 31) + (this.supportsSeries ? 1231 : 1237)) * 31) + (this.isLoadingMovies ? 1231 : 1237)) * 31) + (this.isLoadingSeries ? 1231 : 1237)) * 31) + (this.isLoadingMoreMovies ? 1231 : 1237)) * 31) + (this.isLoadingMoreSeries ? 1231 : 1237)) * 31) + (this.hasMoreMovies ? 1231 : 1237)) * 31) + (this.hasMoreSeries ? 1231 : 1237)) * 31) + this.loadedMovieOffset) * 31) + this.loadedSeriesOffset) * 31;
        String str = this.error;
        return iE + (str != null ? str.hashCode() : 0);
    }

    public final boolean isLoadingMoreMovies() {
        return this.isLoadingMoreMovies;
    }

    public final boolean isLoadingMoreSeries() {
        return this.isLoadingMoreSeries;
    }

    public final boolean isLoadingMovies() {
        return this.isLoadingMovies;
    }

    public final boolean isLoadingSeries() {
        return this.isLoadingSeries;
    }

    public String toString() {
        CatalogConfig catalogConfig = this.catalog;
        List<MediaItem> list = this.movieItems;
        List<MediaItem> list2 = this.seriesItems;
        boolean z = this.supportsMovies;
        boolean z5 = this.supportsSeries;
        boolean z10 = this.isLoadingMovies;
        boolean z11 = this.isLoadingSeries;
        boolean z12 = this.isLoadingMoreMovies;
        boolean z13 = this.isLoadingMoreSeries;
        boolean z14 = this.hasMoreMovies;
        boolean z15 = this.hasMoreSeries;
        int i10 = this.loadedMovieOffset;
        int i11 = this.loadedSeriesOffset;
        String str = this.error;
        StringBuilder sb2 = new StringBuilder("CollectionDetailsUiState(catalog=");
        sb2.append(catalogConfig);
        sb2.append(", movieItems=");
        sb2.append(list);
        sb2.append(", seriesItems=");
        sb2.append(list2);
        sb2.append(", supportsMovies=");
        sb2.append(z);
        sb2.append(", supportsSeries=");
        v.f.h(sb2, z5, ", isLoadingMovies=", z10, ", isLoadingSeries=");
        v.f.h(sb2, z11, ", isLoadingMoreMovies=", z12, ", isLoadingMoreSeries=");
        v.f.h(sb2, z13, ", hasMoreMovies=", z14, ", hasMoreSeries=");
        sb2.append(z15);
        sb2.append(", loadedMovieOffset=");
        sb2.append(i10);
        sb2.append(", loadedSeriesOffset=");
        sb2.append(i11);
        sb2.append(", error=");
        sb2.append(str);
        sb2.append(")");
        return sb2.toString();
    }

    public CollectionDetailsUiState(CatalogConfig catalogConfig, List<MediaItem> list, List<MediaItem> list2, boolean z, boolean z5, boolean z10, boolean z11, boolean z12, boolean z13, boolean z14, boolean z15, int i10, int i11, String str) {
        this.catalog = catalogConfig;
        this.movieItems = list;
        this.seriesItems = list2;
        this.supportsMovies = z;
        this.supportsSeries = z5;
        this.isLoadingMovies = z10;
        this.isLoadingSeries = z11;
        this.isLoadingMoreMovies = z12;
        this.isLoadingMoreSeries = z13;
        this.hasMoreMovies = z14;
        this.hasMoreSeries = z15;
        this.loadedMovieOffset = i10;
        this.loadedSeriesOffset = i11;
        this.error = str;
    }

    /* JADX WARN: Illegal instructions before constructor call */
    public /* synthetic */ CollectionDetailsUiState(CatalogConfig catalogConfig, List list, List list2, boolean z, boolean z5, boolean z10, boolean z11, boolean z12, boolean z13, boolean z14, boolean z15, int i10, int i11, String str, int i12, kotlin.jvm.internal.h hVar) {
        CatalogConfig catalogConfig2 = (i12 & 1) != 0 ? null : catalogConfig;
        int i13 = i12 & 2;
        List list3 = z.f19728i;
        this(catalogConfig2, i13 != 0 ? list3 : list, (i12 & 4) == 0 ? list2 : list3, (i12 & 8) != 0 ? false : z, (i12 & 16) != 0 ? false : z5, (i12 & 32) != 0 ? true : z10, (i12 & 64) == 0 ? z11 : true, (i12 & 128) != 0 ? false : z12, (i12 & 256) != 0 ? false : z13, (i12 & 512) != 0 ? false : z14, (i12 & 1024) != 0 ? false : z15, (i12 & 2048) != 0 ? 0 : i10, (i12 & 4096) == 0 ? i11 : 0, (i12 & 8192) != 0 ? null : str);
    }
}
