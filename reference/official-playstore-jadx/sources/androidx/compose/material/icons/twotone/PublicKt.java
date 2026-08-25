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
import v.d;
import v.e;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\"\u0010\u0010\u0000\u001a\u0004\u0018\u00010\u0001X\u0082\u000e¢\u0006\u0002\n\u0000\"\u0015\u0010\u0002\u001a\u00020\u0001*\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"_public", "Landroidx/compose/ui/graphics/vector/ImageVector;", "Public", "Landroidx/compose/material/icons/Icons$TwoTone;", "getPublic", "(Landroidx/compose/material/icons/Icons$TwoTone;)Landroidx/compose/ui/graphics/vector/ImageVector;", "material-icons-extended_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class PublicKt {
    private static ImageVector _public;

    public static final ImageVector getPublic(Icons.TwoTone twoTone) {
        ImageVector imageVector = _public;
        if (imageVector != null) {
            return imageVector;
        }
        ImageVector.Builder builder = new ImageVector.Builder("TwoTone.Public", Dp.m5678constructorimpl(24.0f), Dp.m5678constructorimpl(24.0f), 24.0f, 24.0f, 0L, 0, false, 96, null);
        int defaultFillType = VectorKt.getDefaultFillType();
        Color.Companion companion = Color.INSTANCE;
        SolidColor solidColor = new SolidColor(companion.m3498getBlack0d7_KjU(), null);
        StrokeCap.Companion companion2 = StrokeCap.INSTANCE;
        int iM3803getButtKaPHkGw = companion2.m3803getButtKaPHkGw();
        StrokeJoin.Companion companion3 = StrokeJoin.INSTANCE;
        int iM3813getBevelLxFBmk8 = companion3.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderA = e.a(14.99f, 4.59f, 5.0f);
        pathBuilderA.curveToRelative(0.0f, 1.1f, -0.9f, 2.0f, -2.0f, 2.0f);
        pathBuilderA.horizontalLineToRelative(-2.0f);
        pathBuilderA.verticalLineToRelative(2.0f);
        pathBuilderA.curveToRelative(0.0f, 0.55f, -0.45f, 1.0f, -1.0f, 1.0f);
        e.g(pathBuilderA, -2.0f, 2.0f, 6.0f);
        pathBuilderA.curveToRelative(0.55f, 0.0f, 1.0f, 0.45f, 1.0f, 1.0f);
        pathBuilderA.verticalLineToRelative(3.0f);
        pathBuilderA.horizontalLineToRelative(1.0f);
        pathBuilderA.curveToRelative(0.89f, 0.0f, 1.64f, 0.59f, 1.9f, 1.4f);
        pathBuilderA.curveTo(19.19f, 15.98f, 20.0f, 14.08f, 20.0f, 12.0f);
        pathBuilderA.curveToRelative(0.0f, -3.35f, -2.08f, -6.23f, -5.01f, -7.41f);
        d.k(pathBuilderA, 8.99f, 16.0f, -1.0f);
        pathBuilderA.lineToRelative(-4.78f, -4.78f);
        pathBuilderA.curveTo(4.08f, 10.79f, 4.0f, 11.39f, 4.0f, 12.0f);
        pathBuilderA.curveToRelative(0.0f, 4.07f, 3.06f, 7.43f, 6.99f, 7.93f);
        pathBuilderA.verticalLineTo(18.0f);
        pathBuilderA.curveToRelative(-1.1f, 0.0f, -2.0f, -0.9f, -2.0f, -2.0f);
        pathBuilderA.close();
        ImageVector.Builder.m4024addPathoIyEayM$default(builder, pathBuilderA.getNodes(), defaultFillType, "", solidColor, 0.3f, null, 0.3f, 1.0f, iM3803getButtKaPHkGw, iM3813getBevelLxFBmk8, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null);
        int defaultFillType2 = VectorKt.getDefaultFillType();
        SolidColor solidColor2 = new SolidColor(companion.m3498getBlack0d7_KjU(), null);
        int iM3803getButtKaPHkGw2 = companion2.m3803getButtKaPHkGw();
        int iM3813getBevelLxFBmk82 = companion3.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderA2 = c.a(12.0f, 2.0f);
        pathBuilderA2.curveTo(6.48f, 2.0f, 2.0f, 6.48f, 2.0f, 12.0f);
        pathBuilderA2.reflectiveCurveToRelative(4.48f, 10.0f, 10.0f, 10.0f);
        pathBuilderA2.reflectiveCurveToRelative(10.0f, -4.48f, 10.0f, -10.0f);
        pathBuilderA2.reflectiveCurveTo(17.52f, 2.0f, 12.0f, 2.0f);
        pathBuilderA2.close();
        pathBuilderA2.moveTo(10.99f, 19.93f);
        pathBuilderA2.curveTo(7.06f, 19.43f, 4.0f, 16.07f, 4.0f, 12.0f);
        pathBuilderA2.curveToRelative(0.0f, -0.61f, 0.08f, -1.21f, 0.21f, -1.78f);
        pathBuilderA2.lineTo(8.99f, 15.0f);
        pathBuilderA2.verticalLineToRelative(1.0f);
        pathBuilderA2.curveToRelative(0.0f, 1.1f, 0.9f, 2.0f, 2.0f, 2.0f);
        b.f(pathBuilderA2, 1.93f, 17.89f, 17.4f);
        pathBuilderA2.curveToRelative(-0.26f, -0.81f, -1.0f, -1.4f, -1.9f, -1.4f);
        pathBuilderA2.horizontalLineToRelative(-1.0f);
        pathBuilderA2.verticalLineToRelative(-3.0f);
        pathBuilderA2.curveToRelative(0.0f, -0.55f, -0.45f, -1.0f, -1.0f, -1.0f);
        e.g(pathBuilderA2, -6.0f, -2.0f, 2.0f);
        pathBuilderA2.curveToRelative(0.55f, 0.0f, 1.0f, -0.45f, 1.0f, -1.0f);
        pathBuilderA2.lineTo(10.99f, 7.0f);
        pathBuilderA2.horizontalLineToRelative(2.0f);
        pathBuilderA2.curveToRelative(1.1f, 0.0f, 2.0f, -0.9f, 2.0f, -2.0f);
        pathBuilderA2.verticalLineToRelative(-0.41f);
        pathBuilderA2.curveTo(17.92f, 5.77f, 20.0f, 8.65f, 20.0f, 12.0f);
        pathBuilderA2.curveToRelative(0.0f, 2.08f, -0.81f, 3.98f, -2.11f, 5.4f);
        pathBuilderA2.close();
        ImageVector imageVectorBuild = ImageVector.Builder.m4024addPathoIyEayM$default(builder, pathBuilderA2.getNodes(), defaultFillType2, "", solidColor2, 1.0f, null, 1.0f, 1.0f, iM3803getButtKaPHkGw2, iM3813getBevelLxFBmk82, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null).build();
        _public = imageVectorBuild;
        return imageVectorBuild;
    }
}
