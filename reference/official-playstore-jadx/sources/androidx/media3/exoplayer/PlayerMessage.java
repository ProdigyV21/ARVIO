package androidx.media3.exoplayer;

import android.os.Looper;
import androidx.media3.common.C;
import androidx.media3.common.IllegalSeekPositionException;
import androidx.media3.common.Timeline;
import androidx.media3.common.util.Clock;
import java.util.concurrent.TimeoutException;

/* JADX INFO: loaded from: classes3.dex */
public final class PlayerMessage {
    private final Clock clock;
    private boolean isCanceled;
    private boolean isDelivered;
    private boolean isProcessed;
    private boolean isSent;
    private Looper looper;
    private int mediaItemIndex;
    private Object payload;
    private final Sender sender;
    private final Target target;
    private final Timeline timeline;
    private int type;
    private long positionMs = C.TIME_UNSET;
    private boolean deleteAfterDelivery = true;

    public interface Sender {
        void sendMessage(PlayerMessage playerMessage);
    }

    public interface Target {
        void handleMessage(int i10, Object obj) throws ExoPlaybackException;
    }

    public PlayerMessage(Sender sender, Target target, Timeline timeline, int i10, Clock clock, Looper looper) {
        this.sender = sender;
        this.target = target;
        this.timeline = timeline;
        this.looper = looper;
        this.clock = clock;
        this.mediaItemIndex = i10;
    }

    public synchronized boolean blockUntilDelivered() throws InterruptedException {
        try {
            ac.b.s(this.isSent);
            ac.b.s(this.looper.getThread() != Thread.currentThread());
            while (!this.isProcessed) {
                wait();
            }
        } catch (Throwable th) {
            throw th;
        }
        return this.isDelivered;
    }

    public synchronized PlayerMessage cancel() {
        ac.b.s(this.isSent);
        this.isCanceled = true;
        markAsProcessed(false);
        return this;
    }

    public boolean getDeleteAfterDelivery() {
        return this.deleteAfterDelivery;
    }

    public Looper getLooper() {
        return this.looper;
    }

    public int getMediaItemIndex() {
        return this.mediaItemIndex;
    }

    public Object getPayload() {
        return this.payload;
    }

    public long getPositionMs() {
        return this.positionMs;
    }

    public Target getTarget() {
        return this.target;
    }

    public Timeline getTimeline() {
        return this.timeline;
    }

    public int getType() {
        return this.type;
    }

    public synchronized boolean isCanceled() {
        return this.isCanceled;
    }

    public synchronized void markAsProcessed(boolean z) {
        this.isDelivered = z | this.isDelivered;
        this.isProcessed = true;
        notifyAll();
    }

    public PlayerMessage send() {
        ac.b.s(!this.isSent);
        if (this.positionMs == C.TIME_UNSET) {
            ac.b.j(this.deleteAfterDelivery);
        }
        this.isSent = true;
        this.sender.sendMessage(this);
        return this;
    }

    public PlayerMessage setDeleteAfterDelivery(boolean z) {
        ac.b.s(!this.isSent);
        this.deleteAfterDelivery = z;
        return this;
    }

    public PlayerMessage setLooper(Looper looper) {
        ac.b.s(!this.isSent);
        this.looper = looper;
        return this;
    }

    public PlayerMessage setPayload(Object obj) {
        ac.b.s(!this.isSent);
        this.payload = obj;
        return this;
    }

    public PlayerMessage setPosition(long j10) {
        ac.b.s(!this.isSent);
        this.positionMs = j10;
        return this;
    }

    public PlayerMessage setType(int i10) {
        ac.b.s(!this.isSent);
        this.type = i10;
        return this;
    }

    public PlayerMessage setPosition(int i10, long j10) {
        ac.b.s(!this.isSent);
        ac.b.j(j10 != C.TIME_UNSET);
        if (i10 >= 0 && (this.timeline.isEmpty() || i10 < this.timeline.getWindowCount())) {
            this.mediaItemIndex = i10;
            this.positionMs = j10;
            return this;
        }
        throw new IllegalSeekPositionException(this.timeline, i10, j10);
    }

    public synchronized boolean blockUntilDelivered(long j10) throws InterruptedException, TimeoutException {
        boolean z;
        try {
            ac.b.s(this.isSent);
            ac.b.s(this.looper.getThread() != Thread.currentThread());
            long jElapsedRealtime = this.clock.elapsedRealtime() + j10;
            while (true) {
                z = this.isProcessed;
                if (z || j10 <= 0) {
                    break;
                }
                this.clock.onThreadBlocked();
                wait(j10);
                j10 = jElapsedRealtime - this.clock.elapsedRealtime();
            }
            if (!z) {
                throw new TimeoutException("Message delivery timed out.");
            }
        } catch (Throwable th) {
            throw th;
        }
        return this.isDelivered;
    }
}
