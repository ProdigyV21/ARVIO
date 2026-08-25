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

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\"\u0010\u0010\u0000\u001a\u0004\u0018\u00010\u0001X\u0082\u000e¢\u0006\u0002\n\u0000\"\u0015\u0010\u0002\u001a\u00020\u0001*\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"_wbAuto", "Landroidx/compose/ui/graphics/vector/ImageVector;", "WbAuto", "Landroidx/compose/material/icons/Icons$Rounded;", "getWbAuto", "(Landroidx/compose/material/icons/Icons$Rounded;)Landroidx/compose/ui/graphics/vector/ImageVector;", "material-icons-extended_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class WbAutoKt {
    private static ImageVector _wbAuto;

    public static final ImageVector getWbAuto(Icons.Rounded rounded) {
        ImageVector imageVector = _wbAuto;
        if (imageVector != null) {
            return imageVector;
        }
        ImageVector.Builder builder = new ImageVector.Builder("Rounded.WbAuto", Dp.m5678constructorimpl(24.0f), Dp.m5678constructorimpl(24.0f), 24.0f, 24.0f, 0L, 0, false, 96, null);
        int defaultFillType = VectorKt.getDefaultFillType();
        SolidColor solidColor = new SolidColor(Color.INSTANCE.m3498getBlack0d7_KjU(), null);
        int iM3803getButtKaPHkGw = StrokeCap.INSTANCE.m3803getButtKaPHkGw();
        int iM3813getBevelLxFBmk8 = StrokeJoin.INSTANCE.m3813getBevelLxFBmk8();
        PathBuilder pathBuilder = new PathBuilder();
        pathBuilder.moveTo(6.85f, 12.65f);
        pathBuilder.horizontalLineToRelative(2.3f);
        pathBuilder.lineTo(8.0f, 9.0f);
        pathBuilder.close();
        pathBuilder.moveTo(22.72f, 7.0f);
        pathBuilder.curveToRelative(-0.42f, 0.0f, -0.77f, 0.3f, -0.85f, 0.7f);
        pathBuilder.lineToRelative(-1.07f, 5.59f);
        pathBuilder.lineToRelative(-1.31f, -5.51f);
        pathBuilder.curveToRelative(-0.11f, -0.46f, -0.52f, -0.78f, -0.99f, -0.78f);
        pathBuilder.reflectiveCurveToRelative(-0.88f, 0.32f, -0.98f, 0.78f);
        pathBuilder.lineToRelative(-1.31f, 5.51f);
        pathBuilder.lineToRelative(-1.07f, -5.59f);
        pathBuilder.curveToRelative(-0.08f, -0.4f, -0.44f, -0.7f, -0.85f, -0.7f);
        pathBuilder.curveToRelative(-0.01f, 0.0f, -0.03f, 0.01f, -0.04f, 0.01f);
        pathBuilder.curveTo(12.78f, 5.18f, 10.53f, 4.0f, 8.0f, 4.0f);
        pathBuilder.curveToRelative(-4.42f, 0.0f, -8.0f, 3.58f, -8.0f, 8.0f);
        pathBuilder.reflectiveCurveToRelative(3.58f, 8.0f, 8.0f, 8.0f);
        pathBuilder.curveToRelative(3.17f, 0.0f, 5.9f, -1.85f, 7.2f, -4.52f);
        pathBuilder.curveToRelative(0.2f, 0.32f, 0.55f, 0.52f, 0.94f, 0.52f);
        pathBuilder.curveToRelative(0.51f, 0.0f, 0.95f, -0.35f, 1.07f, -0.84f);
        pathBuilder.lineTo(18.5f, 9.9f);
        pathBuilder.lineToRelative(1.29f, 5.26f);
        pathBuilder.curveToRelative(0.12f, 0.49f, 0.57f, 0.84f, 1.07f, 0.84f);
        pathBuilder.curveToRelative(0.52f, 0.0f, 0.96f, -0.36f, 1.08f, -0.86f);
        pathBuilder.lineToRelative(1.61f, -7.08f);
        pathBuilder.curveToRelative(0.13f, -0.54f, -0.28f, -1.06f, -0.83f, -1.06f);
        pathBuilder.close();
        pathBuilder.moveTo(10.93f, 16.0f);
        pathBuilder.curveToRelative(-0.38f, 0.0f, -0.72f, -0.24f, -0.84f, -0.6f);
        pathBuilder.lineTo(9.6f, 14.0f);
        pathBuilder.lineTo(6.4f, 14.0f);
        pathBuilder.lineToRelative(-0.49f, 1.4f);
        pathBuilder.curveToRelative(-0.13f, 0.36f, -0.46f, 0.6f, -0.84f, 0.6f);
        pathBuilder.curveToRelative(-0.62f, 0.0f, -1.05f, -0.61f, -0.84f, -1.19f);
        pathBuilder.lineToRelative(2.44f, -6.86f);
        pathBuilder.curveTo(6.87f, 7.38f, 7.4f, 7.0f, 8.0f, 7.0f);
        pathBuilder.reflectiveCurveToRelative(1.13f, 0.38f, 1.34f, 0.94f);
        pathBuilder.lineToRelative(2.44f, 6.86f);
        pathBuilder.curveToRelative(0.2f, 0.59f, -0.23f, 1.2f, -0.85f, 1.2f);
        pathBuilder.close();
        ImageVector imageVectorBuild = ImageVector.Builder.m4024addPathoIyEayM$default(builder, pathBuilder.getNodes(), defaultFillType, "", solidColor, 1.0f, null, 1.0f, 1.0f, iM3803getButtKaPHkGw, iM3813getBevelLxFBmk8, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null).build();
        _wbAuto = imageVectorBuild;
        return imageVectorBuild;
    }
}
