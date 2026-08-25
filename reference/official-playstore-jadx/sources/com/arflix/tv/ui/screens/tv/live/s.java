package com.arflix.tv.ui.screens.tv.live;

import androidx.compose.runtime.MutableState;
import androidx.compose.ui.focus.FocusState;
import coil.compose.AsyncImagePainter;

/* JADX INFO: loaded from: classes4.dex */
public final /* synthetic */ class s implements r7.l {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final /* synthetic */ int f11747i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final /* synthetic */ MutableState f11748l;

    public /* synthetic */ s(MutableState mutableState, int i10) {
        this.f11747i = i10;
        this.f11748l = mutableState;
    }

    @Override // r7.l
    public final Object invoke(Object obj) {
        switch (this.f11747i) {
            case 0:
                return ChannelLogoKt.ChannelLogo_uFdPcIQ$lambda$3$1$0(this.f11748l, (AsyncImagePainter.State.Success) obj);
            case 1:
                return ChannelLogoKt.ChannelLogo_uFdPcIQ$lambda$3$2$0(this.f11748l, (AsyncImagePainter.State.Error) obj);
            case 2:
                return FullscreenGuideOverlayKt.GuideProgramRow$lambda$3$0(this.f11748l, (FocusState) obj);
            case 3:
                return FullscreenHudKt.HudIconButton$lambda$3$0(this.f11748l, (FocusState) obj);
            case 4:
                return FullscreenHudKt.HudActionButton$lambda$3$0(this.f11748l, (FocusState) obj);
            case 5:
                return FullscreenHudKt.HudSeekBar$lambda$3$0(this.f11748l, (FocusState) obj);
            case 6:
                return LiveTvEnhancementsKt.VariantRow$lambda$3$0(this.f11748l, (FocusState) obj);
            case 7:
                return LiveTvScreenKt.LiveTvScreen$lambda$251$10$14$0(this.f11748l, ((Boolean) obj).booleanValue());
            case 8:
                return LiveTvScreenKt.LiveTvScreen$lambda$251$10$18$0(this.f11748l, (String) obj);
            case 9:
                return LiveTvScreenKt.LiveTvScreen$lambda$251$2$10$6$0(this.f11748l, (FocusState) obj);
            case 10:
                return SearchOverlayKt.SearchOverlay$lambda$16$1$0$3$0(this.f11748l, (String) obj);
            default:
                return SearchOverlayKt.SearchResultRow$lambda$3$0(this.f11748l, (FocusState) obj);
        }
    }
}
