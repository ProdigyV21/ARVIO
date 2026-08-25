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
import v.b;
import v.c;
import v.d;
import v.e;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\"\u0010\u0010\u0000\u001a\u0004\u0018\u00010\u0001X\u0082\u000e¢\u0006\u0002\n\u0000\"\u0015\u0010\u0002\u001a\u00020\u0001*\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"_directionsBoatFilled", "Landroidx/compose/ui/graphics/vector/ImageVector;", "DirectionsBoatFilled", "Landroidx/compose/material/icons/Icons$Sharp;", "getDirectionsBoatFilled", "(Landroidx/compose/material/icons/Icons$Sharp;)Landroidx/compose/ui/graphics/vector/ImageVector;", "material-icons-extended_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class DirectionsBoatFilledKt {
    private static ImageVector _directionsBoatFilled;

    public static final ImageVector getDirectionsBoatFilled(Icons.Sharp sharp) {
        ImageVector imageVector = _directionsBoatFilled;
        if (imageVector != null) {
            return imageVector;
        }
        ImageVector.Builder builder = new ImageVector.Builder("Sharp.DirectionsBoatFilled", Dp.m5678constructorimpl(24.0f), Dp.m5678constructorimpl(24.0f), 24.0f, 24.0f, 0L, 0, false, 96, null);
        int defaultFillType = VectorKt.getDefaultFillType();
        SolidColor solidColor = new SolidColor(Color.INSTANCE.m3498getBlack0d7_KjU(), null);
        int iM3803getButtKaPHkGw = StrokeCap.INSTANCE.m3803getButtKaPHkGw();
        int iM3813getBevelLxFBmk8 = StrokeJoin.INSTANCE.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderA = c.a(20.0f, 21.0f);
        pathBuilderA.curveToRelative(-1.39f, 0.0f, -2.78f, -0.47f, -4.0f, -1.32f);
        pathBuilderA.curveToRelative(-2.44f, 1.71f, -5.56f, 1.71f, -8.0f, 0.0f);
        pathBuilderA.curveTo(6.78f, 20.53f, 5.39f, 21.0f, 4.0f, 21.0f);
        a.n(pathBuilderA, 2.0f, 2.0f, 2.0f);
        pathBuilderA.curveToRelative(1.38f, 0.0f, 2.74f, -0.35f, 4.0f, -0.99f);
        pathBuilderA.curveToRelative(2.52f, 1.29f, 5.48f, 1.29f, 8.0f, 0.0f);
        pathBuilderA.curveToRelative(1.26f, 0.65f, 2.62f, 0.99f, 4.0f, 0.99f);
        y.a.q(pathBuilderA, 2.0f, -2.0f, 20.0f);
        pathBuilderA.moveTo(3.95f, 19.0f);
        pathBuilderA.horizontalLineTo(4.0f);
        pathBuilderA.curveToRelative(1.6f, 0.0f, 3.02f, -0.88f, 4.0f, -2.0f);
        pathBuilderA.curveToRelative(0.98f, 1.12f, 2.4f, 2.0f, 4.0f, 2.0f);
        pathBuilderA.reflectiveCurveToRelative(3.02f, -0.88f, 4.0f, -2.0f);
        pathBuilderA.curveToRelative(0.98f, 1.12f, 2.4f, 2.0f, 4.0f, 2.0f);
        pathBuilderA.horizontalLineToRelative(0.05f);
        pathBuilderA.lineToRelative(2.18f, -7.65f);
        b.n(pathBuilderA, 20.0f, 10.62f, 4.0f, -5.0f);
        pathBuilderA.verticalLineTo(1.0f);
        pathBuilderA.horizontalLineTo(9.0f);
        pathBuilderA.verticalLineToRelative(3.0f);
        pathBuilderA.horizontalLineTo(4.0f);
        pathBuilderA.verticalLineToRelative(6.62f);
        d.C(pathBuilderA, -2.23f, 0.73f, 3.95f, 19.0f);
        a.C(pathBuilderA, 6.0f, 6.0f, 12.0f, 3.97f);
        pathBuilderA.lineTo(12.0f, 8.0f);
        ImageVector imageVectorBuild = ImageVector.Builder.m4024addPathoIyEayM$default(builder, e.e(pathBuilderA, 6.0f, 9.97f, 6.0f), defaultFillType, "", solidColor, 1.0f, null, 1.0f, 1.0f, iM3803getButtKaPHkGw, iM3813getBevelLxFBmk8, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null).build();
        _directionsBoatFilled = imageVectorBuild;
        return imageVectorBuild;
    }
}
