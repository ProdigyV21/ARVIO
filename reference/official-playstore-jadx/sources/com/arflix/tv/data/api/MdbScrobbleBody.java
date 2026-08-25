package com.arflix.tv.data.api;

import kotlin.Metadata;
import kotlin.jvm.internal.p;

/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\r\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\b\u0087\b\u0018\u00002\u00020\u0001B'\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0005\u0012\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u0007¢\u0006\u0004\b\b\u0010\tJ\t\u0010\u0010\u001a\u00020\u0003HÆ\u0003J\u000b\u0010\u0011\u001a\u0004\u0018\u00010\u0005HÆ\u0003J\u000b\u0010\u0012\u001a\u0004\u0018\u00010\u0007HÆ\u0003J+\u0010\u0013\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u00052\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u0007HÆ\u0001J\u0014\u0010\u0014\u001a\u00020\u00152\b\u0010\u0016\u001a\u0004\u0018\u00010\u0001HÖ\u0083\u0004J\n\u0010\u0017\u001a\u00020\u0003HÖ\u0081\u0004J\n\u0010\u0018\u001a\u00020\u0019HÖ\u0081\u0004R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR\u0013\u0010\u0004\u001a\u0004\u0018\u00010\u0005¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\rR\u0013\u0010\u0006\u001a\u0004\u0018\u00010\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000f¨\u0006\u001a"}, d2 = {"Lcom/arflix/tv/data/api/MdbScrobbleBody;", "", "progress", "", "movie", "Lcom/arflix/tv/data/api/MdbScrobbleMovie;", "show", "Lcom/arflix/tv/data/api/MdbScrobbleShow;", "<init>", "(ILcom/arflix/tv/data/api/MdbScrobbleMovie;Lcom/arflix/tv/data/api/MdbScrobbleShow;)V", "getProgress", "()I", "getMovie", "()Lcom/arflix/tv/data/api/MdbScrobbleMovie;", "getShow", "()Lcom/arflix/tv/data/api/MdbScrobbleShow;", "component1", "component2", "component3", "copy", "equals", "", "other", "hashCode", "toString", "", "app_playRelease"}, k = 1, mv = {2, 3, 0}, xi = 48)
public final /* data */ class MdbScrobbleBody {
    public static final int $stable = 0;
    private final MdbScrobbleMovie movie;
    private final int progress;
    private final MdbScrobbleShow show;

    public MdbScrobbleBody(int i10, MdbScrobbleMovie mdbScrobbleMovie, MdbScrobbleShow mdbScrobbleShow) {
        this.progress = i10;
        this.movie = mdbScrobbleMovie;
        this.show = mdbScrobbleShow;
    }

    public static /* synthetic */ MdbScrobbleBody copy$default(MdbScrobbleBody mdbScrobbleBody, int i10, MdbScrobbleMovie mdbScrobbleMovie, MdbScrobbleShow mdbScrobbleShow, int i11, Object obj) {
        if ((i11 & 1) != 0) {
            i10 = mdbScrobbleBody.progress;
        }
        if ((i11 & 2) != 0) {
            mdbScrobbleMovie = mdbScrobbleBody.movie;
        }
        if ((i11 & 4) != 0) {
            mdbScrobbleShow = mdbScrobbleBody.show;
        }
        return mdbScrobbleBody.copy(i10, mdbScrobbleMovie, mdbScrobbleShow);
    }

    /* JADX INFO: renamed from: component1, reason: from getter */
    public final int getProgress() {
        return this.progress;
    }

    /* JADX INFO: renamed from: component2, reason: from getter */
    public final MdbScrobbleMovie getMovie() {
        return this.movie;
    }

    /* JADX INFO: renamed from: component3, reason: from getter */
    public final MdbScrobbleShow getShow() {
        return this.show;
    }

    public final MdbScrobbleBody copy(int progress, MdbScrobbleMovie movie, MdbScrobbleShow show) {
        return new MdbScrobbleBody(progress, movie, show);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof MdbScrobbleBody)) {
            return false;
        }
        MdbScrobbleBody mdbScrobbleBody = (MdbScrobbleBody) other;
        return this.progress == mdbScrobbleBody.progress && p.a(this.movie, mdbScrobbleBody.movie) && p.a(this.show, mdbScrobbleBody.show);
    }

    public final MdbScrobbleMovie getMovie() {
        return this.movie;
    }

    public final int getProgress() {
        return this.progress;
    }

    public final MdbScrobbleShow getShow() {
        return this.show;
    }

    public int hashCode() {
        int i10 = this.progress * 31;
        MdbScrobbleMovie mdbScrobbleMovie = this.movie;
        int iHashCode = (i10 + (mdbScrobbleMovie == null ? 0 : mdbScrobbleMovie.hashCode())) * 31;
        MdbScrobbleShow mdbScrobbleShow = this.show;
        return iHashCode + (mdbScrobbleShow != null ? mdbScrobbleShow.hashCode() : 0);
    }

    public String toString() {
        return "MdbScrobbleBody(progress=" + this.progress + ", movie=" + this.movie + ", show=" + this.show + ")";
    }

    public /* synthetic */ MdbScrobbleBody(int i10, MdbScrobbleMovie mdbScrobbleMovie, MdbScrobbleShow mdbScrobbleShow, int i11, kotlin.jvm.internal.h hVar) {
        this(i10, (i11 & 2) != 0 ? null : mdbScrobbleMovie, (i11 & 4) != 0 ? null : mdbScrobbleShow);
    }
}
