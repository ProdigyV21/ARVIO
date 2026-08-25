package androidx.media3.exoplayer.video;

import android.graphics.Bitmap;
import android.view.Surface;
import androidx.media3.common.Effect;
import androidx.media3.common.Format;
import androidx.media3.common.VideoSize;
import androidx.media3.common.util.Size;
import androidx.media3.common.util.TimestampIterator;
import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.util.List;
import java.util.concurrent.Executor;

/* JADX INFO: loaded from: classes3.dex */
public interface VideoSink {
    public static final int INPUT_TYPE_BITMAP = 2;
    public static final int INPUT_TYPE_SURFACE = 1;
    public static final int RELEASE_FIRST_FRAME_IMMEDIATELY = 0;
    public static final int RELEASE_FIRST_FRAME_WHEN_PREVIOUS_STREAM_PROCESSED = 2;
    public static final int RELEASE_FIRST_FRAME_WHEN_STARTED = 1;

    @Target({ElementType.TYPE_USE})
    @Documented
    @Retention(RetentionPolicy.SOURCE)
    public @interface FirstFrameReleaseInstruction {
    }

    @Target({ElementType.TYPE_USE})
    @Documented
    @Retention(RetentionPolicy.SOURCE)
    public @interface InputType {
    }

    public interface Listener {
        public static final Listener NO_OP = new Listener() { // from class: androidx.media3.exoplayer.video.VideoSink.Listener.1
            @Override // androidx.media3.exoplayer.video.VideoSink.Listener
            public final /* synthetic */ void onError(VideoSinkException videoSinkException) {
                n.a(this, videoSinkException);
            }

            @Override // androidx.media3.exoplayer.video.VideoSink.Listener
            public final /* synthetic */ void onFirstFrameRendered() {
                n.b(this);
            }

            @Override // androidx.media3.exoplayer.video.VideoSink.Listener
            public final /* synthetic */ void onFrameAvailableForRendering() {
                n.c(this);
            }

            @Override // androidx.media3.exoplayer.video.VideoSink.Listener
            public final /* synthetic */ void onFrameDropped() {
                n.d(this);
            }

            @Override // androidx.media3.exoplayer.video.VideoSink.Listener
            public final /* synthetic */ void onVideoSizeChanged(VideoSize videoSize) {
                n.e(this, videoSize);
            }
        };

        void onError(VideoSinkException videoSinkException);

        void onFirstFrameRendered();

        void onFrameAvailableForRendering();

        void onFrameDropped();

        void onVideoSizeChanged(VideoSize videoSize);
    }

    public interface VideoFrameHandler {
        void render(long j10);

        void skip();
    }

    public static final class VideoSinkException extends Exception {
        public final Format format;

        public VideoSinkException(Throwable th, Format format) {
            super(th);
            this.format = format;
        }
    }

    void allowReleaseFirstFrameBeforeStarted();

    void clearOutputSurfaceInfo();

    void flush(boolean z);

    Surface getInputSurface();

    boolean handleInputBitmap(Bitmap bitmap, TimestampIterator timestampIterator);

    boolean handleInputFrame(long j10, VideoFrameHandler videoFrameHandler);

    boolean initialize(Format format) throws VideoSinkException;

    boolean isEnded();

    boolean isInitialized();

    boolean isReady(boolean z);

    void join(boolean z);

    void onInputStreamChanged(int i10, Format format, long j10, int i11, List<Effect> list);

    void redraw();

    void release();

    void render(long j10, long j11) throws VideoSinkException;

    void setBufferTimestampAdjustmentUs(long j10);

    void setChangeFrameRateStrategy(int i10);

    void setListener(Listener listener, Executor executor);

    void setOutputSurfaceInfo(Surface surface, Size size);

    void setPlaybackSpeed(float f10);

    void setVideoEffects(List<Effect> list);

    void setVideoFrameMetadataListener(VideoFrameMetadataListener videoFrameMetadataListener);

    void signalEndOfCurrentInputStream();

    void signalEndOfInput();

    void startRendering();

    void stopRendering();
}
