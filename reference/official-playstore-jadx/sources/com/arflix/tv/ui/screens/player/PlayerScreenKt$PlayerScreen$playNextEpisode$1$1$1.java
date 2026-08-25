package com.arflix.tv.ui.screens.player;

import com.arflix.tv.data.model.EpisodeIdentity;
import kotlin.Metadata;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0002\u001a\u00020\u0001*\u00020\u0000H\n¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"Lka/k0;", "Lx6/t0;", "<anonymous>", "(Lka/k0;)V"}, k = 3, mv = {2, 3, 0})
@f7.e(c = "com.arflix.tv.ui.screens.player.PlayerScreenKt$PlayerScreen$playNextEpisode$1$1$1", f = "PlayerScreen.kt", l = {460}, m = "invokeSuspend", v = 2)
public final class PlayerScreenKt$PlayerScreen$playNextEpisode$1$1$1 extends f7.j implements r7.p<ka.k0, d7.d<? super x6.t0>, Object> {
    final /* synthetic */ long $durationSnapshot;
    final /* synthetic */ String $nextAddonId;
    final /* synthetic */ String $nextBingeGroup;
    final /* synthetic */ EpisodeIdentity $nextIdentity;
    final /* synthetic */ String $nextSourceName;
    final /* synthetic */ r7.r<EpisodeIdentity, String, String, String, x6.t0> $onPlayNext;
    final /* synthetic */ int $playbackStateSnapshot;
    final /* synthetic */ long $positionSnapshot;
    final /* synthetic */ int $progressPercentSnapshot;
    final /* synthetic */ PlayerViewModel $viewModel;
    int I$0;
    private /* synthetic */ Object L$0;
    Object L$1;
    int label;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    /* JADX WARN: Multi-variable type inference failed */
    public PlayerScreenKt$PlayerScreen$playNextEpisode$1$1$1(r7.r<? super EpisodeIdentity, ? super String, ? super String, ? super String, x6.t0> rVar, EpisodeIdentity episodeIdentity, String str, String str2, String str3, PlayerViewModel playerViewModel, long j10, long j11, int i10, int i11, d7.d<? super PlayerScreenKt$PlayerScreen$playNextEpisode$1$1$1> dVar) {
        super(2, dVar);
        this.$onPlayNext = rVar;
        this.$nextIdentity = episodeIdentity;
        this.$nextAddonId = str;
        this.$nextSourceName = str2;
        this.$nextBingeGroup = str3;
        this.$viewModel = playerViewModel;
        this.$positionSnapshot = j10;
        this.$durationSnapshot = j11;
        this.$progressPercentSnapshot = i10;
        this.$playbackStateSnapshot = i11;
    }

    @Override // f7.a
    public final d7.d<x6.t0> create(Object obj, d7.d<?> dVar) {
        PlayerScreenKt$PlayerScreen$playNextEpisode$1$1$1 playerScreenKt$PlayerScreen$playNextEpisode$1$1$1 = new PlayerScreenKt$PlayerScreen$playNextEpisode$1$1$1(this.$onPlayNext, this.$nextIdentity, this.$nextAddonId, this.$nextSourceName, this.$nextBingeGroup, this.$viewModel, this.$positionSnapshot, this.$durationSnapshot, this.$progressPercentSnapshot, this.$playbackStateSnapshot, dVar);
        playerScreenKt$PlayerScreen$playNextEpisode$1$1$1.L$0 = obj;
        return playerScreenKt$PlayerScreen$playNextEpisode$1$1$1;
    }

    @Override // f7.a
    public final Object invokeSuspend(Object obj) {
        PlayerScreenKt$PlayerScreen$playNextEpisode$1$1$1 playerScreenKt$PlayerScreen$playNextEpisode$1$1$1;
        int i10 = this.label;
        if (i10 != 0) {
            if (i10 != 1) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            k2.c.G(obj);
            playerScreenKt$PlayerScreen$playNextEpisode$1$1$1 = this;
            playerScreenKt$PlayerScreen$playNextEpisode$1$1$1.$onPlayNext.invoke(playerScreenKt$PlayerScreen$playNextEpisode$1$1$1.$nextIdentity, playerScreenKt$PlayerScreen$playNextEpisode$1$1$1.$nextAddonId, playerScreenKt$PlayerScreen$playNextEpisode$1$1$1.$nextSourceName, playerScreenKt$PlayerScreen$playNextEpisode$1$1$1.$nextBingeGroup);
            return x6.t0.f22605a;
        }
        k2.c.G(obj);
        PlayerViewModel playerViewModel = this.$viewModel;
        long j10 = this.$positionSnapshot;
        long j11 = this.$durationSnapshot;
        int i11 = this.$progressPercentSnapshot;
        int i12 = this.$playbackStateSnapshot;
        this.L$0 = null;
        this.L$1 = null;
        this.I$0 = 0;
        this.label = 1;
        playerScreenKt$PlayerScreen$playNextEpisode$1$1$1 = this;
        try {
            Object objSaveProgressAndWait = playerViewModel.saveProgressAndWait(j10, j11, i11, false, i12, playerScreenKt$PlayerScreen$playNextEpisode$1$1$1);
            e7.a aVar = e7.a.f15033i;
            if (objSaveProgressAndWait == aVar) {
                return aVar;
            }
        } catch (Throwable unused) {
        }
        playerScreenKt$PlayerScreen$playNextEpisode$1$1$1.$onPlayNext.invoke(playerScreenKt$PlayerScreen$playNextEpisode$1$1$1.$nextIdentity, playerScreenKt$PlayerScreen$playNextEpisode$1$1$1.$nextAddonId, playerScreenKt$PlayerScreen$playNextEpisode$1$1$1.$nextSourceName, playerScreenKt$PlayerScreen$playNextEpisode$1$1$1.$nextBingeGroup);
        return x6.t0.f22605a;
    }

    @Override // r7.p
    public final Object invoke(ka.k0 k0Var, d7.d<? super x6.t0> dVar) {
        return ((PlayerScreenKt$PlayerScreen$playNextEpisode$1$1$1) create(k0Var, dVar)).invokeSuspend(x6.t0.f22605a);
    }
}
