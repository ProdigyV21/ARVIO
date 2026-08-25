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
import v.f;
import y.a;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\"\u0010\u0010\u0000\u001a\u0004\u0018\u00010\u0001X\u0082\u000e¢\u0006\u0002\n\u0000\"\u0015\u0010\u0002\u001a\u00020\u0001*\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"_appsOutage", "Landroidx/compose/ui/graphics/vector/ImageVector;", "AppsOutage", "Landroidx/compose/material/icons/Icons$Outlined;", "getAppsOutage", "(Landroidx/compose/material/icons/Icons$Outlined;)Landroidx/compose/ui/graphics/vector/ImageVector;", "material-icons-extended_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class AppsOutageKt {
    private static ImageVector _appsOutage;

    public static final ImageVector getAppsOutage(Icons.Outlined outlined) {
        ImageVector imageVector = _appsOutage;
        if (imageVector != null) {
            return imageVector;
        }
        ImageVector.Builder builder = new ImageVector.Builder("Outlined.AppsOutage", Dp.m5678constructorimpl(24.0f), Dp.m5678constructorimpl(24.0f), 24.0f, 24.0f, 0L, 0, false, 96, null);
        int defaultFillType = VectorKt.getDefaultFillType();
        SolidColor solidColor = new SolidColor(Color.INSTANCE.m3498getBlack0d7_KjU(), null);
        int iM3803getButtKaPHkGw = StrokeCap.INSTANCE.m3803getButtKaPHkGw();
        int iM3813getBevelLxFBmk8 = StrokeJoin.INSTANCE.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderS = a.s(4.0f, 8.0f, 4.0f, 4.0f, 4.0f);
        v.a.D(pathBuilderS, 8.0f, 10.0f, 20.0f, 4.0f);
        v.a.t(pathBuilderS, -4.0f, -4.0f, 20.0f);
        v.a.C(pathBuilderS, 4.0f, 20.0f, 4.0f, -4.0f);
        c.l(pathBuilderS, 4.0f, 20.0f, 4.0f, 14.0f);
        d.z(pathBuilderS, 4.0f, -4.0f, 4.0f, 14.0f);
        v.a.C(pathBuilderS, 10.0f, 14.0f, 4.0f, -4.0f);
        c.D(pathBuilderS, -4.0f, 14.0f, 16.0f, 20.0f);
        f.w(pathBuilderS, 4.0f, -4.0f, -4.0f, 20.0f);
        pathBuilderS.moveTo(19.0f, 0.0f);
        pathBuilderS.curveToRelative(-2.76f, 0.0f, -5.0f, 2.24f, -5.0f, 5.0f);
        pathBuilderS.reflectiveCurveToRelative(2.24f, 5.0f, 5.0f, 5.0f);
        pathBuilderS.reflectiveCurveToRelative(5.0f, -2.24f, 5.0f, -5.0f);
        pathBuilderS.reflectiveCurveTo(21.76f, 0.0f, 19.0f, 0.0f);
        v.a.u(pathBuilderS, 19.5f, 8.0f, -1.0f, 7.0f);
        c.D(pathBuilderS, 1.0f, 8.0f, 19.5f, 6.0f);
        f.A(pathBuilderS, -1.0f, 2.0f, 1.0f, 6.0f);
        v.a.C(pathBuilderS, 16.0f, 14.0f, 4.0f, -2.07f);
        pathBuilderS.curveTo(19.67f, 11.98f, 19.34f, 12.0f, 19.0f, 12.0f);
        pathBuilderS.curveToRelative(-1.07f, 0.0f, -2.09f, -0.24f, -3.0f, -0.68f);
        w.a.k(pathBuilderS, 14.0f, 10.0f, 4.0f, 4.0f);
        pathBuilderS.horizontalLineToRelative(2.68f);
        pathBuilderS.curveTo(12.24f, 7.09f, 12.0f, 6.07f, 12.0f, 5.0f);
        pathBuilderS.curveToRelative(0.0f, -0.34f, 0.02f, -0.67f, 0.07f, -1.0f);
        ImageVector imageVectorBuild = ImageVector.Builder.m4024addPathoIyEayM$default(builder, v.a.l(pathBuilderS, 10.0f), defaultFillType, "", solidColor, 1.0f, null, 1.0f, 1.0f, iM3803getButtKaPHkGw, iM3813getBevelLxFBmk8, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null).build();
        _appsOutage = imageVectorBuild;
        return imageVectorBuild;
    }
}
