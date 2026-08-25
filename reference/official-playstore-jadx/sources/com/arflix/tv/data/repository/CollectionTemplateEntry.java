package com.arflix.tv.data.repository;

import androidx.fragment.app.a2;
import androidx.media3.extractor.text.ttml.TtmlNode;
import com.arflix.tv.data.model.CollectionGroupKind;
import com.arflix.tv.data.model.CollectionSourceConfig;
import com.arflix.tv.data.model.CollectionTileShape;
import io.ktor.http.LinkHeader;
import java.util.List;
import kotlin.Metadata;

/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u0000B\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u001d\n\u0002\u0010\b\n\u0002\b\u0002\b\u0081\b\u0018\u00002\u00020\u0001B]\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u0012\u0006\u0010\u0007\u001a\u00020\u0003\u0012\u0006\u0010\b\u001a\u00020\t\u0012\u0006\u0010\n\u001a\u00020\u000b\u0012\b\u0010\f\u001a\u0004\u0018\u00010\u0003\u0012\f\u0010\r\u001a\b\u0012\u0004\u0012\u00020\u000f0\u000e\u0012\f\u0010\u0010\u001a\b\u0012\u0004\u0012\u00020\u00110\u000e¢\u0006\u0004\b\u0012\u0010\u0013J\t\u0010\"\u001a\u00020\u0003HÆ\u0003J\t\u0010#\u001a\u00020\u0003HÆ\u0003J\t\u0010$\u001a\u00020\u0006HÆ\u0003J\t\u0010%\u001a\u00020\u0003HÆ\u0003J\t\u0010&\u001a\u00020\tHÆ\u0003J\t\u0010'\u001a\u00020\u000bHÆ\u0003J\u000b\u0010(\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000f\u0010)\u001a\b\u0012\u0004\u0012\u00020\u000f0\u000eHÆ\u0003J\u000f\u0010*\u001a\b\u0012\u0004\u0012\u00020\u00110\u000eHÆ\u0003Jq\u0010+\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u00062\b\b\u0002\u0010\u0007\u001a\u00020\u00032\b\b\u0002\u0010\b\u001a\u00020\t2\b\b\u0002\u0010\n\u001a\u00020\u000b2\n\b\u0002\u0010\f\u001a\u0004\u0018\u00010\u00032\u000e\b\u0002\u0010\r\u001a\b\u0012\u0004\u0012\u00020\u000f0\u000e2\u000e\b\u0002\u0010\u0010\u001a\b\u0012\u0004\u0012\u00020\u00110\u000eHÆ\u0001J\u0014\u0010,\u001a\u00020\u000b2\b\u0010-\u001a\u0004\u0018\u00010\u0001HÖ\u0083\u0004J\n\u0010.\u001a\u00020/HÖ\u0081\u0004J\n\u00100\u001a\u00020\u0003HÖ\u0081\u0004R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\u0015R\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0016\u0010\u0015R\u0011\u0010\u0005\u001a\u00020\u0006¢\u0006\b\n\u0000\u001a\u0004\b\u0017\u0010\u0018R\u0011\u0010\u0007\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0019\u0010\u0015R\u0011\u0010\b\u001a\u00020\t¢\u0006\b\n\u0000\u001a\u0004\b\u001a\u0010\u001bR\u0011\u0010\n\u001a\u00020\u000b¢\u0006\b\n\u0000\u001a\u0004\b\u001c\u0010\u001dR\u0013\u0010\f\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u001e\u0010\u0015R\u0017\u0010\r\u001a\b\u0012\u0004\u0012\u00020\u000f0\u000e¢\u0006\b\n\u0000\u001a\u0004\b\u001f\u0010 R\u0017\u0010\u0010\u001a\b\u0012\u0004\u0012\u00020\u00110\u000e¢\u0006\b\n\u0000\u001a\u0004\b!\u0010 ¨\u00061"}, d2 = {"Lcom/arflix/tv/data/repository/CollectionTemplateEntry;", "", TtmlNode.ATTR_ID, "", LinkHeader.Parameters.Title, "group", "Lcom/arflix/tv/data/model/CollectionGroupKind;", "coverImageUrl", "tileShape", "Lcom/arflix/tv/data/model/CollectionTileShape;", "hideTitle", "", "heroVideoUrl", "sources", "", "Lcom/arflix/tv/data/model/CollectionSourceConfig;", "listMetadata", "Lcom/arflix/tv/data/repository/CollectionSourceListMetadata;", "<init>", "(Ljava/lang/String;Ljava/lang/String;Lcom/arflix/tv/data/model/CollectionGroupKind;Ljava/lang/String;Lcom/arflix/tv/data/model/CollectionTileShape;ZLjava/lang/String;Ljava/util/List;Ljava/util/List;)V", "getId", "()Ljava/lang/String;", "getTitle", "getGroup", "()Lcom/arflix/tv/data/model/CollectionGroupKind;", "getCoverImageUrl", "getTileShape", "()Lcom/arflix/tv/data/model/CollectionTileShape;", "getHideTitle", "()Z", "getHeroVideoUrl", "getSources", "()Ljava/util/List;", "getListMetadata", "component1", "component2", "component3", "component4", "component5", "component6", "component7", "component8", "component9", "copy", "equals", "other", "hashCode", "", "toString", "app_playRelease"}, k = 1, mv = {2, 3, 0}, xi = 48)
public final /* data */ class CollectionTemplateEntry {
    public static final int $stable = 0;
    private final String coverImageUrl;
    private final CollectionGroupKind group;
    private final String heroVideoUrl;
    private final boolean hideTitle;
    private final String id;
    private final List<CollectionSourceListMetadata> listMetadata;
    private final List<CollectionSourceConfig> sources;
    private final CollectionTileShape tileShape;
    private final String title;

    public CollectionTemplateEntry(String str, String str2, CollectionGroupKind collectionGroupKind, String str3, CollectionTileShape collectionTileShape, boolean z, String str4, List<CollectionSourceConfig> list, List<CollectionSourceListMetadata> list2) {
        this.id = str;
        this.title = str2;
        this.group = collectionGroupKind;
        this.coverImageUrl = str3;
        this.tileShape = collectionTileShape;
        this.hideTitle = z;
        this.heroVideoUrl = str4;
        this.sources = list;
        this.listMetadata = list2;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static /* synthetic */ CollectionTemplateEntry copy$default(CollectionTemplateEntry collectionTemplateEntry, String str, String str2, CollectionGroupKind collectionGroupKind, String str3, CollectionTileShape collectionTileShape, boolean z, String str4, List list, List list2, int i10, Object obj) {
        if ((i10 & 1) != 0) {
            str = collectionTemplateEntry.id;
        }
        if ((i10 & 2) != 0) {
            str2 = collectionTemplateEntry.title;
        }
        if ((i10 & 4) != 0) {
            collectionGroupKind = collectionTemplateEntry.group;
        }
        if ((i10 & 8) != 0) {
            str3 = collectionTemplateEntry.coverImageUrl;
        }
        if ((i10 & 16) != 0) {
            collectionTileShape = collectionTemplateEntry.tileShape;
        }
        if ((i10 & 32) != 0) {
            z = collectionTemplateEntry.hideTitle;
        }
        if ((i10 & 64) != 0) {
            str4 = collectionTemplateEntry.heroVideoUrl;
        }
        if ((i10 & 128) != 0) {
            list = collectionTemplateEntry.sources;
        }
        if ((i10 & 256) != 0) {
            list2 = collectionTemplateEntry.listMetadata;
        }
        List list3 = list;
        List list4 = list2;
        boolean z5 = z;
        String str5 = str4;
        CollectionTileShape collectionTileShape2 = collectionTileShape;
        CollectionGroupKind collectionGroupKind2 = collectionGroupKind;
        return collectionTemplateEntry.copy(str, str2, collectionGroupKind2, str3, collectionTileShape2, z5, str5, list3, list4);
    }

    /* JADX INFO: renamed from: component1, reason: from getter */
    public final String getId() {
        return this.id;
    }

    /* JADX INFO: renamed from: component2, reason: from getter */
    public final String getTitle() {
        return this.title;
    }

    /* JADX INFO: renamed from: component3, reason: from getter */
    public final CollectionGroupKind getGroup() {
        return this.group;
    }

    /* JADX INFO: renamed from: component4, reason: from getter */
    public final String getCoverImageUrl() {
        return this.coverImageUrl;
    }

    /* JADX INFO: renamed from: component5, reason: from getter */
    public final CollectionTileShape getTileShape() {
        return this.tileShape;
    }

    /* JADX INFO: renamed from: component6, reason: from getter */
    public final boolean getHideTitle() {
        return this.hideTitle;
    }

    /* JADX INFO: renamed from: component7, reason: from getter */
    public final String getHeroVideoUrl() {
        return this.heroVideoUrl;
    }

    public final List<CollectionSourceConfig> component8() {
        return this.sources;
    }

    public final List<CollectionSourceListMetadata> component9() {
        return this.listMetadata;
    }

    public final CollectionTemplateEntry copy(String id, String title, CollectionGroupKind group, String coverImageUrl, CollectionTileShape tileShape, boolean hideTitle, String heroVideoUrl, List<CollectionSourceConfig> sources, List<CollectionSourceListMetadata> listMetadata) {
        return new CollectionTemplateEntry(id, title, group, coverImageUrl, tileShape, hideTitle, heroVideoUrl, sources, listMetadata);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof CollectionTemplateEntry)) {
            return false;
        }
        CollectionTemplateEntry collectionTemplateEntry = (CollectionTemplateEntry) other;
        return kotlin.jvm.internal.p.a(this.id, collectionTemplateEntry.id) && kotlin.jvm.internal.p.a(this.title, collectionTemplateEntry.title) && this.group == collectionTemplateEntry.group && kotlin.jvm.internal.p.a(this.coverImageUrl, collectionTemplateEntry.coverImageUrl) && this.tileShape == collectionTemplateEntry.tileShape && this.hideTitle == collectionTemplateEntry.hideTitle && kotlin.jvm.internal.p.a(this.heroVideoUrl, collectionTemplateEntry.heroVideoUrl) && kotlin.jvm.internal.p.a(this.sources, collectionTemplateEntry.sources) && kotlin.jvm.internal.p.a(this.listMetadata, collectionTemplateEntry.listMetadata);
    }

    public final String getCoverImageUrl() {
        return this.coverImageUrl;
    }

    public final CollectionGroupKind getGroup() {
        return this.group;
    }

    public final String getHeroVideoUrl() {
        return this.heroVideoUrl;
    }

    public final boolean getHideTitle() {
        return this.hideTitle;
    }

    public final String getId() {
        return this.id;
    }

    public final List<CollectionSourceListMetadata> getListMetadata() {
        return this.listMetadata;
    }

    public final List<CollectionSourceConfig> getSources() {
        return this.sources;
    }

    public final CollectionTileShape getTileShape() {
        return this.tileShape;
    }

    public final String getTitle() {
        return this.title;
    }

    public int hashCode() {
        int iHashCode = (((this.tileShape.hashCode() + androidx.compose.foundation.c.c((this.group.hashCode() + androidx.compose.foundation.c.c(this.id.hashCode() * 31, 31, this.title)) * 31, 31, this.coverImageUrl)) * 31) + (this.hideTitle ? 1231 : 1237)) * 31;
        String str = this.heroVideoUrl;
        return this.listMetadata.hashCode() + androidx.compose.material3.d.e(this.sources, (iHashCode + (str == null ? 0 : str.hashCode())) * 31, 31);
    }

    public String toString() {
        String str = this.id;
        String str2 = this.title;
        CollectionGroupKind collectionGroupKind = this.group;
        String str3 = this.coverImageUrl;
        CollectionTileShape collectionTileShape = this.tileShape;
        boolean z = this.hideTitle;
        String str4 = this.heroVideoUrl;
        List<CollectionSourceConfig> list = this.sources;
        List<CollectionSourceListMetadata> list2 = this.listMetadata;
        StringBuilder sbR = a2.r("CollectionTemplateEntry(id=", str, ", title=", str2, ", group=");
        sbR.append(collectionGroupKind);
        sbR.append(", coverImageUrl=");
        sbR.append(str3);
        sbR.append(", tileShape=");
        sbR.append(collectionTileShape);
        sbR.append(", hideTitle=");
        sbR.append(z);
        sbR.append(", heroVideoUrl=");
        sbR.append(str4);
        sbR.append(", sources=");
        sbR.append(list);
        sbR.append(", listMetadata=");
        return a0.c.q(sbR, list2, ")");
    }
}
