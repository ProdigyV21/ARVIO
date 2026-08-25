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
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\"\u0010\u0010\u0000\u001a\u0004\u0018\u00010\u0001X\u0082\u000e¢\u0006\u0002\n\u0000\"\u0015\u0010\u0002\u001a\u00020\u0001*\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"_bento", "Landroidx/compose/ui/graphics/vector/ImageVector;", "Bento", "Landroidx/compose/material/icons/Icons$TwoTone;", "getBento", "(Landroidx/compose/material/icons/Icons$TwoTone;)Landroidx/compose/ui/graphics/vector/ImageVector;", "material-icons-extended_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class BentoKt {
    private static ImageVector _bento;

    public static final ImageVector getBento(Icons.TwoTone twoTone) {
        ImageVector imageVector = _bento;
        if (imageVector != null) {
            return imageVector;
        }
        ImageVector.Builder builder = new ImageVector.Builder("TwoTone.Bento", Dp.m5678constructorimpl(24.0f), Dp.m5678constructorimpl(24.0f), 24.0f, 24.0f, 0L, 0, false, 96, null);
        int defaultFillType = VectorKt.getDefaultFillType();
        Color.Companion companion = Color.INSTANCE;
        SolidColor solidColor = new SolidColor(companion.m3498getBlack0d7_KjU(), null);
        StrokeCap.Companion companion2 = StrokeCap.INSTANCE;
        int iM3803getButtKaPHkGw = companion2.m3803getButtKaPHkGw();
        StrokeJoin.Companion companion3 = StrokeJoin.INSTANCE;
        int iM3813getBevelLxFBmk8 = companion3.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderS = a.s(4.0f, 17.0f, 8.0f, 7.0f, 4.0f);
        f.r(pathBuilderS, 17.0f, 8.0f, 10.5f);
        pathBuilderS.curveToRelative(0.83f, 0.0f, 1.5f, 0.67f, 1.5f, 1.5f);
        pathBuilderS.reflectiveCurveTo(8.83f, 13.5f, 8.0f, 13.5f);
        pathBuilderS.reflectiveCurveTo(6.5f, 12.83f, 6.5f, 12.0f);
        pathBuilderS.reflectiveCurveTo(7.17f, 10.5f, 8.0f, 10.5f);
        b.w(pathBuilderS, 14.0f, 13.0f, 6.0f, 4.0f);
        c.D(pathBuilderS, -6.0f, 13.0f, 20.0f, 7.0f);
        pathBuilderS.verticalLineToRelative(4.0f);
        pathBuilderS.horizontalLineToRelative(-6.0f);
        pathBuilderS.verticalLineTo(7.0f);
        pathBuilderS.horizontalLineTo(20.0f);
        pathBuilderS.close();
        ImageVector.Builder.m4024addPathoIyEayM$default(builder, pathBuilderS.getNodes(), defaultFillType, "", solidColor, 0.3f, null, 0.3f, 1.0f, iM3803getButtKaPHkGw, iM3813getBevelLxFBmk8, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null);
        int defaultFillType2 = VectorKt.getDefaultFillType();
        SolidColor solidColor2 = new SolidColor(companion.m3498getBlack0d7_KjU(), null);
        int iM3803getButtKaPHkGw2 = companion2.m3803getButtKaPHkGw();
        int iM3813getBevelLxFBmk82 = companion3.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderQ = v.a.q(20.0f, 5.0f, 4.0f);
        pathBuilderQ.curveTo(2.9f, 5.0f, 2.0f, 5.9f, 2.0f, 7.0f);
        pathBuilderQ.verticalLineToRelative(10.0f);
        pathBuilderQ.curveToRelative(0.0f, 1.1f, 0.9f, 2.0f, 2.0f, 2.0f);
        pathBuilderQ.horizontalLineToRelative(16.0f);
        pathBuilderQ.curveToRelative(1.1f, 0.0f, 2.0f, -0.9f, 2.0f, -2.0f);
        pathBuilderQ.verticalLineTo(7.0f);
        pathBuilderQ.curveTo(22.0f, 5.9f, 21.1f, 5.0f, 20.0f, 5.0f);
        v.a.u(pathBuilderQ, 20.0f, 11.0f, -6.0f, 7.0f);
        c.D(pathBuilderQ, 6.0f, 11.0f, 4.0f, 7.0f);
        d.z(pathBuilderQ, 8.0f, 10.0f, 4.0f, 7.0f);
        c.m(pathBuilderQ, 14.0f, 17.0f, -4.0f, 6.0f);
        e.y(pathBuilderQ, 4.0f, 14.0f, 9.5f, 12.0f);
        pathBuilderQ.curveToRelative(0.0f, 0.83f, -0.67f, 1.5f, -1.5f, 1.5f);
        pathBuilderQ.reflectiveCurveTo(6.5f, 12.83f, 6.5f, 12.0f);
        pathBuilderQ.reflectiveCurveToRelative(0.67f, -1.5f, 1.5f, -1.5f);
        ImageVector imageVectorBuild = ImageVector.Builder.m4024addPathoIyEayM$default(builder, e.u(pathBuilderQ, 9.5f, 11.17f, 9.5f, 12.0f), defaultFillType2, "", solidColor2, 1.0f, null, 1.0f, 1.0f, iM3803getButtKaPHkGw2, iM3813getBevelLxFBmk82, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null).build();
        _bento = imageVectorBuild;
        return imageVectorBuild;
    }
}
