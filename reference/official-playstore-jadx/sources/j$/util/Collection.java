package j$.util;

import j$.util.List;
import j$.util.stream.Stream;
import j$.util.stream.StreamSupport;
import java.lang.reflect.Field;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.function.Consumer;
import java.util.function.IntFunction;
import java.util.function.Predicate;

/* JADX INFO: loaded from: classes2.dex */
public interface Collection<E> {
    void forEach(Consumer<? super E> consumer);

    Stream<E> parallelStream();

    boolean removeIf(Predicate<? super E> predicate);

    Spliterator<E> spliterator();

    Stream<E> stream();

    <T> T[] toArray(IntFunction<T[]> intFunction);

    /* JADX INFO: renamed from: j$.util.Collection$-EL, reason: invalid class name */
    public final /* synthetic */ class EL {
        public static /* synthetic */ Stream stream(java.util.Collection collection) {
            return collection instanceof Collection ? ((Collection) collection).stream() : CC.$default$stream(collection);
        }

        public static Spliterator b(java.util.Collection collection) {
            if (collection instanceof Collection) {
                return ((Collection) collection).spliterator();
            }
            if (collection instanceof LinkedHashSet) {
                return Spliterators.spliterator((LinkedHashSet) collection, 17);
            }
            if (!(collection instanceof java.util.SortedSet)) {
                return collection instanceof java.util.Set ? Spliterators.spliterator((java.util.Set) collection, 1) : collection instanceof java.util.List ? List.CC.$default$spliterator((java.util.List) collection) : CC.$default$spliterator(collection);
            }
            java.util.SortedSet sortedSet = (java.util.SortedSet) collection;
            return new b0(sortedSet, sortedSet);
        }

        public static void a(java.util.Collection collection, Consumer consumer) {
            if (collection instanceof Collection) {
                ((Collection) collection).forEach(consumer);
                return;
            }
            Objects.requireNonNull(consumer);
            Iterator<E> it = collection.iterator();
            while (it.hasNext()) {
                consumer.n(it.next());
            }
        }
    }

    /* JADX INFO: renamed from: j$.util.Collection$-CC, reason: invalid class name */
    public final /* synthetic */ class CC {
        public static boolean $default$removeIf(java.util.Collection collection, Predicate predicate) {
            boolean zRemoveIf;
            if (!DesugarCollections.f18427a.isInstance(collection)) {
                Objects.requireNonNull(predicate);
                Iterator<E> it = collection.iterator();
                boolean z = false;
                while (it.hasNext()) {
                    if (predicate.test(it.next())) {
                        it.remove();
                        z = true;
                    }
                }
                return z;
            }
            Field field = DesugarCollections.f18428b;
            if (field == null) {
                try {
                    java.util.Collection collection2 = (java.util.Collection) DesugarCollections.f18429c.get(collection);
                    return collection2 instanceof Collection ? ((Collection) collection2).removeIf(predicate) : $default$removeIf(collection2, predicate);
                } catch (IllegalAccessException e5) {
                    throw new Error("Runtime illegal access in synchronized collection removeIf fall-back.", e5);
                }
            }
            try {
                synchronized (field.get(collection)) {
                    java.util.Collection collection3 = (java.util.Collection) DesugarCollections.f18429c.get(collection);
                    zRemoveIf = collection3 instanceof Collection ? ((Collection) collection3).removeIf(predicate) : $default$removeIf(collection3, predicate);
                }
                return zRemoveIf;
            } catch (IllegalAccessException e6) {
                throw new Error("Runtime illegal access in synchronized collection removeIf.", e6);
            }
        }

        public static Spliterator $default$spliterator(java.util.Collection collection) {
            return Spliterators.spliterator(collection, 0);
        }

        public static Stream $default$stream(java.util.Collection collection) {
            return StreamSupport.stream(EL.b(collection), false);
        }
    }
}
