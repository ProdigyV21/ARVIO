package com.arflix.tv.ui.screens.watchlist;

import androidx.compose.runtime.Composer;
import com.arflix.tv.data.model.MediaItem;
import com.arflix.tv.data.model.MediaType;
import com.arflix.tv.data.repository.HomeServerCatalogCandidate;
import r7.p;
import x6.x;

/* JADX INFO: loaded from: classes4.dex */
public final /* synthetic */ class n implements p {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final /* synthetic */ int f12185i;

    public /* synthetic */ n(int i10) {
        this.f12185i = i10;
    }

    @Override // r7.p
    public final Object invoke(Object obj, Object obj2) {
        switch (this.f12185i) {
            case 0:
                return WatchlistScreenKt.ProviderTabs$lambda$0$0$0(((Integer) obj).intValue(), (LibraryProviderOption) obj2);
            case 1:
                return WatchlistScreenKt.ProviderTabs$lambda$0$0$2(((Integer) obj).intValue(), (LibraryFilter) obj2);
            case 2:
                return ComposableSingletons$WatchlistScreenKt.lambda_1087281494$lambda$0((Composer) obj, ((Integer) obj2).intValue());
            case 3:
                return WatchlistScreenKt.WatchlistItemsSection_7vwJh_E$lambda$2$1$0$0(((Integer) obj).intValue(), (MediaItem) obj2);
            case 4:
                return WatchlistScreenKt.WatchlistContent_05tvjtU$lambda$1$0$0(((Integer) obj).intValue(), (x) obj2);
            case 5:
                return WatchlistScreenKt.WatchlistScreen$lambda$0$0((MediaType) obj, ((Integer) obj2).intValue());
            case 6:
                return WatchlistScreenKt.LibraryResults_8u0NR3k$lambda$1$1$0$0(((Integer) obj).intValue(), (MediaItem) obj2);
            default:
                return WatchlistScreenKt.LibrarySidebar$lambda$0$0$0$0(((Integer) obj).intValue(), (HomeServerCatalogCandidate) obj2);
        }
    }
}
