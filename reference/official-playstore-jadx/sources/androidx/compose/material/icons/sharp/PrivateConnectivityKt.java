package androidx.compose.material.icons.sharp;

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
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\"\u0010\u0010\u0000\u001a\u0004\u0018\u00010\u0001X\u0082\u000e¢\u0006\u0002\n\u0000\"\u0015\u0010\u0002\u001a\u00020\u0001*\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"_privateConnectivity", "Landroidx/compose/ui/graphics/vector/ImageVector;", "PrivateConnectivity", "Landroidx/compose/material/icons/Icons$Sharp;", "getPrivateConnectivity", "(Landroidx/compose/material/icons/Icons$Sharp;)Landroidx/compose/ui/graphics/vector/ImageVector;", "material-icons-extended_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class PrivateConnectivityKt {
    private static ImageVector _privateConnectivity;

    public static final ImageVector getPrivateConnectivity(Icons.Sharp sharp) {
        ImageVector imageVector = _privateConnectivity;
        if (imageVector != null) {
            return imageVector;
        }
        ImageVector.Builder builder = new ImageVector.Builder("Sharp.PrivateConnectivity", Dp.m5678constructorimpl(24.0f), Dp.m5678constructorimpl(24.0f), 24.0f, 24.0f, 0L, 0, false, 96, null);
        int defaultFillType = VectorKt.getDefaultFillType();
        SolidColor solidColor = new SolidColor(Color.INSTANCE.m3498getBlack0d7_KjU(), null);
        int iM3803getButtKaPHkGw = StrokeCap.INSTANCE.m3803getButtKaPHkGw();
        int iM3813getBevelLxFBmk8 = StrokeJoin.INSTANCE.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderA = c.a(18.93f, 11.0f);
        pathBuilderA.curveToRelative(-0.49f, -3.39f, -3.4f, -6.0f, -6.93f, -6.0f);
        pathBuilderA.reflectiveCurveToRelative(-6.44f, 2.61f, -6.93f, 6.0f);
        a.n(pathBuilderA, 2.0f, 2.0f, 3.07f);
        pathBuilderA.curveToRelative(0.49f, 3.39f, 3.4f, 6.0f, 6.93f, 6.0f);
        pathBuilderA.reflectiveCurveToRelative(6.44f, -2.61f, 6.93f, -6.0f);
        b.e(pathBuilderA, 22.0f, -2.0f, 18.93f);
        c.u(pathBuilderA, 15.0f, 10.5f, 5.0f, 9.0f);
        e.l(pathBuilderA, -5.0f, 1.0f, -1.0f);
        pathBuilderA.curveToRelative(0.0f, -1.21f, 1.08f, -2.18f, 2.34f, -1.97f);
        pathBuilderA.curveTo(13.32f, 7.69f, 14.0f, 8.61f, 14.0f, 9.61f);
        f.v(pathBuilderA, 0.0f, 0.89f, 15.0f);
        pathBuilderA.moveTo(12.75f, 13.0f);
        pathBuilderA.curveToRelative(0.0f, 0.41f, -0.34f, 0.75f, -0.75f, 0.75f);
        pathBuilderA.reflectiveCurveToRelative(-0.75f, -0.34f, -0.75f, -0.75f);
        pathBuilderA.curveToRelative(0.0f, -0.41f, 0.34f, -0.75f, 0.75f, -0.75f);
        pathBuilderA.reflectiveCurveTo(12.75f, 12.59f, 12.75f, 13.0f);
        c.y(pathBuilderA, 13.0f, 9.5f, 1.0f, -2.0f);
        pathBuilderA.verticalLineToRelative(-1.0f);
        pathBuilderA.curveToRelative(0.0f, -0.55f, 0.45f, -1.0f, 1.0f, -1.0f);
        ImageVector imageVectorBuild = ImageVector.Builder.m4024addPathoIyEayM$default(builder, e.u(pathBuilderA, 13.0f, 8.95f, 13.0f, 9.5f), defaultFillType, "", solidColor, 1.0f, null, 1.0f, 1.0f, iM3803getButtKaPHkGw, iM3813getBevelLxFBmk8, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null).build();
        _privateConnectivity = imageVectorBuild;
        return imageVectorBuild;
    }
}
