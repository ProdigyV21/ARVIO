package com.arflix.tv.ui.screens.details;

import kotlin.Metadata;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\t\n\u0002\b\u0011\n\u0002\u0010\u000b\n\u0002\b\u0004\b\u0082\b\u0018\u00002\u00020\u0001B/\u0012\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u0012\u0006\u0010\u0007\u001a\u00020\b¢\u0006\u0004\b\t\u0010\nJ\u0010\u0010\u0013\u001a\u0004\u0018\u00010\u0003HÆ\u0003¢\u0006\u0002\u0010\fJ\u0010\u0010\u0014\u001a\u0004\u0018\u00010\u0003HÆ\u0003¢\u0006\u0002\u0010\fJ\t\u0010\u0015\u001a\u00020\u0006HÆ\u0003J\t\u0010\u0016\u001a\u00020\bHÆ\u0003J:\u0010\u0017\u001a\u00020\u00002\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u00062\b\b\u0002\u0010\u0007\u001a\u00020\bHÆ\u0001¢\u0006\u0002\u0010\u0018J\u0014\u0010\u0019\u001a\u00020\u001a2\b\u0010\u001b\u001a\u0004\u0018\u00010\u0001HÖ\u0083\u0004J\n\u0010\u001c\u001a\u00020\u0003HÖ\u0081\u0004J\n\u0010\u001d\u001a\u00020\u0006HÖ\u0081\u0004R\u0015\u0010\u0002\u001a\u0004\u0018\u00010\u0003¢\u0006\n\n\u0002\u0010\r\u001a\u0004\b\u000b\u0010\fR\u0015\u0010\u0004\u001a\u0004\u0018\u00010\u0003¢\u0006\n\n\u0002\u0010\r\u001a\u0004\b\u000e\u0010\fR\u0011\u0010\u0005\u001a\u00020\u0006¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u0010R\u0011\u0010\u0007\u001a\u00020\b¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u0012¨\u0006\u001e"}, d2 = {"Lcom/arflix/tv/ui/screens/details/ResumeInfo;", "", "season", "", "episode", "label", "", "positionMs", "", "<init>", "(Ljava/lang/Integer;Ljava/lang/Integer;Ljava/lang/String;J)V", "getSeason", "()Ljava/lang/Integer;", "Ljava/lang/Integer;", "getEpisode", "getLabel", "()Ljava/lang/String;", "getPositionMs", "()J", "component1", "component2", "component3", "component4", "copy", "(Ljava/lang/Integer;Ljava/lang/Integer;Ljava/lang/String;J)Lcom/arflix/tv/ui/screens/details/ResumeInfo;", "equals", "", "other", "hashCode", "toString", "app_playRelease"}, k = 1, mv = {2, 3, 0}, xi = 48)
final /* data */ class ResumeInfo {
    private final Integer episode;
    private final String label;
    private final long positionMs;
    private final Integer season;

    public ResumeInfo(Integer num, Integer num2, String str, long j10) {
        this.season = num;
        this.episode = num2;
        this.label = str;
        this.positionMs = j10;
    }

    public static /* synthetic */ ResumeInfo copy$default(ResumeInfo resumeInfo, Integer num, Integer num2, String str, long j10, int i10, Object obj) {
        if ((i10 & 1) != 0) {
            num = resumeInfo.season;
        }
        if ((i10 & 2) != 0) {
            num2 = resumeInfo.episode;
        }
        if ((i10 & 4) != 0) {
            str = resumeInfo.label;
        }
        if ((i10 & 8) != 0) {
            j10 = resumeInfo.positionMs;
        }
        String str2 = str;
        return resumeInfo.copy(num, num2, str2, j10);
    }

    /* JADX INFO: renamed from: component1, reason: from getter */
    public final Integer getSeason() {
        return this.season;
    }

    /* JADX INFO: renamed from: component2, reason: from getter */
    public final Integer getEpisode() {
        return this.episode;
    }

    /* JADX INFO: renamed from: component3, reason: from getter */
    public final String getLabel() {
        return this.label;
    }

    /* JADX INFO: renamed from: component4, reason: from getter */
    public final long getPositionMs() {
        return this.positionMs;
    }

    public final ResumeInfo copy(Integer season, Integer episode, String label, long positionMs) {
        return new ResumeInfo(season, episode, label, positionMs);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof ResumeInfo)) {
            return false;
        }
        ResumeInfo resumeInfo = (ResumeInfo) other;
        return kotlin.jvm.internal.p.a(this.season, resumeInfo.season) && kotlin.jvm.internal.p.a(this.episode, resumeInfo.episode) && kotlin.jvm.internal.p.a(this.label, resumeInfo.label) && this.positionMs == resumeInfo.positionMs;
    }

    public final Integer getEpisode() {
        return this.episode;
    }

    public final String getLabel() {
        return this.label;
    }

    public final long getPositionMs() {
        return this.positionMs;
    }

    public final Integer getSeason() {
        return this.season;
    }

    public int hashCode() {
        Integer num = this.season;
        int iHashCode = (num == null ? 0 : num.hashCode()) * 31;
        Integer num2 = this.episode;
        int iC = androidx.compose.foundation.c.c((iHashCode + (num2 != null ? num2.hashCode() : 0)) * 31, 31, this.label);
        long j10 = this.positionMs;
        return iC + ((int) (j10 ^ (j10 >>> 32)));
    }

    public String toString() {
        return "ResumeInfo(season=" + this.season + ", episode=" + this.episode + ", label=" + this.label + ", positionMs=" + this.positionMs + ")";
    }

    /* JADX WARN: Illegal instructions before constructor call */
    public /* synthetic */ ResumeInfo(Integer num, Integer num2, String str, long j10, int i10, kotlin.jvm.internal.h hVar) {
        long j11;
        String str2;
        Integer num3;
        num = (i10 & 1) != 0 ? null : num;
        if ((i10 & 2) != 0) {
            j11 = j10;
            str2 = str;
            num3 = null;
        } else {
            j11 = j10;
            str2 = str;
            num3 = num2;
        }
        this(num, num3, str2, j11);
    }
}
