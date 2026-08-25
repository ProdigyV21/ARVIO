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
import v.b;
import v.c;
import v.d;
import v.f;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\"\u0010\u0010\u0000\u001a\u0004\u0018\u00010\u0001X\u0082\u000e¢\u0006\u0002\n\u0000\"\u0015\u0010\u0002\u001a\u00020\u0001*\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"_shutterSpeed", "Landroidx/compose/ui/graphics/vector/ImageVector;", "ShutterSpeed", "Landroidx/compose/material/icons/Icons$Filled;", "getShutterSpeed", "(Landroidx/compose/material/icons/Icons$Filled;)Landroidx/compose/ui/graphics/vector/ImageVector;", "material-icons-extended_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class ShutterSpeedKt {
    private static ImageVector _shutterSpeed;

    public static final ImageVector getShutterSpeed(Icons.Filled filled) {
        ImageVector imageVector = _shutterSpeed;
        if (imageVector != null) {
            return imageVector;
        }
        ImageVector.Builder builder = new ImageVector.Builder("Filled.ShutterSpeed", Dp.m5678constructorimpl(24.0f), Dp.m5678constructorimpl(24.0f), 24.0f, 24.0f, 0L, 0, false, 96, null);
        int defaultFillType = VectorKt.getDefaultFillType();
        SolidColor solidColor = new SolidColor(Color.INSTANCE.m3498getBlack0d7_KjU(), null);
        int iM3803getButtKaPHkGw = StrokeCap.INSTANCE.m3803getButtKaPHkGw();
        int iM3813getBevelLxFBmk8 = StrokeJoin.INSTANCE.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderS = c.s(15.0f, 1.0f, 9.0f, 1.0f, 2.0f);
        d.q(pathBuilderS, 6.0f, 15.0f, 1.0f);
        pathBuilderS.moveTo(19.03f, 7.39f);
        pathBuilderS.lineToRelative(1.42f, -1.42f);
        pathBuilderS.curveToRelative(-0.43f, -0.51f, -0.9f, -0.99f, -1.41f, -1.41f);
        pathBuilderS.lineToRelative(-1.42f, 1.42f);
        pathBuilderS.curveTo(16.07f, 4.74f, 14.12f, 4.0f, 12.0f, 4.0f);
        pathBuilderS.curveToRelative(-4.97f, 0.0f, -9.0f, 4.03f, -9.0f, 9.0f);
        pathBuilderS.reflectiveCurveToRelative(4.02f, 9.0f, 9.0f, 9.0f);
        pathBuilderS.reflectiveCurveToRelative(9.0f, -4.03f, 9.0f, -9.0f);
        pathBuilderS.curveToRelative(0.0f, -2.12f, -0.74f, -4.07f, -1.97f, -5.61f);
        pathBuilderS.close();
        pathBuilderS.moveTo(12.0f, 20.0f);
        pathBuilderS.curveToRelative(-3.87f, 0.0f, -7.0f, -3.13f, -7.0f, -7.0f);
        pathBuilderS.reflectiveCurveToRelative(3.13f, -7.0f, 7.0f, -7.0f);
        pathBuilderS.reflectiveCurveToRelative(7.0f, 3.13f, 7.0f, 7.0f);
        pathBuilderS.reflectiveCurveToRelative(-3.13f, 7.0f, -7.0f, 7.0f);
        a.p(pathBuilderS, 11.68f, 15.0f, 6.35f, 15.0f);
        pathBuilderS.curveToRelative(0.57f, 1.62f, 1.82f, 2.92f, 3.41f, 3.56f);
        b.D(pathBuilderS, -0.11f, -0.06f, 2.03f, -3.5f);
        pathBuilderS.moveTo(17.65f, 11.0f);
        pathBuilderS.curveToRelative(-0.57f, -1.6f, -1.78f, -2.89f, -3.34f, -3.54f);
        f.n(pathBuilderS, 12.26f, 11.0f, 5.39f);
        pathBuilderS.moveTo(10.61f, 18.83f);
        pathBuilderS.curveToRelative(0.45f, 0.11f, 0.91f, 0.17f, 1.39f, 0.17f);
        pathBuilderS.curveToRelative(1.34f, 0.0f, 2.57f, -0.45f, 3.57f, -1.19f);
        b.D(pathBuilderS, -2.11f, -3.9f, -2.85f, 4.92f);
        pathBuilderS.moveTo(7.55f, 8.99f);
        pathBuilderS.curveTo(6.59f, 10.05f, 6.0f, 11.46f, 6.0f, 13.0f);
        pathBuilderS.curveToRelative(0.0f, 0.34f, 0.04f, 0.67f, 0.09f, 1.0f);
        d.q(pathBuilderS, 4.72f, 7.55f, 8.99f);
        pathBuilderS.moveTo(16.34f, 17.13f);
        pathBuilderS.curveTo(17.37f, 16.06f, 18.0f, 14.6f, 18.0f, 13.0f);
        pathBuilderS.curveToRelative(0.0f, -0.34f, -0.04f, -0.67f, -0.09f, -1.0f);
        c.v(pathBuilderS, -4.34f, 2.77f, 5.13f);
        pathBuilderS.moveTo(13.33f, 7.15f);
        pathBuilderS.curveTo(12.9f, 7.06f, 12.46f, 7.0f, 12.0f, 7.0f);
        pathBuilderS.curveToRelative(-1.4f, 0.0f, -2.69f, 0.49f, -3.71f, 1.29f);
        ImageVector imageVectorBuild = ImageVector.Builder.m4024addPathoIyEayM$default(builder, b.t(pathBuilderS, 2.32f, 3.56f, 2.72f, -4.7f), defaultFillType, "", solidColor, 1.0f, null, 1.0f, 1.0f, iM3803getButtKaPHkGw, iM3813getBevelLxFBmk8, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null).build();
        _shutterSpeed = imageVectorBuild;
        return imageVectorBuild;
    }
}
