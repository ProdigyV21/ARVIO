package androidx.media3.common;

import androidx.compose.foundation.c;
import androidx.media3.common.util.Util;
import java.io.IOException;
import java.util.Collections;
import java.util.PriorityQueue;

/* JADX INFO: loaded from: classes3.dex */
public final class PriorityTaskManager {
    private final Object lock = new Object();
    private final PriorityQueue<Integer> queue = new PriorityQueue<>(10, Collections.reverseOrder());
    private int highestPriority = Integer.MIN_VALUE;

    public static class PriorityTooLowException extends IOException {
        public PriorityTooLowException(int i10, int i11) {
            super(c.s("Priority too low [priority=", i10, ", highest=", i11, "]"));
        }
    }

    public void add(int i10) {
        synchronized (this.lock) {
            this.queue.add(Integer.valueOf(i10));
            this.highestPriority = Math.max(this.highestPriority, i10);
        }
    }

    public void proceed(int i10) throws InterruptedException {
        synchronized (this.lock) {
            while (this.highestPriority != i10) {
                try {
                    this.lock.wait();
                } catch (Throwable th) {
                    throw th;
                }
            }
        }
    }

    public boolean proceedNonBlocking(int i10) {
        boolean z;
        synchronized (this.lock) {
            z = this.highestPriority == i10;
        }
        return z;
    }

    public void proceedOrThrow(int i10) throws PriorityTooLowException {
        synchronized (this.lock) {
            try {
                if (this.highestPriority != i10) {
                    throw new PriorityTooLowException(i10, this.highestPriority);
                }
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    public void remove(int i10) {
        synchronized (this.lock) {
            this.queue.remove(Integer.valueOf(i10));
            this.highestPriority = this.queue.isEmpty() ? Integer.MIN_VALUE : ((Integer) Util.castNonNull(this.queue.peek())).intValue();
            this.lock.notifyAll();
        }
    }
}
