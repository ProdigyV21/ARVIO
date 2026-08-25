package j$.util.stream;

import j$.util.Objects;
import j$.util.Spliterator;

/* JADX INFO: loaded from: classes2.dex */
public final class StreamSupport {
    public static <T> Stream<T> stream(Spliterator<T> spliterator, boolean z) {
        Objects.requireNonNull(spliterator);
        return new a5(spliterator, y6.n(spliterator), z);
    }
}
