package com.arflix.tv.data.api;

import kotlin.Metadata;
import kotlin.jvm.internal.p;

/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\f\n\u0002\u0010\u000b\n\u0002\b\u0004\b\u0087\b\u0018\u00002\u00020\u0001B\u001f\u0012\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0005¢\u0006\u0004\b\u0006\u0010\u0007J\u0010\u0010\r\u001a\u0004\u0018\u00010\u0003HÆ\u0003¢\u0006\u0002\u0010\tJ\u000b\u0010\u000e\u001a\u0004\u0018\u00010\u0005HÆ\u0003J&\u0010\u000f\u001a\u00020\u00002\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0005HÆ\u0001¢\u0006\u0002\u0010\u0010J\u0014\u0010\u0011\u001a\u00020\u00122\b\u0010\u0013\u001a\u0004\u0018\u00010\u0001HÖ\u0083\u0004J\n\u0010\u0014\u001a\u00020\u0003HÖ\u0081\u0004J\n\u0010\u0015\u001a\u00020\u0005HÖ\u0081\u0004R\u0015\u0010\u0002\u001a\u0004\u0018\u00010\u0003¢\u0006\n\n\u0002\u0010\n\u001a\u0004\b\b\u0010\tR\u0013\u0010\u0004\u001a\u0004\u0018\u00010\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\f¨\u0006\u0016"}, d2 = {"Lcom/arflix/tv/data/api/TraktSearchListIds;", "", "trakt", "", "slug", "", "<init>", "(Ljava/lang/Integer;Ljava/lang/String;)V", "getTrakt", "()Ljava/lang/Integer;", "Ljava/lang/Integer;", "getSlug", "()Ljava/lang/String;", "component1", "component2", "copy", "(Ljava/lang/Integer;Ljava/lang/String;)Lcom/arflix/tv/data/api/TraktSearchListIds;", "equals", "", "other", "hashCode", "toString", "app_playRelease"}, k = 1, mv = {2, 3, 0}, xi = 48)
public final /* data */ class TraktSearchListIds {
    public static final int $stable = 0;
    private final String slug;
    private final Integer trakt;

    /* JADX WARN: Multi-variable type inference failed */
    public TraktSearchListIds() {
        this(null, 0 == true ? 1 : 0, 3, 0 == true ? 1 : 0);
    }

    public static /* synthetic */ TraktSearchListIds copy$default(TraktSearchListIds traktSearchListIds, Integer num, String str, int i10, Object obj) {
        if ((i10 & 1) != 0) {
            num = traktSearchListIds.trakt;
        }
        if ((i10 & 2) != 0) {
            str = traktSearchListIds.slug;
        }
        return traktSearchListIds.copy(num, str);
    }

    /* JADX INFO: renamed from: component1, reason: from getter */
    public final Integer getTrakt() {
        return this.trakt;
    }

    /* JADX INFO: renamed from: component2, reason: from getter */
    public final String getSlug() {
        return this.slug;
    }

    public final TraktSearchListIds copy(Integer trakt, String slug) {
        return new TraktSearchListIds(trakt, slug);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof TraktSearchListIds)) {
            return false;
        }
        TraktSearchListIds traktSearchListIds = (TraktSearchListIds) other;
        return p.a(this.trakt, traktSearchListIds.trakt) && p.a(this.slug, traktSearchListIds.slug);
    }

    public final String getSlug() {
        return this.slug;
    }

    public final Integer getTrakt() {
        return this.trakt;
    }

    public int hashCode() {
        Integer num = this.trakt;
        int iHashCode = (num == null ? 0 : num.hashCode()) * 31;
        String str = this.slug;
        return iHashCode + (str != null ? str.hashCode() : 0);
    }

    public String toString() {
        return "TraktSearchListIds(trakt=" + this.trakt + ", slug=" + this.slug + ")";
    }

    public TraktSearchListIds(Integer num, String str) {
        this.trakt = num;
        this.slug = str;
    }

    public /* synthetic */ TraktSearchListIds(Integer num, String str, int i10, kotlin.jvm.internal.h hVar) {
        this((i10 & 1) != 0 ? null : num, (i10 & 2) != 0 ? null : str);
    }
}
