package androidx.compose.runtime;

import kotlin.Metadata;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\u001a\u0012\u0010\u0000\u001a\b\u0012\u0004\u0012\u0002H\u00020\u0001\"\u0004\b\u0000\u0010\u0002\u001a\u0012\u0010\u0003\u001a\b\u0012\u0004\u0012\u0002H\u00020\u0001\"\u0004\b\u0000\u0010\u0002\u001a\u0012\u0010\u0004\u001a\b\u0012\u0004\u0012\u0002H\u00020\u0001\"\u0004\b\u0000\u0010\u0002¨\u0006\u0005"}, d2 = {"neverEqualPolicy", "Landroidx/compose/runtime/SnapshotMutationPolicy;", "T", "referentialEqualityPolicy", "structuralEqualityPolicy", "runtime_release"}, k = 5, mv = {1, 8, 0}, xi = 48, xs = "androidx/compose/runtime/SnapshotStateKt")
final /* synthetic */ class SnapshotStateKt__SnapshotMutationPolicyKt {
    public static final <T> SnapshotMutationPolicy<T> neverEqualPolicy() {
        return NeverEqualPolicy.INSTANCE;
    }

    public static final <T> SnapshotMutationPolicy<T> referentialEqualityPolicy() {
        return ReferentialEqualityPolicy.INSTANCE;
    }

    public static final <T> SnapshotMutationPolicy<T> structuralEqualityPolicy() {
        return StructuralEqualityPolicy.INSTANCE;
    }
}
