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
import v.e;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\"\u0010\u0010\u0000\u001a\u0004\u0018\u00010\u0001X\u0082\u000e¢\u0006\u0002\n\u0000\"\u0015\u0010\u0002\u001a\u00020\u0001*\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"_gpsOff", "Landroidx/compose/ui/graphics/vector/ImageVector;", "GpsOff", "Landroidx/compose/material/icons/Icons$Filled;", "getGpsOff", "(Landroidx/compose/material/icons/Icons$Filled;)Landroidx/compose/ui/graphics/vector/ImageVector;", "material-icons-extended_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class GpsOffKt {
    private static ImageVector _gpsOff;

    public static final ImageVector getGpsOff(Icons.Filled filled) {
        ImageVector imageVector = _gpsOff;
        if (imageVector != null) {
            return imageVector;
        }
        ImageVector.Builder builder = new ImageVector.Builder("Filled.GpsOff", Dp.m5678constructorimpl(24.0f), Dp.m5678constructorimpl(24.0f), 24.0f, 24.0f, 0L, 0, false, 96, null);
        int defaultFillType = VectorKt.getDefaultFillType();
        SolidColor solidColor = new SolidColor(Color.INSTANCE.m3498getBlack0d7_KjU(), null);
        int iM3803getButtKaPHkGw = StrokeCap.INSTANCE.m3803getButtKaPHkGw();
        int iM3813getBevelLxFBmk8 = StrokeJoin.INSTANCE.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderA = c.a(20.94f, 11.0f);
        pathBuilderA.curveToRelative(-0.46f, -4.17f, -3.77f, -7.48f, -7.94f, -7.94f);
        b.A(pathBuilderA, 13.0f, 1.0f, -2.0f, 2.06f);
        pathBuilderA.curveToRelative(-1.13f, 0.12f, -2.19f, 0.46f, -3.16f, 0.97f);
        pathBuilderA.lineToRelative(1.5f, 1.5f);
        pathBuilderA.curveTo(10.16f, 5.19f, 11.06f, 5.0f, 12.0f, 5.0f);
        pathBuilderA.curveToRelative(3.87f, 0.0f, 7.0f, 3.13f, 7.0f, 7.0f);
        pathBuilderA.curveToRelative(0.0f, 0.94f, -0.19f, 1.84f, -0.52f, 2.65f);
        pathBuilderA.lineToRelative(1.5f, 1.5f);
        pathBuilderA.curveToRelative(0.5f, -0.96f, 0.84f, -2.02f, 0.97f, -3.15f);
        e.p(pathBuilderA, 23.0f, 13.0f, -2.0f, -2.06f);
        pathBuilderA.moveTo(3.0f, 4.27f);
        pathBuilderA.lineToRelative(2.04f, 2.04f);
        pathBuilderA.curveTo(3.97f, 7.62f, 3.25f, 9.23f, 3.06f, 11.0f);
        a.h(pathBuilderA, 1.0f, 11.0f, 2.0f, 2.06f);
        pathBuilderA.curveToRelative(0.46f, 4.17f, 3.77f, 7.48f, 7.94f, 7.94f);
        b.A(pathBuilderA, 11.0f, 23.0f, 2.0f, -2.06f);
        pathBuilderA.curveToRelative(1.77f, -0.2f, 3.38f, -0.91f, 4.69f, -1.98f);
        pathBuilderA.lineTo(19.73f, 21.0f);
        pathBuilderA.lineTo(21.0f, 19.73f);
        b.y(pathBuilderA, 4.27f, 3.0f, 3.0f, 4.27f);
        pathBuilderA.moveTo(16.27f, 17.54f);
        pathBuilderA.curveTo(15.09f, 18.45f, 13.61f, 19.0f, 12.0f, 19.0f);
        pathBuilderA.curveToRelative(-3.87f, 0.0f, -7.0f, -3.13f, -7.0f, -7.0f);
        pathBuilderA.curveToRelative(0.0f, -1.61f, 0.55f, -3.09f, 1.46f, -4.27f);
        ImageVector imageVectorBuild = ImageVector.Builder.m4024addPathoIyEayM$default(builder, c.c(pathBuilderA, 9.81f, 9.81f), defaultFillType, "", solidColor, 1.0f, null, 1.0f, 1.0f, iM3803getButtKaPHkGw, iM3813getBevelLxFBmk8, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null).build();
        _gpsOff = imageVectorBuild;
        return imageVectorBuild;
    }
}
