package com.arflix.tv.ui.screens.player;

import com.arflix.tv.ui.screens.player.PlayerViewModel;
import java.util.List;
import kotlin.Metadata;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000\u0010\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00020\u0001*\u00020\u0000H\n¢\u0006\u0004\b\u0003\u0010\u0004"}, d2 = {"Lka/k0;", "", "Lcom/arflix/tv/ui/screens/player/PlayerViewModel$ScoredCandidate;", "<anonymous>", "(Lka/k0;)Ljava/util/List;"}, k = 3, mv = {2, 3, 0})
@f7.e(c = "com.arflix.tv.ui.screens.player.PlayerViewModel$scoreAgainstBuiltIn$early$1", f = "PlayerViewModel.kt", l = {}, m = "invokeSuspend", v = 2)
public final class PlayerViewModel$scoreAgainstBuiltIn$early$1 extends f7.j implements r7.p<ka.k0, d7.d<? super List<? extends PlayerViewModel.ScoredCandidate>>, Object> {
    final /* synthetic */ List<x6.x> $loaded;
    final /* synthetic */ kotlin.jvm.internal.k0 $refs;
    int label;
    final /* synthetic */ PlayerViewModel this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    /* JADX WARN: Multi-variable type inference failed */
    public PlayerViewModel$scoreAgainstBuiltIn$early$1(PlayerViewModel playerViewModel, List<? extends x6.x> list, kotlin.jvm.internal.k0 k0Var, d7.d<? super PlayerViewModel$scoreAgainstBuiltIn$early$1> dVar) {
        super(2, dVar);
        this.this$0 = playerViewModel;
        this.$loaded = list;
        this.$refs = k0Var;
    }

    @Override // f7.a
    public final d7.d<x6.t0> create(Object obj, d7.d<?> dVar) {
        return new PlayerViewModel$scoreAgainstBuiltIn$early$1(this.this$0, this.$loaded, this.$refs, dVar);
    }

    @Override // f7.a
    public final Object invokeSuspend(Object obj) {
        if (this.label != 0) {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        k2.c.G(obj);
        return this.this$0.scoreCandidatesWithOffsets(this.$loaded, (List) this.$refs.f19746i, false);
    }

    @Override // r7.p
    public final Object invoke(ka.k0 k0Var, d7.d<? super List<PlayerViewModel.ScoredCandidate>> dVar) {
        return ((PlayerViewModel$scoreAgainstBuiltIn$early$1) create(k0Var, dVar)).invokeSuspend(x6.t0.f22605a);
    }
}
