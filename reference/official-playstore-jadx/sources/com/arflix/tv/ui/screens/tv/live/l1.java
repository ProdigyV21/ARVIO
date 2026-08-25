package com.arflix.tv.ui.screens.tv.live;

import android.content.Context;
import androidx.compose.runtime.DisposableEffectScope;
import androidx.media3.exoplayer.ExoPlayer;
import androidx.media3.ui.PlayerView;

/* JADX INFO: loaded from: classes4.dex */
public final /* synthetic */ class l1 implements r7.l {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final /* synthetic */ int f11578i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final /* synthetic */ ExoPlayer f11579l;

    public /* synthetic */ l1(ExoPlayer exoPlayer, int i10) {
        this.f11578i = i10;
        this.f11579l = exoPlayer;
    }

    @Override // r7.l
    public final Object invoke(Object obj) {
        switch (this.f11578i) {
            case 0:
                return LiveTvScreenKt.LiveTvScreen$lambda$196$0(this.f11579l, (DisposableEffectScope) obj);
            case 1:
                return LiveTvScreenKt.LiveTvScreen$lambda$251$10$0$0(this.f11579l, (Context) obj);
            case 2:
                return LiveTvScreenKt.LiveTvScreen$lambda$251$10$1$0(this.f11579l, (PlayerView) obj);
            case 3:
                return MiniPlayerKt.VideoCard$lambda$1$1$0(this.f11579l, (PlayerView) obj);
            default:
                return MiniPlayerKt.VideoCard$lambda$1$0$0(this.f11579l, (Context) obj);
        }
    }
}
