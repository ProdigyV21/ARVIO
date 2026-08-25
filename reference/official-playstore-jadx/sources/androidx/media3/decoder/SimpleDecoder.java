package androidx.media3.decoder;

import ac.b;
import androidx.media3.common.C;
import androidx.media3.decoder.DecoderException;
import androidx.media3.decoder.DecoderInputBuffer;
import androidx.media3.decoder.DecoderOutputBuffer;
import java.util.ArrayDeque;

/* JADX INFO: loaded from: classes3.dex */
public abstract class SimpleDecoder<I extends DecoderInputBuffer, O extends DecoderOutputBuffer, E extends DecoderException> implements Decoder<I, O, E> {
    private int availableInputBufferCount;
    private final I[] availableInputBuffers;
    private int availableOutputBufferCount;
    private final O[] availableOutputBuffers;
    private final Thread decodeThread;
    private I dequeuedInputBuffer;
    private E exception;
    private boolean flushed;
    private final Object lock = new Object();
    private long outputStartTimeUs = C.TIME_UNSET;
    private final ArrayDeque<I> queuedInputBuffers = new ArrayDeque<>();
    private final ArrayDeque<O> queuedOutputBuffers = new ArrayDeque<>();
    private boolean released;
    private int skippedOutputBufferCount;

    public SimpleDecoder(I[] iArr, O[] oArr) {
        this.availableInputBuffers = iArr;
        this.availableInputBufferCount = iArr.length;
        for (int i10 = 0; i10 < this.availableInputBufferCount; i10++) {
            ((I[]) this.availableInputBuffers)[i10] = createInputBuffer();
        }
        this.availableOutputBuffers = oArr;
        this.availableOutputBufferCount = oArr.length;
        for (int i11 = 0; i11 < this.availableOutputBufferCount; i11++) {
            ((O[]) this.availableOutputBuffers)[i11] = createOutputBuffer();
        }
        Thread thread = new Thread("ExoPlayer:SimpleDecoder") { // from class: androidx.media3.decoder.SimpleDecoder.1
            @Override // java.lang.Thread, java.lang.Runnable
            public void run() {
                SimpleDecoder.this.run();
            }
        };
        this.decodeThread = thread;
        thread.start();
    }

    private boolean canDecodeBuffer() {
        return !this.queuedInputBuffers.isEmpty() && this.availableOutputBufferCount > 0;
    }

    private boolean decode() throws InterruptedException {
        E e5;
        synchronized (this.lock) {
            while (!this.released && !canDecodeBuffer()) {
                try {
                    this.lock.wait();
                } finally {
                }
            }
            if (this.released) {
                return false;
            }
            I iRemoveFirst = this.queuedInputBuffers.removeFirst();
            O[] oArr = this.availableOutputBuffers;
            int i10 = this.availableOutputBufferCount - 1;
            this.availableOutputBufferCount = i10;
            O o6 = oArr[i10];
            boolean z = this.flushed;
            this.flushed = false;
            if (iRemoveFirst.isEndOfStream()) {
                o6.addFlag(4);
            } else {
                o6.timeUs = iRemoveFirst.timeUs;
                if (iRemoveFirst.isFirstSample()) {
                    o6.addFlag(C.BUFFER_FLAG_FIRST_SAMPLE);
                }
                if (!isAtLeastOutputStartTimeUs(iRemoveFirst.timeUs)) {
                    o6.shouldBeSkipped = true;
                }
                try {
                    e5 = (E) decode(iRemoveFirst, o6, z);
                } catch (OutOfMemoryError e6) {
                    e5 = (E) createUnexpectedDecodeException(e6);
                } catch (RuntimeException e10) {
                    e5 = (E) createUnexpectedDecodeException(e10);
                }
                if (e5 != null) {
                    synchronized (this.lock) {
                        this.exception = e5;
                    }
                    return false;
                }
            }
            synchronized (this.lock) {
                try {
                    if (this.flushed) {
                        o6.release();
                    } else if (o6.shouldBeSkipped) {
                        this.skippedOutputBufferCount++;
                        o6.release();
                    } else {
                        o6.skippedOutputBufferCount = this.skippedOutputBufferCount;
                        this.skippedOutputBufferCount = 0;
                        this.queuedOutputBuffers.addLast(o6);
                    }
                    releaseInputBufferInternal(iRemoveFirst);
                } finally {
                }
            }
            return true;
        }
    }

    private void maybeNotifyDecodeLoop() {
        if (canDecodeBuffer()) {
            this.lock.notify();
        }
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: E extends androidx.media3.decoder.DecoderException */
    private void maybeThrowException() throws E, DecoderException {
        E e5 = this.exception;
        if (e5 != null) {
            throw e5;
        }
    }

    private void releaseInputBufferInternal(I i10) {
        i10.clear();
        I[] iArr = this.availableInputBuffers;
        int i11 = this.availableInputBufferCount;
        this.availableInputBufferCount = i11 + 1;
        iArr[i11] = i10;
    }

    private void releaseOutputBufferInternal(O o6) {
        o6.clear();
        O[] oArr = this.availableOutputBuffers;
        int i10 = this.availableOutputBufferCount;
        this.availableOutputBufferCount = i10 + 1;
        oArr[i10] = o6;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void run() {
        do {
            try {
            } catch (InterruptedException e5) {
                throw new IllegalStateException(e5);
            }
        } while (decode());
    }

    public abstract I createInputBuffer();

    public abstract O createOutputBuffer();

    public abstract E createUnexpectedDecodeException(Throwable th);

    public abstract E decode(I i10, O o6, boolean z);

    @Override // androidx.media3.decoder.Decoder
    public final void flush() {
        synchronized (this.lock) {
            try {
                this.flushed = true;
                this.skippedOutputBufferCount = 0;
                I i10 = this.dequeuedInputBuffer;
                if (i10 != null) {
                    releaseInputBufferInternal(i10);
                    this.dequeuedInputBuffer = null;
                }
                while (!this.queuedInputBuffers.isEmpty()) {
                    releaseInputBufferInternal(this.queuedInputBuffers.removeFirst());
                }
                while (!this.queuedOutputBuffers.isEmpty()) {
                    this.queuedOutputBuffers.removeFirst().release();
                }
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    public final boolean isAtLeastOutputStartTimeUs(long j10) {
        boolean z;
        synchronized (this.lock) {
            long j11 = this.outputStartTimeUs;
            z = j11 == C.TIME_UNSET || j10 >= j11;
        }
        return z;
    }

    @Override // androidx.media3.decoder.Decoder
    public void release() {
        synchronized (this.lock) {
            this.released = true;
            this.lock.notify();
        }
        try {
            this.decodeThread.join();
        } catch (InterruptedException unused) {
            Thread.currentThread().interrupt();
        }
    }

    public void releaseOutputBuffer(O o6) {
        synchronized (this.lock) {
            releaseOutputBufferInternal(o6);
            maybeNotifyDecodeLoop();
        }
    }

    public final void setInitialInputBufferSize(int i10) {
        b.s(this.availableInputBufferCount == this.availableInputBuffers.length);
        for (I i11 : this.availableInputBuffers) {
            i11.ensureSpaceForWrite(i10);
        }
    }

    @Override // androidx.media3.decoder.Decoder
    public final void setOutputStartTimeUs(long j10) {
        synchronized (this.lock) {
            try {
                b.s(this.availableInputBufferCount == this.availableInputBuffers.length || this.flushed);
                this.outputStartTimeUs = j10;
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    @Override // androidx.media3.decoder.Decoder
    public final I dequeueInputBuffer() throws DecoderException {
        I i10;
        synchronized (this.lock) {
            maybeThrowException();
            b.s(this.dequeuedInputBuffer == null);
            int i11 = this.availableInputBufferCount;
            if (i11 == 0) {
                i10 = null;
            } else {
                I[] iArr = this.availableInputBuffers;
                int i12 = i11 - 1;
                this.availableInputBufferCount = i12;
                i10 = iArr[i12];
            }
            this.dequeuedInputBuffer = i10;
        }
        return i10;
    }

    @Override // androidx.media3.decoder.Decoder
    public final O dequeueOutputBuffer() throws DecoderException {
        synchronized (this.lock) {
            try {
                maybeThrowException();
                if (this.queuedOutputBuffers.isEmpty()) {
                    return null;
                }
                return this.queuedOutputBuffers.removeFirst();
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    @Override // androidx.media3.decoder.Decoder
    public final void queueInputBuffer(I i10) throws DecoderException {
        synchronized (this.lock) {
            maybeThrowException();
            b.j(i10 == this.dequeuedInputBuffer);
            this.queuedInputBuffers.addLast(i10);
            maybeNotifyDecodeLoop();
            this.dequeuedInputBuffer = null;
        }
    }
}
