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
import v.c;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\"\u0010\u0010\u0000\u001a\u0004\u0018\u00010\u0001X\u0082\u000e¢\u0006\u0002\n\u0000\"\u0015\u0010\u0002\u001a\u00020\u0001*\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"_airlineSeatReclineNormal", "Landroidx/compose/ui/graphics/vector/ImageVector;", "AirlineSeatReclineNormal", "Landroidx/compose/material/icons/Icons$Rounded;", "getAirlineSeatReclineNormal", "(Landroidx/compose/material/icons/Icons$Rounded;)Landroidx/compose/ui/graphics/vector/ImageVector;", "material-icons-extended_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class AirlineSeatReclineNormalKt {
    private static ImageVector _airlineSeatReclineNormal;

    public static final ImageVector getAirlineSeatReclineNormal(Icons.Rounded rounded) {
        ImageVector imageVector = _airlineSeatReclineNormal;
        if (imageVector != null) {
            return imageVector;
        }
        ImageVector.Builder builder = new ImageVector.Builder("Rounded.AirlineSeatReclineNormal", Dp.m5678constructorimpl(24.0f), Dp.m5678constructorimpl(24.0f), 24.0f, 24.0f, 0L, 0, false, 96, null);
        int defaultFillType = VectorKt.getDefaultFillType();
        SolidColor solidColor = new SolidColor(Color.INSTANCE.m3498getBlack0d7_KjU(), null);
        int iM3803getButtKaPHkGw = StrokeCap.INSTANCE.m3803getButtKaPHkGw();
        int iM3813getBevelLxFBmk8 = StrokeJoin.INSTANCE.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderA = c.a(7.59f, 5.41f);
        pathBuilderA.curveToRelative(-0.78f, -0.78f, -0.78f, -2.05f, 0.0f, -2.83f);
        pathBuilderA.reflectiveCurveToRelative(2.05f, -0.78f, 2.83f, 0.0f);
        pathBuilderA.reflectiveCurveToRelative(0.78f, 2.05f, 0.0f, 2.83f);
        pathBuilderA.curveToRelative(-0.79f, 0.79f, -2.05f, 0.79f, -2.83f, 0.0f);
        a.p(pathBuilderA, 6.0f, 16.0f, 6.0f, 8.0f);
        pathBuilderA.curveToRelative(0.0f, -0.55f, -0.45f, -1.0f, -1.0f, -1.0f);
        pathBuilderA.reflectiveCurveToRelative(-1.0f, 0.45f, -1.0f, 1.0f);
        pathBuilderA.verticalLineToRelative(8.0f);
        pathBuilderA.curveToRelative(0.0f, 2.76f, 2.24f, 5.0f, 5.0f, 5.0f);
        pathBuilderA.horizontalLineToRelative(5.0f);
        pathBuilderA.curveToRelative(0.55f, 0.0f, 1.0f, -0.45f, 1.0f, -1.0f);
        pathBuilderA.reflectiveCurveToRelative(-0.45f, -1.0f, -1.0f, -1.0f);
        pathBuilderA.lineTo(9.0f, 19.0f);
        pathBuilderA.curveToRelative(-1.66f, 0.0f, -3.0f, -1.34f, -3.0f, -3.0f);
        a.z(pathBuilderA, 19.28f, 19.35f, -3.77f, -3.77f);
        pathBuilderA.curveToRelative(-0.37f, -0.37f, -0.88f, -0.58f, -1.41f, -0.58f);
        pathBuilderA.horizontalLineToRelative(-2.6f);
        pathBuilderA.verticalLineToRelative(-3.68f);
        pathBuilderA.curveToRelative(1.09f, 0.89f, 2.66f, 1.7f, 4.2f, 2.02f);
        pathBuilderA.curveToRelative(0.67f, 0.14f, 1.3f, -0.36f, 1.3f, -1.04f);
        pathBuilderA.curveToRelative(0.0f, -0.53f, -0.39f, -0.96f, -0.92f, -1.05f);
        pathBuilderA.curveToRelative(-1.42f, -0.24f, -2.88f, -1.01f, -3.75f, -1.97f);
        pathBuilderA.lineToRelative(-1.4f, -1.55f);
        pathBuilderA.curveToRelative(-0.19f, -0.21f, -0.43f, -0.38f, -0.69f, -0.5f);
        pathBuilderA.curveToRelative(-0.29f, -0.14f, -0.62f, -0.23f, -0.96f, -0.23f);
        pathBuilderA.horizontalLineToRelative(-0.03f);
        pathBuilderA.curveTo(8.01f, 7.0f, 7.0f, 8.01f, 7.0f, 9.25f);
        pathBuilderA.lineTo(7.0f, 15.0f);
        pathBuilderA.curveToRelative(0.0f, 1.66f, 1.34f, 3.0f, 3.0f, 3.0f);
        pathBuilderA.horizontalLineToRelative(5.07f);
        pathBuilderA.lineToRelative(2.78f, 2.78f);
        pathBuilderA.curveToRelative(0.39f, 0.39f, 1.04f, 0.39f, 1.43f, 0.0f);
        pathBuilderA.curveToRelative(0.4f, -0.39f, 0.4f, -1.03f, 0.0f, -1.43f);
        pathBuilderA.close();
        ImageVector imageVectorBuild = ImageVector.Builder.m4024addPathoIyEayM$default(builder, pathBuilderA.getNodes(), defaultFillType, "", solidColor, 1.0f, null, 1.0f, 1.0f, iM3803getButtKaPHkGw, iM3813getBevelLxFBmk8, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null).build();
        _airlineSeatReclineNormal = imageVectorBuild;
        return imageVectorBuild;
    }
}
