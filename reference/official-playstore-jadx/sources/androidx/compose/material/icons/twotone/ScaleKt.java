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
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\"\u0010\u0010\u0000\u001a\u0004\u0018\u00010\u0001X\u0082\u000e¢\u0006\u0002\n\u0000\"\u0015\u0010\u0002\u001a\u00020\u0001*\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"_scale", "Landroidx/compose/ui/graphics/vector/ImageVector;", "Scale", "Landroidx/compose/material/icons/Icons$TwoTone;", "getScale", "(Landroidx/compose/material/icons/Icons$TwoTone;)Landroidx/compose/ui/graphics/vector/ImageVector;", "material-icons-extended_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class ScaleKt {
    private static ImageVector _scale;

    public static final ImageVector getScale(Icons.TwoTone twoTone) {
        ImageVector imageVector = _scale;
        if (imageVector != null) {
            return imageVector;
        }
        ImageVector.Builder builder = new ImageVector.Builder("TwoTone.Scale", Dp.m5678constructorimpl(24.0f), Dp.m5678constructorimpl(24.0f), 24.0f, 24.0f, 0L, 0, false, 96, null);
        int defaultFillType = VectorKt.getDefaultFillType();
        Color.Companion companion = Color.INSTANCE;
        SolidColor solidColor = new SolidColor(companion.m3498getBlack0d7_KjU(), null);
        StrokeCap.Companion companion2 = StrokeCap.INSTANCE;
        int iM3803getButtKaPHkGw = companion2.m3803getButtKaPHkGw();
        StrokeJoin.Companion companion3 = StrokeJoin.INSTANCE;
        int iM3813getBevelLxFBmk8 = companion3.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderA = c.a(18.87f, 4.0f);
        pathBuilderA.curveTo(17.5f, 5.19f, 15.0f, 6.12f, 12.0f, 6.12f);
        pathBuilderA.curveTo(9.0f, 6.12f, 6.5f, 5.19f, 5.13f, 4.0f);
        ImageVector.Builder.m4024addPathoIyEayM$default(builder, a.l(pathBuilderA, 18.87f), defaultFillType, "", solidColor, 0.3f, null, 0.3f, 1.0f, iM3803getButtKaPHkGw, iM3813getBevelLxFBmk8, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null);
        int defaultFillType2 = VectorKt.getDefaultFillType();
        SolidColor solidColor2 = new SolidColor(companion.m3498getBlack0d7_KjU(), null);
        int iM3803getButtKaPHkGw2 = companion2.m3803getButtKaPHkGw();
        int iM3813getBevelLxFBmk82 = companion3.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderA2 = e.a(14.0f, 11.0f, 8.0f);
        pathBuilderA2.curveToRelative(4.56f, -0.58f, 8.0f, -3.1f, 8.0f, -6.0f);
        pathBuilderA2.horizontalLineTo(2.0f);
        pathBuilderA2.curveToRelative(0.0f, 2.9f, 3.44f, 5.42f, 8.0f, 6.0f);
        pathBuilderA2.lineToRelative(0.0f, 3.0f);
        pathBuilderA2.curveToRelative(-3.68f, 0.73f, -8.0f, 3.61f, -8.0f, 11.0f);
        a0.a.i(pathBuilderA2, 6.0f, -2.0f, 4.13f);
        pathBuilderA2.curveToRelative(0.93f, -6.83f, 6.65f, -7.2f, 7.87f, -7.2f);
        pathBuilderA2.reflectiveCurveToRelative(6.94f, 0.37f, 7.87f, 7.2f);
        a.n(pathBuilderA2, 16.0f, 2.0f, 6.0f);
        pathBuilderA2.curveTo(22.0f, 14.61f, 17.68f, 11.73f, 14.0f, 11.0f);
        pathBuilderA2.close();
        pathBuilderA2.moveTo(18.87f, 4.0f);
        pathBuilderA2.curveTo(17.5f, 5.19f, 15.0f, 6.12f, 12.0f, 6.12f);
        pathBuilderA2.curveTo(9.0f, 6.12f, 6.5f, 5.19f, 5.13f, 4.0f);
        a.x(pathBuilderA2, 18.87f, 12.0f, 22.0f);
        pathBuilderA2.curveToRelative(-1.1f, 0.0f, -2.0f, -0.9f, -2.0f, -2.0f);
        pathBuilderA2.curveToRelative(0.0f, -0.55f, 0.22f, -1.05f, 0.59f, -1.41f);
        pathBuilderA2.curveTo(11.39f, 17.79f, 16.0f, 16.0f, 16.0f, 16.0f);
        pathBuilderA2.reflectiveCurveToRelative(-1.79f, 4.61f, -2.59f, 5.41f);
        pathBuilderA2.curveTo(13.05f, 21.78f, 12.55f, 22.0f, 12.0f, 22.0f);
        pathBuilderA2.close();
        ImageVector imageVectorBuild = ImageVector.Builder.m4024addPathoIyEayM$default(builder, pathBuilderA2.getNodes(), defaultFillType2, "", solidColor2, 1.0f, null, 1.0f, 1.0f, iM3803getButtKaPHkGw2, iM3813getBevelLxFBmk82, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null).build();
        _scale = imageVectorBuild;
        return imageVectorBuild;
    }
}
