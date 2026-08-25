package androidx.compose.material.icons.outlined;

import a0.a;
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
import v.e;
import v.f;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\"\u0010\u0010\u0000\u001a\u0004\u0018\u00010\u0001X\u0082\u000e¢\u0006\u0002\n\u0000\"\u0015\u0010\u0002\u001a\u00020\u0001*\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"_tty", "Landroidx/compose/ui/graphics/vector/ImageVector;", "Tty", "Landroidx/compose/material/icons/Icons$Outlined;", "getTty", "(Landroidx/compose/material/icons/Icons$Outlined;)Landroidx/compose/ui/graphics/vector/ImageVector;", "material-icons-extended_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class TtyKt {
    private static ImageVector _tty;

    public static final ImageVector getTty(Icons.Outlined outlined) {
        ImageVector imageVector = _tty;
        if (imageVector != null) {
            return imageVector;
        }
        ImageVector.Builder builder = new ImageVector.Builder("Outlined.Tty", Dp.m5678constructorimpl(24.0f), Dp.m5678constructorimpl(24.0f), 24.0f, 24.0f, 0L, 0, false, 96, null);
        int defaultFillType = VectorKt.getDefaultFillType();
        SolidColor solidColor = new SolidColor(Color.INSTANCE.m3498getBlack0d7_KjU(), null);
        int iM3803getButtKaPHkGw = StrokeCap.INSTANCE.m3803getButtKaPHkGw();
        int iM3813getBevelLxFBmk8 = StrokeJoin.INSTANCE.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderU = a.u(16.0f, 6.0f, -2.0f, 4.0f, 2.0f);
        v.a.D(pathBuilderU, 6.0f, 18.0f, 7.0f, -2.0f);
        v.a.t(pathBuilderU, 2.0f, 2.0f, 7.0f);
        e.m(pathBuilderU, 19.0f, 9.0f, 2.0f, 7.0f);
        c.D(pathBuilderU, -2.0f, 9.0f, 19.0f, 4.0f);
        f.w(pathBuilderU, -2.0f, 2.0f, 2.0f, 4.0f);
        v.a.C(pathBuilderU, 15.0f, 7.0f, -2.0f, 2.0f);
        c.D(pathBuilderU, 2.0f, 7.0f, 19.0f, 10.0f);
        f.w(pathBuilderU, -2.0f, 2.0f, 2.0f, 10.0f);
        v.a.C(pathBuilderU, 16.0f, 10.0f, -2.0f, 2.0f);
        c.D(pathBuilderU, 2.0f, 10.0f, 13.0f, 4.0f);
        f.w(pathBuilderU, -2.0f, 2.0f, 2.0f, 4.0f);
        pathBuilderU.moveTo(20.0f, 15.82f);
        pathBuilderU.verticalLineToRelative(4.15f);
        pathBuilderU.curveToRelative(0.0f, 0.56f, -0.47f, 1.03f, -1.03f, 1.0f);
        pathBuilderU.curveToRelative(-2.89f, -0.17f, -5.6f, -1.03f, -7.97f, -2.4f);
        pathBuilderU.curveTo(8.27f, 16.99f, 6.01f, 14.73f, 4.43f, 12.0f);
        pathBuilderU.curveTo(3.05f, 9.63f, 2.2f, 6.92f, 2.03f, 4.03f);
        pathBuilderU.curveTo(2.0f, 3.47f, 2.47f, 3.0f, 3.03f, 3.0f);
        pathBuilderU.horizontalLineToRelative(4.15f);
        pathBuilderU.curveToRelative(0.48f, 0.0f, 0.89f, 0.34f, 0.98f, 0.8f);
        pathBuilderU.lineTo(8.9f, 7.48f);
        pathBuilderU.curveToRelative(0.07f, 0.33f, -0.04f, 0.67f, -0.27f, 0.9f);
        pathBuilderU.lineTo(6.1f, 10.9f);
        pathBuilderU.curveToRelative(1.43f, 2.5f, 3.5f, 4.57f, 6.0f, 6.0f);
        pathBuilderU.lineToRelative(2.52f, -2.52f);
        pathBuilderU.curveToRelative(0.24f, -0.24f, 0.58f, -0.34f, 0.9f, -0.27f);
        pathBuilderU.lineToRelative(3.67f, 0.73f);
        pathBuilderU.curveTo(19.66f, 14.93f, 20.0f, 15.34f, 20.0f, 15.82f);
        v.a.z(pathBuilderU, 5.18f, 8.99f, 1.65f, -1.65f);
        pathBuilderU.lineTo(6.36f, 5.0f);
        pathBuilderU.horizontalLineTo(4.13f);
        pathBuilderU.curveTo(4.3f, 6.37f, 4.66f, 7.71f, 5.18f, 8.99f);
        v.a.z(pathBuilderU, 18.0f, 16.64f, -2.34f, -0.47f);
        pathBuilderU.lineToRelative(-1.65f, 1.65f);
        pathBuilderU.curveToRelative(1.28f, 0.52f, 2.63f, 0.87f, 3.99f, 1.05f);
        w.a.k(pathBuilderU, 16.64f, 20.0f, 4.0f, 2.0f);
        w.a.o(pathBuilderU, 2.0f, 4.0f, 20.0f);
        v.a.C(pathBuilderU, 20.0f, 12.0f, 2.0f, -2.0f);
        c.D(pathBuilderU, -2.0f, 12.0f, 13.0f, 10.0f);
        ImageVector imageVectorBuild = ImageVector.Builder.m4024addPathoIyEayM$default(builder, e.f(pathBuilderU, -2.0f, 2.0f, 2.0f, 10.0f), defaultFillType, "", solidColor, 1.0f, null, 1.0f, 1.0f, iM3803getButtKaPHkGw, iM3813getBevelLxFBmk8, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null).build();
        _tty = imageVectorBuild;
        return imageVectorBuild;
    }
}
