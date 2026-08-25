package com.arflix.tv.ui.screens.home;

import android.content.Context;
import androidx.compose.runtime.MutableState;
import androidx.media3.common.MediaItem;
import androidx.media3.exoplayer.ExoPlayer;
import java.util.Locale;
import kotlin.Metadata;
import x6.t0;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0002\u001a\u00020\u0001*\u00020\u0000H\n¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"Lka/k0;", "Lx6/t0;", "<anonymous>", "(Lka/k0;)V"}, k = 3, mv = {2, 3, 0})
@f7.e(c = "com.arflix.tv.ui.screens.home.HomeScreenKt$HomeScreen$24$1", f = "HomeScreen.kt", l = {}, m = "invokeSuspend", v = 2)
public final class HomeScreenKt$HomeScreen$24$1 extends f7.j implements r7.p<ka.k0, d7.d<? super t0>, Object> {
    final /* synthetic */ Context $context;
    final /* synthetic */ MutableState<HomeHeroPlaybackHandles> $heroPlaybackHandles$delegate;
    final /* synthetic */ String $heroVideoUrl;
    final /* synthetic */ MutableState<String> $preparedHeroVideoUrl$delegate;
    int label;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public HomeScreenKt$HomeScreen$24$1(String str, Context context, MutableState<HomeHeroPlaybackHandles> mutableState, MutableState<String> mutableState2, d7.d<? super HomeScreenKt$HomeScreen$24$1> dVar) {
        super(2, dVar);
        this.$heroVideoUrl = str;
        this.$context = context;
        this.$heroPlaybackHandles$delegate = mutableState;
        this.$preparedHeroVideoUrl$delegate = mutableState2;
    }

    @Override // f7.a
    public final d7.d<t0> create(Object obj, d7.d<?> dVar) {
        return new HomeScreenKt$HomeScreen$24$1(this.$heroVideoUrl, this.$context, this.$heroPlaybackHandles$delegate, this.$preparedHeroVideoUrl$delegate, dVar);
    }

    @Override // f7.a
    public final Object invokeSuspend(Object obj) {
        if (this.label != 0) {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        k2.c.G(obj);
        if (this.$heroVideoUrl != null && HomeScreenKt.HomeScreen$lambda$66(this.$heroPlaybackHandles$delegate) == null) {
            this.$heroPlaybackHandles$delegate.setValue(HomeScreenKt.createHomeHeroPlaybackHandles(this.$context));
        }
        HomeHeroPlaybackHandles homeHeroPlaybackHandlesHomeScreen$lambda$66 = HomeScreenKt.HomeScreen$lambda$66(this.$heroPlaybackHandles$delegate);
        ExoPlayer player = homeHeroPlaybackHandlesHomeScreen$lambda$66 != null ? homeHeroPlaybackHandlesHomeScreen$lambda$66.getPlayer() : null;
        String str = this.$heroVideoUrl;
        t0 t0Var = t0.f22605a;
        if (str != null) {
            HomeHeroPlaybackHandles homeHeroPlaybackHandlesHomeScreen$lambda$662 = HomeScreenKt.HomeScreen$lambda$66(this.$heroPlaybackHandles$delegate);
            if (homeHeroPlaybackHandlesHomeScreen$lambda$662 != null) {
                if (!kotlin.jvm.internal.p.a(HomeScreenKt.HomeScreen$lambda$69(this.$preparedHeroVideoUrl$delegate), this.$heroVideoUrl)) {
                    if (player != null) {
                        player.stop();
                    }
                    if (player != null) {
                        player.clearMediaItems();
                    }
                    MediaItem mediaItemBuild = new MediaItem.Builder().setUri(this.$heroVideoUrl).setLiveConfiguration(new MediaItem.LiveConfiguration.Builder().setMinPlaybackSpeed(1.0f).setMaxPlaybackSpeed(1.0f).setTargetOffsetMs(4000L).build()).build();
                    String lowerCase = this.$heroVideoUrl.toLowerCase(Locale.ROOT);
                    if (kotlin.text.o.T(lowerCase, ".m3u8", false) || kotlin.text.o.T(lowerCase, "/hls", false) || kotlin.text.o.T(lowerCase, "format=hls", false)) {
                        if (player != null) {
                            player.setMediaSource(homeHeroPlaybackHandlesHomeScreen$lambda$662.getHlsFactory().createMediaSource(mediaItemBuild));
                        }
                    } else if (player != null) {
                        player.setMediaItem(mediaItemBuild);
                    }
                    if (player != null) {
                        player.prepare();
                    }
                    this.$preparedHeroVideoUrl$delegate.setValue(this.$heroVideoUrl);
                }
                if (player != null) {
                    player.setRepeatMode(0);
                }
                if (player != null) {
                    player.setVolume(1.0f);
                }
                if (player != null) {
                    player.setPlayWhenReady(true);
                    return t0Var;
                }
            }
        } else if (player != null) {
            player.setPlayWhenReady(false);
        }
        return t0Var;
    }

    @Override // r7.p
    public final Object invoke(ka.k0 k0Var, d7.d<? super t0> dVar) {
        return ((HomeScreenKt$HomeScreen$24$1) create(k0Var, dVar)).invokeSuspend(t0.f22605a);
    }
}
