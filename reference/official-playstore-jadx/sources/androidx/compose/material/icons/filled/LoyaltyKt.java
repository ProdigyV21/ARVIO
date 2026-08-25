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
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\"\u0010\u0010\u0000\u001a\u0004\u0018\u00010\u0001X\u0082\u000e¢\u0006\u0002\n\u0000\"\u0015\u0010\u0002\u001a\u00020\u0001*\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"_loyalty", "Landroidx/compose/ui/graphics/vector/ImageVector;", "Loyalty", "Landroidx/compose/material/icons/Icons$Filled;", "getLoyalty", "(Landroidx/compose/material/icons/Icons$Filled;)Landroidx/compose/ui/graphics/vector/ImageVector;", "material-icons-extended_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class LoyaltyKt {
    private static ImageVector _loyalty;

    public static final ImageVector getLoyalty(Icons.Filled filled) {
        ImageVector imageVector = _loyalty;
        if (imageVector != null) {
            return imageVector;
        }
        ImageVector.Builder builder = new ImageVector.Builder("Filled.Loyalty", Dp.m5678constructorimpl(24.0f), Dp.m5678constructorimpl(24.0f), 24.0f, 24.0f, 0L, 0, false, 96, null);
        int defaultFillType = VectorKt.getDefaultFillType();
        SolidColor solidColor = new SolidColor(Color.INSTANCE.m3498getBlack0d7_KjU(), null);
        int iM3803getButtKaPHkGw = StrokeCap.INSTANCE.m3803getButtKaPHkGw();
        int iM3813getBevelLxFBmk8 = StrokeJoin.INSTANCE.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderR = a.r(21.41f, 11.58f, -9.0f, -9.0f);
        pathBuilderR.curveTo(12.05f, 2.22f, 11.55f, 2.0f, 11.0f, 2.0f);
        pathBuilderR.lineTo(4.0f, 2.0f);
        pathBuilderR.curveToRelative(-1.1f, 0.0f, -2.0f, 0.9f, -2.0f, 2.0f);
        pathBuilderR.verticalLineToRelative(7.0f);
        pathBuilderR.curveToRelative(0.0f, 0.55f, 0.22f, 1.05f, 0.59f, 1.42f);
        pathBuilderR.lineToRelative(9.0f, 9.0f);
        pathBuilderR.curveToRelative(0.36f, 0.36f, 0.86f, 0.58f, 1.41f, 0.58f);
        pathBuilderR.curveToRelative(0.55f, 0.0f, 1.05f, -0.22f, 1.41f, -0.59f);
        pathBuilderR.lineToRelative(7.0f, -7.0f);
        pathBuilderR.curveToRelative(0.37f, -0.36f, 0.59f, -0.86f, 0.59f, -1.41f);
        pathBuilderR.curveToRelative(0.0f, -0.55f, -0.23f, -1.06f, -0.59f, -1.42f);
        pathBuilderR.close();
        pathBuilderR.moveTo(5.5f, 7.0f);
        pathBuilderR.curveTo(4.67f, 7.0f, 4.0f, 6.33f, 4.0f, 5.5f);
        pathBuilderR.reflectiveCurveTo(4.67f, 4.0f, 5.5f, 4.0f);
        pathBuilderR.reflectiveCurveTo(7.0f, 4.67f, 7.0f, 5.5f);
        pathBuilderR.reflectiveCurveTo(6.33f, 7.0f, 5.5f, 7.0f);
        a.p(pathBuilderR, 17.27f, 15.27f, 13.0f, 19.54f);
        pathBuilderR.lineToRelative(-4.27f, -4.27f);
        pathBuilderR.curveTo(8.28f, 14.81f, 8.0f, 14.19f, 8.0f, 13.5f);
        pathBuilderR.curveToRelative(0.0f, -1.38f, 1.12f, -2.5f, 2.5f, -2.5f);
        pathBuilderR.curveToRelative(0.69f, 0.0f, 1.32f, 0.28f, 1.77f, 0.74f);
        pathBuilderR.lineToRelative(0.73f, 0.72f);
        pathBuilderR.lineToRelative(0.73f, -0.73f);
        pathBuilderR.curveToRelative(0.45f, -0.45f, 1.08f, -0.73f, 1.77f, -0.73f);
        pathBuilderR.curveToRelative(1.38f, 0.0f, 2.5f, 1.12f, 2.5f, 2.5f);
        pathBuilderR.curveToRelative(0.0f, 0.69f, -0.28f, 1.32f, -0.73f, 1.77f);
        pathBuilderR.close();
        ImageVector imageVectorBuild = ImageVector.Builder.m4024addPathoIyEayM$default(builder, pathBuilderR.getNodes(), defaultFillType, "", solidColor, 1.0f, null, 1.0f, 1.0f, iM3803getButtKaPHkGw, iM3813getBevelLxFBmk8, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null).build();
        _loyalty = imageVectorBuild;
        return imageVectorBuild;
    }
}
