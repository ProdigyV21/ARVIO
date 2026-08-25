package androidx.compose.runtime.external.kotlinx.collections.immutable.implementations.immutableSet;

import androidx.compose.runtime.external.kotlinx.collections.immutable.PersistentSet;
import androidx.compose.runtime.external.kotlinx.collections.immutable.internal.DeltaCounter;
import androidx.compose.runtime.external.kotlinx.collections.immutable.internal.MutabilityOwnership;
import io.ktor.http.ContentDisposition;
import java.util.Collection;
import java.util.Iterator;
import kotlin.Metadata;
import kotlin.collections.l;
import kotlin.jvm.internal.h;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000N\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\u001e\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010)\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\b\n\u0002\b\n\b\u0000\u0018\u0000*\u0004\b\u0000\u0010\u00012\b\u0012\u0004\u0012\u00028\u00000\u00022\b\u0012\u0004\u0012\u00028\u00000\u0003B\u0015\u0012\f\u0010\u0005\u001a\b\u0012\u0004\u0012\u00028\u00000\u0004¢\u0006\u0004\b\u0006\u0010\u0007J\u0015\u0010\b\u001a\b\u0012\u0004\u0012\u00028\u00000\u0004H\u0016¢\u0006\u0004\b\b\u0010\tJ\u0018\u0010\f\u001a\u00020\u000b2\u0006\u0010\n\u001a\u00028\u0000H\u0096\u0002¢\u0006\u0004\b\f\u0010\rJ\u0017\u0010\u000e\u001a\u00020\u000b2\u0006\u0010\n\u001a\u00028\u0000H\u0016¢\u0006\u0004\b\u000e\u0010\rJ\u001d\u0010\u0011\u001a\u00020\u000b2\f\u0010\u0010\u001a\b\u0012\u0004\u0012\u00028\u00000\u000fH\u0016¢\u0006\u0004\b\u0011\u0010\u0012J\u001d\u0010\u0013\u001a\u00020\u000b2\f\u0010\u0010\u001a\b\u0012\u0004\u0012\u00028\u00000\u000fH\u0016¢\u0006\u0004\b\u0013\u0010\u0012J\u001d\u0010\u0014\u001a\u00020\u000b2\f\u0010\u0010\u001a\b\u0012\u0004\u0012\u00028\u00000\u000fH\u0016¢\u0006\u0004\b\u0014\u0010\u0012J\u001d\u0010\u0015\u001a\u00020\u000b2\f\u0010\u0010\u001a\b\u0012\u0004\u0012\u00028\u00000\u000fH\u0016¢\u0006\u0004\b\u0015\u0010\u0012J\u0017\u0010\u0016\u001a\u00020\u000b2\u0006\u0010\n\u001a\u00028\u0000H\u0016¢\u0006\u0004\b\u0016\u0010\rJ\u000f\u0010\u0018\u001a\u00020\u0017H\u0016¢\u0006\u0004\b\u0018\u0010\u0019J\u0016\u0010\u001b\u001a\b\u0012\u0004\u0012\u00028\u00000\u001aH\u0096\u0002¢\u0006\u0004\b\u001b\u0010\u001cR\u001c\u0010\u0005\u001a\b\u0012\u0004\u0012\u00028\u00000\u00048\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b\u0005\u0010\u001dR$\u0010 \u001a\u00020\u001e2\u0006\u0010\u001f\u001a\u00020\u001e8\u0000@BX\u0080\u000e¢\u0006\f\n\u0004\b \u0010!\u001a\u0004\b\"\u0010#R0\u0010%\u001a\b\u0012\u0004\u0012\u00028\u00000$2\f\u0010\u001f\u001a\b\u0012\u0004\u0012\u00028\u00000$8\u0000@BX\u0080\u000e¢\u0006\f\n\u0004\b%\u0010&\u001a\u0004\b'\u0010(R$\u0010*\u001a\u00020)2\u0006\u0010\u001f\u001a\u00020)8\u0000@BX\u0080\u000e¢\u0006\f\n\u0004\b*\u0010+\u001a\u0004\b,\u0010-R*\u0010/\u001a\u00020)2\u0006\u0010.\u001a\u00020)8\u0016@VX\u0096\u000e¢\u0006\u0012\n\u0004\b/\u0010+\u001a\u0004\b0\u0010-\"\u0004\b1\u00102¨\u00063"}, d2 = {"Landroidx/compose/runtime/external/kotlinx/collections/immutable/implementations/immutableSet/PersistentHashSetBuilder;", "E", "Lkotlin/collections/l;", "Landroidx/compose/runtime/external/kotlinx/collections/immutable/PersistentSet$Builder;", "Landroidx/compose/runtime/external/kotlinx/collections/immutable/implementations/immutableSet/PersistentHashSet;", "set", "<init>", "(Landroidx/compose/runtime/external/kotlinx/collections/immutable/implementations/immutableSet/PersistentHashSet;)V", "build", "()Landroidx/compose/runtime/external/kotlinx/collections/immutable/implementations/immutableSet/PersistentHashSet;", "element", "", "contains", "(Ljava/lang/Object;)Z", "add", "", "elements", "addAll", "(Ljava/util/Collection;)Z", "retainAll", "removeAll", "containsAll", "remove", "Lx6/t0;", "clear", "()V", "", "iterator", "()Ljava/util/Iterator;", "Landroidx/compose/runtime/external/kotlinx/collections/immutable/implementations/immutableSet/PersistentHashSet;", "Landroidx/compose/runtime/external/kotlinx/collections/immutable/internal/MutabilityOwnership;", "<set-?>", "ownership", "Landroidx/compose/runtime/external/kotlinx/collections/immutable/internal/MutabilityOwnership;", "getOwnership$runtime_release", "()Landroidx/compose/runtime/external/kotlinx/collections/immutable/internal/MutabilityOwnership;", "Landroidx/compose/runtime/external/kotlinx/collections/immutable/implementations/immutableSet/TrieNode;", "node", "Landroidx/compose/runtime/external/kotlinx/collections/immutable/implementations/immutableSet/TrieNode;", "getNode$runtime_release", "()Landroidx/compose/runtime/external/kotlinx/collections/immutable/implementations/immutableSet/TrieNode;", "", "modCount", "I", "getModCount$runtime_release", "()I", "value", ContentDisposition.Parameters.Size, "getSize", "setSize", "(I)V", "runtime_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
public final class PersistentHashSetBuilder<E> extends l implements PersistentSet.Builder<E> {
    public static final int $stable = 8;
    private int modCount;
    private TrieNode<E> node;
    private MutabilityOwnership ownership = new MutabilityOwnership();
    private PersistentHashSet<E> set;
    private int size;

    public PersistentHashSetBuilder(PersistentHashSet<E> persistentHashSet) {
        this.set = persistentHashSet;
        this.node = this.set.getNode$runtime_release();
        this.size = this.set.size();
    }

    @Override // kotlin.collections.l, java.util.AbstractCollection, java.util.Collection, java.util.Set
    public boolean add(E element) {
        int size = size();
        this.node = this.node.mutableAdd(element != null ? element.hashCode() : 0, element, 0, this);
        return size != size();
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
    public boolean addAll(Collection<? extends E> elements) {
        h hVar = null;
        PersistentHashSet<E> persistentHashSetBuild = elements instanceof PersistentHashSet ? (PersistentHashSet) elements : null;
        if (persistentHashSetBuild == null) {
            PersistentHashSetBuilder persistentHashSetBuilder = elements instanceof PersistentHashSetBuilder ? (PersistentHashSetBuilder) elements : null;
            persistentHashSetBuild = persistentHashSetBuilder != null ? persistentHashSetBuilder.build() : null;
        }
        if (persistentHashSetBuild == null) {
            return super.addAll(elements);
        }
        DeltaCounter deltaCounter = new DeltaCounter(0, 1, hVar);
        int size = size();
        TrieNode<E> trieNodeMutableAddAll = this.node.mutableAddAll(persistentHashSetBuild.getNode$runtime_release(), 0, deltaCounter, this);
        int size2 = (elements.size() + size) - deltaCounter.getCount();
        if (size != size2) {
            this.node = trieNodeMutableAddAll;
            setSize(size2);
        }
        return size != size();
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
    public void clear() {
        this.node = TrieNode.INSTANCE.getEMPTY$runtime_release();
        setSize(0);
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
    public boolean contains(Object element) {
        return this.node.contains(element != null ? element.hashCode() : 0, element, 0);
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
    public boolean containsAll(Collection<? extends Object> elements) {
        return elements instanceof PersistentHashSet ? this.node.containsAll(((PersistentHashSet) elements).getNode$runtime_release(), 0) : elements instanceof PersistentHashSetBuilder ? this.node.containsAll(((PersistentHashSetBuilder) elements).node, 0) : super.containsAll(elements);
    }

    /* JADX INFO: renamed from: getModCount$runtime_release, reason: from getter */
    public final int getModCount() {
        return this.modCount;
    }

    public final TrieNode<E> getNode$runtime_release() {
        return this.node;
    }

    /* JADX INFO: renamed from: getOwnership$runtime_release, reason: from getter */
    public final MutabilityOwnership getOwnership() {
        return this.ownership;
    }

    @Override // kotlin.collections.l
    public int getSize() {
        return this.size;
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.lang.Iterable, java.util.Set
    public Iterator<E> iterator() {
        return new PersistentHashSetMutableIterator(this);
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
    public boolean remove(Object element) {
        int size = size();
        this.node = this.node.mutableRemove(element != null ? element.hashCode() : 0, element, 0, this);
        return size != size();
    }

    @Override // java.util.AbstractSet, java.util.AbstractCollection, java.util.Collection, java.util.Set
    public boolean removeAll(Collection<? extends Object> elements) {
        h hVar = null;
        PersistentHashSet<E> persistentHashSetBuild = elements instanceof PersistentHashSet ? (PersistentHashSet) elements : null;
        if (persistentHashSetBuild == null) {
            PersistentHashSetBuilder persistentHashSetBuilder = elements instanceof PersistentHashSetBuilder ? (PersistentHashSetBuilder) elements : null;
            persistentHashSetBuild = persistentHashSetBuilder != null ? persistentHashSetBuilder.build() : null;
        }
        if (persistentHashSetBuild == null) {
            return super.removeAll(elements);
        }
        DeltaCounter deltaCounter = new DeltaCounter(0, 1, hVar);
        int size = size();
        Object objMutableRemoveAll = this.node.mutableRemoveAll(persistentHashSetBuild.getNode$runtime_release(), 0, deltaCounter, this);
        int count = size - deltaCounter.getCount();
        if (count == 0) {
            clear();
        } else if (count != size) {
            this.node = (TrieNode) objMutableRemoveAll;
            setSize(count);
        }
        return size != size();
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
    public boolean retainAll(Collection<? extends Object> elements) {
        h hVar = null;
        PersistentHashSet<E> persistentHashSetBuild = elements instanceof PersistentHashSet ? (PersistentHashSet) elements : null;
        if (persistentHashSetBuild == null) {
            PersistentHashSetBuilder persistentHashSetBuilder = elements instanceof PersistentHashSetBuilder ? (PersistentHashSetBuilder) elements : null;
            persistentHashSetBuild = persistentHashSetBuilder != null ? persistentHashSetBuilder.build() : null;
        }
        if (persistentHashSetBuild == null) {
            return super.retainAll(elements);
        }
        DeltaCounter deltaCounter = new DeltaCounter(0, 1, hVar);
        int size = size();
        Object objMutableRetainAll = this.node.mutableRetainAll(persistentHashSetBuild.getNode$runtime_release(), 0, deltaCounter, this);
        int count = deltaCounter.getCount();
        if (count == 0) {
            clear();
        } else if (count != size) {
            this.node = (TrieNode) objMutableRetainAll;
            setSize(count);
        }
        return size != size();
    }

    public void setSize(int i10) {
        this.size = i10;
        this.modCount++;
    }

    @Override // androidx.compose.runtime.external.kotlinx.collections.immutable.PersistentSet.Builder, androidx.compose.runtime.external.kotlinx.collections.immutable.PersistentCollection.Builder
    public PersistentHashSet<E> build() {
        PersistentHashSet<E> persistentHashSet;
        if (this.node == this.set.getNode$runtime_release()) {
            persistentHashSet = this.set;
        } else {
            this.ownership = new MutabilityOwnership();
            persistentHashSet = new PersistentHashSet<>(this.node, size());
        }
        this.set = persistentHashSet;
        return persistentHashSet;
    }
}
