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
import v.c;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\"\u0010\u0010\u0000\u001a\u0004\u0018\u00010\u0001X\u0082\u000e¢\u0006\u0002\n\u0000\"\u0015\u0010\u0002\u001a\u00020\u0001*\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"_pattern", "Landroidx/compose/ui/graphics/vector/ImageVector;", "Pattern", "Landroidx/compose/material/icons/Icons$Filled;", "getPattern", "(Landroidx/compose/material/icons/Icons$Filled;)Landroidx/compose/ui/graphics/vector/ImageVector;", "material-icons-extended_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class PatternKt {
    private static ImageVector _pattern;

    public static final ImageVector getPattern(Icons.Filled filled) {
        ImageVector imageVector = _pattern;
        if (imageVector != null) {
            return imageVector;
        }
        ImageVector.Builder builder = new ImageVector.Builder("Filled.Pattern", Dp.m5678constructorimpl(24.0f), Dp.m5678constructorimpl(24.0f), 24.0f, 24.0f, 0L, 0, false, 96, null);
        int defaultFillType = VectorKt.getDefaultFillType();
        SolidColor solidColor = new SolidColor(Color.INSTANCE.m3498getBlack0d7_KjU(), null);
        int iM3803getButtKaPHkGw = StrokeCap.INSTANCE.m3803getButtKaPHkGw();
        int iM3813getBevelLxFBmk8 = StrokeJoin.INSTANCE.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderA = c.a(4.0f, 6.0f);
        pathBuilderA.curveToRelative(0.0f, -1.1f, 0.9f, -2.0f, 2.0f, -2.0f);
        pathBuilderA.reflectiveCurveToRelative(2.0f, 0.9f, 2.0f, 2.0f);
        pathBuilderA.reflectiveCurveTo(7.1f, 8.0f, 6.0f, 8.0f);
        pathBuilderA.reflectiveCurveTo(4.0f, 7.1f, 4.0f, 6.0f);
        pathBuilderA.close();
        pathBuilderA.moveTo(6.0f, 14.0f);
        pathBuilderA.curveToRelative(1.1f, 0.0f, 2.0f, -0.9f, 2.0f, -2.0f);
        pathBuilderA.reflectiveCurveToRelative(-0.9f, -2.0f, -2.0f, -2.0f);
        pathBuilderA.reflectiveCurveToRelative(-2.0f, 0.9f, -2.0f, 2.0f);
        pathBuilderA.reflectiveCurveTo(4.9f, 14.0f, 6.0f, 14.0f);
        pathBuilderA.close();
        pathBuilderA.moveTo(12.0f, 8.0f);
        pathBuilderA.curveToRelative(1.1f, 0.0f, 2.0f, -0.9f, 2.0f, -2.0f);
        pathBuilderA.reflectiveCurveToRelative(-0.9f, -2.0f, -2.0f, -2.0f);
        pathBuilderA.reflectiveCurveToRelative(-2.0f, 0.9f, -2.0f, 2.0f);
        pathBuilderA.reflectiveCurveTo(10.9f, 8.0f, 12.0f, 8.0f);
        pathBuilderA.close();
        pathBuilderA.moveTo(18.0f, 10.0f);
        pathBuilderA.curveToRelative(-1.1f, 0.0f, -2.0f, 0.9f, -2.0f, 2.0f);
        pathBuilderA.reflectiveCurveToRelative(0.9f, 2.0f, 2.0f, 2.0f);
        pathBuilderA.reflectiveCurveToRelative(2.0f, -0.9f, 2.0f, -2.0f);
        pathBuilderA.reflectiveCurveTo(19.1f, 10.0f, 18.0f, 10.0f);
        pathBuilderA.close();
        pathBuilderA.moveTo(17.98f, 16.0f);
        pathBuilderA.curveToRelative(-0.74f, 0.0f, -1.37f, 0.4f, -1.72f, 1.0f);
        pathBuilderA.horizontalLineToRelative(-2.54f);
        pathBuilderA.curveToRelative(-0.34f, -0.6f, -0.98f, -1.0f, -1.72f, -1.0f);
        pathBuilderA.reflectiveCurveToRelative(-1.37f, 0.4f, -1.72f, 1.0f);
        pathBuilderA.horizontalLineTo(8.41f);
        pathBuilderA.lineToRelative(3.07f, -3.07f);
        pathBuilderA.curveTo(11.65f, 13.97f, 11.82f, 14.0f, 12.0f, 14.0f);
        pathBuilderA.curveToRelative(1.1f, 0.0f, 2.0f, -0.9f, 2.0f, -2.0f);
        pathBuilderA.curveToRelative(0.0f, -0.18f, -0.03f, -0.35f, -0.07f, -0.51f);
        pathBuilderA.lineToRelative(3.56f, -3.56f);
        pathBuilderA.curveTo(17.65f, 7.97f, 17.82f, 8.0f, 18.0f, 8.0f);
        pathBuilderA.curveToRelative(1.1f, 0.0f, 2.0f, -0.9f, 2.0f, -2.0f);
        pathBuilderA.reflectiveCurveToRelative(-0.9f, -2.0f, -2.0f, -2.0f);
        pathBuilderA.reflectiveCurveToRelative(-2.0f, 0.9f, -2.0f, 2.0f);
        pathBuilderA.curveToRelative(0.0f, 0.18f, 0.03f, 0.35f, 0.07f, 0.51f);
        pathBuilderA.lineToRelative(-3.56f, 3.56f);
        pathBuilderA.curveTo(12.35f, 10.03f, 12.18f, 10.0f, 12.0f, 10.0f);
        pathBuilderA.curveToRelative(-1.1f, 0.0f, -2.0f, 0.9f, -2.0f, 2.0f);
        pathBuilderA.curveToRelative(0.0f, 0.18f, 0.03f, 0.35f, 0.07f, 0.51f);
        pathBuilderA.lineToRelative(-3.56f, 3.56f);
        pathBuilderA.curveTo(6.35f, 16.03f, 6.18f, 16.0f, 6.0f, 16.0f);
        pathBuilderA.curveToRelative(-1.1f, 0.0f, -2.0f, 0.9f, -2.0f, 2.0f);
        pathBuilderA.reflectiveCurveToRelative(0.9f, 2.0f, 2.0f, 2.0f);
        pathBuilderA.curveToRelative(0.74f, 0.0f, 1.37f, -0.4f, 1.72f, -1.0f);
        pathBuilderA.horizontalLineToRelative(2.57f);
        pathBuilderA.curveToRelative(0.34f, 0.6f, 0.98f, 1.0f, 1.72f, 1.0f);
        pathBuilderA.reflectiveCurveToRelative(1.37f, -0.4f, 1.72f, -1.0f);
        pathBuilderA.horizontalLineToRelative(2.55f);
        pathBuilderA.curveToRelative(0.34f, 0.6f, 0.98f, 1.0f, 1.72f, 1.0f);
        pathBuilderA.curveToRelative(1.1f, 0.0f, 2.0f, -0.9f, 2.0f, -2.0f);
        pathBuilderA.curveTo(19.98f, 16.9f, 19.08f, 16.0f, 17.98f, 16.0f);
        pathBuilderA.close();
        ImageVector imageVectorBuild = ImageVector.Builder.m4024addPathoIyEayM$default(builder, pathBuilderA.getNodes(), defaultFillType, "", solidColor, 1.0f, null, 1.0f, 1.0f, iM3803getButtKaPHkGw, iM3813getBevelLxFBmk8, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null).build();
        _pattern = imageVectorBuild;
        return imageVectorBuild;
    }
}
