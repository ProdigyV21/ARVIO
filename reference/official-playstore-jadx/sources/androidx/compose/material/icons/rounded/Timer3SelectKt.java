package androidx.compose.material.icons.rounded;

import a0.b;
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
import v.e;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\"\u0010\u0010\u0000\u001a\u0004\u0018\u00010\u0001X\u0082\u000e¢\u0006\u0002\n\u0000\"\u0015\u0010\u0002\u001a\u00020\u0001*\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"_timer3Select", "Landroidx/compose/ui/graphics/vector/ImageVector;", "Timer3Select", "Landroidx/compose/material/icons/Icons$Rounded;", "getTimer3Select", "(Landroidx/compose/material/icons/Icons$Rounded;)Landroidx/compose/ui/graphics/vector/ImageVector;", "material-icons-extended_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class Timer3SelectKt {
    private static ImageVector _timer3Select;

    public static final ImageVector getTimer3Select(Icons.Rounded rounded) {
        ImageVector imageVector = _timer3Select;
        if (imageVector != null) {
            return imageVector;
        }
        ImageVector.Builder builder = new ImageVector.Builder("Rounded.Timer3Select", Dp.m5678constructorimpl(24.0f), Dp.m5678constructorimpl(24.0f), 24.0f, 24.0f, 0L, 0, false, 96, null);
        int defaultFillType = VectorKt.getDefaultFillType();
        SolidColor solidColor = new SolidColor(Color.INSTANCE.m3498getBlack0d7_KjU(), null);
        int iM3803getButtKaPHkGw = StrokeCap.INSTANCE.m3803getButtKaPHkGw();
        int iM3813getBevelLxFBmk8 = StrokeJoin.INSTANCE.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderJ = a.j(21.0f, 12.0f, 21.0f, 12.0f);
        pathBuilderJ.curveToRelative(0.0f, 0.55f, -0.45f, 1.0f, -1.0f, 1.0f);
        e.g(pathBuilderJ, -3.0f, 1.0f, 2.5f);
        pathBuilderJ.curveToRelative(0.83f, 0.0f, 1.5f, 0.68f, 1.5f, 1.5f);
        pathBuilderJ.verticalLineToRelative(2.0f);
        pathBuilderJ.curveToRelative(0.0f, 0.83f, -0.67f, 1.5f, -1.5f, 1.5f);
        pathBuilderJ.horizontalLineTo(16.0f);
        pathBuilderJ.curveToRelative(-0.55f, 0.0f, -1.0f, -0.45f, -1.0f, -1.0f);
        pathBuilderJ.verticalLineToRelative(0.0f);
        pathBuilderJ.curveToRelative(0.0f, -0.55f, 0.45f, -1.0f, 1.0f, -1.0f);
        e.g(pathBuilderJ, 3.0f, -1.0f, -2.5f);
        pathBuilderJ.curveToRelative(-0.82f, 0.0f, -1.5f, -0.68f, -1.5f, -1.5f);
        pathBuilderJ.verticalLineToRelative(-2.0f);
        pathBuilderJ.curveToRelative(0.0f, -0.82f, 0.68f, -1.5f, 1.5f, -1.5f);
        pathBuilderJ.horizontalLineTo(20.0f);
        pathBuilderJ.curveTo(20.55f, 11.0f, 21.0f, 11.45f, 21.0f, 12.0f);
        a.p(pathBuilderJ, 4.0f, 6.5f, 4.0f, 6.5f);
        pathBuilderJ.curveTo(4.0f, 7.33f, 4.67f, 8.0f, 5.5f, 8.0f);
        a0.a.w(pathBuilderJ, 10.0f, 2.5f, 5.5f);
        pathBuilderJ.curveTo(4.67f, 10.5f, 4.0f, 11.17f, 4.0f, 12.0f);
        pathBuilderJ.verticalLineToRelative(0.0f);
        pathBuilderJ.curveToRelative(0.0f, 0.83f, 0.67f, 1.5f, 1.5f, 1.5f);
        b.i(pathBuilderJ, 10.0f, 16.0f, 5.5f);
        pathBuilderJ.curveTo(4.67f, 16.0f, 4.0f, 16.67f, 4.0f, 17.5f);
        pathBuilderJ.verticalLineToRelative(0.0f);
        pathBuilderJ.curveTo(4.0f, 18.33f, 4.67f, 19.0f, 5.5f, 19.0f);
        pathBuilderJ.horizontalLineTo(10.0f);
        pathBuilderJ.curveToRelative(1.66f, 0.0f, 3.0f, -1.34f, 3.0f, -3.0f);
        pathBuilderJ.verticalLineToRelative(-1.9f);
        pathBuilderJ.curveToRelative(0.0f, -1.16f, -0.94f, -2.1f, -2.1f, -2.1f);
        pathBuilderJ.curveToRelative(1.16f, 0.0f, 2.1f, -0.94f, 2.1f, -2.1f);
        pathBuilderJ.verticalLineTo(8.0f);
        pathBuilderJ.curveToRelative(0.0f, -1.66f, -1.34f, -3.0f, -3.0f, -3.0f);
        pathBuilderJ.horizontalLineTo(5.5f);
        pathBuilderJ.curveTo(4.67f, 5.0f, 4.0f, 5.67f, 4.0f, 6.5f);
        pathBuilderJ.close();
        ImageVector imageVectorBuild = ImageVector.Builder.m4024addPathoIyEayM$default(builder, pathBuilderJ.getNodes(), defaultFillType, "", solidColor, 1.0f, null, 1.0f, 1.0f, iM3803getButtKaPHkGw, iM3813getBevelLxFBmk8, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null).build();
        _timer3Select = imageVectorBuild;
        return imageVectorBuild;
    }
}
