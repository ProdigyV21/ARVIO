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

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\"\u0010\u0010\u0000\u001a\u0004\u0018\u00010\u0001X\u0082\u000e¢\u0006\u0002\n\u0000\"\u0015\u0010\u0002\u001a\u00020\u0001*\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"_pin", "Landroidx/compose/ui/graphics/vector/ImageVector;", "Pin", "Landroidx/compose/material/icons/Icons$Rounded;", "getPin", "(Landroidx/compose/material/icons/Icons$Rounded;)Landroidx/compose/ui/graphics/vector/ImageVector;", "material-icons-extended_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class PinKt {
    private static ImageVector _pin;

    public static final ImageVector getPin(Icons.Rounded rounded) {
        ImageVector imageVector = _pin;
        if (imageVector != null) {
            return imageVector;
        }
        ImageVector.Builder builder = new ImageVector.Builder("Rounded.Pin", Dp.m5678constructorimpl(24.0f), Dp.m5678constructorimpl(24.0f), 24.0f, 24.0f, 0L, 0, false, 96, null);
        int defaultFillType = VectorKt.getDefaultFillType();
        SolidColor solidColor = new SolidColor(Color.INSTANCE.m3498getBlack0d7_KjU(), null);
        int iM3803getButtKaPHkGw = StrokeCap.INSTANCE.m3803getButtKaPHkGw();
        int iM3813getBevelLxFBmk8 = StrokeJoin.INSTANCE.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderQ = a.q(20.0f, 4.0f, 4.0f);
        pathBuilderQ.curveTo(2.9f, 4.0f, 2.0f, 4.9f, 2.0f, 6.0f);
        pathBuilderQ.verticalLineToRelative(12.0f);
        pathBuilderQ.curveToRelative(0.0f, 1.1f, 0.9f, 2.0f, 2.0f, 2.0f);
        pathBuilderQ.horizontalLineToRelative(16.0f);
        pathBuilderQ.curveToRelative(1.1f, 0.0f, 2.0f, -0.9f, 2.0f, -2.0f);
        pathBuilderQ.verticalLineTo(6.0f);
        pathBuilderQ.curveTo(22.0f, 4.9f, 21.1f, 4.0f, 20.0f, 4.0f);
        pathBuilderQ.close();
        pathBuilderQ.moveTo(7.64f, 14.47f);
        pathBuilderQ.curveTo(7.64f, 14.76f, 7.4f, 15.0f, 7.11f, 15.0f);
        pathBuilderQ.horizontalLineTo(7.02f);
        pathBuilderQ.curveToRelative(-0.29f, 0.0f, -0.53f, -0.24f, -0.53f, -0.53f);
        pathBuilderQ.verticalLineTo(10.5f);
        pathBuilderQ.lineToRelative(-0.45f, 0.33f);
        pathBuilderQ.curveToRelative(-0.24f, 0.18f, -0.59f, 0.12f, -0.76f, -0.14f);
        pathBuilderQ.curveToRelative(-0.15f, -0.24f, -0.1f, -0.55f, 0.13f, -0.72f);
        pathBuilderQ.lineTo(6.6f, 9.12f);
        pathBuilderQ.curveTo(6.71f, 9.04f, 6.84f, 9.0f, 6.98f, 9.0f);
        pathBuilderQ.curveToRelative(0.36f, 0.0f, 0.66f, 0.29f, 0.66f, 0.66f);
        a.D(pathBuilderQ, 14.47f, 13.01f, 15.0f, -2.67f);
        pathBuilderQ.curveToRelative(-0.4f, 0.0f, -0.72f, -0.32f, -0.72f, -0.72f);
        pathBuilderQ.curveToRelative(0.0f, -0.19f, 0.08f, -0.38f, 0.21f, -0.51f);
        pathBuilderQ.curveToRelative(0.95f, -0.95f, 1.58f, -1.58f, 1.92f, -1.94f);
        pathBuilderQ.curveToRelative(0.4f, -0.42f, 0.54f, -0.69f, 0.54f, -1.06f);
        pathBuilderQ.curveToRelative(0.0f, -0.4f, -0.31f, -0.72f, -0.81f, -0.72f);
        pathBuilderQ.curveToRelative(-0.34f, 0.0f, -0.57f, 0.16f, -0.72f, 0.37f);
        pathBuilderQ.curveToRelative(-0.15f, 0.2f, -0.41f, 0.26f, -0.64f, 0.16f);
        pathBuilderQ.curveToRelative(-0.34f, -0.14f, -0.45f, -0.57f, -0.22f, -0.85f);
        pathBuilderQ.curveToRelative(0.15f, -0.19f, 0.37f, -0.38f, 0.67f, -0.53f);
        pathBuilderQ.curveToRelative(0.69f, -0.33f, 1.48f, -0.2f, 1.95f, 0.03f);
        pathBuilderQ.curveToRelative(0.86f, 0.44f, 0.91f, 1.24f, 0.91f, 1.48f);
        pathBuilderQ.curveToRelative(0.0f, 0.64f, -0.31f, 1.26f, -0.92f, 1.86f);
        pathBuilderQ.curveToRelative(-0.25f, 0.25f, -0.72f, 0.71f, -1.4f, 1.39f);
        pathBuilderQ.lineToRelative(0.03f, 0.05f);
        pathBuilderQ.horizontalLineToRelative(1.88f);
        pathBuilderQ.curveToRelative(0.27f, 0.0f, 0.49f, 0.22f, 0.49f, 0.49f);
        pathBuilderQ.reflectiveCurveTo(13.28f, 15.0f, 13.01f, 15.0f);
        pathBuilderQ.close();
        pathBuilderQ.moveTo(18.75f, 14.15f);
        pathBuilderQ.curveTo(18.67f, 14.28f, 18.19f, 15.0f, 16.99f, 15.0f);
        pathBuilderQ.curveToRelative(-0.03f, 0.0f, -1.23f, 0.06f, -1.83f, -0.98f);
        pathBuilderQ.curveToRelative(-0.15f, -0.26f, -0.04f, -0.6f, 0.24f, -0.71f);
        pathBuilderQ.lineToRelative(0.12f, -0.05f);
        pathBuilderQ.curveToRelative(0.22f, -0.09f, 0.47f, -0.01f, 0.59f, 0.19f);
        pathBuilderQ.curveToRelative(0.14f, 0.24f, 0.39f, 0.49f, 0.88f, 0.49f);
        pathBuilderQ.curveToRelative(0.41f, 0.0f, 0.89f, -0.28f, 0.89f, -0.77f);
        pathBuilderQ.curveToRelative(0.0f, -0.55f, -0.48f, -0.79f, -1.04f, -0.79f);
        pathBuilderQ.lineToRelative(0.0f, 0.0f);
        pathBuilderQ.curveToRelative(-0.27f, 0.0f, -0.49f, -0.23f, -0.49f, -0.5f);
        pathBuilderQ.curveToRelative(0.0f, -0.26f, 0.2f, -0.47f, 0.45f, -0.49f);
        pathBuilderQ.lineToRelative(0.0f, -0.01f);
        pathBuilderQ.curveToRelative(0.33f, 0.0f, 0.88f, -0.14f, 0.88f, -0.72f);
        pathBuilderQ.curveToRelative(0.0f, -0.39f, -0.31f, -0.65f, -0.75f, -0.65f);
        pathBuilderQ.curveToRelative(-0.32f, 0.0f, -0.53f, 0.13f, -0.67f, 0.3f);
        pathBuilderQ.curveToRelative(-0.14f, 0.18f, -0.37f, 0.26f, -0.58f, 0.17f);
        pathBuilderQ.lineToRelative(-0.08f, -0.03f);
        pathBuilderQ.curveToRelative(-0.3f, -0.12f, -0.4f, -0.5f, -0.2f, -0.75f);
        pathBuilderQ.curveTo(15.67f, 9.35f, 16.16f, 9.0f, 16.94f, 9.0f);
        pathBuilderQ.curveToRelative(1.09f, 0.0f, 1.54f, 0.64f, 1.62f, 0.75f);
        pathBuilderQ.curveToRelative(0.33f, 0.5f, 0.28f, 1.16f, 0.02f, 1.57f);
        pathBuilderQ.curveToRelative(-0.15f, 0.22f, -0.32f, 0.38f, -0.52f, 0.48f);
        pathBuilderQ.verticalLineToRelative(0.07f);
        pathBuilderQ.curveToRelative(0.28f, 0.11f, 0.51f, 0.28f, 0.68f, 0.52f);
        pathBuilderQ.curveTo(19.11f, 12.91f, 19.07f, 13.66f, 18.75f, 14.15f);
        pathBuilderQ.close();
        ImageVector imageVectorBuild = ImageVector.Builder.m4024addPathoIyEayM$default(builder, pathBuilderQ.getNodes(), defaultFillType, "", solidColor, 1.0f, null, 1.0f, 1.0f, iM3803getButtKaPHkGw, iM3813getBevelLxFBmk8, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null).build();
        _pin = imageVectorBuild;
        return imageVectorBuild;
    }
}
