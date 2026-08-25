package com.arflix.tv.data.api;

import androidx.media3.extractor.text.ttml.TtmlNode;
import java.util.Map;
import kotlin.Metadata;
import kotlin.collections.a0;
import kotlin.jvm.internal.p;

/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010$\n\u0002\u0010\u000e\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0010\u000b\n\u0002\b\u0004\b\u0087\b\u0018\u00002\u00020\u0001B'\u0012\b\b\u0002\u0010\u0002\u001a\u00020\u0003\u0012\u0014\b\u0002\u0010\u0004\u001a\u000e\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u00070\u0005¢\u0006\u0004\b\b\u0010\tJ\t\u0010\u000e\u001a\u00020\u0003HÆ\u0003J\u0015\u0010\u000f\u001a\u000e\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u00070\u0005HÆ\u0003J)\u0010\u0010\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\u0014\b\u0002\u0010\u0004\u001a\u000e\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u00070\u0005HÆ\u0001J\u0014\u0010\u0011\u001a\u00020\u00122\b\u0010\u0013\u001a\u0004\u0018\u00010\u0001HÖ\u0083\u0004J\n\u0010\u0014\u001a\u00020\u0003HÖ\u0081\u0004J\n\u0010\u0015\u001a\u00020\u0006HÖ\u0081\u0004R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR\u001d\u0010\u0004\u001a\u000e\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u00070\u0005¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\r¨\u0006\u0016"}, d2 = {"Lcom/arflix/tv/data/api/TmdbWatchProvidersResponse;", "", TtmlNode.ATTR_ID, "", "results", "", "", "Lcom/arflix/tv/data/api/TmdbWatchProviderRegion;", "<init>", "(ILjava/util/Map;)V", "getId", "()I", "getResults", "()Ljava/util/Map;", "component1", "component2", "copy", "equals", "", "other", "hashCode", "toString", "app_playRelease"}, k = 1, mv = {2, 3, 0}, xi = 48)
public final /* data */ class TmdbWatchProvidersResponse {
    public static final int $stable = 0;
    private final int id;
    private final Map<String, TmdbWatchProviderRegion> results;

    /* JADX WARN: Multi-variable type inference failed */
    public TmdbWatchProvidersResponse() {
        this(0, null, 3, 0 == true ? 1 : 0);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static /* synthetic */ TmdbWatchProvidersResponse copy$default(TmdbWatchProvidersResponse tmdbWatchProvidersResponse, int i10, Map map, int i11, Object obj) {
        if ((i11 & 1) != 0) {
            i10 = tmdbWatchProvidersResponse.id;
        }
        if ((i11 & 2) != 0) {
            map = tmdbWatchProvidersResponse.results;
        }
        return tmdbWatchProvidersResponse.copy(i10, map);
    }

    /* JADX INFO: renamed from: component1, reason: from getter */
    public final int getId() {
        return this.id;
    }

    public final Map<String, TmdbWatchProviderRegion> component2() {
        return this.results;
    }

    public final TmdbWatchProvidersResponse copy(int id, Map<String, TmdbWatchProviderRegion> results) {
        return new TmdbWatchProvidersResponse(id, results);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof TmdbWatchProvidersResponse)) {
            return false;
        }
        TmdbWatchProvidersResponse tmdbWatchProvidersResponse = (TmdbWatchProvidersResponse) other;
        return this.id == tmdbWatchProvidersResponse.id && p.a(this.results, tmdbWatchProvidersResponse.results);
    }

    public final int getId() {
        return this.id;
    }

    public final Map<String, TmdbWatchProviderRegion> getResults() {
        return this.results;
    }

    public int hashCode() {
        return this.results.hashCode() + (this.id * 31);
    }

    public String toString() {
        return "TmdbWatchProvidersResponse(id=" + this.id + ", results=" + this.results + ")";
    }

    public TmdbWatchProvidersResponse(int i10, Map<String, TmdbWatchProviderRegion> map) {
        this.id = i10;
        this.results = map;
    }

    public /* synthetic */ TmdbWatchProvidersResponse(int i10, Map map, int i11, kotlin.jvm.internal.h hVar) {
        this((i11 & 1) != 0 ? 0 : i10, (i11 & 2) != 0 ? a0.f19683i : map);
    }
}
