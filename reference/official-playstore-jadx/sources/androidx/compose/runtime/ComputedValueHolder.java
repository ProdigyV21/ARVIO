package androidx.compose.runtime;

import kotlin.Metadata;
import kotlin.jvm.internal.p;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000N\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0005\b\u0080\b\u0018\u0000*\u0004\b\u0000\u0010\u00012\b\u0012\u0004\u0012\u00028\u00000\u0002B \u0012\u0017\u0010\u0006\u001a\u0013\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00028\u00000\u0003¢\u0006\u0002\b\u0005¢\u0006\u0004\b\u0007\u0010\bJ\u0017\u0010\u000b\u001a\u00028\u00002\u0006\u0010\n\u001a\u00020\tH\u0016¢\u0006\u0004\b\u000b\u0010\fJ#\u0010\u0010\u001a\b\u0012\u0004\u0012\u00028\u00000\u000f2\f\u0010\u000e\u001a\b\u0012\u0004\u0012\u00028\u00000\rH\u0016¢\u0006\u0004\b\u0010\u0010\u0011J!\u0010\u0012\u001a\u0013\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00028\u00000\u0003¢\u0006\u0002\b\u0005HÆ\u0003¢\u0006\u0004\b\u0012\u0010\u0013J1\u0010\u0014\u001a\b\u0012\u0004\u0012\u00028\u00000\u00002\u0019\b\u0002\u0010\u0006\u001a\u0013\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00028\u00000\u0003¢\u0006\u0002\b\u0005HÆ\u0001¢\u0006\u0004\b\u0014\u0010\u0015J\u0010\u0010\u0017\u001a\u00020\u0016HÖ\u0001¢\u0006\u0004\b\u0017\u0010\u0018J\u0010\u0010\u001a\u001a\u00020\u0019HÖ\u0001¢\u0006\u0004\b\u001a\u0010\u001bJ\u001a\u0010\u001f\u001a\u00020\u001e2\b\u0010\u001d\u001a\u0004\u0018\u00010\u001cHÖ\u0003¢\u0006\u0004\b\u001f\u0010 R(\u0010\u0006\u001a\u0013\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00028\u00000\u0003¢\u0006\u0002\b\u00058\u0006¢\u0006\f\n\u0004\b\u0006\u0010!\u001a\u0004\b\"\u0010\u0013¨\u0006#"}, d2 = {"Landroidx/compose/runtime/ComputedValueHolder;", "T", "Landroidx/compose/runtime/ValueHolder;", "Lkotlin/Function1;", "Landroidx/compose/runtime/CompositionLocalAccessorScope;", "Lx6/n;", "compute", "<init>", "(Lr7/l;)V", "Landroidx/compose/runtime/PersistentCompositionLocalMap;", "map", "readValue", "(Landroidx/compose/runtime/PersistentCompositionLocalMap;)Ljava/lang/Object;", "Landroidx/compose/runtime/CompositionLocal;", "local", "Landroidx/compose/runtime/ProvidedValue;", "toProvided", "(Landroidx/compose/runtime/CompositionLocal;)Landroidx/compose/runtime/ProvidedValue;", "component1", "()Lr7/l;", "copy", "(Lr7/l;)Landroidx/compose/runtime/ComputedValueHolder;", "", "toString", "()Ljava/lang/String;", "", "hashCode", "()I", "", "other", "", "equals", "(Ljava/lang/Object;)Z", "Lr7/l;", "getCompute", "runtime_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
public final /* data */ class ComputedValueHolder<T> implements ValueHolder<T> {
    public static final int $stable = 0;
    private final r7.l<CompositionLocalAccessorScope, T> compute;

    /* JADX WARN: Multi-variable type inference failed */
    public ComputedValueHolder(r7.l<? super CompositionLocalAccessorScope, ? extends T> lVar) {
        this.compute = lVar;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static /* synthetic */ ComputedValueHolder copy$default(ComputedValueHolder computedValueHolder, r7.l lVar, int i10, Object obj) {
        if ((i10 & 1) != 0) {
            lVar = computedValueHolder.compute;
        }
        return computedValueHolder.copy(lVar);
    }

    public final r7.l<CompositionLocalAccessorScope, T> component1() {
        return this.compute;
    }

    public final ComputedValueHolder<T> copy(r7.l<? super CompositionLocalAccessorScope, ? extends T> compute) {
        return new ComputedValueHolder<>(compute);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        return (other instanceof ComputedValueHolder) && p.a(this.compute, ((ComputedValueHolder) other).compute);
    }

    public final r7.l<CompositionLocalAccessorScope, T> getCompute() {
        return this.compute;
    }

    public int hashCode() {
        return this.compute.hashCode();
    }

    @Override // androidx.compose.runtime.ValueHolder
    public T readValue(PersistentCompositionLocalMap map) {
        return (T) this.compute.invoke(map);
    }

    @Override // androidx.compose.runtime.ValueHolder
    public ProvidedValue<T> toProvided(CompositionLocal<T> local) {
        return new ProvidedValue<>(local, null, false, null, null, this.compute, false);
    }

    public String toString() {
        return "ComputedValueHolder(compute=" + this.compute + ')';
    }
}
