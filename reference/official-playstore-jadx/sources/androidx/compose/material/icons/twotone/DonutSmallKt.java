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
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\"\u0010\u0010\u0000\u001a\u0004\u0018\u00010\u0001X\u0082\u000e¢\u0006\u0002\n\u0000\"\u0015\u0010\u0002\u001a\u00020\u0001*\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"_donutSmall", "Landroidx/compose/ui/graphics/vector/ImageVector;", "DonutSmall", "Landroidx/compose/material/icons/Icons$TwoTone;", "getDonutSmall", "(Landroidx/compose/material/icons/Icons$TwoTone;)Landroidx/compose/ui/graphics/vector/ImageVector;", "material-icons-extended_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class DonutSmallKt {
    private static ImageVector _donutSmall;

    public static final ImageVector getDonutSmall(Icons.TwoTone twoTone) {
        ImageVector imageVector = _donutSmall;
        if (imageVector != null) {
            return imageVector;
        }
        ImageVector.Builder builder = new ImageVector.Builder("TwoTone.DonutSmall", Dp.m5678constructorimpl(24.0f), Dp.m5678constructorimpl(24.0f), 24.0f, 24.0f, 0L, 0, false, 96, null);
        int defaultFillType = VectorKt.getDefaultFillType();
        Color.Companion companion = Color.INSTANCE;
        SolidColor solidColor = new SolidColor(companion.m3498getBlack0d7_KjU(), null);
        StrokeCap.Companion companion2 = StrokeCap.INSTANCE;
        int iM3803getButtKaPHkGw = companion2.m3803getButtKaPHkGw();
        StrokeJoin.Companion companion3 = StrokeJoin.INSTANCE;
        int iM3813getBevelLxFBmk8 = companion3.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderA = a.a(15.99f, 9.0f, 3.43f);
        pathBuilderA.curveTo(18.6f, 7.0f, 17.0f, 5.4f, 15.0f, 4.58f);
        pathBuilderA.verticalLineToRelative(3.43f);
        pathBuilderA.curveToRelative(0.37f, 0.28f, 0.71f, 0.62f, 0.99f, 0.99f);
        pathBuilderA.close();
        pathBuilderA.moveTo(4.0f, 12.0f);
        pathBuilderA.curveToRelative(0.0f, 3.35f, 2.04f, 6.24f, 5.0f, 7.42f);
        pathBuilderA.verticalLineToRelative(-3.44f);
        pathBuilderA.curveToRelative(-1.23f, -0.93f, -2.0f, -2.4f, -2.0f, -3.99f);
        pathBuilderA.curveTo(7.0f, 10.4f, 7.77f, 8.93f, 9.0f, 8.0f);
        pathBuilderA.lineTo(9.0f, 4.58f);
        pathBuilderA.curveTo(6.04f, 5.76f, 4.0f, 8.65f, 4.0f, 12.0f);
        d.k(pathBuilderA, 15.0f, 15.99f, 3.43f);
        pathBuilderA.curveToRelative(2.0f, -0.82f, 3.6f, -2.42f, 4.42f, -4.42f);
        pathBuilderA.horizontalLineToRelative(-3.43f);
        pathBuilderA.curveToRelative(-0.28f, 0.37f, -0.62f, 0.71f, -0.99f, 0.99f);
        pathBuilderA.close();
        ImageVector.Builder.m4024addPathoIyEayM$default(builder, pathBuilderA.getNodes(), defaultFillType, "", solidColor, 0.3f, null, 0.3f, 1.0f, iM3803getButtKaPHkGw, iM3813getBevelLxFBmk8, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null);
        int defaultFillType2 = VectorKt.getDefaultFillType();
        SolidColor solidColor2 = new SolidColor(companion.m3498getBlack0d7_KjU(), null);
        int iM3803getButtKaPHkGw2 = companion2.m3803getButtKaPHkGw();
        int iM3813getBevelLxFBmk82 = companion3.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderA2 = a.a(14.82f, 11.0f, 7.13f);
        pathBuilderA2.curveToRelative(-0.47f, -4.72f, -4.23f, -8.48f, -8.95f, -8.95f);
        pathBuilderA2.verticalLineToRelative(7.13f);
        pathBuilderA2.curveToRelative(0.85f, 0.31f, 1.51f, 0.97f, 1.82f, 1.82f);
        pathBuilderA2.close();
        pathBuilderA2.moveTo(15.0f, 4.58f);
        pathBuilderA2.curveTo(17.0f, 5.4f, 18.6f, 7.0f, 19.42f, 9.0f);
        pathBuilderA2.horizontalLineToRelative(-3.43f);
        pathBuilderA2.curveToRelative(-0.28f, -0.37f, -0.62f, -0.71f, -0.99f, -0.99f);
        c.z(pathBuilderA2, 15.0f, 4.58f, 2.0f, 12.0f);
        pathBuilderA2.curveToRelative(0.0f, 5.19f, 3.95f, 9.45f, 9.0f, 9.95f);
        pathBuilderA2.verticalLineToRelative(-7.13f);
        pathBuilderA2.curveTo(9.84f, 14.4f, 9.0f, 13.3f, 9.0f, 12.0f);
        pathBuilderA2.curveToRelative(0.0f, -1.3f, 0.84f, -2.4f, 2.0f, -2.82f);
        pathBuilderA2.lineTo(11.0f, 2.05f);
        pathBuilderA2.curveToRelative(-5.05f, 0.5f, -9.0f, 4.76f, -9.0f, 9.95f);
        d.k(pathBuilderA2, 9.0f, 4.58f, 3.44f);
        pathBuilderA2.curveToRelative(-1.23f, 0.92f, -2.0f, 2.39f, -2.0f, 3.98f);
        pathBuilderA2.curveToRelative(0.0f, 1.59f, 0.77f, 3.06f, 2.0f, 3.99f);
        pathBuilderA2.verticalLineToRelative(3.44f);
        pathBuilderA2.curveTo(6.04f, 18.24f, 4.0f, 15.35f, 4.0f, 12.0f);
        pathBuilderA2.curveToRelative(0.0f, -3.35f, 2.04f, -6.24f, 5.0f, -7.42f);
        d.k(pathBuilderA2, 13.0f, 14.82f, 7.13f);
        pathBuilderA2.curveToRelative(4.72f, -0.47f, 8.48f, -4.23f, 8.95f, -8.95f);
        pathBuilderA2.horizontalLineToRelative(-7.13f);
        pathBuilderA2.curveToRelative(-0.31f, 0.85f, -0.97f, 1.51f, -1.82f, 1.82f);
        pathBuilderA2.close();
        pathBuilderA2.moveTo(15.0f, 15.99f);
        pathBuilderA2.curveToRelative(0.37f, -0.28f, 0.71f, -0.61f, 0.99f, -0.99f);
        pathBuilderA2.horizontalLineToRelative(3.43f);
        pathBuilderA2.curveTo(18.6f, 17.0f, 17.0f, 18.6f, 15.0f, 19.42f);
        ImageVector imageVectorBuild = ImageVector.Builder.m4024addPathoIyEayM$default(builder, b.c(pathBuilderA2, -3.43f), defaultFillType2, "", solidColor2, 1.0f, null, 1.0f, 1.0f, iM3803getButtKaPHkGw2, iM3813getBevelLxFBmk82, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null).build();
        _donutSmall = imageVectorBuild;
        return imageVectorBuild;
    }
}
