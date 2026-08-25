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
import v.e;
import w.a;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\"\u0010\u0010\u0000\u001a\u0004\u0018\u00010\u0001X\u0082\u000e¢\u0006\u0002\n\u0000\"\u0015\u0010\u0002\u001a\u00020\u0001*\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"_pivotTableChart", "Landroidx/compose/ui/graphics/vector/ImageVector;", "PivotTableChart", "Landroidx/compose/material/icons/Icons$Rounded;", "getPivotTableChart", "(Landroidx/compose/material/icons/Icons$Rounded;)Landroidx/compose/ui/graphics/vector/ImageVector;", "material-icons-extended_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class PivotTableChartKt {
    private static ImageVector _pivotTableChart;

    public static final ImageVector getPivotTableChart(Icons.Rounded rounded) {
        ImageVector imageVector = _pivotTableChart;
        if (imageVector != null) {
            return imageVector;
        }
        ImageVector.Builder builder = new ImageVector.Builder("Rounded.PivotTableChart", Dp.m5678constructorimpl(24.0f), Dp.m5678constructorimpl(24.0f), 24.0f, 24.0f, 0L, 0, false, 96, null);
        int defaultFillType = VectorKt.getDefaultFillType();
        Color.Companion companion = Color.INSTANCE;
        SolidColor solidColor = new SolidColor(companion.m3498getBlack0d7_KjU(), null);
        StrokeCap.Companion companion2 = StrokeCap.INSTANCE;
        int iM3803getButtKaPHkGw = companion2.m3803getButtKaPHkGw();
        StrokeJoin.Companion companion3 = StrokeJoin.INSTANCE;
        int iM3813getBevelLxFBmk8 = companion3.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderA = c.a(21.0f, 5.0f);
        pathBuilderA.curveToRelative(0.0f, -1.1f, -0.9f, -2.0f, -2.0f, -2.0f);
        ImageVector.Builder.m4024addPathoIyEayM$default(builder, e.f(pathBuilderA, -9.0f, 5.0f, 11.0f, 5.0f), defaultFillType, "", solidColor, 1.0f, null, 1.0f, 1.0f, iM3803getButtKaPHkGw, iM3813getBevelLxFBmk8, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null);
        int defaultFillType2 = VectorKt.getDefaultFillType();
        SolidColor solidColor2 = new SolidColor(companion.m3498getBlack0d7_KjU(), null);
        int iM3803getButtKaPHkGw2 = companion2.m3803getButtKaPHkGw();
        int iM3813getBevelLxFBmk82 = companion3.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderA2 = c.a(3.0f, 19.0f);
        pathBuilderA2.curveToRelative(0.0f, 1.1f, 0.9f, 2.0f, 2.0f, 2.0f);
        ImageVector.Builder.m4024addPathoIyEayM$default(builder, a.d(pathBuilderA2, 3.0f, 10.0f, 3.0f, 19.0f), defaultFillType2, "", solidColor2, 1.0f, null, 1.0f, 1.0f, iM3803getButtKaPHkGw2, iM3813getBevelLxFBmk82, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null);
        int defaultFillType3 = VectorKt.getDefaultFillType();
        SolidColor solidColor3 = new SolidColor(companion.m3498getBlack0d7_KjU(), null);
        int iM3803getButtKaPHkGw3 = companion2.m3803getButtKaPHkGw();
        int iM3813getBevelLxFBmk83 = companion3.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderQ = a.q(3.0f, 5.0f, 3.0f, 5.0f, 3.0f);
        pathBuilderQ.horizontalLineTo(5.0f);
        pathBuilderQ.curveTo(3.9f, 3.0f, 3.0f, 3.9f, 3.0f, 5.0f);
        pathBuilderQ.close();
        ImageVector.Builder.m4024addPathoIyEayM$default(builder, pathBuilderQ.getNodes(), defaultFillType3, "", solidColor3, 1.0f, null, 1.0f, 1.0f, iM3803getButtKaPHkGw3, iM3813getBevelLxFBmk83, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null);
        int defaultFillType4 = VectorKt.getDefaultFillType();
        SolidColor solidColor4 = new SolidColor(companion.m3498getBlack0d7_KjU(), null);
        int iM3803getButtKaPHkGw4 = companion2.m3803getButtKaPHkGw();
        int iM3813getBevelLxFBmk84 = companion3.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderR = v.a.r(17.65f, 9.35f, -2.79f, 2.79f);
        pathBuilderR.curveTo(14.54f, 12.46f, 14.76f, 13.0f, 15.21f, 13.0f);
        pathBuilderR.horizontalLineTo(17.0f);
        pathBuilderR.verticalLineToRelative(2.0f);
        pathBuilderR.curveToRelative(0.0f, 1.1f, -0.9f, 2.0f, -2.0f, 2.0f);
        pathBuilderR.horizontalLineToRelative(-2.0f);
        pathBuilderR.verticalLineToRelative(-1.79f);
        pathBuilderR.curveToRelative(0.0f, -0.45f, -0.54f, -0.67f, -0.85f, -0.35f);
        pathBuilderR.lineToRelative(-2.79f, 2.79f);
        pathBuilderR.curveToRelative(-0.2f, 0.2f, -0.2f, 0.51f, 0.0f, 0.71f);
        pathBuilderR.lineToRelative(2.79f, 2.79f);
        pathBuilderR.curveToRelative(0.31f, 0.31f, 0.85f, 0.09f, 0.85f, -0.35f);
        pathBuilderR.verticalLineTo(19.0f);
        pathBuilderR.horizontalLineToRelative(2.0f);
        pathBuilderR.curveToRelative(2.21f, 0.0f, 4.0f, -1.79f, 4.0f, -4.0f);
        pathBuilderR.verticalLineToRelative(-2.0f);
        pathBuilderR.horizontalLineToRelative(1.79f);
        pathBuilderR.curveToRelative(0.45f, 0.0f, 0.67f, -0.54f, 0.35f, -0.85f);
        pathBuilderR.lineToRelative(-2.79f, -2.79f);
        pathBuilderR.curveTo(18.16f, 9.16f, 17.84f, 9.16f, 17.65f, 9.35f);
        pathBuilderR.close();
        ImageVector imageVectorBuild = ImageVector.Builder.m4024addPathoIyEayM$default(builder, pathBuilderR.getNodes(), defaultFillType4, "", solidColor4, 1.0f, null, 1.0f, 1.0f, iM3803getButtKaPHkGw4, iM3813getBevelLxFBmk84, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null).build();
        _pivotTableChart = imageVectorBuild;
        return imageVectorBuild;
    }
}
