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
import v.e;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\"\u0010\u0010\u0000\u001a\u0004\u0018\u00010\u0001X\u0082\u000e¢\u0006\u0002\n\u0000\"\u0015\u0010\u0002\u001a\u00020\u0001*\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"_storm", "Landroidx/compose/ui/graphics/vector/ImageVector;", "Storm", "Landroidx/compose/material/icons/Icons$TwoTone;", "getStorm", "(Landroidx/compose/material/icons/Icons$TwoTone;)Landroidx/compose/ui/graphics/vector/ImageVector;", "material-icons-extended_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class StormKt {
    private static ImageVector _storm;

    public static final ImageVector getStorm(Icons.TwoTone twoTone) {
        ImageVector imageVector = _storm;
        if (imageVector != null) {
            return imageVector;
        }
        ImageVector.Builder builder = new ImageVector.Builder("TwoTone.Storm", Dp.m5678constructorimpl(24.0f), Dp.m5678constructorimpl(24.0f), 24.0f, 24.0f, 0L, 0, false, 96, null);
        int defaultFillType = VectorKt.getDefaultFillType();
        Color.Companion companion = Color.INSTANCE;
        SolidColor solidColor = new SolidColor(companion.m3498getBlack0d7_KjU(), null);
        StrokeCap.Companion companion2 = StrokeCap.INSTANCE;
        int iM3803getButtKaPHkGw = companion2.m3803getButtKaPHkGw();
        StrokeJoin.Companion companion3 = StrokeJoin.INSTANCE;
        int iM3813getBevelLxFBmk8 = companion3.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderB = a.b(12.0f, 12.0f, -2.0f, 0.0f);
        pathBuilderB.arcToRelative(2.0f, 2.0f, 0.0f, true, true, 4.0f, 0.0f);
        pathBuilderB.arcToRelative(2.0f, 2.0f, 0.0f, true, true, -4.0f, 0.0f);
        ImageVector.Builder.m4024addPathoIyEayM$default(builder, pathBuilderB.getNodes(), defaultFillType, "", solidColor, 0.3f, null, 0.3f, 1.0f, iM3803getButtKaPHkGw, iM3813getBevelLxFBmk8, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null);
        int defaultFillType2 = VectorKt.getDefaultFillType();
        SolidColor solidColor2 = new SolidColor(companion.m3498getBlack0d7_KjU(), null);
        int iM3803getButtKaPHkGw2 = companion2.m3803getButtKaPHkGw();
        int iM3813getBevelLxFBmk82 = companion3.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderA = c.a(17.2f, 9.0f);
        pathBuilderA.curveTo(15.54f, 6.13f, 11.86f, 5.15f, 9.0f, 6.8f);
        pathBuilderA.curveToRelative(-2.67f, 1.54f, -3.7f, 4.84f, -2.5f, 7.6f);
        pathBuilderA.curveToRelative(0.09f, 0.2f, 0.19f, 0.4f, 0.3f, 0.6f);
        pathBuilderA.curveToRelative(1.66f, 2.87f, 5.33f, 3.85f, 8.2f, 2.2f);
        pathBuilderA.curveToRelative(2.67f, -1.54f, 3.7f, -4.84f, 2.5f, -7.6f);
        pathBuilderA.curveTo(17.41f, 9.4f, 17.31f, 9.2f, 17.2f, 9.0f);
        pathBuilderA.close();
        pathBuilderA.moveTo(12.0f, 16.0f);
        pathBuilderA.curveToRelative(-2.21f, 0.0f, -4.0f, -1.79f, -4.0f, -4.0f);
        pathBuilderA.reflectiveCurveToRelative(1.79f, -4.0f, 4.0f, -4.0f);
        pathBuilderA.reflectiveCurveToRelative(4.0f, 1.79f, 4.0f, 4.0f);
        ImageVector.Builder.m4024addPathoIyEayM$default(builder, e.u(pathBuilderA, 14.21f, 16.0f, 12.0f, 16.0f), defaultFillType2, "", solidColor2, 0.3f, null, 0.3f, 1.0f, iM3803getButtKaPHkGw2, iM3813getBevelLxFBmk82, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null);
        int defaultFillType3 = VectorKt.getDefaultFillType();
        SolidColor solidColor3 = new SolidColor(companion.m3498getBlack0d7_KjU(), null);
        int iM3803getButtKaPHkGw3 = companion2.m3803getButtKaPHkGw();
        int iM3813getBevelLxFBmk83 = companion3.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderA2 = c.a(12.0f, 8.0f);
        pathBuilderA2.curveToRelative(-2.21f, 0.0f, -4.0f, 1.79f, -4.0f, 4.0f);
        pathBuilderA2.reflectiveCurveToRelative(1.79f, 4.0f, 4.0f, 4.0f);
        pathBuilderA2.reflectiveCurveToRelative(4.0f, -1.79f, 4.0f, -4.0f);
        pathBuilderA2.reflectiveCurveTo(14.21f, 8.0f, 12.0f, 8.0f);
        pathBuilderA2.close();
        pathBuilderA2.moveTo(12.0f, 14.0f);
        pathBuilderA2.curveToRelative(-1.1f, 0.0f, -2.0f, -0.9f, -2.0f, -2.0f);
        pathBuilderA2.reflectiveCurveToRelative(0.9f, -2.0f, 2.0f, -2.0f);
        pathBuilderA2.reflectiveCurveToRelative(2.0f, 0.9f, 2.0f, 2.0f);
        ImageVector.Builder.m4024addPathoIyEayM$default(builder, e.u(pathBuilderA2, 13.1f, 14.0f, 12.0f, 14.0f), defaultFillType3, "", solidColor3, 1.0f, null, 1.0f, 1.0f, iM3803getButtKaPHkGw3, iM3813getBevelLxFBmk83, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null);
        int defaultFillType4 = VectorKt.getDefaultFillType();
        SolidColor solidColor4 = new SolidColor(companion.m3498getBlack0d7_KjU(), null);
        int iM3803getButtKaPHkGw4 = companion2.m3803getButtKaPHkGw();
        int iM3813getBevelLxFBmk84 = companion3.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderA3 = c.a(18.93f, 8.0f);
        pathBuilderA3.curveTo(16.72f, 4.18f, 11.82f, 2.87f, 8.0f, 5.07f);
        pathBuilderA3.curveToRelative(-1.41f, 0.82f, -2.48f, 2.0f, -3.16f, 3.37f);
        pathBuilderA3.curveTo(4.71f, 6.24f, 5.06f, 4.04f, 5.86f, 2.0f);
        pathBuilderA3.horizontalLineTo(3.74f);
        pathBuilderA3.curveTo(2.2f, 6.49f, 2.52f, 11.58f, 5.07f, 16.0f);
        pathBuilderA3.curveToRelative(1.1f, 1.91f, 2.88f, 3.19f, 4.86f, 3.72f);
        pathBuilderA3.curveToRelative(1.98f, 0.53f, 4.16f, 0.31f, 6.07f, -0.79f);
        pathBuilderA3.curveToRelative(1.41f, -0.82f, 2.48f, -2.0f, 3.16f, -3.37f);
        pathBuilderA3.curveToRelative(0.13f, 2.2f, -0.21f, 4.4f, -1.01f, 6.44f);
        pathBuilderA3.horizontalLineToRelative(2.11f);
        pathBuilderA3.curveTo(21.79f, 17.51f, 21.48f, 12.42f, 18.93f, 8.0f);
        pathBuilderA3.close();
        pathBuilderA3.moveTo(15.0f, 17.2f);
        pathBuilderA3.curveToRelative(-2.87f, 1.65f, -6.54f, 0.67f, -8.2f, -2.2f);
        pathBuilderA3.curveToRelative(-0.11f, -0.2f, -0.21f, -0.4f, -0.3f, -0.6f);
        pathBuilderA3.curveTo(5.3f, 11.64f, 6.33f, 8.34f, 9.0f, 6.8f);
        pathBuilderA3.curveToRelative(2.86f, -1.65f, 6.54f, -0.67f, 8.2f, 2.2f);
        pathBuilderA3.curveToRelative(0.11f, 0.2f, 0.21f, 0.4f, 0.3f, 0.6f);
        pathBuilderA3.curveTo(18.7f, 12.36f, 17.67f, 15.66f, 15.0f, 17.2f);
        pathBuilderA3.close();
        ImageVector imageVectorBuild = ImageVector.Builder.m4024addPathoIyEayM$default(builder, pathBuilderA3.getNodes(), defaultFillType4, "", solidColor4, 1.0f, null, 1.0f, 1.0f, iM3803getButtKaPHkGw4, iM3813getBevelLxFBmk84, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null).build();
        _storm = imageVectorBuild;
        return imageVectorBuild;
    }
}
