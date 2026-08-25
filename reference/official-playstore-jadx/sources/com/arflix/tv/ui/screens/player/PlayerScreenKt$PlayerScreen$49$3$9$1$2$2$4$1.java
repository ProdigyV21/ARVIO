package com.arflix.tv.ui.screens.player;

import androidx.compose.foundation.gestures.TapGestureDetectorKt;
import androidx.compose.runtime.MutableIntState;
import androidx.compose.runtime.MutableLongState;
import androidx.compose.runtime.MutableState;
import androidx.compose.ui.geometry.Offset;
import androidx.compose.ui.input.pointer.PointerInputScope;
import androidx.media3.exoplayer.ExoPlayer;
import com.arflix.tv.cast.CastManager;
import kotlin.Metadata;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0002\u001a\u00020\u0001*\u00020\u0000H\n¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"Landroidx/compose/ui/input/pointer/PointerInputScope;", "Lx6/t0;", "<anonymous>", "(Landroidx/compose/ui/input/pointer/PointerInputScope;)V"}, k = 3, mv = {2, 3, 0})
@f7.e(c = "com.arflix.tv.ui.screens.player.PlayerScreenKt$PlayerScreen$49$3$9$1$2$2$4$1", f = "PlayerScreen.kt", l = {3804}, m = "invokeSuspend", v = 2)
public final class PlayerScreenKt$PlayerScreen$49$3$9$1$2$2$4$1 extends f7.j implements r7.p<PointerInputScope, d7.d<? super x6.t0>, Object> {
    final /* synthetic */ CastManager $castManager;
    final /* synthetic */ MutableLongState $duration$delegate;
    final /* synthetic */ ExoPlayer $exoPlayer;
    final /* synthetic */ boolean $isCasting;
    final /* synthetic */ MutableState<Boolean> $playerReleased$delegate;
    final /* synthetic */ MutableIntState $trackbarWidthPx$delegate;
    private /* synthetic */ Object L$0;
    int label;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public PlayerScreenKt$PlayerScreen$49$3$9$1$2$2$4$1(boolean z, CastManager castManager, ExoPlayer exoPlayer, MutableLongState mutableLongState, MutableIntState mutableIntState, MutableState<Boolean> mutableState, d7.d<? super PlayerScreenKt$PlayerScreen$49$3$9$1$2$2$4$1> dVar) {
        super(2, dVar);
        this.$isCasting = z;
        this.$castManager = castManager;
        this.$exoPlayer = exoPlayer;
        this.$duration$delegate = mutableLongState;
        this.$trackbarWidthPx$delegate = mutableIntState;
        this.$playerReleased$delegate = mutableState;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final x6.t0 invokeSuspend$lambda$0(boolean z, CastManager castManager, ExoPlayer exoPlayer, MutableLongState mutableLongState, MutableIntState mutableIntState, MutableState mutableState, Offset offset) {
        if (mutableLongState.getLongValue() > 0 && mutableIntState.getIntValue() > 0) {
            long jM = (long) (qb.d.m(Offset.m3236getXimpl(offset.getPackedValue()) / mutableIntState.getIntValue(), 0.0f, 1.0f) * mutableLongState.getLongValue());
            if (z) {
                castManager.seekTo(jM);
            } else if (!PlayerScreenKt.PlayerScreen$lambda$274(mutableState)) {
                exoPlayer.seekTo(jM);
            }
        }
        return x6.t0.f22605a;
    }

    @Override // f7.a
    public final d7.d<x6.t0> create(Object obj, d7.d<?> dVar) {
        PlayerScreenKt$PlayerScreen$49$3$9$1$2$2$4$1 playerScreenKt$PlayerScreen$49$3$9$1$2$2$4$1 = new PlayerScreenKt$PlayerScreen$49$3$9$1$2$2$4$1(this.$isCasting, this.$castManager, this.$exoPlayer, this.$duration$delegate, this.$trackbarWidthPx$delegate, this.$playerReleased$delegate, dVar);
        playerScreenKt$PlayerScreen$49$3$9$1$2$2$4$1.L$0 = obj;
        return playerScreenKt$PlayerScreen$49$3$9$1$2$2$4$1;
    }

    @Override // r7.p
    public final Object invoke(PointerInputScope pointerInputScope, d7.d<? super x6.t0> dVar) {
        return ((PlayerScreenKt$PlayerScreen$49$3$9$1$2$2$4$1) create(pointerInputScope, dVar)).invokeSuspend(x6.t0.f22605a);
    }

    @Override // f7.a
    public final Object invokeSuspend(Object obj) {
        PointerInputScope pointerInputScope = (PointerInputScope) this.L$0;
        int i10 = this.label;
        if (i10 == 0) {
            k2.c.G(obj);
            final boolean z = this.$isCasting;
            final CastManager castManager = this.$castManager;
            final ExoPlayer exoPlayer = this.$exoPlayer;
            final MutableLongState mutableLongState = this.$duration$delegate;
            final MutableIntState mutableIntState = this.$trackbarWidthPx$delegate;
            final MutableState<Boolean> mutableState = this.$playerReleased$delegate;
            r7.l lVar = new r7.l() { // from class: com.arflix.tv.ui.screens.player.s1
                @Override // r7.l
                public final Object invoke(Object obj2) {
                    return PlayerScreenKt$PlayerScreen$49$3$9$1$2$2$4$1.invokeSuspend$lambda$0(z, castManager, exoPlayer, mutableLongState, mutableIntState, mutableState, (Offset) obj2);
                }
            };
            this.L$0 = null;
            this.label = 1;
            Object objDetectTapGestures$default = TapGestureDetectorKt.detectTapGestures$default(pointerInputScope, null, null, null, lVar, this, 7, null);
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
