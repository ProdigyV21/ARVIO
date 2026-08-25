package com.arflix.tv.data.api;

import com.google.gson.annotations.SerializedName;
import kotlin.Metadata;
import kotlin.jvm.internal.p;

/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\f\n\u0002\u0010\u000b\n\u0002\b\u0004\b\u0087\b\u0018\u00002\u00020\u0001B!\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\b\u0010\u0005\u001a\u0004\u0018\u00010\u0006¢\u0006\u0004\b\u0007\u0010\bJ\t\u0010\u000e\u001a\u00020\u0003HÆ\u0003J\t\u0010\u000f\u001a\u00020\u0003HÆ\u0003J\u000b\u0010\u0010\u001a\u0004\u0018\u00010\u0006HÆ\u0003J)\u0010\u0011\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u0006HÆ\u0001J\u0014\u0010\u0012\u001a\u00020\u00132\b\u0010\u0014\u001a\u0004\u0018\u00010\u0001HÖ\u0083\u0004J\n\u0010\u0015\u001a\u00020\u0003HÖ\u0081\u0004J\n\u0010\u0016\u001a\u00020\u0006HÖ\u0081\u0004R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\nR\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\nR\u0018\u0010\u0005\u001a\u0004\u0018\u00010\u00068\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\r¨\u0006\u0017"}, d2 = {"Lcom/arflix/tv/data/api/TraktWatchedEpisode;", "", "number", "", "plays", "lastWatchedAt", "", "<init>", "(IILjava/lang/String;)V", "getNumber", "()I", "getPlays", "getLastWatchedAt", "()Ljava/lang/String;", "component1", "component2", "component3", "copy", "equals", "", "other", "hashCode", "toString", "app_playRelease"}, k = 1, mv = {2, 3, 0}, xi = 48)
public final /* data */ class TraktWatchedEpisode {
    public static final int $stable = 0;

    @SerializedName("last_watched_at")
    private final String lastWatchedAt;
    private final int number;
    private final int plays;

    public TraktWatchedEpisode(int i10, int i11, String str) {
        this.number = i10;
        this.plays = i11;
        this.lastWatchedAt = str;
    }

    public static /* synthetic */ TraktWatchedEpisode copy$default(TraktWatchedEpisode traktWatchedEpisode, int i10, int i11, String str, int i12, Object obj) {
        if ((i12 & 1) != 0) {
            i10 = traktWatchedEpisode.number;
        }
        if ((i12 & 2) != 0) {
            i11 = traktWatchedEpisode.plays;
        }
        if ((i12 & 4) != 0) {
            str = traktWatchedEpisode.lastWatchedAt;
        }
        return traktWatchedEpisode.copy(i10, i11, str);
    }

    /* JADX INFO: renamed from: component1, reason: from getter */
    public final int getNumber() {
        return this.number;
    }

    /* JADX INFO: renamed from: component2, reason: from getter */
    public final int getPlays() {
        return this.plays;
    }

    /* JADX INFO: renamed from: component3, reason: from getter */
    public final String getLastWatchedAt() {
        return this.lastWatchedAt;
    }

    public final TraktWatchedEpisode copy(int number, int plays, String lastWatchedAt) {
        return new TraktWatchedEpisode(number, plays, lastWatchedAt);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof TraktWatchedEpisode)) {
            return false;
        }
        TraktWatchedEpisode traktWatchedEpisode = (TraktWatchedEpisode) other;
        return this.number == traktWatchedEpisode.number && this.plays == traktWatchedEpisode.plays && p.a(this.lastWatchedAt, traktWatchedEpisode.lastWatchedAt);
    }

    public final String getLastWatchedAt() {
        return this.lastWatchedAt;
    }

    public final int getNumber() {
        return this.number;
    }

    public final int getPlays() {
        return this.plays;
    }

    public int hashCode() {
        int i10 = ((this.number * 31) + this.plays) * 31;
        String str = this.lastWatchedAt;
        return i10 + (str == null ? 0 : str.hashCode());
    }

    public String toString() {
        int i10 = this.number;
        int i11 = this.plays;
        return a0.c.p(androidx.compose.foundation.c.v("TraktWatchedEpisode(number=", i10, ", plays=", i11, ", lastWatchedAt="), this.lastWatchedAt, ")");
    }
}
