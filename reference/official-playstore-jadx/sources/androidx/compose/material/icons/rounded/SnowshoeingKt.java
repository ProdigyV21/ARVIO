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
import v.b;
import v.c;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\"\u0010\u0010\u0000\u001a\u0004\u0018\u00010\u0001X\u0082\u000e¢\u0006\u0002\n\u0000\"\u0015\u0010\u0002\u001a\u00020\u0001*\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"_snowshoeing", "Landroidx/compose/ui/graphics/vector/ImageVector;", "Snowshoeing", "Landroidx/compose/material/icons/Icons$Rounded;", "getSnowshoeing", "(Landroidx/compose/material/icons/Icons$Rounded;)Landroidx/compose/ui/graphics/vector/ImageVector;", "material-icons-extended_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class SnowshoeingKt {
    private static ImageVector _snowshoeing;

    public static final ImageVector getSnowshoeing(Icons.Rounded rounded) {
        ImageVector imageVector = _snowshoeing;
        if (imageVector != null) {
            return imageVector;
        }
        ImageVector.Builder builder = new ImageVector.Builder("Rounded.Snowshoeing", Dp.m5678constructorimpl(24.0f), Dp.m5678constructorimpl(24.0f), 24.0f, 24.0f, 0L, 0, false, 96, null);
        int defaultFillType = VectorKt.getDefaultFillType();
        SolidColor solidColor = new SolidColor(Color.INSTANCE.m3498getBlack0d7_KjU(), null);
        int iM3803getButtKaPHkGw = StrokeCap.INSTANCE.m3803getButtKaPHkGw();
        int iM3813getBevelLxFBmk8 = StrokeJoin.INSTANCE.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderA = c.a(12.5f, 3.5f);
        pathBuilderA.curveToRelative(0.0f, -1.1f, 0.9f, -2.0f, 2.0f, -2.0f);
        pathBuilderA.reflectiveCurveToRelative(2.0f, 0.9f, 2.0f, 2.0f);
        pathBuilderA.curveToRelative(0.0f, 1.1f, -0.9f, 2.0f, -2.0f, 2.0f);
        pathBuilderA.reflectiveCurveTo(12.5f, 4.6f, 12.5f, 3.5f);
        pathBuilderA.close();
        pathBuilderA.moveTo(20.0f, 11.91f);
        pathBuilderA.curveToRelative(0.0f, -0.49f, -0.36f, -0.9f, -0.84f, -0.98f);
        pathBuilderA.curveToRelative(-1.53f, -0.25f, -2.79f, -1.16f, -3.47f, -2.35f);
        pathBuilderA.lineToRelative(-1.0f, -1.58f);
        pathBuilderA.curveToRelative(-0.4f, -0.6f, -1.0f, -1.0f, -1.7f, -1.0f);
        pathBuilderA.curveToRelative(-0.68f, 0.0f, -1.28f, 0.28f, -4.77f, 1.76f);
        pathBuilderA.curveTo(7.49f, 8.07f, 7.0f, 8.8f, 7.0f, 9.6f);
        pathBuilderA.verticalLineTo(12.0f);
        pathBuilderA.curveToRelative(0.0f, 0.55f, 0.45f, 1.0f, 1.0f, 1.0f);
        pathBuilderA.reflectiveCurveToRelative(1.0f, -0.45f, 1.0f, -1.0f);
        pathBuilderA.verticalLineTo(9.58f);
        pathBuilderA.lineToRelative(1.79f, -0.7f);
        pathBuilderA.lineTo(9.2f, 17.0f);
        pathBuilderA.lineToRelative(-2.88f, 2.03f);
        pathBuilderA.lineToRelative(-1.14f, -1.47f);
        pathBuilderA.lineTo(4.0f, 18.5f);
        pathBuilderA.lineToRelative(2.38f, 3.04f);
        pathBuilderA.curveToRelative(0.51f, 0.65f, 1.16f, 1.15f, 1.88f, 1.41f);
        pathBuilderA.curveToRelative(0.28f, 0.1f, 0.53f, 0.04f, 0.72f, -0.11f);
        pathBuilderA.curveToRelative(0.3f, -0.23f, 0.42f, -0.7f, 0.12f, -1.07f);
        pathBuilderA.curveToRelative(-0.08f, -0.1f, -0.2f, -0.17f, -0.31f, -0.22f);
        pathBuilderA.curveToRelative(-0.43f, -0.18f, -0.82f, -0.45f, -1.14f, -0.83f);
        pathBuilderA.lineToRelative(-0.08f, -0.1f);
        pathBuilderA.lineToRelative(2.85f, -2.01f);
        pathBuilderA.curveToRelative(0.38f, -0.27f, 0.65f, -0.66f, 0.77f, -1.1f);
        pathBuilderA.lineToRelative(0.7f, -2.53f);
        b.u(pathBuilderA, 2.11f, 2.0f, 4.52f, -2.0f);
        pathBuilderA.verticalLineTo(23.0f);
        pathBuilderA.horizontalLineToRelative(3.87f);
        pathBuilderA.curveToRelative(0.82f, 0.0f, 1.61f, -0.21f, 2.26f, -0.61f);
        pathBuilderA.curveToRelative(0.26f, -0.16f, 0.37f, -0.39f, 0.37f, -0.64f);
        pathBuilderA.curveToRelative(0.0f, -0.38f, -0.3f, -0.75f, -0.77f, -0.75f);
        pathBuilderA.curveToRelative(-0.13f, 0.0f, -0.26f, 0.04f, -0.37f, 0.1f);
        pathBuilderA.curveToRelative(-0.4f, 0.23f, -0.87f, 0.37f, -1.36f, 0.4f);
        pathBuilderA.lineToRelative(0.0f, -5.16f);
        pathBuilderA.curveToRelative(0.0f, -0.55f, -0.23f, -1.07f, -0.62f, -1.45f);
        pathBuilderA.lineToRelative(-1.49f, -1.41f);
        pathBuilderA.lineToRelative(0.6f, -3.0f);
        pathBuilderA.curveToRelative(1.07f, 1.24f, 2.63f, 2.15f, 4.37f, 2.43f);
        pathBuilderA.curveTo(19.46f, 13.01f, 20.0f, 12.52f, 20.0f, 11.91f);
        pathBuilderA.close();
        ImageVector imageVectorBuild = ImageVector.Builder.m4024addPathoIyEayM$default(builder, pathBuilderA.getNodes(), defaultFillType, "", solidColor, 1.0f, null, 1.0f, 1.0f, iM3803getButtKaPHkGw, iM3813getBevelLxFBmk8, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null).build();
        _snowshoeing = imageVectorBuild;
        return imageVectorBuild;
    }
}
