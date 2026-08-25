package com.arflix.tv.ui.screens.player;

import androidx.compose.runtime.MutableState;
import com.arflix.tv.data.model.EpisodeIdentity;
import com.arflix.tv.data.model.MediaType;
import kotlin.Metadata;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0002\u001a\u00020\u0001*\u00020\u0000H\n¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"Lka/k0;", "Lx6/t0;", "<anonymous>", "(Lka/k0;)V"}, k = 3, mv = {2, 3, 0})
@f7.e(c = "com.arflix.tv.ui.screens.player.PlayerScreenKt$PlayerScreen$7$1", f = "PlayerScreen.kt", l = {433, 434}, m = "invokeSuspend", v = 2)
public final class PlayerScreenKt$PlayerScreen$7$1 extends f7.j implements r7.p<ka.k0, d7.d<? super x6.t0>, Object> {
    final /* synthetic */ Integer $episodeNumber;
    final /* synthetic */ Integer $kitsuEpisodeNumber;
    final /* synthetic */ Integer $kitsuId;
    final /* synthetic */ int $mediaId;
    final /* synthetic */ MediaType $mediaType;
    final /* synthetic */ MutableState<EpisodeIdentity> $nextEpisodeIdentity$delegate;
    final /* synthetic */ MutableState<EpisodeIdentity> $previousEpisodeIdentity$delegate;
    final /* synthetic */ Integer $seasonNumber;
    final /* synthetic */ Integer $tmdbEpisodeNumber;
    final /* synthetic */ Integer $tmdbSeasonNumber;
    final /* synthetic */ PlayerViewModel $viewModel;
    Object L$0;
    Object L$1;
    int label;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public PlayerScreenKt$PlayerScreen$7$1(MediaType mediaType, Integer num, Integer num2, Integer num3, Integer num4, Integer num5, Integer num6, PlayerViewModel playerViewModel, int i10, MutableState<EpisodeIdentity> mutableState, MutableState<EpisodeIdentity> mutableState2, d7.d<? super PlayerScreenKt$PlayerScreen$7$1> dVar) {
        super(2, dVar);
        this.$mediaType = mediaType;
        this.$seasonNumber = num;
        this.$episodeNumber = num2;
        this.$tmdbSeasonNumber = num3;
        this.$tmdbEpisodeNumber = num4;
        this.$kitsuId = num5;
        this.$kitsuEpisodeNumber = num6;
        this.$viewModel = playerViewModel;
        this.$mediaId = i10;
        this.$nextEpisodeIdentity$delegate = mutableState;
        this.$previousEpisodeIdentity$delegate = mutableState2;
    }

    @Override // f7.a
    public final d7.d<x6.t0> create(Object obj, d7.d<?> dVar) {
        return new PlayerScreenKt$PlayerScreen$7$1(this.$mediaType, this.$seasonNumber, this.$episodeNumber, this.$tmdbSeasonNumber, this.$tmdbEpisodeNumber, this.$kitsuId, this.$kitsuEpisodeNumber, this.$viewModel, this.$mediaId, this.$nextEpisodeIdentity$delegate, this.$previousEpisodeIdentity$delegate, dVar);
    }

    /* JADX WARN: Code restructure failed: missing block: B:28:0x00a2, code lost:
    
        if (r2 == r5) goto L29;
     */
    @Override // f7.a
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object invokeSuspend(java.lang.Object r17) {
        /*
            r16 = this;
            r0 = r16
            int r1 = r0.label
            r2 = 2
            r3 = 0
            r4 = 1
            e7.a r5 = e7.a.f15033i
            if (r1 == 0) goto L35
            if (r1 == r4) goto L26
            if (r1 != r2) goto L1e
            java.lang.Object r1 = r0.L$1
            androidx.compose.runtime.MutableState r1 = (androidx.compose.runtime.MutableState) r1
            java.lang.Object r2 = r0.L$0
            com.arflix.tv.data.model.EpisodeIdentity r2 = (com.arflix.tv.data.model.EpisodeIdentity) r2
            k2.c.G(r17)
            r2 = r17
            goto La5
        L1e:
            java.lang.IllegalStateException r1 = new java.lang.IllegalStateException
            java.lang.String r2 = "call to 'resume' before 'invoke' with coroutine"
            r1.<init>(r2)
            throw r1
        L26:
            java.lang.Object r1 = r0.L$1
            androidx.compose.runtime.MutableState r1 = (androidx.compose.runtime.MutableState) r1
            java.lang.Object r4 = r0.L$0
            com.arflix.tv.data.model.EpisodeIdentity r4 = (com.arflix.tv.data.model.EpisodeIdentity) r4
            k2.c.G(r17)
            r6 = r4
            r4 = r17
            goto L8c
        L35:
            k2.c.G(r17)
            com.arflix.tv.data.model.MediaType r1 = r0.$mediaType
            com.arflix.tv.data.model.MediaType r6 = com.arflix.tv.data.model.MediaType.TV
            if (r1 != r6) goto Lab
            java.lang.Integer r1 = r0.$seasonNumber
            if (r1 == 0) goto Lab
            java.lang.Integer r1 = r0.$episodeNumber
            if (r1 == 0) goto Lab
            com.arflix.tv.data.model.EpisodeIdentity r6 = new com.arflix.tv.data.model.EpisodeIdentity
            java.lang.Integer r1 = r0.$seasonNumber
            int r7 = r1.intValue()
            java.lang.Integer r1 = r0.$episodeNumber
            int r8 = r1.intValue()
            java.lang.Integer r1 = r0.$tmdbSeasonNumber
            if (r1 == 0) goto L5e
        L58:
            int r1 = r1.intValue()
            r9 = r1
            goto L61
        L5e:
            java.lang.Integer r1 = r0.$seasonNumber
            goto L58
        L61:
            java.lang.Integer r1 = r0.$tmdbEpisodeNumber
            if (r1 == 0) goto L6b
        L65:
            int r1 = r1.intValue()
            r10 = r1
            goto L6e
        L6b:
            java.lang.Integer r1 = r0.$episodeNumber
            goto L65
        L6e:
            java.lang.Integer r11 = r0.$kitsuId
            java.lang.Integer r12 = r0.$kitsuEpisodeNumber
            r14 = 64
            r15 = 0
            r13 = 0
            r6.<init>(r7, r8, r9, r10, r11, r12, r13, r14, r15)
            androidx.compose.runtime.MutableState<com.arflix.tv.data.model.EpisodeIdentity> r1 = r0.$nextEpisodeIdentity$delegate
            com.arflix.tv.ui.screens.player.PlayerViewModel r7 = r0.$viewModel
            int r8 = r0.$mediaId
            r0.L$0 = r6
            r0.L$1 = r1
            r0.label = r4
            java.lang.Object r4 = r7.adjacentEpisodeIdentity(r8, r6, r4, r0)
            if (r4 != r5) goto L8c
            goto La4
        L8c:
            com.arflix.tv.data.model.EpisodeIdentity r4 = (com.arflix.tv.data.model.EpisodeIdentity) r4
            com.arflix.tv.ui.screens.player.PlayerScreenKt.access$PlayerScreen$lambda$131(r1, r4)
            androidx.compose.runtime.MutableState<com.arflix.tv.data.model.EpisodeIdentity> r1 = r0.$previousEpisodeIdentity$delegate
            com.arflix.tv.ui.screens.player.PlayerViewModel r4 = r0.$viewModel
            int r7 = r0.$mediaId
            r0.L$0 = r3
            r0.L$1 = r1
            r0.label = r2
            r2 = 0
            java.lang.Object r2 = r4.adjacentEpisodeIdentity(r7, r6, r2, r0)
            if (r2 != r5) goto La5
        La4:
            return r5
        La5:
            com.arflix.tv.data.model.EpisodeIdentity r2 = (com.arflix.tv.data.model.EpisodeIdentity) r2
            com.arflix.tv.ui.screens.player.PlayerScreenKt.access$PlayerScreen$lambda$134(r1, r2)
            goto Lb5
        Lab:
            androidx.compose.runtime.MutableState<com.arflix.tv.data.model.EpisodeIdentity> r1 = r0.$nextEpisodeIdentity$delegate
            com.arflix.tv.ui.screens.player.PlayerScreenKt.access$PlayerScreen$lambda$131(r1, r3)
            androidx.compose.runtime.MutableState<com.arflix.tv.data.model.EpisodeIdentity> r1 = r0.$previousEpisodeIdentity$delegate
            com.arflix.tv.ui.screens.player.PlayerScreenKt.access$PlayerScreen$lambda$134(r1, r3)
        Lb5:
            x6.t0 r1 = x6.t0.f22605a
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.arflix.tv.ui.screens.player.PlayerScreenKt$PlayerScreen$7$1.invokeSuspend(java.lang.Object):java.lang.Object");
    }

    @Override // r7.p
    public final Object invoke(ka.k0 k0Var, d7.d<? super x6.t0> dVar) {
        return ((PlayerScreenKt$PlayerScreen$7$1) create(k0Var, dVar)).invokeSuspend(x6.t0.f22605a);
    }
}
