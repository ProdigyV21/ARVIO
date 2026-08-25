package androidx.compose.material.icons.outlined;

import a0.a;
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
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\"\u0010\u0010\u0000\u001a\u0004\u0018\u00010\u0001X\u0082\u000e¢\u0006\u0002\n\u0000\"\u0015\u0010\u0002\u001a\u00020\u0001*\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"_earbudsBattery", "Landroidx/compose/ui/graphics/vector/ImageVector;", "EarbudsBattery", "Landroidx/compose/material/icons/Icons$Outlined;", "getEarbudsBattery", "(Landroidx/compose/material/icons/Icons$Outlined;)Landroidx/compose/ui/graphics/vector/ImageVector;", "material-icons-extended_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class EarbudsBatteryKt {
    private static ImageVector _earbudsBattery;

    public static final ImageVector getEarbudsBattery(Icons.Outlined outlined) {
        ImageVector imageVector = _earbudsBattery;
        if (imageVector != null) {
            return imageVector;
        }
        ImageVector.Builder builder = new ImageVector.Builder("Outlined.EarbudsBattery", Dp.m5678constructorimpl(24.0f), Dp.m5678constructorimpl(24.0f), 24.0f, 24.0f, 0L, 0, false, 96, null);
        int defaultFillType = VectorKt.getDefaultFillType();
        SolidColor solidColor = new SolidColor(Color.INSTANCE.m3498getBlack0d7_KjU(), null);
        int iM3803getButtKaPHkGw = StrokeCap.INSTANCE.m3803getButtKaPHkGw();
        int iM3813getBevelLxFBmk8 = StrokeJoin.INSTANCE.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderU = a.u(21.0f, 7.0f, -1.0f, 6.0f, -2.0f);
        pathBuilderU.verticalLineToRelative(1.0f);
        pathBuilderU.horizontalLineToRelative(-1.0f);
        pathBuilderU.curveToRelative(-0.55f, 0.0f, -1.0f, 0.45f, -1.0f, 1.0f);
        pathBuilderU.verticalLineToRelative(9.0f);
        pathBuilderU.curveToRelative(0.0f, 0.55f, 0.45f, 1.0f, 1.0f, 1.0f);
        pathBuilderU.horizontalLineToRelative(4.0f);
        pathBuilderU.curveToRelative(0.55f, 0.0f, 1.0f, -0.45f, 1.0f, -1.0f);
        pathBuilderU.verticalLineTo(8.0f);
        pathBuilderU.curveTo(22.0f, 7.45f, 21.55f, 7.0f, 21.0f, 7.0f);
        v.a.u(pathBuilderU, 20.0f, 16.0f, -2.0f, 9.0f);
        c.D(pathBuilderU, 2.0f, 16.0f, 14.0f, 9.38f);
        pathBuilderU.curveTo(14.0f, 7.51f, 12.49f, 6.0f, 10.62f, 6.0f);
        pathBuilderU.reflectiveCurveTo(7.25f, 7.51f, 7.25f, 9.38f);
        pathBuilderU.verticalLineToRelative(5.25f);
        pathBuilderU.curveToRelative(0.0f, 1.04f, -0.84f, 1.88f, -1.88f, 1.88f);
        pathBuilderU.reflectiveCurveTo(3.5f, 15.66f, 3.5f, 14.62f);
        pathBuilderU.verticalLineToRelative(-4.7f);
        pathBuilderU.curveTo(3.66f, 9.97f, 3.83f, 10.0f, 4.0f, 10.0f);
        pathBuilderU.curveToRelative(1.1f, 0.0f, 2.0f, -0.9f, 2.0f, -2.0f);
        pathBuilderU.reflectiveCurveTo(5.1f, 6.0f, 4.0f, 6.0f);
        pathBuilderU.reflectiveCurveTo(2.0f, 6.9f, 2.0f, 8.0f);
        pathBuilderU.curveToRelative(0.0f, 0.04f, 0.0f, 6.62f, 0.0f, 6.62f);
        pathBuilderU.curveTo(2.0f, 16.49f, 3.51f, 18.0f, 5.38f, 18.0f);
        pathBuilderU.reflectiveCurveToRelative(3.38f, -1.51f, 3.38f, -3.38f);
        pathBuilderU.verticalLineTo(9.38f);
        pathBuilderU.curveToRelative(0.0f, -1.04f, 0.84f, -1.88f, 1.88f, -1.88f);
        pathBuilderU.reflectiveCurveToRelative(1.88f, 0.84f, 1.88f, 1.88f);
        pathBuilderU.verticalLineToRelative(4.7f);
        pathBuilderU.curveTo(12.34f, 14.03f, 12.17f, 14.0f, 12.0f, 14.0f);
        pathBuilderU.curveToRelative(-1.1f, 0.0f, -2.0f, 0.9f, -2.0f, 2.0f);
        pathBuilderU.reflectiveCurveToRelative(0.9f, 2.0f, 2.0f, 2.0f);
        pathBuilderU.reflectiveCurveToRelative(2.0f, -0.9f, 2.0f, -2.0f);
        pathBuilderU.curveTo(14.0f, 15.96f, 14.0f, 9.38f, 14.0f, 9.38f);
        pathBuilderU.close();
        ImageVector imageVectorBuild = ImageVector.Builder.m4024addPathoIyEayM$default(builder, pathBuilderU.getNodes(), defaultFillType, "", solidColor, 1.0f, null, 1.0f, 1.0f, iM3803getButtKaPHkGw, iM3813getBevelLxFBmk8, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null).build();
        _earbudsBattery = imageVectorBuild;
        return imageVectorBuild;
    }
}
