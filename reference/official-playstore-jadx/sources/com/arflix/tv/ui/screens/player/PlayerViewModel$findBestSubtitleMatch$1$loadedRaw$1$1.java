package com.arflix.tv.ui.screens.player;

import com.arflix.tv.data.model.Subtitle;
import kotlin.Metadata;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\u0010\u0004\u001a\u0010\u0012\u0004\u0012\u00020\u0002\u0012\u0006\u0012\u0004\u0018\u00010\u00030\u0001*\u00020\u0000H\n¢\u0006\u0004\b\u0004\u0010\u0005"}, d2 = {"Lka/k0;", "Lx6/x;", "Lcom/arflix/tv/data/model/Subtitle;", "", "<anonymous>", "(Lka/k0;)Lx6/x;"}, k = 3, mv = {2, 3, 0})
@f7.e(c = "com.arflix.tv.ui.screens.player.PlayerViewModel$findBestSubtitleMatch$1$loadedRaw$1$1", f = "PlayerViewModel.kt", l = {3034}, m = "invokeSuspend", v = 2)
public final class PlayerViewModel$findBestSubtitleMatch$1$loadedRaw$1$1 extends f7.j implements r7.p<ka.k0, d7.d<? super x6.x>, Object> {
    final /* synthetic */ Subtitle $sub;
    Object L$0;
    int label;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public PlayerViewModel$findBestSubtitleMatch$1$loadedRaw$1$1(Subtitle subtitle, d7.d<? super PlayerViewModel$findBestSubtitleMatch$1$loadedRaw$1$1> dVar) {
        super(2, dVar);
        this.$sub = subtitle;
    }

    @Override // f7.a
    public final d7.d<x6.t0> create(Object obj, d7.d<?> dVar) {
        return new PlayerViewModel$findBestSubtitleMatch$1$loadedRaw$1$1(this.$sub, dVar);
    }

    @Override // f7.a
    public final Object invokeSuspend(Object obj) {
        Subtitle subtitle;
        int i10 = this.label;
        if (i10 == 0) {
            k2.c.G(obj);
            subtitle = this.$sub;
            SubtitleSyncMatcher subtitleSyncMatcher = SubtitleSyncMatcher.INSTANCE;
            String url = subtitle.getUrl();
            this.L$0 = subtitle;
            this.label = 1;
            obj = subtitleSyncMatcher.loadRaw(url, this);
            e7.a aVar = e7.a.f15033i;
            if (obj == aVar) {
                return aVar;
            }
        } else {
            if (i10 != 1) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            subtitle = (Subtitle) this.L$0;
            k2.c.G(obj);
        }
        return new x6.x(subtitle, obj);
    }

    @Override // r7.p
    public final Object invoke(ka.k0 k0Var, d7.d<? super x6.x> dVar) {
        return ((PlayerViewModel$findBestSubtitleMatch$1$loadedRaw$1$1) create(k0Var, dVar)).invokeSuspend(x6.t0.f22605a);
    }
}
