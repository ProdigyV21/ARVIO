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
import v.c;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\"\u0010\u0010\u0000\u001a\u0004\u0018\u00010\u0001X\u0082\u000e¢\u0006\u0002\n\u0000\"\u0015\u0010\u0002\u001a\u00020\u0001*\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"_energySavingsLeaf", "Landroidx/compose/ui/graphics/vector/ImageVector;", "EnergySavingsLeaf", "Landroidx/compose/material/icons/Icons$TwoTone;", "getEnergySavingsLeaf", "(Landroidx/compose/material/icons/Icons$TwoTone;)Landroidx/compose/ui/graphics/vector/ImageVector;", "material-icons-extended_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class EnergySavingsLeafKt {
    private static ImageVector _energySavingsLeaf;

    public static final ImageVector getEnergySavingsLeaf(Icons.TwoTone twoTone) {
        ImageVector imageVector = _energySavingsLeaf;
        if (imageVector != null) {
            return imageVector;
        }
        ImageVector.Builder builder = new ImageVector.Builder("TwoTone.EnergySavingsLeaf", Dp.m5678constructorimpl(24.0f), Dp.m5678constructorimpl(24.0f), 24.0f, 24.0f, 0L, 0, false, 96, null);
        int defaultFillType = VectorKt.getDefaultFillType();
        Color.Companion companion = Color.INSTANCE;
        SolidColor solidColor = new SolidColor(companion.m3498getBlack0d7_KjU(), null);
        StrokeCap.Companion companion2 = StrokeCap.INSTANCE;
        int iM3803getButtKaPHkGw = companion2.m3803getButtKaPHkGw();
        StrokeJoin.Companion companion3 = StrokeJoin.INSTANCE;
        int iM3813getBevelLxFBmk8 = companion3.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderA = c.a(7.1f, 7.01f);
        pathBuilderA.curveTo(5.74f, 8.32f, 5.0f, 10.1f, 5.0f, 12.0f);
        pathBuilderA.curveToRelative(0.0f, 3.86f, 3.14f, 7.0f, 7.0f, 7.0f);
        pathBuilderA.curveToRelative(1.87f, 0.0f, 3.63f, -0.73f, 4.95f, -2.05f);
        pathBuilderA.curveTo(18.27f, 15.63f, 19.0f, 13.87f, 19.0f, 12.0f);
        pathBuilderA.verticalLineTo(5.0f);
        pathBuilderA.horizontalLineToRelative(-7.0f);
        pathBuilderA.curveTo(10.16f, 5.0f, 8.42f, 5.71f, 7.1f, 7.01f);
        pathBuilderA.close();
        pathBuilderA.moveTo(13.88f, 7.12f);
        pathBuilderA.curveToRelative(0.14f, 0.14f, 0.16f, 0.36f, 0.04f, 0.52f);
        pathBuilderA.lineToRelative(-2.44f, 3.33f);
        pathBuilderA.lineToRelative(4.05f, 0.4f);
        pathBuilderA.curveToRelative(0.44f, 0.04f, 0.63f, 0.59f, 0.3f, 0.89f);
        pathBuilderA.lineToRelative(-5.16f, 4.63f);
        pathBuilderA.curveToRelative(-0.16f, 0.15f, -0.41f, 0.14f, -0.56f, -0.01f);
        pathBuilderA.curveToRelative(-0.14f, -0.14f, -0.16f, -0.36f, -0.04f, -0.52f);
        pathBuilderA.lineToRelative(2.44f, -3.33f);
        pathBuilderA.lineToRelative(-4.05f, -0.4f);
        pathBuilderA.curveToRelative(-0.44f, -0.04f, -0.63f, -0.59f, -0.3f, -0.89f);
        pathBuilderA.lineToRelative(5.16f, -4.63f);
        pathBuilderA.curveTo(13.48f, 6.96f, 13.73f, 6.97f, 13.88f, 7.12f);
        pathBuilderA.close();
        ImageVector.Builder.m4024addPathoIyEayM$default(builder, pathBuilderA.getNodes(), defaultFillType, "", solidColor, 0.3f, null, 0.3f, 1.0f, iM3803getButtKaPHkGw, iM3813getBevelLxFBmk8, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null);
        int defaultFillType2 = VectorKt.getDefaultFillType();
        SolidColor solidColor2 = new SolidColor(companion.m3498getBlack0d7_KjU(), null);
        int iM3803getButtKaPHkGw2 = companion2.m3803getButtKaPHkGw();
        int iM3813getBevelLxFBmk82 = companion3.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderA2 = c.a(12.0f, 3.0f);
        pathBuilderA2.curveTo(12.0f, 3.0f, 12.0f, 3.0f, 12.0f, 3.0f);
        pathBuilderA2.curveToRelative(-4.8f, 0.0f, -9.0f, 3.86f, -9.0f, 9.0f);
        pathBuilderA2.curveToRelative(0.0f, 2.12f, 0.74f, 4.07f, 1.97f, 5.61f);
        pathBuilderA2.lineTo(3.0f, 19.59f);
        pathBuilderA2.lineTo(4.41f, 21.0f);
        pathBuilderA2.lineToRelative(1.97f, -1.97f);
        pathBuilderA2.curveTo(7.93f, 20.26f, 9.88f, 21.0f, 12.0f, 21.0f);
        pathBuilderA2.curveToRelative(2.3f, 0.0f, 4.61f, -0.88f, 6.36f, -2.64f);
        pathBuilderA2.curveTo(20.12f, 16.61f, 21.0f, 14.3f, 21.0f, 12.0f);
        c.n(pathBuilderA2, 3.0f, 12.0f, 19.0f, 12.0f);
        pathBuilderA2.curveToRelative(0.0f, 1.87f, -0.73f, 3.63f, -2.05f, 4.95f);
        pathBuilderA2.curveTo(15.63f, 18.27f, 13.87f, 19.0f, 12.0f, 19.0f);
        pathBuilderA2.curveToRelative(-3.86f, 0.0f, -7.0f, -3.14f, -7.0f, -7.0f);
        pathBuilderA2.curveToRelative(0.0f, -1.9f, 0.74f, -3.68f, 2.1f, -4.99f);
        pathBuilderA2.curveTo(8.42f, 5.71f, 10.16f, 5.0f, 12.0f, 5.0f);
        ImageVector.Builder.m4024addPathoIyEayM$default(builder, a.e(pathBuilderA2, 7.0f, 12.0f), defaultFillType2, "", solidColor2, 1.0f, null, 1.0f, 1.0f, iM3803getButtKaPHkGw2, iM3813getBevelLxFBmk82, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null);
        int defaultFillType3 = VectorKt.getDefaultFillType();
        SolidColor solidColor3 = new SolidColor(companion.m3498getBlack0d7_KjU(), null);
        int iM3803getButtKaPHkGw3 = companion2.m3803getButtKaPHkGw();
        int iM3813getBevelLxFBmk83 = companion3.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderR = a.r(8.46f, 12.63f, 4.05f, 0.4f);
        pathBuilderR.lineToRelative(-2.44f, 3.33f);
        pathBuilderR.curveToRelative(-0.11f, 0.16f, -0.1f, 0.38f, 0.04f, 0.52f);
        pathBuilderR.curveToRelative(0.15f, 0.15f, 0.4f, 0.16f, 0.56f, 0.01f);
        pathBuilderR.lineToRelative(5.16f, -4.63f);
        pathBuilderR.curveToRelative(0.33f, -0.3f, 0.15f, -0.85f, -0.3f, -0.89f);
        pathBuilderR.lineToRelative(-4.05f, -0.4f);
        pathBuilderR.lineToRelative(2.44f, -3.33f);
        pathBuilderR.curveToRelative(0.11f, -0.16f, 0.1f, -0.38f, -0.04f, -0.52f);
        pathBuilderR.curveToRelative(-0.15f, -0.15f, -0.4f, -0.16f, -0.56f, -0.01f);
        pathBuilderR.lineToRelative(-5.16f, 4.63f);
        pathBuilderR.curveTo(7.84f, 12.04f, 8.02f, 12.59f, 8.46f, 12.63f);
        pathBuilderR.close();
        ImageVector imageVectorBuild = ImageVector.Builder.m4024addPathoIyEayM$default(builder, pathBuilderR.getNodes(), defaultFillType3, "", solidColor3, 1.0f, null, 1.0f, 1.0f, iM3803getButtKaPHkGw3, iM3813getBevelLxFBmk83, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null).build();
        _energySavingsLeaf = imageVectorBuild;
        return imageVectorBuild;
    }
}
