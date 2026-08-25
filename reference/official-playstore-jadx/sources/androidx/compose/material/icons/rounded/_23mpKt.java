package androidx.compose.material.icons.rounded;

import a0.b;
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
import v.e;
import v.f;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\"\u0010\u0010\u0000\u001a\u0004\u0018\u00010\u0001X\u0082\u000e¢\u0006\u0002\n\u0000\"\u0015\u0010\u0002\u001a\u00020\u0001*\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"__23mp", "Landroidx/compose/ui/graphics/vector/ImageVector;", "_23mp", "Landroidx/compose/material/icons/Icons$Rounded;", "get_23mp", "(Landroidx/compose/material/icons/Icons$Rounded;)Landroidx/compose/ui/graphics/vector/ImageVector;", "material-icons-extended_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class _23mpKt {
    private static ImageVector __23mp;

    public static final ImageVector get_23mp(Icons.Rounded rounded) {
        ImageVector imageVector = __23mp;
        if (imageVector != null) {
            return imageVector;
        }
        ImageVector.Builder builder = new ImageVector.Builder("Rounded._23mp", Dp.m5678constructorimpl(24.0f), Dp.m5678constructorimpl(24.0f), 24.0f, 24.0f, 0L, 0, false, 96, null);
        int defaultFillType = VectorKt.getDefaultFillType();
        Color.Companion companion = Color.INSTANCE;
        SolidColor solidColor = new SolidColor(companion.m3498getBlack0d7_KjU(), null);
        StrokeCap.Companion companion2 = StrokeCap.INSTANCE;
        int iM3803getButtKaPHkGw = companion2.m3803getButtKaPHkGw();
        StrokeJoin.Companion companion3 = StrokeJoin.INSTANCE;
        int iM3813getBevelLxFBmk8 = companion3.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderQ = a.q(19.0f, 3.0f, 5.0f);
        pathBuilderQ.curveTo(3.9f, 3.0f, 3.0f, 3.9f, 3.0f, 5.0f);
        pathBuilderQ.verticalLineToRelative(14.0f);
        pathBuilderQ.curveToRelative(0.0f, 1.1f, 0.9f, 2.0f, 2.0f, 2.0f);
        pathBuilderQ.horizontalLineToRelative(14.0f);
        pathBuilderQ.curveToRelative(1.1f, 0.0f, 2.0f, -0.9f, 2.0f, -2.0f);
        pathBuilderQ.verticalLineTo(5.0f);
        pathBuilderQ.curveTo(21.0f, 3.9f, 20.1f, 3.0f, 19.0f, 3.0f);
        pathBuilderQ.close();
        pathBuilderQ.moveTo(6.5f, 9.0f);
        pathBuilderQ.curveToRelative(0.0f, -0.55f, 0.45f, -1.0f, 1.0f, -1.0f);
        b.w(pathBuilderQ, 2.0f, 7.0f, 7.25f);
        pathBuilderQ.curveTo(6.84f, 7.0f, 6.5f, 6.66f, 6.5f, 6.25f);
        pathBuilderQ.reflectiveCurveTo(6.84f, 5.5f, 7.25f, 5.5f);
        pathBuilderQ.horizontalLineTo(10.0f);
        pathBuilderQ.curveToRelative(0.55f, 0.0f, 1.0f, 0.45f, 1.0f, 1.0f);
        pathBuilderQ.verticalLineTo(8.0f);
        pathBuilderQ.curveToRelative(0.0f, 0.55f, -0.45f, 1.0f, -1.0f, 1.0f);
        a.n(pathBuilderQ, 8.0f, 1.0f, 2.25f);
        pathBuilderQ.curveToRelative(0.41f, 0.0f, 0.75f, 0.34f, 0.75f, 0.75f);
        pathBuilderQ.reflectiveCurveToRelative(-0.34f, 0.75f, -0.75f, 0.75f);
        pathBuilderQ.horizontalLineTo(7.5f);
        pathBuilderQ.curveToRelative(-0.55f, 0.0f, -1.0f, -0.45f, -1.0f, -1.0f);
        f.r(pathBuilderQ, 9.0f, 12.5f, 17.75f);
        pathBuilderQ.curveToRelative(0.0f, 0.41f, -0.34f, 0.75f, -0.75f, 0.75f);
        pathBuilderQ.reflectiveCurveTo(11.0f, 18.16f, 11.0f, 17.75f);
        w.a.j(pathBuilderQ, 14.0f, -1.0f, 2.25f);
        pathBuilderQ.curveTo(10.0f, 16.66f, 9.66f, 17.0f, 9.25f, 17.0f);
        pathBuilderQ.reflectiveCurveTo(8.5f, 16.66f, 8.5f, 16.25f);
        w.a.j(pathBuilderQ, 14.0f, -1.0f, 3.75f);
        pathBuilderQ.curveToRelative(0.0f, 0.41f, -0.34f, 0.75f, -0.75f, 0.75f);
        pathBuilderQ.reflectiveCurveTo(6.0f, 18.16f, 6.0f, 17.75f);
        pathBuilderQ.verticalLineTo(13.5f);
        pathBuilderQ.curveToRelative(0.0f, -0.55f, 0.45f, -1.0f, 1.0f, -1.0f);
        pathBuilderQ.horizontalLineToRelative(4.5f);
        pathBuilderQ.curveToRelative(0.55f, 0.0f, 1.0f, 0.45f, 1.0f, 1.0f);
        f.r(pathBuilderQ, 17.75f, 13.0f, 10.75f);
        pathBuilderQ.curveToRelative(0.0f, -0.41f, 0.34f, -0.75f, 0.75f, -0.75f);
        b.u(pathBuilderQ, 16.0f, 9.0f, -1.5f);
        pathBuilderQ.curveTo(14.22f, 9.0f, 14.0f, 8.78f, 14.0f, 8.5f);
        pathBuilderQ.reflectiveCurveTo(14.22f, 8.0f, 14.5f, 8.0f);
        b.u(pathBuilderQ, 16.0f, 7.0f, -2.25f);
        pathBuilderQ.curveTo(13.34f, 7.0f, 13.0f, 6.66f, 13.0f, 6.25f);
        pathBuilderQ.reflectiveCurveToRelative(0.34f, -0.75f, 0.75f, -0.75f);
        pathBuilderQ.horizontalLineToRelative(2.75f);
        pathBuilderQ.curveToRelative(0.55f, 0.0f, 1.0f, 0.45f, 1.0f, 1.0f);
        pathBuilderQ.verticalLineToRelative(4.0f);
        pathBuilderQ.curveToRelative(0.0f, 0.55f, -0.45f, 1.0f, -1.0f, 1.0f);
        pathBuilderQ.horizontalLineToRelative(-2.75f);
        pathBuilderQ.curveTo(13.34f, 11.5f, 13.0f, 11.16f, 13.0f, 10.75f);
        pathBuilderQ.close();
        pathBuilderQ.moveTo(18.0f, 16.0f);
        pathBuilderQ.curveToRelative(0.0f, 0.55f, -0.45f, 1.0f, -1.0f, 1.0f);
        pathBuilderQ.horizontalLineToRelative(-2.0f);
        pathBuilderQ.verticalLineToRelative(0.75f);
        pathBuilderQ.curveToRelative(0.0f, 0.41f, -0.34f, 0.75f, -0.75f, 0.75f);
        pathBuilderQ.reflectiveCurveToRelative(-0.75f, -0.34f, -0.75f, -0.75f);
        pathBuilderQ.verticalLineTo(13.5f);
        pathBuilderQ.curveToRelative(0.0f, -0.55f, 0.45f, -1.0f, 1.0f, -1.0f);
        pathBuilderQ.horizontalLineTo(17.0f);
        pathBuilderQ.curveToRelative(0.55f, 0.0f, 1.0f, 0.45f, 1.0f, 1.0f);
        ImageVector.Builder.m4024addPathoIyEayM$default(builder, v.b.i(pathBuilderQ, 16.0f), defaultFillType, "", solidColor, 1.0f, null, 1.0f, 1.0f, iM3803getButtKaPHkGw, iM3813getBevelLxFBmk8, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null);
        ImageVector imageVectorBuild = ImageVector.Builder.m4024addPathoIyEayM$default(builder, e.d(15.0f, 14.0f, 1.5f, 1.5f, -1.5f), VectorKt.getDefaultFillType(), "", new SolidColor(companion.m3498getBlack0d7_KjU(), null), 1.0f, null, 1.0f, 1.0f, companion2.m3803getButtKaPHkGw(), companion3.m3813getBevelLxFBmk8(), 1.0f, 0.0f, 0.0f, 0.0f, 14336, null).build();
        __23mp = imageVectorBuild;
        return imageVectorBuild;
    }
}
