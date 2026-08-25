package androidx.compose.material.icons.rounded;

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
import v.e;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\"\u0010\u0010\u0000\u001a\u0004\u0018\u00010\u0001X\u0082\u000e¢\u0006\u0002\n\u0000\"\u0015\u0010\u0002\u001a\u00020\u0001*\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"_sensorOccupied", "Landroidx/compose/ui/graphics/vector/ImageVector;", "SensorOccupied", "Landroidx/compose/material/icons/Icons$Rounded;", "getSensorOccupied", "(Landroidx/compose/material/icons/Icons$Rounded;)Landroidx/compose/ui/graphics/vector/ImageVector;", "material-icons-extended_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class SensorOccupiedKt {
    private static ImageVector _sensorOccupied;

    public static final ImageVector getSensorOccupied(Icons.Rounded rounded) {
        ImageVector imageVector = _sensorOccupied;
        if (imageVector != null) {
            return imageVector;
        }
        ImageVector.Builder builder = new ImageVector.Builder("Rounded.SensorOccupied", Dp.m5678constructorimpl(24.0f), Dp.m5678constructorimpl(24.0f), 24.0f, 24.0f, 0L, 0, false, 96, null);
        int defaultFillType = VectorKt.getDefaultFillType();
        Color.Companion companion = Color.INSTANCE;
        SolidColor solidColor = new SolidColor(companion.m3498getBlack0d7_KjU(), null);
        StrokeCap.Companion companion2 = StrokeCap.INSTANCE;
        int iM3803getButtKaPHkGw = companion2.m3803getButtKaPHkGw();
        StrokeJoin.Companion companion3 = StrokeJoin.INSTANCE;
        int iM3813getBevelLxFBmk8 = companion3.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderA = c.a(12.0f, 11.0f);
        pathBuilderA.curveToRelative(1.66f, 0.0f, 3.0f, -1.34f, 3.0f, -3.0f);
        pathBuilderA.reflectiveCurveToRelative(-1.34f, -3.0f, -3.0f, -3.0f);
        pathBuilderA.reflectiveCurveTo(9.0f, 6.34f, 9.0f, 8.0f);
        ImageVector.Builder.m4024addPathoIyEayM$default(builder, e.u(pathBuilderA, 10.34f, 11.0f, 12.0f, 11.0f), defaultFillType, "", solidColor, 1.0f, null, 1.0f, 1.0f, iM3803getButtKaPHkGw, iM3813getBevelLxFBmk8, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null);
        int defaultFillType2 = VectorKt.getDefaultFillType();
        SolidColor solidColor2 = new SolidColor(companion.m3498getBlack0d7_KjU(), null);
        int iM3803getButtKaPHkGw2 = companion2.m3803getButtKaPHkGw();
        int iM3813getBevelLxFBmk82 = companion3.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderA2 = c.a(12.0f, 12.0f);
        pathBuilderA2.curveToRelative(-1.84f, 0.0f, -3.56f, 0.5f, -5.03f, 1.37f);
        pathBuilderA2.curveTo(6.36f, 13.73f, 6.0f, 14.39f, 6.0f, 15.09f);
        pathBuilderA2.lineTo(6.0f, 16.0f);
        pathBuilderA2.curveToRelative(0.0f, 0.55f, 0.45f, 1.0f, 1.0f, 1.0f);
        pathBuilderA2.horizontalLineToRelative(10.0f);
        pathBuilderA2.curveToRelative(0.55f, 0.0f, 1.0f, -0.45f, 1.0f, -1.0f);
        pathBuilderA2.lineToRelative(0.0f, -0.91f);
        pathBuilderA2.curveToRelative(0.0f, -0.7f, -0.36f, -1.36f, -0.97f, -1.72f);
        pathBuilderA2.curveTo(15.56f, 12.5f, 13.84f, 12.0f, 12.0f, 12.0f);
        pathBuilderA2.close();
        ImageVector.Builder.m4024addPathoIyEayM$default(builder, pathBuilderA2.getNodes(), defaultFillType2, "", solidColor2, 1.0f, null, 1.0f, 1.0f, iM3803getButtKaPHkGw2, iM3813getBevelLxFBmk82, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null);
        int defaultFillType3 = VectorKt.getDefaultFillType();
        SolidColor solidColor3 = new SolidColor(companion.m3498getBlack0d7_KjU(), null);
        int iM3803getButtKaPHkGw3 = companion2.m3803getButtKaPHkGw();
        int iM3813getBevelLxFBmk83 = companion3.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderJ = a.j(22.11f, 7.79f, 22.11f, 7.79f);
        pathBuilderJ.curveToRelative(0.55f, -0.23f, 0.78f, -0.88f, 0.5f, -1.41f);
        pathBuilderJ.curveToRelative(-1.13f, -2.12f, -2.87f, -3.86f, -4.99f, -4.99f);
        pathBuilderJ.curveToRelative(-0.52f, -0.28f, -1.17f, -0.04f, -1.4f, 0.5f);
        pathBuilderJ.verticalLineToRelative(0.0f);
        pathBuilderJ.curveToRelative(-0.19f, 0.47f, -0.01f, 1.02f, 0.43f, 1.25f);
        pathBuilderJ.curveToRelative(1.79f, 0.94f, 3.26f, 2.42f, 4.21f, 4.21f);
        pathBuilderJ.curveTo(21.09f, 7.8f, 21.64f, 7.98f, 22.11f, 7.79f);
        pathBuilderJ.close();
        ImageVector.Builder.m4024addPathoIyEayM$default(builder, pathBuilderJ.getNodes(), defaultFillType3, "", solidColor3, 1.0f, null, 1.0f, 1.0f, iM3803getButtKaPHkGw3, iM3813getBevelLxFBmk83, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null);
        int defaultFillType4 = VectorKt.getDefaultFillType();
        SolidColor solidColor4 = new SolidColor(companion.m3498getBlack0d7_KjU(), null);
        int iM3803getButtKaPHkGw4 = companion2.m3803getButtKaPHkGw();
        int iM3813getBevelLxFBmk84 = companion3.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderJ2 = a.j(7.79f, 1.89f, 7.79f, 1.89f);
        pathBuilderJ2.curveToRelative(-0.23f, -0.55f, -0.88f, -0.78f, -1.4f, -0.5f);
        pathBuilderJ2.curveTo(4.27f, 2.52f, 2.52f, 4.26f, 1.4f, 6.38f);
        pathBuilderJ2.curveToRelative(-0.28f, 0.52f, -0.05f, 1.18f, 0.5f, 1.41f);
        pathBuilderJ2.lineToRelative(0.0f, 0.0f);
        pathBuilderJ2.curveToRelative(0.47f, 0.2f, 1.02f, 0.01f, 1.25f, -0.43f);
        pathBuilderJ2.curveToRelative(0.94f, -1.79f, 2.42f, -3.26f, 4.21f, -4.21f);
        pathBuilderJ2.curveTo(7.8f, 2.91f, 7.98f, 2.36f, 7.79f, 1.89f);
        pathBuilderJ2.close();
        ImageVector.Builder.m4024addPathoIyEayM$default(builder, pathBuilderJ2.getNodes(), defaultFillType4, "", solidColor4, 1.0f, null, 1.0f, 1.0f, iM3803getButtKaPHkGw4, iM3813getBevelLxFBmk84, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null);
        int defaultFillType5 = VectorKt.getDefaultFillType();
        SolidColor solidColor5 = new SolidColor(companion.m3498getBlack0d7_KjU(), null);
        int iM3803getButtKaPHkGw5 = companion2.m3803getButtKaPHkGw();
        int iM3813getBevelLxFBmk85 = companion3.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderJ3 = a.j(1.89f, 16.21f, 1.89f, 16.21f);
        pathBuilderJ3.curveToRelative(-0.55f, 0.23f, -0.78f, 0.88f, -0.5f, 1.4f);
        pathBuilderJ3.curveToRelative(1.13f, 2.12f, 2.87f, 3.87f, 5.0f, 5.0f);
        pathBuilderJ3.curveToRelative(0.52f, 0.28f, 1.17f, 0.04f, 1.4f, -0.5f);
        pathBuilderJ3.lineToRelative(0.0f, 0.0f);
        pathBuilderJ3.curveToRelative(0.19f, -0.47f, 0.01f, -1.02f, -0.43f, -1.25f);
        pathBuilderJ3.curveToRelative(-1.79f, -0.94f, -3.26f, -2.42f, -4.21f, -4.21f);
        pathBuilderJ3.curveTo(2.91f, 16.2f, 2.36f, 16.02f, 1.89f, 16.21f);
        pathBuilderJ3.close();
        ImageVector.Builder.m4024addPathoIyEayM$default(builder, pathBuilderJ3.getNodes(), defaultFillType5, "", solidColor5, 1.0f, null, 1.0f, 1.0f, iM3803getButtKaPHkGw5, iM3813getBevelLxFBmk85, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null);
        int defaultFillType6 = VectorKt.getDefaultFillType();
        SolidColor solidColor6 = new SolidColor(companion.m3498getBlack0d7_KjU(), null);
        int iM3803getButtKaPHkGw6 = companion2.m3803getButtKaPHkGw();
        int iM3813getBevelLxFBmk86 = companion3.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderJ4 = a.j(16.21f, 22.11f, 16.21f, 22.11f);
        pathBuilderJ4.curveToRelative(0.23f, 0.55f, 0.88f, 0.78f, 1.4f, 0.5f);
        pathBuilderJ4.curveToRelative(2.12f, -1.13f, 3.87f, -2.87f, 5.0f, -5.0f);
        pathBuilderJ4.curveToRelative(0.28f, -0.52f, 0.04f, -1.17f, -0.5f, -1.4f);
        pathBuilderJ4.horizontalLineToRelative(0.0f);
        pathBuilderJ4.curveToRelative(-0.47f, -0.19f, -1.02f, -0.01f, -1.25f, 0.43f);
        pathBuilderJ4.curveToRelative(-0.94f, 1.79f, -2.42f, 3.26f, -4.21f, 4.21f);
        pathBuilderJ4.curveTo(16.2f, 21.09f, 16.02f, 21.64f, 16.21f, 22.11f);
        pathBuilderJ4.close();
        ImageVector imageVectorBuild = ImageVector.Builder.m4024addPathoIyEayM$default(builder, pathBuilderJ4.getNodes(), defaultFillType6, "", solidColor6, 1.0f, null, 1.0f, 1.0f, iM3803getButtKaPHkGw6, iM3813getBevelLxFBmk86, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null).build();
        _sensorOccupied = imageVectorBuild;
        return imageVectorBuild;
    }
}
