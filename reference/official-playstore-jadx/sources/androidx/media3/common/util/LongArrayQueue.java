package androidx.media3.common.util;

import java.util.NoSuchElementException;

/* JADX INFO: loaded from: classes3.dex */
public final class LongArrayQueue {
    public static final int DEFAULT_INITIAL_CAPACITY = 16;
    private long[] data;
    private int headIndex;
    private int size;
    private int tailIndex;
    private int wrapAroundMask;

    public LongArrayQueue() {
        this(16);
    }

    private void doubleArraySize() {
        long[] jArr = this.data;
        int length = jArr.length << 1;
        if (length < 0) {
            throw new IllegalStateException();
        }
        long[] jArr2 = new long[length];
        int length2 = jArr.length;
        int i10 = this.headIndex;
        int i11 = length2 - i10;
        System.arraycopy(jArr, i10, jArr2, 0, i11);
        System.arraycopy(this.data, 0, jArr2, i11, i10);
        this.headIndex = 0;
        this.tailIndex = this.size - 1;
        this.data = jArr2;
        this.wrapAroundMask = jArr2.length - 1;
    }

    public void add(long j10) {
        if (this.size == this.data.length) {
            doubleArraySize();
        }
        int i10 = (this.tailIndex + 1) & this.wrapAroundMask;
        this.tailIndex = i10;
        this.data[i10] = j10;
        this.size++;
    }

    public int capacity() {
        return this.data.length;
    }

    public void clear() {
        this.headIndex = 0;
        this.tailIndex = -1;
        this.size = 0;
    }

    public long element() {
        if (this.size != 0) {
            return this.data[this.headIndex];
        }
        throw new NoSuchElementException();
    }

    public boolean isEmpty() {
        return this.size == 0;
    }

    public long remove() {
        int i10 = this.size;
        if (i10 == 0) {
            throw new NoSuchElementException();
        }
        long[] jArr = this.data;
        int i11 = this.headIndex;
        long j10 = jArr[i11];
        this.headIndex = this.wrapAroundMask & (i11 + 1);
        this.size = i10 - 1;
        return j10;
    }

    public int size() {
        return this.size;
    }

    public LongArrayQueue(int i10) {
        ac.b.j(i10 >= 0 && i10 <= 1073741824);
        i10 = i10 == 0 ? 1 : i10;
        i10 = Integer.bitCount(i10) != 1 ? Integer.highestOneBit(i10 - 1) << 1 : i10;
        this.headIndex = 0;
        this.tailIndex = -1;
        this.size = 0;
        long[] jArr = new long[i10];
        this.data = jArr;
        this.wrapAroundMask = jArr.length - 1;
    }
}
