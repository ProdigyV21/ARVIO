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
import v.e;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\"\u0010\u0010\u0000\u001a\u0004\u0018\u00010\u0001X\u0082\u000e¢\u0006\u0002\n\u0000\"\u0015\u0010\u0002\u001a\u00020\u0001*\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"_savings", "Landroidx/compose/ui/graphics/vector/ImageVector;", "Savings", "Landroidx/compose/material/icons/Icons$Rounded;", "getSavings", "(Landroidx/compose/material/icons/Icons$Rounded;)Landroidx/compose/ui/graphics/vector/ImageVector;", "material-icons-extended_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class SavingsKt {
    private static ImageVector _savings;

    public static final ImageVector getSavings(Icons.Rounded rounded) {
        ImageVector imageVector = _savings;
        if (imageVector != null) {
            return imageVector;
        }
        ImageVector.Builder builder = new ImageVector.Builder("Rounded.Savings", Dp.m5678constructorimpl(24.0f), Dp.m5678constructorimpl(24.0f), 24.0f, 24.0f, 0L, 0, false, 96, null);
        int defaultFillType = VectorKt.getDefaultFillType();
        SolidColor solidColor = new SolidColor(Color.INSTANCE.m3498getBlack0d7_KjU(), null);
        int iM3803getButtKaPHkGw = StrokeCap.INSTANCE.m3803getButtKaPHkGw();
        int iM3813getBevelLxFBmk8 = StrokeJoin.INSTANCE.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderR = a.r(19.83f, 7.5f, -2.27f, -2.27f);
        pathBuilderR.curveToRelative(0.07f, -0.42f, 0.18f, -0.81f, 0.32f, -1.15f);
        pathBuilderR.curveToRelative(0.11f, -0.26f, 0.15f, -0.56f, 0.09f, -0.87f);
        pathBuilderR.curveTo(17.84f, 2.49f, 17.14f, 1.99f, 16.4f, 2.0f);
        pathBuilderR.curveToRelative(-1.59f, 0.03f, -3.0f, 0.81f, -3.9f, 2.0f);
        pathBuilderR.lineToRelative(-5.0f, 0.0f);
        pathBuilderR.curveTo(4.46f, 4.0f, 2.0f, 6.46f, 2.0f, 9.5f);
        pathBuilderR.curveToRelative(0.0f, 2.25f, 1.37f, 7.48f, 2.08f, 10.04f);
        pathBuilderR.curveTo(4.32f, 20.4f, 5.11f, 21.0f, 6.01f, 21.0f);
        pathBuilderR.lineTo(8.0f, 21.0f);
        pathBuilderR.curveToRelative(1.1f, 0.0f, 2.0f, -0.9f, 2.0f, -2.0f);
        e.l(pathBuilderR, 0.0f, 2.0f, 0.0f);
        pathBuilderR.curveToRelative(0.0f, 1.1f, 0.9f, 2.0f, 2.0f, 2.0f);
        pathBuilderR.lineToRelative(2.01f, 0.0f);
        pathBuilderR.curveToRelative(0.88f, 0.0f, 1.66f, -0.58f, 1.92f, -1.43f);
        pathBuilderR.lineToRelative(1.25f, -4.16f);
        pathBuilderR.lineToRelative(2.14f, -0.72f);
        pathBuilderR.curveToRelative(0.41f, -0.14f, 0.68f, -0.52f, 0.68f, -0.95f);
        pathBuilderR.verticalLineTo(8.5f);
        pathBuilderR.curveToRelative(0.0f, -0.55f, -0.45f, -1.0f, -1.0f, -1.0f);
        w.a.s(pathBuilderR, 19.83f, 12.0f, 9.0f, 9.0f);
        pathBuilderR.curveTo(8.45f, 9.0f, 8.0f, 8.55f, 8.0f, 8.0f);
        pathBuilderR.verticalLineToRelative(0.0f);
        pathBuilderR.curveToRelative(0.0f, -0.55f, 0.45f, -1.0f, 1.0f, -1.0f);
        pathBuilderR.horizontalLineToRelative(3.0f);
        pathBuilderR.curveToRelative(0.55f, 0.0f, 1.0f, 0.45f, 1.0f, 1.0f);
        pathBuilderR.verticalLineToRelative(0.0f);
        pathBuilderR.curveTo(13.0f, 8.55f, 12.55f, 9.0f, 12.0f, 9.0f);
        pathBuilderR.close();
        pathBuilderR.moveTo(16.0f, 11.0f);
        pathBuilderR.curveToRelative(-0.55f, 0.0f, -1.0f, -0.45f, -1.0f, -1.0f);
        pathBuilderR.curveToRelative(0.0f, -0.55f, 0.45f, -1.0f, 1.0f, -1.0f);
        pathBuilderR.reflectiveCurveToRelative(1.0f, 0.45f, 1.0f, 1.0f);
        pathBuilderR.curveTo(17.0f, 10.55f, 16.55f, 11.0f, 16.0f, 11.0f);
        pathBuilderR.close();
        ImageVector imageVectorBuild = ImageVector.Builder.m4024addPathoIyEayM$default(builder, pathBuilderR.getNodes(), defaultFillType, "", solidColor, 1.0f, null, 1.0f, 1.0f, iM3803getButtKaPHkGw, iM3813getBevelLxFBmk8, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null).build();
        _savings = imageVectorBuild;
        return imageVectorBuild;
    }
}
