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
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\"\u0010\u0010\u0000\u001a\u0004\u0018\u00010\u0001X\u0082\u000e¢\u0006\u0002\n\u0000\"\u0015\u0010\u0002\u001a\u00020\u0001*\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"_airlineSeatLegroomExtra", "Landroidx/compose/ui/graphics/vector/ImageVector;", "AirlineSeatLegroomExtra", "Landroidx/compose/material/icons/Icons$Rounded;", "getAirlineSeatLegroomExtra", "(Landroidx/compose/material/icons/Icons$Rounded;)Landroidx/compose/ui/graphics/vector/ImageVector;", "material-icons-extended_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class AirlineSeatLegroomExtraKt {
    private static ImageVector _airlineSeatLegroomExtra;

    public static final ImageVector getAirlineSeatLegroomExtra(Icons.Rounded rounded) {
        ImageVector imageVector = _airlineSeatLegroomExtra;
        if (imageVector != null) {
            return imageVector;
        }
        ImageVector.Builder builder = new ImageVector.Builder("Rounded.AirlineSeatLegroomExtra", Dp.m5678constructorimpl(24.0f), Dp.m5678constructorimpl(24.0f), 24.0f, 24.0f, 0L, 0, false, 96, null);
        int defaultFillType = VectorKt.getDefaultFillType();
        SolidColor solidColor = new SolidColor(Color.INSTANCE.m3498getBlack0d7_KjU(), null);
        int iM3803getButtKaPHkGw = StrokeCap.INSTANCE.m3803getButtKaPHkGw();
        int iM3813getBevelLxFBmk8 = StrokeJoin.INSTANCE.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderJ = a.j(4.0f, 12.0f, 4.0f, 4.0f);
        pathBuilderJ.curveToRelative(0.0f, -0.55f, -0.45f, -1.0f, -1.0f, -1.0f);
        pathBuilderJ.reflectiveCurveToRelative(-1.0f, 0.45f, -1.0f, 1.0f);
        pathBuilderJ.verticalLineToRelative(8.0f);
        pathBuilderJ.curveToRelative(0.0f, 2.76f, 2.24f, 5.0f, 5.0f, 5.0f);
        pathBuilderJ.horizontalLineToRelative(5.0f);
        pathBuilderJ.curveToRelative(0.55f, 0.0f, 1.0f, -0.45f, 1.0f, -1.0f);
        pathBuilderJ.reflectiveCurveToRelative(-0.45f, -1.0f, -1.0f, -1.0f);
        pathBuilderJ.lineTo(7.0f, 15.0f);
        pathBuilderJ.curveToRelative(-1.66f, 0.0f, -3.0f, -1.34f, -3.0f, -3.0f);
        pathBuilderJ.close();
        pathBuilderJ.moveTo(22.83f, 17.24f);
        pathBuilderJ.curveToRelative(-0.38f, -0.72f, -1.29f, -0.97f, -2.03f, -0.63f);
        pathBuilderJ.lineToRelative(-1.09f, 0.5f);
        pathBuilderJ.lineToRelative(-3.41f, -6.98f);
        pathBuilderJ.curveTo(15.96f, 9.45f, 15.27f, 9.0f, 14.51f, 9.0f);
        pathBuilderJ.lineTo(11.0f, 9.0f);
        pathBuilderJ.lineTo(11.0f, 3.0f);
        pathBuilderJ.lineTo(5.0f, 3.0f);
        pathBuilderJ.verticalLineToRelative(8.0f);
        pathBuilderJ.curveToRelative(0.0f, 1.66f, 1.34f, 3.0f, 3.0f, 3.0f);
        pathBuilderJ.horizontalLineToRelative(7.0f);
        pathBuilderJ.lineToRelative(2.56f, 5.25f);
        pathBuilderJ.curveToRelative(0.48f, 0.98f, 1.64f, 1.39f, 2.63f, 0.94f);
        pathBuilderJ.lineToRelative(1.95f, -0.89f);
        pathBuilderJ.curveToRelative(0.76f, -0.36f, 1.09f, -1.3f, 0.69f, -2.06f);
        pathBuilderJ.close();
        ImageVector imageVectorBuild = ImageVector.Builder.m4024addPathoIyEayM$default(builder, pathBuilderJ.getNodes(), defaultFillType, "", solidColor, 1.0f, null, 1.0f, 1.0f, iM3803getButtKaPHkGw, iM3813getBevelLxFBmk8, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null).build();
        _airlineSeatLegroomExtra = imageVectorBuild;
        return imageVectorBuild;
    }
}
