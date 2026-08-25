package androidx.compose.material.icons.twotone;

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
import v.c;
import v.e;
import v.f;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\"\u0010\u0010\u0000\u001a\u0004\u0018\u00010\u0001X\u0082\u000e¢\u0006\u0002\n\u0000\"\u0015\u0010\u0002\u001a\u00020\u0001*\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"_copyAll", "Landroidx/compose/ui/graphics/vector/ImageVector;", "CopyAll", "Landroidx/compose/material/icons/Icons$TwoTone;", "getCopyAll", "(Landroidx/compose/material/icons/Icons$TwoTone;)Landroidx/compose/ui/graphics/vector/ImageVector;", "material-icons-extended_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class CopyAllKt {
    private static ImageVector _copyAll;

    public static final ImageVector getCopyAll(Icons.TwoTone twoTone) {
        ImageVector imageVector = _copyAll;
        if (imageVector != null) {
            return imageVector;
        }
        ImageVector.Builder builder = new ImageVector.Builder("TwoTone.CopyAll", Dp.m5678constructorimpl(24.0f), Dp.m5678constructorimpl(24.0f), 24.0f, 24.0f, 0L, 0, false, 96, null);
        int defaultFillType = VectorKt.getDefaultFillType();
        Color.Companion companion = Color.INSTANCE;
        SolidColor solidColor = new SolidColor(companion.m3498getBlack0d7_KjU(), null);
        StrokeCap.Companion companion2 = StrokeCap.INSTANCE;
        int iM3803getButtKaPHkGw = companion2.m3803getButtKaPHkGw();
        StrokeJoin.Companion companion3 = StrokeJoin.INSTANCE;
        ImageVector.Builder.m4024addPathoIyEayM$default(builder, e.d(9.0f, 4.0f, 9.0f, 12.0f, -9.0f), defaultFillType, "", solidColor, 0.3f, null, 0.3f, 1.0f, iM3803getButtKaPHkGw, companion3.m3813getBevelLxFBmk8(), 1.0f, 0.0f, 0.0f, 0.0f, 14336, null);
        int defaultFillType2 = VectorKt.getDefaultFillType();
        SolidColor solidColor2 = new SolidColor(companion.m3498getBlack0d7_KjU(), null);
        int iM3803getButtKaPHkGw2 = companion2.m3803getButtKaPHkGw();
        int iM3813getBevelLxFBmk8 = companion3.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderQ = a.q(18.0f, 2.0f, 9.0f);
        pathBuilderQ.curveTo(7.9f, 2.0f, 7.0f, 2.9f, 7.0f, 4.0f);
        pathBuilderQ.verticalLineToRelative(12.0f);
        pathBuilderQ.curveToRelative(0.0f, 1.1f, 0.9f, 2.0f, 2.0f, 2.0f);
        pathBuilderQ.horizontalLineToRelative(9.0f);
        pathBuilderQ.curveToRelative(1.1f, 0.0f, 2.0f, -0.9f, 2.0f, -2.0f);
        pathBuilderQ.verticalLineTo(4.0f);
        pathBuilderQ.curveTo(20.0f, 2.9f, 19.1f, 2.0f, 18.0f, 2.0f);
        f.o(pathBuilderQ, 18.0f, 16.0f, 9.0f, 4.0f);
        c.D(pathBuilderQ, 9.0f, 16.0f, 3.0f, 15.0f);
        b.f(pathBuilderQ, -2.0f, 2.0f, 2.0f, 3.0f);
        a.C(pathBuilderQ, 3.0f, 9.5f, 2.0f, 2.0f);
        c.l(pathBuilderQ, 3.0f, 9.5f, 10.0f, 20.0f);
        f.w(pathBuilderQ, 2.0f, 2.0f, -2.0f, 20.0f);
        c.m(pathBuilderQ, 3.0f, 18.5f, -2.0f, 2.0f);
        e.y(pathBuilderQ, 2.0f, 3.0f, 5.0f, 22.0f);
        pathBuilderQ.curveToRelative(-1.1f, 0.0f, -2.0f, -0.9f, -2.0f, -2.0f);
        c.D(pathBuilderQ, 2.0f, 22.0f, 8.5f, 22.0f);
        f.w(pathBuilderQ, -2.0f, -2.0f, 2.0f, 22.0f);
        pathBuilderQ.moveTo(13.5f, 22.0f);
        pathBuilderQ.lineTo(13.5f, 22.0f);
        a0.a.m(pathBuilderQ, 0.0f, -2.0f, 2.0f, 0.0f);
        pathBuilderQ.curveTo(15.5f, 21.1f, 14.6f, 22.0f, 13.5f, 22.0f);
        a.p(pathBuilderQ, 5.0f, 6.0f, 5.0f, 6.0f);
        e.h(pathBuilderQ, 0.0f, 2.0f, 3.0f, 0.0f);
        pathBuilderQ.curveTo(3.0f, 6.9f, 3.9f, 6.0f, 5.0f, 6.0f);
        pathBuilderQ.close();
        ImageVector imageVectorBuild = ImageVector.Builder.m4024addPathoIyEayM$default(builder, pathBuilderQ.getNodes(), defaultFillType2, "", solidColor2, 1.0f, null, 1.0f, 1.0f, iM3803getButtKaPHkGw2, iM3813getBevelLxFBmk8, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null).build();
        _copyAll = imageVectorBuild;
        return imageVectorBuild;
    }
}
