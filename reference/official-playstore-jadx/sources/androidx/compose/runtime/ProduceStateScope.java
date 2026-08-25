package androidx.compose.runtime;

import ka.k0;
import kotlin.Metadata;
import x6.t0;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0001\n\u0002\b\u0003\bf\u0018\u0000*\u0004\b\u0000\u0010\u00012\b\u0012\u0004\u0012\u00028\u00000\u00022\u00020\u0003J\u001e\u0010\b\u001a\u00020\u00072\f\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004H¦@¢\u0006\u0004\b\b\u0010\tø\u0001\u0000\u0082\u0002\u0006\n\u0004\b!0\u0001¨\u0006\nÀ\u0006\u0001"}, d2 = {"Landroidx/compose/runtime/ProduceStateScope;", "T", "Landroidx/compose/runtime/MutableState;", "Lka/k0;", "Lkotlin/Function0;", "Lx6/t0;", "onDispose", "", "awaitDispose", "(Lr7/a;Ld7/d;)Ljava/lang/Object;", "runtime_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
public interface ProduceStateScope<T> extends MutableState<T>, k0 {
    Object awaitDispose(r7.a<t0> aVar, d7.d<?> dVar);

    @Override // ka.k0
    /* synthetic */ d7.j getCoroutineContext();
}
