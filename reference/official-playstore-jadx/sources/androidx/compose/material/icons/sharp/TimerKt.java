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
import v.e;
import v.f;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\"\u0010\u0010\u0000\u001a\u0004\u0018\u00010\u0001X\u0082\u000e¢\u0006\u0002\n\u0000\"\u0015\u0010\u0002\u001a\u00020\u0001*\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"_timer", "Landroidx/compose/ui/graphics/vector/ImageVector;", "Timer", "Landroidx/compose/material/icons/Icons$Sharp;", "getTimer", "(Landroidx/compose/material/icons/Icons$Sharp;)Landroidx/compose/ui/graphics/vector/ImageVector;", "material-icons-extended_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class TimerKt {
    private static ImageVector _timer;

    public static final ImageVector getTimer(Icons.Sharp sharp) {
        ImageVector imageVector = _timer;
        if (imageVector != null) {
            return imageVector;
        }
        ImageVector.Builder builder = new ImageVector.Builder("Sharp.Timer", Dp.m5678constructorimpl(24.0f), Dp.m5678constructorimpl(24.0f), 24.0f, 24.0f, 0L, 0, false, 96, null);
        int defaultFillType = VectorKt.getDefaultFillType();
        SolidColor solidColor = new SolidColor(Color.INSTANCE.m3498getBlack0d7_KjU(), null);
        int iM3803getButtKaPHkGw = StrokeCap.INSTANCE.m3803getButtKaPHkGw();
        int iM3813getBevelLxFBmk8 = StrokeJoin.INSTANCE.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderH = b.h(15.0f, 1.0f, 9.0f, 2.0f, 6.0f);
        a.D(pathBuilderH, 1.0f, 11.0f, 14.0f, 2.0f);
        f.k(pathBuilderH, 8.0f, -2.0f, 14.0f);
        pathBuilderH.moveTo(19.03f, 7.39f);
        pathBuilderH.lineToRelative(1.42f, -1.42f);
        pathBuilderH.curveToRelative(-0.43f, -0.51f, -0.9f, -0.99f, -1.41f, -1.41f);
        pathBuilderH.lineToRelative(-1.42f, 1.42f);
        pathBuilderH.curveTo(16.07f, 4.74f, 14.12f, 4.0f, 12.0f, 4.0f);
        pathBuilderH.curveToRelative(-4.97f, 0.0f, -9.0f, 4.03f, -9.0f, 9.0f);
        pathBuilderH.reflectiveCurveToRelative(4.02f, 9.0f, 9.0f, 9.0f);
        pathBuilderH.reflectiveCurveToRelative(9.0f, -4.03f, 9.0f, -9.0f);
        pathBuilderH.curveTo(21.0f, 10.88f, 20.26f, 8.93f, 19.03f, 7.39f);
        pathBuilderH.close();
        pathBuilderH.moveTo(12.0f, 20.0f);
        pathBuilderH.curveToRelative(-3.87f, 0.0f, -7.0f, -3.13f, -7.0f, -7.0f);
        pathBuilderH.reflectiveCurveToRelative(3.13f, -7.0f, 7.0f, -7.0f);
        pathBuilderH.reflectiveCurveToRelative(7.0f, 3.13f, 7.0f, 7.0f);
        ImageVector imageVectorBuild = ImageVector.Builder.m4024addPathoIyEayM$default(builder, e.u(pathBuilderH, 15.87f, 20.0f, 12.0f, 20.0f), defaultFillType, "", solidColor, 1.0f, null, 1.0f, 1.0f, iM3803getButtKaPHkGw, iM3813getBevelLxFBmk8, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null).build();
        _timer = imageVectorBuild;
        return imageVectorBuild;
    }
}
