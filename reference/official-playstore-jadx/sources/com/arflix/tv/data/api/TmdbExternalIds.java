package com.arflix.tv.data.api;

import com.google.gson.annotations.SerializedName;
import kotlin.Metadata;
import kotlin.jvm.internal.p;

/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0002\b\f\n\u0002\u0010\u000b\n\u0002\b\u0004\b\u0087\b\u0018\u00002\u00020\u0001B\u001f\u0012\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0005¢\u0006\u0004\b\u0006\u0010\u0007J\u000b\u0010\r\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u0010\u0010\u000e\u001a\u0004\u0018\u00010\u0005HÆ\u0003¢\u0006\u0002\u0010\u000bJ&\u0010\u000f\u001a\u00020\u00002\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0005HÆ\u0001¢\u0006\u0002\u0010\u0010J\u0014\u0010\u0011\u001a\u00020\u00122\b\u0010\u0013\u001a\u0004\u0018\u00010\u0001HÖ\u0083\u0004J\n\u0010\u0014\u001a\u00020\u0005HÖ\u0081\u0004J\n\u0010\u0015\u001a\u00020\u0003HÖ\u0081\u0004R\u0018\u0010\u0002\u001a\u0004\u0018\u00010\u00038\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u001a\u0010\u0004\u001a\u0004\u0018\u00010\u00058\u0006X\u0087\u0004¢\u0006\n\n\u0002\u0010\f\u001a\u0004\b\n\u0010\u000b¨\u0006\u0016"}, d2 = {"Lcom/arflix/tv/data/api/TmdbExternalIds;", "", "imdbId", "", "tvdbId", "", "<init>", "(Ljava/lang/String;Ljava/lang/Integer;)V", "getImdbId", "()Ljava/lang/String;", "getTvdbId", "()Ljava/lang/Integer;", "Ljava/lang/Integer;", "component1", "component2", "copy", "(Ljava/lang/String;Ljava/lang/Integer;)Lcom/arflix/tv/data/api/TmdbExternalIds;", "equals", "", "other", "hashCode", "toString", "app_playRelease"}, k = 1, mv = {2, 3, 0}, xi = 48)
public final /* data */ class TmdbExternalIds {
    public static final int $stable = 0;

    @SerializedName("imdb_id")
    private final String imdbId;

    @SerializedName("tvdb_id")
    private final Integer tvdbId;

    /* JADX WARN: Multi-variable type inference failed */
    public TmdbExternalIds() {
        this(null, 0 == true ? 1 : 0, 3, 0 == true ? 1 : 0);
    }

    public static /* synthetic */ TmdbExternalIds copy$default(TmdbExternalIds tmdbExternalIds, String str, Integer num, int i10, Object obj) {
        if ((i10 & 1) != 0) {
            str = tmdbExternalIds.imdbId;
        }
        if ((i10 & 2) != 0) {
            num = tmdbExternalIds.tvdbId;
        }
        return tmdbExternalIds.copy(str, num);
    }

    /* JADX INFO: renamed from: component1, reason: from getter */
    public final String getImdbId() {
        return this.imdbId;
    }

    /* JADX INFO: renamed from: component2, reason: from getter */
    public final Integer getTvdbId() {
        return this.tvdbId;
    }

    public final TmdbExternalIds copy(String imdbId, Integer tvdbId) {
        return new TmdbExternalIds(imdbId, tvdbId);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof TmdbExternalIds)) {
            return false;
        }
        TmdbExternalIds tmdbExternalIds = (TmdbExternalIds) other;
        return p.a(this.imdbId, tmdbExternalIds.imdbId) && p.a(this.tvdbId, tmdbExternalIds.tvdbId);
    }

    public final String getImdbId() {
        return this.imdbId;
    }

    public final Integer getTvdbId() {
        return this.tvdbId;
    }

    public int hashCode() {
        String str = this.imdbId;
        int iHashCode = (str == null ? 0 : str.hashCode()) * 31;
        Integer num = this.tvdbId;
        return iHashCode + (num != null ? num.hashCode() : 0);
    }

    public String toString() {
        return "TmdbExternalIds(imdbId=" + this.imdbId + ", tvdbId=" + this.tvdbId + ")";
    }

    public TmdbExternalIds(String str, Integer num) {
        this.imdbId = str;
        this.tvdbId = num;
    }

    public /* synthetic */ TmdbExternalIds(String str, Integer num, int i10, kotlin.jvm.internal.h hVar) {
        this((i10 & 1) != 0 ? null : str, (i10 & 2) != 0 ? null : num);
    }
}
