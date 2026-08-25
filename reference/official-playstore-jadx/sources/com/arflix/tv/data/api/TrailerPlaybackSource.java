package com.arflix.tv.data.api;

import androidx.fragment.app.a2;
import kotlin.Metadata;
import kotlin.jvm.internal.p;

/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\n\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\u0087\b\u0018\u00002\u00020\u0001B\u001b\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0003¢\u0006\u0004\b\u0005\u0010\u0006J\t\u0010\n\u001a\u00020\u0003HÆ\u0003J\u000b\u0010\u000b\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u001f\u0010\f\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0003HÆ\u0001J\u0014\u0010\r\u001a\u00020\u000e2\b\u0010\u000f\u001a\u0004\u0018\u00010\u0001HÖ\u0083\u0004J\n\u0010\u0010\u001a\u00020\u0011HÖ\u0081\u0004J\n\u0010\u0012\u001a\u00020\u0003HÖ\u0081\u0004R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u0013\u0010\u0004\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\b¨\u0006\u0013"}, d2 = {"Lcom/arflix/tv/data/api/TrailerPlaybackSource;", "", "videoUrl", "", "audioUrl", "<init>", "(Ljava/lang/String;Ljava/lang/String;)V", "getVideoUrl", "()Ljava/lang/String;", "getAudioUrl", "component1", "component2", "copy", "equals", "", "other", "hashCode", "", "toString", "app_playRelease"}, k = 1, mv = {2, 3, 0}, xi = 48)
public final /* data */ class TrailerPlaybackSource {
    public static final int $stable = 0;
    private final String audioUrl;
    private final String videoUrl;

    public TrailerPlaybackSource(String str, String str2) {
        this.videoUrl = str;
        this.audioUrl = str2;
    }

    public static /* synthetic */ TrailerPlaybackSource copy$default(TrailerPlaybackSource trailerPlaybackSource, String str, String str2, int i10, Object obj) {
        if ((i10 & 1) != 0) {
            str = trailerPlaybackSource.videoUrl;
        }
        if ((i10 & 2) != 0) {
            str2 = trailerPlaybackSource.audioUrl;
        }
        return trailerPlaybackSource.copy(str, str2);
    }

    /* JADX INFO: renamed from: component1, reason: from getter */
    public final String getVideoUrl() {
        return this.videoUrl;
    }

    /* JADX INFO: renamed from: component2, reason: from getter */
    public final String getAudioUrl() {
        return this.audioUrl;
    }

    public final TrailerPlaybackSource copy(String videoUrl, String audioUrl) {
        return new TrailerPlaybackSource(videoUrl, audioUrl);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof TrailerPlaybackSource)) {
            return false;
        }
        TrailerPlaybackSource trailerPlaybackSource = (TrailerPlaybackSource) other;
        return p.a(this.videoUrl, trailerPlaybackSource.videoUrl) && p.a(this.audioUrl, trailerPlaybackSource.audioUrl);
    }

    public final String getAudioUrl() {
        return this.audioUrl;
    }

    public final String getVideoUrl() {
        return this.videoUrl;
    }

    public int hashCode() {
        int iHashCode = this.videoUrl.hashCode() * 31;
        String str = this.audioUrl;
        return iHashCode + (str == null ? 0 : str.hashCode());
    }

    public String toString() {
        return a2.m("TrailerPlaybackSource(videoUrl=", this.videoUrl, ", audioUrl=", this.audioUrl, ")");
    }

    public /* synthetic */ TrailerPlaybackSource(String str, String str2, int i10, kotlin.jvm.internal.h hVar) {
        this(str, (i10 & 2) != 0 ? null : str2);
    }
}
