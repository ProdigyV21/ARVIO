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
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\"\u0010\u0010\u0000\u001a\u0004\u0018\u00010\u0001X\u0082\u000e¢\u0006\u0002\n\u0000\"\u0015\u0010\u0002\u001a\u00020\u0001*\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"_waterDamage", "Landroidx/compose/ui/graphics/vector/ImageVector;", "WaterDamage", "Landroidx/compose/material/icons/Icons$Rounded;", "getWaterDamage", "(Landroidx/compose/material/icons/Icons$Rounded;)Landroidx/compose/ui/graphics/vector/ImageVector;", "material-icons-extended_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class WaterDamageKt {
    private static ImageVector _waterDamage;

    public static final ImageVector getWaterDamage(Icons.Rounded rounded) {
        ImageVector imageVector = _waterDamage;
        if (imageVector != null) {
            return imageVector;
        }
        ImageVector.Builder builder = new ImageVector.Builder("Rounded.WaterDamage", Dp.m5678constructorimpl(24.0f), Dp.m5678constructorimpl(24.0f), 24.0f, 24.0f, 0L, 0, false, 96, null);
        int defaultFillType = VectorKt.getDefaultFillType();
        SolidColor solidColor = new SolidColor(Color.INSTANCE.m3498getBlack0d7_KjU(), null);
        int iM3803getButtKaPHkGw = StrokeCap.INSTANCE.m3803getButtKaPHkGw();
        int iM3813getBevelLxFBmk8 = StrokeJoin.INSTANCE.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderR = a.r(11.33f, 3.6f, -8.36f, 7.53f);
        pathBuilderR.curveTo(2.63f, 11.43f, 2.84f, 12.0f, 3.3f, 12.0f);
        pathBuilderR.horizontalLineTo(5.0f);
        pathBuilderR.verticalLineToRelative(7.0f);
        pathBuilderR.curveToRelative(0.0f, 0.55f, 0.45f, 1.0f, 1.0f, 1.0f);
        pathBuilderR.horizontalLineToRelative(12.0f);
        pathBuilderR.curveToRelative(0.55f, 0.0f, 1.0f, -0.45f, 1.0f, -1.0f);
        pathBuilderR.verticalLineToRelative(-7.0f);
        pathBuilderR.horizontalLineToRelative(1.7f);
        pathBuilderR.curveToRelative(0.46f, 0.0f, 0.68f, -0.57f, 0.33f, -0.87f);
        pathBuilderR.lineTo(12.67f, 3.6f);
        pathBuilderR.curveTo(12.29f, 3.26f, 11.71f, 3.26f, 11.33f, 3.6f);
        pathBuilderR.close();
        pathBuilderR.moveTo(12.0f, 16.0f);
        pathBuilderR.curveToRelative(-1.1f, 0.0f, -2.0f, -0.9f, -2.0f, -2.0f);
        pathBuilderR.curveToRelative(0.0f, -0.78f, 0.99f, -2.44f, 1.58f, -3.36f);
        pathBuilderR.curveToRelative(0.2f, -0.31f, 0.64f, -0.31f, 0.84f, 0.0f);
        pathBuilderR.curveTo(13.01f, 11.56f, 14.0f, 13.22f, 14.0f, 14.0f);
        pathBuilderR.curveTo(14.0f, 15.1f, 13.1f, 16.0f, 12.0f, 16.0f);
        pathBuilderR.close();
        ImageVector imageVectorBuild = ImageVector.Builder.m4024addPathoIyEayM$default(builder, pathBuilderR.getNodes(), defaultFillType, "", solidColor, 1.0f, null, 1.0f, 1.0f, iM3803getButtKaPHkGw, iM3813getBevelLxFBmk8, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null).build();
        _waterDamage = imageVectorBuild;
        return imageVectorBuild;
    }
}
