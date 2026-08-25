package androidx.compose.material.icons.sharp;

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

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\"\u0010\u0010\u0000\u001a\u0004\u0018\u00010\u0001X\u0082\u000e¢\u0006\u0002\n\u0000\"\u0015\u0010\u0002\u001a\u00020\u0001*\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"_http", "Landroidx/compose/ui/graphics/vector/ImageVector;", "Http", "Landroidx/compose/material/icons/Icons$Sharp;", "getHttp", "(Landroidx/compose/material/icons/Icons$Sharp;)Landroidx/compose/ui/graphics/vector/ImageVector;", "material-icons-extended_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class HttpKt {
    private static ImageVector _http;

    public static final ImageVector getHttp(Icons.Sharp sharp) {
        ImageVector imageVector = _http;
        if (imageVector != null) {
            return imageVector;
        }
        ImageVector.Builder builder = new ImageVector.Builder("Sharp.Http", Dp.m5678constructorimpl(24.0f), Dp.m5678constructorimpl(24.0f), 24.0f, 24.0f, 0L, 0, false, 96, null);
        int defaultFillType = VectorKt.getDefaultFillType();
        SolidColor solidColor = new SolidColor(Color.INSTANCE.m3498getBlack0d7_KjU(), null);
        int iM3803getButtKaPHkGw = StrokeCap.INSTANCE.m3803getButtKaPHkGw();
        int iM3813getBevelLxFBmk8 = StrokeJoin.INSTANCE.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderI = e.i(4.5f, 11.0f, -2.0f, 2.5f, 9.0f);
        a.h(pathBuilderI, 1.0f, 9.0f, 6.0f, 1.5f);
        c.w(pathBuilderI, -2.5f, 2.0f, 4.5f, 15.0f);
        pathBuilderI.lineTo(6.0f, 15.0f);
        pathBuilderI.lineTo(6.0f, 9.0f);
        d.f(pathBuilderI, 4.5f, 9.0f, 2.0f);
        pathBuilderI.moveTo(7.0f, 10.5f);
        pathBuilderI.horizontalLineToRelative(1.5f);
        pathBuilderI.lineTo(8.5f, 15.0f);
        a.h(pathBuilderI, 10.0f, 15.0f, -4.5f, 1.5f);
        pathBuilderI.lineTo(11.5f, 9.0f);
        d.f(pathBuilderI, 7.0f, 9.0f, 1.5f);
        pathBuilderI.moveTo(12.5f, 10.5f);
        pathBuilderI.lineTo(14.0f, 10.5f);
        b.A(pathBuilderI, 14.0f, 15.0f, 1.5f, -4.5f);
        pathBuilderI.lineTo(17.0f, 10.5f);
        b.g(pathBuilderI, 17.0f, 9.0f, -4.5f, 1.5f);
        a.C(pathBuilderI, 23.0f, 9.0f, -5.0f, 6.0f);
        c.q(pathBuilderI, 1.5f, -2.0f, 23.0f, 13.0f);
        c.z(pathBuilderI, 23.0f, 9.0f, 21.5f, 11.5f);
        ImageVector imageVectorBuild = ImageVector.Builder.m4024addPathoIyEayM$default(builder, a0.a.h(pathBuilderI, -2.0f, -1.0f, 2.0f, 1.0f), defaultFillType, "", solidColor, 1.0f, null, 1.0f, 1.0f, iM3803getButtKaPHkGw, iM3813getBevelLxFBmk8, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null).build();
        _http = imageVectorBuild;
        return imageVectorBuild;
    }
}
