package com.arflix.tv.data.repository;

import androidx.fragment.app.a2;
import kotlin.Metadata;

/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u000e\n\u0002\u0010\b\n\u0002\b\u0002\b\u0081\b\u0018\u00002\u00020\u0001B\u001f\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0006¢\u0006\u0004\b\u0007\u0010\bJ\t\u0010\u000e\u001a\u00020\u0003HÆ\u0003J\t\u0010\u000f\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0010\u001a\u00020\u0006HÆ\u0003J'\u0010\u0011\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u0006HÆ\u0001J\u0014\u0010\u0012\u001a\u00020\u00062\b\u0010\u0013\u001a\u0004\u0018\u00010\u0001HÖ\u0083\u0004J\n\u0010\u0014\u001a\u00020\u0015HÖ\u0081\u0004J\n\u0010\u0016\u001a\u00020\u0003HÖ\u0081\u0004R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\nR\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\nR\u0011\u0010\u0005\u001a\u00020\u0006¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\r¨\u0006\u0017"}, d2 = {"Lcom/arflix/tv/data/repository/EpisodeIdCandidate;", "", "contentId", "", "label", "preferAnimePath", "", "<init>", "(Ljava/lang/String;Ljava/lang/String;Z)V", "getContentId", "()Ljava/lang/String;", "getLabel", "getPreferAnimePath", "()Z", "component1", "component2", "component3", "copy", "equals", "other", "hashCode", "", "toString", "app_playRelease"}, k = 1, mv = {2, 3, 0}, xi = 48)
public final /* data */ class EpisodeIdCandidate {
    public static final int $stable = 0;
    private final String contentId;
    private final String label;
    private final boolean preferAnimePath;

    public EpisodeIdCandidate(String str, String str2, boolean z) {
        this.contentId = str;
        this.label = str2;
        this.preferAnimePath = z;
    }

    public static /* synthetic */ EpisodeIdCandidate copy$default(EpisodeIdCandidate episodeIdCandidate, String str, String str2, boolean z, int i10, Object obj) {
        if ((i10 & 1) != 0) {
            str = episodeIdCandidate.contentId;
        }
        if ((i10 & 2) != 0) {
            str2 = episodeIdCandidate.label;
        }
        if ((i10 & 4) != 0) {
            z = episodeIdCandidate.preferAnimePath;
        }
        return episodeIdCandidate.copy(str, str2, z);
    }

    /* JADX INFO: renamed from: component1, reason: from getter */
    public final String getContentId() {
        return this.contentId;
    }

    /* JADX INFO: renamed from: component2, reason: from getter */
    public final String getLabel() {
        return this.label;
    }

    /* JADX INFO: renamed from: component3, reason: from getter */
    public final boolean getPreferAnimePath() {
        return this.preferAnimePath;
    }

    public final EpisodeIdCandidate copy(String contentId, String label, boolean preferAnimePath) {
        return new EpisodeIdCandidate(contentId, label, preferAnimePath);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof EpisodeIdCandidate)) {
            return false;
        }
        EpisodeIdCandidate episodeIdCandidate = (EpisodeIdCandidate) other;
        return kotlin.jvm.internal.p.a(this.contentId, episodeIdCandidate.contentId) && kotlin.jvm.internal.p.a(this.label, episodeIdCandidate.label) && this.preferAnimePath == episodeIdCandidate.preferAnimePath;
    }

    public final String getContentId() {
        return this.contentId;
    }

    public final String getLabel() {
        return this.label;
    }

    public final boolean getPreferAnimePath() {
        return this.preferAnimePath;
    }

    public int hashCode() {
        return androidx.compose.foundation.c.c(this.contentId.hashCode() * 31, 31, this.label) + (this.preferAnimePath ? 1231 : 1237);
    }

    public String toString() {
        return a0.c.m(")", this.preferAnimePath, a2.r("EpisodeIdCandidate(contentId=", this.contentId, ", label=", this.label, ", preferAnimePath="));
    }
}
