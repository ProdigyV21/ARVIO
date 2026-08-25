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

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\"\u0010\u0010\u0000\u001a\u0004\u0018\u00010\u0001X\u0082\u000e¢\u0006\u0002\n\u0000\"\u0015\u0010\u0002\u001a\u00020\u0001*\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"_locationOff", "Landroidx/compose/ui/graphics/vector/ImageVector;", "LocationOff", "Landroidx/compose/material/icons/Icons$Filled;", "getLocationOff", "(Landroidx/compose/material/icons/Icons$Filled;)Landroidx/compose/ui/graphics/vector/ImageVector;", "material-icons-extended_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class LocationOffKt {
    private static ImageVector _locationOff;

    public static final ImageVector getLocationOff(Icons.Filled filled) {
        ImageVector imageVector = _locationOff;
        if (imageVector != null) {
            return imageVector;
        }
        ImageVector.Builder builder = new ImageVector.Builder("Filled.LocationOff", Dp.m5678constructorimpl(24.0f), Dp.m5678constructorimpl(24.0f), 24.0f, 24.0f, 0L, 0, false, 96, null);
        int defaultFillType = VectorKt.getDefaultFillType();
        SolidColor solidColor = new SolidColor(Color.INSTANCE.m3498getBlack0d7_KjU(), null);
        int iM3803getButtKaPHkGw = StrokeCap.INSTANCE.m3803getButtKaPHkGw();
        int iM3813getBevelLxFBmk8 = StrokeJoin.INSTANCE.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderA = c.a(12.0f, 6.5f);
        pathBuilderA.curveToRelative(1.38f, 0.0f, 2.5f, 1.12f, 2.5f, 2.5f);
        pathBuilderA.curveToRelative(0.0f, 0.74f, -0.33f, 1.39f, -0.83f, 1.85f);
        pathBuilderA.lineToRelative(3.63f, 3.63f);
        pathBuilderA.curveToRelative(0.98f, -1.86f, 1.7f, -3.8f, 1.7f, -5.48f);
        pathBuilderA.curveToRelative(0.0f, -3.87f, -3.13f, -7.0f, -7.0f, -7.0f);
        pathBuilderA.curveToRelative(-1.98f, 0.0f, -3.76f, 0.83f, -5.04f, 2.15f);
        pathBuilderA.lineToRelative(3.19f, 3.19f);
        pathBuilderA.curveToRelative(0.46f, -0.52f, 1.11f, -0.84f, 1.85f, -0.84f);
        a.z(pathBuilderA, 16.37f, 16.1f, -4.63f, -4.63f);
        pathBuilderA.lineToRelative(-0.11f, -0.11f);
        pathBuilderA.lineTo(3.27f, 3.0f);
        pathBuilderA.lineTo(2.0f, 4.27f);
        pathBuilderA.lineToRelative(3.18f, 3.18f);
        pathBuilderA.curveTo(5.07f, 7.95f, 5.0f, 8.47f, 5.0f, 9.0f);
        pathBuilderA.curveToRelative(0.0f, 5.25f, 7.0f, 13.0f, 7.0f, 13.0f);
        pathBuilderA.reflectiveCurveToRelative(1.67f, -1.85f, 3.38f, -4.35f);
        pathBuilderA.lineTo(18.73f, 21.0f);
        ImageVector imageVectorBuild = ImageVector.Builder.m4024addPathoIyEayM$default(builder, b.v(pathBuilderA, 20.0f, 19.73f, -3.63f, -3.63f), defaultFillType, "", solidColor, 1.0f, null, 1.0f, 1.0f, iM3803getButtKaPHkGw, iM3813getBevelLxFBmk8, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null).build();
        _locationOff = imageVectorBuild;
        return imageVectorBuild;
    }
}
