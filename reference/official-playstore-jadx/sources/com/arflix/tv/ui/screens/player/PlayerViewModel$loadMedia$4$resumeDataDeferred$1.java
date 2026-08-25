package com.arflix.tv.ui.screens.player;

import com.arflix.tv.data.model.MediaType;
import com.arflix.tv.ui.screens.player.PlayerViewModel;
import kotlin.Metadata;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0002\u001a\u00020\u0001*\u00020\u0000H\n¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"Lka/k0;", "Lcom/arflix/tv/ui/screens/player/PlayerViewModel$ResumeData;", "<anonymous>", "(Lka/k0;)Lcom/arflix/tv/ui/screens/player/PlayerViewModel$ResumeData;"}, k = 3, mv = {2, 3, 0})
@f7.e(c = "com.arflix.tv.ui.screens.player.PlayerViewModel$loadMedia$4$resumeDataDeferred$1", f = "PlayerViewModel.kt", l = {856}, m = "invokeSuspend", v = 2)
public final class PlayerViewModel$loadMedia$4$resumeDataDeferred$1 extends f7.j implements r7.p<ka.k0, d7.d<? super PlayerViewModel.ResumeData>, Object> {
    final /* synthetic */ Integer $episodeNumber;
    final /* synthetic */ int $mediaId;
    final /* synthetic */ MediaType $mediaType;
    final /* synthetic */ Integer $seasonNumber;
    final /* synthetic */ Long $startPositionMs;
    int label;
    final /* synthetic */ PlayerViewModel this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public PlayerViewModel$loadMedia$4$resumeDataDeferred$1(PlayerViewModel playerViewModel, MediaType mediaType, int i10, Integer num, Integer num2, Long l10, d7.d<? super PlayerViewModel$loadMedia$4$resumeDataDeferred$1> dVar) {
        super(2, dVar);
        this.this$0 = playerViewModel;
        this.$mediaType = mediaType;
        this.$mediaId = i10;
        this.$seasonNumber = num;
        this.$episodeNumber = num2;
        this.$startPositionMs = l10;
    }

    @Override // f7.a
    public final d7.d<x6.t0> create(Object obj, d7.d<?> dVar) {
        return new PlayerViewModel$loadMedia$4$resumeDataDeferred$1(this.this$0, this.$mediaType, this.$mediaId, this.$seasonNumber, this.$episodeNumber, this.$startPositionMs, dVar);
    }

    @Override // f7.a
    public final Object invokeSuspend(Object obj) {
        int i10 = this.label;
        if (i10 != 0) {
            if (i10 != 1) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            k2.c.G(obj);
            return obj;
        }
        k2.c.G(obj);
        PlayerViewModel playerViewModel = this.this$0;
        MediaType mediaType = this.$mediaType;
        int i11 = this.$mediaId;
        Integer num = this.$seasonNumber;
        Integer num2 = this.$episodeNumber;
        Long l10 = this.$startPositionMs;
        this.label = 1;
        Object objResolveResumeData = playerViewModel.resolveResumeData(mediaType, i11, num, num2, l10, this);
        e7.a aVar = e7.a.f15033i;
        return objResolveResumeData == aVar ? aVar : objResolveResumeData;
    }

    @Override // r7.p
    public final Object invoke(ka.k0 k0Var, d7.d<? super PlayerViewModel.ResumeData> dVar) {
        return ((PlayerViewModel$loadMedia$4$resumeDataDeferred$1) create(k0Var, dVar)).invokeSuspend(x6.t0.f22605a);
    }
}
