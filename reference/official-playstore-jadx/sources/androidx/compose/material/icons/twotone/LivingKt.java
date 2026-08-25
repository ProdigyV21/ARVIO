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
import v.e;
import v.f;
import y.a;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\"\u0010\u0010\u0000\u001a\u0004\u0018\u00010\u0001X\u0082\u000e¢\u0006\u0002\n\u0000\"\u0015\u0010\u0002\u001a\u00020\u0001*\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"_living", "Landroidx/compose/ui/graphics/vector/ImageVector;", "Living", "Landroidx/compose/material/icons/Icons$TwoTone;", "getLiving", "(Landroidx/compose/material/icons/Icons$TwoTone;)Landroidx/compose/ui/graphics/vector/ImageVector;", "material-icons-extended_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class LivingKt {
    private static ImageVector _living;

    public static final ImageVector getLiving(Icons.TwoTone twoTone) {
        ImageVector imageVector = _living;
        if (imageVector != null) {
            return imageVector;
        }
        ImageVector.Builder builder = new ImageVector.Builder("TwoTone.Living", Dp.m5678constructorimpl(24.0f), Dp.m5678constructorimpl(24.0f), 24.0f, 24.0f, 0L, 0, false, 96, null);
        int defaultFillType = VectorKt.getDefaultFillType();
        Color.Companion companion = Color.INSTANCE;
        SolidColor solidColor = new SolidColor(companion.m3498getBlack0d7_KjU(), null);
        StrokeCap.Companion companion2 = StrokeCap.INSTANCE;
        int iM3803getButtKaPHkGw = companion2.m3803getButtKaPHkGw();
        StrokeJoin.Companion companion3 = StrokeJoin.INSTANCE;
        int iM3813getBevelLxFBmk8 = companion3.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderS = a.s(4.0f, 20.0f, 16.0f, 4.0f, 4.0f);
        f.r(pathBuilderS, 20.0f, 5.0f, 12.5f);
        pathBuilderS.curveToRelative(0.0f, -0.92f, 0.51f, -1.72f, 1.25f, -2.15f);
        pathBuilderS.verticalLineTo(9.0f);
        pathBuilderS.curveToRelative(0.0f, -1.66f, 1.34f, -3.0f, 3.0f, -3.0f);
        pathBuilderS.horizontalLineToRelative(5.5f);
        pathBuilderS.curveToRelative(1.66f, 0.0f, 3.0f, 1.34f, 3.0f, 3.0f);
        pathBuilderS.verticalLineToRelative(1.35f);
        pathBuilderS.curveTo(18.49f, 10.78f, 19.0f, 11.58f, 19.0f, 12.5f);
        pathBuilderS.verticalLineTo(16.0f);
        pathBuilderS.curveToRelative(0.0f, 1.1f, -0.9f, 2.0f, -2.0f, 2.0f);
        pathBuilderS.horizontalLineTo(7.0f);
        pathBuilderS.curveToRelative(-1.1f, 0.0f, -2.0f, -0.9f, -2.0f, -2.0f);
        ImageVector.Builder.m4024addPathoIyEayM$default(builder, b.i(pathBuilderS, 12.5f), defaultFillType, "", solidColor, 0.3f, null, 0.3f, 1.0f, iM3803getButtKaPHkGw, iM3813getBevelLxFBmk8, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null);
        int defaultFillType2 = VectorKt.getDefaultFillType();
        SolidColor solidColor2 = new SolidColor(companion.m3498getBlack0d7_KjU(), null);
        int iM3803getButtKaPHkGw2 = companion2.m3803getButtKaPHkGw();
        int iM3813getBevelLxFBmk82 = companion3.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderQ = v.a.q(20.0f, 2.0f, 4.0f);
        pathBuilderQ.curveTo(2.9f, 2.0f, 2.0f, 2.9f, 2.0f, 4.0f);
        pathBuilderQ.verticalLineToRelative(16.0f);
        pathBuilderQ.curveToRelative(0.0f, 1.1f, 0.9f, 2.0f, 2.0f, 2.0f);
        pathBuilderQ.horizontalLineToRelative(16.0f);
        pathBuilderQ.curveToRelative(1.1f, 0.0f, 2.0f, -0.9f, 2.0f, -2.0f);
        pathBuilderQ.verticalLineTo(4.0f);
        pathBuilderQ.curveTo(22.0f, 2.9f, 21.1f, 2.0f, 20.0f, 2.0f);
        f.o(pathBuilderQ, 20.0f, 20.0f, 4.0f, 4.0f);
        c.D(pathBuilderQ, 16.0f, 20.0f, 7.0f, 18.0f);
        pathBuilderQ.horizontalLineToRelative(10.0f);
        pathBuilderQ.curveToRelative(1.1f, 0.0f, 2.0f, -0.9f, 2.0f, -2.0f);
        pathBuilderQ.verticalLineToRelative(-3.5f);
        pathBuilderQ.curveToRelative(0.0f, -0.92f, -0.51f, -1.72f, -1.25f, -2.15f);
        pathBuilderQ.verticalLineTo(9.0f);
        pathBuilderQ.curveToRelative(0.0f, -1.66f, -1.34f, -3.0f, -3.0f, -3.0f);
        pathBuilderQ.horizontalLineToRelative(-5.5f);
        pathBuilderQ.curveToRelative(-1.66f, 0.0f, -3.0f, 1.34f, -3.0f, 3.0f);
        pathBuilderQ.verticalLineToRelative(1.35f);
        pathBuilderQ.curveTo(5.51f, 10.78f, 5.0f, 11.58f, 5.0f, 12.5f);
        pathBuilderQ.verticalLineTo(16.0f);
        pathBuilderQ.curveTo(5.0f, 17.1f, 5.9f, 18.0f, 7.0f, 18.0f);
        pathBuilderQ.close();
        pathBuilderQ.moveTo(7.75f, 9.0f);
        pathBuilderQ.curveToRelative(0.0f, -0.83f, 0.67f, -1.5f, 1.5f, -1.5f);
        pathBuilderQ.horizontalLineToRelative(5.5f);
        pathBuilderQ.curveToRelative(0.83f, 0.0f, 1.5f, 0.67f, 1.5f, 1.5f);
        pathBuilderQ.verticalLineToRelative(1.03f);
        pathBuilderQ.curveTo(14.99f, 10.15f, 14.0f, 11.2f, 14.0f, 12.5f);
        w.a.j(pathBuilderQ, 13.0f, -4.0f, -0.5f);
        pathBuilderQ.curveToRelative(0.0f, -1.3f, -0.99f, -2.35f, -2.25f, -2.47f);
        f.r(pathBuilderQ, 9.0f, 6.5f, 12.5f);
        pathBuilderQ.curveToRelative(0.0f, -0.55f, 0.45f, -1.0f, 1.0f, -1.0f);
        pathBuilderQ.reflectiveCurveToRelative(1.0f, 0.45f, 1.0f, 1.0f);
        e.l(pathBuilderQ, 2.0f, 7.0f, -2.0f);
        pathBuilderQ.curveToRelative(0.0f, -0.55f, 0.45f, -1.0f, 1.0f, -1.0f);
        pathBuilderQ.reflectiveCurveToRelative(1.0f, 0.45f, 1.0f, 1.0f);
        pathBuilderQ.verticalLineTo(16.0f);
        pathBuilderQ.curveToRelative(0.0f, 0.28f, -0.22f, 0.5f, -0.5f, 0.5f);
        pathBuilderQ.horizontalLineTo(7.0f);
        pathBuilderQ.curveToRelative(-0.28f, 0.0f, -0.5f, -0.22f, -0.5f, -0.5f);
        ImageVector imageVectorBuild = ImageVector.Builder.m4024addPathoIyEayM$default(builder, b.i(pathBuilderQ, 12.5f), defaultFillType2, "", solidColor2, 1.0f, null, 1.0f, 1.0f, iM3803getButtKaPHkGw2, iM3813getBevelLxFBmk82, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null).build();
        _living = imageVectorBuild;
        return imageVectorBuild;
    }
}
