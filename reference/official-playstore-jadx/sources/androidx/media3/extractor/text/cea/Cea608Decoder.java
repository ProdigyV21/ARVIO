package androidx.media3.extractor.text.cea;

import android.text.Layout;
import android.text.SpannableString;
import android.text.SpannableStringBuilder;
import android.text.style.ForegroundColorSpan;
import android.text.style.StyleSpan;
import android.text.style.UnderlineSpan;
import androidx.compose.material3.MenuKt;
import androidx.compose.runtime.ComposerKt;
import androidx.media3.common.C;
import androidx.media3.common.MimeTypes;
import androidx.media3.common.text.Cue;
import androidx.media3.common.util.Log;
import androidx.media3.common.util.ParsableByteArray;
import androidx.media3.container.NalUnitUtil;
import androidx.media3.extractor.AacUtil;
import androidx.media3.extractor.flac.FlacConstants;
import androidx.media3.extractor.metadata.dvbsi.AppInfoTableDecoder;
import androidx.media3.extractor.text.Subtitle;
import androidx.media3.extractor.text.SubtitleDecoderException;
import androidx.media3.extractor.text.SubtitleInputBuffer;
import androidx.media3.extractor.text.SubtitleOutputBuffer;
import androidx.media3.extractor.ts.PsExtractor;
import androidx.media3.extractor.ts.TsExtractor;
import com.arflix.tv.ui.theme.AnimationConstants;
import com.google.android.gms.cast.MediaError;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/* JADX INFO: loaded from: classes3.dex */
public final class Cea608Decoder extends CeaDecoder {
    private static final int CC_FIELD_FLAG = 1;
    private static final byte CC_IMPLICIT_DATA_HEADER = -4;
    private static final int CC_MODE_PAINT_ON = 3;
    private static final int CC_MODE_POP_ON = 2;
    private static final int CC_MODE_ROLL_UP = 1;
    private static final int CC_MODE_UNKNOWN = 0;
    private static final int CC_TYPE_FLAG = 2;
    private static final int CC_VALID_FLAG = 4;
    private static final byte CTRL_BACKSPACE = 33;
    private static final byte CTRL_CARRIAGE_RETURN = 45;
    private static final byte CTRL_DELETE_TO_END_OF_ROW = 36;
    private static final byte CTRL_END_OF_CAPTION = 47;
    private static final byte CTRL_ERASE_DISPLAYED_MEMORY = 44;
    private static final byte CTRL_ERASE_NON_DISPLAYED_MEMORY = 46;
    private static final byte CTRL_RESUME_CAPTION_LOADING = 32;
    private static final byte CTRL_RESUME_DIRECT_CAPTIONING = 41;
    private static final byte CTRL_RESUME_TEXT_DISPLAY = 43;
    private static final byte CTRL_ROLL_UP_CAPTIONS_2_ROWS = 37;
    private static final byte CTRL_ROLL_UP_CAPTIONS_3_ROWS = 38;
    private static final byte CTRL_ROLL_UP_CAPTIONS_4_ROWS = 39;
    private static final byte CTRL_TEXT_RESTART = 42;
    private static final int DEFAULT_CAPTIONS_ROW_COUNT = 4;
    public static final long MIN_DATA_CHANNEL_TIMEOUT_MS = 16000;
    private static final int NTSC_CC_CHANNEL_1 = 0;
    private static final int NTSC_CC_CHANNEL_2 = 1;
    private static final int NTSC_CC_FIELD_1 = 0;
    private static final int NTSC_CC_FIELD_2 = 1;
    private static final int STYLE_ITALICS = 7;
    private static final int STYLE_UNCHANGED = 8;
    private static final String TAG = "Cea608Decoder";
    private int captionMode;
    private int captionRowCount;
    private List<Cue> cues;
    private boolean isCaptionValid;
    private boolean isInCaptionService;
    private long lastCueUpdateUs;
    private List<Cue> lastCues;
    private final int packetLength;
    private byte repeatableControlCc1;
    private byte repeatableControlCc2;
    private boolean repeatableControlSet;
    private final int selectedChannel;
    private final int selectedField;
    private final long validDataChannelTimeoutUs;
    private static final int[] ROW_INDICES = {11, 1, 3, 12, 14, 5, 7, 9};
    private static final int[] COLUMN_INDICES = {0, 4, 8, 12, 16, 20, 24, 28};
    private static final int[] STYLE_COLORS = {-1, -16711936, -16776961, -16711681, -65536, -256, -65281};
    private static final int[] BASIC_CHARACTER_SET = {32, 33, 34, 35, 36, 37, 38, 39, 40, 41, 225, 43, 44, 45, 46, 47, 48, 49, 50, 51, 52, 53, 54, 55, 56, 57, 58, 59, 60, 61, 62, 63, 64, 65, 66, 67, 68, 69, 70, 71, 72, 73, 74, 75, 76, 77, 78, 79, 80, 81, 82, 83, 84, 85, 86, 87, 88, 89, 90, 91, 233, 93, 237, 243, AnimationConstants.DURATION_IMAGE_CROSSFADE, 97, 98, 99, 100, 101, MediaError.DetailedErrorCode.MEDIA_DECODE, MediaError.DetailedErrorCode.MEDIA_NETWORK, MediaError.DetailedErrorCode.MEDIA_SRC_NOT_SUPPORTED, 105, 106, 107, 108, 109, MediaError.DetailedErrorCode.SOURCE_BUFFER_FAILURE, 111, 112, 113, 114, 115, AppInfoTableDecoder.APPLICATION_INFORMATION_TABLE_ID, 117, 118, 119, MenuKt.InTransitionDuration, 121, 122, 231, 247, 209, 241, 9632};
    private static final int[] SPECIAL_CHARACTER_SET = {174, 176, PsExtractor.PRIVATE_STREAM_1, 191, 8482, 162, 163, 9834, 224, 32, 232, 226, 234, 238, 244, 251};
    private static final int[] SPECIAL_ES_FR_CHARACTER_SET = {193, 201, 211, 218, 220, 252, 8216, 161, 42, 39, 8212, 169, 8480, 8226, 8220, 8221, PsExtractor.AUDIO_STREAM, 194, 199, 200, 202, 203, 235, ComposerKt.referenceKey, ComposerKt.reuseKey, 239, 212, 217, 249, 219, 171, 187};
    private static final int[] SPECIAL_PT_DE_CHARACTER_SET = {195, 227, 205, ComposerKt.providerMapsKey, 236, 210, 242, 213, 245, 123, 125, 92, 94, 95, 124, 126, 196, 228, 214, 246, 223, 165, 164, 9474, 197, 229, 216, 248, 9484, 9488, 9492, 9496};
    private static final boolean[] ODD_PARITY_BYTE_TABLE = {false, true, true, false, true, false, false, true, true, false, false, true, false, true, true, false, true, false, false, true, false, true, true, false, false, true, true, false, true, false, false, true, true, false, false, true, false, true, true, false, false, true, true, false, true, false, false, true, false, true, true, false, true, false, false, true, true, false, false, true, false, true, true, false, true, false, false, true, false, true, true, false, false, true, true, false, true, false, false, true, false, true, true, false, true, false, false, true, true, false, false, true, false, true, true, false, false, true, true, false, true, false, false, true, true, false, false, true, false, true, true, false, true, false, false, true, false, true, true, false, false, true, true, false, true, false, false, true, true, false, false, true, false, true, true, false, false, true, true, false, true, false, false, true, false, true, true, false, true, false, false, true, true, false, false, true, false, true, true, false, false, true, true, false, true, false, false, true, true, false, false, true, false, true, true, false, true, false, false, true, false, true, true, false, false, true, true, false, true, false, false, true, false, true, true, false, true, false, false, true, true, false, false, true, false, true, true, false, true, false, false, true, false, true, true, false, false, true, true, false, true, false, false, true, true, false, false, true, false, true, true, false, false, true, true, false, true, false, false, true, false, true, true, false, true, false, false, true, true, false, false, true, false, true, true, false};
    private final ParsableByteArray ccData = new ParsableByteArray();
    private final ArrayList<CueBuilder> cueBuilders = new ArrayList<>();
    private CueBuilder currentCueBuilder = new CueBuilder(0, 4);
    private int currentChannel = 0;

    public static final class CueBuilder {
        private static final int BASE_ROW = 15;
        private static final int SCREEN_CHARWIDTH = 32;
        private int captionMode;
        private int captionRowCount;
        private int indent;
        private int row;
        private int tabOffset;
        private final List<CueStyle> cueStyles = new ArrayList();
        private final List<SpannableString> rolledUpCaptions = new ArrayList();
        private final StringBuilder captionStringBuilder = new StringBuilder();

        public static class CueStyle {
            public int start;
            public final int style;
            public final boolean underline;

            public CueStyle(int i10, boolean z, int i11) {
                this.style = i10;
                this.underline = z;
                this.start = i11;
            }
        }

        public CueBuilder(int i10, int i11) {
            reset(i10);
            this.captionRowCount = i11;
        }

        private SpannableString buildCurrentLine() {
            SpannableStringBuilder spannableStringBuilder = new SpannableStringBuilder(this.captionStringBuilder);
            int length = spannableStringBuilder.length();
            int i10 = -1;
            int i11 = -1;
            int i12 = -1;
            int i13 = -1;
            int i14 = 0;
            int i15 = 0;
            boolean z = false;
            while (i14 < this.cueStyles.size()) {
                CueStyle cueStyle = this.cueStyles.get(i14);
                boolean z5 = cueStyle.underline;
                int i16 = cueStyle.style;
                if (i16 != 8) {
                    boolean z10 = i16 == 7;
                    if (i16 != 7) {
                        i13 = Cea608Decoder.STYLE_COLORS[i16];
                    }
                    z = z10;
                }
                int i17 = cueStyle.start;
                i14++;
                if (i17 != (i14 < this.cueStyles.size() ? this.cueStyles.get(i14).start : length)) {
                    if (i10 != -1 && !z5) {
                        setUnderlineSpan(spannableStringBuilder, i10, i17);
                        i10 = -1;
                    } else if (i10 == -1 && z5) {
                        i10 = i17;
                    }
                    if (i11 != -1 && !z) {
                        setItalicSpan(spannableStringBuilder, i11, i17);
                        i11 = -1;
                    } else if (i11 == -1 && z) {
                        i11 = i17;
                    }
                    if (i13 != i12) {
                        setColorSpan(spannableStringBuilder, i15, i17, i12);
                        i12 = i13;
                        i15 = i17;
                    }
                }
            }
            if (i10 != -1 && i10 != length) {
                setUnderlineSpan(spannableStringBuilder, i10, length);
            }
            if (i11 != -1 && i11 != length) {
                setItalicSpan(spannableStringBuilder, i11, length);
            }
            if (i15 != length) {
                setColorSpan(spannableStringBuilder, i15, length, i12);
            }
            return new SpannableString(spannableStringBuilder);
        }

        private static void setColorSpan(SpannableStringBuilder spannableStringBuilder, int i10, int i11, int i12) {
            if (i12 == -1) {
                return;
            }
            spannableStringBuilder.setSpan(new ForegroundColorSpan(i12), i10, i11, 33);
        }

        private static void setItalicSpan(SpannableStringBuilder spannableStringBuilder, int i10, int i11) {
            spannableStringBuilder.setSpan(new StyleSpan(2), i10, i11, 33);
        }

        private static void setUnderlineSpan(SpannableStringBuilder spannableStringBuilder, int i10, int i11) {
            spannableStringBuilder.setSpan(new UnderlineSpan(), i10, i11, 33);
        }

        public void append(char c10) {
            if (this.captionStringBuilder.length() < 32) {
                this.captionStringBuilder.append(c10);
            }
        }

        public void backspace() {
            int length = this.captionStringBuilder.length();
            if (length > 0) {
                this.captionStringBuilder.delete(length - 1, length);
                for (int size = this.cueStyles.size() - 1; size >= 0; size--) {
                    CueStyle cueStyle = this.cueStyles.get(size);
                    int i10 = cueStyle.start;
                    if (i10 != length) {
                        return;
                    }
                    cueStyle.start = i10 - 1;
                }
            }
        }

        public Cue build(int i10) {
            float f10;
            SpannableStringBuilder spannableStringBuilder = new SpannableStringBuilder();
            for (int i11 = 0; i11 < this.rolledUpCaptions.size(); i11++) {
                spannableStringBuilder.append((CharSequence) this.rolledUpCaptions.get(i11));
                spannableStringBuilder.append('\n');
            }
            spannableStringBuilder.append((CharSequence) buildCurrentLine());
            if (spannableStringBuilder.length() == 0) {
                return null;
            }
            int i12 = this.indent + this.tabOffset;
            int length = (32 - i12) - spannableStringBuilder.length();
            int i13 = i12 - length;
            if (i10 == Integer.MIN_VALUE) {
                i10 = (this.captionMode != 2 || (Math.abs(i13) >= 3 && length >= 0)) ? (this.captionMode != 2 || i13 <= 0) ? 0 : 2 : 1;
            }
            if (i10 != 1) {
                if (i10 == 2) {
                    i12 = 32 - length;
                }
                f10 = ((i12 / 32.0f) * 0.8f) + 0.1f;
            } else {
                f10 = 0.5f;
            }
            int i14 = this.row;
            if (i14 > 7) {
                i14 -= 17;
            } else if (this.captionMode == 1) {
                i14 -= this.captionRowCount - 1;
            }
            return new Cue.Builder().setText(spannableStringBuilder).setTextAlignment(Layout.Alignment.ALIGN_NORMAL).setLine(i14, 1).setPosition(f10).setPositionAnchor(i10).build();
        }

        public boolean isEmpty() {
            return this.cueStyles.isEmpty() && this.rolledUpCaptions.isEmpty() && this.captionStringBuilder.length() == 0;
        }

        public void reset(int i10) {
            this.captionMode = i10;
            this.cueStyles.clear();
            this.rolledUpCaptions.clear();
            this.captionStringBuilder.setLength(0);
            this.row = 15;
            this.indent = 0;
            this.tabOffset = 0;
        }

        public void rollUp() {
            this.rolledUpCaptions.add(buildCurrentLine());
            this.captionStringBuilder.setLength(0);
            this.cueStyles.clear();
            int iMin = Math.min(this.captionRowCount, this.row);
            while (this.rolledUpCaptions.size() >= iMin) {
                this.rolledUpCaptions.remove(0);
            }
        }

        public void setCaptionMode(int i10) {
            this.captionMode = i10;
        }

        public void setCaptionRowCount(int i10) {
            this.captionRowCount = i10;
        }

        public void setStyle(int i10, boolean z) {
            this.cueStyles.add(new CueStyle(i10, z, this.captionStringBuilder.length()));
        }
    }

    public Cea608Decoder(String str, int i10, long j10) {
        if (j10 != C.TIME_UNSET) {
            ac.b.j(j10 >= MIN_DATA_CHANNEL_TIMEOUT_MS);
            this.validDataChannelTimeoutUs = j10 * 1000;
        } else {
            this.validDataChannelTimeoutUs = C.TIME_UNSET;
        }
        this.packetLength = MimeTypes.APPLICATION_MP4CEA608.equals(str) ? 2 : 3;
        if (i10 == 1) {
            this.selectedChannel = 0;
            this.selectedField = 0;
        } else if (i10 == 2) {
            this.selectedChannel = 1;
            this.selectedField = 0;
        } else if (i10 == 3) {
            this.selectedChannel = 0;
            this.selectedField = 1;
        } else if (i10 != 4) {
            Log.w(TAG, "Invalid channel. Defaulting to CC1.");
            this.selectedChannel = 0;
            this.selectedField = 0;
        } else {
            this.selectedChannel = 1;
            this.selectedField = 1;
        }
        setCaptionMode(0);
        resetCueBuilders();
        this.isInCaptionService = true;
        this.lastCueUpdateUs = C.TIME_UNSET;
    }

    private static char getBasicChar(byte b10) {
        return (char) BASIC_CHARACTER_SET[(b10 & 127) - 32];
    }

    private static int getChannel(byte b10) {
        return (b10 >> 3) & 1;
    }

    private List<Cue> getDisplayCues() {
        int size = this.cueBuilders.size();
        ArrayList arrayList = new ArrayList(size);
        int iMin = 2;
        for (int i10 = 0; i10 < size; i10++) {
            Cue cueBuild = this.cueBuilders.get(i10).build(Integer.MIN_VALUE);
            arrayList.add(cueBuild);
            if (cueBuild != null) {
                iMin = Math.min(iMin, cueBuild.positionAnchor);
            }
        }
        ArrayList arrayList2 = new ArrayList(size);
        for (int i11 = 0; i11 < size; i11++) {
            Cue cueBuild2 = (Cue) arrayList.get(i11);
            if (cueBuild2 != null) {
                if (cueBuild2.positionAnchor != iMin) {
                    cueBuild2 = this.cueBuilders.get(i11).build(iMin);
                    cueBuild2.getClass();
                }
                arrayList2.add(cueBuild2);
            }
        }
        return arrayList2;
    }

    private static char getExtendedEsFrChar(byte b10) {
        return (char) SPECIAL_ES_FR_CHARACTER_SET[b10 & 31];
    }

    private static char getExtendedPtDeChar(byte b10) {
        return (char) SPECIAL_PT_DE_CHARACTER_SET[b10 & 31];
    }

    private static char getExtendedWestEuropeanChar(byte b10, byte b11) {
        return (b10 & 1) == 0 ? getExtendedEsFrChar(b11) : getExtendedPtDeChar(b11);
    }

    private static char getSpecialNorthAmericanChar(byte b10) {
        return (char) SPECIAL_CHARACTER_SET[b10 & 15];
    }

    private void handleMidrowCtrl(byte b10) {
        this.currentCueBuilder.append(' ');
        this.currentCueBuilder.setStyle((b10 >> 1) & 7, (b10 & 1) == 1);
    }

    private void handleMiscCode(byte b10) {
        if (b10 == 32) {
            setCaptionMode(2);
            return;
        }
        if (b10 == 41) {
            setCaptionMode(3);
            return;
        }
        switch (b10) {
            case 37:
                setCaptionMode(1);
                setCaptionRowCount(2);
                break;
            case FlacConstants.STREAM_INFO_BLOCK_SIZE /* 38 */:
                setCaptionMode(1);
                setCaptionRowCount(3);
                break;
            case NalUnitUtil.H265_NAL_UNIT_TYPE_PREFIX_SEI /* 39 */:
                setCaptionMode(1);
                setCaptionRowCount(4);
                break;
            default:
                int i10 = this.captionMode;
                if (i10 != 0) {
                    if (b10 != 33) {
                        switch (b10) {
                            case 44:
                                this.cues = Collections.EMPTY_LIST;
                                if (i10 == 1 || i10 == 3) {
                                    resetCueBuilders();
                                }
                                break;
                            case TsExtractor.TS_STREAM_TYPE_MHAS /* 45 */:
                                if (i10 == 1 && !this.currentCueBuilder.isEmpty()) {
                                    this.currentCueBuilder.rollUp();
                                    break;
                                }
                                break;
                            case 46:
                                resetCueBuilders();
                                break;
                            case 47:
                                this.cues = getDisplayCues();
                                resetCueBuilders();
                                break;
                        }
                    } else {
                        this.currentCueBuilder.backspace();
                        break;
                    }
                }
                break;
        }
    }

    private void handlePreambleAddressCode(byte b10, byte b11) {
        int i10 = ROW_INDICES[b10 & 7];
        if ((b11 & CTRL_RESUME_CAPTION_LOADING) != 0) {
            i10++;
        }
        if (i10 != this.currentCueBuilder.row) {
            if (this.captionMode != 1 && !this.currentCueBuilder.isEmpty()) {
                CueBuilder cueBuilder = new CueBuilder(this.captionMode, this.captionRowCount);
                this.currentCueBuilder = cueBuilder;
                this.cueBuilders.add(cueBuilder);
            }
            this.currentCueBuilder.row = i10;
        }
        boolean z = (b11 & 16) == 16;
        boolean z5 = (b11 & 1) == 1;
        int i11 = (b11 >> 1) & 7;
        this.currentCueBuilder.setStyle(z ? 8 : i11, z5);
        if (z) {
            this.currentCueBuilder.indent = COLUMN_INDICES[i11];
        }
    }

    private static boolean isCtrlCode(byte b10) {
        return (b10 & 224) == 0;
    }

    private static boolean isExtendedWestEuropeanChar(byte b10, byte b11) {
        return (b10 & 246) == 18 && (b11 & 224) == 32;
    }

    private static boolean isMidrowCtrlCode(byte b10, byte b11) {
        return (b10 & 247) == 17 && (b11 & 240) == 32;
    }

    private static boolean isMiscCode(byte b10, byte b11) {
        return (b10 & 246) == 20 && (b11 & 240) == 32;
    }

    private static boolean isPreambleAddressCode(byte b10, byte b11) {
        return (b10 & 240) == 16 && (b11 & 192) == 64;
    }

    private static boolean isRepeatable(byte b10) {
        return (b10 & 240) == 16;
    }

    private boolean isRepeatedCommand(boolean z, byte b10, byte b11) {
        if (!z || !isRepeatable(b10)) {
            this.repeatableControlSet = false;
        } else {
            if (this.repeatableControlSet && this.repeatableControlCc1 == b10 && this.repeatableControlCc2 == b11) {
                this.repeatableControlSet = false;
                return true;
            }
            this.repeatableControlSet = true;
            this.repeatableControlCc1 = b10;
            this.repeatableControlCc2 = b11;
        }
        return false;
    }

    private static boolean isServiceSwitchCommand(byte b10) {
        return (b10 & 246) == 20;
    }

    private static boolean isSpecialNorthAmericanChar(byte b10, byte b11) {
        return (b10 & 247) == 17 && (b11 & 240) == 48;
    }

    private static boolean isTabCtrlCode(byte b10, byte b11) {
        return (b10 & 247) == 23 && b11 >= 33 && b11 <= 35;
    }

    private static boolean isXdsControlCode(byte b10) {
        return 1 <= b10 && b10 <= 15;
    }

    private void maybeUpdateIsInCaptionService(byte b10, byte b11) {
        if (isXdsControlCode(b10)) {
            this.isInCaptionService = false;
            return;
        }
        if (isServiceSwitchCommand(b10)) {
            if (b11 != 32 && b11 != 47) {
                switch (b11) {
                    case 37:
                    case FlacConstants.STREAM_INFO_BLOCK_SIZE /* 38 */:
                    case NalUnitUtil.H265_NAL_UNIT_TYPE_PREFIX_SEI /* 39 */:
                        break;
                    default:
                        switch (b11) {
                            case AacUtil.AUDIO_OBJECT_TYPE_AAC_XHE /* 42 */:
                            case 43:
                                this.isInCaptionService = false;
                                break;
                        }
                        return;
                }
            }
            this.isInCaptionService = true;
        }
    }

    private void resetCueBuilders() {
        this.currentCueBuilder.reset(this.captionMode);
        this.cueBuilders.clear();
        this.cueBuilders.add(this.currentCueBuilder);
    }

    private void setCaptionMode(int i10) {
        int i11 = this.captionMode;
        if (i11 == i10) {
            return;
        }
        this.captionMode = i10;
        if (i10 == 3) {
            for (int i12 = 0; i12 < this.cueBuilders.size(); i12++) {
                this.cueBuilders.get(i12).setCaptionMode(i10);
            }
            return;
        }
        resetCueBuilders();
        if (i11 == 3 || i10 == 1 || i10 == 0) {
            this.cues = Collections.EMPTY_LIST;
        }
    }

    private void setCaptionRowCount(int i10) {
        this.captionRowCount = i10;
        this.currentCueBuilder.setCaptionRowCount(i10);
    }

    private boolean shouldClearStuckCaptions() {
        return (this.validDataChannelTimeoutUs == C.TIME_UNSET || this.lastCueUpdateUs == C.TIME_UNSET || getPositionUs() - this.lastCueUpdateUs < this.validDataChannelTimeoutUs) ? false : true;
    }

    private boolean updateAndVerifyCurrentChannel(byte b10) {
        if (isCtrlCode(b10)) {
            this.currentChannel = getChannel(b10);
        }
        return this.currentChannel == this.selectedChannel;
    }

    @Override // androidx.media3.extractor.text.cea.CeaDecoder
    public Subtitle createSubtitle() {
        List<Cue> list = this.cues;
        this.lastCues = list;
        list.getClass();
        return new CeaSubtitle(list);
    }

    /* JADX WARN: Removed duplicated region for block: B:26:0x0060  */
    @Override // androidx.media3.extractor.text.cea.CeaDecoder
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public void decode(androidx.media3.extractor.text.SubtitleInputBuffer r10) {
        /*
            Method dump skipped, instruction units count: 264
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.media3.extractor.text.cea.Cea608Decoder.decode(androidx.media3.extractor.text.SubtitleInputBuffer):void");
    }

    @Override // androidx.media3.extractor.text.cea.CeaDecoder, androidx.media3.decoder.Decoder
    public /* bridge */ /* synthetic */ SubtitleInputBuffer dequeueInputBuffer() throws SubtitleDecoderException {
        return super.dequeueInputBuffer();
    }

    @Override // androidx.media3.extractor.text.cea.CeaDecoder, androidx.media3.decoder.Decoder
    public void flush() {
        super.flush();
        this.cues = null;
        this.lastCues = null;
        setCaptionMode(0);
        setCaptionRowCount(4);
        resetCueBuilders();
        this.isCaptionValid = false;
        this.repeatableControlSet = false;
        this.repeatableControlCc1 = (byte) 0;
        this.repeatableControlCc2 = (byte) 0;
        this.currentChannel = 0;
        this.isInCaptionService = true;
        this.lastCueUpdateUs = C.TIME_UNSET;
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
    public void release() {
    }

    @Override // androidx.media3.extractor.text.cea.CeaDecoder, androidx.media3.extractor.text.SubtitleDecoder
    public /* bridge */ /* synthetic */ void setPositionUs(long j10) {
        super.setPositionUs(j10);
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // androidx.media3.extractor.text.cea.CeaDecoder, androidx.media3.decoder.Decoder
    public SubtitleOutputBuffer dequeueOutputBuffer() throws SubtitleDecoderException {
        SubtitleOutputBuffer availableOutputBuffer;
        SubtitleOutputBuffer subtitleOutputBufferDequeueOutputBuffer = super.dequeueOutputBuffer();
        if (subtitleOutputBufferDequeueOutputBuffer != null) {
            return subtitleOutputBufferDequeueOutputBuffer;
        }
        if (!shouldClearStuckCaptions() || (availableOutputBuffer = getAvailableOutputBuffer()) == null) {
            return null;
        }
        this.cues = Collections.EMPTY_LIST;
        this.lastCueUpdateUs = C.TIME_UNSET;
        availableOutputBuffer.setContent(getPositionUs(), createSubtitle(), Long.MAX_VALUE);
        return availableOutputBuffer;
    }
}
