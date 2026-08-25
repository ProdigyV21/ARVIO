package com.arflix.tv.data.api;

import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.p;

/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0087\b\u0018\u00002\u00020\u0001B'\u0012\u000e\u0010\u0002\u001a\n\u0012\u0004\u0012\u00020\u0004\u0018\u00010\u0003\u0012\u000e\u0010\u0005\u001a\n\u0012\u0004\u0012\u00020\u0006\u0018\u00010\u0003¢\u0006\u0004\b\u0007\u0010\bJ\u0011\u0010\f\u001a\n\u0012\u0004\u0012\u00020\u0004\u0018\u00010\u0003HÆ\u0003J\u0011\u0010\r\u001a\n\u0012\u0004\u0012\u00020\u0006\u0018\u00010\u0003HÆ\u0003J-\u0010\u000e\u001a\u00020\u00002\u0010\b\u0002\u0010\u0002\u001a\n\u0012\u0004\u0012\u00020\u0004\u0018\u00010\u00032\u0010\b\u0002\u0010\u0005\u001a\n\u0012\u0004\u0012\u00020\u0006\u0018\u00010\u0003HÆ\u0001J\u0014\u0010\u000f\u001a\u00020\u00102\b\u0010\u0011\u001a\u0004\u0018\u00010\u0001HÖ\u0083\u0004J\n\u0010\u0012\u001a\u00020\u0013HÖ\u0081\u0004J\n\u0010\u0014\u001a\u00020\u0015HÖ\u0081\u0004R\u0019\u0010\u0002\u001a\n\u0012\u0004\u0012\u00020\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\nR\u0019\u0010\u0005\u001a\n\u0012\u0004\u0012\u00020\u0006\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\n¨\u0006\u0016"}, d2 = {"Lcom/arflix/tv/data/api/KitsuMediaRelationshipsResponse;", "", "data", "", "Lcom/arflix/tv/data/api/KitsuMediaRelationship;", "included", "Lcom/arflix/tv/data/api/KitsuIncludedAnime;", "<init>", "(Ljava/util/List;Ljava/util/List;)V", "getData", "()Ljava/util/List;", "getIncluded", "component1", "component2", "copy", "equals", "", "other", "hashCode", "", "toString", "", "app_playRelease"}, k = 1, mv = {2, 3, 0}, xi = 48)
public final /* data */ class KitsuMediaRelationshipsResponse {
    public static final int $stable = 0;
    private final List<KitsuMediaRelationship> data;
    private final List<KitsuIncludedAnime> included;

    public KitsuMediaRelationshipsResponse(List<KitsuMediaRelationship> list, List<KitsuIncludedAnime> list2) {
        this.data = list;
        this.included = list2;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static /* synthetic */ KitsuMediaRelationshipsResponse copy$default(KitsuMediaRelationshipsResponse kitsuMediaRelationshipsResponse, List list, List list2, int i10, Object obj) {
        if ((i10 & 1) != 0) {
            list = kitsuMediaRelationshipsResponse.data;
        }
        if ((i10 & 2) != 0) {
            list2 = kitsuMediaRelationshipsResponse.included;
        }
        return kitsuMediaRelationshipsResponse.copy(list, list2);
    }

    public final List<KitsuMediaRelationship> component1() {
        return this.data;
    }

    public final List<KitsuIncludedAnime> component2() {
        return this.included;
    }

    public final KitsuMediaRelationshipsResponse copy(List<KitsuMediaRelationship> data, List<KitsuIncludedAnime> included) {
        return new KitsuMediaRelationshipsResponse(data, included);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof KitsuMediaRelationshipsResponse)) {
            return false;
        }
        KitsuMediaRelationshipsResponse kitsuMediaRelationshipsResponse = (KitsuMediaRelationshipsResponse) other;
        return p.a(this.data, kitsuMediaRelationshipsResponse.data) && p.a(this.included, kitsuMediaRelationshipsResponse.included);
    }

    public final List<KitsuMediaRelationship> getData() {
        return this.data;
    }

    public final List<KitsuIncludedAnime> getIncluded() {
        return this.included;
    }

    public int hashCode() {
        List<KitsuMediaRelationship> list = this.data;
        int iHashCode = (list == null ? 0 : list.hashCode()) * 31;
        List<KitsuIncludedAnime> list2 = this.included;
        return iHashCode + (list2 != null ? list2.hashCode() : 0);
    }

    public String toString() {
        return "KitsuMediaRelationshipsResponse(data=" + this.data + ", included=" + this.included + ")";
    }
}
