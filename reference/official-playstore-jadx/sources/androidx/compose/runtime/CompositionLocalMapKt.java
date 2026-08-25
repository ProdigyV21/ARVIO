package androidx.compose.runtime;

import androidx.compose.runtime.external.kotlinx.collections.immutable.PersistentMap;
import androidx.compose.runtime.internal.PersistentCompositionLocalMapKt;
import java.util.Map;
import kotlin.Metadata;
import x6.t0;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010%\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0010\u0011\n\u0002\u0018\u0002\n\u0002\b\u0006\u001aD\u0010\b\u001a\u00020\u0000*\u00020\u00002.\u0010\u0007\u001a*\u0012 \u0012\u001e\u0012\f\u0012\n\u0012\u0006\u0012\u0004\u0018\u00010\u00040\u0003\u0012\f\u0012\n\u0012\u0006\u0012\u0004\u0018\u00010\u00040\u00050\u0002\u0012\u0004\u0012\u00020\u00060\u0001H\u0080\b¢\u0006\u0004\b\b\u0010\t\u001a'\u0010\r\u001a\u00020\f\"\u0004\b\u0000\u0010\n*\u00020\u00002\f\u0010\u000b\u001a\b\u0012\u0004\u0012\u00028\u00000\u0003H\u0000¢\u0006\u0004\b\r\u0010\u000e\u001a'\u0010\u000f\u001a\u00028\u0000\"\u0004\b\u0000\u0010\n*\u00020\u00002\f\u0010\u000b\u001a\b\u0012\u0004\u0012\u00028\u00000\u0003H\u0000¢\u0006\u0004\b\u000f\u0010\u0010\u001a5\u0010\u0016\u001a\u00020\u00002\u0012\u0010\u0013\u001a\u000e\u0012\n\b\u0001\u0012\u0006\u0012\u0002\b\u00030\u00120\u00112\u0006\u0010\u0014\u001a\u00020\u00002\b\b\u0002\u0010\u0015\u001a\u00020\u0000H\u0000¢\u0006\u0004\b\u0016\u0010\u0017¨\u0006\u0018"}, d2 = {"Landroidx/compose/runtime/PersistentCompositionLocalMap;", "Lkotlin/Function1;", "", "Landroidx/compose/runtime/CompositionLocal;", "", "Landroidx/compose/runtime/ValueHolder;", "Lx6/t0;", "mutator", "mutate", "(Landroidx/compose/runtime/PersistentCompositionLocalMap;Lr7/l;)Landroidx/compose/runtime/PersistentCompositionLocalMap;", "T", "key", "", "contains", "(Landroidx/compose/runtime/PersistentCompositionLocalMap;Landroidx/compose/runtime/CompositionLocal;)Z", "read", "(Landroidx/compose/runtime/PersistentCompositionLocalMap;Landroidx/compose/runtime/CompositionLocal;)Ljava/lang/Object;", "", "Landroidx/compose/runtime/ProvidedValue;", "values", "parentScope", "previous", "updateCompositionMap", "([Landroidx/compose/runtime/ProvidedValue;Landroidx/compose/runtime/PersistentCompositionLocalMap;Landroidx/compose/runtime/PersistentCompositionLocalMap;)Landroidx/compose/runtime/PersistentCompositionLocalMap;", "runtime_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class CompositionLocalMapKt {
    public static final <T> boolean contains(PersistentCompositionLocalMap persistentCompositionLocalMap, CompositionLocal<T> compositionLocal) {
        return persistentCompositionLocalMap.containsKey(compositionLocal);
    }

    /* JADX WARN: Type inference failed for: r0v2, types: [androidx.compose.runtime.PersistentCompositionLocalMap] */
    public static final PersistentCompositionLocalMap mutate(PersistentCompositionLocalMap persistentCompositionLocalMap, r7.l<? super Map<CompositionLocal<Object>, ValueHolder<Object>>, t0> lVar) {
        PersistentMap.Builder<CompositionLocal<Object>, ValueHolder<Object>> builderBuilder2 = persistentCompositionLocalMap.builder2();
        lVar.invoke(builderBuilder2);
        return builderBuilder2.build2();
    }

    public static final <T> T read(PersistentCompositionLocalMap persistentCompositionLocalMap, CompositionLocal<T> compositionLocal) {
        ValueHolder defaultValueHolder$runtime_release = persistentCompositionLocalMap.get((Object) compositionLocal);
        if (defaultValueHolder$runtime_release == null) {
            defaultValueHolder$runtime_release = compositionLocal.getDefaultValueHolder$runtime_release();
        }
        return (T) defaultValueHolder$runtime_release.readValue(persistentCompositionLocalMap);
    }

    /* JADX WARN: Type inference failed for: r6v1, types: [androidx.compose.runtime.PersistentCompositionLocalMap] */
    public static final PersistentCompositionLocalMap updateCompositionMap(ProvidedValue<?>[] providedValueArr, PersistentCompositionLocalMap persistentCompositionLocalMap, PersistentCompositionLocalMap persistentCompositionLocalMap2) {
        PersistentMap.Builder<CompositionLocal<Object>, ValueHolder<Object>> builderBuilder2 = PersistentCompositionLocalMapKt.persistentCompositionLocalHashMapOf().builder2();
        for (ProvidedValue<?> providedValue : providedValueArr) {
            ProvidableCompositionLocal providableCompositionLocal = (ProvidableCompositionLocal) providedValue.getCompositionLocal();
            if (providedValue.getCanOverride() || !contains(persistentCompositionLocalMap, providableCompositionLocal)) {
                builderBuilder2.put(providableCompositionLocal, providableCompositionLocal.updatedStateOf$runtime_release(providedValue, (ValueHolder) persistentCompositionLocalMap2.get((Object) providableCompositionLocal)));
            }
        }
        return builderBuilder2.build2();
    }

    public static /* synthetic */ PersistentCompositionLocalMap updateCompositionMap$default(ProvidedValue[] providedValueArr, PersistentCompositionLocalMap persistentCompositionLocalMap, PersistentCompositionLocalMap persistentCompositionLocalMap2, int i10, Object obj) {
        if ((i10 & 4) != 0) {
            persistentCompositionLocalMap2 = PersistentCompositionLocalMapKt.persistentCompositionLocalHashMapOf();
        }
        return updateCompositionMap(providedValueArr, persistentCompositionLocalMap, persistentCompositionLocalMap2);
    }
}
