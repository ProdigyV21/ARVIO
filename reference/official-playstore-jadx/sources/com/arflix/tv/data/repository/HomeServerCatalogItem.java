package com.arflix.tv.data.repository;

import androidx.fragment.app.a2;
import androidx.media3.extractor.text.ttml.TtmlNode;
import com.arflix.tv.data.model.MediaType;
import io.ktor.http.LinkHeader;
import java.util.Map;
import kotlin.Metadata;

/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u0000>\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010$\n\u0002\b\u0002\n\u0002\u0010\u0006\n\u0002\b\u0003\n\u0002\u0010\t\n\u0002\b'\n\u0002\u0010\u000b\n\u0002\b\u0004\b\u0087\b\u0018\u00002\u00020\u0001B\u0087\u0001\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u0012\b\u0010\u0007\u001a\u0004\u0018\u00010\b\u0012\u0012\u0010\t\u001a\u000e\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u00030\n\u0012\b\b\u0002\u0010\u000b\u001a\u00020\u0003\u0012\n\b\u0002\u0010\f\u001a\u0004\u0018\u00010\r\u0012\b\b\u0002\u0010\u000e\u001a\u00020\u0003\u0012\n\b\u0002\u0010\u000f\u001a\u0004\u0018\u00010\u0003\u0012\b\b\u0002\u0010\u0010\u001a\u00020\u0011\u0012\b\b\u0002\u0010\u0012\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0013\u001a\u00020\u0003¢\u0006\u0004\b\u0014\u0010\u0015J\t\u0010*\u001a\u00020\u0003HÆ\u0003J\t\u0010+\u001a\u00020\u0003HÆ\u0003J\t\u0010,\u001a\u00020\u0006HÆ\u0003J\u0010\u0010-\u001a\u0004\u0018\u00010\bHÆ\u0003¢\u0006\u0002\u0010\u001cJ\u0015\u0010.\u001a\u000e\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u00030\nHÆ\u0003J\t\u0010/\u001a\u00020\u0003HÆ\u0003J\u0010\u00100\u001a\u0004\u0018\u00010\rHÆ\u0003¢\u0006\u0002\u0010\"J\t\u00101\u001a\u00020\u0003HÆ\u0003J\u000b\u00102\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\t\u00103\u001a\u00020\u0011HÆ\u0003J\t\u00104\u001a\u00020\u0003HÆ\u0003J\t\u00105\u001a\u00020\u0003HÆ\u0003J\u0098\u0001\u00106\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u00062\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\b2\u0014\b\u0002\u0010\t\u001a\u000e\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u00030\n2\b\b\u0002\u0010\u000b\u001a\u00020\u00032\n\b\u0002\u0010\f\u001a\u0004\u0018\u00010\r2\b\b\u0002\u0010\u000e\u001a\u00020\u00032\n\b\u0002\u0010\u000f\u001a\u0004\u0018\u00010\u00032\b\b\u0002\u0010\u0010\u001a\u00020\u00112\b\b\u0002\u0010\u0012\u001a\u00020\u00032\b\b\u0002\u0010\u0013\u001a\u00020\u0003HÆ\u0001¢\u0006\u0002\u00107J\u0014\u00108\u001a\u0002092\b\u0010:\u001a\u0004\u0018\u00010\u0001HÖ\u0083\u0004J\n\u0010;\u001a\u00020\bHÖ\u0081\u0004J\n\u0010<\u001a\u00020\u0003HÖ\u0081\u0004R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0016\u0010\u0017R\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0018\u0010\u0017R\u0011\u0010\u0005\u001a\u00020\u0006¢\u0006\b\n\u0000\u001a\u0004\b\u0019\u0010\u001aR\u0015\u0010\u0007\u001a\u0004\u0018\u00010\b¢\u0006\n\n\u0002\u0010\u001d\u001a\u0004\b\u001b\u0010\u001cR\u001d\u0010\t\u001a\u000e\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u00030\n¢\u0006\b\n\u0000\u001a\u0004\b\u001e\u0010\u001fR\u0011\u0010\u000b\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b \u0010\u0017R\u0015\u0010\f\u001a\u0004\u0018\u00010\r¢\u0006\n\n\u0002\u0010#\u001a\u0004\b!\u0010\"R\u0011\u0010\u000e\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b$\u0010\u0017R\u0013\u0010\u000f\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b%\u0010\u0017R\u0011\u0010\u0010\u001a\u00020\u0011¢\u0006\b\n\u0000\u001a\u0004\b&\u0010'R\u0011\u0010\u0012\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b(\u0010\u0017R\u0011\u0010\u0013\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b)\u0010\u0017¨\u0006="}, d2 = {"Lcom/arflix/tv/data/repository/HomeServerCatalogItem;", "", TtmlNode.ATTR_ID, "", LinkHeader.Parameters.Title, "mediaType", "Lcom/arflix/tv/data/model/MediaType;", "year", "", "providerIds", "", "overview", "rating", "", "imageUrl", "backdropUrl", "addedAt", "", "sourceRef", "providerName", "<init>", "(Ljava/lang/String;Ljava/lang/String;Lcom/arflix/tv/data/model/MediaType;Ljava/lang/Integer;Ljava/util/Map;Ljava/lang/String;Ljava/lang/Double;Ljava/lang/String;Ljava/lang/String;JLjava/lang/String;Ljava/lang/String;)V", "getId", "()Ljava/lang/String;", "getTitle", "getMediaType", "()Lcom/arflix/tv/data/model/MediaType;", "getYear", "()Ljava/lang/Integer;", "Ljava/lang/Integer;", "getProviderIds", "()Ljava/util/Map;", "getOverview", "getRating", "()Ljava/lang/Double;", "Ljava/lang/Double;", "getImageUrl", "getBackdropUrl", "getAddedAt", "()J", "getSourceRef", "getProviderName", "component1", "component2", "component3", "component4", "component5", "component6", "component7", "component8", "component9", "component10", "component11", "component12", "copy", "(Ljava/lang/String;Ljava/lang/String;Lcom/arflix/tv/data/model/MediaType;Ljava/lang/Integer;Ljava/util/Map;Ljava/lang/String;Ljava/lang/Double;Ljava/lang/String;Ljava/lang/String;JLjava/lang/String;Ljava/lang/String;)Lcom/arflix/tv/data/repository/HomeServerCatalogItem;", "equals", "", "other", "hashCode", "toString", "app_playRelease"}, k = 1, mv = {2, 3, 0}, xi = 48)
public final /* data */ class HomeServerCatalogItem {
    public static final int $stable = 0;
    private final long addedAt;
    private final String backdropUrl;
    private final String id;
    private final String imageUrl;
    private final MediaType mediaType;
    private final String overview;
    private final Map<String, String> providerIds;
    private final String providerName;
    private final Double rating;
    private final String sourceRef;
    private final String title;
    private final Integer year;

    public HomeServerCatalogItem(String str, String str2, MediaType mediaType, Integer num, Map<String, String> map, String str3, Double d4, String str4, String str5, long j10, String str6, String str7) {
        this.id = str;
        this.title = str2;
        this.mediaType = mediaType;
        this.year = num;
        this.providerIds = map;
        this.overview = str3;
        this.rating = d4;
        this.imageUrl = str4;
        this.backdropUrl = str5;
        this.addedAt = j10;
        this.sourceRef = str6;
        this.providerName = str7;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static /* synthetic */ HomeServerCatalogItem copy$default(HomeServerCatalogItem homeServerCatalogItem, String str, String str2, MediaType mediaType, Integer num, Map map, String str3, Double d4, String str4, String str5, long j10, String str6, String str7, int i10, Object obj) {
        if ((i10 & 1) != 0) {
            str = homeServerCatalogItem.id;
        }
        return homeServerCatalogItem.copy(str, (i10 & 2) != 0 ? homeServerCatalogItem.title : str2, (i10 & 4) != 0 ? homeServerCatalogItem.mediaType : mediaType, (i10 & 8) != 0 ? homeServerCatalogItem.year : num, (i10 & 16) != 0 ? homeServerCatalogItem.providerIds : map, (i10 & 32) != 0 ? homeServerCatalogItem.overview : str3, (i10 & 64) != 0 ? homeServerCatalogItem.rating : d4, (i10 & 128) != 0 ? homeServerCatalogItem.imageUrl : str4, (i10 & 256) != 0 ? homeServerCatalogItem.backdropUrl : str5, (i10 & 512) != 0 ? homeServerCatalogItem.addedAt : j10, (i10 & 1024) != 0 ? homeServerCatalogItem.sourceRef : str6, (i10 & 2048) != 0 ? homeServerCatalogItem.providerName : str7);
    }

    /* JADX INFO: renamed from: component1, reason: from getter */
    public final String getId() {
        return this.id;
    }

    /* JADX INFO: renamed from: component10, reason: from getter */
    public final long getAddedAt() {
        return this.addedAt;
    }

    /* JADX INFO: renamed from: component11, reason: from getter */
    public final String getSourceRef() {
        return this.sourceRef;
    }

    /* JADX INFO: renamed from: component12, reason: from getter */
    public final String getProviderName() {
        return this.providerName;
    }

    /* JADX INFO: renamed from: component2, reason: from getter */
    public final String getTitle() {
        return this.title;
    }

    /* JADX INFO: renamed from: component3, reason: from getter */
    public final MediaType getMediaType() {
        return this.mediaType;
    }

    /* JADX INFO: renamed from: component4, reason: from getter */
    public final Integer getYear() {
        return this.year;
    }

    public final Map<String, String> component5() {
        return this.providerIds;
    }

    /* JADX INFO: renamed from: component6, reason: from getter */
    public final String getOverview() {
        return this.overview;
    }

    /* JADX INFO: renamed from: component7, reason: from getter */
    public final Double getRating() {
        return this.rating;
    }

    /* JADX INFO: renamed from: component8, reason: from getter */
    public final String getImageUrl() {
        return this.imageUrl;
    }

    /* JADX INFO: renamed from: component9, reason: from getter */
    public final String getBackdropUrl() {
        return this.backdropUrl;
    }

    public final HomeServerCatalogItem copy(String id, String title, MediaType mediaType, Integer year, Map<String, String> providerIds, String overview, Double rating, String imageUrl, String backdropUrl, long addedAt, String sourceRef, String providerName) {
        return new HomeServerCatalogItem(id, title, mediaType, year, providerIds, overview, rating, imageUrl, backdropUrl, addedAt, sourceRef, providerName);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof HomeServerCatalogItem)) {
            return false;
        }
        HomeServerCatalogItem homeServerCatalogItem = (HomeServerCatalogItem) other;
        return kotlin.jvm.internal.p.a(this.id, homeServerCatalogItem.id) && kotlin.jvm.internal.p.a(this.title, homeServerCatalogItem.title) && this.mediaType == homeServerCatalogItem.mediaType && kotlin.jvm.internal.p.a(this.year, homeServerCatalogItem.year) && kotlin.jvm.internal.p.a(this.providerIds, homeServerCatalogItem.providerIds) && kotlin.jvm.internal.p.a(this.overview, homeServerCatalogItem.overview) && kotlin.jvm.internal.p.a(this.rating, homeServerCatalogItem.rating) && kotlin.jvm.internal.p.a(this.imageUrl, homeServerCatalogItem.imageUrl) && kotlin.jvm.internal.p.a(this.backdropUrl, homeServerCatalogItem.backdropUrl) && this.addedAt == homeServerCatalogItem.addedAt && kotlin.jvm.internal.p.a(this.sourceRef, homeServerCatalogItem.sourceRef) && kotlin.jvm.internal.p.a(this.providerName, homeServerCatalogItem.providerName);
    }

    public final long getAddedAt() {
        return this.addedAt;
    }

    public final String getBackdropUrl() {
        return this.backdropUrl;
    }

    public final String getId() {
        return this.id;
    }

    public final String getImageUrl() {
        return this.imageUrl;
    }

    public final MediaType getMediaType() {
        return this.mediaType;
    }

    public final String getOverview() {
        return this.overview;
    }

    public final Map<String, String> getProviderIds() {
        return this.providerIds;
    }

    public final String getProviderName() {
        return this.providerName;
    }

    public final Double getRating() {
        return this.rating;
    }

    public final String getSourceRef() {
        return this.sourceRef;
    }

    public final String getTitle() {
        return this.title;
    }

    public final Integer getYear() {
        return this.year;
    }

    public int hashCode() {
        int iHashCode = (this.mediaType.hashCode() + androidx.compose.foundation.c.c(this.id.hashCode() * 31, 31, this.title)) * 31;
        Integer num = this.year;
        int iC = androidx.compose.foundation.c.c(a2.g(this.providerIds, (iHashCode + (num == null ? 0 : num.hashCode())) * 31, 31), 31, this.overview);
        Double d4 = this.rating;
        int iC2 = androidx.compose.foundation.c.c((iC + (d4 == null ? 0 : d4.hashCode())) * 31, 31, this.imageUrl);
        String str = this.backdropUrl;
        int iHashCode2 = str != null ? str.hashCode() : 0;
        long j10 = this.addedAt;
        return this.providerName.hashCode() + androidx.compose.foundation.c.c((((iC2 + iHashCode2) * 31) + ((int) (j10 ^ (j10 >>> 32)))) * 31, 31, this.sourceRef);
    }

    public String toString() {
        String str = this.id;
        String str2 = this.title;
        MediaType mediaType = this.mediaType;
        Integer num = this.year;
        Map<String, String> map = this.providerIds;
        String str3 = this.overview;
        Double d4 = this.rating;
        String str4 = this.imageUrl;
        String str5 = this.backdropUrl;
        long j10 = this.addedAt;
        String str6 = this.sourceRef;
        String str7 = this.providerName;
        StringBuilder sbR = a2.r("HomeServerCatalogItem(id=", str, ", title=", str2, ", mediaType=");
        sbR.append(mediaType);
        sbR.append(", year=");
        sbR.append(num);
        sbR.append(", providerIds=");
        sbR.append(map);
        sbR.append(", overview=");
        sbR.append(str3);
        sbR.append(", rating=");
        sbR.append(d4);
        sbR.append(", imageUrl=");
        sbR.append(str4);
        sbR.append(", backdropUrl=");
        sbR.append(str5);
        sbR.append(", addedAt=");
        sbR.append(j10);
        y.a.i(sbR, ", sourceRef=", str6, ", providerName=", str7);
        sbR.append(")");
        return sbR.toString();
    }

    public /* synthetic */ HomeServerCatalogItem(String str, String str2, MediaType mediaType, Integer num, Map map, String str3, Double d4, String str4, String str5, long j10, String str6, String str7, int i10, kotlin.jvm.internal.h hVar) {
        this(str, str2, mediaType, num, map, (i10 & 32) != 0 ? "" : str3, (i10 & 64) != 0 ? null : d4, (i10 & 128) != 0 ? "" : str4, (i10 & 256) != 0 ? null : str5, (i10 & 512) != 0 ? 0L : j10, (i10 & 1024) != 0 ? "" : str6, (i10 & 2048) != 0 ? "" : str7);
    }
}
