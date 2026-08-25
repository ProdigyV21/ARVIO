package androidx.compose.runtime;

import kotlin.Metadata;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000(\n\u0002\u0010\t\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\u001a\u0017\u0010\u0003\u001a\u00020\u00022\u0006\u0010\u0001\u001a\u00020\u0000H\u0007¢\u0006\u0004\b\u0003\u0010\u0004\u001a*\u0010\n\u001a\u00020\u0000*\u00020\u00052\b\u0010\u0007\u001a\u0004\u0018\u00010\u00062\n\u0010\t\u001a\u0006\u0012\u0002\b\u00030\bH\u0086\n¢\u0006\u0004\b\n\u0010\u000b\u001a2\u0010\r\u001a\u00020\f*\u00020\u00022\b\u0010\u0007\u001a\u0004\u0018\u00010\u00062\n\u0010\t\u001a\u0006\u0012\u0002\b\u00030\b2\u0006\u0010\u0001\u001a\u00020\u0000H\u0086\n¢\u0006\u0004\b\r\u0010\u000e¨\u0006\u000f"}, d2 = {"", "value", "Landroidx/compose/runtime/MutableLongState;", "mutableLongStateOf", "(J)Landroidx/compose/runtime/MutableLongState;", "Landroidx/compose/runtime/LongState;", "", "thisObj", "Lkotlin/reflect/m;", "property", "getValue", "(Landroidx/compose/runtime/LongState;Ljava/lang/Object;Lkotlin/reflect/m;)J", "Lx6/t0;", "setValue", "(Landroidx/compose/runtime/MutableLongState;Ljava/lang/Object;Lkotlin/reflect/m;J)V", "runtime_release"}, k = 5, mv = {1, 8, 0}, xi = 48, xs = "androidx/compose/runtime/SnapshotLongStateKt")
final /* synthetic */ class SnapshotLongStateKt__SnapshotLongStateKt {
    public static final long getValue(LongState longState, Object obj, kotlin.reflect.m<?> mVar) {
        return longState.getLongValue();
    }

    public static final MutableLongState mutableLongStateOf(long j10) {
        return ActualAndroid_androidKt.createSnapshotMutableLongState(j10);
    }

    public static final void setValue(MutableLongState mutableLongState, Object obj, kotlin.reflect.m<?> mVar, long j10) {
        mutableLongState.setLongValue(j10);
    }
}
