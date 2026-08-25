package com.arflix.tv.util;

import a0.c;
import kotlin.Metadata;
import kotlin.jvm.internal.p;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u0007\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u000f\n\u0002\u0010\u000b\n\u0002\b\u0004\b\u0087\b\u0018\u00002\u00020\u0001B)\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u0012\b\u0010\u0007\u001a\u0004\u0018\u00010\b¢\u0006\u0004\b\t\u0010\nJ\t\u0010\u0012\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0013\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0014\u001a\u00020\u0006HÆ\u0003J\u000b\u0010\u0015\u001a\u0004\u0018\u00010\bHÆ\u0003J3\u0010\u0016\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u00062\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\bHÆ\u0001J\u0014\u0010\u0017\u001a\u00020\u00182\b\u0010\u0019\u001a\u0004\u0018\u00010\u0001HÖ\u0083\u0004J\n\u0010\u001a\u001a\u00020\u0003HÖ\u0081\u0004J\n\u0010\u001b\u001a\u00020\bHÖ\u0081\u0004R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\fR\u0011\u0010\u0005\u001a\u00020\u0006¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000fR\u0013\u0010\u0007\u001a\u0004\u0018\u00010\b¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u0011¨\u0006\u001c"}, d2 = {"Lcom/arflix/tv/util/InProgressSnapshot;", "", "season", "", "episode", "progress", "", "updatedAt", "", "<init>", "(IIFLjava/lang/String;)V", "getSeason", "()I", "getEpisode", "getProgress", "()F", "getUpdatedAt", "()Ljava/lang/String;", "component1", "component2", "component3", "component4", "copy", "equals", "", "other", "hashCode", "toString", "app_playRelease"}, k = 1, mv = {2, 3, 0}, xi = 48)
public final /* data */ class InProgressSnapshot {
    public static final int $stable = 0;
    private final int episode;
    private final float progress;
    private final int season;
    private final String updatedAt;

    public InProgressSnapshot(int i10, int i11, float f10, String str) {
        this.season = i10;
        this.episode = i11;
        this.progress = f10;
        this.updatedAt = str;
    }

    public static /* synthetic */ InProgressSnapshot copy$default(InProgressSnapshot inProgressSnapshot, int i10, int i11, float f10, String str, int i12, Object obj) {
        if ((i12 & 1) != 0) {
            i10 = inProgressSnapshot.season;
        }
        if ((i12 & 2) != 0) {
            i11 = inProgressSnapshot.episode;
        }
        if ((i12 & 4) != 0) {
            f10 = inProgressSnapshot.progress;
        }
        if ((i12 & 8) != 0) {
            str = inProgressSnapshot.updatedAt;
        }
        return inProgressSnapshot.copy(i10, i11, f10, str);
    }

    /* JADX INFO: renamed from: component1, reason: from getter */
    public final int getSeason() {
        return this.season;
    }

    /* JADX INFO: renamed from: component2, reason: from getter */
    public final int getEpisode() {
        return this.episode;
    }

    /* JADX INFO: renamed from: component3, reason: from getter */
    public final float getProgress() {
        return this.progress;
    }

    /* JADX INFO: renamed from: component4, reason: from getter */
    public final String getUpdatedAt() {
        return this.updatedAt;
    }

    public final InProgressSnapshot copy(int season, int episode, float progress, String updatedAt) {
        return new InProgressSnapshot(season, episode, progress, updatedAt);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof InProgressSnapshot)) {
            return false;
        }
        InProgressSnapshot inProgressSnapshot = (InProgressSnapshot) other;
        return this.season == inProgressSnapshot.season && this.episode == inProgressSnapshot.episode && Float.compare(this.progress, inProgressSnapshot.progress) == 0 && p.a(this.updatedAt, inProgressSnapshot.updatedAt);
    }

    public final int getEpisode() {
        return this.episode;
    }

    public final float getProgress() {
        return this.progress;
    }

    public final int getSeason() {
        return this.season;
    }

    public final String getUpdatedAt() {
        return this.updatedAt;
    }

    public int hashCode() {
        int iB = c.b(this.progress, ((this.season * 31) + this.episode) * 31, 31);
        String str = this.updatedAt;
        return iB + (str == null ? 0 : str.hashCode());
    }

    public String toString() {
        int i10 = this.season;
        int i11 = this.episode;
        float f10 = this.progress;
        String str = this.updatedAt;
        StringBuilder sbV = androidx.compose.foundation.c.v("InProgressSnapshot(season=", i10, ", episode=", i11, ", progress=");
        sbV.append(f10);
        sbV.append(", updatedAt=");
        sbV.append(str);
        sbV.append(")");
        return sbV.toString();
    }
}
