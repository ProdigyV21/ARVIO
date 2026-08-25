package com.arflix.tv.data.api;

import androidx.media3.extractor.text.ttml.TtmlNode;
import kotlin.Metadata;
import kotlin.jvm.internal.p;

/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u0000@\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0007\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0016\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\u0087\b\u0018\u00002\u00020\u0001B=\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\b\u0010\b\u001a\u0004\u0018\u00010\t\u0012\b\u0010\n\u001a\u0004\u0018\u00010\u000b\u0012\b\u0010\f\u001a\u0004\u0018\u00010\r¢\u0006\u0004\b\u000e\u0010\u000fJ\t\u0010\u001c\u001a\u00020\u0003HÆ\u0003J\t\u0010\u001d\u001a\u00020\u0005HÆ\u0003J\t\u0010\u001e\u001a\u00020\u0007HÆ\u0003J\u000b\u0010\u001f\u001a\u0004\u0018\u00010\tHÆ\u0003J\u000b\u0010 \u001a\u0004\u0018\u00010\u000bHÆ\u0003J\u000b\u0010!\u001a\u0004\u0018\u00010\rHÆ\u0003JK\u0010\"\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00072\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\t2\n\b\u0002\u0010\n\u001a\u0004\u0018\u00010\u000b2\n\b\u0002\u0010\f\u001a\u0004\u0018\u00010\rHÆ\u0001J\u0014\u0010#\u001a\u00020$2\b\u0010%\u001a\u0004\u0018\u00010\u0001HÖ\u0083\u0004J\n\u0010&\u001a\u00020'HÖ\u0081\u0004J\n\u0010(\u001a\u00020\u0005HÖ\u0081\u0004R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u0011R\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u0013R\u0011\u0010\u0006\u001a\u00020\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\u0015R\u0013\u0010\b\u001a\u0004\u0018\u00010\t¢\u0006\b\n\u0000\u001a\u0004\b\u0016\u0010\u0017R\u0013\u0010\n\u001a\u0004\u0018\u00010\u000b¢\u0006\b\n\u0000\u001a\u0004\b\u0018\u0010\u0019R\u0013\u0010\f\u001a\u0004\u0018\u00010\r¢\u0006\b\n\u0000\u001a\u0004\b\u001a\u0010\u001b¨\u0006)"}, d2 = {"Lcom/arflix/tv/data/api/TraktScrobbleResponse;", "", TtmlNode.ATTR_ID, "", "action", "", "progress", "", "movie", "Lcom/arflix/tv/data/api/TraktMovieInfo;", "episode", "Lcom/arflix/tv/data/api/TraktEpisodeInfo;", "show", "Lcom/arflix/tv/data/api/TraktShowInfo;", "<init>", "(JLjava/lang/String;FLcom/arflix/tv/data/api/TraktMovieInfo;Lcom/arflix/tv/data/api/TraktEpisodeInfo;Lcom/arflix/tv/data/api/TraktShowInfo;)V", "getId", "()J", "getAction", "()Ljava/lang/String;", "getProgress", "()F", "getMovie", "()Lcom/arflix/tv/data/api/TraktMovieInfo;", "getEpisode", "()Lcom/arflix/tv/data/api/TraktEpisodeInfo;", "getShow", "()Lcom/arflix/tv/data/api/TraktShowInfo;", "component1", "component2", "component3", "component4", "component5", "component6", "copy", "equals", "", "other", "hashCode", "", "toString", "app_playRelease"}, k = 1, mv = {2, 3, 0}, xi = 48)
public final /* data */ class TraktScrobbleResponse {
    public static final int $stable = 0;
    private final String action;
    private final TraktEpisodeInfo episode;
    private final long id;
    private final TraktMovieInfo movie;
    private final float progress;
    private final TraktShowInfo show;

    public TraktScrobbleResponse(long j10, String str, float f10, TraktMovieInfo traktMovieInfo, TraktEpisodeInfo traktEpisodeInfo, TraktShowInfo traktShowInfo) {
        this.id = j10;
        this.action = str;
        this.progress = f10;
        this.movie = traktMovieInfo;
        this.episode = traktEpisodeInfo;
        this.show = traktShowInfo;
    }

    public static /* synthetic */ TraktScrobbleResponse copy$default(TraktScrobbleResponse traktScrobbleResponse, long j10, String str, float f10, TraktMovieInfo traktMovieInfo, TraktEpisodeInfo traktEpisodeInfo, TraktShowInfo traktShowInfo, int i10, Object obj) {
        if ((i10 & 1) != 0) {
            j10 = traktScrobbleResponse.id;
        }
        long j11 = j10;
        if ((i10 & 2) != 0) {
            str = traktScrobbleResponse.action;
        }
        String str2 = str;
        if ((i10 & 4) != 0) {
            f10 = traktScrobbleResponse.progress;
        }
        float f11 = f10;
        if ((i10 & 8) != 0) {
            traktMovieInfo = traktScrobbleResponse.movie;
        }
        TraktMovieInfo traktMovieInfo2 = traktMovieInfo;
        if ((i10 & 16) != 0) {
            traktEpisodeInfo = traktScrobbleResponse.episode;
        }
        TraktEpisodeInfo traktEpisodeInfo2 = traktEpisodeInfo;
        if ((i10 & 32) != 0) {
            traktShowInfo = traktScrobbleResponse.show;
        }
        return traktScrobbleResponse.copy(j11, str2, f11, traktMovieInfo2, traktEpisodeInfo2, traktShowInfo);
    }

    /* JADX INFO: renamed from: component1, reason: from getter */
    public final long getId() {
        return this.id;
    }

    /* JADX INFO: renamed from: component2, reason: from getter */
    public final String getAction() {
        return this.action;
    }

    /* JADX INFO: renamed from: component3, reason: from getter */
    public final float getProgress() {
        return this.progress;
    }

    /* JADX INFO: renamed from: component4, reason: from getter */
    public final TraktMovieInfo getMovie() {
        return this.movie;
    }

    /* JADX INFO: renamed from: component5, reason: from getter */
    public final TraktEpisodeInfo getEpisode() {
        return this.episode;
    }

    /* JADX INFO: renamed from: component6, reason: from getter */
    public final TraktShowInfo getShow() {
        return this.show;
    }

    public final TraktScrobbleResponse copy(long id, String action, float progress, TraktMovieInfo movie, TraktEpisodeInfo episode, TraktShowInfo show) {
        return new TraktScrobbleResponse(id, action, progress, movie, episode, show);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof TraktScrobbleResponse)) {
            return false;
        }
        TraktScrobbleResponse traktScrobbleResponse = (TraktScrobbleResponse) other;
        return this.id == traktScrobbleResponse.id && p.a(this.action, traktScrobbleResponse.action) && Float.compare(this.progress, traktScrobbleResponse.progress) == 0 && p.a(this.movie, traktScrobbleResponse.movie) && p.a(this.episode, traktScrobbleResponse.episode) && p.a(this.show, traktScrobbleResponse.show);
    }

    public final String getAction() {
        return this.action;
    }

    public final TraktEpisodeInfo getEpisode() {
        return this.episode;
    }

    public final long getId() {
        return this.id;
    }

    public final TraktMovieInfo getMovie() {
        return this.movie;
    }

    public final float getProgress() {
        return this.progress;
    }

    public final TraktShowInfo getShow() {
        return this.show;
    }

    public int hashCode() {
        long j10 = this.id;
        int iB = a0.c.b(this.progress, androidx.compose.foundation.c.c(((int) (j10 ^ (j10 >>> 32))) * 31, 31, this.action), 31);
        TraktMovieInfo traktMovieInfo = this.movie;
        int iHashCode = (iB + (traktMovieInfo == null ? 0 : traktMovieInfo.hashCode())) * 31;
        TraktEpisodeInfo traktEpisodeInfo = this.episode;
        int iHashCode2 = (iHashCode + (traktEpisodeInfo == null ? 0 : traktEpisodeInfo.hashCode())) * 31;
        TraktShowInfo traktShowInfo = this.show;
        return iHashCode2 + (traktShowInfo != null ? traktShowInfo.hashCode() : 0);
    }

    public String toString() {
        return "TraktScrobbleResponse(id=" + this.id + ", action=" + this.action + ", progress=" + this.progress + ", movie=" + this.movie + ", episode=" + this.episode + ", show=" + this.show + ")";
    }
}
