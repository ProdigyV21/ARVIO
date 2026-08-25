package j$.util;

/* JADX INFO: loaded from: classes2.dex */
public interface n0 extends Spliterator {
    void forEachRemaining(Object obj);

    boolean tryAdvance(Object obj);

    @Override // j$.util.Spliterator
    n0 trySplit();
}
