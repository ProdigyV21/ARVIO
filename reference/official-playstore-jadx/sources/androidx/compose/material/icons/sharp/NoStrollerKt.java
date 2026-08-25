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
import v.c;
import v.d;
import v.e;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\"\u0010\u0010\u0000\u001a\u0004\u0018\u00010\u0001X\u0082\u000e¢\u0006\u0002\n\u0000\"\u0015\u0010\u0002\u001a\u00020\u0001*\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"_noStroller", "Landroidx/compose/ui/graphics/vector/ImageVector;", "NoStroller", "Landroidx/compose/material/icons/Icons$Sharp;", "getNoStroller", "(Landroidx/compose/material/icons/Icons$Sharp;)Landroidx/compose/ui/graphics/vector/ImageVector;", "material-icons-extended_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class NoStrollerKt {
    private static ImageVector _noStroller;

    public static final ImageVector getNoStroller(Icons.Sharp sharp) {
        ImageVector imageVector = _noStroller;
        if (imageVector != null) {
            return imageVector;
        }
        ImageVector.Builder builder = new ImageVector.Builder("Sharp.NoStroller", Dp.m5678constructorimpl(24.0f), Dp.m5678constructorimpl(24.0f), 24.0f, 24.0f, 0L, 0, false, 96, null);
        int defaultFillType = VectorKt.getDefaultFillType();
        SolidColor solidColor = new SolidColor(Color.INSTANCE.m3498getBlack0d7_KjU(), null);
        int iM3803getButtKaPHkGw = StrokeCap.INSTANCE.m3803getButtKaPHkGw();
        int iM3813getBevelLxFBmk8 = StrokeJoin.INSTANCE.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderJ = a.j(10.91f, 8.08f, 6.53f, 3.7f);
        pathBuilderJ.curveTo(7.6f, 3.25f, 8.77f, 3.0f, 10.0f, 3.0f);
        pathBuilderJ.curveToRelative(1.56f, 0.0f, 3.03f, 0.4f, 4.3f, 1.1f);
        c.z(pathBuilderJ, 10.91f, 8.08f, 21.19f, 21.19f);
        pathBuilderJ.lineToRelative(-4.78f, -4.78f);
        pathBuilderJ.lineToRelative(-5.75f, -5.75f);
        pathBuilderJ.lineTo(2.81f, 2.81f);
        pathBuilderJ.lineTo(1.39f, 4.22f);
        pathBuilderJ.lineToRelative(7.97f, 7.97f);
        pathBuilderJ.lineTo(5.27f, 17.0f);
        pathBuilderJ.horizontalLineToRelative(8.9f);
        pathBuilderJ.lineToRelative(1.13f, 1.13f);
        pathBuilderJ.curveToRelative(-0.88f, 0.33f, -1.47f, 1.25f, -1.26f, 2.28f);
        pathBuilderJ.curveToRelative(0.15f, 0.76f, 0.78f, 1.39f, 1.54f, 1.54f);
        pathBuilderJ.curveToRelative(1.03f, 0.21f, 1.95f, -0.38f, 2.28f, -1.26f);
        d.C(pathBuilderJ, 1.91f, 1.91f, 21.19f, 21.19f);
        pathBuilderJ.moveTo(6.0f, 18.0f);
        pathBuilderJ.curveToRelative(-1.1f, 0.0f, -2.0f, 0.9f, -2.0f, 2.0f);
        pathBuilderJ.reflectiveCurveToRelative(0.9f, 2.0f, 2.0f, 2.0f);
        pathBuilderJ.reflectiveCurveToRelative(2.0f, -0.9f, 2.0f, -2.0f);
        pathBuilderJ.reflectiveCurveTo(7.1f, 18.0f, 6.0f, 18.0f);
        pathBuilderJ.close();
        pathBuilderJ.moveTo(17.0f, 6.27f);
        pathBuilderJ.curveTo(17.58f, 5.59f, 17.97f, 5.0f, 18.65f, 5.0f);
        pathBuilderJ.curveTo(19.42f, 5.0f, 20.0f, 5.66f, 20.0f, 6.48f);
        c.i(pathBuilderJ, 7.0f, 2.0f, 6.48f);
        pathBuilderJ.curveTo(22.0f, 4.56f, 20.52f, 3.0f, 18.65f, 3.0f);
        pathBuilderJ.curveToRelative(-1.66f, 0.0f, -2.54f, 1.27f, -3.18f, 2.03f);
        pathBuilderJ.lineToRelative(-3.5f, 4.11f);
        ImageVector imageVectorBuild = ImageVector.Builder.m4024addPathoIyEayM$default(builder, e.e(pathBuilderJ, 17.0f, 14.17f, 6.27f), defaultFillType, "", solidColor, 1.0f, null, 1.0f, 1.0f, iM3803getButtKaPHkGw, iM3813getBevelLxFBmk8, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null).build();
        _noStroller = imageVectorBuild;
        return imageVectorBuild;
    }
}
