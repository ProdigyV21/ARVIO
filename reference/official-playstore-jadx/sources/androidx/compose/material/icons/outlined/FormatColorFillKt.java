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
import v.b;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\"\u0010\u0010\u0000\u001a\u0004\u0018\u00010\u0001X\u0082\u000e¢\u0006\u0002\n\u0000\"\u0015\u0010\u0002\u001a\u00020\u0001*\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"_formatColorFill", "Landroidx/compose/ui/graphics/vector/ImageVector;", "FormatColorFill", "Landroidx/compose/material/icons/Icons$Outlined;", "getFormatColorFill", "(Landroidx/compose/material/icons/Icons$Outlined;)Landroidx/compose/ui/graphics/vector/ImageVector;", "material-icons-extended_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class FormatColorFillKt {
    private static ImageVector _formatColorFill;

    public static final ImageVector getFormatColorFill(Icons.Outlined outlined) {
        ImageVector imageVector = _formatColorFill;
        if (imageVector != null) {
            return imageVector;
        }
        ImageVector.Builder builder = new ImageVector.Builder("Outlined.FormatColorFill", Dp.m5678constructorimpl(24.0f), Dp.m5678constructorimpl(24.0f), 24.0f, 24.0f, 0L, 0, false, 96, null);
        int defaultFillType = VectorKt.getDefaultFillType();
        SolidColor solidColor = new SolidColor(Color.INSTANCE.m3498getBlack0d7_KjU(), null);
        int iM3803getButtKaPHkGw = StrokeCap.INSTANCE.m3803getButtKaPHkGw();
        int iM3813getBevelLxFBmk8 = StrokeJoin.INSTANCE.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderJ = a.j(16.56f, 8.94f, 7.62f, 0.0f);
        pathBuilderJ.lineTo(6.21f, 1.41f);
        pathBuilderJ.lineToRelative(2.38f, 2.38f);
        pathBuilderJ.lineTo(3.44f, 8.94f);
        pathBuilderJ.curveToRelative(-0.59f, 0.59f, -0.59f, 1.54f, 0.0f, 2.12f);
        pathBuilderJ.lineToRelative(5.5f, 5.5f);
        pathBuilderJ.curveTo(9.23f, 16.85f, 9.62f, 17.0f, 10.0f, 17.0f);
        pathBuilderJ.reflectiveCurveToRelative(0.77f, -0.15f, 1.06f, -0.44f);
        pathBuilderJ.lineToRelative(5.5f, -5.5f);
        pathBuilderJ.curveTo(17.15f, 10.48f, 17.15f, 9.53f, 16.56f, 8.94f);
        a.p(pathBuilderJ, 5.21f, 10.0f, 10.0f, 5.21f);
        w.a.e(pathBuilderJ, 14.79f, 10.0f, 5.21f);
        pathBuilderJ.moveTo(19.0f, 11.5f);
        pathBuilderJ.curveToRelative(0.0f, 0.0f, -2.0f, 2.17f, -2.0f, 3.5f);
        pathBuilderJ.curveToRelative(0.0f, 1.1f, 0.9f, 2.0f, 2.0f, 2.0f);
        pathBuilderJ.reflectiveCurveToRelative(2.0f, -0.9f, 2.0f, -2.0f);
        pathBuilderJ.curveTo(21.0f, 13.67f, 19.0f, 11.5f, 19.0f, 11.5f);
        b.w(pathBuilderJ, 2.0f, 20.0f, 20.0f, 4.0f);
        ImageVector imageVectorBuild = ImageVector.Builder.m4024addPathoIyEayM$default(builder, a.m(pathBuilderJ, 2.0f, 20.0f), defaultFillType, "", solidColor, 1.0f, null, 1.0f, 1.0f, iM3803getButtKaPHkGw, iM3813getBevelLxFBmk8, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null).build();
        _formatColorFill = imageVectorBuild;
        return imageVectorBuild;
    }
}
