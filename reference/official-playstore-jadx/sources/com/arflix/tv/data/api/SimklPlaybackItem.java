package com.arflix.tv.data.api;

import androidx.media3.extractor.text.ttml.TtmlNode;
import com.google.gson.annotations.SerializedName;
import io.ktor.http.LinkHeader;
import kotlin.Metadata;
import kotlin.jvm.internal.p;

/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u0000D\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0010\u0007\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u001c\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\u0087\b\u0018\u00002\u00020\u0001Be\u0012\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\b\b\u0002\u0010\u0004\u001a\u00020\u0005\u0012\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u0007\u0012\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\u0007\u0012\n\b\u0002\u0010\t\u001a\u0004\u0018\u00010\n\u0012\n\b\u0002\u0010\u000b\u001a\u0004\u0018\u00010\f\u0012\n\b\u0002\u0010\r\u001a\u0004\u0018\u00010\f\u0012\n\b\u0002\u0010\u000e\u001a\u0004\u0018\u00010\u000f¢\u0006\u0004\b\u0010\u0010\u0011J\u0010\u0010!\u001a\u0004\u0018\u00010\u0003HÆ\u0003¢\u0006\u0002\u0010\u0013J\t\u0010\"\u001a\u00020\u0005HÆ\u0003J\u000b\u0010#\u001a\u0004\u0018\u00010\u0007HÆ\u0003J\u000b\u0010$\u001a\u0004\u0018\u00010\u0007HÆ\u0003J\u000b\u0010%\u001a\u0004\u0018\u00010\nHÆ\u0003J\u000b\u0010&\u001a\u0004\u0018\u00010\fHÆ\u0003J\u000b\u0010'\u001a\u0004\u0018\u00010\fHÆ\u0003J\u000b\u0010(\u001a\u0004\u0018\u00010\u000fHÆ\u0003Jl\u0010)\u001a\u00020\u00002\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u00072\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\u00072\n\b\u0002\u0010\t\u001a\u0004\u0018\u00010\n2\n\b\u0002\u0010\u000b\u001a\u0004\u0018\u00010\f2\n\b\u0002\u0010\r\u001a\u0004\u0018\u00010\f2\n\b\u0002\u0010\u000e\u001a\u0004\u0018\u00010\u000fHÆ\u0001¢\u0006\u0002\u0010*J\u0014\u0010+\u001a\u00020,2\b\u0010-\u001a\u0004\u0018\u00010\u0001HÖ\u0083\u0004J\n\u0010.\u001a\u00020/HÖ\u0081\u0004J\n\u00100\u001a\u00020\u0007HÖ\u0081\u0004R\u001a\u0010\u0002\u001a\u0004\u0018\u00010\u00038\u0006X\u0087\u0004¢\u0006\n\n\u0002\u0010\u0014\u001a\u0004\b\u0012\u0010\u0013R\u0016\u0010\u0004\u001a\u00020\u00058\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0015\u0010\u0016R\u0018\u0010\u0006\u001a\u0004\u0018\u00010\u00078\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0017\u0010\u0018R\u0018\u0010\b\u001a\u0004\u0018\u00010\u00078\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0019\u0010\u0018R\u0018\u0010\t\u001a\u0004\u0018\u00010\n8\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u001a\u0010\u001bR\u0018\u0010\u000b\u001a\u0004\u0018\u00010\f8\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u001c\u0010\u001dR\u0018\u0010\r\u001a\u0004\u0018\u00010\f8\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u001e\u0010\u001dR\u0018\u0010\u000e\u001a\u0004\u0018\u00010\u000f8\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u001f\u0010 ¨\u00061"}, d2 = {"Lcom/arflix/tv/data/api/SimklPlaybackItem;", "", TtmlNode.ATTR_ID, "", "progress", "", "pausedAt", "", LinkHeader.Parameters.Type, "movie", "Lcom/arflix/tv/data/api/SimklMovieRef;", "show", "Lcom/arflix/tv/data/api/SimklShowRef;", "anime", "episode", "Lcom/arflix/tv/data/api/SimklPlaybackEpisode;", "<init>", "(Ljava/lang/Long;FLjava/lang/String;Ljava/lang/String;Lcom/arflix/tv/data/api/SimklMovieRef;Lcom/arflix/tv/data/api/SimklShowRef;Lcom/arflix/tv/data/api/SimklShowRef;Lcom/arflix/tv/data/api/SimklPlaybackEpisode;)V", "getId", "()Ljava/lang/Long;", "Ljava/lang/Long;", "getProgress", "()F", "getPausedAt", "()Ljava/lang/String;", "getType", "getMovie", "()Lcom/arflix/tv/data/api/SimklMovieRef;", "getShow", "()Lcom/arflix/tv/data/api/SimklShowRef;", "getAnime", "getEpisode", "()Lcom/arflix/tv/data/api/SimklPlaybackEpisode;", "component1", "component2", "component3", "component4", "component5", "component6", "component7", "component8", "copy", "(Ljava/lang/Long;FLjava/lang/String;Ljava/lang/String;Lcom/arflix/tv/data/api/SimklMovieRef;Lcom/arflix/tv/data/api/SimklShowRef;Lcom/arflix/tv/data/api/SimklShowRef;Lcom/arflix/tv/data/api/SimklPlaybackEpisode;)Lcom/arflix/tv/data/api/SimklPlaybackItem;", "equals", "", "other", "hashCode", "", "toString", "app_playRelease"}, k = 1, mv = {2, 3, 0}, xi = 48)
public final /* data */ class SimklPlaybackItem {
    public static final int $stable = 0;

    @SerializedName("anime")
    private final SimklShowRef anime;

    @SerializedName("episode")
    private final SimklPlaybackEpisode episode;

    @SerializedName(TtmlNode.ATTR_ID)
    private final Long id;

    @SerializedName("movie")
    private final SimklMovieRef movie;

    @SerializedName("paused_at")
    private final String pausedAt;

    @SerializedName("progress")
    private final float progress;

    @SerializedName("show")
    private final SimklShowRef show;

    @SerializedName(LinkHeader.Parameters.Type)
    private final String type;

    public SimklPlaybackItem() {
        this(null, 0.0f, null, null, null, null, null, null, 255, null);
    }

    public static /* synthetic */ SimklPlaybackItem copy$default(SimklPlaybackItem simklPlaybackItem, Long l10, float f10, String str, String str2, SimklMovieRef simklMovieRef, SimklShowRef simklShowRef, SimklShowRef simklShowRef2, SimklPlaybackEpisode simklPlaybackEpisode, int i10, Object obj) {
        if ((i10 & 1) != 0) {
            l10 = simklPlaybackItem.id;
        }
        if ((i10 & 2) != 0) {
            f10 = simklPlaybackItem.progress;
        }
        if ((i10 & 4) != 0) {
            str = simklPlaybackItem.pausedAt;
        }
        if ((i10 & 8) != 0) {
            str2 = simklPlaybackItem.type;
        }
        if ((i10 & 16) != 0) {
            simklMovieRef = simklPlaybackItem.movie;
        }
        if ((i10 & 32) != 0) {
            simklShowRef = simklPlaybackItem.show;
        }
        if ((i10 & 64) != 0) {
            simklShowRef2 = simklPlaybackItem.anime;
        }
        if ((i10 & 128) != 0) {
            simklPlaybackEpisode = simklPlaybackItem.episode;
        }
        SimklShowRef simklShowRef3 = simklShowRef2;
        SimklPlaybackEpisode simklPlaybackEpisode2 = simklPlaybackEpisode;
        SimklMovieRef simklMovieRef2 = simklMovieRef;
        SimklShowRef simklShowRef4 = simklShowRef;
        return simklPlaybackItem.copy(l10, f10, str, str2, simklMovieRef2, simklShowRef4, simklShowRef3, simklPlaybackEpisode2);
    }

    /* JADX INFO: renamed from: component1, reason: from getter */
    public final Long getId() {
        return this.id;
    }

    /* JADX INFO: renamed from: component2, reason: from getter */
    public final float getProgress() {
        return this.progress;
    }

    /* JADX INFO: renamed from: component3, reason: from getter */
    public final String getPausedAt() {
        return this.pausedAt;
    }

    /* JADX INFO: renamed from: component4, reason: from getter */
    public final String getType() {
        return this.type;
    }

    /* JADX INFO: renamed from: component5, reason: from getter */
    public final SimklMovieRef getMovie() {
        return this.movie;
    }

    /* JADX INFO: renamed from: component6, reason: from getter */
    public final SimklShowRef getShow() {
        return this.show;
    }

    /* JADX INFO: renamed from: component7, reason: from getter */
    public final SimklShowRef getAnime() {
        return this.anime;
    }

    /* JADX INFO: renamed from: component8, reason: from getter */
    public final SimklPlaybackEpisode getEpisode() {
        return this.episode;
    }

    public final SimklPlaybackItem copy(Long id, float progress, String pausedAt, String type, SimklMovieRef movie, SimklShowRef show, SimklShowRef anime, SimklPlaybackEpisode episode) {
        return new SimklPlaybackItem(id, progress, pausedAt, type, movie, show, anime, episode);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof SimklPlaybackItem)) {
            return false;
        }
        SimklPlaybackItem simklPlaybackItem = (SimklPlaybackItem) other;
        return p.a(this.id, simklPlaybackItem.id) && Float.compare(this.progress, simklPlaybackItem.progress) == 0 && p.a(this.pausedAt, simklPlaybackItem.pausedAt) && p.a(this.type, simklPlaybackItem.type) && p.a(this.movie, simklPlaybackItem.movie) && p.a(this.show, simklPlaybackItem.show) && p.a(this.anime, simklPlaybackItem.anime) && p.a(this.episode, simklPlaybackItem.episode);
    }

    public final SimklShowRef getAnime() {
        return this.anime;
    }

    public final SimklPlaybackEpisode getEpisode() {
        return this.episode;
    }

    public final Long getId() {
        return this.id;
    }

    public final SimklMovieRef getMovie() {
        return this.movie;
    }

    public final String getPausedAt() {
        return this.pausedAt;
    }

    public final float getProgress() {
        return this.progress;
    }

    public final SimklShowRef getShow() {
        return this.show;
    }

    public final String getType() {
        return this.type;
    }

    public int hashCode() {
        Long l10 = this.id;
        int iB = a0.c.b(this.progress, (l10 == null ? 0 : l10.hashCode()) * 31, 31);
        String str = this.pausedAt;
        int iHashCode = (iB + (str == null ? 0 : str.hashCode())) * 31;
        String str2 = this.type;
        int iHashCode2 = (iHashCode + (str2 == null ? 0 : str2.hashCode())) * 31;
        SimklMovieRef simklMovieRef = this.movie;
        int iHashCode3 = (iHashCode2 + (simklMovieRef == null ? 0 : simklMovieRef.hashCode())) * 31;
        SimklShowRef simklShowRef = this.show;
        int iHashCode4 = (iHashCode3 + (simklShowRef == null ? 0 : simklShowRef.hashCode())) * 31;
        SimklShowRef simklShowRef2 = this.anime;
        int iHashCode5 = (iHashCode4 + (simklShowRef2 == null ? 0 : simklShowRef2.hashCode())) * 31;
        SimklPlaybackEpisode simklPlaybackEpisode = this.episode;
        return iHashCode5 + (simklPlaybackEpisode != null ? simklPlaybackEpisode.hashCode() : 0);
    }

    public String toString() {
        Long l10 = this.id;
        float f10 = this.progress;
        String str = this.pausedAt;
        String str2 = this.type;
        SimklMovieRef simklMovieRef = this.movie;
        SimklShowRef simklShowRef = this.show;
        SimklShowRef simklShowRef2 = this.anime;
        SimklPlaybackEpisode simklPlaybackEpisode = this.episode;
        StringBuilder sb2 = new StringBuilder("SimklPlaybackItem(id=");
        sb2.append(l10);
        sb2.append(", progress=");
        sb2.append(f10);
        sb2.append(", pausedAt=");
        y.a.i(sb2, str, ", type=", str2, ", movie=");
        sb2.append(simklMovieRef);
        sb2.append(", show=");
        sb2.append(simklShowRef);
        sb2.append(", anime=");
        sb2.append(simklShowRef2);
        sb2.append(", episode=");
        sb2.append(simklPlaybackEpisode);
        sb2.append(")");
        return sb2.toString();
    }

    public SimklPlaybackItem(Long l10, float f10, String str, String str2, SimklMovieRef simklMovieRef, SimklShowRef simklShowRef, SimklShowRef simklShowRef2, SimklPlaybackEpisode simklPlaybackEpisode) {
        this.id = l10;
        this.progress = f10;
        this.pausedAt = str;
        this.type = str2;
        this.movie = simklMovieRef;
        this.show = simklShowRef;
        this.anime = simklShowRef2;
        this.episode = simklPlaybackEpisode;
    }

    public /* synthetic */ SimklPlaybackItem(Long l10, float f10, String str, String str2, SimklMovieRef simklMovieRef, SimklShowRef simklShowRef, SimklShowRef simklShowRef2, SimklPlaybackEpisode simklPlaybackEpisode, int i10, kotlin.jvm.internal.h hVar) {
        this((i10 & 1) != 0 ? null : l10, (i10 & 2) != 0 ? 0.0f : f10, (i10 & 4) != 0 ? null : str, (i10 & 8) != 0 ? null : str2, (i10 & 16) != 0 ? null : simklMovieRef, (i10 & 32) != 0 ? null : simklShowRef, (i10 & 64) != 0 ? null : simklShowRef2, (i10 & 128) != 0 ? null : simklPlaybackEpisode);
    }
}
