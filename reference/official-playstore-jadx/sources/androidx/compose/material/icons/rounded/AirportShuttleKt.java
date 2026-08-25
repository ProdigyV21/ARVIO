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
import v.b;
import v.c;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\"\u0010\u0010\u0000\u001a\u0004\u0018\u00010\u0001X\u0082\u000e¢\u0006\u0002\n\u0000\"\u0015\u0010\u0002\u001a\u00020\u0001*\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"_airportShuttle", "Landroidx/compose/ui/graphics/vector/ImageVector;", "AirportShuttle", "Landroidx/compose/material/icons/Icons$Rounded;", "getAirportShuttle", "(Landroidx/compose/material/icons/Icons$Rounded;)Landroidx/compose/ui/graphics/vector/ImageVector;", "material-icons-extended_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class AirportShuttleKt {
    private static ImageVector _airportShuttle;

    public static final ImageVector getAirportShuttle(Icons.Rounded rounded) {
        ImageVector imageVector = _airportShuttle;
        if (imageVector != null) {
            return imageVector;
        }
        ImageVector.Builder builder = new ImageVector.Builder("Rounded.AirportShuttle", Dp.m5678constructorimpl(24.0f), Dp.m5678constructorimpl(24.0f), 24.0f, 24.0f, 0L, 0, false, 96, null);
        int defaultFillType = VectorKt.getDefaultFillType();
        SolidColor solidColor = new SolidColor(Color.INSTANCE.m3498getBlack0d7_KjU(), null);
        int iM3803getButtKaPHkGw = StrokeCap.INSTANCE.m3803getButtKaPHkGw();
        int iM3813getBevelLxFBmk8 = StrokeJoin.INSTANCE.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderR = a.r(22.41f, 10.41f, -4.83f, -4.83f);
        pathBuilderR.curveToRelative(-0.37f, -0.37f, -0.88f, -0.58f, -1.41f, -0.58f);
        pathBuilderR.lineTo(3.0f, 5.0f);
        pathBuilderR.curveToRelative(-1.1f, 0.0f, -2.0f, 0.89f, -2.0f, 2.0f);
        pathBuilderR.verticalLineToRelative(7.0f);
        pathBuilderR.curveToRelative(0.0f, 1.1f, 0.9f, 2.0f, 2.0f, 2.0f);
        pathBuilderR.curveToRelative(0.0f, 1.66f, 1.34f, 3.0f, 3.0f, 3.0f);
        pathBuilderR.reflectiveCurveToRelative(3.0f, -1.34f, 3.0f, -3.0f);
        pathBuilderR.horizontalLineToRelative(6.0f);
        pathBuilderR.curveToRelative(0.0f, 1.66f, 1.34f, 3.0f, 3.0f, 3.0f);
        pathBuilderR.reflectiveCurveToRelative(3.0f, -1.34f, 3.0f, -3.0f);
        pathBuilderR.curveToRelative(1.1f, 0.0f, 2.0f, -0.9f, 2.0f, -2.0f);
        pathBuilderR.verticalLineToRelative(-2.17f);
        pathBuilderR.curveToRelative(0.0f, -0.53f, -0.21f, -1.04f, -0.59f, -1.42f);
        a.p(pathBuilderR, 3.0f, 10.0f, 3.0f, 8.0f);
        pathBuilderR.curveToRelative(0.0f, -0.55f, 0.45f, -1.0f, 1.0f, -1.0f);
        c.q(pathBuilderR, 3.0f, 4.0f, 4.0f, 11.0f);
        pathBuilderR.curveToRelative(-0.55f, 0.0f, -1.0f, -0.45f, -1.0f, -1.0f);
        pathBuilderR.close();
        pathBuilderR.moveTo(6.0f, 17.25f);
        pathBuilderR.curveToRelative(-0.69f, 0.0f, -1.25f, -0.56f, -1.25f, -1.25f);
        pathBuilderR.reflectiveCurveToRelative(0.56f, -1.25f, 1.25f, -1.25f);
        pathBuilderR.reflectiveCurveToRelative(1.25f, 0.56f, 1.25f, 1.25f);
        pathBuilderR.reflectiveCurveToRelative(-0.56f, 1.25f, -1.25f, 1.25f);
        a.p(pathBuilderR, 13.0f, 11.0f, 9.0f, 11.0f);
        b.g(pathBuilderR, 9.0f, 7.0f, 4.0f, 4.0f);
        pathBuilderR.moveTo(18.0f, 17.25f);
        pathBuilderR.curveToRelative(-0.69f, 0.0f, -1.25f, -0.56f, -1.25f, -1.25f);
        pathBuilderR.reflectiveCurveToRelative(0.56f, -1.25f, 1.25f, -1.25f);
        pathBuilderR.reflectiveCurveToRelative(1.25f, 0.56f, 1.25f, 1.25f);
        pathBuilderR.reflectiveCurveToRelative(-0.56f, 1.25f, -1.25f, 1.25f);
        a.p(pathBuilderR, 15.0f, 11.0f, 15.0f, 7.0f);
        pathBuilderR.horizontalLineToRelative(1.0f);
        pathBuilderR.lineToRelative(4.0f, 4.0f);
        pathBuilderR.horizontalLineToRelative(-5.0f);
        pathBuilderR.close();
        ImageVector imageVectorBuild = ImageVector.Builder.m4024addPathoIyEayM$default(builder, pathBuilderR.getNodes(), defaultFillType, "", solidColor, 1.0f, null, 1.0f, 1.0f, iM3803getButtKaPHkGw, iM3813getBevelLxFBmk8, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null).build();
        _airportShuttle = imageVectorBuild;
        return imageVectorBuild;
    }
}
