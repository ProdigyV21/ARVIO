package androidx.compose.ui.graphics.colorspace;

import androidx.media3.extractor.text.ttml.TtmlNode;
import kotlin.Metadata;
import kotlin.jvm.internal.h;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000<\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u000b\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0011\n\u0002\b\u000e\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0014\n\u0002\b\u0013\n\u0002\u0010\b\n\u0002\b\u0005\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0016\u0010;\u001a\u00020\u00102\u0006\u0010<\u001a\u00020=H\u0080\b¢\u0006\u0002\b>J\u001a\u0010?\u001a\u0004\u0018\u00010\u00102\b\b\u0001\u0010@\u001a\u00020)2\u0006\u0010A\u001a\u00020%R\u0011\u0010\u0003\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006R\u0011\u0010\u0007\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\u0006R\u0011\u0010\t\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u0006R\u0011\u0010\u000b\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\u0006R\u0011\u0010\r\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u0006R\u0011\u0010\u000f\u001a\u00020\u0010¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u0012R\u0011\u0010\u0013\u001a\u00020\u0010¢\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\u0012R\u001c\u0010\u0015\u001a\b\u0012\u0004\u0012\u00020\u00100\u0016X\u0080\u0004¢\u0006\n\n\u0002\u0010\u0019\u001a\u0004\b\u0017\u0010\u0018R\u0011\u0010\u001a\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u001b\u0010\u0006R\u0011\u0010\u001c\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u001d\u0010\u0006R\u0011\u0010\u001e\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u001f\u0010\u0006R\u0011\u0010 \u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b!\u0010\u0006R\u0011\u0010\"\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b#\u0010\u0006R\u000e\u0010$\u001a\u00020%X\u0082\u0004¢\u0006\u0002\n\u0000R\u0011\u0010&\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b'\u0010\u0006R\u0014\u0010(\u001a\u00020)X\u0080\u0004¢\u0006\b\n\u0000\u001a\u0004\b*\u0010+R\u0011\u0010,\u001a\u00020\u0010¢\u0006\b\n\u0000\u001a\u0004\b-\u0010\u0012R\u0011\u0010.\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b/\u0010\u0006R\u0011\u00100\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b1\u0010\u0006R\u0011\u00102\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b3\u0010\u0006R\u0014\u00104\u001a\u00020)X\u0080\u0004¢\u0006\b\n\u0000\u001a\u0004\b5\u0010+R\u0014\u00106\u001a\u00020%X\u0080\u0004¢\u0006\b\n\u0000\u001a\u0004\b7\u00108R\u0014\u00109\u001a\u00020\u0004X\u0080\u0004¢\u0006\b\n\u0000\u001a\u0004\b:\u0010\u0006¨\u0006B"}, d2 = {"Landroidx/compose/ui/graphics/colorspace/ColorSpaces;", "", "()V", "Aces", "Landroidx/compose/ui/graphics/colorspace/Rgb;", "getAces", "()Landroidx/compose/ui/graphics/colorspace/Rgb;", "Acescg", "getAcescg", "AdobeRgb", "getAdobeRgb", "Bt2020", "getBt2020", "Bt709", "getBt709", "CieLab", "Landroidx/compose/ui/graphics/colorspace/ColorSpace;", "getCieLab", "()Landroidx/compose/ui/graphics/colorspace/ColorSpace;", "CieXyz", "getCieXyz", "ColorSpacesArray", "", "getColorSpacesArray$ui_graphics_release", "()[Landroidx/compose/ui/graphics/colorspace/ColorSpace;", "[Landroidx/compose/ui/graphics/colorspace/ColorSpace;", "DciP3", "getDciP3", "DisplayP3", "getDisplayP3", "ExtendedSrgb", "getExtendedSrgb", "LinearExtendedSrgb", "getLinearExtendedSrgb", "LinearSrgb", "getLinearSrgb", "NoneTransferParameters", "Landroidx/compose/ui/graphics/colorspace/TransferParameters;", "Ntsc1953", "getNtsc1953", "Ntsc1953Primaries", "", "getNtsc1953Primaries$ui_graphics_release", "()[F", "Oklab", "getOklab", "ProPhotoRgb", "getProPhotoRgb", "SmpteC", "getSmpteC", "Srgb", "getSrgb", "SrgbPrimaries", "getSrgbPrimaries$ui_graphics_release", "SrgbTransferParameters", "getSrgbTransferParameters$ui_graphics_release", "()Landroidx/compose/ui/graphics/colorspace/TransferParameters;", "Unspecified", "getUnspecified$ui_graphics_release", "getColorSpace", TtmlNode.ATTR_ID, "", "getColorSpace$ui_graphics_release", "match", "toXYZD50", "function", "ui-graphics_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
public final class ColorSpaces {
    private static final Rgb Aces;
    private static final Rgb Acescg;
    private static final Rgb AdobeRgb;
    private static final Rgb Bt2020;
    private static final Rgb Bt709;
    private static final ColorSpace CieLab;
    private static final ColorSpace CieXyz;
    private static final ColorSpace[] ColorSpacesArray;
    private static final Rgb DciP3;
    private static final Rgb DisplayP3;
    private static final Rgb ExtendedSrgb;
    public static final ColorSpaces INSTANCE = new ColorSpaces();
    private static final Rgb LinearExtendedSrgb;
    private static final Rgb LinearSrgb;
    private static final TransferParameters NoneTransferParameters;
    private static final Rgb Ntsc1953;
    private static final float[] Ntsc1953Primaries;
    private static final ColorSpace Oklab;
    private static final Rgb ProPhotoRgb;
    private static final Rgb SmpteC;
    private static final Rgb Srgb;
    private static final float[] SrgbPrimaries;
    private static final TransferParameters SrgbTransferParameters;
    private static final Rgb Unspecified;

    static {
        float[] fArr = {0.64f, 0.33f, 0.3f, 0.6f, 0.15f, 0.06f};
        SrgbPrimaries = fArr;
        float[] fArr2 = {0.67f, 0.33f, 0.21f, 0.71f, 0.14f, 0.08f};
        Ntsc1953Primaries = fArr2;
        int i10 = 96;
        h hVar = null;
        double d4 = 0.9478672985781991d;
        double d10 = 0.05213270142180095d;
        double d11 = 0.07739938080495357d;
        double d12 = 0.04045d;
        double d13 = 0.0d;
        double d14 = 0.0d;
        TransferParameters transferParameters = new TransferParameters(2.4d, d4, d10, d11, d12, d13, d14, i10, hVar);
        SrgbTransferParameters = transferParameters;
        TransferParameters transferParameters2 = new TransferParameters(2.2d, d4, d10, d11, d12, d13, d14, i10, hVar);
        NoneTransferParameters = transferParameters2;
        Illuminant illuminant = Illuminant.INSTANCE;
        Rgb rgb = new Rgb("sRGB IEC61966-2.1", fArr, illuminant.getD65(), transferParameters, 0);
        Srgb = rgb;
        Rgb rgb2 = new Rgb("sRGB IEC61966-2.1 (Linear)", fArr, illuminant.getD65(), 1.0d, 0.0f, 1.0f, 1);
        LinearSrgb = rgb2;
        Rgb rgb3 = new Rgb("scRGB-nl IEC 61966-2-2:2003", fArr, illuminant.getD65(), null, new androidx.compose.ui.graphics.c(11), new androidx.compose.ui.graphics.c(12), -0.799f, 2.399f, transferParameters, 2);
        ExtendedSrgb = rgb3;
        Rgb rgb4 = new Rgb("scRGB IEC 61966-2-2:2003", fArr, illuminant.getD65(), 1.0d, -0.5f, 7.499f, 3);
        LinearExtendedSrgb = rgb4;
        Rgb rgb5 = new Rgb("Rec. ITU-R BT.709-5", new float[]{0.64f, 0.33f, 0.3f, 0.6f, 0.15f, 0.06f}, illuminant.getD65(), new TransferParameters(2.2222222222222223d, 0.9099181073703367d, 0.09008189262966333d, 0.2222222222222222d, 0.081d, 0.0d, 0.0d, 96, null), 4);
        Bt709 = rgb5;
        double d15 = 0.0d;
        Rgb rgb6 = new Rgb("Rec. ITU-R BT.2020-1", new float[]{0.708f, 0.292f, 0.17f, 0.797f, 0.131f, 0.046f}, illuminant.getD65(), new TransferParameters(2.2222222222222223d, 0.9096697898662786d, 0.09033021013372146d, 0.2222222222222222d, 0.08145d, 0.0d, d15, 96, null), 5);
        Bt2020 = rgb6;
        Rgb rgb7 = new Rgb("SMPTE RP 431-2-2007 DCI (P3)", new float[]{0.68f, 0.32f, 0.265f, 0.69f, 0.15f, 0.06f}, new WhitePoint(0.314f, 0.351f), 2.6d, 0.0f, 1.0f, 6);
        DciP3 = rgb7;
        Rgb rgb8 = new Rgb("Display P3", new float[]{0.68f, 0.32f, 0.265f, 0.69f, 0.15f, 0.06f}, illuminant.getD65(), transferParameters, 7);
        DisplayP3 = rgb8;
        Rgb rgb9 = new Rgb("NTSC (1953)", fArr2, illuminant.getC(), new TransferParameters(2.2222222222222223d, 0.9099181073703367d, 0.09008189262966333d, 0.2222222222222222d, 0.081d, d15, 0.0d, 96, null), 8);
        Ntsc1953 = rgb9;
        double d16 = 0.0d;
        Rgb rgb10 = new Rgb("SMPTE-C RGB", new float[]{0.63f, 0.34f, 0.31f, 0.595f, 0.155f, 0.07f}, illuminant.getD65(), new TransferParameters(2.2222222222222223d, 0.9099181073703367d, 0.09008189262966333d, 0.2222222222222222d, 0.081d, 0.0d, d16, 96, null), 9);
        SmpteC = rgb10;
        Rgb rgb11 = new Rgb("Adobe RGB (1998)", new float[]{0.64f, 0.33f, 0.21f, 0.71f, 0.15f, 0.06f}, illuminant.getD65(), 2.2d, 0.0f, 1.0f, 10);
        AdobeRgb = rgb11;
        Rgb rgb12 = new Rgb("ROMM RGB ISO 22028-2:2013", new float[]{0.7347f, 0.2653f, 0.1596f, 0.8404f, 0.0366f, 1.0E-4f}, illuminant.getD50(), new TransferParameters(1.8d, 1.0d, 0.0d, 0.0625d, 0.031248d, d16, 0.0d, 96, null), 11);
        ProPhotoRgb = rgb12;
        Rgb rgb13 = new Rgb("SMPTE ST 2065-1:2012 ACES", new float[]{0.7347f, 0.2653f, 0.0f, 1.0f, 1.0E-4f, -0.077f}, illuminant.getD60(), 1.0d, -65504.0f, 65504.0f, 12);
        Aces = rgb13;
        Rgb rgb14 = new Rgb("Academy S-2014-004 ACEScg", new float[]{0.713f, 0.293f, 0.165f, 0.83f, 0.128f, 0.044f}, illuminant.getD60(), 1.0d, -65504.0f, 65504.0f, 13);
        Acescg = rgb14;
        Xyz xyz = new Xyz("Generic XYZ", 14);
        CieXyz = xyz;
        Lab lab = new Lab("Generic L*a*b*", 15);
        CieLab = lab;
        Rgb rgb15 = new Rgb("None", fArr, illuminant.getD65(), transferParameters2, 16);
        Unspecified = rgb15;
        Oklab oklab = new Oklab("Oklab", 17);
        Oklab = oklab;
        ColorSpacesArray = new ColorSpace[]{rgb, rgb2, rgb3, rgb4, rgb5, rgb6, rgb7, rgb8, rgb9, rgb10, rgb11, rgb12, rgb13, rgb14, xyz, lab, rgb15, oklab};
    }

    private ColorSpaces() {
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final double ExtendedSrgb$lambda$0(double d4) {
        return ColorSpaceKt.absRcpResponse(d4, 0.9478672985781991d, 0.05213270142180095d, 0.07739938080495357d, 0.04045d, 2.4d);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final double ExtendedSrgb$lambda$1(double d4) {
        return ColorSpaceKt.absResponse(d4, 0.9478672985781991d, 0.05213270142180095d, 0.07739938080495357d, 0.04045d, 2.4d);
    }

    public final Rgb getAces() {
        return Aces;
    }

    public final Rgb getAcescg() {
        return Acescg;
    }

    public final Rgb getAdobeRgb() {
        return AdobeRgb;
    }

    public final Rgb getBt2020() {
        return Bt2020;
    }

    public final Rgb getBt709() {
        return Bt709;
    }

    public final ColorSpace getCieLab() {
        return CieLab;
    }

    public final ColorSpace getCieXyz() {
        return CieXyz;
    }

    public final ColorSpace getColorSpace$ui_graphics_release(int id) {
        return getColorSpacesArray$ui_graphics_release()[id];
    }

    public final ColorSpace[] getColorSpacesArray$ui_graphics_release() {
        return ColorSpacesArray;
    }

    public final Rgb getDciP3() {
        return DciP3;
    }

    public final Rgb getDisplayP3() {
        return DisplayP3;
    }

    public final Rgb getExtendedSrgb() {
        return ExtendedSrgb;
    }

    public final Rgb getLinearExtendedSrgb() {
        return LinearExtendedSrgb;
    }

    public final Rgb getLinearSrgb() {
        return LinearSrgb;
    }

    public final Rgb getNtsc1953() {
        return Ntsc1953;
    }

    public final float[] getNtsc1953Primaries$ui_graphics_release() {
        return Ntsc1953Primaries;
    }

    public final ColorSpace getOklab() {
        return Oklab;
    }

    public final Rgb getProPhotoRgb() {
        return ProPhotoRgb;
    }

    public final Rgb getSmpteC() {
        return SmpteC;
    }

    public final Rgb getSrgb() {
        return Srgb;
    }

    public final float[] getSrgbPrimaries$ui_graphics_release() {
        return SrgbPrimaries;
    }

    public final TransferParameters getSrgbTransferParameters$ui_graphics_release() {
        return SrgbTransferParameters;
    }

    public final Rgb getUnspecified$ui_graphics_release() {
        return Unspecified;
    }

    public final ColorSpace match(float[] toXYZD50, TransferParameters function) {
        for (ColorSpace colorSpace : ColorSpacesArray) {
            if (ColorModel.m3857equalsimpl0(colorSpace.getModel(), ColorModel.INSTANCE.m3864getRgbxdoWZVw())) {
                Rgb rgb = (Rgb) ColorSpaceKt.adapt$default(colorSpace, Illuminant.INSTANCE.getD50(), null, 2, null);
                if (ColorSpaceKt.compare(toXYZD50, rgb.getTransform()) && ColorSpaceKt.compare(function, rgb.getTransferParameters())) {
                    return colorSpace;
                }
            }
        }
        return null;
    }
}
