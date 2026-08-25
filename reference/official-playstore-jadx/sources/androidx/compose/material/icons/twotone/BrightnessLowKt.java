package androidx.compose.material.icons.twotone;

import a0.b;
import androidx.compose.material.icons.Icons;
import androidx.compose.ui.graphics.Color;
import androidx.compose.ui.graphics.SolidColor;
import androidx.compose.ui.graphics.StrokeCap;
import androidx.compose.ui.graphics.StrokeJoin;
import androidx.compose.ui.graphics.vector.ImageVector;
import androidx.compose.ui.graphics.vector.PathBuilder;
import androidx.compose.ui.graphics.vector.VectorKt;
import androidx.compose.ui.unit.Dp;
import kotlin.Metadata;
import v.c;
import w.a;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\"\u0010\u0010\u0000\u001a\u0004\u0018\u00010\u0001X\u0082\u000e¢\u0006\u0002\n\u0000\"\u0015\u0010\u0002\u001a\u00020\u0001*\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"_brightnessLow", "Landroidx/compose/ui/graphics/vector/ImageVector;", "BrightnessLow", "Landroidx/compose/material/icons/Icons$TwoTone;", "getBrightnessLow", "(Landroidx/compose/material/icons/Icons$TwoTone;)Landroidx/compose/ui/graphics/vector/ImageVector;", "material-icons-extended_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class BrightnessLowKt {
    private static ImageVector _brightnessLow;

    public static final ImageVector getBrightnessLow(Icons.TwoTone twoTone) {
        ImageVector imageVector = _brightnessLow;
        if (imageVector != null) {
            return imageVector;
        }
        ImageVector.Builder builder = new ImageVector.Builder("TwoTone.BrightnessLow", Dp.m5678constructorimpl(24.0f), Dp.m5678constructorimpl(24.0f), 24.0f, 24.0f, 0L, 0, false, 96, null);
        int defaultFillType = VectorKt.getDefaultFillType();
        Color.Companion companion = Color.INSTANCE;
        SolidColor solidColor = new SolidColor(companion.m3498getBlack0d7_KjU(), null);
        StrokeCap.Companion companion2 = StrokeCap.INSTANCE;
        int iM3803getButtKaPHkGw = companion2.m3803getButtKaPHkGw();
        StrokeJoin.Companion companion3 = StrokeJoin.INSTANCE;
        int iM3813getBevelLxFBmk8 = companion3.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderA = b.a(18.0f, 9.52f, 6.0f, -3.52f);
        pathBuilderA.lineTo(12.0f, 3.52f);
        v.b.m(pathBuilderA, 9.52f, 6.0f, 6.0f, 3.52f);
        pathBuilderA.lineTo(3.52f, 12.0f);
        v.b.n(pathBuilderA, 6.0f, 14.48f, 18.0f, 3.52f);
        pathBuilderA.lineTo(12.0f, 20.48f);
        v.b.m(pathBuilderA, 14.48f, 18.0f, 18.0f, -3.52f);
        v.b.y(pathBuilderA, 20.48f, 12.0f, 18.0f, 9.52f);
        pathBuilderA.moveTo(12.0f, 18.0f);
        pathBuilderA.curveToRelative(-3.31f, 0.0f, -6.0f, -2.69f, -6.0f, -6.0f);
        pathBuilderA.reflectiveCurveToRelative(2.69f, -6.0f, 6.0f, -6.0f);
        pathBuilderA.reflectiveCurveToRelative(6.0f, 2.69f, 6.0f, 6.0f);
        ImageVector.Builder.m4024addPathoIyEayM$default(builder, c.h(pathBuilderA, -2.69f, 6.0f, -6.0f, 6.0f), defaultFillType, "", solidColor, 0.3f, null, 1.0f, 1.0f, iM3803getButtKaPHkGw, iM3813getBevelLxFBmk8, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null);
        int defaultFillType2 = VectorKt.getDefaultFillType();
        SolidColor solidColor2 = new SolidColor(companion.m3498getBlack0d7_KjU(), null);
        int iM3803getButtKaPHkGw2 = companion2.m3803getButtKaPHkGw();
        int iM3813getBevelLxFBmk82 = companion3.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderT = a.t(20.0f, 8.69f, 20.0f, 4.0f, -4.69f);
        pathBuilderT.lineTo(12.0f, 0.69f);
        pathBuilderT.lineTo(8.69f, 4.0f);
        pathBuilderT.lineTo(4.0f, 4.0f);
        pathBuilderT.verticalLineToRelative(4.69f);
        pathBuilderT.lineTo(0.69f, 12.0f);
        pathBuilderT.lineTo(4.0f, 15.31f);
        pathBuilderT.lineTo(4.0f, 20.0f);
        pathBuilderT.horizontalLineToRelative(4.69f);
        pathBuilderT.lineTo(12.0f, 23.31f);
        pathBuilderT.lineTo(15.31f, 20.0f);
        pathBuilderT.lineTo(20.0f, 20.0f);
        pathBuilderT.verticalLineToRelative(-4.69f);
        v.b.y(pathBuilderT, 23.31f, 12.0f, 20.0f, 8.69f);
        pathBuilderT.moveTo(18.0f, 14.48f);
        pathBuilderT.lineTo(18.0f, 18.0f);
        pathBuilderT.horizontalLineToRelative(-3.52f);
        pathBuilderT.lineTo(12.0f, 20.48f);
        pathBuilderT.lineTo(9.52f, 18.0f);
        pathBuilderT.lineTo(6.0f, 18.0f);
        pathBuilderT.verticalLineToRelative(-3.52f);
        pathBuilderT.lineTo(3.52f, 12.0f);
        pathBuilderT.lineTo(6.0f, 9.52f);
        pathBuilderT.lineTo(6.0f, 6.0f);
        pathBuilderT.horizontalLineToRelative(3.52f);
        pathBuilderT.lineTo(12.0f, 3.52f);
        pathBuilderT.lineTo(14.48f, 6.0f);
        pathBuilderT.lineTo(18.0f, 6.0f);
        pathBuilderT.verticalLineToRelative(3.52f);
        v.b.y(pathBuilderT, 20.48f, 12.0f, 18.0f, 14.48f);
        pathBuilderT.moveTo(12.0f, 6.0f);
        pathBuilderT.curveToRelative(-3.31f, 0.0f, -6.0f, 2.69f, -6.0f, 6.0f);
        pathBuilderT.reflectiveCurveToRelative(2.69f, 6.0f, 6.0f, 6.0f);
        pathBuilderT.reflectiveCurveToRelative(6.0f, -2.69f, 6.0f, -6.0f);
        pathBuilderT.reflectiveCurveToRelative(-2.69f, -6.0f, -6.0f, -6.0f);
        pathBuilderT.close();
        pathBuilderT.moveTo(12.0f, 16.0f);
        pathBuilderT.curveToRelative(-2.21f, 0.0f, -4.0f, -1.79f, -4.0f, -4.0f);
        pathBuilderT.reflectiveCurveToRelative(1.79f, -4.0f, 4.0f, -4.0f);
        pathBuilderT.reflectiveCurveToRelative(4.0f, 1.79f, 4.0f, 4.0f);
        ImageVector imageVectorBuild = ImageVector.Builder.m4024addPathoIyEayM$default(builder, c.h(pathBuilderT, -1.79f, 4.0f, -4.0f, 4.0f), defaultFillType2, "", solidColor2, 1.0f, null, 1.0f, 1.0f, iM3803getButtKaPHkGw2, iM3813getBevelLxFBmk82, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null).build();
        _brightnessLow = imageVectorBuild;
        return imageVectorBuild;
    }
}
