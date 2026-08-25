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
import v.e;
import v.f;
import y.a;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\"\u0010\u0010\u0000\u001a\u0004\u0018\u00010\u0001X\u0082\u000e¢\u0006\u0002\n\u0000\"\u0015\u0010\u0002\u001a\u00020\u0001*\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"__4gPlusMobiledata", "Landroidx/compose/ui/graphics/vector/ImageVector;", "_4gPlusMobiledata", "Landroidx/compose/material/icons/Icons$Outlined;", "get_4gPlusMobiledata", "(Landroidx/compose/material/icons/Icons$Outlined;)Landroidx/compose/ui/graphics/vector/ImageVector;", "material-icons-extended_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class _4gPlusMobiledataKt {
    private static ImageVector __4gPlusMobiledata;

    public static final ImageVector get_4gPlusMobiledata(Icons.Outlined outlined) {
        ImageVector imageVector = __4gPlusMobiledata;
        if (imageVector != null) {
            return imageVector;
        }
        ImageVector.Builder builder = new ImageVector.Builder("Outlined._4gPlusMobiledata", Dp.m5678constructorimpl(24.0f), Dp.m5678constructorimpl(24.0f), 24.0f, 24.0f, 0L, 0, false, 96, null);
        int defaultFillType = VectorKt.getDefaultFillType();
        SolidColor solidColor = new SolidColor(Color.INSTANCE.m3498getBlack0d7_KjU(), null);
        int iM3803getButtKaPHkGw = StrokeCap.INSTANCE.m3803getButtKaPHkGw();
        int iM3813getBevelLxFBmk8 = StrokeJoin.INSTANCE.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderU = a.u(13.0f, 11.0f, 2.0f, 2.0f, 2.0f);
        a0.a.d(pathBuilderU, -4.0f, 9.0f, 6.0f);
        pathBuilderU.curveToRelative(0.0f, -1.1f, -0.9f, -2.0f, -2.0f, -2.0f);
        pathBuilderU.horizontalLineToRelative(-4.0f);
        pathBuilderU.curveTo(9.9f, 7.0f, 9.0f, 7.9f, 9.0f, 9.0f);
        pathBuilderU.verticalLineToRelative(6.0f);
        pathBuilderU.curveToRelative(0.0f, 1.1f, 0.9f, 2.0f, 2.0f, 2.0f);
        pathBuilderU.horizontalLineToRelative(4.0f);
        pathBuilderU.curveToRelative(1.1f, 0.0f, 2.0f, -0.9f, 2.0f, -2.0f);
        e.y(pathBuilderU, -4.0f, 13.0f, 24.0f, 11.0f);
        w.a.C(pathBuilderU, -2.0f, 9.0f, -2.0f, 2.0f);
        v.a.o(pathBuilderU, -2.0f, 2.0f, 2.0f, 2.0f);
        f.w(pathBuilderU, 2.0f, -2.0f, 2.0f, 11.0f);
        v.a.A(pathBuilderU, 7.0f, 7.0f, 5.0f, 5.0f);
        w.a.m(pathBuilderU, 3.0f, 7.0f, 1.0f, 7.0f);
        v.a.o(pathBuilderU, 4.0f, 3.0f, 2.0f, -3.0f);
        ImageVector imageVectorBuild = ImageVector.Builder.m4024addPathoIyEayM$default(builder, e.k(pathBuilderU, 1.0f, -2.0f, 7.0f, 7.0f), defaultFillType, "", solidColor, 1.0f, null, 1.0f, 1.0f, iM3803getButtKaPHkGw, iM3813getBevelLxFBmk8, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null).build();
        __4gPlusMobiledata = imageVectorBuild;
        return imageVectorBuild;
    }
}
