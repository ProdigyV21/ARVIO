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
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\"\u0010\u0010\u0000\u001a\u0004\u0018\u00010\u0001X\u0082\u000e¢\u0006\u0002\n\u0000\"\u0015\u0010\u0002\u001a\u00020\u0001*\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"_rocket", "Landroidx/compose/ui/graphics/vector/ImageVector;", "Rocket", "Landroidx/compose/material/icons/Icons$Rounded;", "getRocket", "(Landroidx/compose/material/icons/Icons$Rounded;)Landroidx/compose/ui/graphics/vector/ImageVector;", "material-icons-extended_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class RocketKt {
    private static ImageVector _rocket;

    public static final ImageVector getRocket(Icons.Rounded rounded) {
        ImageVector imageVector = _rocket;
        if (imageVector != null) {
            return imageVector;
        }
        ImageVector.Builder builder = new ImageVector.Builder("Rounded.Rocket", Dp.m5678constructorimpl(24.0f), Dp.m5678constructorimpl(24.0f), 24.0f, 24.0f, 0L, 0, false, 96, null);
        int defaultFillType = VectorKt.getDefaultFillType();
        SolidColor solidColor = new SolidColor(Color.INSTANCE.m3498getBlack0d7_KjU(), null);
        int iM3803getButtKaPHkGw = StrokeCap.INSTANCE.m3803getButtKaPHkGw();
        int iM3813getBevelLxFBmk8 = StrokeJoin.INSTANCE.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderA = c.a(11.41f, 2.87f);
        pathBuilderA.curveToRelative(0.35f, -0.26f, 0.82f, -0.26f, 1.18f, 0.0f);
        pathBuilderA.curveTo(13.81f, 3.75f, 16.5f, 6.46f, 16.5f, 13.0f);
        pathBuilderA.curveToRelative(0.0f, 2.16f, -0.78f, 4.76f, -1.36f, 6.35f);
        pathBuilderA.curveTo(15.0f, 19.74f, 14.63f, 20.0f, 14.21f, 20.0f);
        pathBuilderA.lineToRelative(-4.41f, 0.0f);
        pathBuilderA.curveToRelative(-0.42f, 0.0f, -0.8f, -0.26f, -0.94f, -0.65f);
        pathBuilderA.curveTo(8.28f, 17.76f, 7.5f, 15.16f, 7.5f, 13.0f);
        pathBuilderA.curveTo(7.5f, 6.46f, 10.19f, 3.75f, 11.41f, 2.87f);
        pathBuilderA.close();
        pathBuilderA.moveTo(14.0f, 11.0f);
        pathBuilderA.curveToRelative(0.0f, -1.1f, -0.9f, -2.0f, -2.0f, -2.0f);
        pathBuilderA.reflectiveCurveToRelative(-2.0f, 0.9f, -2.0f, 2.0f);
        pathBuilderA.reflectiveCurveToRelative(0.9f, 2.0f, 2.0f, 2.0f);
        pathBuilderA.reflectiveCurveTo(14.0f, 12.1f, 14.0f, 11.0f);
        pathBuilderA.close();
        pathBuilderA.moveTo(7.69f, 20.52f);
        pathBuilderA.curveToRelative(-0.48f, -1.23f, -1.52f, -4.17f, -1.67f, -6.87f);
        pathBuilderA.lineToRelative(-1.13f, 0.75f);
        pathBuilderA.curveTo(4.33f, 14.78f, 4.0f, 15.4f, 4.0f, 16.07f);
        pathBuilderA.verticalLineToRelative(4.45f);
        pathBuilderA.curveToRelative(0.0f, 0.71f, 0.71f, 1.19f, 1.37f, 0.93f);
        c.z(pathBuilderA, 7.69f, 20.52f, 20.0f, 20.52f);
        pathBuilderA.verticalLineToRelative(-4.45f);
        pathBuilderA.curveToRelative(0.0f, -0.67f, -0.33f, -1.29f, -0.89f, -1.66f);
        pathBuilderA.lineToRelative(-1.13f, -0.75f);
        pathBuilderA.curveToRelative(-0.15f, 2.69f, -1.2f, 5.64f, -1.67f, 6.87f);
        pathBuilderA.lineToRelative(2.32f, 0.93f);
        pathBuilderA.curveTo(19.29f, 21.71f, 20.0f, 21.23f, 20.0f, 20.52f);
        pathBuilderA.close();
        ImageVector imageVectorBuild = ImageVector.Builder.m4024addPathoIyEayM$default(builder, pathBuilderA.getNodes(), defaultFillType, "", solidColor, 1.0f, null, 1.0f, 1.0f, iM3803getButtKaPHkGw, iM3813getBevelLxFBmk8, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null).build();
        _rocket = imageVectorBuild;
        return imageVectorBuild;
    }
}
