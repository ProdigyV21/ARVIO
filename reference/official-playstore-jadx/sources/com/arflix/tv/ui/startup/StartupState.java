package com.arflix.tv.ui.startup;

import androidx.compose.foundation.c;
import androidx.compose.material3.d;
import androidx.fragment.app.a2;
import androidx.media3.exoplayer.analytics.AnalyticsListener;
import com.arflix.tv.data.model.Category;
import com.arflix.tv.data.model.MediaItem;
import java.util.List;
import java.util.Map;
import kotlin.Metadata;
import kotlin.jvm.internal.p;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000@\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\u0007\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010$\n\u0002\b\u001f\n\u0002\u0010\b\n\u0002\b\u0002\b\u0087\b\u0018\u00002\u00020\u0001B\u0083\u0001\u0012\b\b\u0002\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0004\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0005\u001a\u00020\u0006\u0012\b\b\u0002\u0010\u0007\u001a\u00020\b\u0012\u000e\b\u0002\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u000b0\n\u0012\n\b\u0002\u0010\f\u001a\u0004\u0018\u00010\r\u0012\n\b\u0002\u0010\u000e\u001a\u0004\u0018\u00010\b\u0012\u0014\b\u0002\u0010\u000f\u001a\u000e\u0012\u0004\u0012\u00020\b\u0012\u0004\u0012\u00020\b0\u0010\u0012\b\b\u0002\u0010\u0011\u001a\u00020\u0003\u0012\n\b\u0002\u0010\u0012\u001a\u0004\u0018\u00010\b¢\u0006\u0004\b\u0013\u0010\u0014J\t\u0010\"\u001a\u00020\u0003HÆ\u0003J\t\u0010#\u001a\u00020\u0003HÆ\u0003J\t\u0010$\u001a\u00020\u0006HÆ\u0003J\t\u0010%\u001a\u00020\bHÆ\u0003J\u000f\u0010&\u001a\b\u0012\u0004\u0012\u00020\u000b0\nHÆ\u0003J\u000b\u0010'\u001a\u0004\u0018\u00010\rHÆ\u0003J\u000b\u0010(\u001a\u0004\u0018\u00010\bHÆ\u0003J\u0015\u0010)\u001a\u000e\u0012\u0004\u0012\u00020\b\u0012\u0004\u0012\u00020\b0\u0010HÆ\u0003J\t\u0010*\u001a\u00020\u0003HÆ\u0003J\u000b\u0010+\u001a\u0004\u0018\u00010\bHÆ\u0003J\u0085\u0001\u0010,\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u00062\b\b\u0002\u0010\u0007\u001a\u00020\b2\u000e\b\u0002\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u000b0\n2\n\b\u0002\u0010\f\u001a\u0004\u0018\u00010\r2\n\b\u0002\u0010\u000e\u001a\u0004\u0018\u00010\b2\u0014\b\u0002\u0010\u000f\u001a\u000e\u0012\u0004\u0012\u00020\b\u0012\u0004\u0012\u00020\b0\u00102\b\b\u0002\u0010\u0011\u001a\u00020\u00032\n\b\u0002\u0010\u0012\u001a\u0004\u0018\u00010\bHÆ\u0001J\u0014\u0010-\u001a\u00020\u00032\b\u0010.\u001a\u0004\u0018\u00010\u0001HÖ\u0083\u0004J\n\u0010/\u001a\u000200HÖ\u0081\u0004J\n\u00101\u001a\u00020\bHÖ\u0081\u0004R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0002\u0010\u0015R\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0004\u0010\u0015R\u0011\u0010\u0005\u001a\u00020\u0006¢\u0006\b\n\u0000\u001a\u0004\b\u0016\u0010\u0017R\u0011\u0010\u0007\u001a\u00020\b¢\u0006\b\n\u0000\u001a\u0004\b\u0018\u0010\u0019R\u0017\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u000b0\n¢\u0006\b\n\u0000\u001a\u0004\b\u001a\u0010\u001bR\u0013\u0010\f\u001a\u0004\u0018\u00010\r¢\u0006\b\n\u0000\u001a\u0004\b\u001c\u0010\u001dR\u0013\u0010\u000e\u001a\u0004\u0018\u00010\b¢\u0006\b\n\u0000\u001a\u0004\b\u001e\u0010\u0019R\u001d\u0010\u000f\u001a\u000e\u0012\u0004\u0012\u00020\b\u0012\u0004\u0012\u00020\b0\u0010¢\u0006\b\n\u0000\u001a\u0004\b\u001f\u0010 R\u0011\u0010\u0011\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u0015R\u0013\u0010\u0012\u001a\u0004\u0018\u00010\b¢\u0006\b\n\u0000\u001a\u0004\b!\u0010\u0019¨\u00062"}, d2 = {"Lcom/arflix/tv/ui/startup/StartupState;", "", "isLoading", "", "isReady", "loadingProgress", "", "loadingMessage", "", "categories", "", "Lcom/arflix/tv/data/model/Category;", "heroItem", "Lcom/arflix/tv/data/model/MediaItem;", "heroLogoUrl", "logoCache", "", "isAuthenticated", "error", "<init>", "(ZZFLjava/lang/String;Ljava/util/List;Lcom/arflix/tv/data/model/MediaItem;Ljava/lang/String;Ljava/util/Map;ZLjava/lang/String;)V", "()Z", "getLoadingProgress", "()F", "getLoadingMessage", "()Ljava/lang/String;", "getCategories", "()Ljava/util/List;", "getHeroItem", "()Lcom/arflix/tv/data/model/MediaItem;", "getHeroLogoUrl", "getLogoCache", "()Ljava/util/Map;", "getError", "component1", "component2", "component3", "component4", "component5", "component6", "component7", "component8", "component9", "component10", "copy", "equals", "other", "hashCode", "", "toString", "app_playRelease"}, k = 1, mv = {2, 3, 0}, xi = 48)
public final /* data */ class StartupState {
    public static final int $stable = 0;
    private final List<Category> categories;
    private final String error;
    private final MediaItem heroItem;
    private final String heroLogoUrl;
    private final boolean isAuthenticated;
    private final boolean isLoading;
    private final boolean isReady;
    private final String loadingMessage;
    private final float loadingProgress;
    private final Map<String, String> logoCache;

    public StartupState() {
        this(false, false, 0.0f, null, null, null, null, null, false, null, AnalyticsListener.EVENT_DRM_KEYS_LOADED, null);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static /* synthetic */ StartupState copy$default(StartupState startupState, boolean z, boolean z5, float f10, String str, List list, MediaItem mediaItem, String str2, Map map, boolean z10, String str3, int i10, Object obj) {
        if ((i10 & 1) != 0) {
            z = startupState.isLoading;
        }
        if ((i10 & 2) != 0) {
            z5 = startupState.isReady;
        }
        if ((i10 & 4) != 0) {
            f10 = startupState.loadingProgress;
        }
        if ((i10 & 8) != 0) {
            str = startupState.loadingMessage;
        }
        if ((i10 & 16) != 0) {
            list = startupState.categories;
        }
        if ((i10 & 32) != 0) {
            mediaItem = startupState.heroItem;
        }
        if ((i10 & 64) != 0) {
            str2 = startupState.heroLogoUrl;
        }
        if ((i10 & 128) != 0) {
            map = startupState.logoCache;
        }
        if ((i10 & 256) != 0) {
            z10 = startupState.isAuthenticated;
        }
        if ((i10 & 512) != 0) {
            str3 = startupState.error;
        }
        boolean z11 = z10;
        String str4 = str3;
        String str5 = str2;
        Map map2 = map;
        List list2 = list;
        MediaItem mediaItem2 = mediaItem;
        return startupState.copy(z, z5, f10, str, list2, mediaItem2, str5, map2, z11, str4);
    }

    /* JADX INFO: renamed from: component1, reason: from getter */
    public final boolean getIsLoading() {
        return this.isLoading;
    }

    /* JADX INFO: renamed from: component10, reason: from getter */
    public final String getError() {
        return this.error;
    }

    /* JADX INFO: renamed from: component2, reason: from getter */
    public final boolean getIsReady() {
        return this.isReady;
    }

    /* JADX INFO: renamed from: component3, reason: from getter */
    public final float getLoadingProgress() {
        return this.loadingProgress;
    }

    /* JADX INFO: renamed from: component4, reason: from getter */
    public final String getLoadingMessage() {
        return this.loadingMessage;
    }

    public final List<Category> component5() {
        return this.categories;
    }

    /* JADX INFO: renamed from: component6, reason: from getter */
    public final MediaItem getHeroItem() {
        return this.heroItem;
    }

    /* JADX INFO: renamed from: component7, reason: from getter */
    public final String getHeroLogoUrl() {
        return this.heroLogoUrl;
    }

    public final Map<String, String> component8() {
        return this.logoCache;
    }

    /* JADX INFO: renamed from: component9, reason: from getter */
    public final boolean getIsAuthenticated() {
        return this.isAuthenticated;
    }

    public final StartupState copy(boolean isLoading, boolean isReady, float loadingProgress, String loadingMessage, List<Category> categories, MediaItem heroItem, String heroLogoUrl, Map<String, String> logoCache, boolean isAuthenticated, String error) {
        return new StartupState(isLoading, isReady, loadingProgress, loadingMessage, categories, heroItem, heroLogoUrl, logoCache, isAuthenticated, error);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof StartupState)) {
            return false;
        }
        StartupState startupState = (StartupState) other;
        return this.isLoading == startupState.isLoading && this.isReady == startupState.isReady && Float.compare(this.loadingProgress, startupState.loadingProgress) == 0 && p.a(this.loadingMessage, startupState.loadingMessage) && p.a(this.categories, startupState.categories) && p.a(this.heroItem, startupState.heroItem) && p.a(this.heroLogoUrl, startupState.heroLogoUrl) && p.a(this.logoCache, startupState.logoCache) && this.isAuthenticated == startupState.isAuthenticated && p.a(this.error, startupState.error);
    }

    public final List<Category> getCategories() {
        return this.categories;
    }

    public final String getError() {
        return this.error;
    }

    public final MediaItem getHeroItem() {
        return this.heroItem;
    }

    public final String getHeroLogoUrl() {
        return this.heroLogoUrl;
    }

    public final String getLoadingMessage() {
        return this.loadingMessage;
    }

    public final float getLoadingProgress() {
        return this.loadingProgress;
    }

    public final Map<String, String> getLogoCache() {
        return this.logoCache;
    }

    public int hashCode() {
        int iE = d.e(this.categories, c.c(a0.c.b(this.loadingProgress, (((this.isLoading ? 1231 : 1237) * 31) + (this.isReady ? 1231 : 1237)) * 31, 31), 31, this.loadingMessage), 31);
        MediaItem mediaItem = this.heroItem;
        int iHashCode = (iE + (mediaItem == null ? 0 : mediaItem.hashCode())) * 31;
        String str = this.heroLogoUrl;
        int iG = (a2.g(this.logoCache, (iHashCode + (str == null ? 0 : str.hashCode())) * 31, 31) + (this.isAuthenticated ? 1231 : 1237)) * 31;
        String str2 = this.error;
        return iG + (str2 != null ? str2.hashCode() : 0);
    }

    public final boolean isAuthenticated() {
        return this.isAuthenticated;
    }

    public final boolean isLoading() {
        return this.isLoading;
    }

    public final boolean isReady() {
        return this.isReady;
    }

    public String toString() {
        return "StartupState(isLoading=" + this.isLoading + ", isReady=" + this.isReady + ", loadingProgress=" + this.loadingProgress + ", loadingMessage=" + this.loadingMessage + ", categories=" + this.categories + ", heroItem=" + this.heroItem + ", heroLogoUrl=" + this.heroLogoUrl + ", logoCache=" + this.logoCache + ", isAuthenticated=" + this.isAuthenticated + ", error=" + this.error + ")";
    }

    public StartupState(boolean z, boolean z5, float f10, String str, List<Category> list, MediaItem mediaItem, String str2, Map<String, String> map, boolean z10, String str3) {
        this.isLoading = z;
        this.isReady = z5;
        this.loadingProgress = f10;
        this.loadingMessage = str;
        this.categories = list;
        this.heroItem = mediaItem;
        this.heroLogoUrl = str2;
        this.logoCache = map;
        this.isAuthenticated = z10;
        this.error = str3;
    }

    /*  JADX ERROR: NullPointerException in pass: InitCodeVariables
        java.lang.NullPointerException
        */
    public /* synthetic */ StartupState(boolean r3, boolean r4, float r5, java.lang.String r6, java.util.List r7, com.arflix.tv.data.model.MediaItem r8, java.lang.String r9, java.util.Map r10, boolean r11, java.lang.String r12, int r13, kotlin.jvm.internal.h r14) {
        /*
            r2 = this;
            r14 = r13 & 1
            if (r14 == 0) goto L5
            r3 = 1
        L5:
            r14 = r13 & 2
            r0 = 0
            if (r14 == 0) goto Lb
            r4 = r0
        Lb:
            r14 = r13 & 4
            if (r14 == 0) goto L10
            r5 = 0
        L10:
            r14 = r13 & 8
            if (r14 == 0) goto L16
            java.lang.String r6 = "Starting..."
        L16:
            r14 = r13 & 16
            if (r14 == 0) goto L1c
            kotlin.collections.z r7 = kotlin.collections.z.f19728i
        L1c:
            r14 = r13 & 32
            r1 = 0
            if (r14 == 0) goto L22
            r8 = r1
        L22:
            r14 = r13 & 64
            if (r14 == 0) goto L27
            r9 = r1
        L27:
            r14 = r13 & 128(0x80, float:1.8E-43)
            if (r14 == 0) goto L2d
            kotlin.collections.a0 r10 = kotlin.collections.a0.f19683i
        L2d:
            r14 = r13 & 256(0x100, float:3.59E-43)
            if (r14 == 0) goto L32
            r11 = r0
        L32:
            r13 = r13 & 512(0x200, float:7.17E-43)
            if (r13 == 0) goto L42
            r14 = r1
            r12 = r10
            r13 = r11
            r10 = r8
            r11 = r9
            r8 = r6
            r9 = r7
            r6 = r4
            r7 = r5
            r4 = r2
            r5 = r3
            goto L4d
        L42:
            r14 = r12
            r13 = r11
            r11 = r9
            r12 = r10
            r9 = r7
            r10 = r8
            r7 = r5
            r8 = r6
            r5 = r3
            r6 = r4
            r4 = r2
        L4d:
            r4.<init>(r5, r6, r7, r8, r9, r10, r11, r12, r13, r14)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.arflix.tv.ui.startup.StartupState.<init>(boolean, boolean, float, java.lang.String, java.util.List, com.arflix.tv.data.model.MediaItem, java.lang.String, java.util.Map, boolean, java.lang.String, int, kotlin.jvm.internal.h):void");
    }
}
