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
import v.c;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\"\u0010\u0010\u0000\u001a\u0004\u0018\u00010\u0001X\u0082\u000e¢\u0006\u0002\n\u0000\"\u0015\u0010\u0002\u001a\u00020\u0001*\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"_earbudsBattery", "Landroidx/compose/ui/graphics/vector/ImageVector;", "EarbudsBattery", "Landroidx/compose/material/icons/Icons$Rounded;", "getEarbudsBattery", "(Landroidx/compose/material/icons/Icons$Rounded;)Landroidx/compose/ui/graphics/vector/ImageVector;", "material-icons-extended_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class EarbudsBatteryKt {
    private static ImageVector _earbudsBattery;

    public static final ImageVector getEarbudsBattery(Icons.Rounded rounded) {
        ImageVector imageVector = _earbudsBattery;
        if (imageVector != null) {
            return imageVector;
        }
        ImageVector.Builder builder = new ImageVector.Builder("Rounded.EarbudsBattery", Dp.m5678constructorimpl(24.0f), Dp.m5678constructorimpl(24.0f), 24.0f, 24.0f, 0L, 0, false, 96, null);
        int defaultFillType = VectorKt.getDefaultFillType();
        SolidColor solidColor = new SolidColor(Color.INSTANCE.m3498getBlack0d7_KjU(), null);
        int iM3803getButtKaPHkGw = StrokeCap.INSTANCE.m3803getButtKaPHkGw();
        int iM3813getBevelLxFBmk8 = StrokeJoin.INSTANCE.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderG = c.g(21.0f, 7.0f, -1.0f, 0.0f, 0.0f);
        pathBuilderG.verticalLineTo(6.5f);
        pathBuilderG.curveTo(20.0f, 6.22f, 19.78f, 6.0f, 19.5f, 6.0f);
        pathBuilderG.lineToRelative(-1.0f, 0.0f);
        pathBuilderG.curveTo(18.22f, 6.0f, 18.0f, 6.22f, 18.0f, 6.5f);
        pathBuilderG.verticalLineTo(7.0f);
        pathBuilderG.lineToRelative(0.0f, 0.0f);
        pathBuilderG.horizontalLineToRelative(-1.0f);
        pathBuilderG.curveToRelative(-0.55f, 0.0f, -1.0f, 0.45f, -1.0f, 1.0f);
        pathBuilderG.verticalLineToRelative(9.0f);
        pathBuilderG.curveToRelative(0.0f, 0.55f, 0.45f, 1.0f, 1.0f, 1.0f);
        pathBuilderG.horizontalLineToRelative(4.0f);
        pathBuilderG.curveToRelative(0.55f, 0.0f, 1.0f, -0.45f, 1.0f, -1.0f);
        pathBuilderG.verticalLineTo(8.0f);
        pathBuilderG.curveTo(22.0f, 7.45f, 21.55f, 7.0f, 21.0f, 7.0f);
        pathBuilderG.close();
        pathBuilderG.moveTo(14.0f, 9.38f);
        pathBuilderG.curveTo(14.0f, 7.51f, 12.49f, 6.0f, 10.62f, 6.0f);
        pathBuilderG.reflectiveCurveTo(7.25f, 7.51f, 7.25f, 9.38f);
        pathBuilderG.verticalLineToRelative(5.25f);
        pathBuilderG.curveToRelative(0.0f, 1.04f, -0.84f, 1.88f, -1.88f, 1.88f);
        pathBuilderG.reflectiveCurveTo(3.5f, 15.66f, 3.5f, 14.62f);
        pathBuilderG.verticalLineToRelative(-4.7f);
        pathBuilderG.curveTo(3.66f, 9.97f, 3.83f, 10.0f, 4.0f, 10.0f);
        pathBuilderG.curveToRelative(1.1f, 0.0f, 2.0f, -0.9f, 2.0f, -2.0f);
        pathBuilderG.reflectiveCurveTo(5.1f, 6.0f, 4.0f, 6.0f);
        pathBuilderG.reflectiveCurveTo(2.0f, 6.9f, 2.0f, 8.0f);
        pathBuilderG.curveToRelative(0.0f, 0.04f, 0.0f, 6.62f, 0.0f, 6.62f);
        pathBuilderG.curveTo(2.0f, 16.49f, 3.51f, 18.0f, 5.38f, 18.0f);
        pathBuilderG.reflectiveCurveToRelative(3.38f, -1.51f, 3.38f, -3.38f);
        pathBuilderG.verticalLineTo(9.38f);
        pathBuilderG.curveToRelative(0.0f, -1.04f, 0.84f, -1.88f, 1.88f, -1.88f);
        pathBuilderG.reflectiveCurveToRelative(1.88f, 0.84f, 1.88f, 1.88f);
        pathBuilderG.verticalLineToRelative(4.7f);
        pathBuilderG.curveTo(12.34f, 14.03f, 12.17f, 14.0f, 12.0f, 14.0f);
        pathBuilderG.curveToRelative(-1.1f, 0.0f, -2.0f, 0.9f, -2.0f, 2.0f);
        pathBuilderG.reflectiveCurveToRelative(0.9f, 2.0f, 2.0f, 2.0f);
        pathBuilderG.reflectiveCurveToRelative(2.0f, -0.9f, 2.0f, -2.0f);
        pathBuilderG.curveTo(14.0f, 15.96f, 14.0f, 9.38f, 14.0f, 9.38f);
        pathBuilderG.close();
        ImageVector imageVectorBuild = ImageVector.Builder.m4024addPathoIyEayM$default(builder, pathBuilderG.getNodes(), defaultFillType, "", solidColor, 1.0f, null, 1.0f, 1.0f, iM3803getButtKaPHkGw, iM3813getBevelLxFBmk8, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null).build();
        _earbudsBattery = imageVectorBuild;
        return imageVectorBuild;
    }
}
