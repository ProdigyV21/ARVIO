package com.arflix.tv.ui.screens.player;

import androidx.compose.runtime.MutableIntState;
import androidx.compose.runtime.MutableLongState;
import androidx.compose.runtime.MutableState;
import androidx.compose.runtime.State;
import java.util.Set;
import kotlin.Metadata;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0002\u001a\u00020\u0001*\u00020\u0000H\n¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"Lka/k0;", "Lx6/t0;", "<anonymous>", "(Lka/k0;)V"}, k = 3, mv = {2, 3, 0})
@f7.e(c = "com.arflix.tv.ui.screens.player.PlayerScreenKt$PlayerScreen$12$1", f = "PlayerScreen.kt", l = {}, m = "invokeSuspend", v = 2)
public final class PlayerScreenKt$PlayerScreen$12$1 extends f7.j implements r7.p<ka.k0, d7.d<? super x6.t0>, Object> {
    final /* synthetic */ MutableIntState $autoAdvanceAttempts$delegate;
    final /* synthetic */ MutableIntState $currentStreamIndex$delegate;
    final /* synthetic */ MutableIntState $dvStartupFallbackStage$delegate;
    final /* synthetic */ MutableState<Boolean> $hasPlaybackStarted$delegate;
    final /* synthetic */ MutableState<Boolean> $isAutoAdvancing$delegate;
    final /* synthetic */ MutableIntState $longRebufferCount$delegate;
    final /* synthetic */ MutableState<Boolean> $pendingStartupFailover$delegate;
    final /* synthetic */ MutableState<String> $pendingStartupFailoverMessage$delegate;
    final /* synthetic */ MutableState<Boolean> $pendingStartupFailureRecorded$delegate;
    final /* synthetic */ MutableState<Boolean> $playbackIssueReported$delegate;
    final /* synthetic */ MutableState<Boolean> $rebufferRecoverAttempted$delegate;
    final /* synthetic */ MutableState<Boolean> $startupHardFailureReported$delegate;
    final /* synthetic */ MutableState<Boolean> $startupRecoverAttempted$delegate;
    final /* synthetic */ MutableState<Boolean> $startupSameSourceRefreshAttempted$delegate;
    final /* synthetic */ MutableIntState $startupSameSourceRetryCount$delegate;
    final /* synthetic */ MutableState<String> $startupUrlLock$delegate;
    final /* synthetic */ MutableState<String> $switchNotice$delegate;
    final /* synthetic */ MutableLongState $switchNoticeUntilMs$delegate;
    final /* synthetic */ MutableState<Set<Integer>> $triedStreamIndexes$delegate;
    final /* synthetic */ State<PlayerUiState> $uiState$delegate;
    final /* synthetic */ MutableState<Boolean> $userSelectedSourceManually$delegate;
    final /* synthetic */ PlayerViewModel $viewModel;
    int label;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public PlayerScreenKt$PlayerScreen$12$1(PlayerViewModel playerViewModel, MutableState<Boolean> mutableState, MutableState<Boolean> mutableState2, MutableState<Boolean> mutableState3, MutableState<Boolean> mutableState4, State<PlayerUiState> state, MutableIntState mutableIntState, MutableState<Set<Integer>> mutableState5, MutableIntState mutableIntState2, MutableState<String> mutableState6, MutableLongState mutableLongState, MutableState<Boolean> mutableState7, MutableState<Boolean> mutableState8, MutableState<Boolean> mutableState9, MutableIntState mutableIntState3, MutableState<Boolean> mutableState10, MutableState<String> mutableState11, MutableState<String> mutableState12, MutableIntState mutableIntState4, MutableState<Boolean> mutableState13, MutableIntState mutableIntState5, MutableState<Boolean> mutableState14, d7.d<? super PlayerScreenKt$PlayerScreen$12$1> dVar) {
        super(2, dVar);
        this.$viewModel = playerViewModel;
        this.$pendingStartupFailover$delegate = mutableState;
        this.$hasPlaybackStarted$delegate = mutableState2;
        this.$userSelectedSourceManually$delegate = mutableState3;
        this.$pendingStartupFailureRecorded$delegate = mutableState4;
        this.$uiState$delegate = state;
        this.$currentStreamIndex$delegate = mutableIntState;
        this.$triedStreamIndexes$delegate = mutableState5;
        this.$autoAdvanceAttempts$delegate = mutableIntState2;
        this.$switchNotice$delegate = mutableState6;
        this.$switchNoticeUntilMs$delegate = mutableLongState;
        this.$playbackIssueReported$delegate = mutableState7;
        this.$startupRecoverAttempted$delegate = mutableState8;
        this.$startupHardFailureReported$delegate = mutableState9;
        this.$startupSameSourceRetryCount$delegate = mutableIntState3;
        this.$startupSameSourceRefreshAttempted$delegate = mutableState10;
        this.$startupUrlLock$delegate = mutableState11;
        this.$pendingStartupFailoverMessage$delegate = mutableState12;
        this.$dvStartupFallbackStage$delegate = mutableIntState4;
        this.$rebufferRecoverAttempted$delegate = mutableState13;
        this.$longRebufferCount$delegate = mutableIntState5;
        this.$isAutoAdvancing$delegate = mutableState14;
    }

    @Override // f7.a
    public final d7.d<x6.t0> create(Object obj, d7.d<?> dVar) {
        return new PlayerScreenKt$PlayerScreen$12$1(this.$viewModel, this.$pendingStartupFailover$delegate, this.$hasPlaybackStarted$delegate, this.$userSelectedSourceManually$delegate, this.$pendingStartupFailureRecorded$delegate, this.$uiState$delegate, this.$currentStreamIndex$delegate, this.$triedStreamIndexes$delegate, this.$autoAdvanceAttempts$delegate, this.$switchNotice$delegate, this.$switchNoticeUntilMs$delegate, this.$playbackIssueReported$delegate, this.$startupRecoverAttempted$delegate, this.$startupHardFailureReported$delegate, this.$startupSameSourceRetryCount$delegate, this.$startupSameSourceRefreshAttempted$delegate, this.$startupUrlLock$delegate, this.$pendingStartupFailoverMessage$delegate, this.$dvStartupFallbackStage$delegate, this.$rebufferRecoverAttempted$delegate, this.$longRebufferCount$delegate, this.$isAutoAdvancing$delegate, dVar);
    }

    @Override // f7.a
    public final Object invokeSuspend(Object obj) {
        String streamLoadPhase;
        if (this.label != 0) {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        k2.c.G(obj);
        boolean zPlayerScreen$lambda$231 = PlayerScreenKt.PlayerScreen$lambda$231(this.$pendingStartupFailover$delegate);
        x6.t0 t0Var = x6.t0.f22605a;
        if (zPlayerScreen$lambda$231 && !PlayerScreenKt.PlayerScreen$lambda$26(this.$hasPlaybackStarted$delegate) && !PlayerScreenKt.PlayerScreen$lambda$196(this.$userSelectedSourceManually$delegate)) {
            if (!PlayerScreenKt.PlayerScreen$tryAdvanceToNextStream(this.$viewModel, this.$uiState$delegate, this.$currentStreamIndex$delegate, this.$triedStreamIndexes$delegate, this.$autoAdvanceAttempts$delegate, this.$switchNotice$delegate, this.$switchNoticeUntilMs$delegate, this.$userSelectedSourceManually$delegate, this.$playbackIssueReported$delegate, this.$startupRecoverAttempted$delegate, this.$startupHardFailureReported$delegate, this.$startupSameSourceRetryCount$delegate, this.$startupSameSourceRefreshAttempted$delegate, this.$startupUrlLock$delegate, this.$pendingStartupFailover$delegate, this.$pendingStartupFailoverMessage$delegate, this.$pendingStartupFailureRecorded$delegate, this.$dvStartupFallbackStage$delegate, this.$rebufferRecoverAttempted$delegate, this.$longRebufferCount$delegate, this.$isAutoAdvancing$delegate, (4194304 & 2097152) != 0 ? null : null, (4194304 & 4194304) != 0 ? true : !PlayerScreenKt.PlayerScreen$lambda$237(r1), (4194304 & 8388608) != 0 ? "Source didn't start" : null) && !PlayerScreenKt.PlayerScreen$lambda$3(this.$uiState$delegate).getSourceSearchActive() && PlayerScreenKt.PlayerScreen$lambda$3(this.$uiState$delegate).getStreamProgress() == null && (((streamLoadPhase = PlayerScreenKt.PlayerScreen$lambda$3(this.$uiState$delegate).getStreamLoadPhase()) == null || kotlin.text.o.h0(streamLoadPhase)) && !PlayerScreenKt.PlayerScreen$lambda$213(this.$playbackIssueReported$delegate))) {
                PlayerScreenKt.PlayerScreen$lambda$214(this.$playbackIssueReported$delegate, true);
                PlayerScreenKt.PlayerScreen$lambda$232(this.$pendingStartupFailover$delegate, false);
                PlayerViewModel playerViewModel = this.$viewModel;
                String strPlayerScreen$lambda$234 = PlayerScreenKt.PlayerScreen$lambda$234(this.$pendingStartupFailoverMessage$delegate);
                if (strPlayerScreen$lambda$234 == null) {
                    strPlayerScreen$lambda$234 = "Source failed during startup. Try another source.";
                }
                playerViewModel.reportPlaybackError(strPlayerScreen$lambda$234);
            }
        }
        return t0Var;
    }

    @Override // r7.p
    public final Object invoke(ka.k0 k0Var, d7.d<? super x6.t0> dVar) {
        return ((PlayerScreenKt$PlayerScreen$12$1) create(k0Var, dVar)).invokeSuspend(x6.t0.f22605a);
    }
}
