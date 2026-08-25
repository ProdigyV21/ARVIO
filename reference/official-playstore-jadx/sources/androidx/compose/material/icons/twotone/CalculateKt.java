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
import v.f;
import y.a;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\"\u0010\u0010\u0000\u001a\u0004\u0018\u00010\u0001X\u0082\u000e¢\u0006\u0002\n\u0000\"\u0015\u0010\u0002\u001a\u00020\u0001*\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"_calculate", "Landroidx/compose/ui/graphics/vector/ImageVector;", "Calculate", "Landroidx/compose/material/icons/Icons$TwoTone;", "getCalculate", "(Landroidx/compose/material/icons/Icons$TwoTone;)Landroidx/compose/ui/graphics/vector/ImageVector;", "material-icons-extended_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class CalculateKt {
    private static ImageVector _calculate;

    public static final ImageVector getCalculate(Icons.TwoTone twoTone) {
        ImageVector imageVector = _calculate;
        if (imageVector != null) {
            return imageVector;
        }
        ImageVector.Builder builder = new ImageVector.Builder("TwoTone.Calculate", Dp.m5678constructorimpl(24.0f), Dp.m5678constructorimpl(24.0f), 24.0f, 24.0f, 0L, 0, false, 96, null);
        int defaultFillType = VectorKt.getDefaultFillType();
        Color.Companion companion = Color.INSTANCE;
        SolidColor solidColor = new SolidColor(companion.m3498getBlack0d7_KjU(), null);
        StrokeCap.Companion companion2 = StrokeCap.INSTANCE;
        int iM3803getButtKaPHkGw = companion2.m3803getButtKaPHkGw();
        StrokeJoin.Companion companion3 = StrokeJoin.INSTANCE;
        int iM3813getBevelLxFBmk8 = companion3.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderS = a.s(5.0f, 19.0f, 14.0f, 5.0f, 5.0f);
        f.r(pathBuilderS, 19.0f, 13.03f, 7.06f);
        pathBuilderS.lineTo(14.09f, 6.0f);
        pathBuilderS.lineToRelative(1.41f, 1.41f);
        pathBuilderS.lineTo(16.91f, 6.0f);
        pathBuilderS.lineToRelative(1.06f, 1.06f);
        pathBuilderS.lineToRelative(-1.41f, 1.41f);
        pathBuilderS.lineToRelative(1.41f, 1.41f);
        pathBuilderS.lineToRelative(-1.06f, 1.06f);
        pathBuilderS.lineTo(15.5f, 9.54f);
        pathBuilderS.lineToRelative(-1.41f, 1.41f);
        pathBuilderS.lineToRelative(-1.06f, -1.06f);
        d.C(pathBuilderS, 1.41f, -1.41f, 13.03f, 7.06f);
        v.a.C(pathBuilderS, 13.0f, 13.25f, 5.0f, 1.5f);
        c.D(pathBuilderS, -5.0f, 13.25f, 13.0f, 15.75f);
        f.w(pathBuilderS, 5.0f, 1.5f, -5.0f, 15.75f);
        v.a.C(pathBuilderS, 6.25f, 7.72f, 5.0f, 1.5f);
        c.D(pathBuilderS, -5.0f, 7.72f, 6.0f, 14.5f);
        v.a.o(pathBuilderS, 2.0f, -2.0f, 1.5f, 2.0f);
        w.a.C(pathBuilderS, 2.0f, 16.0f, -2.0f, 2.0f);
        pathBuilderS.horizontalLineTo(8.0f);
        pathBuilderS.verticalLineToRelative(-2.0f);
        pathBuilderS.horizontalLineTo(6.0f);
        pathBuilderS.verticalLineTo(14.5f);
        pathBuilderS.close();
        ImageVector.Builder.m4024addPathoIyEayM$default(builder, pathBuilderS.getNodes(), defaultFillType, "", solidColor, 0.3f, null, 0.3f, 1.0f, iM3803getButtKaPHkGw, iM3813getBevelLxFBmk8, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null);
        int defaultFillType2 = VectorKt.getDefaultFillType();
        SolidColor solidColor2 = new SolidColor(companion.m3498getBlack0d7_KjU(), null);
        int iM3803getButtKaPHkGw2 = companion2.m3803getButtKaPHkGw();
        int iM3813getBevelLxFBmk82 = companion3.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderQ = v.a.q(19.0f, 3.0f, 5.0f);
        pathBuilderQ.curveTo(3.9f, 3.0f, 3.0f, 3.9f, 3.0f, 5.0f);
        pathBuilderQ.verticalLineToRelative(14.0f);
        pathBuilderQ.curveToRelative(0.0f, 1.1f, 0.9f, 2.0f, 2.0f, 2.0f);
        pathBuilderQ.horizontalLineToRelative(14.0f);
        pathBuilderQ.curveToRelative(1.1f, 0.0f, 2.0f, -0.9f, 2.0f, -2.0f);
        pathBuilderQ.verticalLineTo(5.0f);
        pathBuilderQ.curveTo(21.0f, 3.9f, 20.1f, 3.0f, 19.0f, 3.0f);
        f.o(pathBuilderQ, 19.0f, 19.0f, 5.0f, 5.0f);
        ImageVector.Builder.m4024addPathoIyEayM$default(builder, v.a.e(pathBuilderQ, 14.0f, 19.0f), defaultFillType2, "", solidColor2, 1.0f, null, 1.0f, 1.0f, iM3803getButtKaPHkGw2, iM3813getBevelLxFBmk82, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null);
        ImageVector.Builder.m4024addPathoIyEayM$default(builder, e.d(6.25f, 7.72f, 5.0f, 1.5f, -5.0f), VectorKt.getDefaultFillType(), "", new SolidColor(companion.m3498getBlack0d7_KjU(), null), 1.0f, null, 1.0f, 1.0f, companion2.m3803getButtKaPHkGw(), companion3.m3813getBevelLxFBmk8(), 1.0f, 0.0f, 0.0f, 0.0f, 14336, null);
        ImageVector.Builder.m4024addPathoIyEayM$default(builder, e.d(13.0f, 15.75f, 5.0f, 1.5f, -5.0f), VectorKt.getDefaultFillType(), "", new SolidColor(companion.m3498getBlack0d7_KjU(), null), 1.0f, null, 1.0f, 1.0f, companion2.m3803getButtKaPHkGw(), companion3.m3813getBevelLxFBmk8(), 1.0f, 0.0f, 0.0f, 0.0f, 14336, null);
        ImageVector.Builder.m4024addPathoIyEayM$default(builder, e.d(13.0f, 13.25f, 5.0f, 1.5f, -5.0f), VectorKt.getDefaultFillType(), "", new SolidColor(companion.m3498getBlack0d7_KjU(), null), 1.0f, null, 1.0f, 1.0f, companion2.m3803getButtKaPHkGw(), companion3.m3813getBevelLxFBmk8(), 1.0f, 0.0f, 0.0f, 0.0f, 14336, null);
        int defaultFillType3 = VectorKt.getDefaultFillType();
        SolidColor solidColor3 = new SolidColor(companion.m3498getBlack0d7_KjU(), null);
        int iM3803getButtKaPHkGw3 = companion2.m3803getButtKaPHkGw();
        int iM3813getBevelLxFBmk83 = companion3.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderR = v.a.r(8.0f, 18.0f, 1.5f, 0.0f);
        pathBuilderR.lineToRelative(0.0f, -2.0f);
        pathBuilderR.lineToRelative(2.0f, 0.0f);
        pathBuilderR.lineToRelative(0.0f, -1.5f);
        pathBuilderR.lineToRelative(-2.0f, 0.0f);
        pathBuilderR.lineToRelative(0.0f, -2.0f);
        pathBuilderR.lineToRelative(-1.5f, 0.0f);
        pathBuilderR.lineToRelative(0.0f, 2.0f);
        pathBuilderR.lineToRelative(-2.0f, 0.0f);
        ImageVector.Builder.m4024addPathoIyEayM$default(builder, b.t(pathBuilderR, 0.0f, 1.5f, 2.0f, 0.0f), defaultFillType3, "", solidColor3, 1.0f, null, 1.0f, 1.0f, iM3803getButtKaPHkGw3, iM3813getBevelLxFBmk83, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null);
        int defaultFillType4 = VectorKt.getDefaultFillType();
        SolidColor solidColor4 = new SolidColor(companion.m3498getBlack0d7_KjU(), null);
        int iM3803getButtKaPHkGw4 = companion2.m3803getButtKaPHkGw();
        int iM3813getBevelLxFBmk84 = companion3.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderR2 = v.a.r(14.09f, 10.95f, 1.41f, -1.41f);
        pathBuilderR2.lineToRelative(1.41f, 1.41f);
        pathBuilderR2.lineToRelative(1.06f, -1.06f);
        pathBuilderR2.lineToRelative(-1.41f, -1.42f);
        pathBuilderR2.lineToRelative(1.41f, -1.41f);
        pathBuilderR2.lineToRelative(-1.06f, -1.06f);
        pathBuilderR2.lineToRelative(-1.41f, 1.41f);
        pathBuilderR2.lineToRelative(-1.41f, -1.41f);
        pathBuilderR2.lineToRelative(-1.06f, 1.06f);
        ImageVector imageVectorBuild = ImageVector.Builder.m4024addPathoIyEayM$default(builder, b.t(pathBuilderR2, 1.41f, 1.41f, -1.41f, 1.42f), defaultFillType4, "", solidColor4, 1.0f, null, 1.0f, 1.0f, iM3803getButtKaPHkGw4, iM3813getBevelLxFBmk84, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null).build();
        _calculate = imageVectorBuild;
        return imageVectorBuild;
    }
}
