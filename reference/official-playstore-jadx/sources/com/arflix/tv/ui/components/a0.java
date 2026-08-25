package com.arflix.tv.ui.components;

import androidx.compose.foundation.lazy.LazyListScope;
import androidx.compose.runtime.DisposableEffectScope;
import androidx.compose.runtime.MutableState;
import androidx.compose.runtime.State;
import androidx.media3.exoplayer.ExoPlayer;
import com.arflix.tv.data.model.StreamSource;
import com.arflix.tv.data.repository.HomeServerLibrarySort;
import com.arflix.tv.ui.screens.collections.VideoHeroKt;
import com.arflix.tv.ui.screens.details.DetailsScreenKt;
import com.arflix.tv.ui.screens.details.DetailsViewModel;
import com.arflix.tv.ui.screens.watchlist.WatchlistScreenKt;
import com.arflix.tv.ui.screens.watchlist.WatchlistViewModel;
import java.util.List;

/* JADX INFO: loaded from: classes4.dex */
public final /* synthetic */ class a0 implements r7.l {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final /* synthetic */ int f7785i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final /* synthetic */ Object f7786l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ Object f7787m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final /* synthetic */ Object f7788n;

    public /* synthetic */ a0(Object obj, Object obj2, Object obj3, int i10) {
        this.f7785i = i10;
        this.f7786l = obj;
        this.f7787m = obj2;
        this.f7788n = obj3;
    }

    @Override // r7.l
    public final Object invoke(Object obj) {
        switch (this.f7785i) {
            case 0:
                return ContextMenuKt.SeasonContextMenu$lambda$0$0((r7.a) this.f7786l, (r7.a) this.f7787m, (r7.a) this.f7788n, (ContextAction) obj);
            case 1:
                return StreamSelectorKt.StreamSelector$lambda$44$0$1$3$3$0((List) this.f7786l, (StreamSource) this.f7787m, (r7.l) this.f7788n, (LazyListScope) obj);
            case 2:
                return VideoHeroKt.VideoHero$lambda$3$0((androidx.lifecycle.y) this.f7786l, (ExoPlayer) this.f7787m, (State) this.f7788n, (DisposableEffectScope) obj);
            case 3:
                return DetailsScreenKt.DetailsScreen$lambda$82$0((androidx.lifecycle.y) this.f7786l, (DetailsViewModel) this.f7787m, (MutableState) this.f7788n, (DisposableEffectScope) obj);
            default:
                return WatchlistScreenKt.WatchlistScreen$lambda$74$5$0((WatchlistViewModel) this.f7786l, (MutableState) this.f7787m, (MutableState) this.f7788n, (HomeServerLibrarySort) obj);
        }
    }
}
