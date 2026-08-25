package androidx.media3.exoplayer.image;

import ac.b;
import android.graphics.Bitmap;
import androidx.media3.common.C;
import androidx.media3.common.Format;
import androidx.media3.common.PlaybackException;
import androidx.media3.common.util.TraceUtil;
import androidx.media3.decoder.DecoderInputBuffer;
import androidx.media3.exoplayer.BaseRenderer;
import androidx.media3.exoplayer.ExoPlaybackException;
import androidx.media3.exoplayer.FormatHolder;
import androidx.media3.exoplayer.image.ImageDecoder;
import androidx.media3.exoplayer.t0;
import java.util.ArrayDeque;

/* JADX INFO: loaded from: classes3.dex */
public class ImageRenderer extends BaseRenderer {
    private static final long IMAGE_PRESENTATION_WINDOW_THRESHOLD_US = 30000;
    private static final int REINITIALIZATION_STATE_NONE = 0;
    private static final int REINITIALIZATION_STATE_SIGNAL_END_OF_STREAM_THEN_WAIT = 2;
    private static final int REINITIALIZATION_STATE_WAIT_END_OF_STREAM = 3;
    private static final String TAG = "ImageRenderer";
    private boolean codecNeedsInitialization;
    private int currentTileIndex;
    private ImageDecoder decoder;
    private final ImageDecoder.Factory decoderFactory;
    private int decoderReinitializationState;
    private int firstFrameState;
    private final DecoderInputBuffer flagsOnlyBuffer;
    private ImageOutput imageOutput;
    private DecoderInputBuffer inputBuffer;
    private Format inputFormat;
    private boolean inputStreamEnded;
    private long largestQueuedPresentationTimeUs;
    private long lastProcessedOutputBufferTimeUs;
    private TileInfo nextTileInfo;
    private Bitmap outputBitmap;
    private boolean outputStreamEnded;
    private OutputStreamInfo outputStreamInfo;
    private final ArrayDeque<OutputStreamInfo> pendingOutputStreamChanges;
    private boolean readyToOutputTiles;
    private TileInfo tileInfo;

    public static final class OutputStreamInfo {
        public static final OutputStreamInfo UNSET = new OutputStreamInfo(C.TIME_UNSET, C.TIME_UNSET);
        public final long previousStreamLastBufferTimeUs;
        public final long streamOffsetUs;

        public OutputStreamInfo(long j10, long j11) {
            this.previousStreamLastBufferTimeUs = j10;
            this.streamOffsetUs = j11;
        }
    }

    public static class TileInfo {
        private final long presentationTimeUs;
        private Bitmap tileBitmap;
        private final int tileIndex;

        public TileInfo(int i10, long j10) {
            this.tileIndex = i10;
            this.presentationTimeUs = j10;
        }

        public long getPresentationTimeUs() {
            return this.presentationTimeUs;
        }

        public Bitmap getTileBitmap() {
            return this.tileBitmap;
        }

        public int getTileIndex() {
            return this.tileIndex;
        }

        public boolean hasTileBitmap() {
            return this.tileBitmap != null;
        }

        public void setTileBitmap(Bitmap bitmap) {
            this.tileBitmap = bitmap;
        }
    }

    public ImageRenderer(ImageDecoder.Factory factory, ImageOutput imageOutput) {
        super(4);
        this.decoderFactory = factory;
        this.imageOutput = getImageOutput(imageOutput);
        this.flagsOnlyBuffer = DecoderInputBuffer.newNoDataInstance();
        this.outputStreamInfo = OutputStreamInfo.UNSET;
        this.pendingOutputStreamChanges = new ArrayDeque<>();
        this.largestQueuedPresentationTimeUs = C.TIME_UNSET;
        this.lastProcessedOutputBufferTimeUs = C.TIME_UNSET;
        this.decoderReinitializationState = 0;
        this.firstFrameState = 1;
    }

    private boolean canCreateDecoderForFormat(Format format) {
        int iSupportsFormat = this.decoderFactory.supportsFormat(format);
        return iSupportsFormat == t0.c(4) || iSupportsFormat == t0.c(3);
    }

    private Bitmap cropTileFromImageGrid(int i10) {
        this.outputBitmap.getClass();
        int width = this.outputBitmap.getWidth();
        Format format = this.inputFormat;
        format.getClass();
        int i11 = width / format.tileCountHorizontal;
        int height = this.outputBitmap.getHeight();
        Format format2 = this.inputFormat;
        format2.getClass();
        int i12 = height / format2.tileCountVertical;
        int i13 = this.inputFormat.tileCountHorizontal;
        return Bitmap.createBitmap(this.outputBitmap, (i10 % i13) * i11, (i10 / i13) * i12, i11, i12);
    }

    /* JADX WARN: Removed duplicated region for block: B:46:0x008c  */
    /* JADX WARN: Removed duplicated region for block: B:54:0x00ba  */
    /* JADX WARN: Removed duplicated region for block: B:58:0x00e6  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private boolean drainOutput(long r13, long r15) throws androidx.media3.exoplayer.ExoPlaybackException, androidx.media3.exoplayer.image.ImageDecoderException {
        /*
            Method dump skipped, instruction units count: 240
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.media3.exoplayer.image.ImageRenderer.drainOutput(long, long):boolean");
    }

    /* JADX WARN: Removed duplicated region for block: B:38:0x007f  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private boolean feedInputBuffer(long r8) throws androidx.media3.decoder.DecoderException {
        /*
            Method dump skipped, instruction units count: 225
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.media3.exoplayer.image.ImageRenderer.feedInputBuffer(long):boolean");
    }

    private static ImageOutput getImageOutput(ImageOutput imageOutput) {
        return imageOutput == null ? ImageOutput.NO_OP : imageOutput;
    }

    private boolean isTileLastInGrid(TileInfo tileInfo) {
        Format format = this.inputFormat;
        format.getClass();
        if (format.tileCountHorizontal != -1 && this.inputFormat.tileCountVertical != -1) {
            int tileIndex = tileInfo.getTileIndex();
            Format format2 = this.inputFormat;
            format2.getClass();
            if (tileIndex != (format2.tileCountVertical * this.inputFormat.tileCountHorizontal) - 1) {
                return false;
            }
        }
        return true;
    }

    private void lowerFirstFrameState(int i10) {
        this.firstFrameState = Math.min(this.firstFrameState, i10);
    }

    private void maybeAdvanceTileInfo(long j10, DecoderInputBuffer decoderInputBuffer) {
        boolean z = true;
        if (decoderInputBuffer.isEndOfStream()) {
            this.readyToOutputTiles = true;
            return;
        }
        TileInfo tileInfo = new TileInfo(this.currentTileIndex, decoderInputBuffer.timeUs);
        this.nextTileInfo = tileInfo;
        this.currentTileIndex++;
        if (!this.readyToOutputTiles) {
            long presentationTimeUs = tileInfo.getPresentationTimeUs();
            boolean z5 = presentationTimeUs - 30000 <= j10 && j10 <= 30000 + presentationTimeUs;
            TileInfo tileInfo2 = this.tileInfo;
            boolean z10 = tileInfo2 != null && tileInfo2.getPresentationTimeUs() <= j10 && j10 < presentationTimeUs;
            TileInfo tileInfo3 = this.nextTileInfo;
            tileInfo3.getClass();
            boolean zIsTileLastInGrid = isTileLastInGrid(tileInfo3);
            if (!z5 && !z10 && !zIsTileLastInGrid) {
                z = false;
            }
            this.readyToOutputTiles = z;
            if (z10 && !z5) {
                return;
            }
        }
        this.tileInfo = this.nextTileInfo;
        this.nextTileInfo = null;
    }

    private boolean maybeInitCodec() throws ExoPlaybackException {
        if (!maybeInitializeProcessingPipeline()) {
            return false;
        }
        if (!this.codecNeedsInitialization) {
            return true;
        }
        Format format = this.inputFormat;
        format.getClass();
        if (!canCreateDecoderForFormat(format)) {
            throw createRendererException(new ImageDecoderException("Provided decoder factory can't create decoder for format."), this.inputFormat, PlaybackException.ERROR_CODE_DECODING_FORMAT_UNSUPPORTED);
        }
        ImageDecoder imageDecoder = this.decoder;
        if (imageDecoder != null) {
            imageDecoder.release();
        }
        this.decoder = this.decoderFactory.createImageDecoder();
        this.codecNeedsInitialization = false;
        return true;
    }

    private void onProcessedOutputBuffer(long j10) {
        this.lastProcessedOutputBufferTimeUs = j10;
        while (!this.pendingOutputStreamChanges.isEmpty() && j10 >= this.pendingOutputStreamChanges.peek().previousStreamLastBufferTimeUs) {
            this.outputStreamInfo = this.pendingOutputStreamChanges.removeFirst();
        }
    }

    private void releaseDecoderResources() {
        this.inputBuffer = null;
        this.decoderReinitializationState = 0;
        this.largestQueuedPresentationTimeUs = C.TIME_UNSET;
        ImageDecoder imageDecoder = this.decoder;
        if (imageDecoder != null) {
            imageDecoder.release();
            this.decoder = null;
        }
    }

    private void setImageOutput(ImageOutput imageOutput) {
        this.imageOutput = getImageOutput(imageOutput);
    }

    private boolean shouldForceRender() {
        boolean z = getState() == 2;
        int i10 = this.firstFrameState;
        if (i10 == 0) {
            return z;
        }
        if (i10 == 1) {
            return true;
        }
        if (i10 == 3) {
            return false;
        }
        throw new IllegalStateException();
    }

    @Override // androidx.media3.exoplayer.Renderer
    public String getName() {
        return TAG;
    }

    @Override // androidx.media3.exoplayer.BaseRenderer, androidx.media3.exoplayer.PlayerMessage.Target
    public void handleMessage(int i10, Object obj) throws ExoPlaybackException {
        if (i10 != 15) {
            super.handleMessage(i10, obj);
        } else {
            setImageOutput(obj instanceof ImageOutput ? (ImageOutput) obj : null);
        }
    }

    @Override // androidx.media3.exoplayer.Renderer
    public boolean isEnded() {
        return this.outputStreamEnded;
    }

    @Override // androidx.media3.exoplayer.Renderer
    public boolean isReady() {
        int i10 = this.firstFrameState;
        if (i10 != 3) {
            return i10 == 0 && this.readyToOutputTiles;
        }
        return true;
    }

    public boolean maybeInitializeProcessingPipeline() throws ExoPlaybackException {
        return true;
    }

    @Override // androidx.media3.exoplayer.BaseRenderer
    public void onDisabled() {
        this.inputFormat = null;
        this.outputStreamInfo = OutputStreamInfo.UNSET;
        this.pendingOutputStreamChanges.clear();
        releaseDecoderResources();
        this.imageOutput.onDisabled();
    }

    @Override // androidx.media3.exoplayer.BaseRenderer
    public void onEnabled(boolean z, boolean z5) throws ExoPlaybackException {
        this.firstFrameState = z5 ? 1 : 0;
    }

    @Override // androidx.media3.exoplayer.BaseRenderer
    public void onPositionReset(long j10, boolean z, boolean z5) throws ExoPlaybackException {
        lowerFirstFrameState(1);
        this.outputStreamEnded = false;
        this.inputStreamEnded = false;
        this.outputBitmap = null;
        this.tileInfo = null;
        this.nextTileInfo = null;
        this.readyToOutputTiles = false;
        this.inputBuffer = null;
        ImageDecoder imageDecoder = this.decoder;
        if (imageDecoder != null) {
            imageDecoder.flush();
        }
        this.pendingOutputStreamChanges.clear();
    }

    @Override // androidx.media3.exoplayer.BaseRenderer
    public void onRelease() {
        releaseDecoderResources();
    }

    @Override // androidx.media3.exoplayer.BaseRenderer
    public void onReset() {
        releaseDecoderResources();
        lowerFirstFrameState(1);
    }

    /* JADX WARN: Code restructure failed: missing block: B:11:0x0027, code lost:
    
        if (r2 >= r6) goto L15;
     */
    @Override // androidx.media3.exoplayer.BaseRenderer
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public void onStreamChanged(androidx.media3.common.Format[] r5, long r6, long r8, androidx.media3.exoplayer.source.MediaSource.MediaPeriodId r10) throws androidx.media3.exoplayer.ExoPlaybackException {
        /*
            r4 = this;
            super.onStreamChanged(r5, r6, r8, r10)
            r5 = r4
            androidx.media3.exoplayer.image.ImageRenderer$OutputStreamInfo r6 = r5.outputStreamInfo
            long r6 = r6.streamOffsetUs
            r0 = -9223372036854775807(0x8000000000000001, double:-4.9E-324)
            int r6 = (r6 > r0 ? 1 : (r6 == r0 ? 0 : -1))
            if (r6 == 0) goto L37
            java.util.ArrayDeque<androidx.media3.exoplayer.image.ImageRenderer$OutputStreamInfo> r6 = r5.pendingOutputStreamChanges
            boolean r6 = r6.isEmpty()
            if (r6 == 0) goto L2a
            long r6 = r5.largestQueuedPresentationTimeUs
            int r10 = (r6 > r0 ? 1 : (r6 == r0 ? 0 : -1))
            if (r10 == 0) goto L37
            long r2 = r5.lastProcessedOutputBufferTimeUs
            int r10 = (r2 > r0 ? 1 : (r2 == r0 ? 0 : -1))
            if (r10 == 0) goto L2a
            int r6 = (r2 > r6 ? 1 : (r2 == r6 ? 0 : -1))
            if (r6 < 0) goto L2a
            goto L37
        L2a:
            java.util.ArrayDeque<androidx.media3.exoplayer.image.ImageRenderer$OutputStreamInfo> r6 = r5.pendingOutputStreamChanges
            androidx.media3.exoplayer.image.ImageRenderer$OutputStreamInfo r7 = new androidx.media3.exoplayer.image.ImageRenderer$OutputStreamInfo
            long r0 = r5.largestQueuedPresentationTimeUs
            r7.<init>(r0, r8)
            r6.add(r7)
            return
        L37:
            androidx.media3.exoplayer.image.ImageRenderer$OutputStreamInfo r6 = new androidx.media3.exoplayer.image.ImageRenderer$OutputStreamInfo
            r6.<init>(r0, r8)
            r5.outputStreamInfo = r6
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.media3.exoplayer.image.ImageRenderer.onStreamChanged(androidx.media3.common.Format[], long, long, androidx.media3.exoplayer.source.MediaSource$MediaPeriodId):void");
    }

    public boolean processOutputBuffer(long j10, long j11, Bitmap bitmap, long j12) throws ExoPlaybackException {
        long j13 = j12 - j10;
        if (!shouldForceRender() && j13 >= 30000) {
            return false;
        }
        this.imageOutput.onImageAvailable(j12 - this.outputStreamInfo.streamOffsetUs, bitmap);
        return true;
    }

    @Override // androidx.media3.exoplayer.Renderer
    public void render(long j10, long j11) throws ExoPlaybackException {
        if (this.outputStreamEnded) {
            return;
        }
        if (this.inputFormat == null) {
            FormatHolder formatHolder = getFormatHolder();
            this.flagsOnlyBuffer.clear();
            int source = readSource(formatHolder, this.flagsOnlyBuffer, 2);
            if (source != -5) {
                if (source == -4) {
                    b.s(this.flagsOnlyBuffer.isEndOfStream());
                    this.inputStreamEnded = true;
                    this.outputStreamEnded = true;
                    return;
                }
                return;
            }
            Format format = formatHolder.format;
            format.getClass();
            this.inputFormat = format;
            this.codecNeedsInitialization = true;
        }
        if (this.decoder != null || maybeInitCodec()) {
            try {
                TraceUtil.beginSection("drainAndFeedDecoder");
                while (drainOutput(j10, j11)) {
                }
                while (feedInputBuffer(j10)) {
                }
                TraceUtil.endSection();
            } catch (ImageDecoderException e5) {
                throw createRendererException(e5, null, PlaybackException.ERROR_CODE_DECODING_FAILED);
            }
        }
    }

    @Override // androidx.media3.exoplayer.RendererCapabilities
    public int supportsFormat(Format format) {
        return this.decoderFactory.supportsFormat(format);
    }
}
