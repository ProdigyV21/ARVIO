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
import v.f;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\"\u0010\u0010\u0000\u001a\u0004\u0018\u00010\u0001X\u0082\u000e¢\u0006\u0002\n\u0000\"\u0015\u0010\u0002\u001a\u00020\u0001*\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"_heatPump", "Landroidx/compose/ui/graphics/vector/ImageVector;", "HeatPump", "Landroidx/compose/material/icons/Icons$Sharp;", "getHeatPump", "(Landroidx/compose/material/icons/Icons$Sharp;)Landroidx/compose/ui/graphics/vector/ImageVector;", "material-icons-extended_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class HeatPumpKt {
    private static ImageVector _heatPump;

    public static final ImageVector getHeatPump(Icons.Sharp sharp) {
        ImageVector imageVector = _heatPump;
        if (imageVector != null) {
            return imageVector;
        }
        ImageVector.Builder builder = new ImageVector.Builder("Sharp.HeatPump", Dp.m5678constructorimpl(24.0f), Dp.m5678constructorimpl(24.0f), 24.0f, 24.0f, 0L, 0, false, 96, null);
        int defaultFillType = VectorKt.getDefaultFillType();
        SolidColor solidColor = new SolidColor(Color.INSTANCE.m3498getBlack0d7_KjU(), null);
        int iM3803getButtKaPHkGw = StrokeCap.INSTANCE.m3803getButtKaPHkGw();
        int iM3813getBevelLxFBmk8 = StrokeJoin.INSTANCE.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderH = b.h(21.0f, 3.0f, 3.0f, 18.0f, 18.0f);
        f.r(pathBuilderH, 3.0f, 12.75f, 7.08f);
        pathBuilderH.curveToRelative(0.82f, 0.12f, 1.57f, 0.44f, 2.2f, 0.91f);
        c.e(pathBuilderH, -2.2f, 2.2f, 7.08f);
        pathBuilderH.moveTo(11.25f, 7.08f);
        pathBuilderH.verticalLineToRelative(3.11f);
        pathBuilderH.lineToRelative(-2.2f, -2.2f);
        pathBuilderH.curveTo(9.68f, 7.52f, 10.43f, 7.2f, 11.25f, 7.08f);
        a.z(pathBuilderH, 7.99f, 9.05f, 2.2f, 2.2f);
        pathBuilderH.horizontalLineTo(7.08f);
        pathBuilderH.curveTo(7.2f, 10.43f, 7.52f, 9.68f, 7.99f, 9.05f);
        a.g(pathBuilderH, 7.08f, 12.75f, 3.11f);
        pathBuilderH.lineToRelative(-2.2f, 2.2f);
        pathBuilderH.curveTo(7.52f, 14.32f, 7.2f, 13.57f, 7.08f, 12.75f);
        pathBuilderH.close();
        pathBuilderH.moveTo(11.25f, 16.92f);
        pathBuilderH.curveToRelative(-0.82f, -0.12f, -1.57f, -0.44f, -2.2f, -0.91f);
        c.e(pathBuilderH, 2.2f, -2.2f, 16.92f);
        pathBuilderH.moveTo(12.0f, 13.0f);
        pathBuilderH.curveToRelative(-0.55f, 0.0f, -1.0f, -0.45f, -1.0f, -1.0f);
        pathBuilderH.curveToRelative(0.0f, -0.55f, 0.45f, -1.0f, 1.0f, -1.0f);
        pathBuilderH.reflectiveCurveToRelative(1.0f, 0.45f, 1.0f, 1.0f);
        pathBuilderH.curveTo(13.0f, 12.55f, 12.55f, 13.0f, 12.0f, 13.0f);
        d.k(pathBuilderH, 12.75f, 16.92f, -3.11f);
        pathBuilderH.lineToRelative(2.2f, 2.2f);
        pathBuilderH.curveTo(14.32f, 16.48f, 13.57f, 16.8f, 12.75f, 16.92f);
        a.z(pathBuilderH, 16.01f, 14.95f, -2.2f, -2.2f);
        pathBuilderH.horizontalLineToRelative(3.11f);
        pathBuilderH.curveTo(16.8f, 13.57f, 16.48f, 14.32f, 16.01f, 14.95f);
        a.z(pathBuilderH, 13.81f, 11.25f, 2.2f, -2.2f);
        pathBuilderH.curveToRelative(0.47f, 0.64f, 0.79f, 1.39f, 0.91f, 2.2f);
        ImageVector imageVectorBuild = ImageVector.Builder.m4024addPathoIyEayM$default(builder, a.l(pathBuilderH, 13.81f), defaultFillType, "", solidColor, 1.0f, null, 1.0f, 1.0f, iM3803getButtKaPHkGw, iM3813getBevelLxFBmk8, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null).build();
        _heatPump = imageVectorBuild;
        return imageVectorBuild;
    }
}
