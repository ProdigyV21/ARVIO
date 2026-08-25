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
import v.a;
import v.d;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\"\u0010\u0010\u0000\u001a\u0004\u0018\u00010\u0001X\u0082\u000e¢\u0006\u0002\n\u0000\"\u0015\u0010\u0002\u001a\u00020\u0001*\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"_touchApp", "Landroidx/compose/ui/graphics/vector/ImageVector;", "TouchApp", "Landroidx/compose/material/icons/Icons$Outlined;", "getTouchApp", "(Landroidx/compose/material/icons/Icons$Outlined;)Landroidx/compose/ui/graphics/vector/ImageVector;", "material-icons-extended_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class TouchAppKt {
    private static ImageVector _touchApp;

    public static final ImageVector getTouchApp(Icons.Outlined outlined) {
        ImageVector imageVector = _touchApp;
        if (imageVector != null) {
            return imageVector;
        }
        ImageVector.Builder builder = new ImageVector.Builder("Outlined.TouchApp", Dp.m5678constructorimpl(24.0f), Dp.m5678constructorimpl(24.0f), 24.0f, 24.0f, 0L, 0, false, 96, null);
        int defaultFillType = VectorKt.getDefaultFillType();
        SolidColor solidColor = new SolidColor(Color.INSTANCE.m3498getBlack0d7_KjU(), null);
        int iM3803getButtKaPHkGw = StrokeCap.INSTANCE.m3803getButtKaPHkGw();
        int iM3813getBevelLxFBmk8 = StrokeJoin.INSTANCE.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderR = a.r(18.19f, 12.44f, -3.24f, -1.62f);
        pathBuilderR.curveToRelative(1.29f, -1.0f, 2.12f, -2.56f, 2.12f, -4.32f);
        pathBuilderR.curveToRelative(0.0f, -3.03f, -2.47f, -5.5f, -5.5f, -5.5f);
        pathBuilderR.reflectiveCurveToRelative(-5.5f, 2.47f, -5.5f, 5.5f);
        pathBuilderR.curveToRelative(0.0f, 2.13f, 1.22f, 3.98f, 3.0f, 4.89f);
        pathBuilderR.verticalLineToRelative(3.26f);
        pathBuilderR.curveToRelative(-2.15f, -0.46f, -2.02f, -0.44f, -2.26f, -0.44f);
        pathBuilderR.curveToRelative(-0.53f, 0.0f, -1.03f, 0.21f, -1.41f, 0.59f);
        pathBuilderR.lineTo(4.0f, 16.22f);
        pathBuilderR.lineToRelative(5.09f, 5.09f);
        pathBuilderR.curveTo(9.52f, 21.75f, 10.12f, 22.0f, 10.74f, 22.0f);
        pathBuilderR.horizontalLineToRelative(6.3f);
        pathBuilderR.curveToRelative(0.98f, 0.0f, 1.81f, -0.7f, 1.97f, -1.67f);
        pathBuilderR.lineToRelative(0.8f, -4.71f);
        pathBuilderR.curveTo(20.03f, 14.32f, 19.38f, 13.04f, 18.19f, 12.44f);
        a.p(pathBuilderR, 17.84f, 15.29f, 17.04f, 20.0f);
        pathBuilderR.horizontalLineToRelative(-6.3f);
        pathBuilderR.curveToRelative(-0.09f, 0.0f, -0.17f, -0.04f, -0.24f, -0.1f);
        pathBuilderR.lineToRelative(-3.68f, -3.68f);
        pathBuilderR.lineToRelative(4.25f, 0.89f);
        pathBuilderR.verticalLineTo(6.5f);
        pathBuilderR.curveToRelative(0.0f, -0.28f, 0.22f, -0.5f, 0.5f, -0.5f);
        pathBuilderR.curveToRelative(0.28f, 0.0f, 0.5f, 0.22f, 0.5f, 0.5f);
        d.D(pathBuilderR, 6.0f, 1.76f, 3.46f, 1.73f);
        pathBuilderR.curveTo(17.69f, 14.43f, 17.91f, 14.86f, 17.84f, 15.29f);
        pathBuilderR.close();
        pathBuilderR.moveTo(8.07f, 6.5f);
        pathBuilderR.curveToRelative(0.0f, -1.93f, 1.57f, -3.5f, 3.5f, -3.5f);
        pathBuilderR.reflectiveCurveToRelative(3.5f, 1.57f, 3.5f, 3.5f);
        pathBuilderR.curveToRelative(0.0f, 0.95f, -0.38f, 1.81f, -1.0f, 2.44f);
        pathBuilderR.verticalLineTo(6.5f);
        pathBuilderR.curveToRelative(0.0f, -1.38f, -1.12f, -2.5f, -2.5f, -2.5f);
        pathBuilderR.curveToRelative(-1.38f, 0.0f, -2.5f, 1.12f, -2.5f, 2.5f);
        pathBuilderR.verticalLineToRelative(2.44f);
        pathBuilderR.curveTo(8.45f, 8.31f, 8.07f, 7.45f, 8.07f, 6.5f);
        pathBuilderR.close();
        ImageVector imageVectorBuild = ImageVector.Builder.m4024addPathoIyEayM$default(builder, pathBuilderR.getNodes(), defaultFillType, "", solidColor, 1.0f, null, 1.0f, 1.0f, iM3803getButtKaPHkGw, iM3813getBevelLxFBmk8, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null).build();
        _touchApp = imageVectorBuild;
        return imageVectorBuild;
    }
}
