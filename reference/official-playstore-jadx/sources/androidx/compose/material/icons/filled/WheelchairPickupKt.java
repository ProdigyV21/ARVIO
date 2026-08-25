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
import v.b;
import v.c;
import w.a;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\"\u0010\u0010\u0000\u001a\u0004\u0018\u00010\u0001X\u0082\u000e¢\u0006\u0002\n\u0000\"\u0015\u0010\u0002\u001a\u00020\u0001*\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"_wheelchairPickup", "Landroidx/compose/ui/graphics/vector/ImageVector;", "WheelchairPickup", "Landroidx/compose/material/icons/Icons$Filled;", "getWheelchairPickup", "(Landroidx/compose/material/icons/Icons$Filled;)Landroidx/compose/ui/graphics/vector/ImageVector;", "material-icons-extended_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class WheelchairPickupKt {
    private static ImageVector _wheelchairPickup;

    public static final ImageVector getWheelchairPickup(Icons.Filled filled) {
        ImageVector imageVector = _wheelchairPickup;
        if (imageVector != null) {
            return imageVector;
        }
        ImageVector.Builder builder = new ImageVector.Builder("Filled.WheelchairPickup", Dp.m5678constructorimpl(24.0f), Dp.m5678constructorimpl(24.0f), 24.0f, 24.0f, 0L, 0, false, 96, null);
        int defaultFillType = VectorKt.getDefaultFillType();
        SolidColor solidColor = new SolidColor(Color.INSTANCE.m3498getBlack0d7_KjU(), null);
        int iM3803getButtKaPHkGw = StrokeCap.INSTANCE.m3803getButtKaPHkGw();
        int iM3813getBevelLxFBmk8 = StrokeJoin.INSTANCE.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderA = c.a(4.5f, 4.0f);
        pathBuilderA.curveToRelative(0.0f, -1.11f, 0.89f, -2.0f, 2.0f, -2.0f);
        pathBuilderA.reflectiveCurveToRelative(2.0f, 0.89f, 2.0f, 2.0f);
        pathBuilderA.reflectiveCurveToRelative(-0.89f, 2.0f, -2.0f, 2.0f);
        pathBuilderA.reflectiveCurveTo(4.5f, 5.11f, 4.5f, 4.0f);
        a.u(pathBuilderA, 10.0f, 10.95f, 9.0f);
        pathBuilderA.curveToRelative(0.0f, -1.1f, -0.9f, -2.0f, -2.0f, -2.0f);
        pathBuilderA.horizontalLineTo(5.0f);
        pathBuilderA.curveTo(3.9f, 7.0f, 3.0f, 7.9f, 3.0f, 9.0f);
        b.z(pathBuilderA, 6.0f, 2.0f, 7.0f, 3.5f);
        pathBuilderA.verticalLineToRelative(-0.11f);
        pathBuilderA.curveToRelative(-1.24f, -1.26f, -2.0f, -2.99f, -2.0f, -4.89f);
        pathBuilderA.curveTo(6.5f, 14.42f, 7.91f, 12.16f, 10.0f, 10.95f);
        pathBuilderA.close();
        pathBuilderA.moveTo(16.5f, 17.0f);
        pathBuilderA.curveToRelative(0.0f, 1.65f, -1.35f, 3.0f, -3.0f, 3.0f);
        pathBuilderA.reflectiveCurveToRelative(-3.0f, -1.35f, -3.0f, -3.0f);
        pathBuilderA.curveToRelative(0.0f, -1.11f, 0.61f, -2.06f, 1.5f, -2.58f);
        pathBuilderA.verticalLineToRelative(-2.16f);
        pathBuilderA.curveTo(9.98f, 12.9f, 8.5f, 14.77f, 8.5f, 17.0f);
        pathBuilderA.curveToRelative(0.0f, 2.76f, 2.24f, 5.0f, 5.0f, 5.0f);
        pathBuilderA.reflectiveCurveToRelative(5.0f, -2.24f, 5.0f, -5.0f);
        a.s(pathBuilderA, 16.5f, 19.54f, 14.0f, 15.0f);
        b.r(pathBuilderA, 8.0f, -2.0f, 8.0f, 5.46f);
        pathBuilderA.lineToRelative(2.47f, 3.71f);
        ImageVector imageVectorBuild = ImageVector.Builder.m4024addPathoIyEayM$default(builder, b.e(pathBuilderA, 1.66f, -1.11f, 19.54f, 14.0f), defaultFillType, "", solidColor, 1.0f, null, 1.0f, 1.0f, iM3803getButtKaPHkGw, iM3813getBevelLxFBmk8, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null).build();
        _wheelchairPickup = imageVectorBuild;
        return imageVectorBuild;
    }
}
