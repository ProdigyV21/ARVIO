package androidx.compose.runtime;

import androidx.media3.exoplayer.upstream.CmcdData;
import kotlin.Metadata;
import kotlin.jvm.internal.p;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\bÂ\u0002\u0018\u00002\n\u0012\u0006\u0012\u0004\u0018\u00010\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0003J\u001c\u0010\u0004\u001a\u00020\u00052\b\u0010\u0006\u001a\u0004\u0018\u00010\u00022\b\u0010\u0007\u001a\u0004\u0018\u00010\u0002H\u0016J\b\u0010\b\u001a\u00020\tH\u0016¨\u0006\n"}, d2 = {"Landroidx/compose/runtime/StructuralEqualityPolicy;", "Landroidx/compose/runtime/SnapshotMutationPolicy;", "", "()V", "equivalent", "", CmcdData.OBJECT_TYPE_AUDIO_ONLY, "b", "toString", "", "runtime_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
final class StructuralEqualityPolicy implements SnapshotMutationPolicy<Object> {
    public static final StructuralEqualityPolicy INSTANCE = new StructuralEqualityPolicy();

    private StructuralEqualityPolicy() {
    }

    @Override // androidx.compose.runtime.SnapshotMutationPolicy
    public boolean equivalent(Object a10, Object b10) {
        return p.a(a10, b10);
    }

    @Override // androidx.compose.runtime.SnapshotMutationPolicy
    public final /* synthetic */ Object merge(Object obj, Object obj2, Object obj3) {
        return n.a(this, obj, obj2, obj3);
    }

    public String toString() {
        return "StructuralEqualityPolicy";
    }
}
