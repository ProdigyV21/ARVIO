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
import v.c;
import v.d;
import v.e;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\"\u0010\u0010\u0000\u001a\u0004\u0018\u00010\u0001X\u0082\u000e¢\u0006\u0002\n\u0000\"\u001e\u0010\u0002\u001a\u00020\u0001*\u00020\u00038FX\u0087\u0004¢\u0006\f\u0012\u0004\b\u0004\u0010\u0005\u001a\u0004\b\u0006\u0010\u0007¨\u0006\b"}, d2 = {"_scheduleSend", "Landroidx/compose/ui/graphics/vector/ImageVector;", "ScheduleSend", "Landroidx/compose/material/icons/Icons$Filled;", "getScheduleSend$annotations", "(Landroidx/compose/material/icons/Icons$Filled;)V", "getScheduleSend", "(Landroidx/compose/material/icons/Icons$Filled;)Landroidx/compose/ui/graphics/vector/ImageVector;", "material-icons-extended_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class ScheduleSendKt {
    private static ImageVector _scheduleSend;

    public static final ImageVector getScheduleSend(Icons.Filled filled) {
        ImageVector imageVector = _scheduleSend;
        if (imageVector != null) {
            return imageVector;
        }
        ImageVector.Builder builder = new ImageVector.Builder("Filled.ScheduleSend", Dp.m5678constructorimpl(24.0f), Dp.m5678constructorimpl(24.0f), 24.0f, 24.0f, 0L, 0, false, 96, null);
        int defaultFillType = VectorKt.getDefaultFillType();
        SolidColor solidColor = new SolidColor(Color.INSTANCE.m3498getBlack0d7_KjU(), null);
        int iM3803getButtKaPHkGw = StrokeCap.INSTANCE.m3803getButtKaPHkGw();
        int iM3813getBevelLxFBmk8 = StrokeJoin.INSTANCE.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderS = c.s(16.5f, 12.5f, 15.0f, 12.5f, 4.0f);
        pathBuilderS.lineToRelative(3.0f, 2.0f);
        pathBuilderS.lineToRelative(0.75f, -1.23f);
        d.C(pathBuilderS, -2.25f, -1.52f, 16.5f, 12.5f);
        pathBuilderS.moveTo(16.0f, 9.0f);
        pathBuilderS.lineTo(2.0f, 3.0f);
        pathBuilderS.verticalLineToRelative(7.0f);
        pathBuilderS.lineToRelative(9.0f, 2.0f);
        pathBuilderS.lineToRelative(-9.0f, 2.0f);
        pathBuilderS.verticalLineToRelative(7.0f);
        pathBuilderS.lineToRelative(7.27f, -3.11f);
        pathBuilderS.curveTo(10.09f, 20.83f, 12.79f, 23.0f, 16.0f, 23.0f);
        pathBuilderS.curveToRelative(3.86f, 0.0f, 7.0f, -3.14f, 7.0f, -7.0f);
        pathBuilderS.reflectiveCurveToRelative(-3.14f, -7.0f, -7.0f, -7.0f);
        pathBuilderS.close();
        pathBuilderS.moveTo(16.0f, 21.0f);
        pathBuilderS.curveToRelative(-2.75f, 0.0f, -4.98f, -2.22f, -5.0f, -4.97f);
        pathBuilderS.verticalLineToRelative(-0.07f);
        pathBuilderS.curveToRelative(0.02f, -2.74f, 2.25f, -4.97f, 5.0f, -4.97f);
        pathBuilderS.curveToRelative(2.76f, 0.0f, 5.0f, 2.24f, 5.0f, 5.0f);
        ImageVector imageVectorBuild = ImageVector.Builder.m4024addPathoIyEayM$default(builder, e.u(pathBuilderS, 18.76f, 21.0f, 16.0f, 21.0f), defaultFillType, "", solidColor, 1.0f, null, 1.0f, 1.0f, iM3803getButtKaPHkGw, iM3813getBevelLxFBmk8, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null).build();
        _scheduleSend = imageVectorBuild;
        return imageVectorBuild;
    }

    @x6.e
    public static /* synthetic */ void getScheduleSend$annotations(Icons.Filled filled) {
    }
}
