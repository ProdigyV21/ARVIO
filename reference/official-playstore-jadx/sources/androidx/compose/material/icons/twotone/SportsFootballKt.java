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
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\"\u0010\u0010\u0000\u001a\u0004\u0018\u00010\u0001X\u0082\u000e¢\u0006\u0002\n\u0000\"\u0015\u0010\u0002\u001a\u00020\u0001*\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"_sportsFootball", "Landroidx/compose/ui/graphics/vector/ImageVector;", "SportsFootball", "Landroidx/compose/material/icons/Icons$TwoTone;", "getSportsFootball", "(Landroidx/compose/material/icons/Icons$TwoTone;)Landroidx/compose/ui/graphics/vector/ImageVector;", "material-icons-extended_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class SportsFootballKt {
    private static ImageVector _sportsFootball;

    public static final ImageVector getSportsFootball(Icons.TwoTone twoTone) {
        ImageVector imageVector = _sportsFootball;
        if (imageVector != null) {
            return imageVector;
        }
        ImageVector.Builder builder = new ImageVector.Builder("TwoTone.SportsFootball", Dp.m5678constructorimpl(24.0f), Dp.m5678constructorimpl(24.0f), 24.0f, 24.0f, 0L, 0, false, 96, null);
        int defaultFillType = VectorKt.getDefaultFillType();
        Color.Companion companion = Color.INSTANCE;
        SolidColor solidColor = new SolidColor(companion.m3498getBlack0d7_KjU(), null);
        StrokeCap.Companion companion2 = StrokeCap.INSTANCE;
        int iM3803getButtKaPHkGw = companion2.m3803getButtKaPHkGw();
        StrokeJoin.Companion companion3 = StrokeJoin.INSTANCE;
        int iM3813getBevelLxFBmk8 = companion3.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderA = c.a(16.26f, 5.0f);
        pathBuilderA.curveToRelative(-0.35f, 0.0f, -0.8f, 0.01f, -1.33f, 0.06f);
        pathBuilderA.lineToRelative(4.03f, 4.03f);
        pathBuilderA.curveToRelative(0.14f, -1.63f, 0.01f, -3.07f, -0.17f, -3.86f);
        pathBuilderA.curveTo(18.28f, 5.12f, 17.4f, 5.0f, 16.26f, 5.0f);
        pathBuilderA.close();
        ImageVector.Builder.m4024addPathoIyEayM$default(builder, pathBuilderA.getNodes(), defaultFillType, "", solidColor, 0.3f, null, 0.3f, 1.0f, iM3803getButtKaPHkGw, iM3813getBevelLxFBmk8, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null);
        int defaultFillType2 = VectorKt.getDefaultFillType();
        SolidColor solidColor2 = new SolidColor(companion.m3498getBlack0d7_KjU(), null);
        int iM3803getButtKaPHkGw2 = companion2.m3803getButtKaPHkGw();
        int iM3813getBevelLxFBmk82 = companion3.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderA2 = c.a(5.21f, 18.77f);
        pathBuilderA2.curveTo(5.72f, 18.88f, 6.6f, 19.0f, 7.74f, 19.0f);
        pathBuilderA2.curveToRelative(0.34f, 0.0f, 0.79f, -0.01f, 1.3f, -0.05f);
        pathBuilderA2.lineToRelative(-4.01f, -4.01f);
        pathBuilderA2.curveTo(4.91f, 16.56f, 5.03f, 17.98f, 5.21f, 18.77f);
        pathBuilderA2.close();
        ImageVector.Builder.m4024addPathoIyEayM$default(builder, pathBuilderA2.getNodes(), defaultFillType2, "", solidColor2, 0.3f, null, 0.3f, 1.0f, iM3803getButtKaPHkGw2, iM3813getBevelLxFBmk82, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null);
        int defaultFillType3 = VectorKt.getDefaultFillType();
        SolidColor solidColor3 = new SolidColor(companion.m3498getBlack0d7_KjU(), null);
        int iM3803getButtKaPHkGw3 = companion2.m3803getButtKaPHkGw();
        int iM3813getBevelLxFBmk83 = companion3.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderA3 = c.a(7.87f, 7.87f);
        pathBuilderA3.curveToRelative(-1.28f, 1.28f, -2.03f, 2.97f, -2.45f, 4.65f);
        pathBuilderA3.lineToRelative(6.04f, 6.04f);
        pathBuilderA3.curveToRelative(1.6f, -0.39f, 3.33f, -1.11f, 4.66f, -2.44f);
        pathBuilderA3.curveToRelative(1.28f, -1.28f, 2.03f, -2.95f, 2.44f, -4.63f);
        pathBuilderA3.lineToRelative(-6.05f, -6.05f);
        pathBuilderA3.curveTo(10.92f, 5.83f, 9.2f, 6.55f, 7.87f, 7.87f);
        a.z(pathBuilderA3, 15.5f, 9.9f, -5.6f, 5.6f);
        pathBuilderA3.lineToRelative(-1.4f, -1.4f);
        ImageVector.Builder.m4024addPathoIyEayM$default(builder, b.e(pathBuilderA3, 5.6f, -5.6f, 15.5f, 9.9f), defaultFillType3, "", solidColor3, 0.3f, null, 0.3f, 1.0f, iM3803getButtKaPHkGw3, iM3813getBevelLxFBmk83, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null);
        int defaultFillType4 = VectorKt.getDefaultFillType();
        SolidColor solidColor4 = new SolidColor(companion.m3498getBlack0d7_KjU(), null);
        int iM3803getButtKaPHkGw4 = companion2.m3803getButtKaPHkGw();
        int iM3813getBevelLxFBmk84 = companion3.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderA4 = c.a(20.31f, 3.69f);
        pathBuilderA4.curveTo(19.99f, 3.36f, 18.37f, 3.0f, 16.26f, 3.0f);
        pathBuilderA4.curveToRelative(-3.03f, 0.0f, -7.09f, 0.75f, -9.8f, 3.46f);
        pathBuilderA4.curveTo(1.87f, 11.05f, 2.9f, 19.52f, 3.69f, 20.31f);
        pathBuilderA4.curveTo(4.01f, 20.64f, 5.63f, 21.0f, 7.74f, 21.0f);
        pathBuilderA4.curveToRelative(3.03f, 0.0f, 7.09f, -0.75f, 9.8f, -3.46f);
        pathBuilderA4.curveTo(22.13f, 12.95f, 21.1f, 4.48f, 20.31f, 3.69f);
        pathBuilderA4.close();
        pathBuilderA4.moveTo(7.74f, 19.0f);
        pathBuilderA4.curveToRelative(-1.14f, 0.0f, -2.02f, -0.12f, -2.53f, -0.23f);
        pathBuilderA4.curveToRelative(-0.18f, -0.79f, -0.3f, -2.21f, -0.17f, -3.83f);
        pathBuilderA4.lineToRelative(4.01f, 4.01f);
        pathBuilderA4.curveTo(8.53f, 18.99f, 8.08f, 19.0f, 7.74f, 19.0f);
        pathBuilderA4.close();
        pathBuilderA4.moveTo(16.13f, 16.13f);
        pathBuilderA4.curveToRelative(-1.33f, 1.33f, -3.06f, 2.05f, -4.66f, 2.44f);
        pathBuilderA4.lineToRelative(-6.04f, -6.04f);
        pathBuilderA4.curveToRelative(0.42f, -1.68f, 1.16f, -3.37f, 2.45f, -4.65f);
        pathBuilderA4.curveToRelative(1.32f, -1.32f, 3.05f, -2.04f, 4.64f, -2.43f);
        pathBuilderA4.lineToRelative(6.05f, 6.05f);
        pathBuilderA4.curveTo(18.15f, 13.17f, 17.4f, 14.85f, 16.13f, 16.13f);
        a.z(pathBuilderA4, 18.96f, 9.09f, -4.03f, -4.03f);
        pathBuilderA4.curveTo(15.45f, 5.01f, 15.91f, 5.0f, 16.26f, 5.0f);
        pathBuilderA4.curveToRelative(1.14f, 0.0f, 2.02f, 0.12f, 2.53f, 0.23f);
        pathBuilderA4.curveTo(18.97f, 6.02f, 19.09f, 7.45f, 18.96f, 9.09f);
        pathBuilderA4.close();
        ImageVector.Builder.m4024addPathoIyEayM$default(builder, pathBuilderA4.getNodes(), defaultFillType4, "", solidColor4, 1.0f, null, 1.0f, 1.0f, iM3803getButtKaPHkGw4, iM3813getBevelLxFBmk84, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null);
        ImageVector imageVectorBuild = ImageVector.Builder.m4024addPathoIyEayM$default(builder, b.t(a.r(8.5f, 14.1f, 5.6f, -5.6f), 1.4f, 1.4f, -5.6f, 5.6f), VectorKt.getDefaultFillType(), "", new SolidColor(companion.m3498getBlack0d7_KjU(), null), 1.0f, null, 1.0f, 1.0f, companion2.m3803getButtKaPHkGw(), companion3.m3813getBevelLxFBmk8(), 1.0f, 0.0f, 0.0f, 0.0f, 14336, null).build();
        _sportsFootball = imageVectorBuild;
        return imageVectorBuild;
    }
}
