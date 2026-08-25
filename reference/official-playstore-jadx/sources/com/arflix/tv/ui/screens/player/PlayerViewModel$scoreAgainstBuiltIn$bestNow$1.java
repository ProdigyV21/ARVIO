package com.arflix.tv.ui.screens.player;

import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;
import kotlin.Metadata;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0006\n\u0002\b\u0002\u0010\u0002\u001a\u00020\u0001*\u00020\u0000H\n¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"Lka/k0;", "", "<anonymous>", "(Lka/k0;)D"}, k = 3, mv = {2, 3, 0})
@f7.e(c = "com.arflix.tv.ui.screens.player.PlayerViewModel$scoreAgainstBuiltIn$bestNow$1", f = "PlayerViewModel.kt", l = {}, m = "invokeSuspend", v = 2)
public final class PlayerViewModel$scoreAgainstBuiltIn$bestNow$1 extends f7.j implements r7.p<ka.k0, d7.d<? super Double>, Object> {
    final /* synthetic */ List<x6.x> $loaded;
    final /* synthetic */ kotlin.jvm.internal.k0 $refs;
    int label;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    /* JADX WARN: Multi-variable type inference failed */
    public PlayerViewModel$scoreAgainstBuiltIn$bestNow$1(List<? extends x6.x> list, kotlin.jvm.internal.k0 k0Var, d7.d<? super PlayerViewModel$scoreAgainstBuiltIn$bestNow$1> dVar) {
        super(2, dVar);
        this.$loaded = list;
        this.$refs = k0Var;
    }

    @Override // f7.a
    public final d7.d<x6.t0> create(Object obj, d7.d<?> dVar) {
        return new PlayerViewModel$scoreAgainstBuiltIn$bestNow$1(this.$loaded, this.$refs, dVar);
    }

    @Override // f7.a
    public final Object invokeSuspend(Object obj) {
        if (this.label != 0) {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        k2.c.G(obj);
        List<x6.x> list = this.$loaded;
        kotlin.jvm.internal.k0 k0Var = this.$refs;
        Iterator<T> it = list.iterator();
        if (!it.hasNext()) {
            throw new NoSuchElementException();
        }
        double dScoreByTiming$default = SubtitleSyncMatcher.scoreByTiming$default(SubtitleSyncMatcher.INSTANCE, (List) ((x6.x) it.next()).f22609l, (List) k0Var.f19746i, 0L, 4, null);
        while (it.hasNext()) {
            dScoreByTiming$default = Math.max(dScoreByTiming$default, SubtitleSyncMatcher.scoreByTiming$default(SubtitleSyncMatcher.INSTANCE, (List) ((x6.x) it.next()).f22609l, (List) k0Var.f19746i, 0L, 4, null));
        }
        return new Double(dScoreByTiming$default);
    }

    @Override // r7.p
    public final Object invoke(ka.k0 k0Var, d7.d<? super Double> dVar) {
        return ((PlayerViewModel$scoreAgainstBuiltIn$bestNow$1) create(k0Var, dVar)).invokeSuspend(x6.t0.f22605a);
    }
}
