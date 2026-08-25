package androidx.media3.extractor.text.cea;

import android.text.Layout;
import android.text.SpannableString;
import android.text.SpannableStringBuilder;
import android.text.style.BackgroundColorSpan;
import android.text.style.ForegroundColorSpan;
import android.text.style.StyleSpan;
import android.text.style.UnderlineSpan;
import androidx.fragment.app.a2;
import androidx.media3.common.text.Cue;
import androidx.media3.common.util.CodecSpecificDataUtil;
import androidx.media3.common.util.Log;
import androidx.media3.common.util.ParsableBitArray;
import androidx.media3.common.util.ParsableByteArray;
import androidx.media3.extractor.text.Subtitle;
import androidx.media3.extractor.text.SubtitleDecoderException;
import androidx.media3.extractor.text.SubtitleInputBuffer;
import androidx.media3.extractor.text.SubtitleOutputBuffer;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import org.checkerframework.checker.nullness.qual.RequiresNonNull;

/* JADX INFO: loaded from: classes3.dex */
public final class Cea708Decoder extends CeaDecoder {
    private static final int CC_VALID_FLAG = 4;
    private static final int CHARACTER_BIG_CARONS = 42;
    private static final int CHARACTER_BIG_OE = 44;
    private static final int CHARACTER_BOLD_BULLET = 53;
    private static final int CHARACTER_CLOSE_DOUBLE_QUOTE = 52;
    private static final int CHARACTER_CLOSE_SINGLE_QUOTE = 50;
    private static final int CHARACTER_DIAERESIS_Y = 63;
    private static final int CHARACTER_ELLIPSIS = 37;
    private static final int CHARACTER_FIVE_EIGHTHS = 120;
    private static final int CHARACTER_HORIZONTAL_BORDER = 125;
    private static final int CHARACTER_LOWER_LEFT_BORDER = 124;
    private static final int CHARACTER_LOWER_RIGHT_BORDER = 126;
    private static final int CHARACTER_MN = 127;
    private static final int CHARACTER_NBTSP = 33;
    private static final int CHARACTER_ONE_EIGHTH = 118;
    private static final int CHARACTER_OPEN_DOUBLE_QUOTE = 51;
    private static final int CHARACTER_OPEN_SINGLE_QUOTE = 49;
    private static final int CHARACTER_SEVEN_EIGHTHS = 121;
    private static final int CHARACTER_SM = 61;
    private static final int CHARACTER_SMALL_CARONS = 58;
    private static final int CHARACTER_SMALL_OE = 60;
    private static final int CHARACTER_SOLID_BLOCK = 48;
    private static final int CHARACTER_THREE_EIGHTHS = 119;
    private static final int CHARACTER_TM = 57;
    private static final int CHARACTER_TSP = 32;
    private static final int CHARACTER_UPPER_LEFT_BORDER = 127;
    private static final int CHARACTER_UPPER_RIGHT_BORDER = 123;
    private static final int CHARACTER_VERTICAL_BORDER = 122;
    private static final int COMMAND_BS = 8;
    private static final int COMMAND_CLW = 136;
    private static final int COMMAND_CR = 13;
    private static final int COMMAND_CW0 = 128;
    private static final int COMMAND_CW1 = 129;
    private static final int COMMAND_CW2 = 130;
    private static final int COMMAND_CW3 = 131;
    private static final int COMMAND_CW4 = 132;
    private static final int COMMAND_CW5 = 133;
    private static final int COMMAND_CW6 = 134;
    private static final int COMMAND_CW7 = 135;
    private static final int COMMAND_DF0 = 152;
    private static final int COMMAND_DF1 = 153;
    private static final int COMMAND_DF2 = 154;
    private static final int COMMAND_DF3 = 155;
    private static final int COMMAND_DF4 = 156;
    private static final int COMMAND_DF5 = 157;
    private static final int COMMAND_DF6 = 158;
    private static final int COMMAND_DF7 = 159;
    private static final int COMMAND_DLC = 142;
    private static final int COMMAND_DLW = 140;
    private static final int COMMAND_DLY = 141;
    private static final int COMMAND_DSW = 137;
    private static final int COMMAND_ETX = 3;
    private static final int COMMAND_EXT1 = 16;
    private static final int COMMAND_EXT1_END = 23;
    private static final int COMMAND_EXT1_START = 17;
    private static final int COMMAND_FF = 12;
    private static final int COMMAND_HCR = 14;
    private static final int COMMAND_HDW = 138;
    private static final int COMMAND_NUL = 0;
    private static final int COMMAND_P16_END = 31;
    private static final int COMMAND_P16_START = 24;
    private static final int COMMAND_RST = 143;
    private static final int COMMAND_SPA = 144;
    private static final int COMMAND_SPC = 145;
    private static final int COMMAND_SPL = 146;
    private static final int COMMAND_SWA = 151;
    private static final int COMMAND_TGW = 139;
    private static final int DTVCC_PACKET_DATA = 2;
    private static final int DTVCC_PACKET_START = 3;
    private static final int GROUP_C0_END = 31;
    private static final int GROUP_C1_END = 159;
    private static final int GROUP_C2_END = 31;
    private static final int GROUP_C3_END = 159;
    private static final int GROUP_G0_END = 127;
    private static final int GROUP_G1_END = 255;
    private static final int GROUP_G2_END = 127;
    private static final int GROUP_G3_END = 255;
    private static final int NUM_WINDOWS = 8;
    private static final String TAG = "Cea708Decoder";
    private final CueInfoBuilder[] cueInfoBuilders;
    private List<Cue> cues;
    private CueInfoBuilder currentCueInfoBuilder;
    private DtvCcPacket currentDtvCcPacket;
    private int currentWindow;
    private final boolean isWideAspectRatio;
    private List<Cue> lastCues;
    private final int selectedServiceNumber;
    private final ParsableByteArray ccData = new ParsableByteArray();
    private final ParsableBitArray captionChannelPacketData = new ParsableBitArray();
    private int previousSequenceNumber = -1;

    public static final class Cea708CueInfo {
        private static final Comparator<Cea708CueInfo> LEAST_IMPORTANT_FIRST = new a();
        public final Cue cue;
        public final int priority;

        public Cea708CueInfo(CharSequence charSequence, Layout.Alignment alignment, float f10, int i10, int i11, float f11, int i12, float f12, boolean z, int i13, int i14) {
            Cue.Builder size = new Cue.Builder().setText(charSequence).setTextAlignment(alignment).setLine(f10, i10).setLineAnchor(i11).setPosition(f11).setPositionAnchor(i12).setSize(f12);
            if (z) {
                size.setWindowColor(i13);
            }
            this.cue = size.build();
            this.priority = i14;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static /* synthetic */ int lambda$static$0(Cea708CueInfo cea708CueInfo, Cea708CueInfo cea708CueInfo2) {
            return Integer.compare(cea708CueInfo2.priority, cea708CueInfo.priority);
        }
    }

    public static final class CueInfoBuilder {
        private static final int BORDER_AND_EDGE_TYPE_NONE = 0;
        private static final int BORDER_AND_EDGE_TYPE_UNIFORM = 3;
        public static final int COLOR_SOLID_BLACK;
        public static final int COLOR_SOLID_WHITE = getArgbColorFromCeaColor(2, 2, 2, 0);
        public static final int COLOR_TRANSPARENT;
        private static final int DEFAULT_PRIORITY = 4;
        private static final int DIRECTION_BOTTOM_TO_TOP = 3;
        private static final int DIRECTION_LEFT_TO_RIGHT = 0;
        private static final int DIRECTION_RIGHT_TO_LEFT = 1;
        private static final int DIRECTION_TOP_TO_BOTTOM = 2;
        private static final int HORIZONTAL_SIZE = 209;
        private static final int JUSTIFICATION_CENTER = 2;
        private static final int JUSTIFICATION_FULL = 3;
        private static final int JUSTIFICATION_LEFT = 0;
        private static final int JUSTIFICATION_RIGHT = 1;
        private static final int MAXIMUM_ROW_COUNT = 15;
        private static final int PEN_FONT_STYLE_DEFAULT = 0;
        private static final int PEN_FONT_STYLE_MONOSPACED_WITHOUT_SERIFS = 3;
        private static final int PEN_FONT_STYLE_MONOSPACED_WITH_SERIFS = 1;
        private static final int PEN_FONT_STYLE_PROPORTIONALLY_SPACED_WITHOUT_SERIFS = 4;
        private static final int PEN_FONT_STYLE_PROPORTIONALLY_SPACED_WITH_SERIFS = 2;
        private static final int PEN_OFFSET_NORMAL = 1;
        private static final int PEN_SIZE_STANDARD = 1;
        private static final int[] PEN_STYLE_BACKGROUND;
        private static final int[] PEN_STYLE_EDGE_TYPE;
        private static final int[] PEN_STYLE_FONT_STYLE;
        private static final int RELATIVE_CUE_SIZE = 99;
        private static final int VERTICAL_SIZE = 74;
        private static final int[] WINDOW_STYLE_FILL;
        private static final int[] WINDOW_STYLE_JUSTIFICATION;
        private static final int[] WINDOW_STYLE_PRINT_DIRECTION;
        private static final int[] WINDOW_STYLE_SCROLL_DIRECTION;
        private static final boolean[] WINDOW_STYLE_WORD_WRAP;
        private int anchorId;
        private int backgroundColor;
        private int backgroundColorStartPosition;
        private boolean defined;
        private int foregroundColor;
        private int foregroundColorStartPosition;
        private int horizontalAnchor;
        private int italicsStartPosition;
        private int justification;
        private int penStyleId;
        private int priority;
        private boolean relativePositioning;
        private int row;
        private int rowCount;
        private int underlineStartPosition;
        private int verticalAnchor;
        private boolean visible;
        private int windowFillColor;
        private int windowStyleId;
        private final List<SpannableString> rolledUpCaptions = new ArrayList();
        private final SpannableStringBuilder captionStringBuilder = new SpannableStringBuilder();

        static {
            int argbColorFromCeaColor = getArgbColorFromCeaColor(0, 0, 0, 0);
            COLOR_SOLID_BLACK = argbColorFromCeaColor;
            int argbColorFromCeaColor2 = getArgbColorFromCeaColor(0, 0, 0, 3);
            COLOR_TRANSPARENT = argbColorFromCeaColor2;
            WINDOW_STYLE_JUSTIFICATION = new int[]{0, 0, 0, 0, 0, 2, 0};
            WINDOW_STYLE_PRINT_DIRECTION = new int[]{0, 0, 0, 0, 0, 0, 2};
            WINDOW_STYLE_SCROLL_DIRECTION = new int[]{3, 3, 3, 3, 3, 3, 1};
            WINDOW_STYLE_WORD_WRAP = new boolean[]{false, false, false, true, true, true, false};
            WINDOW_STYLE_FILL = new int[]{argbColorFromCeaColor, argbColorFromCeaColor2, argbColorFromCeaColor, argbColorFromCeaColor, argbColorFromCeaColor2, argbColorFromCeaColor, argbColorFromCeaColor};
            PEN_STYLE_FONT_STYLE = new int[]{0, 1, 2, 3, 4, 3, 4};
            PEN_STYLE_EDGE_TYPE = new int[]{0, 0, 0, 0, 0, 3, 3};
            PEN_STYLE_BACKGROUND = new int[]{argbColorFromCeaColor, argbColorFromCeaColor, argbColorFromCeaColor, argbColorFromCeaColor, argbColorFromCeaColor, argbColorFromCeaColor2, argbColorFromCeaColor2};
        }

        public CueInfoBuilder() {
            reset();
        }

        public static int getArgbColorFromCeaColor(int i10, int i11, int i12) {
            return getArgbColorFromCeaColor(i10, i11, i12, 0);
        }

        public void append(char c10) {
            if (c10 != '\n') {
                this.captionStringBuilder.append(c10);
                return;
            }
            this.rolledUpCaptions.add(buildSpannableString());
            this.captionStringBuilder.clear();
            if (this.italicsStartPosition != -1) {
                this.italicsStartPosition = 0;
            }
            if (this.underlineStartPosition != -1) {
                this.underlineStartPosition = 0;
            }
            if (this.foregroundColorStartPosition != -1) {
                this.foregroundColorStartPosition = 0;
            }
            if (this.backgroundColorStartPosition != -1) {
                this.backgroundColorStartPosition = 0;
            }
            while (true) {
                if (this.rolledUpCaptions.size() < this.rowCount && this.rolledUpCaptions.size() < 15) {
                    this.row = this.rolledUpCaptions.size();
                    return;
                }
                this.rolledUpCaptions.remove(0);
            }
        }

        public void backspace() {
            int length = this.captionStringBuilder.length();
            if (length > 0) {
                this.captionStringBuilder.delete(length - 1, length);
            }
        }

        public Cea708CueInfo build() {
            Layout.Alignment alignment;
            float f10;
            float f11;
            int i10;
            float f12;
            int i11;
            if (isEmpty()) {
                return null;
            }
            SpannableStringBuilder spannableStringBuilder = new SpannableStringBuilder();
            for (int i12 = 0; i12 < this.rolledUpCaptions.size(); i12++) {
                spannableStringBuilder.append((CharSequence) this.rolledUpCaptions.get(i12));
                spannableStringBuilder.append('\n');
            }
            spannableStringBuilder.append((CharSequence) buildSpannableString());
            int i13 = this.justification;
            int i14 = 2;
            if (i13 == 0) {
                alignment = Layout.Alignment.ALIGN_NORMAL;
            } else if (i13 == 1) {
                alignment = Layout.Alignment.ALIGN_OPPOSITE;
            } else if (i13 != 2) {
                if (i13 != 3) {
                    throw new IllegalArgumentException("Unexpected justification value: " + this.justification);
                }
                alignment = Layout.Alignment.ALIGN_NORMAL;
            } else {
                alignment = Layout.Alignment.ALIGN_CENTER;
            }
            if (this.relativePositioning) {
                f10 = this.horizontalAnchor / 99.0f;
                f11 = this.verticalAnchor / 99.0f;
            } else {
                f10 = this.horizontalAnchor / 209.0f;
                f11 = this.verticalAnchor / 74.0f;
            }
            float f13 = (f10 * 0.9f) + 0.05f;
            float f14 = (f11 * 0.9f) + 0.05f;
            int i15 = this.anchorId;
            if (i15 / 3 == 0) {
                i10 = i15;
                f12 = f13;
                i11 = 0;
            } else if (i15 / 3 == 1) {
                i10 = i15;
                f12 = f13;
                i11 = 1;
            } else {
                i10 = i15;
                f12 = f13;
                i11 = 2;
            }
            if (i10 % 3 == 0) {
                i14 = 0;
            } else if (i10 % 3 == 1) {
                i14 = 1;
            }
            int i16 = this.windowFillColor;
            return new Cea708CueInfo(spannableStringBuilder, alignment, f14, 0, i11, f12, i14, -3.4028235E38f, i16 != COLOR_SOLID_BLACK, i16, this.priority);
        }

        public SpannableString buildSpannableString() {
            SpannableStringBuilder spannableStringBuilder = new SpannableStringBuilder(this.captionStringBuilder);
            int length = spannableStringBuilder.length();
            if (length > 0) {
                if (this.italicsStartPosition != -1) {
                    spannableStringBuilder.setSpan(new StyleSpan(2), this.italicsStartPosition, length, 33);
                }
                if (this.underlineStartPosition != -1) {
                    spannableStringBuilder.setSpan(new UnderlineSpan(), this.underlineStartPosition, length, 33);
                }
                if (this.foregroundColorStartPosition != -1) {
                    spannableStringBuilder.setSpan(new ForegroundColorSpan(this.foregroundColor), this.foregroundColorStartPosition, length, 33);
                }
                if (this.backgroundColorStartPosition != -1) {
                    spannableStringBuilder.setSpan(new BackgroundColorSpan(this.backgroundColor), this.backgroundColorStartPosition, length, 33);
                }
            }
            return new SpannableString(spannableStringBuilder);
        }

        public void clear() {
            this.rolledUpCaptions.clear();
            this.captionStringBuilder.clear();
            this.italicsStartPosition = -1;
            this.underlineStartPosition = -1;
            this.foregroundColorStartPosition = -1;
            this.backgroundColorStartPosition = -1;
            this.row = 0;
        }

        public void defineWindow(boolean z, int i10, boolean z5, int i11, int i12, int i13, int i14, int i15, int i16) {
            this.defined = true;
            this.visible = z;
            this.priority = i10;
            this.relativePositioning = z5;
            this.verticalAnchor = i11;
            this.horizontalAnchor = i12;
            this.anchorId = i14;
            int i17 = i13 + 1;
            if (this.rowCount != i17) {
                this.rowCount = i17;
                while (true) {
                    if (this.rolledUpCaptions.size() < this.rowCount && this.rolledUpCaptions.size() < 15) {
                        break;
                    } else {
                        this.rolledUpCaptions.remove(0);
                    }
                }
            }
            if (i15 != 0 && this.windowStyleId != i15) {
                this.windowStyleId = i15;
                int i18 = i15 - 1;
                setWindowAttributes(WINDOW_STYLE_FILL[i18], COLOR_TRANSPARENT, WINDOW_STYLE_WORD_WRAP[i18], 0, WINDOW_STYLE_PRINT_DIRECTION[i18], WINDOW_STYLE_SCROLL_DIRECTION[i18], WINDOW_STYLE_JUSTIFICATION[i18]);
            }
            if (i16 == 0 || this.penStyleId == i16) {
                return;
            }
            this.penStyleId = i16;
            int i19 = i16 - 1;
            setPenAttributes(0, 1, 1, false, false, PEN_STYLE_EDGE_TYPE[i19], PEN_STYLE_FONT_STYLE[i19]);
            setPenColor(COLOR_SOLID_WHITE, PEN_STYLE_BACKGROUND[i19], COLOR_SOLID_BLACK);
        }

        public boolean isDefined() {
            return this.defined;
        }

        public boolean isEmpty() {
            if (isDefined()) {
                return this.rolledUpCaptions.isEmpty() && this.captionStringBuilder.length() == 0;
            }
            return true;
        }

        public boolean isVisible() {
            return this.visible;
        }

        public void reset() {
            clear();
            this.defined = false;
            this.visible = false;
            this.priority = 4;
            this.relativePositioning = false;
            this.verticalAnchor = 0;
            this.horizontalAnchor = 0;
            this.anchorId = 0;
            this.rowCount = 15;
            this.justification = 0;
            this.windowStyleId = 0;
            this.penStyleId = 0;
            int i10 = COLOR_SOLID_BLACK;
            this.windowFillColor = i10;
            this.foregroundColor = COLOR_SOLID_WHITE;
            this.backgroundColor = i10;
        }

        public void setPenAttributes(int i10, int i11, int i12, boolean z, boolean z5, int i13, int i14) {
            if (this.italicsStartPosition != -1) {
                if (!z) {
                    this.captionStringBuilder.setSpan(new StyleSpan(2), this.italicsStartPosition, this.captionStringBuilder.length(), 33);
                    this.italicsStartPosition = -1;
                }
            } else if (z) {
                this.italicsStartPosition = this.captionStringBuilder.length();
            }
            if (this.underlineStartPosition == -1) {
                if (z5) {
                    this.underlineStartPosition = this.captionStringBuilder.length();
                }
            } else {
                if (z5) {
                    return;
                }
                this.captionStringBuilder.setSpan(new UnderlineSpan(), this.underlineStartPosition, this.captionStringBuilder.length(), 33);
                this.underlineStartPosition = -1;
            }
        }

        public void setPenColor(int i10, int i11, int i12) {
            if (this.foregroundColorStartPosition != -1 && this.foregroundColor != i10) {
                this.captionStringBuilder.setSpan(new ForegroundColorSpan(this.foregroundColor), this.foregroundColorStartPosition, this.captionStringBuilder.length(), 33);
            }
            if (i10 != COLOR_SOLID_WHITE) {
                this.foregroundColorStartPosition = this.captionStringBuilder.length();
                this.foregroundColor = i10;
            }
            if (this.backgroundColorStartPosition != -1 && this.backgroundColor != i11) {
                this.captionStringBuilder.setSpan(new BackgroundColorSpan(this.backgroundColor), this.backgroundColorStartPosition, this.captionStringBuilder.length(), 33);
            }
            if (i11 != COLOR_SOLID_BLACK) {
                this.backgroundColorStartPosition = this.captionStringBuilder.length();
                this.backgroundColor = i11;
            }
        }

        public void setPenLocation(int i10, int i11) {
            if (this.row != i10) {
                append('\n');
            }
            this.row = i10;
        }

        public void setVisibility(boolean z) {
            this.visible = z;
        }

        public void setWindowAttributes(int i10, int i11, boolean z, int i12, int i13, int i14, int i15) {
            this.windowFillColor = i10;
            this.justification = i15;
        }

        /* JADX WARN: Removed duplicated region for block: B:9:0x001b  */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        public static int getArgbColorFromCeaColor(int r4, int r5, int r6, int r7) {
            /*
                r0 = 4
                ac.b.n(r4, r0)
                ac.b.n(r5, r0)
                ac.b.n(r6, r0)
                ac.b.n(r7, r0)
                r0 = 0
                r1 = 1
                r2 = 255(0xff, float:3.57E-43)
                if (r7 == 0) goto L1b
                if (r7 == r1) goto L1b
                r3 = 2
                if (r7 == r3) goto L1f
                r3 = 3
                if (r7 == r3) goto L1d
            L1b:
                r7 = r2
                goto L21
            L1d:
                r7 = r0
                goto L21
            L1f:
                r7 = 127(0x7f, float:1.78E-43)
            L21:
                if (r4 <= r1) goto L25
                r4 = r2
                goto L26
            L25:
                r4 = r0
            L26:
                if (r5 <= r1) goto L2a
                r5 = r2
                goto L2b
            L2a:
                r5 = r0
            L2b:
                if (r6 <= r1) goto L2e
                r0 = r2
            L2e:
                int r4 = android.graphics.Color.argb(r7, r4, r5, r0)
                return r4
            */
            throw new UnsupportedOperationException("Method not decompiled: androidx.media3.extractor.text.cea.Cea708Decoder.CueInfoBuilder.getArgbColorFromCeaColor(int, int, int, int):int");
        }
    }

    public static final class DtvCcPacket {
        int currentIndex = 0;
        public final byte[] packetData;
        public final int packetSize;
        public final int sequenceNumber;

        public DtvCcPacket(int i10, int i11) {
            this.sequenceNumber = i10;
            this.packetSize = i11;
            this.packetData = new byte[(i11 * 2) - 1];
        }
    }

    public Cea708Decoder(int i10, List<byte[]> list) {
        this.selectedServiceNumber = i10 == -1 ? 1 : i10;
        this.isWideAspectRatio = list != null && CodecSpecificDataUtil.parseCea708InitializationData(list);
        this.cueInfoBuilders = new CueInfoBuilder[8];
        for (int i11 = 0; i11 < 8; i11++) {
            this.cueInfoBuilders[i11] = new CueInfoBuilder();
        }
        this.currentCueInfoBuilder = this.cueInfoBuilders[0];
    }

    private void finalizeCurrentPacket() {
        if (this.currentDtvCcPacket == null) {
            return;
        }
        processCurrentPacket();
        this.currentDtvCcPacket = null;
    }

    private List<Cue> getDisplayCues() {
        Cea708CueInfo cea708CueInfoBuild;
        ArrayList arrayList = new ArrayList();
        for (int i10 = 0; i10 < 8; i10++) {
            if (!this.cueInfoBuilders[i10].isEmpty() && this.cueInfoBuilders[i10].isVisible() && (cea708CueInfoBuild = this.cueInfoBuilders[i10].build()) != null) {
                arrayList.add(cea708CueInfoBuild);
            }
        }
        Collections.sort(arrayList, Cea708CueInfo.LEAST_IMPORTANT_FIRST);
        ArrayList arrayList2 = new ArrayList(arrayList.size());
        for (int i11 = 0; i11 < arrayList.size(); i11++) {
            arrayList2.add(((Cea708CueInfo) arrayList.get(i11)).cue);
        }
        return Collections.unmodifiableList(arrayList2);
    }

    private void handleC0Command(int i10) {
        if (i10 != 0) {
            if (i10 == 3) {
                this.cues = getDisplayCues();
                return;
            }
            if (i10 == 8) {
                this.currentCueInfoBuilder.backspace();
                return;
            }
            switch (i10) {
                case 12:
                    resetCueBuilders();
                    break;
                case 13:
                    this.currentCueInfoBuilder.append('\n');
                    break;
                case 14:
                    break;
                default:
                    if (i10 >= 17 && i10 <= 23) {
                        a2.s(i10, "Currently unsupported COMMAND_EXT1 Command: ", TAG);
                        this.captionChannelPacketData.skipBits(8);
                    } else if (i10 >= 24 && i10 <= 31) {
                        a2.s(i10, "Currently unsupported COMMAND_P16 Command: ", TAG);
                        this.captionChannelPacketData.skipBits(16);
                    } else {
                        a2.s(i10, "Invalid C0 command: ", TAG);
                    }
                    break;
            }
        }
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    private void handleC1Command(int i10) {
        int i11 = 1;
        switch (i10) {
            case 128:
            case 129:
            case 130:
            case COMMAND_CW3 /* 131 */:
            case COMMAND_CW4 /* 132 */:
            case COMMAND_CW5 /* 133 */:
            case 134:
            case 135:
                int i12 = i10 - 128;
                if (this.currentWindow != i12) {
                    this.currentWindow = i12;
                    this.currentCueInfoBuilder = this.cueInfoBuilders[i12];
                }
                break;
            case 136:
                while (i11 <= 8) {
                    if (this.captionChannelPacketData.readBit()) {
                        this.cueInfoBuilders[8 - i11].clear();
                    }
                    i11++;
                }
                break;
            case COMMAND_DSW /* 137 */:
                for (int i13 = 1; i13 <= 8; i13++) {
                    if (this.captionChannelPacketData.readBit()) {
                        this.cueInfoBuilders[8 - i13].setVisibility(true);
                    }
                }
                break;
            case 138:
                while (i11 <= 8) {
                    if (this.captionChannelPacketData.readBit()) {
                        this.cueInfoBuilders[8 - i11].setVisibility(false);
                    }
                    i11++;
                }
                break;
            case 139:
                for (int i14 = 1; i14 <= 8; i14++) {
                    if (this.captionChannelPacketData.readBit()) {
                        this.cueInfoBuilders[8 - i14].setVisibility(!r0.isVisible());
                    }
                }
                break;
            case COMMAND_DLW /* 140 */:
                while (i11 <= 8) {
                    if (this.captionChannelPacketData.readBit()) {
                        this.cueInfoBuilders[8 - i11].reset();
                    }
                    i11++;
                }
                break;
            case COMMAND_DLY /* 141 */:
                this.captionChannelPacketData.skipBits(8);
                break;
            case COMMAND_DLC /* 142 */:
                break;
            case COMMAND_RST /* 143 */:
                resetCueBuilders();
                break;
            case COMMAND_SPA /* 144 */:
                if (this.currentCueInfoBuilder.isDefined()) {
                    handleSetPenAttributes();
                } else {
                    this.captionChannelPacketData.skipBits(16);
                }
                break;
            case COMMAND_SPC /* 145 */:
                if (this.currentCueInfoBuilder.isDefined()) {
                    handleSetPenColor();
                } else {
                    this.captionChannelPacketData.skipBits(24);
                }
                break;
            case COMMAND_SPL /* 146 */:
                if (this.currentCueInfoBuilder.isDefined()) {
                    handleSetPenLocation();
                } else {
                    this.captionChannelPacketData.skipBits(16);
                }
                break;
            case 147:
            case 148:
            case 149:
            case 150:
            default:
                a2.s(i10, "Invalid C1 command: ", TAG);
                break;
            case COMMAND_SWA /* 151 */:
                if (this.currentCueInfoBuilder.isDefined()) {
                    handleSetWindowAttributes();
                } else {
                    this.captionChannelPacketData.skipBits(32);
                }
                break;
            case COMMAND_DF0 /* 152 */:
            case COMMAND_DF1 /* 153 */:
            case COMMAND_DF2 /* 154 */:
            case COMMAND_DF3 /* 155 */:
            case COMMAND_DF4 /* 156 */:
            case COMMAND_DF5 /* 157 */:
            case COMMAND_DF6 /* 158 */:
            case 159:
                int i15 = i10 - 152;
                handleDefineWindow(i15);
                if (this.currentWindow != i15) {
                    this.currentWindow = i15;
                    this.currentCueInfoBuilder = this.cueInfoBuilders[i15];
                }
                break;
        }
    }

    private void handleC2Command(int i10) {
        if (i10 <= 7) {
            return;
        }
        if (i10 <= 15) {
            this.captionChannelPacketData.skipBits(8);
        } else if (i10 <= 23) {
            this.captionChannelPacketData.skipBits(16);
        } else if (i10 <= 31) {
            this.captionChannelPacketData.skipBits(24);
        }
    }

    private void handleC3Command(int i10) {
        if (i10 <= 135) {
            this.captionChannelPacketData.skipBits(32);
            return;
        }
        if (i10 <= COMMAND_RST) {
            this.captionChannelPacketData.skipBits(40);
        } else if (i10 <= 159) {
            this.captionChannelPacketData.skipBits(2);
            this.captionChannelPacketData.skipBits(this.captionChannelPacketData.readBits(6) * 8);
        }
    }

    private void handleDefineWindow(int i10) {
        CueInfoBuilder cueInfoBuilder = this.cueInfoBuilders[i10];
        this.captionChannelPacketData.skipBits(2);
        boolean bit = this.captionChannelPacketData.readBit();
        this.captionChannelPacketData.skipBits(2);
        int bits = this.captionChannelPacketData.readBits(3);
        boolean bit2 = this.captionChannelPacketData.readBit();
        int bits2 = this.captionChannelPacketData.readBits(7);
        int bits3 = this.captionChannelPacketData.readBits(8);
        int bits4 = this.captionChannelPacketData.readBits(4);
        int bits5 = this.captionChannelPacketData.readBits(4);
        this.captionChannelPacketData.skipBits(2);
        this.captionChannelPacketData.skipBits(6);
        this.captionChannelPacketData.skipBits(2);
        cueInfoBuilder.defineWindow(bit, bits, bit2, bits2, bits3, bits5, bits4, this.captionChannelPacketData.readBits(3), this.captionChannelPacketData.readBits(3));
    }

    private void handleG0Character(int i10) {
        if (i10 == 127) {
            this.currentCueInfoBuilder.append((char) 9835);
        } else {
            this.currentCueInfoBuilder.append((char) (i10 & 255));
        }
    }

    private void handleG1Character(int i10) {
        this.currentCueInfoBuilder.append((char) (i10 & 255));
    }

    private void handleG2Character(int i10) {
        if (i10 == 32) {
            this.currentCueInfoBuilder.append(' ');
            return;
        }
        if (i10 == 33) {
            this.currentCueInfoBuilder.append((char) 160);
            return;
        }
        if (i10 == CHARACTER_ELLIPSIS) {
            this.currentCueInfoBuilder.append((char) 8230);
            return;
        }
        if (i10 == 42) {
            this.currentCueInfoBuilder.append((char) 352);
            return;
        }
        if (i10 == CHARACTER_BIG_OE) {
            this.currentCueInfoBuilder.append((char) 338);
            return;
        }
        if (i10 == 63) {
            this.currentCueInfoBuilder.append((char) 376);
            return;
        }
        if (i10 == CHARACTER_TM) {
            this.currentCueInfoBuilder.append((char) 8482);
            return;
        }
        if (i10 == CHARACTER_SMALL_CARONS) {
            this.currentCueInfoBuilder.append((char) 353);
            return;
        }
        if (i10 == CHARACTER_SMALL_OE) {
            this.currentCueInfoBuilder.append((char) 339);
            return;
        }
        if (i10 == CHARACTER_SM) {
            this.currentCueInfoBuilder.append((char) 8480);
            return;
        }
        switch (i10) {
            case 48:
                this.currentCueInfoBuilder.append((char) 9608);
                break;
            case CHARACTER_OPEN_SINGLE_QUOTE /* 49 */:
                this.currentCueInfoBuilder.append((char) 8216);
                break;
            case 50:
                this.currentCueInfoBuilder.append((char) 8217);
                break;
            case CHARACTER_OPEN_DOUBLE_QUOTE /* 51 */:
                this.currentCueInfoBuilder.append((char) 8220);
                break;
            case CHARACTER_CLOSE_DOUBLE_QUOTE /* 52 */:
                this.currentCueInfoBuilder.append((char) 8221);
                break;
            case CHARACTER_BOLD_BULLET /* 53 */:
                this.currentCueInfoBuilder.append((char) 8226);
                break;
            default:
                switch (i10) {
                    case CHARACTER_ONE_EIGHTH /* 118 */:
                        this.currentCueInfoBuilder.append((char) 8539);
                        break;
                    case CHARACTER_THREE_EIGHTHS /* 119 */:
                        this.currentCueInfoBuilder.append((char) 8540);
                        break;
                    case 120:
                        this.currentCueInfoBuilder.append((char) 8541);
                        break;
                    case CHARACTER_SEVEN_EIGHTHS /* 121 */:
                        this.currentCueInfoBuilder.append((char) 8542);
                        break;
                    case CHARACTER_VERTICAL_BORDER /* 122 */:
                        this.currentCueInfoBuilder.append((char) 9474);
                        break;
                    case CHARACTER_UPPER_RIGHT_BORDER /* 123 */:
                        this.currentCueInfoBuilder.append((char) 9488);
                        break;
                    case CHARACTER_LOWER_LEFT_BORDER /* 124 */:
                        this.currentCueInfoBuilder.append((char) 9492);
                        break;
                    case CHARACTER_HORIZONTAL_BORDER /* 125 */:
                        this.currentCueInfoBuilder.append((char) 9472);
                        break;
                    case CHARACTER_LOWER_RIGHT_BORDER /* 126 */:
                        this.currentCueInfoBuilder.append((char) 9496);
                        break;
                    case 127:
                        this.currentCueInfoBuilder.append((char) 9484);
                        break;
                    default:
                        a2.s(i10, "Invalid G2 character: ", TAG);
                        break;
                }
                break;
        }
    }

    private void handleG3Character(int i10) {
        if (i10 == 160) {
            this.currentCueInfoBuilder.append((char) 13252);
        } else {
            a2.s(i10, "Invalid G3 character: ", TAG);
            this.currentCueInfoBuilder.append('_');
        }
    }

    private void handleSetPenAttributes() {
        this.currentCueInfoBuilder.setPenAttributes(this.captionChannelPacketData.readBits(4), this.captionChannelPacketData.readBits(2), this.captionChannelPacketData.readBits(2), this.captionChannelPacketData.readBit(), this.captionChannelPacketData.readBit(), this.captionChannelPacketData.readBits(3), this.captionChannelPacketData.readBits(3));
    }

    private void handleSetPenColor() {
        int argbColorFromCeaColor = CueInfoBuilder.getArgbColorFromCeaColor(this.captionChannelPacketData.readBits(2), this.captionChannelPacketData.readBits(2), this.captionChannelPacketData.readBits(2), this.captionChannelPacketData.readBits(2));
        int argbColorFromCeaColor2 = CueInfoBuilder.getArgbColorFromCeaColor(this.captionChannelPacketData.readBits(2), this.captionChannelPacketData.readBits(2), this.captionChannelPacketData.readBits(2), this.captionChannelPacketData.readBits(2));
        this.captionChannelPacketData.skipBits(2);
        this.currentCueInfoBuilder.setPenColor(argbColorFromCeaColor, argbColorFromCeaColor2, CueInfoBuilder.getArgbColorFromCeaColor(this.captionChannelPacketData.readBits(2), this.captionChannelPacketData.readBits(2), this.captionChannelPacketData.readBits(2)));
    }

    private void handleSetPenLocation() {
        this.captionChannelPacketData.skipBits(4);
        int bits = this.captionChannelPacketData.readBits(4);
        this.captionChannelPacketData.skipBits(2);
        this.currentCueInfoBuilder.setPenLocation(bits, this.captionChannelPacketData.readBits(6));
    }

    private void handleSetWindowAttributes() {
        int argbColorFromCeaColor = CueInfoBuilder.getArgbColorFromCeaColor(this.captionChannelPacketData.readBits(2), this.captionChannelPacketData.readBits(2), this.captionChannelPacketData.readBits(2), this.captionChannelPacketData.readBits(2));
        int bits = this.captionChannelPacketData.readBits(2);
        int argbColorFromCeaColor2 = CueInfoBuilder.getArgbColorFromCeaColor(this.captionChannelPacketData.readBits(2), this.captionChannelPacketData.readBits(2), this.captionChannelPacketData.readBits(2));
        if (this.captionChannelPacketData.readBit()) {
            bits |= 4;
        }
        boolean bit = this.captionChannelPacketData.readBit();
        int bits2 = this.captionChannelPacketData.readBits(2);
        int bits3 = this.captionChannelPacketData.readBits(2);
        int bits4 = this.captionChannelPacketData.readBits(2);
        this.captionChannelPacketData.skipBits(8);
        this.currentCueInfoBuilder.setWindowAttributes(argbColorFromCeaColor, argbColorFromCeaColor2, bit, bits, bits2, bits3, bits4);
    }

    @RequiresNonNull({"currentDtvCcPacket"})
    private void processCurrentPacket() {
        DtvCcPacket dtvCcPacket = this.currentDtvCcPacket;
        if (dtvCcPacket.currentIndex != (dtvCcPacket.packetSize * 2) - 1) {
            Log.d(TAG, "DtvCcPacket ended prematurely; size is " + ((this.currentDtvCcPacket.packetSize * 2) - 1) + ", but current index is " + this.currentDtvCcPacket.currentIndex + " (sequence number " + this.currentDtvCcPacket.sequenceNumber + ");");
        }
        ParsableBitArray parsableBitArray = this.captionChannelPacketData;
        DtvCcPacket dtvCcPacket2 = this.currentDtvCcPacket;
        parsableBitArray.reset(dtvCcPacket2.packetData, dtvCcPacket2.currentIndex);
        boolean z = false;
        while (true) {
            if (this.captionChannelPacketData.bitsLeft() <= 0) {
                break;
            }
            int bits = this.captionChannelPacketData.readBits(3);
            int bits2 = this.captionChannelPacketData.readBits(5);
            if (bits == 7) {
                this.captionChannelPacketData.skipBits(2);
                bits = this.captionChannelPacketData.readBits(6);
                if (bits < 7) {
                    a2.s(bits, "Invalid extended service number: ", TAG);
                }
            }
            if (bits2 == 0) {
                if (bits != 0) {
                    Log.w(TAG, "serviceNumber is non-zero (" + bits + ") when blockSize is 0");
                }
            } else if (bits != this.selectedServiceNumber) {
                this.captionChannelPacketData.skipBytes(bits2);
            } else {
                int position = (bits2 * 8) + this.captionChannelPacketData.getPosition();
                while (this.captionChannelPacketData.getPosition() < position) {
                    int bits3 = this.captionChannelPacketData.readBits(8);
                    if (bits3 == 16) {
                        int bits4 = this.captionChannelPacketData.readBits(8);
                        if (bits4 <= 31) {
                            handleC2Command(bits4);
                        } else {
                            if (bits4 <= 127) {
                                handleG2Character(bits4);
                            } else if (bits4 <= 159) {
                                handleC3Command(bits4);
                            } else if (bits4 <= 255) {
                                handleG3Character(bits4);
                            } else {
                                a2.s(bits4, "Invalid extended command: ", TAG);
                            }
                            z = true;
                        }
                    } else if (bits3 <= 31) {
                        handleC0Command(bits3);
                    } else {
                        if (bits3 <= 127) {
                            handleG0Character(bits3);
                        } else if (bits3 <= 159) {
                            handleC1Command(bits3);
                        } else if (bits3 <= 255) {
                            handleG1Character(bits3);
                        } else {
                            a2.s(bits3, "Invalid base command: ", TAG);
                        }
                        z = true;
                    }
                }
            }
        }
        if (z) {
            this.cues = getDisplayCues();
        }
    }

    private void resetCueBuilders() {
        for (int i10 = 0; i10 < 8; i10++) {
            this.cueInfoBuilders[i10].reset();
        }
    }

    @Override // androidx.media3.extractor.text.cea.CeaDecoder
    public Subtitle createSubtitle() {
        List<Cue> list = this.cues;
        this.lastCues = list;
        list.getClass();
        return new CeaSubtitle(list);
    }

    @Override // androidx.media3.extractor.text.cea.CeaDecoder
    public void decode(SubtitleInputBuffer subtitleInputBuffer) {
        ByteBuffer byteBuffer = subtitleInputBuffer.data;
        byteBuffer.getClass();
        this.ccData.reset(byteBuffer.array(), byteBuffer.limit());
        while (this.ccData.bytesLeft() >= 3) {
            int unsignedByte = this.ccData.readUnsignedByte();
            int i10 = unsignedByte & 3;
            boolean z = (unsignedByte & 4) == 4;
            byte unsignedByte2 = (byte) this.ccData.readUnsignedByte();
            byte unsignedByte3 = (byte) this.ccData.readUnsignedByte();
            if (i10 == 2 || i10 == 3) {
                if (z) {
                    if (i10 == 3) {
                        finalizeCurrentPacket();
                        int i11 = (unsignedByte2 & 192) >> 6;
                        int i12 = this.previousSequenceNumber;
                        if (i12 != -1 && i11 != (i12 + 1) % 4) {
                            resetCueBuilders();
                            Log.w(TAG, "Sequence number discontinuity. previous=" + this.previousSequenceNumber + " current=" + i11);
                        }
                        this.previousSequenceNumber = i11;
                        int i13 = unsignedByte2 & 63;
                        if (i13 == 0) {
                            i13 = 64;
                        }
                        DtvCcPacket dtvCcPacket = new DtvCcPacket(i11, i13);
                        this.currentDtvCcPacket = dtvCcPacket;
                        byte[] bArr = dtvCcPacket.packetData;
                        int i14 = dtvCcPacket.currentIndex;
                        dtvCcPacket.currentIndex = i14 + 1;
                        bArr[i14] = unsignedByte3;
                    } else {
                        ac.b.j(i10 == 2);
                        DtvCcPacket dtvCcPacket2 = this.currentDtvCcPacket;
                        if (dtvCcPacket2 == null) {
                            Log.e(TAG, "Encountered DTVCC_PACKET_DATA before DTVCC_PACKET_START");
                        } else {
                            byte[] bArr2 = dtvCcPacket2.packetData;
                            int i15 = dtvCcPacket2.currentIndex;
                            int i16 = i15 + 1;
                            dtvCcPacket2.currentIndex = i16;
                            bArr2[i15] = unsignedByte2;
                            dtvCcPacket2.currentIndex = i15 + 2;
                            bArr2[i16] = unsignedByte3;
                        }
                    }
                    DtvCcPacket dtvCcPacket3 = this.currentDtvCcPacket;
                    if (dtvCcPacket3.currentIndex == (dtvCcPacket3.packetSize * 2) - 1) {
                        finalizeCurrentPacket();
                    }
                }
            }
        }
    }

    @Override // androidx.media3.extractor.text.cea.CeaDecoder, androidx.media3.decoder.Decoder
    public /* bridge */ /* synthetic */ SubtitleInputBuffer dequeueInputBuffer() throws SubtitleDecoderException {
        return super.dequeueInputBuffer();
    }

    @Override // androidx.media3.extractor.text.cea.CeaDecoder, androidx.media3.decoder.Decoder
    public /* bridge */ /* synthetic */ SubtitleOutputBuffer dequeueOutputBuffer() throws SubtitleDecoderException {
        return super.dequeueOutputBuffer();
    }

    @Override // androidx.media3.extractor.text.cea.CeaDecoder, androidx.media3.decoder.Decoder
    public void flush() {
        super.flush();
        this.cues = null;
        this.lastCues = null;
        this.currentWindow = 0;
        this.currentCueInfoBuilder = this.cueInfoBuilders[0];
        resetCueBuilders();
        this.currentDtvCcPacket = null;
    }

    @Override // androidx.media3.extractor.text.cea.CeaDecoder, androidx.media3.decoder.Decoder
    public String getName() {
        return TAG;
    }

    @Override // androidx.media3.extractor.text.cea.CeaDecoder
    public boolean isNewSubtitleDataAvailable() {
        return this.cues != this.lastCues;
    }

    @Override // androidx.media3.extractor.text.cea.CeaDecoder
    public /* bridge */ /* synthetic */ void queueInputBuffer(SubtitleInputBuffer subtitleInputBuffer) throws SubtitleDecoderException {
        super.queueInputBuffer(subtitleInputBuffer);
    }

    @Override // androidx.media3.extractor.text.cea.CeaDecoder, androidx.media3.decoder.Decoder
    public /* bridge */ /* synthetic */ void release() {
        super.release();
    }

    @Override // androidx.media3.extractor.text.cea.CeaDecoder, androidx.media3.extractor.text.SubtitleDecoder
    public /* bridge */ /* synthetic */ void setPositionUs(long j10) {
        super.setPositionUs(j10);
    }
}
