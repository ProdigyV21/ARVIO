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
import v.f;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\"\u0010\u0010\u0000\u001a\u0004\u0018\u00010\u0001X\u0082\u000e¢\u0006\u0002\n\u0000\"\u0015\u0010\u0002\u001a\u00020\u0001*\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"_multipleStop", "Landroidx/compose/ui/graphics/vector/ImageVector;", "MultipleStop", "Landroidx/compose/material/icons/Icons$Rounded;", "getMultipleStop", "(Landroidx/compose/material/icons/Icons$Rounded;)Landroidx/compose/ui/graphics/vector/ImageVector;", "material-icons-extended_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class MultipleStopKt {
    private static ImageVector _multipleStop;

    public static final ImageVector getMultipleStop(Icons.Rounded rounded) {
        ImageVector imageVector = _multipleStop;
        if (imageVector != null) {
            return imageVector;
        }
        ImageVector.Builder builder = new ImageVector.Builder("Rounded.MultipleStop", Dp.m5678constructorimpl(24.0f), Dp.m5678constructorimpl(24.0f), 24.0f, 24.0f, 0L, 0, false, 96, null);
        int defaultFillType = VectorKt.getDefaultFillType();
        SolidColor solidColor = new SolidColor(Color.INSTANCE.m3498getBlack0d7_KjU(), null);
        int iM3803getButtKaPHkGw = StrokeCap.INSTANCE.m3803getButtKaPHkGw();
        int iM3813getBevelLxFBmk8 = StrokeJoin.INSTANCE.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderA = c.a(17.0f, 5.21f);
        pathBuilderA.curveToRelative(0.0f, -0.45f, 0.54f, -0.67f, 0.85f, -0.35f);
        pathBuilderA.lineToRelative(2.79f, 2.79f);
        pathBuilderA.curveToRelative(0.2f, 0.2f, 0.2f, 0.51f, 0.0f, 0.71f);
        pathBuilderA.lineToRelative(-2.79f, 2.79f);
        pathBuilderA.curveTo(17.54f, 11.46f, 17.0f, 11.24f, 17.0f, 10.79f);
        pathBuilderA.verticalLineTo(9.0f);
        pathBuilderA.horizontalLineToRelative(-3.0f);
        pathBuilderA.curveToRelative(-0.55f, 0.0f, -1.0f, -0.45f, -1.0f, -1.0f);
        pathBuilderA.verticalLineToRelative(0.0f);
        pathBuilderA.curveToRelative(0.0f, -0.55f, 0.45f, -1.0f, 1.0f, -1.0f);
        c.D(pathBuilderA, 3.0f, 5.21f, 10.0f, 7.0f);
        pathBuilderA.curveTo(9.45f, 7.0f, 9.0f, 7.45f, 9.0f, 8.0f);
        pathBuilderA.reflectiveCurveToRelative(0.45f, 1.0f, 1.0f, 1.0f);
        pathBuilderA.reflectiveCurveToRelative(1.0f, -0.45f, 1.0f, -1.0f);
        pathBuilderA.reflectiveCurveTo(10.55f, 7.0f, 10.0f, 7.0f);
        pathBuilderA.close();
        pathBuilderA.moveTo(6.0f, 7.0f);
        pathBuilderA.curveTo(5.45f, 7.0f, 5.0f, 7.45f, 5.0f, 8.0f);
        pathBuilderA.reflectiveCurveToRelative(0.45f, 1.0f, 1.0f, 1.0f);
        pathBuilderA.reflectiveCurveToRelative(1.0f, -0.45f, 1.0f, -1.0f);
        pathBuilderA.reflectiveCurveTo(6.55f, 7.0f, 6.0f, 7.0f);
        a.g(pathBuilderA, 7.0f, 17.0f, 3.0f);
        pathBuilderA.curveToRelative(0.55f, 0.0f, 1.0f, -0.45f, 1.0f, -1.0f);
        pathBuilderA.verticalLineToRelative(0.0f);
        pathBuilderA.curveToRelative(0.0f, -0.55f, -0.45f, -1.0f, -1.0f, -1.0f);
        pathBuilderA.horizontalLineTo(7.0f);
        pathBuilderA.verticalLineToRelative(-1.79f);
        pathBuilderA.curveToRelative(0.0f, -0.45f, -0.54f, -0.67f, -0.85f, -0.35f);
        pathBuilderA.lineToRelative(-2.79f, 2.79f);
        pathBuilderA.curveToRelative(-0.2f, 0.2f, -0.2f, 0.51f, 0.0f, 0.71f);
        pathBuilderA.lineToRelative(2.79f, 2.79f);
        pathBuilderA.curveTo(6.46f, 19.46f, 7.0f, 19.24f, 7.0f, 18.79f);
        f.r(pathBuilderA, 17.0f, 14.0f, 17.0f);
        pathBuilderA.curveToRelative(0.55f, 0.0f, 1.0f, -0.45f, 1.0f, -1.0f);
        pathBuilderA.curveToRelative(0.0f, -0.55f, -0.45f, -1.0f, -1.0f, -1.0f);
        pathBuilderA.reflectiveCurveToRelative(-1.0f, 0.45f, -1.0f, 1.0f);
        pathBuilderA.curveTo(13.0f, 16.55f, 13.45f, 17.0f, 14.0f, 17.0f);
        pathBuilderA.close();
        pathBuilderA.moveTo(18.0f, 17.0f);
        pathBuilderA.curveToRelative(0.55f, 0.0f, 1.0f, -0.45f, 1.0f, -1.0f);
        pathBuilderA.curveToRelative(0.0f, -0.55f, -0.45f, -1.0f, -1.0f, -1.0f);
        pathBuilderA.reflectiveCurveToRelative(-1.0f, 0.45f, -1.0f, 1.0f);
        pathBuilderA.curveTo(17.0f, 16.55f, 17.45f, 17.0f, 18.0f, 17.0f);
        pathBuilderA.close();
        ImageVector imageVectorBuild = ImageVector.Builder.m4024addPathoIyEayM$default(builder, pathBuilderA.getNodes(), defaultFillType, "", solidColor, 1.0f, null, 1.0f, 1.0f, iM3803getButtKaPHkGw, iM3813getBevelLxFBmk8, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null).build();
        _multipleStop = imageVectorBuild;
        return imageVectorBuild;
    }
}
