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

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\"\u0010\u0010\u0000\u001a\u0004\u0018\u00010\u0001X\u0082\u000e¢\u0006\u0002\n\u0000\"\u0015\u0010\u0002\u001a\u00020\u0001*\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"_nestCamWiredStand", "Landroidx/compose/ui/graphics/vector/ImageVector;", "NestCamWiredStand", "Landroidx/compose/material/icons/Icons$Filled;", "getNestCamWiredStand", "(Landroidx/compose/material/icons/Icons$Filled;)Landroidx/compose/ui/graphics/vector/ImageVector;", "material-icons-extended_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class NestCamWiredStandKt {
    private static ImageVector _nestCamWiredStand;

    public static final ImageVector getNestCamWiredStand(Icons.Filled filled) {
        ImageVector imageVector = _nestCamWiredStand;
        if (imageVector != null) {
            return imageVector;
        }
        ImageVector.Builder builder = new ImageVector.Builder("Filled.NestCamWiredStand", Dp.m5678constructorimpl(24.0f), Dp.m5678constructorimpl(24.0f), 24.0f, 24.0f, 0L, 0, false, 96, null);
        int defaultFillType = VectorKt.getDefaultFillType();
        SolidColor solidColor = new SolidColor(Color.INSTANCE.m3498getBlack0d7_KjU(), null);
        int iM3803getButtKaPHkGw = StrokeCap.INSTANCE.m3803getButtKaPHkGw();
        int iM3813getBevelLxFBmk8 = StrokeJoin.INSTANCE.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderR = a.r(15.83f, 1.01f, -4.11f, 0.42f);
        pathBuilderR.curveTo(8.47f, 1.75f, 6.0f, 4.48f, 6.0f, 7.75f);
        pathBuilderR.reflectiveCurveToRelative(2.47f, 6.0f, 5.72f, 6.33f);
        pathBuilderR.lineToRelative(1.9f, 0.19f);
        pathBuilderR.lineToRelative(-0.56f, 0.85f);
        pathBuilderR.curveTo(12.71f, 15.04f, 12.36f, 15.0f, 12.0f, 15.0f);
        pathBuilderR.curveToRelative(-2.76f, 0.0f, -5.0f, 2.24f, -5.0f, 5.0f);
        pathBuilderR.verticalLineToRelative(2.0f);
        pathBuilderR.curveToRelative(0.0f, 0.55f, 0.45f, 1.0f, 1.0f, 1.0f);
        pathBuilderR.horizontalLineToRelative(8.0f);
        pathBuilderR.curveToRelative(0.55f, 0.0f, 1.0f, -0.45f, 1.0f, -1.0f);
        pathBuilderR.verticalLineToRelative(-2.0f);
        pathBuilderR.curveToRelative(0.0f, -1.67f, -0.83f, -3.15f, -2.09f, -4.06f);
        pathBuilderR.lineToRelative(0.97f, -1.45f);
        pathBuilderR.curveTo(17.02f, 14.56f, 18.0f, 13.66f, 18.0f, 12.5f);
        pathBuilderR.verticalLineTo(3.0f);
        pathBuilderR.curveTo(18.0f, 1.83f, 17.0f, 0.91f, 15.83f, 1.01f);
        pathBuilderR.close();
        ImageVector imageVectorBuild = ImageVector.Builder.m4024addPathoIyEayM$default(builder, pathBuilderR.getNodes(), defaultFillType, "", solidColor, 1.0f, null, 1.0f, 1.0f, iM3803getButtKaPHkGw, iM3813getBevelLxFBmk8, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null).build();
        _nestCamWiredStand = imageVectorBuild;
        return imageVectorBuild;
    }
}
