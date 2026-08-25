package androidx.compose.runtime;

import kotlin.Metadata;
import kotlin.jvm.internal.r;
import r7.p;
import x6.t0;

/* JADX INFO: Add missing generic type declarations: [T] */
/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\u0010\u0005\u001a\u00020\u0001\"\u0004\b\u0000\u0010\u0000*\u00028\u00002\u0006\u0010\u0002\u001a\u00020\u0001H\n¢\u0006\u0004\b\u0003\u0010\u0004"}, d2 = {"T", "Lx6/t0;", "it", "invoke", "(Ljava/lang/Object;Lx6/t0;)V", "<anonymous>"}, k = 3, mv = {1, 8, 0})
public final class Updater$reconcile$1<T> extends r implements p<T, t0, t0> {
    final /* synthetic */ r7.l<T, t0> $block;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    /* JADX WARN: Multi-variable type inference failed */
    public Updater$reconcile$1(r7.l<? super T, t0> lVar) {
        super(2);
        this.$block = lVar;
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // r7.p
    public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
        invoke(obj, (t0) obj2);
        return t0.f22605a;
    }

    public final void invoke(T t2, t0 t0Var) {
        this.$block.invoke(t2);
    }
}
