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
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\"\u0010\u0010\u0000\u001a\u0004\u0018\u00010\u0001X\u0082\u000e¢\u0006\u0002\n\u0000\"\u0015\u0010\u0002\u001a\u00020\u0001*\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"_ssidChart", "Landroidx/compose/ui/graphics/vector/ImageVector;", "SsidChart", "Landroidx/compose/material/icons/Icons$Rounded;", "getSsidChart", "(Landroidx/compose/material/icons/Icons$Rounded;)Landroidx/compose/ui/graphics/vector/ImageVector;", "material-icons-extended_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class SsidChartKt {
    private static ImageVector _ssidChart;

    public static final ImageVector getSsidChart(Icons.Rounded rounded) {
        ImageVector imageVector = _ssidChart;
        if (imageVector != null) {
            return imageVector;
        }
        ImageVector.Builder builder = new ImageVector.Builder("Rounded.SsidChart", Dp.m5678constructorimpl(24.0f), Dp.m5678constructorimpl(24.0f), 24.0f, 24.0f, 0L, 0, false, 96, null);
        int defaultFillType = VectorKt.getDefaultFillType();
        SolidColor solidColor = new SolidColor(Color.INSTANCE.m3498getBlack0d7_KjU(), null);
        int iM3803getButtKaPHkGw = StrokeCap.INSTANCE.m3803getButtKaPHkGw();
        int iM3813getBevelLxFBmk8 = StrokeJoin.INSTANCE.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderA = c.a(3.0f, 9.03f);
        pathBuilderA.curveToRelative(0.0f, -0.32f, 0.15f, -0.62f, 0.41f, -0.81f);
        pathBuilderA.lineTo(7.14f, 5.5f);
        pathBuilderA.curveToRelative(0.4f, -0.29f, 0.95f, -0.25f, 1.3f, 0.1f);
        pathBuilderA.lineToRelative(3.78f, 3.78f);
        pathBuilderA.lineToRelative(7.2f, -5.23f);
        pathBuilderA.curveTo(20.07f, 3.67f, 21.0f, 4.14f, 21.0f, 4.96f);
        pathBuilderA.curveToRelative(0.0f, 0.32f, -0.15f, 0.62f, -0.41f, 0.81f);
        pathBuilderA.lineToRelative(-7.9f, 5.73f);
        pathBuilderA.curveToRelative(-0.4f, 0.29f, -0.95f, 0.25f, -1.29f, -0.1f);
        pathBuilderA.lineTo(7.62f, 7.62f);
        pathBuilderA.lineTo(4.59f, 9.84f);
        pathBuilderA.curveTo(3.93f, 10.32f, 3.0f, 9.85f, 3.0f, 9.03f);
        pathBuilderA.close();
        pathBuilderA.moveTo(21.0f, 16.0f);
        pathBuilderA.curveToRelative(0.0f, -0.55f, -0.45f, -1.0f, -1.0f, -1.0f);
        pathBuilderA.horizontalLineToRelative(-3.35f);
        pathBuilderA.curveToRelative(-0.23f, 0.0f, -0.45f, 0.08f, -0.62f, 0.22f);
        pathBuilderA.lineToRelative(-3.9f, 3.12f);
        pathBuilderA.lineTo(6.6f, 12.99f);
        pathBuilderA.curveToRelative(-0.35f, -0.34f, -0.88f, -0.38f, -1.27f, -0.1f);
        pathBuilderA.lineToRelative(-1.9f, 1.35f);
        pathBuilderA.curveTo(3.16f, 14.43f, 3.0f, 14.74f, 3.0f, 15.06f);
        pathBuilderA.curveToRelative(0.0f, 0.81f, 0.92f, 1.29f, 1.58f, 0.81f);
        pathBuilderA.lineTo(5.8f, 15.0f);
        pathBuilderA.lineToRelative(5.57f, 5.39f);
        pathBuilderA.curveToRelative(0.36f, 0.35f, 0.93f, 0.38f, 1.32f, 0.06f);
        pathBuilderA.lineTo(17.0f, 17.0f);
        pathBuilderA.horizontalLineToRelative(3.0f);
        pathBuilderA.curveTo(20.55f, 17.0f, 21.0f, 16.55f, 21.0f, 16.0f);
        pathBuilderA.close();
        ImageVector imageVectorBuild = ImageVector.Builder.m4024addPathoIyEayM$default(builder, pathBuilderA.getNodes(), defaultFillType, "", solidColor, 1.0f, null, 1.0f, 1.0f, iM3803getButtKaPHkGw, iM3813getBevelLxFBmk8, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null).build();
        _ssidChart = imageVectorBuild;
        return imageVectorBuild;
    }
}
