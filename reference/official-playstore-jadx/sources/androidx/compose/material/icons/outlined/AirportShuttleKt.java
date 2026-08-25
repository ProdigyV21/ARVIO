package androidx.compose.material.icons.outlined;

import a0.b;
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
import v.e;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\"\u0010\u0010\u0000\u001a\u0004\u0018\u00010\u0001X\u0082\u000e¢\u0006\u0002\n\u0000\"\u0015\u0010\u0002\u001a\u00020\u0001*\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"_airportShuttle", "Landroidx/compose/ui/graphics/vector/ImageVector;", "AirportShuttle", "Landroidx/compose/material/icons/Icons$Outlined;", "getAirportShuttle", "(Landroidx/compose/material/icons/Icons$Outlined;)Landroidx/compose/ui/graphics/vector/ImageVector;", "material-icons-extended_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class AirportShuttleKt {
    private static ImageVector _airportShuttle;

    public static final ImageVector getAirportShuttle(Icons.Outlined outlined) {
        ImageVector imageVector = _airportShuttle;
        if (imageVector != null) {
            return imageVector;
        }
        ImageVector.Builder builder = new ImageVector.Builder("Outlined.AirportShuttle", Dp.m5678constructorimpl(24.0f), Dp.m5678constructorimpl(24.0f), 24.0f, 24.0f, 0L, 0, false, 96, null);
        int defaultFillType = VectorKt.getDefaultFillType();
        SolidColor solidColor = new SolidColor(Color.INSTANCE.m3498getBlack0d7_KjU(), null);
        int iM3803getButtKaPHkGw = StrokeCap.INSTANCE.m3803getButtKaPHkGw();
        int iM3813getBevelLxFBmk8 = StrokeJoin.INSTANCE.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderJ = a.j(17.0f, 5.0f, 3.0f, 5.0f);
        pathBuilderJ.curveToRelative(-1.1f, 0.0f, -2.0f, 0.89f, -2.0f, 2.0f);
        pathBuilderJ.verticalLineToRelative(9.0f);
        pathBuilderJ.horizontalLineToRelative(2.0f);
        pathBuilderJ.curveToRelative(0.0f, 1.66f, 1.34f, 3.0f, 3.0f, 3.0f);
        pathBuilderJ.reflectiveCurveToRelative(3.0f, -1.34f, 3.0f, -3.0f);
        pathBuilderJ.horizontalLineToRelative(6.0f);
        pathBuilderJ.curveToRelative(0.0f, 1.66f, 1.34f, 3.0f, 3.0f, 3.0f);
        pathBuilderJ.reflectiveCurveToRelative(3.0f, -1.34f, 3.0f, -3.0f);
        b.r(pathBuilderJ, 2.0f, -5.0f, -6.0f, -6.0f);
        pathBuilderJ.moveTo(15.0f, 7.0f);
        e.C(pathBuilderJ, 1.0f, 3.0f, 3.0f, -4.0f);
        c.z(pathBuilderJ, 15.0f, 7.0f, 9.0f, 7.0f);
        c.q(pathBuilderJ, 4.0f, 3.0f, 9.0f, 10.0f);
        c.z(pathBuilderJ, 9.0f, 7.0f, 3.0f, 7.0f);
        c.q(pathBuilderJ, 4.0f, 3.0f, 3.0f, 10.0f);
        c.z(pathBuilderJ, 3.0f, 7.0f, 6.0f, 17.25f);
        pathBuilderJ.curveToRelative(-0.69f, 0.0f, -1.25f, -0.56f, -1.25f, -1.25f);
        pathBuilderJ.reflectiveCurveToRelative(0.56f, -1.25f, 1.25f, -1.25f);
        pathBuilderJ.reflectiveCurveToRelative(1.25f, 0.56f, 1.25f, 1.25f);
        pathBuilderJ.reflectiveCurveToRelative(-0.56f, 1.25f, -1.25f, 1.25f);
        pathBuilderJ.close();
        pathBuilderJ.moveTo(18.0f, 17.25f);
        pathBuilderJ.curveToRelative(-0.69f, 0.0f, -1.25f, -0.56f, -1.25f, -1.25f);
        pathBuilderJ.reflectiveCurveToRelative(0.56f, -1.25f, 1.25f, -1.25f);
        pathBuilderJ.reflectiveCurveToRelative(1.25f, 0.56f, 1.25f, 1.25f);
        pathBuilderJ.reflectiveCurveToRelative(-0.56f, 1.25f, -1.25f, 1.25f);
        a.g(pathBuilderJ, 21.0f, 14.0f, -0.78f);
        pathBuilderJ.curveToRelative(-0.55f, -0.61f, -1.34f, -1.0f, -2.22f, -1.0f);
        pathBuilderJ.reflectiveCurveToRelative(-1.67f, 0.39f, -2.22f, 1.0f);
        pathBuilderJ.lineTo(8.22f, 14.0f);
        pathBuilderJ.curveToRelative(-0.55f, -0.61f, -1.33f, -1.0f, -2.22f, -1.0f);
        pathBuilderJ.reflectiveCurveToRelative(-1.67f, 0.39f, -2.22f, 1.0f);
        a.h(pathBuilderJ, 3.0f, 14.0f, -2.0f, 18.0f);
        ImageVector imageVectorBuild = ImageVector.Builder.m4024addPathoIyEayM$default(builder, v.b.c(pathBuilderJ, 2.0f), defaultFillType, "", solidColor, 1.0f, null, 1.0f, 1.0f, iM3803getButtKaPHkGw, iM3813getBevelLxFBmk8, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null).build();
        _airportShuttle = imageVectorBuild;
        return imageVectorBuild;
    }
}
