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
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\"\u0010\u0010\u0000\u001a\u0004\u0018\u00010\u0001X\u0082\u000e¢\u0006\u0002\n\u0000\"\u0015\u0010\u0002\u001a\u00020\u0001*\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"_pieChartOutline", "Landroidx/compose/ui/graphics/vector/ImageVector;", "PieChartOutline", "Landroidx/compose/material/icons/Icons$TwoTone;", "getPieChartOutline", "(Landroidx/compose/material/icons/Icons$TwoTone;)Landroidx/compose/ui/graphics/vector/ImageVector;", "material-icons-extended_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class PieChartOutlineKt {
    private static ImageVector _pieChartOutline;

    public static final ImageVector getPieChartOutline(Icons.TwoTone twoTone) {
        ImageVector imageVector = _pieChartOutline;
        if (imageVector != null) {
            return imageVector;
        }
        ImageVector.Builder builder = new ImageVector.Builder("TwoTone.PieChartOutline", Dp.m5678constructorimpl(24.0f), Dp.m5678constructorimpl(24.0f), 24.0f, 24.0f, 0L, 0, false, 96, null);
        int defaultFillType = VectorKt.getDefaultFillType();
        SolidColor solidColor = new SolidColor(Color.INSTANCE.m3498getBlack0d7_KjU(), null);
        int iM3803getButtKaPHkGw = StrokeCap.INSTANCE.m3803getButtKaPHkGw();
        int iM3813getBevelLxFBmk8 = StrokeJoin.INSTANCE.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderA = c.a(12.0f, 2.0f);
        pathBuilderA.curveTo(6.5f, 2.0f, 2.0f, 6.5f, 2.0f, 12.0f);
        pathBuilderA.reflectiveCurveToRelative(4.5f, 10.0f, 10.0f, 10.0f);
        pathBuilderA.reflectiveCurveToRelative(10.0f, -4.5f, 10.0f, -10.0f);
        pathBuilderA.reflectiveCurveTo(17.5f, 2.0f, 12.0f, 2.0f);
        pathBuilderA.close();
        pathBuilderA.moveTo(11.0f, 19.94f);
        pathBuilderA.curveToRelative(-3.93f, -0.5f, -7.0f, -3.88f, -7.0f, -7.94f);
        pathBuilderA.reflectiveCurveToRelative(3.07f, -7.44f, 7.0f, -7.93f);
        b.f(pathBuilderA, 15.87f, 13.0f, 19.93f);
        pathBuilderA.lineTo(13.0f, 13.0f);
        pathBuilderA.horizontalLineToRelative(6.93f);
        pathBuilderA.curveToRelative(-0.45f, 3.61f, -3.32f, 6.48f, -6.93f, 6.93f);
        a.p(pathBuilderA, 13.0f, 11.0f, 13.0f, 4.07f);
        pathBuilderA.curveToRelative(3.61f, 0.45f, 6.48f, 3.33f, 6.93f, 6.93f);
        ImageVector imageVectorBuild = ImageVector.Builder.m4024addPathoIyEayM$default(builder, d.o(pathBuilderA, 13.0f, 11.0f), defaultFillType, "", solidColor, 1.0f, null, 1.0f, 1.0f, iM3803getButtKaPHkGw, iM3813getBevelLxFBmk8, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null).build();
        _pieChartOutline = imageVectorBuild;
        return imageVectorBuild;
    }
}
