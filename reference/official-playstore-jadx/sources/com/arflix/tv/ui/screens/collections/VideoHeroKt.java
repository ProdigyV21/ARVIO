package com.arflix.tv.ui.screens.collections;

import android.content.Context;
import android.view.ViewGroup;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.DisposableEffectResult;
import androidx.compose.runtime.DisposableEffectScope;
import androidx.compose.runtime.RecomposeScopeImplKt;
import androidx.compose.runtime.State;
import androidx.compose.ui.Modifier;
import androidx.lifecycle.y;
import androidx.media3.common.AudioAttributes;
import androidx.media3.common.DeviceInfo;
import androidx.media3.common.MediaItem;
import androidx.media3.common.MediaMetadata;
import androidx.media3.common.PlaybackException;
import androidx.media3.common.PlaybackParameters;
import androidx.media3.common.Player;
import androidx.media3.common.Timeline;
import androidx.media3.common.TrackSelectionParameters;
import androidx.media3.common.Tracks;
import androidx.media3.common.VideoSize;
import androidx.media3.common.text.CueGroup;
import androidx.media3.exoplayer.ExoPlayer;
import androidx.media3.ui.PlayerView;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.f0;
import x6.t0;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000\u0018\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\u001a/\u0010\u0007\u001a\u00020\u00052\u0006\u0010\u0001\u001a\u00020\u00002\b\b\u0002\u0010\u0003\u001a\u00020\u00022\f\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004H\u0007¢\u0006\u0004\b\u0007\u0010\b¨\u0006\n²\u0006\u0012\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u00050\u00048\nX\u008a\u0084\u0002"}, d2 = {"", "videoUrl", "Landroidx/compose/ui/Modifier;", "modifier", "Lkotlin/Function0;", "Lx6/t0;", "onEnded", "VideoHero", "(Ljava/lang/String;Landroidx/compose/ui/Modifier;Lr7/a;Landroidx/compose/runtime/Composer;II)V", "currentOnEnded", "app_playRelease"}, k = 2, mv = {2, 3, 0}, xi = 48)
public final class VideoHeroKt {
    /* JADX WARN: Removed duplicated region for block: B:46:0x0098  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static final void VideoHero(java.lang.String r12, androidx.compose.ui.Modifier r13, r7.a<x6.t0> r14, androidx.compose.runtime.Composer r15, int r16, int r17) {
        /*
            Method dump skipped, instruction units count: 356
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.arflix.tv.ui.screens.collections.VideoHeroKt.VideoHero(java.lang.String, androidx.compose.ui.Modifier, r7.a, androidx.compose.runtime.Composer, int, int):void");
    }

    private static final r7.a<t0> VideoHero$lambda$0(State<? extends r7.a<t0>> state) {
        return state.getValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r3v1, types: [androidx.media3.common.Player$Listener, com.arflix.tv.ui.screens.collections.VideoHeroKt$VideoHero$1$1$listener$1] */
    public static final DisposableEffectResult VideoHero$lambda$2$0(final ExoPlayer exoPlayer, State state, DisposableEffectScope disposableEffectScope) {
        final e eVar = new e(new f0(), state, 2);
        final ?? r32 = new Player.Listener() { // from class: com.arflix.tv.ui.screens.collections.VideoHeroKt$VideoHero$1$1$listener$1
            @Override // androidx.media3.common.Player.Listener
            public final /* synthetic */ void onAudioAttributesChanged(AudioAttributes audioAttributes) {
                androidx.media3.common.j.a(this, audioAttributes);
            }

            @Override // androidx.media3.common.Player.Listener
            public final /* synthetic */ void onAudioSessionIdChanged(int i10) {
                androidx.media3.common.j.b(this, i10);
            }

            @Override // androidx.media3.common.Player.Listener
            public final /* synthetic */ void onAvailableCommandsChanged(Player.Commands commands) {
                androidx.media3.common.j.c(this, commands);
            }

            @Override // androidx.media3.common.Player.Listener
            public final /* synthetic */ void onCues(CueGroup cueGroup) {
                androidx.media3.common.j.d(this, cueGroup);
            }

            @Override // androidx.media3.common.Player.Listener
            public final /* synthetic */ void onDeviceInfoChanged(DeviceInfo deviceInfo) {
                androidx.media3.common.j.f(this, deviceInfo);
            }

            @Override // androidx.media3.common.Player.Listener
            public final /* synthetic */ void onDeviceVolumeChanged(int i10, boolean z) {
                androidx.media3.common.j.g(this, i10, z);
            }

            @Override // androidx.media3.common.Player.Listener
            public final /* synthetic */ void onEvents(Player player, Player.Events events) {
                androidx.media3.common.j.h(this, player, events);
            }

            @Override // androidx.media3.common.Player.Listener
            public final /* synthetic */ void onIsLoadingChanged(boolean z) {
                androidx.media3.common.j.i(this, z);
            }

            @Override // androidx.media3.common.Player.Listener
            public final /* synthetic */ void onIsPlayingChanged(boolean z) {
                androidx.media3.common.j.j(this, z);
            }

            @Override // androidx.media3.common.Player.Listener
            public final /* synthetic */ void onLoadingChanged(boolean z) {
                androidx.media3.common.j.k(this, z);
            }

            @Override // androidx.media3.common.Player.Listener
            public final /* synthetic */ void onMaxSeekToPreviousPositionChanged(long j10) {
                androidx.media3.common.j.l(this, j10);
            }

            @Override // androidx.media3.common.Player.Listener
            public final /* synthetic */ void onMediaItemTransition(MediaItem mediaItem, int i10) {
                androidx.media3.common.j.m(this, mediaItem, i10);
            }

            @Override // androidx.media3.common.Player.Listener
            public final /* synthetic */ void onMediaMetadataChanged(MediaMetadata mediaMetadata) {
                androidx.media3.common.j.n(this, mediaMetadata);
            }

            @Override // androidx.media3.common.Player.Listener
            public final /* synthetic */ void onMetadata(androidx.media3.common.Metadata metadata) {
                androidx.media3.common.j.o(this, metadata);
            }

            @Override // androidx.media3.common.Player.Listener
            public final /* synthetic */ void onPlayWhenReadyChanged(boolean z, int i10) {
                androidx.media3.common.j.p(this, z, i10);
            }

            @Override // androidx.media3.common.Player.Listener
            public final /* synthetic */ void onPlaybackParametersChanged(PlaybackParameters playbackParameters) {
                androidx.media3.common.j.q(this, playbackParameters);
            }

            @Override // androidx.media3.common.Player.Listener
            public void onPlaybackStateChanged(int state2) {
                if (state2 == 4) {
                    eVar.invoke();
                }
            }

            @Override // androidx.media3.common.Player.Listener
            public final /* synthetic */ void onPlaybackSuppressionReasonChanged(int i10) {
                androidx.media3.common.j.s(this, i10);
            }

            @Override // androidx.media3.common.Player.Listener
            public void onPlayerError(PlaybackException error) {
                androidx.compose.material3.d.y("hero video playback error — falling back to static: ", error.getMessage(), "VideoHero");
                eVar.invoke();
            }

            @Override // androidx.media3.common.Player.Listener
            public final /* synthetic */ void onPlayerErrorChanged(PlaybackException playbackException) {
                androidx.media3.common.j.u(this, playbackException);
            }

            @Override // androidx.media3.common.Player.Listener
            public final /* synthetic */ void onPlayerStateChanged(boolean z, int i10) {
                androidx.media3.common.j.v(this, z, i10);
            }

            @Override // androidx.media3.common.Player.Listener
            public final /* synthetic */ void onPlaylistMetadataChanged(MediaMetadata mediaMetadata) {
                androidx.media3.common.j.w(this, mediaMetadata);
            }

            @Override // androidx.media3.common.Player.Listener
            public final /* synthetic */ void onPositionDiscontinuity(int i10) {
                androidx.media3.common.j.x(this, i10);
            }

            @Override // androidx.media3.common.Player.Listener
            public final /* synthetic */ void onRenderedFirstFrame() {
                androidx.media3.common.j.z(this);
            }

            @Override // androidx.media3.common.Player.Listener
            public final /* synthetic */ void onRepeatModeChanged(int i10) {
                androidx.media3.common.j.A(this, i10);
            }

            @Override // androidx.media3.common.Player.Listener
            public final /* synthetic */ void onSeekBackIncrementChanged(long j10) {
                androidx.media3.common.j.B(this, j10);
            }

            @Override // androidx.media3.common.Player.Listener
            public final /* synthetic */ void onSeekForwardIncrementChanged(long j10) {
                androidx.media3.common.j.C(this, j10);
            }

            @Override // androidx.media3.common.Player.Listener
            public final /* synthetic */ void onShuffleModeEnabledChanged(boolean z) {
                androidx.media3.common.j.D(this, z);
            }

            @Override // androidx.media3.common.Player.Listener
            public final /* synthetic */ void onSkipSilenceEnabledChanged(boolean z) {
                androidx.media3.common.j.E(this, z);
            }

            @Override // androidx.media3.common.Player.Listener
            public final /* synthetic */ void onSurfaceSizeChanged(int i10, int i11) {
                androidx.media3.common.j.F(this, i10, i11);
            }

            @Override // androidx.media3.common.Player.Listener
            public final /* synthetic */ void onTimelineChanged(Timeline timeline, int i10) {
                androidx.media3.common.j.G(this, timeline, i10);
            }

            @Override // androidx.media3.common.Player.Listener
            public final /* synthetic */ void onTrackSelectionParametersChanged(TrackSelectionParameters trackSelectionParameters) {
                androidx.media3.common.j.H(this, trackSelectionParameters);
            }

            @Override // androidx.media3.common.Player.Listener
            public final /* synthetic */ void onTracksChanged(Tracks tracks) {
                androidx.media3.common.j.I(this, tracks);
            }

            @Override // androidx.media3.common.Player.Listener
            public final /* synthetic */ void onVideoSizeChanged(VideoSize videoSize) {
                androidx.media3.common.j.J(this, videoSize);
            }

            @Override // androidx.media3.common.Player.Listener
            public final /* synthetic */ void onVolumeChanged(float f10) {
                androidx.media3.common.j.K(this, f10);
            }

            @Override // androidx.media3.common.Player.Listener
            public final /* synthetic */ void onCues(List list) {
                androidx.media3.common.j.e(this, list);
            }

            @Override // androidx.media3.common.Player.Listener
            public final /* synthetic */ void onPositionDiscontinuity(Player.PositionInfo positionInfo, Player.PositionInfo positionInfo2, int i10) {
                androidx.media3.common.j.y(this, positionInfo, positionInfo2, i10);
            }
        };
        exoPlayer.addListener(r32);
        return new DisposableEffectResult() { // from class: com.arflix.tv.ui.screens.collections.VideoHeroKt$VideoHero$lambda$2$0$$inlined$onDispose$1
            @Override // androidx.compose.runtime.DisposableEffectResult
            public void dispose() {
                exoPlayer.removeListener(r32);
                exoPlayer.release();
            }
        };
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final t0 VideoHero$lambda$2$0$0(f0 f0Var, State state) {
        if (!f0Var.f19738i) {
            f0Var.f19738i = true;
            VideoHero$lambda$0(state).invoke();
        }
        return t0.f22605a;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final DisposableEffectResult VideoHero$lambda$3$0(final y yVar, ExoPlayer exoPlayer, State state, DisposableEffectScope disposableEffectScope) {
        final androidx.activity.i iVar = new androidx.activity.i(exoPlayer, state, 2);
        yVar.getLifecycle().addObserver(iVar);
        return new DisposableEffectResult() { // from class: com.arflix.tv.ui.screens.collections.VideoHeroKt$VideoHero$lambda$3$0$$inlined$onDispose$1
            @Override // androidx.compose.runtime.DisposableEffectResult
            public void dispose() {
                yVar.getLifecycle().removeObserver(iVar);
            }
        };
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void VideoHero$lambda$3$0$0(ExoPlayer exoPlayer, State state, y yVar, androidx.lifecycle.p pVar) {
        if (pVar == androidx.lifecycle.p.ON_STOP) {
            exoPlayer.pause();
            VideoHero$lambda$0(state).invoke();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final PlayerView VideoHero$lambda$4$0(Context context) {
        PlayerView playerView = new PlayerView(context);
        playerView.setUseController(false);
        playerView.setControllerAutoShow(false);
        playerView.hideController();
        playerView.setFocusable(false);
        playerView.setFocusableInTouchMode(false);
        playerView.setDescendantFocusability(393216);
        playerView.setResizeMode(4);
        playerView.setLayoutParams(new ViewGroup.LayoutParams(-1, -1));
        return playerView;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final t0 VideoHero$lambda$5$0(ExoPlayer exoPlayer, PlayerView playerView) {
        playerView.setUseController(false);
        playerView.setControllerAutoShow(false);
        playerView.hideController();
        playerView.setPlayer(exoPlayer);
        return t0.f22605a;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final t0 VideoHero$lambda$6(String str, Modifier modifier, r7.a aVar, int i10, int i11, Composer composer, int i12) {
        VideoHero(str, modifier, aVar, composer, RecomposeScopeImplKt.updateChangedFlags(i10 | 1), i11);
        return t0.f22605a;
    }
}
