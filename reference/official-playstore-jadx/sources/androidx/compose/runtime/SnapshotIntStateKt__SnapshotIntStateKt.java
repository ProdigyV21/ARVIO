package androidx.compose.runtime;

import kotlin.Metadata;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000(\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\u001a\u0017\u0010\u0003\u001a\u00020\u00022\u0006\u0010\u0001\u001a\u00020\u0000H\u0007¢\u0006\u0004\b\u0003\u0010\u0004\u001a*\u0010\n\u001a\u00020\u0000*\u00020\u00052\b\u0010\u0007\u001a\u0004\u0018\u00010\u00062\n\u0010\t\u001a\u0006\u0012\u0002\b\u00030\bH\u0086\n¢\u0006\u0004\b\n\u0010\u000b\u001a2\u0010\r\u001a\u00020\f*\u00020\u00022\b\u0010\u0007\u001a\u0004\u0018\u00010\u00062\n\u0010\t\u001a\u0006\u0012\u0002\b\u00030\b2\u0006\u0010\u0001\u001a\u00020\u0000H\u0086\n¢\u0006\u0004\b\r\u0010\u000e¨\u0006\u000f"}, d2 = {"", "value", "Landroidx/compose/runtime/MutableIntState;", "mutableIntStateOf", "(I)Landroidx/compose/runtime/MutableIntState;", "Landroidx/compose/runtime/IntState;", "", "thisObj", "Lkotlin/reflect/m;", "property", "getValue", "(Landroidx/compose/runtime/IntState;Ljava/lang/Object;Lkotlin/reflect/m;)I", "Lx6/t0;", "setValue", "(Landroidx/compose/runtime/MutableIntState;Ljava/lang/Object;Lkotlin/reflect/m;I)V", "runtime_release"}, k = 5, mv = {1, 8, 0}, xi = 48, xs = "androidx/compose/runtime/SnapshotIntStateKt")
final /* synthetic */ class SnapshotIntStateKt__SnapshotIntStateKt {
    public static final int getValue(IntState intState, Object obj, kotlin.reflect.m<?> mVar) {
        return intState.getIntValue();
    }

    public static final MutableIntState mutableIntStateOf(int i10) {
        return ActualAndroid_androidKt.createSnapshotMutableIntState(i10);
    }

    public static final void setValue(MutableIntState mutableIntState, Object obj, kotlin.reflect.m<?> mVar, int i10) {
        mutableIntState.setIntValue(i10);
    }
}
