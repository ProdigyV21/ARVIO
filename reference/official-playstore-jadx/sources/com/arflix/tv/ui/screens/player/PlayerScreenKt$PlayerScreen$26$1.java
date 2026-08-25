package com.arflix.tv.ui.screens.player;

import android.app.Activity;
import androidx.compose.runtime.MutableIntState;
import androidx.compose.runtime.MutableState;
import androidx.compose.runtime.State;
import androidx.media3.datasource.okhttp.OkHttpDataSource;
import androidx.media3.exoplayer.ExoPlayer;
import androidx.media3.exoplayer.dash.DashMediaSource;
import androidx.media3.exoplayer.hls.HlsMediaSource;
import androidx.media3.exoplayer.source.DefaultMediaSourceFactory;
import androidx.media3.exoplayer.source.ProgressiveMediaSource;
import java.util.Map;
import java.util.Set;
import kotlin.Metadata;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0002\u001a\u00020\u0001*\u00020\u0000H\n¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"Lka/k0;", "Lx6/t0;", "<anonymous>", "(Lka/k0;)V"}, k = 3, mv = {2, 3, 0})
@f7.e(c = "com.arflix.tv.ui.screens.player.PlayerScreenKt$PlayerScreen$26$1", f = "PlayerScreen.kt", l = {1766}, m = "invokeSuspend", v = 2)
public final class PlayerScreenKt$PlayerScreen$26$1 extends f7.j implements r7.p<ka.k0, d7.d<? super x6.t0>, Object> {
    final /* synthetic */ boolean $allowAudioExceedCodecCapabilities;
    final /* synthetic */ boolean $allowRendererExceedCodecCapabilities;
    final /* synthetic */ boolean $allowVideoExceedCodecCapabilities;
    final /* synthetic */ Set<String> $audioDisabledForcedUrls;
    final /* synthetic */ Map<String, String> $baseRequestHeaders;
    final /* synthetic */ MutableState<Long> $blackVideoReadySinceMs$delegate;
    final /* synthetic */ MutableIntState $blackVideoRecoveryStage$delegate;
    final /* synthetic */ MutableState<Long> $bufferingStartTime$delegate;
    final /* synthetic */ DashMediaSource.Factory $dashFactory;
    final /* synthetic */ ProgressiveMediaSource.Factory $directProgressiveDvFactory;
    final /* synthetic */ ProgressiveMediaSource.Factory $directProgressiveFactory;
    final /* synthetic */ MutableIntState $dvStartupFallbackStage$delegate;
    final /* synthetic */ ExoPlayer $exoPlayer;
    final /* synthetic */ MutableState<Boolean> $firstVideoFrameRendered$delegate;
    final /* synthetic */ Activity $frameRateActivity;
    final /* synthetic */ MutableState<Boolean> $hasPlaybackStarted$delegate;
    final /* synthetic */ HlsMediaSource.Factory $hlsFactory;
    final /* synthetic */ OkHttpDataSource.Factory $httpDataSourceFactory;
    final /* synthetic */ State<PlayerUiState> $latestUiState$delegate;
    final /* synthetic */ MutableIntState $longRebufferCount$delegate;
    final /* synthetic */ DefaultMediaSourceFactory $mediaSourceFactory;
    final /* synthetic */ MutableState<Boolean> $pendingStartupFailover$delegate;
    final /* synthetic */ MutableState<String> $pendingStartupFailoverMessage$delegate;
    final /* synthetic */ MutableState<Boolean> $pendingStartupFailureRecorded$delegate;
    final /* synthetic */ MutableState<Boolean> $playbackIssueReported$delegate;
    final /* synthetic */ MutableState<Boolean> $playerReleased$delegate;
    final /* synthetic */ DefaultMediaSourceFactory $preloadMediaSourceFactory;
    final /* synthetic */ MutableState<Long> $readyPlayingSinceMs$delegate;
    final /* synthetic */ MutableState<Boolean> $rebufferRecoverAttempted$delegate;
    final /* synthetic */ Set<String> $safeAudioForcedUrls;
    final /* synthetic */ MutableState<Boolean> $startupHardFailureReported$delegate;
    final /* synthetic */ MutableState<String> $startupPhase$delegate;
    final /* synthetic */ MutableState<Boolean> $startupRecoverAttempted$delegate;
    final /* synthetic */ MutableState<Boolean> $startupSameSourceRefreshAttempted$delegate;
    final /* synthetic */ MutableIntState $startupSameSourceRetryCount$delegate;
    final /* synthetic */ MutableState<String> $startupUrlLock$delegate;
    final /* synthetic */ MutableState<Long> $streamSelectedTime$delegate;
    final /* synthetic */ State<PlayerUiState> $uiState$delegate;
    int I$0;
    long J$0;
    long J$1;
    private /* synthetic */ Object L$0;
    Object L$1;
    Object L$2;
    Object L$3;
    int label;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public PlayerScreenKt$PlayerScreen$26$1(Activity activity, ExoPlayer exoPlayer, OkHttpDataSource.Factory factory, Map<String, String> map, DefaultMediaSourceFactory defaultMediaSourceFactory, HlsMediaSource.Factory factory2, DashMediaSource.Factory factory3, ProgressiveMediaSource.Factory factory4, ProgressiveMediaSource.Factory factory5, DefaultMediaSourceFactory defaultMediaSourceFactory2, MutableState<Boolean> mutableState, State<PlayerUiState> state, MutableState<Long> mutableState2, MutableState<Long> mutableState3, MutableState<Boolean> mutableState4, MutableState<String> mutableState5, MutableState<Boolean> mutableState6, MutableState<Long> mutableState7, MutableState<Boolean> mutableState8, MutableState<Boolean> mutableState9, MutableIntState mutableIntState, MutableState<String> mutableState10, MutableState<Boolean> mutableState11, MutableState<Boolean> mutableState12, MutableIntState mutableIntState2, MutableState<Boolean> mutableState13, MutableState<Boolean> mutableState14, MutableState<String> mutableState15, MutableState<Boolean> mutableState16, MutableIntState mutableIntState3, MutableIntState mutableIntState4, MutableState<Long> mutableState17, Set<String> set, Set<String> set2, boolean z, boolean z5, boolean z10, State<PlayerUiState> state2, d7.d<? super PlayerScreenKt$PlayerScreen$26$1> dVar) {
        super(2, dVar);
        this.$frameRateActivity = activity;
        this.$exoPlayer = exoPlayer;
        this.$httpDataSourceFactory = factory;
        this.$baseRequestHeaders = map;
        this.$preloadMediaSourceFactory = defaultMediaSourceFactory;
        this.$hlsFactory = factory2;
        this.$dashFactory = factory3;
        this.$directProgressiveDvFactory = factory4;
        this.$directProgressiveFactory = factory5;
        this.$mediaSourceFactory = defaultMediaSourceFactory2;
        this.$playerReleased$delegate = mutableState;
        this.$uiState$delegate = state;
        this.$streamSelectedTime$delegate = mutableState2;
        this.$bufferingStartTime$delegate = mutableState3;
        this.$hasPlaybackStarted$delegate = mutableState4;
        this.$startupPhase$delegate = mutableState5;
        this.$firstVideoFrameRendered$delegate = mutableState6;
        this.$readyPlayingSinceMs$delegate = mutableState7;
        this.$playbackIssueReported$delegate = mutableState8;
        this.$rebufferRecoverAttempted$delegate = mutableState9;
        this.$longRebufferCount$delegate = mutableIntState;
        this.$startupUrlLock$delegate = mutableState10;
        this.$startupRecoverAttempted$delegate = mutableState11;
        this.$startupHardFailureReported$delegate = mutableState12;
        this.$startupSameSourceRetryCount$delegate = mutableIntState2;
        this.$startupSameSourceRefreshAttempted$delegate = mutableState13;
        this.$pendingStartupFailover$delegate = mutableState14;
        this.$pendingStartupFailoverMessage$delegate = mutableState15;
        this.$pendingStartupFailureRecorded$delegate = mutableState16;
        this.$dvStartupFallbackStage$delegate = mutableIntState3;
        this.$blackVideoRecoveryStage$delegate = mutableIntState4;
        this.$blackVideoReadySinceMs$delegate = mutableState17;
        this.$audioDisabledForcedUrls = set;
        this.$safeAudioForcedUrls = set2;
        this.$allowVideoExceedCodecCapabilities = z;
        this.$allowAudioExceedCodecCapabilities = z5;
        this.$allowRendererExceedCodecCapabilities = z10;
        this.$latestUiState$delegate = state2;
    }

    @Override // f7.a
    public final d7.d<x6.t0> create(Object obj, d7.d<?> dVar) {
        PlayerScreenKt$PlayerScreen$26$1 playerScreenKt$PlayerScreen$26$1 = new PlayerScreenKt$PlayerScreen$26$1(this.$frameRateActivity, this.$exoPlayer, this.$httpDataSourceFactory, this.$baseRequestHeaders, this.$preloadMediaSourceFactory, this.$hlsFactory, this.$dashFactory, this.$directProgressiveDvFactory, this.$directProgressiveFactory, this.$mediaSourceFactory, this.$playerReleased$delegate, this.$uiState$delegate, this.$streamSelectedTime$delegate, this.$bufferingStartTime$delegate, this.$hasPlaybackStarted$delegate, this.$startupPhase$delegate, this.$firstVideoFrameRendered$delegate, this.$readyPlayingSinceMs$delegate, this.$playbackIssueReported$delegate, this.$rebufferRecoverAttempted$delegate, this.$longRebufferCount$delegate, this.$startupUrlLock$delegate, this.$startupRecoverAttempted$delegate, this.$startupHardFailureReported$delegate, this.$startupSameSourceRetryCount$delegate, this.$startupSameSourceRefreshAttempted$delegate, this.$pendingStartupFailover$delegate, this.$pendingStartupFailoverMessage$delegate, this.$pendingStartupFailureRecorded$delegate, this.$dvStartupFallbackStage$delegate, this.$blackVideoRecoveryStage$delegate, this.$blackVideoReadySinceMs$delegate, this.$audioDisabledForcedUrls, this.$safeAudioForcedUrls, this.$allowVideoExceedCodecCapabilities, this.$allowAudioExceedCodecCapabilities, this.$allowRendererExceedCodecCapabilities, this.$latestUiState$delegate, dVar);
        playerScreenKt$PlayerScreen$26$1.L$0 = obj;
        return playerScreenKt$PlayerScreen$26$1;
    }

    /* JADX WARN: Removed duplicated region for block: B:101:0x02d2  */
    /* JADX WARN: Removed duplicated region for block: B:111:0x0304  */
    /* JADX WARN: Removed duplicated region for block: B:114:0x030b  */
    /* JADX WARN: Removed duplicated region for block: B:115:0x0312  */
    /* JADX WARN: Removed duplicated region for block: B:131:0x0350  */
    /* JADX WARN: Removed duplicated region for block: B:132:0x0356  */
    /* JADX WARN: Removed duplicated region for block: B:135:0x037d  */
    /* JADX WARN: Removed duplicated region for block: B:136:0x0383  */
    /* JADX WARN: Removed duplicated region for block: B:139:0x0390  */
    /* JADX WARN: Removed duplicated region for block: B:140:0x0396  */
    /* JADX WARN: Removed duplicated region for block: B:143:0x03a3  */
    /* JADX WARN: Removed duplicated region for block: B:144:0x03a9  */
    /* JADX WARN: Removed duplicated region for block: B:152:0x03c0  */
    /* JADX WARN: Removed duplicated region for block: B:155:0x03c5  */
    /* JADX WARN: Removed duplicated region for block: B:158:0x03fa  */
    /* JADX WARN: Removed duplicated region for block: B:159:0x0425  */
    /* JADX WARN: Removed duplicated region for block: B:95:0x02b7  */
    /* JADX WARN: Removed duplicated region for block: B:98:0x02c7  */
    @Override // f7.a
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object invokeSuspend(java.lang.Object r17) throws java.lang.Throwable {
        /*
            Method dump skipped, instruction units count: 1089
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.arflix.tv.ui.screens.player.PlayerScreenKt$PlayerScreen$26$1.invokeSuspend(java.lang.Object):java.lang.Object");
    }

    @Override // r7.p
    public final Object invoke(ka.k0 k0Var, d7.d<? super x6.t0> dVar) {
        return ((PlayerScreenKt$PlayerScreen$26$1) create(k0Var, dVar)).invokeSuspend(x6.t0.f22605a);
    }
}
