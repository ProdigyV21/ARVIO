package androidx.compose.runtime;

import kotlin.Metadata;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u00000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\b\b'\u0018\u0000*\u0004\b\u0000\u0010\u00012\b\u0012\u0004\u0012\u00028\u00000\u0002B\u0017\b\u0000\u0012\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00028\u00000\u0003¢\u0006\u0004\b\u0005\u0010\u0006J#\u0010\n\u001a\b\u0012\u0004\u0012\u00028\u00000\t2\f\u0010\b\u001a\b\u0012\u0004\u0012\u00028\u00000\u0007H\u0002¢\u0006\u0004\b\n\u0010\u000bJ\u001d\u0010\u000e\u001a\b\u0012\u0004\u0012\u00028\u00000\u00072\u0006\u0010\b\u001a\u00028\u0000H ¢\u0006\u0004\b\f\u0010\rJ\u001e\u0010\u000f\u001a\b\u0012\u0004\u0012\u00028\u00000\u00072\u0006\u0010\b\u001a\u00028\u0000H\u0086\u0004¢\u0006\u0004\b\u000f\u0010\rJ\u001e\u0010\u0010\u001a\b\u0012\u0004\u0012\u00028\u00000\u00072\u0006\u0010\b\u001a\u00028\u0000H\u0086\u0004¢\u0006\u0004\b\u0010\u0010\rJ/\u0010\u0015\u001a\b\u0012\u0004\u0012\u00028\u00000\u00072\u0017\u0010\u0014\u001a\u0013\u0012\u0004\u0012\u00020\u0012\u0012\u0004\u0012\u00028\u00000\u0011¢\u0006\u0002\b\u0013H\u0086\u0004¢\u0006\u0004\b\u0015\u0010\u0016J3\u0010\u001a\u001a\b\u0012\u0004\u0012\u00028\u00000\t2\f\u0010\b\u001a\b\u0012\u0004\u0012\u00028\u00000\u00072\u000e\u0010\u0017\u001a\n\u0012\u0004\u0012\u00028\u0000\u0018\u00010\tH\u0010¢\u0006\u0004\b\u0018\u0010\u0019¨\u0006\u001b"}, d2 = {"Landroidx/compose/runtime/ProvidableCompositionLocal;", "T", "Landroidx/compose/runtime/CompositionLocal;", "Lkotlin/Function0;", "defaultFactory", "<init>", "(Lr7/a;)V", "Landroidx/compose/runtime/ProvidedValue;", "value", "Landroidx/compose/runtime/ValueHolder;", "valueHolderOf", "(Landroidx/compose/runtime/ProvidedValue;)Landroidx/compose/runtime/ValueHolder;", "defaultProvidedValue$runtime_release", "(Ljava/lang/Object;)Landroidx/compose/runtime/ProvidedValue;", "defaultProvidedValue", "provides", "providesDefault", "Lkotlin/Function1;", "Landroidx/compose/runtime/CompositionLocalAccessorScope;", "Lx6/n;", "compute", "providesComputed", "(Lr7/l;)Landroidx/compose/runtime/ProvidedValue;", "previous", "updatedStateOf$runtime_release", "(Landroidx/compose/runtime/ProvidedValue;Landroidx/compose/runtime/ValueHolder;)Landroidx/compose/runtime/ValueHolder;", "updatedStateOf", "runtime_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
public abstract class ProvidableCompositionLocal<T> extends CompositionLocal<T> {
    public static final int $stable = 0;

    public ProvidableCompositionLocal(r7.a<? extends T> aVar) {
        super(aVar, null);
    }

    private final ValueHolder<T> valueHolderOf(ProvidedValue<T> value) {
        if (!value.getIsDynamic()) {
            return value.getCompute$runtime_release() != null ? new ComputedValueHolder(value.getCompute$runtime_release()) : value.getState$runtime_release() != null ? new DynamicValueHolder(value.getState$runtime_release()) : new StaticValueHolder(value.getEffectiveValue$runtime_release());
        }
        MutableState<T> state$runtime_release = value.getState$runtime_release();
        if (state$runtime_release == null) {
            T value2 = value.getValue();
            SnapshotMutationPolicy<T> mutationPolicy$runtime_release = value.getMutationPolicy$runtime_release();
            if (mutationPolicy$runtime_release == null) {
                mutationPolicy$runtime_release = SnapshotStateKt.structuralEqualityPolicy();
            }
            state$runtime_release = SnapshotStateKt.mutableStateOf(value2, mutationPolicy$runtime_release);
        }
        return new DynamicValueHolder(state$runtime_release);
    }

    public abstract ProvidedValue<T> defaultProvidedValue$runtime_release(T value);

    public final ProvidedValue<T> provides(T value) {
        return defaultProvidedValue$runtime_release(value);
    }

    public final ProvidedValue<T> providesComputed(r7.l<? super CompositionLocalAccessorScope, ? extends T> compute) {
        return new ProvidedValue<>(this, null, false, null, null, compute, false);
    }

    public final ProvidedValue<T> providesDefault(T value) {
        return defaultProvidedValue$runtime_release(value).ifNotAlreadyProvided$runtime_release();
    }

    /* JADX WARN: Removed duplicated region for block: B:13:0x0034 A[PHI: r5
      0x0034: PHI (r5v2 java.lang.Object) = (r5v5 java.lang.Object), (r5v6 java.lang.Object) binds: [B:17:0x0044, B:12:0x0032] A[DONT_GENERATE, DONT_INLINE]] */
    @Override // androidx.compose.runtime.CompositionLocal
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public androidx.compose.runtime.ValueHolder<T> updatedStateOf$runtime_release(androidx.compose.runtime.ProvidedValue<T> r4, androidx.compose.runtime.ValueHolder<T> r5) {
        /*
            r3 = this;
            boolean r0 = r5 instanceof androidx.compose.runtime.DynamicValueHolder
            r1 = 0
            if (r0 == 0) goto L1a
            boolean r0 = r4.getIsDynamic()
            if (r0 == 0) goto L47
            r1 = r5
            androidx.compose.runtime.DynamicValueHolder r1 = (androidx.compose.runtime.DynamicValueHolder) r1
            androidx.compose.runtime.MutableState r5 = r1.getState()
            java.lang.Object r0 = r4.getEffectiveValue$runtime_release()
            r5.setValue(r0)
            goto L47
        L1a:
            boolean r0 = r5 instanceof androidx.compose.runtime.StaticValueHolder
            if (r0 == 0) goto L36
            boolean r0 = r4.isStatic$runtime_release()
            if (r0 == 0) goto L47
            java.lang.Object r0 = r4.getEffectiveValue$runtime_release()
            androidx.compose.runtime.StaticValueHolder r5 = (androidx.compose.runtime.StaticValueHolder) r5
            java.lang.Object r2 = r5.getValue()
            boolean r0 = kotlin.jvm.internal.p.a(r0, r2)
            if (r0 == 0) goto L47
        L34:
            r1 = r5
            goto L47
        L36:
            boolean r0 = r5 instanceof androidx.compose.runtime.ComputedValueHolder
            if (r0 == 0) goto L47
            r7.l r0 = r4.getCompute$runtime_release()
            androidx.compose.runtime.ComputedValueHolder r5 = (androidx.compose.runtime.ComputedValueHolder) r5
            r7.l r2 = r5.getCompute()
            if (r0 != r2) goto L47
            goto L34
        L47:
            if (r1 != 0) goto L4e
            androidx.compose.runtime.ValueHolder r4 = r3.valueHolderOf(r4)
            return r4
        L4e:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.runtime.ProvidableCompositionLocal.updatedStateOf$runtime_release(androidx.compose.runtime.ProvidedValue, androidx.compose.runtime.ValueHolder):androidx.compose.runtime.ValueHolder");
    }
}
