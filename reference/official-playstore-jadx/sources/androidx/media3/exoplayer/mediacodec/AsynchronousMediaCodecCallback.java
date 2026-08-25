package androidx.media3.exoplayer.mediacodec;

import android.media.MediaCodec;
import android.media.MediaFormat;
import android.os.Handler;
import android.os.HandlerThread;
import androidx.media3.common.util.Util;
import androidx.media3.exoplayer.mediacodec.MediaCodecAdapter;
import java.util.ArrayDeque;

/* JADX INFO: loaded from: classes3.dex */
final class AsynchronousMediaCodecCallback extends MediaCodec.Callback {
    private final HandlerThread callbackThread;
    private MediaFormat currentFormat;
    private Handler handler;
    private IllegalStateException internalException;
    private MediaCodec.CryptoException mediaCodecCryptoException;
    private MediaCodec.CodecException mediaCodecException;
    private MediaCodecAdapter.OnBufferAvailableListener onBufferAvailableListener;
    private long pendingFlushCount;
    private MediaFormat pendingOutputFormat;
    private boolean shutDown;
    private final Object lock = new Object();
    private final t.g availableInputBuffers = new t.g();
    private final t.g availableOutputBuffers = new t.g();
    private final ArrayDeque<MediaCodec.BufferInfo> bufferInfos = new ArrayDeque<>();
    private final ArrayDeque<MediaFormat> formats = new ArrayDeque<>();

    public AsynchronousMediaCodecCallback(HandlerThread handlerThread) {
        this.callbackThread = handlerThread;
    }

    private void addOutputFormat(MediaFormat mediaFormat) {
        this.availableOutputBuffers.a(-2);
        this.formats.add(mediaFormat);
    }

    private void flushInternal() {
        if (!this.formats.isEmpty()) {
            this.pendingOutputFormat = this.formats.getLast();
        }
        t.g gVar = this.availableInputBuffers;
        gVar.f21815c = gVar.f21814b;
        t.g gVar2 = this.availableOutputBuffers;
        gVar2.f21815c = gVar2.f21814b;
        this.bufferInfos.clear();
        this.formats.clear();
    }

    private boolean isFlushingOrShutdown() {
        return this.pendingFlushCount > 0 || this.shutDown;
    }

    private void maybeThrowException() {
        maybeThrowInternalException();
        maybeThrowMediaCodecException();
        maybeThrowMediaCodecCryptoException();
    }

    private void maybeThrowInternalException() {
        IllegalStateException illegalStateException = this.internalException;
        if (illegalStateException == null) {
            return;
        }
        this.internalException = null;
        throw illegalStateException;
    }

    private void maybeThrowMediaCodecCryptoException() {
        MediaCodec.CryptoException cryptoException = this.mediaCodecCryptoException;
        if (cryptoException == null) {
            return;
        }
        this.mediaCodecCryptoException = null;
        throw cryptoException;
    }

    private void maybeThrowMediaCodecException() {
        MediaCodec.CodecException codecException = this.mediaCodecException;
        if (codecException == null) {
            return;
        }
        this.mediaCodecException = null;
        throw codecException;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void onFlushCompleted() {
        synchronized (this.lock) {
            try {
                if (this.shutDown) {
                    return;
                }
                long j10 = this.pendingFlushCount - 1;
                this.pendingFlushCount = j10;
                if (j10 > 0) {
                    return;
                }
                if (j10 < 0) {
                    setInternalException(new IllegalStateException());
                } else {
                    flushInternal();
                }
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    private void setInternalException(IllegalStateException illegalStateException) {
        synchronized (this.lock) {
            this.internalException = illegalStateException;
        }
    }

    public int dequeueInputBufferIndex() {
        synchronized (this.lock) {
            try {
                maybeThrowException();
                int i10 = -1;
                if (isFlushingOrShutdown()) {
                    return -1;
                }
                t.g gVar = this.availableInputBuffers;
                int i11 = gVar.f21814b;
                int i12 = gVar.f21815c;
                if (!(i11 == i12)) {
                    if (i11 == i12) {
                        throw new ArrayIndexOutOfBoundsException();
                    }
                    i10 = gVar.f21813a[i11];
                    gVar.f21814b = (i11 + 1) & gVar.f21816d;
                }
                return i10;
            } finally {
            }
        }
    }

    public int dequeueOutputBufferIndex(MediaCodec.BufferInfo bufferInfo) {
        synchronized (this.lock) {
            try {
                maybeThrowException();
                if (isFlushingOrShutdown()) {
                    return -1;
                }
                t.g gVar = this.availableOutputBuffers;
                int i10 = gVar.f21814b;
                int i11 = gVar.f21815c;
                if (i10 == i11) {
                    return -1;
                }
                if (i10 == i11) {
                    throw new ArrayIndexOutOfBoundsException();
                }
                int i12 = gVar.f21813a[i10];
                gVar.f21814b = (i10 + 1) & gVar.f21816d;
                if (i12 >= 0) {
                    this.currentFormat.getClass();
                    MediaCodec.BufferInfo bufferInfoRemove = this.bufferInfos.remove();
                    bufferInfo.set(bufferInfoRemove.offset, bufferInfoRemove.size, bufferInfoRemove.presentationTimeUs, bufferInfoRemove.flags);
                } else if (i12 == -2) {
                    this.currentFormat = this.formats.remove();
                }
                return i12;
            } finally {
            }
        }
    }

    public void flush() {
        synchronized (this.lock) {
            this.pendingFlushCount++;
            ((Handler) Util.castNonNull(this.handler)).post(new Runnable() { // from class: androidx.media3.exoplayer.mediacodec.d
                @Override // java.lang.Runnable
                public final void run() {
                    this.f3703i.onFlushCompleted();
                }
            });
        }
    }

    public MediaFormat getOutputFormat() {
        MediaFormat mediaFormat;
        synchronized (this.lock) {
            try {
                mediaFormat = this.currentFormat;
                if (mediaFormat == null) {
                    throw new IllegalStateException();
                }
            } catch (Throwable th) {
                throw th;
            }
        }
        return mediaFormat;
    }

    public void initialize(MediaCodec mediaCodec) {
        ac.b.s(this.handler == null);
        this.callbackThread.start();
        Handler handler = new Handler(this.callbackThread.getLooper());
        mediaCodec.setCallback(this, handler);
        this.handler = handler;
    }

    @Override // android.media.MediaCodec.Callback
    public void onCryptoError(MediaCodec mediaCodec, MediaCodec.CryptoException cryptoException) {
        synchronized (this.lock) {
            this.mediaCodecCryptoException = cryptoException;
        }
    }

    @Override // android.media.MediaCodec.Callback
    public void onError(MediaCodec mediaCodec, MediaCodec.CodecException codecException) {
        synchronized (this.lock) {
            this.mediaCodecException = codecException;
        }
    }

    @Override // android.media.MediaCodec.Callback
    public void onInputBufferAvailable(MediaCodec mediaCodec, int i10) {
        synchronized (this.lock) {
            try {
                this.availableInputBuffers.a(i10);
                MediaCodecAdapter.OnBufferAvailableListener onBufferAvailableListener = this.onBufferAvailableListener;
                if (onBufferAvailableListener != null) {
                    onBufferAvailableListener.onInputBufferAvailable();
                }
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    @Override // android.media.MediaCodec.Callback
    public void onOutputBufferAvailable(MediaCodec mediaCodec, int i10, MediaCodec.BufferInfo bufferInfo) {
        synchronized (this.lock) {
            try {
                MediaFormat mediaFormat = this.pendingOutputFormat;
                if (mediaFormat != null) {
                    addOutputFormat(mediaFormat);
                    this.pendingOutputFormat = null;
                }
                this.availableOutputBuffers.a(i10);
                this.bufferInfos.add(bufferInfo);
                MediaCodecAdapter.OnBufferAvailableListener onBufferAvailableListener = this.onBufferAvailableListener;
                if (onBufferAvailableListener != null) {
                    onBufferAvailableListener.onOutputBufferAvailable();
                }
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    @Override // android.media.MediaCodec.Callback
    public void onOutputFormatChanged(MediaCodec mediaCodec, MediaFormat mediaFormat) {
        synchronized (this.lock) {
            addOutputFormat(mediaFormat);
            this.pendingOutputFormat = null;
        }
    }

    public void setOnBufferAvailableListener(MediaCodecAdapter.OnBufferAvailableListener onBufferAvailableListener) {
        synchronized (this.lock) {
            this.onBufferAvailableListener = onBufferAvailableListener;
        }
    }

    public void shutdown() {
        synchronized (this.lock) {
            this.shutDown = true;
            this.callbackThread.quit();
            flushInternal();
        }
    }

    public void useInputBuffer(Runnable runnable) {
        synchronized (this.lock) {
            maybeThrowException();
            runnable.run();
        }
    }
}
