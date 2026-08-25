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
import v.c;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\"\u0010\u0010\u0000\u001a\u0004\u0018\u00010\u0001X\u0082\u000e¢\u0006\u0002\n\u0000\"\u0015\u0010\u0002\u001a\u00020\u0001*\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"_flight", "Landroidx/compose/ui/graphics/vector/ImageVector;", "Flight", "Landroidx/compose/material/icons/Icons$Rounded;", "getFlight", "(Landroidx/compose/material/icons/Icons$Rounded;)Landroidx/compose/ui/graphics/vector/ImageVector;", "material-icons-extended_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class FlightKt {
    private static ImageVector _flight;

    public static final ImageVector getFlight(Icons.Rounded rounded) {
        ImageVector imageVector = _flight;
        if (imageVector != null) {
            return imageVector;
        }
        ImageVector.Builder builder = new ImageVector.Builder("Rounded.Flight", Dp.m5678constructorimpl(24.0f), Dp.m5678constructorimpl(24.0f), 24.0f, 24.0f, 0L, 0, false, 96, null);
        int defaultFillType = VectorKt.getDefaultFillType();
        SolidColor solidColor = new SolidColor(Color.INSTANCE.m3498getBlack0d7_KjU(), null);
        int iM3803getButtKaPHkGw = StrokeCap.INSTANCE.m3803getButtKaPHkGw();
        int iM3813getBevelLxFBmk8 = StrokeJoin.INSTANCE.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderA = c.a(21.0f, 14.58f);
        pathBuilderA.curveToRelative(0.0f, -0.36f, -0.19f, -0.69f, -0.49f, -0.89f);
        pathBuilderA.lineTo(13.0f, 9.0f);
        pathBuilderA.verticalLineTo(3.5f);
        pathBuilderA.curveToRelative(0.0f, -0.83f, -0.67f, -1.5f, -1.5f, -1.5f);
        pathBuilderA.reflectiveCurveTo(10.0f, 2.67f, 10.0f, 3.5f);
        pathBuilderA.verticalLineTo(9.0f);
        pathBuilderA.lineToRelative(-7.51f, 4.69f);
        pathBuilderA.curveToRelative(-0.3f, 0.19f, -0.49f, 0.53f, -0.49f, 0.89f);
        pathBuilderA.curveToRelative(0.0f, 0.7f, 0.68f, 1.21f, 1.36f, 1.0f);
        pathBuilderA.lineTo(10.0f, 13.5f);
        pathBuilderA.verticalLineTo(19.0f);
        pathBuilderA.lineToRelative(-1.8f, 1.35f);
        pathBuilderA.curveToRelative(-0.13f, 0.09f, -0.2f, 0.24f, -0.2f, 0.4f);
        pathBuilderA.verticalLineToRelative(0.59f);
        pathBuilderA.curveToRelative(0.0f, 0.33f, 0.32f, 0.57f, 0.64f, 0.48f);
        pathBuilderA.lineTo(11.5f, 21.0f);
        pathBuilderA.lineToRelative(2.86f, 0.82f);
        pathBuilderA.curveToRelative(0.32f, 0.09f, 0.64f, -0.15f, 0.64f, -0.48f);
        pathBuilderA.verticalLineToRelative(-0.59f);
        pathBuilderA.curveToRelative(0.0f, -0.16f, -0.07f, -0.31f, -0.2f, -0.4f);
        pathBuilderA.lineTo(13.0f, 19.0f);
        pathBuilderA.verticalLineToRelative(-5.5f);
        pathBuilderA.lineToRelative(6.64f, 2.08f);
        pathBuilderA.curveToRelative(0.68f, 0.21f, 1.36f, -0.3f, 1.36f, -1.0f);
        pathBuilderA.close();
        ImageVector imageVectorBuild = ImageVector.Builder.m4024addPathoIyEayM$default(builder, pathBuilderA.getNodes(), defaultFillType, "", solidColor, 1.0f, null, 1.0f, 1.0f, iM3803getButtKaPHkGw, iM3813getBevelLxFBmk8, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null).build();
        _flight = imageVectorBuild;
        return imageVectorBuild;
    }
}
