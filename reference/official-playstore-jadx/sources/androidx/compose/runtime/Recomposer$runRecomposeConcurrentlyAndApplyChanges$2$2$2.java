package androidx.compose.runtime;

import ka.k0;
import kotlin.Metadata;
import r7.p;
import x6.t0;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0002\u001a\u00020\u0001*\u00020\u0000H\u008a@¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"Lka/k0;", "Lx6/t0;", "<anonymous>", "(Lka/k0;)V"}, k = 3, mv = {1, 8, 0})
@f7.e(c = "androidx.compose.runtime.Recomposer$runRecomposeConcurrentlyAndApplyChanges$2$2$2", f = "Recomposer.kt", l = {}, m = "invokeSuspend")
public final class Recomposer$runRecomposeConcurrentlyAndApplyChanges$2$2$2 extends f7.j implements p<k0, d7.d<? super t0>, Object> {
    final /* synthetic */ ControlledComposition $composition;
    int label;
    final /* synthetic */ Recomposer this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public Recomposer$runRecomposeConcurrentlyAndApplyChanges$2$2$2(Recomposer recomposer, ControlledComposition controlledComposition, d7.d<? super Recomposer$runRecomposeConcurrentlyAndApplyChanges$2$2$2> dVar) {
        super(2, dVar);
        this.this$0 = recomposer;
        this.$composition = controlledComposition;
    }

    @Override // f7.a
    public final d7.d<t0> create(Object obj, d7.d<?> dVar) {
        return new Recomposer$runRecomposeConcurrentlyAndApplyChanges$2$2$2(this.this$0, this.$composition, dVar);
    }

    @Override // f7.a
    public final Object invokeSuspend(Object obj) {
        ka.k kVarDeriveStateLocked;
        if (this.label != 0) {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        k2.c.G(obj);
        ControlledComposition controlledCompositionPerformRecompose = this.this$0.performRecompose(this.$composition, null);
        Object obj2 = this.this$0.stateLock;
        Recomposer recomposer = this.this$0;
        synchronized (obj2) {
            if (controlledCompositionPerformRecompose != null) {
                try {
                    recomposer.compositionsAwaitingApply.add(controlledCompositionPerformRecompose);
                } catch (Throwable th) {
                    throw th;
                }
            }
            recomposer.concurrentCompositionsOutstanding--;
            kVarDeriveStateLocked = recomposer.deriveStateLocked();
        }
        if (kVarDeriveStateLocked != null) {
            kVarDeriveStateLocked.resumeWith(t0.f22605a);
        }
        return t0.f22605a;
    }

    @Override // r7.p
    public final Object invoke(k0 k0Var, d7.d<? super t0> dVar) {
        return ((Recomposer$runRecomposeConcurrentlyAndApplyChanges$2$2$2) create(k0Var, dVar)).invokeSuspend(t0.f22605a);
    }
}
