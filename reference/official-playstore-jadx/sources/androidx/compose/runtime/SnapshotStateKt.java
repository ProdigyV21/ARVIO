package androidx.compose.runtime;

import androidx.compose.runtime.collection.MutableVector;
import androidx.compose.runtime.snapshots.SnapshotStateList;
import androidx.compose.runtime.snapshots.SnapshotStateMap;
import java.util.Collection;
import kotlin.Metadata;
import na.h1;
import r7.p;
import x6.t0;
import x6.x;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"androidx/compose/runtime/SnapshotStateKt__DerivedStateKt", "androidx/compose/runtime/SnapshotStateKt__ProduceStateKt", "androidx/compose/runtime/SnapshotStateKt__SnapshotFlowKt", "androidx/compose/runtime/SnapshotStateKt__SnapshotMutationPolicyKt", "androidx/compose/runtime/SnapshotStateKt__SnapshotStateKt"}, k = 4, mv = {1, 8, 0}, xi = 48)
public final class SnapshotStateKt {
    public static final <T extends R, R> State<R> collectAsState(na.j<? extends T> jVar, R r4, d7.j jVar2, Composer composer, int i10, int i11) {
        return SnapshotStateKt__SnapshotFlowKt.collectAsState(jVar, r4, jVar2, composer, i10, i11);
    }

    public static final MutableVector<DerivedStateObserver> derivedStateObservers() {
        return SnapshotStateKt__DerivedStateKt.derivedStateObservers();
    }

    public static final <T> State<T> derivedStateOf(SnapshotMutationPolicy<T> snapshotMutationPolicy, r7.a<? extends T> aVar) {
        return SnapshotStateKt__DerivedStateKt.derivedStateOf(snapshotMutationPolicy, aVar);
    }

    public static final <T> T getValue(State<? extends T> state, Object obj, kotlin.reflect.m<?> mVar) {
        return (T) SnapshotStateKt__SnapshotStateKt.getValue(state, obj, mVar);
    }

    public static final <T> SnapshotStateList<T> mutableStateListOf() {
        return SnapshotStateKt__SnapshotStateKt.mutableStateListOf();
    }

    public static final <K, V> SnapshotStateMap<K, V> mutableStateMapOf() {
        return SnapshotStateKt__SnapshotStateKt.mutableStateMapOf();
    }

    public static final <T> MutableState<T> mutableStateOf(T t2, SnapshotMutationPolicy<T> snapshotMutationPolicy) {
        return SnapshotStateKt__SnapshotStateKt.mutableStateOf(t2, snapshotMutationPolicy);
    }

    public static final <T> SnapshotMutationPolicy<T> neverEqualPolicy() {
        return SnapshotStateKt__SnapshotMutationPolicyKt.neverEqualPolicy();
    }

    public static final <R> void observeDerivedStateRecalculations(DerivedStateObserver derivedStateObserver, r7.a<? extends R> aVar) {
        SnapshotStateKt__DerivedStateKt.observeDerivedStateRecalculations(derivedStateObserver, aVar);
    }

    public static final <T> State<T> produceState(T t2, Object obj, Object obj2, Object obj3, p<? super ProduceStateScope<T>, ? super d7.d<? super t0>, ? extends Object> pVar, Composer composer, int i10) {
        return SnapshotStateKt__ProduceStateKt.produceState(t2, obj, obj2, obj3, pVar, composer, i10);
    }

    public static final <T> SnapshotMutationPolicy<T> referentialEqualityPolicy() {
        return SnapshotStateKt__SnapshotMutationPolicyKt.referentialEqualityPolicy();
    }

    public static final <T> State<T> rememberUpdatedState(T t2, Composer composer, int i10) {
        return SnapshotStateKt__SnapshotStateKt.rememberUpdatedState(t2, composer, i10);
    }

    public static final <T> void setValue(MutableState<T> mutableState, Object obj, kotlin.reflect.m<?> mVar, T t2) {
        SnapshotStateKt__SnapshotStateKt.setValue(mutableState, obj, mVar, t2);
    }

    public static final <T> na.j<T> snapshotFlow(r7.a<? extends T> aVar) {
        return SnapshotStateKt__SnapshotFlowKt.snapshotFlow(aVar);
    }

    public static final <T> SnapshotMutationPolicy<T> structuralEqualityPolicy() {
        return SnapshotStateKt__SnapshotMutationPolicyKt.structuralEqualityPolicy();
    }

    public static final <T> SnapshotStateList<T> toMutableStateList(Collection<? extends T> collection) {
        return SnapshotStateKt__SnapshotStateKt.toMutableStateList(collection);
    }

    public static final <K, V> SnapshotStateMap<K, V> toMutableStateMap(Iterable<? extends x> iterable) {
        return SnapshotStateKt__SnapshotStateKt.toMutableStateMap(iterable);
    }

    public static final <T> State<T> collectAsState(h1<? extends T> h1Var, d7.j jVar, Composer composer, int i10, int i11) {
        return SnapshotStateKt__SnapshotFlowKt.collectAsState(h1Var, jVar, composer, i10, i11);
    }

    public static final <T> State<T> derivedStateOf(r7.a<? extends T> aVar) {
        return SnapshotStateKt__DerivedStateKt.derivedStateOf(aVar);
    }

    public static final <T> SnapshotStateList<T> mutableStateListOf(T... tArr) {
        return SnapshotStateKt__SnapshotStateKt.mutableStateListOf(tArr);
    }

    public static final <K, V> SnapshotStateMap<K, V> mutableStateMapOf(x... xVarArr) {
        return SnapshotStateKt__SnapshotStateKt.mutableStateMapOf(xVarArr);
    }

    public static final <T> State<T> produceState(T t2, Object obj, Object obj2, p<? super ProduceStateScope<T>, ? super d7.d<? super t0>, ? extends Object> pVar, Composer composer, int i10) {
        return SnapshotStateKt__ProduceStateKt.produceState(t2, obj, obj2, pVar, composer, i10);
    }

    public static final <T> State<T> produceState(T t2, Object obj, p<? super ProduceStateScope<T>, ? super d7.d<? super t0>, ? extends Object> pVar, Composer composer, int i10) {
        return SnapshotStateKt__ProduceStateKt.produceState(t2, obj, pVar, composer, i10);
    }

    public static final <T> State<T> produceState(T t2, p<? super ProduceStateScope<T>, ? super d7.d<? super t0>, ? extends Object> pVar, Composer composer, int i10) {
        return SnapshotStateKt__ProduceStateKt.produceState(t2, pVar, composer, i10);
    }

    public static final <T> State<T> produceState(T t2, Object[] objArr, p<? super ProduceStateScope<T>, ? super d7.d<? super t0>, ? extends Object> pVar, Composer composer, int i10) {
        return SnapshotStateKt__ProduceStateKt.produceState((Object) t2, objArr, (p) pVar, composer, i10);
    }
}
