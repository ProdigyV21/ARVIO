package com.arflix.tv.data.api;

import androidx.media3.extractor.text.ttml.TtmlNode;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.z;
import kotlin.jvm.internal.p;

/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\r\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\b\u0087\b\u0018\u00002\u00020\u0001B1\u0012\b\b\u0002\u0010\u0002\u001a\u00020\u0003\u0012\u000e\b\u0002\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005\u0012\u000e\b\u0002\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005¢\u0006\u0004\b\b\u0010\tJ\t\u0010\u000f\u001a\u00020\u0003HÆ\u0003J\u000f\u0010\u0010\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005HÆ\u0003J\u000f\u0010\u0011\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005HÆ\u0003J3\u0010\u0012\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\u000e\b\u0002\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u00052\u000e\b\u0002\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005HÆ\u0001J\u0014\u0010\u0013\u001a\u00020\u00142\b\u0010\u0015\u001a\u0004\u0018\u00010\u0001HÖ\u0083\u0004J\n\u0010\u0016\u001a\u00020\u0003HÖ\u0081\u0004J\n\u0010\u0017\u001a\u00020\u0018HÖ\u0081\u0004R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR\u0017\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\rR\u0017\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\r¨\u0006\u0019"}, d2 = {"Lcom/arflix/tv/data/api/TmdbImagesResponse;", "", TtmlNode.ATTR_ID, "", "logos", "", "Lcom/arflix/tv/data/api/TmdbImage;", "backdrops", "<init>", "(ILjava/util/List;Ljava/util/List;)V", "getId", "()I", "getLogos", "()Ljava/util/List;", "getBackdrops", "component1", "component2", "component3", "copy", "equals", "", "other", "hashCode", "toString", "", "app_playRelease"}, k = 1, mv = {2, 3, 0}, xi = 48)
public final /* data */ class TmdbImagesResponse {
    public static final int $stable = 0;
    private final List<TmdbImage> backdrops;
    private final int id;
    private final List<TmdbImage> logos;

    public TmdbImagesResponse() {
        this(0, null, null, 7, null);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static /* synthetic */ TmdbImagesResponse copy$default(TmdbImagesResponse tmdbImagesResponse, int i10, List list, List list2, int i11, Object obj) {
        if ((i11 & 1) != 0) {
            i10 = tmdbImagesResponse.id;
        }
        if ((i11 & 2) != 0) {
            list = tmdbImagesResponse.logos;
        }
        if ((i11 & 4) != 0) {
            list2 = tmdbImagesResponse.backdrops;
        }
        return tmdbImagesResponse.copy(i10, list, list2);
    }

    /* JADX INFO: renamed from: component1, reason: from getter */
    public final int getId() {
        return this.id;
    }

    public final List<TmdbImage> component2() {
        return this.logos;
    }

    public final List<TmdbImage> component3() {
        return this.backdrops;
    }

    public final TmdbImagesResponse copy(int id, List<TmdbImage> logos, List<TmdbImage> backdrops) {
        return new TmdbImagesResponse(id, logos, backdrops);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof TmdbImagesResponse)) {
            return false;
        }
        TmdbImagesResponse tmdbImagesResponse = (TmdbImagesResponse) other;
        return this.id == tmdbImagesResponse.id && p.a(this.logos, tmdbImagesResponse.logos) && p.a(this.backdrops, tmdbImagesResponse.backdrops);
    }

    public final List<TmdbImage> getBackdrops() {
        return this.backdrops;
    }

    public final int getId() {
        return this.id;
    }

    public final List<TmdbImage> getLogos() {
        return this.logos;
    }

    public int hashCode() {
        return this.backdrops.hashCode() + androidx.compose.material3.d.e(this.logos, this.id * 31, 31);
    }

    public String toString() {
        int i10 = this.id;
        List<TmdbImage> list = this.logos;
        List<TmdbImage> list2 = this.backdrops;
        StringBuilder sb2 = new StringBuilder("TmdbImagesResponse(id=");
        sb2.append(i10);
        sb2.append(", logos=");
        sb2.append(list);
        sb2.append(", backdrops=");
        return a0.c.q(sb2, list2, ")");
    }

    public TmdbImagesResponse(int i10, List<TmdbImage> list, List<TmdbImage> list2) {
        this.id = i10;
        this.logos = list;
        this.backdrops = list2;
    }

    /* JADX WARN: Illegal instructions before constructor call */
    public /* synthetic */ TmdbImagesResponse(int i10, List list, List list2, int i11, kotlin.jvm.internal.h hVar) {
        i10 = (i11 & 1) != 0 ? 0 : i10;
        int i12 = i11 & 2;
        z zVar = z.f19728i;
        this(i10, i12 != 0 ? zVar : list, (i11 & 4) != 0 ? zVar : list2);
    }
}
