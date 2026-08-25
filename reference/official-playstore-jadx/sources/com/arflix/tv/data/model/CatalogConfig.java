package com.arflix.tv.data.model;

import androidx.compose.foundation.c;
import androidx.compose.material3.d;
import androidx.fragment.app.a2;
import androidx.media3.exoplayer.source.ProgressiveMediaSource;
import androidx.media3.extractor.text.ttml.TtmlNode;
import androidx.media3.session.MediaUtils;
import io.ktor.http.LinkHeader;
import java.io.Serializable;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.z;
import kotlin.jvm.internal.h;
import kotlin.jvm.internal.p;
import y.a;

/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u0000R\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b@\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\b\u0087\b\u0018\u00002\u00020\u0001B§\u0002\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u0012\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\u0003\u0012\b\b\u0002\u0010\t\u001a\u00020\n\u0012\n\b\u0002\u0010\u000b\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\f\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\r\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\u000e\u001a\u0004\u0018\u00010\u0003\u0012\b\b\u0002\u0010\u000f\u001a\u00020\u0010\u0012\n\b\u0002\u0010\u0011\u001a\u0004\u0018\u00010\u0012\u0012\n\b\u0002\u0010\u0013\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\u0014\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\u0015\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\u0016\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\u0017\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\u0018\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\u0019\u001a\u0004\u0018\u00010\u0003\u0012\b\b\u0002\u0010\u001a\u001a\u00020\u001b\u0012\b\b\u0002\u0010\u001c\u001a\u00020\n\u0012\u000e\b\u0002\u0010\u001d\u001a\b\u0012\u0004\u0012\u00020\u001f0\u001e\u0012\u000e\b\u0002\u0010 \u001a\b\u0012\u0004\u0012\u00020\u00030\u001e\u0012\n\b\u0002\u0010!\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\"\u001a\u0004\u0018\u00010\u0003¢\u0006\u0004\b#\u0010$J\t\u0010D\u001a\u00020\u0003HÆ\u0003J\t\u0010E\u001a\u00020\u0003HÆ\u0003J\t\u0010F\u001a\u00020\u0006HÆ\u0003J\u000b\u0010G\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u0010H\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\t\u0010I\u001a\u00020\nHÆ\u0003J\u000b\u0010J\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u0010K\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u0010L\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u0010M\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\t\u0010N\u001a\u00020\u0010HÆ\u0003J\u000b\u0010O\u001a\u0004\u0018\u00010\u0012HÆ\u0003J\u000b\u0010P\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u0010Q\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u0010R\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u0010S\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u0010T\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u0010U\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u0010V\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\t\u0010W\u001a\u00020\u001bHÆ\u0003J\t\u0010X\u001a\u00020\nHÆ\u0003J\u000f\u0010Y\u001a\b\u0012\u0004\u0012\u00020\u001f0\u001eHÆ\u0003J\u000f\u0010Z\u001a\b\u0012\u0004\u0012\u00020\u00030\u001eHÆ\u0003J\u000b\u0010[\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u0010\\\u001a\u0004\u0018\u00010\u0003HÆ\u0003J¯\u0002\u0010]\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u00062\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\u00032\b\b\u0002\u0010\t\u001a\u00020\n2\n\b\u0002\u0010\u000b\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\f\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\r\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u000e\u001a\u0004\u0018\u00010\u00032\b\b\u0002\u0010\u000f\u001a\u00020\u00102\n\b\u0002\u0010\u0011\u001a\u0004\u0018\u00010\u00122\n\b\u0002\u0010\u0013\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0014\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0015\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0016\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0017\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0018\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0019\u001a\u0004\u0018\u00010\u00032\b\b\u0002\u0010\u001a\u001a\u00020\u001b2\b\b\u0002\u0010\u001c\u001a\u00020\n2\u000e\b\u0002\u0010\u001d\u001a\b\u0012\u0004\u0012\u00020\u001f0\u001e2\u000e\b\u0002\u0010 \u001a\b\u0012\u0004\u0012\u00020\u00030\u001e2\n\b\u0002\u0010!\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\"\u001a\u0004\u0018\u00010\u0003HÆ\u0001J\u0014\u0010^\u001a\u00020\n2\b\u0010_\u001a\u0004\u0018\u00010`HÖ\u0083\u0004J\n\u0010a\u001a\u00020bHÖ\u0081\u0004J\n\u0010c\u001a\u00020\u0003HÖ\u0081\u0004R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b%\u0010&R\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b'\u0010&R\u0011\u0010\u0005\u001a\u00020\u0006¢\u0006\b\n\u0000\u001a\u0004\b(\u0010)R\u0013\u0010\u0007\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b*\u0010&R\u0013\u0010\b\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b+\u0010&R\u0011\u0010\t\u001a\u00020\n¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010,R\u0013\u0010\u000b\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b-\u0010&R\u0013\u0010\f\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b.\u0010&R\u0013\u0010\r\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b/\u0010&R\u0013\u0010\u000e\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b0\u0010&R\u0011\u0010\u000f\u001a\u00020\u0010¢\u0006\b\n\u0000\u001a\u0004\b1\u00102R\u0013\u0010\u0011\u001a\u0004\u0018\u00010\u0012¢\u0006\b\n\u0000\u001a\u0004\b3\u00104R\u0013\u0010\u0013\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b5\u0010&R\u0013\u0010\u0014\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b6\u0010&R\u0013\u0010\u0015\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b7\u0010&R\u0013\u0010\u0016\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b8\u0010&R\u0013\u0010\u0017\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b9\u0010&R\u0013\u0010\u0018\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b:\u0010&R\u0013\u0010\u0019\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b;\u0010&R\u0011\u0010\u001a\u001a\u00020\u001b¢\u0006\b\n\u0000\u001a\u0004\b<\u0010=R\u0011\u0010\u001c\u001a\u00020\n¢\u0006\b\n\u0000\u001a\u0004\b>\u0010,R\u0017\u0010\u001d\u001a\b\u0012\u0004\u0012\u00020\u001f0\u001e¢\u0006\b\n\u0000\u001a\u0004\b?\u0010@R\u0017\u0010 \u001a\b\u0012\u0004\u0012\u00020\u00030\u001e¢\u0006\b\n\u0000\u001a\u0004\bA\u0010@R\u0013\u0010!\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\bB\u0010&R\u0013\u0010\"\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\bC\u0010&¨\u0006d"}, d2 = {"Lcom/arflix/tv/data/model/CatalogConfig;", "Ljava/io/Serializable;", TtmlNode.ATTR_ID, "", LinkHeader.Parameters.Title, "sourceType", "Lcom/arflix/tv/data/model/CatalogSourceType;", "sourceUrl", "sourceRef", "isPreinstalled", "", "addonId", "addonCatalogType", "addonCatalogId", "addonName", "kind", "Lcom/arflix/tv/data/model/CatalogKind;", "collectionGroup", "Lcom/arflix/tv/data/model/CollectionGroupKind;", "collectionDescription", "collectionCoverImageUrl", "collectionFocusGifUrl", "collectionHeroImageUrl", "collectionHeroGifUrl", "collectionHeroVideoUrl", "collectionClearLogoUrl", "collectionTileShape", "Lcom/arflix/tv/data/model/CollectionTileShape;", "collectionHideTitle", "collectionSources", "", "Lcom/arflix/tv/data/model/CollectionSourceConfig;", "requiredAddonUrls", "packId", "packName", "<init>", "(Ljava/lang/String;Ljava/lang/String;Lcom/arflix/tv/data/model/CatalogSourceType;Ljava/lang/String;Ljava/lang/String;ZLjava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Lcom/arflix/tv/data/model/CatalogKind;Lcom/arflix/tv/data/model/CollectionGroupKind;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Lcom/arflix/tv/data/model/CollectionTileShape;ZLjava/util/List;Ljava/util/List;Ljava/lang/String;Ljava/lang/String;)V", "getId", "()Ljava/lang/String;", "getTitle", "getSourceType", "()Lcom/arflix/tv/data/model/CatalogSourceType;", "getSourceUrl", "getSourceRef", "()Z", "getAddonId", "getAddonCatalogType", "getAddonCatalogId", "getAddonName", "getKind", "()Lcom/arflix/tv/data/model/CatalogKind;", "getCollectionGroup", "()Lcom/arflix/tv/data/model/CollectionGroupKind;", "getCollectionDescription", "getCollectionCoverImageUrl", "getCollectionFocusGifUrl", "getCollectionHeroImageUrl", "getCollectionHeroGifUrl", "getCollectionHeroVideoUrl", "getCollectionClearLogoUrl", "getCollectionTileShape", "()Lcom/arflix/tv/data/model/CollectionTileShape;", "getCollectionHideTitle", "getCollectionSources", "()Ljava/util/List;", "getRequiredAddonUrls", "getPackId", "getPackName", "component1", "component2", "component3", "component4", "component5", "component6", "component7", "component8", "component9", "component10", "component11", "component12", "component13", "component14", "component15", "component16", "component17", "component18", "component19", "component20", "component21", "component22", "component23", "component24", "component25", "copy", "equals", "other", "", "hashCode", "", "toString", "app_playRelease"}, k = 1, mv = {2, 3, 0}, xi = 48)
public final /* data */ class CatalogConfig implements Serializable {
    public static final int $stable = 0;
    private final String addonCatalogId;
    private final String addonCatalogType;
    private final String addonId;
    private final String addonName;
    private final String collectionClearLogoUrl;
    private final String collectionCoverImageUrl;
    private final String collectionDescription;
    private final String collectionFocusGifUrl;
    private final CollectionGroupKind collectionGroup;
    private final String collectionHeroGifUrl;
    private final String collectionHeroImageUrl;
    private final String collectionHeroVideoUrl;
    private final boolean collectionHideTitle;
    private final List<CollectionSourceConfig> collectionSources;
    private final CollectionTileShape collectionTileShape;
    private final String id;
    private final boolean isPreinstalled;
    private final CatalogKind kind;
    private final String packId;
    private final String packName;
    private final List<String> requiredAddonUrls;
    private final String sourceRef;
    private final CatalogSourceType sourceType;
    private final String sourceUrl;
    private final String title;

    public CatalogConfig(String str, String str2, CatalogSourceType catalogSourceType, String str3, String str4, boolean z, String str5, String str6, String str7, String str8, CatalogKind catalogKind, CollectionGroupKind collectionGroupKind, String str9, String str10, String str11, String str12, String str13, String str14, String str15, CollectionTileShape collectionTileShape, boolean z5, List<CollectionSourceConfig> list, List<String> list2, String str16, String str17) {
        this.id = str;
        this.title = str2;
        this.sourceType = catalogSourceType;
        this.sourceUrl = str3;
        this.sourceRef = str4;
        this.isPreinstalled = z;
        this.addonId = str5;
        this.addonCatalogType = str6;
        this.addonCatalogId = str7;
        this.addonName = str8;
        this.kind = catalogKind;
        this.collectionGroup = collectionGroupKind;
        this.collectionDescription = str9;
        this.collectionCoverImageUrl = str10;
        this.collectionFocusGifUrl = str11;
        this.collectionHeroImageUrl = str12;
        this.collectionHeroGifUrl = str13;
        this.collectionHeroVideoUrl = str14;
        this.collectionClearLogoUrl = str15;
        this.collectionTileShape = collectionTileShape;
        this.collectionHideTitle = z5;
        this.collectionSources = list;
        this.requiredAddonUrls = list2;
        this.packId = str16;
        this.packName = str17;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static /* synthetic */ CatalogConfig copy$default(CatalogConfig catalogConfig, String str, String str2, CatalogSourceType catalogSourceType, String str3, String str4, boolean z, String str5, String str6, String str7, String str8, CatalogKind catalogKind, CollectionGroupKind collectionGroupKind, String str9, String str10, String str11, String str12, String str13, String str14, String str15, CollectionTileShape collectionTileShape, boolean z5, List list, List list2, String str16, String str17, int i10, Object obj) {
        String str18;
        String str19;
        String str20 = (i10 & 1) != 0 ? catalogConfig.id : str;
        String str21 = (i10 & 2) != 0 ? catalogConfig.title : str2;
        CatalogSourceType catalogSourceType2 = (i10 & 4) != 0 ? catalogConfig.sourceType : catalogSourceType;
        String str22 = (i10 & 8) != 0 ? catalogConfig.sourceUrl : str3;
        String str23 = (i10 & 16) != 0 ? catalogConfig.sourceRef : str4;
        boolean z10 = (i10 & 32) != 0 ? catalogConfig.isPreinstalled : z;
        String str24 = (i10 & 64) != 0 ? catalogConfig.addonId : str5;
        String str25 = (i10 & 128) != 0 ? catalogConfig.addonCatalogType : str6;
        String str26 = (i10 & 256) != 0 ? catalogConfig.addonCatalogId : str7;
        String str27 = (i10 & 512) != 0 ? catalogConfig.addonName : str8;
        CatalogKind catalogKind2 = (i10 & 1024) != 0 ? catalogConfig.kind : catalogKind;
        CollectionGroupKind collectionGroupKind2 = (i10 & 2048) != 0 ? catalogConfig.collectionGroup : collectionGroupKind;
        String str28 = (i10 & 4096) != 0 ? catalogConfig.collectionDescription : str9;
        String str29 = (i10 & 8192) != 0 ? catalogConfig.collectionCoverImageUrl : str10;
        String str30 = str20;
        String str31 = (i10 & 16384) != 0 ? catalogConfig.collectionFocusGifUrl : str11;
        String str32 = (i10 & 32768) != 0 ? catalogConfig.collectionHeroImageUrl : str12;
        String str33 = (i10 & 65536) != 0 ? catalogConfig.collectionHeroGifUrl : str13;
        String str34 = (i10 & 131072) != 0 ? catalogConfig.collectionHeroVideoUrl : str14;
        String str35 = (i10 & MediaUtils.TRANSACTION_SIZE_LIMIT_IN_BYTES) != 0 ? catalogConfig.collectionClearLogoUrl : str15;
        CollectionTileShape collectionTileShape2 = (i10 & 524288) != 0 ? catalogConfig.collectionTileShape : collectionTileShape;
        boolean z11 = (i10 & ProgressiveMediaSource.DEFAULT_LOADING_CHECK_INTERVAL_BYTES) != 0 ? catalogConfig.collectionHideTitle : z5;
        List list3 = (i10 & 2097152) != 0 ? catalogConfig.collectionSources : list;
        List list4 = (i10 & 4194304) != 0 ? catalogConfig.requiredAddonUrls : list2;
        String str36 = (i10 & 8388608) != 0 ? catalogConfig.packId : str16;
        if ((i10 & 16777216) != 0) {
            str19 = str36;
            str18 = catalogConfig.packName;
        } else {
            str18 = str17;
            str19 = str36;
        }
        return catalogConfig.copy(str30, str21, catalogSourceType2, str22, str23, z10, str24, str25, str26, str27, catalogKind2, collectionGroupKind2, str28, str29, str31, str32, str33, str34, str35, collectionTileShape2, z11, list3, list4, str19, str18);
    }

    /* JADX INFO: renamed from: component1, reason: from getter */
    public final String getId() {
        return this.id;
    }

    /* JADX INFO: renamed from: component10, reason: from getter */
    public final String getAddonName() {
        return this.addonName;
    }

    /* JADX INFO: renamed from: component11, reason: from getter */
    public final CatalogKind getKind() {
        return this.kind;
    }

    /* JADX INFO: renamed from: component12, reason: from getter */
    public final CollectionGroupKind getCollectionGroup() {
        return this.collectionGroup;
    }

    /* JADX INFO: renamed from: component13, reason: from getter */
    public final String getCollectionDescription() {
        return this.collectionDescription;
    }

    /* JADX INFO: renamed from: component14, reason: from getter */
    public final String getCollectionCoverImageUrl() {
        return this.collectionCoverImageUrl;
    }

    /* JADX INFO: renamed from: component15, reason: from getter */
    public final String getCollectionFocusGifUrl() {
        return this.collectionFocusGifUrl;
    }

    /* JADX INFO: renamed from: component16, reason: from getter */
    public final String getCollectionHeroImageUrl() {
        return this.collectionHeroImageUrl;
    }

    /* JADX INFO: renamed from: component17, reason: from getter */
    public final String getCollectionHeroGifUrl() {
        return this.collectionHeroGifUrl;
    }

    /* JADX INFO: renamed from: component18, reason: from getter */
    public final String getCollectionHeroVideoUrl() {
        return this.collectionHeroVideoUrl;
    }

    /* JADX INFO: renamed from: component19, reason: from getter */
    public final String getCollectionClearLogoUrl() {
        return this.collectionClearLogoUrl;
    }

    /* JADX INFO: renamed from: component2, reason: from getter */
    public final String getTitle() {
        return this.title;
    }

    /* JADX INFO: renamed from: component20, reason: from getter */
    public final CollectionTileShape getCollectionTileShape() {
        return this.collectionTileShape;
    }

    /* JADX INFO: renamed from: component21, reason: from getter */
    public final boolean getCollectionHideTitle() {
        return this.collectionHideTitle;
    }

    public final List<CollectionSourceConfig> component22() {
        return this.collectionSources;
    }

    public final List<String> component23() {
        return this.requiredAddonUrls;
    }

    /* JADX INFO: renamed from: component24, reason: from getter */
    public final String getPackId() {
        return this.packId;
    }

    /* JADX INFO: renamed from: component25, reason: from getter */
    public final String getPackName() {
        return this.packName;
    }

    /* JADX INFO: renamed from: component3, reason: from getter */
    public final CatalogSourceType getSourceType() {
        return this.sourceType;
    }

    /* JADX INFO: renamed from: component4, reason: from getter */
    public final String getSourceUrl() {
        return this.sourceUrl;
    }

    /* JADX INFO: renamed from: component5, reason: from getter */
    public final String getSourceRef() {
        return this.sourceRef;
    }

    /* JADX INFO: renamed from: component6, reason: from getter */
    public final boolean getIsPreinstalled() {
        return this.isPreinstalled;
    }

    /* JADX INFO: renamed from: component7, reason: from getter */
    public final String getAddonId() {
        return this.addonId;
    }

    /* JADX INFO: renamed from: component8, reason: from getter */
    public final String getAddonCatalogType() {
        return this.addonCatalogType;
    }

    /* JADX INFO: renamed from: component9, reason: from getter */
    public final String getAddonCatalogId() {
        return this.addonCatalogId;
    }

    public final CatalogConfig copy(String id, String title, CatalogSourceType sourceType, String sourceUrl, String sourceRef, boolean isPreinstalled, String addonId, String addonCatalogType, String addonCatalogId, String addonName, CatalogKind kind, CollectionGroupKind collectionGroup, String collectionDescription, String collectionCoverImageUrl, String collectionFocusGifUrl, String collectionHeroImageUrl, String collectionHeroGifUrl, String collectionHeroVideoUrl, String collectionClearLogoUrl, CollectionTileShape collectionTileShape, boolean collectionHideTitle, List<CollectionSourceConfig> collectionSources, List<String> requiredAddonUrls, String packId, String packName) {
        return new CatalogConfig(id, title, sourceType, sourceUrl, sourceRef, isPreinstalled, addonId, addonCatalogType, addonCatalogId, addonName, kind, collectionGroup, collectionDescription, collectionCoverImageUrl, collectionFocusGifUrl, collectionHeroImageUrl, collectionHeroGifUrl, collectionHeroVideoUrl, collectionClearLogoUrl, collectionTileShape, collectionHideTitle, collectionSources, requiredAddonUrls, packId, packName);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof CatalogConfig)) {
            return false;
        }
        CatalogConfig catalogConfig = (CatalogConfig) other;
        return p.a(this.id, catalogConfig.id) && p.a(this.title, catalogConfig.title) && this.sourceType == catalogConfig.sourceType && p.a(this.sourceUrl, catalogConfig.sourceUrl) && p.a(this.sourceRef, catalogConfig.sourceRef) && this.isPreinstalled == catalogConfig.isPreinstalled && p.a(this.addonId, catalogConfig.addonId) && p.a(this.addonCatalogType, catalogConfig.addonCatalogType) && p.a(this.addonCatalogId, catalogConfig.addonCatalogId) && p.a(this.addonName, catalogConfig.addonName) && this.kind == catalogConfig.kind && this.collectionGroup == catalogConfig.collectionGroup && p.a(this.collectionDescription, catalogConfig.collectionDescription) && p.a(this.collectionCoverImageUrl, catalogConfig.collectionCoverImageUrl) && p.a(this.collectionFocusGifUrl, catalogConfig.collectionFocusGifUrl) && p.a(this.collectionHeroImageUrl, catalogConfig.collectionHeroImageUrl) && p.a(this.collectionHeroGifUrl, catalogConfig.collectionHeroGifUrl) && p.a(this.collectionHeroVideoUrl, catalogConfig.collectionHeroVideoUrl) && p.a(this.collectionClearLogoUrl, catalogConfig.collectionClearLogoUrl) && this.collectionTileShape == catalogConfig.collectionTileShape && this.collectionHideTitle == catalogConfig.collectionHideTitle && p.a(this.collectionSources, catalogConfig.collectionSources) && p.a(this.requiredAddonUrls, catalogConfig.requiredAddonUrls) && p.a(this.packId, catalogConfig.packId) && p.a(this.packName, catalogConfig.packName);
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

    public final String getAddonName() {
        return this.addonName;
    }

    public final String getCollectionClearLogoUrl() {
        return this.collectionClearLogoUrl;
    }

    public final String getCollectionCoverImageUrl() {
        return this.collectionCoverImageUrl;
    }

    public final String getCollectionDescription() {
        return this.collectionDescription;
    }

    public final String getCollectionFocusGifUrl() {
        return this.collectionFocusGifUrl;
    }

    public final CollectionGroupKind getCollectionGroup() {
        return this.collectionGroup;
    }

    public final String getCollectionHeroGifUrl() {
        return this.collectionHeroGifUrl;
    }

    public final String getCollectionHeroImageUrl() {
        return this.collectionHeroImageUrl;
    }

    public final String getCollectionHeroVideoUrl() {
        return this.collectionHeroVideoUrl;
    }

    public final boolean getCollectionHideTitle() {
        return this.collectionHideTitle;
    }

    public final List<CollectionSourceConfig> getCollectionSources() {
        return this.collectionSources;
    }

    public final CollectionTileShape getCollectionTileShape() {
        return this.collectionTileShape;
    }

    public final String getId() {
        return this.id;
    }

    public final CatalogKind getKind() {
        return this.kind;
    }

    public final String getPackId() {
        return this.packId;
    }

    public final String getPackName() {
        return this.packName;
    }

    public final List<String> getRequiredAddonUrls() {
        return this.requiredAddonUrls;
    }

    public final String getSourceRef() {
        return this.sourceRef;
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

    public int hashCode() {
        int iHashCode = (this.sourceType.hashCode() + c.c(this.id.hashCode() * 31, 31, this.title)) * 31;
        String str = this.sourceUrl;
        int iHashCode2 = (iHashCode + (str == null ? 0 : str.hashCode())) * 31;
        String str2 = this.sourceRef;
        int iHashCode3 = (((iHashCode2 + (str2 == null ? 0 : str2.hashCode())) * 31) + (this.isPreinstalled ? 1231 : 1237)) * 31;
        String str3 = this.addonId;
        int iHashCode4 = (iHashCode3 + (str3 == null ? 0 : str3.hashCode())) * 31;
        String str4 = this.addonCatalogType;
        int iHashCode5 = (iHashCode4 + (str4 == null ? 0 : str4.hashCode())) * 31;
        String str5 = this.addonCatalogId;
        int iHashCode6 = (iHashCode5 + (str5 == null ? 0 : str5.hashCode())) * 31;
        String str6 = this.addonName;
        int iHashCode7 = (this.kind.hashCode() + ((iHashCode6 + (str6 == null ? 0 : str6.hashCode())) * 31)) * 31;
        CollectionGroupKind collectionGroupKind = this.collectionGroup;
        int iHashCode8 = (iHashCode7 + (collectionGroupKind == null ? 0 : collectionGroupKind.hashCode())) * 31;
        String str7 = this.collectionDescription;
        int iHashCode9 = (iHashCode8 + (str7 == null ? 0 : str7.hashCode())) * 31;
        String str8 = this.collectionCoverImageUrl;
        int iHashCode10 = (iHashCode9 + (str8 == null ? 0 : str8.hashCode())) * 31;
        String str9 = this.collectionFocusGifUrl;
        int iHashCode11 = (iHashCode10 + (str9 == null ? 0 : str9.hashCode())) * 31;
        String str10 = this.collectionHeroImageUrl;
        int iHashCode12 = (iHashCode11 + (str10 == null ? 0 : str10.hashCode())) * 31;
        String str11 = this.collectionHeroGifUrl;
        int iHashCode13 = (iHashCode12 + (str11 == null ? 0 : str11.hashCode())) * 31;
        String str12 = this.collectionHeroVideoUrl;
        int iHashCode14 = (iHashCode13 + (str12 == null ? 0 : str12.hashCode())) * 31;
        String str13 = this.collectionClearLogoUrl;
        int iE = d.e(this.requiredAddonUrls, d.e(this.collectionSources, (((this.collectionTileShape.hashCode() + ((iHashCode14 + (str13 == null ? 0 : str13.hashCode())) * 31)) * 31) + (this.collectionHideTitle ? 1231 : 1237)) * 31, 31), 31);
        String str14 = this.packId;
        int iHashCode15 = (iE + (str14 == null ? 0 : str14.hashCode())) * 31;
        String str15 = this.packName;
        return iHashCode15 + (str15 != null ? str15.hashCode() : 0);
    }

    public final boolean isPreinstalled() {
        return this.isPreinstalled;
    }

    public String toString() {
        String str = this.id;
        String str2 = this.title;
        CatalogSourceType catalogSourceType = this.sourceType;
        String str3 = this.sourceUrl;
        String str4 = this.sourceRef;
        boolean z = this.isPreinstalled;
        String str5 = this.addonId;
        String str6 = this.addonCatalogType;
        String str7 = this.addonCatalogId;
        String str8 = this.addonName;
        CatalogKind catalogKind = this.kind;
        CollectionGroupKind collectionGroupKind = this.collectionGroup;
        String str9 = this.collectionDescription;
        String str10 = this.collectionCoverImageUrl;
        String str11 = this.collectionFocusGifUrl;
        String str12 = this.collectionHeroImageUrl;
        String str13 = this.collectionHeroGifUrl;
        String str14 = this.collectionHeroVideoUrl;
        String str15 = this.collectionClearLogoUrl;
        CollectionTileShape collectionTileShape = this.collectionTileShape;
        boolean z5 = this.collectionHideTitle;
        List<CollectionSourceConfig> list = this.collectionSources;
        List<String> list2 = this.requiredAddonUrls;
        String str16 = this.packId;
        String str17 = this.packName;
        StringBuilder sbR = a2.r("CatalogConfig(id=", str, ", title=", str2, ", sourceType=");
        sbR.append(catalogSourceType);
        sbR.append(", sourceUrl=");
        sbR.append(str3);
        sbR.append(", sourceRef=");
        sbR.append(str4);
        sbR.append(", isPreinstalled=");
        sbR.append(z);
        sbR.append(", addonId=");
        a.i(sbR, str5, ", addonCatalogType=", str6, ", addonCatalogId=");
        a.i(sbR, str7, ", addonName=", str8, ", kind=");
        sbR.append(catalogKind);
        sbR.append(", collectionGroup=");
        sbR.append(collectionGroupKind);
        sbR.append(", collectionDescription=");
        a.i(sbR, str9, ", collectionCoverImageUrl=", str10, ", collectionFocusGifUrl=");
        a.i(sbR, str11, ", collectionHeroImageUrl=", str12, ", collectionHeroGifUrl=");
        a.i(sbR, str13, ", collectionHeroVideoUrl=", str14, ", collectionClearLogoUrl=");
        sbR.append(str15);
        sbR.append(", collectionTileShape=");
        sbR.append(collectionTileShape);
        sbR.append(", collectionHideTitle=");
        sbR.append(z5);
        sbR.append(", collectionSources=");
        sbR.append(list);
        sbR.append(", requiredAddonUrls=");
        sbR.append(list2);
        sbR.append(", packId=");
        sbR.append(str16);
        sbR.append(", packName=");
        return a0.c.p(sbR, str17, ")");
    }

    /* JADX WARN: Illegal instructions before constructor call */
    public /* synthetic */ CatalogConfig(String str, String str2, CatalogSourceType catalogSourceType, String str3, String str4, boolean z, String str5, String str6, String str7, String str8, CatalogKind catalogKind, CollectionGroupKind collectionGroupKind, String str9, String str10, String str11, String str12, String str13, String str14, String str15, CollectionTileShape collectionTileShape, boolean z5, List list, List list2, String str16, String str17, int i10, h hVar) {
        String str18 = (i10 & 8) != 0 ? null : str3;
        String str19 = (i10 & 16) != 0 ? null : str4;
        boolean z10 = (i10 & 32) != 0 ? false : z;
        String str20 = (i10 & 64) != 0 ? null : str5;
        String str21 = (i10 & 128) != 0 ? null : str6;
        String str22 = (i10 & 256) != 0 ? null : str7;
        String str23 = (i10 & 512) != 0 ? null : str8;
        CatalogKind catalogKind2 = (i10 & 1024) != 0 ? CatalogKind.STANDARD : catalogKind;
        CollectionGroupKind collectionGroupKind2 = (i10 & 2048) != 0 ? null : collectionGroupKind;
        String str24 = (i10 & 4096) != 0 ? null : str9;
        String str25 = (i10 & 8192) != 0 ? null : str10;
        String str26 = (i10 & 16384) != 0 ? null : str11;
        String str27 = (32768 & i10) != 0 ? null : str12;
        String str28 = (65536 & i10) != 0 ? null : str13;
        String str29 = (131072 & i10) != 0 ? null : str14;
        String str30 = (262144 & i10) != 0 ? null : str15;
        CollectionTileShape collectionTileShape2 = (524288 & i10) != 0 ? CollectionTileShape.LANDSCAPE : collectionTileShape;
        boolean z11 = (1048576 & i10) != 0 ? false : z5;
        int i11 = 2097152 & i10;
        z zVar = z.f19728i;
        this(str, str2, catalogSourceType, str18, str19, z10, str20, str21, str22, str23, catalogKind2, collectionGroupKind2, str24, str25, str26, str27, str28, str29, str30, collectionTileShape2, z11, i11 != 0 ? zVar : list, (4194304 & i10) != 0 ? zVar : list2, (8388608 & i10) != 0 ? null : str16, (i10 & 16777216) != 0 ? null : str17);
    }
}
