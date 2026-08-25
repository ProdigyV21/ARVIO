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
import v.d;
import v.f;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\"\u0010\u0010\u0000\u001a\u0004\u0018\u00010\u0001X\u0082\u000e¢\u0006\u0002\n\u0000\"\u0015\u0010\u0002\u001a\u00020\u0001*\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"_extensionOff", "Landroidx/compose/ui/graphics/vector/ImageVector;", "ExtensionOff", "Landroidx/compose/material/icons/Icons$Filled;", "getExtensionOff", "(Landroidx/compose/material/icons/Icons$Filled;)Landroidx/compose/ui/graphics/vector/ImageVector;", "material-icons-extended_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class ExtensionOffKt {
    private static ImageVector _extensionOff;

    public static final ImageVector getExtensionOff(Icons.Filled filled) {
        ImageVector imageVector = _extensionOff;
        if (imageVector != null) {
            return imageVector;
        }
        ImageVector.Builder builder = new ImageVector.Builder("Filled.ExtensionOff", Dp.m5678constructorimpl(24.0f), Dp.m5678constructorimpl(24.0f), 24.0f, 24.0f, 0L, 0, false, 96, null);
        int defaultFillType = VectorKt.getDefaultFillType();
        SolidColor solidColor = new SolidColor(Color.INSTANCE.m3498getBlack0d7_KjU(), null);
        int iM3803getButtKaPHkGw = StrokeCap.INSTANCE.m3803getButtKaPHkGw();
        int iM3813getBevelLxFBmk8 = StrokeJoin.INSTANCE.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderR = a.r(19.78f, 22.61f, -1.63f, -1.63f);
        pathBuilderR.curveTo(18.1f, 20.98f, 18.05f, 21.0f, 18.0f, 21.0f);
        pathBuilderR.horizontalLineToRelative(-3.8f);
        pathBuilderR.curveToRelative(0.0f, -2.71f, -2.16f, -3.0f, -2.7f, -3.0f);
        pathBuilderR.reflectiveCurveToRelative(-2.7f, 0.29f, -2.7f, 3.0f);
        pathBuilderR.horizontalLineTo(5.0f);
        pathBuilderR.curveToRelative(-1.1f, 0.0f, -2.0f, -0.9f, -2.0f, -2.0f);
        pathBuilderR.verticalLineToRelative(-3.8f);
        pathBuilderR.curveToRelative(2.71f, 0.0f, 3.0f, -2.16f, 3.0f, -2.7f);
        pathBuilderR.curveToRelative(0.0f, -0.54f, -0.3f, -2.7f, -2.99f, -2.7f);
        pathBuilderR.verticalLineTo(6.0f);
        pathBuilderR.curveToRelative(0.0f, -0.05f, 0.02f, -0.09f, 0.02f, -0.14f);
        pathBuilderR.lineTo(1.39f, 4.22f);
        pathBuilderR.lineToRelative(1.41f, -1.41f);
        d.C(pathBuilderR, 18.38f, 18.38f, 19.78f, 22.61f);
        pathBuilderR.moveTo(20.0f, 17.17f);
        pathBuilderR.verticalLineTo(15.0f);
        pathBuilderR.curveToRelative(1.38f, 0.0f, 2.5f, -1.12f, 2.5f, -2.5f);
        pathBuilderR.reflectiveCurveTo(21.38f, 10.0f, 20.0f, 10.0f);
        pathBuilderR.verticalLineTo(6.0f);
        pathBuilderR.curveToRelative(0.0f, -1.1f, -0.9f, -2.0f, -2.0f, -2.0f);
        pathBuilderR.horizontalLineToRelative(-4.0f);
        pathBuilderR.curveToRelative(0.0f, -1.38f, -1.12f, -2.5f, -2.5f, -2.5f);
        pathBuilderR.reflectiveCurveTo(9.0f, 2.62f, 9.0f, 4.0f);
        ImageVector imageVectorBuild = ImageVector.Builder.m4024addPathoIyEayM$default(builder, f.i(pathBuilderR, 6.83f, 20.0f, 17.17f), defaultFillType, "", solidColor, 1.0f, null, 1.0f, 1.0f, iM3803getButtKaPHkGw, iM3813getBevelLxFBmk8, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null).build();
        _extensionOff = imageVectorBuild;
        return imageVectorBuild;
    }
}
