package com.arflix.tv.data.api;

import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.p;

/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0087\b\u0018\u00002\u00020\u0001B\u0017\u0012\u000e\u0010\u0002\u001a\n\u0012\u0004\u0012\u00020\u0004\u0018\u00010\u0003¢\u0006\u0004\b\u0005\u0010\u0006J\u0011\u0010\t\u001a\n\u0012\u0004\u0012\u00020\u0004\u0018\u00010\u0003HÆ\u0003J\u001b\u0010\n\u001a\u00020\u00002\u0010\b\u0002\u0010\u0002\u001a\n\u0012\u0004\u0012\u00020\u0004\u0018\u00010\u0003HÆ\u0001J\u0014\u0010\u000b\u001a\u00020\f2\b\u0010\r\u001a\u0004\u0018\u00010\u0001HÖ\u0083\u0004J\n\u0010\u000e\u001a\u00020\u000fHÖ\u0081\u0004J\n\u0010\u0010\u001a\u00020\u0011HÖ\u0081\u0004R\u0019\u0010\u0002\u001a\n\u0012\u0004\u0012\u00020\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\b¨\u0006\u0012"}, d2 = {"Lcom/arflix/tv/data/api/KitsuSearchResponse;", "", "data", "", "Lcom/arflix/tv/data/api/KitsuAnime;", "<init>", "(Ljava/util/List;)V", "getData", "()Ljava/util/List;", "component1", "copy", "equals", "", "other", "hashCode", "", "toString", "", "app_playRelease"}, k = 1, mv = {2, 3, 0}, xi = 48)
public final /* data */ class KitsuSearchResponse {
    public static final int $stable = 0;
    private final List<KitsuAnime> data;

    public KitsuSearchResponse(List<KitsuAnime> list) {
        this.data = list;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static /* synthetic */ KitsuSearchResponse copy$default(KitsuSearchResponse kitsuSearchResponse, List list, int i10, Object obj) {
        if ((i10 & 1) != 0) {
            list = kitsuSearchResponse.data;
        }
        return kitsuSearchResponse.copy(list);
    }

    public final List<KitsuAnime> component1() {
        return this.data;
    }

    public final KitsuSearchResponse copy(List<KitsuAnime> data) {
        return new KitsuSearchResponse(data);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        return (other instanceof KitsuSearchResponse) && p.a(this.data, ((KitsuSearchResponse) other).data);
    }

    public final List<KitsuAnime> getData() {
        return this.data;
    }

    public int hashCode() {
        List<KitsuAnime> list = this.data;
        if (list == null) {
            return 0;
        }
        return list.hashCode();
    }

    public String toString() {
        return "KitsuSearchResponse(data=" + this.data + ")";
    }
}
