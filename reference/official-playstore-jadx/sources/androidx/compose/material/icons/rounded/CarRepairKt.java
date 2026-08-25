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
import v.f;
import y.a;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\"\u0010\u0010\u0000\u001a\u0004\u0018\u00010\u0001X\u0082\u000e¢\u0006\u0002\n\u0000\"\u0015\u0010\u0002\u001a\u00020\u0001*\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"_carRepair", "Landroidx/compose/ui/graphics/vector/ImageVector;", "CarRepair", "Landroidx/compose/material/icons/Icons$Rounded;", "getCarRepair", "(Landroidx/compose/material/icons/Icons$Rounded;)Landroidx/compose/ui/graphics/vector/ImageVector;", "material-icons-extended_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class CarRepairKt {
    private static ImageVector _carRepair;

    public static final ImageVector getCarRepair(Icons.Rounded rounded) {
        ImageVector imageVector = _carRepair;
        if (imageVector != null) {
            return imageVector;
        }
        ImageVector.Builder builder = new ImageVector.Builder("Rounded.CarRepair", Dp.m5678constructorimpl(24.0f), Dp.m5678constructorimpl(24.0f), 24.0f, 24.0f, 0L, 0, false, 96, null);
        int defaultFillType = VectorKt.getDefaultFillType();
        Color.Companion companion = Color.INSTANCE;
        SolidColor solidColor = new SolidColor(companion.m3498getBlack0d7_KjU(), null);
        StrokeCap.Companion companion2 = StrokeCap.INSTANCE;
        int iM3803getButtKaPHkGw = companion2.m3803getButtKaPHkGw();
        StrokeJoin.Companion companion3 = StrokeJoin.INSTANCE;
        int iM3813getBevelLxFBmk8 = companion3.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderU = a.u(7.0f, 15.0f, -1.0f, 10.0f, 1.0f);
        pathBuilderU.curveToRelative(0.0f, 0.55f, 0.45f, 1.0f, 1.0f, 1.0f);
        pathBuilderU.horizontalLineToRelative(0.0f);
        pathBuilderU.curveToRelative(0.55f, 0.0f, 1.0f, -0.45f, 1.0f, -1.0f);
        pathBuilderU.verticalLineTo(8.69f);
        pathBuilderU.curveToRelative(0.0f, 0.0f, -1.34f, -4.03f, -1.56f, -4.69f);
        pathBuilderU.curveToRelative(-0.05f, -0.16f, -0.12f, -0.29f, -0.19f, -0.4f);
        pathBuilderU.curveToRelative(-0.02f, -0.02f, -0.03f, -0.04f, -0.05f, -0.07f);
        pathBuilderU.curveTo(16.82f, 3.01f, 16.28f, 3.0f, 16.28f, 3.0f);
        pathBuilderU.horizontalLineTo(7.72f);
        pathBuilderU.curveToRelative(0.0f, 0.0f, -0.54f, 0.01f, -0.92f, 0.54f);
        pathBuilderU.curveTo(6.78f, 3.56f, 6.77f, 3.58f, 6.75f, 3.6f);
        pathBuilderU.curveTo(6.68f, 3.71f, 6.61f, 3.84f, 6.56f, 4.0f);
        pathBuilderU.curveTo(6.34f, 4.66f, 5.0f, 8.69f, 5.0f, 8.69f);
        pathBuilderU.verticalLineTo(15.0f);
        pathBuilderU.curveToRelative(0.0f, 0.55f, 0.45f, 1.0f, 1.0f, 1.0f);
        pathBuilderU.horizontalLineToRelative(0.0f);
        pathBuilderU.curveTo(6.55f, 16.0f, 7.0f, 15.55f, 7.0f, 15.0f);
        pathBuilderU.close();
        pathBuilderU.moveTo(9.0f, 11.5f);
        pathBuilderU.curveToRelative(-0.55f, 0.0f, -1.0f, -0.45f, -1.0f, -1.0f);
        pathBuilderU.reflectiveCurveToRelative(0.45f, -1.0f, 1.0f, -1.0f);
        pathBuilderU.reflectiveCurveToRelative(1.0f, 0.45f, 1.0f, 1.0f);
        pathBuilderU.reflectiveCurveTo(9.55f, 11.5f, 9.0f, 11.5f);
        pathBuilderU.close();
        pathBuilderU.moveTo(15.0f, 11.5f);
        pathBuilderU.curveToRelative(-0.55f, 0.0f, -1.0f, -0.45f, -1.0f, -1.0f);
        pathBuilderU.reflectiveCurveToRelative(0.45f, -1.0f, 1.0f, -1.0f);
        pathBuilderU.reflectiveCurveToRelative(1.0f, 0.45f, 1.0f, 1.0f);
        pathBuilderU.reflectiveCurveTo(15.55f, 11.5f, 15.0f, 11.5f);
        v.a.g(pathBuilderU, 8.33f, 5.0f, 7.34f);
        pathBuilderU.lineToRelative(0.23f, 0.69f);
        pathBuilderU.lineTo(16.33f, 7.0f);
        ImageVector.Builder.m4024addPathoIyEayM$default(builder, f.i(pathBuilderU, 7.67f, 8.33f, 5.0f), defaultFillType, "", solidColor, 1.0f, null, 1.0f, 1.0f, iM3803getButtKaPHkGw, iM3813getBevelLxFBmk8, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null);
        int defaultFillType2 = VectorKt.getDefaultFillType();
        SolidColor solidColor2 = new SolidColor(companion.m3498getBlack0d7_KjU(), null);
        int iM3803getButtKaPHkGw2 = companion2.m3803getButtKaPHkGw();
        int iM3813getBevelLxFBmk82 = companion3.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderJ = v.a.j(4.0f, 18.01f, 4.0f, 18.01f);
        pathBuilderJ.curveTo(4.0f, 18.55f, 4.45f, 19.0f, 4.99f, 19.0f);
        pathBuilderJ.horizontalLineTo(11.0f);
        pathBuilderJ.verticalLineToRelative(2.01f);
        pathBuilderJ.curveToRelative(0.0f, 0.55f, 0.45f, 0.99f, 0.99f, 0.99f);
        pathBuilderJ.horizontalLineToRelative(0.01f);
        pathBuilderJ.curveToRelative(0.55f, 0.0f, 0.99f, -0.45f, 0.99f, -0.99f);
        pathBuilderJ.verticalLineTo(19.0f);
        pathBuilderJ.horizontalLineToRelative(6.01f);
        pathBuilderJ.curveToRelative(0.55f, 0.0f, 0.99f, -0.45f, 0.99f, -0.99f);
        pathBuilderJ.verticalLineToRelative(0.0f);
        pathBuilderJ.curveToRelative(0.0f, -0.55f, -0.45f, -0.99f, -0.99f, -0.99f);
        pathBuilderJ.horizontalLineTo(4.99f);
        pathBuilderJ.curveTo(4.45f, 17.01f, 4.0f, 17.46f, 4.0f, 18.01f);
        pathBuilderJ.close();
        ImageVector imageVectorBuild = ImageVector.Builder.m4024addPathoIyEayM$default(builder, pathBuilderJ.getNodes(), defaultFillType2, "", solidColor2, 1.0f, null, 1.0f, 1.0f, iM3803getButtKaPHkGw2, iM3813getBevelLxFBmk82, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null).build();
        _carRepair = imageVectorBuild;
        return imageVectorBuild;
    }
}
