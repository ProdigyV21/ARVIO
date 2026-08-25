package com.arflix.tv.ui.components;

import androidx.compose.runtime.State;
import androidx.compose.ui.graphics.GraphicsLayerScope;
import androidx.compose.ui.graphics.drawscope.DrawScope;
import com.arflix.tv.ui.screens.search.SearchScreenKt;
import com.arflix.tv.ui.screens.watchlist.WatchlistScreenKt;

/* JADX INFO: loaded from: classes4.dex */
public final /* synthetic */ class c implements r7.l {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final /* synthetic */ int f7851i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final /* synthetic */ State f7852l;

    public /* synthetic */ c(State state, int i10) {
        this.f7851i = i10;
        this.f7852l = state;
    }

    @Override // r7.l
    public final Object invoke(Object obj) {
        switch (this.f7851i) {
            case 0:
                return AppTopBarKt.TopBarProfileAvatar$lambda$2$0(this.f7852l, (GraphicsLayerScope) obj);
            case 1:
                return AppTopBarKt.TopBarSettingsGear$lambda$3$0(this.f7852l, (GraphicsLayerScope) obj);
            case 2:
                return AppTopBarKt.TopBarNavChip$lambda$4$0(this.f7852l, (GraphicsLayerScope) obj);
            case 3:
                return PlayerLoadingScreenKt.BufferingIndicator$lambda$1$0$0$0(this.f7852l, (DrawScope) obj);
            case 4:
                return PremiumEffectsKt.ShimmerEffect$lambda$1$0(this.f7852l, (DrawScope) obj);
            case 5:
                return SidebarKt.SidebarProfileAvatar$lambda$2$0$0(this.f7852l, (GraphicsLayerScope) obj);
            case 6:
                return SidebarKt.SidebarIcon$lambda$4$0$0$0(this.f7852l, (GraphicsLayerScope) obj);
            case 7:
                return SearchScreenKt.RowsLayer$lambda$4$0$0$1$2$0(this.f7852l, (GraphicsLayerScope) obj);
            case 8:
                return WatchlistScreenKt.LibraryResults_8u0NR3k$lambda$1$0$0(this.f7852l, (GraphicsLayerScope) obj);
            default:
                return WatchlistScreenKt.SelectablePill_dR3JOV0$lambda$1$0(this.f7852l, (GraphicsLayerScope) obj);
        }
    }
}
