package androidx.media3.container;

import ac.b;
import androidx.media3.common.C;
import androidx.media3.common.util.ParsableByteArray;
import androidx.media3.common.util.Util;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;

/* JADX INFO: loaded from: classes3.dex */
public final class ReorderingBufferQueue {
    private BuffersWithTimestamp lastQueuedBuffer;
    private final OutputConsumer outputConsumer;
    private final ArrayDeque<ParsableByteArray> unusedParsableByteArrays = new ArrayDeque<>();
    private final ArrayDeque<BuffersWithTimestamp> unusedBuffersWithTimestamp = new ArrayDeque<>();
    private final PriorityQueue<BuffersWithTimestamp> pendingBuffers = new PriorityQueue<>();
    private int reorderingQueueSize = -1;

    public static final class BuffersWithTimestamp implements Comparable<BuffersWithTimestamp> {
        public long presentationTimeUs = C.TIME_UNSET;
        public final List<ParsableByteArray> nalBuffers = new ArrayList();

        public void init(long j10, ParsableByteArray parsableByteArray) {
            b.j(j10 != C.TIME_UNSET);
            b.s(this.nalBuffers.isEmpty());
            this.presentationTimeUs = j10;
            this.nalBuffers.add(parsableByteArray);
        }

        @Override // java.lang.Comparable
        public int compareTo(BuffersWithTimestamp buffersWithTimestamp) {
            return Long.compare(this.presentationTimeUs, buffersWithTimestamp.presentationTimeUs);
        }
    }

    public interface OutputConsumer {
        void consume(long j10, ParsableByteArray parsableByteArray);
    }

    public ReorderingBufferQueue(OutputConsumer outputConsumer) {
        this.outputConsumer = outputConsumer;
    }

    private ParsableByteArray copy(ParsableByteArray parsableByteArray) {
        ParsableByteArray parsableByteArray2 = this.unusedParsableByteArrays.isEmpty() ? new ParsableByteArray() : this.unusedParsableByteArrays.pop();
        parsableByteArray2.reset(parsableByteArray.bytesLeft());
        System.arraycopy(parsableByteArray.getData(), parsableByteArray.getPosition(), parsableByteArray2.getData(), 0, parsableByteArray2.bytesLeft());
        return parsableByteArray2;
    }

    private void flushQueueDownToSize(int i10) {
        while (this.pendingBuffers.size() > i10) {
            BuffersWithTimestamp buffersWithTimestamp = (BuffersWithTimestamp) Util.castNonNull(this.pendingBuffers.poll());
            for (int i11 = 0; i11 < buffersWithTimestamp.nalBuffers.size(); i11++) {
                this.outputConsumer.consume(buffersWithTimestamp.presentationTimeUs, buffersWithTimestamp.nalBuffers.get(i11));
                this.unusedParsableByteArrays.push(buffersWithTimestamp.nalBuffers.get(i11));
            }
            buffersWithTimestamp.nalBuffers.clear();
            BuffersWithTimestamp buffersWithTimestamp2 = this.lastQueuedBuffer;
            if (buffersWithTimestamp2 != null && buffersWithTimestamp2.presentationTimeUs == buffersWithTimestamp.presentationTimeUs) {
                this.lastQueuedBuffer = null;
            }
            this.unusedBuffersWithTimestamp.push(buffersWithTimestamp);
        }
    }

    public void add(long j10, ParsableByteArray parsableByteArray) {
        int i10;
        if (j10 == C.TIME_UNSET || (i10 = this.reorderingQueueSize) == 0 || (i10 != -1 && this.pendingBuffers.size() >= this.reorderingQueueSize && j10 < ((BuffersWithTimestamp) Util.castNonNull(this.pendingBuffers.peek())).presentationTimeUs)) {
            this.outputConsumer.consume(j10, parsableByteArray);
            return;
        }
        ParsableByteArray parsableByteArrayCopy = copy(parsableByteArray);
        BuffersWithTimestamp buffersWithTimestamp = this.lastQueuedBuffer;
        if (buffersWithTimestamp != null && j10 == buffersWithTimestamp.presentationTimeUs) {
            buffersWithTimestamp.nalBuffers.add(parsableByteArrayCopy);
            return;
        }
        BuffersWithTimestamp buffersWithTimestamp2 = this.unusedBuffersWithTimestamp.isEmpty() ? new BuffersWithTimestamp() : this.unusedBuffersWithTimestamp.pop();
        buffersWithTimestamp2.init(j10, parsableByteArrayCopy);
        this.pendingBuffers.add(buffersWithTimestamp2);
        this.lastQueuedBuffer = buffersWithTimestamp2;
        int i11 = this.reorderingQueueSize;
        if (i11 != -1) {
            flushQueueDownToSize(i11);
        }
    }

    public void clear() {
        this.pendingBuffers.clear();
    }

    public void flush() {
        flushQueueDownToSize(0);
    }

    public int getMaxSize() {
        return this.reorderingQueueSize;
    }

    public void setMaxSize(int i10) {
        b.s(i10 >= 0);
        this.reorderingQueueSize = i10;
        flushQueueDownToSize(i10);
    }
}
