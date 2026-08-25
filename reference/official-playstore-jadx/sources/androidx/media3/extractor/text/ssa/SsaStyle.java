package androidx.media3.extractor.text.ssa;

import ac.b;
import android.graphics.Color;
import android.graphics.PointF;
import android.text.TextUtils;
import androidx.fragment.app.a2;
import androidx.media3.common.util.Log;
import androidx.media3.common.util.Util;
import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import m2.f0;
import t7.a;

/* JADX INFO: loaded from: classes3.dex */
final class SsaStyle {
    public static final int SSA_ALIGNMENT_BOTTOM_CENTER = 2;
    public static final int SSA_ALIGNMENT_BOTTOM_LEFT = 1;
    public static final int SSA_ALIGNMENT_BOTTOM_RIGHT = 3;
    public static final int SSA_ALIGNMENT_MIDDLE_CENTER = 5;
    public static final int SSA_ALIGNMENT_MIDDLE_LEFT = 4;
    public static final int SSA_ALIGNMENT_MIDDLE_RIGHT = 6;
    public static final int SSA_ALIGNMENT_TOP_CENTER = 8;
    public static final int SSA_ALIGNMENT_TOP_LEFT = 7;
    public static final int SSA_ALIGNMENT_TOP_RIGHT = 9;
    public static final int SSA_ALIGNMENT_UNKNOWN = -1;
    public static final int SSA_BORDER_STYLE_BOX = 3;
    public static final int SSA_BORDER_STYLE_OUTLINE = 1;
    public static final int SSA_BORDER_STYLE_UNKNOWN = -1;
    private static final String TAG = "SsaStyle";
    public final int alignment;
    public final boolean bold;
    public final int borderStyle;
    public final float fontSize;
    public final boolean italic;
    public final String name;
    public final Integer outlineColor;
    public final Integer primaryColor;
    public final boolean strikeout;
    public final boolean underline;

    public static final class Format {
        public final int alignmentIndex;
        public final int boldIndex;
        public final int borderStyleIndex;
        public final int fontSizeIndex;
        public final int italicIndex;
        public final int length;
        public final int nameIndex;
        public final int outlineColorIndex;
        public final int primaryColorIndex;
        public final int strikeoutIndex;
        public final int underlineIndex;

        private Format(int i10, int i11, int i12, int i13, int i14, int i15, int i16, int i17, int i18, int i19, int i20) {
            this.nameIndex = i10;
            this.alignmentIndex = i11;
            this.primaryColorIndex = i12;
            this.outlineColorIndex = i13;
            this.fontSizeIndex = i14;
            this.boldIndex = i15;
            this.italicIndex = i16;
            this.underlineIndex = i17;
            this.strikeoutIndex = i18;
            this.borderStyleIndex = i19;
            this.length = i20;
        }

        public static Format fromFormatLine(String str) {
            String[] strArrSplit = TextUtils.split(str.substring(7), ",");
            int i10 = -1;
            int i11 = -1;
            int i12 = -1;
            int i13 = -1;
            int i14 = -1;
            int i15 = -1;
            int i16 = -1;
            int i17 = -1;
            int i18 = -1;
            int i19 = -1;
            for (int i20 = 0; i20 < strArrSplit.length; i20++) {
                String strS = a.S(strArrSplit[i20].trim());
                strS.getClass();
                switch (strS) {
                    case "italic":
                        i16 = i20;
                        break;
                    case "underline":
                        i17 = i20;
                        break;
                    case "strikeout":
                        i18 = i20;
                        break;
                    case "primarycolour":
                        i12 = i20;
                        break;
                    case "bold":
                        i15 = i20;
                        break;
                    case "name":
                        i10 = i20;
                        break;
                    case "fontsize":
                        i14 = i20;
                        break;
                    case "borderstyle":
                        i19 = i20;
                        break;
                    case "alignment":
                        i11 = i20;
                        break;
                    case "outlinecolour":
                        i13 = i20;
                        break;
                }
            }
            if (i10 != -1) {
                return new Format(i10, i11, i12, i13, i14, i15, i16, i17, i18, i19, strArrSplit.length);
            }
            return null;
        }
    }

    public static final class Overrides {
        private static final String TAG = "SsaStyle.Overrides";
        public final int alignment;
        public final PointF position;
        private static final Pattern BRACES_PATTERN = Pattern.compile("\\{([^}]*)\\}");
        private static final String PADDED_DECIMAL_PATTERN = "\\s*\\d+(?:\\.\\d+)?\\s*";
        private static final Pattern POSITION_PATTERN = Pattern.compile(Util.formatInvariant("\\\\pos\\((%1$s),(%1$s)\\)", PADDED_DECIMAL_PATTERN));
        private static final Pattern MOVE_PATTERN = Pattern.compile(Util.formatInvariant("\\\\move\\(%1$s,%1$s,(%1$s),(%1$s)(?:,%1$s,%1$s)?\\)", PADDED_DECIMAL_PATTERN));
        private static final Pattern ALIGNMENT_OVERRIDE_PATTERN = Pattern.compile("\\\\an(\\d+)");

        private Overrides(int i10, PointF pointF) {
            this.alignment = i10;
            this.position = pointF;
        }

        private static int parseAlignmentOverride(String str) {
            Matcher matcher = ALIGNMENT_OVERRIDE_PATTERN.matcher(str);
            if (!matcher.find()) {
                return -1;
            }
            String strGroup = matcher.group(1);
            strGroup.getClass();
            return SsaStyle.parseAlignment(strGroup);
        }

        public static Overrides parseFromDialogue(String str) {
            Matcher matcher = BRACES_PATTERN.matcher(str);
            PointF pointF = null;
            int i10 = -1;
            while (matcher.find()) {
                String strGroup = matcher.group(1);
                strGroup.getClass();
                try {
                    PointF position = parsePosition(strGroup);
                    if (position != null) {
                        pointF = position;
                    }
                } catch (RuntimeException unused) {
                }
                try {
                    int alignmentOverride = parseAlignmentOverride(strGroup);
                    if (alignmentOverride != -1) {
                        i10 = alignmentOverride;
                    }
                } catch (RuntimeException unused2) {
                }
            }
            return new Overrides(i10, pointF);
        }

        private static PointF parsePosition(String str) {
            String strGroup;
            String strGroup2;
            Matcher matcher = POSITION_PATTERN.matcher(str);
            Matcher matcher2 = MOVE_PATTERN.matcher(str);
            boolean zFind = matcher.find();
            boolean zFind2 = matcher2.find();
            if (zFind) {
                if (zFind2) {
                    Log.i(TAG, "Override has both \\pos(x,y) and \\move(x1,y1,x2,y2); using \\pos values. override='" + str + "'");
                }
                strGroup = matcher.group(1);
                strGroup2 = matcher.group(2);
            } else {
                if (!zFind2) {
                    return null;
                }
                strGroup = matcher2.group(1);
                strGroup2 = matcher2.group(2);
            }
            strGroup.getClass();
            float f10 = Float.parseFloat(strGroup.trim());
            strGroup2.getClass();
            return new PointF(f10, Float.parseFloat(strGroup2.trim()));
        }

        public static String stripStyleOverrides(String str) {
            return BRACES_PATTERN.matcher(str).replaceAll("");
        }
    }

    @Target({ElementType.TYPE_USE})
    @Documented
    @Retention(RetentionPolicy.SOURCE)
    public @interface SsaAlignment {
    }

    @Target({ElementType.TYPE_USE})
    @Documented
    @Retention(RetentionPolicy.SOURCE)
    public @interface SsaBorderStyle {
    }

    private SsaStyle(String str, int i10, Integer num, Integer num2, float f10, boolean z, boolean z5, boolean z10, boolean z11, int i11) {
        this.name = str;
        this.alignment = i10;
        this.primaryColor = num;
        this.outlineColor = num2;
        this.fontSize = f10;
        this.bold = z;
        this.italic = z5;
        this.underline = z10;
        this.strikeout = z11;
        this.borderStyle = i11;
    }

    public static SsaStyle fromStyleLine(String str, Format format) {
        b.j(str.startsWith("Style:"));
        String[] strArrSplit = TextUtils.split(str.substring(6), ",");
        int length = strArrSplit.length;
        int i10 = format.length;
        if (length != i10) {
            Log.w(TAG, Util.formatInvariant("Skipping malformed 'Style:' line (expected %s values, found %s): '%s'", Integer.valueOf(i10), Integer.valueOf(strArrSplit.length), str));
            return null;
        }
        try {
            String strTrim = strArrSplit[format.nameIndex].trim();
            int i11 = format.alignmentIndex;
            int alignment = i11 != -1 ? parseAlignment(strArrSplit[i11].trim()) : -1;
            int i12 = format.primaryColorIndex;
            Integer color = i12 != -1 ? parseColor(strArrSplit[i12].trim()) : null;
            int i13 = format.outlineColorIndex;
            Integer color2 = i13 != -1 ? parseColor(strArrSplit[i13].trim()) : null;
            int i14 = format.fontSizeIndex;
            float fontSize = i14 != -1 ? parseFontSize(strArrSplit[i14].trim()) : -3.4028235E38f;
            int i15 = format.boldIndex;
            boolean z = i15 != -1 && parseBooleanValue(strArrSplit[i15].trim());
            int i16 = format.italicIndex;
            boolean z5 = i16 != -1 && parseBooleanValue(strArrSplit[i16].trim());
            int i17 = format.underlineIndex;
            boolean z10 = i17 != -1 && parseBooleanValue(strArrSplit[i17].trim());
            int i18 = format.strikeoutIndex;
            boolean z11 = i18 != -1 && parseBooleanValue(strArrSplit[i18].trim());
            int i19 = format.borderStyleIndex;
            return new SsaStyle(strTrim, alignment, color, color2, fontSize, z, z5, z10, z11, i19 != -1 ? parseBorderStyle(strArrSplit[i19].trim()) : -1);
        } catch (RuntimeException e5) {
            Log.w(TAG, "Skipping malformed 'Style:' line: '" + str + "'", e5);
            return null;
        }
    }

    private static boolean isValidAlignment(int i10) {
        switch (i10) {
            case 1:
            case 2:
            case 3:
            case 4:
            case 5:
            case 6:
            case 7:
            case 8:
            case 9:
                return true;
            default:
                return false;
        }
    }

    private static boolean isValidBorderStyle(int i10) {
        return i10 == 1 || i10 == 3;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static int parseAlignment(String str) {
        try {
            int i10 = Integer.parseInt(str.trim());
            if (isValidAlignment(i10)) {
                return i10;
            }
        } catch (NumberFormatException unused) {
        }
        a2.w("Ignoring unknown alignment: ", str, TAG);
        return -1;
    }

    private static boolean parseBooleanValue(String str) {
        try {
            int i10 = Integer.parseInt(str);
            return i10 == 1 || i10 == -1;
        } catch (NumberFormatException e5) {
            Log.w(TAG, "Failed to parse boolean value: '" + str + "'", e5);
            return false;
        }
    }

    private static int parseBorderStyle(String str) {
        try {
            int i10 = Integer.parseInt(str.trim());
            if (isValidBorderStyle(i10)) {
                return i10;
            }
        } catch (NumberFormatException unused) {
        }
        a2.w("Ignoring unknown BorderStyle: ", str, TAG);
        return -1;
    }

    public static Integer parseColor(String str) {
        try {
            long j10 = str.startsWith("&H") ? Long.parseLong(str.substring(2), 16) : Long.parseLong(str);
            b.j(j10 <= 4294967295L);
            return Integer.valueOf(Color.argb(f0.f(((j10 >> 24) & 255) ^ 255), f0.f(j10 & 255), f0.f((j10 >> 8) & 255), f0.f((j10 >> 16) & 255)));
        } catch (IllegalArgumentException e5) {
            Log.w(TAG, "Failed to parse color expression: '" + str + "'", e5);
            return null;
        }
    }

    private static float parseFontSize(String str) {
        try {
            return Float.parseFloat(str);
        } catch (NumberFormatException e5) {
            Log.w(TAG, "Failed to parse font size: '" + str + "'", e5);
            return -3.4028235E38f;
        }
    }
}
