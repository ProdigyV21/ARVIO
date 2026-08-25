package androidx.compose.runtime;

import kotlin.Metadata;
import x6.t0;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0007\bg\u0018\u0000*\u0004\b\u0000\u0010\u00012\b\u0012\u0004\u0012\u00028\u00000\u0002J\u0010\u0010\u0003\u001a\u00028\u0000H¦\u0002¢\u0006\u0004\b\u0003\u0010\u0004J\u001c\u0010\u0007\u001a\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00020\u00060\u0005H¦\u0002¢\u0006\u0004\b\u0007\u0010\bR\u001c\u0010\f\u001a\u00028\u00008&@&X¦\u000e¢\u0006\f\u001a\u0004\b\t\u0010\u0004\"\u0004\b\n\u0010\u000bø\u0001\u0000\u0082\u0002\u0006\n\u0004\b!0\u0001¨\u0006\rÀ\u0006\u0001"}, d2 = {"Landroidx/compose/runtime/MutableState;", "T", "Landroidx/compose/runtime/State;", "component1", "()Ljava/lang/Object;", "Lkotlin/Function1;", "Lx6/t0;", "component2", "()Lr7/l;", "getValue", "setValue", "(Ljava/lang/Object;)V", "value", "runtime_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
public interface MutableState<T> extends State<T> {
    T component1();

    r7.l<T, t0> component2();

    @Override // androidx.compose.runtime.State
    T getValue();

    void setValue(T t2);
}
