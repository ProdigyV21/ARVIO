package com.arflix.tv.data.api;

import kotlin.Metadata;
import kotlin.jvm.internal.p;

/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u00008\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0007\n\u0002\b\u0010\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0087\b\u0018\u00002\u00020\u0001B3\u0012\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0005\u0012\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u0007\u0012\u0006\u0010\b\u001a\u00020\t¢\u0006\u0004\b\n\u0010\u000bJ\u000b\u0010\u0014\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u0010\u0015\u001a\u0004\u0018\u00010\u0005HÆ\u0003J\u000b\u0010\u0016\u001a\u0004\u0018\u00010\u0007HÆ\u0003J\t\u0010\u0017\u001a\u00020\tHÆ\u0003J7\u0010\u0018\u001a\u00020\u00002\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u00052\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u00072\b\b\u0002\u0010\b\u001a\u00020\tHÆ\u0001J\u0014\u0010\u0019\u001a\u00020\u001a2\b\u0010\u001b\u001a\u0004\u0018\u00010\u0001HÖ\u0083\u0004J\n\u0010\u001c\u001a\u00020\u001dHÖ\u0081\u0004J\n\u0010\u001e\u001a\u00020\u001fHÖ\u0081\u0004R\u0013\u0010\u0002\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\rR\u0013\u0010\u0004\u001a\u0004\u0018\u00010\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000fR\u0013\u0010\u0006\u001a\u0004\u0018\u00010\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u0011R\u0011\u0010\b\u001a\u00020\t¢\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u0013¨\u0006 "}, d2 = {"Lcom/arflix/tv/data/api/TraktScrobbleBody;", "", "movie", "Lcom/arflix/tv/data/api/TraktMovieId;", "episode", "Lcom/arflix/tv/data/api/TraktEpisodeId;", "show", "Lcom/arflix/tv/data/api/TraktShowId;", "progress", "", "<init>", "(Lcom/arflix/tv/data/api/TraktMovieId;Lcom/arflix/tv/data/api/TraktEpisodeId;Lcom/arflix/tv/data/api/TraktShowId;F)V", "getMovie", "()Lcom/arflix/tv/data/api/TraktMovieId;", "getEpisode", "()Lcom/arflix/tv/data/api/TraktEpisodeId;", "getShow", "()Lcom/arflix/tv/data/api/TraktShowId;", "getProgress", "()F", "component1", "component2", "component3", "component4", "copy", "equals", "", "other", "hashCode", "", "toString", "", "app_playRelease"}, k = 1, mv = {2, 3, 0}, xi = 48)
public final /* data */ class TraktScrobbleBody {
    public static final int $stable = 0;
    private final TraktEpisodeId episode;
    private final TraktMovieId movie;
    private final float progress;
    private final TraktShowId show;

    public TraktScrobbleBody(TraktMovieId traktMovieId, TraktEpisodeId traktEpisodeId, TraktShowId traktShowId, float f10) {
        this.movie = traktMovieId;
        this.episode = traktEpisodeId;
        this.show = traktShowId;
        this.progress = f10;
    }

    public static /* synthetic */ TraktScrobbleBody copy$default(TraktScrobbleBody traktScrobbleBody, TraktMovieId traktMovieId, TraktEpisodeId traktEpisodeId, TraktShowId traktShowId, float f10, int i10, Object obj) {
        if ((i10 & 1) != 0) {
            traktMovieId = traktScrobbleBody.movie;
        }
        if ((i10 & 2) != 0) {
            traktEpisodeId = traktScrobbleBody.episode;
        }
        if ((i10 & 4) != 0) {
            traktShowId = traktScrobbleBody.show;
        }
        if ((i10 & 8) != 0) {
            f10 = traktScrobbleBody.progress;
        }
        return traktScrobbleBody.copy(traktMovieId, traktEpisodeId, traktShowId, f10);
    }

    /* JADX INFO: renamed from: component1, reason: from getter */
    public final TraktMovieId getMovie() {
        return this.movie;
    }

    /* JADX INFO: renamed from: component2, reason: from getter */
    public final TraktEpisodeId getEpisode() {
        return this.episode;
    }

    /* JADX INFO: renamed from: component3, reason: from getter */
    public final TraktShowId getShow() {
        return this.show;
    }

    /* JADX INFO: renamed from: component4, reason: from getter */
    public final float getProgress() {
        return this.progress;
    }

    public final TraktScrobbleBody copy(TraktMovieId movie, TraktEpisodeId episode, TraktShowId show, float progress) {
        return new TraktScrobbleBody(movie, episode, show, progress);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof TraktScrobbleBody)) {
            return false;
        }
        TraktScrobbleBody traktScrobbleBody = (TraktScrobbleBody) other;
        return p.a(this.movie, traktScrobbleBody.movie) && p.a(this.episode, traktScrobbleBody.episode) && p.a(this.show, traktScrobbleBody.show) && Float.compare(this.progress, traktScrobbleBody.progress) == 0;
    }

    public final TraktEpisodeId getEpisode() {
        return this.episode;
    }

    public final TraktMovieId getMovie() {
        return this.movie;
    }

    public final float getProgress() {
        return this.progress;
    }

    public final TraktShowId getShow() {
        return this.show;
    }

    public int hashCode() {
        TraktMovieId traktMovieId = this.movie;
        int iHashCode = (traktMovieId == null ? 0 : traktMovieId.hashCode()) * 31;
        TraktEpisodeId traktEpisodeId = this.episode;
        int iHashCode2 = (iHashCode + (traktEpisodeId == null ? 0 : traktEpisodeId.hashCode())) * 31;
        TraktShowId traktShowId = this.show;
        return Float.floatToIntBits(this.progress) + ((iHashCode2 + (traktShowId != null ? traktShowId.hashCode() : 0)) * 31);
    }

    public String toString() {
        return "TraktScrobbleBody(movie=" + this.movie + ", episode=" + this.episode + ", show=" + this.show + ", progress=" + this.progress + ")";
    }

    public /* synthetic */ TraktScrobbleBody(TraktMovieId traktMovieId, TraktEpisodeId traktEpisodeId, TraktShowId traktShowId, float f10, int i10, kotlin.jvm.internal.h hVar) {
        this((i10 & 1) != 0 ? null : traktMovieId, (i10 & 2) != 0 ? null : traktEpisodeId, (i10 & 4) != 0 ? null : traktShowId, f10);
    }
}
