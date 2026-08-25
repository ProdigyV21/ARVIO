package com.arflix.tv.data.api;

import java.util.Map;
import kotlin.Metadata;
import kotlin.jvm.internal.p;

/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010$\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0014\n\u0002\u0010\u000b\n\u0002\b\u0004\b\u0087\b\u0018\u00002\u00020\u0001BE\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\u0014\u0010\u0004\u001a\u0010\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u0003\u0018\u00010\u0005\u0012\b\u0010\u0006\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\u0007\u001a\u0004\u0018\u00010\b\u0012\b\u0010\t\u001a\u0004\u0018\u00010\u0003¢\u0006\u0004\b\n\u0010\u000bJ\u000b\u0010\u0015\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u0017\u0010\u0016\u001a\u0010\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u0003\u0018\u00010\u0005HÆ\u0003J\u000b\u0010\u0017\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u0010\u0010\u0018\u001a\u0004\u0018\u00010\bHÆ\u0003¢\u0006\u0002\u0010\u0012J\u000b\u0010\u0019\u001a\u0004\u0018\u00010\u0003HÆ\u0003JV\u0010\u001a\u001a\u00020\u00002\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u00032\u0016\b\u0002\u0010\u0004\u001a\u0010\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u0003\u0018\u00010\u00052\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\b2\n\b\u0002\u0010\t\u001a\u0004\u0018\u00010\u0003HÆ\u0001¢\u0006\u0002\u0010\u001bJ\u0014\u0010\u001c\u001a\u00020\u001d2\b\u0010\u001e\u001a\u0004\u0018\u00010\u0001HÖ\u0083\u0004J\n\u0010\u001f\u001a\u00020\bHÖ\u0081\u0004J\n\u0010 \u001a\u00020\u0003HÖ\u0081\u0004R\u0013\u0010\u0002\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\rR\u001f\u0010\u0004\u001a\u0010\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u0003\u0018\u00010\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000fR\u0013\u0010\u0006\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\rR\u0015\u0010\u0007\u001a\u0004\u0018\u00010\b¢\u0006\n\n\u0002\u0010\u0013\u001a\u0004\b\u0011\u0010\u0012R\u0013\u0010\t\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\r¨\u0006!"}, d2 = {"Lcom/arflix/tv/data/api/KitsuAnimeAttributes;", "", "canonicalTitle", "", "titles", "", "slug", "episodeCount", "", "status", "<init>", "(Ljava/lang/String;Ljava/util/Map;Ljava/lang/String;Ljava/lang/Integer;Ljava/lang/String;)V", "getCanonicalTitle", "()Ljava/lang/String;", "getTitles", "()Ljava/util/Map;", "getSlug", "getEpisodeCount", "()Ljava/lang/Integer;", "Ljava/lang/Integer;", "getStatus", "component1", "component2", "component3", "component4", "component5", "copy", "(Ljava/lang/String;Ljava/util/Map;Ljava/lang/String;Ljava/lang/Integer;Ljava/lang/String;)Lcom/arflix/tv/data/api/KitsuAnimeAttributes;", "equals", "", "other", "hashCode", "toString", "app_playRelease"}, k = 1, mv = {2, 3, 0}, xi = 48)
public final /* data */ class KitsuAnimeAttributes {
    public static final int $stable = 0;
    private final String canonicalTitle;
    private final Integer episodeCount;
    private final String slug;
    private final String status;
    private final Map<String, String> titles;

    public KitsuAnimeAttributes(String str, Map<String, String> map, String str2, Integer num, String str3) {
        this.canonicalTitle = str;
        this.titles = map;
        this.slug = str2;
        this.episodeCount = num;
        this.status = str3;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static /* synthetic */ KitsuAnimeAttributes copy$default(KitsuAnimeAttributes kitsuAnimeAttributes, String str, Map map, String str2, Integer num, String str3, int i10, Object obj) {
        if ((i10 & 1) != 0) {
            str = kitsuAnimeAttributes.canonicalTitle;
        }
        if ((i10 & 2) != 0) {
            map = kitsuAnimeAttributes.titles;
        }
        if ((i10 & 4) != 0) {
            str2 = kitsuAnimeAttributes.slug;
        }
        if ((i10 & 8) != 0) {
            num = kitsuAnimeAttributes.episodeCount;
        }
        if ((i10 & 16) != 0) {
            str3 = kitsuAnimeAttributes.status;
        }
        String str4 = str3;
        String str5 = str2;
        return kitsuAnimeAttributes.copy(str, map, str5, num, str4);
    }

    /* JADX INFO: renamed from: component1, reason: from getter */
    public final String getCanonicalTitle() {
        return this.canonicalTitle;
    }

    public final Map<String, String> component2() {
        return this.titles;
    }

    /* JADX INFO: renamed from: component3, reason: from getter */
    public final String getSlug() {
        return this.slug;
    }

    /* JADX INFO: renamed from: component4, reason: from getter */
    public final Integer getEpisodeCount() {
        return this.episodeCount;
    }

    /* JADX INFO: renamed from: component5, reason: from getter */
    public final String getStatus() {
        return this.status;
    }

    public final KitsuAnimeAttributes copy(String canonicalTitle, Map<String, String> titles, String slug, Integer episodeCount, String status) {
        return new KitsuAnimeAttributes(canonicalTitle, titles, slug, episodeCount, status);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof KitsuAnimeAttributes)) {
            return false;
        }
        KitsuAnimeAttributes kitsuAnimeAttributes = (KitsuAnimeAttributes) other;
        return p.a(this.canonicalTitle, kitsuAnimeAttributes.canonicalTitle) && p.a(this.titles, kitsuAnimeAttributes.titles) && p.a(this.slug, kitsuAnimeAttributes.slug) && p.a(this.episodeCount, kitsuAnimeAttributes.episodeCount) && p.a(this.status, kitsuAnimeAttributes.status);
    }

    public final String getCanonicalTitle() {
        return this.canonicalTitle;
    }

    public final Integer getEpisodeCount() {
        return this.episodeCount;
    }

    public final String getSlug() {
        return this.slug;
    }

    public final String getStatus() {
        return this.status;
    }

    public final Map<String, String> getTitles() {
        return this.titles;
    }

    public int hashCode() {
        String str = this.canonicalTitle;
        int iHashCode = (str == null ? 0 : str.hashCode()) * 31;
        Map<String, String> map = this.titles;
        int iHashCode2 = (iHashCode + (map == null ? 0 : map.hashCode())) * 31;
        String str2 = this.slug;
        int iHashCode3 = (iHashCode2 + (str2 == null ? 0 : str2.hashCode())) * 31;
        Integer num = this.episodeCount;
        int iHashCode4 = (iHashCode3 + (num == null ? 0 : num.hashCode())) * 31;
        String str3 = this.status;
        return iHashCode4 + (str3 != null ? str3.hashCode() : 0);
    }

    public String toString() {
        String str = this.canonicalTitle;
        Map<String, String> map = this.titles;
        String str2 = this.slug;
        Integer num = this.episodeCount;
        String str3 = this.status;
        StringBuilder sb2 = new StringBuilder("KitsuAnimeAttributes(canonicalTitle=");
        sb2.append(str);
        sb2.append(", titles=");
        sb2.append(map);
        sb2.append(", slug=");
        sb2.append(str2);
        sb2.append(", episodeCount=");
        sb2.append(num);
        sb2.append(", status=");
        return a0.c.p(sb2, str3, ")");
    }
}
