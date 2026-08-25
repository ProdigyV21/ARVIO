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
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\"\u0010\u0010\u0000\u001a\u0004\u0018\u00010\u0001X\u0082\u000e¢\u0006\u0002\n\u0000\"\u0015\u0010\u0002\u001a\u00020\u0001*\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"_noPhotography", "Landroidx/compose/ui/graphics/vector/ImageVector;", "NoPhotography", "Landroidx/compose/material/icons/Icons$TwoTone;", "getNoPhotography", "(Landroidx/compose/material/icons/Icons$TwoTone;)Landroidx/compose/ui/graphics/vector/ImageVector;", "material-icons-extended_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class NoPhotographyKt {
    private static ImageVector _noPhotography;

    public static final ImageVector getNoPhotography(Icons.TwoTone twoTone) {
        ImageVector imageVector = _noPhotography;
        if (imageVector != null) {
            return imageVector;
        }
        ImageVector.Builder builder = new ImageVector.Builder("TwoTone.NoPhotography", Dp.m5678constructorimpl(24.0f), Dp.m5678constructorimpl(24.0f), 24.0f, 24.0f, 0L, 0, false, 96, null);
        int defaultFillType = VectorKt.getDefaultFillType();
        Color.Companion companion = Color.INSTANCE;
        SolidColor solidColor = new SolidColor(companion.m3498getBlack0d7_KjU(), null);
        StrokeCap.Companion companion2 = StrokeCap.INSTANCE;
        int iM3803getButtKaPHkGw = companion2.m3803getButtKaPHkGw();
        StrokeJoin.Companion companion3 = StrokeJoin.INSTANCE;
        int iM3813getBevelLxFBmk8 = companion3.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderJ = a.j(10.94f, 8.12f, 8.9f, 6.07f);
        pathBuilderJ.lineTo(9.88f, 5.0f);
        a0.a.n(pathBuilderJ, 4.24f, 1.83f, 2.0f, 20.0f);
        pathBuilderJ.verticalLineToRelative(10.17f);
        pathBuilderJ.lineToRelative(-3.12f, -3.12f);
        pathBuilderJ.curveTo(16.96f, 13.71f, 17.0f, 13.36f, 17.0f, 13.0f);
        pathBuilderJ.curveToRelative(0.0f, -2.76f, -2.24f, -5.0f, -5.0f, -5.0f);
        pathBuilderJ.curveTo(11.64f, 8.0f, 11.29f, 8.04f, 10.94f, 8.12f);
        pathBuilderJ.close();
        pathBuilderJ.moveTo(12.0f, 18.0f);
        pathBuilderJ.curveToRelative(-2.76f, 0.0f, -5.0f, -2.24f, -5.0f, -5.0f);
        pathBuilderJ.curveToRelative(0.0f, -0.91f, 0.25f, -1.76f, 0.68f, -2.49f);
        b.m(pathBuilderJ, 4.17f, 7.0f, 4.0f, 12.0f);
        pathBuilderJ.horizontalLineToRelative(12.17f);
        pathBuilderJ.lineToRelative(-1.68f, -1.68f);
        pathBuilderJ.curveTo(13.76f, 17.75f, 12.91f, 18.0f, 12.0f, 18.0f);
        pathBuilderJ.close();
        ImageVector.Builder.m4024addPathoIyEayM$default(builder, pathBuilderJ.getNodes(), defaultFillType, "", solidColor, 0.3f, null, 0.3f, 1.0f, iM3803getButtKaPHkGw, iM3813getBevelLxFBmk8, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null);
        int defaultFillType2 = VectorKt.getDefaultFillType();
        SolidColor solidColor2 = new SolidColor(companion.m3498getBlack0d7_KjU(), null);
        int iM3803getButtKaPHkGw2 = companion2.m3803getButtKaPHkGw();
        int iM3813getBevelLxFBmk82 = companion3.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderJ2 = a.j(8.9f, 6.07f, 7.48f, 4.66f);
        pathBuilderJ2.lineTo(9.0f, 3.0f);
        a0.a.n(pathBuilderJ2, 6.0f, 1.83f, 2.0f, 20.0f);
        pathBuilderJ2.curveToRelative(1.1f, 0.0f, 2.0f, 0.9f, 2.0f, 2.0f);
        pathBuilderJ2.verticalLineToRelative(12.0f);
        pathBuilderJ2.curveToRelative(0.0f, 0.05f, -0.01f, 0.1f, -0.02f, 0.16f);
        b.n(pathBuilderJ2, 20.0f, 17.17f, 7.0f, -4.05f);
        pathBuilderJ2.lineToRelative(-1.83f, -2.0f);
        w.a.w(pathBuilderJ2, 9.88f, 8.9f, 6.07f);
        pathBuilderJ2.moveTo(20.49f, 23.31f);
        pathBuilderJ2.lineTo(18.17f, 21.0f);
        pathBuilderJ2.horizontalLineTo(4.0f);
        pathBuilderJ2.curveToRelative(-1.1f, 0.0f, -2.0f, -0.9f, -2.0f, -2.0f);
        pathBuilderJ2.verticalLineTo(7.0f);
        pathBuilderJ2.curveToRelative(0.0f, -0.59f, 0.27f, -1.12f, 0.68f, -1.49f);
        pathBuilderJ2.lineToRelative(-2.0f, -2.0f);
        pathBuilderJ2.lineTo(2.1f, 2.1f);
        pathBuilderJ2.lineTo(7.0f, 7.0f);
        pathBuilderJ2.lineToRelative(2.01f, 2.01f);
        pathBuilderJ2.lineToRelative(1.43f, 1.43f);
        pathBuilderJ2.lineToRelative(4.1f, 4.1f);
        pathBuilderJ2.lineToRelative(1.43f, 1.43f);
        pathBuilderJ2.lineTo(19.0f, 19.0f);
        pathBuilderJ2.lineToRelative(1.82f, 1.82f);
        d.C(pathBuilderJ2, 1.08f, 1.08f, 20.49f, 23.31f);
        pathBuilderJ2.moveTo(9.19f, 12.02f);
        pathBuilderJ2.curveTo(9.08f, 12.33f, 9.0f, 12.65f, 9.0f, 13.0f);
        pathBuilderJ2.curveToRelative(0.0f, 1.65f, 1.35f, 3.0f, 3.0f, 3.0f);
        pathBuilderJ2.curveToRelative(0.35f, 0.0f, 0.67f, -0.08f, 0.98f, -0.19f);
        c.z(pathBuilderJ2, 9.19f, 12.02f, 16.17f, 19.0f);
        pathBuilderJ2.lineToRelative(-1.68f, -1.68f);
        pathBuilderJ2.curveTo(13.76f, 17.75f, 12.91f, 18.0f, 12.0f, 18.0f);
        pathBuilderJ2.curveToRelative(-2.76f, 0.0f, -5.0f, -2.24f, -5.0f, -5.0f);
        pathBuilderJ2.curveToRelative(0.0f, -0.91f, 0.25f, -1.76f, 0.68f, -2.49f);
        b.m(pathBuilderJ2, 4.17f, 7.0f, 4.0f, 12.0f);
        a.x(pathBuilderJ2, 16.17f, 14.81f, 11.98f);
        pathBuilderJ2.lineToRelative(2.08f, 2.08f);
        pathBuilderJ2.curveTo(16.96f, 13.71f, 17.0f, 13.36f, 17.0f, 13.0f);
        pathBuilderJ2.curveToRelative(0.0f, -2.76f, -2.24f, -5.0f, -5.0f, -5.0f);
        pathBuilderJ2.curveToRelative(-0.36f, 0.0f, -0.71f, 0.04f, -1.06f, 0.12f);
        pathBuilderJ2.lineToRelative(2.08f, 2.08f);
        pathBuilderJ2.curveTo(13.85f, 10.5f, 14.5f, 11.15f, 14.81f, 11.98f);
        pathBuilderJ2.close();
        ImageVector imageVectorBuild = ImageVector.Builder.m4024addPathoIyEayM$default(builder, pathBuilderJ2.getNodes(), defaultFillType2, "", solidColor2, 1.0f, null, 1.0f, 1.0f, iM3803getButtKaPHkGw2, iM3813getBevelLxFBmk82, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null).build();
        _noPhotography = imageVectorBuild;
        return imageVectorBuild;
    }
}
