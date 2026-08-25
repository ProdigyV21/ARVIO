package com.discord.org.webrtc;

import android.media.MediaCodec;
import android.media.MediaFormat;
import android.os.SystemClock;
import android.view.Surface;
import androidx.fragment.app.a2;
import androidx.media3.exoplayer.audio.SilenceSkippingAudioProcessor;
import com.discord.DiscordDecoderStreamManager;
import com.discord.org.webrtc.EglBase;
import com.discord.org.webrtc.EncodedImage;
import com.discord.org.webrtc.ThreadUtils;
import com.discord.org.webrtc.VideoDecoder;
import com.discord.org.webrtc.VideoFrame;
import j$.util.function.Consumer$CC;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.concurrent.BlockingDeque;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.TimeUnit;
import java.util.function.Consumer;

/* JADX INFO: loaded from: classes4.dex */
public class AndroidVideoDecoder implements VideoDecoder, VideoSink {
    private static final int DEQUEUE_INPUT_TIMEOUT_US = 500000;
    private static final int DEQUEUE_OUTPUT_BUFFER_TIMEOUT_US = 100000;
    private static final int MEDIA_CODEC_RELEASE_TIMEOUT_MS = 5000;
    private static final String TAG = "AndroidVideoDecoder";
    private VideoDecoder.Callback callback;
    private MediaCodecWrapper codec;
    private final String codecName;
    private final VideoCodecMimeType codecType;
    private int colorFormat;
    private ThreadUtils.ThreadChecker decoderThreadChecker;
    private final BlockingDeque<FrameInfo> frameInfos;
    private boolean hasDecodedFirstFrame;
    private boolean hasExternalConsumer;
    private int height;
    private boolean keyFrameRequired;
    private final MediaCodecWrapperFactory mediaCodecWrapperFactory;
    private Thread outputThread;
    private ThreadUtils.ThreadChecker outputThreadChecker;
    private DecodedTextureMetadata renderedTextureMetadata;
    private volatile boolean running;
    private final EglBase.Context sharedContext;
    private volatile Exception shutdownException;
    private int sliceHeight;
    private String streamId;
    private int stride;
    private Surface surface;
    private SurfaceTextureHelper surfaceTextureHelper;
    private int width;
    private final Object dimensionLock = new Object();
    private final Object renderedTextureMetadataLock = new Object();

    public static class DecodedTextureMetadata {
        final Integer decodeTimeMs;
        final long presentationTimestampUs;

        public DecodedTextureMetadata(long j10, Integer num) {
            this.presentationTimestampUs = j10;
            this.decodeTimeMs = num;
        }
    }

    public static class FrameInfo {
        final long decodeStartTimeMs;
        final int rotation;

        public FrameInfo(long j10, int i10) {
            this.decodeStartTimeMs = j10;
            this.rotation = i10;
        }
    }

    public AndroidVideoDecoder(MediaCodecWrapperFactory mediaCodecWrapperFactory, String str, VideoCodecMimeType videoCodecMimeType, int i10, EglBase.Context context) {
        if (!isSupportedColorFormat(i10)) {
            throw new IllegalArgumentException(a0.c.i(i10, "Unsupported color format: "));
        }
        Logging.d(TAG, "ctor name: " + str + " type: " + videoCodecMimeType + " color format: " + i10 + " context: " + context);
        this.mediaCodecWrapperFactory = mediaCodecWrapperFactory;
        this.codecName = str;
        this.codecType = videoCodecMimeType;
        this.colorFormat = i10;
        this.sharedContext = context;
        this.frameInfos = new LinkedBlockingDeque();
    }

    private VideoFrame.Buffer copyI420Buffer(ByteBuffer byteBuffer, int i10, int i11, int i12, int i13) {
        if (i10 % 2 != 0) {
            throw new AssertionError(a0.c.i(i10, "Stride is not divisible by two: "));
        }
        int i14 = (i12 + 1) / 2;
        int i15 = i11 % 2;
        int i16 = i15 == 0 ? (i13 + 1) / 2 : i13 / 2;
        int i17 = i10 / 2;
        int i18 = i10 * i11;
        int i19 = i17 * i16;
        int i20 = ((i17 * i11) / 2) + i18;
        int i21 = i20 + i19;
        VideoFrame.I420Buffer i420BufferAllocateI420Buffer = allocateI420Buffer(i12, i13);
        copyPlane(byteBuffer.slice(), i10, i420BufferAllocateI420Buffer.getDataY(), i420BufferAllocateI420Buffer.getStrideY(), i12, i13);
        copyPlane(byteBuffer.slice(), i17, i420BufferAllocateI420Buffer.getDataU(), i420BufferAllocateI420Buffer.getStrideU(), i14, i16);
        if (i15 == 1) {
            ByteBuffer dataU = i420BufferAllocateI420Buffer.getDataU();
            dataU.put(byteBuffer);
        }
        copyPlane(byteBuffer.slice(), i17, i420BufferAllocateI420Buffer.getDataV(), i420BufferAllocateI420Buffer.getStrideV(), i14, i16);
        if (i15 == 1) {
            ByteBuffer dataV = i420BufferAllocateI420Buffer.getDataV();
            dataV.put(byteBuffer);
        }
        return i420BufferAllocateI420Buffer;
    }

    private VideoFrame.Buffer copyNV12ToI420Buffer(ByteBuffer byteBuffer, int i10, int i11, int i12, int i13) {
        return new NV12Buffer(i12, i13, i10, i11, byteBuffer, null).toI420();
    }

    private Thread createOutputThread() {
        return new Thread("AndroidVideoDecoder.outputThread") { // from class: com.discord.org.webrtc.AndroidVideoDecoder.1
            @Override // java.lang.Thread, java.lang.Runnable
            public void run() throws Throwable {
                AndroidVideoDecoder.this.outputThreadChecker = new ThreadUtils.ThreadChecker();
                while (AndroidVideoDecoder.this.running) {
                    AndroidVideoDecoder.this.deliverDecodedFrame();
                }
                AndroidVideoDecoder.this.releaseCodecOnOutputThread();
            }
        };
    }

    private void deliverByteFrame(int i10, MediaCodec.BufferInfo bufferInfo, int i11, Integer num) throws Throwable {
        int i12;
        int i13;
        int i14;
        int i15;
        AndroidVideoDecoder androidVideoDecoder;
        VideoFrame.Buffer bufferCopyNV12ToI420Buffer;
        synchronized (this.dimensionLock) {
            try {
                i12 = this.width;
                i13 = this.height;
                i14 = this.stride;
                i15 = this.sliceHeight;
            } catch (Throwable th) {
                th = th;
                while (true) {
                    try {
                        throw th;
                    } catch (Throwable th2) {
                        th = th2;
                    }
                }
            }
        }
        int i16 = bufferInfo.size;
        if (i16 < ((i12 * i13) * 3) / 2) {
            Logging.e(TAG, "Insufficient output buffer size: " + i16);
            return;
        }
        if (i16 < ((i14 * i13) * 3) / 2 && i15 == i13 && i14 > i12) {
            i14 = (i16 * 2) / (i13 * 3);
        }
        int i17 = i14;
        ByteBuffer outputBuffer = this.codec.getOutputBuffer(i10);
        ByteBuffer byteBufferSlice = outputBuffer.slice();
        if (this.colorFormat == 19) {
            androidVideoDecoder = this;
            bufferCopyNV12ToI420Buffer = androidVideoDecoder.copyI420Buffer(byteBufferSlice, i17, i15, i12, i13);
        } else {
            androidVideoDecoder = this;
            bufferCopyNV12ToI420Buffer = androidVideoDecoder.copyNV12ToI420Buffer(byteBufferSlice, i17, i15, i12, i13);
        }
        androidVideoDecoder.codec.releaseOutputBuffer(i10, false);
        VideoFrame videoFrame = new VideoFrame(bufferCopyNV12ToI420Buffer, i11, bufferInfo.presentationTimeUs * 1000);
        androidVideoDecoder.callback.onDecodedFrame(videoFrame, num, null);
        videoFrame.release();
    }

    private void deliverTextureFrame(int i10, MediaCodec.BufferInfo bufferInfo, int i11, Integer num) {
        int i12;
        int i13;
        synchronized (this.dimensionLock) {
            i12 = this.width;
            i13 = this.height;
        }
        synchronized (this.renderedTextureMetadataLock) {
            try {
                if (this.hasExternalConsumer) {
                    this.codec.releaseOutputBuffer(i10, true);
                    VideoFrame videoFrame = new VideoFrame(new MockBuffer(i12, i13), i11, bufferInfo.presentationTimeUs * 1000);
                    VideoDecoder.Callback callback = this.callback;
                    if (callback != null) {
                        callback.onDecodedFrame(videoFrame, num, null);
                    }
                    videoFrame.release();
                    return;
                }
                if (this.renderedTextureMetadata != null) {
                    this.codec.releaseOutputBuffer(i10, false);
                    return;
                }
                if (i12 > 0 && i13 > 0) {
                    this.surfaceTextureHelper.setTextureSize(i12, i13);
                    this.surfaceTextureHelper.setFrameRotation(i11);
                    this.renderedTextureMetadata = new DecodedTextureMetadata(bufferInfo.presentationTimeUs, num);
                    this.codec.releaseOutputBuffer(i10, true);
                    return;
                }
                this.codec.releaseOutputBuffer(i10, false);
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    private VideoCodecStatus initDecodeInternal(int i10, int i11) {
        this.decoderThreadChecker.checkIsOnValidThread();
        String str = this.codecName;
        VideoCodecMimeType videoCodecMimeType = this.codecType;
        int i12 = this.colorFormat;
        StringBuilder sb2 = new StringBuilder("initDecodeInternal name: ");
        sb2.append(str);
        sb2.append(" type: ");
        sb2.append(videoCodecMimeType);
        sb2.append(" width: ");
        a2.y(sb2, i10, " height: ", i11, " color format: ");
        sb2.append(i12);
        Logging.d(TAG, sb2.toString());
        if (this.outputThread != null) {
            Logging.e(TAG, "initDecodeInternal called while the codec is already running");
            return VideoCodecStatus.FALLBACK_SOFTWARE;
        }
        this.width = i10;
        this.height = i11;
        this.stride = i10;
        this.sliceHeight = i11;
        this.hasDecodedFirstFrame = false;
        this.keyFrameRequired = true;
        try {
            this.codec = this.mediaCodecWrapperFactory.createByCodecName(this.codecName);
            try {
                MediaFormat mediaFormatCreateVideoFormat = MediaFormat.createVideoFormat(this.codecType.mimeType(), i10, i11);
                if (this.sharedContext == null) {
                    mediaFormatCreateVideoFormat.setInteger("color-format", this.colorFormat);
                }
                this.codec.configure(mediaFormatCreateVideoFormat, this.surface, null, 0);
                this.codec.start();
                this.running = true;
                Thread threadCreateOutputThread = createOutputThread();
                this.outputThread = threadCreateOutputThread;
                threadCreateOutputThread.start();
                Logging.d(TAG, "initDecodeInternal done");
                return VideoCodecStatus.OK;
            } catch (IllegalArgumentException e5) {
                e = e5;
                Logging.e(TAG, "initDecode failed", e);
                release();
                return VideoCodecStatus.FALLBACK_SOFTWARE;
            } catch (IllegalStateException e6) {
                e = e6;
                Logging.e(TAG, "initDecode failed", e);
                release();
                return VideoCodecStatus.FALLBACK_SOFTWARE;
            }
        } catch (IOException | IllegalArgumentException | IllegalStateException unused) {
            Logging.e(TAG, "Cannot create media decoder " + this.codecName);
            return VideoCodecStatus.FALLBACK_SOFTWARE;
        }
    }

    private boolean isSupportedColorFormat(int i10) {
        for (int i11 : MediaCodecUtils.DECODER_COLOR_FORMATS) {
            if (i11 == i10) {
                return true;
            }
        }
        return false;
    }

    private void reformat(MediaFormat mediaFormat) {
        int integer;
        int integer2;
        this.outputThreadChecker.checkIsOnValidThread();
        Logging.d(TAG, "Decoder format changed: " + mediaFormat);
        if (mediaFormat.containsKey("crop-left") && mediaFormat.containsKey("crop-right") && mediaFormat.containsKey("crop-bottom") && mediaFormat.containsKey("crop-top")) {
            integer = (mediaFormat.getInteger("crop-right") + 1) - mediaFormat.getInteger("crop-left");
            integer2 = (mediaFormat.getInteger("crop-bottom") + 1) - mediaFormat.getInteger("crop-top");
        } else {
            integer = mediaFormat.getInteger("width");
            integer2 = mediaFormat.getInteger("height");
        }
        synchronized (this.dimensionLock) {
            try {
                int i10 = this.width;
                if (integer != i10 || integer2 != this.height) {
                    if (this.hasDecodedFirstFrame) {
                        stopOnOutputThread(new RuntimeException("Unexpected size change. Configured " + this.width + "*" + this.height + ". New " + integer + "*" + integer2));
                        return;
                    }
                    if (integer > 0 && integer2 > 0) {
                        this.width = integer;
                        this.height = integer2;
                    }
                    Logging.w(TAG, "Unexpected format dimensions. Configured " + i10 + "*" + this.height + ". New " + integer + "*" + integer2 + ". Skip it");
                    return;
                }
                if (this.surface == null && mediaFormat.containsKey("color-format")) {
                    int integer3 = mediaFormat.getInteger("color-format");
                    this.colorFormat = integer3;
                    Logging.d(TAG, "Color: 0x" + Integer.toHexString(integer3));
                    if (!isSupportedColorFormat(this.colorFormat)) {
                        stopOnOutputThread(new IllegalStateException(a0.c.i(this.colorFormat, "Unsupported color format: ")));
                        return;
                    }
                }
                synchronized (this.dimensionLock) {
                    try {
                        if (mediaFormat.containsKey("stride")) {
                            this.stride = mediaFormat.getInteger("stride");
                        }
                        if (mediaFormat.containsKey("slice-height")) {
                            this.sliceHeight = mediaFormat.getInteger("slice-height");
                        }
                        Logging.d(TAG, "Frame stride and slice height: " + this.stride + " x " + this.sliceHeight);
                        this.stride = Math.max(this.width, this.stride);
                        this.sliceHeight = Math.max(this.height, this.sliceHeight);
                    } finally {
                    }
                }
            } finally {
            }
        }
    }

    private VideoCodecStatus reinitDecode(int i10, int i11) {
        this.decoderThreadChecker.checkIsOnValidThread();
        VideoCodecStatus videoCodecStatusReleaseInternal = releaseInternal();
        return videoCodecStatusReleaseInternal != VideoCodecStatus.OK ? videoCodecStatusReleaseInternal : initDecodeInternal(i10, i11);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void releaseCodecOnOutputThread() {
        this.outputThreadChecker.checkIsOnValidThread();
        Logging.d(TAG, "Releasing MediaCodec on output thread");
        try {
            this.codec.stop();
        } catch (Exception e5) {
            Logging.e(TAG, "Media decoder stop failed", e5);
        }
        try {
            this.codec.release();
        } catch (Exception e6) {
            Logging.e(TAG, "Media decoder release failed", e6);
            this.shutdownException = e6;
        }
        Logging.d(TAG, "Release on output thread done");
    }

    /* JADX WARN: Multi-variable type inference failed */
    private VideoCodecStatus releaseInternal() {
        if (!this.running) {
            Logging.d(TAG, "release: Decoder is not running.");
            return VideoCodecStatus.OK;
        }
        try {
            this.running = false;
            if (!ThreadUtils.joinUninterruptibly(this.outputThread, 5000L)) {
                Logging.e(TAG, "Media decoder release timeout", new RuntimeException());
                return VideoCodecStatus.TIMEOUT;
            }
            if (this.shutdownException != null) {
                Logging.e(TAG, "Media decoder release error", new RuntimeException(this.shutdownException));
                this.shutdownException = null;
                return VideoCodecStatus.ERROR;
            }
            this.codec = null;
            this.outputThread = null;
            return VideoCodecStatus.OK;
        } finally {
            this.codec = null;
            this.outputThread = null;
        }
    }

    private void stopOnOutputThread(Exception exc) {
        this.outputThreadChecker.checkIsOnValidThread();
        this.running = false;
        this.shutdownException = exc;
    }

    public VideoFrame.I420Buffer allocateI420Buffer(int i10, int i11) {
        return JavaI420Buffer.allocate(i10, i11);
    }

    public void copyPlane(ByteBuffer byteBuffer, int i10, ByteBuffer byteBuffer2, int i11, int i12, int i13) {
        YuvHelper.copyPlane(byteBuffer, i10, byteBuffer2, i11, i12, i13);
    }

    @Override // com.discord.org.webrtc.VideoDecoder
    public final /* synthetic */ long createNative(long j10) {
        return a0.a(this, j10);
    }

    public SurfaceTextureHelper createSurfaceTextureHelper() {
        return SurfaceTextureHelper.create("decoder-texture-thread", this.sharedContext);
    }

    @Override // com.discord.org.webrtc.VideoDecoder
    public VideoCodecStatus decode(EncodedImage encodedImage, VideoDecoder.DecodeInfo decodeInfo) {
        int i10;
        int i11;
        VideoCodecStatus videoCodecStatusReinitDecode;
        this.decoderThreadChecker.checkIsOnValidThread();
        MediaCodecWrapper mediaCodecWrapper = this.codec;
        if (mediaCodecWrapper == null || this.callback == null) {
            boolean z = mediaCodecWrapper != null;
            Logging.d(TAG, "decode uninitalized, codec: " + z + ", callback: " + this.callback);
            return VideoCodecStatus.UNINITIALIZED;
        }
        ByteBuffer byteBuffer = encodedImage.buffer;
        if (byteBuffer == null) {
            Logging.e(TAG, "decode() - no input data");
            return VideoCodecStatus.ERR_PARAMETER;
        }
        int iRemaining = byteBuffer.remaining();
        if (iRemaining == 0) {
            Logging.e(TAG, "decode() - input buffer empty");
            return VideoCodecStatus.ERR_PARAMETER;
        }
        synchronized (this.dimensionLock) {
            i10 = this.width;
            i11 = this.height;
        }
        int i12 = encodedImage.encodedWidth;
        int i13 = encodedImage.encodedHeight;
        if (i12 * i13 > 0 && ((i12 != i10 || i13 != i11) && (videoCodecStatusReinitDecode = reinitDecode(i12, i13)) != VideoCodecStatus.OK)) {
            return videoCodecStatusReinitDecode;
        }
        if (this.keyFrameRequired && encodedImage.frameType != EncodedImage.FrameType.VideoFrameKey) {
            Logging.e(TAG, "decode() - key frame required first");
            return VideoCodecStatus.NO_OUTPUT;
        }
        try {
            int iDequeueInputBuffer = this.codec.dequeueInputBuffer(500000L);
            if (iDequeueInputBuffer < 0) {
                Logging.e(TAG, "decode() - no HW buffers available; decoder falling behind");
                return VideoCodecStatus.ERROR;
            }
            try {
                ByteBuffer inputBuffer = this.codec.getInputBuffer(iDequeueInputBuffer);
                if (inputBuffer.capacity() < iRemaining) {
                    Logging.e(TAG, "decode() - HW buffer too small");
                    return VideoCodecStatus.ERROR;
                }
                inputBuffer.put(encodedImage.buffer);
                this.frameInfos.offer(new FrameInfo(SystemClock.elapsedRealtime(), encodedImage.rotation));
                try {
                    this.codec.queueInputBuffer(iDequeueInputBuffer, 0, iRemaining, TimeUnit.NANOSECONDS.toMicros(encodedImage.captureTimeNs), 0);
                    if (this.keyFrameRequired) {
                        this.keyFrameRequired = false;
                    }
                    return VideoCodecStatus.OK;
                } catch (IllegalStateException e5) {
                    Logging.e(TAG, "queueInputBuffer failed", e5);
                    this.frameInfos.pollLast();
                    return VideoCodecStatus.ERROR;
                }
            } catch (IllegalStateException e6) {
                Logging.e(TAG, "getInputBuffer with index=" + iDequeueInputBuffer + " failed", e6);
                return VideoCodecStatus.ERROR;
            }
        } catch (IllegalStateException e10) {
            Logging.e(TAG, "dequeueInputBuffer failed", e10);
            return VideoCodecStatus.ERROR;
        }
    }

    public void deliverDecodedFrame() throws Throwable {
        Integer numValueOf;
        int i10;
        this.outputThreadChecker.checkIsOnValidThread();
        try {
            MediaCodec.BufferInfo bufferInfo = new MediaCodec.BufferInfo();
            int iDequeueOutputBuffer = this.codec.dequeueOutputBuffer(bufferInfo, SilenceSkippingAudioProcessor.DEFAULT_MINIMUM_SILENCE_DURATION_US);
            if (iDequeueOutputBuffer == -2) {
                reformat(this.codec.getOutputFormat());
                return;
            }
            if (iDequeueOutputBuffer < 0) {
                Logging.v(TAG, "dequeueOutputBuffer returned " + iDequeueOutputBuffer);
                return;
            }
            FrameInfo frameInfoPoll = this.frameInfos.poll();
            if (frameInfoPoll != null) {
                numValueOf = Integer.valueOf((int) (SystemClock.elapsedRealtime() - frameInfoPoll.decodeStartTimeMs));
                i10 = frameInfoPoll.rotation;
            } else {
                numValueOf = null;
                i10 = 0;
            }
            this.hasDecodedFirstFrame = true;
            if (this.surface != null) {
                deliverTextureFrame(iDequeueOutputBuffer, bufferInfo, i10, numValueOf);
            } else {
                deliverByteFrame(iDequeueOutputBuffer, bufferInfo, i10, numValueOf);
            }
        } catch (IllegalStateException e5) {
            Logging.e(TAG, "deliverDecodedFrame failed", e5);
        }
    }

    @Override // com.discord.org.webrtc.VideoDecoder
    public String getImplementationName() {
        return this.codecName;
    }

    @Override // com.discord.org.webrtc.VideoDecoder
    public VideoCodecStatus initDecode(VideoDecoder.Settings settings, VideoDecoder.Callback callback) {
        this.decoderThreadChecker = new ThreadUtils.ThreadChecker();
        this.callback = callback;
        this.hasExternalConsumer = false;
        String str = this.streamId;
        if (str != null && !str.isEmpty()) {
            Surface streamConsumer = DiscordDecoderStreamManager.getStreamConsumer(this.streamId);
            if (streamConsumer != null && streamConsumer.isValid()) {
                Logging.d(TAG, "initDecode: Using external surface, surfaceId: " + this.streamId);
                this.surface = streamConsumer;
                this.hasExternalConsumer = true;
                return initDecodeInternal(settings.width, settings.height);
            }
            DiscordDecoderStreamManager.addOnConsumerReadyListener(this.streamId, new Consumer() { // from class: com.discord.org.webrtc.a
                @Override // java.util.function.Consumer
                public final void accept(Object obj) {
                    this.f12504a.lambda$initDecode$0((Surface) obj);
                }

                public /* synthetic */ Consumer andThen(Consumer consumer) {
                    return Consumer$CC.$default$andThen(this, consumer);
                }
            });
        }
        if (this.sharedContext != null) {
            try {
                this.surfaceTextureHelper = createSurfaceTextureHelper();
                this.surface = new Surface(this.surfaceTextureHelper.getSurfaceTexture());
                this.surfaceTextureHelper.startListening(this);
            } catch (Throwable th) {
                Logging.e(TAG, "Error creating SurfaceTextureHelper", th);
                Surface surface = this.surface;
                if (surface != null) {
                    surface.release();
                    this.surface = null;
                }
                SurfaceTextureHelper surfaceTextureHelper = this.surfaceTextureHelper;
                if (surfaceTextureHelper != null) {
                    surfaceTextureHelper.dispose();
                    this.surfaceTextureHelper = null;
                }
                return VideoCodecStatus.FALLBACK_SOFTWARE;
            }
        }
        return initDecodeInternal(settings.width, settings.height);
    }

    @Override // com.discord.org.webrtc.VideoSink
    public void onFrame(VideoFrame videoFrame) {
        long j10;
        Integer num;
        synchronized (this.renderedTextureMetadataLock) {
            DecodedTextureMetadata decodedTextureMetadata = this.renderedTextureMetadata;
            if (decodedTextureMetadata == null) {
                throw new IllegalStateException("Rendered texture metadata was null in onTextureFrameAvailable.");
            }
            j10 = decodedTextureMetadata.presentationTimestampUs * 1000;
            num = decodedTextureMetadata.decodeTimeMs;
            this.renderedTextureMetadata = null;
        }
        this.callback.onDecodedFrame(new VideoFrame(videoFrame.getBuffer(), videoFrame.getRotation(), j10), num, null);
    }

    @Override // com.discord.org.webrtc.VideoDecoder
    public VideoCodecStatus release() {
        boolean z;
        Logging.d(TAG, "release");
        VideoCodecStatus videoCodecStatusReleaseInternal = releaseInternal();
        String str = this.streamId;
        if (str != null && !str.isEmpty()) {
            DiscordDecoderStreamManager.removeOnConsumerReadyListener(this.streamId);
        }
        if (this.surface != null) {
            synchronized (this.renderedTextureMetadataLock) {
                z = this.hasExternalConsumer;
                this.hasExternalConsumer = false;
            }
            if (z) {
                this.surface = null;
                SurfaceTextureHelper surfaceTextureHelper = this.surfaceTextureHelper;
                if (surfaceTextureHelper != null) {
                    surfaceTextureHelper.stopListening();
                    this.surfaceTextureHelper.dispose();
                    this.surfaceTextureHelper = null;
                }
            } else {
                releaseSurface();
                this.surface = null;
                this.surfaceTextureHelper.stopListening();
                this.surfaceTextureHelper.dispose();
                this.surfaceTextureHelper = null;
            }
        }
        synchronized (this.renderedTextureMetadataLock) {
            this.renderedTextureMetadata = null;
        }
        this.callback = null;
        this.frameInfos.clear();
        return videoCodecStatusReleaseInternal;
    }

    public void releaseSurface() {
        this.surface.release();
    }

    @Override // com.discord.org.webrtc.VideoDecoder
    public void setStreamId(String str) {
        this.streamId = str;
        if (str == null || str.isEmpty()) {
            return;
        }
        DiscordDecoderStreamManager.registerStreamProducer(str, this);
    }

    /* JADX INFO: renamed from: tryUpdateSurface, reason: merged with bridge method [inline-methods] */
    public void lambda$initDecode$0(Surface surface) {
        MediaCodecWrapper mediaCodecWrapper;
        synchronized (this.renderedTextureMetadataLock) {
            try {
                this.hasExternalConsumer = true;
                this.surface = surface;
                if (this.running && (mediaCodecWrapper = this.codec) != null) {
                    mediaCodecWrapper.setOutputSurface(surface);
                }
            } catch (Throwable th) {
                throw th;
            }
        }
    }
}
