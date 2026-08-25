package androidx.media3.session;

import android.os.Handler;
import androidx.media3.common.util.Log;
import androidx.media3.common.util.Util;
import java.util.ArrayList;
import java.util.Iterator;

/* JADX INFO: loaded from: classes3.dex */
final class SequencedFutureManager {
    private static final String TAG = "SequencedFutureManager";
    private boolean isReleased;
    private int nextSequenceNumber;
    private Runnable pendingLazyReleaseCallback;
    private Handler releaseCallbackHandler;
    private final Object lock = new Object();
    private final t.e seqToFutureMap = new t.e(0);

    public static final class SequencedFuture<T> extends com.google.common.util.concurrent.p {
        private final T resultWhenClosed;
        private final int sequenceNumber;

        private SequencedFuture(int i10, T t2) {
            this.sequenceNumber = i10;
            this.resultWhenClosed = t2;
        }

        public static <T> SequencedFuture<T> create(int i10, T t2) {
            return new SequencedFuture<>(i10, t2);
        }

        public T getResultWhenClosed() {
            return this.resultWhenClosed;
        }

        public int getSequenceNumber() {
            return this.sequenceNumber;
        }

        @Override // com.google.common.util.concurrent.p
        public boolean set(T t2) {
            return super.set(t2);
        }

        public void setWithTheValueOfResultWhenClosed() {
            set(this.resultWhenClosed);
        }
    }

    public <T> SequencedFuture<T> createSequencedFuture(T t2) {
        SequencedFuture<T> sequencedFutureCreate;
        synchronized (this.lock) {
            try {
                int iObtainNextSequenceNumber = obtainNextSequenceNumber();
                sequencedFutureCreate = SequencedFuture.create(iObtainNextSequenceNumber, t2);
                if (this.isReleased) {
                    sequencedFutureCreate.setWithTheValueOfResultWhenClosed();
                } else {
                    this.seqToFutureMap.put(Integer.valueOf(iObtainNextSequenceNumber), sequencedFutureCreate);
                }
            } catch (Throwable th) {
                throw th;
            }
        }
        return sequencedFutureCreate;
    }

    public void lazyRelease(long j10, Runnable runnable) {
        synchronized (this.lock) {
            try {
                Handler handlerCreateHandlerForCurrentLooper = Util.createHandlerForCurrentLooper();
                this.releaseCallbackHandler = handlerCreateHandlerForCurrentLooper;
                this.pendingLazyReleaseCallback = runnable;
                if (this.seqToFutureMap.isEmpty()) {
                    release();
                } else {
                    handlerCreateHandlerForCurrentLooper.postDelayed(new j3(this, 0), j10);
                }
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    public int obtainNextSequenceNumber() {
        int i10;
        synchronized (this.lock) {
            i10 = this.nextSequenceNumber;
            this.nextSequenceNumber = i10 + 1;
        }
        return i10;
    }

    public void release() {
        ArrayList arrayList;
        synchronized (this.lock) {
            try {
                this.isReleased = true;
                arrayList = new ArrayList(this.seqToFutureMap.values());
                this.seqToFutureMap.clear();
                if (this.pendingLazyReleaseCallback != null) {
                    Handler handler = this.releaseCallbackHandler;
                    handler.getClass();
                    handler.post(this.pendingLazyReleaseCallback);
                    this.pendingLazyReleaseCallback = null;
                    this.releaseCallbackHandler = null;
                }
            } catch (Throwable th) {
                throw th;
            }
        }
        Iterator it = arrayList.iterator();
        while (it.hasNext()) {
            ((SequencedFuture) it.next()).setWithTheValueOfResultWhenClosed();
        }
    }

    public <T> void setFutureResult(int i10, T t2) {
        synchronized (this.lock) {
            try {
                SequencedFuture sequencedFuture = (SequencedFuture) this.seqToFutureMap.remove(Integer.valueOf(i10));
                if (sequencedFuture != null) {
                    if (sequencedFuture.getResultWhenClosed().getClass() == t2.getClass()) {
                        sequencedFuture.set(t2);
                    } else {
                        Log.w(TAG, "Type mismatch, expected " + sequencedFuture.getResultWhenClosed().getClass() + ", but was " + t2.getClass());
                    }
                }
                if (this.pendingLazyReleaseCallback != null && this.seqToFutureMap.isEmpty()) {
                    release();
                }
            } catch (Throwable th) {
                throw th;
            }
        }
    }
}
