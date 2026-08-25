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
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\"\u0010\u0010\u0000\u001a\u0004\u0018\u00010\u0001X\u0082\u000e¢\u0006\u0002\n\u0000\"\u0015\u0010\u0002\u001a\u00020\u0001*\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"_airlineSeatFlatAngled", "Landroidx/compose/ui/graphics/vector/ImageVector;", "AirlineSeatFlatAngled", "Landroidx/compose/material/icons/Icons$Rounded;", "getAirlineSeatFlatAngled", "(Landroidx/compose/material/icons/Icons$Rounded;)Landroidx/compose/ui/graphics/vector/ImageVector;", "material-icons-extended_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class AirlineSeatFlatAngledKt {
    private static ImageVector _airlineSeatFlatAngled;

    public static final ImageVector getAirlineSeatFlatAngled(Icons.Rounded rounded) {
        ImageVector imageVector = _airlineSeatFlatAngled;
        if (imageVector != null) {
            return imageVector;
        }
        ImageVector.Builder builder = new ImageVector.Builder("Rounded.AirlineSeatFlatAngled", Dp.m5678constructorimpl(24.0f), Dp.m5678constructorimpl(24.0f), 24.0f, 24.0f, 0L, 0, false, 96, null);
        int defaultFillType = VectorKt.getDefaultFillType();
        SolidColor solidColor = new SolidColor(Color.INSTANCE.m3498getBlack0d7_KjU(), null);
        int iM3803getButtKaPHkGw = StrokeCap.INSTANCE.m3803getButtKaPHkGw();
        int iM3813getBevelLxFBmk8 = StrokeJoin.INSTANCE.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderR = a.r(22.25f, 14.29f, -0.69f, 1.89f);
        pathBuilderR.lineTo(9.2f, 11.71f);
        pathBuilderR.lineToRelative(1.39f, -3.79f);
        pathBuilderR.curveToRelative(0.38f, -1.03f, 1.52f, -1.56f, 2.56f, -1.19f);
        pathBuilderR.lineToRelative(6.69f, 2.41f);
        pathBuilderR.curveToRelative(2.1f, 0.76f, 3.18f, 3.06f, 2.41f, 5.15f);
        a.z(pathBuilderR, 2.45f, 12.48f, 5.55f, 2.0f);
        pathBuilderR.lineTo(8.0f, 18.0f);
        pathBuilderR.curveToRelative(0.0f, 0.55f, 0.45f, 1.0f, 1.0f, 1.0f);
        pathBuilderR.horizontalLineToRelative(6.0f);
        pathBuilderR.curveToRelative(0.55f, 0.0f, 1.0f, -0.45f, 1.0f, -1.0f);
        pathBuilderR.verticalLineToRelative(-0.63f);
        pathBuilderR.lineToRelative(3.58f, 1.29f);
        pathBuilderR.curveToRelative(0.52f, 0.19f, 1.1f, -0.08f, 1.29f, -0.6f);
        pathBuilderR.curveToRelative(0.19f, -0.52f, -0.08f, -1.1f, -0.6f, -1.29f);
        pathBuilderR.lineTo(3.13f, 10.59f);
        pathBuilderR.curveToRelative(-0.52f, -0.19f, -1.1f, 0.08f, -1.29f, 0.6f);
        pathBuilderR.curveToRelative(-0.18f, 0.52f, 0.09f, 1.1f, 0.61f, 1.29f);
        pathBuilderR.close();
        pathBuilderR.moveTo(7.3f, 10.2f);
        pathBuilderR.curveToRelative(1.49f, -0.72f, 2.12f, -2.51f, 1.41f, -4.0f);
        pathBuilderR.curveTo(7.99f, 4.71f, 6.2f, 4.08f, 4.7f, 4.8f);
        pathBuilderR.curveToRelative(-1.49f, 0.71f, -2.12f, 2.5f, -1.4f, 4.0f);
        pathBuilderR.curveToRelative(0.71f, 1.49f, 2.5f, 2.12f, 4.0f, 1.4f);
        pathBuilderR.close();
        ImageVector imageVectorBuild = ImageVector.Builder.m4024addPathoIyEayM$default(builder, pathBuilderR.getNodes(), defaultFillType, "", solidColor, 1.0f, null, 1.0f, 1.0f, iM3803getButtKaPHkGw, iM3813getBevelLxFBmk8, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null).build();
        _airlineSeatFlatAngled = imageVectorBuild;
        return imageVectorBuild;
    }
}
