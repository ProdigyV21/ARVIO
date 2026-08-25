package androidx.compose.material3;

import androidx.compose.foundation.interaction.FocusInteraction;
import androidx.compose.foundation.interaction.Interaction;
import androidx.compose.foundation.interaction.InteractionSource;
import androidx.compose.foundation.interaction.PressInteraction;
import androidx.compose.runtime.MutableIntState;
import f7.j;
import ka.k0;
import kotlin.Metadata;
import na.k;
import r7.p;
import x6.t0;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0002\u001a\u00020\u0001*\u00020\u0000H\u008a@¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"Lka/k0;", "Lx6/t0;", "<anonymous>", "(Lka/k0;)V"}, k = 3, mv = {1, 8, 0})
@f7.e(c = "androidx.compose.material3.SegmentedButtonKt$interactionCountAsState$1$1", f = "SegmentedButton.kt", l = {402}, m = "invokeSuspend")
public final class SegmentedButtonKt$interactionCountAsState$1$1 extends j implements p<k0, d7.d<? super t0>, Object> {
    final /* synthetic */ MutableIntState $interactionCount;
    final /* synthetic */ InteractionSource $this_interactionCountAsState;
    int label;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public SegmentedButtonKt$interactionCountAsState$1$1(InteractionSource interactionSource, MutableIntState mutableIntState, d7.d<? super SegmentedButtonKt$interactionCountAsState$1$1> dVar) {
        super(2, dVar);
        this.$this_interactionCountAsState = interactionSource;
        this.$interactionCount = mutableIntState;
    }

    @Override // f7.a
    public final d7.d<t0> create(Object obj, d7.d<?> dVar) {
        return new SegmentedButtonKt$interactionCountAsState$1$1(this.$this_interactionCountAsState, this.$interactionCount, dVar);
    }

    @Override // f7.a
    public final Object invokeSuspend(Object obj) {
        int i10 = this.label;
        if (i10 == 0) {
            k2.c.G(obj);
            na.j<Interaction> interactions = this.$this_interactionCountAsState.getInteractions();
            final MutableIntState mutableIntState = this.$interactionCount;
            k kVar = new k() { // from class: androidx.compose.material3.SegmentedButtonKt$interactionCountAsState$1$1.1
                @Override // na.k
                public /* bridge */ /* synthetic */ Object emit(Object obj2, d7.d dVar) {
                    return emit((Interaction) obj2, (d7.d<? super t0>) dVar);
                }

                public final Object emit(Interaction interaction, d7.d<? super t0> dVar) {
                    if (interaction instanceof PressInteraction.Press ? true : interaction instanceof FocusInteraction.Focus) {
                        MutableIntState mutableIntState2 = mutableIntState;
                        mutableIntState2.setIntValue(mutableIntState2.getIntValue() + 1);
                    } else {
                        if (interaction instanceof PressInteraction.Release ? true : interaction instanceof FocusInteraction.Unfocus ? true : interaction instanceof PressInteraction.Cancel) {
                            mutableIntState.setIntValue(r2.getIntValue() - 1);
                        }
                    }
                    return t0.f22605a;
                }
            };
            this.label = 1;
            Object objCollect = interactions.collect(kVar, this);
            e7.a aVar = e7.a.f15033i;
            if (objCollect == aVar) {
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
        return ((SegmentedButtonKt$interactionCountAsState$1$1) create(k0Var, dVar)).invokeSuspend(t0.f22605a);
    }
}
