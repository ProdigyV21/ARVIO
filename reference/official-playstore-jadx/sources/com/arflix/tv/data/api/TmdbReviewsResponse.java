package com.arflix.tv.data.api;

import androidx.media3.extractor.text.ttml.TtmlNode;
import com.google.gson.annotations.SerializedName;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.z;
import kotlin.jvm.internal.p;

/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0012\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\b\u0087\b\u0018\u00002\u00020\u0001B?\u0012\b\b\u0002\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0004\u001a\u00020\u0003\u0012\u000e\b\u0002\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00070\u0006\u0012\b\b\u0002\u0010\b\u001a\u00020\u0003\u0012\b\b\u0002\u0010\t\u001a\u00020\u0003¢\u0006\u0004\b\n\u0010\u000bJ\t\u0010\u0013\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0014\u001a\u00020\u0003HÆ\u0003J\u000f\u0010\u0015\u001a\b\u0012\u0004\u0012\u00020\u00070\u0006HÆ\u0003J\t\u0010\u0016\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0017\u001a\u00020\u0003HÆ\u0003JA\u0010\u0018\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\u000e\b\u0002\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00070\u00062\b\b\u0002\u0010\b\u001a\u00020\u00032\b\b\u0002\u0010\t\u001a\u00020\u0003HÆ\u0001J\u0014\u0010\u0019\u001a\u00020\u001a2\b\u0010\u001b\u001a\u0004\u0018\u00010\u0001HÖ\u0083\u0004J\n\u0010\u001c\u001a\u00020\u0003HÖ\u0081\u0004J\n\u0010\u001d\u001a\u00020\u001eHÖ\u0081\u0004R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\rR\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\rR\u0017\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00070\u0006¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u0010R\u0016\u0010\b\u001a\u00020\u00038\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\rR\u0016\u0010\t\u001a\u00020\u00038\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\r¨\u0006\u001f"}, d2 = {"Lcom/arflix/tv/data/api/TmdbReviewsResponse;", "", TtmlNode.ATTR_ID, "", "page", "results", "", "Lcom/arflix/tv/data/api/TmdbReview;", "totalPages", "totalResults", "<init>", "(IILjava/util/List;II)V", "getId", "()I", "getPage", "getResults", "()Ljava/util/List;", "getTotalPages", "getTotalResults", "component1", "component2", "component3", "component4", "component5", "copy", "equals", "", "other", "hashCode", "toString", "", "app_playRelease"}, k = 1, mv = {2, 3, 0}, xi = 48)
public final /* data */ class TmdbReviewsResponse {
    public static final int $stable = 0;
    private final int id;
    private final int page;
    private final List<TmdbReview> results;

    @SerializedName("total_pages")
    private final int totalPages;

    @SerializedName("total_results")
    private final int totalResults;

    public TmdbReviewsResponse() {
        this(0, 0, null, 0, 0, 31, null);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static /* synthetic */ TmdbReviewsResponse copy$default(TmdbReviewsResponse tmdbReviewsResponse, int i10, int i11, List list, int i12, int i13, int i14, Object obj) {
        if ((i14 & 1) != 0) {
            i10 = tmdbReviewsResponse.id;
        }
        if ((i14 & 2) != 0) {
            i11 = tmdbReviewsResponse.page;
        }
        if ((i14 & 4) != 0) {
            list = tmdbReviewsResponse.results;
        }
        if ((i14 & 8) != 0) {
            i12 = tmdbReviewsResponse.totalPages;
        }
        if ((i14 & 16) != 0) {
            i13 = tmdbReviewsResponse.totalResults;
        }
        int i15 = i13;
        List list2 = list;
        return tmdbReviewsResponse.copy(i10, i11, list2, i12, i15);
    }

    /* JADX INFO: renamed from: component1, reason: from getter */
    public final int getId() {
        return this.id;
    }

    /* JADX INFO: renamed from: component2, reason: from getter */
    public final int getPage() {
        return this.page;
    }

    public final List<TmdbReview> component3() {
        return this.results;
    }

    /* JADX INFO: renamed from: component4, reason: from getter */
    public final int getTotalPages() {
        return this.totalPages;
    }

    /* JADX INFO: renamed from: component5, reason: from getter */
    public final int getTotalResults() {
        return this.totalResults;
    }

    public final TmdbReviewsResponse copy(int id, int page, List<TmdbReview> results, int totalPages, int totalResults) {
        return new TmdbReviewsResponse(id, page, results, totalPages, totalResults);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof TmdbReviewsResponse)) {
            return false;
        }
        TmdbReviewsResponse tmdbReviewsResponse = (TmdbReviewsResponse) other;
        return this.id == tmdbReviewsResponse.id && this.page == tmdbReviewsResponse.page && p.a(this.results, tmdbReviewsResponse.results) && this.totalPages == tmdbReviewsResponse.totalPages && this.totalResults == tmdbReviewsResponse.totalResults;
    }

    public final int getId() {
        return this.id;
    }

    public final int getPage() {
        return this.page;
    }

    public final List<TmdbReview> getResults() {
        return this.results;
    }

    public final int getTotalPages() {
        return this.totalPages;
    }

    public final int getTotalResults() {
        return this.totalResults;
    }

    public int hashCode() {
        return ((androidx.compose.material3.d.e(this.results, ((this.id * 31) + this.page) * 31, 31) + this.totalPages) * 31) + this.totalResults;
    }

    public String toString() {
        int i10 = this.id;
        int i11 = this.page;
        List<TmdbReview> list = this.results;
        int i12 = this.totalPages;
        int i13 = this.totalResults;
        StringBuilder sbV = androidx.compose.foundation.c.v("TmdbReviewsResponse(id=", i10, ", page=", i11, ", results=");
        sbV.append(list);
        sbV.append(", totalPages=");
        sbV.append(i12);
        sbV.append(", totalResults=");
        return androidx.compose.material3.d.j(i13, ")", sbV);
    }

    public TmdbReviewsResponse(int i10, int i11, List<TmdbReview> list, int i12, int i13) {
        this.id = i10;
        this.page = i11;
        this.results = list;
        this.totalPages = i12;
        this.totalResults = i13;
    }

    public /* synthetic */ TmdbReviewsResponse(int i10, int i11, List list, int i12, int i13, int i14, kotlin.jvm.internal.h hVar) {
        this((i14 & 1) != 0 ? 0 : i10, (i14 & 2) != 0 ? 1 : i11, (i14 & 4) != 0 ? z.f19728i : list, (i14 & 8) != 0 ? 1 : i12, (i14 & 16) != 0 ? 0 : i13);
    }
}
