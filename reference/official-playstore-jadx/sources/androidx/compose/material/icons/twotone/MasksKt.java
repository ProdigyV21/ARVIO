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
import v.b;
import v.c;
import v.f;
import w.a;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\"\u0010\u0010\u0000\u001a\u0004\u0018\u00010\u0001X\u0082\u000e¢\u0006\u0002\n\u0000\"\u0015\u0010\u0002\u001a\u00020\u0001*\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"_masks", "Landroidx/compose/ui/graphics/vector/ImageVector;", "Masks", "Landroidx/compose/material/icons/Icons$TwoTone;", "getMasks", "(Landroidx/compose/material/icons/Icons$TwoTone;)Landroidx/compose/ui/graphics/vector/ImageVector;", "material-icons-extended_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class MasksKt {
    private static ImageVector _masks;

    public static final ImageVector getMasks(Icons.TwoTone twoTone) {
        ImageVector imageVector = _masks;
        if (imageVector != null) {
            return imageVector;
        }
        ImageVector.Builder builder = new ImageVector.Builder("TwoTone.Masks", Dp.m5678constructorimpl(24.0f), Dp.m5678constructorimpl(24.0f), 24.0f, 24.0f, 0L, 0, false, 96, null);
        int defaultFillType = VectorKt.getDefaultFillType();
        Color.Companion companion = Color.INSTANCE;
        SolidColor solidColor = new SolidColor(companion.m3498getBlack0d7_KjU(), null);
        StrokeCap.Companion companion2 = StrokeCap.INSTANCE;
        int iM3803getButtKaPHkGw = companion2.m3803getButtKaPHkGw();
        StrokeJoin.Companion companion3 = StrokeJoin.INSTANCE;
        int iM3813getBevelLxFBmk8 = companion3.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderA = c.a(14.34f, 8.78f);
        pathBuilderA.curveTo(13.52f, 8.33f, 12.93f, 8.0f, 12.0f, 8.0f);
        pathBuilderA.curveToRelative(-0.93f, 0.0f, -1.52f, 0.33f, -2.34f, 0.77f);
        pathBuilderA.curveTo(8.95f, 9.16f, 8.12f, 9.62f, 7.0f, 9.85f);
        pathBuilderA.verticalLineToRelative(1.65f);
        pathBuilderA.curveToRelative(0.0f, 2.76f, 2.24f, 5.0f, 5.0f, 5.0f);
        pathBuilderA.reflectiveCurveToRelative(5.0f, -2.24f, 5.0f, -5.0f);
        pathBuilderA.verticalLineTo(9.85f);
        pathBuilderA.curveTo(15.88f, 9.62f, 15.05f, 9.16f, 14.34f, 8.78f);
        pathBuilderA.close();
        pathBuilderA.moveTo(15.0f, 11.25f);
        pathBuilderA.curveToRelative(-0.75f, -0.21f, -1.26f, -0.51f, -1.71f, -0.78f);
        pathBuilderA.curveTo(12.83f, 10.2f, 12.49f, 10.0f, 12.0f, 10.0f);
        pathBuilderA.curveToRelative(-0.49f, 0.0f, -0.84f, 0.2f, -1.31f, 0.48f);
        pathBuilderA.curveToRelative(-0.44f, 0.26f, -0.96f, 0.56f, -1.69f, 0.76f);
        pathBuilderA.verticalLineTo(10.2f);
        pathBuilderA.curveToRelative(0.48f, -0.17f, 0.84f, -0.38f, 1.18f, -0.58f);
        pathBuilderA.curveTo(10.72f, 9.3f, 11.23f, 9.0f, 12.0f, 9.0f);
        pathBuilderA.reflectiveCurveToRelative(1.27f, 0.3f, 1.8f, 0.62f);
        pathBuilderA.curveToRelative(0.34f, 0.2f, 0.71f, 0.42f, 1.2f, 0.59f);
        ImageVector.Builder.m4024addPathoIyEayM$default(builder, b.i(pathBuilderA, 11.25f), defaultFillType, "", solidColor, 0.3f, null, 0.3f, 1.0f, iM3803getButtKaPHkGw, iM3813getBevelLxFBmk8, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null);
        int defaultFillType2 = VectorKt.getDefaultFillType();
        SolidColor solidColor2 = new SolidColor(companion.m3498getBlack0d7_KjU(), null);
        int iM3803getButtKaPHkGw2 = companion2.m3803getButtKaPHkGw();
        int iM3813getBevelLxFBmk82 = companion3.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderA2 = c.a(19.5f, 6.0f);
        pathBuilderA2.curveToRelative(-1.31f, 0.0f, -2.37f, 1.01f, -2.48f, 2.3f);
        pathBuilderA2.curveTo(15.14f, 7.8f, 14.18f, 6.5f, 12.0f, 6.5f);
        pathBuilderA2.curveToRelative(-2.19f, 0.0f, -3.14f, 1.3f, -5.02f, 1.8f);
        pathBuilderA2.curveTo(6.87f, 7.02f, 5.81f, 6.0f, 4.5f, 6.0f);
        pathBuilderA2.curveTo(3.12f, 6.0f, 2.0f, 7.12f, 2.0f, 8.5f);
        pathBuilderA2.verticalLineTo(9.0f);
        pathBuilderA2.curveToRelative(0.0f, 6.0f, 3.6f, 7.81f, 6.52f, 7.98f);
        pathBuilderA2.curveTo(9.53f, 17.62f, 10.72f, 18.0f, 12.0f, 18.0f);
        pathBuilderA2.reflectiveCurveToRelative(2.47f, -0.38f, 3.48f, -1.02f);
        pathBuilderA2.curveTo(18.4f, 16.81f, 22.0f, 15.0f, 22.0f, 9.0f);
        pathBuilderA2.verticalLineTo(8.5f);
        pathBuilderA2.curveTo(22.0f, 7.12f, 20.88f, 6.0f, 19.5f, 6.0f);
        a.u(pathBuilderA2, 3.5f, 9.0f, 8.5f);
        pathBuilderA2.curveToRelative(0.0f, -0.55f, 0.45f, -1.0f, 1.0f, -1.0f);
        pathBuilderA2.reflectiveCurveToRelative(1.0f, 0.45f, 1.0f, 1.0f);
        pathBuilderA2.verticalLineToRelative(3.0f);
        pathBuilderA2.curveToRelative(0.0f, 1.28f, 0.38f, 2.47f, 1.01f, 3.48f);
        pathBuilderA2.curveTo(4.99f, 14.27f, 3.5f, 12.65f, 3.5f, 9.0f);
        a.u(pathBuilderA2, 7.0f, 11.5f, 9.85f);
        pathBuilderA2.curveToRelative(1.12f, -0.23f, 1.95f, -0.69f, 2.66f, -1.08f);
        pathBuilderA2.curveTo(10.48f, 8.33f, 11.07f, 8.0f, 12.0f, 8.0f);
        pathBuilderA2.curveToRelative(0.93f, 0.0f, 1.52f, 0.33f, 2.34f, 0.78f);
        pathBuilderA2.curveToRelative(0.71f, 0.39f, 1.54f, 0.84f, 2.66f, 1.08f);
        pathBuilderA2.verticalLineToRelative(1.65f);
        pathBuilderA2.curveToRelative(0.0f, 2.76f, -2.24f, 5.0f, -5.0f, 5.0f);
        pathBuilderA2.reflectiveCurveTo(7.0f, 14.26f, 7.0f, 11.5f);
        pathBuilderA2.close();
        pathBuilderA2.moveTo(20.5f, 9.0f);
        pathBuilderA2.curveToRelative(0.0f, 3.65f, -1.49f, 5.27f, -3.01f, 5.98f);
        pathBuilderA2.curveToRelative(0.64f, -1.01f, 1.01f, -2.2f, 1.01f, -3.48f);
        pathBuilderA2.verticalLineToRelative(-3.0f);
        pathBuilderA2.curveToRelative(0.0f, -0.55f, 0.45f, -1.0f, 1.0f, -1.0f);
        pathBuilderA2.reflectiveCurveToRelative(1.0f, 0.45f, 1.0f, 1.0f);
        f.r(pathBuilderA2, 9.0f, 10.69f, 10.48f);
        pathBuilderA2.curveToRelative(-0.44f, 0.26f, -0.96f, 0.56f, -1.69f, 0.76f);
        pathBuilderA2.verticalLineTo(10.2f);
        pathBuilderA2.curveToRelative(0.48f, -0.17f, 0.84f, -0.38f, 1.18f, -0.58f);
        pathBuilderA2.curveTo(10.72f, 9.3f, 11.23f, 9.0f, 12.0f, 9.0f);
        pathBuilderA2.reflectiveCurveToRelative(1.27f, 0.3f, 1.8f, 0.62f);
        pathBuilderA2.curveToRelative(0.34f, 0.2f, 0.71f, 0.42f, 1.2f, 0.59f);
        pathBuilderA2.verticalLineToRelative(1.04f);
        pathBuilderA2.curveToRelative(-0.75f, -0.21f, -1.26f, -0.51f, -1.71f, -0.78f);
        pathBuilderA2.curveTo(12.83f, 10.2f, 12.49f, 10.0f, 12.0f, 10.0f);
        pathBuilderA2.curveTo(11.51f, 10.0f, 11.16f, 10.2f, 10.69f, 10.48f);
        pathBuilderA2.close();
        ImageVector imageVectorBuild = ImageVector.Builder.m4024addPathoIyEayM$default(builder, pathBuilderA2.getNodes(), defaultFillType2, "", solidColor2, 1.0f, null, 1.0f, 1.0f, iM3803getButtKaPHkGw2, iM3813getBevelLxFBmk82, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null).build();
        _masks = imageVectorBuild;
        return imageVectorBuild;
    }
}
