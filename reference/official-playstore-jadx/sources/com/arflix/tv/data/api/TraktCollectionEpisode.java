package com.arflix.tv.data.api;

import com.google.gson.annotations.SerializedName;
import kotlin.Metadata;
import kotlin.jvm.internal.p;

/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\n\n\u0002\u0010\u000b\n\u0002\b\u0004\b\u0087\b\u0018\u00002\u00020\u0001B\u0019\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\b\u0010\u0004\u001a\u0004\u0018\u00010\u0005¢\u0006\u0004\b\u0006\u0010\u0007J\t\u0010\f\u001a\u00020\u0003HÆ\u0003J\u000b\u0010\r\u001a\u0004\u0018\u00010\u0005HÆ\u0003J\u001f\u0010\u000e\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0005HÆ\u0001J\u0014\u0010\u000f\u001a\u00020\u00102\b\u0010\u0011\u001a\u0004\u0018\u00010\u0001HÖ\u0083\u0004J\n\u0010\u0012\u001a\u00020\u0003HÖ\u0081\u0004J\n\u0010\u0013\u001a\u00020\u0005HÖ\u0081\u0004R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u0018\u0010\u0004\u001a\u0004\u0018\u00010\u00058\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000b¨\u0006\u0014"}, d2 = {"Lcom/arflix/tv/data/api/TraktCollectionEpisode;", "", "number", "", "collectedAt", "", "<init>", "(ILjava/lang/String;)V", "getNumber", "()I", "getCollectedAt", "()Ljava/lang/String;", "component1", "component2", "copy", "equals", "", "other", "hashCode", "toString", "app_playRelease"}, k = 1, mv = {2, 3, 0}, xi = 48)
public final /* data */ class TraktCollectionEpisode {
    public static final int $stable = 0;

    @SerializedName("collected_at")
    private final String collectedAt;
    private final int number;

    public TraktCollectionEpisode(int i10, String str) {
        this.number = i10;
        this.collectedAt = str;
    }

    public static /* synthetic */ TraktCollectionEpisode copy$default(TraktCollectionEpisode traktCollectionEpisode, int i10, String str, int i11, Object obj) {
        if ((i11 & 1) != 0) {
            i10 = traktCollectionEpisode.number;
        }
        if ((i11 & 2) != 0) {
            str = traktCollectionEpisode.collectedAt;
        }
        return traktCollectionEpisode.copy(i10, str);
    }

    /* JADX INFO: renamed from: component1, reason: from getter */
    public final int getNumber() {
        return this.number;
    }

    /* JADX INFO: renamed from: component2, reason: from getter */
    public final String getCollectedAt() {
        return this.collectedAt;
    }

    public final TraktCollectionEpisode copy(int number, String collectedAt) {
        return new TraktCollectionEpisode(number, collectedAt);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof TraktCollectionEpisode)) {
            return false;
        }
        TraktCollectionEpisode traktCollectionEpisode = (TraktCollectionEpisode) other;
        return this.number == traktCollectionEpisode.number && p.a(this.collectedAt, traktCollectionEpisode.collectedAt);
    }

    public final String getCollectedAt() {
        return this.collectedAt;
    }

    public final int getNumber() {
        return this.number;
    }

    public int hashCode() {
        int i10 = this.number * 31;
        String str = this.collectedAt;
        return i10 + (str == null ? 0 : str.hashCode());
    }

    public String toString() {
        return "TraktCollectionEpisode(number=" + this.number + ", collectedAt=" + this.collectedAt + ")";
    }
}
