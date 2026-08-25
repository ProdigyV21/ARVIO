package androidx.compose.material.icons.outlined;

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
import v.c;
import v.f;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\"\u0010\u0010\u0000\u001a\u0004\u0018\u00010\u0001X\u0082\u000e¢\u0006\u0002\n\u0000\"\u0015\u0010\u0002\u001a\u00020\u0001*\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"_pivotTableChart", "Landroidx/compose/ui/graphics/vector/ImageVector;", "PivotTableChart", "Landroidx/compose/material/icons/Icons$Outlined;", "getPivotTableChart", "(Landroidx/compose/material/icons/Icons$Outlined;)Landroidx/compose/ui/graphics/vector/ImageVector;", "material-icons-extended_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class PivotTableChartKt {
    private static ImageVector _pivotTableChart;

    public static final ImageVector getPivotTableChart(Icons.Outlined outlined) {
        ImageVector imageVector = _pivotTableChart;
        if (imageVector != null) {
            return imageVector;
        }
        ImageVector.Builder builder = new ImageVector.Builder("Outlined.PivotTableChart", Dp.m5678constructorimpl(24.0f), Dp.m5678constructorimpl(24.0f), 24.0f, 24.0f, 0L, 0, false, 96, null);
        int defaultFillType = VectorKt.getDefaultFillType();
        SolidColor solidColor = new SolidColor(Color.INSTANCE.m3498getBlack0d7_KjU(), null);
        int iM3803getButtKaPHkGw = StrokeCap.INSTANCE.m3803getButtKaPHkGw();
        int iM3813getBevelLxFBmk8 = StrokeJoin.INSTANCE.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderA = c.a(21.0f, 5.0f);
        pathBuilderA.curveToRelative(0.0f, -1.1f, -0.9f, -2.0f, -2.0f, -2.0f);
        f.w(pathBuilderA, -9.0f, 5.0f, 11.0f, 5.0f);
        pathBuilderA.moveTo(3.0f, 19.0f);
        pathBuilderA.curveToRelative(0.0f, 1.1f, 0.9f, 2.0f, 2.0f, 2.0f);
        a0.c.D(pathBuilderA, 3.0f, 10.0f, 3.0f, 19.0f);
        c.m(pathBuilderA, 3.0f, 5.0f, 3.0f, 5.0f);
        pathBuilderA.verticalLineTo(3.0f);
        pathBuilderA.horizontalLineTo(5.0f);
        pathBuilderA.curveTo(3.9f, 3.0f, 3.0f, 3.9f, 3.0f, 5.0f);
        a.p(pathBuilderA, 18.0f, 8.99f, 14.0f, 13.0f);
        pathBuilderA.lineToRelative(1.41f, 1.41f);
        pathBuilderA.lineToRelative(1.59f, -1.6f);
        pathBuilderA.verticalLineTo(15.0f);
        pathBuilderA.curveToRelative(0.0f, 1.1f, -0.9f, 2.0f, -2.0f, 2.0f);
        pathBuilderA.horizontalLineToRelative(-2.17f);
        pathBuilderA.lineToRelative(1.59f, -1.59f);
        pathBuilderA.lineTo(13.0f, 14.0f);
        pathBuilderA.lineToRelative(-4.0f, 4.0f);
        pathBuilderA.lineToRelative(4.0f, 4.0f);
        pathBuilderA.lineToRelative(1.41f, -1.41f);
        pathBuilderA.lineTo(12.83f, 19.0f);
        pathBuilderA.horizontalLineTo(15.0f);
        pathBuilderA.curveToRelative(2.21f, 0.0f, 4.0f, -1.79f, 4.0f, -4.0f);
        pathBuilderA.verticalLineToRelative(-2.18f);
        pathBuilderA.lineToRelative(1.59f, 1.6f);
        ImageVector imageVectorBuild = ImageVector.Builder.m4024addPathoIyEayM$default(builder, a.f(pathBuilderA, 22.0f, 13.0f, 18.0f, 8.99f), defaultFillType, "", solidColor, 1.0f, null, 1.0f, 1.0f, iM3803getButtKaPHkGw, iM3813getBevelLxFBmk8, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null).build();
        _pivotTableChart = imageVectorBuild;
        return imageVectorBuild;
    }
}
