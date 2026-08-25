package androidx.media3.exoplayer.source;

import android.util.SparseArray;
import androidx.media3.common.util.Consumer;

/* JADX INFO: loaded from: classes3.dex */
final class SpannedData<V> {
    private int memoizedReadIndex;
    private final Consumer<V> removeCallback;
    private final SparseArray<V> spans;

    public SpannedData() {
        this(new p(1));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ void lambda$new$0(Object obj) {
    }

    public void appendSpan(int i10, V v2) {
        if (this.memoizedReadIndex == -1) {
            ac.b.s(this.spans.size() == 0);
            this.memoizedReadIndex = 0;
        }
        if (this.spans.size() > 0) {
            SparseArray<V> sparseArray = this.spans;
            int iKeyAt = sparseArray.keyAt(sparseArray.size() - 1);
            ac.b.j(i10 >= iKeyAt);
            if (iKeyAt == i10) {
                Consumer<V> consumer = this.removeCallback;
                SparseArray<V> sparseArray2 = this.spans;
                consumer.accept(sparseArray2.valueAt(sparseArray2.size() - 1));
            }
        }
        this.spans.append(i10, v2);
    }

    public void clear() {
        for (int i10 = 0; i10 < this.spans.size(); i10++) {
            this.removeCallback.accept(this.spans.valueAt(i10));
        }
        this.memoizedReadIndex = -1;
        this.spans.clear();
    }

    public void discardFrom(int i10) {
        for (int size = this.spans.size() - 1; size >= 0 && i10 < this.spans.keyAt(size); size--) {
            this.removeCallback.accept(this.spans.valueAt(size));
            this.spans.removeAt(size);
        }
        this.memoizedReadIndex = this.spans.size() > 0 ? Math.min(this.memoizedReadIndex, this.spans.size() - 1) : -1;
    }

    public void discardTo(int i10) {
        int i11 = 0;
        while (i11 < this.spans.size() - 1) {
            int i12 = i11 + 1;
            if (i10 < this.spans.keyAt(i12)) {
                return;
            }
            this.removeCallback.accept(this.spans.valueAt(i11));
            this.spans.removeAt(i11);
            int i13 = this.memoizedReadIndex;
            if (i13 > 0) {
                this.memoizedReadIndex = i13 - 1;
            }
            i11 = i12;
        }
    }

    public V get(int i10) {
        if (this.memoizedReadIndex == -1) {
            this.memoizedReadIndex = 0;
        }
        while (true) {
            int i11 = this.memoizedReadIndex;
            if (i11 <= 0 || i10 >= this.spans.keyAt(i11)) {
                break;
            }
            this.memoizedReadIndex--;
        }
        while (this.memoizedReadIndex < this.spans.size() - 1 && i10 >= this.spans.keyAt(this.memoizedReadIndex + 1)) {
            this.memoizedReadIndex++;
        }
        return this.spans.valueAt(this.memoizedReadIndex);
    }

    public V getEndValue() {
        return this.spans.valueAt(r0.size() - 1);
    }

    public boolean isEmpty() {
        return this.spans.size() == 0;
    }

    public SpannedData(Consumer<V> consumer) {
        this.spans = new SparseArray<>();
        this.removeCallback = consumer;
        this.memoizedReadIndex = -1;
    }
}
