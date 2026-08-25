package androidx.compose.animation;

import androidx.compose.animation.core.Transition;
import androidx.compose.runtime.ProduceStateScope;
import androidx.compose.runtime.SnapshotStateKt;
import androidx.compose.runtime.State;
import d7.d;
import f7.e;
import f7.j;
import k2.c;
import kotlin.Metadata;
import kotlin.jvm.internal.r;
import na.k;
import r7.p;
import x6.t0;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0012\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u000b\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0004\u001a\u00020\u0003\"\u0004\b\u0000\u0010\u0000*\b\u0012\u0004\u0012\u00020\u00020\u0001H\u008a@¢\u0006\u0004\b\u0004\u0010\u0005"}, d2 = {"T", "Landroidx/compose/runtime/ProduceStateScope;", "", "Lx6/t0;", "<anonymous>", "(Landroidx/compose/runtime/ProduceStateScope;)V"}, k = 3, mv = {1, 8, 0})
@e(c = "androidx.compose.animation.AnimatedVisibilityKt$AnimatedEnterExitImpl$shouldDisposeAfterExit$2$1", f = "AnimatedVisibility.kt", l = {803}, m = "invokeSuspend")
public final class AnimatedVisibilityKt$AnimatedEnterExitImpl$shouldDisposeAfterExit$2$1 extends j implements p<ProduceStateScope<Boolean>, d<? super t0>, Object> {
    final /* synthetic */ Transition<EnterExitState> $childTransition;
    final /* synthetic */ State<p<EnterExitState, EnterExitState, Boolean>> $shouldDisposeBlockUpdated$delegate;
    private /* synthetic */ Object L$0;
    int label;

    /* JADX INFO: renamed from: androidx.compose.animation.AnimatedVisibilityKt$AnimatedEnterExitImpl$shouldDisposeAfterExit$2$1$1, reason: invalid class name */
    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0003\u0010\u0000\u001a\u00020\u0001\"\u0004\b\u0000\u0010\u0002H\n¢\u0006\u0004\b\u0003\u0010\u0004"}, d2 = {"<anonymous>", "", "T", "invoke", "()Ljava/lang/Boolean;"}, k = 3, mv = {1, 8, 0}, xi = 48)
    public static final class AnonymousClass1 extends r implements r7.a<Boolean> {
        final /* synthetic */ Transition<EnterExitState> $childTransition;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public AnonymousClass1(Transition<EnterExitState> transition) {
            super(0);
            this.$childTransition = transition;
        }

        @Override // r7.a
        public final Boolean invoke() {
            return Boolean.valueOf(AnimatedVisibilityKt.getExitFinished(this.$childTransition));
        }
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    /* JADX WARN: Multi-variable type inference failed */
    public AnimatedVisibilityKt$AnimatedEnterExitImpl$shouldDisposeAfterExit$2$1(Transition<EnterExitState> transition, State<? extends p<? super EnterExitState, ? super EnterExitState, Boolean>> state, d<? super AnimatedVisibilityKt$AnimatedEnterExitImpl$shouldDisposeAfterExit$2$1> dVar) {
        super(2, dVar);
        this.$childTransition = transition;
        this.$shouldDisposeBlockUpdated$delegate = state;
    }

    @Override // f7.a
    public final d<t0> create(Object obj, d<?> dVar) {
        AnimatedVisibilityKt$AnimatedEnterExitImpl$shouldDisposeAfterExit$2$1 animatedVisibilityKt$AnimatedEnterExitImpl$shouldDisposeAfterExit$2$1 = new AnimatedVisibilityKt$AnimatedEnterExitImpl$shouldDisposeAfterExit$2$1(this.$childTransition, this.$shouldDisposeBlockUpdated$delegate, dVar);
        animatedVisibilityKt$AnimatedEnterExitImpl$shouldDisposeAfterExit$2$1.L$0 = obj;
        return animatedVisibilityKt$AnimatedEnterExitImpl$shouldDisposeAfterExit$2$1;
    }

    @Override // r7.p
    public final Object invoke(ProduceStateScope<Boolean> produceStateScope, d<? super t0> dVar) {
        return ((AnimatedVisibilityKt$AnimatedEnterExitImpl$shouldDisposeAfterExit$2$1) create(produceStateScope, dVar)).invokeSuspend(t0.f22605a);
    }

    @Override // f7.a
    public final Object invokeSuspend(Object obj) {
        int i10 = this.label;
        if (i10 == 0) {
            c.G(obj);
            final ProduceStateScope produceStateScope = (ProduceStateScope) this.L$0;
            na.j jVarSnapshotFlow = SnapshotStateKt.snapshotFlow(new AnonymousClass1(this.$childTransition));
            final Transition<EnterExitState> transition = this.$childTransition;
            final State<p<EnterExitState, EnterExitState, Boolean>> state = this.$shouldDisposeBlockUpdated$delegate;
            k kVar = new k() { // from class: androidx.compose.animation.AnimatedVisibilityKt$AnimatedEnterExitImpl$shouldDisposeAfterExit$2$1.2
                @Override // na.k
                public /* bridge */ /* synthetic */ Object emit(Object obj2, d dVar) {
                    return emit(((Boolean) obj2).booleanValue(), (d<? super t0>) dVar);
                }

                public final Object emit(boolean z, d<? super t0> dVar) {
                    produceStateScope.setValue(Boolean.valueOf(z ? ((Boolean) AnimatedVisibilityKt.AnimatedEnterExitImpl$lambda$4(state).invoke(transition.getCurrentState(), transition.getTargetState())).booleanValue() : false));
                    return t0.f22605a;
                }
            };
            this.label = 1;
            Object objCollect = jVarSnapshotFlow.collect(kVar, this);
            e7.a aVar = e7.a.f15033i;
            if (objCollect == aVar) {
                return aVar;
            }
        } else {
            if (i10 != 1) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            c.G(obj);
        }
        return t0.f22605a;
    }
}
