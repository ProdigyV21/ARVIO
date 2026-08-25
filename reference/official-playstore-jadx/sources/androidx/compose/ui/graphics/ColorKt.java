package androidx.compose.ui.graphics;

import androidx.compose.ui.graphics.colorspace.ColorModel;
import androidx.compose.ui.graphics.colorspace.ColorSpace;
import androidx.compose.ui.graphics.colorspace.ColorSpaces;
import androidx.compose.ui.graphics.colorspace.DoubleFunction;
import androidx.compose.ui.graphics.colorspace.Rgb;
import androidx.compose.ui.util.MathHelpersKt;
import androidx.media3.exoplayer.upstream.CmcdData;
import androidx.media3.extractor.text.ttml.TtmlNode;
import kotlin.Metadata;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000>\n\u0002\u0010\u0007\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\t\n\u0002\b\u0013\n\u0002\u0010\u0014\n\u0002\b\f\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0002\b\t\u001a;\u0010\b\u001a\u00020\u00072\u0006\u0010\u0001\u001a\u00020\u00002\u0006\u0010\u0002\u001a\u00020\u00002\u0006\u0010\u0003\u001a\u00020\u00002\b\b\u0002\u0010\u0004\u001a\u00020\u00002\b\b\u0002\u0010\u0006\u001a\u00020\u0005H\u0007¢\u0006\u0004\b\b\u0010\t\u001a\u0019\u0010\b\u001a\u00020\u00072\b\b\u0001\u0010\u000b\u001a\u00020\nH\u0007¢\u0006\u0004\b\b\u0010\f\u001a\u0017\u0010\b\u001a\u00020\u00072\u0006\u0010\u000b\u001a\u00020\rH\u0007¢\u0006\u0004\b\b\u0010\u000e\u001a7\u0010\b\u001a\u00020\u00072\b\b\u0001\u0010\u0001\u001a\u00020\n2\b\b\u0001\u0010\u0002\u001a\u00020\n2\b\b\u0001\u0010\u0003\u001a\u00020\n2\b\b\u0003\u0010\u0004\u001a\u00020\nH\u0007¢\u0006\u0004\b\b\u0010\u000f\u001a,\u0010\u0015\u001a\u00020\u00072\u0006\u0010\u0010\u001a\u00020\u00072\u0006\u0010\u0011\u001a\u00020\u00072\b\b\u0001\u0010\u0012\u001a\u00020\u0000H\u0007ø\u0001\u0000¢\u0006\u0004\b\u0013\u0010\u0014\u001a\u001e\u0010\u0019\u001a\u00020\u0007*\u00020\u00072\u0006\u0010\u0016\u001a\u00020\u0007H\u0007ø\u0001\u0000¢\u0006\u0004\b\u0017\u0010\u0018\u001a8\u0010\u001f\u001a\u00020\u00002\u0006\u0010\u001a\u001a\u00020\u00002\u0006\u0010\u001b\u001a\u00020\u00002\u0006\u0010\u001c\u001a\u00020\u00002\u0006\u0010\u001d\u001a\u00020\u00002\u0006\u0010\u001e\u001a\u00020\u0000H\u0082\b¢\u0006\u0004\b\u001f\u0010 \u001a\u0016\u0010$\u001a\u00020!*\u00020\u0007H\u0003ø\u0001\u0000¢\u0006\u0004\b\"\u0010#\u001a\u0016\u0010'\u001a\u00020\u0000*\u00020\u0007H\u0007ø\u0001\u0000¢\u0006\u0004\b%\u0010&\u001a\u0017\u0010)\u001a\u00020\u00002\u0006\u0010(\u001a\u00020\u0000H\u0002¢\u0006\u0004\b)\u0010*\u001a\u0016\u0010-\u001a\u00020\n*\u00020\u0007H\u0007ø\u0001\u0000¢\u0006\u0004\b+\u0010,\u001a%\u00102\u001a\u00020\u0007*\u00020\u00072\f\u0010/\u001a\b\u0012\u0004\u0012\u00020\u00070.H\u0086\bø\u0001\u0000¢\u0006\u0004\b0\u00101\"\u001f\u00108\u001a\u000203*\u00020\u00078Æ\u0002X\u0087\u0004¢\u0006\f\u0012\u0004\b6\u00107\u001a\u0004\b4\u00105\"\u001f\u0010;\u001a\u000203*\u00020\u00078Æ\u0002X\u0087\u0004¢\u0006\f\u0012\u0004\b:\u00107\u001a\u0004\b9\u00105\u0082\u0002\u0007\n\u0005\b¡\u001e0\u0001¨\u0006<"}, d2 = {"", "red", "green", "blue", "alpha", "Landroidx/compose/ui/graphics/colorspace/ColorSpace;", "colorSpace", "Landroidx/compose/ui/graphics/Color;", "Color", "(FFFFLandroidx/compose/ui/graphics/colorspace/ColorSpace;)J", "", TtmlNode.ATTR_TTS_COLOR, "(I)J", "", "(J)J", "(IIII)J", TtmlNode.START, "stop", "fraction", "lerp-jxsXWHM", "(JJF)J", "lerp", "background", "compositeOver--OWjLjI", "(JJ)J", "compositeOver", "fgC", "bgC", "fgA", "bgA", CmcdData.OBJECT_TYPE_AUDIO_ONLY, "compositeComponent", "(FFFFF)F", "", "getComponents-8_81llA", "(J)[F", "getComponents", "luminance-8_81llA", "(J)F", "luminance", "v", "saturate", "(F)F", "toArgb-8_81llA", "(J)I", "toArgb", "Lkotlin/Function0;", "block", "takeOrElse-DxMtmZc", "(JLr7/a;)J", "takeOrElse", "", "isSpecified-8_81llA", "(J)Z", "isSpecified-8_81llA$annotations", "(J)V", "isSpecified", "isUnspecified-8_81llA", "isUnspecified-8_81llA$annotations", "isUnspecified", "ui-graphics_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class ColorKt {
    public static final long Color(float f10, float f11, float f12, float f13, ColorSpace colorSpace) {
        float minValue = colorSpace.getMinValue(0);
        if (f10 <= colorSpace.getMaxValue(0) && minValue <= f10) {
            float minValue2 = colorSpace.getMinValue(1);
            if (f11 <= colorSpace.getMaxValue(1) && minValue2 <= f11) {
                float minValue3 = colorSpace.getMinValue(2);
                if (f12 <= colorSpace.getMaxValue(2) && minValue3 <= f12 && 0.0f <= f13 && f13 <= 1.0f) {
                    if (colorSpace.getIsSrgb()) {
                        return Color.m3468constructorimpl((((long) ((((((int) ((f10 * 255.0f) + 0.5f)) << 16) | (((int) ((f13 * 255.0f) + 0.5f)) << 24)) | (((int) ((f11 * 255.0f) + 0.5f)) << 8)) | ((int) ((f12 * 255.0f) + 0.5f)))) & 4294967295L) << 32);
                    }
                    if (colorSpace.getComponentCount() != 3) {
                        throw new IllegalArgumentException("Color only works with ColorSpaces with 3 components");
                    }
                    int id$ui_graphics_release = colorSpace.getId();
                    if (id$ui_graphics_release == -1) {
                        throw new IllegalArgumentException("Unknown color space, please use a color space in ColorSpaces");
                    }
                    return Color.m3468constructorimpl(((((long) Float16.m3580constructorimpl(f11)) & 65535) << 32) | ((((long) Float16.m3580constructorimpl(f10)) & 65535) << 48) | ((((long) Float16.m3580constructorimpl(f12)) & 65535) << 16) | ((((long) ((int) ((Math.max(0.0f, Math.min(f13, 1.0f)) * 1023.0f) + 0.5f))) & 1023) << 6) | (((long) id$ui_graphics_release) & 63));
                }
            }
        }
        throw new IllegalArgumentException(("red = " + f10 + ", green = " + f11 + ", blue = " + f12 + ", alpha = " + f13 + " outside the range for " + colorSpace).toString());
    }

    public static /* synthetic */ long Color$default(float f10, float f11, float f12, float f13, ColorSpace colorSpace, int i10, Object obj) {
        if ((i10 & 8) != 0) {
            f13 = 1.0f;
        }
        if ((i10 & 16) != 0) {
            colorSpace = ColorSpaces.INSTANCE.getSrgb();
        }
        return Color(f10, f11, f12, f13, colorSpace);
    }

    private static final float compositeComponent(float f10, float f11, float f12, float f13, float f14) {
        if (f14 == 0.0f) {
            return 0.0f;
        }
        return (((1.0f - f12) * (f11 * f13)) + (f10 * f12)) / f14;
    }

    /* JADX INFO: renamed from: compositeOver--OWjLjI, reason: not valid java name */
    public static final long m3517compositeOverOWjLjI(long j10, long j11) {
        float f10;
        float f11;
        long jM3469convertvNxB06k = Color.m3469convertvNxB06k(j10, Color.m3476getColorSpaceimpl(j11));
        float fM3474getAlphaimpl = Color.m3474getAlphaimpl(j11);
        float fM3474getAlphaimpl2 = Color.m3474getAlphaimpl(jM3469convertvNxB06k);
        float f12 = 1.0f - fM3474getAlphaimpl2;
        float f13 = (fM3474getAlphaimpl * f12) + fM3474getAlphaimpl2;
        float fM3478getRedimpl = Color.m3478getRedimpl(jM3469convertvNxB06k);
        float fM3478getRedimpl2 = Color.m3478getRedimpl(j11);
        float f14 = 0.0f;
        if (f13 == 0.0f) {
            f10 = 0.0f;
        } else {
            f10 = (((fM3478getRedimpl2 * fM3474getAlphaimpl) * f12) + (fM3478getRedimpl * fM3474getAlphaimpl2)) / f13;
        }
        float fM3477getGreenimpl = Color.m3477getGreenimpl(jM3469convertvNxB06k);
        float fM3477getGreenimpl2 = Color.m3477getGreenimpl(j11);
        if (f13 == 0.0f) {
            f11 = 0.0f;
        } else {
            f11 = (((fM3477getGreenimpl2 * fM3474getAlphaimpl) * f12) + (fM3477getGreenimpl * fM3474getAlphaimpl2)) / f13;
        }
        float fM3475getBlueimpl = Color.m3475getBlueimpl(jM3469convertvNxB06k);
        float fM3475getBlueimpl2 = Color.m3475getBlueimpl(j11);
        if (f13 != 0.0f) {
            f14 = (((fM3475getBlueimpl2 * fM3474getAlphaimpl) * f12) + (fM3475getBlueimpl * fM3474getAlphaimpl2)) / f13;
        }
        return Color(f10, f11, f14, f13, Color.m3476getColorSpaceimpl(j11));
    }

    /* JADX INFO: renamed from: getComponents-8_81llA, reason: not valid java name */
    private static final float[] m3518getComponents8_81llA(long j10) {
        return new float[]{Color.m3478getRedimpl(j10), Color.m3477getGreenimpl(j10), Color.m3475getBlueimpl(j10), Color.m3474getAlphaimpl(j10)};
    }

    /* JADX INFO: renamed from: isSpecified-8_81llA, reason: not valid java name */
    public static final boolean m3519isSpecified8_81llA(long j10) {
        return j10 != Color.INSTANCE.m3508getUnspecified0d7_KjU();
    }

    /* JADX INFO: renamed from: isSpecified-8_81llA$annotations, reason: not valid java name */
    public static /* synthetic */ void m3520isSpecified8_81llA$annotations(long j10) {
    }

    /* JADX INFO: renamed from: isUnspecified-8_81llA, reason: not valid java name */
    public static final boolean m3521isUnspecified8_81llA(long j10) {
        return j10 == Color.INSTANCE.m3508getUnspecified0d7_KjU();
    }

    /* JADX INFO: renamed from: isUnspecified-8_81llA$annotations, reason: not valid java name */
    public static /* synthetic */ void m3522isUnspecified8_81llA$annotations(long j10) {
    }

    /* JADX INFO: renamed from: lerp-jxsXWHM, reason: not valid java name */
    public static final long m3523lerpjxsXWHM(long j10, long j11, float f10) {
        ColorSpace oklab = ColorSpaces.INSTANCE.getOklab();
        long jM3469convertvNxB06k = Color.m3469convertvNxB06k(j10, oklab);
        long jM3469convertvNxB06k2 = Color.m3469convertvNxB06k(j11, oklab);
        float fM3474getAlphaimpl = Color.m3474getAlphaimpl(jM3469convertvNxB06k);
        float fM3478getRedimpl = Color.m3478getRedimpl(jM3469convertvNxB06k);
        float fM3477getGreenimpl = Color.m3477getGreenimpl(jM3469convertvNxB06k);
        float fM3475getBlueimpl = Color.m3475getBlueimpl(jM3469convertvNxB06k);
        float fM3474getAlphaimpl2 = Color.m3474getAlphaimpl(jM3469convertvNxB06k2);
        float fM3478getRedimpl2 = Color.m3478getRedimpl(jM3469convertvNxB06k2);
        float fM3477getGreenimpl2 = Color.m3477getGreenimpl(jM3469convertvNxB06k2);
        float fM3475getBlueimpl2 = Color.m3475getBlueimpl(jM3469convertvNxB06k2);
        return Color.m3469convertvNxB06k(Color(MathHelpersKt.lerp(fM3478getRedimpl, fM3478getRedimpl2, f10), MathHelpersKt.lerp(fM3477getGreenimpl, fM3477getGreenimpl2, f10), MathHelpersKt.lerp(fM3475getBlueimpl, fM3475getBlueimpl2, f10), MathHelpersKt.lerp(fM3474getAlphaimpl, fM3474getAlphaimpl2, f10), oklab), Color.m3476getColorSpaceimpl(j11));
    }

    /* JADX INFO: renamed from: luminance-8_81llA, reason: not valid java name */
    public static final float m3524luminance8_81llA(long j10) {
        ColorSpace colorSpaceM3476getColorSpaceimpl = Color.m3476getColorSpaceimpl(j10);
        if (!ColorModel.m3857equalsimpl0(colorSpaceM3476getColorSpaceimpl.getModel(), ColorModel.INSTANCE.m3864getRgbxdoWZVw())) {
            throw new IllegalArgumentException(("The specified color must be encoded in an RGB color space. The supplied color space is " + ((Object) ColorModel.m3860toStringimpl(colorSpaceM3476getColorSpaceimpl.getModel()))).toString());
        }
        DoubleFunction eotfFunc = ((Rgb) colorSpaceM3476getColorSpaceimpl).getEotfFunc();
        double dInvoke = eotfFunc.invoke(Color.m3478getRedimpl(j10));
        return saturate((float) ((eotfFunc.invoke(Color.m3475getBlueimpl(j10)) * 0.0722d) + (eotfFunc.invoke(Color.m3477getGreenimpl(j10)) * 0.7152d) + (dInvoke * 0.2126d)));
    }

    private static final float saturate(float f10) {
        if (f10 <= 0.0f) {
            return 0.0f;
        }
        if (f10 >= 1.0f) {
            return 1.0f;
        }
        return f10;
    }

    /* JADX INFO: renamed from: takeOrElse-DxMtmZc, reason: not valid java name */
    public static final long m3525takeOrElseDxMtmZc(long j10, r7.a<Color> aVar) {
        return j10 != Color.INSTANCE.m3508getUnspecified0d7_KjU() ? j10 : ((Color) aVar.invoke()).m3482unboximpl();
    }

    /* JADX INFO: renamed from: toArgb-8_81llA, reason: not valid java name */
    public static final int m3526toArgb8_81llA(long j10) {
        return (int) (Color.m3469convertvNxB06k(j10, ColorSpaces.INSTANCE.getSrgb()) >>> 32);
    }

    public static /* synthetic */ long Color$default(int i10, int i11, int i12, int i13, int i14, Object obj) {
        if ((i14 & 8) != 0) {
            i13 = 255;
        }
        return Color(i10, i11, i12, i13);
    }

    public static final long Color(int i10) {
        return Color.m3468constructorimpl(((long) i10) << 32);
    }

    public static final long Color(long j10) {
        return Color.m3468constructorimpl((j10 & 4294967295L) << 32);
    }

    public static final long Color(int i10, int i11, int i12, int i13) {
        return Color(((i10 & 255) << 16) | ((i13 & 255) << 24) | ((i11 & 255) << 8) | (i12 & 255));
    }
}
