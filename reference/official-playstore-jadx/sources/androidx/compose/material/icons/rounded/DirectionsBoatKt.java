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
import v.e;
import w.a;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\"\u0010\u0010\u0000\u001a\u0004\u0018\u00010\u0001X\u0082\u000e¢\u0006\u0002\n\u0000\"\u0015\u0010\u0002\u001a\u00020\u0001*\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"_directionsBoat", "Landroidx/compose/ui/graphics/vector/ImageVector;", "DirectionsBoat", "Landroidx/compose/material/icons/Icons$Rounded;", "getDirectionsBoat", "(Landroidx/compose/material/icons/Icons$Rounded;)Landroidx/compose/ui/graphics/vector/ImageVector;", "material-icons-extended_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class DirectionsBoatKt {
    private static ImageVector _directionsBoat;

    public static final ImageVector getDirectionsBoat(Icons.Rounded rounded) {
        ImageVector imageVector = _directionsBoat;
        if (imageVector != null) {
            return imageVector;
        }
        ImageVector.Builder builder = new ImageVector.Builder("Rounded.DirectionsBoat", Dp.m5678constructorimpl(24.0f), Dp.m5678constructorimpl(24.0f), 24.0f, 24.0f, 0L, 0, false, 96, null);
        int defaultFillType = VectorKt.getDefaultFillType();
        SolidColor solidColor = new SolidColor(Color.INSTANCE.m3498getBlack0d7_KjU(), null);
        int iM3803getButtKaPHkGw = StrokeCap.INSTANCE.m3803getButtKaPHkGw();
        int iM3813getBevelLxFBmk8 = StrokeJoin.INSTANCE.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderA = c.a(20.0f, 21.0f);
        pathBuilderA.curveToRelative(-1.19f, 0.0f, -2.38f, -0.35f, -3.47f, -0.98f);
        pathBuilderA.curveToRelative(-0.33f, -0.19f, -0.73f, -0.19f, -1.07f, 0.0f);
        pathBuilderA.curveToRelative(-2.17f, 1.26f, -4.76f, 1.26f, -6.93f, 0.0f);
        pathBuilderA.curveToRelative(-0.33f, -0.19f, -0.73f, -0.19f, -1.07f, 0.0f);
        pathBuilderA.curveTo(6.38f, 20.65f, 5.19f, 21.0f, 4.0f, 21.0f);
        pathBuilderA.horizontalLineTo(3.0f);
        pathBuilderA.curveToRelative(-0.55f, 0.0f, -1.0f, 0.45f, -1.0f, 1.0f);
        pathBuilderA.verticalLineToRelative(0.0f);
        pathBuilderA.curveToRelative(0.0f, 0.55f, 0.45f, 1.0f, 1.0f, 1.0f);
        pathBuilderA.horizontalLineToRelative(1.0f);
        pathBuilderA.curveToRelative(1.38f, 0.0f, 2.74f, -0.35f, 4.0f, -0.99f);
        pathBuilderA.curveToRelative(2.52f, 1.29f, 5.48f, 1.29f, 8.0f, 0.0f);
        pathBuilderA.curveToRelative(1.26f, 0.65f, 2.62f, 0.99f, 4.0f, 0.99f);
        pathBuilderA.horizontalLineToRelative(1.0f);
        pathBuilderA.curveToRelative(0.55f, 0.0f, 1.0f, -0.45f, 1.0f, -1.0f);
        pathBuilderA.verticalLineToRelative(0.0f);
        pathBuilderA.curveToRelative(0.0f, -0.55f, -0.45f, -1.0f, -1.0f, -1.0f);
        a.s(pathBuilderA, 20.0f, 3.95f, 19.0f, 4.0f);
        pathBuilderA.curveToRelative(1.27f, 0.0f, 2.42f, -0.55f, 3.33f, -1.33f);
        pathBuilderA.curveToRelative(0.39f, -0.34f, 0.95f, -0.34f, 1.34f, 0.0f);
        pathBuilderA.curveTo(9.58f, 18.45f, 10.73f, 19.0f, 12.0f, 19.0f);
        pathBuilderA.reflectiveCurveToRelative(2.42f, -0.55f, 3.33f, -1.33f);
        pathBuilderA.curveToRelative(0.39f, -0.34f, 0.95f, -0.34f, 1.34f, 0.0f);
        pathBuilderA.curveTo(17.58f, 18.45f, 18.73f, 19.0f, 20.0f, 19.0f);
        pathBuilderA.horizontalLineToRelative(0.05f);
        pathBuilderA.lineToRelative(1.9f, -6.68f);
        pathBuilderA.curveToRelative(0.11f, -0.37f, 0.04f, -1.06f, -0.66f, -1.28f);
        pathBuilderA.lineTo(20.0f, 10.62f);
        pathBuilderA.verticalLineTo(6.0f);
        pathBuilderA.curveToRelative(0.0f, -1.1f, -0.9f, -2.0f, -2.0f, -2.0f);
        pathBuilderA.horizontalLineToRelative(-3.0f);
        pathBuilderA.verticalLineTo(2.0f);
        pathBuilderA.curveToRelative(0.0f, -0.55f, -0.45f, -1.0f, -1.0f, -1.0f);
        pathBuilderA.horizontalLineToRelative(-4.0f);
        pathBuilderA.curveTo(9.45f, 1.0f, 9.0f, 1.45f, 9.0f, 2.0f);
        pathBuilderA.verticalLineToRelative(2.0f);
        pathBuilderA.horizontalLineTo(6.0f);
        pathBuilderA.curveTo(4.9f, 4.0f, 4.0f, 4.9f, 4.0f, 6.0f);
        pathBuilderA.verticalLineToRelative(4.62f);
        pathBuilderA.lineToRelative(-1.29f, 0.42f);
        pathBuilderA.curveToRelative(-0.63f, 0.19f, -0.81f, 0.84f, -0.66f, 1.28f);
        c.z(pathBuilderA, 3.95f, 19.0f, 6.0f, 6.0f);
        c.q(pathBuilderA, 12.0f, 3.97f, 12.62f, 8.2f);
        pathBuilderA.curveToRelative(-0.41f, -0.13f, -0.84f, -0.13f, -1.25f, 0.0f);
        ImageVector imageVectorBuild = ImageVector.Builder.m4024addPathoIyEayM$default(builder, e.e(pathBuilderA, 6.0f, 9.97f, 6.0f), defaultFillType, "", solidColor, 1.0f, null, 1.0f, 1.0f, iM3803getButtKaPHkGw, iM3813getBevelLxFBmk8, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null).build();
        _directionsBoat = imageVectorBuild;
        return imageVectorBuild;
    }
}
