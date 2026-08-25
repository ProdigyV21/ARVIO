package androidx.compose.material.icons.filled;

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
import v.a;
import v.c;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\"\u0010\u0010\u0000\u001a\u0004\u0018\u00010\u0001X\u0082\u000e¢\u0006\u0002\n\u0000\"\u0015\u0010\u0002\u001a\u00020\u0001*\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"_noiseControlOff", "Landroidx/compose/ui/graphics/vector/ImageVector;", "NoiseControlOff", "Landroidx/compose/material/icons/Icons$Filled;", "getNoiseControlOff", "(Landroidx/compose/material/icons/Icons$Filled;)Landroidx/compose/ui/graphics/vector/ImageVector;", "material-icons-extended_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class NoiseControlOffKt {
    private static ImageVector _noiseControlOff;

    public static final ImageVector getNoiseControlOff(Icons.Filled filled) {
        ImageVector imageVector = _noiseControlOff;
        if (imageVector != null) {
            return imageVector;
        }
        ImageVector.Builder builder = new ImageVector.Builder("Filled.NoiseControlOff", Dp.m5678constructorimpl(24.0f), Dp.m5678constructorimpl(24.0f), 24.0f, 24.0f, 0L, 0, false, 96, null);
        int defaultFillType = VectorKt.getDefaultFillType();
        Color.Companion companion = Color.INSTANCE;
        SolidColor solidColor = new SolidColor(companion.m3498getBlack0d7_KjU(), null);
        StrokeCap.Companion companion2 = StrokeCap.INSTANCE;
        int iM3803getButtKaPHkGw = companion2.m3803getButtKaPHkGw();
        StrokeJoin.Companion companion3 = StrokeJoin.INSTANCE;
        int iM3813getBevelLxFBmk8 = companion3.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderA = c.a(12.0f, 4.0f);
        pathBuilderA.curveToRelative(1.44f, 0.0f, 2.79f, 0.38f, 3.95f, 1.05f);
        pathBuilderA.lineToRelative(1.45f, -1.45f);
        pathBuilderA.curveTo(15.85f, 2.59f, 13.99f, 2.0f, 12.0f, 2.0f);
        pathBuilderA.reflectiveCurveTo(8.15f, 2.59f, 6.59f, 3.59f);
        pathBuilderA.lineToRelative(1.45f, 1.45f);
        pathBuilderA.curveTo(9.21f, 4.38f, 10.56f, 4.0f, 12.0f, 4.0f);
        pathBuilderA.close();
        ImageVector.Builder.m4024addPathoIyEayM$default(builder, pathBuilderA.getNodes(), defaultFillType, "", solidColor, 1.0f, null, 1.0f, 1.0f, iM3803getButtKaPHkGw, iM3813getBevelLxFBmk8, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null);
        int defaultFillType2 = VectorKt.getDefaultFillType();
        SolidColor solidColor2 = new SolidColor(companion.m3498getBlack0d7_KjU(), null);
        int iM3803getButtKaPHkGw2 = companion2.m3803getButtKaPHkGw();
        int iM3813getBevelLxFBmk82 = companion3.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderA2 = c.a(20.0f, 12.0f);
        pathBuilderA2.curveToRelative(0.0f, 1.44f, -0.38f, 2.79f, -1.05f, 3.95f);
        pathBuilderA2.lineToRelative(1.45f, 1.45f);
        pathBuilderA2.curveTo(21.41f, 15.85f, 22.0f, 13.99f, 22.0f, 12.0f);
        pathBuilderA2.reflectiveCurveToRelative(-0.59f, -3.85f, -1.59f, -5.41f);
        pathBuilderA2.lineToRelative(-1.45f, 1.45f);
        pathBuilderA2.curveTo(19.62f, 9.21f, 20.0f, 10.56f, 20.0f, 12.0f);
        pathBuilderA2.close();
        ImageVector.Builder.m4024addPathoIyEayM$default(builder, pathBuilderA2.getNodes(), defaultFillType2, "", solidColor2, 1.0f, null, 1.0f, 1.0f, iM3803getButtKaPHkGw2, iM3813getBevelLxFBmk82, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null);
        int defaultFillType3 = VectorKt.getDefaultFillType();
        SolidColor solidColor3 = new SolidColor(companion.m3498getBlack0d7_KjU(), null);
        int iM3803getButtKaPHkGw3 = companion2.m3803getButtKaPHkGw();
        int iM3813getBevelLxFBmk83 = companion3.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderA3 = c.a(12.0f, 20.0f);
        pathBuilderA3.curveToRelative(-1.44f, 0.0f, -2.79f, -0.38f, -3.95f, -1.05f);
        pathBuilderA3.lineToRelative(-1.45f, 1.45f);
        pathBuilderA3.curveTo(8.15f, 21.41f, 10.01f, 22.0f, 12.0f, 22.0f);
        pathBuilderA3.reflectiveCurveToRelative(3.85f, -0.59f, 5.41f, -1.59f);
        pathBuilderA3.lineToRelative(-1.45f, -1.45f);
        pathBuilderA3.curveTo(14.79f, 19.62f, 13.44f, 20.0f, 12.0f, 20.0f);
        pathBuilderA3.close();
        ImageVector.Builder.m4024addPathoIyEayM$default(builder, pathBuilderA3.getNodes(), defaultFillType3, "", solidColor3, 1.0f, null, 1.0f, 1.0f, iM3803getButtKaPHkGw3, iM3813getBevelLxFBmk83, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null);
        int defaultFillType4 = VectorKt.getDefaultFillType();
        SolidColor solidColor4 = new SolidColor(companion.m3498getBlack0d7_KjU(), null);
        int iM3803getButtKaPHkGw4 = companion2.m3803getButtKaPHkGw();
        int iM3813getBevelLxFBmk84 = companion3.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderA4 = c.a(4.0f, 12.0f);
        pathBuilderA4.curveToRelative(0.0f, -1.44f, 0.38f, -2.79f, 1.05f, -3.95f);
        pathBuilderA4.lineTo(3.59f, 6.59f);
        pathBuilderA4.curveTo(2.59f, 8.15f, 2.0f, 10.01f, 2.0f, 12.0f);
        pathBuilderA4.reflectiveCurveToRelative(0.59f, 3.85f, 1.59f, 5.41f);
        pathBuilderA4.lineToRelative(1.45f, -1.45f);
        pathBuilderA4.curveTo(4.38f, 14.79f, 4.0f, 13.44f, 4.0f, 12.0f);
        pathBuilderA4.close();
        ImageVector.Builder.m4024addPathoIyEayM$default(builder, pathBuilderA4.getNodes(), defaultFillType4, "", solidColor4, 1.0f, null, 1.0f, 1.0f, iM3803getButtKaPHkGw4, iM3813getBevelLxFBmk84, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null);
        int defaultFillType5 = VectorKt.getDefaultFillType();
        SolidColor solidColor5 = new SolidColor(companion.m3498getBlack0d7_KjU(), null);
        int iM3803getButtKaPHkGw5 = companion2.m3803getButtKaPHkGw();
        int iM3813getBevelLxFBmk85 = companion3.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderA5 = c.a(11.5f, 6.0f);
        pathBuilderA5.curveTo(9.02f, 6.0f, 7.0f, 8.02f, 7.0f, 10.5f);
        pathBuilderA5.curveToRelative(0.0f, 1.22f, 0.49f, 2.41f, 1.35f, 3.27f);
        pathBuilderA5.lineToRelative(1.36f, 1.36f);
        pathBuilderA5.curveToRelative(0.17f, 0.17f, 0.31f, 0.44f, 0.44f, 0.82f);
        pathBuilderA5.curveTo(10.56f, 17.17f, 11.71f, 18.0f, 13.0f, 18.0f);
        pathBuilderA5.curveToRelative(1.65f, 0.0f, 3.0f, -1.35f, 3.0f, -3.0f);
        pathBuilderA5.horizontalLineToRelative(-2.0f);
        pathBuilderA5.curveToRelative(0.0f, 0.55f, -0.45f, 1.0f, -1.0f, 1.0f);
        pathBuilderA5.curveToRelative(-0.43f, 0.0f, -0.81f, -0.27f, -0.95f, -0.68f);
        pathBuilderA5.curveToRelative(-0.15f, -0.44f, -0.4f, -1.08f, -0.93f, -1.61f);
        pathBuilderA5.lineToRelative(-1.36f, -1.36f);
        pathBuilderA5.curveTo(9.28f, 11.87f, 9.0f, 11.19f, 9.0f, 10.5f);
        pathBuilderA5.curveTo(9.0f, 9.12f, 10.12f, 8.0f, 11.5f, 8.0f);
        pathBuilderA5.curveToRelative(1.21f, 0.0f, 2.22f, 0.86f, 2.45f, 2.0f);
        pathBuilderA5.horizontalLineToRelative(2.02f);
        pathBuilderA5.curveTo(15.72f, 7.75f, 13.81f, 6.0f, 11.5f, 6.0f);
        pathBuilderA5.close();
        ImageVector.Builder.m4024addPathoIyEayM$default(builder, pathBuilderA5.getNodes(), defaultFillType5, "", solidColor5, 1.0f, null, 1.0f, 1.0f, iM3803getButtKaPHkGw5, iM3813getBevelLxFBmk85, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null);
        int defaultFillType6 = VectorKt.getDefaultFillType();
        SolidColor solidColor6 = new SolidColor(companion.m3498getBlack0d7_KjU(), null);
        int iM3803getButtKaPHkGw6 = companion2.m3803getButtKaPHkGw();
        int iM3813getBevelLxFBmk86 = companion3.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderB = a.b(13.5f, 12.5f, -1.5f, 0.0f);
        pathBuilderB.arcToRelative(1.5f, 1.5f, 0.0f, true, true, 3.0f, 0.0f);
        pathBuilderB.arcToRelative(1.5f, 1.5f, 0.0f, true, true, -3.0f, 0.0f);
        ImageVector imageVectorBuild = ImageVector.Builder.m4024addPathoIyEayM$default(builder, pathBuilderB.getNodes(), defaultFillType6, "", solidColor6, 1.0f, null, 1.0f, 1.0f, iM3803getButtKaPHkGw6, iM3813getBevelLxFBmk86, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null).build();
        _noiseControlOff = imageVectorBuild;
        return imageVectorBuild;
    }
}
