package com.arflix.tv.ui.screens.watchlist;

import androidx.fragment.app.a2;
import com.arflix.tv.data.model.MediaItem;
import com.arflix.tv.ui.screens.watchlist.WatchlistSourceItem;
import java.util.Iterator;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.x;
import kotlin.jvm.internal.p;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u00006\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b,\b\u0087\b\u0018\u00002\u00020\u0001B\u0095\u0001\u0012\u000e\b\u0002\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003\u0012\b\b\u0002\u0010\u0005\u001a\u00020\u0006\u0012\b\b\u0002\u0010\u0007\u001a\u00020\b\u0012\u000e\b\u0002\u0010\t\u001a\b\u0012\u0004\u0012\u00020\n0\u0003\u0012\u000e\b\u0002\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\n0\u0003\u0012\n\b\u0002\u0010\f\u001a\u0004\u0018\u00010\u0006\u0012\n\b\u0002\u0010\r\u001a\u0004\u0018\u00010\u0006\u0012\b\b\u0002\u0010\u000e\u001a\u00020\u000f\u0012\b\b\u0002\u0010\u0010\u001a\u00020\u0011\u0012\b\b\u0002\u0010\u0012\u001a\u00020\u0011\u0012\b\b\u0002\u0010\u0013\u001a\u00020\b\u0012\b\b\u0002\u0010\u0014\u001a\u00020\b¢\u0006\u0004\b\u0015\u0010\u0016J\u000f\u0010,\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003HÆ\u0003J\t\u0010-\u001a\u00020\u0006HÆ\u0003J\t\u0010.\u001a\u00020\bHÆ\u0003J\u000f\u0010/\u001a\b\u0012\u0004\u0012\u00020\n0\u0003HÆ\u0003J\u000f\u00100\u001a\b\u0012\u0004\u0012\u00020\n0\u0003HÆ\u0003J\u000b\u00101\u001a\u0004\u0018\u00010\u0006HÆ\u0003J\u000b\u00102\u001a\u0004\u0018\u00010\u0006HÆ\u0003J\t\u00103\u001a\u00020\u000fHÆ\u0003J\t\u00104\u001a\u00020\u0011HÆ\u0003J\t\u00105\u001a\u00020\u0011HÆ\u0003J\t\u00106\u001a\u00020\bHÆ\u0003J\t\u00107\u001a\u00020\bHÆ\u0003J\u0097\u0001\u00108\u001a\u00020\u00002\u000e\b\u0002\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u00062\b\b\u0002\u0010\u0007\u001a\u00020\b2\u000e\b\u0002\u0010\t\u001a\b\u0012\u0004\u0012\u00020\n0\u00032\u000e\b\u0002\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\n0\u00032\n\b\u0002\u0010\f\u001a\u0004\u0018\u00010\u00062\n\b\u0002\u0010\r\u001a\u0004\u0018\u00010\u00062\b\b\u0002\u0010\u000e\u001a\u00020\u000f2\b\b\u0002\u0010\u0010\u001a\u00020\u00112\b\b\u0002\u0010\u0012\u001a\u00020\u00112\b\b\u0002\u0010\u0013\u001a\u00020\b2\b\b\u0002\u0010\u0014\u001a\u00020\bHÆ\u0001J\u0014\u00109\u001a\u00020\b2\b\u0010:\u001a\u0004\u0018\u00010\u0001HÖ\u0083\u0004J\n\u0010;\u001a\u00020\u0011HÖ\u0081\u0004J\n\u0010<\u001a\u00020\u0006HÖ\u0081\u0004R\u0017\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0017\u0010\u0018R\u0011\u0010\u0005\u001a\u00020\u0006¢\u0006\b\n\u0000\u001a\u0004\b\u0019\u0010\u001aR\u0011\u0010\u0007\u001a\u00020\b¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\u001bR\u0017\u0010\t\u001a\b\u0012\u0004\u0012\u00020\n0\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u001c\u0010\u0018R\u0017\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\n0\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u001d\u0010\u0018R\u0013\u0010\f\u001a\u0004\u0018\u00010\u0006¢\u0006\b\n\u0000\u001a\u0004\b\u001e\u0010\u001aR\u0013\u0010\r\u001a\u0004\u0018\u00010\u0006¢\u0006\b\n\u0000\u001a\u0004\b\u001f\u0010\u001aR\u0011\u0010\u000e\u001a\u00020\u000f¢\u0006\b\n\u0000\u001a\u0004\b \u0010!R\u0011\u0010\u0010\u001a\u00020\u0011¢\u0006\b\n\u0000\u001a\u0004\b\"\u0010#R\u0011\u0010\u0012\u001a\u00020\u0011¢\u0006\b\n\u0000\u001a\u0004\b$\u0010#R\u0011\u0010\u0013\u001a\u00020\b¢\u0006\b\n\u0000\u001a\u0004\b%\u0010\u001bR\u0011\u0010\u0014\u001a\u00020\b¢\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\u001bR\u0011\u0010&\u001a\u00020\b8F¢\u0006\u0006\u001a\u0004\b&\u0010\u001bR\u0017\u0010'\u001a\b\u0012\u0004\u0012\u00020\n0\u00038F¢\u0006\u0006\u001a\u0004\b(\u0010\u0018R\u0011\u0010)\u001a\u00020\u00048F¢\u0006\u0006\u001a\u0004\b*\u0010+¨\u0006="}, d2 = {"Lcom/arflix/tv/ui/screens/watchlist/WatchlistUiState;", "", "sources", "", "Lcom/arflix/tv/ui/screens/watchlist/WatchlistSourceItem;", "selectedSourceId", "", "isLoading", "", "movies", "Lcom/arflix/tv/data/model/MediaItem;", "series", "error", "toastMessage", "toastType", "Lcom/arflix/tv/ui/screens/watchlist/ToastType;", "lastFocusedSectionIndex", "", "lastFocusedItemIndex", "hasMore", "isLoadingMore", "<init>", "(Ljava/util/List;Ljava/lang/String;ZLjava/util/List;Ljava/util/List;Ljava/lang/String;Ljava/lang/String;Lcom/arflix/tv/ui/screens/watchlist/ToastType;IIZZ)V", "getSources", "()Ljava/util/List;", "getSelectedSourceId", "()Ljava/lang/String;", "()Z", "getMovies", "getSeries", "getError", "getToastMessage", "getToastType", "()Lcom/arflix/tv/ui/screens/watchlist/ToastType;", "getLastFocusedSectionIndex", "()I", "getLastFocusedItemIndex", "getHasMore", "isEmpty", "allItems", "getAllItems", "selectedSource", "getSelectedSource", "()Lcom/arflix/tv/ui/screens/watchlist/WatchlistSourceItem;", "component1", "component2", "component3", "component4", "component5", "component6", "component7", "component8", "component9", "component10", "component11", "component12", "copy", "equals", "other", "hashCode", "toString", "app_playRelease"}, k = 1, mv = {2, 3, 0}, xi = 48)
public final /* data */ class WatchlistUiState {
    public static final int $stable = 0;
    private final String error;
    private final boolean hasMore;
    private final boolean isLoading;
    private final boolean isLoadingMore;
    private final int lastFocusedItemIndex;
    private final int lastFocusedSectionIndex;
    private final List<MediaItem> movies;
    private final String selectedSourceId;
    private final List<MediaItem> series;
    private final List<WatchlistSourceItem> sources;
    private final String toastMessage;
    private final ToastType toastType;

    public WatchlistUiState() {
        this(null, null, false, null, null, null, null, null, 0, 0, false, false, 4095, null);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static /* synthetic */ WatchlistUiState copy$default(WatchlistUiState watchlistUiState, List list, String str, boolean z, List list2, List list3, String str2, String str3, ToastType toastType, int i10, int i11, boolean z5, boolean z10, int i12, Object obj) {
        if ((i12 & 1) != 0) {
            list = watchlistUiState.sources;
        }
        if ((i12 & 2) != 0) {
            str = watchlistUiState.selectedSourceId;
        }
        if ((i12 & 4) != 0) {
            z = watchlistUiState.isLoading;
        }
        if ((i12 & 8) != 0) {
            list2 = watchlistUiState.movies;
        }
        if ((i12 & 16) != 0) {
            list3 = watchlistUiState.series;
        }
        if ((i12 & 32) != 0) {
            str2 = watchlistUiState.error;
        }
        if ((i12 & 64) != 0) {
            str3 = watchlistUiState.toastMessage;
        }
        if ((i12 & 128) != 0) {
            toastType = watchlistUiState.toastType;
        }
        if ((i12 & 256) != 0) {
            i10 = watchlistUiState.lastFocusedSectionIndex;
        }
        if ((i12 & 512) != 0) {
            i11 = watchlistUiState.lastFocusedItemIndex;
        }
        if ((i12 & 1024) != 0) {
            z5 = watchlistUiState.hasMore;
        }
        if ((i12 & 2048) != 0) {
            z10 = watchlistUiState.isLoadingMore;
        }
        boolean z11 = z5;
        boolean z12 = z10;
        int i13 = i10;
        int i14 = i11;
        String str4 = str3;
        ToastType toastType2 = toastType;
        List list4 = list3;
        String str5 = str2;
        return watchlistUiState.copy(list, str, z, list2, list4, str5, str4, toastType2, i13, i14, z11, z12);
    }

    public final List<WatchlistSourceItem> component1() {
        return this.sources;
    }

    /* JADX INFO: renamed from: component10, reason: from getter */
    public final int getLastFocusedItemIndex() {
        return this.lastFocusedItemIndex;
    }

    /* JADX INFO: renamed from: component11, reason: from getter */
    public final boolean getHasMore() {
        return this.hasMore;
    }

    /* JADX INFO: renamed from: component12, reason: from getter */
    public final boolean getIsLoadingMore() {
        return this.isLoadingMore;
    }

    /* JADX INFO: renamed from: component2, reason: from getter */
    public final String getSelectedSourceId() {
        return this.selectedSourceId;
    }

    /* JADX INFO: renamed from: component3, reason: from getter */
    public final boolean getIsLoading() {
        return this.isLoading;
    }

    public final List<MediaItem> component4() {
        return this.movies;
    }

    public final List<MediaItem> component5() {
        return this.series;
    }

    /* JADX INFO: renamed from: component6, reason: from getter */
    public final String getError() {
        return this.error;
    }

    /* JADX INFO: renamed from: component7, reason: from getter */
    public final String getToastMessage() {
        return this.toastMessage;
    }

    /* JADX INFO: renamed from: component8, reason: from getter */
    public final ToastType getToastType() {
        return this.toastType;
    }

    /* JADX INFO: renamed from: component9, reason: from getter */
    public final int getLastFocusedSectionIndex() {
        return this.lastFocusedSectionIndex;
    }

    public final WatchlistUiState copy(List<? extends WatchlistSourceItem> sources, String selectedSourceId, boolean isLoading, List<MediaItem> movies, List<MediaItem> series, String error, String toastMessage, ToastType toastType, int lastFocusedSectionIndex, int lastFocusedItemIndex, boolean hasMore, boolean isLoadingMore) {
        return new WatchlistUiState(sources, selectedSourceId, isLoading, movies, series, error, toastMessage, toastType, lastFocusedSectionIndex, lastFocusedItemIndex, hasMore, isLoadingMore);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof WatchlistUiState)) {
            return false;
        }
        WatchlistUiState watchlistUiState = (WatchlistUiState) other;
        return p.a(this.sources, watchlistUiState.sources) && p.a(this.selectedSourceId, watchlistUiState.selectedSourceId) && this.isLoading == watchlistUiState.isLoading && p.a(this.movies, watchlistUiState.movies) && p.a(this.series, watchlistUiState.series) && p.a(this.error, watchlistUiState.error) && p.a(this.toastMessage, watchlistUiState.toastMessage) && this.toastType == watchlistUiState.toastType && this.lastFocusedSectionIndex == watchlistUiState.lastFocusedSectionIndex && this.lastFocusedItemIndex == watchlistUiState.lastFocusedItemIndex && this.hasMore == watchlistUiState.hasMore && this.isLoadingMore == watchlistUiState.isLoadingMore;
    }

    public final List<MediaItem> getAllItems() {
        return x.I0(this.movies, this.series);
    }

    public final String getError() {
        return this.error;
    }

    public final boolean getHasMore() {
        return this.hasMore;
    }

    public final int getLastFocusedItemIndex() {
        return this.lastFocusedItemIndex;
    }

    public final int getLastFocusedSectionIndex() {
        return this.lastFocusedSectionIndex;
    }

    public final List<MediaItem> getMovies() {
        return this.movies;
    }

    public final WatchlistSourceItem getSelectedSource() {
        Object next;
        Iterator<T> it = this.sources.iterator();
        while (true) {
            if (!it.hasNext()) {
                next = null;
                break;
            }
            next = it.next();
            if (p.a(((WatchlistSourceItem) next).getId(), this.selectedSourceId)) {
                break;
            }
        }
        WatchlistSourceItem watchlistSourceItem = (WatchlistSourceItem) next;
        return watchlistSourceItem == null ? WatchlistSourceItem.MyWatchlist.INSTANCE : watchlistSourceItem;
    }

    public final String getSelectedSourceId() {
        return this.selectedSourceId;
    }

    public final List<MediaItem> getSeries() {
        return this.series;
    }

    public final List<WatchlistSourceItem> getSources() {
        return this.sources;
    }

    public final String getToastMessage() {
        return this.toastMessage;
    }

    public final ToastType getToastType() {
        return this.toastType;
    }

    public int hashCode() {
        int iE = androidx.compose.material3.d.e(this.series, androidx.compose.material3.d.e(this.movies, (androidx.compose.foundation.c.c(this.sources.hashCode() * 31, 31, this.selectedSourceId) + (this.isLoading ? 1231 : 1237)) * 31, 31), 31);
        String str = this.error;
        int iHashCode = (iE + (str == null ? 0 : str.hashCode())) * 31;
        String str2 = this.toastMessage;
        return ((((((((this.toastType.hashCode() + ((iHashCode + (str2 != null ? str2.hashCode() : 0)) * 31)) * 31) + this.lastFocusedSectionIndex) * 31) + this.lastFocusedItemIndex) * 31) + (this.hasMore ? 1231 : 1237)) * 31) + (this.isLoadingMore ? 1231 : 1237);
    }

    public final boolean isEmpty() {
        return this.movies.isEmpty() && this.series.isEmpty();
    }

    public final boolean isLoading() {
        return this.isLoading;
    }

    public final boolean isLoadingMore() {
        return this.isLoadingMore;
    }

    public String toString() {
        List<WatchlistSourceItem> list = this.sources;
        String str = this.selectedSourceId;
        boolean z = this.isLoading;
        List<MediaItem> list2 = this.movies;
        List<MediaItem> list3 = this.series;
        String str2 = this.error;
        String str3 = this.toastMessage;
        ToastType toastType = this.toastType;
        int i10 = this.lastFocusedSectionIndex;
        int i11 = this.lastFocusedItemIndex;
        boolean z5 = this.hasMore;
        boolean z10 = this.isLoadingMore;
        StringBuilder sb2 = new StringBuilder("WatchlistUiState(sources=");
        sb2.append(list);
        sb2.append(", selectedSourceId=");
        sb2.append(str);
        sb2.append(", isLoading=");
        sb2.append(z);
        sb2.append(", movies=");
        sb2.append(list2);
        sb2.append(", series=");
        sb2.append(list3);
        sb2.append(", error=");
        sb2.append(str2);
        sb2.append(", toastMessage=");
        sb2.append(str3);
        sb2.append(", toastType=");
        sb2.append(toastType);
        sb2.append(", lastFocusedSectionIndex=");
        a2.y(sb2, i10, ", lastFocusedItemIndex=", i11, ", hasMore=");
        sb2.append(z5);
        sb2.append(", isLoadingMore=");
        sb2.append(z10);
        sb2.append(")");
        return sb2.toString();
    }

    /* JADX WARN: Multi-variable type inference failed */
    public WatchlistUiState(List<? extends WatchlistSourceItem> list, String str, boolean z, List<MediaItem> list2, List<MediaItem> list3, String str2, String str3, ToastType toastType, int i10, int i11, boolean z5, boolean z10) {
        this.sources = list;
        this.selectedSourceId = str;
        this.isLoading = z;
        this.movies = list2;
        this.series = list3;
        this.error = str2;
        this.toastMessage = str3;
        this.toastType = toastType;
        this.lastFocusedSectionIndex = i10;
        this.lastFocusedItemIndex = i11;
        this.hasMore = z5;
        this.isLoadingMore = z10;
    }

    /*  JADX ERROR: NullPointerException in pass: InitCodeVariables
        java.lang.NullPointerException
        */
    public WatchlistUiState(java.util.List r2, java.lang.String r3, boolean r4, java.util.List r5, java.util.List r6, java.lang.String r7, java.lang.String r8, com.arflix.tv.ui.screens.watchlist.ToastType r9, int r10, int r11, boolean r12, boolean r13, int r14, kotlin.jvm.internal.h r15) {
        /*
            r1 = this;
            r15 = r14 & 1
            if (r15 == 0) goto La
            com.arflix.tv.ui.screens.watchlist.WatchlistSourceItem$MyWatchlist r2 = com.arflix.tv.ui.screens.watchlist.WatchlistSourceItem.MyWatchlist.INSTANCE
            java.util.List r2 = java.util.Collections.singletonList(r2)
        La:
            r15 = r14 & 2
            if (r15 == 0) goto L14
            com.arflix.tv.ui.screens.watchlist.WatchlistSourceItem$MyWatchlist r3 = com.arflix.tv.ui.screens.watchlist.WatchlistSourceItem.MyWatchlist.INSTANCE
            java.lang.String r3 = r3.getId()
        L14:
            r15 = r14 & 4
            if (r15 == 0) goto L19
            r4 = 1
        L19:
            r15 = r14 & 8
            kotlin.collections.z r0 = kotlin.collections.z.f19728i
            if (r15 == 0) goto L20
            r5 = r0
        L20:
            r15 = r14 & 16
            if (r15 == 0) goto L25
            r6 = r0
        L25:
            r15 = r14 & 32
            r0 = 0
            if (r15 == 0) goto L2b
            r7 = r0
        L2b:
            r15 = r14 & 64
            if (r15 == 0) goto L30
            r8 = r0
        L30:
            r15 = r14 & 128(0x80, float:1.8E-43)
            if (r15 == 0) goto L36
            com.arflix.tv.ui.screens.watchlist.ToastType r9 = com.arflix.tv.ui.screens.watchlist.ToastType.INFO
        L36:
            r15 = r14 & 256(0x100, float:3.59E-43)
            r0 = 0
            if (r15 == 0) goto L3c
            r10 = r0
        L3c:
            r15 = r14 & 512(0x200, float:7.17E-43)
            if (r15 == 0) goto L41
            r11 = r0
        L41:
            r15 = r14 & 1024(0x400, float:1.435E-42)
            if (r15 == 0) goto L46
            r12 = r0
        L46:
            r14 = r14 & 2048(0x800, float:2.87E-42)
            if (r14 == 0) goto L58
            r15 = r0
            r13 = r11
            r14 = r12
            r11 = r9
            r12 = r10
            r9 = r7
            r10 = r8
            r7 = r5
            r8 = r6
            r5 = r3
            r6 = r4
            r3 = r1
            r4 = r2
            goto L65
        L58:
            r15 = r13
            r14 = r12
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
            r3 = r1
        L65:
            r3.<init>(r4, r5, r6, r7, r8, r9, r10, r11, r12, r13, r14, r15)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.arflix.tv.ui.screens.watchlist.WatchlistUiState.<init>(java.util.List, java.lang.String, boolean, java.util.List, java.util.List, java.lang.String, java.lang.String, com.arflix.tv.ui.screens.watchlist.ToastType, int, int, boolean, boolean, int, kotlin.jvm.internal.h):void");
    }
}
