package androidx.compose.ui.layout;

import io.ktor.http.ContentDisposition;
import j$.lang.Iterable$CC;
import j$.util.Collection;
import j$.util.Spliterator;
import j$.util.stream.Stream;
import j$.util.stream.StreamSupport;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.Spliterator;
import java.util.function.Consumer;
import java.util.function.IntFunction;
import java.util.function.Predicate;
import java.util.stream.Stream;
import kotlin.Metadata;
import kotlin.collections.x;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0002\b\u0004\bf\u0018\u00002\u00020\u0001:\u0001\fJ\u0017\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0003\u001a\u00020\u0002H&¢\u0006\u0004\b\u0005\u0010\u0006J#\u0010\n\u001a\u00020\t2\b\u0010\u0007\u001a\u0004\u0018\u00010\u00012\b\u0010\b\u001a\u0004\u0018\u00010\u0001H&¢\u0006\u0004\b\n\u0010\u000bø\u0001\u0000\u0082\u0002\u0006\n\u0004\b!0\u0001¨\u0006\rÀ\u0006\u0001"}, d2 = {"Landroidx/compose/ui/layout/SubcomposeSlotReusePolicy;", "", "Landroidx/compose/ui/layout/SubcomposeSlotReusePolicy$SlotIdsSet;", "slotIds", "Lx6/t0;", "getSlotsToRetain", "(Landroidx/compose/ui/layout/SubcomposeSlotReusePolicy$SlotIdsSet;)V", "slotId", "reusableSlotId", "", "areCompatible", "(Ljava/lang/Object;Ljava/lang/Object;)Z", "SlotIdsSet", "ui_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
public interface SubcomposeSlotReusePolicy {

    @Metadata(d1 = {"\u0000<\n\u0002\u0018\u0002\n\u0002\u0010\u001e\n\u0002\u0010\u0000\n\u0002\u0010#\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0002\b\n\n\u0002\u0010)\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0004\b\u0007\u0018\u00002\n\u0012\u0006\u0012\u0004\u0018\u00010\u00020\u0001B\u001b\b\u0000\u0012\u0010\b\u0002\u0010\u0004\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00020\u0003¢\u0006\u0004\b\u0005\u0010\u0006J\u001a\u0010\t\u001a\u00020\b2\b\u0010\u0007\u001a\u0004\u0018\u00010\u0002H\u0096\u0003¢\u0006\u0004\b\t\u0010\nJ \u0010\f\u001a\u00020\b2\u000e\u0010\u000b\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00020\u0001H\u0096\u0001¢\u0006\u0004\b\f\u0010\rJ\u0010\u0010\u000e\u001a\u00020\bH\u0096\u0001¢\u0006\u0004\b\u000e\u0010\u000fJ\u0019\u0010\u0012\u001a\u00020\b2\b\u0010\u0010\u001a\u0004\u0018\u00010\u0002H\u0000¢\u0006\u0004\b\u0011\u0010\nJ\u0018\u0010\u0014\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00020\u0013H\u0096\u0002¢\u0006\u0004\b\u0014\u0010\u0015J\u0017\u0010\u0016\u001a\u00020\b2\b\u0010\u0010\u001a\u0004\u0018\u00010\u0002¢\u0006\u0004\b\u0016\u0010\nJ\u001d\u0010\u0018\u001a\u00020\b2\u000e\u0010\u0017\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00020\u0001¢\u0006\u0004\b\u0018\u0010\rJ#\u0010\u0018\u001a\u00020\b2\u0014\u0010\u001a\u001a\u0010\u0012\u0006\u0012\u0004\u0018\u00010\u0002\u0012\u0004\u0012\u00020\b0\u0019¢\u0006\u0004\b\u0018\u0010\u001bJ\u001d\u0010\u001c\u001a\u00020\b2\u000e\u0010\u0017\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00020\u0001¢\u0006\u0004\b\u001c\u0010\rJ#\u0010\u001c\u001a\u00020\b2\u0014\u0010\u001a\u001a\u0010\u0012\u0006\u0012\u0004\u0018\u00010\u0002\u0012\u0004\u0012\u00020\b0\u0019¢\u0006\u0004\b\u001c\u0010\u001bJ\r\u0010\u001e\u001a\u00020\u001d¢\u0006\u0004\b\u001e\u0010\u001fR\u001c\u0010\u0004\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00020\u00038\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u0004\u0010 R\u0014\u0010$\u001a\u00020!8\u0016X\u0096\u0005¢\u0006\u0006\u001a\u0004\b\"\u0010#¨\u0006%"}, d2 = {"Landroidx/compose/ui/layout/SubcomposeSlotReusePolicy$SlotIdsSet;", "", "", "", "set", "<init>", "(Ljava/util/Set;)V", "element", "", "contains", "(Ljava/lang/Object;)Z", "elements", "containsAll", "(Ljava/util/Collection;)Z", "isEmpty", "()Z", "slotId", "add$ui_release", "add", "", "iterator", "()Ljava/util/Iterator;", "remove", "slotIds", "removeAll", "Lkotlin/Function1;", "predicate", "(Lr7/l;)Z", "retainAll", "Lx6/t0;", "clear", "()V", "Ljava/util/Set;", "", "getSize", "()I", ContentDisposition.Parameters.Size, "ui_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
    public static final class SlotIdsSet implements Collection<Object>, s7.a, j$.util.Collection {
        public static final int $stable = 8;
        private final Set<Object> set;

        /* JADX WARN: Multi-variable type inference failed */
        public SlotIdsSet() {
            this(null, 1, 0 == true ? 1 : 0);
        }

        @Override // java.util.Collection
        /* JADX INFO: renamed from: add$ui_release, reason: merged with bridge method [inline-methods] */
        public final boolean add(Object slotId) {
            return this.set.add(slotId);
        }

        @Override // java.util.Collection
        public boolean addAll(Collection<? extends Object> collection) {
            throw new UnsupportedOperationException("Operation is not supported for read-only collection");
        }

        @Override // java.util.Collection
        public final void clear() {
            this.set.clear();
        }

        @Override // java.util.Collection
        public boolean contains(Object element) {
            return this.set.contains(element);
        }

        @Override // java.util.Collection
        public boolean containsAll(Collection<? extends Object> elements) {
            return this.set.containsAll(elements);
        }

        @Override // java.lang.Iterable, j$.util.Collection
        public /* synthetic */ void forEach(Consumer consumer) {
            Iterable$CC.$default$forEach(this, consumer);
        }

        public int getSize() {
            return this.set.size();
        }

        @Override // java.util.Collection
        public boolean isEmpty() {
            return this.set.isEmpty();
        }

        @Override // java.util.Collection, java.lang.Iterable
        public Iterator<Object> iterator() {
            return this.set.iterator();
        }

        @Override // java.util.Collection
        public /* synthetic */ Stream<Object> parallelStream() {
            return Stream.Wrapper.convert(parallelStream());
        }

        @Override // java.util.Collection
        public final boolean remove(Object slotId) {
            return this.set.remove(slotId);
        }

        @Override // java.util.Collection
        public final boolean removeAll(Collection<? extends Object> slotIds) {
            return this.set.remove(slotIds);
        }

        @Override // java.util.Collection, j$.util.Collection
        public boolean removeIf(Predicate<? super Object> predicate) {
            throw new UnsupportedOperationException("Operation is not supported for read-only collection");
        }

        @Override // java.util.Collection
        public final boolean retainAll(Collection<? extends Object> slotIds) {
            return this.set.retainAll(slotIds);
        }

        @Override // java.util.Collection
        public final /* bridge */ int size() {
            return getSize();
        }

        @Override // java.util.Collection, java.lang.Iterable
        public /* synthetic */ Spliterator spliterator() {
            return Spliterator.Wrapper.convert(spliterator());
        }

        @Override // java.util.Collection
        public /* synthetic */ java.util.stream.Stream<Object> stream() {
            return Stream.Wrapper.convert(stream());
        }

        @Override // java.util.Collection
        public Object[] toArray() {
            return kotlin.jvm.internal.o.a(this);
        }

        public SlotIdsSet(Set<Object> set) {
            this.set = set;
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // java.util.Collection, j$.util.Collection
        public /* synthetic */ j$.util.stream.Stream parallelStream() {
            return StreamSupport.stream(Collection.EL.b(this), true);
        }

        public final boolean removeAll(r7.l<Object, Boolean> predicate) {
            return x.j0(this.set, predicate, true);
        }

        public final boolean retainAll(r7.l<Object, Boolean> predicate) {
            return x.j0(this.set, predicate, false);
        }

        @Override // java.util.Collection, java.lang.Iterable, j$.util.Collection, j$.util.List
        public /* synthetic */ j$.util.Spliterator spliterator() {
            return Collection.CC.$default$spliterator(this);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // java.util.Collection, j$.util.Collection
        public /* synthetic */ j$.util.stream.Stream stream() {
            return Collection.CC.$default$stream(this);
        }

        @Override // java.util.Collection, j$.util.Collection
        public /* synthetic */ Object[] toArray(IntFunction intFunction) {
            return toArray((Object[]) intFunction.apply(0));
        }

        @Override // java.util.Collection
        public <T> T[] toArray(T[] tArr) {
            return (T[]) kotlin.jvm.internal.o.b(this, tArr);
        }

        public /* synthetic */ SlotIdsSet(Set set, int i10, kotlin.jvm.internal.h hVar) {
            this((i10 & 1) != 0 ? new LinkedHashSet() : set);
        }
    }

    boolean areCompatible(Object slotId, Object reusableSlotId);

    void getSlotsToRetain(SlotIdsSet slotIds);
}
