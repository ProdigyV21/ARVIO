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
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\"\u0010\u0010\u0000\u001a\u0004\u0018\u00010\u0001X\u0082\u000e¢\u0006\u0002\n\u0000\"\u0015\u0010\u0002\u001a\u00020\u0001*\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"_shieldMoon", "Landroidx/compose/ui/graphics/vector/ImageVector;", "ShieldMoon", "Landroidx/compose/material/icons/Icons$TwoTone;", "getShieldMoon", "(Landroidx/compose/material/icons/Icons$TwoTone;)Landroidx/compose/ui/graphics/vector/ImageVector;", "material-icons-extended_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class ShieldMoonKt {
    private static ImageVector _shieldMoon;

    public static final ImageVector getShieldMoon(Icons.TwoTone twoTone) {
        ImageVector imageVector = _shieldMoon;
        if (imageVector != null) {
            return imageVector;
        }
        ImageVector.Builder builder = new ImageVector.Builder("TwoTone.ShieldMoon", Dp.m5678constructorimpl(24.0f), Dp.m5678constructorimpl(24.0f), 24.0f, 24.0f, 0L, 0, false, 96, null);
        int defaultFillType = VectorKt.getDefaultFillType();
        Color.Companion companion = Color.INSTANCE;
        SolidColor solidColor = new SolidColor(companion.m3498getBlack0d7_KjU(), null);
        StrokeCap.Companion companion2 = StrokeCap.INSTANCE;
        int iM3803getButtKaPHkGw = companion2.m3803getButtKaPHkGw();
        StrokeJoin.Companion companion3 = StrokeJoin.INSTANCE;
        int iM3813getBevelLxFBmk8 = companion3.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderI = a.i(6.0f, 6.39f, 4.7f);
        pathBuilderI.curveToRelative(0.0f, 4.0f, 2.55f, 7.7f, 6.0f, 8.83f);
        pathBuilderI.curveToRelative(3.45f, -1.13f, 6.0f, -4.82f, 6.0f, -8.83f);
        pathBuilderI.verticalLineToRelative(-4.7f);
        d.C(pathBuilderI, -6.0f, -2.25f, 6.0f, 6.39f);
        pathBuilderI.moveTo(12.21f, 7.61f);
        pathBuilderI.curveToRelative(-0.46f, 1.23f, -0.39f, 2.64f, 0.32f, 3.86f);
        pathBuilderI.curveToRelative(0.71f, 1.22f, 1.89f, 1.99f, 3.18f, 2.2f);
        pathBuilderI.curveToRelative(0.34f, 0.06f, 0.49f, 0.47f, 0.26f, 0.74f);
        pathBuilderI.curveToRelative(-1.84f, 2.17f, -5.21f, 2.1f, -6.96f, -0.07f);
        pathBuilderI.curveToRelative(-2.19f, -2.72f, -0.65f, -6.72f, 2.69f, -7.33f);
        pathBuilderI.curveTo(12.04f, 6.95f, 12.33f, 7.28f, 12.21f, 7.61f);
        pathBuilderI.close();
        ImageVector.Builder.m4024addPathoIyEayM$default(builder, pathBuilderI.getNodes(), defaultFillType, "", solidColor, 0.3f, null, 0.3f, 1.0f, iM3803getButtKaPHkGw, iM3813getBevelLxFBmk8, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null);
        int defaultFillType2 = VectorKt.getDefaultFillType();
        SolidColor solidColor2 = new SolidColor(companion.m3498getBlack0d7_KjU(), null);
        int iM3803getButtKaPHkGw2 = companion2.m3803getButtKaPHkGw();
        int iM3813getBevelLxFBmk82 = companion3.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderS = c.s(12.0f, 2.0f, 4.0f, 5.0f, 6.09f);
        pathBuilderS.curveToRelative(0.0f, 5.05f, 3.41f, 9.76f, 8.0f, 10.91f);
        pathBuilderS.curveToRelative(4.59f, -1.15f, 8.0f, -5.86f, 8.0f, -10.91f);
        c.x(pathBuilderS, 5.0f, 12.0f, 2.0f);
        pathBuilderS.moveTo(18.0f, 11.09f);
        pathBuilderS.curveToRelative(0.0f, 4.0f, -2.55f, 7.7f, -6.0f, 8.83f);
        pathBuilderS.curveToRelative(-3.45f, -1.13f, -6.0f, -4.82f, -6.0f, -8.83f);
        pathBuilderS.verticalLineToRelative(-4.7f);
        pathBuilderS.lineToRelative(6.0f, -2.25f);
        ImageVector.Builder.m4024addPathoIyEayM$default(builder, b.d(pathBuilderS, 6.0f, 2.25f, 11.09f), defaultFillType2, "", solidColor2, 1.0f, null, 1.0f, 1.0f, iM3803getButtKaPHkGw2, iM3813getBevelLxFBmk82, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null);
        int defaultFillType3 = VectorKt.getDefaultFillType();
        SolidColor solidColor3 = new SolidColor(companion.m3498getBlack0d7_KjU(), null);
        int iM3803getButtKaPHkGw3 = companion2.m3803getButtKaPHkGw();
        int iM3813getBevelLxFBmk83 = companion3.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderA = c.a(9.01f, 14.33f);
        pathBuilderA.curveToRelative(1.75f, 2.17f, 5.12f, 2.24f, 6.96f, 0.07f);
        pathBuilderA.curveToRelative(0.23f, -0.27f, 0.08f, -0.68f, -0.26f, -0.74f);
        pathBuilderA.curveToRelative(-1.29f, -0.21f, -2.48f, -0.98f, -3.18f, -2.2f);
        pathBuilderA.curveToRelative(-0.71f, -1.22f, -0.78f, -2.63f, -0.32f, -3.86f);
        pathBuilderA.curveToRelative(0.12f, -0.33f, -0.16f, -0.66f, -0.51f, -0.6f);
        pathBuilderA.curveTo(8.36f, 7.62f, 6.81f, 11.61f, 9.01f, 14.33f);
        pathBuilderA.close();
        ImageVector imageVectorBuild = ImageVector.Builder.m4024addPathoIyEayM$default(builder, pathBuilderA.getNodes(), defaultFillType3, "", solidColor3, 1.0f, null, 1.0f, 1.0f, iM3803getButtKaPHkGw3, iM3813getBevelLxFBmk83, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null).build();
        _shieldMoon = imageVectorBuild;
        return imageVectorBuild;
    }
}
