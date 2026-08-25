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
import v.a;
import v.b;
import v.c;
import v.e;
import v.f;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\"\u0010\u0010\u0000\u001a\u0004\u0018\u00010\u0001X\u0082\u000e¢\u0006\u0002\n\u0000\"\u0015\u0010\u0002\u001a\u00020\u0001*\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"_formatShapes", "Landroidx/compose/ui/graphics/vector/ImageVector;", "FormatShapes", "Landroidx/compose/material/icons/Icons$Rounded;", "getFormatShapes", "(Landroidx/compose/material/icons/Icons$Rounded;)Landroidx/compose/ui/graphics/vector/ImageVector;", "material-icons-extended_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class FormatShapesKt {
    private static ImageVector _formatShapes;

    public static final ImageVector getFormatShapes(Icons.Rounded rounded) {
        ImageVector imageVector = _formatShapes;
        if (imageVector != null) {
            return imageVector;
        }
        ImageVector.Builder builder = new ImageVector.Builder("Rounded.FormatShapes", Dp.m5678constructorimpl(24.0f), Dp.m5678constructorimpl(24.0f), 24.0f, 24.0f, 0L, 0, false, 96, null);
        int defaultFillType = VectorKt.getDefaultFillType();
        SolidColor solidColor = new SolidColor(Color.INSTANCE.m3498getBlack0d7_KjU(), null);
        int iM3803getButtKaPHkGw = StrokeCap.INSTANCE.m3803getButtKaPHkGw();
        int iM3813getBevelLxFBmk8 = StrokeJoin.INSTANCE.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderJ = a.j(23.0f, 6.0f, 23.0f, 2.0f);
        pathBuilderJ.curveToRelative(0.0f, -0.55f, -0.45f, -1.0f, -1.0f, -1.0f);
        pathBuilderJ.horizontalLineToRelative(-4.0f);
        pathBuilderJ.curveToRelative(-0.55f, 0.0f, -1.0f, 0.45f, -1.0f, 1.0f);
        pathBuilderJ.verticalLineToRelative(1.0f);
        pathBuilderJ.lineTo(7.0f, 3.0f);
        pathBuilderJ.lineTo(7.0f, 2.0f);
        pathBuilderJ.curveToRelative(0.0f, -0.55f, -0.45f, -1.0f, -1.0f, -1.0f);
        pathBuilderJ.lineTo(2.0f, 1.0f);
        pathBuilderJ.curveToRelative(-0.55f, 0.0f, -1.0f, 0.45f, -1.0f, 1.0f);
        pathBuilderJ.verticalLineToRelative(4.0f);
        pathBuilderJ.curveToRelative(0.0f, 0.55f, 0.45f, 1.0f, 1.0f, 1.0f);
        c.q(pathBuilderJ, 1.0f, 10.0f, 2.0f, 17.0f);
        pathBuilderJ.curveToRelative(-0.55f, 0.0f, -1.0f, 0.45f, -1.0f, 1.0f);
        pathBuilderJ.verticalLineToRelative(4.0f);
        pathBuilderJ.curveToRelative(0.0f, 0.55f, 0.45f, 1.0f, 1.0f, 1.0f);
        pathBuilderJ.horizontalLineToRelative(4.0f);
        pathBuilderJ.curveToRelative(0.55f, 0.0f, 1.0f, -0.45f, 1.0f, -1.0f);
        e.l(pathBuilderJ, -1.0f, 10.0f, 1.0f);
        pathBuilderJ.curveToRelative(0.0f, 0.55f, 0.45f, 1.0f, 1.0f, 1.0f);
        pathBuilderJ.horizontalLineToRelative(4.0f);
        pathBuilderJ.curveToRelative(0.55f, 0.0f, 1.0f, -0.45f, 1.0f, -1.0f);
        pathBuilderJ.verticalLineToRelative(-4.0f);
        pathBuilderJ.curveToRelative(0.0f, -0.55f, -0.45f, -1.0f, -1.0f, -1.0f);
        c.A(pathBuilderJ, -1.0f, 21.0f, 7.0f, 1.0f);
        pathBuilderJ.curveToRelative(0.55f, 0.0f, 1.0f, -0.45f, 1.0f, -1.0f);
        b.w(pathBuilderJ, 3.0f, 3.0f, 2.0f, 2.0f);
        b.y(pathBuilderJ, 3.0f, 5.0f, 3.0f, 3.0f);
        pathBuilderJ.moveTo(5.0f, 21.0f);
        a.h(pathBuilderJ, 3.0f, 21.0f, -2.0f, 2.0f);
        b.f(pathBuilderJ, 2.0f, 17.0f, 19.0f);
        pathBuilderJ.lineTo(7.0f, 19.0f);
        pathBuilderJ.verticalLineToRelative(-1.0f);
        pathBuilderJ.curveToRelative(0.0f, -0.55f, -0.45f, -1.0f, -1.0f, -1.0f);
        pathBuilderJ.lineTo(5.0f, 17.0f);
        pathBuilderJ.lineTo(5.0f, 7.0f);
        pathBuilderJ.horizontalLineToRelative(1.0f);
        pathBuilderJ.curveToRelative(0.55f, 0.0f, 1.0f, -0.45f, 1.0f, -1.0f);
        b.A(pathBuilderJ, 7.0f, 5.0f, 10.0f, 1.0f);
        pathBuilderJ.curveToRelative(0.0f, 0.55f, 0.45f, 1.0f, 1.0f, 1.0f);
        e.g(pathBuilderJ, 1.0f, 10.0f, -1.0f);
        pathBuilderJ.curveToRelative(-0.55f, 0.0f, -1.0f, 0.45f, -1.0f, 1.0f);
        c.r(pathBuilderJ, 1.0f, 21.0f, 21.0f, -2.0f);
        b.l(pathBuilderJ, -2.0f, 2.0f, 2.0f);
        pathBuilderJ.moveTo(19.0f, 5.0f);
        b.A(pathBuilderJ, 19.0f, 3.0f, 2.0f, 2.0f);
        f.x(pathBuilderJ, -2.0f, 12.94f, 7.65f);
        pathBuilderJ.curveToRelative(-0.15f, -0.39f, -0.53f, -0.65f, -0.95f, -0.65f);
        pathBuilderJ.curveToRelative(-0.42f, 0.0f, -0.8f, 0.26f, -0.94f, 0.65f);
        pathBuilderJ.lineToRelative(-2.77f, 7.33f);
        pathBuilderJ.curveToRelative(-0.19f, 0.49f, 0.17f, 1.02f, 0.7f, 1.02f);
        pathBuilderJ.curveToRelative(0.32f, 0.0f, 0.6f, -0.2f, 0.71f, -0.5f);
        pathBuilderJ.lineToRelative(0.55f, -1.5f);
        pathBuilderJ.horizontalLineToRelative(3.49f);
        pathBuilderJ.lineToRelative(0.56f, 1.51f);
        pathBuilderJ.curveToRelative(0.11f, 0.29f, 0.39f, 0.49f, 0.71f, 0.49f);
        pathBuilderJ.horizontalLineToRelative(0.01f);
        pathBuilderJ.curveToRelative(0.53f, 0.0f, 0.89f, -0.53f, 0.71f, -1.02f);
        b.C(pathBuilderJ, -2.78f, -7.33f, 10.69f, 12.74f);
        pathBuilderJ.lineTo(12.0f, 8.91f);
        ImageVector imageVectorBuild = ImageVector.Builder.m4024addPathoIyEayM$default(builder, a0.a.g(pathBuilderJ, 1.3f, 3.83f, -2.61f), defaultFillType, "", solidColor, 1.0f, null, 1.0f, 1.0f, iM3803getButtKaPHkGw, iM3813getBevelLxFBmk8, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null).build();
        _formatShapes = imageVectorBuild;
        return imageVectorBuild;
    }
}
