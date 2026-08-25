package androidx.compose.material.icons.outlined;

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
import v.b;
import v.c;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\"\u0010\u0010\u0000\u001a\u0004\u0018\u00010\u0001X\u0082\u000e¢\u0006\u0002\n\u0000\"\u0015\u0010\u0002\u001a\u00020\u0001*\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"_monetizationOn", "Landroidx/compose/ui/graphics/vector/ImageVector;", "MonetizationOn", "Landroidx/compose/material/icons/Icons$Outlined;", "getMonetizationOn", "(Landroidx/compose/material/icons/Icons$Outlined;)Landroidx/compose/ui/graphics/vector/ImageVector;", "material-icons-extended_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class MonetizationOnKt {
    private static ImageVector _monetizationOn;

    public static final ImageVector getMonetizationOn(Icons.Outlined outlined) {
        ImageVector imageVector = _monetizationOn;
        if (imageVector != null) {
            return imageVector;
        }
        ImageVector.Builder builder = new ImageVector.Builder("Outlined.MonetizationOn", Dp.m5678constructorimpl(24.0f), Dp.m5678constructorimpl(24.0f), 24.0f, 24.0f, 0L, 0, false, 96, null);
        int defaultFillType = VectorKt.getDefaultFillType();
        SolidColor solidColor = new SolidColor(Color.INSTANCE.m3498getBlack0d7_KjU(), null);
        int iM3803getButtKaPHkGw = StrokeCap.INSTANCE.m3803getButtKaPHkGw();
        int iM3813getBevelLxFBmk8 = StrokeJoin.INSTANCE.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderA = c.a(12.0f, 2.0f);
        pathBuilderA.curveTo(6.48f, 2.0f, 2.0f, 6.48f, 2.0f, 12.0f);
        pathBuilderA.reflectiveCurveToRelative(4.48f, 10.0f, 10.0f, 10.0f);
        pathBuilderA.reflectiveCurveToRelative(10.0f, -4.48f, 10.0f, -10.0f);
        pathBuilderA.reflectiveCurveTo(17.52f, 2.0f, 12.0f, 2.0f);
        pathBuilderA.close();
        pathBuilderA.moveTo(12.0f, 20.0f);
        pathBuilderA.curveToRelative(-4.41f, 0.0f, -8.0f, -3.59f, -8.0f, -8.0f);
        pathBuilderA.reflectiveCurveToRelative(3.59f, -8.0f, 8.0f, -8.0f);
        pathBuilderA.reflectiveCurveToRelative(8.0f, 3.59f, 8.0f, 8.0f);
        pathBuilderA.reflectiveCurveToRelative(-3.59f, 8.0f, -8.0f, 8.0f);
        pathBuilderA.close();
        pathBuilderA.moveTo(12.31f, 11.14f);
        pathBuilderA.curveToRelative(-1.77f, -0.45f, -2.34f, -0.94f, -2.34f, -1.67f);
        pathBuilderA.curveToRelative(0.0f, -0.84f, 0.79f, -1.43f, 2.1f, -1.43f);
        pathBuilderA.curveToRelative(1.38f, 0.0f, 1.9f, 0.66f, 1.94f, 1.64f);
        pathBuilderA.horizontalLineToRelative(1.71f);
        pathBuilderA.curveToRelative(-0.05f, -1.34f, -0.87f, -2.57f, -2.49f, -2.97f);
        pathBuilderA.lineTo(13.23f, 5.0f);
        pathBuilderA.lineTo(10.9f, 5.0f);
        pathBuilderA.verticalLineToRelative(1.69f);
        pathBuilderA.curveToRelative(-1.51f, 0.32f, -2.72f, 1.3f, -2.72f, 2.81f);
        pathBuilderA.curveToRelative(0.0f, 1.79f, 1.49f, 2.69f, 3.66f, 3.21f);
        pathBuilderA.curveToRelative(1.95f, 0.46f, 2.34f, 1.15f, 2.34f, 1.87f);
        pathBuilderA.curveToRelative(0.0f, 0.53f, -0.39f, 1.39f, -2.1f, 1.39f);
        pathBuilderA.curveToRelative(-1.6f, 0.0f, -2.23f, -0.72f, -2.32f, -1.64f);
        pathBuilderA.lineTo(8.04f, 14.33f);
        pathBuilderA.curveToRelative(0.1f, 1.7f, 1.36f, 2.66f, 2.86f, 2.97f);
        b.A(pathBuilderA, 10.9f, 19.0f, 2.34f, -1.67f);
        pathBuilderA.curveToRelative(1.52f, -0.29f, 2.72f, -1.16f, 2.73f, -2.77f);
        pathBuilderA.curveToRelative(-0.01f, -2.2f, -1.9f, -2.96f, -3.66f, -3.42f);
        pathBuilderA.close();
        ImageVector imageVectorBuild = ImageVector.Builder.m4024addPathoIyEayM$default(builder, pathBuilderA.getNodes(), defaultFillType, "", solidColor, 1.0f, null, 1.0f, 1.0f, iM3803getButtKaPHkGw, iM3813getBevelLxFBmk8, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null).build();
        _monetizationOn = imageVectorBuild;
        return imageVectorBuild;
    }
}
