package com.arflix.tv.data.repository;

import androidx.fragment.app.a2;
import kotlin.Metadata;

/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0010\b\n\u0002\b\u001a\n\u0002\u0010\u000b\n\u0002\b\u0004\b\u0081\b\u0018\u00002\u00020\u0001BQ\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\b\u0010\u0004\u001a\u0004\u0018\u00010\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0003\u0012\u0006\u0010\u0006\u001a\u00020\u0003\u0012\b\u0010\u0007\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\b\u001a\u0004\u0018\u00010\t\u0012\b\u0010\n\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\u000b\u001a\u0004\u0018\u00010\u0003¢\u0006\u0004\b\f\u0010\rJ\t\u0010\u0019\u001a\u00020\u0003HÆ\u0003J\u000b\u0010\u001a\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\t\u0010\u001b\u001a\u00020\u0003HÆ\u0003J\t\u0010\u001c\u001a\u00020\u0003HÆ\u0003J\u000b\u0010\u001d\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u0010\u0010\u001e\u001a\u0004\u0018\u00010\tHÆ\u0003¢\u0006\u0002\u0010\u0015J\u000b\u0010\u001f\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u0010 \u001a\u0004\u0018\u00010\u0003HÆ\u0003Jh\u0010!\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u00032\b\b\u0002\u0010\u0006\u001a\u00020\u00032\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\t2\n\b\u0002\u0010\n\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u000b\u001a\u0004\u0018\u00010\u0003HÆ\u0001¢\u0006\u0002\u0010\"J\u0014\u0010#\u001a\u00020$2\b\u0010%\u001a\u0004\u0018\u00010\u0001HÖ\u0083\u0004J\n\u0010&\u001a\u00020\tHÖ\u0081\u0004J\n\u0010'\u001a\u00020\u0003HÖ\u0081\u0004R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000fR\u0013\u0010\u0004\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u000fR\u0011\u0010\u0005\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u000fR\u0011\u0010\u0006\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u000fR\u0013\u0010\u0007\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u000fR\u0015\u0010\b\u001a\u0004\u0018\u00010\t¢\u0006\n\n\u0002\u0010\u0016\u001a\u0004\b\u0014\u0010\u0015R\u0013\u0010\n\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0017\u0010\u000fR\u0013\u0010\u000b\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0018\u0010\u000f¨\u0006("}, d2 = {"Lcom/arflix/tv/data/repository/CollectionSourceListMetadata;", "", "sourceCatalogId", "", "sourceAddonId", "sourceName", "sourceLabel", "mediaType", "itemCount", "", "author", "url", "<init>", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/Integer;Ljava/lang/String;Ljava/lang/String;)V", "getSourceCatalogId", "()Ljava/lang/String;", "getSourceAddonId", "getSourceName", "getSourceLabel", "getMediaType", "getItemCount", "()Ljava/lang/Integer;", "Ljava/lang/Integer;", "getAuthor", "getUrl", "component1", "component2", "component3", "component4", "component5", "component6", "component7", "component8", "copy", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/Integer;Ljava/lang/String;Ljava/lang/String;)Lcom/arflix/tv/data/repository/CollectionSourceListMetadata;", "equals", "", "other", "hashCode", "toString", "app_playRelease"}, k = 1, mv = {2, 3, 0}, xi = 48)
public final /* data */ class CollectionSourceListMetadata {
    public static final int $stable = 0;
    private final String author;
    private final Integer itemCount;
    private final String mediaType;
    private final String sourceAddonId;
    private final String sourceCatalogId;
    private final String sourceLabel;
    private final String sourceName;
    private final String url;

    public CollectionSourceListMetadata(String str, String str2, String str3, String str4, String str5, Integer num, String str6, String str7) {
        this.sourceCatalogId = str;
        this.sourceAddonId = str2;
        this.sourceName = str3;
        this.sourceLabel = str4;
        this.mediaType = str5;
        this.itemCount = num;
        this.author = str6;
        this.url = str7;
    }

    public static /* synthetic */ CollectionSourceListMetadata copy$default(CollectionSourceListMetadata collectionSourceListMetadata, String str, String str2, String str3, String str4, String str5, Integer num, String str6, String str7, int i10, Object obj) {
        if ((i10 & 1) != 0) {
            str = collectionSourceListMetadata.sourceCatalogId;
        }
        if ((i10 & 2) != 0) {
            str2 = collectionSourceListMetadata.sourceAddonId;
        }
        if ((i10 & 4) != 0) {
            str3 = collectionSourceListMetadata.sourceName;
        }
        if ((i10 & 8) != 0) {
            str4 = collectionSourceListMetadata.sourceLabel;
        }
        if ((i10 & 16) != 0) {
            str5 = collectionSourceListMetadata.mediaType;
        }
        if ((i10 & 32) != 0) {
            num = collectionSourceListMetadata.itemCount;
        }
        if ((i10 & 64) != 0) {
            str6 = collectionSourceListMetadata.author;
        }
        if ((i10 & 128) != 0) {
            str7 = collectionSourceListMetadata.url;
        }
        String str8 = str6;
        String str9 = str7;
        String str10 = str5;
        Integer num2 = num;
        return collectionSourceListMetadata.copy(str, str2, str3, str4, str10, num2, str8, str9);
    }

    /* JADX INFO: renamed from: component1, reason: from getter */
    public final String getSourceCatalogId() {
        return this.sourceCatalogId;
    }

    /* JADX INFO: renamed from: component2, reason: from getter */
    public final String getSourceAddonId() {
        return this.sourceAddonId;
    }

    /* JADX INFO: renamed from: component3, reason: from getter */
    public final String getSourceName() {
        return this.sourceName;
    }

    /* JADX INFO: renamed from: component4, reason: from getter */
    public final String getSourceLabel() {
        return this.sourceLabel;
    }

    /* JADX INFO: renamed from: component5, reason: from getter */
    public final String getMediaType() {
        return this.mediaType;
    }

    /* JADX INFO: renamed from: component6, reason: from getter */
    public final Integer getItemCount() {
        return this.itemCount;
    }

    /* JADX INFO: renamed from: component7, reason: from getter */
    public final String getAuthor() {
        return this.author;
    }

    /* JADX INFO: renamed from: component8, reason: from getter */
    public final String getUrl() {
        return this.url;
    }

    public final CollectionSourceListMetadata copy(String sourceCatalogId, String sourceAddonId, String sourceName, String sourceLabel, String mediaType, Integer itemCount, String author, String url) {
        return new CollectionSourceListMetadata(sourceCatalogId, sourceAddonId, sourceName, sourceLabel, mediaType, itemCount, author, url);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof CollectionSourceListMetadata)) {
            return false;
        }
        CollectionSourceListMetadata collectionSourceListMetadata = (CollectionSourceListMetadata) other;
        return kotlin.jvm.internal.p.a(this.sourceCatalogId, collectionSourceListMetadata.sourceCatalogId) && kotlin.jvm.internal.p.a(this.sourceAddonId, collectionSourceListMetadata.sourceAddonId) && kotlin.jvm.internal.p.a(this.sourceName, collectionSourceListMetadata.sourceName) && kotlin.jvm.internal.p.a(this.sourceLabel, collectionSourceListMetadata.sourceLabel) && kotlin.jvm.internal.p.a(this.mediaType, collectionSourceListMetadata.mediaType) && kotlin.jvm.internal.p.a(this.itemCount, collectionSourceListMetadata.itemCount) && kotlin.jvm.internal.p.a(this.author, collectionSourceListMetadata.author) && kotlin.jvm.internal.p.a(this.url, collectionSourceListMetadata.url);
    }

    public final String getAuthor() {
        return this.author;
    }

    public final Integer getItemCount() {
        return this.itemCount;
    }

    public final String getMediaType() {
        return this.mediaType;
    }

    public final String getSourceAddonId() {
        return this.sourceAddonId;
    }

    public final String getSourceCatalogId() {
        return this.sourceCatalogId;
    }

    public final String getSourceLabel() {
        return this.sourceLabel;
    }

    public final String getSourceName() {
        return this.sourceName;
    }

    public final String getUrl() {
        return this.url;
    }

    public int hashCode() {
        int iHashCode = this.sourceCatalogId.hashCode() * 31;
        String str = this.sourceAddonId;
        int iC = androidx.compose.foundation.c.c(androidx.compose.foundation.c.c((iHashCode + (str == null ? 0 : str.hashCode())) * 31, 31, this.sourceName), 31, this.sourceLabel);
        String str2 = this.mediaType;
        int iHashCode2 = (iC + (str2 == null ? 0 : str2.hashCode())) * 31;
        Integer num = this.itemCount;
        int iHashCode3 = (iHashCode2 + (num == null ? 0 : num.hashCode())) * 31;
        String str3 = this.author;
        int iHashCode4 = (iHashCode3 + (str3 == null ? 0 : str3.hashCode())) * 31;
        String str4 = this.url;
        return iHashCode4 + (str4 != null ? str4.hashCode() : 0);
    }

    public String toString() {
        String str = this.sourceCatalogId;
        String str2 = this.sourceAddonId;
        String str3 = this.sourceName;
        String str4 = this.sourceLabel;
        String str5 = this.mediaType;
        Integer num = this.itemCount;
        String str6 = this.author;
        String str7 = this.url;
        StringBuilder sbR = a2.r("CollectionSourceListMetadata(sourceCatalogId=", str, ", sourceAddonId=", str2, ", sourceName=");
        y.a.i(sbR, str3, ", sourceLabel=", str4, ", mediaType=");
        sbR.append(str5);
        sbR.append(", itemCount=");
        sbR.append(num);
        sbR.append(", author=");
        return a2.n(sbR, str6, ", url=", str7, ")");
    }
}
