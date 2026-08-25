package androidx.compose.runtime;

import ka.k0;
import kotlin.Metadata;
import r7.p;
import x6.t0;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0003\u001a\u00020\u0002\"\u0004\b\u0000\u0010\u0000*\u00020\u0001H\u008a@¢\u0006\u0004\b\u0003\u0010\u0004"}, d2 = {"T", "Lka/k0;", "Lx6/t0;", "<anonymous>", "(Lka/k0;)V"}, k = 3, mv = {1, 8, 0})
@f7.e(c = "androidx.compose.runtime.SnapshotStateKt__ProduceStateKt$produceState$1$1", f = "ProduceState.kt", l = {83}, m = "invokeSuspend")
public final class SnapshotStateKt__ProduceStateKt$produceState$1$1 extends f7.j implements p<k0, d7.d<? super t0>, Object> {
    final /* synthetic */ p<ProduceStateScope<T>, d7.d<? super t0>, Object> $producer;
    final /* synthetic */ MutableState<T> $result;
    private /* synthetic */ Object L$0;
    int label;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    /* JADX WARN: Multi-variable type inference failed */
    public SnapshotStateKt__ProduceStateKt$produceState$1$1(p<? super ProduceStateScope<T>, ? super d7.d<? super t0>, ? extends Object> pVar, MutableState<T> mutableState, d7.d<? super SnapshotStateKt__ProduceStateKt$produceState$1$1> dVar) {
        super(2, dVar);
        this.$producer = pVar;
        this.$result = mutableState;
    }

    @Override // f7.a
    public final d7.d<t0> create(Object obj, d7.d<?> dVar) {
        SnapshotStateKt__ProduceStateKt$produceState$1$1 snapshotStateKt__ProduceStateKt$produceState$1$1 = new SnapshotStateKt__ProduceStateKt$produceState$1$1(this.$producer, this.$result, dVar);
        snapshotStateKt__ProduceStateKt$produceState$1$1.L$0 = obj;
        return snapshotStateKt__ProduceStateKt$produceState$1$1;
    }

    @Override // f7.a
    public final Object invokeSuspend(Object obj) {
        int i10 = this.label;
        if (i10 == 0) {
            k2.c.G(obj);
            k0 k0Var = (k0) this.L$0;
            p<ProduceStateScope<T>, d7.d<? super t0>, Object> pVar = this.$producer;
            ProduceStateScopeImpl produceStateScopeImpl = new ProduceStateScopeImpl(this.$result, k0Var.getCoroutineContext());
            this.label = 1;
            Object objInvoke = pVar.invoke(produceStateScopeImpl, this);
            e7.a aVar = e7.a.f15033i;
            if (objInvoke == aVar) {
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
        return ((SnapshotStateKt__ProduceStateKt$produceState$1$1) create(k0Var, dVar)).invokeSuspend(t0.f22605a);
    }
}
