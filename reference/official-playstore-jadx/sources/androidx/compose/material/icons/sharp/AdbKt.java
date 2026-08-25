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
import v.c;
import v.d;
import v.e;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\"\u0010\u0010\u0000\u001a\u0004\u0018\u00010\u0001X\u0082\u000e¢\u0006\u0002\n\u0000\"\u0015\u0010\u0002\u001a\u00020\u0001*\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"_adb", "Landroidx/compose/ui/graphics/vector/ImageVector;", "Adb", "Landroidx/compose/material/icons/Icons$Sharp;", "getAdb", "(Landroidx/compose/material/icons/Icons$Sharp;)Landroidx/compose/ui/graphics/vector/ImageVector;", "material-icons-extended_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class AdbKt {
    private static ImageVector _adb;

    public static final ImageVector getAdb(Icons.Sharp sharp) {
        ImageVector imageVector = _adb;
        if (imageVector != null) {
            return imageVector;
        }
        ImageVector.Builder builder = new ImageVector.Builder("Sharp.Adb", Dp.m5678constructorimpl(24.0f), Dp.m5678constructorimpl(24.0f), 24.0f, 24.0f, 0L, 0, false, 96, null);
        int defaultFillType = VectorKt.getDefaultFillType();
        SolidColor solidColor = new SolidColor(Color.INSTANCE.m3498getBlack0d7_KjU(), null);
        int iM3803getButtKaPHkGw = StrokeCap.INSTANCE.m3803getButtKaPHkGw();
        int iM3813getBevelLxFBmk8 = StrokeJoin.INSTANCE.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderA = c.a(5.0f, 16.0f);
        pathBuilderA.curveToRelative(0.0f, 3.87f, 3.13f, 7.0f, 7.0f, 7.0f);
        pathBuilderA.reflectiveCurveToRelative(7.0f, -3.13f, 7.0f, -7.0f);
        d.g(pathBuilderA, -4.0f, 5.0f, 12.0f, 4.0f);
        pathBuilderA.moveTo(16.12f, 4.37f);
        pathBuilderA.lineToRelative(2.1f, -2.1f);
        pathBuilderA.lineToRelative(-0.82f, -0.83f);
        pathBuilderA.lineToRelative(-2.3f, 2.31f);
        pathBuilderA.curveTo(14.16f, 3.28f, 13.12f, 3.0f, 12.0f, 3.0f);
        pathBuilderA.reflectiveCurveToRelative(-2.16f, 0.28f, -3.09f, 0.75f);
        pathBuilderA.lineTo(6.6f, 1.44f);
        pathBuilderA.lineToRelative(-0.82f, 0.83f);
        pathBuilderA.lineToRelative(2.1f, 2.1f);
        pathBuilderA.curveTo(6.14f, 5.64f, 5.0f, 7.68f, 5.0f, 10.0f);
        e.l(pathBuilderA, 1.0f, 14.0f, -1.0f);
        pathBuilderA.curveToRelative(0.0f, -2.32f, -1.14f, -4.36f, -2.88f, -5.63f);
        pathBuilderA.close();
        pathBuilderA.moveTo(9.0f, 9.0f);
        pathBuilderA.curveToRelative(-0.55f, 0.0f, -1.0f, -0.45f, -1.0f, -1.0f);
        pathBuilderA.reflectiveCurveToRelative(0.45f, -1.0f, 1.0f, -1.0f);
        pathBuilderA.reflectiveCurveToRelative(1.0f, 0.45f, 1.0f, 1.0f);
        pathBuilderA.reflectiveCurveToRelative(-0.45f, 1.0f, -1.0f, 1.0f);
        pathBuilderA.close();
        pathBuilderA.moveTo(15.0f, 9.0f);
        pathBuilderA.curveToRelative(-0.55f, 0.0f, -1.0f, -0.45f, -1.0f, -1.0f);
        pathBuilderA.reflectiveCurveToRelative(0.45f, -1.0f, 1.0f, -1.0f);
        pathBuilderA.reflectiveCurveToRelative(1.0f, 0.45f, 1.0f, 1.0f);
        ImageVector imageVectorBuild = ImageVector.Builder.m4024addPathoIyEayM$default(builder, c.h(pathBuilderA, -0.45f, 1.0f, -1.0f, 1.0f), defaultFillType, "", solidColor, 1.0f, null, 1.0f, 1.0f, iM3803getButtKaPHkGw, iM3813getBevelLxFBmk8, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null).build();
        _adb = imageVectorBuild;
        return imageVectorBuild;
    }
}
