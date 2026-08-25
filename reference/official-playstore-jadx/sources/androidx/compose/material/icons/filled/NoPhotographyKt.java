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
import v.d;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\"\u0010\u0010\u0000\u001a\u0004\u0018\u00010\u0001X\u0082\u000e¢\u0006\u0002\n\u0000\"\u0015\u0010\u0002\u001a\u00020\u0001*\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"_noPhotography", "Landroidx/compose/ui/graphics/vector/ImageVector;", "NoPhotography", "Landroidx/compose/material/icons/Icons$Filled;", "getNoPhotography", "(Landroidx/compose/material/icons/Icons$Filled;)Landroidx/compose/ui/graphics/vector/ImageVector;", "material-icons-extended_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class NoPhotographyKt {
    private static ImageVector _noPhotography;

    public static final ImageVector getNoPhotography(Icons.Filled filled) {
        ImageVector imageVector = _noPhotography;
        if (imageVector != null) {
            return imageVector;
        }
        ImageVector.Builder builder = new ImageVector.Builder("Filled.NoPhotography", Dp.m5678constructorimpl(24.0f), Dp.m5678constructorimpl(24.0f), 24.0f, 24.0f, 0L, 0, false, 96, null);
        int defaultFillType = VectorKt.getDefaultFillType();
        SolidColor solidColor = new SolidColor(Color.INSTANCE.m3498getBlack0d7_KjU(), null);
        int iM3803getButtKaPHkGw = StrokeCap.INSTANCE.m3803getButtKaPHkGw();
        int iM3813getBevelLxFBmk8 = StrokeJoin.INSTANCE.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderJ = a.j(10.94f, 8.12f, 7.48f, 4.66f);
        pathBuilderJ.lineTo(9.0f, 3.0f);
        a0.a.n(pathBuilderJ, 6.0f, 1.83f, 2.0f, 20.0f);
        pathBuilderJ.curveToRelative(1.1f, 0.0f, 2.0f, 0.9f, 2.0f, 2.0f);
        pathBuilderJ.verticalLineToRelative(12.0f);
        pathBuilderJ.curveToRelative(0.0f, 0.05f, -0.01f, 0.1f, -0.02f, 0.16f);
        pathBuilderJ.lineToRelative(-5.1f, -5.1f);
        pathBuilderJ.curveTo(16.96f, 13.71f, 17.0f, 13.36f, 17.0f, 13.0f);
        pathBuilderJ.curveToRelative(0.0f, -2.76f, -2.24f, -5.0f, -5.0f, -5.0f);
        pathBuilderJ.curveTo(11.64f, 8.0f, 11.29f, 8.04f, 10.94f, 8.12f);
        a.p(pathBuilderJ, 20.49f, 23.31f, 18.17f, 21.0f);
        pathBuilderJ.horizontalLineTo(4.0f);
        pathBuilderJ.curveToRelative(-1.1f, 0.0f, -2.0f, -0.9f, -2.0f, -2.0f);
        pathBuilderJ.verticalLineTo(7.0f);
        pathBuilderJ.curveToRelative(0.0f, -0.59f, 0.27f, -1.12f, 0.68f, -1.49f);
        pathBuilderJ.lineToRelative(-2.0f, -2.0f);
        pathBuilderJ.lineTo(2.1f, 2.1f);
        d.C(pathBuilderJ, 19.8f, 19.8f, 20.49f, 23.31f);
        pathBuilderJ.moveTo(14.49f, 17.32f);
        pathBuilderJ.lineToRelative(-1.5f, -1.5f);
        pathBuilderJ.curveTo(12.67f, 15.92f, 12.35f, 16.0f, 12.0f, 16.0f);
        pathBuilderJ.curveToRelative(-1.66f, 0.0f, -3.0f, -1.34f, -3.0f, -3.0f);
        pathBuilderJ.curveToRelative(0.0f, -0.35f, 0.08f, -0.67f, 0.19f, -0.98f);
        pathBuilderJ.lineToRelative(-1.5f, -1.5f);
        pathBuilderJ.curveTo(7.25f, 11.24f, 7.0f, 12.09f, 7.0f, 13.0f);
        pathBuilderJ.curveToRelative(0.0f, 2.76f, 2.24f, 5.0f, 5.0f, 5.0f);
        pathBuilderJ.curveTo(12.91f, 18.0f, 13.76f, 17.75f, 14.49f, 17.32f);
        pathBuilderJ.close();
        ImageVector imageVectorBuild = ImageVector.Builder.m4024addPathoIyEayM$default(builder, pathBuilderJ.getNodes(), defaultFillType, "", solidColor, 1.0f, null, 1.0f, 1.0f, iM3803getButtKaPHkGw, iM3813getBevelLxFBmk8, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null).build();
        _noPhotography = imageVectorBuild;
        return imageVectorBuild;
    }
}
