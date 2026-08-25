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
import v.d;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\"\u0010\u0010\u0000\u001a\u0004\u0018\u00010\u0001X\u0082\u000e¢\u0006\u0002\n\u0000\"\u0015\u0010\u0002\u001a\u00020\u0001*\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"_powerOff", "Landroidx/compose/ui/graphics/vector/ImageVector;", "PowerOff", "Landroidx/compose/material/icons/Icons$Rounded;", "getPowerOff", "(Landroidx/compose/material/icons/Icons$Rounded;)Landroidx/compose/ui/graphics/vector/ImageVector;", "material-icons-extended_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class PowerOffKt {
    private static ImageVector _powerOff;

    public static final ImageVector getPowerOff(Icons.Rounded rounded) {
        ImageVector imageVector = _powerOff;
        if (imageVector != null) {
            return imageVector;
        }
        ImageVector.Builder builder = new ImageVector.Builder("Rounded.PowerOff", Dp.m5678constructorimpl(24.0f), Dp.m5678constructorimpl(24.0f), 24.0f, 24.0f, 0L, 0, false, 96, null);
        int defaultFillType = VectorKt.getDefaultFillType();
        SolidColor solidColor = new SolidColor(Color.INSTANCE.m3498getBlack0d7_KjU(), null);
        int iM3803getButtKaPHkGw = StrokeCap.INSTANCE.m3803getButtKaPHkGw();
        int iM3813getBevelLxFBmk8 = StrokeJoin.INSTANCE.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderJ = a.j(18.0f, 13.66f, 18.0f, 8.99f);
        pathBuilderJ.curveToRelative(0.0f, -1.0f, -1.01f, -2.0f, -2.0f, -1.99f);
        pathBuilderJ.lineTo(16.0f, 4.0f);
        pathBuilderJ.curveToRelative(0.0f, -0.55f, -0.45f, -1.0f, -1.0f, -1.0f);
        pathBuilderJ.reflectiveCurveToRelative(-1.0f, 0.45f, -1.0f, 1.0f);
        d.D(pathBuilderJ, 3.0f, -3.88f, 7.63f, 7.63f);
        pathBuilderJ.curveToRelative(0.15f, -0.3f, 0.25f, -0.63f, 0.25f, -0.97f);
        pathBuilderJ.close();
        pathBuilderJ.moveTo(10.0f, 4.0f);
        pathBuilderJ.curveToRelative(0.0f, -0.55f, -0.45f, -1.0f, -1.0f, -1.0f);
        pathBuilderJ.reflectiveCurveToRelative(-1.0f, 0.45f, -1.0f, 1.0f);
        pathBuilderJ.verticalLineToRelative(0.88f);
        d.C(pathBuilderJ, 2.0f, 2.0f, 10.0f, 4.0f);
        pathBuilderJ.moveTo(20.15f, 19.86f);
        pathBuilderJ.lineToRelative(-7.66f, -7.66f);
        pathBuilderJ.lineToRelative(-5.1f, -5.1f);
        pathBuilderJ.lineToRelative(-2.56f, -2.56f);
        pathBuilderJ.curveToRelative(-0.39f, -0.39f, -1.02f, -0.39f, -1.41f, 0.0f);
        pathBuilderJ.curveToRelative(-0.39f, 0.39f, -0.39f, 1.02f, 0.0f, 1.41f);
        pathBuilderJ.lineToRelative(2.63f, 2.63f);
        pathBuilderJ.curveToRelative(-0.03f, 0.13f, -0.05f, 0.27f, -0.05f, 0.41f);
        pathBuilderJ.verticalLineToRelative(4.66f);
        pathBuilderJ.curveToRelative(0.0f, 0.53f, 0.21f, 1.04f, 0.58f, 1.41f);
        pathBuilderJ.lineTo(9.5f, 18.0f);
        pathBuilderJ.verticalLineToRelative(2.0f);
        pathBuilderJ.curveToRelative(0.0f, 0.55f, 0.45f, 1.0f, 1.0f, 1.0f);
        pathBuilderJ.horizontalLineToRelative(3.0f);
        pathBuilderJ.curveToRelative(0.55f, 0.0f, 1.0f, -0.45f, 1.0f, -1.0f);
        pathBuilderJ.verticalLineToRelative(-2.0f);
        pathBuilderJ.lineToRelative(0.48f, -0.48f);
        pathBuilderJ.lineToRelative(3.76f, 3.76f);
        pathBuilderJ.curveToRelative(0.39f, 0.39f, 1.02f, 0.39f, 1.41f, 0.0f);
        pathBuilderJ.curveToRelative(0.39f, -0.39f, 0.39f, -1.03f, 0.0f, -1.42f);
        pathBuilderJ.close();
        ImageVector imageVectorBuild = ImageVector.Builder.m4024addPathoIyEayM$default(builder, pathBuilderJ.getNodes(), defaultFillType, "", solidColor, 1.0f, null, 1.0f, 1.0f, iM3803getButtKaPHkGw, iM3813getBevelLxFBmk8, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null).build();
        _powerOff = imageVectorBuild;
        return imageVectorBuild;
    }
}
