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
import v.f;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\"\u0010\u0010\u0000\u001a\u0004\u0018\u00010\u0001X\u0082\u000e¢\u0006\u0002\n\u0000\"\u0015\u0010\u0002\u001a\u00020\u0001*\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"_healthAndSafety", "Landroidx/compose/ui/graphics/vector/ImageVector;", "HealthAndSafety", "Landroidx/compose/material/icons/Icons$Rounded;", "getHealthAndSafety", "(Landroidx/compose/material/icons/Icons$Rounded;)Landroidx/compose/ui/graphics/vector/ImageVector;", "material-icons-extended_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class HealthAndSafetyKt {
    private static ImageVector _healthAndSafety;

    public static final ImageVector getHealthAndSafety(Icons.Rounded rounded) {
        ImageVector imageVector = _healthAndSafety;
        if (imageVector != null) {
            return imageVector;
        }
        ImageVector.Builder builder = new ImageVector.Builder("Rounded.HealthAndSafety", Dp.m5678constructorimpl(24.0f), Dp.m5678constructorimpl(24.0f), 24.0f, 24.0f, 0L, 0, false, 96, null);
        int defaultFillType = VectorKt.getDefaultFillType();
        SolidColor solidColor = new SolidColor(Color.INSTANCE.m3498getBlack0d7_KjU(), null);
        int iM3803getButtKaPHkGw = StrokeCap.INSTANCE.m3803getButtKaPHkGw();
        int iM3813getBevelLxFBmk8 = StrokeJoin.INSTANCE.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderA = a.a(10.5f, 13.0f, -1.0f);
        pathBuilderA.curveTo(8.67f, 13.0f, 8.0f, 12.33f, 8.0f, 11.5f);
        pathBuilderA.verticalLineToRelative(0.0f);
        pathBuilderA.curveTo(8.0f, 10.67f, 8.67f, 10.0f, 9.5f, 10.0f);
        pathBuilderA.horizontalLineToRelative(1.0f);
        pathBuilderA.verticalLineTo(9.0f);
        pathBuilderA.curveToRelative(0.0f, -0.83f, 0.67f, -1.5f, 1.5f, -1.5f);
        pathBuilderA.horizontalLineToRelative(0.0f);
        pathBuilderA.curveToRelative(0.83f, 0.0f, 1.5f, 0.67f, 1.5f, 1.5f);
        pathBuilderA.verticalLineToRelative(1.0f);
        pathBuilderA.horizontalLineToRelative(1.0f);
        pathBuilderA.curveToRelative(0.83f, 0.0f, 1.5f, 0.67f, 1.5f, 1.5f);
        pathBuilderA.verticalLineToRelative(0.0f);
        pathBuilderA.curveToRelative(0.0f, 0.83f, -0.67f, 1.5f, -1.5f, 1.5f);
        pathBuilderA.horizontalLineToRelative(-1.0f);
        pathBuilderA.verticalLineToRelative(1.0f);
        pathBuilderA.curveToRelative(0.0f, 0.83f, -0.67f, 1.5f, -1.5f, 1.5f);
        pathBuilderA.horizontalLineToRelative(0.0f);
        pathBuilderA.curveToRelative(-0.83f, 0.0f, -1.5f, -0.67f, -1.5f, -1.5f);
        f.r(pathBuilderA, 13.0f, 11.3f, 2.26f);
        pathBuilderA.lineToRelative(-6.0f, 2.25f);
        pathBuilderA.curveTo(4.52f, 4.81f, 4.0f, 5.55f, 4.0f, 6.39f);
        pathBuilderA.verticalLineToRelative(4.7f);
        pathBuilderA.curveToRelative(0.0f, 5.05f, 3.41f, 9.76f, 8.0f, 10.91f);
        pathBuilderA.curveToRelative(4.59f, -1.15f, 8.0f, -5.86f, 8.0f, -10.91f);
        pathBuilderA.verticalLineToRelative(-4.7f);
        pathBuilderA.curveToRelative(0.0f, -0.83f, -0.52f, -1.58f, -1.3f, -1.87f);
        pathBuilderA.lineToRelative(-6.0f, -2.25f);
        pathBuilderA.curveTo(12.25f, 2.09f, 11.75f, 2.09f, 11.3f, 2.26f);
        pathBuilderA.close();
        ImageVector imageVectorBuild = ImageVector.Builder.m4024addPathoIyEayM$default(builder, pathBuilderA.getNodes(), defaultFillType, "", solidColor, 1.0f, null, 1.0f, 1.0f, iM3803getButtKaPHkGw, iM3813getBevelLxFBmk8, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null).build();
        _healthAndSafety = imageVectorBuild;
        return imageVectorBuild;
    }
}
