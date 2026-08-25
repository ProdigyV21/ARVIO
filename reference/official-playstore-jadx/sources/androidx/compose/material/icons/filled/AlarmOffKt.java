package androidx.compose.material.icons.filled;

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

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\"\u0010\u0010\u0000\u001a\u0004\u0018\u00010\u0001X\u0082\u000e¢\u0006\u0002\n\u0000\"\u0015\u0010\u0002\u001a\u00020\u0001*\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"_alarmOff", "Landroidx/compose/ui/graphics/vector/ImageVector;", "AlarmOff", "Landroidx/compose/material/icons/Icons$Filled;", "getAlarmOff", "(Landroidx/compose/material/icons/Icons$Filled;)Landroidx/compose/ui/graphics/vector/ImageVector;", "material-icons-extended_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class AlarmOffKt {
    private static ImageVector _alarmOff;

    public static final ImageVector getAlarmOff(Icons.Filled filled) {
        ImageVector imageVector = _alarmOff;
        if (imageVector != null) {
            return imageVector;
        }
        ImageVector.Builder builder = new ImageVector.Builder("Filled.AlarmOff", Dp.m5678constructorimpl(24.0f), Dp.m5678constructorimpl(24.0f), 24.0f, 24.0f, 0L, 0, false, 96, null);
        int defaultFillType = VectorKt.getDefaultFillType();
        SolidColor solidColor = new SolidColor(Color.INSTANCE.m3498getBlack0d7_KjU(), null);
        int iM3803getButtKaPHkGw = StrokeCap.INSTANCE.m3803getButtKaPHkGw();
        int iM3813getBevelLxFBmk8 = StrokeJoin.INSTANCE.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderA = c.a(12.0f, 6.0f);
        pathBuilderA.curveToRelative(3.87f, 0.0f, 7.0f, 3.13f, 7.0f, 7.0f);
        pathBuilderA.curveToRelative(0.0f, 0.84f, -0.16f, 1.65f, -0.43f, 2.4f);
        pathBuilderA.lineToRelative(1.52f, 1.52f);
        pathBuilderA.curveToRelative(0.58f, -1.19f, 0.91f, -2.51f, 0.91f, -3.92f);
        pathBuilderA.curveToRelative(0.0f, -4.97f, -4.03f, -9.0f, -9.0f, -9.0f);
        pathBuilderA.curveToRelative(-1.41f, 0.0f, -2.73f, 0.33f, -3.92f, 0.91f);
        pathBuilderA.lineTo(9.6f, 6.43f);
        pathBuilderA.curveTo(10.35f, 6.16f, 11.16f, 6.0f, 12.0f, 6.0f);
        a.z(pathBuilderA, 22.0f, 5.72f, -4.6f, -3.86f);
        pathBuilderA.lineToRelative(-1.29f, 1.53f);
        d.C(pathBuilderA, 4.6f, 3.86f, 22.0f, 5.72f);
        pathBuilderA.moveTo(2.92f, 2.29f);
        pathBuilderA.lineTo(1.65f, 3.57f);
        pathBuilderA.lineTo(2.98f, 4.9f);
        pathBuilderA.lineToRelative(-1.11f, 0.93f);
        pathBuilderA.lineToRelative(1.42f, 1.42f);
        pathBuilderA.lineToRelative(1.11f, -0.94f);
        pathBuilderA.lineToRelative(0.8f, 0.8f);
        pathBuilderA.curveTo(3.83f, 8.69f, 3.0f, 10.75f, 3.0f, 13.0f);
        pathBuilderA.curveToRelative(0.0f, 4.97f, 4.02f, 9.0f, 9.0f, 9.0f);
        pathBuilderA.curveToRelative(2.25f, 0.0f, 4.31f, -0.83f, 5.89f, -2.2f);
        pathBuilderA.lineToRelative(2.2f, 2.2f);
        pathBuilderA.lineToRelative(1.27f, -1.27f);
        d.l(pathBuilderA, 3.89f, 3.27f, -0.97f, -0.98f);
        pathBuilderA.moveTo(16.47f, 18.39f);
        pathBuilderA.curveTo(15.26f, 19.39f, 13.7f, 20.0f, 12.0f, 20.0f);
        pathBuilderA.curveToRelative(-3.87f, 0.0f, -7.0f, -3.13f, -7.0f, -7.0f);
        pathBuilderA.curveToRelative(0.0f, -1.7f, 0.61f, -3.26f, 1.61f, -4.47f);
        b.C(pathBuilderA, 9.86f, 9.86f, 8.02f, 3.28f);
        pathBuilderA.lineTo(6.6f, 1.86f);
        pathBuilderA.lineToRelative(-0.86f, 0.71f);
        ImageVector imageVectorBuild = ImageVector.Builder.m4024addPathoIyEayM$default(builder, b.t(pathBuilderA, 1.42f, 1.42f, 0.86f, -0.71f), defaultFillType, "", solidColor, 1.0f, null, 1.0f, 1.0f, iM3803getButtKaPHkGw, iM3813getBevelLxFBmk8, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null).build();
        _alarmOff = imageVectorBuild;
        return imageVectorBuild;
    }
}
