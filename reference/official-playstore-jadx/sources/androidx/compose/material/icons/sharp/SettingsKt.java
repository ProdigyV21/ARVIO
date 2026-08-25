package androidx.compose.material.icons.sharp;

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
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\"\u0010\u0010\u0000\u001a\u0004\u0018\u00010\u0001X\u0082\u000e¢\u0006\u0002\n\u0000\"\u0015\u0010\u0002\u001a\u00020\u0001*\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"_settings", "Landroidx/compose/ui/graphics/vector/ImageVector;", "Settings", "Landroidx/compose/material/icons/Icons$Sharp;", "getSettings", "(Landroidx/compose/material/icons/Icons$Sharp;)Landroidx/compose/ui/graphics/vector/ImageVector;", "material-icons-core_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class SettingsKt {
    private static ImageVector _settings;

    public static final ImageVector getSettings(Icons.Sharp sharp) {
        ImageVector imageVector = _settings;
        if (imageVector != null) {
            return imageVector;
        }
        ImageVector.Builder builder = new ImageVector.Builder("Sharp.Settings", Dp.m5678constructorimpl(24.0f), Dp.m5678constructorimpl(24.0f), 24.0f, 24.0f, 0L, 0, false, 96, null);
        int defaultFillType = VectorKt.getDefaultFillType();
        SolidColor solidColor = new SolidColor(Color.INSTANCE.m3498getBlack0d7_KjU(), null);
        int iM3803getButtKaPHkGw = StrokeCap.INSTANCE.m3803getButtKaPHkGw();
        int iM3813getBevelLxFBmk8 = StrokeJoin.INSTANCE.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderR = a.r(19.44f, 12.99f, -0.01f, 0.02f);
        pathBuilderR.curveToRelative(0.04f, -0.33f, 0.08f, -0.67f, 0.08f, -1.01f);
        pathBuilderR.curveToRelative(0.0f, -0.34f, -0.03f, -0.66f, -0.07f, -0.99f);
        pathBuilderR.lineToRelative(0.01f, 0.02f);
        pathBuilderR.lineToRelative(2.44f, -1.92f);
        pathBuilderR.lineToRelative(-2.43f, -4.22f);
        pathBuilderR.lineToRelative(-2.87f, 1.16f);
        pathBuilderR.lineToRelative(0.01f, 0.01f);
        pathBuilderR.curveToRelative(-0.52f, -0.4f, -1.09f, -0.74f, -1.71f, -1.0f);
        w.a.f(pathBuilderR, 0.01f, 14.44f, 2.0f, 9.57f);
        pathBuilderR.lineToRelative(-0.44f, 3.07f);
        pathBuilderR.horizontalLineToRelative(0.01f);
        pathBuilderR.curveToRelative(-0.62f, 0.26f, -1.19f, 0.6f, -1.71f, 1.0f);
        pathBuilderR.lineToRelative(0.01f, -0.01f);
        pathBuilderR.lineToRelative(-2.88f, -1.17f);
        pathBuilderR.lineToRelative(-2.44f, 4.22f);
        pathBuilderR.lineToRelative(2.44f, 1.92f);
        pathBuilderR.lineToRelative(0.01f, -0.02f);
        pathBuilderR.curveToRelative(-0.04f, 0.33f, -0.07f, 0.65f, -0.07f, 0.99f);
        pathBuilderR.curveToRelative(0.0f, 0.34f, 0.03f, 0.68f, 0.08f, 1.01f);
        pathBuilderR.lineToRelative(-0.01f, -0.02f);
        pathBuilderR.lineToRelative(-2.1f, 1.65f);
        pathBuilderR.lineToRelative(-0.33f, 0.26f);
        pathBuilderR.lineToRelative(2.43f, 4.2f);
        pathBuilderR.lineToRelative(2.88f, -1.15f);
        pathBuilderR.lineToRelative(-0.02f, -0.04f);
        pathBuilderR.curveToRelative(0.53f, 0.41f, 1.1f, 0.75f, 1.73f, 1.01f);
        c.A(pathBuilderR, -0.03f, 9.58f, 22.0f, 4.85f);
        pathBuilderR.reflectiveCurveToRelative(0.03f, -0.18f, 0.06f, -0.42f);
        pathBuilderR.lineToRelative(0.38f, -2.65f);
        pathBuilderR.horizontalLineToRelative(-0.01f);
        pathBuilderR.curveToRelative(0.62f, -0.26f, 1.2f, -0.6f, 1.73f, -1.01f);
        pathBuilderR.lineToRelative(-0.02f, 0.04f);
        pathBuilderR.lineToRelative(2.88f, 1.15f);
        pathBuilderR.lineToRelative(2.43f, -4.2f);
        pathBuilderR.reflectiveCurveToRelative(-0.14f, -0.12f, -0.33f, -0.26f);
        b.C(pathBuilderR, -2.11f, -1.66f, 12.0f, 15.5f);
        pathBuilderR.curveToRelative(-1.93f, 0.0f, -3.5f, -1.57f, -3.5f, -3.5f);
        pathBuilderR.reflectiveCurveToRelative(1.57f, -3.5f, 3.5f, -3.5f);
        pathBuilderR.reflectiveCurveToRelative(3.5f, 1.57f, 3.5f, 3.5f);
        ImageVector imageVectorBuild = ImageVector.Builder.m4024addPathoIyEayM$default(builder, c.h(pathBuilderR, -1.57f, 3.5f, -3.5f, 3.5f), defaultFillType, "", solidColor, 1.0f, null, 1.0f, 1.0f, iM3803getButtKaPHkGw, iM3813getBevelLxFBmk8, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null).build();
        _settings = imageVectorBuild;
        return imageVectorBuild;
    }
}
