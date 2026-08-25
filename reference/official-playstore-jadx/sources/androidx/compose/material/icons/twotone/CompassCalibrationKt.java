package androidx.compose.material.icons.twotone;

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
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\"\u0010\u0010\u0000\u001a\u0004\u0018\u00010\u0001X\u0082\u000e¢\u0006\u0002\n\u0000\"\u0015\u0010\u0002\u001a\u00020\u0001*\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"_compassCalibration", "Landroidx/compose/ui/graphics/vector/ImageVector;", "CompassCalibration", "Landroidx/compose/material/icons/Icons$TwoTone;", "getCompassCalibration", "(Landroidx/compose/material/icons/Icons$TwoTone;)Landroidx/compose/ui/graphics/vector/ImageVector;", "material-icons-extended_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class CompassCalibrationKt {
    private static ImageVector _compassCalibration;

    public static final ImageVector getCompassCalibration(Icons.TwoTone twoTone) {
        ImageVector imageVector = _compassCalibration;
        if (imageVector != null) {
            return imageVector;
        }
        ImageVector.Builder builder = new ImageVector.Builder("TwoTone.CompassCalibration", Dp.m5678constructorimpl(24.0f), Dp.m5678constructorimpl(24.0f), 24.0f, 24.0f, 0L, 0, false, 96, null);
        int defaultFillType = VectorKt.getDefaultFillType();
        Color.Companion companion = Color.INSTANCE;
        SolidColor solidColor = new SolidColor(companion.m3498getBlack0d7_KjU(), null);
        StrokeCap.Companion companion2 = StrokeCap.INSTANCE;
        int iM3803getButtKaPHkGw = companion2.m3803getButtKaPHkGw();
        StrokeJoin.Companion companion3 = StrokeJoin.INSTANCE;
        int iM3813getBevelLxFBmk8 = companion3.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderR = a.r(4.94f, 7.26f, 2.21f, 2.21f);
        pathBuilderR.curveToRelative(1.44f, -0.91f, 3.11f, -1.4f, 4.85f, -1.4f);
        pathBuilderR.curveToRelative(1.74f, 0.0f, 3.41f, 0.49f, 4.84f, 1.4f);
        pathBuilderR.lineToRelative(2.21f, -2.21f);
        pathBuilderR.curveTo(17.0f, 5.79f, 14.56f, 5.0f, 12.0f, 5.0f);
        pathBuilderR.curveToRelative(-2.56f, 0.0f, -5.01f, 0.79f, -7.06f, 2.26f);
        pathBuilderR.close();
        ImageVector.Builder.m4024addPathoIyEayM$default(builder, pathBuilderR.getNodes(), defaultFillType, "", solidColor, 0.3f, null, 0.3f, 1.0f, iM3803getButtKaPHkGw, iM3813getBevelLxFBmk8, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null);
        int defaultFillType2 = VectorKt.getDefaultFillType();
        SolidColor solidColor2 = new SolidColor(companion.m3498getBlack0d7_KjU(), null);
        int iM3803getButtKaPHkGw2 = companion2.m3803getButtKaPHkGw();
        int iM3813getBevelLxFBmk82 = companion3.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderB = a.b(12.0f, 17.0f, -3.0f, 0.0f);
        pathBuilderB.arcToRelative(3.0f, 3.0f, 0.0f, true, true, 6.0f, 0.0f);
        pathBuilderB.arcToRelative(3.0f, 3.0f, 0.0f, true, true, -6.0f, 0.0f);
        ImageVector.Builder.m4024addPathoIyEayM$default(builder, pathBuilderB.getNodes(), defaultFillType2, "", solidColor2, 0.3f, null, 0.3f, 1.0f, iM3803getButtKaPHkGw2, iM3813getBevelLxFBmk82, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null);
        int defaultFillType3 = VectorKt.getDefaultFillType();
        SolidColor solidColor3 = new SolidColor(companion.m3498getBlack0d7_KjU(), null);
        int iM3803getButtKaPHkGw3 = companion2.m3803getButtKaPHkGw();
        int iM3813getBevelLxFBmk83 = companion3.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderA = c.a(17.0f, 17.0f);
        pathBuilderA.curveToRelative(0.0f, -2.76f, -2.24f, -5.0f, -5.0f, -5.0f);
        pathBuilderA.reflectiveCurveToRelative(-5.0f, 2.24f, -5.0f, 5.0f);
        pathBuilderA.reflectiveCurveToRelative(2.24f, 5.0f, 5.0f, 5.0f);
        pathBuilderA.reflectiveCurveToRelative(5.0f, -2.24f, 5.0f, -5.0f);
        pathBuilderA.close();
        pathBuilderA.moveTo(9.0f, 17.0f);
        pathBuilderA.curveToRelative(0.0f, -1.65f, 1.35f, -3.0f, 3.0f, -3.0f);
        pathBuilderA.reflectiveCurveToRelative(3.0f, 1.35f, 3.0f, 3.0f);
        pathBuilderA.reflectiveCurveToRelative(-1.35f, 3.0f, -3.0f, 3.0f);
        pathBuilderA.reflectiveCurveToRelative(-3.0f, -1.35f, -3.0f, -3.0f);
        a.z(pathBuilderA, 2.0f, 7.15f, 5.0f, 5.0f);
        pathBuilderA.curveToRelative(1.28f, -1.28f, 3.05f, -2.08f, 5.0f, -2.08f);
        pathBuilderA.reflectiveCurveToRelative(3.72f, 0.79f, 5.0f, 2.07f);
        pathBuilderA.lineToRelative(5.0f, -5.0f);
        pathBuilderA.curveTo(19.44f, 4.59f, 15.9f, 3.0f, 12.0f, 3.0f);
        pathBuilderA.curveTo(8.1f, 3.0f, 4.56f, 4.59f, 2.0f, 7.15f);
        pathBuilderA.close();
        pathBuilderA.moveTo(16.84f, 9.47f);
        pathBuilderA.curveToRelative(-1.44f, -0.91f, -3.1f, -1.4f, -4.84f, -1.4f);
        pathBuilderA.curveToRelative(-1.74f, 0.0f, -3.41f, 0.49f, -4.85f, 1.41f);
        pathBuilderA.lineTo(4.94f, 7.26f);
        pathBuilderA.curveTo(6.99f, 5.79f, 9.44f, 5.0f, 12.0f, 5.0f);
        pathBuilderA.curveToRelative(2.56f, 0.0f, 5.0f, 0.79f, 7.05f, 2.26f);
        ImageVector imageVectorBuild = ImageVector.Builder.m4024addPathoIyEayM$default(builder, c.c(pathBuilderA, -2.21f, 2.21f), defaultFillType3, "", solidColor3, 1.0f, null, 1.0f, 1.0f, iM3803getButtKaPHkGw3, iM3813getBevelLxFBmk83, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null).build();
        _compassCalibration = imageVectorBuild;
        return imageVectorBuild;
    }
}
