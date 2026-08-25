package com.arflix.tv;

import androidx.compose.runtime.Composer;
import com.arflix.tv.ui.components.MobileHeroBannerKt;
import com.arflix.tv.ui.screens.home.HomeScreenKt;
import com.arflix.tv.ui.screens.player.PlayerScreenKt;
import com.arflix.tv.ui.screens.plugin.PluginScreenKt;
import com.arflix.tv.ui.screens.tv.TvScreenKt;
import com.arflix.tv.ui.screens.tv.live.FullscreenGuideOverlayKt;
import com.arflix.tv.ui.screens.watchlist.WatchlistScreenKt;
import r7.p;

/* JADX INFO: loaded from: classes3.dex */
public final /* synthetic */ class j implements p {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final /* synthetic */ int f7754i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final /* synthetic */ int f7755l;

    public /* synthetic */ j(int i10, int i11) {
        this.f7754i = i11;
        this.f7755l = i10;
    }

    @Override // r7.p
    public final Object invoke(Object obj, Object obj2) {
        int i10 = this.f7754i;
        Composer composer = (Composer) obj;
        int iIntValue = ((Integer) obj2).intValue();
        switch (i10) {
            case 0:
                return MainActivityKt.ArvioLoadingScreen$lambda$5(this.f7755l, composer, iIntValue);
            case 1:
                return MobileHeroBannerKt.MobileHeroBannerSeriesPreview$lambda$1(this.f7755l, composer, iIntValue);
            case 2:
                return MobileHeroBannerKt.MobileHeroBannerFilmPreview$lambda$1(this.f7755l, composer, iIntValue);
            case 3:
                return HomeScreenKt.IncludedWithPrimeBadge$lambda$1(this.f7755l, composer, iIntValue);
            case 4:
                return PlayerScreenKt.PlayerMetaSeparator$lambda$0(this.f7755l, composer, iIntValue);
            case 5:
                return PluginScreenKt.HideDialogSystemBars$lambda$2(this.f7755l, composer, iIntValue);
            case 6:
                return TvScreenKt.NotConfiguredPanel$lambda$1(this.f7755l, composer, iIntValue);
            case 7:
                return FullscreenGuideOverlayKt.GuideSheetHandle$lambda$1(this.f7755l, composer, iIntValue);
            default:
                return WatchlistScreenKt.CenteredLoading$lambda$1(this.f7755l, composer, iIntValue);
        }
    }
}
