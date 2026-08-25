package com.arflix.tv.data.api;

import androidx.media3.extractor.text.ttml.TtmlNode;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.z;
import kotlin.jvm.internal.p;

/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\b\u0087\b\u0018\u00002\u00020\u0001B!\u0012\b\b\u0002\u0010\u0002\u001a\u00020\u0003\u0012\u000e\b\u0002\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005¢\u0006\u0004\b\u0007\u0010\bJ\t\u0010\r\u001a\u00020\u0003HÆ\u0003J\u000f\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005HÆ\u0003J#\u0010\u000f\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\u000e\b\u0002\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005HÆ\u0001J\u0014\u0010\u0010\u001a\u00020\u00112\b\u0010\u0012\u001a\u0004\u0018\u00010\u0001HÖ\u0083\u0004J\n\u0010\u0013\u001a\u00020\u0003HÖ\u0081\u0004J\n\u0010\u0014\u001a\u00020\u0015HÖ\u0081\u0004R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\nR\u0017\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\f¨\u0006\u0016"}, d2 = {"Lcom/arflix/tv/data/api/TmdbVideosResponse;", "", TtmlNode.ATTR_ID, "", "results", "", "Lcom/arflix/tv/data/api/TmdbVideo;", "<init>", "(ILjava/util/List;)V", "getId", "()I", "getResults", "()Ljava/util/List;", "component1", "component2", "copy", "equals", "", "other", "hashCode", "toString", "", "app_playRelease"}, k = 1, mv = {2, 3, 0}, xi = 48)
public final /* data */ class TmdbVideosResponse {
    public static final int $stable = 0;
    private final int id;
    private final List<TmdbVideo> results;

    /* JADX WARN: Multi-variable type inference failed */
    public TmdbVideosResponse() {
        this(0, null, 3, 0 == true ? 1 : 0);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static /* synthetic */ TmdbVideosResponse copy$default(TmdbVideosResponse tmdbVideosResponse, int i10, List list, int i11, Object obj) {
        if ((i11 & 1) != 0) {
            i10 = tmdbVideosResponse.id;
        }
        if ((i11 & 2) != 0) {
            list = tmdbVideosResponse.results;
        }
        return tmdbVideosResponse.copy(i10, list);
    }

    /* JADX INFO: renamed from: component1, reason: from getter */
    public final int getId() {
        return this.id;
    }

    public final List<TmdbVideo> component2() {
        return this.results;
    }

    public final TmdbVideosResponse copy(int id, List<TmdbVideo> results) {
        return new TmdbVideosResponse(id, results);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof TmdbVideosResponse)) {
            return false;
        }
        TmdbVideosResponse tmdbVideosResponse = (TmdbVideosResponse) other;
        return this.id == tmdbVideosResponse.id && p.a(this.results, tmdbVideosResponse.results);
    }

    public final int getId() {
        return this.id;
    }

    public final List<TmdbVideo> getResults() {
        return this.results;
    }

    public int hashCode() {
        return this.results.hashCode() + (this.id * 31);
    }

    public String toString() {
        return "TmdbVideosResponse(id=" + this.id + ", results=" + this.results + ")";
    }

    public TmdbVideosResponse(int i10, List<TmdbVideo> list) {
        this.id = i10;
        this.results = list;
    }

    public /* synthetic */ TmdbVideosResponse(int i10, List list, int i11, kotlin.jvm.internal.h hVar) {
        this((i11 & 1) != 0 ? 0 : i10, (i11 & 2) != 0 ? z.f19728i : list);
    }
}
