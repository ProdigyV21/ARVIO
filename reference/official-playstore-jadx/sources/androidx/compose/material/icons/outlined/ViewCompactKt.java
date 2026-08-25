package androidx.compose.material.icons.outlined;

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
import v.e;
import w.a;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\"\u0010\u0010\u0000\u001a\u0004\u0018\u00010\u0001X\u0082\u000e¢\u0006\u0002\n\u0000\"\u0015\u0010\u0002\u001a\u00020\u0001*\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"_viewCompact", "Landroidx/compose/ui/graphics/vector/ImageVector;", "ViewCompact", "Landroidx/compose/material/icons/Icons$Outlined;", "getViewCompact", "(Landroidx/compose/material/icons/Icons$Outlined;)Landroidx/compose/ui/graphics/vector/ImageVector;", "material-icons-extended_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class ViewCompactKt {
    private static ImageVector _viewCompact;

    public static final ImageVector getViewCompact(Icons.Outlined outlined) {
        ImageVector imageVector = _viewCompact;
        if (imageVector != null) {
            return imageVector;
        }
        ImageVector.Builder builder = new ImageVector.Builder("Outlined.ViewCompact", Dp.m5678constructorimpl(24.0f), Dp.m5678constructorimpl(24.0f), 24.0f, 24.0f, 0L, 0, false, 96, null);
        int defaultFillType = VectorKt.getDefaultFillType();
        SolidColor solidColor = new SolidColor(Color.INSTANCE.m3498getBlack0d7_KjU(), null);
        int iM3803getButtKaPHkGw = StrokeCap.INSTANCE.m3803getButtKaPHkGw();
        int iM3813getBevelLxFBmk8 = StrokeJoin.INSTANCE.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderQ = a.q(2.0f, 4.0f, 16.0f, 20.0f, 4.0f);
        a.s(pathBuilderQ, 2.0f, 6.5f, 18.0f, 4.0f);
        v.a.t(pathBuilderQ, -2.5f, 2.5f, 18.0f);
        v.a.A(pathBuilderQ, 6.5f, 13.25f, 4.0f, -2.5f);
        c.D(pathBuilderQ, 2.5f, 13.25f, 6.5f, 8.5f);
        a.l(pathBuilderQ, 4.0f, 6.0f, 2.5f, 8.5f);
        v.a.A(pathBuilderQ, 11.0f, 18.0f, 8.5f, -2.5f);
        c.l(pathBuilderQ, 11.0f, 18.0f, 11.0f, 13.25f);
        pathBuilderQ.horizontalLineTo(8.5f);
        pathBuilderQ.verticalLineToRelative(-2.5f);
        pathBuilderQ.horizontalLineTo(11.0f);
        pathBuilderQ.verticalLineTo(13.25f);
        pathBuilderQ.close();
        c.B(pathBuilderQ, 11.0f, 8.5f, 8.5f, 6.0f);
        c.l(pathBuilderQ, 11.0f, 8.5f, 15.5f, 18.0f);
        c.k(pathBuilderQ, 13.0f, -2.5f, 2.5f, 18.0f);
        v.a.A(pathBuilderQ, 15.5f, 13.25f, 13.0f, -2.5f);
        c.D(pathBuilderQ, 2.5f, 13.25f, 15.5f, 8.5f);
        a.l(pathBuilderQ, 13.0f, 6.0f, 2.5f, 8.5f);
        v.a.C(pathBuilderQ, 20.0f, 18.0f, -2.5f, -2.5f);
        c.l(pathBuilderQ, 20.0f, 18.0f, 20.0f, 13.25f);
        d.z(pathBuilderQ, -2.5f, -2.5f, 20.0f, 13.25f);
        e.m(pathBuilderQ, 20.0f, 8.5f, -2.5f, 6.0f);
        ImageVector imageVectorBuild = ImageVector.Builder.m4024addPathoIyEayM$default(builder, v.a.m(pathBuilderQ, 20.0f, 8.5f), defaultFillType, "", solidColor, 1.0f, null, 1.0f, 1.0f, iM3803getButtKaPHkGw, iM3813getBevelLxFBmk8, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null).build();
        _viewCompact = imageVectorBuild;
        return imageVectorBuild;
    }
}
