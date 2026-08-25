package com.arflix.tv.data.api;

import java.util.List;
import kotlin.Metadata;
import kotlin.collections.z;
import kotlin.jvm.internal.p;

/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0087\b\u0018\u00002\u00020\u0001B\u0017\u0012\u000e\b\u0002\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003¢\u0006\u0004\b\u0005\u0010\u0006J\u000f\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003HÆ\u0003J\u0019\u0010\n\u001a\u00020\u00002\u000e\b\u0002\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003HÆ\u0001J\u0014\u0010\u000b\u001a\u00020\f2\b\u0010\r\u001a\u0004\u0018\u00010\u0001HÖ\u0083\u0004J\n\u0010\u000e\u001a\u00020\u000fHÖ\u0081\u0004J\n\u0010\u0010\u001a\u00020\u0011HÖ\u0081\u0004R\u0017\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\b¨\u0006\u0012"}, d2 = {"Lcom/arflix/tv/data/api/TmdbCombinedCredits;", "", "cast", "", "Lcom/arflix/tv/data/api/TmdbMediaItem;", "<init>", "(Ljava/util/List;)V", "getCast", "()Ljava/util/List;", "component1", "copy", "equals", "", "other", "hashCode", "", "toString", "", "app_playRelease"}, k = 1, mv = {2, 3, 0}, xi = 48)
public final /* data */ class TmdbCombinedCredits {
    public static final int $stable = 8;
    private final List<TmdbMediaItem> cast;

    /* JADX WARN: Multi-variable type inference failed */
    public TmdbCombinedCredits() {
        this(null, 1, 0 == true ? 1 : 0);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static /* synthetic */ TmdbCombinedCredits copy$default(TmdbCombinedCredits tmdbCombinedCredits, List list, int i10, Object obj) {
        if ((i10 & 1) != 0) {
            list = tmdbCombinedCredits.cast;
        }
        return tmdbCombinedCredits.copy(list);
    }

    public final List<TmdbMediaItem> component1() {
        return this.cast;
    }

    public final TmdbCombinedCredits copy(List<TmdbMediaItem> cast) {
        return new TmdbCombinedCredits(cast);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        return (other instanceof TmdbCombinedCredits) && p.a(this.cast, ((TmdbCombinedCredits) other).cast);
    }

    public final List<TmdbMediaItem> getCast() {
        return this.cast;
    }

    public int hashCode() {
        return this.cast.hashCode();
    }

    public String toString() {
        return "TmdbCombinedCredits(cast=" + this.cast + ")";
    }

    public TmdbCombinedCredits(List<TmdbMediaItem> list) {
        this.cast = list;
    }

    public /* synthetic */ TmdbCombinedCredits(List list, int i10, kotlin.jvm.internal.h hVar) {
        this((i10 & 1) != 0 ? z.f19728i : list);
    }
}
