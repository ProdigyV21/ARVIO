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

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\"\u0010\u0010\u0000\u001a\u0004\u0018\u00010\u0001X\u0082\u000e¢\u0006\u0002\n\u0000\"\u0015\u0010\u0002\u001a\u00020\u0001*\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"_brightness6", "Landroidx/compose/ui/graphics/vector/ImageVector;", "Brightness6", "Landroidx/compose/material/icons/Icons$Rounded;", "getBrightness6", "(Landroidx/compose/material/icons/Icons$Rounded;)Landroidx/compose/ui/graphics/vector/ImageVector;", "material-icons-extended_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class Brightness6Kt {
    private static ImageVector _brightness6;

    public static final ImageVector getBrightness6(Icons.Rounded rounded) {
        ImageVector imageVector = _brightness6;
        if (imageVector != null) {
            return imageVector;
        }
        ImageVector.Builder builder = new ImageVector.Builder("Rounded.Brightness6", Dp.m5678constructorimpl(24.0f), Dp.m5678constructorimpl(24.0f), 24.0f, 24.0f, 0L, 0, false, 96, null);
        int defaultFillType = VectorKt.getDefaultFillType();
        SolidColor solidColor = new SolidColor(Color.INSTANCE.m3498getBlack0d7_KjU(), null);
        int iM3803getButtKaPHkGw = StrokeCap.INSTANCE.m3803getButtKaPHkGw();
        int iM3813getBevelLxFBmk8 = StrokeJoin.INSTANCE.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderR = a.r(20.0f, 15.31f, 2.6f, -2.6f);
        pathBuilderR.curveToRelative(0.39f, -0.39f, 0.39f, -1.02f, 0.0f, -1.41f);
        pathBuilderR.lineTo(20.0f, 8.69f);
        pathBuilderR.lineTo(20.0f, 5.0f);
        pathBuilderR.curveToRelative(0.0f, -0.55f, -0.45f, -1.0f, -1.0f, -1.0f);
        pathBuilderR.horizontalLineToRelative(-3.69f);
        pathBuilderR.lineToRelative(-2.6f, -2.6f);
        pathBuilderR.curveToRelative(-0.39f, -0.39f, -1.02f, -0.39f, -1.41f, 0.0f);
        pathBuilderR.lineTo(8.69f, 4.0f);
        pathBuilderR.lineTo(5.0f, 4.0f);
        pathBuilderR.curveToRelative(-0.55f, 0.0f, -1.0f, 0.45f, -1.0f, 1.0f);
        pathBuilderR.verticalLineToRelative(3.69f);
        pathBuilderR.lineToRelative(-2.6f, 2.6f);
        pathBuilderR.curveToRelative(-0.39f, 0.39f, -0.39f, 1.02f, 0.0f, 1.41f);
        pathBuilderR.lineTo(4.0f, 15.3f);
        pathBuilderR.lineTo(4.0f, 19.0f);
        pathBuilderR.curveToRelative(0.0f, 0.55f, 0.45f, 1.0f, 1.0f, 1.0f);
        pathBuilderR.horizontalLineToRelative(3.69f);
        pathBuilderR.lineToRelative(2.6f, 2.6f);
        pathBuilderR.curveToRelative(0.39f, 0.39f, 1.02f, 0.39f, 1.41f, 0.0f);
        pathBuilderR.lineToRelative(2.6f, -2.6f);
        pathBuilderR.lineTo(19.0f, 20.0f);
        pathBuilderR.curveToRelative(0.55f, 0.0f, 1.0f, -0.45f, 1.0f, -1.0f);
        b.f(pathBuilderR, -3.69f, 12.0f, 16.9f);
        pathBuilderR.lineTo(12.0f, 7.1f);
        pathBuilderR.curveToRelative(0.0f, -0.61f, 0.55f, -1.11f, 1.15f, -0.99f);
        pathBuilderR.curveTo(15.91f, 6.65f, 18.0f, 9.08f, 18.0f, 12.0f);
        pathBuilderR.reflectiveCurveToRelative(-2.09f, 5.35f, -4.85f, 5.89f);
        pathBuilderR.curveToRelative(-0.6f, 0.12f, -1.15f, -0.38f, -1.15f, -0.99f);
        pathBuilderR.close();
        ImageVector imageVectorBuild = ImageVector.Builder.m4024addPathoIyEayM$default(builder, pathBuilderR.getNodes(), defaultFillType, "", solidColor, 1.0f, null, 1.0f, 1.0f, iM3803getButtKaPHkGw, iM3813getBevelLxFBmk8, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null).build();
        _brightness6 = imageVectorBuild;
        return imageVectorBuild;
    }
}
