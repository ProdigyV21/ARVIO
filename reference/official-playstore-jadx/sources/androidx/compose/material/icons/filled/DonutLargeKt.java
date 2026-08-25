package androidx.compose.material.icons.filled;

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
import v.e;
import w.a;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\"\u0010\u0010\u0000\u001a\u0004\u0018\u00010\u0001X\u0082\u000e¢\u0006\u0002\n\u0000\"\u0015\u0010\u0002\u001a\u00020\u0001*\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"_donutLarge", "Landroidx/compose/ui/graphics/vector/ImageVector;", "DonutLarge", "Landroidx/compose/material/icons/Icons$Filled;", "getDonutLarge", "(Landroidx/compose/material/icons/Icons$Filled;)Landroidx/compose/ui/graphics/vector/ImageVector;", "material-icons-extended_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class DonutLargeKt {
    private static ImageVector _donutLarge;

    public static final ImageVector getDonutLarge(Icons.Filled filled) {
        ImageVector imageVector = _donutLarge;
        if (imageVector != null) {
            return imageVector;
        }
        ImageVector.Builder builder = new ImageVector.Builder("Filled.DonutLarge", Dp.m5678constructorimpl(24.0f), Dp.m5678constructorimpl(24.0f), 24.0f, 24.0f, 0L, 0, false, 96, null);
        int defaultFillType = VectorKt.getDefaultFillType();
        SolidColor solidColor = new SolidColor(Color.INSTANCE.m3498getBlack0d7_KjU(), null);
        int iM3803getButtKaPHkGw = StrokeCap.INSTANCE.m3803getButtKaPHkGw();
        int iM3813getBevelLxFBmk8 = StrokeJoin.INSTANCE.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderA = e.a(11.0f, 5.08f, 2.0f);
        pathBuilderA.curveTo(6.0f, 2.5f, 2.0f, 6.81f, 2.0f, 12.0f);
        pathBuilderA.reflectiveCurveToRelative(4.0f, 9.5f, 9.0f, 10.0f);
        pathBuilderA.verticalLineToRelative(-3.08f);
        pathBuilderA.curveToRelative(-3.0f, -0.48f, -6.0f, -3.4f, -6.0f, -6.92f);
        pathBuilderA.reflectiveCurveTo(8.0f, 5.56f, 11.0f, 5.08f);
        a.r(pathBuilderA, 18.97f, 11.0f, 22.0f);
        pathBuilderA.curveToRelative(-0.47f, -5.0f, -4.0f, -8.53f, -9.0f, -9.0f);
        pathBuilderA.verticalLineToRelative(3.08f);
        pathBuilderA.curveTo(16.0f, 5.51f, 18.54f, 8.0f, 18.97f, 11.0f);
        a.u(pathBuilderA, 13.0f, 18.92f, 22.0f);
        pathBuilderA.curveToRelative(5.0f, -0.47f, 8.53f, -4.0f, 9.0f, -9.0f);
        pathBuilderA.horizontalLineToRelative(-3.03f);
        pathBuilderA.curveTo(18.54f, 16.0f, 16.0f, 18.49f, 13.0f, 18.92f);
        pathBuilderA.close();
        ImageVector imageVectorBuild = ImageVector.Builder.m4024addPathoIyEayM$default(builder, pathBuilderA.getNodes(), defaultFillType, "", solidColor, 1.0f, null, 1.0f, 1.0f, iM3803getButtKaPHkGw, iM3813getBevelLxFBmk8, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null).build();
        _donutLarge = imageVectorBuild;
        return imageVectorBuild;
    }
}
