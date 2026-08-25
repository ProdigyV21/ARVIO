package androidx.compose.material.icons.sharp;

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
import v.d;
import v.e;
import v.f;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\"\u0010\u0010\u0000\u001a\u0004\u0018\u00010\u0001X\u0082\u000e¢\u0006\u0002\n\u0000\"\u0015\u0010\u0002\u001a\u00020\u0001*\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"_motionPhotosAuto", "Landroidx/compose/ui/graphics/vector/ImageVector;", "MotionPhotosAuto", "Landroidx/compose/material/icons/Icons$Sharp;", "getMotionPhotosAuto", "(Landroidx/compose/material/icons/Icons$Sharp;)Landroidx/compose/ui/graphics/vector/ImageVector;", "material-icons-extended_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class MotionPhotosAutoKt {
    private static ImageVector _motionPhotosAuto;

    public static final ImageVector getMotionPhotosAuto(Icons.Sharp sharp) {
        ImageVector imageVector = _motionPhotosAuto;
        if (imageVector != null) {
            return imageVector;
        }
        ImageVector.Builder builder = new ImageVector.Builder("Sharp.MotionPhotosAuto", Dp.m5678constructorimpl(24.0f), Dp.m5678constructorimpl(24.0f), 24.0f, 24.0f, 0L, 0, false, 96, null);
        int defaultFillType = VectorKt.getDefaultFillType();
        SolidColor solidColor = new SolidColor(Color.INSTANCE.m3498getBlack0d7_KjU(), null);
        int iM3803getButtKaPHkGw = StrokeCap.INSTANCE.m3803getButtKaPHkGw();
        int iM3813getBevelLxFBmk8 = StrokeJoin.INSTANCE.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderR = a.r(2.88f, 7.88f, 1.54f, 1.54f);
        pathBuilderR.curveTo(4.15f, 10.23f, 4.0f, 11.1f, 4.0f, 12.0f);
        pathBuilderR.curveToRelative(0.0f, 4.41f, 3.59f, 8.0f, 8.0f, 8.0f);
        pathBuilderR.reflectiveCurveToRelative(8.0f, -3.59f, 8.0f, -8.0f);
        pathBuilderR.reflectiveCurveToRelative(-3.59f, -8.0f, -8.0f, -8.0f);
        pathBuilderR.curveToRelative(-0.9f, 0.0f, -1.77f, 0.15f, -2.58f, 0.42f);
        pathBuilderR.lineTo(7.89f, 2.89f);
        pathBuilderR.curveTo(9.15f, 2.32f, 10.54f, 2.0f, 12.0f, 2.0f);
        pathBuilderR.curveToRelative(5.52f, 0.0f, 10.0f, 4.48f, 10.0f, 10.0f);
        pathBuilderR.reflectiveCurveToRelative(-4.48f, 10.0f, -10.0f, 10.0f);
        pathBuilderR.reflectiveCurveTo(2.0f, 17.52f, 2.0f, 12.0f);
        pathBuilderR.curveTo(2.0f, 10.53f, 2.32f, 9.14f, 2.88f, 7.88f);
        pathBuilderR.close();
        pathBuilderR.moveTo(7.0f, 5.5f);
        pathBuilderR.curveTo(7.0f, 6.33f, 6.33f, 7.0f, 5.5f, 7.0f);
        pathBuilderR.reflectiveCurveTo(4.0f, 6.33f, 4.0f, 5.5f);
        pathBuilderR.reflectiveCurveTo(4.67f, 4.0f, 5.5f, 4.0f);
        pathBuilderR.reflectiveCurveTo(7.0f, 4.67f, 7.0f, 5.5f);
        a.g(pathBuilderR, 12.03f, 8.99f, -0.07f);
        pathBuilderR.lineToRelative(-1.16f, 3.31f);
        d.q(pathBuilderR, 2.39f, 12.03f, 8.99f);
        pathBuilderR.moveTo(12.0f, 18.0f);
        pathBuilderR.curveToRelative(3.31f, 0.0f, 6.0f, -2.69f, 6.0f, -6.0f);
        pathBuilderR.reflectiveCurveToRelative(-2.69f, -6.0f, -6.0f, -6.0f);
        pathBuilderR.reflectiveCurveToRelative(-6.0f, 2.69f, -6.0f, 6.0f);
        pathBuilderR.reflectiveCurveTo(8.69f, 18.0f, 12.0f, 18.0f);
        a.g(pathBuilderR, 11.29f, 7.5f, 1.43f);
        pathBuilderR.lineToRelative(3.01f, 8.0f);
        e.C(pathBuilderR, -1.39f, -0.72f, -2.04f, -3.23f);
        pathBuilderR.lineTo(9.66f, 15.5f);
        ImageVector imageVectorBuild = ImageVector.Builder.m4024addPathoIyEayM$default(builder, f.i(pathBuilderR, 8.28f, 11.29f, 7.5f), defaultFillType, "", solidColor, 1.0f, null, 1.0f, 1.0f, iM3803getButtKaPHkGw, iM3813getBevelLxFBmk8, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null).build();
        _motionPhotosAuto = imageVectorBuild;
        return imageVectorBuild;
    }
}
