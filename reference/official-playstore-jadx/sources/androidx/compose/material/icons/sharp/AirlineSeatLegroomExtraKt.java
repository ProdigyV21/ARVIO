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
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\"\u0010\u0010\u0000\u001a\u0004\u0018\u00010\u0001X\u0082\u000e¢\u0006\u0002\n\u0000\"\u0015\u0010\u0002\u001a\u00020\u0001*\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"_airlineSeatLegroomExtra", "Landroidx/compose/ui/graphics/vector/ImageVector;", "AirlineSeatLegroomExtra", "Landroidx/compose/material/icons/Icons$Sharp;", "getAirlineSeatLegroomExtra", "(Landroidx/compose/material/icons/Icons$Sharp;)Landroidx/compose/ui/graphics/vector/ImageVector;", "material-icons-extended_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class AirlineSeatLegroomExtraKt {
    private static ImageVector _airlineSeatLegroomExtra;

    public static final ImageVector getAirlineSeatLegroomExtra(Icons.Sharp sharp) {
        ImageVector imageVector = _airlineSeatLegroomExtra;
        if (imageVector != null) {
            return imageVector;
        }
        ImageVector.Builder builder = new ImageVector.Builder("Sharp.AirlineSeatLegroomExtra", Dp.m5678constructorimpl(24.0f), Dp.m5678constructorimpl(24.0f), 24.0f, 24.0f, 0L, 0, false, 96, null);
        int defaultFillType = VectorKt.getDefaultFillType();
        SolidColor solidColor = new SolidColor(Color.INSTANCE.m3498getBlack0d7_KjU(), null);
        int iM3803getButtKaPHkGw = StrokeCap.INSTANCE.m3803getButtKaPHkGw();
        int iM3813getBevelLxFBmk8 = StrokeJoin.INSTANCE.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderS = c.s(4.0f, 3.0f, 2.0f, 3.0f, 14.0f);
        d.m(pathBuilderS, 11.0f, -2.0f, 4.0f, 15.0f);
        pathBuilderS.moveTo(22.24f, 15.96f);
        pathBuilderS.lineToRelative(-2.53f, 1.15f);
        pathBuilderS.lineToRelative(-3.41f, -6.98f);
        pathBuilderS.curveTo(15.96f, 9.45f, 15.27f, 9.0f, 14.51f, 9.0f);
        pathBuilderS.lineTo(11.0f, 9.0f);
        pathBuilderS.lineTo(11.0f, 3.0f);
        a.h(pathBuilderS, 5.0f, 3.0f, 11.0f, 10.0f);
        pathBuilderS.lineToRelative(3.41f, 7.0f);
        ImageVector imageVectorBuild = ImageVector.Builder.m4024addPathoIyEayM$default(builder, b.t(pathBuilderS, 5.07f, -2.32f, -1.24f, -2.72f), defaultFillType, "", solidColor, 1.0f, null, 1.0f, 1.0f, iM3803getButtKaPHkGw, iM3813getBevelLxFBmk8, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null).build();
        _airlineSeatLegroomExtra = imageVectorBuild;
        return imageVectorBuild;
    }
}
