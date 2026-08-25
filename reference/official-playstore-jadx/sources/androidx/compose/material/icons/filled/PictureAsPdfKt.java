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
import v.c;
import v.d;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\"\u0010\u0010\u0000\u001a\u0004\u0018\u00010\u0001X\u0082\u000e¢\u0006\u0002\n\u0000\"\u0015\u0010\u0002\u001a\u00020\u0001*\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"_pictureAsPdf", "Landroidx/compose/ui/graphics/vector/ImageVector;", "PictureAsPdf", "Landroidx/compose/material/icons/Icons$Filled;", "getPictureAsPdf", "(Landroidx/compose/material/icons/Icons$Filled;)Landroidx/compose/ui/graphics/vector/ImageVector;", "material-icons-extended_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class PictureAsPdfKt {
    private static ImageVector _pictureAsPdf;

    public static final ImageVector getPictureAsPdf(Icons.Filled filled) {
        ImageVector imageVector = _pictureAsPdf;
        if (imageVector != null) {
            return imageVector;
        }
        ImageVector.Builder builder = new ImageVector.Builder("Filled.PictureAsPdf", Dp.m5678constructorimpl(24.0f), Dp.m5678constructorimpl(24.0f), 24.0f, 24.0f, 0L, 0, false, 96, null);
        int defaultFillType = VectorKt.getDefaultFillType();
        SolidColor solidColor = new SolidColor(Color.INSTANCE.m3498getBlack0d7_KjU(), null);
        int iM3803getButtKaPHkGw = StrokeCap.INSTANCE.m3803getButtKaPHkGw();
        int iM3813getBevelLxFBmk8 = StrokeJoin.INSTANCE.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderJ = a.j(20.0f, 2.0f, 8.0f, 2.0f);
        pathBuilderJ.curveToRelative(-1.1f, 0.0f, -2.0f, 0.9f, -2.0f, 2.0f);
        pathBuilderJ.verticalLineToRelative(12.0f);
        pathBuilderJ.curveToRelative(0.0f, 1.1f, 0.9f, 2.0f, 2.0f, 2.0f);
        pathBuilderJ.horizontalLineToRelative(12.0f);
        pathBuilderJ.curveToRelative(1.1f, 0.0f, 2.0f, -0.9f, 2.0f, -2.0f);
        pathBuilderJ.lineTo(22.0f, 4.0f);
        pathBuilderJ.curveToRelative(0.0f, -1.1f, -0.9f, -2.0f, -2.0f, -2.0f);
        pathBuilderJ.close();
        pathBuilderJ.moveTo(11.5f, 9.5f);
        pathBuilderJ.curveToRelative(0.0f, 0.83f, -0.67f, 1.5f, -1.5f, 1.5f);
        pathBuilderJ.lineTo(9.0f, 11.0f);
        pathBuilderJ.verticalLineToRelative(2.0f);
        pathBuilderJ.lineTo(7.5f, 13.0f);
        pathBuilderJ.lineTo(7.5f, 7.0f);
        pathBuilderJ.lineTo(10.0f, 7.0f);
        pathBuilderJ.curveToRelative(0.83f, 0.0f, 1.5f, 0.67f, 1.5f, 1.5f);
        b.f(pathBuilderJ, 1.0f, 16.5f, 11.5f);
        pathBuilderJ.curveToRelative(0.0f, 0.83f, -0.67f, 1.5f, -1.5f, 1.5f);
        pathBuilderJ.horizontalLineToRelative(-2.5f);
        pathBuilderJ.lineTo(12.5f, 7.0f);
        pathBuilderJ.lineTo(15.0f, 7.0f);
        pathBuilderJ.curveToRelative(0.83f, 0.0f, 1.5f, 0.67f, 1.5f, 1.5f);
        b.f(pathBuilderJ, 3.0f, 20.5f, 8.5f);
        a.h(pathBuilderJ, 19.0f, 8.5f, 1.0f, 1.5f);
        pathBuilderJ.lineTo(20.5f, 11.0f);
        a.h(pathBuilderJ, 19.0f, 11.0f, 2.0f, -1.5f);
        b.g(pathBuilderJ, 17.5f, 7.0f, 3.0f, 1.5f);
        a.C(pathBuilderJ, 9.0f, 9.5f, 1.0f, -1.0f);
        d.f(pathBuilderJ, 9.0f, 8.5f, 1.0f);
        pathBuilderJ.moveTo(4.0f, 6.0f);
        pathBuilderJ.lineTo(2.0f, 6.0f);
        pathBuilderJ.verticalLineToRelative(14.0f);
        pathBuilderJ.curveToRelative(0.0f, 1.1f, 0.9f, 2.0f, 2.0f, 2.0f);
        c.q(pathBuilderJ, 14.0f, -2.0f, 4.0f, 20.0f);
        c.z(pathBuilderJ, 4.0f, 6.0f, 14.0f, 11.5f);
        ImageVector imageVectorBuild = ImageVector.Builder.m4024addPathoIyEayM$default(builder, a0.a.h(pathBuilderJ, 1.0f, -3.0f, -1.0f, 3.0f), defaultFillType, "", solidColor, 1.0f, null, 1.0f, 1.0f, iM3803getButtKaPHkGw, iM3813getBevelLxFBmk8, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null).build();
        _pictureAsPdf = imageVectorBuild;
        return imageVectorBuild;
    }
}
