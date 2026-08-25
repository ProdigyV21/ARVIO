package androidx.media3.extractor.text.vobsub;

import android.graphics.Bitmap;
import android.graphics.Rect;
import androidx.media3.common.C;
import androidx.media3.common.text.Cue;
import androidx.media3.common.util.Consumer;
import androidx.media3.common.util.Log;
import androidx.media3.common.util.ParsableBitArray;
import androidx.media3.common.util.ParsableByteArray;
import androidx.media3.common.util.Util;
import androidx.media3.exoplayer.dash.DashMediaSource;
import androidx.media3.extractor.text.CuesWithTiming;
import androidx.media3.extractor.text.Subtitle;
import androidx.media3.extractor.text.SubtitleParser;
import androidx.media3.extractor.text.b;
import com.google.common.collect.f1;
import com.google.common.collect.h1;
import com.google.common.collect.o3;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.List;
import java.util.zip.Inflater;

/* JADX INFO: loaded from: classes3.dex */
public final class VobsubParser implements SubtitleParser {
    public static final int CUE_REPLACEMENT_BEHAVIOR = 2;
    private static final int DEFAULT_DURATION_US = 5000000;
    private static final String TAG = "VobsubParser";
    private final CueBuilder cueBuilder;
    private Inflater inflater;
    private final ParsableByteArray scratch = new ParsableByteArray();
    private final ParsableByteArray inflatedScratch = new ParsableByteArray();

    public static final class CueBuilder {
        private static final int CMD_ALPHA = 4;
        private static final int CMD_AREA = 5;
        private static final int CMD_COLORS = 3;
        private static final int CMD_END = 255;
        private static final int CMD_FORCE_START = 0;
        private static final int CMD_OFFSETS = 6;
        private static final int CMD_START = 1;
        private static final int CMD_STOP = 2;
        private Rect boundingBox;
        private final int[] colors = new int[4];
        private int dataOffset0 = -1;
        private int dataOffset1 = -1;
        private boolean hasColors;
        private boolean hasPlane;
        private int[] palette;
        private int planeHeight;
        private int planeWidth;

        public static final class Run {
            public int colorIndex;
            public int length;

            private Run() {
            }
        }

        private static int getColor(int[] iArr, int i10) {
            return (i10 < 0 || i10 >= iArr.length) ? iArr[0] : iArr[i10];
        }

        private static int parseColor(String str) {
            try {
                return Integer.parseInt(str, 16);
            } catch (RuntimeException unused) {
                return 0;
            }
        }

        private void parseControl(int[] iArr, ParsableByteArray parsableByteArray, int i10) {
            while (parsableByteArray.getPosition() < i10 && parsableByteArray.bytesLeft() > 0) {
                switch (parsableByteArray.readUnsignedByte()) {
                    case 0:
                    case 1:
                    case 2:
                        break;
                    case 3:
                        if (!parseControlColors(iArr, parsableByteArray)) {
                            return;
                        }
                        break;
                    case 4:
                        if (!parseControlAlpha(parsableByteArray)) {
                            return;
                        }
                        break;
                    case 5:
                        if (!parseControlArea(parsableByteArray)) {
                            return;
                        }
                        break;
                    case 6:
                        if (!parseControlOffsets(parsableByteArray)) {
                            return;
                        }
                        break;
                    default:
                        return;
                }
            }
        }

        private boolean parseControlAlpha(ParsableByteArray parsableByteArray) {
            if (parsableByteArray.bytesLeft() < 2 || !this.hasColors) {
                return false;
            }
            int unsignedByte = parsableByteArray.readUnsignedByte();
            int unsignedByte2 = parsableByteArray.readUnsignedByte();
            int[] iArr = this.colors;
            iArr[3] = setAlpha(iArr[3], unsignedByte >> 4);
            int[] iArr2 = this.colors;
            iArr2[2] = setAlpha(iArr2[2], unsignedByte & 15);
            int[] iArr3 = this.colors;
            iArr3[1] = setAlpha(iArr3[1], unsignedByte2 >> 4);
            int[] iArr4 = this.colors;
            iArr4[0] = setAlpha(iArr4[0], unsignedByte2 & 15);
            return true;
        }

        private boolean parseControlArea(ParsableByteArray parsableByteArray) {
            if (parsableByteArray.bytesLeft() < 6) {
                return false;
            }
            int unsignedByte = parsableByteArray.readUnsignedByte();
            int unsignedByte2 = parsableByteArray.readUnsignedByte();
            int i10 = (unsignedByte << 4) | (unsignedByte2 >> 4);
            int unsignedByte3 = ((unsignedByte2 & 15) << 8) | parsableByteArray.readUnsignedByte();
            int unsignedByte4 = parsableByteArray.readUnsignedByte();
            int unsignedByte5 = parsableByteArray.readUnsignedByte();
            this.boundingBox = new Rect(i10, (unsignedByte4 << 4) | (unsignedByte5 >> 4), unsignedByte3 + 1, (parsableByteArray.readUnsignedByte() | ((unsignedByte5 & 15) << 8)) + 1);
            return true;
        }

        private boolean parseControlColors(int[] iArr, ParsableByteArray parsableByteArray) {
            if (parsableByteArray.bytesLeft() < 2) {
                return false;
            }
            int unsignedByte = parsableByteArray.readUnsignedByte();
            int unsignedByte2 = parsableByteArray.readUnsignedByte();
            this.colors[3] = getColor(iArr, unsignedByte >> 4);
            this.colors[2] = getColor(iArr, unsignedByte & 15);
            this.colors[1] = getColor(iArr, unsignedByte2 >> 4);
            this.colors[0] = getColor(iArr, unsignedByte2 & 15);
            this.hasColors = true;
            return true;
        }

        private boolean parseControlOffsets(ParsableByteArray parsableByteArray) {
            if (parsableByteArray.bytesLeft() < 4) {
                return false;
            }
            this.dataOffset0 = parsableByteArray.readUnsignedShort();
            this.dataOffset1 = parsableByteArray.readUnsignedShort();
            return true;
        }

        private void parseRleData(ParsableBitArray parsableBitArray, boolean z, Rect rect, int[] iArr) {
            int iWidth = rect.width();
            int iHeight = rect.height();
            int i10 = !z ? 1 : 0;
            int i11 = i10 * iWidth;
            Run run = new Run();
            while (true) {
                int i12 = 0;
                do {
                    parseRun(parsableBitArray, iWidth, run);
                    int iMin = Math.min(run.length, iWidth - i12);
                    if (iMin > 0) {
                        int i13 = i11 + iMin;
                        Arrays.fill(iArr, i11, i13, this.colors[run.colorIndex]);
                        i12 += iMin;
                        i11 = i13;
                    }
                } while (i12 < iWidth);
                i10 += 2;
                if (i10 >= iHeight) {
                    return;
                }
                i11 = i10 * iWidth;
                parsableBitArray.byteAlign();
            }
        }

        private static void parseRun(ParsableBitArray parsableBitArray, int i10, Run run) {
            int bits = 0;
            for (int i11 = 1; bits < i11 && i11 <= 64; i11 <<= 2) {
                if (parsableBitArray.bitsLeft() < 4) {
                    run.colorIndex = -1;
                    run.length = 0;
                    return;
                }
                bits = (bits << 4) | parsableBitArray.readBits(4);
            }
            run.colorIndex = bits & 3;
            if (bits >= 4) {
                i10 = bits >> 2;
            }
            run.length = i10;
        }

        private static int setAlpha(int i10, int i11) {
            return (i10 & 16777215) | ((i11 * 17) << 24);
        }

        public Cue build(ParsableByteArray parsableByteArray) {
            Rect rect;
            if (this.palette == null || !this.hasPlane || !this.hasColors || (rect = this.boundingBox) == null || this.dataOffset0 == -1 || this.dataOffset1 == -1 || rect.width() < 2 || this.boundingBox.height() < 2) {
                return null;
            }
            Rect rect2 = this.boundingBox;
            int[] iArr = new int[rect2.height() * rect2.width()];
            ParsableBitArray parsableBitArray = new ParsableBitArray();
            parsableByteArray.setPosition(this.dataOffset0);
            parsableBitArray.reset(parsableByteArray);
            parseRleData(parsableBitArray, true, rect2, iArr);
            parsableByteArray.setPosition(this.dataOffset1);
            parsableBitArray.reset(parsableByteArray);
            parseRleData(parsableBitArray, false, rect2, iArr);
            return new Cue.Builder().setBitmap(Bitmap.createBitmap(iArr, rect2.width(), rect2.height(), Bitmap.Config.ARGB_8888)).setPosition(rect2.left / this.planeWidth).setPositionAnchor(0).setLine(rect2.top / this.planeHeight, 0).setLineAnchor(0).setSize(rect2.width() / this.planeWidth).setBitmapHeight(rect2.height() / this.planeHeight).build();
        }

        public void parseIdx(String str) {
            for (String str2 : Util.split(str.trim(), "\\r?\\n")) {
                if (str2.startsWith("palette: ")) {
                    String[] strArrSplit = Util.split(str2.substring(9), ",");
                    this.palette = new int[strArrSplit.length];
                    for (int i10 = 0; i10 < strArrSplit.length; i10++) {
                        this.palette[i10] = parseColor(strArrSplit[i10].trim());
                    }
                } else if (str2.startsWith("size: ")) {
                    String[] strArrSplit2 = Util.split(str2.substring(6).trim(), "x");
                    if (strArrSplit2.length == 2) {
                        try {
                            this.planeWidth = Integer.parseInt(strArrSplit2[0]);
                            this.planeHeight = Integer.parseInt(strArrSplit2[1]);
                            this.hasPlane = true;
                        } catch (RuntimeException e5) {
                            Log.w(VobsubParser.TAG, "Parsing IDX failed", e5);
                        }
                    }
                }
            }
        }

        public void parseSpu(ParsableByteArray parsableByteArray) {
            int[] iArr = this.palette;
            if (iArr == null || !this.hasPlane) {
                return;
            }
            parsableByteArray.skipBytes(parsableByteArray.readUnsignedShort() - 2);
            parseControl(iArr, parsableByteArray, parsableByteArray.readUnsignedShort());
        }

        public void reset() {
            this.hasColors = false;
            this.boundingBox = null;
            this.dataOffset0 = -1;
            this.dataOffset1 = -1;
        }
    }

    public VobsubParser(List<byte[]> list) {
        CueBuilder cueBuilder = new CueBuilder();
        this.cueBuilder = cueBuilder;
        cueBuilder.parseIdx(new String(list.get(0), StandardCharsets.UTF_8));
    }

    @Override // androidx.media3.extractor.text.SubtitleParser
    public int getCueReplacementBehavior() {
        return 2;
    }

    @Override // androidx.media3.extractor.text.SubtitleParser
    public final /* synthetic */ void parse(byte[] bArr, SubtitleParser.OutputOptions outputOptions, Consumer consumer) {
        b.a(this, bArr, outputOptions, consumer);
    }

    @Override // androidx.media3.extractor.text.SubtitleParser
    public final /* synthetic */ Subtitle parseToLegacySubtitle(byte[] bArr, int i10, int i11) {
        return b.b(this, bArr, i10, i11);
    }

    @Override // androidx.media3.extractor.text.SubtitleParser
    public final /* synthetic */ void reset() {
        b.c(this);
    }

    @Override // androidx.media3.extractor.text.SubtitleParser
    public void parse(byte[] bArr, int i10, int i11, SubtitleParser.OutputOptions outputOptions, Consumer<CuesWithTiming> consumer) {
        o3 o3VarS;
        this.scratch.reset(bArr, i11 + i10);
        this.scratch.setPosition(i10);
        Cue cue = parse();
        if (cue != null) {
            o3VarS = h1.s(cue);
        } else {
            f1 f1Var = h1.f14020l;
            o3VarS = o3.f14078o;
        }
        consumer.accept(new CuesWithTiming(o3VarS, C.TIME_UNSET, DashMediaSource.MIN_LIVE_DEFAULT_START_POSITION_US));
    }

    private Cue parse() {
        if (this.inflater == null) {
            this.inflater = new Inflater();
        }
        if (Util.maybeInflate(this.scratch, this.inflatedScratch, this.inflater)) {
            this.scratch.reset(this.inflatedScratch.getData(), this.inflatedScratch.limit());
        }
        this.cueBuilder.reset();
        int iBytesLeft = this.scratch.bytesLeft();
        if (iBytesLeft < 2 || this.scratch.readUnsignedShort() != iBytesLeft) {
            return null;
        }
        this.cueBuilder.parseSpu(this.scratch);
        return this.cueBuilder.build(this.scratch);
    }
}
