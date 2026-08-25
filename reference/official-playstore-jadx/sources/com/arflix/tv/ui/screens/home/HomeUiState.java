package com.arflix.tv.ui.screens.home;

import androidx.fragment.app.a2;
import androidx.media3.common.C;
import androidx.media3.exoplayer.source.ProgressiveMediaSource;
import androidx.media3.session.MediaUtils;
import com.arflix.tv.data.model.Category;
import com.arflix.tv.data.model.MediaItem;
import com.arflix.tv.data.repository.CloudSyncStatus;
import com.arflix.tv.updater.UpdateStatus;
import java.util.List;
import java.util.Map;
import kotlin.Metadata;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000V\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\b\n\u0002\b\u0004\n\u0002\u0010$\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\bI\b\u0087\b\u0018\u00002\u00020\u0001BÓ\u0002\u0012\b\b\u0002\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0004\u001a\u00020\u0003\u0012\u000e\b\u0002\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00070\u0006\u0012\u000e\b\u0002\u0010\b\u001a\b\u0012\u0004\u0012\u00020\t0\u0006\u0012\n\b\u0002\u0010\n\u001a\u0004\u0018\u00010\u000b\u0012\n\b\u0002\u0010\f\u001a\u0004\u0018\u00010\r\u0012\n\b\u0002\u0010\u000e\u001a\u0004\u0018\u00010\u000b\u0012\n\b\u0002\u0010\u000f\u001a\u0004\u0018\u00010\u000b\u0012\b\b\u0002\u0010\u0010\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0011\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0012\u001a\u00020\u0013\u0012\b\b\u0002\u0010\u0014\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0015\u001a\u00020\u0003\u0012\n\b\u0002\u0010\u0016\u001a\u0004\u0018\u00010\u000b\u0012\u0014\b\u0002\u0010\u0017\u001a\u000e\u0012\u0004\u0012\u00020\u000b\u0012\u0004\u0012\u00020\u000b0\u0018\u0012\n\b\u0002\u0010\u0019\u001a\u0004\u0018\u00010\r\u0012\n\b\u0002\u0010\u001a\u001a\u0004\u0018\u00010\u000b\u0012\b\b\u0002\u0010\u001b\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u001c\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u001d\u001a\u00020\u000b\u0012\b\b\u0002\u0010\u001e\u001a\u00020\u001f\u0012\n\b\u0002\u0010 \u001a\u0004\u0018\u00010\u000b\u0012\b\b\u0002\u0010!\u001a\u00020\"\u0012\b\b\u0002\u0010#\u001a\u00020$\u0012\b\b\u0002\u0010%\u001a\u00020\u0003\u0012\b\b\u0002\u0010&\u001a\u00020\u0003\u0012\u0014\b\u0002\u0010'\u001a\u000e\u0012\u0004\u0012\u00020\u000b\u0012\u0004\u0012\u00020\u00030\u0018\u0012\b\b\u0002\u0010(\u001a\u00020\u0003¢\u0006\u0004\b)\u0010*J\t\u0010L\u001a\u00020\u0003HÆ\u0003J\t\u0010M\u001a\u00020\u0003HÆ\u0003J\u000f\u0010N\u001a\b\u0012\u0004\u0012\u00020\u00070\u0006HÆ\u0003J\u000f\u0010O\u001a\b\u0012\u0004\u0012\u00020\t0\u0006HÆ\u0003J\u000b\u0010P\u001a\u0004\u0018\u00010\u000bHÆ\u0003J\u000b\u0010Q\u001a\u0004\u0018\u00010\rHÆ\u0003J\u000b\u0010R\u001a\u0004\u0018\u00010\u000bHÆ\u0003J\u000b\u0010S\u001a\u0004\u0018\u00010\u000bHÆ\u0003J\t\u0010T\u001a\u00020\u0003HÆ\u0003J\t\u0010U\u001a\u00020\u0003HÆ\u0003J\t\u0010V\u001a\u00020\u0013HÆ\u0003J\t\u0010W\u001a\u00020\u0003HÆ\u0003J\t\u0010X\u001a\u00020\u0003HÆ\u0003J\u000b\u0010Y\u001a\u0004\u0018\u00010\u000bHÆ\u0003J\u0015\u0010Z\u001a\u000e\u0012\u0004\u0012\u00020\u000b\u0012\u0004\u0012\u00020\u000b0\u0018HÆ\u0003J\u000b\u0010[\u001a\u0004\u0018\u00010\rHÆ\u0003J\u000b\u0010\\\u001a\u0004\u0018\u00010\u000bHÆ\u0003J\t\u0010]\u001a\u00020\u0003HÆ\u0003J\t\u0010^\u001a\u00020\u0003HÆ\u0003J\t\u0010_\u001a\u00020\u000bHÆ\u0003J\t\u0010`\u001a\u00020\u001fHÆ\u0003J\u000b\u0010a\u001a\u0004\u0018\u00010\u000bHÆ\u0003J\t\u0010b\u001a\u00020\"HÆ\u0003J\t\u0010c\u001a\u00020$HÆ\u0003J\t\u0010d\u001a\u00020\u0003HÆ\u0003J\t\u0010e\u001a\u00020\u0003HÆ\u0003J\u0015\u0010f\u001a\u000e\u0012\u0004\u0012\u00020\u000b\u0012\u0004\u0012\u00020\u00030\u0018HÆ\u0003J\t\u0010g\u001a\u00020\u0003HÆ\u0003JÕ\u0002\u0010h\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\u000e\b\u0002\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00070\u00062\u000e\b\u0002\u0010\b\u001a\b\u0012\u0004\u0012\u00020\t0\u00062\n\b\u0002\u0010\n\u001a\u0004\u0018\u00010\u000b2\n\b\u0002\u0010\f\u001a\u0004\u0018\u00010\r2\n\b\u0002\u0010\u000e\u001a\u0004\u0018\u00010\u000b2\n\b\u0002\u0010\u000f\u001a\u0004\u0018\u00010\u000b2\b\b\u0002\u0010\u0010\u001a\u00020\u00032\b\b\u0002\u0010\u0011\u001a\u00020\u00032\b\b\u0002\u0010\u0012\u001a\u00020\u00132\b\b\u0002\u0010\u0014\u001a\u00020\u00032\b\b\u0002\u0010\u0015\u001a\u00020\u00032\n\b\u0002\u0010\u0016\u001a\u0004\u0018\u00010\u000b2\u0014\b\u0002\u0010\u0017\u001a\u000e\u0012\u0004\u0012\u00020\u000b\u0012\u0004\u0012\u00020\u000b0\u00182\n\b\u0002\u0010\u0019\u001a\u0004\u0018\u00010\r2\n\b\u0002\u0010\u001a\u001a\u0004\u0018\u00010\u000b2\b\b\u0002\u0010\u001b\u001a\u00020\u00032\b\b\u0002\u0010\u001c\u001a\u00020\u00032\b\b\u0002\u0010\u001d\u001a\u00020\u000b2\b\b\u0002\u0010\u001e\u001a\u00020\u001f2\n\b\u0002\u0010 \u001a\u0004\u0018\u00010\u000b2\b\b\u0002\u0010!\u001a\u00020\"2\b\b\u0002\u0010#\u001a\u00020$2\b\b\u0002\u0010%\u001a\u00020\u00032\b\b\u0002\u0010&\u001a\u00020\u00032\u0014\b\u0002\u0010'\u001a\u000e\u0012\u0004\u0012\u00020\u000b\u0012\u0004\u0012\u00020\u00030\u00182\b\b\u0002\u0010(\u001a\u00020\u0003HÆ\u0001J\u0014\u0010i\u001a\u00020\u00032\b\u0010j\u001a\u0004\u0018\u00010\u0001HÖ\u0083\u0004J\n\u0010k\u001a\u00020\u0013HÖ\u0081\u0004J\n\u0010l\u001a\u00020\u000bHÖ\u0081\u0004R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0002\u0010+R\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0004\u0010+R\u0017\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00070\u0006¢\u0006\b\n\u0000\u001a\u0004\b,\u0010-R\u0017\u0010\b\u001a\b\u0012\u0004\u0012\u00020\t0\u0006¢\u0006\b\n\u0000\u001a\u0004\b.\u0010-R\u0013\u0010\n\u001a\u0004\u0018\u00010\u000b¢\u0006\b\n\u0000\u001a\u0004\b/\u00100R\u0013\u0010\f\u001a\u0004\u0018\u00010\r¢\u0006\b\n\u0000\u001a\u0004\b1\u00102R\u0013\u0010\u000e\u001a\u0004\u0018\u00010\u000b¢\u0006\b\n\u0000\u001a\u0004\b3\u00100R\u0013\u0010\u000f\u001a\u0004\u0018\u00010\u000b¢\u0006\b\n\u0000\u001a\u0004\b4\u00100R\u0011\u0010\u0010\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b5\u0010+R\u0011\u0010\u0011\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b6\u0010+R\u0011\u0010\u0012\u001a\u00020\u0013¢\u0006\b\n\u0000\u001a\u0004\b7\u00108R\u0011\u0010\u0014\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b9\u0010+R\u0011\u0010\u0015\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b:\u0010+R\u0013\u0010\u0016\u001a\u0004\u0018\u00010\u000b¢\u0006\b\n\u0000\u001a\u0004\b;\u00100R\u001d\u0010\u0017\u001a\u000e\u0012\u0004\u0012\u00020\u000b\u0012\u0004\u0012\u00020\u000b0\u0018¢\u0006\b\n\u0000\u001a\u0004\b<\u0010=R\u0013\u0010\u0019\u001a\u0004\u0018\u00010\r¢\u0006\b\n\u0000\u001a\u0004\b>\u00102R\u0013\u0010\u001a\u001a\u0004\u0018\u00010\u000b¢\u0006\b\n\u0000\u001a\u0004\b?\u00100R\u0011\u0010\u001b\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u001b\u0010+R\u0011\u0010\u001c\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u001c\u0010+R\u0011\u0010\u001d\u001a\u00020\u000b¢\u0006\b\n\u0000\u001a\u0004\b@\u00100R\u0011\u0010\u001e\u001a\u00020\u001f¢\u0006\b\n\u0000\u001a\u0004\bA\u0010BR\u0013\u0010 \u001a\u0004\u0018\u00010\u000b¢\u0006\b\n\u0000\u001a\u0004\bC\u00100R\u0011\u0010!\u001a\u00020\"¢\u0006\b\n\u0000\u001a\u0004\bD\u0010ER\u0011\u0010#\u001a\u00020$¢\u0006\b\n\u0000\u001a\u0004\bF\u0010GR\u0011\u0010%\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\bH\u0010+R\u0011\u0010&\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\bI\u0010+R\u001d\u0010'\u001a\u000e\u0012\u0004\u0012\u00020\u000b\u0012\u0004\u0012\u00020\u00030\u0018¢\u0006\b\n\u0000\u001a\u0004\bJ\u0010=R\u0011\u0010(\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\bK\u0010+¨\u0006m"}, d2 = {"Lcom/arflix/tv/ui/screens/home/HomeUiState;", "", "isLoading", "", "isInitialLoad", "categories", "", "Lcom/arflix/tv/data/model/Category;", "collectionRows", "Lcom/arflix/tv/ui/screens/home/HomeCollectionRow;", "error", "", "heroItem", "Lcom/arflix/tv/data/model/MediaItem;", "heroLogoUrl", "heroTrailerKey", "trailerAutoPlay", "trailerSoundEnabled", "trailerDelaySeconds", "", "trailerInCards", "showBudget", "heroOverviewOverride", "cardLogoUrls", "", "previousHeroItem", "previousHeroLogoUrl", "isHeroTransitioning", "isAuthenticated", "clockFormat", "syncStatus", "Lcom/arflix/tv/data/repository/CloudSyncStatus;", "toastMessage", "toastType", "Lcom/arflix/tv/ui/screens/home/ToastType;", "updateStatus", "Lcom/arflix/tv/updater/UpdateStatus;", "showAppUpdateDialog", "hasUpdateBadge", "categoryHasMoreMap", "smoothScrolling", "<init>", "(ZZLjava/util/List;Ljava/util/List;Ljava/lang/String;Lcom/arflix/tv/data/model/MediaItem;Ljava/lang/String;Ljava/lang/String;ZZIZZLjava/lang/String;Ljava/util/Map;Lcom/arflix/tv/data/model/MediaItem;Ljava/lang/String;ZZLjava/lang/String;Lcom/arflix/tv/data/repository/CloudSyncStatus;Ljava/lang/String;Lcom/arflix/tv/ui/screens/home/ToastType;Lcom/arflix/tv/updater/UpdateStatus;ZZLjava/util/Map;Z)V", "()Z", "getCategories", "()Ljava/util/List;", "getCollectionRows", "getError", "()Ljava/lang/String;", "getHeroItem", "()Lcom/arflix/tv/data/model/MediaItem;", "getHeroLogoUrl", "getHeroTrailerKey", "getTrailerAutoPlay", "getTrailerSoundEnabled", "getTrailerDelaySeconds", "()I", "getTrailerInCards", "getShowBudget", "getHeroOverviewOverride", "getCardLogoUrls", "()Ljava/util/Map;", "getPreviousHeroItem", "getPreviousHeroLogoUrl", "getClockFormat", "getSyncStatus", "()Lcom/arflix/tv/data/repository/CloudSyncStatus;", "getToastMessage", "getToastType", "()Lcom/arflix/tv/ui/screens/home/ToastType;", "getUpdateStatus", "()Lcom/arflix/tv/updater/UpdateStatus;", "getShowAppUpdateDialog", "getHasUpdateBadge", "getCategoryHasMoreMap", "getSmoothScrolling", "component1", "component2", "component3", "component4", "component5", "component6", "component7", "component8", "component9", "component10", "component11", "component12", "component13", "component14", "component15", "component16", "component17", "component18", "component19", "component20", "component21", "component22", "component23", "component24", "component25", "component26", "component27", "component28", "copy", "equals", "other", "hashCode", "toString", "app_playRelease"}, k = 1, mv = {2, 3, 0}, xi = 48)
public final /* data */ class HomeUiState {
    public static final int $stable = 0;
    private final Map<String, String> cardLogoUrls;
    private final List<Category> categories;
    private final Map<String, Boolean> categoryHasMoreMap;
    private final String clockFormat;
    private final List<HomeCollectionRow> collectionRows;
    private final String error;
    private final boolean hasUpdateBadge;
    private final MediaItem heroItem;
    private final String heroLogoUrl;
    private final String heroOverviewOverride;
    private final String heroTrailerKey;
    private final boolean isAuthenticated;
    private final boolean isHeroTransitioning;
    private final boolean isInitialLoad;
    private final boolean isLoading;
    private final MediaItem previousHeroItem;
    private final String previousHeroLogoUrl;
    private final boolean showAppUpdateDialog;
    private final boolean showBudget;
    private final boolean smoothScrolling;
    private final CloudSyncStatus syncStatus;
    private final String toastMessage;
    private final ToastType toastType;
    private final boolean trailerAutoPlay;
    private final int trailerDelaySeconds;
    private final boolean trailerInCards;
    private final boolean trailerSoundEnabled;
    private final UpdateStatus updateStatus;

    public HomeUiState() {
        this(false, false, null, null, null, null, null, null, false, false, 0, false, false, null, null, null, null, false, false, null, null, null, null, null, false, false, null, false, 268435455, null);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static /* synthetic */ HomeUiState copy$default(HomeUiState homeUiState, boolean z, boolean z5, List list, List list2, String str, MediaItem mediaItem, String str2, String str3, boolean z10, boolean z11, int i10, boolean z12, boolean z13, String str4, Map map, MediaItem mediaItem2, String str5, boolean z14, boolean z15, String str6, CloudSyncStatus cloudSyncStatus, String str7, ToastType toastType, UpdateStatus updateStatus, boolean z16, boolean z17, Map map2, boolean z18, int i11, Object obj) {
        boolean z19;
        Map map3;
        boolean z20 = (i11 & 1) != 0 ? homeUiState.isLoading : z;
        boolean z21 = (i11 & 2) != 0 ? homeUiState.isInitialLoad : z5;
        List list3 = (i11 & 4) != 0 ? homeUiState.categories : list;
        List list4 = (i11 & 8) != 0 ? homeUiState.collectionRows : list2;
        String str8 = (i11 & 16) != 0 ? homeUiState.error : str;
        MediaItem mediaItem3 = (i11 & 32) != 0 ? homeUiState.heroItem : mediaItem;
        String str9 = (i11 & 64) != 0 ? homeUiState.heroLogoUrl : str2;
        String str10 = (i11 & 128) != 0 ? homeUiState.heroTrailerKey : str3;
        boolean z22 = (i11 & 256) != 0 ? homeUiState.trailerAutoPlay : z10;
        boolean z23 = (i11 & 512) != 0 ? homeUiState.trailerSoundEnabled : z11;
        int i12 = (i11 & 1024) != 0 ? homeUiState.trailerDelaySeconds : i10;
        boolean z24 = (i11 & 2048) != 0 ? homeUiState.trailerInCards : z12;
        boolean z25 = (i11 & 4096) != 0 ? homeUiState.showBudget : z13;
        String str11 = (i11 & 8192) != 0 ? homeUiState.heroOverviewOverride : str4;
        boolean z26 = z20;
        Map map4 = (i11 & 16384) != 0 ? homeUiState.cardLogoUrls : map;
        MediaItem mediaItem4 = (i11 & 32768) != 0 ? homeUiState.previousHeroItem : mediaItem2;
        String str12 = (i11 & 65536) != 0 ? homeUiState.previousHeroLogoUrl : str5;
        boolean z27 = (i11 & 131072) != 0 ? homeUiState.isHeroTransitioning : z14;
        boolean z28 = (i11 & MediaUtils.TRANSACTION_SIZE_LIMIT_IN_BYTES) != 0 ? homeUiState.isAuthenticated : z15;
        String str13 = (i11 & 524288) != 0 ? homeUiState.clockFormat : str6;
        CloudSyncStatus cloudSyncStatus2 = (i11 & ProgressiveMediaSource.DEFAULT_LOADING_CHECK_INTERVAL_BYTES) != 0 ? homeUiState.syncStatus : cloudSyncStatus;
        String str14 = (i11 & 2097152) != 0 ? homeUiState.toastMessage : str7;
        ToastType toastType2 = (i11 & 4194304) != 0 ? homeUiState.toastType : toastType;
        UpdateStatus updateStatus2 = (i11 & 8388608) != 0 ? homeUiState.updateStatus : updateStatus;
        boolean z29 = (i11 & 16777216) != 0 ? homeUiState.showAppUpdateDialog : z16;
        boolean z30 = (i11 & 33554432) != 0 ? homeUiState.hasUpdateBadge : z17;
        Map map5 = (i11 & C.BUFFER_FLAG_NOT_DEPENDED_ON) != 0 ? homeUiState.categoryHasMoreMap : map2;
        if ((i11 & C.BUFFER_FLAG_FIRST_SAMPLE) != 0) {
            map3 = map5;
            z19 = homeUiState.smoothScrolling;
        } else {
            z19 = z18;
            map3 = map5;
        }
        return homeUiState.copy(z26, z21, list3, list4, str8, mediaItem3, str9, str10, z22, z23, i12, z24, z25, str11, map4, mediaItem4, str12, z27, z28, str13, cloudSyncStatus2, str14, toastType2, updateStatus2, z29, z30, map3, z19);
    }

    /* JADX INFO: renamed from: component1, reason: from getter */
    public final boolean getIsLoading() {
        return this.isLoading;
    }

    /* JADX INFO: renamed from: component10, reason: from getter */
    public final boolean getTrailerSoundEnabled() {
        return this.trailerSoundEnabled;
    }

    /* JADX INFO: renamed from: component11, reason: from getter */
    public final int getTrailerDelaySeconds() {
        return this.trailerDelaySeconds;
    }

    /* JADX INFO: renamed from: component12, reason: from getter */
    public final boolean getTrailerInCards() {
        return this.trailerInCards;
    }

    /* JADX INFO: renamed from: component13, reason: from getter */
    public final boolean getShowBudget() {
        return this.showBudget;
    }

    /* JADX INFO: renamed from: component14, reason: from getter */
    public final String getHeroOverviewOverride() {
        return this.heroOverviewOverride;
    }

    public final Map<String, String> component15() {
        return this.cardLogoUrls;
    }

    /* JADX INFO: renamed from: component16, reason: from getter */
    public final MediaItem getPreviousHeroItem() {
        return this.previousHeroItem;
    }

    /* JADX INFO: renamed from: component17, reason: from getter */
    public final String getPreviousHeroLogoUrl() {
        return this.previousHeroLogoUrl;
    }

    /* JADX INFO: renamed from: component18, reason: from getter */
    public final boolean getIsHeroTransitioning() {
        return this.isHeroTransitioning;
    }

    /* JADX INFO: renamed from: component19, reason: from getter */
    public final boolean getIsAuthenticated() {
        return this.isAuthenticated;
    }

    /* JADX INFO: renamed from: component2, reason: from getter */
    public final boolean getIsInitialLoad() {
        return this.isInitialLoad;
    }

    /* JADX INFO: renamed from: component20, reason: from getter */
    public final String getClockFormat() {
        return this.clockFormat;
    }

    /* JADX INFO: renamed from: component21, reason: from getter */
    public final CloudSyncStatus getSyncStatus() {
        return this.syncStatus;
    }

    /* JADX INFO: renamed from: component22, reason: from getter */
    public final String getToastMessage() {
        return this.toastMessage;
    }

    /* JADX INFO: renamed from: component23, reason: from getter */
    public final ToastType getToastType() {
        return this.toastType;
    }

    /* JADX INFO: renamed from: component24, reason: from getter */
    public final UpdateStatus getUpdateStatus() {
        return this.updateStatus;
    }

    /* JADX INFO: renamed from: component25, reason: from getter */
    public final boolean getShowAppUpdateDialog() {
        return this.showAppUpdateDialog;
    }

    /* JADX INFO: renamed from: component26, reason: from getter */
    public final boolean getHasUpdateBadge() {
        return this.hasUpdateBadge;
    }

    public final Map<String, Boolean> component27() {
        return this.categoryHasMoreMap;
    }

    /* JADX INFO: renamed from: component28, reason: from getter */
    public final boolean getSmoothScrolling() {
        return this.smoothScrolling;
    }

    public final List<Category> component3() {
        return this.categories;
    }

    public final List<HomeCollectionRow> component4() {
        return this.collectionRows;
    }

    /* JADX INFO: renamed from: component5, reason: from getter */
    public final String getError() {
        return this.error;
    }

    /* JADX INFO: renamed from: component6, reason: from getter */
    public final MediaItem getHeroItem() {
        return this.heroItem;
    }

    /* JADX INFO: renamed from: component7, reason: from getter */
    public final String getHeroLogoUrl() {
        return this.heroLogoUrl;
    }

    /* JADX INFO: renamed from: component8, reason: from getter */
    public final String getHeroTrailerKey() {
        return this.heroTrailerKey;
    }

    /* JADX INFO: renamed from: component9, reason: from getter */
    public final boolean getTrailerAutoPlay() {
        return this.trailerAutoPlay;
    }

    public final HomeUiState copy(boolean isLoading, boolean isInitialLoad, List<Category> categories, List<HomeCollectionRow> collectionRows, String error, MediaItem heroItem, String heroLogoUrl, String heroTrailerKey, boolean trailerAutoPlay, boolean trailerSoundEnabled, int trailerDelaySeconds, boolean trailerInCards, boolean showBudget, String heroOverviewOverride, Map<String, String> cardLogoUrls, MediaItem previousHeroItem, String previousHeroLogoUrl, boolean isHeroTransitioning, boolean isAuthenticated, String clockFormat, CloudSyncStatus syncStatus, String toastMessage, ToastType toastType, UpdateStatus updateStatus, boolean showAppUpdateDialog, boolean hasUpdateBadge, Map<String, Boolean> categoryHasMoreMap, boolean smoothScrolling) {
        return new HomeUiState(isLoading, isInitialLoad, categories, collectionRows, error, heroItem, heroLogoUrl, heroTrailerKey, trailerAutoPlay, trailerSoundEnabled, trailerDelaySeconds, trailerInCards, showBudget, heroOverviewOverride, cardLogoUrls, previousHeroItem, previousHeroLogoUrl, isHeroTransitioning, isAuthenticated, clockFormat, syncStatus, toastMessage, toastType, updateStatus, showAppUpdateDialog, hasUpdateBadge, categoryHasMoreMap, smoothScrolling);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof HomeUiState)) {
            return false;
        }
        HomeUiState homeUiState = (HomeUiState) other;
        return this.isLoading == homeUiState.isLoading && this.isInitialLoad == homeUiState.isInitialLoad && kotlin.jvm.internal.p.a(this.categories, homeUiState.categories) && kotlin.jvm.internal.p.a(this.collectionRows, homeUiState.collectionRows) && kotlin.jvm.internal.p.a(this.error, homeUiState.error) && kotlin.jvm.internal.p.a(this.heroItem, homeUiState.heroItem) && kotlin.jvm.internal.p.a(this.heroLogoUrl, homeUiState.heroLogoUrl) && kotlin.jvm.internal.p.a(this.heroTrailerKey, homeUiState.heroTrailerKey) && this.trailerAutoPlay == homeUiState.trailerAutoPlay && this.trailerSoundEnabled == homeUiState.trailerSoundEnabled && this.trailerDelaySeconds == homeUiState.trailerDelaySeconds && this.trailerInCards == homeUiState.trailerInCards && this.showBudget == homeUiState.showBudget && kotlin.jvm.internal.p.a(this.heroOverviewOverride, homeUiState.heroOverviewOverride) && kotlin.jvm.internal.p.a(this.cardLogoUrls, homeUiState.cardLogoUrls) && kotlin.jvm.internal.p.a(this.previousHeroItem, homeUiState.previousHeroItem) && kotlin.jvm.internal.p.a(this.previousHeroLogoUrl, homeUiState.previousHeroLogoUrl) && this.isHeroTransitioning == homeUiState.isHeroTransitioning && this.isAuthenticated == homeUiState.isAuthenticated && kotlin.jvm.internal.p.a(this.clockFormat, homeUiState.clockFormat) && this.syncStatus == homeUiState.syncStatus && kotlin.jvm.internal.p.a(this.toastMessage, homeUiState.toastMessage) && this.toastType == homeUiState.toastType && kotlin.jvm.internal.p.a(this.updateStatus, homeUiState.updateStatus) && this.showAppUpdateDialog == homeUiState.showAppUpdateDialog && this.hasUpdateBadge == homeUiState.hasUpdateBadge && kotlin.jvm.internal.p.a(this.categoryHasMoreMap, homeUiState.categoryHasMoreMap) && this.smoothScrolling == homeUiState.smoothScrolling;
    }

    public final Map<String, String> getCardLogoUrls() {
        return this.cardLogoUrls;
    }

    public final List<Category> getCategories() {
        return this.categories;
    }

    public final Map<String, Boolean> getCategoryHasMoreMap() {
        return this.categoryHasMoreMap;
    }

    public final String getClockFormat() {
        return this.clockFormat;
    }

    public final List<HomeCollectionRow> getCollectionRows() {
        return this.collectionRows;
    }

    public final String getError() {
        return this.error;
    }

    public final boolean getHasUpdateBadge() {
        return this.hasUpdateBadge;
    }

    public final MediaItem getHeroItem() {
        return this.heroItem;
    }

    public final String getHeroLogoUrl() {
        return this.heroLogoUrl;
    }

    public final String getHeroOverviewOverride() {
        return this.heroOverviewOverride;
    }

    public final String getHeroTrailerKey() {
        return this.heroTrailerKey;
    }

    public final MediaItem getPreviousHeroItem() {
        return this.previousHeroItem;
    }

    public final String getPreviousHeroLogoUrl() {
        return this.previousHeroLogoUrl;
    }

    public final boolean getShowAppUpdateDialog() {
        return this.showAppUpdateDialog;
    }

    public final boolean getShowBudget() {
        return this.showBudget;
    }

    public final boolean getSmoothScrolling() {
        return this.smoothScrolling;
    }

    public final CloudSyncStatus getSyncStatus() {
        return this.syncStatus;
    }

    public final String getToastMessage() {
        return this.toastMessage;
    }

    public final ToastType getToastType() {
        return this.toastType;
    }

    public final boolean getTrailerAutoPlay() {
        return this.trailerAutoPlay;
    }

    public final int getTrailerDelaySeconds() {
        return this.trailerDelaySeconds;
    }

    public final boolean getTrailerInCards() {
        return this.trailerInCards;
    }

    public final boolean getTrailerSoundEnabled() {
        return this.trailerSoundEnabled;
    }

    public final UpdateStatus getUpdateStatus() {
        return this.updateStatus;
    }

    public int hashCode() {
        int iE = androidx.compose.material3.d.e(this.collectionRows, androidx.compose.material3.d.e(this.categories, (((this.isLoading ? 1231 : 1237) * 31) + (this.isInitialLoad ? 1231 : 1237)) * 31, 31), 31);
        String str = this.error;
        int iHashCode = (iE + (str == null ? 0 : str.hashCode())) * 31;
        MediaItem mediaItem = this.heroItem;
        int iHashCode2 = (iHashCode + (mediaItem == null ? 0 : mediaItem.hashCode())) * 31;
        String str2 = this.heroLogoUrl;
        int iHashCode3 = (iHashCode2 + (str2 == null ? 0 : str2.hashCode())) * 31;
        String str3 = this.heroTrailerKey;
        int iHashCode4 = (((((((((((iHashCode3 + (str3 == null ? 0 : str3.hashCode())) * 31) + (this.trailerAutoPlay ? 1231 : 1237)) * 31) + (this.trailerSoundEnabled ? 1231 : 1237)) * 31) + this.trailerDelaySeconds) * 31) + (this.trailerInCards ? 1231 : 1237)) * 31) + (this.showBudget ? 1231 : 1237)) * 31;
        String str4 = this.heroOverviewOverride;
        int iG = a2.g(this.cardLogoUrls, (iHashCode4 + (str4 == null ? 0 : str4.hashCode())) * 31, 31);
        MediaItem mediaItem2 = this.previousHeroItem;
        int iHashCode5 = (iG + (mediaItem2 == null ? 0 : mediaItem2.hashCode())) * 31;
        String str5 = this.previousHeroLogoUrl;
        int iHashCode6 = (this.syncStatus.hashCode() + androidx.compose.foundation.c.c((((((iHashCode5 + (str5 == null ? 0 : str5.hashCode())) * 31) + (this.isHeroTransitioning ? 1231 : 1237)) * 31) + (this.isAuthenticated ? 1231 : 1237)) * 31, 31, this.clockFormat)) * 31;
        String str6 = this.toastMessage;
        return a2.g(this.categoryHasMoreMap, (((((this.updateStatus.hashCode() + ((this.toastType.hashCode() + ((iHashCode6 + (str6 != null ? str6.hashCode() : 0)) * 31)) * 31)) * 31) + (this.showAppUpdateDialog ? 1231 : 1237)) * 31) + (this.hasUpdateBadge ? 1231 : 1237)) * 31, 31) + (this.smoothScrolling ? 1231 : 1237);
    }

    public final boolean isAuthenticated() {
        return this.isAuthenticated;
    }

    public final boolean isHeroTransitioning() {
        return this.isHeroTransitioning;
    }

    public final boolean isInitialLoad() {
        return this.isInitialLoad;
    }

    public final boolean isLoading() {
        return this.isLoading;
    }

    public String toString() {
        boolean z = this.isLoading;
        boolean z5 = this.isInitialLoad;
        List<Category> list = this.categories;
        List<HomeCollectionRow> list2 = this.collectionRows;
        String str = this.error;
        MediaItem mediaItem = this.heroItem;
        String str2 = this.heroLogoUrl;
        String str3 = this.heroTrailerKey;
        boolean z10 = this.trailerAutoPlay;
        boolean z11 = this.trailerSoundEnabled;
        int i10 = this.trailerDelaySeconds;
        boolean z12 = this.trailerInCards;
        boolean z13 = this.showBudget;
        String str4 = this.heroOverviewOverride;
        Map<String, String> map = this.cardLogoUrls;
        MediaItem mediaItem2 = this.previousHeroItem;
        String str5 = this.previousHeroLogoUrl;
        boolean z14 = this.isHeroTransitioning;
        boolean z15 = this.isAuthenticated;
        String str6 = this.clockFormat;
        CloudSyncStatus cloudSyncStatus = this.syncStatus;
        String str7 = this.toastMessage;
        ToastType toastType = this.toastType;
        UpdateStatus updateStatus = this.updateStatus;
        boolean z16 = this.showAppUpdateDialog;
        boolean z17 = this.hasUpdateBadge;
        Map<String, Boolean> map2 = this.categoryHasMoreMap;
        boolean z18 = this.smoothScrolling;
        StringBuilder sb2 = new StringBuilder("HomeUiState(isLoading=");
        sb2.append(z);
        sb2.append(", isInitialLoad=");
        sb2.append(z5);
        sb2.append(", categories=");
        a2.A(sb2, list, ", collectionRows=", list2, ", error=");
        sb2.append(str);
        sb2.append(", heroItem=");
        sb2.append(mediaItem);
        sb2.append(", heroLogoUrl=");
        y.a.i(sb2, str2, ", heroTrailerKey=", str3, ", trailerAutoPlay=");
        v.f.h(sb2, z10, ", trailerSoundEnabled=", z11, ", trailerDelaySeconds=");
        sb2.append(i10);
        sb2.append(", trailerInCards=");
        sb2.append(z12);
        sb2.append(", showBudget=");
        a2.B(sb2, z13, ", heroOverviewOverride=", str4, ", cardLogoUrls=");
        sb2.append(map);
        sb2.append(", previousHeroItem=");
        sb2.append(mediaItem2);
        sb2.append(", previousHeroLogoUrl=");
        sb2.append(str5);
        sb2.append(", isHeroTransitioning=");
        sb2.append(z14);
        sb2.append(", isAuthenticated=");
        a2.B(sb2, z15, ", clockFormat=", str6, ", syncStatus=");
        sb2.append(cloudSyncStatus);
        sb2.append(", toastMessage=");
        sb2.append(str7);
        sb2.append(", toastType=");
        sb2.append(toastType);
        sb2.append(", updateStatus=");
        sb2.append(updateStatus);
        sb2.append(", showAppUpdateDialog=");
        v.f.h(sb2, z16, ", hasUpdateBadge=", z17, ", categoryHasMoreMap=");
        sb2.append(map2);
        sb2.append(", smoothScrolling=");
        sb2.append(z18);
        sb2.append(")");
        return sb2.toString();
    }

    public HomeUiState(boolean z, boolean z5, List<Category> list, List<HomeCollectionRow> list2, String str, MediaItem mediaItem, String str2, String str3, boolean z10, boolean z11, int i10, boolean z12, boolean z13, String str4, Map<String, String> map, MediaItem mediaItem2, String str5, boolean z14, boolean z15, String str6, CloudSyncStatus cloudSyncStatus, String str7, ToastType toastType, UpdateStatus updateStatus, boolean z16, boolean z17, Map<String, Boolean> map2, boolean z18) {
        this.isLoading = z;
        this.isInitialLoad = z5;
        this.categories = list;
        this.collectionRows = list2;
        this.error = str;
        this.heroItem = mediaItem;
        this.heroLogoUrl = str2;
        this.heroTrailerKey = str3;
        this.trailerAutoPlay = z10;
        this.trailerSoundEnabled = z11;
        this.trailerDelaySeconds = i10;
        this.trailerInCards = z12;
        this.showBudget = z13;
        this.heroOverviewOverride = str4;
        this.cardLogoUrls = map;
        this.previousHeroItem = mediaItem2;
        this.previousHeroLogoUrl = str5;
        this.isHeroTransitioning = z14;
        this.isAuthenticated = z15;
        this.clockFormat = str6;
        this.syncStatus = cloudSyncStatus;
        this.toastMessage = str7;
        this.toastType = toastType;
        this.updateStatus = updateStatus;
        this.showAppUpdateDialog = z16;
        this.hasUpdateBadge = z17;
        this.categoryHasMoreMap = map2;
        this.smoothScrolling = z18;
    }

    /* JADX WARN: Illegal instructions before constructor call */
    public /* synthetic */ HomeUiState(boolean z, boolean z5, List list, List list2, String str, MediaItem mediaItem, String str2, String str3, boolean z10, boolean z11, int i10, boolean z12, boolean z13, String str4, Map map, MediaItem mediaItem2, String str5, boolean z14, boolean z15, String str6, CloudSyncStatus cloudSyncStatus, String str7, ToastType toastType, UpdateStatus updateStatus, boolean z16, boolean z17, Map map2, boolean z18, int i11, kotlin.jvm.internal.h hVar) {
        boolean z19 = (i11 & 1) != 0 ? false : z;
        boolean z20 = (i11 & 2) != 0 ? true : z5;
        int i12 = i11 & 4;
        List list3 = kotlin.collections.z.f19728i;
        List list4 = i12 != 0 ? list3 : list;
        list3 = (i11 & 8) == 0 ? list2 : list3;
        String str8 = (i11 & 16) != 0 ? null : str;
        MediaItem mediaItem3 = (i11 & 32) != 0 ? null : mediaItem;
        String str9 = (i11 & 64) != 0 ? null : str2;
        String str10 = (i11 & 128) != 0 ? null : str3;
        boolean z21 = (i11 & 256) != 0 ? false : z10;
        boolean z22 = (i11 & 512) != 0 ? false : z11;
        int i13 = (i11 & 1024) != 0 ? 2 : i10;
        boolean z23 = (i11 & 2048) != 0 ? true : z12;
        boolean z24 = (i11 & 4096) == 0 ? z13 : true;
        String str11 = (i11 & 8192) != 0 ? null : str4;
        int i14 = i11 & 16384;
        Map map3 = kotlin.collections.a0.f19683i;
        this(z19, z20, list4, list3, str8, mediaItem3, str9, str10, z21, z22, i13, z23, z24, str11, i14 != 0 ? map3 : map, (i11 & 32768) != 0 ? null : mediaItem2, (i11 & 65536) != 0 ? null : str5, (i11 & 131072) != 0 ? false : z14, (i11 & MediaUtils.TRANSACTION_SIZE_LIMIT_IN_BYTES) != 0 ? false : z15, (i11 & 524288) != 0 ? "24h" : str6, (i11 & ProgressiveMediaSource.DEFAULT_LOADING_CHECK_INTERVAL_BYTES) != 0 ? CloudSyncStatus.NOT_SIGNED_IN : cloudSyncStatus, (i11 & 2097152) != 0 ? null : str7, (i11 & 4194304) != 0 ? ToastType.INFO : toastType, (i11 & 8388608) != 0 ? UpdateStatus.Idle.INSTANCE : updateStatus, (i11 & 16777216) != 0 ? false : z16, (i11 & 33554432) != 0 ? false : z17, (i11 & C.BUFFER_FLAG_NOT_DEPENDED_ON) == 0 ? map2 : map3, (i11 & C.BUFFER_FLAG_FIRST_SAMPLE) != 0 ? false : z18);
    }
}
