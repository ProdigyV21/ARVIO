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
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\"\u0010\u0010\u0000\u001a\u0004\u0018\u00010\u0001X\u0082\u000e¢\u0006\u0002\n\u0000\"\u0015\u0010\u0002\u001a\u00020\u0001*\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"_flood", "Landroidx/compose/ui/graphics/vector/ImageVector;", "Flood", "Landroidx/compose/material/icons/Icons$TwoTone;", "getFlood", "(Landroidx/compose/material/icons/Icons$TwoTone;)Landroidx/compose/ui/graphics/vector/ImageVector;", "material-icons-extended_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class FloodKt {
    private static ImageVector _flood;

    public static final ImageVector getFlood(Icons.TwoTone twoTone) {
        ImageVector imageVector = _flood;
        if (imageVector != null) {
            return imageVector;
        }
        ImageVector.Builder builder = new ImageVector.Builder("TwoTone.Flood", Dp.m5678constructorimpl(24.0f), Dp.m5678constructorimpl(24.0f), 24.0f, 24.0f, 0L, 0, false, 96, null);
        int defaultFillType = VectorKt.getDefaultFillType();
        Color.Companion companion = Color.INSTANCE;
        SolidColor solidColor = new SolidColor(companion.m3498getBlack0d7_KjU(), null);
        StrokeCap.Companion companion2 = StrokeCap.INSTANCE;
        int iM3803getButtKaPHkGw = companion2.m3803getButtKaPHkGw();
        StrokeJoin.Companion companion3 = StrokeJoin.INSTANCE;
        int iM3813getBevelLxFBmk8 = companion3.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderA = c.a(8.66f, 15.5f);
        pathBuilderA.curveToRelative(1.01f, 0.0f, 1.3f, -0.65f, 2.42f, -0.9f);
        pathBuilderA.lineToRelative(-0.91f, -3.39f);
        pathBuilderA.lineToRelative(3.86f, -1.04f);
        pathBuilderA.lineToRelative(1.42f, 5.31f);
        pathBuilderA.curveToRelative(1.03f, -0.07f, 1.3f, -0.85f, 2.85f, -0.96f);
        pathBuilderA.lineTo(16.16f, 6.5f);
        pathBuilderA.lineToRelative(-5.74f, -2.09f);
        pathBuilderA.lineTo(6.5f, 9.09f);
        pathBuilderA.lineToRelative(1.7f, 6.36f);
        pathBuilderA.curveTo(8.33f, 15.48f, 8.48f, 15.5f, 8.66f, 15.5f);
        pathBuilderA.close();
        ImageVector.Builder.m4024addPathoIyEayM$default(builder, pathBuilderA.getNodes(), defaultFillType, "", solidColor, 0.3f, null, 0.3f, 1.0f, iM3803getButtKaPHkGw, iM3813getBevelLxFBmk8, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null);
        int defaultFillType2 = VectorKt.getDefaultFillType();
        SolidColor solidColor2 = new SolidColor(companion.m3498getBlack0d7_KjU(), null);
        int iM3803getButtKaPHkGw2 = companion2.m3803getButtKaPHkGw();
        int iM3813getBevelLxFBmk82 = companion3.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderA2 = c.a(18.67f, 19.0f);
        pathBuilderA2.curveToRelative(-1.95f, 0.0f, -2.09f, 1.0f, -3.33f, 1.0f);
        pathBuilderA2.curveToRelative(-1.19f, 0.0f, -1.42f, -1.0f, -3.33f, -1.0f);
        pathBuilderA2.curveToRelative(-1.95f, 0.0f, -2.1f, 1.0f, -3.34f, 1.0f);
        pathBuilderA2.curveToRelative(-1.24f, 0.0f, -1.38f, -1.0f, -3.33f, -1.0f);
        pathBuilderA2.curveToRelative(-1.95f, 0.0f, -2.1f, 1.0f, -3.34f, 1.0f);
        pathBuilderA2.verticalLineToRelative(2.0f);
        pathBuilderA2.curveToRelative(1.95f, 0.0f, 2.11f, -1.0f, 3.34f, -1.0f);
        pathBuilderA2.curveToRelative(1.24f, 0.0f, 1.38f, 1.0f, 3.33f, 1.0f);
        pathBuilderA2.curveToRelative(1.95f, 0.0f, 2.1f, -1.0f, 3.34f, -1.0f);
        pathBuilderA2.curveToRelative(1.22f, 0.0f, 1.4f, 1.0f, 3.33f, 1.0f);
        pathBuilderA2.curveToRelative(1.93f, 0.0f, 2.1f, -1.0f, 3.33f, -1.0f);
        pathBuilderA2.curveToRelative(1.22f, 0.0f, 1.4f, 1.0f, 3.33f, 1.0f);
        pathBuilderA2.verticalLineToRelative(-2.0f);
        pathBuilderA2.curveTo(20.76f, 20.0f, 20.62f, 19.0f, 18.67f, 19.0f);
        pathBuilderA2.close();
        ImageVector.Builder.m4024addPathoIyEayM$default(builder, pathBuilderA2.getNodes(), defaultFillType2, "", solidColor2, 1.0f, null, 1.0f, 1.0f, iM3803getButtKaPHkGw2, iM3813getBevelLxFBmk82, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null);
        int defaultFillType3 = VectorKt.getDefaultFillType();
        SolidColor solidColor3 = new SolidColor(companion.m3498getBlack0d7_KjU(), null);
        int iM3803getButtKaPHkGw3 = companion2.m3803getButtKaPHkGw();
        int iM3813getBevelLxFBmk83 = companion3.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderA3 = c.a(8.68f, 17.5f);
        pathBuilderA3.curveToRelative(1.95f, 0.0f, 2.09f, -1.0f, 3.33f, -1.0f);
        pathBuilderA3.curveToRelative(1.19f, 0.0f, 1.42f, 1.0f, 3.33f, 1.0f);
        pathBuilderA3.curveToRelative(1.95f, 0.0f, 2.09f, -1.0f, 3.33f, -1.0f);
        pathBuilderA3.curveToRelative(1.19f, 0.0f, 1.4f, 0.98f, 3.31f, 1.0f);
        pathBuilderA3.verticalLineToRelative(-2.0f);
        pathBuilderA3.curveToRelative(-0.63f, 0.0f, -1.0f, -0.28f, -1.48f, -0.55f);
        pathBuilderA3.lineToRelative(-2.02f, -7.53f);
        pathBuilderA3.lineToRelative(2.09f, 0.85f);
        pathBuilderA3.lineToRelative(0.74f, -1.86f);
        pathBuilderA3.lineTo(9.78f, 2.0f);
        pathBuilderA3.lineTo(2.0f, 11.61f);
        pathBuilderA3.lineToRelative(1.57f, 1.23f);
        pathBuilderA3.lineToRelative(1.39f, -1.78f);
        pathBuilderA3.lineToRelative(0.93f, 3.48f);
        pathBuilderA3.curveToRelative(-0.18f, -0.02f, -0.35f, -0.05f, -0.56f, -0.05f);
        pathBuilderA3.curveToRelative(-1.95f, 0.0f, -2.09f, 1.0f, -3.33f, 1.0f);
        pathBuilderA3.verticalLineToRelative(2.0f);
        pathBuilderA3.curveToRelative(1.9f, 0.0f, 2.17f, -1.0f, 3.35f, -1.0f);
        pathBuilderA3.curveTo(6.54f, 16.5f, 6.77f, 17.5f, 8.68f, 17.5f);
        a.z(pathBuilderA3, 10.42f, 4.41f, 5.74f, 2.09f);
        pathBuilderA3.lineToRelative(2.15f, 8.02f);
        pathBuilderA3.curveToRelative(-1.54f, 0.11f, -1.82f, 0.89f, -2.85f, 0.96f);
        pathBuilderA3.lineToRelative(-1.42f, -5.31f);
        pathBuilderA3.lineToRelative(-3.86f, 1.04f);
        pathBuilderA3.lineToRelative(0.91f, 3.39f);
        pathBuilderA3.curveToRelative(-1.12f, 0.25f, -1.41f, 0.9f, -2.42f, 0.9f);
        pathBuilderA3.curveToRelative(-0.18f, 0.0f, -0.33f, -0.02f, -0.45f, -0.05f);
        ImageVector imageVectorBuild = ImageVector.Builder.m4024addPathoIyEayM$default(builder, a.f(pathBuilderA3, 6.5f, 9.09f, 10.42f, 4.41f), defaultFillType3, "", solidColor3, 1.0f, null, 1.0f, 1.0f, iM3803getButtKaPHkGw3, iM3813getBevelLxFBmk83, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null).build();
        _flood = imageVectorBuild;
        return imageVectorBuild;
    }
}
