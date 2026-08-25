package androidx.media3.common;

import android.content.Context;
import android.graphics.Bitmap;
import android.view.Surface;
import androidx.media3.common.util.TimestampIterator;
import java.util.List;
import java.util.concurrent.Executor;

/* JADX INFO: loaded from: classes3.dex */
public interface VideoGraph {

    public interface Factory {
        VideoGraph create(Context context, ColorInfo colorInfo, DebugViewProvider debugViewProvider, Listener listener, Executor executor, long j10, boolean z);

        boolean supportsMultipleInputs();
    }

    public interface Listener {
        void onEnded(long j10);

        void onError(VideoFrameProcessingException videoFrameProcessingException);

        void onOutputFrameAvailableForRendering(long j10, boolean z);

        void onOutputFrameRateChanged(float f10);

        void onOutputSizeChanged(int i10, int i11);
    }

    void flush();

    Surface getInputSurface(int i10);

    int getPendingInputFrameCount(int i10);

    boolean hasProducedFrameWithTimestampZero();

    void initialize() throws VideoFrameProcessingException;

    boolean queueInputBitmap(int i10, Bitmap bitmap, TimestampIterator timestampIterator);

    boolean queueInputTexture(int i10, int i11, long j10);

    void redraw();

    void registerInput(int i10) throws VideoFrameProcessingException;

    boolean registerInputFrame(int i10);

    void registerInputStream(int i10, int i11, Format format, List<Effect> list, long j10);

    void release();

    void renderOutputFrame(long j10);

    void setCompositionEffects(List<Effect> list);

    void setCompositorSettings(VideoCompositorSettings videoCompositorSettings);

    void setOnInputFrameProcessedListener(int i10, OnInputFrameProcessedListener onInputFrameProcessedListener);

    void setOnInputSurfaceReadyListener(int i10, Runnable runnable);

    void setOutputSurfaceInfo(SurfaceInfo surfaceInfo);

    void signalEndOfInput(int i10);
}
