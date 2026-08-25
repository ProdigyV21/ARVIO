package androidx.media3.exoplayer.video;

import android.os.Handler;
import android.os.SystemClock;
import androidx.media3.common.Format;
import androidx.media3.common.VideoSize;
import androidx.media3.common.util.Util;
import androidx.media3.exoplayer.CodecParameters;
import androidx.media3.exoplayer.DecoderCounters;
import androidx.media3.exoplayer.DecoderReuseEvaluation;

/* JADX INFO: loaded from: classes3.dex */
public interface VideoRendererEventListener {

    public static final class EventDispatcher {
        private final Handler handler;
        private final VideoRendererEventListener listener;

        public EventDispatcher(Handler handler, VideoRendererEventListener videoRendererEventListener) {
            if (videoRendererEventListener != null) {
                handler.getClass();
            } else {
                handler = null;
            }
            this.handler = handler;
            this.listener = videoRendererEventListener;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public /* synthetic */ void lambda$decoderInitialized$1(String str, long j10, long j11) {
            ((VideoRendererEventListener) Util.castNonNull(this.listener)).onVideoDecoderInitialized(str, j10, j11);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public /* synthetic */ void lambda$decoderReleased$7(String str) {
            ((VideoRendererEventListener) Util.castNonNull(this.listener)).onVideoDecoderReleased(str);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public /* synthetic */ void lambda$disabled$8(DecoderCounters decoderCounters) {
            decoderCounters.ensureUpdated();
            ((VideoRendererEventListener) Util.castNonNull(this.listener)).onVideoDisabled(decoderCounters);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public /* synthetic */ void lambda$droppedFrames$3(int i10, long j10) {
            ((VideoRendererEventListener) Util.castNonNull(this.listener)).onDroppedFrames(i10, j10);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public /* synthetic */ void lambda$enabled$0(DecoderCounters decoderCounters) {
            ((VideoRendererEventListener) Util.castNonNull(this.listener)).onVideoEnabled(decoderCounters);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public /* synthetic */ void lambda$inputFormatChanged$2(Format format, DecoderReuseEvaluation decoderReuseEvaluation) {
            ((VideoRendererEventListener) Util.castNonNull(this.listener)).onVideoInputFormatChanged(format, decoderReuseEvaluation);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public /* synthetic */ void lambda$renderedFirstFrame$6(Object obj, long j10) {
            ((VideoRendererEventListener) Util.castNonNull(this.listener)).onRenderedFirstFrame(obj, j10);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public /* synthetic */ void lambda$reportVideoFrameProcessingOffset$4(long j10, int i10) {
            ((VideoRendererEventListener) Util.castNonNull(this.listener)).onVideoFrameProcessingOffset(j10, i10);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public /* synthetic */ void lambda$videoCodecError$9(Exception exc) {
            ((VideoRendererEventListener) Util.castNonNull(this.listener)).onVideoCodecError(exc);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public /* synthetic */ void lambda$videoCodecParametersChanged$10(CodecParameters codecParameters) {
            ((VideoRendererEventListener) Util.castNonNull(this.listener)).onVideoCodecParametersChanged(codecParameters);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public /* synthetic */ void lambda$videoSizeChanged$5(VideoSize videoSize) {
            ((VideoRendererEventListener) Util.castNonNull(this.listener)).onVideoSizeChanged(videoSize);
        }

        public void decoderInitialized(String str, long j10, long j11) {
            Handler handler = this.handler;
            if (handler != null) {
                handler.post(new androidx.media3.exoplayer.audio.k(this, str, j10, j11, 1));
            }
        }

        public void decoderReleased(String str) {
            Handler handler = this.handler;
            if (handler != null) {
                handler.post(new e(this, str, 2));
            }
        }

        public void disabled(DecoderCounters decoderCounters) {
            decoderCounters.ensureUpdated();
            Handler handler = this.handler;
            if (handler != null) {
                handler.post(new m(this, decoderCounters, 1));
            }
        }

        public void droppedFrames(int i10, long j10) {
            Handler handler = this.handler;
            if (handler != null) {
                handler.post(new k(i10, j10, this));
            }
        }

        public void enabled(DecoderCounters decoderCounters) {
            Handler handler = this.handler;
            if (handler != null) {
                handler.post(new m(this, decoderCounters, 0));
            }
        }

        public void inputFormatChanged(Format format, DecoderReuseEvaluation decoderReuseEvaluation) {
            Handler handler = this.handler;
            if (handler != null) {
                handler.post(new f(this, format, decoderReuseEvaluation, 1));
            }
        }

        public void renderedFirstFrame(Object obj) {
            if (this.handler != null) {
                this.handler.post(new l(this, obj, SystemClock.elapsedRealtime(), 0));
            }
        }

        public void reportVideoFrameProcessingOffset(long j10, int i10) {
            Handler handler = this.handler;
            if (handler != null) {
                handler.post(new k(this, i10, 1, j10));
            }
        }

        public void videoCodecError(Exception exc) {
            Handler handler = this.handler;
            if (handler != null) {
                handler.post(new e(this, exc, 4));
            }
        }

        public void videoCodecParametersChanged(CodecParameters codecParameters) {
            Handler handler = this.handler;
            if (handler != null) {
                handler.post(new e(this, codecParameters, 5));
            }
        }

        public void videoSizeChanged(VideoSize videoSize) {
            Handler handler = this.handler;
            if (handler != null) {
                handler.post(new e(this, videoSize, 3));
            }
        }
    }

    void onDroppedFrames(int i10, long j10);

    void onRenderedFirstFrame(Object obj, long j10);

    void onVideoCodecError(Exception exc);

    void onVideoCodecParametersChanged(CodecParameters codecParameters);

    void onVideoDecoderInitialized(String str, long j10, long j11);

    void onVideoDecoderReleased(String str);

    void onVideoDisabled(DecoderCounters decoderCounters);

    void onVideoEnabled(DecoderCounters decoderCounters);

    void onVideoFrameProcessingOffset(long j10, int i10);

    void onVideoInputFormatChanged(Format format, DecoderReuseEvaluation decoderReuseEvaluation);

    void onVideoSizeChanged(VideoSize videoSize);
}
