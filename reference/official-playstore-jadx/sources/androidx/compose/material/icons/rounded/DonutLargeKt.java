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
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\"\u0010\u0010\u0000\u001a\u0004\u0018\u00010\u0001X\u0082\u000e¢\u0006\u0002\n\u0000\"\u0015\u0010\u0002\u001a\u00020\u0001*\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"_donutLarge", "Landroidx/compose/ui/graphics/vector/ImageVector;", "DonutLarge", "Landroidx/compose/material/icons/Icons$Rounded;", "getDonutLarge", "(Landroidx/compose/material/icons/Icons$Rounded;)Landroidx/compose/ui/graphics/vector/ImageVector;", "material-icons-extended_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class DonutLargeKt {
    private static ImageVector _donutLarge;

    public static final ImageVector getDonutLarge(Icons.Rounded rounded) {
        ImageVector imageVector = _donutLarge;
        if (imageVector != null) {
            return imageVector;
        }
        ImageVector.Builder builder = new ImageVector.Builder("Rounded.DonutLarge", Dp.m5678constructorimpl(24.0f), Dp.m5678constructorimpl(24.0f), 24.0f, 24.0f, 0L, 0, false, 96, null);
        int defaultFillType = VectorKt.getDefaultFillType();
        SolidColor solidColor = new SolidColor(Color.INSTANCE.m3498getBlack0d7_KjU(), null);
        int iM3803getButtKaPHkGw = StrokeCap.INSTANCE.m3803getButtKaPHkGw();
        int iM3813getBevelLxFBmk8 = StrokeJoin.INSTANCE.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderA = c.a(14.07f, 5.32f);
        pathBuilderA.curveTo(16.26f, 6.0f, 18.0f, 7.74f, 18.68f, 9.93f);
        pathBuilderA.curveToRelative(0.19f, 0.63f, 0.76f, 1.07f, 1.41f, 1.07f);
        pathBuilderA.horizontalLineToRelative(0.04f);
        pathBuilderA.curveToRelative(1.0f, 0.0f, 1.72f, -0.96f, 1.43f, -1.91f);
        pathBuilderA.curveToRelative(-0.97f, -3.18f, -3.48f, -5.69f, -6.66f, -6.66f);
        pathBuilderA.curveToRelative(-0.94f, -0.29f, -1.9f, 0.43f, -1.9f, 1.43f);
        pathBuilderA.verticalLineToRelative(0.04f);
        pathBuilderA.curveToRelative(0.0f, 0.66f, 0.44f, 1.23f, 1.07f, 1.42f);
        pathBuilderA.close();
        pathBuilderA.moveTo(18.68f, 14.07f);
        pathBuilderA.curveToRelative(-0.68f, 2.2f, -2.42f, 3.93f, -4.61f, 4.61f);
        pathBuilderA.curveToRelative(-0.63f, 0.19f, -1.07f, 0.76f, -1.07f, 1.41f);
        pathBuilderA.verticalLineToRelative(0.04f);
        pathBuilderA.curveToRelative(0.0f, 1.0f, 0.96f, 1.72f, 1.91f, 1.43f);
        pathBuilderA.curveToRelative(3.18f, -0.97f, 5.69f, -3.48f, 6.66f, -6.66f);
        pathBuilderA.curveToRelative(0.29f, -0.95f, -0.43f, -1.91f, -1.42f, -1.91f);
        pathBuilderA.horizontalLineToRelative(-0.05f);
        pathBuilderA.curveToRelative(-0.66f, 0.01f, -1.23f, 0.45f, -1.42f, 1.08f);
        pathBuilderA.close();
        pathBuilderA.moveTo(11.0f, 20.11f);
        pathBuilderA.curveToRelative(0.0f, -0.67f, -0.45f, -1.24f, -1.09f, -1.44f);
        pathBuilderA.curveTo(7.07f, 17.78f, 5.0f, 15.13f, 5.0f, 12.0f);
        pathBuilderA.reflectiveCurveToRelative(2.07f, -5.78f, 4.91f, -6.67f);
        pathBuilderA.curveToRelative(0.64f, -0.2f, 1.09f, -0.77f, 1.09f, -1.44f);
        pathBuilderA.verticalLineToRelative(-0.01f);
        pathBuilderA.curveToRelative(0.0f, -1.0f, -0.97f, -1.74f, -1.93f, -1.44f);
        pathBuilderA.curveTo(4.98f, 3.69f, 2.0f, 7.5f, 2.0f, 12.0f);
        pathBuilderA.curveToRelative(0.0f, 4.5f, 2.98f, 8.31f, 7.07f, 9.56f);
        pathBuilderA.curveToRelative(0.96f, 0.3f, 1.93f, -0.44f, 1.93f, -1.45f);
        pathBuilderA.close();
        ImageVector imageVectorBuild = ImageVector.Builder.m4024addPathoIyEayM$default(builder, pathBuilderA.getNodes(), defaultFillType, "", solidColor, 1.0f, null, 1.0f, 1.0f, iM3803getButtKaPHkGw, iM3813getBevelLxFBmk8, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null).build();
        _donutLarge = imageVectorBuild;
        return imageVectorBuild;
    }
}
