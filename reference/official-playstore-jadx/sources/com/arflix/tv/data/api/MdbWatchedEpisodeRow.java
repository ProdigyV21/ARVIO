package com.arflix.tv.data.api;

import com.google.gson.annotations.SerializedName;
import kotlin.Metadata;
import kotlin.jvm.internal.p;

/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\u0087\b\u0018\u00002\u00020\u0001B\u001f\u0012\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0005¢\u0006\u0004\b\u0006\u0010\u0007J\u000b\u0010\f\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u0010\r\u001a\u0004\u0018\u00010\u0005HÆ\u0003J!\u0010\u000e\u001a\u00020\u00002\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0005HÆ\u0001J\u0014\u0010\u000f\u001a\u00020\u00102\b\u0010\u0011\u001a\u0004\u0018\u00010\u0001HÖ\u0083\u0004J\n\u0010\u0012\u001a\u00020\u0013HÖ\u0081\u0004J\n\u0010\u0014\u001a\u00020\u0003HÖ\u0081\u0004R\u0018\u0010\u0002\u001a\u0004\u0018\u00010\u00038\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u0013\u0010\u0004\u001a\u0004\u0018\u00010\u0005¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000b¨\u0006\u0015"}, d2 = {"Lcom/arflix/tv/data/api/MdbWatchedEpisodeRow;", "", "lastWatchedAt", "", "episode", "Lcom/arflix/tv/data/api/MdbEpisodeInfo;", "<init>", "(Ljava/lang/String;Lcom/arflix/tv/data/api/MdbEpisodeInfo;)V", "getLastWatchedAt", "()Ljava/lang/String;", "getEpisode", "()Lcom/arflix/tv/data/api/MdbEpisodeInfo;", "component1", "component2", "copy", "equals", "", "other", "hashCode", "", "toString", "app_playRelease"}, k = 1, mv = {2, 3, 0}, xi = 48)
public final /* data */ class MdbWatchedEpisodeRow {
    public static final int $stable = 0;
    private final MdbEpisodeInfo episode;

    @SerializedName("last_watched_at")
    private final String lastWatchedAt;

    /* JADX WARN: Multi-variable type inference failed */
    public MdbWatchedEpisodeRow() {
        this(null, 0 == true ? 1 : 0, 3, 0 == true ? 1 : 0);
    }

    public static /* synthetic */ MdbWatchedEpisodeRow copy$default(MdbWatchedEpisodeRow mdbWatchedEpisodeRow, String str, MdbEpisodeInfo mdbEpisodeInfo, int i10, Object obj) {
        if ((i10 & 1) != 0) {
            str = mdbWatchedEpisodeRow.lastWatchedAt;
        }
        if ((i10 & 2) != 0) {
            mdbEpisodeInfo = mdbWatchedEpisodeRow.episode;
        }
        return mdbWatchedEpisodeRow.copy(str, mdbEpisodeInfo);
    }

    /* JADX INFO: renamed from: component1, reason: from getter */
    public final String getLastWatchedAt() {
        return this.lastWatchedAt;
    }

    /* JADX INFO: renamed from: component2, reason: from getter */
    public final MdbEpisodeInfo getEpisode() {
        return this.episode;
    }

    public final MdbWatchedEpisodeRow copy(String lastWatchedAt, MdbEpisodeInfo episode) {
        return new MdbWatchedEpisodeRow(lastWatchedAt, episode);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof MdbWatchedEpisodeRow)) {
            return false;
        }
        MdbWatchedEpisodeRow mdbWatchedEpisodeRow = (MdbWatchedEpisodeRow) other;
        return p.a(this.lastWatchedAt, mdbWatchedEpisodeRow.lastWatchedAt) && p.a(this.episode, mdbWatchedEpisodeRow.episode);
    }

    public final MdbEpisodeInfo getEpisode() {
        return this.episode;
    }

    public final String getLastWatchedAt() {
        return this.lastWatchedAt;
    }

    public int hashCode() {
        String str = this.lastWatchedAt;
        int iHashCode = (str == null ? 0 : str.hashCode()) * 31;
        MdbEpisodeInfo mdbEpisodeInfo = this.episode;
        return iHashCode + (mdbEpisodeInfo != null ? mdbEpisodeInfo.hashCode() : 0);
    }

    public String toString() {
        return "MdbWatchedEpisodeRow(lastWatchedAt=" + this.lastWatchedAt + ", episode=" + this.episode + ")";
    }

    public MdbWatchedEpisodeRow(String str, MdbEpisodeInfo mdbEpisodeInfo) {
        this.lastWatchedAt = str;
        this.episode = mdbEpisodeInfo;
    }

    public /* synthetic */ MdbWatchedEpisodeRow(String str, MdbEpisodeInfo mdbEpisodeInfo, int i10, kotlin.jvm.internal.h hVar) {
        this((i10 & 1) != 0 ? null : str, (i10 & 2) != 0 ? null : mdbEpisodeInfo);
    }
}
