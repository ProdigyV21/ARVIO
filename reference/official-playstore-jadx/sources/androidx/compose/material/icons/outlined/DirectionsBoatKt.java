package androidx.compose.material.icons.outlined;

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
import v.d;
import v.e;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\"\u0010\u0010\u0000\u001a\u0004\u0018\u00010\u0001X\u0082\u000e¢\u0006\u0002\n\u0000\"\u0015\u0010\u0002\u001a\u00020\u0001*\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"_directionsBoat", "Landroidx/compose/ui/graphics/vector/ImageVector;", "DirectionsBoat", "Landroidx/compose/material/icons/Icons$Outlined;", "getDirectionsBoat", "(Landroidx/compose/material/icons/Icons$Outlined;)Landroidx/compose/ui/graphics/vector/ImageVector;", "material-icons-extended_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class DirectionsBoatKt {
    private static ImageVector _directionsBoat;

    public static final ImageVector getDirectionsBoat(Icons.Outlined outlined) {
        ImageVector imageVector = _directionsBoat;
        if (imageVector != null) {
            return imageVector;
        }
        ImageVector.Builder builder = new ImageVector.Builder("Outlined.DirectionsBoat", Dp.m5678constructorimpl(24.0f), Dp.m5678constructorimpl(24.0f), 24.0f, 24.0f, 0L, 0, false, 96, null);
        int defaultFillType = VectorKt.getDefaultFillType();
        SolidColor solidColor = new SolidColor(Color.INSTANCE.m3498getBlack0d7_KjU(), null);
        int iM3803getButtKaPHkGw = StrokeCap.INSTANCE.m3803getButtKaPHkGw();
        int iM3813getBevelLxFBmk8 = StrokeJoin.INSTANCE.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderA = b.a(13.0f, 3.0f, 1.0f, -2.0f);
        pathBuilderA.lineTo(11.0f, 3.0f);
        pathBuilderA.horizontalLineToRelative(2.0f);
        pathBuilderA.moveToRelative(-1.0f, 7.11f);
        pathBuilderA.lineToRelative(5.38f, 1.77f);
        pathBuilderA.lineToRelative(2.39f, 0.78f);
        pathBuilderA.lineToRelative(-1.12f, 3.97f);
        pathBuilderA.curveToRelative(-0.54f, -0.3f, -0.94f, -0.71f, -1.14f, -0.94f);
        pathBuilderA.lineTo(16.0f, 13.96f);
        pathBuilderA.lineToRelative(-1.51f, 1.72f);
        pathBuilderA.curveToRelative(-0.34f, 0.4f, -1.28f, 1.32f, -2.49f, 1.32f);
        pathBuilderA.reflectiveCurveToRelative(-2.15f, -0.92f, -2.49f, -1.32f);
        pathBuilderA.lineTo(8.0f, 13.96f);
        pathBuilderA.lineToRelative(-1.51f, 1.72f);
        pathBuilderA.curveToRelative(-0.2f, 0.23f, -0.6f, 0.63f, -1.14f, 0.93f);
        pathBuilderA.lineToRelative(-1.13f, -3.96f);
        pathBuilderA.lineToRelative(2.4f, -0.79f);
        pathBuilderA.lineTo(12.0f, 10.11f);
        pathBuilderA.moveTo(15.0f, 1.0f);
        pathBuilderA.lineTo(9.0f, 1.0f);
        pathBuilderA.verticalLineToRelative(3.0f);
        pathBuilderA.lineTo(6.0f, 4.0f);
        pathBuilderA.curveToRelative(-1.1f, 0.0f, -2.0f, 0.9f, -2.0f, 2.0f);
        pathBuilderA.verticalLineToRelative(4.62f);
        pathBuilderA.lineToRelative(-1.29f, 0.42f);
        pathBuilderA.curveToRelative(-0.26f, 0.08f, -0.48f, 0.26f, -0.6f, 0.5f);
        pathBuilderA.reflectiveCurveToRelative(-0.15f, 0.52f, -0.06f, 0.78f);
        pathBuilderA.lineTo(3.95f, 19.0f);
        pathBuilderA.lineTo(4.0f, 19.0f);
        pathBuilderA.curveToRelative(1.6f, 0.0f, 3.02f, -0.88f, 4.0f, -2.0f);
        pathBuilderA.curveToRelative(0.98f, 1.12f, 2.4f, 2.0f, 4.0f, 2.0f);
        pathBuilderA.reflectiveCurveToRelative(3.02f, -0.88f, 4.0f, -2.0f);
        pathBuilderA.curveToRelative(0.98f, 1.12f, 2.4f, 2.0f, 4.0f, 2.0f);
        pathBuilderA.horizontalLineToRelative(0.05f);
        pathBuilderA.lineToRelative(1.89f, -6.68f);
        pathBuilderA.curveToRelative(0.08f, -0.26f, 0.06f, -0.54f, -0.06f, -0.78f);
        pathBuilderA.reflectiveCurveToRelative(-0.34f, -0.42f, -0.6f, -0.5f);
        pathBuilderA.lineTo(20.0f, 10.62f);
        pathBuilderA.lineTo(20.0f, 6.0f);
        pathBuilderA.curveToRelative(0.0f, -1.1f, -0.9f, -2.0f, -2.0f, -2.0f);
        d.q(pathBuilderA, -3.0f, 15.0f, 1.0f);
        pathBuilderA.moveTo(6.0f, 9.97f);
        b.A(pathBuilderA, 6.0f, 6.0f, 12.0f, 3.97f);
        b.y(pathBuilderA, 12.0f, 8.0f, 6.0f, 9.97f);
        pathBuilderA.moveTo(16.0f, 19.68f);
        pathBuilderA.curveToRelative(-1.22f, 0.85f, -2.61f, 1.28f, -4.0f, 1.28f);
        pathBuilderA.reflectiveCurveToRelative(-2.78f, -0.43f, -4.0f, -1.28f);
        pathBuilderA.curveTo(6.78f, 20.53f, 5.39f, 21.0f, 4.0f, 21.0f);
        a.h(pathBuilderA, 2.0f, 21.0f, 2.0f, 2.0f);
        pathBuilderA.curveToRelative(1.38f, 0.0f, 2.74f, -0.35f, 4.0f, -0.99f);
        pathBuilderA.curveToRelative(1.26f, 0.64f, 2.63f, 0.97f, 4.0f, 0.97f);
        pathBuilderA.reflectiveCurveToRelative(2.74f, -0.32f, 4.0f, -0.97f);
        pathBuilderA.curveToRelative(1.26f, 0.65f, 2.62f, 0.99f, 4.0f, 0.99f);
        e.g(pathBuilderA, 2.0f, -2.0f, -2.0f);
        pathBuilderA.curveToRelative(-1.39f, 0.0f, -2.78f, -0.47f, -4.0f, -1.32f);
        pathBuilderA.close();
        ImageVector imageVectorBuild = ImageVector.Builder.m4024addPathoIyEayM$default(builder, pathBuilderA.getNodes(), defaultFillType, "", solidColor, 1.0f, null, 1.0f, 1.0f, iM3803getButtKaPHkGw, iM3813getBevelLxFBmk8, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null).build();
        _directionsBoat = imageVectorBuild;
        return imageVectorBuild;
    }
}
