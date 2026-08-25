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
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\"\u0010\u0010\u0000\u001a\u0004\u0018\u00010\u0001X\u0082\u000e¢\u0006\u0002\n\u0000\"\u0015\u0010\u0002\u001a\u00020\u0001*\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"_safetyCheck", "Landroidx/compose/ui/graphics/vector/ImageVector;", "SafetyCheck", "Landroidx/compose/material/icons/Icons$Rounded;", "getSafetyCheck", "(Landroidx/compose/material/icons/Icons$Rounded;)Landroidx/compose/ui/graphics/vector/ImageVector;", "material-icons-extended_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class SafetyCheckKt {
    private static ImageVector _safetyCheck;

    public static final ImageVector getSafetyCheck(Icons.Rounded rounded) {
        ImageVector imageVector = _safetyCheck;
        if (imageVector != null) {
            return imageVector;
        }
        ImageVector.Builder builder = new ImageVector.Builder("Rounded.SafetyCheck", Dp.m5678constructorimpl(24.0f), Dp.m5678constructorimpl(24.0f), 24.0f, 24.0f, 0L, 0, false, 96, null);
        int defaultFillType = VectorKt.getDefaultFillType();
        SolidColor solidColor = new SolidColor(Color.INSTANCE.m3498getBlack0d7_KjU(), null);
        int iM3803getButtKaPHkGw = StrokeCap.INSTANCE.m3803getButtKaPHkGw();
        int iM3813getBevelLxFBmk8 = StrokeJoin.INSTANCE.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderR = a.r(11.3f, 2.26f, -6.0f, 2.25f);
        pathBuilderR.curveTo(4.52f, 4.81f, 4.0f, 5.55f, 4.0f, 6.39f);
        pathBuilderR.verticalLineToRelative(4.7f);
        pathBuilderR.curveToRelative(0.0f, 4.83f, 3.13f, 9.37f, 7.43f, 10.75f);
        pathBuilderR.curveToRelative(0.37f, 0.12f, 0.77f, 0.12f, 1.14f, 0.0f);
        pathBuilderR.curveToRelative(4.3f, -1.38f, 7.43f, -5.91f, 7.43f, -10.75f);
        pathBuilderR.verticalLineToRelative(-4.7f);
        pathBuilderR.curveToRelative(0.0f, -0.83f, -0.52f, -1.58f, -1.3f, -1.87f);
        pathBuilderR.lineToRelative(-6.0f, -2.25f);
        pathBuilderR.curveTo(12.25f, 2.09f, 11.75f, 2.09f, 11.3f, 2.26f);
        pathBuilderR.close();
        pathBuilderR.moveTo(12.0f, 17.0f);
        pathBuilderR.curveToRelative(-2.76f, 0.0f, -5.0f, -2.24f, -5.0f, -5.0f);
        pathBuilderR.reflectiveCurveToRelative(2.24f, -5.0f, 5.0f, -5.0f);
        pathBuilderR.reflectiveCurveToRelative(5.0f, 2.24f, 5.0f, 5.0f);
        pathBuilderR.reflectiveCurveTo(14.76f, 17.0f, 12.0f, 17.0f);
        pathBuilderR.close();
        pathBuilderR.moveTo(14.0f, 14.0f);
        pathBuilderR.curveToRelative(-0.2f, 0.2f, -0.51f, 0.2f, -0.71f, 0.0f);
        pathBuilderR.lineToRelative(-1.65f, -1.65f);
        pathBuilderR.curveToRelative(-0.09f, -0.09f, -0.15f, -0.22f, -0.15f, -0.35f);
        pathBuilderR.verticalLineTo(9.5f);
        pathBuilderR.curveTo(11.5f, 9.22f, 11.72f, 9.0f, 12.0f, 9.0f);
        pathBuilderR.curveToRelative(0.28f, 0.0f, 0.5f, 0.22f, 0.5f, 0.5f);
        pathBuilderR.verticalLineToRelative(2.29f);
        pathBuilderR.lineToRelative(1.5f, 1.5f);
        pathBuilderR.curveTo(14.2f, 13.49f, 14.2f, 13.8f, 14.0f, 14.0f);
        pathBuilderR.close();
        ImageVector imageVectorBuild = ImageVector.Builder.m4024addPathoIyEayM$default(builder, pathBuilderR.getNodes(), defaultFillType, "", solidColor, 1.0f, null, 1.0f, 1.0f, iM3803getButtKaPHkGw, iM3813getBevelLxFBmk8, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null).build();
        _safetyCheck = imageVectorBuild;
        return imageVectorBuild;
    }
}
