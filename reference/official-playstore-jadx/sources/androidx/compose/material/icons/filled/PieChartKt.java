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
import v.a;
import v.d;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\"\u0010\u0010\u0000\u001a\u0004\u0018\u00010\u0001X\u0082\u000e¢\u0006\u0002\n\u0000\"\u0015\u0010\u0002\u001a\u00020\u0001*\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"_pieChart", "Landroidx/compose/ui/graphics/vector/ImageVector;", "PieChart", "Landroidx/compose/material/icons/Icons$Filled;", "getPieChart", "(Landroidx/compose/material/icons/Icons$Filled;)Landroidx/compose/ui/graphics/vector/ImageVector;", "material-icons-extended_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class PieChartKt {
    private static ImageVector _pieChart;

    public static final ImageVector getPieChart(Icons.Filled filled) {
        ImageVector imageVector = _pieChart;
        if (imageVector != null) {
            return imageVector;
        }
        ImageVector.Builder builder = new ImageVector.Builder("Filled.PieChart", Dp.m5678constructorimpl(24.0f), Dp.m5678constructorimpl(24.0f), 24.0f, 24.0f, 0L, 0, false, 96, null);
        int defaultFillType = VectorKt.getDefaultFillType();
        SolidColor solidColor = new SolidColor(Color.INSTANCE.m3498getBlack0d7_KjU(), null);
        int iM3803getButtKaPHkGw = StrokeCap.INSTANCE.m3803getButtKaPHkGw();
        int iM3813getBevelLxFBmk8 = StrokeJoin.INSTANCE.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderI = a.i(11.0f, 2.0f, 20.0f);
        pathBuilderI.curveToRelative(-5.07f, -0.5f, -9.0f, -4.79f, -9.0f, -10.0f);
        pathBuilderI.reflectiveCurveToRelative(3.93f, -9.5f, 9.0f, -10.0f);
        d.k(pathBuilderI, 13.03f, 2.0f, 8.99f);
        pathBuilderI.lineTo(22.0f, 10.99f);
        pathBuilderI.curveToRelative(-0.47f, -4.74f, -4.24f, -8.52f, -8.97f, -8.99f);
        a.p(pathBuilderI, 13.03f, 13.01f, 13.03f, 22.0f);
        pathBuilderI.curveToRelative(4.74f, -0.47f, 8.5f, -4.25f, 8.97f, -8.99f);
        ImageVector imageVectorBuild = ImageVector.Builder.m4024addPathoIyEayM$default(builder, a.d(pathBuilderI, -8.97f), defaultFillType, "", solidColor, 1.0f, null, 1.0f, 1.0f, iM3803getButtKaPHkGw, iM3813getBevelLxFBmk8, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null).build();
        _pieChart = imageVectorBuild;
        return imageVectorBuild;
    }
}
