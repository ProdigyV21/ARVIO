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
import androidx.fragment.app.a2;
import kotlin.Metadata;
import v.b;
import v.c;
import v.e;
import w.a;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\"\u0010\u0010\u0000\u001a\u0004\u0018\u00010\u0001X\u0082\u000e¢\u0006\u0002\n\u0000\"\u0015\u0010\u0002\u001a\u00020\u0001*\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"_polyline", "Landroidx/compose/ui/graphics/vector/ImageVector;", "Polyline", "Landroidx/compose/material/icons/Icons$Outlined;", "getPolyline", "(Landroidx/compose/material/icons/Icons$Outlined;)Landroidx/compose/ui/graphics/vector/ImageVector;", "material-icons-extended_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class PolylineKt {
    private static ImageVector _polyline;

    public static final ImageVector getPolyline(Icons.Outlined outlined) {
        ImageVector imageVector = _polyline;
        if (imageVector != null) {
            return imageVector;
        }
        ImageVector.Builder builder = new ImageVector.Builder("Outlined.Polyline", Dp.m5678constructorimpl(24.0f), Dp.m5678constructorimpl(24.0f), 24.0f, 24.0f, 0L, 0, false, 96, null);
        int defaultFillType = VectorKt.getDefaultFillType();
        SolidColor solidColor = new SolidColor(Color.INSTANCE.m3498getBlack0d7_KjU(), null);
        int iM3803getButtKaPHkGw = StrokeCap.INSTANCE.m3803getButtKaPHkGw();
        int iM3813getBevelLxFBmk8 = StrokeJoin.INSTANCE.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderG = a.g(15.0f, 16.0f, 1.26f, -6.0f, -3.0f);
        a2.u(pathBuilderG, -3.17f, 11.7f, 8.0f, 16.0f);
        a.j(pathBuilderG, 2.0f, -6.0f, 4.9f);
        b.m(pathBuilderG, 7.3f, 10.0f, 3.0f, 6.0f);
        e.n(pathBuilderG, 5.0f, 7.0f, 3.5f, 22.0f);
        y.a.q(pathBuilderG, 6.0f, -6.0f, 15.0f);
        v.a.C(pathBuilderG, 12.0f, 4.0f, 2.0f, 2.0f);
        c.D(pathBuilderG, -2.0f, 4.0f, 7.0f, 14.0f);
        c.k(pathBuilderG, 5.0f, -2.0f, 2.0f, 14.0f);
        v.a.C(pathBuilderG, 19.0f, 20.0f, -2.0f, -2.0f);
        ImageVector imageVectorBuild = ImageVector.Builder.m4024addPathoIyEayM$default(builder, v.a.e(pathBuilderG, 2.0f, 20.0f), defaultFillType, "", solidColor, 1.0f, null, 1.0f, 1.0f, iM3803getButtKaPHkGw, iM3813getBevelLxFBmk8, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null).build();
        _polyline = imageVectorBuild;
        return imageVectorBuild;
    }
}
