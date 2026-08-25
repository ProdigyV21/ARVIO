package com.arflix.tv.ui.screens.player;

import androidx.compose.foundation.gestures.TapGestureDetectorKt;
import androidx.compose.runtime.MutableState;
import androidx.compose.runtime.State;
import androidx.compose.ui.geometry.Offset;
import androidx.compose.ui.input.pointer.PointerInputScope;
import androidx.compose.ui.unit.IntSize;
import kotlin.Metadata;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0002\u001a\u00020\u0001*\u00020\u0000H\n¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"Landroidx/compose/ui/input/pointer/PointerInputScope;", "Lx6/t0;", "<anonymous>", "(Landroidx/compose/ui/input/pointer/PointerInputScope;)V"}, k = 3, mv = {2, 3, 0})
@f7.e(c = "com.arflix.tv.ui.screens.player.PlayerScreenKt$PlayerScreen$49$1$1", f = "PlayerScreen.kt", l = {2666}, m = "invokeSuspend", v = 2)
public final class PlayerScreenKt$PlayerScreen$49$1$1 extends f7.j implements r7.p<PointerInputScope, d7.d<? super x6.t0>, Object> {
    final /* synthetic */ r7.l<Long, x6.t0> $queueControlsSeek;
    final /* synthetic */ MutableState<Boolean> $showControls$delegate;
    final /* synthetic */ MutableState<Boolean> $showSourceMenu$delegate;
    final /* synthetic */ MutableState<Boolean> $showSubtitleMenu$delegate;
    final /* synthetic */ State<PlayerUiState> $uiState$delegate;
    private /* synthetic */ Object L$0;
    int label;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    /* JADX WARN: Multi-variable type inference failed */
    public PlayerScreenKt$PlayerScreen$49$1$1(r7.l<? super Long, x6.t0> lVar, State<PlayerUiState> state, MutableState<Boolean> mutableState, MutableState<Boolean> mutableState2, MutableState<Boolean> mutableState3, d7.d<? super PlayerScreenKt$PlayerScreen$49$1$1> dVar) {
        super(2, dVar);
        this.$queueControlsSeek = lVar;
        this.$uiState$delegate = state;
        this.$showSubtitleMenu$delegate = mutableState;
        this.$showSourceMenu$delegate = mutableState2;
        this.$showControls$delegate = mutableState3;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final x6.t0 invokeSuspend$lambda$0(PointerInputScope pointerInputScope, r7.l lVar, State state, MutableState mutableState, MutableState mutableState2, Offset offset) {
        if (PlayerScreenKt.PlayerScreen$lambda$3(state).getError() == null && !PlayerScreenKt.PlayerScreen$lambda$109(mutableState) && !PlayerScreenKt.PlayerScreen$lambda$112(mutableState2)) {
            if (Offset.m3236getXimpl(offset.getPackedValue()) < IntSize.m5844getWidthimpl(pointerInputScope.getBoundsSize()) / 2) {
                lVar.invoke(-10000L);
            } else {
                lVar.invoke(10000L);
            }
        }
        return x6.t0.f22605a;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final x6.t0 invokeSuspend$lambda$1(State state, MutableState mutableState, MutableState mutableState2, MutableState mutableState3, Offset offset) {
        if (PlayerScreenKt.PlayerScreen$lambda$3(state).getError() == null && !PlayerScreenKt.PlayerScreen$lambda$109(mutableState) && !PlayerScreenKt.PlayerScreen$lambda$112(mutableState2)) {
            PlayerScreenKt.PlayerScreen$lambda$33(mutableState3, !PlayerScreenKt.PlayerScreen$lambda$32(mutableState3));
        }
        return x6.t0.f22605a;
    }

    @Override // f7.a
    public final d7.d<x6.t0> create(Object obj, d7.d<?> dVar) {
        PlayerScreenKt$PlayerScreen$49$1$1 playerScreenKt$PlayerScreen$49$1$1 = new PlayerScreenKt$PlayerScreen$49$1$1(this.$queueControlsSeek, this.$uiState$delegate, this.$showSubtitleMenu$delegate, this.$showSourceMenu$delegate, this.$showControls$delegate, dVar);
        playerScreenKt$PlayerScreen$49$1$1.L$0 = obj;
        return playerScreenKt$PlayerScreen$49$1$1;
    }

    @Override // r7.p
    public final Object invoke(PointerInputScope pointerInputScope, d7.d<? super x6.t0> dVar) {
        return ((PlayerScreenKt$PlayerScreen$49$1$1) create(pointerInputScope, dVar)).invokeSuspend(x6.t0.f22605a);
    }

    @Override // f7.a
    public final Object invokeSuspend(Object obj) {
        PointerInputScope pointerInputScope = (PointerInputScope) this.L$0;
        int i10 = this.label;
        if (i10 == 0) {
            k2.c.G(obj);
            r7.l<Long, x6.t0> lVar = this.$queueControlsSeek;
            State<PlayerUiState> state = this.$uiState$delegate;
            MutableState<Boolean> mutableState = this.$showSubtitleMenu$delegate;
            MutableState<Boolean> mutableState2 = this.$showSourceMenu$delegate;
            o1 o1Var = new o1(pointerInputScope, lVar, state, mutableState, mutableState2, 0);
            p1 p1Var = new p1(state, mutableState, mutableState2, this.$showControls$delegate);
            this.L$0 = null;
            this.label = 1;
            Object objDetectTapGestures$default = TapGestureDetectorKt.detectTapGestures$default(pointerInputScope, o1Var, null, null, p1Var, this, 6, null);
            e7.a aVar = e7.a.f15033i;
            if (objDetectTapGestures$default == aVar) {
                return aVar;
            }
        } else {
            if (i10 != 1) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            k2.c.G(obj);
        }
        return x6.t0.f22605a;
    }
}
