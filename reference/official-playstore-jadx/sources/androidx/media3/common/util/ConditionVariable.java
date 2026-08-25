package androidx.media3.common.util;

/* JADX INFO: loaded from: classes3.dex */
public class ConditionVariable {
    private final Clock clock;
    private boolean isOpen;

    public ConditionVariable() {
        this(Clock.DEFAULT);
    }

    public synchronized void block() throws InterruptedException {
        while (!this.isOpen) {
            this.clock.onThreadBlocked();
            wait();
        }
    }

    public synchronized void blockUninterruptible() {
        boolean z = false;
        while (!this.isOpen) {
            try {
                this.clock.onThreadBlocked();
                wait();
            } catch (InterruptedException unused) {
                z = true;
            }
        }
        if (z) {
            Thread.currentThread().interrupt();
        }
    }

    public synchronized boolean close() {
        boolean z;
        z = this.isOpen;
        this.isOpen = false;
        return z;
    }

    public synchronized boolean isOpen() {
        return this.isOpen;
    }

    public synchronized boolean open() {
        if (this.isOpen) {
            return false;
        }
        this.isOpen = true;
        notifyAll();
        return true;
    }

    public ConditionVariable(Clock clock) {
        this.clock = clock;
    }

    public synchronized boolean block(long j10) throws InterruptedException {
        if (j10 <= 0) {
            return this.isOpen;
        }
        long jElapsedRealtime = this.clock.elapsedRealtime();
        long j11 = j10 + jElapsedRealtime;
        if (j11 < jElapsedRealtime) {
            block();
        } else {
            while (!this.isOpen && jElapsedRealtime < j11) {
                this.clock.onThreadBlocked();
                wait(j11 - jElapsedRealtime);
                jElapsedRealtime = this.clock.elapsedRealtime();
            }
        }
        return this.isOpen;
    }

    public synchronized boolean blockUninterruptible(long j10) {
        if (j10 <= 0) {
            return this.isOpen;
        }
        long jElapsedRealtime = this.clock.elapsedRealtime();
        long j11 = j10 + jElapsedRealtime;
        if (j11 < jElapsedRealtime) {
            blockUninterruptible();
        } else {
            boolean z = false;
            while (!this.isOpen && jElapsedRealtime < j11) {
                try {
                    this.clock.onThreadBlocked();
                    wait(j11 - jElapsedRealtime);
                } catch (InterruptedException unused) {
                    z = true;
                }
                jElapsedRealtime = this.clock.elapsedRealtime();
            }
            if (z) {
                Thread.currentThread().interrupt();
            }
        }
        return this.isOpen;
    }
}
