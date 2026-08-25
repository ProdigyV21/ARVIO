package androidx.compose.material.icons.sharp;

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
import x6.e;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\"\u0010\u0010\u0000\u001a\u0004\u0018\u00010\u0001X\u0082\u000e¢\u0006\u0002\n\u0000\"\u001e\u0010\u0002\u001a\u00020\u0001*\u00020\u00038FX\u0087\u0004¢\u0006\f\u0012\u0004\b\u0004\u0010\u0005\u001a\u0004\b\u0006\u0010\u0007¨\u0006\b"}, d2 = {"_multilineChart", "Landroidx/compose/ui/graphics/vector/ImageVector;", "MultilineChart", "Landroidx/compose/material/icons/Icons$Sharp;", "getMultilineChart$annotations", "(Landroidx/compose/material/icons/Icons$Sharp;)V", "getMultilineChart", "(Landroidx/compose/material/icons/Icons$Sharp;)Landroidx/compose/ui/graphics/vector/ImageVector;", "material-icons-extended_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class MultilineChartKt {
    private static ImageVector _multilineChart;

    public static final ImageVector getMultilineChart(Icons.Sharp sharp) {
        ImageVector imageVector = _multilineChart;
        if (imageVector != null) {
            return imageVector;
        }
        ImageVector.Builder builder = new ImageVector.Builder("Sharp.MultilineChart", Dp.m5678constructorimpl(24.0f), Dp.m5678constructorimpl(24.0f), 24.0f, 24.0f, 0L, 0, false, 96, null);
        int defaultFillType = VectorKt.getDefaultFillType();
        SolidColor solidColor = new SolidColor(Color.INSTANCE.m3498getBlack0d7_KjU(), null);
        int iM3803getButtKaPHkGw = StrokeCap.INSTANCE.m3803getButtKaPHkGw();
        int iM3813getBevelLxFBmk8 = StrokeJoin.INSTANCE.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderR = a.r(22.0f, 6.92f, -1.41f, -1.41f);
        pathBuilderR.lineToRelative(-2.85f, 3.21f);
        pathBuilderR.curveTo(15.68f, 6.4f, 12.83f, 5.0f, 9.61f, 5.0f);
        pathBuilderR.curveTo(6.72f, 5.0f, 4.07f, 6.16f, 2.0f, 8.0f);
        pathBuilderR.lineToRelative(1.42f, 1.42f);
        pathBuilderR.curveTo(5.12f, 7.93f, 7.27f, 7.0f, 9.61f, 7.0f);
        pathBuilderR.curveToRelative(2.74f, 0.0f, 5.09f, 1.26f, 6.77f, 3.24f);
        pathBuilderR.lineToRelative(-2.88f, 3.24f);
        pathBuilderR.lineToRelative(-4.0f, -4.0f);
        pathBuilderR.lineTo(2.0f, 16.99f);
        pathBuilderR.lineToRelative(1.5f, 1.5f);
        pathBuilderR.lineToRelative(6.0f, -6.01f);
        pathBuilderR.lineToRelative(4.0f, 4.0f);
        pathBuilderR.lineToRelative(4.05f, -4.55f);
        pathBuilderR.curveToRelative(0.75f, 1.35f, 1.25f, 2.9f, 1.44f, 4.55f);
        pathBuilderR.horizontalLineTo(21.0f);
        pathBuilderR.curveToRelative(-0.22f, -2.3f, -0.95f, -4.39f, -2.04f, -6.14f);
        ImageVector imageVectorBuild = ImageVector.Builder.m4024addPathoIyEayM$default(builder, d.o(pathBuilderR, 22.0f, 6.92f), defaultFillType, "", solidColor, 1.0f, null, 1.0f, 1.0f, iM3803getButtKaPHkGw, iM3813getBevelLxFBmk8, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null).build();
        _multilineChart = imageVectorBuild;
        return imageVectorBuild;
    }

    @e
    public static /* synthetic */ void getMultilineChart$annotations(Icons.Sharp sharp) {
    }
}
