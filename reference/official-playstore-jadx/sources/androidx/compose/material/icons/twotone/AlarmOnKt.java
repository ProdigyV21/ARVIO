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
import v.b;
import v.c;
import v.d;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\"\u0010\u0010\u0000\u001a\u0004\u0018\u00010\u0001X\u0082\u000e¢\u0006\u0002\n\u0000\"\u0015\u0010\u0002\u001a\u00020\u0001*\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"_alarmOn", "Landroidx/compose/ui/graphics/vector/ImageVector;", "AlarmOn", "Landroidx/compose/material/icons/Icons$TwoTone;", "getAlarmOn", "(Landroidx/compose/material/icons/Icons$TwoTone;)Landroidx/compose/ui/graphics/vector/ImageVector;", "material-icons-extended_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class AlarmOnKt {
    private static ImageVector _alarmOn;

    public static final ImageVector getAlarmOn(Icons.TwoTone twoTone) {
        ImageVector imageVector = _alarmOn;
        if (imageVector != null) {
            return imageVector;
        }
        ImageVector.Builder builder = new ImageVector.Builder("TwoTone.AlarmOn", Dp.m5678constructorimpl(24.0f), Dp.m5678constructorimpl(24.0f), 24.0f, 24.0f, 0L, 0, false, 96, null);
        int defaultFillType = VectorKt.getDefaultFillType();
        Color.Companion companion = Color.INSTANCE;
        SolidColor solidColor = new SolidColor(companion.m3498getBlack0d7_KjU(), null);
        StrokeCap.Companion companion2 = StrokeCap.INSTANCE;
        int iM3803getButtKaPHkGw = companion2.m3803getButtKaPHkGw();
        StrokeJoin.Companion companion3 = StrokeJoin.INSTANCE;
        int iM3813getBevelLxFBmk8 = companion3.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderA = c.a(12.0f, 6.0f);
        pathBuilderA.curveToRelative(-3.86f, 0.0f, -7.0f, 3.14f, -7.0f, 7.0f);
        pathBuilderA.reflectiveCurveToRelative(3.14f, 7.0f, 7.0f, 7.0f);
        pathBuilderA.reflectiveCurveToRelative(7.0f, -3.14f, 7.0f, -7.0f);
        pathBuilderA.reflectiveCurveToRelative(-3.14f, -7.0f, -7.0f, -7.0f);
        a.z(pathBuilderA, 10.53f, 16.64f, -3.18f, -3.18f);
        pathBuilderA.lineToRelative(1.06f, -1.06f);
        pathBuilderA.lineToRelative(2.13f, 2.13f);
        pathBuilderA.lineToRelative(4.93f, -4.95f);
        ImageVector.Builder.m4024addPathoIyEayM$default(builder, b.t(pathBuilderA, 1.06f, 1.06f, -6.0f, 6.0f), defaultFillType, "", solidColor, 0.3f, null, 0.3f, 1.0f, iM3803getButtKaPHkGw, iM3813getBevelLxFBmk8, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null);
        int defaultFillType2 = VectorKt.getDefaultFillType();
        SolidColor solidColor2 = new SolidColor(companion.m3498getBlack0d7_KjU(), null);
        int iM3803getButtKaPHkGw2 = companion2.m3803getButtKaPHkGw();
        int iM3813getBevelLxFBmk82 = companion3.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderJ = a.j(10.54f, 14.53f, 8.41f, 12.4f);
        pathBuilderJ.lineToRelative(-1.06f, 1.06f);
        pathBuilderJ.lineToRelative(3.18f, 3.18f);
        b.D(pathBuilderJ, 6.0f, -6.0f, -1.06f, -1.06f);
        pathBuilderJ.moveTo(17.337f, 1.81f);
        pathBuilderJ.lineToRelative(4.607f, 3.845f);
        b.D(pathBuilderJ, -1.28f, 1.535f, -4.61f, -3.843f);
        pathBuilderJ.moveTo(6.663f, 1.81f);
        pathBuilderJ.lineToRelative(1.282f, 1.536f);
        d.l(pathBuilderJ, 3.337f, 7.19f, -1.28f, -1.536f);
        pathBuilderJ.moveTo(12.0f, 4.0f);
        pathBuilderJ.curveToRelative(-4.97f, 0.0f, -9.0f, 4.03f, -9.0f, 9.0f);
        pathBuilderJ.reflectiveCurveToRelative(4.03f, 9.0f, 9.0f, 9.0f);
        pathBuilderJ.reflectiveCurveToRelative(9.0f, -4.03f, 9.0f, -9.0f);
        pathBuilderJ.reflectiveCurveToRelative(-4.03f, -9.0f, -9.0f, -9.0f);
        pathBuilderJ.close();
        pathBuilderJ.moveTo(12.0f, 20.0f);
        pathBuilderJ.curveToRelative(-3.86f, 0.0f, -7.0f, -3.14f, -7.0f, -7.0f);
        pathBuilderJ.reflectiveCurveToRelative(3.14f, -7.0f, 7.0f, -7.0f);
        pathBuilderJ.reflectiveCurveToRelative(7.0f, 3.14f, 7.0f, 7.0f);
        ImageVector imageVectorBuild = ImageVector.Builder.m4024addPathoIyEayM$default(builder, c.h(pathBuilderJ, -3.14f, 7.0f, -7.0f, 7.0f), defaultFillType2, "", solidColor2, 1.0f, null, 1.0f, 1.0f, iM3803getButtKaPHkGw2, iM3813getBevelLxFBmk82, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null).build();
        _alarmOn = imageVectorBuild;
        return imageVectorBuild;
    }
}
