package androidx.compose.material3;

import androidx.compose.foundation.interaction.FocusInteraction;
import androidx.compose.foundation.interaction.HoverInteraction;
import androidx.compose.foundation.interaction.Interaction;
import androidx.compose.foundation.interaction.InteractionSource;
import androidx.compose.foundation.interaction.PressInteraction;
import f7.j;
import java.util.ArrayList;
import ka.k0;
import ka.m0;
import kotlin.Metadata;
import kotlin.collections.x;
import na.k;
import r7.p;
import x6.t0;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0002\u001a\u00020\u0001*\u00020\u0000H\u008a@¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"Lka/k0;", "Lx6/t0;", "<anonymous>", "(Lka/k0;)V"}, k = 3, mv = {1, 8, 0})
@f7.e(c = "androidx.compose.material3.FloatingActionButtonElevation$animateElevation$2$1", f = "FloatingActionButton.kt", l = {519}, m = "invokeSuspend")
public final class FloatingActionButtonElevation$animateElevation$2$1 extends j implements p<k0, d7.d<? super t0>, Object> {
    final /* synthetic */ FloatingActionButtonElevationAnimatable $animatable;
    final /* synthetic */ InteractionSource $interactionSource;
    private /* synthetic */ Object L$0;
    int label;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public FloatingActionButtonElevation$animateElevation$2$1(InteractionSource interactionSource, FloatingActionButtonElevationAnimatable floatingActionButtonElevationAnimatable, d7.d<? super FloatingActionButtonElevation$animateElevation$2$1> dVar) {
        super(2, dVar);
        this.$interactionSource = interactionSource;
        this.$animatable = floatingActionButtonElevationAnimatable;
    }

    @Override // f7.a
    public final d7.d<t0> create(Object obj, d7.d<?> dVar) {
        FloatingActionButtonElevation$animateElevation$2$1 floatingActionButtonElevation$animateElevation$2$1 = new FloatingActionButtonElevation$animateElevation$2$1(this.$interactionSource, this.$animatable, dVar);
        floatingActionButtonElevation$animateElevation$2$1.L$0 = obj;
        return floatingActionButtonElevation$animateElevation$2$1;
    }

    @Override // f7.a
    public final Object invokeSuspend(Object obj) {
        int i10 = this.label;
        if (i10 == 0) {
            k2.c.G(obj);
            final k0 k0Var = (k0) this.L$0;
            final ArrayList arrayList = new ArrayList();
            na.j<Interaction> interactions = this.$interactionSource.getInteractions();
            final FloatingActionButtonElevationAnimatable floatingActionButtonElevationAnimatable = this.$animatable;
            k kVar = new k() { // from class: androidx.compose.material3.FloatingActionButtonElevation$animateElevation$2$1.1

                /* JADX INFO: renamed from: androidx.compose.material3.FloatingActionButtonElevation$animateElevation$2$1$1$1, reason: invalid class name and collision with other inner class name */
                @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0002\u001a\u00020\u0001*\u00020\u0000H\u008a@¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"Lka/k0;", "Lx6/t0;", "<anonymous>", "(Lka/k0;)V"}, k = 3, mv = {1, 8, 0})
                @f7.e(c = "androidx.compose.material3.FloatingActionButtonElevation$animateElevation$2$1$1$1", f = "FloatingActionButton.kt", l = {545}, m = "invokeSuspend")
                public static final class C01031 extends j implements p<k0, d7.d<? super t0>, Object> {
                    final /* synthetic */ FloatingActionButtonElevationAnimatable $animatable;
                    final /* synthetic */ Interaction $targetInteraction;
                    int label;

                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                    public C01031(FloatingActionButtonElevationAnimatable floatingActionButtonElevationAnimatable, Interaction interaction, d7.d<? super C01031> dVar) {
                        super(2, dVar);
                        this.$animatable = floatingActionButtonElevationAnimatable;
                        this.$targetInteraction = interaction;
                    }

                    @Override // f7.a
                    public final d7.d<t0> create(Object obj, d7.d<?> dVar) {
                        return new C01031(this.$animatable, this.$targetInteraction, dVar);
                    }

                    @Override // f7.a
                    public final Object invokeSuspend(Object obj) {
                        int i10 = this.label;
                        if (i10 == 0) {
                            k2.c.G(obj);
                            FloatingActionButtonElevationAnimatable floatingActionButtonElevationAnimatable = this.$animatable;
                            Interaction interaction = this.$targetInteraction;
                            this.label = 1;
                            Object objAnimateElevation = floatingActionButtonElevationAnimatable.animateElevation(interaction, this);
                            e7.a aVar = e7.a.f15033i;
                            if (objAnimateElevation == aVar) {
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
                        return ((C01031) create(k0Var, dVar)).invokeSuspend(t0.f22605a);
                    }
                }

                @Override // na.k
                public /* bridge */ /* synthetic */ Object emit(Object obj2, d7.d dVar) {
                    return emit((Interaction) obj2, (d7.d<? super t0>) dVar);
                }

                public final Object emit(Interaction interaction, d7.d<? super t0> dVar) {
                    if (interaction instanceof HoverInteraction.Enter) {
                        arrayList.add(interaction);
                    } else if (interaction instanceof HoverInteraction.Exit) {
                        arrayList.remove(((HoverInteraction.Exit) interaction).getEnter());
                    } else if (interaction instanceof FocusInteraction.Focus) {
                        arrayList.add(interaction);
                    } else if (interaction instanceof FocusInteraction.Unfocus) {
                        arrayList.remove(((FocusInteraction.Unfocus) interaction).getFocus());
                    } else if (interaction instanceof PressInteraction.Press) {
                        arrayList.add(interaction);
                    } else if (interaction instanceof PressInteraction.Release) {
                        arrayList.remove(((PressInteraction.Release) interaction).getPress());
                    } else if (interaction instanceof PressInteraction.Cancel) {
                        arrayList.remove(((PressInteraction.Cancel) interaction).getPress());
                    }
                    m0.p(k0Var, null, 0, new C01031(floatingActionButtonElevationAnimatable, (Interaction) x.y0(arrayList), null), 3);
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
        return ((FloatingActionButtonElevation$animateElevation$2$1) create(k0Var, dVar)).invokeSuspend(t0.f22605a);
    }
}
