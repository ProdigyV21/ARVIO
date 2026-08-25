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
import v.b;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\"\u0010\u0010\u0000\u001a\u0004\u0018\u00010\u0001X\u0082\u000e¢\u0006\u0002\n\u0000\"\u0015\u0010\u0002\u001a\u00020\u0001*\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"_ssidChart", "Landroidx/compose/ui/graphics/vector/ImageVector;", "SsidChart", "Landroidx/compose/material/icons/Icons$Filled;", "getSsidChart", "(Landroidx/compose/material/icons/Icons$Filled;)Landroidx/compose/ui/graphics/vector/ImageVector;", "material-icons-extended_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class SsidChartKt {
    private static ImageVector _ssidChart;

    public static final ImageVector getSsidChart(Icons.Filled filled) {
        ImageVector imageVector = _ssidChart;
        if (imageVector != null) {
            return imageVector;
        }
        ImageVector.Builder builder = new ImageVector.Builder("Filled.SsidChart", Dp.m5678constructorimpl(24.0f), Dp.m5678constructorimpl(24.0f), 24.0f, 24.0f, 0L, 0, false, 96, null);
        int defaultFillType = VectorKt.getDefaultFillType();
        SolidColor solidColor = new SolidColor(Color.INSTANCE.m3498getBlack0d7_KjU(), null);
        int iM3803getButtKaPHkGw = StrokeCap.INSTANCE.m3803getButtKaPHkGw();
        int iM3813getBevelLxFBmk8 = StrokeJoin.INSTANCE.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderJ = a.j(21.0f, 5.47f, 12.0f, 12.0f);
        pathBuilderJ.lineTo(7.62f, 7.62f);
        pathBuilderJ.lineTo(3.0f, 11.0f);
        pathBuilderJ.verticalLineTo(8.52f);
        pathBuilderJ.lineTo(7.83f, 5.0f);
        pathBuilderJ.lineToRelative(4.38f, 4.38f);
        b.y(pathBuilderJ, 21.0f, 3.0f, 21.0f, 5.47f);
        pathBuilderJ.moveTo(21.0f, 15.0f);
        pathBuilderJ.horizontalLineToRelative(-4.7f);
        pathBuilderJ.lineToRelative(-4.17f, 3.34f);
        pathBuilderJ.lineTo(6.0f, 12.41f);
        pathBuilderJ.lineToRelative(-3.0f, 2.13f);
        pathBuilderJ.lineTo(3.0f, 17.0f);
        pathBuilderJ.lineToRelative(2.8f, -2.0f);
        pathBuilderJ.lineToRelative(6.2f, 6.0f);
        pathBuilderJ.lineToRelative(5.0f, -4.0f);
        pathBuilderJ.horizontalLineToRelative(4.0f);
        pathBuilderJ.verticalLineTo(15.0f);
        pathBuilderJ.close();
        ImageVector imageVectorBuild = ImageVector.Builder.m4024addPathoIyEayM$default(builder, pathBuilderJ.getNodes(), defaultFillType, "", solidColor, 1.0f, null, 1.0f, 1.0f, iM3803getButtKaPHkGw, iM3813getBevelLxFBmk8, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null).build();
        _ssidChart = imageVectorBuild;
        return imageVectorBuild;
    }
}
