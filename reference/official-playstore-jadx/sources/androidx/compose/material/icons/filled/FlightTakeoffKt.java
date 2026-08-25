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
import v.a;
import v.f;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\"\u0010\u0010\u0000\u001a\u0004\u0018\u00010\u0001X\u0082\u000e¢\u0006\u0002\n\u0000\"\u0015\u0010\u0002\u001a\u00020\u0001*\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"_flightTakeoff", "Landroidx/compose/ui/graphics/vector/ImageVector;", "FlightTakeoff", "Landroidx/compose/material/icons/Icons$Filled;", "getFlightTakeoff", "(Landroidx/compose/material/icons/Icons$Filled;)Landroidx/compose/ui/graphics/vector/ImageVector;", "material-icons-extended_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class FlightTakeoffKt {
    private static ImageVector _flightTakeoff;

    public static final ImageVector getFlightTakeoff(Icons.Filled filled) {
        ImageVector imageVector = _flightTakeoff;
        if (imageVector != null) {
            return imageVector;
        }
        ImageVector.Builder builder = new ImageVector.Builder("Filled.FlightTakeoff", Dp.m5678constructorimpl(24.0f), Dp.m5678constructorimpl(24.0f), 24.0f, 24.0f, 0L, 0, false, 96, null);
        int defaultFillType = VectorKt.getDefaultFillType();
        SolidColor solidColor = new SolidColor(Color.INSTANCE.m3498getBlack0d7_KjU(), null);
        int iM3803getButtKaPHkGw = StrokeCap.INSTANCE.m3803getButtKaPHkGw();
        int iM3813getBevelLxFBmk8 = StrokeJoin.INSTANCE.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderK = a.k(2.5f, 19.0f, 19.0f, 2.0f, -19.0f);
        f.r(pathBuilderK, 19.0f, 22.07f, 9.64f);
        pathBuilderK.curveToRelative(-0.21f, -0.8f, -1.04f, -1.28f, -1.84f, -1.06f);
        pathBuilderK.lineTo(14.92f, 10.0f);
        pathBuilderK.lineToRelative(-6.9f, -6.43f);
        pathBuilderK.lineTo(6.09f, 4.08f);
        pathBuilderK.lineToRelative(4.14f, 7.17f);
        pathBuilderK.lineToRelative(-4.97f, 1.33f);
        pathBuilderK.lineToRelative(-1.97f, -1.54f);
        pathBuilderK.lineToRelative(-1.45f, 0.39f);
        pathBuilderK.lineToRelative(2.59f, 4.49f);
        pathBuilderK.curveToRelative(0.0f, 0.0f, 7.12f, -1.9f, 16.57f, -4.43f);
        pathBuilderK.curveTo(21.81f, 11.26f, 22.28f, 10.44f, 22.07f, 9.64f);
        pathBuilderK.close();
        ImageVector imageVectorBuild = ImageVector.Builder.m4024addPathoIyEayM$default(builder, pathBuilderK.getNodes(), defaultFillType, "", solidColor, 1.0f, null, 1.0f, 1.0f, iM3803getButtKaPHkGw, iM3813getBevelLxFBmk8, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null).build();
        _flightTakeoff = imageVectorBuild;
        return imageVectorBuild;
    }
}
