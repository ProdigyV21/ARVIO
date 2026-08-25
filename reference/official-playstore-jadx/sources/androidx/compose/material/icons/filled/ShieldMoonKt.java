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
import v.c;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\"\u0010\u0010\u0000\u001a\u0004\u0018\u00010\u0001X\u0082\u000e¢\u0006\u0002\n\u0000\"\u0015\u0010\u0002\u001a\u00020\u0001*\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"_shieldMoon", "Landroidx/compose/ui/graphics/vector/ImageVector;", "ShieldMoon", "Landroidx/compose/material/icons/Icons$Filled;", "getShieldMoon", "(Landroidx/compose/material/icons/Icons$Filled;)Landroidx/compose/ui/graphics/vector/ImageVector;", "material-icons-extended_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class ShieldMoonKt {
    private static ImageVector _shieldMoon;

    public static final ImageVector getShieldMoon(Icons.Filled filled) {
        ImageVector imageVector = _shieldMoon;
        if (imageVector != null) {
            return imageVector;
        }
        ImageVector.Builder builder = new ImageVector.Builder("Filled.ShieldMoon", Dp.m5678constructorimpl(24.0f), Dp.m5678constructorimpl(24.0f), 24.0f, 24.0f, 0L, 0, false, 96, null);
        int defaultFillType = VectorKt.getDefaultFillType();
        SolidColor solidColor = new SolidColor(Color.INSTANCE.m3498getBlack0d7_KjU(), null);
        int iM3803getButtKaPHkGw = StrokeCap.INSTANCE.m3803getButtKaPHkGw();
        int iM3813getBevelLxFBmk8 = StrokeJoin.INSTANCE.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderS = c.s(12.0f, 2.0f, 4.0f, 5.0f, 6.09f);
        pathBuilderS.curveToRelative(0.0f, 5.05f, 3.41f, 9.76f, 8.0f, 10.91f);
        pathBuilderS.curveToRelative(4.59f, -1.15f, 8.0f, -5.86f, 8.0f, -10.91f);
        c.x(pathBuilderS, 5.0f, 12.0f, 2.0f);
        pathBuilderS.moveTo(15.97f, 14.41f);
        pathBuilderS.curveToRelative(-1.84f, 2.17f, -5.21f, 2.1f, -6.96f, -0.07f);
        pathBuilderS.curveToRelative(-2.19f, -2.72f, -0.65f, -6.72f, 2.69f, -7.33f);
        pathBuilderS.curveToRelative(0.34f, -0.06f, 0.63f, 0.27f, 0.51f, 0.6f);
        pathBuilderS.curveToRelative(-0.46f, 1.23f, -0.39f, 2.64f, 0.32f, 3.86f);
        pathBuilderS.curveToRelative(0.71f, 1.22f, 1.89f, 1.99f, 3.18f, 2.2f);
        pathBuilderS.curveTo(16.05f, 13.72f, 16.2f, 14.14f, 15.97f, 14.41f);
        pathBuilderS.close();
        ImageVector imageVectorBuild = ImageVector.Builder.m4024addPathoIyEayM$default(builder, pathBuilderS.getNodes(), defaultFillType, "", solidColor, 1.0f, null, 1.0f, 1.0f, iM3803getButtKaPHkGw, iM3813getBevelLxFBmk8, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null).build();
        _shieldMoon = imageVectorBuild;
        return imageVectorBuild;
    }
}
