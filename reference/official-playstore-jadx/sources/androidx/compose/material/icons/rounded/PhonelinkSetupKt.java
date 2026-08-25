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

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\"\u0010\u0010\u0000\u001a\u0004\u0018\u00010\u0001X\u0082\u000e¢\u0006\u0002\n\u0000\"\u0015\u0010\u0002\u001a\u00020\u0001*\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"_phonelinkSetup", "Landroidx/compose/ui/graphics/vector/ImageVector;", "PhonelinkSetup", "Landroidx/compose/material/icons/Icons$Rounded;", "getPhonelinkSetup", "(Landroidx/compose/material/icons/Icons$Rounded;)Landroidx/compose/ui/graphics/vector/ImageVector;", "material-icons-extended_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class PhonelinkSetupKt {
    private static ImageVector _phonelinkSetup;

    public static final ImageVector getPhonelinkSetup(Icons.Rounded rounded) {
        ImageVector imageVector = _phonelinkSetup;
        if (imageVector != null) {
            return imageVector;
        }
        ImageVector.Builder builder = new ImageVector.Builder("Rounded.PhonelinkSetup", Dp.m5678constructorimpl(24.0f), Dp.m5678constructorimpl(24.0f), 24.0f, 24.0f, 0L, 0, false, 96, null);
        int defaultFillType = VectorKt.getDefaultFillType();
        SolidColor solidColor = new SolidColor(Color.INSTANCE.m3498getBlack0d7_KjU(), null);
        int iM3803getButtKaPHkGw = StrokeCap.INSTANCE.m3803getButtKaPHkGw();
        int iM3813getBevelLxFBmk8 = StrokeJoin.INSTANCE.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderI = a.i(7.0f, 3.0f, 2.0f);
        pathBuilderI.curveToRelative(0.0f, 0.55f, 0.45f, 1.0f, 1.0f, 1.0f);
        pathBuilderI.reflectiveCurveToRelative(1.0f, -0.45f, 1.0f, -1.0f);
        b.A(pathBuilderI, 9.0f, 4.0f, 10.0f, 16.0f);
        pathBuilderI.lineTo(9.0f, 20.0f);
        pathBuilderI.verticalLineToRelative(-1.0f);
        pathBuilderI.curveToRelative(0.0f, -0.55f, -0.45f, -1.0f, -1.0f, -1.0f);
        pathBuilderI.reflectiveCurveToRelative(-1.0f, 0.45f, -1.0f, 1.0f);
        pathBuilderI.verticalLineToRelative(2.0f);
        pathBuilderI.curveToRelative(0.0f, 1.1f, 0.9f, 2.0f, 2.0f, 2.0f);
        pathBuilderI.horizontalLineToRelative(10.0f);
        pathBuilderI.curveToRelative(1.1f, 0.0f, 2.0f, -0.9f, 2.0f, -2.0f);
        pathBuilderI.lineTo(21.0f, 3.0f);
        pathBuilderI.curveToRelative(0.0f, -1.1f, -0.9f, -2.0f, -2.0f, -2.0f);
        pathBuilderI.lineTo(9.0f, 1.0f);
        pathBuilderI.curveToRelative(-1.1f, 0.0f, -2.0f, 0.9f, -2.0f, 2.0f);
        pathBuilderI.close();
        pathBuilderI.moveTo(9.5f, 15.5f);
        pathBuilderI.curveToRelative(0.29f, -0.12f, 0.55f, -0.29f, 0.8f, -0.48f);
        pathBuilderI.lineToRelative(-0.02f, 0.03f);
        pathBuilderI.lineToRelative(1.01f, 0.39f);
        pathBuilderI.curveToRelative(0.23f, 0.09f, 0.49f, 0.0f, 0.61f, -0.22f);
        pathBuilderI.lineToRelative(0.84f, -1.46f);
        pathBuilderI.curveToRelative(0.12f, -0.21f, 0.07f, -0.49f, -0.12f, -0.64f);
        pathBuilderI.lineToRelative(-0.85f, -0.68f);
        pathBuilderI.lineToRelative(-0.02f, 0.03f);
        pathBuilderI.curveToRelative(0.02f, -0.16f, 0.05f, -0.32f, 0.05f, -0.48f);
        pathBuilderI.reflectiveCurveToRelative(-0.03f, -0.32f, -0.05f, -0.48f);
        pathBuilderI.lineToRelative(0.02f, 0.03f);
        pathBuilderI.lineToRelative(0.85f, -0.68f);
        pathBuilderI.curveToRelative(0.19f, -0.15f, 0.24f, -0.43f, 0.12f, -0.64f);
        pathBuilderI.lineToRelative(-0.84f, -1.46f);
        pathBuilderI.curveToRelative(-0.12f, -0.21f, -0.38f, -0.31f, -0.61f, -0.22f);
        pathBuilderI.lineToRelative(-1.01f, 0.39f);
        pathBuilderI.lineToRelative(0.02f, 0.03f);
        pathBuilderI.curveToRelative(-0.25f, -0.17f, -0.51f, -0.34f, -0.8f, -0.46f);
        pathBuilderI.lineToRelative(-0.17f, -1.08f);
        pathBuilderI.curveTo(9.3f, 7.18f, 9.09f, 7.0f, 8.84f, 7.0f);
        pathBuilderI.lineTo(7.16f, 7.0f);
        pathBuilderI.curveToRelative(-0.25f, 0.0f, -0.46f, 0.18f, -0.49f, 0.42f);
        pathBuilderI.lineTo(6.5f, 8.5f);
        pathBuilderI.curveToRelative(-0.29f, 0.12f, -0.55f, 0.29f, -0.8f, 0.48f);
        pathBuilderI.lineToRelative(0.02f, -0.03f);
        pathBuilderI.lineToRelative(-1.02f, -0.39f);
        pathBuilderI.curveToRelative(-0.23f, -0.09f, -0.49f, 0.0f, -0.61f, 0.22f);
        pathBuilderI.lineToRelative(-0.84f, 1.46f);
        pathBuilderI.curveToRelative(-0.12f, 0.21f, -0.07f, 0.49f, 0.12f, 0.64f);
        pathBuilderI.lineToRelative(0.85f, 0.68f);
        pathBuilderI.lineToRelative(0.02f, -0.03f);
        pathBuilderI.curveToRelative(-0.02f, 0.15f, -0.05f, 0.31f, -0.05f, 0.47f);
        pathBuilderI.reflectiveCurveToRelative(0.03f, 0.32f, 0.05f, 0.48f);
        pathBuilderI.lineToRelative(-0.02f, -0.03f);
        pathBuilderI.lineToRelative(-0.85f, 0.68f);
        pathBuilderI.curveToRelative(-0.19f, 0.15f, -0.24f, 0.43f, -0.12f, 0.64f);
        pathBuilderI.lineToRelative(0.84f, 1.46f);
        pathBuilderI.curveToRelative(0.12f, 0.21f, 0.38f, 0.31f, 0.61f, 0.22f);
        pathBuilderI.lineToRelative(1.01f, -0.39f);
        pathBuilderI.lineToRelative(-0.01f, -0.04f);
        pathBuilderI.curveToRelative(0.25f, 0.19f, 0.51f, 0.36f, 0.8f, 0.48f);
        pathBuilderI.lineToRelative(0.17f, 1.07f);
        pathBuilderI.curveToRelative(0.03f, 0.25f, 0.24f, 0.43f, 0.49f, 0.43f);
        pathBuilderI.horizontalLineToRelative(1.68f);
        pathBuilderI.curveToRelative(0.25f, 0.0f, 0.46f, -0.18f, 0.49f, -0.42f);
        b.C(pathBuilderI, 0.17f, -1.08f, 6.0f, 12.0f);
        pathBuilderI.curveToRelative(0.0f, -1.1f, 0.9f, -2.0f, 2.0f, -2.0f);
        pathBuilderI.reflectiveCurveToRelative(2.0f, 0.9f, 2.0f, 2.0f);
        pathBuilderI.reflectiveCurveToRelative(-0.9f, 2.0f, -2.0f, 2.0f);
        ImageVector imageVectorBuild = ImageVector.Builder.m4024addPathoIyEayM$default(builder, c.h(pathBuilderI, -2.0f, -0.9f, -2.0f, -2.0f), defaultFillType, "", solidColor, 1.0f, null, 1.0f, 1.0f, iM3803getButtKaPHkGw, iM3813getBevelLxFBmk8, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null).build();
        _phonelinkSetup = imageVectorBuild;
        return imageVectorBuild;
    }
}
