package com.arflix.tv.data.api;

import androidx.media3.extractor.text.ttml.TtmlNode;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.z;
import kotlin.jvm.internal.p;

/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\f\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\b\u0087\b\u0018\u00002\u00020\u0001B1\u0012\b\b\u0002\u0010\u0002\u001a\u00020\u0003\u0012\u000e\b\u0002\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005\u0012\u000e\b\u0002\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\b0\u0005¢\u0006\u0004\b\t\u0010\nJ\t\u0010\u0010\u001a\u00020\u0003HÆ\u0003J\u000f\u0010\u0011\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005HÆ\u0003J\u000f\u0010\u0012\u001a\b\u0012\u0004\u0012\u00020\b0\u0005HÆ\u0003J3\u0010\u0013\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\u000e\b\u0002\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u00052\u000e\b\u0002\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\b0\u0005HÆ\u0001J\u0014\u0010\u0014\u001a\u00020\u00152\b\u0010\u0016\u001a\u0004\u0018\u00010\u0001HÖ\u0083\u0004J\n\u0010\u0017\u001a\u00020\u0003HÖ\u0081\u0004J\n\u0010\u0018\u001a\u00020\u0019HÖ\u0081\u0004R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u0017\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000eR\u0017\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\b0\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u000e¨\u0006\u001a"}, d2 = {"Lcom/arflix/tv/data/api/TmdbCreditsResponse;", "", TtmlNode.ATTR_ID, "", "cast", "", "Lcom/arflix/tv/data/api/TmdbCastMember;", "crew", "Lcom/arflix/tv/data/api/TmdbCrewMember;", "<init>", "(ILjava/util/List;Ljava/util/List;)V", "getId", "()I", "getCast", "()Ljava/util/List;", "getCrew", "component1", "component2", "component3", "copy", "equals", "", "other", "hashCode", "toString", "", "app_playRelease"}, k = 1, mv = {2, 3, 0}, xi = 48)
public final /* data */ class TmdbCreditsResponse {
    public static final int $stable = 0;
    private final List<TmdbCastMember> cast;
    private final List<TmdbCrewMember> crew;
    private final int id;

    public TmdbCreditsResponse() {
        this(0, null, null, 7, null);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static /* synthetic */ TmdbCreditsResponse copy$default(TmdbCreditsResponse tmdbCreditsResponse, int i10, List list, List list2, int i11, Object obj) {
        if ((i11 & 1) != 0) {
            i10 = tmdbCreditsResponse.id;
        }
        if ((i11 & 2) != 0) {
            list = tmdbCreditsResponse.cast;
        }
        if ((i11 & 4) != 0) {
            list2 = tmdbCreditsResponse.crew;
        }
        return tmdbCreditsResponse.copy(i10, list, list2);
    }

    /* JADX INFO: renamed from: component1, reason: from getter */
    public final int getId() {
        return this.id;
    }

    public final List<TmdbCastMember> component2() {
        return this.cast;
    }

    public final List<TmdbCrewMember> component3() {
        return this.crew;
    }

    public final TmdbCreditsResponse copy(int id, List<TmdbCastMember> cast, List<TmdbCrewMember> crew) {
        return new TmdbCreditsResponse(id, cast, crew);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof TmdbCreditsResponse)) {
            return false;
        }
        TmdbCreditsResponse tmdbCreditsResponse = (TmdbCreditsResponse) other;
        return this.id == tmdbCreditsResponse.id && p.a(this.cast, tmdbCreditsResponse.cast) && p.a(this.crew, tmdbCreditsResponse.crew);
    }

    public final List<TmdbCastMember> getCast() {
        return this.cast;
    }

    public final List<TmdbCrewMember> getCrew() {
        return this.crew;
    }

    public final int getId() {
        return this.id;
    }

    public int hashCode() {
        return this.crew.hashCode() + androidx.compose.material3.d.e(this.cast, this.id * 31, 31);
    }

    public String toString() {
        int i10 = this.id;
        List<TmdbCastMember> list = this.cast;
        List<TmdbCrewMember> list2 = this.crew;
        StringBuilder sb2 = new StringBuilder("TmdbCreditsResponse(id=");
        sb2.append(i10);
        sb2.append(", cast=");
        sb2.append(list);
        sb2.append(", crew=");
        return a0.c.q(sb2, list2, ")");
    }

    public TmdbCreditsResponse(int i10, List<TmdbCastMember> list, List<TmdbCrewMember> list2) {
        this.id = i10;
        this.cast = list;
        this.crew = list2;
    }

    /* JADX WARN: Illegal instructions before constructor call */
    public /* synthetic */ TmdbCreditsResponse(int i10, List list, List list2, int i11, kotlin.jvm.internal.h hVar) {
        i10 = (i11 & 1) != 0 ? 0 : i10;
        int i12 = i11 & 2;
        z zVar = z.f19728i;
        this(i10, i12 != 0 ? zVar : list, (i11 & 4) != 0 ? zVar : list2);
    }
}
