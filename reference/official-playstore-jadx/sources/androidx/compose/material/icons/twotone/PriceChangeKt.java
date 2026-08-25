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
import v.d;
import v.f;
import y.a;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\"\u0010\u0010\u0000\u001a\u0004\u0018\u00010\u0001X\u0082\u000e¢\u0006\u0002\n\u0000\"\u0015\u0010\u0002\u001a\u00020\u0001*\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"_priceChange", "Landroidx/compose/ui/graphics/vector/ImageVector;", "PriceChange", "Landroidx/compose/material/icons/Icons$TwoTone;", "getPriceChange", "(Landroidx/compose/material/icons/Icons$TwoTone;)Landroidx/compose/ui/graphics/vector/ImageVector;", "material-icons-extended_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class PriceChangeKt {
    private static ImageVector _priceChange;

    public static final ImageVector getPriceChange(Icons.TwoTone twoTone) {
        ImageVector imageVector = _priceChange;
        if (imageVector != null) {
            return imageVector;
        }
        ImageVector.Builder builder = new ImageVector.Builder("TwoTone.PriceChange", Dp.m5678constructorimpl(24.0f), Dp.m5678constructorimpl(24.0f), 24.0f, 24.0f, 0L, 0, false, 96, null);
        int defaultFillType = VectorKt.getDefaultFillType();
        Color.Companion companion = Color.INSTANCE;
        SolidColor solidColor = new SolidColor(companion.m3498getBlack0d7_KjU(), null);
        StrokeCap.Companion companion2 = StrokeCap.INSTANCE;
        int iM3803getButtKaPHkGw = companion2.m3803getButtKaPHkGw();
        StrokeJoin.Companion companion3 = StrokeJoin.INSTANCE;
        int iM3813getBevelLxFBmk8 = companion3.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderS = a.s(4.0f, 18.0f, 16.0f, 6.0f, 4.0f);
        f.r(pathBuilderS, 18.0f, 16.0f, 8.0f);
        pathBuilderS.lineToRelative(2.0f, 2.0f);
        d.q(pathBuilderS, -4.0f, 16.0f, 8.0f);
        pathBuilderS.moveTo(18.0f, 14.25f);
        pathBuilderS.lineToRelative(-2.0f, 2.0f);
        f.v(pathBuilderS, -2.0f, -2.0f, 18.0f);
        v.a.C(pathBuilderS, 6.0f, 14.0f, 4.0f, -1.0f);
        pathBuilderS.horizontalLineTo(7.0f);
        pathBuilderS.curveToRelative(-0.55f, 0.0f, -1.0f, -0.45f, -1.0f, -1.0f);
        pathBuilderS.verticalLineTo(9.0f);
        pathBuilderS.curveToRelative(0.0f, -0.55f, 0.45f, -1.0f, 1.0f, -1.0f);
        w.a.C(pathBuilderS, 1.0f, 7.0f, 2.0f, 1.0f);
        a0.a.y(pathBuilderS, 2.0f, 2.0f, 8.0f, 1.0f);
        pathBuilderS.horizontalLineToRelative(3.0f);
        pathBuilderS.curveToRelative(0.55f, 0.0f, 1.0f, 0.45f, 1.0f, 1.0f);
        pathBuilderS.verticalLineToRelative(3.0f);
        pathBuilderS.curveToRelative(0.0f, 0.55f, -0.45f, 1.0f, -1.0f, 1.0f);
        a0.a.y(pathBuilderS, -1.0f, 1.0f, 8.0f, -1.0f);
        ImageVector.Builder.m4024addPathoIyEayM$default(builder, v.a.m(pathBuilderS, 6.0f, 14.0f), defaultFillType, "", solidColor, 0.3f, null, 0.3f, 1.0f, iM3803getButtKaPHkGw, iM3813getBevelLxFBmk8, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null);
        int defaultFillType2 = VectorKt.getDefaultFillType();
        SolidColor solidColor2 = new SolidColor(companion.m3498getBlack0d7_KjU(), null);
        int iM3803getButtKaPHkGw2 = companion2.m3803getButtKaPHkGw();
        int iM3813getBevelLxFBmk82 = companion3.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderQ = v.a.q(20.0f, 4.0f, 4.0f);
        pathBuilderQ.curveTo(2.89f, 4.0f, 2.01f, 4.89f, 2.01f, 6.0f);
        pathBuilderQ.lineTo(2.0f, 18.0f);
        pathBuilderQ.curveToRelative(0.0f, 1.11f, 0.89f, 2.0f, 2.0f, 2.0f);
        pathBuilderQ.horizontalLineToRelative(16.0f);
        pathBuilderQ.curveToRelative(1.11f, 0.0f, 2.0f, -0.89f, 2.0f, -2.0f);
        pathBuilderQ.verticalLineTo(6.0f);
        pathBuilderQ.curveTo(22.0f, 4.89f, 21.11f, 4.0f, 20.0f, 4.0f);
        f.o(pathBuilderQ, 20.0f, 18.0f, 4.0f, 6.0f);
        ImageVector.Builder.m4024addPathoIyEayM$default(builder, v.a.e(pathBuilderQ, 16.0f, 18.0f), defaultFillType2, "", solidColor2, 1.0f, null, 1.0f, 1.0f, iM3803getButtKaPHkGw2, iM3813getBevelLxFBmk82, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null);
        int defaultFillType3 = VectorKt.getDefaultFillType();
        SolidColor solidColor3 = new SolidColor(companion.m3498getBlack0d7_KjU(), null);
        int iM3803getButtKaPHkGw3 = companion2.m3803getButtKaPHkGw();
        int iM3813getBevelLxFBmk83 = companion3.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderK = v.a.k(8.0f, 17.0f, 2.0f, -1.0f, 1.0f);
        pathBuilderK.curveToRelative(0.55f, 0.0f, 1.0f, -0.45f, 1.0f, -1.0f);
        pathBuilderK.verticalLineToRelative(-3.0f);
        pathBuilderK.curveToRelative(0.0f, -0.55f, -0.45f, -1.0f, -1.0f, -1.0f);
        a0.a.e(pathBuilderK, 8.0f, -1.0f, 4.0f, 8.0f);
        w.a.B(pathBuilderK, -2.0f, 7.0f, 8.0f, 1.0f);
        pathBuilderK.horizontalLineTo(7.0f);
        pathBuilderK.curveTo(6.45f, 8.0f, 6.0f, 8.45f, 6.0f, 9.0f);
        pathBuilderK.verticalLineToRelative(3.0f);
        pathBuilderK.curveToRelative(0.0f, 0.55f, 0.45f, 1.0f, 1.0f, 1.0f);
        a0.a.y(pathBuilderK, 3.0f, 1.0f, 6.0f, 2.0f);
        ImageVector.Builder.m4024addPathoIyEayM$default(builder, v.a.e(pathBuilderK, 2.0f, 17.0f), defaultFillType3, "", solidColor3, 1.0f, null, 1.0f, 1.0f, iM3803getButtKaPHkGw3, iM3813getBevelLxFBmk83, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null);
        ImageVector.Builder.m4024addPathoIyEayM$default(builder, c.c(v.a.r(16.0f, 8.0f, -2.0f, 2.0f), 4.0f, 0.0f), VectorKt.getDefaultFillType(), "", new SolidColor(companion.m3498getBlack0d7_KjU(), null), 1.0f, null, 1.0f, 1.0f, companion2.m3803getButtKaPHkGw(), companion3.m3813getBevelLxFBmk8(), 1.0f, 0.0f, 0.0f, 0.0f, 14336, null);
        ImageVector imageVectorBuild = ImageVector.Builder.m4024addPathoIyEayM$default(builder, c.c(v.a.r(18.0f, 14.25f, -4.0f, 0.0f), 2.0f, 2.0f), VectorKt.getDefaultFillType(), "", new SolidColor(companion.m3498getBlack0d7_KjU(), null), 1.0f, null, 1.0f, 1.0f, companion2.m3803getButtKaPHkGw(), companion3.m3813getBevelLxFBmk8(), 1.0f, 0.0f, 0.0f, 0.0f, 14336, null).build();
        _priceChange = imageVectorBuild;
        return imageVectorBuild;
    }
}
