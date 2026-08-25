package com.arflix.tv.player.dv;

import android.util.Log;
import androidx.compose.foundation.c;
import androidx.media3.common.util.ParsableByteArray;
import androidx.media3.container.DolbyVisionConfig;
import com.arflix.tv.player.dvmkv.MatroskaExtractor;
import java.util.concurrent.atomic.AtomicLong;
import kotlin.Metadata;
import kotlin.jvm.internal.h;
import x6.c0;

/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\u0012\n\u0000\n\u0002\u0010\b\n\u0002\b\r\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0010\u000e\n\u0002\b\t\b\u0001\u0018\u0000 (2\u00020\u0001:\u0001(B\u0011\u0012\b\b\u0002\u0010\u0003\u001a\u00020\u0002¢\u0006\u0004\b\u0004\u0010\u0005J)\u0010\u000b\u001a\u0004\u0018\u00010\u00062\u0006\u0010\u0007\u001a\u00020\u00062\u0006\u0010\t\u001a\u00020\b2\u0006\u0010\n\u001a\u00020\bH\u0002¢\u0006\u0004\b\u000b\u0010\fJ\u001b\u0010\u000e\u001a\u0004\u0018\u00010\b2\b\u0010\r\u001a\u0004\u0018\u00010\u0006H\u0002¢\u0006\u0004\b\u000e\u0010\u000fJ-\u0010\u0013\u001a\u0004\u0018\u00010\u00062\b\u0010\u0010\u001a\u0004\u0018\u00010\u00062\u0006\u0010\u0011\u001a\u00020\b2\b\u0010\u0012\u001a\u0004\u0018\u00010\u0006H\u0016¢\u0006\u0004\b\u0013\u0010\u0014J+\u0010\u0017\u001a\u00020\u00162\u0006\u0010\u0015\u001a\u00020\b2\b\u0010\u0010\u001a\u0004\u0018\u00010\u00062\b\u0010\u0012\u001a\u0004\u0018\u00010\u0006H\u0016¢\u0006\u0004\b\u0017\u0010\u0018J\u000f\u0010\u0019\u001a\u00020\bH\u0016¢\u0006\u0004\b\u0019\u0010\u001aJ?\u0010\u001e\u001a\u0004\u0018\u00010\u00062\b\u0010\u001b\u001a\u0004\u0018\u00010\u00062\u0006\u0010\u001c\u001a\u00020\b2\u0006\u0010\u001d\u001a\u00020\b2\b\u0010\u0010\u001a\u0004\u0018\u00010\u00062\b\u0010\u0012\u001a\u0004\u0018\u00010\u0006H\u0016¢\u0006\u0004\b\u001e\u0010\u001fJ%\u0010\"\u001a\u0004\u0018\u00010 2\b\u0010!\u001a\u0004\u0018\u00010 2\b\u0010\u0012\u001a\u0004\u0018\u00010\u0006H\u0016¢\u0006\u0004\b\"\u0010#R\u0014\u0010\u0003\u001a\u00020\u00028\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u0003\u0010$R\u0016\u0010%\u001a\u00020\b8\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b%\u0010&R\u0016\u0010'\u001a\u00020\u00028\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b'\u0010$¨\u0006)"}, d2 = {"Lcom/arflix/tv/player/dv/DolbyVisionStripTransformer;", "Lcom/arflix/tv/player/dvmkv/MatroskaExtractor$DolbyVisionSampleTransformer;", "", "stripHdr10PlusSei", "<init>", "(Z)V", "", "data", "", "len", "nalLengthFieldLength", "stripHdr10PlusIfEnabled", "([BII)[B", "configBytes", "resolveProfile", "([B)Ljava/lang/Integer;", "blockAdditionalData", "blockAddIdType", "dolbyVisionConfigBytes", "onDolbyVisionBlockAdditionalData", "([BI[B)[B", "sampleSizeBytes", "Lx6/t0;", "onHevcSample", "(I[B[B)V", "lastTransformedSampleLength", "()I", "sampleLengthDelimitedData", "sampleLength", "nalUnitLengthFieldLength", "transformHevcSample", "([BII[B[B)[B", "", "codecs", "onDolbyVisionCodecString", "(Ljava/lang/String;[B)Ljava/lang/String;", "Z", "lastTransformedLength", "I", "loggedFirstStrip", "Companion", "app_playRelease"}, k = 1, mv = {2, 3, 0}, xi = 48)
public final class DolbyVisionStripTransformer implements MatroskaExtractor.DolbyVisionSampleTransformer {
    private static final String TAG = "DvCompat";
    private int lastTransformedLength;
    private boolean loggedFirstStrip;
    private final boolean stripHdr10PlusSei;

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    public static final int $stable = 8;
    private static final AtomicLong samplesStripped = new AtomicLong(0);

    @Metadata(d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082T¢\u0006\u0002\n\u0000R\u0011\u0010\u0006\u001a\u00020\u0007¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\t¨\u0006\n"}, d2 = {"Lcom/arflix/tv/player/dv/DolbyVisionStripTransformer$Companion;", "", "<init>", "()V", "TAG", "", "samplesStripped", "Ljava/util/concurrent/atomic/AtomicLong;", "getSamplesStripped", "()Ljava/util/concurrent/atomic/AtomicLong;", "app_playRelease"}, k = 1, mv = {2, 3, 0}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(h hVar) {
            this();
        }

        public final AtomicLong getSamplesStripped() {
            return DolbyVisionStripTransformer.samplesStripped;
        }

        private Companion() {
        }
    }

    public DolbyVisionStripTransformer() {
        this(false, 1, null);
    }

    private final Integer resolveProfile(byte[] configBytes) {
        Object c0Var;
        if (configBytes == null || configBytes.length == 0) {
            return null;
        }
        try {
            DolbyVisionConfig dolbyVisionConfig = DolbyVisionConfig.parse(new ParsableByteArray(configBytes));
            c0Var = dolbyVisionConfig != null ? Integer.valueOf(dolbyVisionConfig.profile) : null;
        } catch (Throwable th) {
            c0Var = new c0(th);
        }
        return (Integer) (c0Var instanceof c0 ? null : c0Var);
    }

    private final byte[] stripHdr10PlusIfEnabled(byte[] data, int len, int nalLengthFieldLength) {
        byte[] bArrStripHdr10PlusLengthDelimited;
        if (!this.stripHdr10PlusSei || (bArrStripHdr10PlusLengthDelimited = HevcHdr10PlusStripper.INSTANCE.stripHdr10PlusLengthDelimited(data, len, nalLengthFieldLength)) == null) {
            return null;
        }
        this.lastTransformedLength = bArrStripHdr10PlusLengthDelimited.length;
        return bArrStripHdr10PlusLengthDelimited;
    }

    @Override // com.arflix.tv.player.dvmkv.MatroskaExtractor.DolbyVisionSampleTransformer
    /* JADX INFO: renamed from: lastTransformedSampleLength, reason: from getter */
    public int getLastTransformedLength() {
        return this.lastTransformedLength;
    }

    @Override // com.arflix.tv.player.dvmkv.MatroskaExtractor.DolbyVisionSampleTransformer
    public byte[] onDolbyVisionBlockAdditionalData(byte[] blockAdditionalData, int blockAddIdType, byte[] dolbyVisionConfigBytes) {
        if (blockAdditionalData == null) {
            return null;
        }
        return new byte[0];
    }

    @Override // com.arflix.tv.player.dvmkv.MatroskaExtractor.DolbyVisionSampleTransformer
    public String onDolbyVisionCodecString(String codecs, byte[] dolbyVisionConfigBytes) {
        return null;
    }

    @Override // com.arflix.tv.player.dvmkv.MatroskaExtractor.DolbyVisionSampleTransformer
    public void onHevcSample(int sampleSizeBytes, byte[] blockAdditionalData, byte[] dolbyVisionConfigBytes) {
    }

    @Override // com.arflix.tv.player.dvmkv.MatroskaExtractor.DolbyVisionSampleTransformer
    public byte[] transformHevcSample(byte[] sampleLengthDelimitedData, int sampleLength, int nalUnitLengthFieldLength, byte[] blockAdditionalData, byte[] dolbyVisionConfigBytes) {
        if (sampleLengthDelimitedData == null) {
            return null;
        }
        this.lastTransformedLength = sampleLength;
        Integer numResolveProfile = resolveProfile(dolbyVisionConfigBytes);
        if (numResolveProfile != null && numResolveProfile.intValue() == 5) {
            byte[] bArrStripHdr10PlusIfEnabled = stripHdr10PlusIfEnabled(sampleLengthDelimitedData, sampleLength, nalUnitLengthFieldLength);
            if (bArrStripHdr10PlusIfEnabled != null) {
                return bArrStripHdr10PlusIfEnabled;
            }
        } else {
            byte[] bArrStripRpuLengthDelimited = HevcDvRpuStripper.INSTANCE.stripRpuLengthDelimited(sampleLengthDelimitedData, sampleLength, nalUnitLengthFieldLength);
            if (bArrStripRpuLengthDelimited != null) {
                samplesStripped.incrementAndGet();
                if (!this.loggedFirstStrip) {
                    this.loggedFirstStrip = true;
                    Log.i(TAG, c.s("DV strip active: first sample rewritten (", sampleLength, " -> ", bArrStripRpuLengthDelimited.length, " bytes)"));
                }
                this.lastTransformedLength = bArrStripRpuLengthDelimited.length;
                byte[] bArrStripHdr10PlusIfEnabled2 = stripHdr10PlusIfEnabled(bArrStripRpuLengthDelimited, bArrStripRpuLengthDelimited.length, nalUnitLengthFieldLength);
                return bArrStripHdr10PlusIfEnabled2 == null ? bArrStripRpuLengthDelimited : bArrStripHdr10PlusIfEnabled2;
            }
            byte[] bArrStripHdr10PlusIfEnabled3 = stripHdr10PlusIfEnabled(sampleLengthDelimitedData, sampleLength, nalUnitLengthFieldLength);
            if (bArrStripHdr10PlusIfEnabled3 != null) {
                return bArrStripHdr10PlusIfEnabled3;
            }
        }
        return sampleLengthDelimitedData;
    }

    public DolbyVisionStripTransformer(boolean z) {
        this.stripHdr10PlusSei = z;
    }

    public /* synthetic */ DolbyVisionStripTransformer(boolean z, int i10, h hVar) {
        this((i10 & 1) != 0 ? false : z);
    }
}
