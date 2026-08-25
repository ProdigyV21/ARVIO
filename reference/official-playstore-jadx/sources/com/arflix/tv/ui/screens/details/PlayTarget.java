package com.arflix.tv.ui.screens.details;

import kotlin.Metadata;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\t\n\u0002\b\u0012\n\u0002\u0010\u000b\n\u0002\b\u0004\b\u0082\b\u0018\u00002\u00020\u0001B3\u0012\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u0012\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\b¢\u0006\u0004\b\t\u0010\nJ\u0010\u0010\u0014\u001a\u0004\u0018\u00010\u0003HÆ\u0003¢\u0006\u0002\u0010\fJ\u0010\u0010\u0015\u001a\u0004\u0018\u00010\u0003HÆ\u0003¢\u0006\u0002\u0010\fJ\t\u0010\u0016\u001a\u00020\u0006HÆ\u0003J\u0010\u0010\u0017\u001a\u0004\u0018\u00010\bHÆ\u0003¢\u0006\u0002\u0010\u0012J<\u0010\u0018\u001a\u00020\u00002\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u00062\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\bHÆ\u0001¢\u0006\u0002\u0010\u0019J\u0014\u0010\u001a\u001a\u00020\u001b2\b\u0010\u001c\u001a\u0004\u0018\u00010\u0001HÖ\u0083\u0004J\n\u0010\u001d\u001a\u00020\u0003HÖ\u0081\u0004J\n\u0010\u001e\u001a\u00020\u0006HÖ\u0081\u0004R\u0015\u0010\u0002\u001a\u0004\u0018\u00010\u0003¢\u0006\n\n\u0002\u0010\r\u001a\u0004\b\u000b\u0010\fR\u0015\u0010\u0004\u001a\u0004\u0018\u00010\u0003¢\u0006\n\n\u0002\u0010\r\u001a\u0004\b\u000e\u0010\fR\u0011\u0010\u0005\u001a\u00020\u0006¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u0010R\u0015\u0010\u0007\u001a\u0004\u0018\u00010\b¢\u0006\n\n\u0002\u0010\u0013\u001a\u0004\b\u0011\u0010\u0012¨\u0006\u001f"}, d2 = {"Lcom/arflix/tv/ui/screens/details/PlayTarget;", "", "season", "", "episode", "label", "", "positionMs", "", "<init>", "(Ljava/lang/Integer;Ljava/lang/Integer;Ljava/lang/String;Ljava/lang/Long;)V", "getSeason", "()Ljava/lang/Integer;", "Ljava/lang/Integer;", "getEpisode", "getLabel", "()Ljava/lang/String;", "getPositionMs", "()Ljava/lang/Long;", "Ljava/lang/Long;", "component1", "component2", "component3", "component4", "copy", "(Ljava/lang/Integer;Ljava/lang/Integer;Ljava/lang/String;Ljava/lang/Long;)Lcom/arflix/tv/ui/screens/details/PlayTarget;", "equals", "", "other", "hashCode", "toString", "app_playRelease"}, k = 1, mv = {2, 3, 0}, xi = 48)
final /* data */ class PlayTarget {
    private final Integer episode;
    private final String label;
    private final Long positionMs;
    private final Integer season;

    public PlayTarget(Integer num, Integer num2, String str, Long l10) {
        this.season = num;
        this.episode = num2;
        this.label = str;
        this.positionMs = l10;
    }

    public static /* synthetic */ PlayTarget copy$default(PlayTarget playTarget, Integer num, Integer num2, String str, Long l10, int i10, Object obj) {
        if ((i10 & 1) != 0) {
            num = playTarget.season;
        }
        if ((i10 & 2) != 0) {
            num2 = playTarget.episode;
        }
        if ((i10 & 4) != 0) {
            str = playTarget.label;
        }
        if ((i10 & 8) != 0) {
            l10 = playTarget.positionMs;
        }
        return playTarget.copy(num, num2, str, l10);
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
    public final Long getPositionMs() {
        return this.positionMs;
    }

    public final PlayTarget copy(Integer season, Integer episode, String label, Long positionMs) {
        return new PlayTarget(season, episode, label, positionMs);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof PlayTarget)) {
            return false;
        }
        PlayTarget playTarget = (PlayTarget) other;
        return kotlin.jvm.internal.p.a(this.season, playTarget.season) && kotlin.jvm.internal.p.a(this.episode, playTarget.episode) && kotlin.jvm.internal.p.a(this.label, playTarget.label) && kotlin.jvm.internal.p.a(this.positionMs, playTarget.positionMs);
    }

    public final Integer getEpisode() {
        return this.episode;
    }

    public final String getLabel() {
        return this.label;
    }

    public final Long getPositionMs() {
        return this.positionMs;
    }

    public final Integer getSeason() {
        return this.season;
    }

    public int hashCode() {
        Integer num = this.season;
        int iHashCode = (num == null ? 0 : num.hashCode()) * 31;
        Integer num2 = this.episode;
        int iC = androidx.compose.foundation.c.c((iHashCode + (num2 == null ? 0 : num2.hashCode())) * 31, 31, this.label);
        Long l10 = this.positionMs;
        return iC + (l10 != null ? l10.hashCode() : 0);
    }

    public String toString() {
        return "PlayTarget(season=" + this.season + ", episode=" + this.episode + ", label=" + this.label + ", positionMs=" + this.positionMs + ")";
    }

    public /* synthetic */ PlayTarget(Integer num, Integer num2, String str, Long l10, int i10, kotlin.jvm.internal.h hVar) {
        this((i10 & 1) != 0 ? null : num, (i10 & 2) != 0 ? null : num2, str, (i10 & 8) != 0 ? null : l10);
    }
}
