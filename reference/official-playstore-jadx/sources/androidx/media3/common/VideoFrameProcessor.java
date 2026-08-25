package androidx.media3.common;

import android.content.Context;
import android.graphics.Bitmap;
import android.view.Surface;
import androidx.media3.common.util.TimestampIterator;
import com.google.common.collect.h1;
import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.util.List;
import java.util.concurrent.Executor;

/* JADX INFO: loaded from: classes3.dex */
public interface VideoFrameProcessor {
    public static final long DROP_OUTPUT_FRAME = -2;
    public static final int INPUT_TYPE_BITMAP = 2;
    public static final int INPUT_TYPE_SURFACE = 1;
    public static final int INPUT_TYPE_SURFACE_AUTOMATIC_FRAME_REGISTRATION = 4;
    public static final int INPUT_TYPE_TEXTURE_ID = 3;
    public static final h1 REDRAW = h1.s(new Effect() { // from class: androidx.media3.common.VideoFrameProcessor.1
        @Override // androidx.media3.common.Effect
        public final /* synthetic */ long getDurationAfterEffectApplied(long j10) {
            return d.a(this, j10);
        }
    });

    @Deprecated
    public static final long RENDER_OUTPUT_FRAME_IMMEDIATELY = -1;
    public static final long RENDER_OUTPUT_FRAME_WITH_PRESENTATION_TIME = -3;

    public interface Factory {
        VideoFrameProcessor create(Context context, DebugViewProvider debugViewProvider, ColorInfo colorInfo, boolean z, Executor executor, Listener listener) throws VideoFrameProcessingException;
    }

    @Target({ElementType.TYPE_USE})
    @Documented
    @Retention(RetentionPolicy.SOURCE)
    public @interface InputType {
    }

    public interface Listener {
        void onEnded();

        void onError(VideoFrameProcessingException videoFrameProcessingException);

        void onInputStreamRegistered(int i10, Format format, List<Effect> list);

        void onOutputFrameAvailableForRendering(long j10, boolean z);

        void onOutputFrameRateChanged(float f10);

        void onOutputSizeChanged(int i10, int i11);
    }

    void flush();

    Surface getInputSurface();

    int getPendingInputFrameCount();

    boolean queueInputBitmap(Bitmap bitmap, TimestampIterator timestampIterator);

    boolean queueInputTexture(int i10, long j10);

    void redraw();

    boolean registerInputFrame();

    void registerInputStream(int i10, Format format, List<Effect> list, long j10);

    void release();

    void renderOutputFrame(long j10);

    void setOnInputFrameProcessedListener(OnInputFrameProcessedListener onInputFrameProcessedListener);

    void setOnInputSurfaceReadyListener(Runnable runnable);

    void setOutputSurfaceInfo(SurfaceInfo surfaceInfo);

    void signalEndOfInput();
}
