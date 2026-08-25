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
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\"\u0010\u0010\u0000\u001a\u0004\u0018\u00010\u0001X\u0082\u000e¢\u0006\u0002\n\u0000\"\u0015\u0010\u0002\u001a\u00020\u0001*\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"_nightsStay", "Landroidx/compose/ui/graphics/vector/ImageVector;", "NightsStay", "Landroidx/compose/material/icons/Icons$TwoTone;", "getNightsStay", "(Landroidx/compose/material/icons/Icons$TwoTone;)Landroidx/compose/ui/graphics/vector/ImageVector;", "material-icons-extended_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class NightsStayKt {
    private static ImageVector _nightsStay;

    public static final ImageVector getNightsStay(Icons.TwoTone twoTone) {
        ImageVector imageVector = _nightsStay;
        if (imageVector != null) {
            return imageVector;
        }
        ImageVector.Builder builder = new ImageVector.Builder("TwoTone.NightsStay", Dp.m5678constructorimpl(24.0f), Dp.m5678constructorimpl(24.0f), 24.0f, 24.0f, 0L, 0, false, 96, null);
        int defaultFillType = VectorKt.getDefaultFillType();
        Color.Companion companion = Color.INSTANCE;
        SolidColor solidColor = new SolidColor(companion.m3498getBlack0d7_KjU(), null);
        StrokeCap.Companion companion2 = StrokeCap.INSTANCE;
        int iM3803getButtKaPHkGw = companion2.m3803getButtKaPHkGw();
        StrokeJoin.Companion companion3 = StrokeJoin.INSTANCE;
        int iM3813getBevelLxFBmk8 = companion3.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderA = c.a(8.1f, 14.15f);
        pathBuilderA.curveTo(9.77f, 14.63f, 11.0f, 16.17f, 11.0f, 18.0f);
        pathBuilderA.curveToRelative(0.0f, 0.68f, -0.19f, 1.31f, -0.48f, 1.87f);
        pathBuilderA.curveToRelative(0.48f, 0.09f, 0.97f, 0.14f, 1.48f, 0.14f);
        pathBuilderA.curveToRelative(1.48f, 0.0f, 2.9f, -0.41f, 4.13f, -1.15f);
        pathBuilderA.curveToRelative(-2.62f, -0.92f, -5.23f, -2.82f, -6.8f, -5.86f);
        pathBuilderA.curveTo(7.74f, 9.94f, 7.78f, 7.09f, 8.29f, 4.9f);
        pathBuilderA.curveToRelative(-2.57f, 1.33f, -4.3f, 4.01f, -4.3f, 7.1f);
        pathBuilderA.curveToRelative(0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f);
        pathBuilderA.curveToRelative(0.01f, 0.0f, 0.01f, 0.0f, 0.02f, 0.0f);
        pathBuilderA.curveTo(5.66f, 12.0f, 7.18f, 12.83f, 8.1f, 14.15f);
        pathBuilderA.close();
        ImageVector.Builder.m4024addPathoIyEayM$default(builder, pathBuilderA.getNodes(), defaultFillType, "", solidColor, 0.3f, null, 0.3f, 1.0f, iM3803getButtKaPHkGw, iM3813getBevelLxFBmk8, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null);
        int defaultFillType2 = VectorKt.getDefaultFillType();
        SolidColor solidColor2 = new SolidColor(companion.m3498getBlack0d7_KjU(), null);
        int iM3803getButtKaPHkGw2 = companion2.m3803getButtKaPHkGw();
        int iM3813getBevelLxFBmk82 = companion3.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderA2 = c.a(19.78f, 17.51f);
        pathBuilderA2.curveToRelative(-2.47f, 0.0f, -6.57f, -1.33f, -8.68f, -5.43f);
        pathBuilderA2.curveTo(8.77f, 7.57f, 10.6f, 3.6f, 11.63f, 2.01f);
        pathBuilderA2.curveTo(6.27f, 2.2f, 1.98f, 6.59f, 1.98f, 12.0f);
        pathBuilderA2.curveToRelative(0.0f, 0.14f, 0.02f, 0.28f, 0.02f, 0.42f);
        pathBuilderA2.curveTo(2.61f, 12.16f, 3.28f, 12.0f, 3.98f, 12.0f);
        pathBuilderA2.curveToRelative(0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f);
        pathBuilderA2.curveToRelative(0.0f, -3.09f, 1.73f, -5.77f, 4.3f, -7.1f);
        pathBuilderA2.curveTo(7.78f, 7.09f, 7.74f, 9.94f, 9.32f, 13.0f);
        pathBuilderA2.curveToRelative(1.57f, 3.04f, 4.18f, 4.95f, 6.8f, 5.86f);
        pathBuilderA2.curveToRelative(-1.23f, 0.74f, -2.65f, 1.15f, -4.13f, 1.15f);
        pathBuilderA2.curveToRelative(-0.5f, 0.0f, -1.0f, -0.05f, -1.48f, -0.14f);
        pathBuilderA2.curveToRelative(-0.37f, 0.7f, -0.94f, 1.27f, -1.64f, 1.64f);
        pathBuilderA2.curveToRelative(0.98f, 0.32f, 2.03f, 0.5f, 3.11f, 0.5f);
        pathBuilderA2.curveToRelative(3.5f, 0.0f, 6.58f, -1.8f, 8.37f, -4.52f);
        pathBuilderA2.curveTo(20.18f, 17.5f, 19.98f, 17.51f, 19.78f, 17.51f);
        pathBuilderA2.close();
        ImageVector.Builder.m4024addPathoIyEayM$default(builder, pathBuilderA2.getNodes(), defaultFillType2, "", solidColor2, 1.0f, null, 1.0f, 1.0f, iM3803getButtKaPHkGw2, iM3813getBevelLxFBmk82, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null);
        int defaultFillType3 = VectorKt.getDefaultFillType();
        SolidColor solidColor3 = new SolidColor(companion.m3498getBlack0d7_KjU(), null);
        int iM3803getButtKaPHkGw3 = companion2.m3803getButtKaPHkGw();
        int iM3813getBevelLxFBmk83 = companion3.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderR = a.r(7.0f, 16.0f, -0.18f, 0.0f);
        pathBuilderR.curveTo(6.4f, 14.84f, 5.3f, 14.0f, 4.0f, 14.0f);
        pathBuilderR.curveToRelative(-1.66f, 0.0f, -3.0f, 1.34f, -3.0f, 3.0f);
        pathBuilderR.reflectiveCurveToRelative(1.34f, 3.0f, 3.0f, 3.0f);
        pathBuilderR.curveToRelative(0.62f, 0.0f, 2.49f, 0.0f, 3.0f, 0.0f);
        pathBuilderR.curveToRelative(1.1f, 0.0f, 2.0f, -0.9f, 2.0f, -2.0f);
        pathBuilderR.curveTo(9.0f, 16.9f, 8.1f, 16.0f, 7.0f, 16.0f);
        pathBuilderR.close();
        ImageVector imageVectorBuild = ImageVector.Builder.m4024addPathoIyEayM$default(builder, pathBuilderR.getNodes(), defaultFillType3, "", solidColor3, 1.0f, null, 1.0f, 1.0f, iM3803getButtKaPHkGw3, iM3813getBevelLxFBmk83, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null).build();
        _nightsStay = imageVectorBuild;
        return imageVectorBuild;
    }
}
