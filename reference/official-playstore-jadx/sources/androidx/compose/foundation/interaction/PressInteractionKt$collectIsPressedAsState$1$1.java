package androidx.compose.foundation.interaction;

import androidx.compose.foundation.c;
import androidx.compose.foundation.interaction.PressInteraction;
import androidx.compose.runtime.MutableState;
import d7.d;
import e7.a;
import f7.e;
import f7.j;
import java.util.ArrayList;
import ka.k0;
import kotlin.Metadata;
import na.k;
import r7.p;
import x6.t0;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0002\u001a\u00020\u0001*\u00020\u0000H\u008a@¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"Lka/k0;", "Lx6/t0;", "<anonymous>", "(Lka/k0;)V"}, k = 3, mv = {1, 8, 0})
@e(c = "androidx.compose.foundation.interaction.PressInteractionKt$collectIsPressedAsState$1$1", f = "PressInteraction.kt", l = {88}, m = "invokeSuspend")
public final class PressInteractionKt$collectIsPressedAsState$1$1 extends j implements p<k0, d<? super t0>, Object> {
    final /* synthetic */ MutableState<Boolean> $isPressed;
    final /* synthetic */ InteractionSource $this_collectIsPressedAsState;
    int label;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public PressInteractionKt$collectIsPressedAsState$1$1(InteractionSource interactionSource, MutableState<Boolean> mutableState, d<? super PressInteractionKt$collectIsPressedAsState$1$1> dVar) {
        super(2, dVar);
        this.$this_collectIsPressedAsState = interactionSource;
        this.$isPressed = mutableState;
    }

    @Override // f7.a
    public final d<t0> create(Object obj, d<?> dVar) {
        return new PressInteractionKt$collectIsPressedAsState$1$1(this.$this_collectIsPressedAsState, this.$isPressed, dVar);
    }

    @Override // f7.a
    public final Object invokeSuspend(Object obj) {
        int i10 = this.label;
        if (i10 == 0) {
            final ArrayList arrayListW = c.w(obj);
            na.j<Interaction> interactions = this.$this_collectIsPressedAsState.getInteractions();
            final MutableState<Boolean> mutableState = this.$isPressed;
            k kVar = new k() { // from class: androidx.compose.foundation.interaction.PressInteractionKt$collectIsPressedAsState$1$1.1
                @Override // na.k
                public /* bridge */ /* synthetic */ Object emit(Object obj2, d dVar) {
                    return emit((Interaction) obj2, (d<? super t0>) dVar);
                }

                public final Object emit(Interaction interaction, d<? super t0> dVar) {
                    if (interaction instanceof PressInteraction.Press) {
                        arrayListW.add(interaction);
                    } else if (interaction instanceof PressInteraction.Release) {
                        arrayListW.remove(((PressInteraction.Release) interaction).getPress());
                    } else if (interaction instanceof PressInteraction.Cancel) {
                        arrayListW.remove(((PressInteraction.Cancel) interaction).getPress());
                    }
                    mutableState.setValue(Boolean.valueOf(!arrayListW.isEmpty()));
                    return t0.f22605a;
                }
            };
            this.label = 1;
            Object objCollect = interactions.collect(kVar, this);
            a aVar = a.f15033i;
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
    public final Object invoke(k0 k0Var, d<? super t0> dVar) {
        return ((PressInteractionKt$collectIsPressedAsState$1$1) create(k0Var, dVar)).invokeSuspend(t0.f22605a);
    }
}
