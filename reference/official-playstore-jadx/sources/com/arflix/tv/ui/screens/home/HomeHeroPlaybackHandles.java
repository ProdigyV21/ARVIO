package com.arflix.tv.ui.screens.home;

import androidx.media3.exoplayer.ExoPlayer;
import androidx.media3.exoplayer.hls.HlsMediaSource;
import kotlin.Metadata;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0083\b\u0018\u00002\u00020\u0001B\u0017\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0004\b\u0006\u0010\u0007J\t\u0010\f\u001a\u00020\u0003HÆ\u0003J\t\u0010\r\u001a\u00020\u0005HÆ\u0003J\u001d\u0010\u000e\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u0005HÆ\u0001J\u0014\u0010\u000f\u001a\u00020\u00102\b\u0010\u0011\u001a\u0004\u0018\u00010\u0001HÖ\u0083\u0004J\n\u0010\u0012\u001a\u00020\u0013HÖ\u0081\u0004J\n\u0010\u0014\u001a\u00020\u0015HÖ\u0081\u0004R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000b¨\u0006\u0016"}, d2 = {"Lcom/arflix/tv/ui/screens/home/HomeHeroPlaybackHandles;", "", "player", "Landroidx/media3/exoplayer/ExoPlayer;", "hlsFactory", "Landroidx/media3/exoplayer/hls/HlsMediaSource$Factory;", "<init>", "(Landroidx/media3/exoplayer/ExoPlayer;Landroidx/media3/exoplayer/hls/HlsMediaSource$Factory;)V", "getPlayer", "()Landroidx/media3/exoplayer/ExoPlayer;", "getHlsFactory", "()Landroidx/media3/exoplayer/hls/HlsMediaSource$Factory;", "component1", "component2", "copy", "equals", "", "other", "hashCode", "", "toString", "", "app_playRelease"}, k = 1, mv = {2, 3, 0}, xi = 48)
final /* data */ class HomeHeroPlaybackHandles {
    private final HlsMediaSource.Factory hlsFactory;
    private final ExoPlayer player;

    public HomeHeroPlaybackHandles(ExoPlayer exoPlayer, HlsMediaSource.Factory factory) {
        this.player = exoPlayer;
        this.hlsFactory = factory;
    }

    public static /* synthetic */ HomeHeroPlaybackHandles copy$default(HomeHeroPlaybackHandles homeHeroPlaybackHandles, ExoPlayer exoPlayer, HlsMediaSource.Factory factory, int i10, Object obj) {
        if ((i10 & 1) != 0) {
            exoPlayer = homeHeroPlaybackHandles.player;
        }
        if ((i10 & 2) != 0) {
            factory = homeHeroPlaybackHandles.hlsFactory;
        }
        return homeHeroPlaybackHandles.copy(exoPlayer, factory);
    }

    /* JADX INFO: renamed from: component1, reason: from getter */
    public final ExoPlayer getPlayer() {
        return this.player;
    }

    /* JADX INFO: renamed from: component2, reason: from getter */
    public final HlsMediaSource.Factory getHlsFactory() {
        return this.hlsFactory;
    }

    public final HomeHeroPlaybackHandles copy(ExoPlayer player, HlsMediaSource.Factory hlsFactory) {
        return new HomeHeroPlaybackHandles(player, hlsFactory);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof HomeHeroPlaybackHandles)) {
            return false;
        }
        HomeHeroPlaybackHandles homeHeroPlaybackHandles = (HomeHeroPlaybackHandles) other;
        return kotlin.jvm.internal.p.a(this.player, homeHeroPlaybackHandles.player) && kotlin.jvm.internal.p.a(this.hlsFactory, homeHeroPlaybackHandles.hlsFactory);
    }

    public final HlsMediaSource.Factory getHlsFactory() {
        return this.hlsFactory;
    }

    public final ExoPlayer getPlayer() {
        return this.player;
    }

    public int hashCode() {
        return this.hlsFactory.hashCode() + (this.player.hashCode() * 31);
    }

    public String toString() {
        return "HomeHeroPlaybackHandles(player=" + this.player + ", hlsFactory=" + this.hlsFactory + ")";
    }
}
