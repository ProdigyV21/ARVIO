package androidx.compose.material.icons.twotone;

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
import v.c;
import v.d;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\"\u0010\u0010\u0000\u001a\u0004\u0018\u00010\u0001X\u0082\u000e¢\u0006\u0002\n\u0000\"\u0015\u0010\u0002\u001a\u00020\u0001*\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"_areaChart", "Landroidx/compose/ui/graphics/vector/ImageVector;", "AreaChart", "Landroidx/compose/material/icons/Icons$TwoTone;", "getAreaChart", "(Landroidx/compose/material/icons/Icons$TwoTone;)Landroidx/compose/ui/graphics/vector/ImageVector;", "material-icons-extended_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class AreaChartKt {
    private static ImageVector _areaChart;

    public static final ImageVector getAreaChart(Icons.TwoTone twoTone) {
        ImageVector imageVector = _areaChart;
        if (imageVector != null) {
            return imageVector;
        }
        ImageVector.Builder builder = new ImageVector.Builder("TwoTone.AreaChart", Dp.m5678constructorimpl(24.0f), Dp.m5678constructorimpl(24.0f), 24.0f, 24.0f, 0L, 0, false, 96, null);
        int defaultFillType = VectorKt.getDefaultFillType();
        Color.Companion companion = Color.INSTANCE;
        SolidColor solidColor = new SolidColor(companion.m3498getBlack0d7_KjU(), null);
        StrokeCap.Companion companion2 = StrokeCap.INSTANCE;
        int iM3803getButtKaPHkGw = companion2.m3803getButtKaPHkGw();
        StrokeJoin.Companion companion3 = StrokeJoin.INSTANCE;
        int iM3813getBevelLxFBmk8 = companion3.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderR = a.r(19.0f, 16.95f, -7.0f, -5.45f);
        pathBuilderR.lineToRelative(-4.0f, 5.5f);
        pathBuilderR.lineToRelative(-3.0f, -2.4f);
        pathBuilderR.lineToRelative(0.0f, -3.6f);
        pathBuilderR.lineToRelative(2.44f, 1.83f);
        pathBuilderR.lineToRelative(4.96f, -6.95f);
        ImageVector.Builder.m4024addPathoIyEayM$default(builder, b.t(pathBuilderR, 3.9f, 3.12f, 2.7f, 0.0f), defaultFillType, "", solidColor, 0.3f, null, 0.3f, 1.0f, iM3803getButtKaPHkGw, iM3813getBevelLxFBmk8, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null);
        int defaultFillType2 = VectorKt.getDefaultFillType();
        SolidColor solidColor2 = new SolidColor(companion.m3498getBlack0d7_KjU(), null);
        int iM3803getButtKaPHkGw2 = companion2.m3803getButtKaPHkGw();
        int iM3813getBevelLxFBmk82 = companion3.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderR2 = a.r(17.0f, 7.0f, -5.0f, -4.0f);
        pathBuilderR2.lineToRelative(-5.0f, 7.0f);
        a.h(pathBuilderR2, 3.0f, 7.0f, 13.0f, 18.0f);
        c.n(pathBuilderR2, 7.0f, 17.0f, 19.0f, 16.95f);
        pathBuilderR2.lineToRelative(-7.0f, -5.45f);
        pathBuilderR2.lineTo(8.0f, 17.0f);
        pathBuilderR2.lineToRelative(-3.0f, -2.4f);
        pathBuilderR2.verticalLineTo(11.0f);
        pathBuilderR2.lineToRelative(2.44f, 1.83f);
        pathBuilderR2.lineToRelative(4.96f, -6.95f);
        ImageVector imageVectorBuild = ImageVector.Builder.m4024addPathoIyEayM$default(builder, d.e(pathBuilderR2, 16.3f, 9.0f, 19.0f, 16.95f), defaultFillType2, "", solidColor2, 1.0f, null, 1.0f, 1.0f, iM3803getButtKaPHkGw2, iM3813getBevelLxFBmk82, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null).build();
        _areaChart = imageVectorBuild;
        return imageVectorBuild;
    }
}
