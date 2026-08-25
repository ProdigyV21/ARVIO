package androidx.compose.material3;

import androidx.compose.foundation.MutatePriority;
import androidx.compose.foundation.interaction.DragInteraction;
import androidx.compose.foundation.interaction.HoverInteraction;
import androidx.compose.foundation.interaction.Interaction;
import androidx.compose.foundation.interaction.MutableInteractionSource;
import androidx.compose.foundation.interaction.PressInteraction;
import f7.j;
import ka.k0;
import kotlin.Metadata;
import na.y0;
import r7.p;
import x6.t0;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0002\u001a\u00020\u0001*\u00020\u0000H\u008a@¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"Lka/k0;", "Lx6/t0;", "<anonymous>", "(Lka/k0;)V"}, k = 3, mv = {1, 8, 0})
@f7.e(c = "androidx.compose.material3.LabelKt$HandleInteractions$1$1", f = "Label.kt", l = {121}, m = "invokeSuspend")
public final class LabelKt$HandleInteractions$1$1 extends j implements p<k0, d7.d<? super t0>, Object> {
    final /* synthetic */ MutableInteractionSource $interactionSource;
    final /* synthetic */ BasicTooltipState $state;
    int label;

    /* JADX INFO: renamed from: androidx.compose.material3.LabelKt$HandleInteractions$1$1$1, reason: invalid class name */
    @Metadata(d1 = {"\u0000\u000e\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0003\u001a\u00020\u00022\u0006\u0010\u0001\u001a\u00020\u0000H\u008a@¢\u0006\u0004\b\u0003\u0010\u0004"}, d2 = {"Landroidx/compose/foundation/interaction/Interaction;", "interaction", "Lx6/t0;", "<anonymous>", "(Landroidx/compose/foundation/interaction/Interaction;)V"}, k = 3, mv = {1, 8, 0})
    @f7.e(c = "androidx.compose.material3.LabelKt$HandleInteractions$1$1$1", f = "Label.kt", l = {125}, m = "invokeSuspend")
    public static final class AnonymousClass1 extends j implements p<Interaction, d7.d<? super t0>, Object> {
        final /* synthetic */ BasicTooltipState $state;
        /* synthetic */ Object L$0;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public AnonymousClass1(BasicTooltipState basicTooltipState, d7.d<? super AnonymousClass1> dVar) {
            super(2, dVar);
            this.$state = basicTooltipState;
        }

        @Override // f7.a
        public final d7.d<t0> create(Object obj, d7.d<?> dVar) {
            AnonymousClass1 anonymousClass1 = new AnonymousClass1(this.$state, dVar);
            anonymousClass1.L$0 = obj;
            return anonymousClass1;
        }

        @Override // r7.p
        public final Object invoke(Interaction interaction, d7.d<? super t0> dVar) {
            return ((AnonymousClass1) create(interaction, dVar)).invokeSuspend(t0.f22605a);
        }

        @Override // f7.a
        public final Object invokeSuspend(Object obj) {
            int i10 = this.label;
            if (i10 == 0) {
                k2.c.G(obj);
                Interaction interaction = (Interaction) this.L$0;
                if (interaction instanceof PressInteraction.Press ? true : interaction instanceof DragInteraction.Start ? true : interaction instanceof HoverInteraction.Enter) {
                    BasicTooltipState basicTooltipState = this.$state;
                    MutatePriority mutatePriority = MutatePriority.UserInput;
                    this.label = 1;
                    Object objShow = basicTooltipState.show(mutatePriority, this);
                    e7.a aVar = e7.a.f15033i;
                    if (objShow == aVar) {
                        return aVar;
                    }
                } else {
                    if (interaction instanceof PressInteraction.Release ? true : interaction instanceof DragInteraction.Stop ? true : interaction instanceof HoverInteraction.Exit) {
                        this.$state.dismiss();
                    }
                }
            } else {
                if (i10 != 1) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                k2.c.G(obj);
            }
            return t0.f22605a;
        }
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public LabelKt$HandleInteractions$1$1(MutableInteractionSource mutableInteractionSource, BasicTooltipState basicTooltipState, d7.d<? super LabelKt$HandleInteractions$1$1> dVar) {
        super(2, dVar);
        this.$interactionSource = mutableInteractionSource;
        this.$state = basicTooltipState;
    }

    @Override // f7.a
    public final d7.d<t0> create(Object obj, d7.d<?> dVar) {
        return new LabelKt$HandleInteractions$1$1(this.$interactionSource, this.$state, dVar);
    }

    @Override // f7.a
    public final Object invokeSuspend(Object obj) {
        int i10 = this.label;
        if (i10 == 0) {
            k2.c.G(obj);
            na.j<Interaction> interactions = this.$interactionSource.getInteractions();
            AnonymousClass1 anonymousClass1 = new AnonymousClass1(this.$state, null);
            this.label = 1;
            Object objG = y0.g(interactions, anonymousClass1, this);
            e7.a aVar = e7.a.f15033i;
            if (objG == aVar) {
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
        return ((LabelKt$HandleInteractions$1$1) create(k0Var, dVar)).invokeSuspend(t0.f22605a);
    }
}
