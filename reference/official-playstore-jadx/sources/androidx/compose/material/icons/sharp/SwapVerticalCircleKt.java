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
import v.b;
import v.c;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\"\u0010\u0010\u0000\u001a\u0004\u0018\u00010\u0001X\u0082\u000e¢\u0006\u0002\n\u0000\"\u0015\u0010\u0002\u001a\u00020\u0001*\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"_swapVerticalCircle", "Landroidx/compose/ui/graphics/vector/ImageVector;", "SwapVerticalCircle", "Landroidx/compose/material/icons/Icons$Sharp;", "getSwapVerticalCircle", "(Landroidx/compose/material/icons/Icons$Sharp;)Landroidx/compose/ui/graphics/vector/ImageVector;", "material-icons-extended_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class SwapVerticalCircleKt {
    private static ImageVector _swapVerticalCircle;

    public static final ImageVector getSwapVerticalCircle(Icons.Sharp sharp) {
        ImageVector imageVector = _swapVerticalCircle;
        if (imageVector != null) {
            return imageVector;
        }
        ImageVector.Builder builder = new ImageVector.Builder("Sharp.SwapVerticalCircle", Dp.m5678constructorimpl(24.0f), Dp.m5678constructorimpl(24.0f), 24.0f, 24.0f, 0L, 0, false, 96, null);
        int defaultFillType = VectorKt.getDefaultFillType();
        SolidColor solidColor = new SolidColor(Color.INSTANCE.m3498getBlack0d7_KjU(), null);
        int iM3803getButtKaPHkGw = StrokeCap.INSTANCE.m3803getButtKaPHkGw();
        int iM3813getBevelLxFBmk8 = StrokeJoin.INSTANCE.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderA = c.a(12.0f, 2.0f);
        pathBuilderA.curveTo(6.48f, 2.0f, 2.0f, 6.48f, 2.0f, 12.0f);
        pathBuilderA.reflectiveCurveToRelative(4.48f, 10.0f, 10.0f, 10.0f);
        pathBuilderA.reflectiveCurveToRelative(10.0f, -4.48f, 10.0f, -10.0f);
        pathBuilderA.reflectiveCurveTo(17.52f, 2.0f, 12.0f, 2.0f);
        a.p(pathBuilderA, 6.5f, 9.0f, 10.0f, 5.5f);
        pathBuilderA.lineTo(13.5f, 9.0f);
        pathBuilderA.lineTo(11.0f, 9.0f);
        pathBuilderA.verticalLineToRelative(4.0f);
        pathBuilderA.lineTo(9.0f, 13.0f);
        b.y(pathBuilderA, 9.0f, 9.0f, 6.5f, 9.0f);
        pathBuilderA.moveTo(14.0f, 18.5f);
        pathBuilderA.lineTo(10.5f, 15.0f);
        a.h(pathBuilderA, 13.0f, 15.0f, -4.0f, 2.0f);
        ImageVector imageVectorBuild = ImageVector.Builder.m4024addPathoIyEayM$default(builder, a0.a.c(pathBuilderA, 4.0f, 2.5f, 14.0f, 18.5f), defaultFillType, "", solidColor, 1.0f, null, 1.0f, 1.0f, iM3803getButtKaPHkGw, iM3813getBevelLxFBmk8, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null).build();
        _swapVerticalCircle = imageVectorBuild;
        return imageVectorBuild;
    }
}
