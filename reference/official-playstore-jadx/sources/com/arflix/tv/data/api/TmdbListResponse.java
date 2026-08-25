package com.arflix.tv.data.api;

import com.google.gson.annotations.SerializedName;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.z;
import kotlin.jvm.internal.p;

/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0010\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\b\u0087\b\u0018\u00002\u00020\u0001B5\u0012\b\b\u0002\u0010\u0002\u001a\u00020\u0003\u0012\u000e\b\u0002\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005\u0012\b\b\u0002\u0010\u0007\u001a\u00020\u0003\u0012\b\b\u0002\u0010\b\u001a\u00020\u0003¢\u0006\u0004\b\t\u0010\nJ\t\u0010\u0011\u001a\u00020\u0003HÆ\u0003J\u000f\u0010\u0012\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005HÆ\u0003J\t\u0010\u0013\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0014\u001a\u00020\u0003HÆ\u0003J7\u0010\u0015\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\u000e\b\u0002\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u00052\b\b\u0002\u0010\u0007\u001a\u00020\u00032\b\b\u0002\u0010\b\u001a\u00020\u0003HÆ\u0001J\u0014\u0010\u0016\u001a\u00020\u00172\b\u0010\u0018\u001a\u0004\u0018\u00010\u0001HÖ\u0083\u0004J\n\u0010\u0019\u001a\u00020\u0003HÖ\u0081\u0004J\n\u0010\u001a\u001a\u00020\u001bHÖ\u0081\u0004R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u0017\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000eR\u0016\u0010\u0007\u001a\u00020\u00038\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\fR\u0016\u0010\b\u001a\u00020\u00038\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\f¨\u0006\u001c"}, d2 = {"Lcom/arflix/tv/data/api/TmdbListResponse;", "", "page", "", "results", "", "Lcom/arflix/tv/data/api/TmdbMediaItem;", "totalPages", "totalResults", "<init>", "(ILjava/util/List;II)V", "getPage", "()I", "getResults", "()Ljava/util/List;", "getTotalPages", "getTotalResults", "component1", "component2", "component3", "component4", "copy", "equals", "", "other", "hashCode", "toString", "", "app_playRelease"}, k = 1, mv = {2, 3, 0}, xi = 48)
public final /* data */ class TmdbListResponse {
    public static final int $stable = 8;
    private final int page;
    private final List<TmdbMediaItem> results;

    @SerializedName("total_pages")
    private final int totalPages;

    @SerializedName("total_results")
    private final int totalResults;

    public TmdbListResponse() {
        this(0, null, 0, 0, 15, null);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static /* synthetic */ TmdbListResponse copy$default(TmdbListResponse tmdbListResponse, int i10, List list, int i11, int i12, int i13, Object obj) {
        if ((i13 & 1) != 0) {
            i10 = tmdbListResponse.page;
        }
        if ((i13 & 2) != 0) {
            list = tmdbListResponse.results;
        }
        if ((i13 & 4) != 0) {
            i11 = tmdbListResponse.totalPages;
        }
        if ((i13 & 8) != 0) {
            i12 = tmdbListResponse.totalResults;
        }
        return tmdbListResponse.copy(i10, list, i11, i12);
    }

    /* JADX INFO: renamed from: component1, reason: from getter */
    public final int getPage() {
        return this.page;
    }

    public final List<TmdbMediaItem> component2() {
        return this.results;
    }

    /* JADX INFO: renamed from: component3, reason: from getter */
    public final int getTotalPages() {
        return this.totalPages;
    }

    /* JADX INFO: renamed from: component4, reason: from getter */
    public final int getTotalResults() {
        return this.totalResults;
    }

    public final TmdbListResponse copy(int page, List<TmdbMediaItem> results, int totalPages, int totalResults) {
        return new TmdbListResponse(page, results, totalPages, totalResults);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof TmdbListResponse)) {
            return false;
        }
        TmdbListResponse tmdbListResponse = (TmdbListResponse) other;
        return this.page == tmdbListResponse.page && p.a(this.results, tmdbListResponse.results) && this.totalPages == tmdbListResponse.totalPages && this.totalResults == tmdbListResponse.totalResults;
    }

    public final int getPage() {
        return this.page;
    }

    public final List<TmdbMediaItem> getResults() {
        return this.results;
    }

    public final int getTotalPages() {
        return this.totalPages;
    }

    public final int getTotalResults() {
        return this.totalResults;
    }

    public int hashCode() {
        return ((androidx.compose.material3.d.e(this.results, this.page * 31, 31) + this.totalPages) * 31) + this.totalResults;
    }

    public String toString() {
        return "TmdbListResponse(page=" + this.page + ", results=" + this.results + ", totalPages=" + this.totalPages + ", totalResults=" + this.totalResults + ")";
    }

    public TmdbListResponse(int i10, List<TmdbMediaItem> list, int i11, int i12) {
        this.page = i10;
        this.results = list;
        this.totalPages = i11;
        this.totalResults = i12;
    }

    public /* synthetic */ TmdbListResponse(int i10, List list, int i11, int i12, int i13, kotlin.jvm.internal.h hVar) {
        this((i13 & 1) != 0 ? 1 : i10, (i13 & 2) != 0 ? z.f19728i : list, (i13 & 4) != 0 ? 1 : i11, (i13 & 8) != 0 ? 0 : i12);
    }
}
