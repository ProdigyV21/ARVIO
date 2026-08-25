package com.arflix.tv.ui.screens.player;

import androidx.compose.runtime.MutableIntState;
import androidx.compose.runtime.MutableState;
import com.arflix.tv.data.model.MediaType;
import java.util.Set;
import kotlin.Metadata;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0002\u001a\u00020\u0001*\u00020\u0000H\n¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"Lka/k0;", "Lx6/t0;", "<anonymous>", "(Lka/k0;)V"}, k = 3, mv = {2, 3, 0})
@f7.e(c = "com.arflix.tv.ui.screens.player.PlayerScreenKt$PlayerScreen$11$1", f = "PlayerScreen.kt", l = {}, m = "invokeSuspend", v = 2)
public final class PlayerScreenKt$PlayerScreen$11$1 extends f7.j implements r7.p<ka.k0, d7.d<? super x6.t0>, Object> {
    final /* synthetic */ MutableIntState $autoAdvanceAttempts$delegate;
    final /* synthetic */ MutableState<Long> $blackVideoReadySinceMs$delegate;
    final /* synthetic */ MutableIntState $blackVideoRecoveryStage$delegate;
    final /* synthetic */ MutableIntState $dvStartupFallbackStage$delegate;
    final /* synthetic */ Integer $episodeNumber;
    final /* synthetic */ MutableState<Boolean> $firstVideoFrameRendered$delegate;
    final /* synthetic */ String $imdbId;
    final /* synthetic */ MutableState<Boolean> $isAutoAdvancing$delegate;
    final /* synthetic */ boolean $isLiveStream;
    final /* synthetic */ Integer $kitsuEpisodeNumber;
    final /* synthetic */ Integer $kitsuId;
    final /* synthetic */ MutableIntState $longRebufferCount$delegate;
    final /* synthetic */ int $mediaId;
    final /* synthetic */ MediaType $mediaType;
    final /* synthetic */ MutableState<Boolean> $pendingStartupFailover$delegate;
    final /* synthetic */ MutableState<String> $pendingStartupFailoverMessage$delegate;
    final /* synthetic */ MutableState<Boolean> $pendingStartupFailureRecorded$delegate;
    final /* synthetic */ MutableState<Boolean> $playbackIssueReported$delegate;
    final /* synthetic */ String $preferredAddonId;
    final /* synthetic */ String $preferredBingeGroup;
    final /* synthetic */ String $preferredSourceName;
    final /* synthetic */ MutableState<Long> $readyPlayingSinceMs$delegate;
    final /* synthetic */ MutableState<Boolean> $rebufferRecoverAttempted$delegate;
    final /* synthetic */ Integer $seasonNumber;
    final /* synthetic */ Long $startPositionMs;
    final /* synthetic */ MutableState<Boolean> $startupHardFailureReported$delegate;
    final /* synthetic */ MutableState<Boolean> $startupRecoverAttempted$delegate;
    final /* synthetic */ MutableState<Boolean> $startupSameSourceRefreshAttempted$delegate;
    final /* synthetic */ MutableIntState $startupSameSourceRetryCount$delegate;
    final /* synthetic */ MutableState<String> $startupUrlLock$delegate;
    final /* synthetic */ String $streamUrl;
    final /* synthetic */ Integer $tmdbEpisodeNumber;
    final /* synthetic */ Integer $tmdbSeasonNumber;
    final /* synthetic */ MutableState<Set<Integer>> $triedStreamIndexes$delegate;
    final /* synthetic */ MutableState<Boolean> $userSelectedSourceManually$delegate;
    final /* synthetic */ PlayerViewModel $viewModel;
    int label;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public PlayerScreenKt$PlayerScreen$11$1(PlayerViewModel playerViewModel, MediaType mediaType, int i10, Integer num, Integer num2, Integer num3, Integer num4, Integer num5, String str, String str2, String str3, String str4, String str5, Long l10, boolean z, MutableState<Boolean> mutableState, MutableState<Boolean> mutableState2, MutableState<Boolean> mutableState3, MutableIntState mutableIntState, MutableState<Boolean> mutableState4, MutableState<String> mutableState5, MutableState<Boolean> mutableState6, MutableState<String> mutableState7, MutableState<Boolean> mutableState8, MutableIntState mutableIntState2, MutableIntState mutableIntState3, MutableState<Long> mutableState9, MutableState<Boolean> mutableState10, MutableState<Boolean> mutableState11, MutableIntState mutableIntState4, MutableIntState mutableIntState5, MutableState<Set<Integer>> mutableState12, MutableState<Boolean> mutableState13, MutableState<Boolean> mutableState14, MutableState<Long> mutableState15, Integer num6, d7.d<? super PlayerScreenKt$PlayerScreen$11$1> dVar) {
        super(2, dVar);
        this.$viewModel = playerViewModel;
        this.$mediaType = mediaType;
        this.$mediaId = i10;
        this.$tmdbSeasonNumber = num;
        this.$tmdbEpisodeNumber = num2;
        this.$seasonNumber = num3;
        this.$episodeNumber = num4;
        this.$kitsuId = num5;
        this.$imdbId = str;
        this.$streamUrl = str2;
        this.$preferredAddonId = str3;
        this.$preferredSourceName = str4;
        this.$preferredBingeGroup = str5;
        this.$startPositionMs = l10;
        this.$isLiveStream = z;
        this.$playbackIssueReported$delegate = mutableState;
        this.$startupRecoverAttempted$delegate = mutableState2;
        this.$startupHardFailureReported$delegate = mutableState3;
        this.$startupSameSourceRetryCount$delegate = mutableIntState;
        this.$startupSameSourceRefreshAttempted$delegate = mutableState4;
        this.$startupUrlLock$delegate = mutableState5;
        this.$pendingStartupFailover$delegate = mutableState6;
        this.$pendingStartupFailoverMessage$delegate = mutableState7;
        this.$pendingStartupFailureRecorded$delegate = mutableState8;
        this.$dvStartupFallbackStage$delegate = mutableIntState2;
        this.$blackVideoRecoveryStage$delegate = mutableIntState3;
        this.$blackVideoReadySinceMs$delegate = mutableState9;
        this.$firstVideoFrameRendered$delegate = mutableState10;
        this.$rebufferRecoverAttempted$delegate = mutableState11;
        this.$longRebufferCount$delegate = mutableIntState4;
        this.$autoAdvanceAttempts$delegate = mutableIntState5;
        this.$triedStreamIndexes$delegate = mutableState12;
        this.$isAutoAdvancing$delegate = mutableState13;
        this.$userSelectedSourceManually$delegate = mutableState14;
        this.$readyPlayingSinceMs$delegate = mutableState15;
        this.$kitsuEpisodeNumber = num6;
    }

    @Override // f7.a
    public final d7.d<x6.t0> create(Object obj, d7.d<?> dVar) {
        return new PlayerScreenKt$PlayerScreen$11$1(this.$viewModel, this.$mediaType, this.$mediaId, this.$tmdbSeasonNumber, this.$tmdbEpisodeNumber, this.$seasonNumber, this.$episodeNumber, this.$kitsuId, this.$imdbId, this.$streamUrl, this.$preferredAddonId, this.$preferredSourceName, this.$preferredBingeGroup, this.$startPositionMs, this.$isLiveStream, this.$playbackIssueReported$delegate, this.$startupRecoverAttempted$delegate, this.$startupHardFailureReported$delegate, this.$startupSameSourceRetryCount$delegate, this.$startupSameSourceRefreshAttempted$delegate, this.$startupUrlLock$delegate, this.$pendingStartupFailover$delegate, this.$pendingStartupFailoverMessage$delegate, this.$pendingStartupFailureRecorded$delegate, this.$dvStartupFallbackStage$delegate, this.$blackVideoRecoveryStage$delegate, this.$blackVideoReadySinceMs$delegate, this.$firstVideoFrameRendered$delegate, this.$rebufferRecoverAttempted$delegate, this.$longRebufferCount$delegate, this.$autoAdvanceAttempts$delegate, this.$triedStreamIndexes$delegate, this.$isAutoAdvancing$delegate, this.$userSelectedSourceManually$delegate, this.$readyPlayingSinceMs$delegate, this.$kitsuEpisodeNumber, dVar);
    }

    @Override // f7.a
    public final Object invokeSuspend(Object obj) {
        if (this.label != 0) {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        k2.c.G(obj);
        PlayerScreenKt.PlayerScreen$lambda$214(this.$playbackIssueReported$delegate, false);
        PlayerScreenKt.PlayerScreen$lambda$217(this.$startupRecoverAttempted$delegate, false);
        PlayerScreenKt.PlayerScreen$lambda$220(this.$startupHardFailureReported$delegate, false);
        this.$startupSameSourceRetryCount$delegate.setIntValue(0);
        PlayerScreenKt.PlayerScreen$lambda$226(this.$startupSameSourceRefreshAttempted$delegate, false);
        String strN = null;
        this.$startupUrlLock$delegate.setValue(null);
        PlayerScreenKt.PlayerScreen$lambda$232(this.$pendingStartupFailover$delegate, false);
        this.$pendingStartupFailoverMessage$delegate.setValue(null);
        PlayerScreenKt.PlayerScreen$lambda$238(this.$pendingStartupFailureRecorded$delegate, false);
        this.$dvStartupFallbackStage$delegate.setIntValue(0);
        this.$blackVideoRecoveryStage$delegate.setIntValue(0);
        this.$blackVideoReadySinceMs$delegate.setValue(null);
        PlayerScreenKt.PlayerScreen$lambda$30(this.$firstVideoFrameRendered$delegate, false);
        PlayerScreenKt.PlayerScreen$lambda$256(this.$rebufferRecoverAttempted$delegate, false);
        this.$longRebufferCount$delegate.setIntValue(0);
        this.$autoAdvanceAttempts$delegate.setIntValue(0);
        this.$triedStreamIndexes$delegate.setValue(kotlin.collections.b0.f19686i);
        PlayerScreenKt.PlayerScreen$lambda$268(this.$isAutoAdvancing$delegate, false);
        PlayerScreenKt.PlayerScreen$lambda$197(this.$userSelectedSourceManually$delegate, false);
        this.$readyPlayingSinceMs$delegate.setValue(null);
        PlayerViewModel playerViewModel = this.$viewModel;
        MediaType mediaType = this.$mediaType;
        int i10 = this.$mediaId;
        Integer num = this.$tmdbSeasonNumber;
        Integer num2 = this.$tmdbEpisodeNumber;
        Integer num3 = this.$seasonNumber;
        Integer num4 = this.$episodeNumber;
        Integer num5 = this.$kitsuId;
        if (num5 != null) {
            Integer num6 = this.$kitsuEpisodeNumber;
            int iIntValue = num5.intValue();
            if (num6 != null) {
                strN = androidx.compose.foundation.c.n(iIntValue, num6.intValue(), "kitsu:", ":");
            }
        }
        PlayerViewModel.loadMedia$default(playerViewModel, mediaType, i10, num, num2, num3, num4, strN, this.$imdbId, this.$streamUrl, this.$preferredAddonId, this.$preferredSourceName, this.$preferredBingeGroup, this.$startPositionMs, this.$isLiveStream, null, 16384, null);
        return x6.t0.f22605a;
    }

    @Override // r7.p
    public final Object invoke(ka.k0 k0Var, d7.d<? super x6.t0> dVar) {
        return ((PlayerScreenKt$PlayerScreen$11$1) create(k0Var, dVar)).invokeSuspend(x6.t0.f22605a);
    }
}
