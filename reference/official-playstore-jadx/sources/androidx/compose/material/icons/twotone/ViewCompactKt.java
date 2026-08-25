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
import v.d;
import v.e;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\"\u0010\u0010\u0000\u001a\u0004\u0018\u00010\u0001X\u0082\u000e¢\u0006\u0002\n\u0000\"\u0015\u0010\u0002\u001a\u00020\u0001*\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"_viewCompact", "Landroidx/compose/ui/graphics/vector/ImageVector;", "ViewCompact", "Landroidx/compose/material/icons/Icons$TwoTone;", "getViewCompact", "(Landroidx/compose/material/icons/Icons$TwoTone;)Landroidx/compose/ui/graphics/vector/ImageVector;", "material-icons-extended_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class ViewCompactKt {
    private static ImageVector _viewCompact;

    public static final ImageVector getViewCompact(Icons.TwoTone twoTone) {
        ImageVector imageVector = _viewCompact;
        if (imageVector != null) {
            return imageVector;
        }
        ImageVector.Builder builder = new ImageVector.Builder("TwoTone.ViewCompact", Dp.m5678constructorimpl(24.0f), Dp.m5678constructorimpl(24.0f), 24.0f, 24.0f, 0L, 0, false, 96, null);
        int defaultFillType = VectorKt.getDefaultFillType();
        Color.Companion companion = Color.INSTANCE;
        SolidColor solidColor = new SolidColor(companion.m3498getBlack0d7_KjU(), null);
        StrokeCap.Companion companion2 = StrokeCap.INSTANCE;
        int iM3803getButtKaPHkGw = companion2.m3803getButtKaPHkGw();
        StrokeJoin.Companion companion3 = StrokeJoin.INSTANCE;
        int iM3813getBevelLxFBmk8 = companion3.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderH = d.h(4.0f, 13.25f, 2.5f, -2.5f, 4.0f);
        a.D(pathBuilderH, 13.25f, 4.0f, 18.0f, 2.5f);
        d.u(pathBuilderH, -2.5f, 4.0f, 18.0f);
        c.B(pathBuilderH, 8.5f, 8.5f, 11.0f, 6.0f);
        c.l(pathBuilderH, 8.5f, 8.5f, 17.5f, 18.0f);
        c.k(pathBuilderH, 20.0f, -2.5f, -2.5f, 18.0f);
        a.A(pathBuilderH, 17.5f, 13.25f, 20.0f, -2.5f);
        c.D(pathBuilderH, -2.5f, 13.25f, 17.5f, 6.0f);
        pathBuilderH.verticalLineToRelative(2.5f);
        pathBuilderH.horizontalLineTo(20.0f);
        pathBuilderH.verticalLineTo(6.0f);
        pathBuilderH.horizontalLineTo(17.5f);
        pathBuilderH.close();
        e.m(pathBuilderH, 13.0f, 8.5f, 2.5f, 6.0f);
        c.l(pathBuilderH, 13.0f, 8.5f, 8.5f, 13.25f);
        pathBuilderH.horizontalLineTo(11.0f);
        pathBuilderH.verticalLineToRelative(-2.5f);
        pathBuilderH.horizontalLineTo(8.5f);
        pathBuilderH.verticalLineTo(13.25f);
        pathBuilderH.close();
        a.A(pathBuilderH, 8.5f, 18.0f, 11.0f, -2.5f);
        c.l(pathBuilderH, 8.5f, 18.0f, 4.0f, 8.5f);
        a0.c.D(pathBuilderH, 2.5f, 6.0f, 4.0f, 8.5f);
        a.C(pathBuilderH, 13.0f, 13.25f, 2.5f, -2.5f);
        c.l(pathBuilderH, 13.0f, 13.25f, 13.0f, 18.0f);
        ImageVector.Builder.m4024addPathoIyEayM$default(builder, e.k(pathBuilderH, 2.5f, -2.5f, 13.0f, 18.0f), defaultFillType, "", solidColor, 0.3f, null, 0.3f, 1.0f, iM3803getButtKaPHkGw, iM3813getBevelLxFBmk8, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null);
        int defaultFillType2 = VectorKt.getDefaultFillType();
        SolidColor solidColor2 = new SolidColor(companion.m3498getBlack0d7_KjU(), null);
        int iM3803getButtKaPHkGw2 = companion2.m3803getButtKaPHkGw();
        int iM3813getBevelLxFBmk82 = companion3.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderQ = w.a.q(2.0f, 4.0f, 16.0f, 20.0f, 4.0f);
        w.a.s(pathBuilderQ, 2.0f, 6.5f, 18.0f, 4.0f);
        a.t(pathBuilderQ, -2.5f, 2.5f, 18.0f);
        a.A(pathBuilderQ, 6.5f, 13.25f, 4.0f, -2.5f);
        c.D(pathBuilderQ, 2.5f, 13.25f, 6.5f, 8.5f);
        w.a.l(pathBuilderQ, 4.0f, 6.0f, 2.5f, 8.5f);
        a.A(pathBuilderQ, 11.0f, 18.0f, 8.5f, -2.5f);
        c.l(pathBuilderQ, 11.0f, 18.0f, 11.0f, 13.25f);
        pathBuilderQ.horizontalLineTo(8.5f);
        pathBuilderQ.verticalLineToRelative(-2.5f);
        pathBuilderQ.horizontalLineTo(11.0f);
        pathBuilderQ.verticalLineTo(13.25f);
        pathBuilderQ.close();
        c.B(pathBuilderQ, 11.0f, 8.5f, 8.5f, 6.0f);
        c.l(pathBuilderQ, 11.0f, 8.5f, 15.5f, 18.0f);
        c.k(pathBuilderQ, 13.0f, -2.5f, 2.5f, 18.0f);
        a.A(pathBuilderQ, 15.5f, 13.25f, 13.0f, -2.5f);
        c.D(pathBuilderQ, 2.5f, 13.25f, 15.5f, 8.5f);
        w.a.l(pathBuilderQ, 13.0f, 6.0f, 2.5f, 8.5f);
        a.C(pathBuilderQ, 20.0f, 18.0f, -2.5f, -2.5f);
        c.l(pathBuilderQ, 20.0f, 18.0f, 20.0f, 13.25f);
        d.z(pathBuilderQ, -2.5f, -2.5f, 20.0f, 13.25f);
        e.m(pathBuilderQ, 20.0f, 8.5f, -2.5f, 6.0f);
        ImageVector imageVectorBuild = ImageVector.Builder.m4024addPathoIyEayM$default(builder, a.m(pathBuilderQ, 20.0f, 8.5f), defaultFillType2, "", solidColor2, 1.0f, null, 1.0f, 1.0f, iM3803getButtKaPHkGw2, iM3813getBevelLxFBmk82, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null).build();
        _viewCompact = imageVectorBuild;
        return imageVectorBuild;
    }
}
