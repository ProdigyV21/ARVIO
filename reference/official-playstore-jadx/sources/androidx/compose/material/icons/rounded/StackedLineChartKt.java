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
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\"\u0010\u0010\u0000\u001a\u0004\u0018\u00010\u0001X\u0082\u000e¢\u0006\u0002\n\u0000\"\u0015\u0010\u0002\u001a\u00020\u0001*\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"_stackedLineChart", "Landroidx/compose/ui/graphics/vector/ImageVector;", "StackedLineChart", "Landroidx/compose/material/icons/Icons$Rounded;", "getStackedLineChart", "(Landroidx/compose/material/icons/Icons$Rounded;)Landroidx/compose/ui/graphics/vector/ImageVector;", "material-icons-extended_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class StackedLineChartKt {
    private static ImageVector _stackedLineChart;

    public static final ImageVector getStackedLineChart(Icons.Rounded rounded) {
        ImageVector imageVector = _stackedLineChart;
        if (imageVector != null) {
            return imageVector;
        }
        ImageVector.Builder builder = new ImageVector.Builder("Rounded.StackedLineChart", Dp.m5678constructorimpl(24.0f), Dp.m5678constructorimpl(24.0f), 24.0f, 24.0f, 0L, 0, false, 96, null);
        int defaultFillType = VectorKt.getDefaultFillType();
        SolidColor solidColor = new SolidColor(Color.INSTANCE.m3498getBlack0d7_KjU(), null);
        int iM3803getButtKaPHkGw = StrokeCap.INSTANCE.m3803getButtKaPHkGw();
        int iM3813getBevelLxFBmk8 = StrokeJoin.INSTANCE.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderJ = a.j(2.79f, 14.78f, 2.7f, 14.69f);
        pathBuilderJ.curveToRelative(-0.39f, -0.39f, -0.39f, -1.02f, 0.0f, -1.41f);
        pathBuilderJ.lineToRelative(6.09f, -6.1f);
        pathBuilderJ.curveToRelative(0.39f, -0.39f, 1.02f, -0.39f, 1.41f, 0.0f);
        pathBuilderJ.lineToRelative(3.29f, 3.29f);
        pathBuilderJ.lineToRelative(6.39f, -7.18f);
        pathBuilderJ.curveToRelative(0.38f, -0.43f, 1.05f, -0.44f, 1.45f, -0.04f);
        pathBuilderJ.lineToRelative(0.0f, 0.0f);
        pathBuilderJ.curveToRelative(0.37f, 0.38f, 0.39f, 0.98f, 0.04f, 1.37f);
        pathBuilderJ.lineToRelative(-7.17f, 8.07f);
        pathBuilderJ.curveToRelative(-0.38f, 0.43f, -1.04f, 0.45f, -1.45f, 0.04f);
        pathBuilderJ.lineTo(9.5f, 9.48f);
        pathBuilderJ.lineToRelative(-5.3f, 5.3f);
        pathBuilderJ.curveTo(3.82f, 15.17f, 3.18f, 15.17f, 2.79f, 14.78f);
        a.z(pathBuilderJ, 4.2f, 20.78f, 5.3f, -5.3f);
        pathBuilderJ.lineToRelative(3.25f, 3.25f);
        pathBuilderJ.curveToRelative(0.41f, 0.41f, 1.07f, 0.39f, 1.45f, -0.04f);
        pathBuilderJ.lineToRelative(7.17f, -8.07f);
        pathBuilderJ.curveToRelative(0.35f, -0.39f, 0.33f, -0.99f, -0.04f, -1.37f);
        pathBuilderJ.lineToRelative(0.0f, 0.0f);
        pathBuilderJ.curveToRelative(-0.4f, -0.4f, -1.07f, -0.39f, -1.45f, 0.04f);
        pathBuilderJ.lineToRelative(-6.39f, 7.18f);
        pathBuilderJ.lineToRelative(-3.29f, -3.29f);
        pathBuilderJ.curveToRelative(-0.39f, -0.39f, -1.02f, -0.39f, -1.41f, 0.0f);
        pathBuilderJ.lineToRelative(-6.09f, 6.1f);
        pathBuilderJ.curveToRelative(-0.39f, 0.39f, -0.39f, 1.02f, 0.0f, 1.41f);
        pathBuilderJ.lineToRelative(0.09f, 0.09f);
        pathBuilderJ.curveTo(3.18f, 21.17f, 3.82f, 21.17f, 4.2f, 20.78f);
        pathBuilderJ.close();
        ImageVector imageVectorBuild = ImageVector.Builder.m4024addPathoIyEayM$default(builder, pathBuilderJ.getNodes(), defaultFillType, "", solidColor, 1.0f, null, 1.0f, 1.0f, iM3803getButtKaPHkGw, iM3813getBevelLxFBmk8, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null).build();
        _stackedLineChart = imageVectorBuild;
        return imageVectorBuild;
    }
}
