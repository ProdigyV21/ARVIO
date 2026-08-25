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

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\"\u0010\u0010\u0000\u001a\u0004\u0018\u00010\u0001X\u0082\u000e¢\u0006\u0002\n\u0000\"\u0015\u0010\u0002\u001a\u00020\u0001*\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"_flightLand", "Landroidx/compose/ui/graphics/vector/ImageVector;", "FlightLand", "Landroidx/compose/material/icons/Icons$Rounded;", "getFlightLand", "(Landroidx/compose/material/icons/Icons$Rounded;)Landroidx/compose/ui/graphics/vector/ImageVector;", "material-icons-extended_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class FlightLandKt {
    private static ImageVector _flightLand;

    public static final ImageVector getFlightLand(Icons.Rounded rounded) {
        ImageVector imageVector = _flightLand;
        if (imageVector != null) {
            return imageVector;
        }
        ImageVector.Builder builder = new ImageVector.Builder("Rounded.FlightLand", Dp.m5678constructorimpl(24.0f), Dp.m5678constructorimpl(24.0f), 24.0f, 24.0f, 0L, 0, false, 96, null);
        int defaultFillType = VectorKt.getDefaultFillType();
        SolidColor solidColor = new SolidColor(Color.INSTANCE.m3498getBlack0d7_KjU(), null);
        int iM3803getButtKaPHkGw = StrokeCap.INSTANCE.m3803getButtKaPHkGw();
        int iM3813getBevelLxFBmk8 = StrokeJoin.INSTANCE.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderA = a.a(20.5f, 19.0f, -17.0f);
        pathBuilderA.curveToRelative(-0.55f, 0.0f, -1.0f, 0.45f, -1.0f, 1.0f);
        pathBuilderA.reflectiveCurveToRelative(0.45f, 1.0f, 1.0f, 1.0f);
        pathBuilderA.horizontalLineToRelative(17.0f);
        pathBuilderA.curveToRelative(0.55f, 0.0f, 1.0f, -0.45f, 1.0f, -1.0f);
        pathBuilderA.reflectiveCurveToRelative(-0.45f, -1.0f, -1.0f, -1.0f);
        a.z(pathBuilderA, 3.51f, 11.61f, 15.83f, 4.24f);
        pathBuilderA.curveToRelative(0.8f, 0.21f, 1.62f, -0.26f, 1.84f, -1.06f);
        pathBuilderA.curveToRelative(0.21f, -0.8f, -0.26f, -1.62f, -1.06f, -1.84f);
        pathBuilderA.lineToRelative(-5.31f, -1.42f);
        pathBuilderA.lineToRelative(-2.58f, -8.45f);
        pathBuilderA.curveToRelative(-0.11f, -0.36f, -0.39f, -0.63f, -0.75f, -0.73f);
        pathBuilderA.curveToRelative(-0.68f, -0.18f, -1.35f, 0.33f, -1.35f, 1.04f);
        pathBuilderA.verticalLineToRelative(6.88f);
        pathBuilderA.lineTo(5.15f, 8.95f);
        pathBuilderA.lineTo(4.4f, 7.09f);
        pathBuilderA.curveToRelative(-0.12f, -0.29f, -0.36f, -0.51f, -0.67f, -0.59f);
        pathBuilderA.lineToRelative(-0.33f, -0.09f);
        pathBuilderA.curveToRelative(-0.32f, -0.09f, -0.63f, 0.15f, -0.63f, 0.48f);
        pathBuilderA.verticalLineToRelative(3.75f);
        pathBuilderA.curveToRelative(0.0f, 0.46f, 0.3f, 0.85f, 0.74f, 0.97f);
        pathBuilderA.close();
        ImageVector imageVectorBuild = ImageVector.Builder.m4024addPathoIyEayM$default(builder, pathBuilderA.getNodes(), defaultFillType, "", solidColor, 1.0f, null, 1.0f, 1.0f, iM3803getButtKaPHkGw, iM3813getBevelLxFBmk8, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null).build();
        _flightLand = imageVectorBuild;
        return imageVectorBuild;
    }
}
