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
import v.c;
import w.a;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\"\u0010\u0010\u0000\u001a\u0004\u0018\u00010\u0001X\u0082\u000e¢\u0006\u0002\n\u0000\"\u0015\u0010\u0002\u001a\u00020\u0001*\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"_southAmerica", "Landroidx/compose/ui/graphics/vector/ImageVector;", "SouthAmerica", "Landroidx/compose/material/icons/Icons$TwoTone;", "getSouthAmerica", "(Landroidx/compose/material/icons/Icons$TwoTone;)Landroidx/compose/ui/graphics/vector/ImageVector;", "material-icons-extended_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class SouthAmericaKt {
    private static ImageVector _southAmerica;

    public static final ImageVector getSouthAmerica(Icons.TwoTone twoTone) {
        ImageVector imageVector = _southAmerica;
        if (imageVector != null) {
            return imageVector;
        }
        ImageVector.Builder builder = new ImageVector.Builder("TwoTone.SouthAmerica", Dp.m5678constructorimpl(24.0f), Dp.m5678constructorimpl(24.0f), 24.0f, 24.0f, 0L, 0, false, 96, null);
        int defaultFillType = VectorKt.getDefaultFillType();
        Color.Companion companion = Color.INSTANCE;
        SolidColor solidColor = new SolidColor(companion.m3498getBlack0d7_KjU(), null);
        StrokeCap.Companion companion2 = StrokeCap.INSTANCE;
        int iM3803getButtKaPHkGw = companion2.m3803getButtKaPHkGw();
        StrokeJoin.Companion companion3 = StrokeJoin.INSTANCE;
        int iM3813getBevelLxFBmk8 = companion3.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderA = c.a(4.0f, 12.0f);
        pathBuilderA.curveToRelative(0.0f, -1.95f, 0.7f, -3.74f, 1.87f, -5.13f);
        pathBuilderA.lineTo(9.0f, 10.0f);
        pathBuilderA.verticalLineToRelative(1.0f);
        pathBuilderA.curveToRelative(0.0f, 1.1f, 0.9f, 2.0f, 2.0f, 2.0f);
        pathBuilderA.verticalLineToRelative(5.59f);
        pathBuilderA.curveToRelative(0.0f, 0.27f, 0.11f, 0.52f, 0.29f, 0.71f);
        pathBuilderA.lineTo(12.0f, 20.0f);
        pathBuilderA.curveTo(7.58f, 20.0f, 4.0f, 16.42f, 4.0f, 12.0f);
        a.u(pathBuilderA, 13.0f, 19.94f, 18.0f);
        pathBuilderA.lineToRelative(3.75f, -5.62f);
        pathBuilderA.curveToRelative(0.16f, -0.25f, 0.25f, -0.54f, 0.25f, -0.83f);
        pathBuilderA.verticalLineTo(10.5f);
        pathBuilderA.curveToRelative(0.0f, -0.55f, -0.45f, -1.0f, -1.0f, -1.0f);
        pathBuilderA.horizontalLineToRelative(-1.5f);
        pathBuilderA.lineToRelative(-1.4f, -1.75f);
        pathBuilderA.curveTo(12.72f, 7.28f, 12.15f, 7.0f, 11.54f, 7.0f);
        pathBuilderA.horizontalLineTo(8.0f);
        pathBuilderA.verticalLineTo(5.07f);
        pathBuilderA.curveTo(9.18f, 4.39f, 10.54f, 4.0f, 12.0f, 4.0f);
        pathBuilderA.curveToRelative(4.41f, 0.0f, 8.0f, 3.59f, 8.0f, 8.0f);
        pathBuilderA.curveTo(20.0f, 16.07f, 16.94f, 19.44f, 13.0f, 19.94f);
        pathBuilderA.close();
        ImageVector.Builder.m4024addPathoIyEayM$default(builder, pathBuilderA.getNodes(), defaultFillType, "", solidColor, 0.3f, null, 0.3f, 1.0f, iM3803getButtKaPHkGw, iM3813getBevelLxFBmk8, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null);
        int defaultFillType2 = VectorKt.getDefaultFillType();
        SolidColor solidColor2 = new SolidColor(companion.m3498getBlack0d7_KjU(), null);
        int iM3803getButtKaPHkGw2 = companion2.m3803getButtKaPHkGw();
        int iM3813getBevelLxFBmk82 = companion3.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderA2 = c.a(12.0f, 2.0f);
        pathBuilderA2.curveTo(6.48f, 2.0f, 2.0f, 6.48f, 2.0f, 12.0f);
        pathBuilderA2.curveToRelative(0.0f, 5.52f, 4.48f, 10.0f, 10.0f, 10.0f);
        pathBuilderA2.reflectiveCurveToRelative(10.0f, -4.48f, 10.0f, -10.0f);
        pathBuilderA2.curveTo(22.0f, 6.48f, 17.52f, 2.0f, 12.0f, 2.0f);
        pathBuilderA2.close();
        pathBuilderA2.moveTo(4.0f, 12.0f);
        pathBuilderA2.curveToRelative(0.0f, -1.95f, 0.7f, -3.74f, 1.87f, -5.13f);
        pathBuilderA2.lineTo(9.0f, 10.0f);
        pathBuilderA2.verticalLineToRelative(1.0f);
        pathBuilderA2.curveToRelative(0.0f, 1.1f, 0.9f, 2.0f, 2.0f, 2.0f);
        pathBuilderA2.verticalLineToRelative(5.59f);
        pathBuilderA2.curveToRelative(0.0f, 0.27f, 0.11f, 0.52f, 0.29f, 0.71f);
        pathBuilderA2.lineTo(12.0f, 20.0f);
        pathBuilderA2.curveTo(7.58f, 20.0f, 4.0f, 16.42f, 4.0f, 12.0f);
        a.u(pathBuilderA2, 13.0f, 19.94f, 18.0f);
        pathBuilderA2.lineToRelative(3.75f, -5.62f);
        pathBuilderA2.curveToRelative(0.16f, -0.25f, 0.25f, -0.54f, 0.25f, -0.83f);
        pathBuilderA2.verticalLineTo(10.5f);
        pathBuilderA2.curveToRelative(0.0f, -0.55f, -0.45f, -1.0f, -1.0f, -1.0f);
        pathBuilderA2.horizontalLineToRelative(-1.5f);
        pathBuilderA2.lineToRelative(-1.4f, -1.75f);
        pathBuilderA2.curveTo(12.72f, 7.28f, 12.15f, 7.0f, 11.54f, 7.0f);
        pathBuilderA2.horizontalLineTo(8.0f);
        pathBuilderA2.verticalLineTo(5.07f);
        pathBuilderA2.curveTo(9.18f, 4.39f, 10.54f, 4.0f, 12.0f, 4.0f);
        pathBuilderA2.curveToRelative(4.41f, 0.0f, 8.0f, 3.59f, 8.0f, 8.0f);
        pathBuilderA2.curveTo(20.0f, 16.07f, 16.94f, 19.44f, 13.0f, 19.94f);
        pathBuilderA2.close();
        ImageVector imageVectorBuild = ImageVector.Builder.m4024addPathoIyEayM$default(builder, pathBuilderA2.getNodes(), defaultFillType2, "", solidColor2, 1.0f, null, 1.0f, 1.0f, iM3803getButtKaPHkGw2, iM3813getBevelLxFBmk82, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null).build();
        _southAmerica = imageVectorBuild;
        return imageVectorBuild;
    }
}
