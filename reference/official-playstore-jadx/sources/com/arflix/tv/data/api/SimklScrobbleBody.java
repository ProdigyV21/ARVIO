package com.arflix.tv.data.api;

import com.google.gson.annotations.SerializedName;
import kotlin.Metadata;
import kotlin.jvm.internal.p;

/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u0000:\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0007\n\u0002\b\u0012\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0087\b\u0018\u00002\u00020\u0001B?\u0012\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0005\u0012\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u0005\u0012\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\b\u0012\u0006\u0010\t\u001a\u00020\n¢\u0006\u0004\b\u000b\u0010\fJ\u000b\u0010\u0016\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u0010\u0017\u001a\u0004\u0018\u00010\u0005HÆ\u0003J\u000b\u0010\u0018\u001a\u0004\u0018\u00010\u0005HÆ\u0003J\u000b\u0010\u0019\u001a\u0004\u0018\u00010\bHÆ\u0003J\t\u0010\u001a\u001a\u00020\nHÆ\u0003JC\u0010\u001b\u001a\u00020\u00002\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u00052\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u00052\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\b2\b\b\u0002\u0010\t\u001a\u00020\nHÆ\u0001J\u0014\u0010\u001c\u001a\u00020\u001d2\b\u0010\u001e\u001a\u0004\u0018\u00010\u0001HÖ\u0083\u0004J\n\u0010\u001f\u001a\u00020 HÖ\u0081\u0004J\n\u0010!\u001a\u00020\"HÖ\u0081\u0004R\u0018\u0010\u0002\u001a\u0004\u0018\u00010\u00038\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000eR\u0018\u0010\u0004\u001a\u0004\u0018\u00010\u00058\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u0010R\u0018\u0010\u0006\u001a\u0004\u0018\u00010\u00058\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u0010R\u0018\u0010\u0007\u001a\u0004\u0018\u00010\b8\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u0013R\u0016\u0010\t\u001a\u00020\n8\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\u0015¨\u0006#"}, d2 = {"Lcom/arflix/tv/data/api/SimklScrobbleBody;", "", "movie", "Lcom/arflix/tv/data/api/SimklMovieRef;", "show", "Lcom/arflix/tv/data/api/SimklShowRef;", "anime", "episode", "Lcom/arflix/tv/data/api/SimklEpisodeRef;", "progress", "", "<init>", "(Lcom/arflix/tv/data/api/SimklMovieRef;Lcom/arflix/tv/data/api/SimklShowRef;Lcom/arflix/tv/data/api/SimklShowRef;Lcom/arflix/tv/data/api/SimklEpisodeRef;F)V", "getMovie", "()Lcom/arflix/tv/data/api/SimklMovieRef;", "getShow", "()Lcom/arflix/tv/data/api/SimklShowRef;", "getAnime", "getEpisode", "()Lcom/arflix/tv/data/api/SimklEpisodeRef;", "getProgress", "()F", "component1", "component2", "component3", "component4", "component5", "copy", "equals", "", "other", "hashCode", "", "toString", "", "app_playRelease"}, k = 1, mv = {2, 3, 0}, xi = 48)
public final /* data */ class SimklScrobbleBody {
    public static final int $stable = 0;

    @SerializedName("anime")
    private final SimklShowRef anime;

    @SerializedName("episode")
    private final SimklEpisodeRef episode;

    @SerializedName("movie")
    private final SimklMovieRef movie;

    @SerializedName("progress")
    private final float progress;

    @SerializedName("show")
    private final SimklShowRef show;

    public SimklScrobbleBody(SimklMovieRef simklMovieRef, SimklShowRef simklShowRef, SimklShowRef simklShowRef2, SimklEpisodeRef simklEpisodeRef, float f10) {
        this.movie = simklMovieRef;
        this.show = simklShowRef;
        this.anime = simklShowRef2;
        this.episode = simklEpisodeRef;
        this.progress = f10;
    }

    public static /* synthetic */ SimklScrobbleBody copy$default(SimklScrobbleBody simklScrobbleBody, SimklMovieRef simklMovieRef, SimklShowRef simklShowRef, SimklShowRef simklShowRef2, SimklEpisodeRef simklEpisodeRef, float f10, int i10, Object obj) {
        if ((i10 & 1) != 0) {
            simklMovieRef = simklScrobbleBody.movie;
        }
        if ((i10 & 2) != 0) {
            simklShowRef = simklScrobbleBody.show;
        }
        if ((i10 & 4) != 0) {
            simklShowRef2 = simklScrobbleBody.anime;
        }
        if ((i10 & 8) != 0) {
            simklEpisodeRef = simklScrobbleBody.episode;
        }
        if ((i10 & 16) != 0) {
            f10 = simklScrobbleBody.progress;
        }
        float f11 = f10;
        SimklShowRef simklShowRef3 = simklShowRef2;
        return simklScrobbleBody.copy(simklMovieRef, simklShowRef, simklShowRef3, simklEpisodeRef, f11);
    }

    /* JADX INFO: renamed from: component1, reason: from getter */
    public final SimklMovieRef getMovie() {
        return this.movie;
    }

    /* JADX INFO: renamed from: component2, reason: from getter */
    public final SimklShowRef getShow() {
        return this.show;
    }

    /* JADX INFO: renamed from: component3, reason: from getter */
    public final SimklShowRef getAnime() {
        return this.anime;
    }

    /* JADX INFO: renamed from: component4, reason: from getter */
    public final SimklEpisodeRef getEpisode() {
        return this.episode;
    }

    /* JADX INFO: renamed from: component5, reason: from getter */
    public final float getProgress() {
        return this.progress;
    }

    public final SimklScrobbleBody copy(SimklMovieRef movie, SimklShowRef show, SimklShowRef anime, SimklEpisodeRef episode, float progress) {
        return new SimklScrobbleBody(movie, show, anime, episode, progress);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof SimklScrobbleBody)) {
            return false;
        }
        SimklScrobbleBody simklScrobbleBody = (SimklScrobbleBody) other;
        return p.a(this.movie, simklScrobbleBody.movie) && p.a(this.show, simklScrobbleBody.show) && p.a(this.anime, simklScrobbleBody.anime) && p.a(this.episode, simklScrobbleBody.episode) && Float.compare(this.progress, simklScrobbleBody.progress) == 0;
    }

    public final SimklShowRef getAnime() {
        return this.anime;
    }

    public final SimklEpisodeRef getEpisode() {
        return this.episode;
    }

    public final SimklMovieRef getMovie() {
        return this.movie;
    }

    public final float getProgress() {
        return this.progress;
    }

    public final SimklShowRef getShow() {
        return this.show;
    }

    public int hashCode() {
        SimklMovieRef simklMovieRef = this.movie;
        int iHashCode = (simklMovieRef == null ? 0 : simklMovieRef.hashCode()) * 31;
        SimklShowRef simklShowRef = this.show;
        int iHashCode2 = (iHashCode + (simklShowRef == null ? 0 : simklShowRef.hashCode())) * 31;
        SimklShowRef simklShowRef2 = this.anime;
        int iHashCode3 = (iHashCode2 + (simklShowRef2 == null ? 0 : simklShowRef2.hashCode())) * 31;
        SimklEpisodeRef simklEpisodeRef = this.episode;
        return Float.floatToIntBits(this.progress) + ((iHashCode3 + (simklEpisodeRef != null ? simklEpisodeRef.hashCode() : 0)) * 31);
    }

    public String toString() {
        return "SimklScrobbleBody(movie=" + this.movie + ", show=" + this.show + ", anime=" + this.anime + ", episode=" + this.episode + ", progress=" + this.progress + ")";
    }

    /* JADX WARN: Illegal instructions before constructor call */
    public /* synthetic */ SimklScrobbleBody(SimklMovieRef simklMovieRef, SimklShowRef simklShowRef, SimklShowRef simklShowRef2, SimklEpisodeRef simklEpisodeRef, float f10, int i10, kotlin.jvm.internal.h hVar) {
        float f11;
        SimklEpisodeRef simklEpisodeRef2;
        simklMovieRef = (i10 & 1) != 0 ? null : simklMovieRef;
        simklShowRef = (i10 & 2) != 0 ? null : simklShowRef;
        simklShowRef2 = (i10 & 4) != 0 ? null : simklShowRef2;
        if ((i10 & 8) != 0) {
            f11 = f10;
            simklEpisodeRef2 = null;
        } else {
            f11 = f10;
            simklEpisodeRef2 = simklEpisodeRef;
        }
        this(simklMovieRef, simklShowRef, simklShowRef2, simklEpisodeRef2, f11);
    }
}
