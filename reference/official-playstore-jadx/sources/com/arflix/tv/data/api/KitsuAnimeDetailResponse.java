package com.arflix.tv.data.api;

import kotlin.Metadata;
import kotlin.jvm.internal.p;

/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0087\b\u0018\u00002\u00020\u0001B\u0011\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\u000b\u0010\b\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u0015\u0010\t\u001a\u00020\u00002\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u0003HÆ\u0001J\u0014\u0010\n\u001a\u00020\u000b2\b\u0010\f\u001a\u0004\u0018\u00010\u0001HÖ\u0083\u0004J\n\u0010\r\u001a\u00020\u000eHÖ\u0081\u0004J\n\u0010\u000f\u001a\u00020\u0010HÖ\u0081\u0004R\u0013\u0010\u0002\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007¨\u0006\u0011"}, d2 = {"Lcom/arflix/tv/data/api/KitsuAnimeDetailResponse;", "", "data", "Lcom/arflix/tv/data/api/KitsuAnimeDetail;", "<init>", "(Lcom/arflix/tv/data/api/KitsuAnimeDetail;)V", "getData", "()Lcom/arflix/tv/data/api/KitsuAnimeDetail;", "component1", "copy", "equals", "", "other", "hashCode", "", "toString", "", "app_playRelease"}, k = 1, mv = {2, 3, 0}, xi = 48)
public final /* data */ class KitsuAnimeDetailResponse {
    public static final int $stable = 0;
    private final KitsuAnimeDetail data;

    public KitsuAnimeDetailResponse(KitsuAnimeDetail kitsuAnimeDetail) {
        this.data = kitsuAnimeDetail;
    }

    public static /* synthetic */ KitsuAnimeDetailResponse copy$default(KitsuAnimeDetailResponse kitsuAnimeDetailResponse, KitsuAnimeDetail kitsuAnimeDetail, int i10, Object obj) {
        if ((i10 & 1) != 0) {
            kitsuAnimeDetail = kitsuAnimeDetailResponse.data;
        }
        return kitsuAnimeDetailResponse.copy(kitsuAnimeDetail);
    }

    /* JADX INFO: renamed from: component1, reason: from getter */
    public final KitsuAnimeDetail getData() {
        return this.data;
    }

    public final KitsuAnimeDetailResponse copy(KitsuAnimeDetail data) {
        return new KitsuAnimeDetailResponse(data);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        return (other instanceof KitsuAnimeDetailResponse) && p.a(this.data, ((KitsuAnimeDetailResponse) other).data);
    }

    public final KitsuAnimeDetail getData() {
        return this.data;
    }

    public int hashCode() {
        KitsuAnimeDetail kitsuAnimeDetail = this.data;
        if (kitsuAnimeDetail == null) {
            return 0;
        }
        return kitsuAnimeDetail.hashCode();
    }

    public String toString() {
        return "KitsuAnimeDetailResponse(data=" + this.data + ")";
    }
}
