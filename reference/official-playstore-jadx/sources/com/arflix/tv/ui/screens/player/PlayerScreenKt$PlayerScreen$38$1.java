package com.arflix.tv.ui.screens.player;

import androidx.compose.runtime.MutableFloatState;
import androidx.compose.runtime.MutableIntState;
import androidx.compose.runtime.MutableLongState;
import androidx.compose.runtime.MutableState;
import androidx.compose.runtime.State;
import androidx.media3.exoplayer.ExoPlayer;
import com.arflix.tv.data.model.EpisodeIdentity;
import com.arflix.tv.data.model.MediaType;
import com.arflix.tv.player.dv.DolbyVisionStripExtractorsFactory;
import java.util.Set;
import java.util.concurrent.atomic.AtomicBoolean;
import kotlin.Metadata;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0002\u001a\u00020\u0001*\u00020\u0000H\n¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"Lka/k0;", "Lx6/t0;", "<anonymous>", "(Lka/k0;)V"}, k = 3, mv = {2, 3, 0})
@f7.e(c = "com.arflix.tv.ui.screens.player.PlayerScreenKt$PlayerScreen$38$1", f = "PlayerScreen.kt", l = {2188, 2484}, m = "invokeSuspend", v = 2)
public final class PlayerScreenKt$PlayerScreen$38$1 extends f7.j implements r7.p<ka.k0, d7.d<? super x6.t0>, Object> {
    final /* synthetic */ boolean $allowMidPlaybackSourceFallback;
    final /* synthetic */ boolean $allowRendererExceedCodecCapabilities;
    final /* synthetic */ boolean $allowStartupSourceFallback;
    final /* synthetic */ boolean $allowVideoExceedCodecCapabilities;
    final /* synthetic */ MutableIntState $autoAdvanceAttempts$delegate;
    final /* synthetic */ MutableState<Long> $blackVideoReadySinceMs$delegate;
    final /* synthetic */ MutableIntState $blackVideoRecoveryStage$delegate;
    final /* synthetic */ MutableState<Long> $bufferingStartTime$delegate;
    final /* synthetic */ long $bufferingTimeoutMs;
    final /* synthetic */ MutableLongState $currentPosition$delegate;
    final /* synthetic */ MutableIntState $currentStreamIndex$delegate;
    final /* synthetic */ MutableLongState $duration$delegate;
    final /* synthetic */ Set<String> $dvForcedStripUrls;
    final /* synthetic */ MutableIntState $dvStartupFallbackStage$delegate;
    final /* synthetic */ DolbyVisionStripExtractorsFactory $dvStripExtractorsFactory;
    final /* synthetic */ Integer $episodeNumber;
    final /* synthetic */ ExoPlayer $exoPlayer;
    final /* synthetic */ MutableState<Boolean> $firstVideoFrameRendered$delegate;
    final /* synthetic */ MutableState<Boolean> $hasPlaybackStarted$delegate;
    final /* synthetic */ long $initialBufferingTimeoutMs;
    final /* synthetic */ MutableState<Boolean> $isAutoAdvancing$delegate;
    final /* synthetic */ MutableState<Boolean> $isBuffering$delegate;
    final /* synthetic */ boolean $isCasting;
    final /* synthetic */ MutableState<Boolean> $isPlaying$delegate;
    final /* synthetic */ MutableLongState $lastProgressReportSecond$delegate;
    final /* synthetic */ State<PlayerUiState> $latestUiState$delegate;
    final /* synthetic */ MutableIntState $longRebufferCount$delegate;
    final /* synthetic */ int $mediaId;
    final /* synthetic */ MediaType $mediaType;
    final /* synthetic */ MutableIntState $midPlaybackRecoveryAttempts$delegate;
    final /* synthetic */ MutableState<EpisodeIdentity> $nextEpisodeIdentity$delegate;
    final /* synthetic */ MutableIntState $nextEpisodePromptButton$delegate;
    final /* synthetic */ NextEpisodePromptGate $nextEpisodePromptGate;
    final /* synthetic */ MutableState<String> $pendingNextAddonId$delegate;
    final /* synthetic */ MutableState<String> $pendingNextBingeGroup$delegate;
    final /* synthetic */ MutableState<EpisodeIdentity> $pendingNextIdentity$delegate;
    final /* synthetic */ MutableState<String> $pendingNextSourceName$delegate;
    final /* synthetic */ MutableState<Boolean> $pendingStartupFailover$delegate;
    final /* synthetic */ MutableState<String> $pendingStartupFailoverMessage$delegate;
    final /* synthetic */ MutableState<Boolean> $pendingStartupFailureRecorded$delegate;
    final /* synthetic */ MutableState<Boolean> $playbackIssueReported$delegate;
    final /* synthetic */ AtomicBoolean $playerReleasedAtomic;
    final /* synthetic */ MutableFloatState $progress$delegate;
    final /* synthetic */ MutableState<Long> $readyPlayingSinceMs$delegate;
    final /* synthetic */ MutableState<Boolean> $rebufferRecoverAttempted$delegate;
    final /* synthetic */ Integer $seasonNumber;
    final /* synthetic */ MutableState<Boolean> $showNextEpisodePrompt$delegate;
    final /* synthetic */ MutableState<Boolean> $showSourceMenu$delegate;
    final /* synthetic */ MutableState<Boolean> $showSubtitleMenu$delegate;
    final /* synthetic */ MutableState<Boolean> $startupHardFailureReported$delegate;
    final /* synthetic */ MutableState<Boolean> $startupRecoverAttempted$delegate;
    final /* synthetic */ MutableState<Boolean> $startupSameSourceRefreshAttempted$delegate;
    final /* synthetic */ MutableIntState $startupSameSourceRetryCount$delegate;
    final /* synthetic */ MutableState<String> $startupUrlLock$delegate;
    final /* synthetic */ MutableState<Long> $streamSelectedTime$delegate;
    final /* synthetic */ MutableState<String> $switchNotice$delegate;
    final /* synthetic */ MutableLongState $switchNoticeUntilMs$delegate;
    final /* synthetic */ Integer $tmdbEpisodeNumber;
    final /* synthetic */ Integer $tmdbSeasonNumber;
    final /* synthetic */ MutableState<Set<Integer>> $triedStreamIndexes$delegate;
    final /* synthetic */ State<PlayerUiState> $uiState$delegate;
    final /* synthetic */ MutableState<Boolean> $userSelectedSourceManually$delegate;
    final /* synthetic */ PlayerViewModel $viewModel;
    int I$0;
    int I$1;
    int I$2;
    int I$3;
    long J$0;
    long J$1;
    long J$2;
    private /* synthetic */ Object L$0;
    Object L$1;
    Object L$2;
    Object L$3;
    int label;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public PlayerScreenKt$PlayerScreen$38$1(AtomicBoolean atomicBoolean, boolean z, PlayerViewModel playerViewModel, ExoPlayer exoPlayer, MediaType mediaType, Integer num, Integer num2, long j10, boolean z5, long j11, boolean z10, DolbyVisionStripExtractorsFactory dolbyVisionStripExtractorsFactory, Set<String> set, int i10, NextEpisodePromptGate nextEpisodePromptGate, Integer num3, Integer num4, MutableLongState mutableLongState, MutableLongState mutableLongState2, MutableFloatState mutableFloatState, MutableState<Boolean> mutableState, MutableState<Boolean> mutableState2, State<PlayerUiState> state, MutableState<Long> mutableState3, MutableState<Boolean> mutableState4, MutableState<Long> mutableState5, MutableIntState mutableIntState, MutableState<Boolean> mutableState6, State<PlayerUiState> state2, MutableIntState mutableIntState2, MutableState<Set<Integer>> mutableState7, MutableIntState mutableIntState3, MutableState<String> mutableState8, MutableLongState mutableLongState3, MutableState<Boolean> mutableState9, MutableState<Boolean> mutableState10, MutableState<Boolean> mutableState11, MutableIntState mutableIntState4, MutableState<Boolean> mutableState12, MutableState<String> mutableState13, MutableState<Boolean> mutableState14, MutableState<String> mutableState15, MutableState<Boolean> mutableState16, MutableIntState mutableIntState5, MutableState<Boolean> mutableState17, MutableState<Boolean> mutableState18, MutableState<Long> mutableState19, MutableState<Boolean> mutableState20, MutableState<Long> mutableState21, MutableIntState mutableIntState6, boolean z11, boolean z12, MutableIntState mutableIntState7, MutableLongState mutableLongState4, MutableState<Boolean> mutableState22, MutableState<Boolean> mutableState23, MutableState<Boolean> mutableState24, MutableState<EpisodeIdentity> mutableState25, MutableState<EpisodeIdentity> mutableState26, MutableState<String> mutableState27, MutableState<String> mutableState28, MutableState<String> mutableState29, MutableIntState mutableIntState8, d7.d<? super PlayerScreenKt$PlayerScreen$38$1> dVar) {
        super(2, dVar);
        this.$playerReleasedAtomic = atomicBoolean;
        this.$isCasting = z;
        this.$viewModel = playerViewModel;
        this.$exoPlayer = exoPlayer;
        this.$mediaType = mediaType;
        this.$seasonNumber = num;
        this.$episodeNumber = num2;
        this.$bufferingTimeoutMs = j10;
        this.$allowMidPlaybackSourceFallback = z5;
        this.$initialBufferingTimeoutMs = j11;
        this.$allowStartupSourceFallback = z10;
        this.$dvStripExtractorsFactory = dolbyVisionStripExtractorsFactory;
        this.$dvForcedStripUrls = set;
        this.$mediaId = i10;
        this.$nextEpisodePromptGate = nextEpisodePromptGate;
        this.$tmdbSeasonNumber = num3;
        this.$tmdbEpisodeNumber = num4;
        this.$currentPosition$delegate = mutableLongState;
        this.$duration$delegate = mutableLongState2;
        this.$progress$delegate = mutableFloatState;
        this.$isPlaying$delegate = mutableState;
        this.$isBuffering$delegate = mutableState2;
        this.$latestUiState$delegate = state;
        this.$readyPlayingSinceMs$delegate = mutableState3;
        this.$hasPlaybackStarted$delegate = mutableState4;
        this.$bufferingStartTime$delegate = mutableState5;
        this.$longRebufferCount$delegate = mutableIntState;
        this.$userSelectedSourceManually$delegate = mutableState6;
        this.$uiState$delegate = state2;
        this.$currentStreamIndex$delegate = mutableIntState2;
        this.$triedStreamIndexes$delegate = mutableState7;
        this.$autoAdvanceAttempts$delegate = mutableIntState3;
        this.$switchNotice$delegate = mutableState8;
        this.$switchNoticeUntilMs$delegate = mutableLongState3;
        this.$playbackIssueReported$delegate = mutableState9;
        this.$startupRecoverAttempted$delegate = mutableState10;
        this.$startupHardFailureReported$delegate = mutableState11;
        this.$startupSameSourceRetryCount$delegate = mutableIntState4;
        this.$startupSameSourceRefreshAttempted$delegate = mutableState12;
        this.$startupUrlLock$delegate = mutableState13;
        this.$pendingStartupFailover$delegate = mutableState14;
        this.$pendingStartupFailoverMessage$delegate = mutableState15;
        this.$pendingStartupFailureRecorded$delegate = mutableState16;
        this.$dvStartupFallbackStage$delegate = mutableIntState5;
        this.$rebufferRecoverAttempted$delegate = mutableState17;
        this.$isAutoAdvancing$delegate = mutableState18;
        this.$streamSelectedTime$delegate = mutableState19;
        this.$firstVideoFrameRendered$delegate = mutableState20;
        this.$blackVideoReadySinceMs$delegate = mutableState21;
        this.$blackVideoRecoveryStage$delegate = mutableIntState6;
        this.$allowRendererExceedCodecCapabilities = z11;
        this.$allowVideoExceedCodecCapabilities = z12;
        this.$midPlaybackRecoveryAttempts$delegate = mutableIntState7;
        this.$lastProgressReportSecond$delegate = mutableLongState4;
        this.$showNextEpisodePrompt$delegate = mutableState22;
        this.$showSourceMenu$delegate = mutableState23;
        this.$showSubtitleMenu$delegate = mutableState24;
        this.$nextEpisodeIdentity$delegate = mutableState25;
        this.$pendingNextIdentity$delegate = mutableState26;
        this.$pendingNextAddonId$delegate = mutableState27;
        this.$pendingNextSourceName$delegate = mutableState28;
        this.$pendingNextBingeGroup$delegate = mutableState29;
        this.$nextEpisodePromptButton$delegate = mutableIntState8;
    }

    @Override // f7.a
    public final d7.d<x6.t0> create(Object obj, d7.d<?> dVar) {
        PlayerScreenKt$PlayerScreen$38$1 playerScreenKt$PlayerScreen$38$1 = new PlayerScreenKt$PlayerScreen$38$1(this.$playerReleasedAtomic, this.$isCasting, this.$viewModel, this.$exoPlayer, this.$mediaType, this.$seasonNumber, this.$episodeNumber, this.$bufferingTimeoutMs, this.$allowMidPlaybackSourceFallback, this.$initialBufferingTimeoutMs, this.$allowStartupSourceFallback, this.$dvStripExtractorsFactory, this.$dvForcedStripUrls, this.$mediaId, this.$nextEpisodePromptGate, this.$tmdbSeasonNumber, this.$tmdbEpisodeNumber, this.$currentPosition$delegate, this.$duration$delegate, this.$progress$delegate, this.$isPlaying$delegate, this.$isBuffering$delegate, this.$latestUiState$delegate, this.$readyPlayingSinceMs$delegate, this.$hasPlaybackStarted$delegate, this.$bufferingStartTime$delegate, this.$longRebufferCount$delegate, this.$userSelectedSourceManually$delegate, this.$uiState$delegate, this.$currentStreamIndex$delegate, this.$triedStreamIndexes$delegate, this.$autoAdvanceAttempts$delegate, this.$switchNotice$delegate, this.$switchNoticeUntilMs$delegate, this.$playbackIssueReported$delegate, this.$startupRecoverAttempted$delegate, this.$startupHardFailureReported$delegate, this.$startupSameSourceRetryCount$delegate, this.$startupSameSourceRefreshAttempted$delegate, this.$startupUrlLock$delegate, this.$pendingStartupFailover$delegate, this.$pendingStartupFailoverMessage$delegate, this.$pendingStartupFailureRecorded$delegate, this.$dvStartupFallbackStage$delegate, this.$rebufferRecoverAttempted$delegate, this.$isAutoAdvancing$delegate, this.$streamSelectedTime$delegate, this.$firstVideoFrameRendered$delegate, this.$blackVideoReadySinceMs$delegate, this.$blackVideoRecoveryStage$delegate, this.$allowRendererExceedCodecCapabilities, this.$allowVideoExceedCodecCapabilities, this.$midPlaybackRecoveryAttempts$delegate, this.$lastProgressReportSecond$delegate, this.$showNextEpisodePrompt$delegate, this.$showSourceMenu$delegate, this.$showSubtitleMenu$delegate, this.$nextEpisodeIdentity$delegate, this.$pendingNextIdentity$delegate, this.$pendingNextAddonId$delegate, this.$pendingNextSourceName$delegate, this.$pendingNextBingeGroup$delegate, this.$nextEpisodePromptButton$delegate, dVar);
        playerScreenKt$PlayerScreen$38$1.L$0 = obj;
        return playerScreenKt$PlayerScreen$38$1;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Path cross not found for [B:57:0x0146, B:54:0x013a], limit reached: 382 */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:357:0x0936 -> B:359:0x0939). Please report as a decompilation issue!!! */
    /*  JADX ERROR: JadxRuntimeException in pass: RegionMakerVisitor
        jadx.core.utils.exceptions.JadxRuntimeException: Not found exit edge by exit block: B:11:0x0034
        	at jadx.core.dex.visitors.regions.maker.LoopRegionMaker.checkLoopExits(LoopRegionMaker.java:226)
        	at jadx.core.dex.visitors.regions.maker.LoopRegionMaker.makeLoopRegion(LoopRegionMaker.java:196)
        	at jadx.core.dex.visitors.regions.maker.LoopRegionMaker.process(LoopRegionMaker.java:63)
        	at jadx.core.dex.visitors.regions.maker.RegionMaker.traverse(RegionMaker.java:89)
        	at jadx.core.dex.visitors.regions.maker.RegionMaker.makeRegion(RegionMaker.java:66)
        	at jadx.core.dex.visitors.regions.maker.IfRegionMaker.process(IfRegionMaker.java:96)
        	at jadx.core.dex.visitors.regions.maker.RegionMaker.traverse(RegionMaker.java:106)
        	at jadx.core.dex.visitors.regions.maker.RegionMaker.makeRegion(RegionMaker.java:66)
        	at jadx.core.dex.visitors.regions.maker.IfRegionMaker.process(IfRegionMaker.java:102)
        	at jadx.core.dex.visitors.regions.maker.RegionMaker.traverse(RegionMaker.java:106)
        	at jadx.core.dex.visitors.regions.maker.RegionMaker.makeRegion(RegionMaker.java:66)
        	at jadx.core.dex.visitors.regions.maker.IfRegionMaker.process(IfRegionMaker.java:102)
        	at jadx.core.dex.visitors.regions.maker.RegionMaker.traverse(RegionMaker.java:106)
        	at jadx.core.dex.visitors.regions.maker.RegionMaker.makeRegion(RegionMaker.java:66)
        	at jadx.core.dex.visitors.regions.maker.LoopRegionMaker.process(LoopRegionMaker.java:125)
        	at jadx.core.dex.visitors.regions.maker.RegionMaker.traverse(RegionMaker.java:89)
        	at jadx.core.dex.visitors.regions.maker.RegionMaker.makeRegion(RegionMaker.java:66)
        	at jadx.core.dex.visitors.regions.maker.IfRegionMaker.process(IfRegionMaker.java:96)
        	at jadx.core.dex.visitors.regions.maker.RegionMaker.traverse(RegionMaker.java:106)
        	at jadx.core.dex.visitors.regions.maker.RegionMaker.makeRegion(RegionMaker.java:66)
        	at jadx.core.dex.visitors.regions.maker.RegionMaker.makeMthRegion(RegionMaker.java:48)
        	at jadx.core.dex.visitors.regions.RegionMakerVisitor.visit(RegionMakerVisitor.java:25)
        */
    @Override // f7.a
    public final java.lang.Object invokeSuspend(java.lang.Object r75) {
        /*
            Method dump skipped, instruction units count: 2367
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.arflix.tv.ui.screens.player.PlayerScreenKt$PlayerScreen$38$1.invokeSuspend(java.lang.Object):java.lang.Object");
    }

    @Override // r7.p
    public final Object invoke(ka.k0 k0Var, d7.d<? super x6.t0> dVar) {
        return ((PlayerScreenKt$PlayerScreen$38$1) create(k0Var, dVar)).invokeSuspend(x6.t0.f22605a);
    }
}
