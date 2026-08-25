package androidx.media3.extractor.text.dvb;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.util.SparseArray;
import androidx.media3.common.C;
import androidx.media3.common.text.Cue;
import androidx.media3.common.util.Consumer;
import androidx.media3.common.util.Log;
import androidx.media3.common.util.ParsableBitArray;
import androidx.media3.common.util.ParsableByteArray;
import androidx.media3.common.util.Util;
import androidx.media3.exoplayer.trackselection.AdaptiveTrackSelection;
import androidx.media3.extractor.text.CuesWithTiming;
import androidx.media3.extractor.text.Subtitle;
import androidx.media3.extractor.text.SubtitleParser;
import androidx.media3.extractor.text.b;
import androidx.media3.extractor.ts.TsExtractor;
import com.google.common.collect.f1;
import com.google.common.collect.h1;
import com.google.common.collect.o3;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: loaded from: classes3.dex */
public final class DvbParser implements SubtitleParser {
    public static final int CUE_REPLACEMENT_BEHAVIOR = 2;
    private static final int DATA_TYPE_24_TABLE_DATA = 32;
    private static final int DATA_TYPE_28_TABLE_DATA = 33;
    private static final int DATA_TYPE_2BP_CODE_STRING = 16;
    private static final int DATA_TYPE_48_TABLE_DATA = 34;
    private static final int DATA_TYPE_4BP_CODE_STRING = 17;
    private static final int DATA_TYPE_8BP_CODE_STRING = 18;
    private static final int DATA_TYPE_END_LINE = 240;
    private static final int OBJECT_CODING_PIXELS = 0;
    private static final int OBJECT_CODING_STRING = 1;
    private static final int PAGE_STATE_NORMAL = 0;
    private static final int REGION_DEPTH_4_BIT = 2;
    private static final int REGION_DEPTH_8_BIT = 3;
    private static final int SEGMENT_TYPE_CLUT_DEFINITION = 18;
    private static final int SEGMENT_TYPE_DISPLAY_DEFINITION = 20;
    private static final int SEGMENT_TYPE_OBJECT_DATA = 19;
    private static final int SEGMENT_TYPE_PAGE_COMPOSITION = 16;
    private static final int SEGMENT_TYPE_REGION_COMPOSITION = 17;
    private static final String TAG = "DvbParser";
    private static final byte[] defaultMap2To4 = {0, 7, 8, 15};
    private static final byte[] defaultMap2To8 = {0, 119, -120, -1};
    private static final byte[] defaultMap4To8 = {0, 17, 34, 51, 68, 85, 102, 119, -120, -103, -86, -69, -52, -35, -18, -1};
    private Bitmap bitmap;
    private final Canvas canvas;
    private final ClutDefinition defaultClutDefinition;
    private final DisplayDefinition defaultDisplayDefinition;
    private final Paint defaultPaint;
    private final Paint fillRegionPaint;
    private final SubtitleService subtitleService;

    public static final class ClutDefinition {
        public final int[] clutEntries2Bit;
        public final int[] clutEntries4Bit;
        public final int[] clutEntries8Bit;
        public final int id;

        public ClutDefinition(int i10, int[] iArr, int[] iArr2, int[] iArr3) {
            this.id = i10;
            this.clutEntries2Bit = iArr;
            this.clutEntries4Bit = iArr2;
            this.clutEntries8Bit = iArr3;
        }
    }

    public static final class DisplayDefinition {
        public final int height;
        public final int horizontalPositionMaximum;
        public final int horizontalPositionMinimum;
        public final int verticalPositionMaximum;
        public final int verticalPositionMinimum;
        public final int width;

        public DisplayDefinition(int i10, int i11, int i12, int i13, int i14, int i15) {
            this.width = i10;
            this.height = i11;
            this.horizontalPositionMinimum = i12;
            this.horizontalPositionMaximum = i13;
            this.verticalPositionMinimum = i14;
            this.verticalPositionMaximum = i15;
        }
    }

    public static final class ObjectData {
        public final byte[] bottomFieldData;
        public final int id;
        public final boolean nonModifyingColorFlag;
        public final byte[] topFieldData;

        public ObjectData(int i10, boolean z, byte[] bArr, byte[] bArr2) {
            this.id = i10;
            this.nonModifyingColorFlag = z;
            this.topFieldData = bArr;
            this.bottomFieldData = bArr2;
        }
    }

    public static final class PageComposition {
        public final SparseArray<PageRegion> regions;
        public final int state;
        public final int timeOutSecs;
        public final int version;

        public PageComposition(int i10, int i11, int i12, SparseArray<PageRegion> sparseArray) {
            this.timeOutSecs = i10;
            this.version = i11;
            this.state = i12;
            this.regions = sparseArray;
        }
    }

    public static final class PageRegion {
        public final int horizontalAddress;
        public final int verticalAddress;

        public PageRegion(int i10, int i11) {
            this.horizontalAddress = i10;
            this.verticalAddress = i11;
        }
    }

    public static final class RegionComposition {
        public final int clutId;
        public final int depth;
        public final boolean fillFlag;
        public final int height;
        public final int id;
        public final int levelOfCompatibility;
        public final int pixelCode2Bit;
        public final int pixelCode4Bit;
        public final int pixelCode8Bit;
        public final SparseArray<RegionObject> regionObjects;
        public final int width;

        public RegionComposition(int i10, boolean z, int i11, int i12, int i13, int i14, int i15, int i16, int i17, int i18, SparseArray<RegionObject> sparseArray) {
            this.id = i10;
            this.fillFlag = z;
            this.width = i11;
            this.height = i12;
            this.levelOfCompatibility = i13;
            this.depth = i14;
            this.clutId = i15;
            this.pixelCode8Bit = i16;
            this.pixelCode4Bit = i17;
            this.pixelCode2Bit = i18;
            this.regionObjects = sparseArray;
        }

        public void mergeFrom(RegionComposition regionComposition) {
            SparseArray<RegionObject> sparseArray = regionComposition.regionObjects;
            for (int i10 = 0; i10 < sparseArray.size(); i10++) {
                this.regionObjects.put(sparseArray.keyAt(i10), sparseArray.valueAt(i10));
            }
        }
    }

    public static final class RegionObject {
        public final int backgroundPixelCode;
        public final int foregroundPixelCode;
        public final int horizontalPosition;
        public final int provider;
        public final int type;
        public final int verticalPosition;

        public RegionObject(int i10, int i11, int i12, int i13, int i14, int i15) {
            this.type = i10;
            this.provider = i11;
            this.horizontalPosition = i12;
            this.verticalPosition = i13;
            this.foregroundPixelCode = i14;
            this.backgroundPixelCode = i15;
        }
    }

    public static final class SubtitleService {
        public final int ancillaryPageId;
        public DisplayDefinition displayDefinition;
        public PageComposition pageComposition;
        public final int subtitlePageId;
        public final SparseArray<RegionComposition> regions = new SparseArray<>();
        public final SparseArray<ClutDefinition> cluts = new SparseArray<>();
        public final SparseArray<ObjectData> objects = new SparseArray<>();
        public final SparseArray<ClutDefinition> ancillaryCluts = new SparseArray<>();
        public final SparseArray<ObjectData> ancillaryObjects = new SparseArray<>();

        public SubtitleService(int i10, int i11) {
            this.subtitlePageId = i10;
            this.ancillaryPageId = i11;
        }

        public void reset() {
            this.regions.clear();
            this.cluts.clear();
            this.objects.clear();
            this.ancillaryCluts.clear();
            this.ancillaryObjects.clear();
            this.displayDefinition = null;
            this.pageComposition = null;
        }
    }

    public DvbParser(List<byte[]> list) {
        ParsableByteArray parsableByteArray = new ParsableByteArray(list.get(0));
        int unsignedShort = parsableByteArray.readUnsignedShort();
        int unsignedShort2 = parsableByteArray.readUnsignedShort();
        Paint paint = new Paint();
        this.defaultPaint = paint;
        paint.setStyle(Paint.Style.FILL_AND_STROKE);
        paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC));
        paint.setPathEffect(null);
        Paint paint2 = new Paint();
        this.fillRegionPaint = paint2;
        paint2.setStyle(Paint.Style.FILL);
        paint2.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.DST_OVER));
        paint2.setPathEffect(null);
        this.canvas = new Canvas();
        this.defaultDisplayDefinition = new DisplayDefinition(AdaptiveTrackSelection.DEFAULT_MAX_HEIGHT_TO_DISCARD, 575, 0, AdaptiveTrackSelection.DEFAULT_MAX_HEIGHT_TO_DISCARD, 0, 575);
        this.defaultClutDefinition = new ClutDefinition(0, generateDefault2BitClutEntries(), generateDefault4BitClutEntries(), generateDefault8BitClutEntries());
        this.subtitleService = new SubtitleService(unsignedShort, unsignedShort2);
    }

    private static byte[] buildClutMapTable(int i10, int i11, ParsableBitArray parsableBitArray) {
        byte[] bArr = new byte[i10];
        for (int i12 = 0; i12 < i10; i12++) {
            bArr[i12] = (byte) parsableBitArray.readBits(i11);
        }
        return bArr;
    }

    private static int[] generateDefault2BitClutEntries() {
        return new int[]{0, -1, -16777216, -8421505};
    }

    private static int[] generateDefault4BitClutEntries() {
        int[] iArr = new int[16];
        iArr[0] = 0;
        for (int i10 = 1; i10 < 16; i10++) {
            if (i10 < 8) {
                iArr[i10] = getColor(255, (i10 & 1) != 0 ? 255 : 0, (i10 & 2) != 0 ? 255 : 0, (i10 & 4) != 0 ? 255 : 0);
            } else {
                iArr[i10] = getColor(255, (i10 & 1) != 0 ? 127 : 0, (i10 & 2) != 0 ? 127 : 0, (i10 & 4) == 0 ? 0 : 127);
            }
        }
        return iArr;
    }

    private static int[] generateDefault8BitClutEntries() {
        int[] iArr = new int[256];
        iArr[0] = 0;
        for (int i10 = 0; i10 < 256; i10++) {
            if (i10 < 8) {
                iArr[i10] = getColor(63, (i10 & 1) != 0 ? 255 : 0, (i10 & 2) != 0 ? 255 : 0, (i10 & 4) == 0 ? 0 : 255);
            } else {
                int i11 = i10 & TsExtractor.TS_STREAM_TYPE_DTS_HD;
                if (i11 == 0) {
                    iArr[i10] = getColor(255, ((i10 & 1) != 0 ? 85 : 0) + ((i10 & 16) != 0 ? 170 : 0), ((i10 & 2) != 0 ? 85 : 0) + ((i10 & 32) != 0 ? 170 : 0), ((i10 & 4) == 0 ? 0 : 85) + ((i10 & 64) == 0 ? 0 : 170));
                } else if (i11 == 8) {
                    iArr[i10] = getColor(127, ((i10 & 1) != 0 ? 85 : 0) + ((i10 & 16) != 0 ? 170 : 0), ((i10 & 2) != 0 ? 85 : 0) + ((i10 & 32) != 0 ? 170 : 0), ((i10 & 4) == 0 ? 0 : 85) + ((i10 & 64) == 0 ? 0 : 170));
                } else if (i11 == 128) {
                    iArr[i10] = getColor(255, ((i10 & 1) != 0 ? 43 : 0) + 127 + ((i10 & 16) != 0 ? 85 : 0), ((i10 & 2) != 0 ? 43 : 0) + 127 + ((i10 & 32) != 0 ? 85 : 0), ((i10 & 4) == 0 ? 0 : 43) + 127 + ((i10 & 64) == 0 ? 0 : 85));
                } else if (i11 == 136) {
                    iArr[i10] = getColor(255, ((i10 & 1) != 0 ? 43 : 0) + ((i10 & 16) != 0 ? 85 : 0), ((i10 & 2) != 0 ? 43 : 0) + ((i10 & 32) != 0 ? 85 : 0), ((i10 & 4) == 0 ? 0 : 43) + ((i10 & 64) == 0 ? 0 : 85));
                }
            }
        }
        return iArr;
    }

    private static int getColor(int i10, int i11, int i12, int i13) {
        return (i10 << 24) | (i11 << 16) | (i12 << 8) | i13;
    }

    private static int paint2BitPixelCodeString(ParsableBitArray parsableBitArray, int[] iArr, byte[] bArr, int i10, int i11, Paint paint, Canvas canvas) {
        boolean z;
        int i12;
        int bits;
        int bits2;
        boolean z5 = false;
        while (true) {
            int bits3 = parsableBitArray.readBits(2);
            if (bits3 != 0) {
                z = z5;
                i12 = 1;
            } else {
                if (parsableBitArray.readBit()) {
                    bits = parsableBitArray.readBits(3) + 3;
                    bits2 = parsableBitArray.readBits(2);
                } else {
                    if (parsableBitArray.readBit()) {
                        z = z5;
                        i12 = 1;
                    } else {
                        int bits4 = parsableBitArray.readBits(2);
                        if (bits4 == 0) {
                            z = true;
                        } else if (bits4 == 1) {
                            z = z5;
                            i12 = 2;
                        } else if (bits4 == 2) {
                            bits = parsableBitArray.readBits(4) + 12;
                            bits2 = parsableBitArray.readBits(2);
                        } else if (bits4 != 3) {
                            z = z5;
                        } else {
                            bits = parsableBitArray.readBits(8) + 29;
                            bits2 = parsableBitArray.readBits(2);
                        }
                        bits3 = 0;
                        i12 = 0;
                    }
                    bits3 = 0;
                }
                z = z5;
                i12 = bits;
                bits3 = bits2;
            }
            if (i12 != 0 && paint != null) {
                if (bArr != null) {
                    bits3 = bArr[bits3];
                }
                paint.setColor(iArr[bits3]);
                canvas.drawRect(i10, i11, i10 + i12, 1 + i11, paint);
            }
            i10 += i12;
            if (z) {
                return i10;
            }
            z5 = z;
        }
    }

    private static int paint4BitPixelCodeString(ParsableBitArray parsableBitArray, int[] iArr, byte[] bArr, int i10, int i11, Paint paint, Canvas canvas) {
        boolean z;
        int i12;
        int bits;
        int bits2;
        boolean z5 = false;
        while (true) {
            int bits3 = parsableBitArray.readBits(4);
            if (bits3 != 0) {
                z = z5;
                i12 = 1;
            } else if (parsableBitArray.readBit()) {
                if (parsableBitArray.readBit()) {
                    int bits4 = parsableBitArray.readBits(2);
                    if (bits4 == 0) {
                        z = z5;
                        i12 = 1;
                        bits3 = 0;
                    } else if (bits4 == 1) {
                        bits3 = 0;
                        i12 = 2;
                        z = z5;
                    } else if (bits4 == 2) {
                        bits = parsableBitArray.readBits(4) + 9;
                        bits2 = parsableBitArray.readBits(4);
                    } else if (bits4 != 3) {
                        z = z5;
                        bits3 = 0;
                        i12 = 0;
                    } else {
                        bits = parsableBitArray.readBits(8) + 25;
                        bits2 = parsableBitArray.readBits(4);
                    }
                } else {
                    bits = parsableBitArray.readBits(2) + 4;
                    bits2 = parsableBitArray.readBits(4);
                }
                z = z5;
                i12 = bits;
                bits3 = bits2;
            } else {
                int bits5 = parsableBitArray.readBits(3);
                if (bits5 != 0) {
                    z = z5;
                    i12 = bits5 + 2;
                    bits3 = 0;
                } else {
                    z = true;
                    bits3 = 0;
                    i12 = 0;
                }
            }
            if (i12 != 0 && paint != null) {
                if (bArr != null) {
                    bits3 = bArr[bits3];
                }
                paint.setColor(iArr[bits3]);
                canvas.drawRect(i10, i11, i10 + i12, 1 + i11, paint);
            }
            i10 += i12;
            if (z) {
                return i10;
            }
            z5 = z;
        }
    }

    private static int paint8BitPixelCodeString(ParsableBitArray parsableBitArray, int[] iArr, byte[] bArr, int i10, int i11, Paint paint, Canvas canvas) {
        boolean z;
        int bits;
        boolean z5 = false;
        while (true) {
            int bits2 = parsableBitArray.readBits(8);
            if (bits2 != 0) {
                z = z5;
                bits = 1;
            } else if (parsableBitArray.readBit()) {
                z = z5;
                bits = parsableBitArray.readBits(7);
                bits2 = parsableBitArray.readBits(8);
            } else {
                int bits3 = parsableBitArray.readBits(7);
                if (bits3 != 0) {
                    z = z5;
                    bits = bits3;
                    bits2 = 0;
                } else {
                    z = true;
                    bits2 = 0;
                    bits = 0;
                }
            }
            if (bits != 0 && paint != null) {
                if (bArr != null) {
                    bits2 = bArr[bits2];
                }
                paint.setColor(iArr[bits2]);
                canvas.drawRect(i10, i11, i10 + bits, 1 + i11, paint);
            }
            i10 += bits;
            if (z) {
                return i10;
            }
            z5 = z;
        }
    }

    private static void paintPixelDataSubBlock(byte[] bArr, int[] iArr, int i10, int i11, int i12, Paint paint, Canvas canvas) {
        int[] iArr2;
        Paint paint2;
        Canvas canvas2;
        byte[] bArr2;
        byte[] bArr3;
        ParsableBitArray parsableBitArray = new ParsableBitArray(bArr);
        byte[] bArrBuildClutMapTable = null;
        byte[] bArrBuildClutMapTable2 = null;
        int iPaint2BitPixelCodeString = i11;
        int i13 = i12;
        byte[] bArrBuildClutMapTable3 = null;
        while (parsableBitArray.bitsLeft() != 0) {
            int bits = parsableBitArray.readBits(8);
            if (bits != 240) {
                switch (bits) {
                    case 16:
                        iArr2 = iArr;
                        Paint paint3 = paint;
                        canvas2 = canvas;
                        if (i10 == 3) {
                            bArr3 = bArrBuildClutMapTable3 == null ? defaultMap2To8 : bArrBuildClutMapTable3;
                        } else if (i10 != 2) {
                            bArr2 = null;
                            paint2 = paint3;
                            iPaint2BitPixelCodeString = paint2BitPixelCodeString(parsableBitArray, iArr2, bArr2, iPaint2BitPixelCodeString, i13, paint2, canvas2);
                            parsableBitArray.byteAlign();
                        } else {
                            bArr3 = bArrBuildClutMapTable2 == null ? defaultMap2To4 : bArrBuildClutMapTable2;
                        }
                        paint2 = paint3;
                        bArr2 = bArr3;
                        iPaint2BitPixelCodeString = paint2BitPixelCodeString(parsableBitArray, iArr2, bArr2, iPaint2BitPixelCodeString, i13, paint2, canvas2);
                        parsableBitArray.byteAlign();
                        break;
                    case 17:
                        iArr2 = iArr;
                        Paint paint4 = paint;
                        canvas2 = canvas;
                        paint2 = paint4;
                        iPaint2BitPixelCodeString = paint4BitPixelCodeString(parsableBitArray, iArr2, i10 == 3 ? bArrBuildClutMapTable == null ? defaultMap4To8 : bArrBuildClutMapTable : null, iPaint2BitPixelCodeString, i13, paint2, canvas2);
                        parsableBitArray.byteAlign();
                        break;
                    case 18:
                        iArr2 = iArr;
                        paint2 = paint;
                        canvas2 = canvas;
                        iPaint2BitPixelCodeString = paint8BitPixelCodeString(parsableBitArray, iArr2, null, iPaint2BitPixelCodeString, i13, paint2, canvas2);
                        break;
                    default:
                        switch (bits) {
                            case 32:
                                bArrBuildClutMapTable2 = buildClutMapTable(4, 4, parsableBitArray);
                                break;
                            case 33:
                                bArrBuildClutMapTable3 = buildClutMapTable(4, 8, parsableBitArray);
                                break;
                            case 34:
                                bArrBuildClutMapTable = buildClutMapTable(16, 8, parsableBitArray);
                                break;
                        }
                        iArr2 = iArr;
                        paint2 = paint;
                        canvas2 = canvas;
                        break;
                }
            } else {
                iArr2 = iArr;
                paint2 = paint;
                canvas2 = canvas;
                i13 += 2;
                iPaint2BitPixelCodeString = i11;
            }
            iArr = iArr2;
            paint = paint2;
            canvas = canvas2;
        }
    }

    private static void paintPixelDataSubBlocks(ObjectData objectData, ClutDefinition clutDefinition, int i10, int i11, int i12, Paint paint, Canvas canvas) {
        int[] iArr = i10 == 3 ? clutDefinition.clutEntries8Bit : i10 == 2 ? clutDefinition.clutEntries4Bit : clutDefinition.clutEntries2Bit;
        paintPixelDataSubBlock(objectData.topFieldData, iArr, i10, i11, i12, paint, canvas);
        paintPixelDataSubBlock(objectData.bottomFieldData, iArr, i10, i11, i12 + 1, paint, canvas);
    }

    private static ClutDefinition parseClutDefinition(ParsableBitArray parsableBitArray, int i10) {
        int bits;
        int i11;
        int bits2;
        int bits3;
        int bits4;
        int i12 = 8;
        int bits5 = parsableBitArray.readBits(8);
        parsableBitArray.skipBits(8);
        int i13 = 2;
        int i14 = i10 - 2;
        int[] iArrGenerateDefault2BitClutEntries = generateDefault2BitClutEntries();
        int[] iArrGenerateDefault4BitClutEntries = generateDefault4BitClutEntries();
        int[] iArrGenerateDefault8BitClutEntries = generateDefault8BitClutEntries();
        while (i14 > 0) {
            int bits6 = parsableBitArray.readBits(i12);
            int bits7 = parsableBitArray.readBits(i12);
            int[] iArr = (bits7 & 128) != 0 ? iArrGenerateDefault2BitClutEntries : (bits7 & 64) != 0 ? iArrGenerateDefault4BitClutEntries : iArrGenerateDefault8BitClutEntries;
            if ((bits7 & 1) != 0) {
                bits3 = parsableBitArray.readBits(i12);
                bits4 = parsableBitArray.readBits(i12);
                bits = parsableBitArray.readBits(i12);
                bits2 = parsableBitArray.readBits(i12);
                i11 = i14 - 6;
            } else {
                int bits8 = parsableBitArray.readBits(6) << i13;
                int bits9 = parsableBitArray.readBits(4) << 4;
                bits = parsableBitArray.readBits(4) << 4;
                i11 = i14 - 4;
                bits2 = parsableBitArray.readBits(i13) << 6;
                bits3 = bits8;
                bits4 = bits9;
            }
            if (bits3 == 0) {
                bits2 = 255;
                bits4 = 0;
                bits = 0;
            }
            double d4 = bits3;
            double d10 = bits4 - 128;
            double d11 = bits - 128;
            iArr[bits6] = getColor((byte) (255 - (bits2 & 255)), Util.constrainValue((int) ((1.402d * d10) + d4), 0, 255), Util.constrainValue((int) ((d4 - (0.34414d * d11)) - (d10 * 0.71414d)), 0, 255), Util.constrainValue((int) ((d11 * 1.772d) + d4), 0, 255));
            i14 = i11;
            bits5 = bits5;
            i12 = 8;
            i13 = 2;
        }
        return new ClutDefinition(bits5, iArrGenerateDefault2BitClutEntries, iArrGenerateDefault4BitClutEntries, iArrGenerateDefault8BitClutEntries);
    }

    private static DisplayDefinition parseDisplayDefinition(ParsableBitArray parsableBitArray) {
        int i10;
        int i11;
        int i12;
        int bits;
        parsableBitArray.skipBits(4);
        boolean bit = parsableBitArray.readBit();
        parsableBitArray.skipBits(3);
        int bits2 = parsableBitArray.readBits(16);
        int bits3 = parsableBitArray.readBits(16);
        if (bit) {
            int bits4 = parsableBitArray.readBits(16);
            int bits5 = parsableBitArray.readBits(16);
            int bits6 = parsableBitArray.readBits(16);
            bits = parsableBitArray.readBits(16);
            i12 = bits5;
            i11 = bits6;
            i10 = bits4;
        } else {
            i10 = 0;
            i11 = 0;
            i12 = bits2;
            bits = bits3;
        }
        return new DisplayDefinition(bits2, bits3, i10, i12, i11, bits);
    }

    private static ObjectData parseObjectData(ParsableBitArray parsableBitArray) {
        byte[] bArr;
        int bits = parsableBitArray.readBits(16);
        parsableBitArray.skipBits(4);
        int bits2 = parsableBitArray.readBits(2);
        boolean bit = parsableBitArray.readBit();
        parsableBitArray.skipBits(1);
        byte[] bArr2 = Util.EMPTY_BYTE_ARRAY;
        if (bits2 != 1) {
            if (bits2 == 0) {
                int bits3 = parsableBitArray.readBits(16);
                int bits4 = parsableBitArray.readBits(16);
                if (bits3 > 0) {
                    bArr2 = new byte[bits3];
                    parsableBitArray.readBytes(bArr2, 0, bits3);
                }
                if (bits4 > 0) {
                    bArr = new byte[bits4];
                    parsableBitArray.readBytes(bArr, 0, bits4);
                }
            }
            return new ObjectData(bits, bit, bArr2, bArr);
        }
        parsableBitArray.skipBits(parsableBitArray.readBits(8) * 16);
        bArr = bArr2;
        return new ObjectData(bits, bit, bArr2, bArr);
    }

    private static PageComposition parsePageComposition(ParsableBitArray parsableBitArray, int i10) {
        int bits = parsableBitArray.readBits(8);
        int bits2 = parsableBitArray.readBits(4);
        int bits3 = parsableBitArray.readBits(2);
        parsableBitArray.skipBits(2);
        int i11 = i10 - 2;
        SparseArray sparseArray = new SparseArray();
        while (i11 > 0) {
            int bits4 = parsableBitArray.readBits(8);
            parsableBitArray.skipBits(8);
            i11 -= 6;
            sparseArray.put(bits4, new PageRegion(parsableBitArray.readBits(16), parsableBitArray.readBits(16)));
        }
        return new PageComposition(bits, bits2, bits3, sparseArray);
    }

    private static RegionComposition parseRegionComposition(ParsableBitArray parsableBitArray, int i10) {
        int i11;
        int bits;
        int bits2;
        char c10;
        int bits3 = parsableBitArray.readBits(8);
        int i12 = 4;
        parsableBitArray.skipBits(4);
        boolean bit = parsableBitArray.readBit();
        parsableBitArray.skipBits(3);
        int i13 = 16;
        int bits4 = parsableBitArray.readBits(16);
        int bits5 = parsableBitArray.readBits(16);
        int bits6 = parsableBitArray.readBits(3);
        int bits7 = parsableBitArray.readBits(3);
        int i14 = 2;
        parsableBitArray.skipBits(2);
        int bits8 = parsableBitArray.readBits(8);
        int bits9 = parsableBitArray.readBits(8);
        int bits10 = parsableBitArray.readBits(4);
        int bits11 = parsableBitArray.readBits(2);
        parsableBitArray.skipBits(2);
        int i15 = i10 - 10;
        SparseArray sparseArray = new SparseArray();
        while (i15 > 0) {
            int bits12 = parsableBitArray.readBits(i13);
            int bits13 = parsableBitArray.readBits(i14);
            int bits14 = parsableBitArray.readBits(i14);
            int bits15 = parsableBitArray.readBits(12);
            parsableBitArray.skipBits(i12);
            int bits16 = parsableBitArray.readBits(12);
            int i16 = i15 - 6;
            if (bits13 != 1) {
                i11 = 2;
                if (bits13 != 2) {
                    bits2 = 0;
                    bits = 0;
                    i15 = i16;
                    c10 = '\b';
                }
                sparseArray.put(bits12, new RegionObject(bits13, bits14, bits15, bits16, bits2, bits));
                i13 = 16;
                i14 = i11;
                i12 = 4;
            } else {
                i11 = 2;
            }
            c10 = '\b';
            i15 -= 8;
            bits2 = parsableBitArray.readBits(8);
            bits = parsableBitArray.readBits(8);
            sparseArray.put(bits12, new RegionObject(bits13, bits14, bits15, bits16, bits2, bits));
            i13 = 16;
            i14 = i11;
            i12 = 4;
        }
        return new RegionComposition(bits3, bit, bits4, bits5, bits6, bits7, bits8, bits9, bits10, bits11, sparseArray);
    }

    private static void parseSubtitlingSegment(ParsableBitArray parsableBitArray, SubtitleService subtitleService) {
        RegionComposition regionComposition;
        int bits = parsableBitArray.readBits(8);
        int bits2 = parsableBitArray.readBits(16);
        int bits3 = parsableBitArray.readBits(16);
        int bytePosition = parsableBitArray.getBytePosition() + bits3;
        if (bits3 * 8 > parsableBitArray.bitsLeft()) {
            Log.w(TAG, "Data field length exceeds limit");
            parsableBitArray.skipBits(parsableBitArray.bitsLeft());
            return;
        }
        switch (bits) {
            case 16:
                if (bits2 == subtitleService.subtitlePageId) {
                    PageComposition pageComposition = subtitleService.pageComposition;
                    PageComposition pageComposition2 = parsePageComposition(parsableBitArray, bits3);
                    if (pageComposition2.state != 0) {
                        subtitleService.pageComposition = pageComposition2;
                        subtitleService.regions.clear();
                        subtitleService.cluts.clear();
                        subtitleService.objects.clear();
                    } else if (pageComposition != null && pageComposition.version != pageComposition2.version) {
                        subtitleService.pageComposition = pageComposition2;
                    }
                }
                break;
            case 17:
                PageComposition pageComposition3 = subtitleService.pageComposition;
                if (bits2 == subtitleService.subtitlePageId && pageComposition3 != null) {
                    RegionComposition regionComposition2 = parseRegionComposition(parsableBitArray, bits3);
                    if (pageComposition3.state == 0 && (regionComposition = subtitleService.regions.get(regionComposition2.id)) != null) {
                        regionComposition2.mergeFrom(regionComposition);
                    }
                    subtitleService.regions.put(regionComposition2.id, regionComposition2);
                }
                break;
            case 18:
                if (bits2 == subtitleService.subtitlePageId) {
                    ClutDefinition clutDefinition = parseClutDefinition(parsableBitArray, bits3);
                    subtitleService.cluts.put(clutDefinition.id, clutDefinition);
                } else if (bits2 == subtitleService.ancillaryPageId) {
                    ClutDefinition clutDefinition2 = parseClutDefinition(parsableBitArray, bits3);
                    subtitleService.ancillaryCluts.put(clutDefinition2.id, clutDefinition2);
                }
                break;
            case 19:
                if (bits2 == subtitleService.subtitlePageId) {
                    ObjectData objectData = parseObjectData(parsableBitArray);
                    subtitleService.objects.put(objectData.id, objectData);
                } else if (bits2 == subtitleService.ancillaryPageId) {
                    ObjectData objectData2 = parseObjectData(parsableBitArray);
                    subtitleService.ancillaryObjects.put(objectData2.id, objectData2);
                }
                break;
            case 20:
                if (bits2 == subtitleService.subtitlePageId) {
                    subtitleService.displayDefinition = parseDisplayDefinition(parsableBitArray);
                }
                break;
        }
        parsableBitArray.skipBytes(bytePosition - parsableBitArray.getBytePosition());
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
    public void reset() {
        this.subtitleService.reset();
    }

    @Override // androidx.media3.extractor.text.SubtitleParser
    public void parse(byte[] bArr, int i10, int i11, SubtitleParser.OutputOptions outputOptions, Consumer<CuesWithTiming> consumer) {
        ParsableBitArray parsableBitArray = new ParsableBitArray(bArr, i11 + i10);
        parsableBitArray.setPosition(i10);
        consumer.accept(parse(parsableBitArray));
    }

    private CuesWithTiming parse(ParsableBitArray parsableBitArray) {
        int i10;
        SparseArray<PageRegion> sparseArray;
        int i11;
        while (parsableBitArray.bitsLeft() >= 48 && parsableBitArray.readBits(8) == 15) {
            parseSubtitlingSegment(parsableBitArray, this.subtitleService);
        }
        SubtitleService subtitleService = this.subtitleService;
        PageComposition pageComposition = subtitleService.pageComposition;
        if (pageComposition == null) {
            f1 f1Var = h1.f14020l;
            return new CuesWithTiming(o3.f14078o, C.TIME_UNSET, C.TIME_UNSET);
        }
        DisplayDefinition displayDefinition = subtitleService.displayDefinition;
        if (displayDefinition == null) {
            displayDefinition = this.defaultDisplayDefinition;
        }
        Bitmap bitmap = this.bitmap;
        if (bitmap == null || displayDefinition.width + 1 != bitmap.getWidth() || displayDefinition.height + 1 != this.bitmap.getHeight()) {
            Bitmap bitmapCreateBitmap = Bitmap.createBitmap(displayDefinition.width + 1, displayDefinition.height + 1, Bitmap.Config.ARGB_8888);
            this.bitmap = bitmapCreateBitmap;
            this.canvas.setBitmap(bitmapCreateBitmap);
        }
        ArrayList arrayList = new ArrayList();
        SparseArray<PageRegion> sparseArray2 = pageComposition.regions;
        int i12 = 0;
        while (i12 < sparseArray2.size()) {
            this.canvas.save();
            PageRegion pageRegionValueAt = sparseArray2.valueAt(i12);
            RegionComposition regionComposition = this.subtitleService.regions.get(sparseArray2.keyAt(i12));
            int i13 = pageRegionValueAt.horizontalAddress + displayDefinition.horizontalPositionMinimum;
            int i14 = pageRegionValueAt.verticalAddress + displayDefinition.verticalPositionMinimum;
            this.canvas.clipRect(i13, i14, Math.min(regionComposition.width + i13, displayDefinition.horizontalPositionMaximum), Math.min(regionComposition.height + i14, displayDefinition.verticalPositionMaximum));
            ClutDefinition clutDefinition = this.subtitleService.cluts.get(regionComposition.clutId);
            if (clutDefinition == null && (clutDefinition = this.subtitleService.ancillaryCluts.get(regionComposition.clutId)) == null) {
                clutDefinition = this.defaultClutDefinition;
            }
            ClutDefinition clutDefinition2 = clutDefinition;
            SparseArray<RegionObject> sparseArray3 = regionComposition.regionObjects;
            int i15 = 0;
            while (i15 < sparseArray3.size()) {
                int iKeyAt = sparseArray3.keyAt(i15);
                RegionObject regionObjectValueAt = sparseArray3.valueAt(i15);
                ObjectData objectData = this.subtitleService.objects.get(iKeyAt);
                if (objectData == null) {
                    objectData = this.subtitleService.ancillaryObjects.get(iKeyAt);
                }
                if (objectData != null) {
                    sparseArray = sparseArray2;
                    i11 = i15;
                    paintPixelDataSubBlocks(objectData, clutDefinition2, regionComposition.depth, regionObjectValueAt.horizontalPosition + i13, regionObjectValueAt.verticalPosition + i14, objectData.nonModifyingColorFlag ? null : this.defaultPaint, this.canvas);
                } else {
                    sparseArray = sparseArray2;
                    i11 = i15;
                }
                i15 = i11 + 1;
                sparseArray2 = sparseArray;
            }
            SparseArray<PageRegion> sparseArray4 = sparseArray2;
            if (regionComposition.fillFlag) {
                int i16 = regionComposition.depth;
                if (i16 == 3) {
                    i10 = clutDefinition2.clutEntries8Bit[regionComposition.pixelCode8Bit];
                } else if (i16 == 2) {
                    i10 = clutDefinition2.clutEntries4Bit[regionComposition.pixelCode4Bit];
                } else {
                    i10 = clutDefinition2.clutEntries2Bit[regionComposition.pixelCode2Bit];
                }
                this.fillRegionPaint.setColor(i10);
                this.canvas.drawRect(i13, i14, regionComposition.width + i13, regionComposition.height + i14, this.fillRegionPaint);
            }
            arrayList.add(new Cue.Builder().setBitmap(Bitmap.createBitmap(this.bitmap, i13, i14, regionComposition.width, regionComposition.height)).setPosition(i13 / displayDefinition.width).setPositionAnchor(0).setLine(i14 / displayDefinition.height, 0).setLineAnchor(0).setSize(regionComposition.width / displayDefinition.width).setBitmapHeight(regionComposition.height / displayDefinition.height).build());
            this.canvas.drawColor(0, PorterDuff.Mode.CLEAR);
            this.canvas.restore();
            i12++;
            sparseArray2 = sparseArray4;
        }
        return new CuesWithTiming(arrayList, C.TIME_UNSET, C.TIME_UNSET);
    }
}
