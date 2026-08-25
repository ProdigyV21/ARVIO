package androidx.media3.exoplayer.video.spherical;

import android.graphics.SurfaceTexture;
import android.media.MediaFormat;
import android.opengl.GLES20;
import android.opengl.Matrix;
import androidx.media3.common.Format;
import androidx.media3.common.util.GlUtil;
import androidx.media3.common.util.Log;
import androidx.media3.common.util.TimedValueQueue;
import androidx.media3.exoplayer.video.VideoFrameMetadataListener;
import java.util.Arrays;
import java.util.concurrent.atomic.AtomicBoolean;

/* JADX INFO: loaded from: classes3.dex */
final class SceneRenderer implements VideoFrameMetadataListener, CameraMotionListener {
    private static final String TAG = "SceneRenderer";
    private byte[] lastProjectionData;
    private SurfaceTexture surfaceTexture;
    private int textureId;
    private final AtomicBoolean frameAvailable = new AtomicBoolean();
    private final AtomicBoolean resetRotationAtNextFrame = new AtomicBoolean(true);
    private final ProjectionRenderer projectionRenderer = new ProjectionRenderer();
    private final FrameRotationQueue frameRotationQueue = new FrameRotationQueue();
    private final TimedValueQueue<Long> sampleTimestampQueue = new TimedValueQueue<>();
    private final TimedValueQueue<Projection> projectionQueue = new TimedValueQueue<>();
    private final float[] rotationMatrix = new float[16];
    private final float[] tempMatrix = new float[16];
    private volatile int defaultStereoMode = 0;
    private int lastStereoMode = -1;

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$init$0(SurfaceTexture surfaceTexture) {
        this.frameAvailable.set(true);
    }

    private void setProjection(byte[] bArr, int i10, long j10) {
        byte[] bArr2 = this.lastProjectionData;
        int i11 = this.lastStereoMode;
        this.lastProjectionData = bArr;
        if (i10 == -1) {
            i10 = this.defaultStereoMode;
        }
        this.lastStereoMode = i10;
        if (i11 == i10 && Arrays.equals(bArr2, this.lastProjectionData)) {
            return;
        }
        byte[] bArr3 = this.lastProjectionData;
        Projection projectionDecode = bArr3 != null ? ProjectionDecoder.decode(bArr3, this.lastStereoMode) : null;
        if (projectionDecode == null || !ProjectionRenderer.isSupported(projectionDecode)) {
            projectionDecode = Projection.createEquirectangular(this.lastStereoMode);
        }
        this.projectionQueue.add(j10, projectionDecode);
    }

    public void drawFrame(float[] fArr, boolean z) {
        GLES20.glClear(16384);
        try {
            GlUtil.checkGlError();
        } catch (GlUtil.GlException e5) {
            Log.e(TAG, "Failed to draw a frame", e5);
        }
        if (this.frameAvailable.compareAndSet(true, false)) {
            SurfaceTexture surfaceTexture = this.surfaceTexture;
            surfaceTexture.getClass();
            surfaceTexture.updateTexImage();
            try {
                GlUtil.checkGlError();
            } catch (GlUtil.GlException e6) {
                Log.e(TAG, "Failed to draw a frame", e6);
            }
            if (this.resetRotationAtNextFrame.compareAndSet(true, false)) {
                GlUtil.setToIdentity(this.rotationMatrix);
            }
            long timestamp = this.surfaceTexture.getTimestamp();
            Long lPoll = this.sampleTimestampQueue.poll(timestamp);
            if (lPoll != null) {
                this.frameRotationQueue.pollRotationMatrix(this.rotationMatrix, lPoll.longValue());
            }
            Projection projectionPollFloor = this.projectionQueue.pollFloor(timestamp);
            if (projectionPollFloor != null) {
                this.projectionRenderer.setProjection(projectionPollFloor);
            }
        }
        Matrix.multiplyMM(this.tempMatrix, 0, fArr, 0, this.rotationMatrix, 0);
        this.projectionRenderer.draw(this.textureId, this.tempMatrix, z);
    }

    public SurfaceTexture init() {
        try {
            GLES20.glClearColor(0.5f, 0.5f, 0.5f, 1.0f);
            GlUtil.checkGlError();
            this.projectionRenderer.init();
            GlUtil.checkGlError();
            this.textureId = GlUtil.createExternalTexture();
        } catch (GlUtil.GlException e5) {
            Log.e(TAG, "Failed to initialize the renderer", e5);
        }
        SurfaceTexture surfaceTexture = new SurfaceTexture(this.textureId);
        this.surfaceTexture = surfaceTexture;
        surfaceTexture.setOnFrameAvailableListener(new SurfaceTexture.OnFrameAvailableListener() { // from class: androidx.media3.exoplayer.video.spherical.a
            @Override // android.graphics.SurfaceTexture.OnFrameAvailableListener
            public final void onFrameAvailable(SurfaceTexture surfaceTexture2) {
                this.f3876i.lambda$init$0(surfaceTexture2);
            }
        });
        return this.surfaceTexture;
    }

    @Override // androidx.media3.exoplayer.video.spherical.CameraMotionListener
    public void onCameraMotion(long j10, float[] fArr) {
        this.frameRotationQueue.setRotation(j10, fArr);
    }

    @Override // androidx.media3.exoplayer.video.spherical.CameraMotionListener
    public void onCameraMotionReset() {
        this.sampleTimestampQueue.clear();
        this.frameRotationQueue.reset();
        this.resetRotationAtNextFrame.set(true);
    }

    @Override // androidx.media3.exoplayer.video.VideoFrameMetadataListener
    public void onVideoFrameAboutToBeRendered(long j10, long j11, Format format, MediaFormat mediaFormat) {
        this.sampleTimestampQueue.add(j11, Long.valueOf(j10));
        setProjection(format.projectionData, format.stereoMode, j11);
    }

    public void setDefaultStereoMode(int i10) {
        this.defaultStereoMode = i10;
    }

    public void shutdown() {
        this.projectionRenderer.shutdown();
    }
}
