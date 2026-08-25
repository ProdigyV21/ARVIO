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
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\"\u0010\u0010\u0000\u001a\u0004\u0018\u00010\u0001X\u0082\u000e¢\u0006\u0002\n\u0000\"\u0015\u0010\u0002\u001a\u00020\u0001*\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"_shutterSpeed", "Landroidx/compose/ui/graphics/vector/ImageVector;", "ShutterSpeed", "Landroidx/compose/material/icons/Icons$Rounded;", "getShutterSpeed", "(Landroidx/compose/material/icons/Icons$Rounded;)Landroidx/compose/ui/graphics/vector/ImageVector;", "material-icons-extended_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class ShutterSpeedKt {
    private static ImageVector _shutterSpeed;

    public static final ImageVector getShutterSpeed(Icons.Rounded rounded) {
        ImageVector imageVector = _shutterSpeed;
        if (imageVector != null) {
            return imageVector;
        }
        ImageVector.Builder builder = new ImageVector.Builder("Rounded.ShutterSpeed", Dp.m5678constructorimpl(24.0f), Dp.m5678constructorimpl(24.0f), 24.0f, 24.0f, 0L, 0, false, 96, null);
        int defaultFillType = VectorKt.getDefaultFillType();
        SolidColor solidColor = new SolidColor(Color.INSTANCE.m3498getBlack0d7_KjU(), null);
        int iM3803getButtKaPHkGw = StrokeCap.INSTANCE.m3803getButtKaPHkGw();
        int iM3813getBevelLxFBmk8 = StrokeJoin.INSTANCE.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderA = a.a(10.0f, 3.0f, 4.0f);
        pathBuilderA.curveToRelative(0.55f, 0.0f, 1.0f, -0.45f, 1.0f, -1.0f);
        pathBuilderA.reflectiveCurveToRelative(-0.45f, -1.0f, -1.0f, -1.0f);
        pathBuilderA.horizontalLineToRelative(-4.0f);
        pathBuilderA.curveToRelative(-0.55f, 0.0f, -1.0f, 0.45f, -1.0f, 1.0f);
        pathBuilderA.reflectiveCurveToRelative(0.45f, 1.0f, 1.0f, 1.0f);
        a.z(pathBuilderA, 19.03f, 7.39f, 0.75f, -0.75f);
        pathBuilderA.curveToRelative(0.38f, -0.38f, 0.39f, -1.01f, 0.0f, -1.4f);
        pathBuilderA.lineToRelative(-0.01f, -0.01f);
        pathBuilderA.curveToRelative(-0.39f, -0.39f, -1.01f, -0.38f, -1.4f, 0.0f);
        pathBuilderA.lineToRelative(-0.75f, 0.75f);
        pathBuilderA.curveTo(16.07f, 4.74f, 14.12f, 4.0f, 12.0f, 4.0f);
        pathBuilderA.curveToRelative(-4.8f, 0.0f, -8.88f, 3.96f, -9.0f, 8.76f);
        pathBuilderA.curveTo(2.87f, 17.84f, 6.94f, 22.0f, 12.0f, 22.0f);
        pathBuilderA.curveToRelative(4.98f, 0.0f, 9.0f, -4.03f, 9.0f, -9.0f);
        pathBuilderA.curveToRelative(0.0f, -2.12f, -0.74f, -4.07f, -1.97f, -5.61f);
        pathBuilderA.close();
        pathBuilderA.moveTo(12.0f, 20.0f);
        pathBuilderA.curveToRelative(-3.87f, 0.0f, -7.0f, -3.13f, -7.0f, -7.0f);
        pathBuilderA.reflectiveCurveToRelative(3.13f, -7.0f, 7.0f, -7.0f);
        pathBuilderA.reflectiveCurveToRelative(7.0f, 3.13f, 7.0f, 7.0f);
        pathBuilderA.reflectiveCurveToRelative(-3.13f, 7.0f, -7.0f, 7.0f);
        a.g(pathBuilderA, 10.81f, 15.0f, -3.7f);
        pathBuilderA.curveToRelative(-0.38f, 0.0f, -0.62f, 0.4f, -0.45f, 0.74f);
        pathBuilderA.curveToRelative(0.56f, 1.12f, 1.44f, 2.01f, 2.57f, 2.57f);
        pathBuilderA.curveToRelative(0.23f, 0.11f, 0.52f, 0.02f, 0.65f, -0.21f);
        pathBuilderA.lineToRelative(1.37f, -2.35f);
        pathBuilderA.curveToRelative(0.19f, -0.33f, -0.05f, -0.75f, -0.44f, -0.75f);
        pathBuilderA.close();
        pathBuilderA.moveTo(14.73f, 7.65f);
        pathBuilderA.curveToRelative(-0.23f, -0.12f, -0.52f, -0.02f, -0.65f, 0.2f);
        pathBuilderA.lineToRelative(-1.38f, 2.39f);
        pathBuilderA.curveToRelative(-0.2f, 0.34f, 0.04f, 0.76f, 0.43f, 0.76f);
        pathBuilderA.horizontalLineToRelative(3.76f);
        pathBuilderA.curveToRelative(0.38f, 0.0f, 0.62f, -0.4f, 0.45f, -0.73f);
        pathBuilderA.curveToRelative(-0.58f, -1.13f, -1.49f, -2.04f, -2.61f, -2.62f);
        pathBuilderA.close();
        pathBuilderA.moveTo(13.88f, 14.7f);
        pathBuilderA.curveToRelative(-0.19f, -0.34f, -0.68f, -0.35f, -0.87f, -0.01f);
        pathBuilderA.lineToRelative(-2.04f, 3.52f);
        pathBuilderA.curveToRelative(-0.18f, 0.32f, 0.02f, 0.72f, 0.39f, 0.75f);
        pathBuilderA.curveToRelative(1.34f, 0.14f, 2.69f, -0.18f, 3.83f, -0.89f);
        pathBuilderA.curveToRelative(0.22f, -0.14f, 0.28f, -0.43f, 0.16f, -0.66f);
        b.C(pathBuilderA, -1.47f, -2.71f, 10.31f, 13.23f);
        pathBuilderA.lineTo(7.93f, 9.57f);
        pathBuilderA.curveToRelative(-0.2f, -0.3f, -0.64f, -0.3f, -0.84f, 0.0f);
        pathBuilderA.curveToRelative(-0.81f, 1.16f, -1.17f, 2.57f, -1.05f, 3.98f);
        pathBuilderA.curveToRelative(0.02f, 0.26f, 0.24f, 0.45f, 0.5f, 0.45f);
        pathBuilderA.horizontalLineToRelative(3.35f);
        pathBuilderA.curveToRelative(0.39f, 0.0f, 0.63f, -0.44f, 0.42f, -0.77f);
        a.z(pathBuilderA, 13.97f, 12.74f, 2.02f, 3.74f);
        pathBuilderA.curveToRelative(0.18f, 0.33f, 0.64f, 0.35f, 0.86f, 0.05f);
        pathBuilderA.curveToRelative(0.86f, -1.18f, 1.24f, -2.62f, 1.12f, -4.08f);
        pathBuilderA.curveToRelative(-0.02f, -0.26f, -0.25f, -0.45f, -0.5f, -0.45f);
        pathBuilderA.horizontalLineToRelative(-3.05f);
        pathBuilderA.curveToRelative(-0.39f, 0.0f, -0.63f, 0.4f, -0.45f, 0.74f);
        pathBuilderA.close();
        pathBuilderA.moveTo(10.17f, 11.17f);
        pathBuilderA.curveToRelative(0.2f, 0.31f, 0.66f, 0.3f, 0.85f, -0.02f);
        pathBuilderA.lineToRelative(1.94f, -3.35f);
        pathBuilderA.curveToRelative(0.19f, -0.32f, -0.03f, -0.72f, -0.4f, -0.76f);
        pathBuilderA.curveToRelative(-1.36f, -0.12f, -2.73f, 0.21f, -3.88f, 0.97f);
        pathBuilderA.curveToRelative(-0.22f, 0.15f, -0.27f, 0.46f, -0.13f, 0.68f);
        ImageVector imageVectorBuild = ImageVector.Builder.m4024addPathoIyEayM$default(builder, c.c(pathBuilderA, 1.62f, 2.48f), defaultFillType, "", solidColor, 1.0f, null, 1.0f, 1.0f, iM3803getButtKaPHkGw, iM3813getBevelLxFBmk8, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null).build();
        _shutterSpeed = imageVectorBuild;
        return imageVectorBuild;
    }
}
