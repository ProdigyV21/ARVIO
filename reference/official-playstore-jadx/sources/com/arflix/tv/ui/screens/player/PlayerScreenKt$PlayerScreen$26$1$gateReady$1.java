package com.arflix.tv.ui.screens.player;

import androidx.compose.runtime.SnapshotStateKt;
import androidx.compose.runtime.State;
import kotlin.Metadata;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u000b\n\u0002\b\u0002\u0010\u0002\u001a\u00020\u0001*\u00020\u0000H\n¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"Lka/k0;", "", "<anonymous>", "(Lka/k0;)Z"}, k = 3, mv = {2, 3, 0})
@f7.e(c = "com.arflix.tv.ui.screens.player.PlayerScreenKt$PlayerScreen$26$1$gateReady$1", f = "PlayerScreen.kt", l = {1767}, m = "invokeSuspend", v = 2)
public final class PlayerScreenKt$PlayerScreen$26$1$gateReady$1 extends f7.j implements r7.p<ka.k0, d7.d<? super Boolean>, Object> {
    final /* synthetic */ State<PlayerUiState> $latestUiState$delegate;
    int label;

    /* JADX INFO: renamed from: com.arflix.tv.ui.screens.player.PlayerScreenKt$PlayerScreen$26$1$gateReady$1$2, reason: invalid class name */
    @Metadata(d1 = {"\u0000\b\n\u0000\n\u0002\u0010\u000b\n\u0000\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0001H\n"}, d2 = {"<anonymous>", "", "it"}, k = 3, mv = {2, 3, 0}, xi = 48)
    @f7.e(c = "com.arflix.tv.ui.screens.player.PlayerScreenKt$PlayerScreen$26$1$gateReady$1$2", f = "PlayerScreen.kt", l = {}, m = "invokeSuspend", v = 2)
    public static final class AnonymousClass2 extends f7.j implements r7.p<Boolean, d7.d<? super Boolean>, Object> {
        /* synthetic */ boolean Z$0;
        int label;

        public AnonymousClass2(d7.d<? super AnonymousClass2> dVar) {
            super(2, dVar);
        }

        @Override // f7.a
        public final d7.d<x6.t0> create(Object obj, d7.d<?> dVar) {
            AnonymousClass2 anonymousClass2 = new AnonymousClass2(dVar);
            anonymousClass2.Z$0 = ((Boolean) obj).booleanValue();
            return anonymousClass2;
        }

        @Override // r7.p
        public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
            return invoke(((Boolean) obj).booleanValue(), (d7.d<? super Boolean>) obj2);
        }

        @Override // f7.a
        public final Object invokeSuspend(Object obj) {
            boolean z = this.Z$0;
            if (this.label != 0) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            k2.c.G(obj);
            return Boolean.valueOf(z);
        }

        public final Object invoke(boolean z, d7.d<? super Boolean> dVar) {
            return ((AnonymousClass2) create(Boolean.valueOf(z), dVar)).invokeSuspend(x6.t0.f22605a);
        }
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public PlayerScreenKt$PlayerScreen$26$1$gateReady$1(State<PlayerUiState> state, d7.d<? super PlayerScreenKt$PlayerScreen$26$1$gateReady$1> dVar) {
        super(2, dVar);
        this.$latestUiState$delegate = state;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final boolean invokeSuspend$lambda$0(State state) {
        return PlayerScreenKt.PlayerScreen$lambda$4(state).getSubtitlePreloadComplete();
    }

    @Override // f7.a
    public final d7.d<x6.t0> create(Object obj, d7.d<?> dVar) {
        return new PlayerScreenKt$PlayerScreen$26$1$gateReady$1(this.$latestUiState$delegate, dVar);
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
        na.j jVarSnapshotFlow = SnapshotStateKt.snapshotFlow(new n1(this.$latestUiState$delegate, 0));
        AnonymousClass2 anonymousClass2 = new AnonymousClass2(null);
        this.label = 1;
        Object objM = na.y0.m(jVarSnapshotFlow, anonymousClass2, this);
        e7.a aVar = e7.a.f15033i;
        return objM == aVar ? aVar : objM;
    }

    @Override // r7.p
    public final Object invoke(ka.k0 k0Var, d7.d<? super Boolean> dVar) {
        return ((PlayerScreenKt$PlayerScreen$26$1$gateReady$1) create(k0Var, dVar)).invokeSuspend(x6.t0.f22605a);
    }
}
