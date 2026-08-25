package androidx.media3.exoplayer.source;

import android.os.Handler;
import androidx.media3.common.C;
import androidx.media3.common.Format;
import androidx.media3.common.util.Consumer;
import androidx.media3.common.util.Util;
import androidx.media3.exoplayer.analytics.w;
import androidx.media3.exoplayer.source.MediaSource;
import java.io.IOException;
import java.util.concurrent.CopyOnWriteArrayList;

/* JADX INFO: loaded from: classes3.dex */
public interface MediaSourceEventListener {

    public static class EventDispatcher {
        private final CopyOnWriteArrayList<ListenerAndHandler> listenerAndHandlers;
        public final MediaSource.MediaPeriodId mediaPeriodId;
        public final int windowIndex;

        public static final class ListenerAndHandler {
            public Handler handler;
            public MediaSourceEventListener listener;

            public ListenerAndHandler(Handler handler, MediaSourceEventListener mediaSourceEventListener) {
                this.handler = handler;
                this.listener = mediaSourceEventListener;
            }
        }

        public EventDispatcher() {
            this(new CopyOnWriteArrayList(), 0, null);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public /* synthetic */ void lambda$downstreamFormatChanged$5(MediaLoadData mediaLoadData, MediaSourceEventListener mediaSourceEventListener) {
            mediaSourceEventListener.onDownstreamFormatChanged(this.windowIndex, this.mediaPeriodId, mediaLoadData);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public /* synthetic */ void lambda$loadCanceled$2(LoadEventInfo loadEventInfo, MediaLoadData mediaLoadData, MediaSourceEventListener mediaSourceEventListener) {
            mediaSourceEventListener.onLoadCanceled(this.windowIndex, this.mediaPeriodId, loadEventInfo, mediaLoadData);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public /* synthetic */ void lambda$loadCompleted$1(LoadEventInfo loadEventInfo, MediaLoadData mediaLoadData, MediaSourceEventListener mediaSourceEventListener) {
            mediaSourceEventListener.onLoadCompleted(this.windowIndex, this.mediaPeriodId, loadEventInfo, mediaLoadData);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public /* synthetic */ void lambda$loadError$3(LoadEventInfo loadEventInfo, MediaLoadData mediaLoadData, IOException iOException, boolean z, MediaSourceEventListener mediaSourceEventListener) {
            mediaSourceEventListener.onLoadError(this.windowIndex, this.mediaPeriodId, loadEventInfo, mediaLoadData, iOException, z);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public /* synthetic */ void lambda$loadStarted$0(LoadEventInfo loadEventInfo, MediaLoadData mediaLoadData, int i10, MediaSourceEventListener mediaSourceEventListener) {
            mediaSourceEventListener.onLoadStarted(this.windowIndex, this.mediaPeriodId, loadEventInfo, mediaLoadData, i10);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public /* synthetic */ void lambda$upstreamDiscarded$4(MediaSource.MediaPeriodId mediaPeriodId, MediaLoadData mediaLoadData, MediaSourceEventListener mediaSourceEventListener) {
            mediaSourceEventListener.onUpstreamDiscarded(this.windowIndex, mediaPeriodId, mediaLoadData);
        }

        public void addEventListener(Handler handler, MediaSourceEventListener mediaSourceEventListener) {
            handler.getClass();
            mediaSourceEventListener.getClass();
            this.listenerAndHandlers.add(new ListenerAndHandler(handler, mediaSourceEventListener));
        }

        public void dispatchEvent(Consumer<MediaSourceEventListener> consumer) {
            for (ListenerAndHandler listenerAndHandler : this.listenerAndHandlers) {
                Util.postOrRun(listenerAndHandler.handler, new m(consumer, listenerAndHandler.listener, 1));
            }
        }

        public void downstreamFormatChanged(int i10, Format format, int i11, Object obj, long j10) {
            downstreamFormatChanged(new MediaLoadData(1, i10, format, i11, obj, Util.usToMs(j10), C.TIME_UNSET));
        }

        public void loadCanceled(LoadEventInfo loadEventInfo, int i10) {
            loadCanceled(loadEventInfo, i10, -1, null, 0, null, C.TIME_UNSET, C.TIME_UNSET);
        }

        public void loadCompleted(LoadEventInfo loadEventInfo, int i10) {
            loadCompleted(loadEventInfo, i10, -1, null, 0, null, C.TIME_UNSET, C.TIME_UNSET);
        }

        public void loadError(LoadEventInfo loadEventInfo, int i10, IOException iOException, boolean z) {
            loadError(loadEventInfo, i10, -1, null, 0, null, C.TIME_UNSET, C.TIME_UNSET, iOException, z);
        }

        @Deprecated
        public void loadStarted(LoadEventInfo loadEventInfo, int i10) {
            loadStarted(loadEventInfo, i10, 0);
        }

        public void removeEventListener(MediaSourceEventListener mediaSourceEventListener) {
            for (ListenerAndHandler listenerAndHandler : this.listenerAndHandlers) {
                if (listenerAndHandler.listener == mediaSourceEventListener) {
                    this.listenerAndHandlers.remove(listenerAndHandler);
                }
            }
        }

        public void upstreamDiscarded(int i10, long j10, long j11) {
            upstreamDiscarded(new MediaLoadData(1, i10, null, 3, null, Util.usToMs(j10), Util.usToMs(j11)));
        }

        public EventDispatcher withParameters(int i10, MediaSource.MediaPeriodId mediaPeriodId) {
            return new EventDispatcher(this.listenerAndHandlers, i10, mediaPeriodId);
        }

        private EventDispatcher(CopyOnWriteArrayList<ListenerAndHandler> copyOnWriteArrayList, int i10, MediaSource.MediaPeriodId mediaPeriodId) {
            this.listenerAndHandlers = copyOnWriteArrayList;
            this.windowIndex = i10;
            this.mediaPeriodId = mediaPeriodId;
        }

        public void loadCanceled(LoadEventInfo loadEventInfo, int i10, int i11, Format format, int i12, Object obj, long j10, long j11) {
            loadCanceled(loadEventInfo, new MediaLoadData(i10, i11, format, i12, obj, Util.usToMs(j10), Util.usToMs(j11)));
        }

        public void loadCompleted(LoadEventInfo loadEventInfo, int i10, int i11, Format format, int i12, Object obj, long j10, long j11) {
            loadCompleted(loadEventInfo, new MediaLoadData(i10, i11, format, i12, obj, Util.usToMs(j10), Util.usToMs(j11)));
        }

        public void loadError(LoadEventInfo loadEventInfo, int i10, int i11, Format format, int i12, Object obj, long j10, long j11, IOException iOException, boolean z) {
            loadError(loadEventInfo, new MediaLoadData(i10, i11, format, i12, obj, Util.usToMs(j10), Util.usToMs(j11)), iOException, z);
        }

        public void loadStarted(LoadEventInfo loadEventInfo, int i10, int i11) {
            loadStarted(loadEventInfo, i10, -1, null, 0, null, C.TIME_UNSET, C.TIME_UNSET, i11);
        }

        @Deprecated
        public EventDispatcher withParameters(int i10, MediaSource.MediaPeriodId mediaPeriodId, long j10) {
            return new EventDispatcher(this.listenerAndHandlers, i10, mediaPeriodId);
        }

        @Deprecated
        public void loadStarted(LoadEventInfo loadEventInfo, int i10, int i11, Format format, int i12, Object obj, long j10, long j11) {
            loadStarted(loadEventInfo, new MediaLoadData(i10, i11, format, i12, obj, Util.usToMs(j10), Util.usToMs(j11)));
        }

        public void downstreamFormatChanged(MediaLoadData mediaLoadData) {
            dispatchEvent(new c(this, mediaLoadData));
        }

        public void upstreamDiscarded(MediaLoadData mediaLoadData) {
            MediaSource.MediaPeriodId mediaPeriodId = this.mediaPeriodId;
            mediaPeriodId.getClass();
            dispatchEvent(new androidx.media3.exoplayer.hls.b(this, mediaPeriodId, mediaLoadData, 2));
        }

        public void loadCanceled(LoadEventInfo loadEventInfo, MediaLoadData mediaLoadData) {
            dispatchEvent(new j(this, loadEventInfo, mediaLoadData, 1));
        }

        public void loadCompleted(LoadEventInfo loadEventInfo, MediaLoadData mediaLoadData) {
            dispatchEvent(new j(this, loadEventInfo, mediaLoadData, 0));
        }

        public void loadError(LoadEventInfo loadEventInfo, MediaLoadData mediaLoadData, IOException iOException, boolean z) {
            dispatchEvent(new androidx.media3.exoplayer.analytics.l(this, loadEventInfo, mediaLoadData, iOException, z));
        }

        public void loadStarted(LoadEventInfo loadEventInfo, int i10, int i11, Format format, int i12, Object obj, long j10, long j11, int i13) {
            loadStarted(loadEventInfo, new MediaLoadData(i10, i11, format, i12, obj, Util.usToMs(j10), Util.usToMs(j11)), i13);
        }

        @Deprecated
        public void loadStarted(LoadEventInfo loadEventInfo, MediaLoadData mediaLoadData) {
            loadStarted(loadEventInfo, mediaLoadData, 0);
        }

        public void loadStarted(LoadEventInfo loadEventInfo, MediaLoadData mediaLoadData, int i10) {
            dispatchEvent(new w(this, loadEventInfo, mediaLoadData, i10, 2));
        }
    }

    void onDownstreamFormatChanged(int i10, MediaSource.MediaPeriodId mediaPeriodId, MediaLoadData mediaLoadData);

    void onLoadCanceled(int i10, MediaSource.MediaPeriodId mediaPeriodId, LoadEventInfo loadEventInfo, MediaLoadData mediaLoadData);

    void onLoadCompleted(int i10, MediaSource.MediaPeriodId mediaPeriodId, LoadEventInfo loadEventInfo, MediaLoadData mediaLoadData);

    void onLoadError(int i10, MediaSource.MediaPeriodId mediaPeriodId, LoadEventInfo loadEventInfo, MediaLoadData mediaLoadData, IOException iOException, boolean z);

    void onLoadStarted(int i10, MediaSource.MediaPeriodId mediaPeriodId, LoadEventInfo loadEventInfo, MediaLoadData mediaLoadData, int i11);

    void onUpstreamDiscarded(int i10, MediaSource.MediaPeriodId mediaPeriodId, MediaLoadData mediaLoadData);
}
