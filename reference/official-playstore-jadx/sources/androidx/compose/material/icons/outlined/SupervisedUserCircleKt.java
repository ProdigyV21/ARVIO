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
import v.c;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\"\u0010\u0010\u0000\u001a\u0004\u0018\u00010\u0001X\u0082\u000e¢\u0006\u0002\n\u0000\"\u0015\u0010\u0002\u001a\u00020\u0001*\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"_supervisedUserCircle", "Landroidx/compose/ui/graphics/vector/ImageVector;", "SupervisedUserCircle", "Landroidx/compose/material/icons/Icons$Outlined;", "getSupervisedUserCircle", "(Landroidx/compose/material/icons/Icons$Outlined;)Landroidx/compose/ui/graphics/vector/ImageVector;", "material-icons-extended_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class SupervisedUserCircleKt {
    private static ImageVector _supervisedUserCircle;

    public static final ImageVector getSupervisedUserCircle(Icons.Outlined outlined) {
        ImageVector imageVector = _supervisedUserCircle;
        if (imageVector != null) {
            return imageVector;
        }
        ImageVector.Builder builder = new ImageVector.Builder("Outlined.SupervisedUserCircle", Dp.m5678constructorimpl(24.0f), Dp.m5678constructorimpl(24.0f), 24.0f, 24.0f, 0L, 0, false, 96, null);
        int defaultFillType = VectorKt.getDefaultFillType();
        SolidColor solidColor = new SolidColor(Color.INSTANCE.m3498getBlack0d7_KjU(), null);
        int iM3803getButtKaPHkGw = StrokeCap.INSTANCE.m3803getButtKaPHkGw();
        int iM3813getBevelLxFBmk8 = StrokeJoin.INSTANCE.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderA = c.a(12.5f, 10.0f);
        pathBuilderA.curveToRelative(0.0f, -1.65f, -1.35f, -3.0f, -3.0f, -3.0f);
        pathBuilderA.reflectiveCurveToRelative(-3.0f, 1.35f, -3.0f, 3.0f);
        pathBuilderA.reflectiveCurveToRelative(1.35f, 3.0f, 3.0f, 3.0f);
        pathBuilderA.reflectiveCurveToRelative(3.0f, -1.35f, 3.0f, -3.0f);
        pathBuilderA.close();
        pathBuilderA.moveTo(9.5f, 11.0f);
        pathBuilderA.curveToRelative(-0.55f, 0.0f, -1.0f, -0.45f, -1.0f, -1.0f);
        pathBuilderA.reflectiveCurveToRelative(0.45f, -1.0f, 1.0f, -1.0f);
        pathBuilderA.reflectiveCurveToRelative(1.0f, 0.45f, 1.0f, 1.0f);
        pathBuilderA.reflectiveCurveToRelative(-0.45f, 1.0f, -1.0f, 1.0f);
        pathBuilderA.close();
        pathBuilderA.moveTo(16.0f, 13.0f);
        pathBuilderA.curveToRelative(1.11f, 0.0f, 2.0f, -0.89f, 2.0f, -2.0f);
        pathBuilderA.curveToRelative(0.0f, -1.11f, -0.89f, -2.0f, -2.0f, -2.0f);
        pathBuilderA.curveToRelative(-1.11f, 0.0f, -2.01f, 0.89f, -2.0f, 2.0f);
        pathBuilderA.curveToRelative(0.0f, 1.11f, 0.89f, 2.0f, 2.0f, 2.0f);
        pathBuilderA.close();
        pathBuilderA.moveTo(11.99f, 2.01f);
        pathBuilderA.curveToRelative(-5.52f, 0.0f, -10.0f, 4.48f, -10.0f, 10.0f);
        pathBuilderA.reflectiveCurveToRelative(4.48f, 10.0f, 10.0f, 10.0f);
        pathBuilderA.reflectiveCurveToRelative(10.0f, -4.48f, 10.0f, -10.0f);
        pathBuilderA.reflectiveCurveToRelative(-4.48f, -10.0f, -10.0f, -10.0f);
        pathBuilderA.close();
        pathBuilderA.moveTo(5.84f, 17.12f);
        pathBuilderA.curveToRelative(0.68f, -0.54f, 2.27f, -1.11f, 3.66f, -1.11f);
        pathBuilderA.curveToRelative(0.07f, 0.0f, 0.15f, 0.01f, 0.23f, 0.01f);
        pathBuilderA.curveToRelative(0.24f, -0.64f, 0.67f, -1.29f, 1.3f, -1.86f);
        pathBuilderA.curveToRelative(-0.56f, -0.1f, -1.09f, -0.16f, -1.53f, -0.16f);
        pathBuilderA.curveToRelative(-1.3f, 0.0f, -3.39f, 0.45f, -4.73f, 1.43f);
        pathBuilderA.curveToRelative(-0.5f, -1.04f, -0.78f, -2.2f, -0.78f, -3.43f);
        pathBuilderA.curveToRelative(0.0f, -4.41f, 3.59f, -8.0f, 8.0f, -8.0f);
        pathBuilderA.reflectiveCurveToRelative(8.0f, 3.59f, 8.0f, 8.0f);
        pathBuilderA.curveToRelative(0.0f, 1.2f, -0.27f, 2.34f, -0.75f, 3.37f);
        pathBuilderA.curveToRelative(-1.0f, -0.59f, -2.36f, -0.87f, -3.24f, -0.87f);
        pathBuilderA.curveToRelative(-1.52f, 0.0f, -4.5f, 0.81f, -4.5f, 2.7f);
        pathBuilderA.verticalLineToRelative(2.78f);
        pathBuilderA.curveToRelative(-2.27f, -0.13f, -4.29f, -1.21f, -5.66f, -2.86f);
        pathBuilderA.close();
        ImageVector imageVectorBuild = ImageVector.Builder.m4024addPathoIyEayM$default(builder, pathBuilderA.getNodes(), defaultFillType, "", solidColor, 1.0f, null, 1.0f, 1.0f, iM3803getButtKaPHkGw, iM3813getBevelLxFBmk8, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null).build();
        _supervisedUserCircle = imageVectorBuild;
        return imageVectorBuild;
    }
}
