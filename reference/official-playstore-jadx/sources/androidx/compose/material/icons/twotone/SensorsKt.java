package androidx.compose.material.icons.twotone;

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

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\"\u0010\u0010\u0000\u001a\u0004\u0018\u00010\u0001X\u0082\u000e¢\u0006\u0002\n\u0000\"\u0015\u0010\u0002\u001a\u00020\u0001*\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"_sensors", "Landroidx/compose/ui/graphics/vector/ImageVector;", "Sensors", "Landroidx/compose/material/icons/Icons$TwoTone;", "getSensors", "(Landroidx/compose/material/icons/Icons$TwoTone;)Landroidx/compose/ui/graphics/vector/ImageVector;", "material-icons-extended_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class SensorsKt {
    private static ImageVector _sensors;

    public static final ImageVector getSensors(Icons.TwoTone twoTone) {
        ImageVector imageVector = _sensors;
        if (imageVector != null) {
            return imageVector;
        }
        ImageVector.Builder builder = new ImageVector.Builder("TwoTone.Sensors", Dp.m5678constructorimpl(24.0f), Dp.m5678constructorimpl(24.0f), 24.0f, 24.0f, 0L, 0, false, 96, null);
        int defaultFillType = VectorKt.getDefaultFillType();
        SolidColor solidColor = new SolidColor(Color.INSTANCE.m3498getBlack0d7_KjU(), null);
        int iM3803getButtKaPHkGw = StrokeCap.INSTANCE.m3803getButtKaPHkGw();
        int iM3813getBevelLxFBmk8 = StrokeJoin.INSTANCE.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderA = c.a(7.76f, 16.24f);
        pathBuilderA.curveTo(6.67f, 15.16f, 6.0f, 13.66f, 6.0f, 12.0f);
        pathBuilderA.reflectiveCurveToRelative(0.67f, -3.16f, 1.76f, -4.24f);
        pathBuilderA.lineToRelative(1.42f, 1.42f);
        pathBuilderA.curveTo(8.45f, 9.9f, 8.0f, 10.9f, 8.0f, 12.0f);
        pathBuilderA.curveToRelative(0.0f, 1.1f, 0.45f, 2.1f, 1.17f, 2.83f);
        c.z(pathBuilderA, 7.76f, 16.24f, 16.24f, 16.24f);
        pathBuilderA.curveTo(17.33f, 15.16f, 18.0f, 13.66f, 18.0f, 12.0f);
        pathBuilderA.reflectiveCurveToRelative(-0.67f, -3.16f, -1.76f, -4.24f);
        pathBuilderA.lineToRelative(-1.42f, 1.42f);
        pathBuilderA.curveTo(15.55f, 9.9f, 16.0f, 10.9f, 16.0f, 12.0f);
        pathBuilderA.curveToRelative(0.0f, 1.1f, -0.45f, 2.1f, -1.17f, 2.83f);
        c.z(pathBuilderA, 16.24f, 16.24f, 12.0f, 10.0f);
        pathBuilderA.curveToRelative(-1.1f, 0.0f, -2.0f, 0.9f, -2.0f, 2.0f);
        pathBuilderA.reflectiveCurveToRelative(0.9f, 2.0f, 2.0f, 2.0f);
        pathBuilderA.reflectiveCurveToRelative(2.0f, -0.9f, 2.0f, -2.0f);
        pathBuilderA.reflectiveCurveTo(13.1f, 10.0f, 12.0f, 10.0f);
        pathBuilderA.close();
        pathBuilderA.moveTo(20.0f, 12.0f);
        pathBuilderA.curveToRelative(0.0f, 2.21f, -0.9f, 4.21f, -2.35f, 5.65f);
        pathBuilderA.lineToRelative(1.42f, 1.42f);
        pathBuilderA.curveTo(20.88f, 17.26f, 22.0f, 14.76f, 22.0f, 12.0f);
        pathBuilderA.reflectiveCurveToRelative(-1.12f, -5.26f, -2.93f, -7.07f);
        pathBuilderA.lineToRelative(-1.42f, 1.42f);
        pathBuilderA.curveTo(19.1f, 7.79f, 20.0f, 9.79f, 20.0f, 12.0f);
        a.p(pathBuilderA, 6.35f, 6.35f, 4.93f, 4.93f);
        pathBuilderA.curveTo(3.12f, 6.74f, 2.0f, 9.24f, 2.0f, 12.0f);
        pathBuilderA.reflectiveCurveToRelative(1.12f, 5.26f, 2.93f, 7.07f);
        pathBuilderA.lineToRelative(1.42f, -1.42f);
        pathBuilderA.curveTo(4.9f, 16.21f, 4.0f, 14.21f, 4.0f, 12.0f);
        ImageVector imageVectorBuild = ImageVector.Builder.m4024addPathoIyEayM$default(builder, e.u(pathBuilderA, 4.9f, 7.79f, 6.35f, 6.35f), defaultFillType, "", solidColor, 1.0f, null, 1.0f, 1.0f, iM3803getButtKaPHkGw, iM3813getBevelLxFBmk8, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null).build();
        _sensors = imageVectorBuild;
        return imageVectorBuild;
    }
}
