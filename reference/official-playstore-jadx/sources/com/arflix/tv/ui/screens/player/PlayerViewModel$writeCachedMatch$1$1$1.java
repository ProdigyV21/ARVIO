package com.arflix.tv.ui.screens.player;

import com.arflix.tv.ui.screens.player.PlayerViewModel;
import java.util.List;
import kotlin.Metadata;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000\u000e\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0003\u001a\u00020\u00022\u0006\u0010\u0001\u001a\u00020\u0000H\n¢\u0006\u0004\b\u0003\u0010\u0004"}, d2 = {"Lc1/b;", "it", "Lx6/t0;", "<anonymous>", "(Lc1/b;)V"}, k = 3, mv = {2, 3, 0})
@f7.e(c = "com.arflix.tv.ui.screens.player.PlayerViewModel$writeCachedMatch$1$1$1", f = "PlayerViewModel.kt", l = {}, m = "invokeSuspend", v = 2)
public final class PlayerViewModel$writeCachedMatch$1$1$1 extends f7.j implements r7.p<c1.b, d7.d<? super x6.t0>, Object> {
    final /* synthetic */ List<PlayerViewModel.CachedSubMatch> $cache;
    /* synthetic */ Object L$0;
    int label;
    final /* synthetic */ PlayerViewModel this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public PlayerViewModel$writeCachedMatch$1$1$1(PlayerViewModel playerViewModel, List<PlayerViewModel.CachedSubMatch> list, d7.d<? super PlayerViewModel$writeCachedMatch$1$1$1> dVar) {
        super(2, dVar);
        this.this$0 = playerViewModel;
        this.$cache = list;
    }

    @Override // f7.a
    public final d7.d<x6.t0> create(Object obj, d7.d<?> dVar) {
        PlayerViewModel$writeCachedMatch$1$1$1 playerViewModel$writeCachedMatch$1$1$1 = new PlayerViewModel$writeCachedMatch$1$1$1(this.this$0, this.$cache, dVar);
        playerViewModel$writeCachedMatch$1$1$1.L$0 = obj;
        return playerViewModel$writeCachedMatch$1$1$1;
    }

    @Override // r7.p
    public final Object invoke(c1.b bVar, d7.d<? super x6.t0> dVar) {
        return ((PlayerViewModel$writeCachedMatch$1$1$1) create(bVar, dVar)).invokeSuspend(x6.t0.f22605a);
    }

    @Override // f7.a
    public final Object invokeSuspend(Object obj) {
        c1.b bVar = (c1.b) this.L$0;
        if (this.label != 0) {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        k2.c.G(obj);
        bVar.d(this.this$0.subtitleMatchCacheKey, this.this$0.gson.toJson(this.$cache));
        return x6.t0.f22605a;
    }
}
