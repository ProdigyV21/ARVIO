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
import v.d;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\"\u0010\u0010\u0000\u001a\u0004\u0018\u00010\u0001X\u0082\u000e¢\u0006\u0002\n\u0000\"\u0015\u0010\u0002\u001a\u00020\u0001*\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"_alarmOn", "Landroidx/compose/ui/graphics/vector/ImageVector;", "AlarmOn", "Landroidx/compose/material/icons/Icons$Filled;", "getAlarmOn", "(Landroidx/compose/material/icons/Icons$Filled;)Landroidx/compose/ui/graphics/vector/ImageVector;", "material-icons-extended_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class AlarmOnKt {
    private static ImageVector _alarmOn;

    public static final ImageVector getAlarmOn(Icons.Filled filled) {
        ImageVector imageVector = _alarmOn;
        if (imageVector != null) {
            return imageVector;
        }
        ImageVector.Builder builder = new ImageVector.Builder("Filled.AlarmOn", Dp.m5678constructorimpl(24.0f), Dp.m5678constructorimpl(24.0f), 24.0f, 24.0f, 0L, 0, false, 96, null);
        int defaultFillType = VectorKt.getDefaultFillType();
        SolidColor solidColor = new SolidColor(Color.INSTANCE.m3498getBlack0d7_KjU(), null);
        int iM3803getButtKaPHkGw = StrokeCap.INSTANCE.m3803getButtKaPHkGw();
        int iM3813getBevelLxFBmk8 = StrokeJoin.INSTANCE.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderR = a.r(22.0f, 5.72f, -4.6f, -3.86f);
        pathBuilderR.lineToRelative(-1.29f, 1.53f);
        d.C(pathBuilderR, 4.6f, 3.86f, 22.0f, 5.72f);
        pathBuilderR.moveTo(7.88f, 3.39f);
        pathBuilderR.lineTo(6.6f, 1.86f);
        pathBuilderR.lineTo(2.0f, 5.71f);
        b.D(pathBuilderR, 1.29f, 1.53f, 4.59f, -3.85f);
        pathBuilderR.moveTo(12.0f, 4.0f);
        pathBuilderR.curveToRelative(-4.97f, 0.0f, -9.0f, 4.03f, -9.0f, 9.0f);
        pathBuilderR.reflectiveCurveToRelative(4.02f, 9.0f, 9.0f, 9.0f);
        pathBuilderR.curveToRelative(4.97f, 0.0f, 9.0f, -4.03f, 9.0f, -9.0f);
        pathBuilderR.reflectiveCurveToRelative(-4.03f, -9.0f, -9.0f, -9.0f);
        pathBuilderR.close();
        pathBuilderR.moveTo(12.0f, 20.0f);
        pathBuilderR.curveToRelative(-3.87f, 0.0f, -7.0f, -3.13f, -7.0f, -7.0f);
        pathBuilderR.reflectiveCurveToRelative(3.13f, -7.0f, 7.0f, -7.0f);
        pathBuilderR.reflectiveCurveToRelative(7.0f, 3.13f, 7.0f, 7.0f);
        pathBuilderR.reflectiveCurveToRelative(-3.13f, 7.0f, -7.0f, 7.0f);
        a.p(pathBuilderR, 10.54f, 14.53f, 8.41f, 12.4f);
        pathBuilderR.lineToRelative(-1.06f, 1.06f);
        pathBuilderR.lineToRelative(3.18f, 3.18f);
        pathBuilderR.lineToRelative(6.0f, -6.0f);
        ImageVector imageVectorBuild = ImageVector.Builder.m4024addPathoIyEayM$default(builder, b.t(pathBuilderR, -1.06f, -1.06f, -4.93f, 4.95f), defaultFillType, "", solidColor, 1.0f, null, 1.0f, 1.0f, iM3803getButtKaPHkGw, iM3813getBevelLxFBmk8, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null).build();
        _alarmOn = imageVectorBuild;
        return imageVectorBuild;
    }
}
