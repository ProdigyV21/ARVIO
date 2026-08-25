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
import v.d;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\"\u0010\u0010\u0000\u001a\u0004\u0018\u00010\u0001X\u0082\u000e¢\u0006\u0002\n\u0000\"\u0015\u0010\u0002\u001a\u00020\u0001*\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"_airlineSeatFlat", "Landroidx/compose/ui/graphics/vector/ImageVector;", "AirlineSeatFlat", "Landroidx/compose/material/icons/Icons$Rounded;", "getAirlineSeatFlat", "(Landroidx/compose/material/icons/Icons$Rounded;)Landroidx/compose/ui/graphics/vector/ImageVector;", "material-icons-extended_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class AirlineSeatFlatKt {
    private static ImageVector _airlineSeatFlat;

    public static final ImageVector getAirlineSeatFlat(Icons.Rounded rounded) {
        ImageVector imageVector = _airlineSeatFlat;
        if (imageVector != null) {
            return imageVector;
        }
        ImageVector.Builder builder = new ImageVector.Builder("Rounded.AirlineSeatFlat", Dp.m5678constructorimpl(24.0f), Dp.m5678constructorimpl(24.0f), 24.0f, 24.0f, 0L, 0, false, 96, null);
        int defaultFillType = VectorKt.getDefaultFillType();
        SolidColor solidColor = new SolidColor(Color.INSTANCE.m3498getBlack0d7_KjU(), null);
        int iM3803getButtKaPHkGw = StrokeCap.INSTANCE.m3803getButtKaPHkGw();
        int iM3813getBevelLxFBmk8 = StrokeJoin.INSTANCE.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderS = d.s(22.0f, 11.0f, 2.0f, 9.0f, 13.0f);
        pathBuilderS.lineTo(9.0f, 9.0f);
        pathBuilderS.curveToRelative(0.0f, -1.1f, 0.9f, -2.0f, 2.0f, -2.0f);
        pathBuilderS.horizontalLineToRelative(7.0f);
        pathBuilderS.curveToRelative(2.21f, 0.0f, 4.0f, 1.79f, 4.0f, 4.0f);
        pathBuilderS.close();
        pathBuilderS.moveTo(2.0f, 15.0f);
        pathBuilderS.curveToRelative(0.0f, 0.55f, 0.45f, 1.0f, 1.0f, 1.0f);
        pathBuilderS.horizontalLineToRelative(5.0f);
        pathBuilderS.verticalLineToRelative(1.0f);
        pathBuilderS.curveToRelative(0.0f, 0.55f, 0.45f, 1.0f, 1.0f, 1.0f);
        pathBuilderS.horizontalLineToRelative(6.0f);
        pathBuilderS.curveToRelative(0.55f, 0.0f, 1.0f, -0.45f, 1.0f, -1.0f);
        pathBuilderS.verticalLineToRelative(-1.0f);
        pathBuilderS.horizontalLineToRelative(5.0f);
        pathBuilderS.curveToRelative(0.55f, 0.0f, 1.0f, -0.45f, 1.0f, -1.0f);
        pathBuilderS.reflectiveCurveToRelative(-0.45f, -1.0f, -1.0f, -1.0f);
        pathBuilderS.lineTo(3.0f, 14.0f);
        pathBuilderS.curveToRelative(-0.55f, 0.0f, -1.0f, 0.45f, -1.0f, 1.0f);
        pathBuilderS.close();
        pathBuilderS.moveTo(7.14f, 12.1f);
        pathBuilderS.curveToRelative(1.16f, -1.19f, 1.14f, -3.08f, -0.04f, -4.24f);
        pathBuilderS.curveToRelative(-1.19f, -1.16f, -3.08f, -1.14f, -4.24f, 0.04f);
        pathBuilderS.curveToRelative(-1.16f, 1.19f, -1.14f, 3.08f, 0.04f, 4.24f);
        pathBuilderS.curveToRelative(1.19f, 1.16f, 3.08f, 1.14f, 4.24f, -0.04f);
        pathBuilderS.close();
        ImageVector imageVectorBuild = ImageVector.Builder.m4024addPathoIyEayM$default(builder, pathBuilderS.getNodes(), defaultFillType, "", solidColor, 1.0f, null, 1.0f, 1.0f, iM3803getButtKaPHkGw, iM3813getBevelLxFBmk8, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null).build();
        _airlineSeatFlat = imageVectorBuild;
        return imageVectorBuild;
    }
}
