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
import v.f;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\"\u0010\u0010\u0000\u001a\u0004\u0018\u00010\u0001X\u0082\u000e¢\u0006\u0002\n\u0000\"\u0015\u0010\u0002\u001a\u00020\u0001*\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"_safetyDivider", "Landroidx/compose/ui/graphics/vector/ImageVector;", "SafetyDivider", "Landroidx/compose/material/icons/Icons$Filled;", "getSafetyDivider", "(Landroidx/compose/material/icons/Icons$Filled;)Landroidx/compose/ui/graphics/vector/ImageVector;", "material-icons-extended_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class SafetyDividerKt {
    private static ImageVector _safetyDivider;

    public static final ImageVector getSafetyDivider(Icons.Filled filled) {
        ImageVector imageVector = _safetyDivider;
        if (imageVector != null) {
            return imageVector;
        }
        ImageVector.Builder builder = new ImageVector.Builder("Filled.SafetyDivider", Dp.m5678constructorimpl(24.0f), Dp.m5678constructorimpl(24.0f), 24.0f, 24.0f, 0L, 0, false, 96, null);
        int defaultFillType = VectorKt.getDefaultFillType();
        SolidColor solidColor = new SolidColor(Color.INSTANCE.m3498getBlack0d7_KjU(), null);
        int iM3803getButtKaPHkGw = StrokeCap.INSTANCE.m3803getButtKaPHkGw();
        int iM3813getBevelLxFBmk8 = StrokeJoin.INSTANCE.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderK = a.k(11.0f, 5.0f, 2.0f, 14.0f, -2.0f);
        f.r(pathBuilderK, 5.0f, 5.0f, 12.0f);
        pathBuilderK.curveToRelative(1.1f, 0.0f, 2.0f, -0.9f, 2.0f, -2.0f);
        pathBuilderK.curveToRelative(0.0f, -1.1f, -0.9f, -2.0f, -2.0f, -2.0f);
        pathBuilderK.reflectiveCurveToRelative(-2.0f, 0.9f, -2.0f, 2.0f);
        pathBuilderK.curveTo(3.0f, 11.1f, 3.9f, 12.0f, 5.0f, 12.0f);
        pathBuilderK.close();
        pathBuilderK.moveTo(7.78f, 13.58f);
        pathBuilderK.curveTo(6.93f, 13.21f, 5.99f, 13.0f, 5.0f, 13.0f);
        pathBuilderK.reflectiveCurveToRelative(-1.93f, 0.21f, -2.78f, 0.58f);
        pathBuilderK.curveTo(1.48f, 13.9f, 1.0f, 14.62f, 1.0f, 15.43f);
        pathBuilderK.lineTo(1.0f, 16.0f);
        pathBuilderK.horizontalLineToRelative(8.0f);
        pathBuilderK.lineToRelative(0.0f, -0.57f);
        pathBuilderK.curveTo(9.0f, 14.62f, 8.52f, 13.9f, 7.78f, 13.58f);
        pathBuilderK.close();
        pathBuilderK.moveTo(19.0f, 12.0f);
        pathBuilderK.curveToRelative(1.1f, 0.0f, 2.0f, -0.9f, 2.0f, -2.0f);
        pathBuilderK.curveToRelative(0.0f, -1.1f, -0.9f, -2.0f, -2.0f, -2.0f);
        pathBuilderK.reflectiveCurveToRelative(-2.0f, 0.9f, -2.0f, 2.0f);
        pathBuilderK.curveTo(17.0f, 11.1f, 17.9f, 12.0f, 19.0f, 12.0f);
        pathBuilderK.close();
        pathBuilderK.moveTo(21.78f, 13.58f);
        pathBuilderK.curveTo(20.93f, 13.21f, 19.99f, 13.0f, 19.0f, 13.0f);
        pathBuilderK.reflectiveCurveToRelative(-1.93f, 0.21f, -2.78f, 0.58f);
        pathBuilderK.curveTo(15.48f, 13.9f, 15.0f, 14.62f, 15.0f, 15.43f);
        pathBuilderK.lineTo(15.0f, 16.0f);
        pathBuilderK.horizontalLineToRelative(8.0f);
        pathBuilderK.lineToRelative(0.0f, -0.57f);
        pathBuilderK.curveTo(23.0f, 14.62f, 22.52f, 13.9f, 21.78f, 13.58f);
        pathBuilderK.close();
        ImageVector imageVectorBuild = ImageVector.Builder.m4024addPathoIyEayM$default(builder, pathBuilderK.getNodes(), defaultFillType, "", solidColor, 1.0f, null, 1.0f, 1.0f, iM3803getButtKaPHkGw, iM3813getBevelLxFBmk8, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null).build();
        _safetyDivider = imageVectorBuild;
        return imageVectorBuild;
    }
}
