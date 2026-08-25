package com.arflix.tv.data.api;

import com.google.gson.annotations.SerializedName;
import kotlin.Metadata;
import kotlin.jvm.internal.p;

/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0011\b\u0087\b\u0018\u00002\u00020\u0001B!\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\b\u0010\u0006\u001a\u0004\u0018\u00010\u0007¢\u0006\u0004\b\b\u0010\tJ\t\u0010\u0010\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0011\u001a\u00020\u0005HÆ\u0003J\u000b\u0010\u0012\u001a\u0004\u0018\u00010\u0007HÆ\u0003J)\u0010\u0013\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u0007HÆ\u0001J\u0014\u0010\u0014\u001a\u00020\u00052\b\u0010\u0015\u001a\u0004\u0018\u00010\u0001HÖ\u0083\u0004J\n\u0010\u0016\u001a\u00020\u0003HÖ\u0081\u0004J\n\u0010\u0017\u001a\u00020\u0007HÖ\u0081\u0004R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\rR\u0018\u0010\u0006\u001a\u0004\u0018\u00010\u00078\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000f¨\u0006\u0018"}, d2 = {"Lcom/arflix/tv/data/api/TraktProgressEpisode;", "", "number", "", "completed", "", "lastWatchedAt", "", "<init>", "(IZLjava/lang/String;)V", "getNumber", "()I", "getCompleted", "()Z", "getLastWatchedAt", "()Ljava/lang/String;", "component1", "component2", "component3", "copy", "equals", "other", "hashCode", "toString", "app_playRelease"}, k = 1, mv = {2, 3, 0}, xi = 48)
public final /* data */ class TraktProgressEpisode {
    public static final int $stable = 0;
    private final boolean completed;

    @SerializedName("last_watched_at")
    private final String lastWatchedAt;
    private final int number;

    public TraktProgressEpisode(int i10, boolean z, String str) {
        this.number = i10;
        this.completed = z;
        this.lastWatchedAt = str;
    }

    public static /* synthetic */ TraktProgressEpisode copy$default(TraktProgressEpisode traktProgressEpisode, int i10, boolean z, String str, int i11, Object obj) {
        if ((i11 & 1) != 0) {
            i10 = traktProgressEpisode.number;
        }
        if ((i11 & 2) != 0) {
            z = traktProgressEpisode.completed;
        }
        if ((i11 & 4) != 0) {
            str = traktProgressEpisode.lastWatchedAt;
        }
        return traktProgressEpisode.copy(i10, z, str);
    }

    /* JADX INFO: renamed from: component1, reason: from getter */
    public final int getNumber() {
        return this.number;
    }

    /* JADX INFO: renamed from: component2, reason: from getter */
    public final boolean getCompleted() {
        return this.completed;
    }

    /* JADX INFO: renamed from: component3, reason: from getter */
    public final String getLastWatchedAt() {
        return this.lastWatchedAt;
    }

    public final TraktProgressEpisode copy(int number, boolean completed, String lastWatchedAt) {
        return new TraktProgressEpisode(number, completed, lastWatchedAt);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof TraktProgressEpisode)) {
            return false;
        }
        TraktProgressEpisode traktProgressEpisode = (TraktProgressEpisode) other;
        return this.number == traktProgressEpisode.number && this.completed == traktProgressEpisode.completed && p.a(this.lastWatchedAt, traktProgressEpisode.lastWatchedAt);
    }

    public final boolean getCompleted() {
        return this.completed;
    }

    public final String getLastWatchedAt() {
        return this.lastWatchedAt;
    }

    public final int getNumber() {
        return this.number;
    }

    public int hashCode() {
        int i10 = ((this.number * 31) + (this.completed ? 1231 : 1237)) * 31;
        String str = this.lastWatchedAt;
        return i10 + (str == null ? 0 : str.hashCode());
    }

    public String toString() {
        int i10 = this.number;
        boolean z = this.completed;
        String str = this.lastWatchedAt;
        StringBuilder sb2 = new StringBuilder("TraktProgressEpisode(number=");
        sb2.append(i10);
        sb2.append(", completed=");
        sb2.append(z);
        sb2.append(", lastWatchedAt=");
        return a0.c.p(sb2, str, ")");
    }
}
