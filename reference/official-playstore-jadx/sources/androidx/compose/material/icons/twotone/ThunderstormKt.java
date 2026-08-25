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

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\"\u0010\u0010\u0000\u001a\u0004\u0018\u00010\u0001X\u0082\u000e¢\u0006\u0002\n\u0000\"\u0015\u0010\u0002\u001a\u00020\u0001*\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"_thunderstorm", "Landroidx/compose/ui/graphics/vector/ImageVector;", "Thunderstorm", "Landroidx/compose/material/icons/Icons$TwoTone;", "getThunderstorm", "(Landroidx/compose/material/icons/Icons$TwoTone;)Landroidx/compose/ui/graphics/vector/ImageVector;", "material-icons-extended_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class ThunderstormKt {
    private static ImageVector _thunderstorm;

    public static final ImageVector getThunderstorm(Icons.TwoTone twoTone) {
        ImageVector imageVector = _thunderstorm;
        if (imageVector != null) {
            return imageVector;
        }
        ImageVector.Builder builder = new ImageVector.Builder("TwoTone.Thunderstorm", Dp.m5678constructorimpl(24.0f), Dp.m5678constructorimpl(24.0f), 24.0f, 24.0f, 0L, 0, false, 96, null);
        int defaultFillType = VectorKt.getDefaultFillType();
        Color.Companion companion = Color.INSTANCE;
        SolidColor solidColor = new SolidColor(companion.m3498getBlack0d7_KjU(), null);
        StrokeCap.Companion companion2 = StrokeCap.INSTANCE;
        int iM3803getButtKaPHkGw = companion2.m3803getButtKaPHkGw();
        StrokeJoin.Companion companion3 = StrokeJoin.INSTANCE;
        int iM3813getBevelLxFBmk8 = companion3.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderJ = a.j(17.73f, 9.01f, 16.2f, 8.87f);
        pathBuilderJ.lineToRelative(-0.25f, -1.52f);
        pathBuilderJ.curveTo(15.63f, 5.44f, 13.94f, 4.0f, 12.0f, 4.0f);
        pathBuilderJ.curveToRelative(-1.44f, 0.0f, -2.77f, 0.78f, -3.48f, 2.04f);
        pathBuilderJ.lineTo(8.03f, 6.91f);
        pathBuilderJ.lineTo(7.04f, 7.04f);
        pathBuilderJ.curveTo(5.31f, 7.27f, 4.0f, 8.76f, 4.0f, 10.5f);
        pathBuilderJ.curveTo(4.0f, 12.43f, 5.57f, 14.0f, 7.5f, 14.0f);
        pathBuilderJ.horizontalLineToRelative(10.0f);
        pathBuilderJ.curveToRelative(1.38f, 0.0f, 2.5f, -1.12f, 2.5f, -2.5f);
        pathBuilderJ.curveTo(20.0f, 10.22f, 19.01f, 9.13f, 17.73f, 9.01f);
        pathBuilderJ.close();
        ImageVector.Builder.m4024addPathoIyEayM$default(builder, pathBuilderJ.getNodes(), defaultFillType, "", solidColor, 0.3f, null, 0.3f, 1.0f, iM3803getButtKaPHkGw, iM3813getBevelLxFBmk8, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null);
        int defaultFillType2 = VectorKt.getDefaultFillType();
        SolidColor solidColor2 = new SolidColor(companion.m3498getBlack0d7_KjU(), null);
        int iM3803getButtKaPHkGw2 = companion2.m3803getButtKaPHkGw();
        int iM3813getBevelLxFBmk82 = companion3.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderA = c.a(17.92f, 7.02f);
        pathBuilderA.curveTo(17.45f, 4.18f, 14.97f, 2.0f, 12.0f, 2.0f);
        pathBuilderA.curveTo(9.82f, 2.0f, 7.83f, 3.18f, 6.78f, 5.06f);
        pathBuilderA.curveTo(4.09f, 5.41f, 2.0f, 7.74f, 2.0f, 10.5f);
        pathBuilderA.curveTo(2.0f, 13.53f, 4.47f, 16.0f, 7.5f, 16.0f);
        pathBuilderA.horizontalLineToRelative(10.0f);
        pathBuilderA.curveToRelative(2.48f, 0.0f, 4.5f, -2.02f, 4.5f, -4.5f);
        pathBuilderA.curveTo(22.0f, 9.16f, 20.21f, 7.23f, 17.92f, 7.02f);
        a.g(pathBuilderA, 17.5f, 14.0f, -10.0f);
        pathBuilderA.curveTo(5.57f, 14.0f, 4.0f, 12.43f, 4.0f, 10.5f);
        pathBuilderA.curveToRelative(0.0f, -1.74f, 1.31f, -3.23f, 3.04f, -3.46f);
        pathBuilderA.lineToRelative(0.99f, -0.13f);
        pathBuilderA.lineToRelative(0.49f, -0.87f);
        pathBuilderA.curveTo(9.23f, 4.78f, 10.56f, 4.0f, 12.0f, 4.0f);
        pathBuilderA.curveToRelative(1.94f, 0.0f, 3.63f, 1.44f, 3.95f, 3.35f);
        pathBuilderA.lineToRelative(0.25f, 1.52f);
        pathBuilderA.lineToRelative(1.54f, 0.14f);
        pathBuilderA.curveTo(19.01f, 9.13f, 20.0f, 10.22f, 20.0f, 11.5f);
        pathBuilderA.curveTo(20.0f, 12.88f, 18.88f, 14.0f, 17.5f, 14.0f);
        pathBuilderA.close();
        ImageVector.Builder.m4024addPathoIyEayM$default(builder, pathBuilderA.getNodes(), defaultFillType2, "", solidColor2, 1.0f, null, 1.0f, 1.0f, iM3803getButtKaPHkGw2, iM3813getBevelLxFBmk82, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null);
        int defaultFillType3 = VectorKt.getDefaultFillType();
        SolidColor solidColor3 = new SolidColor(companion.m3498getBlack0d7_KjU(), null);
        int iM3803getButtKaPHkGw3 = companion2.m3803getButtKaPHkGw();
        int iM3813getBevelLxFBmk83 = companion3.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderR = a.r(14.8f, 17.0f, -2.9f, 3.32f);
        pathBuilderR.lineToRelative(2.0f, 1.0f);
        pathBuilderR.lineToRelative(-2.35f, 2.68f);
        pathBuilderR.lineToRelative(2.65f, 0.0f);
        pathBuilderR.lineToRelative(2.9f, -3.32f);
        ImageVector.Builder.m4024addPathoIyEayM$default(builder, b.t(pathBuilderR, -2.0f, -1.0f, 2.35f, -2.68f), defaultFillType3, "", solidColor3, 1.0f, null, 1.0f, 1.0f, iM3803getButtKaPHkGw3, iM3813getBevelLxFBmk83, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null);
        int defaultFillType4 = VectorKt.getDefaultFillType();
        SolidColor solidColor4 = new SolidColor(companion.m3498getBlack0d7_KjU(), null);
        int iM3803getButtKaPHkGw4 = companion2.m3803getButtKaPHkGw();
        int iM3813getBevelLxFBmk84 = companion3.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderR2 = a.r(8.8f, 17.0f, -2.9f, 3.32f);
        pathBuilderR2.lineToRelative(2.0f, 1.0f);
        pathBuilderR2.lineToRelative(-2.35f, 2.68f);
        pathBuilderR2.lineToRelative(2.65f, 0.0f);
        pathBuilderR2.lineToRelative(2.9f, -3.32f);
        ImageVector imageVectorBuild = ImageVector.Builder.m4024addPathoIyEayM$default(builder, b.t(pathBuilderR2, -2.0f, -1.0f, 2.35f, -2.68f), defaultFillType4, "", solidColor4, 1.0f, null, 1.0f, 1.0f, iM3803getButtKaPHkGw4, iM3813getBevelLxFBmk84, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null).build();
        _thunderstorm = imageVectorBuild;
        return imageVectorBuild;
    }
}
