package androidx.compose.material3;

import androidx.compose.foundation.interaction.Interaction;
import androidx.compose.foundation.interaction.MutableInteractionSource;
import f7.j;
import ka.k0;
import ka.m0;
import kotlin.Metadata;
import r7.p;
import x6.t0;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000@\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\u0007\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\n\b\u0002\u0018\u00002\u00020\u0001B\u001f\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\u0006\u0010\u0005\u001a\u00020\u0004\u0012\u0006\u0010\u0006\u001a\u00020\u0004¢\u0006\u0004\b\u0007\u0010\bJ\u0015\u0010\u000b\u001a\u00020\u00042\u0006\u0010\n\u001a\u00020\t¢\u0006\u0004\b\u000b\u0010\fJ\u0015\u0010\u0010\u001a\u00020\u000f2\u0006\u0010\u000e\u001a\u00020\r¢\u0006\u0004\b\u0010\u0010\u0011J-\u0010\u0018\u001a\u00020\u00172\u0006\u0010\n\u001a\u00020\t2\u0006\u0010\u0012\u001a\u00020\r2\u0006\u0010\u0014\u001a\u00020\u00132\u0006\u0010\u0016\u001a\u00020\u0015¢\u0006\u0004\b\u0018\u0010\u0019R\u0017\u0010\u0003\u001a\u00020\u00028\u0006¢\u0006\f\n\u0004\b\u0003\u0010\u001a\u001a\u0004\b\u001b\u0010\u001cR\u0017\u0010\u0005\u001a\u00020\u00048\u0006¢\u0006\f\n\u0004\b\u0005\u0010\u001d\u001a\u0004\b\u001e\u0010\u001fR\u0017\u0010\u0006\u001a\u00020\u00048\u0006¢\u0006\f\n\u0004\b\u0006\u0010\u001d\u001a\u0004\b \u0010\u001f¨\u0006!"}, d2 = {"Landroidx/compose/material3/RangeSliderLogic;", "", "Landroidx/compose/material3/RangeSliderState;", "state", "Landroidx/compose/foundation/interaction/MutableInteractionSource;", "startInteractionSource", "endInteractionSource", "<init>", "(Landroidx/compose/material3/RangeSliderState;Landroidx/compose/foundation/interaction/MutableInteractionSource;Landroidx/compose/foundation/interaction/MutableInteractionSource;)V", "", "draggingStart", "activeInteraction", "(Z)Landroidx/compose/foundation/interaction/MutableInteractionSource;", "", "eventX", "", "compareOffsets", "(F)I", "posX", "Landroidx/compose/foundation/interaction/Interaction;", "interaction", "Lka/k0;", "scope", "Lx6/t0;", "captureThumb", "(ZFLandroidx/compose/foundation/interaction/Interaction;Lka/k0;)V", "Landroidx/compose/material3/RangeSliderState;", "getState", "()Landroidx/compose/material3/RangeSliderState;", "Landroidx/compose/foundation/interaction/MutableInteractionSource;", "getStartInteractionSource", "()Landroidx/compose/foundation/interaction/MutableInteractionSource;", "getEndInteractionSource", "material3_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
final class RangeSliderLogic {
    private final MutableInteractionSource endInteractionSource;
    private final MutableInteractionSource startInteractionSource;
    private final RangeSliderState state;

    /* JADX INFO: renamed from: androidx.compose.material3.RangeSliderLogic$captureThumb$1, reason: invalid class name */
    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0002\u001a\u00020\u0001*\u00020\u0000H\u008a@¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"Lka/k0;", "Lx6/t0;", "<anonymous>", "(Lka/k0;)V"}, k = 3, mv = {1, 8, 0})
    @f7.e(c = "androidx.compose.material3.RangeSliderLogic$captureThumb$1", f = "Slider.kt", l = {1580}, m = "invokeSuspend")
    public static final class AnonymousClass1 extends j implements p<k0, d7.d<? super t0>, Object> {
        final /* synthetic */ boolean $draggingStart;
        final /* synthetic */ Interaction $interaction;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public AnonymousClass1(boolean z, Interaction interaction, d7.d<? super AnonymousClass1> dVar) {
            super(2, dVar);
            this.$draggingStart = z;
            this.$interaction = interaction;
        }

        @Override // f7.a
        public final d7.d<t0> create(Object obj, d7.d<?> dVar) {
            return RangeSliderLogic.this.new AnonymousClass1(this.$draggingStart, this.$interaction, dVar);
        }

        @Override // f7.a
        public final Object invokeSuspend(Object obj) {
            int i10 = this.label;
            if (i10 == 0) {
                k2.c.G(obj);
                MutableInteractionSource mutableInteractionSourceActiveInteraction = RangeSliderLogic.this.activeInteraction(this.$draggingStart);
                Interaction interaction = this.$interaction;
                this.label = 1;
                Object objEmit = mutableInteractionSourceActiveInteraction.emit(interaction, this);
                e7.a aVar = e7.a.f15033i;
                if (objEmit == aVar) {
                    return aVar;
                }
            } else {
                if (i10 != 1) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                k2.c.G(obj);
            }
            return t0.f22605a;
        }

        @Override // r7.p
        public final Object invoke(k0 k0Var, d7.d<? super t0> dVar) {
            return ((AnonymousClass1) create(k0Var, dVar)).invokeSuspend(t0.f22605a);
        }
    }

    public RangeSliderLogic(RangeSliderState rangeSliderState, MutableInteractionSource mutableInteractionSource, MutableInteractionSource mutableInteractionSource2) {
        this.state = rangeSliderState;
        this.startInteractionSource = mutableInteractionSource;
        this.endInteractionSource = mutableInteractionSource2;
    }

    public final MutableInteractionSource activeInteraction(boolean draggingStart) {
        return draggingStart ? this.startInteractionSource : this.endInteractionSource;
    }

    public final void captureThumb(boolean draggingStart, float posX, Interaction interaction, k0 scope) {
        RangeSliderState rangeSliderState = this.state;
        rangeSliderState.onDrag$material3_release(draggingStart, posX - (draggingStart ? rangeSliderState.getRawOffsetStart$material3_release() : rangeSliderState.getRawOffsetEnd$material3_release()));
        m0.p(scope, null, 0, new AnonymousClass1(draggingStart, interaction, null), 3);
    }

    public final int compareOffsets(float eventX) {
        return Float.compare(Math.abs(this.state.getRawOffsetStart$material3_release() - eventX), Math.abs(this.state.getRawOffsetEnd$material3_release() - eventX));
    }

    public final MutableInteractionSource getEndInteractionSource() {
        return this.endInteractionSource;
    }

    public final MutableInteractionSource getStartInteractionSource() {
        return this.startInteractionSource;
    }

    public final RangeSliderState getState() {
        return this.state;
    }
}
