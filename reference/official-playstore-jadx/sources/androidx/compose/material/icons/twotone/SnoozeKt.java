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
import v.b;
import v.c;
import v.d;
import v.e;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\"\u0010\u0010\u0000\u001a\u0004\u0018\u00010\u0001X\u0082\u000e¢\u0006\u0002\n\u0000\"\u0015\u0010\u0002\u001a\u00020\u0001*\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"_snooze", "Landroidx/compose/ui/graphics/vector/ImageVector;", "Snooze", "Landroidx/compose/material/icons/Icons$TwoTone;", "getSnooze", "(Landroidx/compose/material/icons/Icons$TwoTone;)Landroidx/compose/ui/graphics/vector/ImageVector;", "material-icons-extended_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class SnoozeKt {
    private static ImageVector _snooze;

    public static final ImageVector getSnooze(Icons.TwoTone twoTone) {
        ImageVector imageVector = _snooze;
        if (imageVector != null) {
            return imageVector;
        }
        ImageVector.Builder builder = new ImageVector.Builder("TwoTone.Snooze", Dp.m5678constructorimpl(24.0f), Dp.m5678constructorimpl(24.0f), 24.0f, 24.0f, 0L, 0, false, 96, null);
        int defaultFillType = VectorKt.getDefaultFillType();
        SolidColor solidColor = new SolidColor(Color.INSTANCE.m3498getBlack0d7_KjU(), null);
        int iM3803getButtKaPHkGw = StrokeCap.INSTANCE.m3803getButtKaPHkGw();
        int iM3813getBevelLxFBmk8 = StrokeJoin.INSTANCE.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderI = e.i(9.0f, 11.0f, 3.63f, 9.0f, 15.2f);
        b.A(pathBuilderI, 9.0f, 17.0f, 6.0f, -2.0f);
        pathBuilderI.horizontalLineToRelative(-3.63f);
        pathBuilderI.lineTo(15.0f, 10.8f);
        b.y(pathBuilderI, 15.0f, 9.0f, 9.0f, 9.0f);
        pathBuilderI.moveTo(17.337f, 1.81f);
        pathBuilderI.lineToRelative(4.607f, 3.845f);
        b.D(pathBuilderI, -1.28f, 1.535f, -4.61f, -3.843f);
        pathBuilderI.moveTo(6.663f, 1.81f);
        pathBuilderI.lineToRelative(1.282f, 1.536f);
        d.l(pathBuilderI, 3.337f, 7.19f, -1.28f, -1.536f);
        pathBuilderI.moveTo(12.0f, 4.0f);
        pathBuilderI.curveToRelative(-4.97f, 0.0f, -9.0f, 4.03f, -9.0f, 9.0f);
        pathBuilderI.reflectiveCurveToRelative(4.03f, 9.0f, 9.0f, 9.0f);
        pathBuilderI.reflectiveCurveToRelative(9.0f, -4.03f, 9.0f, -9.0f);
        pathBuilderI.reflectiveCurveToRelative(-4.03f, -9.0f, -9.0f, -9.0f);
        pathBuilderI.close();
        pathBuilderI.moveTo(12.0f, 20.0f);
        pathBuilderI.curveToRelative(-3.86f, 0.0f, -7.0f, -3.14f, -7.0f, -7.0f);
        pathBuilderI.reflectiveCurveToRelative(3.14f, -7.0f, 7.0f, -7.0f);
        pathBuilderI.reflectiveCurveToRelative(7.0f, 3.14f, 7.0f, 7.0f);
        ImageVector imageVectorBuild = ImageVector.Builder.m4024addPathoIyEayM$default(builder, c.h(pathBuilderI, -3.14f, 7.0f, -7.0f, 7.0f), defaultFillType, "", solidColor, 1.0f, null, 1.0f, 1.0f, iM3803getButtKaPHkGw, iM3813getBevelLxFBmk8, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null).build();
        _snooze = imageVectorBuild;
        return imageVectorBuild;
    }
}
