package androidx.compose.runtime;

import kotlin.Metadata;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"androidx/compose/runtime/SnapshotDoubleStateKt__SnapshotDoubleStateKt"}, k = 4, mv = {1, 8, 0}, xi = 48)
public final class SnapshotDoubleStateKt {
    public static final double getValue(DoubleState doubleState, Object obj, kotlin.reflect.m<?> mVar) {
        return SnapshotDoubleStateKt__SnapshotDoubleStateKt.getValue(doubleState, obj, mVar);
    }

    public static final MutableDoubleState mutableDoubleStateOf(double d4) {
        return SnapshotDoubleStateKt__SnapshotDoubleStateKt.mutableDoubleStateOf(d4);
    }

    public static final void setValue(MutableDoubleState mutableDoubleState, Object obj, kotlin.reflect.m<?> mVar, double d4) {
        SnapshotDoubleStateKt__SnapshotDoubleStateKt.setValue(mutableDoubleState, obj, mVar, d4);
    }
}
