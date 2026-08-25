package androidx.compose.material.icons.sharp;

import a0.b;
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
import v.e;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\"\u0010\u0010\u0000\u001a\u0004\u0018\u00010\u0001X\u0082\u000e¢\u0006\u0002\n\u0000\"\u0015\u0010\u0002\u001a\u00020\u0001*\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"_elderly", "Landroidx/compose/ui/graphics/vector/ImageVector;", "Elderly", "Landroidx/compose/material/icons/Icons$Sharp;", "getElderly", "(Landroidx/compose/material/icons/Icons$Sharp;)Landroidx/compose/ui/graphics/vector/ImageVector;", "material-icons-extended_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class ElderlyKt {
    private static ImageVector _elderly;

    public static final ImageVector getElderly(Icons.Sharp sharp) {
        ImageVector imageVector = _elderly;
        if (imageVector != null) {
            return imageVector;
        }
        ImageVector.Builder builder = new ImageVector.Builder("Sharp.Elderly", Dp.m5678constructorimpl(24.0f), Dp.m5678constructorimpl(24.0f), 24.0f, 24.0f, 0L, 0, false, 96, null);
        int defaultFillType = VectorKt.getDefaultFillType();
        SolidColor solidColor = new SolidColor(Color.INSTANCE.m3498getBlack0d7_KjU(), null);
        int iM3803getButtKaPHkGw = StrokeCap.INSTANCE.m3803getButtKaPHkGw();
        int iM3813getBevelLxFBmk8 = StrokeJoin.INSTANCE.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderA = c.a(13.5f, 5.5f);
        pathBuilderA.curveToRelative(1.1f, 0.0f, 2.0f, -0.9f, 2.0f, -2.0f);
        pathBuilderA.reflectiveCurveToRelative(-0.9f, -2.0f, -2.0f, -2.0f);
        pathBuilderA.reflectiveCurveToRelative(-2.0f, 0.9f, -2.0f, 2.0f);
        pathBuilderA.reflectiveCurveTo(12.4f, 5.5f, 13.5f, 5.5f);
        b.t(pathBuilderA, 20.0f, 12.5f, 23.0f, -1.0f);
        pathBuilderA.verticalLineTo(12.5f);
        pathBuilderA.curveToRelative(0.0f, -0.28f, -0.22f, -0.5f, -0.5f, -0.5f);
        pathBuilderA.reflectiveCurveTo(18.0f, 12.22f, 18.0f, 12.5f);
        e.l(pathBuilderA, 1.0f, -1.0f, -0.69f);
        pathBuilderA.curveToRelative(-1.46f, -0.38f, -2.7f, -1.29f, -3.51f, -2.52f);
        pathBuilderA.curveTo(13.18f, 11.16f, 13.0f, 12.07f, 13.0f, 13.0f);
        pathBuilderA.curveToRelative(0.0f, 0.23f, 0.02f, 0.46f, 0.03f, 0.69f);
        v.b.n(pathBuilderA, 15.0f, 16.5f, 23.0f, -2.0f);
        pathBuilderA.verticalLineToRelative(-5.0f);
        pathBuilderA.lineToRelative(-1.78f, -2.54f);
        pathBuilderA.lineTo(11.0f, 19.0f);
        pathBuilderA.lineToRelative(-3.0f, 4.0f);
        pathBuilderA.lineToRelative(-1.6f, -1.2f);
        pathBuilderA.lineTo(9.0f, 18.33f);
        pathBuilderA.verticalLineTo(13.0f);
        pathBuilderA.curveToRelative(0.0f, -1.15f, 0.18f, -2.29f, 0.5f, -3.39f);
        c.f(pathBuilderA, 8.0f, 10.46f, 14.0f, 6.0f);
        pathBuilderA.verticalLineTo(9.3f);
        pathBuilderA.lineToRelative(5.4f, -3.07f);
        pathBuilderA.lineToRelative(0.0f, 0.01f);
        pathBuilderA.curveToRelative(0.59f, -0.31f, 1.32f, -0.33f, 1.94f, 0.03f);
        pathBuilderA.curveToRelative(0.36f, 0.21f, 0.63f, 0.51f, 0.8f, 0.85f);
        pathBuilderA.lineToRelative(0.0f, 0.0f);
        pathBuilderA.lineToRelative(0.79f, 1.67f);
        pathBuilderA.curveTo(15.58f, 10.1f, 16.94f, 11.0f, 18.5f, 11.0f);
        pathBuilderA.curveTo(19.33f, 11.0f, 20.0f, 11.67f, 20.0f, 12.5f);
        pathBuilderA.close();
        ImageVector imageVectorBuild = ImageVector.Builder.m4024addPathoIyEayM$default(builder, pathBuilderA.getNodes(), defaultFillType, "", solidColor, 1.0f, null, 1.0f, 1.0f, iM3803getButtKaPHkGw, iM3813getBevelLxFBmk8, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null).build();
        _elderly = imageVectorBuild;
        return imageVectorBuild;
    }
}
