package com.arflix.tv.data.api;

import com.google.gson.annotations.SerializedName;
import kotlin.Metadata;
import kotlin.jvm.internal.p;

/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\n\n\u0002\u0010\u000b\n\u0002\b\u0004\b\u0087\b\u0018\u00002\u00020\u0001B\u001b\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0005¢\u0006\u0004\b\u0006\u0010\u0007J\t\u0010\f\u001a\u00020\u0003HÆ\u0003J\u000b\u0010\r\u001a\u0004\u0018\u00010\u0005HÆ\u0003J\u001f\u0010\u000e\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0005HÆ\u0001J\u0014\u0010\u000f\u001a\u00020\u00102\b\u0010\u0011\u001a\u0004\u0018\u00010\u0001HÖ\u0083\u0004J\n\u0010\u0012\u001a\u00020\u0003HÖ\u0081\u0004J\n\u0010\u0013\u001a\u00020\u0005HÖ\u0081\u0004R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u0018\u0010\u0004\u001a\u0004\u0018\u00010\u00058\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000b¨\u0006\u0014"}, d2 = {"Lcom/arflix/tv/data/api/TraktBulkEpisodeItem;", "", "number", "", "watchedAt", "", "<init>", "(ILjava/lang/String;)V", "getNumber", "()I", "getWatchedAt", "()Ljava/lang/String;", "component1", "component2", "copy", "equals", "", "other", "hashCode", "toString", "app_playRelease"}, k = 1, mv = {2, 3, 0}, xi = 48)
public final /* data */ class TraktBulkEpisodeItem {
    public static final int $stable = 0;
    private final int number;

    @SerializedName("watched_at")
    private final String watchedAt;

    public TraktBulkEpisodeItem(int i10, String str) {
        this.number = i10;
        this.watchedAt = str;
    }

    public static /* synthetic */ TraktBulkEpisodeItem copy$default(TraktBulkEpisodeItem traktBulkEpisodeItem, int i10, String str, int i11, Object obj) {
        if ((i11 & 1) != 0) {
            i10 = traktBulkEpisodeItem.number;
        }
        if ((i11 & 2) != 0) {
            str = traktBulkEpisodeItem.watchedAt;
        }
        return traktBulkEpisodeItem.copy(i10, str);
    }

    /* JADX INFO: renamed from: component1, reason: from getter */
    public final int getNumber() {
        return this.number;
    }

    /* JADX INFO: renamed from: component2, reason: from getter */
    public final String getWatchedAt() {
        return this.watchedAt;
    }

    public final TraktBulkEpisodeItem copy(int number, String watchedAt) {
        return new TraktBulkEpisodeItem(number, watchedAt);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof TraktBulkEpisodeItem)) {
            return false;
        }
        TraktBulkEpisodeItem traktBulkEpisodeItem = (TraktBulkEpisodeItem) other;
        return this.number == traktBulkEpisodeItem.number && p.a(this.watchedAt, traktBulkEpisodeItem.watchedAt);
    }

    public final int getNumber() {
        return this.number;
    }

    public final String getWatchedAt() {
        return this.watchedAt;
    }

    public int hashCode() {
        int i10 = this.number * 31;
        String str = this.watchedAt;
        return i10 + (str == null ? 0 : str.hashCode());
    }

    public String toString() {
        return "TraktBulkEpisodeItem(number=" + this.number + ", watchedAt=" + this.watchedAt + ")";
    }

    public /* synthetic */ TraktBulkEpisodeItem(int i10, String str, int i11, kotlin.jvm.internal.h hVar) {
        this(i10, (i11 & 2) != 0 ? null : str);
    }
}
