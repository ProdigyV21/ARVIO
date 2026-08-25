package androidx.compose.ui.graphics;

import android.graphics.ColorSpace;
import androidx.compose.ui.graphics.colorspace.ColorSpaces;
import androidx.compose.ui.graphics.colorspace.DoubleFunction;
import androidx.compose.ui.graphics.colorspace.Rgb;
import androidx.compose.ui.graphics.colorspace.TransferParameters;
import androidx.compose.ui.graphics.colorspace.WhitePoint;
import j$.util.function.DoubleUnaryOperator$CC;
import java.util.function.DoubleUnaryOperator;
import kotlin.Metadata;
import kotlin.jvm.internal.p;
import q7.n;
import r7.l;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\bÃ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\f\u0010\u0003\u001a\u00020\u0004*\u00020\u0005H\u0007J\f\u0010\u0006\u001a\u00020\u0005*\u00020\u0004H\u0007¨\u0006\u0007"}, d2 = {"Landroidx/compose/ui/graphics/ColorSpaceVerificationHelper;", "", "()V", "androidColorSpace", "Landroid/graphics/ColorSpace;", "Landroidx/compose/ui/graphics/colorspace/ColorSpace;", "composeColorSpace", "ui-graphics_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
final class ColorSpaceVerificationHelper {
    public static final ColorSpaceVerificationHelper INSTANCE = new ColorSpaceVerificationHelper();

    private ColorSpaceVerificationHelper() {
    }

    @n
    public static final ColorSpace androidColorSpace(androidx.compose.ui.graphics.colorspace.ColorSpace colorSpace) {
        ColorSpaces colorSpaces = ColorSpaces.INSTANCE;
        if (p.a(colorSpace, colorSpaces.getSrgb())) {
            ColorSpace colorSpace2 = ColorSpace.get(ColorSpace.Named.SRGB);
            c.f(colorSpace2);
            return colorSpace2;
        }
        if (p.a(colorSpace, colorSpaces.getAces())) {
            ColorSpace colorSpace3 = ColorSpace.get(ColorSpace.Named.ACES);
            c.f(colorSpace3);
            return colorSpace3;
        }
        if (p.a(colorSpace, colorSpaces.getAcescg())) {
            ColorSpace colorSpace4 = ColorSpace.get(ColorSpace.Named.ACESCG);
            c.f(colorSpace4);
            return colorSpace4;
        }
        if (p.a(colorSpace, colorSpaces.getAdobeRgb())) {
            ColorSpace colorSpace5 = ColorSpace.get(ColorSpace.Named.ADOBE_RGB);
            c.f(colorSpace5);
            return colorSpace5;
        }
        if (p.a(colorSpace, colorSpaces.getBt2020())) {
            ColorSpace colorSpace6 = ColorSpace.get(ColorSpace.Named.BT2020);
            c.f(colorSpace6);
            return colorSpace6;
        }
        if (p.a(colorSpace, colorSpaces.getBt709())) {
            ColorSpace colorSpace7 = ColorSpace.get(ColorSpace.Named.BT709);
            c.f(colorSpace7);
            return colorSpace7;
        }
        if (p.a(colorSpace, colorSpaces.getCieLab())) {
            ColorSpace colorSpace8 = ColorSpace.get(ColorSpace.Named.CIE_LAB);
            c.f(colorSpace8);
            return colorSpace8;
        }
        if (p.a(colorSpace, colorSpaces.getCieXyz())) {
            ColorSpace colorSpace9 = ColorSpace.get(ColorSpace.Named.CIE_XYZ);
            c.f(colorSpace9);
            return colorSpace9;
        }
        if (p.a(colorSpace, colorSpaces.getDciP3())) {
            ColorSpace colorSpace10 = ColorSpace.get(ColorSpace.Named.DCI_P3);
            c.f(colorSpace10);
            return colorSpace10;
        }
        if (p.a(colorSpace, colorSpaces.getDisplayP3())) {
            ColorSpace colorSpace11 = ColorSpace.get(ColorSpace.Named.DISPLAY_P3);
            c.f(colorSpace11);
            return colorSpace11;
        }
        if (p.a(colorSpace, colorSpaces.getExtendedSrgb())) {
            ColorSpace colorSpace12 = ColorSpace.get(ColorSpace.Named.EXTENDED_SRGB);
            c.f(colorSpace12);
            return colorSpace12;
        }
        if (p.a(colorSpace, colorSpaces.getLinearExtendedSrgb())) {
            ColorSpace colorSpace13 = ColorSpace.get(ColorSpace.Named.LINEAR_EXTENDED_SRGB);
            c.f(colorSpace13);
            return colorSpace13;
        }
        if (p.a(colorSpace, colorSpaces.getLinearSrgb())) {
            ColorSpace colorSpace14 = ColorSpace.get(ColorSpace.Named.LINEAR_SRGB);
            c.f(colorSpace14);
            return colorSpace14;
        }
        if (p.a(colorSpace, colorSpaces.getNtsc1953())) {
            ColorSpace colorSpace15 = ColorSpace.get(ColorSpace.Named.NTSC_1953);
            c.f(colorSpace15);
            return colorSpace15;
        }
        if (p.a(colorSpace, colorSpaces.getProPhotoRgb())) {
            ColorSpace colorSpace16 = ColorSpace.get(ColorSpace.Named.PRO_PHOTO_RGB);
            c.f(colorSpace16);
            return colorSpace16;
        }
        if (p.a(colorSpace, colorSpaces.getSmpteC())) {
            ColorSpace colorSpace17 = ColorSpace.get(ColorSpace.Named.SMPTE_C);
            c.f(colorSpace17);
            return colorSpace17;
        }
        if (!(colorSpace instanceof Rgb)) {
            ColorSpace colorSpace18 = ColorSpace.get(ColorSpace.Named.SRGB);
            c.f(colorSpace18);
            return colorSpace18;
        }
        Rgb rgb = (Rgb) colorSpace;
        float[] xyz$ui_graphics_release = rgb.getWhitePoint().toXyz$ui_graphics_release();
        TransferParameters transferParameters = rgb.getTransferParameters();
        ColorSpace.Rgb.TransferParameters transferParameters2 = transferParameters != null ? new ColorSpace.Rgb.TransferParameters(transferParameters.getA(), transferParameters.getB(), transferParameters.getC(), transferParameters.getD(), transferParameters.getE(), transferParameters.getF(), transferParameters.getGamma()) : null;
        if (transferParameters2 != null) {
            return c.f(new ColorSpace.Rgb(colorSpace.getName(), rgb.getPrimaries(), xyz$ui_graphics_release, transferParameters2));
        }
        String name = colorSpace.getName();
        float[] primaries = rgb.getPrimaries();
        final l<Double, Double> oetf = rgb.getOetf();
        final int i10 = 0;
        DoubleUnaryOperator doubleUnaryOperator = new DoubleUnaryOperator() { // from class: androidx.compose.ui.graphics.d
            public /* synthetic */ DoubleUnaryOperator andThen(DoubleUnaryOperator doubleUnaryOperator2) {
                int i11 = i10;
                return DoubleUnaryOperator$CC.$default$andThen(this, doubleUnaryOperator2);
            }

            @Override // java.util.function.DoubleUnaryOperator
            public final double applyAsDouble(double d4) {
                switch (i10) {
                    case 0:
                        return ColorSpaceVerificationHelper.androidColorSpace$lambda$0(oetf, d4);
                    default:
                        return ColorSpaceVerificationHelper.androidColorSpace$lambda$1(oetf, d4);
                }
            }

            public /* synthetic */ DoubleUnaryOperator compose(DoubleUnaryOperator doubleUnaryOperator2) {
                int i11 = i10;
                return DoubleUnaryOperator$CC.$default$compose(this, doubleUnaryOperator2);
            }
        };
        final l<Double, Double> eotf = rgb.getEotf();
        final int i11 = 1;
        return c.f(new ColorSpace.Rgb(name, primaries, xyz$ui_graphics_release, doubleUnaryOperator, new DoubleUnaryOperator() { // from class: androidx.compose.ui.graphics.d
            public /* synthetic */ DoubleUnaryOperator andThen(DoubleUnaryOperator doubleUnaryOperator2) {
                int i112 = i11;
                return DoubleUnaryOperator$CC.$default$andThen(this, doubleUnaryOperator2);
            }

            @Override // java.util.function.DoubleUnaryOperator
            public final double applyAsDouble(double d4) {
                switch (i11) {
                    case 0:
                        return ColorSpaceVerificationHelper.androidColorSpace$lambda$0(eotf, d4);
                    default:
                        return ColorSpaceVerificationHelper.androidColorSpace$lambda$1(eotf, d4);
                }
            }

            public /* synthetic */ DoubleUnaryOperator compose(DoubleUnaryOperator doubleUnaryOperator2) {
                int i112 = i11;
                return DoubleUnaryOperator$CC.$default$compose(this, doubleUnaryOperator2);
            }
        }, colorSpace.getMinValue(0), colorSpace.getMaxValue(0)));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final double androidColorSpace$lambda$0(l lVar, double d4) {
        return ((Number) lVar.invoke(Double.valueOf(d4))).doubleValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final double androidColorSpace$lambda$1(l lVar, double d4) {
        return ((Number) lVar.invoke(Double.valueOf(d4))).doubleValue();
    }

    @n
    public static final androidx.compose.ui.graphics.colorspace.ColorSpace composeColorSpace(final ColorSpace colorSpace) {
        int id = colorSpace.getId();
        if (id == ColorSpace.Named.SRGB.ordinal()) {
            return ColorSpaces.INSTANCE.getSrgb();
        }
        if (id == ColorSpace.Named.ACES.ordinal()) {
            return ColorSpaces.INSTANCE.getAces();
        }
        if (id == ColorSpace.Named.ACESCG.ordinal()) {
            return ColorSpaces.INSTANCE.getAcescg();
        }
        if (id == ColorSpace.Named.ADOBE_RGB.ordinal()) {
            return ColorSpaces.INSTANCE.getAdobeRgb();
        }
        if (id == ColorSpace.Named.BT2020.ordinal()) {
            return ColorSpaces.INSTANCE.getBt2020();
        }
        if (id == ColorSpace.Named.BT709.ordinal()) {
            return ColorSpaces.INSTANCE.getBt709();
        }
        if (id == ColorSpace.Named.CIE_LAB.ordinal()) {
            return ColorSpaces.INSTANCE.getCieLab();
        }
        if (id == ColorSpace.Named.CIE_XYZ.ordinal()) {
            return ColorSpaces.INSTANCE.getCieXyz();
        }
        if (id == ColorSpace.Named.DCI_P3.ordinal()) {
            return ColorSpaces.INSTANCE.getDciP3();
        }
        if (id == ColorSpace.Named.DISPLAY_P3.ordinal()) {
            return ColorSpaces.INSTANCE.getDisplayP3();
        }
        if (id == ColorSpace.Named.EXTENDED_SRGB.ordinal()) {
            return ColorSpaces.INSTANCE.getExtendedSrgb();
        }
        if (id == ColorSpace.Named.LINEAR_EXTENDED_SRGB.ordinal()) {
            return ColorSpaces.INSTANCE.getLinearExtendedSrgb();
        }
        if (id == ColorSpace.Named.LINEAR_SRGB.ordinal()) {
            return ColorSpaces.INSTANCE.getLinearSrgb();
        }
        if (id == ColorSpace.Named.NTSC_1953.ordinal()) {
            return ColorSpaces.INSTANCE.getNtsc1953();
        }
        if (id == ColorSpace.Named.PRO_PHOTO_RGB.ordinal()) {
            return ColorSpaces.INSTANCE.getProPhotoRgb();
        }
        if (id == ColorSpace.Named.SMPTE_C.ordinal()) {
            return ColorSpaces.INSTANCE.getSmpteC();
        }
        if (!(colorSpace instanceof ColorSpace.Rgb)) {
            return ColorSpaces.INSTANCE.getSrgb();
        }
        ColorSpace.Rgb rgbD = a.d(colorSpace);
        ColorSpace.Rgb.TransferParameters transferParameters = rgbD.getTransferParameters();
        WhitePoint whitePoint = rgbD.getWhitePoint().length == 3 ? new WhitePoint(rgbD.getWhitePoint()[0], rgbD.getWhitePoint()[1], rgbD.getWhitePoint()[2]) : new WhitePoint(rgbD.getWhitePoint()[0], rgbD.getWhitePoint()[1]);
        TransferParameters transferParameters2 = transferParameters != null ? new TransferParameters(transferParameters.g, transferParameters.a, transferParameters.b, transferParameters.c, transferParameters.d, transferParameters.e, transferParameters.f) : null;
        String name = rgbD.getName();
        float[] primaries = rgbD.getPrimaries();
        float[] transform = rgbD.getTransform();
        final int i10 = 0;
        DoubleFunction doubleFunction = new DoubleFunction() { // from class: androidx.compose.ui.graphics.e
            @Override // androidx.compose.ui.graphics.colorspace.DoubleFunction
            public final double invoke(double d4) {
                switch (i10) {
                    case 0:
                        return ColorSpaceVerificationHelper.composeColorSpace$lambda$2(colorSpace, d4);
                    default:
                        return ColorSpaceVerificationHelper.composeColorSpace$lambda$3(colorSpace, d4);
                }
            }
        };
        final int i11 = 1;
        return new Rgb(name, primaries, whitePoint, transform, doubleFunction, new DoubleFunction() { // from class: androidx.compose.ui.graphics.e
            @Override // androidx.compose.ui.graphics.colorspace.DoubleFunction
            public final double invoke(double d4) {
                switch (i11) {
                    case 0:
                        return ColorSpaceVerificationHelper.composeColorSpace$lambda$2(colorSpace, d4);
                    default:
                        return ColorSpaceVerificationHelper.composeColorSpace$lambda$3(colorSpace, d4);
                }
            }
        }, colorSpace.getMinValue(0), colorSpace.getMaxValue(0), transferParameters2, rgbD.getId());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final double composeColorSpace$lambda$2(ColorSpace colorSpace, double d4) {
        return a.d(colorSpace).getOetf().applyAsDouble(d4);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final double composeColorSpace$lambda$3(ColorSpace colorSpace, double d4) {
        return a.d(colorSpace).getEotf().applyAsDouble(d4);
    }
}
