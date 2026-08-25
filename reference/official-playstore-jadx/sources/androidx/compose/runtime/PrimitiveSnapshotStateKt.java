package androidx.compose.runtime;

import kotlin.Metadata;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"androidx/compose/runtime/PrimitiveSnapshotStateKt__SnapshotFloatStateKt"}, k = 4, mv = {1, 8, 0}, xi = 48)
public final class PrimitiveSnapshotStateKt {
    public static final float getValue(FloatState floatState, Object obj, kotlin.reflect.m<?> mVar) {
        return PrimitiveSnapshotStateKt__SnapshotFloatStateKt.getValue(floatState, obj, mVar);
    }

    public static final MutableFloatState mutableFloatStateOf(float f10) {
        return PrimitiveSnapshotStateKt__SnapshotFloatStateKt.mutableFloatStateOf(f10);
    }

    public static final void setValue(MutableFloatState mutableFloatState, Object obj, kotlin.reflect.m<?> mVar, float f10) {
        PrimitiveSnapshotStateKt__SnapshotFloatStateKt.setValue(mutableFloatState, obj, mVar, f10);
    }
}
