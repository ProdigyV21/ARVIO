package androidx.compose.ui.graphics;

import androidx.compose.ui.graphics.colorspace.ColorSpace;
import androidx.compose.ui.graphics.colorspace.ColorSpaceKt;
import androidx.compose.ui.graphics.colorspace.ColorSpaces;
import androidx.compose.ui.graphics.colorspace.Rgb;
import androidx.media3.exoplayer.upstream.CmcdData;
import kotlin.Metadata;
import kotlin.jvm.internal.p;
import m2.f0;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u00008\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0007\n\u0002\b\u0013\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0002\b\u0013\b\u0087@\u0018\u0000 :2\u00020\u0001:\u0001:B\u000f\u0012\u0006\u0010\u0003\u001a\u00020\u0002¢\u0006\u0004\b\u0004\u0010\u0005J\u001b\u0010\n\u001a\u00020\u00002\u0006\u0010\u0007\u001a\u00020\u0006ø\u0001\u0000ø\u0001\u0001¢\u0006\u0004\b\b\u0010\tJ\u0010\u0010\u000e\u001a\u00020\u000bH\u0087\u0002¢\u0006\u0004\b\f\u0010\rJ\u0010\u0010\u0010\u001a\u00020\u000bH\u0087\u0002¢\u0006\u0004\b\u000f\u0010\rJ\u0010\u0010\u0012\u001a\u00020\u000bH\u0087\u0002¢\u0006\u0004\b\u0011\u0010\rJ\u0010\u0010\u0014\u001a\u00020\u000bH\u0087\u0002¢\u0006\u0004\b\u0013\u0010\rJ\u0010\u0010\u0017\u001a\u00020\u0006H\u0087\u0002¢\u0006\u0004\b\u0015\u0010\u0016J=\u0010\u001e\u001a\u00020\u00002\b\b\u0002\u0010\u0018\u001a\u00020\u000b2\b\b\u0002\u0010\u0019\u001a\u00020\u000b2\b\b\u0002\u0010\u001a\u001a\u00020\u000b2\b\b\u0002\u0010\u001b\u001a\u00020\u000bH\u0007ø\u0001\u0000ø\u0001\u0001¢\u0006\u0004\b\u001c\u0010\u001dJ\u000f\u0010\"\u001a\u00020\u001fH\u0016¢\u0006\u0004\b \u0010!J\u0010\u0010&\u001a\u00020#HÖ\u0001¢\u0006\u0004\b$\u0010%J\u001a\u0010+\u001a\u00020(2\b\u0010'\u001a\u0004\u0018\u00010\u0001HÖ\u0003¢\u0006\u0004\b)\u0010*R\u001d\u0010\u0003\u001a\u00020\u00028\u0006ø\u0001\u0001ø\u0001\u0000¢\u0006\f\n\u0004\b\u0003\u0010,\u001a\u0004\b-\u0010.R\u001a\u0010\u0007\u001a\u00020\u00068FX\u0087\u0004¢\u0006\f\u0012\u0004\b0\u00101\u001a\u0004\b/\u0010\u0016R\u001a\u0010\u0019\u001a\u00020\u000b8FX\u0087\u0004¢\u0006\f\u0012\u0004\b3\u00101\u001a\u0004\b2\u0010\rR\u001a\u0010\u001a\u001a\u00020\u000b8FX\u0087\u0004¢\u0006\f\u0012\u0004\b5\u00101\u001a\u0004\b4\u0010\rR\u001a\u0010\u001b\u001a\u00020\u000b8FX\u0087\u0004¢\u0006\f\u0012\u0004\b7\u00101\u001a\u0004\b6\u0010\rR\u001a\u0010\u0018\u001a\u00020\u000b8FX\u0087\u0004¢\u0006\f\u0012\u0004\b9\u00101\u001a\u0004\b8\u0010\r\u0088\u0001\u0003\u0092\u0001\u00020\u0002\u0082\u0002\u000b\n\u0002\b!\n\u0005\b¡\u001e0\u0001¨\u0006;"}, d2 = {"Landroidx/compose/ui/graphics/Color;", "", "Lx6/o0;", "value", "constructor-impl", "(J)J", "Landroidx/compose/ui/graphics/colorspace/ColorSpace;", "colorSpace", "convert-vNxB06k", "(JLandroidx/compose/ui/graphics/colorspace/ColorSpace;)J", "convert", "", "component1-impl", "(J)F", "component1", "component2-impl", "component2", "component3-impl", "component3", "component4-impl", "component4", "component5-impl", "(J)Landroidx/compose/ui/graphics/colorspace/ColorSpace;", "component5", "alpha", "red", "green", "blue", "copy-wmQWz5c", "(JFFFF)J", "copy", "", "toString-impl", "(J)Ljava/lang/String;", "toString", "", "hashCode-impl", "(J)I", "hashCode", "other", "", "equals-impl", "(JLjava/lang/Object;)Z", "equals", "J", "getValue-s-VKNKU", "()J", "getColorSpace-impl", "getColorSpace$annotations", "()V", "getRed-impl", "getRed$annotations", "getGreen-impl", "getGreen$annotations", "getBlue-impl", "getBlue$annotations", "getAlpha-impl", "getAlpha$annotations", "Companion", "ui-graphics_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
@q7.g
public final class Color {
    private final long value;

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private static final long Black = ColorKt.Color(4278190080L);
    private static final long DarkGray = ColorKt.Color(4282664004L);
    private static final long Gray = ColorKt.Color(4287137928L);
    private static final long LightGray = ColorKt.Color(4291611852L);
    private static final long White = ColorKt.Color(4294967295L);
    private static final long Red = ColorKt.Color(4294901760L);
    private static final long Green = ColorKt.Color(4278255360L);
    private static final long Blue = ColorKt.Color(4278190335L);
    private static final long Yellow = ColorKt.Color(4294967040L);
    private static final long Cyan = ColorKt.Color(4278255615L);
    private static final long Magenta = ColorKt.Color(4294902015L);
    private static final long Transparent = ColorKt.Color(0);
    private static final long Unspecified = ColorKt.Color(0.0f, 0.0f, 0.0f, 0.0f, ColorSpaces.INSTANCE.getUnspecified$ui_graphics_release());

    @Metadata(d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b*\n\u0002\u0010\u0007\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\b\n\u0002\b\t\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J?\u0010-\u001a\u00020\u00042\u0006\u0010.\u001a\u00020/2\u0006\u00100\u001a\u00020/2\u0006\u00101\u001a\u00020/2\b\b\u0002\u00102\u001a\u00020/2\b\b\u0002\u00103\u001a\u000204ø\u0001\u0001ø\u0001\u0000¢\u0006\u0004\b5\u00106J(\u00107\u001a\u00020/2\u0006\u00108\u001a\u0002092\u0006\u0010:\u001a\u00020/2\u0006\u0010;\u001a\u00020/2\u0006\u0010<\u001a\u00020/H\u0002J?\u0010=\u001a\u00020\u00042\u0006\u0010.\u001a\u00020/2\u0006\u00100\u001a\u00020/2\u0006\u0010>\u001a\u00020/2\b\b\u0002\u00102\u001a\u00020/2\b\b\u0002\u00103\u001a\u000204ø\u0001\u0001ø\u0001\u0000¢\u0006\u0004\b?\u00106J(\u0010@\u001a\u00020/2\u0006\u00108\u001a\u0002092\u0006\u0010:\u001a\u00020/2\u0006\u0010;\u001a\u00020/2\u0006\u0010A\u001a\u00020/H\u0002R$\u0010\u0003\u001a\u00020\u00048\u0006X\u0087\u0004ø\u0001\u0000ø\u0001\u0001¢\u0006\u0010\n\u0002\u0010\b\u0012\u0004\b\u0005\u0010\u0002\u001a\u0004\b\u0006\u0010\u0007R$\u0010\t\u001a\u00020\u00048\u0006X\u0087\u0004ø\u0001\u0000ø\u0001\u0001¢\u0006\u0010\n\u0002\u0010\b\u0012\u0004\b\n\u0010\u0002\u001a\u0004\b\u000b\u0010\u0007R$\u0010\f\u001a\u00020\u00048\u0006X\u0087\u0004ø\u0001\u0000ø\u0001\u0001¢\u0006\u0010\n\u0002\u0010\b\u0012\u0004\b\r\u0010\u0002\u001a\u0004\b\u000e\u0010\u0007R$\u0010\u000f\u001a\u00020\u00048\u0006X\u0087\u0004ø\u0001\u0000ø\u0001\u0001¢\u0006\u0010\n\u0002\u0010\b\u0012\u0004\b\u0010\u0010\u0002\u001a\u0004\b\u0011\u0010\u0007R$\u0010\u0012\u001a\u00020\u00048\u0006X\u0087\u0004ø\u0001\u0000ø\u0001\u0001¢\u0006\u0010\n\u0002\u0010\b\u0012\u0004\b\u0013\u0010\u0002\u001a\u0004\b\u0014\u0010\u0007R$\u0010\u0015\u001a\u00020\u00048\u0006X\u0087\u0004ø\u0001\u0000ø\u0001\u0001¢\u0006\u0010\n\u0002\u0010\b\u0012\u0004\b\u0016\u0010\u0002\u001a\u0004\b\u0017\u0010\u0007R$\u0010\u0018\u001a\u00020\u00048\u0006X\u0087\u0004ø\u0001\u0000ø\u0001\u0001¢\u0006\u0010\n\u0002\u0010\b\u0012\u0004\b\u0019\u0010\u0002\u001a\u0004\b\u001a\u0010\u0007R$\u0010\u001b\u001a\u00020\u00048\u0006X\u0087\u0004ø\u0001\u0000ø\u0001\u0001¢\u0006\u0010\n\u0002\u0010\b\u0012\u0004\b\u001c\u0010\u0002\u001a\u0004\b\u001d\u0010\u0007R$\u0010\u001e\u001a\u00020\u00048\u0006X\u0087\u0004ø\u0001\u0000ø\u0001\u0001¢\u0006\u0010\n\u0002\u0010\b\u0012\u0004\b\u001f\u0010\u0002\u001a\u0004\b \u0010\u0007R$\u0010!\u001a\u00020\u00048\u0006X\u0087\u0004ø\u0001\u0000ø\u0001\u0001¢\u0006\u0010\n\u0002\u0010\b\u0012\u0004\b\"\u0010\u0002\u001a\u0004\b#\u0010\u0007R$\u0010$\u001a\u00020\u00048\u0006X\u0087\u0004ø\u0001\u0000ø\u0001\u0001¢\u0006\u0010\n\u0002\u0010\b\u0012\u0004\b%\u0010\u0002\u001a\u0004\b&\u0010\u0007R$\u0010'\u001a\u00020\u00048\u0006X\u0087\u0004ø\u0001\u0000ø\u0001\u0001¢\u0006\u0010\n\u0002\u0010\b\u0012\u0004\b(\u0010\u0002\u001a\u0004\b)\u0010\u0007R$\u0010*\u001a\u00020\u00048\u0006X\u0087\u0004ø\u0001\u0000ø\u0001\u0001¢\u0006\u0010\n\u0002\u0010\b\u0012\u0004\b+\u0010\u0002\u001a\u0004\b,\u0010\u0007\u0082\u0002\u000b\n\u0005\b¡\u001e0\u0001\n\u0002\b!¨\u0006B"}, d2 = {"Landroidx/compose/ui/graphics/Color$Companion;", "", "()V", "Black", "Landroidx/compose/ui/graphics/Color;", "getBlack-0d7_KjU$annotations", "getBlack-0d7_KjU", "()J", "J", "Blue", "getBlue-0d7_KjU$annotations", "getBlue-0d7_KjU", "Cyan", "getCyan-0d7_KjU$annotations", "getCyan-0d7_KjU", "DarkGray", "getDarkGray-0d7_KjU$annotations", "getDarkGray-0d7_KjU", "Gray", "getGray-0d7_KjU$annotations", "getGray-0d7_KjU", "Green", "getGreen-0d7_KjU$annotations", "getGreen-0d7_KjU", "LightGray", "getLightGray-0d7_KjU$annotations", "getLightGray-0d7_KjU", "Magenta", "getMagenta-0d7_KjU$annotations", "getMagenta-0d7_KjU", "Red", "getRed-0d7_KjU$annotations", "getRed-0d7_KjU", "Transparent", "getTransparent-0d7_KjU$annotations", "getTransparent-0d7_KjU", "Unspecified", "getUnspecified-0d7_KjU$annotations", "getUnspecified-0d7_KjU", "White", "getWhite-0d7_KjU$annotations", "getWhite-0d7_KjU", "Yellow", "getYellow-0d7_KjU$annotations", "getYellow-0d7_KjU", "hsl", "hue", "", "saturation", "lightness", "alpha", "colorSpace", "Landroidx/compose/ui/graphics/colorspace/Rgb;", "hsl-JlNiLsg", "(FFFFLandroidx/compose/ui/graphics/colorspace/Rgb;)J", "hslToRgbComponent", "n", "", CmcdData.STREAMING_FORMAT_HLS, CmcdData.STREAMING_FORMAT_SS, CmcdData.STREAM_TYPE_LIVE, "hsv", "value", "hsv-JlNiLsg", "hsvToRgbComponent", "v", "ui-graphics_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(kotlin.jvm.internal.h hVar) {
            this();
        }

        /* JADX INFO: renamed from: getBlack-0d7_KjU$annotations, reason: not valid java name */
        public static /* synthetic */ void m3483getBlack0d7_KjU$annotations() {
        }

        /* JADX INFO: renamed from: getBlue-0d7_KjU$annotations, reason: not valid java name */
        public static /* synthetic */ void m3484getBlue0d7_KjU$annotations() {
        }

        /* JADX INFO: renamed from: getCyan-0d7_KjU$annotations, reason: not valid java name */
        public static /* synthetic */ void m3485getCyan0d7_KjU$annotations() {
        }

        /* JADX INFO: renamed from: getDarkGray-0d7_KjU$annotations, reason: not valid java name */
        public static /* synthetic */ void m3486getDarkGray0d7_KjU$annotations() {
        }

        /* JADX INFO: renamed from: getGray-0d7_KjU$annotations, reason: not valid java name */
        public static /* synthetic */ void m3487getGray0d7_KjU$annotations() {
        }

        /* JADX INFO: renamed from: getGreen-0d7_KjU$annotations, reason: not valid java name */
        public static /* synthetic */ void m3488getGreen0d7_KjU$annotations() {
        }

        /* JADX INFO: renamed from: getLightGray-0d7_KjU$annotations, reason: not valid java name */
        public static /* synthetic */ void m3489getLightGray0d7_KjU$annotations() {
        }

        /* JADX INFO: renamed from: getMagenta-0d7_KjU$annotations, reason: not valid java name */
        public static /* synthetic */ void m3490getMagenta0d7_KjU$annotations() {
        }

        /* JADX INFO: renamed from: getRed-0d7_KjU$annotations, reason: not valid java name */
        public static /* synthetic */ void m3491getRed0d7_KjU$annotations() {
        }

        /* JADX INFO: renamed from: getTransparent-0d7_KjU$annotations, reason: not valid java name */
        public static /* synthetic */ void m3492getTransparent0d7_KjU$annotations() {
        }

        /* JADX INFO: renamed from: getUnspecified-0d7_KjU$annotations, reason: not valid java name */
        public static /* synthetic */ void m3493getUnspecified0d7_KjU$annotations() {
        }

        /* JADX INFO: renamed from: getWhite-0d7_KjU$annotations, reason: not valid java name */
        public static /* synthetic */ void m3494getWhite0d7_KjU$annotations() {
        }

        /* JADX INFO: renamed from: getYellow-0d7_KjU$annotations, reason: not valid java name */
        public static /* synthetic */ void m3495getYellow0d7_KjU$annotations() {
        }

        /* JADX INFO: renamed from: hsl-JlNiLsg$default, reason: not valid java name */
        public static /* synthetic */ long m3496hslJlNiLsg$default(Companion companion, float f10, float f11, float f12, float f13, Rgb rgb, int i10, Object obj) {
            if ((i10 & 8) != 0) {
                f13 = 1.0f;
            }
            float f14 = f13;
            if ((i10 & 16) != 0) {
                rgb = ColorSpaces.INSTANCE.getSrgb();
            }
            return companion.m3511hslJlNiLsg(f10, f11, f12, f14, rgb);
        }

        private final float hslToRgbComponent(int n6, float h3, float s10, float l10) {
            float f10 = ((h3 / 30.0f) + n6) % 12.0f;
            return l10 - (Math.max(-1.0f, Math.min(f10 - 3, Math.min(9 - f10, 1.0f))) * (Math.min(l10, 1.0f - l10) * s10));
        }

        /* JADX INFO: renamed from: hsv-JlNiLsg$default, reason: not valid java name */
        public static /* synthetic */ long m3497hsvJlNiLsg$default(Companion companion, float f10, float f11, float f12, float f13, Rgb rgb, int i10, Object obj) {
            if ((i10 & 8) != 0) {
                f13 = 1.0f;
            }
            float f14 = f13;
            if ((i10 & 16) != 0) {
                rgb = ColorSpaces.INSTANCE.getSrgb();
            }
            return companion.m3512hsvJlNiLsg(f10, f11, f12, f14, rgb);
        }

        private final float hsvToRgbComponent(int n6, float h3, float s10, float v2) {
            float f10 = ((h3 / 60.0f) + n6) % 6.0f;
            return v2 - (Math.max(0.0f, Math.min(f10, Math.min(4 - f10, 1.0f))) * (s10 * v2));
        }

        /* JADX INFO: renamed from: getBlack-0d7_KjU, reason: not valid java name */
        public final long m3498getBlack0d7_KjU() {
            return Color.Black;
        }

        /* JADX INFO: renamed from: getBlue-0d7_KjU, reason: not valid java name */
        public final long m3499getBlue0d7_KjU() {
            return Color.Blue;
        }

        /* JADX INFO: renamed from: getCyan-0d7_KjU, reason: not valid java name */
        public final long m3500getCyan0d7_KjU() {
            return Color.Cyan;
        }

        /* JADX INFO: renamed from: getDarkGray-0d7_KjU, reason: not valid java name */
        public final long m3501getDarkGray0d7_KjU() {
            return Color.DarkGray;
        }

        /* JADX INFO: renamed from: getGray-0d7_KjU, reason: not valid java name */
        public final long m3502getGray0d7_KjU() {
            return Color.Gray;
        }

        /* JADX INFO: renamed from: getGreen-0d7_KjU, reason: not valid java name */
        public final long m3503getGreen0d7_KjU() {
            return Color.Green;
        }

        /* JADX INFO: renamed from: getLightGray-0d7_KjU, reason: not valid java name */
        public final long m3504getLightGray0d7_KjU() {
            return Color.LightGray;
        }

        /* JADX INFO: renamed from: getMagenta-0d7_KjU, reason: not valid java name */
        public final long m3505getMagenta0d7_KjU() {
            return Color.Magenta;
        }

        /* JADX INFO: renamed from: getRed-0d7_KjU, reason: not valid java name */
        public final long m3506getRed0d7_KjU() {
            return Color.Red;
        }

        /* JADX INFO: renamed from: getTransparent-0d7_KjU, reason: not valid java name */
        public final long m3507getTransparent0d7_KjU() {
            return Color.Transparent;
        }

        /* JADX INFO: renamed from: getUnspecified-0d7_KjU, reason: not valid java name */
        public final long m3508getUnspecified0d7_KjU() {
            return Color.Unspecified;
        }

        /* JADX INFO: renamed from: getWhite-0d7_KjU, reason: not valid java name */
        public final long m3509getWhite0d7_KjU() {
            return Color.White;
        }

        /* JADX INFO: renamed from: getYellow-0d7_KjU, reason: not valid java name */
        public final long m3510getYellow0d7_KjU() {
            return Color.Yellow;
        }

        /* JADX INFO: renamed from: hsl-JlNiLsg, reason: not valid java name */
        public final long m3511hslJlNiLsg(float hue, float saturation, float lightness, float alpha, Rgb colorSpace) {
            if (0.0f <= hue && hue <= 360.0f && 0.0f <= saturation && saturation <= 1.0f && 0.0f <= lightness && lightness <= 1.0f) {
                return ColorKt.Color(hslToRgbComponent(0, hue, saturation, lightness), hslToRgbComponent(8, hue, saturation, lightness), hslToRgbComponent(4, hue, saturation, lightness), alpha, colorSpace);
            }
            throw new IllegalArgumentException(("HSL (" + hue + ", " + saturation + ", " + lightness + ") must be in range (0..360, 0..1, 0..1)").toString());
        }

        /* JADX INFO: renamed from: hsv-JlNiLsg, reason: not valid java name */
        public final long m3512hsvJlNiLsg(float hue, float saturation, float value, float alpha, Rgb colorSpace) {
            if (0.0f <= hue && hue <= 360.0f && 0.0f <= saturation && saturation <= 1.0f && 0.0f <= value && value <= 1.0f) {
                return ColorKt.Color(hsvToRgbComponent(5, hue, saturation, value), hsvToRgbComponent(3, hue, saturation, value), hsvToRgbComponent(1, hue, saturation, value), alpha, colorSpace);
            }
            throw new IllegalArgumentException(("HSV (" + hue + ", " + saturation + ", " + value + ") must be in range (0..360, 0..1, 0..1)").toString());
        }

        private Companion() {
        }
    }

    private /* synthetic */ Color(long j10) {
        this.value = j10;
    }

    /* JADX INFO: renamed from: box-impl, reason: not valid java name */
    public static final /* synthetic */ Color m3462boximpl(long j10) {
        return new Color(j10);
    }

    /* JADX INFO: renamed from: component1-impl, reason: not valid java name */
    public static final float m3463component1impl(long j10) {
        return m3478getRedimpl(j10);
    }

    /* JADX INFO: renamed from: component2-impl, reason: not valid java name */
    public static final float m3464component2impl(long j10) {
        return m3477getGreenimpl(j10);
    }

    /* JADX INFO: renamed from: component3-impl, reason: not valid java name */
    public static final float m3465component3impl(long j10) {
        return m3475getBlueimpl(j10);
    }

    /* JADX INFO: renamed from: component4-impl, reason: not valid java name */
    public static final float m3466component4impl(long j10) {
        return m3474getAlphaimpl(j10);
    }

    /* JADX INFO: renamed from: component5-impl, reason: not valid java name */
    public static final ColorSpace m3467component5impl(long j10) {
        return m3476getColorSpaceimpl(j10);
    }

    /* JADX INFO: renamed from: constructor-impl, reason: not valid java name */
    public static long m3468constructorimpl(long j10) {
        return j10;
    }

    /* JADX INFO: renamed from: convert-vNxB06k, reason: not valid java name */
    public static final long m3469convertvNxB06k(long j10, ColorSpace colorSpace) {
        ColorSpace colorSpaceM3476getColorSpaceimpl = m3476getColorSpaceimpl(j10);
        return p.a(colorSpace, colorSpaceM3476getColorSpaceimpl) ? j10 : ColorSpaceKt.m3869connectYBCOT_4$default(colorSpaceM3476getColorSpaceimpl, colorSpace, 0, 2, null).mo3871transformToColorwmQWz5c$ui_graphics_release(m3478getRedimpl(j10), m3477getGreenimpl(j10), m3475getBlueimpl(j10), m3474getAlphaimpl(j10));
    }

    /* JADX INFO: renamed from: copy-wmQWz5c, reason: not valid java name */
    public static final long m3470copywmQWz5c(long j10, float f10, float f11, float f12, float f13) {
        return ColorKt.Color(f11, f12, f13, f10, m3476getColorSpaceimpl(j10));
    }

    /* JADX INFO: renamed from: copy-wmQWz5c$default, reason: not valid java name */
    public static /* synthetic */ long m3471copywmQWz5c$default(long j10, float f10, float f11, float f12, float f13, int i10, Object obj) {
        if ((i10 & 1) != 0) {
            f10 = m3474getAlphaimpl(j10);
        }
        float f14 = f10;
        if ((i10 & 2) != 0) {
            f11 = m3478getRedimpl(j10);
        }
        float f15 = f11;
        if ((i10 & 4) != 0) {
            f12 = m3477getGreenimpl(j10);
        }
        float f16 = f12;
        if ((i10 & 8) != 0) {
            f13 = m3475getBlueimpl(j10);
        }
        return m3470copywmQWz5c(j10, f14, f15, f16, f13);
    }

    /* JADX INFO: renamed from: equals-impl, reason: not valid java name */
    public static boolean m3472equalsimpl(long j10, Object obj) {
        return (obj instanceof Color) && j10 == ((Color) obj).m3482unboximpl();
    }

    /* JADX INFO: renamed from: equals-impl0, reason: not valid java name */
    public static final boolean m3473equalsimpl0(long j10, long j11) {
        return j10 == j11;
    }

    public static /* synthetic */ void getAlpha$annotations() {
    }

    /* JADX INFO: renamed from: getAlpha-impl, reason: not valid java name */
    public static final float m3474getAlphaimpl(long j10) {
        float fU;
        float f10;
        if ((63 & j10) == 0) {
            fU = (float) f0.U((j10 >>> 56) & 255);
            f10 = 255.0f;
        } else {
            fU = (float) f0.U((j10 >>> 6) & 1023);
            f10 = 1023.0f;
        }
        return fU / f10;
    }

    public static /* synthetic */ void getBlue$annotations() {
    }

    /* JADX INFO: renamed from: getBlue-impl, reason: not valid java name */
    public static final float m3475getBlueimpl(long j10) {
        return (63 & j10) == 0 ? ((float) f0.U((j10 >>> 32) & 255)) / 255.0f : Float16.m3597toFloatimpl(Float16.m3581constructorimpl((short) ((j10 >>> 16) & 65535)));
    }

    public static /* synthetic */ void getColorSpace$annotations() {
    }

    /* JADX INFO: renamed from: getColorSpace-impl, reason: not valid java name */
    public static final ColorSpace m3476getColorSpaceimpl(long j10) {
        return ColorSpaces.INSTANCE.getColorSpacesArray$ui_graphics_release()[(int) (j10 & 63)];
    }

    public static /* synthetic */ void getGreen$annotations() {
    }

    /* JADX INFO: renamed from: getGreen-impl, reason: not valid java name */
    public static final float m3477getGreenimpl(long j10) {
        return (63 & j10) == 0 ? ((float) f0.U((j10 >>> 40) & 255)) / 255.0f : Float16.m3597toFloatimpl(Float16.m3581constructorimpl((short) ((j10 >>> 32) & 65535)));
    }

    public static /* synthetic */ void getRed$annotations() {
    }

    /* JADX INFO: renamed from: getRed-impl, reason: not valid java name */
    public static final float m3478getRedimpl(long j10) {
        return (63 & j10) == 0 ? ((float) f0.U((j10 >>> 48) & 255)) / 255.0f : Float16.m3597toFloatimpl(Float16.m3581constructorimpl((short) ((j10 >>> 48) & 65535)));
    }

    /* JADX INFO: renamed from: hashCode-impl, reason: not valid java name */
    public static int m3479hashCodeimpl(long j10) {
        return (int) (j10 ^ (j10 >>> 32));
    }

    /* JADX INFO: renamed from: toString-impl, reason: not valid java name */
    public static String m3480toStringimpl(long j10) {
        return "Color(" + m3478getRedimpl(j10) + ", " + m3477getGreenimpl(j10) + ", " + m3475getBlueimpl(j10) + ", " + m3474getAlphaimpl(j10) + ", " + m3476getColorSpaceimpl(j10).getName() + ')';
    }

    public boolean equals(Object obj) {
        return m3472equalsimpl(this.value, obj);
    }

    /* JADX INFO: renamed from: getValue-s-VKNKU, reason: not valid java name and from getter */
    public final long getValue() {
        return this.value;
    }

    public int hashCode() {
        return m3479hashCodeimpl(this.value);
    }

    public String toString() {
        return m3480toStringimpl(this.value);
    }

    /* JADX INFO: renamed from: unbox-impl, reason: not valid java name */
    public final /* synthetic */ long m3482unboximpl() {
        return this.value;
    }
}
