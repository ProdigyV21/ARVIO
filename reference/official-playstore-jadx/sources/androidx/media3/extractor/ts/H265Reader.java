package androidx.media3.extractor.ts;

import ac.b;
import androidx.media3.common.C;
import androidx.media3.common.ColorInfo;
import androidx.media3.common.Format;
import androidx.media3.common.MimeTypes;
import androidx.media3.common.util.CodecSpecificDataUtil;
import androidx.media3.common.util.ParsableByteArray;
import androidx.media3.common.util.Util;
import androidx.media3.container.NalUnitUtil;
import androidx.media3.extractor.ExtractorOutput;
import androidx.media3.extractor.TrackOutput;
import androidx.media3.extractor.ts.TsPayloadReader;
import java.util.Collections;
import org.checkerframework.checker.nullness.qual.EnsuresNonNull;
import org.checkerframework.checker.nullness.qual.RequiresNonNull;

/* JADX INFO: loaded from: classes3.dex */
public final class H265Reader implements ElementaryStreamReader {
    private final String containerMimeType;
    private String formatId;
    private boolean hasOutputFormat;
    private TrackOutput output;
    private SampleReader sampleReader;
    private final SeiReader seiReader;
    private long totalBytesWritten;
    private final boolean[] prefixFlags = new boolean[3];
    private final NalUnitTargetBuffer vps = new NalUnitTargetBuffer(32, 128);
    private final NalUnitTargetBuffer sps = new NalUnitTargetBuffer(33, 128);
    private final NalUnitTargetBuffer pps = new NalUnitTargetBuffer(34, 128);
    private final NalUnitTargetBuffer prefixSei = new NalUnitTargetBuffer(39, 128);
    private final NalUnitTargetBuffer suffixSei = new NalUnitTargetBuffer(40, 128);
    private long pesTimeUs = C.TIME_UNSET;
    private final ParsableByteArray seiWrapper = new ParsableByteArray();

    public static final class SampleReader {
        private static final int FIRST_SLICE_FLAG_OFFSET = 2;
        private boolean isFirstPrefixNalUnit;
        private boolean isFirstSlice;
        private boolean lookingForFirstSliceFlag;
        private int nalUnitBytesRead;
        private boolean nalUnitHasKeyframeData;
        private long nalUnitPosition;
        private long nalUnitTimeUs;
        private final TrackOutput output;
        private boolean readingPrefix;
        private boolean readingSample;
        private boolean sampleIsKeyframe;
        private long samplePosition;
        private long sampleTimeUs;

        public SampleReader(TrackOutput trackOutput) {
            this.output = trackOutput;
        }

        private static boolean isPrefixNalUnit(int i10) {
            return (32 <= i10 && i10 <= 35) || i10 == 39;
        }

        private static boolean isVclBodyNalUnit(int i10) {
            return i10 < 32 || i10 == 40;
        }

        /* JADX WARN: Type inference fix 'apply assigned field type' failed
        java.lang.UnsupportedOperationException: ArgType.getObject(), call class: class jadx.core.dex.instructions.args.ArgType$PrimitiveArg
        	at jadx.core.dex.instructions.args.ArgType.getObject(ArgType.java:593)
        	at jadx.core.dex.attributes.nodes.ClassTypeVarsAttr.getTypeVarsMapFor(ClassTypeVarsAttr.java:35)
        	at jadx.core.dex.nodes.utils.TypeUtils.replaceClassGenerics(TypeUtils.java:177)
        	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.insertExplicitUseCast(FixTypesVisitor.java:397)
        	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.tryFieldTypeWithNewCasts(FixTypesVisitor.java:359)
        	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.applyFieldType(FixTypesVisitor.java:309)
        	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.visit(FixTypesVisitor.java:94)
         */
        private void outputSample(int i10) {
            long j10 = this.sampleTimeUs;
            if (j10 != C.TIME_UNSET) {
                long j11 = this.nalUnitPosition;
                long j12 = this.samplePosition;
                if (j11 == j12) {
                    return;
                }
                int i11 = (int) (j11 - j12);
                this.output.sampleMetadata(j10, this.sampleIsKeyframe ? 1 : 0, i11, i10, null);
            }
        }

        public void endNalUnit(long j10, int i10, boolean z) {
            if (this.readingPrefix && this.isFirstSlice) {
                this.sampleIsKeyframe = this.nalUnitHasKeyframeData;
                this.readingPrefix = false;
            } else if (this.isFirstPrefixNalUnit || this.isFirstSlice) {
                if (z && this.readingSample) {
                    outputSample(i10 + ((int) (j10 - this.nalUnitPosition)));
                }
                this.samplePosition = this.nalUnitPosition;
                this.sampleTimeUs = this.nalUnitTimeUs;
                this.sampleIsKeyframe = this.nalUnitHasKeyframeData;
                this.readingSample = true;
            }
        }

        public void readNalUnitData(byte[] bArr, int i10, int i11) {
            if (this.lookingForFirstSliceFlag) {
                int i12 = this.nalUnitBytesRead;
                int i13 = (i10 + 2) - i12;
                if (i13 >= i11) {
                    this.nalUnitBytesRead = (i11 - i10) + i12;
                } else {
                    this.isFirstSlice = (bArr[i13] & 128) != 0;
                    this.lookingForFirstSliceFlag = false;
                }
            }
        }

        public void reset() {
            this.lookingForFirstSliceFlag = false;
            this.isFirstSlice = false;
            this.isFirstPrefixNalUnit = false;
            this.readingSample = false;
            this.readingPrefix = false;
        }

        public void startNalUnit(long j10, int i10, int i11, long j11, boolean z) {
            this.isFirstSlice = false;
            this.isFirstPrefixNalUnit = false;
            this.nalUnitTimeUs = j11;
            this.nalUnitBytesRead = 0;
            this.nalUnitPosition = j10;
            if (!isVclBodyNalUnit(i11)) {
                if (this.readingSample && !this.readingPrefix) {
                    if (z) {
                        outputSample(i10);
                    }
                    this.readingSample = false;
                }
                if (isPrefixNalUnit(i11)) {
                    this.isFirstPrefixNalUnit = !this.readingPrefix;
                    this.readingPrefix = true;
                }
            }
            boolean z5 = i11 >= 16 && i11 <= 21;
            this.nalUnitHasKeyframeData = z5;
            this.lookingForFirstSliceFlag = z5 || i11 <= 9;
        }
    }

    public H265Reader(SeiReader seiReader, String str) {
        this.seiReader = seiReader;
        this.containerMimeType = str;
    }

    @EnsuresNonNull({"output", "sampleReader"})
    private void assertTracksCreated() {
        this.output.getClass();
        Util.castNonNull(this.sampleReader);
    }

    @RequiresNonNull({"output", "sampleReader"})
    private void endNalUnit(long j10, int i10, int i11, long j11) {
        this.sampleReader.endNalUnit(j10, i10, this.hasOutputFormat);
        if (!this.hasOutputFormat) {
            this.vps.endNalUnit(i11);
            this.sps.endNalUnit(i11);
            this.pps.endNalUnit(i11);
            if (this.vps.isCompleted() && this.sps.isCompleted() && this.pps.isCompleted()) {
                Format mediaFormat = parseMediaFormat(this.formatId, this.vps, this.sps, this.pps, this.containerMimeType);
                this.output.format(mediaFormat);
                b.s(mediaFormat.maxNumReorderSamples != -1);
                this.seiReader.setReorderingQueueSize(mediaFormat.maxNumReorderSamples);
                this.hasOutputFormat = true;
            }
        }
        if (this.prefixSei.endNalUnit(i11)) {
            NalUnitTargetBuffer nalUnitTargetBuffer = this.prefixSei;
            this.seiWrapper.reset(this.prefixSei.nalData, NalUnitUtil.unescapeStream(nalUnitTargetBuffer.nalData, nalUnitTargetBuffer.nalLength));
            this.seiWrapper.skipBytes(5);
            this.seiReader.consume(j11, this.seiWrapper);
        }
        if (this.suffixSei.endNalUnit(i11)) {
            NalUnitTargetBuffer nalUnitTargetBuffer2 = this.suffixSei;
            this.seiWrapper.reset(this.suffixSei.nalData, NalUnitUtil.unescapeStream(nalUnitTargetBuffer2.nalData, nalUnitTargetBuffer2.nalLength));
            this.seiWrapper.skipBytes(5);
            this.seiReader.consume(j11, this.seiWrapper);
        }
    }

    @RequiresNonNull({"sampleReader"})
    private void nalUnitData(byte[] bArr, int i10, int i11) {
        this.sampleReader.readNalUnitData(bArr, i10, i11);
        if (!this.hasOutputFormat) {
            this.vps.appendToNalUnit(bArr, i10, i11);
            this.sps.appendToNalUnit(bArr, i10, i11);
            this.pps.appendToNalUnit(bArr, i10, i11);
        }
        this.prefixSei.appendToNalUnit(bArr, i10, i11);
        this.suffixSei.appendToNalUnit(bArr, i10, i11);
    }

    private static Format parseMediaFormat(String str, NalUnitTargetBuffer nalUnitTargetBuffer, NalUnitTargetBuffer nalUnitTargetBuffer2, NalUnitTargetBuffer nalUnitTargetBuffer3, String str2) {
        int i10 = nalUnitTargetBuffer.nalLength;
        byte[] bArr = new byte[nalUnitTargetBuffer2.nalLength + i10 + nalUnitTargetBuffer3.nalLength];
        System.arraycopy(nalUnitTargetBuffer.nalData, 0, bArr, 0, i10);
        System.arraycopy(nalUnitTargetBuffer2.nalData, 0, bArr, nalUnitTargetBuffer.nalLength, nalUnitTargetBuffer2.nalLength);
        System.arraycopy(nalUnitTargetBuffer3.nalData, 0, bArr, nalUnitTargetBuffer.nalLength + nalUnitTargetBuffer2.nalLength, nalUnitTargetBuffer3.nalLength);
        NalUnitUtil.H265SpsData h265SpsNalUnit = NalUnitUtil.parseH265SpsNalUnit(nalUnitTargetBuffer2.nalData, 3, nalUnitTargetBuffer2.nalLength, null);
        NalUnitUtil.H265ProfileTierLevel h265ProfileTierLevel = h265SpsNalUnit.profileTierLevel;
        return new Format.Builder().setId(str).setContainerMimeType(str2).setSampleMimeType(MimeTypes.VIDEO_H265).setCodecs(h265ProfileTierLevel != null ? CodecSpecificDataUtil.buildHevcCodecString(h265ProfileTierLevel.generalProfileSpace, h265ProfileTierLevel.generalTierFlag, h265ProfileTierLevel.generalProfileIdc, h265ProfileTierLevel.generalProfileCompatibilityFlags, h265ProfileTierLevel.constraintBytes, h265ProfileTierLevel.generalLevelIdc) : null).setWidth(h265SpsNalUnit.width).setHeight(h265SpsNalUnit.height).setDecodedWidth(h265SpsNalUnit.decodedWidth).setDecodedHeight(h265SpsNalUnit.decodedHeight).setColorInfo(new ColorInfo.Builder().setColorSpace(h265SpsNalUnit.colorSpace).setColorRange(h265SpsNalUnit.colorRange).setColorTransfer(h265SpsNalUnit.colorTransfer).setLumaBitdepth(h265SpsNalUnit.bitDepthLumaMinus8 + 8).setChromaBitdepth(h265SpsNalUnit.bitDepthChromaMinus8 + 8).build()).setPixelWidthHeightRatio(h265SpsNalUnit.pixelWidthHeightRatio).setMaxNumReorderSamples(h265SpsNalUnit.maxNumReorderPics).setMaxSubLayers(h265SpsNalUnit.maxSubLayersMinus1 + 1).setInitializationData(Collections.singletonList(bArr)).build();
    }

    @RequiresNonNull({"sampleReader"})
    private void startNalUnit(long j10, int i10, int i11, long j11) {
        this.sampleReader.startNalUnit(j10, i10, i11, j11, this.hasOutputFormat);
        if (!this.hasOutputFormat) {
            this.vps.startNalUnit(i11);
            this.sps.startNalUnit(i11);
            this.pps.startNalUnit(i11);
        }
        this.prefixSei.startNalUnit(i11);
        this.suffixSei.startNalUnit(i11);
    }

    @Override // androidx.media3.extractor.ts.ElementaryStreamReader
    public void consume(ParsableByteArray parsableByteArray) {
        int i10;
        assertTracksCreated();
        while (parsableByteArray.bytesLeft() > 0) {
            int position = parsableByteArray.getPosition();
            int iLimit = parsableByteArray.limit();
            byte[] data = parsableByteArray.getData();
            this.totalBytesWritten += (long) parsableByteArray.bytesLeft();
            this.output.sampleData(parsableByteArray, parsableByteArray.bytesLeft());
            while (position < iLimit) {
                int iFindNalUnit = NalUnitUtil.findNalUnit(data, position, iLimit, this.prefixFlags);
                if (iFindNalUnit == iLimit) {
                    nalUnitData(data, position, iLimit);
                    return;
                }
                int h265NalUnitType = NalUnitUtil.getH265NalUnitType(data, iFindNalUnit);
                if (iFindNalUnit <= 0 || data[iFindNalUnit - 1] != 0) {
                    i10 = 3;
                } else {
                    iFindNalUnit--;
                    i10 = 4;
                }
                int i11 = iFindNalUnit;
                int i12 = i10;
                int i13 = i11 - position;
                if (i13 > 0) {
                    nalUnitData(data, position, i11);
                }
                int i14 = iLimit - i11;
                long j10 = this.totalBytesWritten - ((long) i14);
                endNalUnit(j10, i14, i13 < 0 ? -i13 : 0, this.pesTimeUs);
                startNalUnit(j10, i14, h265NalUnitType, this.pesTimeUs);
                position = i11 + i12;
            }
        }
    }

    @Override // androidx.media3.extractor.ts.ElementaryStreamReader
    public void createTracks(ExtractorOutput extractorOutput, TsPayloadReader.TrackIdGenerator trackIdGenerator) {
        trackIdGenerator.generateNewId();
        this.formatId = trackIdGenerator.getFormatId();
        TrackOutput trackOutputTrack = extractorOutput.track(trackIdGenerator.getTrackId(), 2);
        this.output = trackOutputTrack;
        this.sampleReader = new SampleReader(trackOutputTrack);
        this.seiReader.createTracks(extractorOutput, trackIdGenerator);
    }

    @Override // androidx.media3.extractor.ts.ElementaryStreamReader
    public void packetFinished(boolean z) {
        assertTracksCreated();
        if (z) {
            this.seiReader.flush();
            endNalUnit(this.totalBytesWritten, 0, 0, this.pesTimeUs);
            startNalUnit(this.totalBytesWritten, 0, 48, this.pesTimeUs);
        }
    }

    @Override // androidx.media3.extractor.ts.ElementaryStreamReader
    public void packetStarted(long j10, int i10) {
        this.pesTimeUs = j10;
    }

    @Override // androidx.media3.extractor.ts.ElementaryStreamReader
    public void seek() {
        this.totalBytesWritten = 0L;
        this.pesTimeUs = C.TIME_UNSET;
        NalUnitUtil.clearPrefixFlags(this.prefixFlags);
        this.vps.reset();
        this.sps.reset();
        this.pps.reset();
        this.prefixSei.reset();
        this.suffixSei.reset();
        this.seiReader.clear();
        SampleReader sampleReader = this.sampleReader;
        if (sampleReader != null) {
            sampleReader.reset();
        }
    }
}
