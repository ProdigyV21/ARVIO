package com.arflix.tv.data.model;

import androidx.compose.foundation.c;
import androidx.fragment.app.a2;
import androidx.media3.extractor.text.ttml.TtmlNode;
import com.google.android.gms.cast.MediaTrack;
import io.ktor.http.LinkHeader;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.z;
import kotlin.jvm.internal.h;
import kotlin.jvm.internal.p;
import y.a;

/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\b \n\u0002\u0010\u000b\n\u0002\b\u0004\b\u0087\b\u0018\u00002\u00020\u0001Bs\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\b\u0010\u0005\u001a\u0004\u0018\u00010\u0003\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\u0003\u0012\b\u0010\t\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\n\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\u000b\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\f\u001a\u0004\u0018\u00010\r\u0012\b\u0010\u000e\u001a\u0004\u0018\u00010\r\u0012\u000e\b\u0002\u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\u00030\u0010¢\u0006\u0004\b\u0011\u0010\u0012J\t\u0010#\u001a\u00020\u0003HÆ\u0003J\t\u0010$\u001a\u00020\u0003HÆ\u0003J\u000b\u0010%\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\t\u0010&\u001a\u00020\u0007HÆ\u0003J\t\u0010'\u001a\u00020\u0003HÆ\u0003J\u000b\u0010(\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u0010)\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u0010*\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u0010\u0010+\u001a\u0004\u0018\u00010\rHÆ\u0003¢\u0006\u0002\u0010\u001eJ\u0010\u0010,\u001a\u0004\u0018\u00010\rHÆ\u0003¢\u0006\u0002\u0010\u001eJ\u000f\u0010-\u001a\b\u0012\u0004\u0012\u00020\u00030\u0010HÆ\u0003J\u008e\u0001\u0010.\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u00032\b\b\u0002\u0010\u0006\u001a\u00020\u00072\b\b\u0002\u0010\b\u001a\u00020\u00032\n\b\u0002\u0010\t\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\n\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u000b\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\f\u001a\u0004\u0018\u00010\r2\n\b\u0002\u0010\u000e\u001a\u0004\u0018\u00010\r2\u000e\b\u0002\u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\u00030\u0010HÆ\u0001¢\u0006\u0002\u0010/J\u0014\u00100\u001a\u0002012\b\u00102\u001a\u0004\u0018\u00010\u0001HÖ\u0083\u0004J\n\u00103\u001a\u00020\rHÖ\u0081\u0004J\n\u00104\u001a\u00020\u0003HÖ\u0081\u0004R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u0014R\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0015\u0010\u0014R\u0013\u0010\u0005\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0016\u0010\u0014R\u0011\u0010\u0006\u001a\u00020\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u0017\u0010\u0018R\u0011\u0010\b\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0019\u0010\u0014R\u0013\u0010\t\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u001a\u0010\u0014R\u0013\u0010\n\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u001b\u0010\u0014R\u0013\u0010\u000b\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u001c\u0010\u0014R\u0015\u0010\f\u001a\u0004\u0018\u00010\r¢\u0006\n\n\u0002\u0010\u001f\u001a\u0004\b\u001d\u0010\u001eR\u0015\u0010\u000e\u001a\u0004\u0018\u00010\r¢\u0006\n\n\u0002\u0010\u001f\u001a\u0004\b \u0010\u001eR\u0017\u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\u00030\u0010¢\u0006\b\n\u0000\u001a\u0004\b!\u0010\"¨\u00065"}, d2 = {"Lcom/arflix/tv/data/model/CatalogDiscoveryResult;", "", TtmlNode.ATTR_ID, "", LinkHeader.Parameters.Title, MediaTrack.ROLE_DESCRIPTION, "sourceType", "Lcom/arflix/tv/data/model/CatalogSourceType;", "sourceUrl", "creatorName", "creatorHandle", "updatedAt", "itemCount", "", "likes", "previewPosterUrls", "", "<init>", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Lcom/arflix/tv/data/model/CatalogSourceType;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/Integer;Ljava/lang/Integer;Ljava/util/List;)V", "getId", "()Ljava/lang/String;", "getTitle", "getDescription", "getSourceType", "()Lcom/arflix/tv/data/model/CatalogSourceType;", "getSourceUrl", "getCreatorName", "getCreatorHandle", "getUpdatedAt", "getItemCount", "()Ljava/lang/Integer;", "Ljava/lang/Integer;", "getLikes", "getPreviewPosterUrls", "()Ljava/util/List;", "component1", "component2", "component3", "component4", "component5", "component6", "component7", "component8", "component9", "component10", "component11", "copy", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Lcom/arflix/tv/data/model/CatalogSourceType;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/Integer;Ljava/lang/Integer;Ljava/util/List;)Lcom/arflix/tv/data/model/CatalogDiscoveryResult;", "equals", "", "other", "hashCode", "toString", "app_playRelease"}, k = 1, mv = {2, 3, 0}, xi = 48)
public final /* data */ class CatalogDiscoveryResult {
    public static final int $stable = 0;
    private final String creatorHandle;
    private final String creatorName;
    private final String description;
    private final String id;
    private final Integer itemCount;
    private final Integer likes;
    private final List<String> previewPosterUrls;
    private final CatalogSourceType sourceType;
    private final String sourceUrl;
    private final String title;
    private final String updatedAt;

    public CatalogDiscoveryResult(String str, String str2, String str3, CatalogSourceType catalogSourceType, String str4, String str5, String str6, String str7, Integer num, Integer num2, List<String> list) {
        this.id = str;
        this.title = str2;
        this.description = str3;
        this.sourceType = catalogSourceType;
        this.sourceUrl = str4;
        this.creatorName = str5;
        this.creatorHandle = str6;
        this.updatedAt = str7;
        this.itemCount = num;
        this.likes = num2;
        this.previewPosterUrls = list;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static /* synthetic */ CatalogDiscoveryResult copy$default(CatalogDiscoveryResult catalogDiscoveryResult, String str, String str2, String str3, CatalogSourceType catalogSourceType, String str4, String str5, String str6, String str7, Integer num, Integer num2, List list, int i10, Object obj) {
        if ((i10 & 1) != 0) {
            str = catalogDiscoveryResult.id;
        }
        if ((i10 & 2) != 0) {
            str2 = catalogDiscoveryResult.title;
        }
        if ((i10 & 4) != 0) {
            str3 = catalogDiscoveryResult.description;
        }
        if ((i10 & 8) != 0) {
            catalogSourceType = catalogDiscoveryResult.sourceType;
        }
        if ((i10 & 16) != 0) {
            str4 = catalogDiscoveryResult.sourceUrl;
        }
        if ((i10 & 32) != 0) {
            str5 = catalogDiscoveryResult.creatorName;
        }
        if ((i10 & 64) != 0) {
            str6 = catalogDiscoveryResult.creatorHandle;
        }
        if ((i10 & 128) != 0) {
            str7 = catalogDiscoveryResult.updatedAt;
        }
        if ((i10 & 256) != 0) {
            num = catalogDiscoveryResult.itemCount;
        }
        if ((i10 & 512) != 0) {
            num2 = catalogDiscoveryResult.likes;
        }
        if ((i10 & 1024) != 0) {
            list = catalogDiscoveryResult.previewPosterUrls;
        }
        Integer num3 = num2;
        List list2 = list;
        String str8 = str7;
        Integer num4 = num;
        String str9 = str5;
        String str10 = str6;
        String str11 = str4;
        String str12 = str3;
        return catalogDiscoveryResult.copy(str, str2, str12, catalogSourceType, str11, str9, str10, str8, num4, num3, list2);
    }

    /* JADX INFO: renamed from: component1, reason: from getter */
    public final String getId() {
        return this.id;
    }

    /* JADX INFO: renamed from: component10, reason: from getter */
    public final Integer getLikes() {
        return this.likes;
    }

    public final List<String> component11() {
        return this.previewPosterUrls;
    }

    /* JADX INFO: renamed from: component2, reason: from getter */
    public final String getTitle() {
        return this.title;
    }

    /* JADX INFO: renamed from: component3, reason: from getter */
    public final String getDescription() {
        return this.description;
    }

    /* JADX INFO: renamed from: component4, reason: from getter */
    public final CatalogSourceType getSourceType() {
        return this.sourceType;
    }

    /* JADX INFO: renamed from: component5, reason: from getter */
    public final String getSourceUrl() {
        return this.sourceUrl;
    }

    /* JADX INFO: renamed from: component6, reason: from getter */
    public final String getCreatorName() {
        return this.creatorName;
    }

    /* JADX INFO: renamed from: component7, reason: from getter */
    public final String getCreatorHandle() {
        return this.creatorHandle;
    }

    /* JADX INFO: renamed from: component8, reason: from getter */
    public final String getUpdatedAt() {
        return this.updatedAt;
    }

    /* JADX INFO: renamed from: component9, reason: from getter */
    public final Integer getItemCount() {
        return this.itemCount;
    }

    public final CatalogDiscoveryResult copy(String id, String title, String description, CatalogSourceType sourceType, String sourceUrl, String creatorName, String creatorHandle, String updatedAt, Integer itemCount, Integer likes, List<String> previewPosterUrls) {
        return new CatalogDiscoveryResult(id, title, description, sourceType, sourceUrl, creatorName, creatorHandle, updatedAt, itemCount, likes, previewPosterUrls);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof CatalogDiscoveryResult)) {
            return false;
        }
        CatalogDiscoveryResult catalogDiscoveryResult = (CatalogDiscoveryResult) other;
        return p.a(this.id, catalogDiscoveryResult.id) && p.a(this.title, catalogDiscoveryResult.title) && p.a(this.description, catalogDiscoveryResult.description) && this.sourceType == catalogDiscoveryResult.sourceType && p.a(this.sourceUrl, catalogDiscoveryResult.sourceUrl) && p.a(this.creatorName, catalogDiscoveryResult.creatorName) && p.a(this.creatorHandle, catalogDiscoveryResult.creatorHandle) && p.a(this.updatedAt, catalogDiscoveryResult.updatedAt) && p.a(this.itemCount, catalogDiscoveryResult.itemCount) && p.a(this.likes, catalogDiscoveryResult.likes) && p.a(this.previewPosterUrls, catalogDiscoveryResult.previewPosterUrls);
    }

    public final String getCreatorHandle() {
        return this.creatorHandle;
    }

    public final String getCreatorName() {
        return this.creatorName;
    }

    public final String getDescription() {
        return this.description;
    }

    public final String getId() {
        return this.id;
    }

    public final Integer getItemCount() {
        return this.itemCount;
    }

    public final Integer getLikes() {
        return this.likes;
    }

    public final List<String> getPreviewPosterUrls() {
        return this.previewPosterUrls;
    }

    public final CatalogSourceType getSourceType() {
        return this.sourceType;
    }

    public final String getSourceUrl() {
        return this.sourceUrl;
    }

    public final String getTitle() {
        return this.title;
    }

    public final String getUpdatedAt() {
        return this.updatedAt;
    }

    public int hashCode() {
        int iC = c.c(this.id.hashCode() * 31, 31, this.title);
        String str = this.description;
        int iC2 = c.c((this.sourceType.hashCode() + ((iC + (str == null ? 0 : str.hashCode())) * 31)) * 31, 31, this.sourceUrl);
        String str2 = this.creatorName;
        int iHashCode = (iC2 + (str2 == null ? 0 : str2.hashCode())) * 31;
        String str3 = this.creatorHandle;
        int iHashCode2 = (iHashCode + (str3 == null ? 0 : str3.hashCode())) * 31;
        String str4 = this.updatedAt;
        int iHashCode3 = (iHashCode2 + (str4 == null ? 0 : str4.hashCode())) * 31;
        Integer num = this.itemCount;
        int iHashCode4 = (iHashCode3 + (num == null ? 0 : num.hashCode())) * 31;
        Integer num2 = this.likes;
        return this.previewPosterUrls.hashCode() + ((iHashCode4 + (num2 != null ? num2.hashCode() : 0)) * 31);
    }

    public String toString() {
        String str = this.id;
        String str2 = this.title;
        String str3 = this.description;
        CatalogSourceType catalogSourceType = this.sourceType;
        String str4 = this.sourceUrl;
        String str5 = this.creatorName;
        String str6 = this.creatorHandle;
        String str7 = this.updatedAt;
        Integer num = this.itemCount;
        Integer num2 = this.likes;
        List<String> list = this.previewPosterUrls;
        StringBuilder sbR = a2.r("CatalogDiscoveryResult(id=", str, ", title=", str2, ", description=");
        sbR.append(str3);
        sbR.append(", sourceType=");
        sbR.append(catalogSourceType);
        sbR.append(", sourceUrl=");
        a.i(sbR, str4, ", creatorName=", str5, ", creatorHandle=");
        a.i(sbR, str6, ", updatedAt=", str7, ", itemCount=");
        a2.z(sbR, num, ", likes=", num2, ", previewPosterUrls=");
        return a0.c.q(sbR, list, ")");
    }

    public /* synthetic */ CatalogDiscoveryResult(String str, String str2, String str3, CatalogSourceType catalogSourceType, String str4, String str5, String str6, String str7, Integer num, Integer num2, List list, int i10, h hVar) {
        this(str, str2, str3, catalogSourceType, str4, str5, str6, str7, num, num2, (i10 & 1024) != 0 ? z.f19728i : list);
    }
}
