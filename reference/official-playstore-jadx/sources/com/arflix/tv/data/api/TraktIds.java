package com.arflix.tv.data.api;

import kotlin.Metadata;
import kotlin.jvm.internal.p;

/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0013\n\u0002\u0010\u000b\n\u0002\b\u0004\b\u0087\b\u0018\u00002\u00020\u0001BC\u0012\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u0007\u0012\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\u0007¢\u0006\u0004\b\t\u0010\nJ\u0010\u0010\u0013\u001a\u0004\u0018\u00010\u0003HÆ\u0003¢\u0006\u0002\u0010\fJ\u0010\u0010\u0014\u001a\u0004\u0018\u00010\u0003HÆ\u0003¢\u0006\u0002\u0010\fJ\u0010\u0010\u0015\u001a\u0004\u0018\u00010\u0003HÆ\u0003¢\u0006\u0002\u0010\fJ\u000b\u0010\u0016\u001a\u0004\u0018\u00010\u0007HÆ\u0003J\u000b\u0010\u0017\u001a\u0004\u0018\u00010\u0007HÆ\u0003JJ\u0010\u0018\u001a\u00020\u00002\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u00072\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\u0007HÆ\u0001¢\u0006\u0002\u0010\u0019J\u0014\u0010\u001a\u001a\u00020\u001b2\b\u0010\u001c\u001a\u0004\u0018\u00010\u0001HÖ\u0083\u0004J\n\u0010\u001d\u001a\u00020\u0003HÖ\u0081\u0004J\n\u0010\u001e\u001a\u00020\u0007HÖ\u0081\u0004R\u0015\u0010\u0002\u001a\u0004\u0018\u00010\u0003¢\u0006\n\n\u0002\u0010\r\u001a\u0004\b\u000b\u0010\fR\u0015\u0010\u0004\u001a\u0004\u0018\u00010\u0003¢\u0006\n\n\u0002\u0010\r\u001a\u0004\b\u000e\u0010\fR\u0015\u0010\u0005\u001a\u0004\u0018\u00010\u0003¢\u0006\n\n\u0002\u0010\r\u001a\u0004\b\u000f\u0010\fR\u0013\u0010\u0006\u001a\u0004\u0018\u00010\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u0011R\u0013\u0010\b\u001a\u0004\u0018\u00010\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u0011¨\u0006\u001f"}, d2 = {"Lcom/arflix/tv/data/api/TraktIds;", "", "trakt", "", "tmdb", "tvdb", "imdb", "", "slug", "<init>", "(Ljava/lang/Integer;Ljava/lang/Integer;Ljava/lang/Integer;Ljava/lang/String;Ljava/lang/String;)V", "getTrakt", "()Ljava/lang/Integer;", "Ljava/lang/Integer;", "getTmdb", "getTvdb", "getImdb", "()Ljava/lang/String;", "getSlug", "component1", "component2", "component3", "component4", "component5", "copy", "(Ljava/lang/Integer;Ljava/lang/Integer;Ljava/lang/Integer;Ljava/lang/String;Ljava/lang/String;)Lcom/arflix/tv/data/api/TraktIds;", "equals", "", "other", "hashCode", "toString", "app_playRelease"}, k = 1, mv = {2, 3, 0}, xi = 48)
public final /* data */ class TraktIds {
    public static final int $stable = 0;
    private final String imdb;
    private final String slug;
    private final Integer tmdb;
    private final Integer trakt;
    private final Integer tvdb;

    public TraktIds() {
        this(null, null, null, null, null, 31, null);
    }

    public static /* synthetic */ TraktIds copy$default(TraktIds traktIds, Integer num, Integer num2, Integer num3, String str, String str2, int i10, Object obj) {
        if ((i10 & 1) != 0) {
            num = traktIds.trakt;
        }
        if ((i10 & 2) != 0) {
            num2 = traktIds.tmdb;
        }
        if ((i10 & 4) != 0) {
            num3 = traktIds.tvdb;
        }
        if ((i10 & 8) != 0) {
            str = traktIds.imdb;
        }
        if ((i10 & 16) != 0) {
            str2 = traktIds.slug;
        }
        String str3 = str2;
        Integer num4 = num3;
        return traktIds.copy(num, num2, num4, str, str3);
    }

    /* JADX INFO: renamed from: component1, reason: from getter */
    public final Integer getTrakt() {
        return this.trakt;
    }

    /* JADX INFO: renamed from: component2, reason: from getter */
    public final Integer getTmdb() {
        return this.tmdb;
    }

    /* JADX INFO: renamed from: component3, reason: from getter */
    public final Integer getTvdb() {
        return this.tvdb;
    }

    /* JADX INFO: renamed from: component4, reason: from getter */
    public final String getImdb() {
        return this.imdb;
    }

    /* JADX INFO: renamed from: component5, reason: from getter */
    public final String getSlug() {
        return this.slug;
    }

    public final TraktIds copy(Integer trakt, Integer tmdb, Integer tvdb, String imdb, String slug) {
        return new TraktIds(trakt, tmdb, tvdb, imdb, slug);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof TraktIds)) {
            return false;
        }
        TraktIds traktIds = (TraktIds) other;
        return p.a(this.trakt, traktIds.trakt) && p.a(this.tmdb, traktIds.tmdb) && p.a(this.tvdb, traktIds.tvdb) && p.a(this.imdb, traktIds.imdb) && p.a(this.slug, traktIds.slug);
    }

    public final String getImdb() {
        return this.imdb;
    }

    public final String getSlug() {
        return this.slug;
    }

    public final Integer getTmdb() {
        return this.tmdb;
    }

    public final Integer getTrakt() {
        return this.trakt;
    }

    public final Integer getTvdb() {
        return this.tvdb;
    }

    public int hashCode() {
        Integer num = this.trakt;
        int iHashCode = (num == null ? 0 : num.hashCode()) * 31;
        Integer num2 = this.tmdb;
        int iHashCode2 = (iHashCode + (num2 == null ? 0 : num2.hashCode())) * 31;
        Integer num3 = this.tvdb;
        int iHashCode3 = (iHashCode2 + (num3 == null ? 0 : num3.hashCode())) * 31;
        String str = this.imdb;
        int iHashCode4 = (iHashCode3 + (str == null ? 0 : str.hashCode())) * 31;
        String str2 = this.slug;
        return iHashCode4 + (str2 != null ? str2.hashCode() : 0);
    }

    public String toString() {
        Integer num = this.trakt;
        Integer num2 = this.tmdb;
        Integer num3 = this.tvdb;
        String str = this.imdb;
        String str2 = this.slug;
        StringBuilder sb2 = new StringBuilder("TraktIds(trakt=");
        sb2.append(num);
        sb2.append(", tmdb=");
        sb2.append(num2);
        sb2.append(", tvdb=");
        sb2.append(num3);
        sb2.append(", imdb=");
        sb2.append(str);
        sb2.append(", slug=");
        return a0.c.p(sb2, str2, ")");
    }

    public TraktIds(Integer num, Integer num2, Integer num3, String str, String str2) {
        this.trakt = num;
        this.tmdb = num2;
        this.tvdb = num3;
        this.imdb = str;
        this.slug = str2;
    }

    public /* synthetic */ TraktIds(Integer num, Integer num2, Integer num3, String str, String str2, int i10, kotlin.jvm.internal.h hVar) {
        this((i10 & 1) != 0 ? null : num, (i10 & 2) != 0 ? null : num2, (i10 & 4) != 0 ? null : num3, (i10 & 8) != 0 ? null : str, (i10 & 16) != 0 ? null : str2);
    }
}
