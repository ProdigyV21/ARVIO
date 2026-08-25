package com.arflix.tv.data.model;

import androidx.fragment.app.a2;
import java.io.Serializable;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.h;
import kotlin.jvm.internal.p;
import y.a;

/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u00006\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0010\b\n\u0002\b\u0007\n\u0002\u0010 \n\u0002\b'\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0003\b\u0087\b\u0018\u00002\u00020\u0001B±\u0001\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0005\u0012\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u0005\u0012\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\u0005\u0012\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\u0005\u0012\n\b\u0002\u0010\t\u001a\u0004\u0018\u00010\n\u0012\n\b\u0002\u0010\u000b\u001a\u0004\u0018\u00010\n\u0012\n\b\u0002\u0010\f\u001a\u0004\u0018\u00010\n\u0012\n\b\u0002\u0010\r\u001a\u0004\u0018\u00010\n\u0012\n\b\u0002\u0010\u000e\u001a\u0004\u0018\u00010\n\u0012\n\b\u0002\u0010\u000f\u001a\u0004\u0018\u00010\u0005\u0012\n\b\u0002\u0010\u0010\u001a\u0004\u0018\u00010\u0005\u0012\u0010\b\u0002\u0010\u0011\u001a\n\u0012\u0004\u0012\u00020\u0005\u0018\u00010\u0012\u0012\n\b\u0002\u0010\u0013\u001a\u0004\u0018\u00010\u0005¢\u0006\u0004\b\u0014\u0010\u0015J\t\u0010)\u001a\u00020\u0003HÆ\u0003J\u000b\u0010*\u001a\u0004\u0018\u00010\u0005HÆ\u0003J\u000b\u0010+\u001a\u0004\u0018\u00010\u0005HÆ\u0003J\u000b\u0010,\u001a\u0004\u0018\u00010\u0005HÆ\u0003J\u000b\u0010-\u001a\u0004\u0018\u00010\u0005HÆ\u0003J\u0010\u0010.\u001a\u0004\u0018\u00010\nHÆ\u0003¢\u0006\u0002\u0010\u001eJ\u0010\u0010/\u001a\u0004\u0018\u00010\nHÆ\u0003¢\u0006\u0002\u0010\u001eJ\u0010\u00100\u001a\u0004\u0018\u00010\nHÆ\u0003¢\u0006\u0002\u0010\u001eJ\u0010\u00101\u001a\u0004\u0018\u00010\nHÆ\u0003¢\u0006\u0002\u0010\u001eJ\u0010\u00102\u001a\u0004\u0018\u00010\nHÆ\u0003¢\u0006\u0002\u0010\u001eJ\u000b\u00103\u001a\u0004\u0018\u00010\u0005HÆ\u0003J\u000b\u00104\u001a\u0004\u0018\u00010\u0005HÆ\u0003J\u0011\u00105\u001a\n\u0012\u0004\u0012\u00020\u0005\u0018\u00010\u0012HÆ\u0003J\u000b\u00106\u001a\u0004\u0018\u00010\u0005HÆ\u0003Jº\u0001\u00107\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u00052\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u00052\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\u00052\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\u00052\n\b\u0002\u0010\t\u001a\u0004\u0018\u00010\n2\n\b\u0002\u0010\u000b\u001a\u0004\u0018\u00010\n2\n\b\u0002\u0010\f\u001a\u0004\u0018\u00010\n2\n\b\u0002\u0010\r\u001a\u0004\u0018\u00010\n2\n\b\u0002\u0010\u000e\u001a\u0004\u0018\u00010\n2\n\b\u0002\u0010\u000f\u001a\u0004\u0018\u00010\u00052\n\b\u0002\u0010\u0010\u001a\u0004\u0018\u00010\u00052\u0010\b\u0002\u0010\u0011\u001a\n\u0012\u0004\u0012\u00020\u0005\u0018\u00010\u00122\n\b\u0002\u0010\u0013\u001a\u0004\u0018\u00010\u0005HÆ\u0001¢\u0006\u0002\u00108J\u0014\u00109\u001a\u00020:2\b\u0010;\u001a\u0004\u0018\u00010<HÖ\u0083\u0004J\n\u0010=\u001a\u00020\nHÖ\u0081\u0004J\n\u0010>\u001a\u00020\u0005HÖ\u0081\u0004R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0016\u0010\u0017R\u0013\u0010\u0004\u001a\u0004\u0018\u00010\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0018\u0010\u0019R\u0013\u0010\u0006\u001a\u0004\u0018\u00010\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u001a\u0010\u0019R\u0013\u0010\u0007\u001a\u0004\u0018\u00010\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u001b\u0010\u0019R\u0013\u0010\b\u001a\u0004\u0018\u00010\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u001c\u0010\u0019R\u0015\u0010\t\u001a\u0004\u0018\u00010\n¢\u0006\n\n\u0002\u0010\u001f\u001a\u0004\b\u001d\u0010\u001eR\u0015\u0010\u000b\u001a\u0004\u0018\u00010\n¢\u0006\n\n\u0002\u0010\u001f\u001a\u0004\b \u0010\u001eR\u0015\u0010\f\u001a\u0004\u0018\u00010\n¢\u0006\n\n\u0002\u0010\u001f\u001a\u0004\b!\u0010\u001eR\u0015\u0010\r\u001a\u0004\u0018\u00010\n¢\u0006\n\n\u0002\u0010\u001f\u001a\u0004\b\"\u0010\u001eR\u0015\u0010\u000e\u001a\u0004\u0018\u00010\n¢\u0006\n\n\u0002\u0010\u001f\u001a\u0004\b#\u0010\u001eR\u0013\u0010\u000f\u001a\u0004\u0018\u00010\u0005¢\u0006\b\n\u0000\u001a\u0004\b$\u0010\u0019R\u0013\u0010\u0010\u001a\u0004\u0018\u00010\u0005¢\u0006\b\n\u0000\u001a\u0004\b%\u0010\u0019R\u0019\u0010\u0011\u001a\n\u0012\u0004\u0012\u00020\u0005\u0018\u00010\u0012¢\u0006\b\n\u0000\u001a\u0004\b&\u0010'R\u0013\u0010\u0013\u001a\u0004\u0018\u00010\u0005¢\u0006\b\n\u0000\u001a\u0004\b(\u0010\u0019¨\u0006?"}, d2 = {"Lcom/arflix/tv/data/model/CollectionSourceConfig;", "Ljava/io/Serializable;", "kind", "Lcom/arflix/tv/data/model/CollectionSourceKind;", "mediaType", "", "addonId", "addonCatalogType", "addonCatalogId", "tmdbGenreId", "", "tmdbPersonId", "tmdbCollectionId", "tmdbKeywordId", "tmdbWatchProviderId", "watchRegion", "sortBy", "curatedRefs", "", "mdblistSlug", "<init>", "(Lcom/arflix/tv/data/model/CollectionSourceKind;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/Integer;Ljava/lang/Integer;Ljava/lang/Integer;Ljava/lang/Integer;Ljava/lang/Integer;Ljava/lang/String;Ljava/lang/String;Ljava/util/List;Ljava/lang/String;)V", "getKind", "()Lcom/arflix/tv/data/model/CollectionSourceKind;", "getMediaType", "()Ljava/lang/String;", "getAddonId", "getAddonCatalogType", "getAddonCatalogId", "getTmdbGenreId", "()Ljava/lang/Integer;", "Ljava/lang/Integer;", "getTmdbPersonId", "getTmdbCollectionId", "getTmdbKeywordId", "getTmdbWatchProviderId", "getWatchRegion", "getSortBy", "getCuratedRefs", "()Ljava/util/List;", "getMdblistSlug", "component1", "component2", "component3", "component4", "component5", "component6", "component7", "component8", "component9", "component10", "component11", "component12", "component13", "component14", "copy", "(Lcom/arflix/tv/data/model/CollectionSourceKind;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/Integer;Ljava/lang/Integer;Ljava/lang/Integer;Ljava/lang/Integer;Ljava/lang/Integer;Ljava/lang/String;Ljava/lang/String;Ljava/util/List;Ljava/lang/String;)Lcom/arflix/tv/data/model/CollectionSourceConfig;", "equals", "", "other", "", "hashCode", "toString", "app_playRelease"}, k = 1, mv = {2, 3, 0}, xi = 48)
public final /* data */ class CollectionSourceConfig implements Serializable {
    public static final int $stable = 0;
    private final String addonCatalogId;
    private final String addonCatalogType;
    private final String addonId;
    private final List<String> curatedRefs;
    private final CollectionSourceKind kind;
    private final String mdblistSlug;
    private final String mediaType;
    private final String sortBy;
    private final Integer tmdbCollectionId;
    private final Integer tmdbGenreId;
    private final Integer tmdbKeywordId;
    private final Integer tmdbPersonId;
    private final Integer tmdbWatchProviderId;
    private final String watchRegion;

    public CollectionSourceConfig(CollectionSourceKind collectionSourceKind, String str, String str2, String str3, String str4, Integer num, Integer num2, Integer num3, Integer num4, Integer num5, String str5, String str6, List<String> list, String str7) {
        this.kind = collectionSourceKind;
        this.mediaType = str;
        this.addonId = str2;
        this.addonCatalogType = str3;
        this.addonCatalogId = str4;
        this.tmdbGenreId = num;
        this.tmdbPersonId = num2;
        this.tmdbCollectionId = num3;
        this.tmdbKeywordId = num4;
        this.tmdbWatchProviderId = num5;
        this.watchRegion = str5;
        this.sortBy = str6;
        this.curatedRefs = list;
        this.mdblistSlug = str7;
    }

    /* JADX INFO: renamed from: component1, reason: from getter */
    public final CollectionSourceKind getKind() {
        return this.kind;
    }

    /* JADX INFO: renamed from: component10, reason: from getter */
    public final Integer getTmdbWatchProviderId() {
        return this.tmdbWatchProviderId;
    }

    /* JADX INFO: renamed from: component11, reason: from getter */
    public final String getWatchRegion() {
        return this.watchRegion;
    }

    /* JADX INFO: renamed from: component12, reason: from getter */
    public final String getSortBy() {
        return this.sortBy;
    }

    public final List<String> component13() {
        return this.curatedRefs;
    }

    /* JADX INFO: renamed from: component14, reason: from getter */
    public final String getMdblistSlug() {
        return this.mdblistSlug;
    }

    /* JADX INFO: renamed from: component2, reason: from getter */
    public final String getMediaType() {
        return this.mediaType;
    }

    /* JADX INFO: renamed from: component3, reason: from getter */
    public final String getAddonId() {
        return this.addonId;
    }

    /* JADX INFO: renamed from: component4, reason: from getter */
    public final String getAddonCatalogType() {
        return this.addonCatalogType;
    }

    /* JADX INFO: renamed from: component5, reason: from getter */
    public final String getAddonCatalogId() {
        return this.addonCatalogId;
    }

    /* JADX INFO: renamed from: component6, reason: from getter */
    public final Integer getTmdbGenreId() {
        return this.tmdbGenreId;
    }

    /* JADX INFO: renamed from: component7, reason: from getter */
    public final Integer getTmdbPersonId() {
        return this.tmdbPersonId;
    }

    /* JADX INFO: renamed from: component8, reason: from getter */
    public final Integer getTmdbCollectionId() {
        return this.tmdbCollectionId;
    }

    /* JADX INFO: renamed from: component9, reason: from getter */
    public final Integer getTmdbKeywordId() {
        return this.tmdbKeywordId;
    }

    public final CollectionSourceConfig copy(CollectionSourceKind kind, String mediaType, String addonId, String addonCatalogType, String addonCatalogId, Integer tmdbGenreId, Integer tmdbPersonId, Integer tmdbCollectionId, Integer tmdbKeywordId, Integer tmdbWatchProviderId, String watchRegion, String sortBy, List<String> curatedRefs, String mdblistSlug) {
        return new CollectionSourceConfig(kind, mediaType, addonId, addonCatalogType, addonCatalogId, tmdbGenreId, tmdbPersonId, tmdbCollectionId, tmdbKeywordId, tmdbWatchProviderId, watchRegion, sortBy, curatedRefs, mdblistSlug);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof CollectionSourceConfig)) {
            return false;
        }
        CollectionSourceConfig collectionSourceConfig = (CollectionSourceConfig) other;
        return this.kind == collectionSourceConfig.kind && p.a(this.mediaType, collectionSourceConfig.mediaType) && p.a(this.addonId, collectionSourceConfig.addonId) && p.a(this.addonCatalogType, collectionSourceConfig.addonCatalogType) && p.a(this.addonCatalogId, collectionSourceConfig.addonCatalogId) && p.a(this.tmdbGenreId, collectionSourceConfig.tmdbGenreId) && p.a(this.tmdbPersonId, collectionSourceConfig.tmdbPersonId) && p.a(this.tmdbCollectionId, collectionSourceConfig.tmdbCollectionId) && p.a(this.tmdbKeywordId, collectionSourceConfig.tmdbKeywordId) && p.a(this.tmdbWatchProviderId, collectionSourceConfig.tmdbWatchProviderId) && p.a(this.watchRegion, collectionSourceConfig.watchRegion) && p.a(this.sortBy, collectionSourceConfig.sortBy) && p.a(this.curatedRefs, collectionSourceConfig.curatedRefs) && p.a(this.mdblistSlug, collectionSourceConfig.mdblistSlug);
    }

    public final String getAddonCatalogId() {
        return this.addonCatalogId;
    }

    public final String getAddonCatalogType() {
        return this.addonCatalogType;
    }

    public final String getAddonId() {
        return this.addonId;
    }

    public final List<String> getCuratedRefs() {
        return this.curatedRefs;
    }

    public final CollectionSourceKind getKind() {
        return this.kind;
    }

    public final String getMdblistSlug() {
        return this.mdblistSlug;
    }

    public final String getMediaType() {
        return this.mediaType;
    }

    public final String getSortBy() {
        return this.sortBy;
    }

    public final Integer getTmdbCollectionId() {
        return this.tmdbCollectionId;
    }

    public final Integer getTmdbGenreId() {
        return this.tmdbGenreId;
    }

    public final Integer getTmdbKeywordId() {
        return this.tmdbKeywordId;
    }

    public final Integer getTmdbPersonId() {
        return this.tmdbPersonId;
    }

    public final Integer getTmdbWatchProviderId() {
        return this.tmdbWatchProviderId;
    }

    public final String getWatchRegion() {
        return this.watchRegion;
    }

    public int hashCode() {
        int iHashCode = this.kind.hashCode() * 31;
        String str = this.mediaType;
        int iHashCode2 = (iHashCode + (str == null ? 0 : str.hashCode())) * 31;
        String str2 = this.addonId;
        int iHashCode3 = (iHashCode2 + (str2 == null ? 0 : str2.hashCode())) * 31;
        String str3 = this.addonCatalogType;
        int iHashCode4 = (iHashCode3 + (str3 == null ? 0 : str3.hashCode())) * 31;
        String str4 = this.addonCatalogId;
        int iHashCode5 = (iHashCode4 + (str4 == null ? 0 : str4.hashCode())) * 31;
        Integer num = this.tmdbGenreId;
        int iHashCode6 = (iHashCode5 + (num == null ? 0 : num.hashCode())) * 31;
        Integer num2 = this.tmdbPersonId;
        int iHashCode7 = (iHashCode6 + (num2 == null ? 0 : num2.hashCode())) * 31;
        Integer num3 = this.tmdbCollectionId;
        int iHashCode8 = (iHashCode7 + (num3 == null ? 0 : num3.hashCode())) * 31;
        Integer num4 = this.tmdbKeywordId;
        int iHashCode9 = (iHashCode8 + (num4 == null ? 0 : num4.hashCode())) * 31;
        Integer num5 = this.tmdbWatchProviderId;
        int iHashCode10 = (iHashCode9 + (num5 == null ? 0 : num5.hashCode())) * 31;
        String str5 = this.watchRegion;
        int iHashCode11 = (iHashCode10 + (str5 == null ? 0 : str5.hashCode())) * 31;
        String str6 = this.sortBy;
        int iHashCode12 = (iHashCode11 + (str6 == null ? 0 : str6.hashCode())) * 31;
        List<String> list = this.curatedRefs;
        int iHashCode13 = (iHashCode12 + (list == null ? 0 : list.hashCode())) * 31;
        String str7 = this.mdblistSlug;
        return iHashCode13 + (str7 != null ? str7.hashCode() : 0);
    }

    public String toString() {
        CollectionSourceKind collectionSourceKind = this.kind;
        String str = this.mediaType;
        String str2 = this.addonId;
        String str3 = this.addonCatalogType;
        String str4 = this.addonCatalogId;
        Integer num = this.tmdbGenreId;
        Integer num2 = this.tmdbPersonId;
        Integer num3 = this.tmdbCollectionId;
        Integer num4 = this.tmdbKeywordId;
        Integer num5 = this.tmdbWatchProviderId;
        String str5 = this.watchRegion;
        String str6 = this.sortBy;
        List<String> list = this.curatedRefs;
        String str7 = this.mdblistSlug;
        StringBuilder sb2 = new StringBuilder("CollectionSourceConfig(kind=");
        sb2.append(collectionSourceKind);
        sb2.append(", mediaType=");
        sb2.append(str);
        sb2.append(", addonId=");
        a.i(sb2, str2, ", addonCatalogType=", str3, ", addonCatalogId=");
        sb2.append(str4);
        sb2.append(", tmdbGenreId=");
        sb2.append(num);
        sb2.append(", tmdbPersonId=");
        a2.z(sb2, num2, ", tmdbCollectionId=", num3, ", tmdbKeywordId=");
        a2.z(sb2, num4, ", tmdbWatchProviderId=", num5, ", watchRegion=");
        a.i(sb2, str5, ", sortBy=", str6, ", curatedRefs=");
        sb2.append(list);
        sb2.append(", mdblistSlug=");
        sb2.append(str7);
        sb2.append(")");
        return sb2.toString();
    }

    public /* synthetic */ CollectionSourceConfig(CollectionSourceKind collectionSourceKind, String str, String str2, String str3, String str4, Integer num, Integer num2, Integer num3, Integer num4, Integer num5, String str5, String str6, List list, String str7, int i10, h hVar) {
        this(collectionSourceKind, (i10 & 2) != 0 ? null : str, (i10 & 4) != 0 ? null : str2, (i10 & 8) != 0 ? null : str3, (i10 & 16) != 0 ? null : str4, (i10 & 32) != 0 ? null : num, (i10 & 64) != 0 ? null : num2, (i10 & 128) != 0 ? null : num3, (i10 & 256) != 0 ? null : num4, (i10 & 512) != 0 ? null : num5, (i10 & 1024) != 0 ? null : str5, (i10 & 2048) != 0 ? null : str6, (i10 & 4096) != 0 ? null : list, (i10 & 8192) != 0 ? null : str7);
    }
}
