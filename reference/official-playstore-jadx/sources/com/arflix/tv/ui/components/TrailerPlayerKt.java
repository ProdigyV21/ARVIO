package com.arflix.tv.ui.components;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.DisposableEffectResult;
import androidx.compose.runtime.DisposableEffectScope;
import androidx.compose.runtime.MutableState;
import androidx.compose.runtime.RecomposeScopeImplKt;
import androidx.compose.runtime.State;
import androidx.compose.ui.Modifier;
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
import com.arflix.tv.R;
import com.arflix.tv.data.api.InAppYouTubeExtractor;
import java.util.List;
import kotlin.Metadata;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000(\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0010\u0007\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u000b\n\u0002\u0018\u0002\n\u0002\b\b\u001aK\u0010\f\u001a\u00020\n2\u0006\u0010\u0001\u001a\u00020\u00002\b\b\u0002\u0010\u0003\u001a\u00020\u00022\b\b\u0002\u0010\u0005\u001a\u00020\u00042\b\b\u0002\u0010\u0007\u001a\u00020\u00062\u0014\b\u0002\u0010\u000b\u001a\u000e\u0012\u0004\u0012\u00020\t\u0012\u0004\u0012\u00020\n0\bH\u0007¢\u0006\u0004\b\f\u0010\r¨\u0006\u0012²\u0006\u0018\u0010\u000e\u001a\u000e\u0012\u0004\u0012\u00020\t\u0012\u0004\u0012\u00020\n0\b8\nX\u008a\u0084\u0002²\u0006\u000e\u0010\u000f\u001a\u00020\t8\n@\nX\u008a\u008e\u0002²\u0006\u0010\u0010\u0010\u001a\u0004\u0018\u00010\u00008\n@\nX\u008a\u008e\u0002²\u0006\u0010\u0010\u0011\u001a\u0004\u0018\u00010\u00008\n@\nX\u008a\u008e\u0002"}, d2 = {"", "youtubeKey", "Landroidx/compose/ui/Modifier;", "modifier", "", "delayMs", "", "volume", "Lkotlin/Function1;", "", "Lx6/t0;", "onPlayingChanged", "TrailerPlayer", "(Ljava/lang/String;Landroidx/compose/ui/Modifier;JFLr7/l;Landroidx/compose/runtime/Composer;II)V", "currentOnPlayingChanged", "shouldPlay", "videoUrl", "audioUrl", "app_playRelease"}, k = 2, mv = {2, 3, 0}, xi = 48)
public final class TrailerPlayerKt {

    @Metadata(k = 3, mv = {2, 3, 0}, xi = 48)
    public static final /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        static {
            int[] iArr = new int[androidx.lifecycle.p.values().length];
            try {
                iArr[androidx.lifecycle.p.ON_PAUSE.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                iArr[androidx.lifecycle.p.ON_RESUME.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            $EnumSwitchMapping$0 = iArr;
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:104:0x0183  */
    /* JADX WARN: Removed duplicated region for block: B:112:0x01c6  */
    /* JADX WARN: Removed duplicated region for block: B:115:0x0209  */
    /* JADX WARN: Removed duplicated region for block: B:119:0x0217  */
    /* JADX WARN: Removed duplicated region for block: B:121:? A[RETURN, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:23:0x003c  */
    /* JADX WARN: Removed duplicated region for block: B:25:0x0041  */
    /* JADX WARN: Removed duplicated region for block: B:34:0x0057  */
    /* JADX WARN: Removed duplicated region for block: B:36:0x005c  */
    /* JADX WARN: Removed duplicated region for block: B:45:0x0072  */
    /* JADX WARN: Removed duplicated region for block: B:47:0x0077  */
    /* JADX WARN: Removed duplicated region for block: B:56:0x0090  */
    /* JADX WARN: Removed duplicated region for block: B:61:0x00a1  */
    /* JADX WARN: Removed duplicated region for block: B:62:0x00a3  */
    /* JADX WARN: Removed duplicated region for block: B:63:0x00a7  */
    /* JADX WARN: Removed duplicated region for block: B:65:0x00aa  */
    /* JADX WARN: Removed duplicated region for block: B:67:0x00af  */
    /* JADX WARN: Removed duplicated region for block: B:69:0x00b2  */
    /* JADX WARN: Removed duplicated region for block: B:75:0x00d1  */
    /* JADX WARN: Removed duplicated region for block: B:78:0x00f7  */
    /* JADX WARN: Removed duplicated region for block: B:79:0x0102  */
    /* JADX WARN: Removed duplicated region for block: B:82:0x010f  */
    /* JADX WARN: Removed duplicated region for block: B:85:0x0124  */
    /* JADX WARN: Removed duplicated region for block: B:88:0x0137  */
    /* JADX WARN: Removed duplicated region for block: B:91:0x014e  */
    /* JADX WARN: Removed duplicated region for block: B:94:0x0161  */
    /* JADX WARN: Removed duplicated region for block: B:95:0x0163  */
    /* JADX WARN: Removed duplicated region for block: B:98:0x016e  */
    /* JADX WARN: Removed duplicated region for block: B:99:0x0170  */
    /* JADX WARN: Type inference failed for: r12v3 */
    /* JADX WARN: Type inference failed for: r12v4, types: [androidx.compose.animation.core.Easing, androidx.compose.animation.core.FiniteAnimationSpec, java.lang.Object] */
    /* JADX WARN: Type inference failed for: r12v6 */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static final void TrailerPlayer(final java.lang.String r21, androidx.compose.ui.Modifier r22, long r23, float r25, r7.l<? super java.lang.Boolean, x6.t0> r26, androidx.compose.runtime.Composer r27, final int r28, final int r29) {
        /*
            Method dump skipped, instruction units count: 550
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.arflix.tv.ui.components.TrailerPlayerKt.TrailerPlayer(java.lang.String, androidx.compose.ui.Modifier, long, float, r7.l, androidx.compose.runtime.Composer, int, int):void");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final x6.t0 TrailerPlayer$lambda$0$0(boolean z) {
        return x6.t0.f22605a;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final r7.l<Boolean, x6.t0> TrailerPlayer$lambda$1(State<? extends r7.l<? super Boolean, x6.t0>> state) {
        return (r7.l) state.getValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Removed duplicated region for block: B:9:0x0027  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static final x6.t0 TrailerPlayer$lambda$14(java.lang.String r12, float r13, com.arflix.tv.data.api.InAppYouTubeExtractor r14, androidx.compose.runtime.State r15, android.content.Context r16, androidx.compose.runtime.MutableState r17, androidx.compose.runtime.MutableState r18, androidx.compose.runtime.MutableState r19, androidx.compose.animation.AnimatedVisibilityScope r20, androidx.compose.runtime.Composer r21, int r22) {
        /*
            Method dump skipped, instruction units count: 319
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.arflix.tv.ui.components.TrailerPlayerKt.TrailerPlayer$lambda$14(java.lang.String, float, com.arflix.tv.data.api.InAppYouTubeExtractor, androidx.compose.runtime.State, android.content.Context, androidx.compose.runtime.MutableState, androidx.compose.runtime.MutableState, androidx.compose.runtime.MutableState, androidx.compose.animation.AnimatedVisibilityScope, androidx.compose.runtime.Composer, int):x6.t0");
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r4v1, types: [androidx.media3.common.Player$Listener, com.arflix.tv.ui.components.TrailerPlayerKt$TrailerPlayer$3$3$1$listener$1] */
    public static final DisposableEffectResult TrailerPlayer$lambda$14$3$0(final ExoPlayer exoPlayer, final androidx.lifecycle.y yVar, final InAppYouTubeExtractor inAppYouTubeExtractor, final String str, final MutableState mutableState, final State state, DisposableEffectScope disposableEffectScope) {
        final ?? r4 = new Player.Listener() { // from class: com.arflix.tv.ui.components.TrailerPlayerKt$TrailerPlayer$3$3$1$listener$1
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
            public void onPlaybackStateChanged(int playbackState) {
                if (playbackState == 4) {
                    TrailerPlayerKt.TrailerPlayer$lambda$4(mutableState, false);
                    TrailerPlayerKt.TrailerPlayer$lambda$1(state).invoke(Boolean.FALSE);
                }
            }

            @Override // androidx.media3.common.Player.Listener
            public final /* synthetic */ void onPlaybackSuppressionReasonChanged(int i10) {
                androidx.media3.common.j.s(this, i10);
            }

            @Override // androidx.media3.common.Player.Listener
            public void onPlayerError(PlaybackException error) {
                inAppYouTubeExtractor.evictCache(str);
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
        final androidx.lifecycle.w wVar = new androidx.lifecycle.w() { // from class: com.arflix.tv.ui.components.a4
            @Override // androidx.lifecycle.w
            public final void onStateChanged(androidx.lifecycle.y yVar2, androidx.lifecycle.p pVar) {
                TrailerPlayerKt.TrailerPlayer$lambda$14$3$0$0(exoPlayer, mutableState, yVar2, pVar);
            }
        };
        exoPlayer.addListener(r4);
        yVar.getLifecycle().addObserver(wVar);
        if (!yVar.getLifecycle().getCurrentState().a(androidx.lifecycle.q.f3270o)) {
            exoPlayer.pause();
        }
        return new DisposableEffectResult() { // from class: com.arflix.tv.ui.components.TrailerPlayerKt$TrailerPlayer$lambda$14$3$0$$inlined$onDispose$1
            @Override // androidx.compose.runtime.DisposableEffectResult
            public void dispose() {
                yVar.getLifecycle().removeObserver(wVar);
                exoPlayer.removeListener(r4);
                TrailerPlayerKt.TrailerPlayer$lambda$1(state).invoke(Boolean.FALSE);
                exoPlayer.stop();
                exoPlayer.release();
            }
        };
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void TrailerPlayer$lambda$14$3$0$0(ExoPlayer exoPlayer, MutableState mutableState, androidx.lifecycle.y yVar, androidx.lifecycle.p pVar) {
        int i10 = WhenMappings.$EnumSwitchMapping$0[pVar.ordinal()];
        if (i10 == 1) {
            exoPlayer.pause();
        } else if (i10 == 2 && TrailerPlayer$lambda$3(mutableState)) {
            exoPlayer.play();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final PlayerView TrailerPlayer$lambda$14$4$0(ExoPlayer exoPlayer, Context context) {
        PlayerView playerView = (PlayerView) LayoutInflater.from(context).inflate(R.layout.trailer_player_view, (ViewGroup) null);
        playerView.setPlayer(exoPlayer);
        playerView.setResizeMode(4);
        playerView.setKeepScreenOn(true);
        return playerView;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final x6.t0 TrailerPlayer$lambda$14$5$0(ExoPlayer exoPlayer, PlayerView playerView) {
        if (playerView.getPlayer() != exoPlayer) {
            playerView.setPlayer(exoPlayer);
        }
        return x6.t0.f22605a;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final x6.t0 TrailerPlayer$lambda$15(String str, Modifier modifier, long j10, float f10, r7.l lVar, int i10, int i11, Composer composer, int i12) {
        TrailerPlayer(str, modifier, j10, f10, lVar, composer, RecomposeScopeImplKt.updateChangedFlags(i10 | 1), i11);
        return x6.t0.f22605a;
    }

    private static final boolean TrailerPlayer$lambda$3(MutableState<Boolean> mutableState) {
        return mutableState.getValue().booleanValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void TrailerPlayer$lambda$4(MutableState<Boolean> mutableState, boolean z) {
        mutableState.setValue(Boolean.valueOf(z));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final String TrailerPlayer$lambda$6(MutableState<String> mutableState) {
        return mutableState.getValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final String TrailerPlayer$lambda$9(MutableState<String> mutableState) {
        return mutableState.getValue();
    }
}
