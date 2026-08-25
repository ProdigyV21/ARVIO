package androidx.compose.runtime.snapshots;

import d7.h;
import d7.i;
import d7.j;
import kotlin.Metadata;
import r7.p;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\bg\u0018\u0000 \u00022\u00020\u0001:\u0001\u0002ø\u0001\u0000\u0082\u0002\u0006\n\u0004\b!0\u0001¨\u0006\u0003À\u0006\u0001"}, d2 = {"Landroidx/compose/runtime/snapshots/SnapshotContextElement;", "Ld7/j$a;", "Key", "runtime_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
public interface SnapshotContextElement extends j.a {

    /* JADX INFO: renamed from: Key, reason: from kotlin metadata */
    public static final Companion INSTANCE = Companion.$$INSTANCE;

    @Metadata(k = 3, mv = {1, 8, 0}, xi = 48)
    public static final class DefaultImpls {
        public static <R> R fold(SnapshotContextElement snapshotContextElement, R r4, p<? super R, ? super j.a, ? extends R> pVar) {
            return (R) pVar.invoke(r4, snapshotContextElement);
        }

        public static <E extends j.a> E get(SnapshotContextElement snapshotContextElement, j.b<E> bVar) {
            return (E) i.a(snapshotContextElement, bVar);
        }

        public static j minusKey(SnapshotContextElement snapshotContextElement, j.b<?> bVar) {
            return i.b(snapshotContextElement, bVar);
        }

        public static j plus(SnapshotContextElement snapshotContextElement, j jVar) {
            return h.a(snapshotContextElement, jVar);
        }
    }

    /* JADX INFO: renamed from: androidx.compose.runtime.snapshots.SnapshotContextElement$Key, reason: from kotlin metadata */
    @Metadata(d1 = {"\u0000\u0010\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0086\u0003\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0003\u0010\u0004¨\u0006\u0005"}, d2 = {"Landroidx/compose/runtime/snapshots/SnapshotContextElement$Key;", "Ld7/j$b;", "Landroidx/compose/runtime/snapshots/SnapshotContextElement;", "<init>", "()V", "runtime_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
    public static final class Companion implements j.b<SnapshotContextElement> {
        static final /* synthetic */ Companion $$INSTANCE = new Companion();

        private Companion() {
        }
    }

    @Override // d7.j
    /* synthetic */ Object fold(Object obj, p pVar);

    @Override // d7.j
    /* synthetic */ j.a get(j.b bVar);

    @Override // d7.j.a
    /* synthetic */ j.b getKey();

    @Override // d7.j
    /* synthetic */ j minusKey(j.b bVar);

    @Override // d7.j
    /* synthetic */ j plus(j jVar);
}
