package androidx.compose.material.icons.outlined;

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

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\"\u0010\u0010\u0000\u001a\u0004\u0018\u00010\u0001X\u0082\u000e¢\u0006\u0002\n\u0000\"\u0015\u0010\u0002\u001a\u00020\u0001*\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"_waves", "Landroidx/compose/ui/graphics/vector/ImageVector;", "Waves", "Landroidx/compose/material/icons/Icons$Outlined;", "getWaves", "(Landroidx/compose/material/icons/Icons$Outlined;)Landroidx/compose/ui/graphics/vector/ImageVector;", "material-icons-extended_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class WavesKt {
    private static ImageVector _waves;

    public static final ImageVector getWaves(Icons.Outlined outlined) {
        ImageVector imageVector = _waves;
        if (imageVector != null) {
            return imageVector;
        }
        ImageVector.Builder builder = new ImageVector.Builder("Outlined.Waves", Dp.m5678constructorimpl(24.0f), Dp.m5678constructorimpl(24.0f), 24.0f, 24.0f, 0L, 0, false, 96, null);
        int defaultFillType = VectorKt.getDefaultFillType();
        SolidColor solidColor = new SolidColor(Color.INSTANCE.m3498getBlack0d7_KjU(), null);
        int iM3803getButtKaPHkGw = StrokeCap.INSTANCE.m3803getButtKaPHkGw();
        int iM3813getBevelLxFBmk8 = StrokeJoin.INSTANCE.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderA = c.a(17.0f, 16.99f);
        pathBuilderA.curveToRelative(-1.35f, 0.0f, -2.2f, 0.42f, -2.95f, 0.8f);
        pathBuilderA.curveToRelative(-0.65f, 0.33f, -1.18f, 0.6f, -2.05f, 0.6f);
        pathBuilderA.curveToRelative(-0.9f, 0.0f, -1.4f, -0.25f, -2.05f, -0.6f);
        pathBuilderA.curveToRelative(-0.75f, -0.38f, -1.57f, -0.8f, -2.95f, -0.8f);
        pathBuilderA.reflectiveCurveToRelative(-2.2f, 0.42f, -2.95f, 0.8f);
        pathBuilderA.curveToRelative(-0.65f, 0.33f, -1.17f, 0.6f, -2.05f, 0.6f);
        pathBuilderA.verticalLineToRelative(1.95f);
        pathBuilderA.curveToRelative(1.35f, 0.0f, 2.2f, -0.42f, 2.95f, -0.8f);
        pathBuilderA.curveToRelative(0.65f, -0.33f, 1.17f, -0.6f, 2.05f, -0.6f);
        pathBuilderA.reflectiveCurveToRelative(1.4f, 0.25f, 2.05f, 0.6f);
        pathBuilderA.curveToRelative(0.75f, 0.38f, 1.57f, 0.8f, 2.95f, 0.8f);
        pathBuilderA.reflectiveCurveToRelative(2.2f, -0.42f, 2.95f, -0.8f);
        pathBuilderA.curveToRelative(0.65f, -0.33f, 1.18f, -0.6f, 2.05f, -0.6f);
        pathBuilderA.curveToRelative(0.9f, 0.0f, 1.4f, 0.25f, 2.05f, 0.6f);
        pathBuilderA.curveToRelative(0.75f, 0.38f, 1.58f, 0.8f, 2.95f, 0.8f);
        pathBuilderA.verticalLineToRelative(-1.95f);
        pathBuilderA.curveToRelative(-0.9f, 0.0f, -1.4f, -0.25f, -2.05f, -0.6f);
        pathBuilderA.curveToRelative(-0.75f, -0.38f, -1.6f, -0.8f, -2.95f, -0.8f);
        pathBuilderA.close();
        pathBuilderA.moveTo(17.0f, 12.54f);
        pathBuilderA.curveToRelative(-1.35f, 0.0f, -2.2f, 0.43f, -2.95f, 0.8f);
        pathBuilderA.curveToRelative(-0.65f, 0.32f, -1.18f, 0.6f, -2.05f, 0.6f);
        pathBuilderA.curveToRelative(-0.9f, 0.0f, -1.4f, -0.25f, -2.05f, -0.6f);
        pathBuilderA.curveToRelative(-0.75f, -0.38f, -1.57f, -0.8f, -2.95f, -0.8f);
        pathBuilderA.reflectiveCurveToRelative(-2.2f, 0.43f, -2.95f, 0.8f);
        pathBuilderA.curveToRelative(-0.65f, 0.32f, -1.17f, 0.6f, -2.05f, 0.6f);
        pathBuilderA.verticalLineToRelative(1.95f);
        pathBuilderA.curveToRelative(1.35f, 0.0f, 2.2f, -0.43f, 2.95f, -0.8f);
        pathBuilderA.curveToRelative(0.65f, -0.35f, 1.15f, -0.6f, 2.05f, -0.6f);
        pathBuilderA.reflectiveCurveToRelative(1.4f, 0.25f, 2.05f, 0.6f);
        pathBuilderA.curveToRelative(0.75f, 0.38f, 1.57f, 0.8f, 2.95f, 0.8f);
        pathBuilderA.reflectiveCurveToRelative(2.2f, -0.43f, 2.95f, -0.8f);
        pathBuilderA.curveToRelative(0.65f, -0.35f, 1.15f, -0.6f, 2.05f, -0.6f);
        pathBuilderA.reflectiveCurveToRelative(1.4f, 0.25f, 2.05f, 0.6f);
        pathBuilderA.curveToRelative(0.75f, 0.38f, 1.58f, 0.8f, 2.95f, 0.8f);
        pathBuilderA.verticalLineToRelative(-1.95f);
        pathBuilderA.curveToRelative(-0.9f, 0.0f, -1.4f, -0.25f, -2.05f, -0.6f);
        pathBuilderA.curveToRelative(-0.75f, -0.38f, -1.6f, -0.8f, -2.95f, -0.8f);
        pathBuilderA.close();
        pathBuilderA.moveTo(19.95f, 4.46f);
        pathBuilderA.curveToRelative(-0.75f, -0.38f, -1.58f, -0.8f, -2.95f, -0.8f);
        pathBuilderA.reflectiveCurveToRelative(-2.2f, 0.42f, -2.95f, 0.8f);
        pathBuilderA.curveToRelative(-0.65f, 0.32f, -1.18f, 0.6f, -2.05f, 0.6f);
        pathBuilderA.curveToRelative(-0.9f, 0.0f, -1.4f, -0.25f, -2.05f, -0.6f);
        pathBuilderA.curveToRelative(-0.75f, -0.37f, -1.57f, -0.8f, -2.95f, -0.8f);
        pathBuilderA.reflectiveCurveToRelative(-2.2f, 0.42f, -2.95f, 0.8f);
        pathBuilderA.curveToRelative(-0.65f, 0.33f, -1.17f, 0.6f, -2.05f, 0.6f);
        pathBuilderA.verticalLineToRelative(1.93f);
        pathBuilderA.curveToRelative(1.35f, 0.0f, 2.2f, -0.43f, 2.95f, -0.8f);
        pathBuilderA.curveToRelative(0.65f, -0.33f, 1.17f, -0.6f, 2.05f, -0.6f);
        pathBuilderA.reflectiveCurveToRelative(1.4f, 0.25f, 2.05f, 0.6f);
        pathBuilderA.curveToRelative(0.75f, 0.38f, 1.57f, 0.8f, 2.95f, 0.8f);
        pathBuilderA.reflectiveCurveToRelative(2.2f, -0.43f, 2.95f, -0.8f);
        pathBuilderA.curveToRelative(0.65f, -0.32f, 1.18f, -0.6f, 2.05f, -0.6f);
        pathBuilderA.curveToRelative(0.9f, 0.0f, 1.4f, 0.25f, 2.05f, 0.6f);
        pathBuilderA.curveToRelative(0.75f, 0.38f, 1.58f, 0.8f, 2.95f, 0.8f);
        pathBuilderA.lineTo(22.0f, 5.04f);
        pathBuilderA.curveToRelative(-0.9f, 0.0f, -1.4f, -0.25f, -2.05f, -0.58f);
        pathBuilderA.close();
        pathBuilderA.moveTo(17.0f, 8.09f);
        pathBuilderA.curveToRelative(-1.35f, 0.0f, -2.2f, 0.43f, -2.95f, 0.8f);
        pathBuilderA.curveToRelative(-0.65f, 0.35f, -1.15f, 0.6f, -2.05f, 0.6f);
        pathBuilderA.reflectiveCurveToRelative(-1.4f, -0.25f, -2.05f, -0.6f);
        pathBuilderA.curveToRelative(-0.75f, -0.38f, -1.57f, -0.8f, -2.95f, -0.8f);
        pathBuilderA.reflectiveCurveToRelative(-2.2f, 0.43f, -2.95f, 0.8f);
        pathBuilderA.curveToRelative(-0.65f, 0.35f, -1.15f, 0.6f, -2.05f, 0.6f);
        pathBuilderA.verticalLineToRelative(1.95f);
        pathBuilderA.curveToRelative(1.35f, 0.0f, 2.2f, -0.43f, 2.95f, -0.8f);
        pathBuilderA.curveToRelative(0.65f, -0.32f, 1.18f, -0.6f, 2.05f, -0.6f);
        pathBuilderA.reflectiveCurveToRelative(1.4f, 0.25f, 2.05f, 0.6f);
        pathBuilderA.curveToRelative(0.75f, 0.38f, 1.57f, 0.8f, 2.95f, 0.8f);
        pathBuilderA.reflectiveCurveToRelative(2.2f, -0.43f, 2.95f, -0.8f);
        pathBuilderA.curveToRelative(0.65f, -0.32f, 1.18f, -0.6f, 2.05f, -0.6f);
        pathBuilderA.curveToRelative(0.9f, 0.0f, 1.4f, 0.25f, 2.05f, 0.6f);
        pathBuilderA.curveToRelative(0.75f, 0.38f, 1.58f, 0.8f, 2.95f, 0.8f);
        pathBuilderA.lineTo(22.0f, 9.49f);
        pathBuilderA.curveToRelative(-0.9f, 0.0f, -1.4f, -0.25f, -2.05f, -0.6f);
        pathBuilderA.curveToRelative(-0.75f, -0.38f, -1.6f, -0.8f, -2.95f, -0.8f);
        pathBuilderA.close();
        ImageVector imageVectorBuild = ImageVector.Builder.m4024addPathoIyEayM$default(builder, pathBuilderA.getNodes(), defaultFillType, "", solidColor, 1.0f, null, 1.0f, 1.0f, iM3803getButtKaPHkGw, iM3813getBevelLxFBmk8, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null).build();
        _waves = imageVectorBuild;
        return imageVectorBuild;
    }
}
