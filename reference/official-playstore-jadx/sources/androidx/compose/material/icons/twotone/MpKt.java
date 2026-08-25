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
import v.b;
import v.c;
import v.d;
import v.e;
import v.f;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\"\u0010\u0010\u0000\u001a\u0004\u0018\u00010\u0001X\u0082\u000e¢\u0006\u0002\n\u0000\"\u0015\u0010\u0002\u001a\u00020\u0001*\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"_mp", "Landroidx/compose/ui/graphics/vector/ImageVector;", "Mp", "Landroidx/compose/material/icons/Icons$TwoTone;", "getMp", "(Landroidx/compose/material/icons/Icons$TwoTone;)Landroidx/compose/ui/graphics/vector/ImageVector;", "material-icons-extended_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class MpKt {
    private static ImageVector _mp;

    public static final ImageVector getMp(Icons.TwoTone twoTone) {
        ImageVector imageVector = _mp;
        if (imageVector != null) {
            return imageVector;
        }
        ImageVector.Builder builder = new ImageVector.Builder("TwoTone.Mp", Dp.m5678constructorimpl(24.0f), Dp.m5678constructorimpl(24.0f), 24.0f, 24.0f, 0L, 0, false, 96, null);
        int defaultFillType = VectorKt.getDefaultFillType();
        Color.Companion companion = Color.INSTANCE;
        SolidColor solidColor = new SolidColor(companion.m3498getBlack0d7_KjU(), null);
        StrokeCap.Companion companion2 = StrokeCap.INSTANCE;
        int iM3803getButtKaPHkGw = companion2.m3803getButtKaPHkGw();
        StrokeJoin.Companion companion3 = StrokeJoin.INSTANCE;
        ImageVector.Builder.m4024addPathoIyEayM$default(builder, e.d(15.0f, 10.5f, 1.5f, 1.5f, -1.5f), defaultFillType, "", solidColor, 0.3f, null, 0.3f, 1.0f, iM3803getButtKaPHkGw, companion3.m3813getBevelLxFBmk8(), 1.0f, 0.0f, 0.0f, 0.0f, 14336, null);
        int defaultFillType2 = VectorKt.getDefaultFillType();
        SolidColor solidColor2 = new SolidColor(companion.m3498getBlack0d7_KjU(), null);
        int iM3803getButtKaPHkGw2 = companion2.m3803getButtKaPHkGw();
        int iM3813getBevelLxFBmk8 = companion3.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderR = a.r(5.0f, 5.0f, 0.0f, 14.0f);
        pathBuilderR.lineToRelative(14.0f, 0.0f);
        f.v(pathBuilderR, 0.0f, -14.0f, 5.0f);
        a.A(pathBuilderR, 12.5f, 15.0f, 11.0f, -4.5f);
        a0.a.y(pathBuilderR, -1.0f, 3.0f, 8.5f, -3.0f);
        w.a.B(pathBuilderR, -1.0f, 15.0f, 6.0f, -5.0f);
        pathBuilderR.curveToRelative(0.0f, -0.55f, 0.45f, -1.0f, 1.0f, -1.0f);
        pathBuilderR.horizontalLineToRelative(4.5f);
        pathBuilderR.curveToRelative(0.55f, 0.0f, 1.0f, 0.45f, 1.0f, 1.0f);
        f.r(pathBuilderR, 15.0f, 18.0f, 12.5f);
        pathBuilderR.curveToRelative(0.0f, 0.55f, -0.45f, 1.0f, -1.0f, 1.0f);
        a0.a.C(pathBuilderR, -2.0f, 15.0f, -1.5f, 9.0f);
        pathBuilderR.horizontalLineTo(17.0f);
        pathBuilderR.curveToRelative(0.55f, 0.0f, 1.0f, 0.45f, 1.0f, 1.0f);
        ImageVector.Builder.m4024addPathoIyEayM$default(builder, b.i(pathBuilderR, 12.5f), defaultFillType2, "", solidColor2, 0.3f, null, 0.3f, 1.0f, iM3803getButtKaPHkGw2, iM3813getBevelLxFBmk8, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null);
        int defaultFillType3 = VectorKt.getDefaultFillType();
        SolidColor solidColor3 = new SolidColor(companion.m3498getBlack0d7_KjU(), null);
        int iM3803getButtKaPHkGw3 = companion2.m3803getButtKaPHkGw();
        int iM3813getBevelLxFBmk82 = companion3.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderH = d.h(17.0f, 9.0f, -3.5f, 6.0f, 15.0f);
        pathBuilderH.verticalLineToRelative(-1.5f);
        pathBuilderH.horizontalLineToRelative(2.0f);
        pathBuilderH.curveToRelative(0.55f, 0.0f, 1.0f, -0.45f, 1.0f, -1.0f);
        pathBuilderH.verticalLineTo(10.0f);
        pathBuilderH.curveTo(18.0f, 9.45f, 17.55f, 9.0f, 17.0f, 9.0f);
        c.C(pathBuilderH, 16.5f, 12.0f, 15.0f, -1.5f);
        ImageVector.Builder.m4024addPathoIyEayM$default(builder, a.e(pathBuilderH, 1.5f, 12.0f), defaultFillType3, "", solidColor3, 1.0f, null, 1.0f, 1.0f, iM3803getButtKaPHkGw3, iM3813getBevelLxFBmk82, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null);
        int defaultFillType4 = VectorKt.getDefaultFillType();
        SolidColor solidColor4 = new SolidColor(companion.m3498getBlack0d7_KjU(), null);
        int iM3803getButtKaPHkGw4 = companion2.m3803getButtKaPHkGw();
        int iM3813getBevelLxFBmk83 = companion3.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderQ = a.q(11.5f, 9.0f, 7.0f);
        pathBuilderQ.curveToRelative(-0.55f, 0.0f, -1.0f, 0.45f, -1.0f, 1.0f);
        b.z(pathBuilderQ, 5.0f, 1.5f, -4.5f, 1.0f);
        a0.a.A(pathBuilderQ, 3.0f, 10.0f, -3.0f, 1.0f);
        w.a.j(pathBuilderQ, 15.0f, 1.5f, -5.0f);
        pathBuilderQ.curveTo(12.5f, 9.45f, 12.05f, 9.0f, 11.5f, 9.0f);
        pathBuilderQ.close();
        ImageVector.Builder.m4024addPathoIyEayM$default(builder, pathBuilderQ.getNodes(), defaultFillType4, "", solidColor4, 1.0f, null, 1.0f, 1.0f, iM3803getButtKaPHkGw4, iM3813getBevelLxFBmk83, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null);
        int defaultFillType5 = VectorKt.getDefaultFillType();
        SolidColor solidColor5 = new SolidColor(companion.m3498getBlack0d7_KjU(), null);
        int iM3803getButtKaPHkGw5 = companion2.m3803getButtKaPHkGw();
        int iM3813getBevelLxFBmk84 = companion3.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderQ2 = a.q(19.0f, 3.0f, 5.0f);
        pathBuilderQ2.curveTo(3.9f, 3.0f, 3.0f, 3.9f, 3.0f, 5.0f);
        pathBuilderQ2.verticalLineTo(19.0f);
        pathBuilderQ2.curveToRelative(0.0f, 1.1f, 0.9f, 2.0f, 2.0f, 2.0f);
        pathBuilderQ2.horizontalLineTo(19.0f);
        pathBuilderQ2.curveToRelative(1.1f, 0.0f, 2.0f, -0.9f, 2.0f, -2.0f);
        pathBuilderQ2.verticalLineTo(5.0f);
        pathBuilderQ2.curveTo(21.0f, 3.9f, 20.1f, 3.0f, 19.0f, 3.0f);
        a.p(pathBuilderQ2, 5.0f, 19.0f, 5.0f, 5.0f);
        pathBuilderQ2.horizontalLineToRelative(14.0f);
        ImageVector imageVectorBuild = ImageVector.Builder.m4024addPathoIyEayM$default(builder, b.e(pathBuilderQ2, 0.0f, 14.0f, 5.0f, 19.0f), defaultFillType5, "", solidColor5, 1.0f, null, 1.0f, 1.0f, iM3803getButtKaPHkGw5, iM3813getBevelLxFBmk84, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null).build();
        _mp = imageVectorBuild;
        return imageVectorBuild;
    }
}
