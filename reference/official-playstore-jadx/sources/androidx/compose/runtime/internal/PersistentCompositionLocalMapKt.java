package androidx.compose.runtime.internal;

import androidx.compose.runtime.CompositionLocal;
import androidx.compose.runtime.PersistentCompositionLocalMap;
import androidx.compose.runtime.ValueHolder;
import androidx.compose.runtime.external.kotlinx.collections.immutable.PersistentMap;
import kotlin.Metadata;
import kotlin.collections.h0;
import x6.x;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0011\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u001a\u000f\u0010\u0001\u001a\u00020\u0000H\u0000¢\u0006\u0004\b\u0001\u0010\u0002\u001a[\u0010\u0001\u001a\u00020\t2J\u0010\b\u001a&\u0012\"\b\u0001\u0012\u001e\u0012\f\u0012\n\u0012\u0006\u0012\u0004\u0018\u00010\u00060\u0005\u0012\f\u0012\n\u0012\u0006\u0012\u0004\u0018\u00010\u00060\u00070\u00040\u0003\"\u001e\u0012\f\u0012\n\u0012\u0006\u0012\u0004\u0018\u00010\u00060\u0005\u0012\f\u0012\n\u0012\u0006\u0012\u0004\u0018\u00010\u00060\u00070\u0004H\u0000¢\u0006\u0004\b\u0001\u0010\n¨\u0006\u000b"}, d2 = {"Landroidx/compose/runtime/internal/PersistentCompositionLocalHashMap;", "persistentCompositionLocalHashMapOf", "()Landroidx/compose/runtime/internal/PersistentCompositionLocalHashMap;", "", "Lx6/x;", "Landroidx/compose/runtime/CompositionLocal;", "", "Landroidx/compose/runtime/ValueHolder;", "pairs", "Landroidx/compose/runtime/PersistentCompositionLocalMap;", "([Lx6/x;)Landroidx/compose/runtime/PersistentCompositionLocalMap;", "runtime_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class PersistentCompositionLocalMapKt {
    public static final PersistentCompositionLocalHashMap persistentCompositionLocalHashMapOf() {
        return PersistentCompositionLocalHashMap.INSTANCE.getEmpty();
    }

    /* JADX WARN: Type inference failed for: r1v1, types: [androidx.compose.runtime.PersistentCompositionLocalMap] */
    public static final PersistentCompositionLocalMap persistentCompositionLocalHashMapOf(x... xVarArr) {
        PersistentMap.Builder<CompositionLocal<Object>, ValueHolder<Object>> builderBuilder2 = PersistentCompositionLocalHashMap.INSTANCE.getEmpty().builder2();
        h0.y0(builderBuilder2, xVarArr);
        return builderBuilder2.build2();
    }
}
