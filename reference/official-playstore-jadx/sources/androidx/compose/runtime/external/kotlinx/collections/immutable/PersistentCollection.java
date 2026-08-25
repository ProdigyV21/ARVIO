package androidx.compose.runtime.external.kotlinx.collections.immutable;

import java.util.Collection;
import kotlin.Metadata;
import r7.l;
import s7.b;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u001e\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0010\u000b\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0004\b`\u0018\u0000*\u0006\b\u0000\u0010\u0001 \u00012\b\u0012\u0004\u0012\u00028\u00000\u0002:\u0001\u0016J\u001d\u0010\u0004\u001a\b\u0012\u0004\u0012\u00028\u00000\u00002\u0006\u0010\u0003\u001a\u00028\u0000H&¢\u0006\u0004\b\u0004\u0010\u0005J#\u0010\b\u001a\b\u0012\u0004\u0012\u00028\u00000\u00002\f\u0010\u0007\u001a\b\u0012\u0004\u0012\u00028\u00000\u0006H&¢\u0006\u0004\b\b\u0010\tJ\u001d\u0010\n\u001a\b\u0012\u0004\u0012\u00028\u00000\u00002\u0006\u0010\u0003\u001a\u00028\u0000H&¢\u0006\u0004\b\n\u0010\u0005J#\u0010\u000b\u001a\b\u0012\u0004\u0012\u00028\u00000\u00002\f\u0010\u0007\u001a\b\u0012\u0004\u0012\u00028\u00000\u0006H&¢\u0006\u0004\b\u000b\u0010\tJ)\u0010\u000b\u001a\b\u0012\u0004\u0012\u00028\u00000\u00002\u0012\u0010\u000e\u001a\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00020\r0\fH&¢\u0006\u0004\b\u000b\u0010\u000fJ#\u0010\u0010\u001a\b\u0012\u0004\u0012\u00028\u00000\u00002\f\u0010\u0007\u001a\b\u0012\u0004\u0012\u00028\u00000\u0006H&¢\u0006\u0004\b\u0010\u0010\tJ\u0015\u0010\u0011\u001a\b\u0012\u0004\u0012\u00028\u00000\u0000H&¢\u0006\u0004\b\u0011\u0010\u0012J\u0015\u0010\u0014\u001a\b\u0012\u0004\u0012\u00028\u00000\u0013H&¢\u0006\u0004\b\u0014\u0010\u0015ø\u0001\u0000\u0082\u0002\u0006\n\u0004\b!0\u0001¨\u0006\u0017À\u0006\u0001"}, d2 = {"Landroidx/compose/runtime/external/kotlinx/collections/immutable/PersistentCollection;", "E", "Landroidx/compose/runtime/external/kotlinx/collections/immutable/ImmutableCollection;", "element", "add", "(Ljava/lang/Object;)Landroidx/compose/runtime/external/kotlinx/collections/immutable/PersistentCollection;", "", "elements", "addAll", "(Ljava/util/Collection;)Landroidx/compose/runtime/external/kotlinx/collections/immutable/PersistentCollection;", "remove", "removeAll", "Lkotlin/Function1;", "", "predicate", "(Lr7/l;)Landroidx/compose/runtime/external/kotlinx/collections/immutable/PersistentCollection;", "retainAll", "clear", "()Landroidx/compose/runtime/external/kotlinx/collections/immutable/PersistentCollection;", "Landroidx/compose/runtime/external/kotlinx/collections/immutable/PersistentCollection$Builder;", "builder", "()Landroidx/compose/runtime/external/kotlinx/collections/immutable/PersistentCollection$Builder;", "Builder", "runtime_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
public interface PersistentCollection<E> extends ImmutableCollection<E> {

    @Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u001f\n\u0000\n\u0002\u0018\u0002\n\u0000\bf\u0018\u0000*\u0004\b\u0001\u0010\u00012\b\u0012\u0004\u0012\u0002H\u00010\u0002J\u000e\u0010\u0003\u001a\b\u0012\u0004\u0012\u00028\u00010\u0004H&ø\u0001\u0000\u0082\u0002\u0006\n\u0004\b!0\u0001¨\u0006\u0005À\u0006\u0001"}, d2 = {"Landroidx/compose/runtime/external/kotlinx/collections/immutable/PersistentCollection$Builder;", "E", "", "build", "Landroidx/compose/runtime/external/kotlinx/collections/immutable/PersistentCollection;", "runtime_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
    public interface Builder<E> extends Collection<E>, b {
        PersistentCollection<E> build();
    }

    @Override // java.util.Collection
    PersistentCollection<E> add(E element);

    @Override // java.util.Collection
    PersistentCollection<E> addAll(Collection<? extends E> elements);

    Builder<E> builder();

    @Override // java.util.Collection
    PersistentCollection<E> clear();

    @Override // java.util.Collection
    PersistentCollection<E> remove(E element);

    @Override // java.util.Collection
    PersistentCollection<E> removeAll(Collection<? extends E> elements);

    PersistentCollection<E> removeAll(l<? super E, Boolean> predicate);

    @Override // java.util.Collection
    PersistentCollection<E> retainAll(Collection<? extends E> elements);
}
