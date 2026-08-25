package com.arflix.tv.data.api;

import androidx.media3.extractor.text.ttml.TtmlNode;
import kotlin.Metadata;
import kotlin.jvm.internal.p;

/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u000f\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0087\b\u0018\u00002\u00020\u0001B+\u0012\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0005\u0012\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u0007¢\u0006\u0004\b\b\u0010\tJ\u0010\u0010\u0011\u001a\u0004\u0018\u00010\u0003HÆ\u0003¢\u0006\u0002\u0010\u000bJ\u000b\u0010\u0012\u001a\u0004\u0018\u00010\u0005HÆ\u0003J\u000b\u0010\u0013\u001a\u0004\u0018\u00010\u0007HÆ\u0003J2\u0010\u0014\u001a\u00020\u00002\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u00052\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u0007HÆ\u0001¢\u0006\u0002\u0010\u0015J\u0014\u0010\u0016\u001a\u00020\u00172\b\u0010\u0018\u001a\u0004\u0018\u00010\u0001HÖ\u0083\u0004J\n\u0010\u0019\u001a\u00020\u001aHÖ\u0081\u0004J\n\u0010\u001b\u001a\u00020\u001cHÖ\u0081\u0004R\u0015\u0010\u0002\u001a\u0004\u0018\u00010\u0003¢\u0006\n\n\u0002\u0010\f\u001a\u0004\b\n\u0010\u000bR\u0013\u0010\u0004\u001a\u0004\u0018\u00010\u0005¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000eR\u0013\u0010\u0006\u001a\u0004\u0018\u00010\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u0010¨\u0006\u001d"}, d2 = {"Lcom/arflix/tv/data/api/MdbScrobbleClearBody;", "", TtmlNode.ATTR_ID, "", "movie", "Lcom/arflix/tv/data/api/MdbScrobbleMovie;", "show", "Lcom/arflix/tv/data/api/MdbScrobbleShow;", "<init>", "(Ljava/lang/Long;Lcom/arflix/tv/data/api/MdbScrobbleMovie;Lcom/arflix/tv/data/api/MdbScrobbleShow;)V", "getId", "()Ljava/lang/Long;", "Ljava/lang/Long;", "getMovie", "()Lcom/arflix/tv/data/api/MdbScrobbleMovie;", "getShow", "()Lcom/arflix/tv/data/api/MdbScrobbleShow;", "component1", "component2", "component3", "copy", "(Ljava/lang/Long;Lcom/arflix/tv/data/api/MdbScrobbleMovie;Lcom/arflix/tv/data/api/MdbScrobbleShow;)Lcom/arflix/tv/data/api/MdbScrobbleClearBody;", "equals", "", "other", "hashCode", "", "toString", "", "app_playRelease"}, k = 1, mv = {2, 3, 0}, xi = 48)
public final /* data */ class MdbScrobbleClearBody {
    public static final int $stable = 0;
    private final Long id;
    private final MdbScrobbleMovie movie;
    private final MdbScrobbleShow show;

    public MdbScrobbleClearBody() {
        this(null, null, null, 7, null);
    }

    public static /* synthetic */ MdbScrobbleClearBody copy$default(MdbScrobbleClearBody mdbScrobbleClearBody, Long l10, MdbScrobbleMovie mdbScrobbleMovie, MdbScrobbleShow mdbScrobbleShow, int i10, Object obj) {
        if ((i10 & 1) != 0) {
            l10 = mdbScrobbleClearBody.id;
        }
        if ((i10 & 2) != 0) {
            mdbScrobbleMovie = mdbScrobbleClearBody.movie;
        }
        if ((i10 & 4) != 0) {
            mdbScrobbleShow = mdbScrobbleClearBody.show;
        }
        return mdbScrobbleClearBody.copy(l10, mdbScrobbleMovie, mdbScrobbleShow);
    }

    /* JADX INFO: renamed from: component1, reason: from getter */
    public final Long getId() {
        return this.id;
    }

    /* JADX INFO: renamed from: component2, reason: from getter */
    public final MdbScrobbleMovie getMovie() {
        return this.movie;
    }

    /* JADX INFO: renamed from: component3, reason: from getter */
    public final MdbScrobbleShow getShow() {
        return this.show;
    }

    public final MdbScrobbleClearBody copy(Long id, MdbScrobbleMovie movie, MdbScrobbleShow show) {
        return new MdbScrobbleClearBody(id, movie, show);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof MdbScrobbleClearBody)) {
            return false;
        }
        MdbScrobbleClearBody mdbScrobbleClearBody = (MdbScrobbleClearBody) other;
        return p.a(this.id, mdbScrobbleClearBody.id) && p.a(this.movie, mdbScrobbleClearBody.movie) && p.a(this.show, mdbScrobbleClearBody.show);
    }

    public final Long getId() {
        return this.id;
    }

    public final MdbScrobbleMovie getMovie() {
        return this.movie;
    }

    public final MdbScrobbleShow getShow() {
        return this.show;
    }

    public int hashCode() {
        Long l10 = this.id;
        int iHashCode = (l10 == null ? 0 : l10.hashCode()) * 31;
        MdbScrobbleMovie mdbScrobbleMovie = this.movie;
        int iHashCode2 = (iHashCode + (mdbScrobbleMovie == null ? 0 : mdbScrobbleMovie.hashCode())) * 31;
        MdbScrobbleShow mdbScrobbleShow = this.show;
        return iHashCode2 + (mdbScrobbleShow != null ? mdbScrobbleShow.hashCode() : 0);
    }

    public String toString() {
        return "MdbScrobbleClearBody(id=" + this.id + ", movie=" + this.movie + ", show=" + this.show + ")";
    }

    public MdbScrobbleClearBody(Long l10, MdbScrobbleMovie mdbScrobbleMovie, MdbScrobbleShow mdbScrobbleShow) {
        this.id = l10;
        this.movie = mdbScrobbleMovie;
        this.show = mdbScrobbleShow;
    }

    public /* synthetic */ MdbScrobbleClearBody(Long l10, MdbScrobbleMovie mdbScrobbleMovie, MdbScrobbleShow mdbScrobbleShow, int i10, kotlin.jvm.internal.h hVar) {
        this((i10 & 1) != 0 ? null : l10, (i10 & 2) != 0 ? null : mdbScrobbleMovie, (i10 & 4) != 0 ? null : mdbScrobbleShow);
    }
}
