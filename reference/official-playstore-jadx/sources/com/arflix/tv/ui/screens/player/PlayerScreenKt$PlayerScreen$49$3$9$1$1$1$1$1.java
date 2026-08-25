package com.arflix.tv.ui.screens.player;

import androidx.compose.runtime.MutableLongState;
import androidx.compose.runtime.MutableState;
import kotlin.Metadata;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0002\u001a\u00020\u0001*\u00020\u0000H\n¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"Lka/k0;", "Lx6/t0;", "<anonymous>", "(Lka/k0;)V"}, k = 3, mv = {2, 3, 0})
@f7.e(c = "com.arflix.tv.ui.screens.player.PlayerScreenKt$PlayerScreen$49$3$9$1$1$1$1$1", f = "PlayerScreen.kt", l = {3444}, m = "invokeSuspend", v = 2)
public final class PlayerScreenKt$PlayerScreen$49$3$9$1$1$1$1$1 extends f7.j implements r7.p<ka.k0, d7.d<? super x6.t0>, Object> {
    final /* synthetic */ String $clockFormat;
    final /* synthetic */ MutableLongState $currentPosition$delegate;
    final /* synthetic */ MutableState<String> $currentTime;
    final /* synthetic */ MutableLongState $duration$delegate;
    final /* synthetic */ MutableState<String> $endsAtTime;
    long J$0;
    int label;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public PlayerScreenKt$PlayerScreen$49$3$9$1$1$1$1$1(MutableState<String> mutableState, String str, MutableState<String> mutableState2, MutableLongState mutableLongState, MutableLongState mutableLongState2, d7.d<? super PlayerScreenKt$PlayerScreen$49$3$9$1$1$1$1$1> dVar) {
        super(2, dVar);
        this.$currentTime = mutableState;
        this.$clockFormat = str;
        this.$endsAtTime = mutableState2;
        this.$duration$delegate = mutableLongState;
        this.$currentPosition$delegate = mutableLongState2;
    }

    @Override // f7.a
    public final d7.d<x6.t0> create(Object obj, d7.d<?> dVar) {
        return new PlayerScreenKt$PlayerScreen$49$3$9$1$1$1$1$1(this.$currentTime, this.$clockFormat, this.$endsAtTime, this.$duration$delegate, this.$currentPosition$delegate, dVar);
    }

    @Override // f7.a
    public final Object invokeSuspend(Object obj) {
        Object objA;
        e7.a aVar;
        int i10 = this.label;
        if (i10 != 0 && i10 != 1) {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        k2.c.G(obj);
        do {
            long jCurrentTimeMillis = System.currentTimeMillis();
            this.$currentTime.setValue(PlayerScreenKt.formatPlayerClockTime(jCurrentTimeMillis, this.$clockFormat));
            if (this.$duration$delegate.getLongValue() <= 0 || this.$currentPosition$delegate.getLongValue() < 0) {
                this.$endsAtTime.setValue("");
            } else {
                long longValue = this.$duration$delegate.getLongValue() - this.$currentPosition$delegate.getLongValue();
                this.$endsAtTime.setValue(PlayerScreenKt.formatPlayerClockTime((longValue >= 0 ? longValue : 0L) + jCurrentTimeMillis, this.$clockFormat));
            }
            this.J$0 = jCurrentTimeMillis;
            this.label = 1;
            objA = ka.s0.a(1000L, this);
            aVar = e7.a.f15033i;
        } while (objA != aVar);
        return aVar;
    }

    @Override // r7.p
    public final Object invoke(ka.k0 k0Var, d7.d<? super x6.t0> dVar) {
        return ((PlayerScreenKt$PlayerScreen$49$3$9$1$1$1$1$1) create(k0Var, dVar)).invokeSuspend(x6.t0.f22605a);
    }
}
