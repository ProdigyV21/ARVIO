package com.arflix.tv.ui.screens.player;

import android.util.Log;
import androidx.compose.ui.layout.LayoutKt;
import androidx.media3.common.audio.AudioProcessor;
import androidx.media3.common.audio.BaseAudioProcessor;
import androidx.media3.extractor.AacUtil;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;
import kotlin.Metadata;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000`\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0012\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\t\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0017\n\u0002\b\u0006\n\u0002\u0010 \n\u0002\u0010\n\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0010\u0006\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0007\b\u0007\u0018\u00002\u00020\u0001B\u0007¢\u0006\u0004\b\u0002\u0010\u0003J9\u0010\f\u001a\u00020\n2\u0006\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0007\u001a\u00020\u00062\u0018\u0010\u000b\u001a\u0014\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\t\u0012\u0004\u0012\u00020\n0\bH\u0002¢\u0006\u0004\b\f\u0010\rJ9\u0010\u000e\u001a\u00020\n2\u0006\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0007\u001a\u00020\u00062\u0018\u0010\u000b\u001a\u0014\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\t\u0012\u0004\u0012\u00020\n0\bH\u0002¢\u0006\u0004\b\u000e\u0010\rJ\u001f\u0010\u0011\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\u000f2\u0006\u0010\u0007\u001a\u00020\u0006H\u0002¢\u0006\u0004\b\u0011\u0010\u0012J1\u0010\u0014\u001a\u00020\n2\u0006\u0010\u0013\u001a\u00020\u000f2\u0018\u0010\u000b\u001a\u0014\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\t\u0012\u0004\u0012\u00020\n0\bH\u0002¢\u0006\u0004\b\u0014\u0010\u0015J\u001d\u0010\u0019\u001a\u00020\u00042\f\u0010\u0018\u001a\b\u0012\u0004\u0012\u00020\u00170\u0016H\u0002¢\u0006\u0004\b\u0019\u0010\u001aJ\u0017\u0010\u001d\u001a\u00020\u001b2\u0006\u0010\u001c\u001a\u00020\u001bH\u0014¢\u0006\u0004\b\u001d\u0010\u001eJ\u000f\u0010\u001f\u001a\u00020\nH\u0014¢\u0006\u0004\b\u001f\u0010\u0003J\u000f\u0010 \u001a\u00020\nH\u0014¢\u0006\u0004\b \u0010\u0003J\u0017\u0010#\u001a\u00020\n2\u0006\u0010\"\u001a\u00020!H\u0016¢\u0006\u0004\b#\u0010$R6\u0010%\u001a\u0016\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\t\u0012\u0004\u0012\u00020\n\u0018\u00010\b8\u0006@\u0006X\u0086\u000e¢\u0006\u0012\n\u0004\b%\u0010&\u001a\u0004\b'\u0010(\"\u0004\b)\u0010*R\u0016\u0010,\u001a\u00020+8\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b,\u0010-R\u0016\u0010.\u001a\u00020+8\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b.\u0010-R$\u00101\u001a\u0012\u0012\u0004\u0012\u00020\u00170/j\b\u0012\u0004\u0012\u00020\u0017`08\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b1\u00102R\u0016\u00103\u001a\u00020\u00178\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b3\u00104R\u0016\u00105\u001a\u00020\u00068\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b5\u00106¨\u00067"}, d2 = {"Lcom/arflix/tv/ui/screens/player/AudioCaptureProcessor;", "Landroidx/media3/common/audio/BaseAudioProcessor;", "<init>", "()V", "", "bytes", "", "channels", "Lkotlin/Function2;", "", "Lx6/t0;", "callback", "processPcm16", "([BILr7/p;)V", "processPcmFloat", "", "samples", "mixToMono", "([SI)[S", "mono", "resampleAndEmit", "([SLr7/p;)V", "", "", "shorts", "shortsToBytes", "(Ljava/util/List;)[B", "Landroidx/media3/common/audio/AudioProcessor$AudioFormat;", "inputAudioFormat", "onConfigure", "(Landroidx/media3/common/audio/AudioProcessor$AudioFormat;)Landroidx/media3/common/audio/AudioProcessor$AudioFormat;", "onFlush", "onReset", "Ljava/nio/ByteBuffer;", "inputBuffer", "queueInput", "(Ljava/nio/ByteBuffer;)V", "onChunk", "Lr7/p;", "getOnChunk", "()Lr7/p;", "setOnChunk", "(Lr7/p;)V", "", "srcStep", "D", "srcFrac", "Ljava/util/ArrayList;", "Lkotlin/collections/ArrayList;", "accum", "Ljava/util/ArrayList;", "prevMono", "S", "queueInputCallCount", "I", "app_playRelease"}, k = 1, mv = {2, 3, 0}, xi = 48)
public final class AudioCaptureProcessor extends BaseAudioProcessor {
    public static final int $stable = 8;
    private volatile r7.p<? super byte[], ? super Long, x6.t0> onChunk;
    private short prevMono;
    private int queueInputCallCount;
    private double srcFrac;
    private double srcStep = 1.0d;
    private final ArrayList<Short> accum = new ArrayList<>(3200);

    private final short[] mixToMono(short[] samples, int channels) {
        if (channels == 1) {
            return samples;
        }
        int i10 = 0;
        if (channels != 6) {
            int length = samples.length / channels;
            short[] sArr = new short[length];
            for (int i11 = 0; i11 < length; i11++) {
                long j10 = 0;
                for (int i12 = 0; i12 < channels; i12++) {
                    j10 += (long) samples[(i11 * channels) + i12];
                }
                sArr[i11] = (short) (j10 / ((long) channels));
            }
            return sArr;
        }
        int length2 = samples.length / 6;
        short[] sArr2 = new short[length2];
        while (i10 < length2) {
            int i13 = i10 * 6;
            long j11 = samples[i13];
            long j12 = samples[i13 + 1];
            long j13 = samples[i13 + 2];
            int i14 = i10;
            sArr2[i14] = (short) ((((((j11 + j12) + ((long) samples[i13 + 4])) + ((long) samples[i13 + 5])) * ((long) 707)) + (j13 * ((long) 1000))) / ((long) 3828));
            i10 = i14 + 1;
            length2 = length2;
        }
        return sArr2;
    }

    private final void processPcm16(byte[] bytes, int channels, r7.p<? super byte[], ? super Long, x6.t0> callback) {
        int length = bytes.length / 2;
        short[] sArr = new short[length];
        for (int i10 = 0; i10 < length; i10++) {
            sArr[i10] = ByteBuffer.wrap(bytes, i10 * 2, 2).order(ByteOrder.LITTLE_ENDIAN).getShort();
        }
        resampleAndEmit(mixToMono(sArr, channels), callback);
    }

    private final void processPcmFloat(byte[] bytes, int channels, r7.p<? super byte[], ? super Long, x6.t0> callback) {
        int iRemaining = ByteBuffer.wrap(bytes).order(ByteOrder.LITTLE_ENDIAN).asFloatBuffer().remaining();
        short[] sArr = new short[iRemaining];
        for (int i10 = 0; i10 < iRemaining; i10++) {
            sArr[i10] = (short) (qb.d.m(r7.get(), -1.0f, 1.0f) * LayoutKt.LargeDimension);
        }
        resampleAndEmit(mixToMono(sArr, channels), callback);
    }

    private final void resampleAndEmit(short[] mono, r7.p<? super byte[], ? super Long, x6.t0> callback) {
        if (mono.length == 0) {
            return;
        }
        double d4 = this.srcFrac;
        while (true) {
            if (d4 >= mono.length) {
                break;
            }
            int iN = qb.d.n((int) d4, 0, mono.length - 1);
            int i10 = iN + 1;
            short s10 = i10 < mono.length ? mono[i10] : mono.length > 0 ? this.prevMono : mono[0];
            double d10 = d4 - ((double) iN);
            this.accum.add(Short.valueOf((short) ((((double) s10) * d10) + ((1.0d - d10) * ((double) mono[iN])))));
            if (this.accum.size() >= 1600) {
                callback.invoke(shortsToBytes(this.accum), Long.valueOf(System.currentTimeMillis()));
                this.accum.clear();
            }
            d4 += this.srcStep;
        }
        this.srcFrac = d4 - ((double) mono.length);
        if (mono.length == 0) {
            return;
        }
        if (mono.length == 0) {
            throw new NoSuchElementException("Array is empty.");
        }
        this.prevMono = mono[mono.length - 1];
    }

    private final byte[] shortsToBytes(List<Short> shorts) {
        ByteBuffer byteBufferOrder = ByteBuffer.allocate(shorts.size() * 2).order(ByteOrder.LITTLE_ENDIAN);
        Iterator<Short> it = shorts.iterator();
        while (it.hasNext()) {
            byteBufferOrder.putShort(it.next().shortValue());
        }
        return byteBufferOrder.array();
    }

    public final r7.p<byte[], Long, x6.t0> getOnChunk() {
        return this.onChunk;
    }

    @Override // androidx.media3.common.audio.BaseAudioProcessor
    public AudioProcessor.AudioFormat onConfigure(AudioProcessor.AudioFormat inputAudioFormat) throws AudioProcessor.UnhandledAudioFormatException {
        int i10 = inputAudioFormat.encoding;
        int i11 = inputAudioFormat.sampleRate;
        int i12 = inputAudioFormat.channelCount;
        boolean z = this.onChunk != null;
        StringBuilder sbV = androidx.compose.foundation.c.v("onConfigure encoding=", i10, " rate=", i11, " ch=");
        sbV.append(i12);
        sbV.append(" onChunk=");
        sbV.append(z);
        Log.d("AudioCapture", sbV.toString());
        int i13 = inputAudioFormat.sampleRate;
        this.srcStep = i13 > 0 ? ((double) i13) / ((double) AacUtil.AAC_HE_V1_MAX_RATE_BYTES_PER_SECOND) : 1.0d;
        this.srcFrac = 0.0d;
        this.accum.clear();
        this.prevMono = (short) 0;
        return inputAudioFormat;
    }

    @Override // androidx.media3.common.audio.BaseAudioProcessor
    public void onFlush() {
        this.srcFrac = 0.0d;
        this.accum.clear();
        this.prevMono = (short) 0;
    }

    @Override // androidx.media3.common.audio.BaseAudioProcessor
    public void onReset() {
        this.srcFrac = 0.0d;
        this.accum.clear();
        this.prevMono = (short) 0;
    }

    @Override // androidx.media3.common.audio.AudioProcessor
    public void queueInput(ByteBuffer inputBuffer) {
        int i10 = this.queueInputCallCount + 1;
        this.queueInputCallCount = i10;
        if (i10 <= 3 || i10 % 500 == 0) {
            int iRemaining = inputBuffer.remaining();
            boolean z = this.onChunk != null;
            int i11 = this.inputAudioFormat.encoding;
            StringBuilder sbV = androidx.compose.foundation.c.v("queueInput #", i10, " remaining=", iRemaining, " onChunk=");
            sbV.append(z);
            sbV.append(" encoding=");
            sbV.append(i11);
            Log.d("AudioCapture", sbV.toString());
        }
        int iRemaining2 = inputBuffer.remaining();
        byte[] bArr = new byte[iRemaining2];
        inputBuffer.get(bArr);
        replaceOutputBuffer(iRemaining2).put(bArr).flip();
        r7.p<? super byte[], ? super Long, x6.t0> pVar = this.onChunk;
        if (pVar == null) {
            return;
        }
        AudioProcessor.AudioFormat audioFormat = this.inputAudioFormat;
        if (audioFormat.equals(AudioProcessor.AudioFormat.NOT_SET)) {
            return;
        }
        int i12 = audioFormat.encoding;
        if (i12 == 2) {
            processPcm16(bArr, audioFormat.channelCount, pVar);
        } else {
            if (i12 != 4) {
                return;
            }
            processPcmFloat(bArr, audioFormat.channelCount, pVar);
        }
    }

    public final void setOnChunk(r7.p<? super byte[], ? super Long, x6.t0> pVar) {
        this.onChunk = pVar;
    }
}
