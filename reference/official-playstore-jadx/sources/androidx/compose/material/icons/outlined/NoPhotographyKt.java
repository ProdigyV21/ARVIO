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
import v.c;
import v.d;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\"\u0010\u0010\u0000\u001a\u0004\u0018\u00010\u0001X\u0082\u000e¢\u0006\u0002\n\u0000\"\u0015\u0010\u0002\u001a\u00020\u0001*\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"_noPhotography", "Landroidx/compose/ui/graphics/vector/ImageVector;", "NoPhotography", "Landroidx/compose/material/icons/Icons$Outlined;", "getNoPhotography", "(Landroidx/compose/material/icons/Icons$Outlined;)Landroidx/compose/ui/graphics/vector/ImageVector;", "material-icons-extended_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class NoPhotographyKt {
    private static ImageVector _noPhotography;

    public static final ImageVector getNoPhotography(Icons.Outlined outlined) {
        ImageVector imageVector = _noPhotography;
        if (imageVector != null) {
            return imageVector;
        }
        ImageVector.Builder builder = new ImageVector.Builder("Outlined.NoPhotography", Dp.m5678constructorimpl(24.0f), Dp.m5678constructorimpl(24.0f), 24.0f, 24.0f, 0L, 0, false, 96, null);
        int defaultFillType = VectorKt.getDefaultFillType();
        SolidColor solidColor = new SolidColor(Color.INSTANCE.m3498getBlack0d7_KjU(), null);
        int iM3803getButtKaPHkGw = StrokeCap.INSTANCE.m3803getButtKaPHkGw();
        int iM3813getBevelLxFBmk8 = StrokeJoin.INSTANCE.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderJ = a.j(8.9f, 6.07f, 7.48f, 4.66f);
        pathBuilderJ.lineTo(9.0f, 3.0f);
        a0.a.n(pathBuilderJ, 6.0f, 1.83f, 2.0f, 20.0f);
        pathBuilderJ.curveToRelative(1.1f, 0.0f, 2.0f, 0.9f, 2.0f, 2.0f);
        pathBuilderJ.verticalLineToRelative(12.0f);
        pathBuilderJ.curveToRelative(0.0f, 0.05f, -0.01f, 0.1f, -0.02f, 0.16f);
        b.n(pathBuilderJ, 20.0f, 17.17f, 7.0f, -4.05f);
        pathBuilderJ.lineToRelative(-1.83f, -2.0f);
        w.a.w(pathBuilderJ, 9.88f, 8.9f, 6.07f);
        pathBuilderJ.moveTo(20.49f, 23.31f);
        pathBuilderJ.lineTo(18.17f, 21.0f);
        pathBuilderJ.horizontalLineTo(4.0f);
        pathBuilderJ.curveToRelative(-1.1f, 0.0f, -2.0f, -0.9f, -2.0f, -2.0f);
        pathBuilderJ.verticalLineTo(7.0f);
        pathBuilderJ.curveToRelative(0.0f, -0.59f, 0.27f, -1.12f, 0.68f, -1.49f);
        pathBuilderJ.lineToRelative(-2.0f, -2.0f);
        pathBuilderJ.lineTo(2.1f, 2.1f);
        d.C(pathBuilderJ, 19.8f, 19.8f, 20.49f, 23.31f);
        pathBuilderJ.moveTo(9.19f, 12.02f);
        pathBuilderJ.curveTo(9.08f, 12.33f, 9.0f, 12.65f, 9.0f, 13.0f);
        pathBuilderJ.curveToRelative(0.0f, 1.66f, 1.34f, 3.0f, 3.0f, 3.0f);
        pathBuilderJ.curveToRelative(0.35f, 0.0f, 0.67f, -0.08f, 0.98f, -0.19f);
        c.z(pathBuilderJ, 9.19f, 12.02f, 16.17f, 19.0f);
        pathBuilderJ.lineToRelative(-1.68f, -1.68f);
        pathBuilderJ.curveTo(13.76f, 17.75f, 12.91f, 18.0f, 12.0f, 18.0f);
        pathBuilderJ.curveToRelative(-2.76f, 0.0f, -5.0f, -2.24f, -5.0f, -5.0f);
        pathBuilderJ.curveToRelative(0.0f, -0.91f, 0.25f, -1.76f, 0.68f, -2.49f);
        b.m(pathBuilderJ, 4.17f, 7.0f, 4.0f, 12.0f);
        a.x(pathBuilderJ, 16.17f, 14.81f, 11.98f);
        pathBuilderJ.lineToRelative(2.07f, 2.07f);
        pathBuilderJ.curveTo(16.96f, 13.71f, 17.0f, 13.36f, 17.0f, 13.0f);
        pathBuilderJ.curveToRelative(0.0f, -2.76f, -2.24f, -5.0f, -5.0f, -5.0f);
        pathBuilderJ.curveToRelative(-0.36f, 0.0f, -0.71f, 0.04f, -1.06f, 0.12f);
        pathBuilderJ.lineToRelative(2.07f, 2.07f);
        pathBuilderJ.curveTo(13.85f, 10.49f, 14.51f, 11.15f, 14.81f, 11.98f);
        pathBuilderJ.close();
        ImageVector imageVectorBuild = ImageVector.Builder.m4024addPathoIyEayM$default(builder, pathBuilderJ.getNodes(), defaultFillType, "", solidColor, 1.0f, null, 1.0f, 1.0f, iM3803getButtKaPHkGw, iM3813getBevelLxFBmk8, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null).build();
        _noPhotography = imageVectorBuild;
        return imageVectorBuild;
    }
}
