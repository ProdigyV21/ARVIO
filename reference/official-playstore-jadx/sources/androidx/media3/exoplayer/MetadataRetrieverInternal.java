package androidx.media3.exoplayer;

import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import android.os.Message;
import androidx.media3.common.C;
import androidx.media3.common.MediaItem;
import androidx.media3.common.Timeline;
import androidx.media3.common.util.Clock;
import androidx.media3.common.util.HandlerWrapper;
import androidx.media3.exoplayer.LoadingInfo;
import androidx.media3.exoplayer.analytics.PlayerId;
import androidx.media3.exoplayer.source.MediaPeriod;
import androidx.media3.exoplayer.source.MediaSource;
import androidx.media3.exoplayer.source.TrackGroupArray;
import androidx.media3.exoplayer.upstream.Allocator;
import androidx.media3.exoplayer.upstream.DefaultAllocator;
import com.google.common.collect.h1;
import com.google.common.util.concurrent.d1;
import com.google.common.util.concurrent.q1;
import java.io.IOException;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

/* JADX INFO: loaded from: classes3.dex */
public final class MetadataRetrieverInternal implements AutoCloseable {
    private final Clock clock;
    private final MediaItem mediaItem;
    private final MediaSource.Factory mediaSourceFactory;
    private q1 preparationFuture;
    private boolean released;
    private RetrievalTask retrievalTask;
    private final Object lock = new Object();
    private final List<d1<?>> allFutures = new ArrayList();

    public static final class InternalResult {
        public final Timeline timeline;
        public final TrackGroupArray trackGroups;

        public InternalResult(TrackGroupArray trackGroupArray, Timeline timeline) {
            this.trackGroups = trackGroupArray;
            this.timeline = timeline;
        }
    }

    public static final class RetrievalTask {
        private static final int MESSAGE_CHECK_FOR_FAILURE = 2;
        private static final int MESSAGE_CONTINUE_LOADING = 3;
        private static final int MESSAGE_PREPARE_SOURCE = 1;
        private static final int MESSAGE_RELEASE = 4;
        private static final SharedWorkerThread SHARED_WORKER_THREAD = new SharedWorkerThread();
        private final MediaItem mediaItem;
        private final MediaSource.Factory mediaSourceFactory;
        private final HandlerWrapper mediaSourceHandler;
        private final OnFailureListener onFailureListener;
        private final OnPreparedListener onPreparedListener;
        private boolean sentReleaseMessage;

        public final class MediaSourceHandlerCallback implements Handler.Callback {
            private static final int ERROR_POLL_INTERVAL_MS = 100;
            private MediaPeriod mediaPeriod;
            private MediaSource mediaSource;
            private final MediaSourceCaller mediaSourceCaller = new MediaSourceCaller();
            private boolean released;
            private Timeline timeline;

            public final class MediaSourceCaller implements MediaSource.MediaSourceCaller {
                private boolean mediaPeriodCreated;
                private final MediaPeriodCallback mediaPeriodCallback = new MediaPeriodCallback();
                private final Allocator allocator = new DefaultAllocator(true, 65536);

                public final class MediaPeriodCallback implements MediaPeriod.Callback {
                    private MediaPeriodCallback() {
                    }

                    @Override // androidx.media3.exoplayer.source.MediaPeriod.Callback
                    public void onPrepared(MediaPeriod mediaPeriod) {
                        OnPreparedListener onPreparedListener = RetrievalTask.this.onPreparedListener;
                        TrackGroupArray trackGroups = mediaPeriod.getTrackGroups();
                        Timeline timeline = MediaSourceHandlerCallback.this.timeline;
                        timeline.getClass();
                        onPreparedListener.onPrepared(trackGroups, timeline);
                        RetrievalTask.this.release();
                    }

                    @Override // androidx.media3.exoplayer.source.SequenceableLoader.Callback
                    public void onContinueLoadingRequested(MediaPeriod mediaPeriod) {
                        RetrievalTask.this.mediaSourceHandler.obtainMessage(3).sendToTarget();
                    }
                }

                public MediaSourceCaller() {
                }

                @Override // androidx.media3.exoplayer.source.MediaSource.MediaSourceCaller
                public void onSourceInfoRefreshed(MediaSource mediaSource, Timeline timeline) {
                    MediaSourceHandlerCallback.this.timeline = timeline;
                    if (this.mediaPeriodCreated) {
                        return;
                    }
                    this.mediaPeriodCreated = true;
                    MediaSourceHandlerCallback.this.mediaPeriod = mediaSource.createPeriod(new MediaSource.MediaPeriodId(timeline.getUidOfPeriod(0)), this.allocator, 0L);
                    MediaSourceHandlerCallback.this.mediaPeriod.prepare(this.mediaPeriodCallback, 0L);
                }
            }

            public MediaSourceHandlerCallback() {
            }

            @Override // android.os.Handler.Callback
            public boolean handleMessage(Message message) {
                if (this.released) {
                    return true;
                }
                int i10 = message.what;
                if (i10 == 1) {
                    MediaSource mediaSourceCreateMediaSource = RetrievalTask.this.mediaSourceFactory.createMediaSource((MediaItem) message.obj);
                    this.mediaSource = mediaSourceCreateMediaSource;
                    mediaSourceCreateMediaSource.prepareSource(this.mediaSourceCaller, null, PlayerId.UNSET);
                    RetrievalTask.this.mediaSourceHandler.sendEmptyMessage(2);
                    return true;
                }
                if (i10 == 2) {
                    try {
                        MediaPeriod mediaPeriod = this.mediaPeriod;
                        if (mediaPeriod == null) {
                            MediaSource mediaSource = this.mediaSource;
                            mediaSource.getClass();
                            mediaSource.maybeThrowSourceInfoRefreshError();
                        } else {
                            mediaPeriod.maybeThrowPrepareError();
                        }
                        RetrievalTask.this.mediaSourceHandler.sendEmptyMessageDelayed(2, 100);
                        return true;
                    } catch (IOException e5) {
                        RetrievalTask.this.onFailureListener.onFailure(e5);
                        RetrievalTask.this.release();
                        return true;
                    }
                }
                if (i10 == 3) {
                    MediaPeriod mediaPeriod2 = this.mediaPeriod;
                    mediaPeriod2.getClass();
                    mediaPeriod2.continueLoading(new LoadingInfo.Builder().setPlaybackPositionUs(0L).build());
                    return true;
                }
                if (i10 != 4) {
                    return false;
                }
                if (this.mediaPeriod != null) {
                    MediaSource mediaSource2 = this.mediaSource;
                    mediaSource2.getClass();
                    mediaSource2.releasePeriod(this.mediaPeriod);
                }
                MediaSource mediaSource3 = this.mediaSource;
                if (mediaSource3 != null) {
                    mediaSource3.releaseSource(this.mediaSourceCaller);
                }
                RetrievalTask.this.mediaSourceHandler.removeCallbacksAndMessages(null);
                RetrievalTask.SHARED_WORKER_THREAD.removeWorker();
                this.released = true;
                return true;
            }
        }

        public interface OnFailureListener {
            void onFailure(Exception exc);
        }

        public interface OnPreparedListener {
            void onPrepared(TrackGroupArray trackGroupArray, Timeline timeline);
        }

        public RetrievalTask(MediaSource.Factory factory, MediaItem mediaItem, Clock clock, OnPreparedListener onPreparedListener, OnFailureListener onFailureListener) {
            this.mediaSourceFactory = factory;
            this.mediaItem = mediaItem;
            this.onPreparedListener = onPreparedListener;
            this.onFailureListener = onFailureListener;
            this.mediaSourceHandler = clock.createHandler(SHARED_WORKER_THREAD.addWorker(), new MediaSourceHandlerCallback());
        }

        public void queueRetrieval() {
            SHARED_WORKER_THREAD.startRetrieval(this);
        }

        public synchronized void release() {
            if (!this.sentReleaseMessage) {
                this.sentReleaseMessage = true;
                this.mediaSourceHandler.obtainMessage(4).sendToTarget();
            }
        }

        public void start() {
            this.mediaSourceHandler.obtainMessage(1, this.mediaItem).sendToTarget();
        }
    }

    public static final class SharedWorkerThread {
        public static final AtomicInteger MAX_PARALLEL_RETRIEVALS = new AtomicInteger(5);
        private HandlerThread mediaSourceThread;
        private final Deque<RetrievalTask> pendingRetrievals;
        private int referenceCount;

        private void maybeStartNewRetrieval() {
            if (!this.pendingRetrievals.isEmpty() && this.referenceCount - this.pendingRetrievals.size() < MAX_PARALLEL_RETRIEVALS.get()) {
                this.pendingRetrievals.removeFirst().start();
            }
        }

        public synchronized Looper addWorker() {
            HandlerThread handlerThread;
            try {
                if (this.mediaSourceThread == null) {
                    ac.b.s(this.referenceCount == 0);
                    HandlerThread handlerThread2 = new HandlerThread("ExoPlayer:MetadataRetriever");
                    this.mediaSourceThread = handlerThread2;
                    handlerThread2.start();
                }
                this.referenceCount++;
                handlerThread = this.mediaSourceThread;
                handlerThread.getClass();
            } catch (Throwable th) {
                throw th;
            }
            return handlerThread.getLooper();
        }

        public synchronized void removeWorker() {
            try {
                int i10 = this.referenceCount - 1;
                this.referenceCount = i10;
                if (i10 == 0) {
                    HandlerThread handlerThread = this.mediaSourceThread;
                    handlerThread.getClass();
                    handlerThread.quit();
                    this.mediaSourceThread = null;
                    this.pendingRetrievals.clear();
                } else {
                    maybeStartNewRetrieval();
                }
            } catch (Throwable th) {
                throw th;
            }
        }

        public synchronized void startRetrieval(RetrievalTask retrievalTask) {
            this.pendingRetrievals.addLast(retrievalTask);
            maybeStartNewRetrieval();
        }

        private SharedWorkerThread() {
            this.pendingRetrievals = new ArrayDeque();
        }
    }

    public MetadataRetrieverInternal(MediaItem mediaItem, MediaSource.Factory factory, Clock clock) {
        this.mediaItem = mediaItem;
        this.mediaSourceFactory = factory;
        this.clock = clock;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$close$0() {
        synchronized (this.lock) {
            try {
                RetrievalTask retrievalTask = this.retrievalTask;
                if (retrievalTask != null) {
                    retrievalTask.release();
                }
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void lambda$startPreparation$1(TrackGroupArray trackGroupArray, Timeline timeline) {
        synchronized (this.lock) {
            q1 q1Var = this.preparationFuture;
            q1Var.getClass();
            q1Var.set(new InternalResult(trackGroupArray, timeline));
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void lambda$startPreparation$2(Exception exc) {
        synchronized (this.lock) {
            q1 q1Var = this.preparationFuture;
            q1Var.getClass();
            q1Var.setException(exc);
        }
    }

    private void startPreparation() {
        if (this.preparationFuture == null) {
            this.preparationFuture = new q1();
            RetrievalTask retrievalTask = new RetrievalTask(this.mediaSourceFactory, this.mediaItem, this.clock, new r0(this), new r0(this));
            this.retrievalTask = retrievalTask;
            retrievalTask.queueRetrieval();
        }
    }

    @Override // java.lang.AutoCloseable
    public void close() {
        synchronized (this.lock) {
            try {
                if (this.released) {
                    return;
                }
                this.released = true;
                a8.e eVarM0 = xc.d.M0(this.allFutures);
                new com.google.common.util.concurrent.n0((h1) eVarM0.f183l, new androidx.loader.content.g(new a(this, 4), 1));
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    public d1<Long> retrieveDurationUs() {
        synchronized (this.lock) {
            try {
                if (this.released) {
                    return xc.d.r0(new IllegalStateException("Retriever is released."));
                }
                d1<Timeline> d1VarRetrieveTimeline = retrieveTimeline();
                final q1 q1Var = new q1();
                this.allFutures.add(q1Var);
                xc.d.G(d1VarRetrieveTimeline, new com.google.common.util.concurrent.v0<Timeline>() { // from class: androidx.media3.exoplayer.MetadataRetrieverInternal.3
                    @Override // com.google.common.util.concurrent.v0
                    public void onFailure(Throwable th) {
                        q1Var.setException(th);
                    }

                    @Override // com.google.common.util.concurrent.v0
                    public void onSuccess(Timeline timeline) {
                        if (timeline.isEmpty()) {
                            q1Var.set(Long.valueOf(C.TIME_UNSET));
                        } else {
                            q1Var.set(Long.valueOf(timeline.getWindow(0, new Timeline.Window()).getDurationUs()));
                        }
                    }
                }, com.google.common.util.concurrent.s0.f14233i);
                return q1Var;
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    public d1<Timeline> retrieveTimeline() {
        synchronized (this.lock) {
            try {
                if (this.released) {
                    return xc.d.r0(new IllegalStateException("Retriever is released."));
                }
                startPreparation();
                final q1 q1Var = new q1();
                this.allFutures.add(q1Var);
                q1 q1Var2 = this.preparationFuture;
                q1Var2.getClass();
                xc.d.G(q1Var2, new com.google.common.util.concurrent.v0<InternalResult>() { // from class: androidx.media3.exoplayer.MetadataRetrieverInternal.2
                    @Override // com.google.common.util.concurrent.v0
                    public void onFailure(Throwable th) {
                        q1Var.setException(th);
                    }

                    @Override // com.google.common.util.concurrent.v0
                    public void onSuccess(InternalResult internalResult) {
                        q1Var.set(internalResult.timeline);
                    }
                }, com.google.common.util.concurrent.s0.f14233i);
                return q1Var;
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    public d1<TrackGroupArray> retrieveTrackGroups() {
        synchronized (this.lock) {
            try {
                if (this.released) {
                    return xc.d.r0(new IllegalStateException("Retriever is released."));
                }
                startPreparation();
                final q1 q1Var = new q1();
                this.allFutures.add(q1Var);
                q1 q1Var2 = this.preparationFuture;
                q1Var2.getClass();
                xc.d.G(q1Var2, new com.google.common.util.concurrent.v0<InternalResult>() { // from class: androidx.media3.exoplayer.MetadataRetrieverInternal.1
                    @Override // com.google.common.util.concurrent.v0
                    public void onFailure(Throwable th) {
                        q1Var.setException(th);
                    }

                    @Override // com.google.common.util.concurrent.v0
                    public void onSuccess(InternalResult internalResult) {
                        q1Var.set(internalResult.trackGroups);
                    }
                }, com.google.common.util.concurrent.s0.f14233i);
                return q1Var;
            } catch (Throwable th) {
                throw th;
            }
        }
    }
}
