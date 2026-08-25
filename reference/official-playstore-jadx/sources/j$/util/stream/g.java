package j$.util.stream;

import j$.util.Spliterator;
import java.util.Iterator;

/* JADX INFO: loaded from: classes2.dex */
public interface g extends AutoCloseable {
    boolean isParallel();

    Iterator iterator();

    g onClose(Runnable runnable);

    g parallel();

    g sequential();

    Spliterator spliterator();

    g unordered();
}
