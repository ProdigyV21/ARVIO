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
import v.c;
import v.d;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\"\u0010\u0010\u0000\u001a\u0004\u0018\u00010\u0001X\u0082\u000e¢\u0006\u0002\n\u0000\"\u0015\u0010\u0002\u001a\u00020\u0001*\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"_monetizationOn", "Landroidx/compose/ui/graphics/vector/ImageVector;", "MonetizationOn", "Landroidx/compose/material/icons/Icons$Rounded;", "getMonetizationOn", "(Landroidx/compose/material/icons/Icons$Rounded;)Landroidx/compose/ui/graphics/vector/ImageVector;", "material-icons-extended_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class MonetizationOnKt {
    private static ImageVector _monetizationOn;

    public static final ImageVector getMonetizationOn(Icons.Rounded rounded) {
        ImageVector imageVector = _monetizationOn;
        if (imageVector != null) {
            return imageVector;
        }
        ImageVector.Builder builder = new ImageVector.Builder("Rounded.MonetizationOn", Dp.m5678constructorimpl(24.0f), Dp.m5678constructorimpl(24.0f), 24.0f, 24.0f, 0L, 0, false, 96, null);
        int defaultFillType = VectorKt.getDefaultFillType();
        SolidColor solidColor = new SolidColor(Color.INSTANCE.m3498getBlack0d7_KjU(), null);
        int iM3803getButtKaPHkGw = StrokeCap.INSTANCE.m3803getButtKaPHkGw();
        int iM3813getBevelLxFBmk8 = StrokeJoin.INSTANCE.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderA = c.a(12.0f, 2.0f);
        pathBuilderA.curveTo(6.48f, 2.0f, 2.0f, 6.48f, 2.0f, 12.0f);
        pathBuilderA.reflectiveCurveToRelative(4.48f, 10.0f, 10.0f, 10.0f);
        pathBuilderA.reflectiveCurveToRelative(10.0f, -4.48f, 10.0f, -10.0f);
        pathBuilderA.reflectiveCurveTo(17.52f, 2.0f, 12.0f, 2.0f);
        d.k(pathBuilderA, 13.41f, 18.09f, 0.58f);
        pathBuilderA.curveToRelative(0.0f, 0.73f, -0.6f, 1.33f, -1.33f, 1.33f);
        pathBuilderA.horizontalLineToRelative(-0.01f);
        pathBuilderA.curveToRelative(-0.73f, 0.0f, -1.33f, -0.6f, -1.33f, -1.33f);
        pathBuilderA.verticalLineToRelative(-0.6f);
        pathBuilderA.curveToRelative(-1.33f, -0.28f, -2.51f, -1.01f, -3.01f, -2.24f);
        pathBuilderA.curveToRelative(-0.23f, -0.55f, 0.2f, -1.16f, 0.8f, -1.16f);
        pathBuilderA.horizontalLineToRelative(0.24f);
        pathBuilderA.curveToRelative(0.37f, 0.0f, 0.67f, 0.25f, 0.81f, 0.6f);
        pathBuilderA.curveToRelative(0.29f, 0.75f, 1.05f, 1.27f, 2.51f, 1.27f);
        pathBuilderA.curveToRelative(1.96f, 0.0f, 2.4f, -0.98f, 2.4f, -1.59f);
        pathBuilderA.curveToRelative(0.0f, -0.83f, -0.44f, -1.61f, -2.67f, -2.14f);
        pathBuilderA.curveToRelative(-2.48f, -0.6f, -4.18f, -1.62f, -4.18f, -3.67f);
        pathBuilderA.curveToRelative(0.0f, -1.72f, 1.39f, -2.84f, 3.11f, -3.21f);
        pathBuilderA.verticalLineToRelative(-0.6f);
        pathBuilderA.curveToRelative(0.0f, -0.73f, 0.6f, -1.33f, 1.33f, -1.33f);
        pathBuilderA.horizontalLineToRelative(0.01f);
        pathBuilderA.curveToRelative(0.73f, 0.0f, 1.33f, 0.6f, 1.33f, 1.33f);
        pathBuilderA.verticalLineToRelative(0.62f);
        pathBuilderA.curveToRelative(1.38f, 0.34f, 2.25f, 1.2f, 2.63f, 2.26f);
        pathBuilderA.curveToRelative(0.2f, 0.55f, -0.22f, 1.13f, -0.81f, 1.13f);
        pathBuilderA.horizontalLineToRelative(-0.26f);
        pathBuilderA.curveToRelative(-0.37f, 0.0f, -0.67f, -0.26f, -0.77f, -0.62f);
        pathBuilderA.curveToRelative(-0.23f, -0.76f, -0.86f, -1.25f, -2.12f, -1.25f);
        pathBuilderA.curveToRelative(-1.5f, 0.0f, -2.4f, 0.68f, -2.4f, 1.64f);
        pathBuilderA.curveToRelative(0.0f, 0.84f, 0.65f, 1.39f, 2.67f, 1.91f);
        pathBuilderA.reflectiveCurveToRelative(4.18f, 1.39f, 4.18f, 3.91f);
        pathBuilderA.curveToRelative(-0.02f, 1.83f, -1.39f, 2.83f, -3.13f, 3.16f);
        pathBuilderA.close();
        ImageVector imageVectorBuild = ImageVector.Builder.m4024addPathoIyEayM$default(builder, pathBuilderA.getNodes(), defaultFillType, "", solidColor, 1.0f, null, 1.0f, 1.0f, iM3803getButtKaPHkGw, iM3813getBevelLxFBmk8, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null).build();
        _monetizationOn = imageVectorBuild;
        return imageVectorBuild;
    }
}
