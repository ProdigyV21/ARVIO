package androidx.compose.material.icons.rounded;

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
import v.f;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\"\u0010\u0010\u0000\u001a\u0004\u0018\u00010\u0001X\u0082\u000e¢\u0006\u0002\n\u0000\"\u0015\u0010\u0002\u001a\u00020\u0001*\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"_fiberPin", "Landroidx/compose/ui/graphics/vector/ImageVector;", "FiberPin", "Landroidx/compose/material/icons/Icons$Rounded;", "getFiberPin", "(Landroidx/compose/material/icons/Icons$Rounded;)Landroidx/compose/ui/graphics/vector/ImageVector;", "material-icons-extended_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class FiberPinKt {
    private static ImageVector _fiberPin;

    public static final ImageVector getFiberPin(Icons.Rounded rounded) {
        ImageVector imageVector = _fiberPin;
        if (imageVector != null) {
            return imageVector;
        }
        ImageVector.Builder builder = new ImageVector.Builder("Rounded.FiberPin", Dp.m5678constructorimpl(24.0f), Dp.m5678constructorimpl(24.0f), 24.0f, 24.0f, 0L, 0, false, 96, null);
        int defaultFillType = VectorKt.getDefaultFillType();
        SolidColor solidColor = new SolidColor(Color.INSTANCE.m3498getBlack0d7_KjU(), null);
        int iM3803getButtKaPHkGw = StrokeCap.INSTANCE.m3803getButtKaPHkGw();
        int iM3813getBevelLxFBmk8 = StrokeJoin.INSTANCE.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderJ = a.j(20.0f, 4.0f, 4.0f, 4.0f);
        pathBuilderJ.curveToRelative(-1.11f, 0.0f, -1.99f, 0.89f, -1.99f, 2.0f);
        pathBuilderJ.lineTo(2.0f, 18.0f);
        pathBuilderJ.curveToRelative(0.0f, 1.11f, 0.89f, 2.0f, 2.0f, 2.0f);
        pathBuilderJ.horizontalLineToRelative(16.0f);
        pathBuilderJ.curveToRelative(1.11f, 0.0f, 2.0f, -0.89f, 2.0f, -2.0f);
        pathBuilderJ.lineTo(22.0f, 6.0f);
        pathBuilderJ.curveToRelative(0.0f, -1.11f, -0.89f, -2.0f, -2.0f, -2.0f);
        pathBuilderJ.close();
        pathBuilderJ.moveTo(9.0f, 11.5f);
        pathBuilderJ.curveToRelative(0.0f, 0.83f, -0.67f, 1.5f, -1.5f, 1.5f);
        pathBuilderJ.horizontalLineToRelative(-2.0f);
        pathBuilderJ.verticalLineToRelative(1.25f);
        pathBuilderJ.curveToRelative(0.0f, 0.41f, -0.34f, 0.75f, -0.75f, 0.75f);
        pathBuilderJ.reflectiveCurveTo(4.0f, 14.66f, 4.0f, 14.25f);
        pathBuilderJ.lineTo(4.0f, 10.0f);
        pathBuilderJ.curveToRelative(0.0f, -0.55f, 0.45f, -1.0f, 1.0f, -1.0f);
        pathBuilderJ.horizontalLineToRelative(2.5f);
        pathBuilderJ.curveToRelative(0.83f, 0.0f, 1.5f, 0.67f, 1.5f, 1.5f);
        b.f(pathBuilderJ, 1.0f, 12.5f, 14.25f);
        pathBuilderJ.curveToRelative(0.0f, 0.41f, -0.34f, 0.75f, -0.75f, 0.75f);
        pathBuilderJ.reflectiveCurveToRelative(-0.75f, -0.34f, -0.75f, -0.75f);
        pathBuilderJ.verticalLineToRelative(-4.5f);
        pathBuilderJ.curveToRelative(0.0f, -0.41f, 0.34f, -0.75f, 0.75f, -0.75f);
        pathBuilderJ.reflectiveCurveToRelative(0.75f, 0.34f, 0.75f, 0.75f);
        b.f(pathBuilderJ, 4.5f, 20.0f, 14.21f);
        pathBuilderJ.curveToRelative(0.0f, 0.44f, -0.35f, 0.79f, -0.79f, 0.79f);
        pathBuilderJ.curveToRelative(-0.25f, 0.0f, -0.49f, -0.12f, -0.64f, -0.33f);
        pathBuilderJ.lineToRelative(-2.31f, -3.17f);
        pathBuilderJ.verticalLineToRelative(2.88f);
        pathBuilderJ.curveToRelative(0.0f, 0.34f, -0.28f, 0.62f, -0.62f, 0.62f);
        pathBuilderJ.horizontalLineToRelative(-0.01f);
        pathBuilderJ.curveToRelative(-0.35f, 0.0f, -0.63f, -0.28f, -0.63f, -0.62f);
        pathBuilderJ.lineTo(15.0f, 9.83f);
        pathBuilderJ.curveToRelative(0.0f, -0.46f, 0.37f, -0.83f, 0.83f, -0.83f);
        pathBuilderJ.curveToRelative(0.27f, 0.0f, 0.52f, 0.13f, 0.67f, 0.35f);
        pathBuilderJ.lineToRelative(2.25f, 3.15f);
        pathBuilderJ.lineTo(18.75f, 9.62f);
        pathBuilderJ.curveToRelative(0.0f, -0.34f, 0.28f, -0.62f, 0.62f, -0.62f);
        pathBuilderJ.horizontalLineToRelative(0.01f);
        pathBuilderJ.curveToRelative(0.34f, 0.0f, 0.62f, 0.28f, 0.62f, 0.62f);
        c.r(pathBuilderJ, 4.59f, 5.5f, 10.5f, 2.0f);
        ImageVector imageVectorBuild = ImageVector.Builder.m4024addPathoIyEayM$default(builder, f.c(pathBuilderJ, 1.0f, -2.0f), defaultFillType, "", solidColor, 1.0f, null, 1.0f, 1.0f, iM3803getButtKaPHkGw, iM3813getBevelLxFBmk8, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null).build();
        _fiberPin = imageVectorBuild;
        return imageVectorBuild;
    }
}
