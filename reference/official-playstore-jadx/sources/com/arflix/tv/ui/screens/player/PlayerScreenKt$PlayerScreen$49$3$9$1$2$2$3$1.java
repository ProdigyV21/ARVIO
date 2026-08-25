package com.arflix.tv.ui.screens.player;

import androidx.compose.foundation.gestures.DragGestureDetectorKt;
import androidx.compose.runtime.MutableIntState;
import androidx.compose.runtime.MutableLongState;
import androidx.compose.runtime.MutableState;
import androidx.compose.ui.geometry.Offset;
import androidx.compose.ui.input.pointer.PointerInputChange;
import androidx.compose.ui.input.pointer.PointerInputScope;
import androidx.media3.exoplayer.ExoPlayer;
import com.arflix.tv.cast.CastManager;
import kotlin.Metadata;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0002\u001a\u00020\u0001*\u00020\u0000H\n¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"Landroidx/compose/ui/input/pointer/PointerInputScope;", "Lx6/t0;", "<anonymous>", "(Landroidx/compose/ui/input/pointer/PointerInputScope;)V"}, k = 3, mv = {2, 3, 0})
@f7.e(c = "com.arflix.tv.ui.screens.player.PlayerScreenKt$PlayerScreen$49$3$9$1$2$2$3$1", f = "PlayerScreen.kt", l = {3796}, m = "invokeSuspend", v = 2)
public final class PlayerScreenKt$PlayerScreen$49$3$9$1$2$2$3$1 extends f7.j implements r7.p<PointerInputScope, d7.d<? super x6.t0>, Object> {
    final /* synthetic */ CastManager $castManager;
    final /* synthetic */ MutableLongState $duration$delegate;
    final /* synthetic */ ExoPlayer $exoPlayer;
    final /* synthetic */ boolean $isCasting;
    final /* synthetic */ MutableState<Boolean> $isControlScrubbing$delegate;
    final /* synthetic */ MutableState<Boolean> $playerReleased$delegate;
    final /* synthetic */ MutableLongState $scrubPreviewPosition$delegate;
    final /* synthetic */ MutableIntState $trackbarWidthPx$delegate;
    private /* synthetic */ Object L$0;
    int label;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public PlayerScreenKt$PlayerScreen$49$3$9$1$2$2$3$1(MutableLongState mutableLongState, MutableIntState mutableIntState, MutableLongState mutableLongState2, MutableState<Boolean> mutableState, boolean z, CastManager castManager, ExoPlayer exoPlayer, MutableState<Boolean> mutableState2, d7.d<? super PlayerScreenKt$PlayerScreen$49$3$9$1$2$2$3$1> dVar) {
        super(2, dVar);
        this.$duration$delegate = mutableLongState;
        this.$trackbarWidthPx$delegate = mutableIntState;
        this.$scrubPreviewPosition$delegate = mutableLongState2;
        this.$isControlScrubbing$delegate = mutableState;
        this.$isCasting = z;
        this.$castManager = castManager;
        this.$exoPlayer = exoPlayer;
        this.$playerReleased$delegate = mutableState2;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final x6.t0 invokeSuspend$lambda$0(MutableLongState mutableLongState, MutableIntState mutableIntState, MutableLongState mutableLongState2, MutableState mutableState, Offset offset) {
        if (mutableLongState.getLongValue() > 0 && mutableIntState.getIntValue() > 0) {
            mutableLongState2.setLongValue((long) (qb.d.m(Offset.m3236getXimpl(offset.getPackedValue()) / mutableIntState.getIntValue(), 0.0f, 1.0f) * mutableLongState.getLongValue()));
            PlayerScreenKt.PlayerScreen$lambda$66(mutableState, true);
        }
        return x6.t0.f22605a;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final x6.t0 invokeSuspend$lambda$1(boolean z, CastManager castManager, ExoPlayer exoPlayer, MutableState mutableState, MutableLongState mutableLongState, MutableState mutableState2) {
        if (PlayerScreenKt.PlayerScreen$lambda$65(mutableState)) {
            if (z) {
                castManager.seekTo(mutableLongState.getLongValue());
            } else if (!PlayerScreenKt.PlayerScreen$lambda$274(mutableState2)) {
                exoPlayer.seekTo(mutableLongState.getLongValue());
            }
            PlayerScreenKt.PlayerScreen$lambda$66(mutableState, false);
        }
        return x6.t0.f22605a;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final x6.t0 invokeSuspend$lambda$2(boolean z, CastManager castManager, ExoPlayer exoPlayer, MutableState mutableState, MutableLongState mutableLongState, MutableState mutableState2) {
        if (PlayerScreenKt.PlayerScreen$lambda$65(mutableState)) {
            if (z) {
                castManager.seekTo(mutableLongState.getLongValue());
            } else if (!PlayerScreenKt.PlayerScreen$lambda$274(mutableState2)) {
                exoPlayer.seekTo(mutableLongState.getLongValue());
            }
            PlayerScreenKt.PlayerScreen$lambda$66(mutableState, false);
        }
        return x6.t0.f22605a;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final x6.t0 invokeSuspend$lambda$3(MutableLongState mutableLongState, MutableIntState mutableIntState, MutableLongState mutableLongState2, MutableState mutableState, PointerInputChange pointerInputChange, float f10) {
        if (mutableLongState.getLongValue() > 0 && mutableIntState.getIntValue() > 0) {
            mutableLongState2.setLongValue(qb.d.o(mutableLongState2.getLongValue() + ((long) ((f10 / mutableIntState.getIntValue()) * mutableLongState.getLongValue())), 0L, mutableLongState.getLongValue()));
            PlayerScreenKt.PlayerScreen$lambda$66(mutableState, true);
        }
        return x6.t0.f22605a;
    }

    @Override // f7.a
    public final d7.d<x6.t0> create(Object obj, d7.d<?> dVar) {
        PlayerScreenKt$PlayerScreen$49$3$9$1$2$2$3$1 playerScreenKt$PlayerScreen$49$3$9$1$2$2$3$1 = new PlayerScreenKt$PlayerScreen$49$3$9$1$2$2$3$1(this.$duration$delegate, this.$trackbarWidthPx$delegate, this.$scrubPreviewPosition$delegate, this.$isControlScrubbing$delegate, this.$isCasting, this.$castManager, this.$exoPlayer, this.$playerReleased$delegate, dVar);
        playerScreenKt$PlayerScreen$49$3$9$1$2$2$3$1.L$0 = obj;
        return playerScreenKt$PlayerScreen$49$3$9$1$2$2$3$1;
    }

    @Override // r7.p
    public final Object invoke(PointerInputScope pointerInputScope, d7.d<? super x6.t0> dVar) {
        return ((PlayerScreenKt$PlayerScreen$49$3$9$1$2$2$3$1) create(pointerInputScope, dVar)).invokeSuspend(x6.t0.f22605a);
    }

    @Override // f7.a
    public final Object invokeSuspend(Object obj) {
        PointerInputScope pointerInputScope = (PointerInputScope) this.L$0;
        int i10 = this.label;
        if (i10 == 0) {
            k2.c.G(obj);
            final MutableLongState mutableLongState = this.$duration$delegate;
            final MutableIntState mutableIntState = this.$trackbarWidthPx$delegate;
            final MutableLongState mutableLongState2 = this.$scrubPreviewPosition$delegate;
            final MutableState<Boolean> mutableState = this.$isControlScrubbing$delegate;
            p1 p1Var = new p1(1, mutableState, mutableLongState, mutableIntState, mutableLongState2);
            final boolean z = this.$isCasting;
            final CastManager castManager = this.$castManager;
            final ExoPlayer exoPlayer = this.$exoPlayer;
            final MutableState<Boolean> mutableState2 = this.$playerReleased$delegate;
            final int i11 = 0;
            r7.a aVar = new r7.a() { // from class: com.arflix.tv.ui.screens.player.q1
                @Override // r7.a
                public final Object invoke() {
                    int i12 = i11;
                    MutableLongState mutableLongState3 = mutableLongState2;
                    MutableState mutableState3 = mutableState2;
                    boolean z5 = z;
                    CastManager castManager2 = castManager;
                    ExoPlayer exoPlayer2 = exoPlayer;
                    MutableState mutableState4 = mutableState;
                    switch (i12) {
                        case 0:
                            return PlayerScreenKt$PlayerScreen$49$3$9$1$2$2$3$1.invokeSuspend$lambda$1(z5, castManager2, exoPlayer2, mutableState4, mutableLongState3, mutableState3);
                        default:
                            return PlayerScreenKt$PlayerScreen$49$3$9$1$2$2$3$1.invokeSuspend$lambda$2(z5, castManager2, exoPlayer2, mutableState4, mutableLongState3, mutableState3);
                    }
                }
            };
            final int i12 = 1;
            r7.a aVar2 = new r7.a() { // from class: com.arflix.tv.ui.screens.player.q1
                @Override // r7.a
                public final Object invoke() {
                    int i122 = i12;
                    MutableLongState mutableLongState3 = mutableLongState2;
                    MutableState mutableState3 = mutableState2;
                    boolean z5 = z;
                    CastManager castManager2 = castManager;
                    ExoPlayer exoPlayer2 = exoPlayer;
                    MutableState mutableState4 = mutableState;
                    switch (i122) {
                        case 0:
                            return PlayerScreenKt$PlayerScreen$49$3$9$1$2$2$3$1.invokeSuspend$lambda$1(z5, castManager2, exoPlayer2, mutableState4, mutableLongState3, mutableState3);
                        default:
                            return PlayerScreenKt$PlayerScreen$49$3$9$1$2$2$3$1.invokeSuspend$lambda$2(z5, castManager2, exoPlayer2, mutableState4, mutableLongState3, mutableState3);
                    }
                }
            };
            r7.p pVar = new r7.p() { // from class: com.arflix.tv.ui.screens.player.r1
                @Override // r7.p
                public final Object invoke(Object obj2, Object obj3) {
                    float fFloatValue = ((Float) obj3).floatValue();
                    return PlayerScreenKt$PlayerScreen$49$3$9$1$2$2$3$1.invokeSuspend$lambda$3(mutableLongState, mutableIntState, mutableLongState2, mutableState, (PointerInputChange) obj2, fFloatValue);
                }
            };
            this.L$0 = null;
            this.label = 1;
            Object objDetectHorizontalDragGestures = DragGestureDetectorKt.detectHorizontalDragGestures(pointerInputScope, p1Var, aVar, aVar2, pVar, this);
            e7.a aVar3 = e7.a.f15033i;
            if (objDetectHorizontalDragGestures == aVar3) {
                return aVar3;
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
