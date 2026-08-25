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
import v.b;
import v.c;
import v.e;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\"\u0010\u0010\u0000\u001a\u0004\u0018\u00010\u0001X\u0082\u000e¢\u0006\u0002\n\u0000\"\u0015\u0010\u0002\u001a\u00020\u0001*\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"_rocketLaunch", "Landroidx/compose/ui/graphics/vector/ImageVector;", "RocketLaunch", "Landroidx/compose/material/icons/Icons$Sharp;", "getRocketLaunch", "(Landroidx/compose/material/icons/Icons$Sharp;)Landroidx/compose/ui/graphics/vector/ImageVector;", "material-icons-extended_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class RocketLaunchKt {
    private static ImageVector _rocketLaunch;

    public static final ImageVector getRocketLaunch(Icons.Sharp sharp) {
        ImageVector imageVector = _rocketLaunch;
        if (imageVector != null) {
            return imageVector;
        }
        ImageVector.Builder builder = new ImageVector.Builder("Sharp.RocketLaunch", Dp.m5678constructorimpl(24.0f), Dp.m5678constructorimpl(24.0f), 24.0f, 24.0f, 0L, 0, false, 96, null);
        int defaultFillType = VectorKt.getDefaultFillType();
        SolidColor solidColor = new SolidColor(Color.INSTANCE.m3498getBlack0d7_KjU(), null);
        int iM3803getButtKaPHkGw = StrokeCap.INSTANCE.m3803getButtKaPHkGw();
        int iM3813getBevelLxFBmk8 = StrokeJoin.INSTANCE.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderA = c.a(9.19f, 6.35f);
        pathBuilderA.curveToRelative(-2.04f, 2.29f, -3.44f, 5.58f, -3.57f, 5.89f);
        pathBuilderA.lineTo(2.0f, 10.69f);
        pathBuilderA.lineToRelative(4.81f, -4.81f);
        b.y(pathBuilderA, 9.19f, 6.35f, 9.19f, 6.35f);
        pathBuilderA.moveTo(11.17f, 17.0f);
        pathBuilderA.curveToRelative(0.0f, 0.0f, 3.74f, -1.55f, 5.89f, -3.7f);
        pathBuilderA.curveToRelative(5.4f, -5.4f, 4.5f, -9.62f, 4.21f, -10.57f);
        pathBuilderA.curveToRelative(-0.95f, -0.3f, -5.17f, -1.19f, -10.57f, 4.21f);
        pathBuilderA.curveTo(8.55f, 9.09f, 7.0f, 12.83f, 7.0f, 12.83f);
        c.z(pathBuilderA, 11.17f, 17.0f, 17.65f, 14.81f);
        pathBuilderA.curveToRelative(-2.29f, 2.04f, -5.58f, 3.44f, -5.89f, 3.57f);
        pathBuilderA.lineTo(13.31f, 22.0f);
        pathBuilderA.lineToRelative(4.81f, -4.81f);
        b.y(pathBuilderA, 17.65f, 14.81f, 17.65f, 14.81f);
        pathBuilderA.moveTo(9.0f, 18.0f);
        pathBuilderA.curveToRelative(0.0f, 0.83f, -0.34f, 1.58f, -0.88f, 2.12f);
        pathBuilderA.curveTo(6.94f, 21.3f, 2.0f, 22.0f, 2.0f, 22.0f);
        pathBuilderA.reflectiveCurveToRelative(0.7f, -4.94f, 1.88f, -6.12f);
        pathBuilderA.curveTo(4.42f, 15.34f, 5.17f, 15.0f, 6.0f, 15.0f);
        pathBuilderA.curveTo(7.66f, 15.0f, 9.0f, 16.34f, 9.0f, 18.0f);
        pathBuilderA.close();
        pathBuilderA.moveTo(13.0f, 9.0f);
        pathBuilderA.curveToRelative(0.0f, -1.1f, 0.9f, -2.0f, 2.0f, -2.0f);
        pathBuilderA.reflectiveCurveToRelative(2.0f, 0.9f, 2.0f, 2.0f);
        pathBuilderA.reflectiveCurveToRelative(-0.9f, 2.0f, -2.0f, 2.0f);
        ImageVector imageVectorBuild = ImageVector.Builder.m4024addPathoIyEayM$default(builder, e.u(pathBuilderA, 13.0f, 10.1f, 13.0f, 9.0f), defaultFillType, "", solidColor, 1.0f, null, 1.0f, 1.0f, iM3803getButtKaPHkGw, iM3813getBevelLxFBmk8, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null).build();
        _rocketLaunch = imageVectorBuild;
        return imageVectorBuild;
    }
}
