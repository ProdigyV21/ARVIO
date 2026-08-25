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
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\"\u0010\u0010\u0000\u001a\u0004\u0018\u00010\u0001X\u0082\u000e¢\u0006\u0002\n\u0000\"\u0015\u0010\u0002\u001a\u00020\u0001*\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"_satelliteAlt", "Landroidx/compose/ui/graphics/vector/ImageVector;", "SatelliteAlt", "Landroidx/compose/material/icons/Icons$Filled;", "getSatelliteAlt", "(Landroidx/compose/material/icons/Icons$Filled;)Landroidx/compose/ui/graphics/vector/ImageVector;", "material-icons-extended_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class SatelliteAltKt {
    private static ImageVector _satelliteAlt;

    public static final ImageVector getSatelliteAlt(Icons.Filled filled) {
        ImageVector imageVector = _satelliteAlt;
        if (imageVector != null) {
            return imageVector;
        }
        ImageVector.Builder builder = new ImageVector.Builder("Filled.SatelliteAlt", Dp.m5678constructorimpl(24.0f), Dp.m5678constructorimpl(24.0f), 24.0f, 24.0f, 0L, 0, false, 96, null);
        int defaultFillType = VectorKt.getDefaultFillType();
        SolidColor solidColor = new SolidColor(Color.INSTANCE.m3498getBlack0d7_KjU(), null);
        int iM3803getButtKaPHkGw = StrokeCap.INSTANCE.m3803getButtKaPHkGw();
        int iM3813getBevelLxFBmk8 = StrokeJoin.INSTANCE.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderR = a.r(15.44f, 0.59f, -3.18f, 3.18f);
        pathBuilderR.curveToRelative(-0.78f, 0.78f, -0.78f, 2.05f, 0.0f, 2.83f);
        pathBuilderR.lineToRelative(1.24f, 1.24f);
        pathBuilderR.lineToRelative(-0.71f, 0.71f);
        pathBuilderR.lineTo(11.55f, 7.3f);
        pathBuilderR.curveToRelative(-0.78f, -0.78f, -2.05f, -0.78f, -2.83f, 0.0f);
        pathBuilderR.lineTo(7.3f, 8.72f);
        pathBuilderR.curveToRelative(-0.78f, 0.78f, -0.78f, 2.05f, 0.0f, 2.83f);
        pathBuilderR.lineToRelative(1.24f, 1.24f);
        pathBuilderR.lineToRelative(-0.71f, 0.71f);
        pathBuilderR.lineTo(6.6f, 12.25f);
        pathBuilderR.curveToRelative(-0.78f, -0.78f, -2.05f, -0.78f, -2.83f, 0.0f);
        pathBuilderR.lineToRelative(-3.18f, 3.18f);
        pathBuilderR.curveToRelative(-0.78f, 0.78f, -0.78f, 2.05f, 0.0f, 2.83f);
        pathBuilderR.lineToRelative(3.54f, 3.54f);
        pathBuilderR.curveToRelative(0.78f, 0.78f, 2.05f, 0.78f, 2.83f, 0.0f);
        pathBuilderR.lineToRelative(3.18f, -3.18f);
        pathBuilderR.curveToRelative(0.78f, -0.78f, 0.78f, -2.05f, 0.0f, -2.83f);
        pathBuilderR.lineToRelative(-1.24f, -1.24f);
        pathBuilderR.lineToRelative(0.71f, -0.71f);
        pathBuilderR.lineToRelative(1.24f, 1.24f);
        pathBuilderR.curveToRelative(0.78f, 0.78f, 2.05f, 0.78f, 2.83f, 0.0f);
        pathBuilderR.lineToRelative(1.41f, -1.41f);
        pathBuilderR.curveToRelative(0.78f, -0.78f, 0.78f, -2.05f, 0.0f, -2.83f);
        pathBuilderR.lineTo(13.84f, 9.6f);
        pathBuilderR.lineToRelative(0.71f, -0.71f);
        pathBuilderR.lineToRelative(1.24f, 1.24f);
        pathBuilderR.curveToRelative(0.78f, 0.78f, 2.05f, 0.78f, 2.83f, 0.0f);
        pathBuilderR.lineToRelative(3.18f, -3.18f);
        pathBuilderR.curveToRelative(0.78f, -0.78f, 0.78f, -2.05f, 0.0f, -2.83f);
        pathBuilderR.lineToRelative(-3.54f, -3.54f);
        pathBuilderR.curveTo(17.48f, -0.2f, 16.22f, -0.2f, 15.44f, 0.59f);
        a.z(pathBuilderR, 6.6f, 19.32f, -1.06f, 1.06f);
        pathBuilderR.lineTo(2.0f, 16.85f);
        d.C(pathBuilderR, 1.06f, -1.06f, 6.6f, 19.32f);
        pathBuilderR.moveTo(8.72f, 17.2f);
        pathBuilderR.lineToRelative(-1.06f, 1.06f);
        pathBuilderR.lineToRelative(-3.54f, -3.54f);
        d.C(pathBuilderR, 1.06f, -1.06f, 8.72f, 17.2f);
        pathBuilderR.moveTo(18.26f, 7.66f);
        pathBuilderR.lineTo(17.2f, 8.72f);
        pathBuilderR.lineToRelative(-3.54f, -3.54f);
        d.C(pathBuilderR, 1.06f, -1.06f, 18.26f, 7.66f);
        pathBuilderR.moveTo(20.38f, 5.54f);
        pathBuilderR.lineTo(19.32f, 6.6f);
        pathBuilderR.lineToRelative(-3.54f, -3.54f);
        b.y(pathBuilderR, 16.85f, 2.0f, 20.38f, 5.54f);
        pathBuilderR.moveTo(14.0f, 21.0f);
        pathBuilderR.lineToRelative(0.0f, 2.0f);
        pathBuilderR.curveToRelative(4.97f, 0.0f, 9.0f, -4.03f, 9.0f, -9.0f);
        pathBuilderR.lineToRelative(-2.0f, 0.0f);
        pathBuilderR.curveTo(21.0f, 17.87f, 17.87f, 21.0f, 14.0f, 21.0f);
        a.z(pathBuilderR, 14.0f, 17.0f, 0.0f, 2.0f);
        pathBuilderR.curveToRelative(2.76f, 0.0f, 5.0f, -2.24f, 5.0f, -5.0f);
        pathBuilderR.lineToRelative(-2.0f, 0.0f);
        pathBuilderR.curveTo(17.0f, 15.66f, 15.66f, 17.0f, 14.0f, 17.0f);
        pathBuilderR.close();
        ImageVector imageVectorBuild = ImageVector.Builder.m4024addPathoIyEayM$default(builder, pathBuilderR.getNodes(), defaultFillType, "", solidColor, 1.0f, null, 1.0f, 1.0f, iM3803getButtKaPHkGw, iM3813getBevelLxFBmk8, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null).build();
        _satelliteAlt = imageVectorBuild;
        return imageVectorBuild;
    }
}
