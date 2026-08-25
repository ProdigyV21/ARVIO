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

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\"\u0010\u0010\u0000\u001a\u0004\u0018\u00010\u0001X\u0082\u000e¢\u0006\u0002\n\u0000\"\u0015\u0010\u0002\u001a\u00020\u0001*\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"_alarmOff", "Landroidx/compose/ui/graphics/vector/ImageVector;", "AlarmOff", "Landroidx/compose/material/icons/Icons$Sharp;", "getAlarmOff", "(Landroidx/compose/material/icons/Icons$Sharp;)Landroidx/compose/ui/graphics/vector/ImageVector;", "material-icons-extended_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class AlarmOffKt {
    private static ImageVector _alarmOff;

    public static final ImageVector getAlarmOff(Icons.Sharp sharp) {
        ImageVector imageVector = _alarmOff;
        if (imageVector != null) {
            return imageVector;
        }
        ImageVector.Builder builder = new ImageVector.Builder("Sharp.AlarmOff", Dp.m5678constructorimpl(24.0f), Dp.m5678constructorimpl(24.0f), 24.0f, 24.0f, 0L, 0, false, 96, null);
        int defaultFillType = VectorKt.getDefaultFillType();
        SolidColor solidColor = new SolidColor(Color.INSTANCE.m3498getBlack0d7_KjU(), null);
        int iM3803getButtKaPHkGw = StrokeCap.INSTANCE.m3803getButtKaPHkGw();
        int iM3813getBevelLxFBmk8 = StrokeJoin.INSTANCE.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderA = c.a(10.04f, 6.29f);
        pathBuilderA.curveTo(10.66f, 6.11f, 11.32f, 6.0f, 12.0f, 6.0f);
        pathBuilderA.curveToRelative(3.86f, 0.0f, 7.0f, 3.14f, 7.0f, 7.0f);
        pathBuilderA.curveToRelative(0.0f, 0.68f, -0.11f, 1.34f, -0.29f, 1.96f);
        pathBuilderA.lineToRelative(1.56f, 1.56f);
        pathBuilderA.curveToRelative(0.47f, -1.08f, 0.73f, -2.27f, 0.73f, -3.52f);
        pathBuilderA.curveToRelative(0.0f, -4.97f, -4.03f, -9.0f, -9.0f, -9.0f);
        pathBuilderA.curveToRelative(-1.25f, 0.0f, -2.44f, 0.26f, -3.53f, 0.72f);
        b.C(pathBuilderA, 1.57f, 1.57f, 17.337f, 1.81f);
        pathBuilderA.lineToRelative(4.607f, 3.845f);
        b.D(pathBuilderA, -1.28f, 1.535f, -4.61f, -3.843f);
        pathBuilderA.moveTo(3.02f, 2.1f);
        pathBuilderA.lineTo(1.61f, 3.51f);
        pathBuilderA.lineToRelative(1.37f, 1.37f);
        pathBuilderA.lineToRelative(-0.92f, 0.77f);
        pathBuilderA.lineToRelative(1.28f, 1.54f);
        pathBuilderA.lineToRelative(1.06f, -0.88f);
        pathBuilderA.lineToRelative(0.8f, 0.8f);
        pathBuilderA.curveTo(3.83f, 8.69f, 3.0f, 10.75f, 3.0f, 13.0f);
        pathBuilderA.curveToRelative(0.0f, 4.97f, 4.03f, 9.0f, 9.0f, 9.0f);
        pathBuilderA.curveToRelative(2.25f, 0.0f, 4.31f, -0.83f, 5.89f, -2.2f);
        pathBuilderA.lineToRelative(2.1f, 2.1f);
        d.C(pathBuilderA, 1.41f, -1.41f, 3.02f, 2.1f);
        pathBuilderA.moveTo(12.0f, 20.0f);
        pathBuilderA.curveToRelative(-3.86f, 0.0f, -7.0f, -3.14f, -7.0f, -7.0f);
        pathBuilderA.curveToRelative(0.0f, -1.7f, 0.61f, -3.26f, 1.62f, -4.47f);
        pathBuilderA.lineToRelative(9.85f, 9.85f);
        pathBuilderA.curveTo(15.26f, 19.39f, 13.7f, 20.0f, 12.0f, 20.0f);
        a.z(pathBuilderA, 7.48f, 3.73f, 0.46f, -0.38f);
        ImageVector imageVectorBuild = ImageVector.Builder.m4024addPathoIyEayM$default(builder, b.t(pathBuilderA, -1.28f, -1.54f, -0.6f, 0.5f), defaultFillType, "", solidColor, 1.0f, null, 1.0f, 1.0f, iM3803getButtKaPHkGw, iM3813getBevelLxFBmk8, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null).build();
        _alarmOff = imageVectorBuild;
        return imageVectorBuild;
    }
}
