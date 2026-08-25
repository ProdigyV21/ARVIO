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
import v.e;
import v.f;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\"\u0010\u0010\u0000\u001a\u0004\u0018\u00010\u0001X\u0082\u000e¢\u0006\u0002\n\u0000\"\u0015\u0010\u0002\u001a\u00020\u0001*\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"_appSettingsAlt", "Landroidx/compose/ui/graphics/vector/ImageVector;", "AppSettingsAlt", "Landroidx/compose/material/icons/Icons$Rounded;", "getAppSettingsAlt", "(Landroidx/compose/material/icons/Icons$Rounded;)Landroidx/compose/ui/graphics/vector/ImageVector;", "material-icons-extended_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class AppSettingsAltKt {
    private static ImageVector _appSettingsAlt;

    public static final ImageVector getAppSettingsAlt(Icons.Rounded rounded) {
        ImageVector imageVector = _appSettingsAlt;
        if (imageVector != null) {
            return imageVector;
        }
        ImageVector.Builder builder = new ImageVector.Builder("Rounded.AppSettingsAlt", Dp.m5678constructorimpl(24.0f), Dp.m5678constructorimpl(24.0f), 24.0f, 24.0f, 0L, 0, false, 96, null);
        int defaultFillType = VectorKt.getDefaultFillType();
        SolidColor solidColor = new SolidColor(Color.INSTANCE.m3498getBlack0d7_KjU(), null);
        int iM3803getButtKaPHkGw = StrokeCap.INSTANCE.m3803getButtKaPHkGw();
        int iM3813getBevelLxFBmk8 = StrokeJoin.INSTANCE.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderC = a.c(17.0f, 18.0f, 7.0f, 6.0f, 10.0f);
        f.t(pathBuilderC, 1.0f, 2.0f, 3.0f);
        pathBuilderC.curveToRelative(0.0f, -1.1f, -0.9f, -2.0f, -2.0f, -2.0f);
        pathBuilderC.lineTo(7.0f, 1.01f);
        pathBuilderC.curveTo(5.9f, 1.01f, 5.0f, 1.9f, 5.0f, 3.0f);
        pathBuilderC.verticalLineToRelative(18.0f);
        pathBuilderC.curveToRelative(0.0f, 1.1f, 0.9f, 2.0f, 2.0f, 2.0f);
        pathBuilderC.horizontalLineToRelative(10.0f);
        pathBuilderC.curveToRelative(1.1f, 0.0f, 2.0f, -0.9f, 2.0f, -2.0f);
        a.t(pathBuilderC, -4.0f, -2.0f, 18.0f);
        pathBuilderC.moveTo(21.0f, 12.0f);
        pathBuilderC.curveToRelative(0.0f, -0.13f, -0.02f, -0.26f, -0.04f, -0.39f);
        pathBuilderC.lineToRelative(0.64f, -0.48f);
        pathBuilderC.curveToRelative(0.2f, -0.15f, 0.26f, -0.44f, 0.13f, -0.66f);
        pathBuilderC.lineToRelative(-0.57f, -0.96f);
        pathBuilderC.curveToRelative(-0.13f, -0.21f, -0.39f, -0.3f, -0.62f, -0.2f);
        pathBuilderC.lineToRelative(-0.72f, 0.3f);
        pathBuilderC.curveToRelative(-0.2f, -0.15f, -0.42f, -0.29f, -0.65f, -0.39f);
        pathBuilderC.lineToRelative(-0.1f, -0.77f);
        pathBuilderC.curveToRelative(-0.03f, -0.25f, -0.24f, -0.43f, -0.49f, -0.44f);
        pathBuilderC.lineToRelative(-1.12f, -0.02f);
        pathBuilderC.curveToRelative(-0.26f, 0.0f, -0.47f, 0.18f, -0.5f, 0.44f);
        pathBuilderC.lineToRelative(-0.1f, 0.79f);
        pathBuilderC.curveToRelative(-0.24f, 0.1f, -0.45f, 0.23f, -0.65f, 0.39f);
        pathBuilderC.lineToRelative(-0.72f, -0.3f);
        pathBuilderC.curveToRelative(-0.23f, -0.1f, -0.5f, -0.01f, -0.62f, 0.2f);
        pathBuilderC.lineToRelative(-0.57f, 0.96f);
        pathBuilderC.curveToRelative(-0.13f, 0.22f, -0.08f, 0.5f, 0.13f, 0.66f);
        pathBuilderC.lineToRelative(0.64f, 0.48f);
        pathBuilderC.curveTo(15.02f, 11.74f, 15.0f, 11.87f, 15.0f, 12.0f);
        pathBuilderC.curveToRelative(0.0f, 0.13f, 0.02f, 0.25f, 0.04f, 0.37f);
        pathBuilderC.lineToRelative(-0.64f, 0.49f);
        pathBuilderC.curveToRelative(-0.2f, 0.15f, -0.26f, 0.43f, -0.13f, 0.65f);
        pathBuilderC.lineToRelative(0.56f, 0.97f);
        pathBuilderC.curveToRelative(0.13f, 0.22f, 0.39f, 0.31f, 0.63f, 0.21f);
        pathBuilderC.lineToRelative(0.73f, -0.31f);
        pathBuilderC.curveToRelative(0.2f, 0.16f, 0.42f, 0.3f, 0.67f, 0.4f);
        pathBuilderC.lineToRelative(0.1f, 0.77f);
        pathBuilderC.curveToRelative(0.03f, 0.25f, 0.24f, 0.44f, 0.5f, 0.44f);
        pathBuilderC.horizontalLineToRelative(1.12f);
        pathBuilderC.curveToRelative(0.25f, 0.0f, 0.46f, -0.19f, 0.5f, -0.44f);
        pathBuilderC.lineToRelative(0.1f, -0.77f);
        pathBuilderC.curveToRelative(0.24f, -0.1f, 0.46f, -0.24f, 0.67f, -0.4f);
        pathBuilderC.lineToRelative(0.73f, 0.31f);
        pathBuilderC.curveToRelative(0.23f, 0.1f, 0.5f, 0.01f, 0.63f, -0.21f);
        pathBuilderC.lineToRelative(0.56f, -0.97f);
        pathBuilderC.curveToRelative(0.13f, -0.22f, 0.07f, -0.5f, -0.13f, -0.65f);
        pathBuilderC.lineToRelative(-0.64f, -0.49f);
        pathBuilderC.curveTo(20.98f, 12.25f, 21.0f, 12.13f, 21.0f, 12.0f);
        pathBuilderC.close();
        pathBuilderC.moveTo(18.0f, 13.5f);
        pathBuilderC.curveToRelative(-0.83f, 0.0f, -1.5f, -0.67f, -1.5f, -1.5f);
        pathBuilderC.reflectiveCurveToRelative(0.67f, -1.5f, 1.5f, -1.5f);
        pathBuilderC.reflectiveCurveToRelative(1.5f, 0.67f, 1.5f, 1.5f);
        ImageVector imageVectorBuild = ImageVector.Builder.m4024addPathoIyEayM$default(builder, e.u(pathBuilderC, 18.83f, 13.5f, 18.0f, 13.5f), defaultFillType, "", solidColor, 1.0f, null, 1.0f, 1.0f, iM3803getButtKaPHkGw, iM3813getBevelLxFBmk8, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null).build();
        _appSettingsAlt = imageVectorBuild;
        return imageVectorBuild;
    }
}
