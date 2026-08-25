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
import v.a;
import v.b;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\"\u0010\u0010\u0000\u001a\u0004\u0018\u00010\u0001X\u0082\u000e¢\u0006\u0002\n\u0000\"\u0015\u0010\u0002\u001a\u00020\u0001*\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"_localActivity", "Landroidx/compose/ui/graphics/vector/ImageVector;", "LocalActivity", "Landroidx/compose/material/icons/Icons$Outlined;", "getLocalActivity", "(Landroidx/compose/material/icons/Icons$Outlined;)Landroidx/compose/ui/graphics/vector/ImageVector;", "material-icons-extended_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class LocalActivityKt {
    private static ImageVector _localActivity;

    public static final ImageVector getLocalActivity(Icons.Outlined outlined) {
        ImageVector imageVector = _localActivity;
        if (imageVector != null) {
            return imageVector;
        }
        ImageVector.Builder builder = new ImageVector.Builder("Outlined.LocalActivity", Dp.m5678constructorimpl(24.0f), Dp.m5678constructorimpl(24.0f), 24.0f, 24.0f, 0L, 0, false, 96, null);
        int defaultFillType = VectorKt.getDefaultFillType();
        SolidColor solidColor = new SolidColor(Color.INSTANCE.m3498getBlack0d7_KjU(), null);
        int iM3803getButtKaPHkGw = StrokeCap.INSTANCE.m3803getButtKaPHkGw();
        int iM3813getBevelLxFBmk8 = StrokeJoin.INSTANCE.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderJ = a.j(22.0f, 10.0f, 22.0f, 6.0f);
        pathBuilderJ.curveToRelative(0.0f, -1.1f, -0.9f, -2.0f, -2.0f, -2.0f);
        pathBuilderJ.lineTo(4.0f, 4.0f);
        pathBuilderJ.curveToRelative(-1.1f, 0.0f, -1.99f, 0.9f, -1.99f, 2.0f);
        pathBuilderJ.verticalLineToRelative(4.0f);
        pathBuilderJ.curveToRelative(1.1f, 0.0f, 1.99f, 0.9f, 1.99f, 2.0f);
        pathBuilderJ.reflectiveCurveToRelative(-0.89f, 2.0f, -2.0f, 2.0f);
        pathBuilderJ.verticalLineToRelative(4.0f);
        pathBuilderJ.curveToRelative(0.0f, 1.1f, 0.9f, 2.0f, 2.0f, 2.0f);
        pathBuilderJ.horizontalLineToRelative(16.0f);
        pathBuilderJ.curveToRelative(1.1f, 0.0f, 2.0f, -0.9f, 2.0f, -2.0f);
        pathBuilderJ.verticalLineToRelative(-4.0f);
        pathBuilderJ.curveToRelative(-1.1f, 0.0f, -2.0f, -0.9f, -2.0f, -2.0f);
        pathBuilderJ.reflectiveCurveToRelative(0.9f, -2.0f, 2.0f, -2.0f);
        pathBuilderJ.close();
        pathBuilderJ.moveTo(20.0f, 8.54f);
        pathBuilderJ.curveToRelative(-1.19f, 0.69f, -2.0f, 1.99f, -2.0f, 3.46f);
        pathBuilderJ.reflectiveCurveToRelative(0.81f, 2.77f, 2.0f, 3.46f);
        pathBuilderJ.lineTo(20.0f, 18.0f);
        pathBuilderJ.lineTo(4.0f, 18.0f);
        pathBuilderJ.verticalLineToRelative(-2.54f);
        pathBuilderJ.curveToRelative(1.19f, -0.69f, 2.0f, -1.99f, 2.0f, -3.46f);
        pathBuilderJ.curveToRelative(0.0f, -1.48f, -0.8f, -2.77f, -1.99f, -3.46f);
        b.g(pathBuilderJ, 4.0f, 6.0f, 16.0f, 2.54f);
        pathBuilderJ.moveTo(9.07f, 16.0f);
        pathBuilderJ.lineTo(12.0f, 14.12f);
        pathBuilderJ.lineTo(14.93f, 16.0f);
        pathBuilderJ.lineToRelative(-0.89f, -3.36f);
        pathBuilderJ.lineToRelative(2.69f, -2.2f);
        pathBuilderJ.lineToRelative(-3.47f, -0.21f);
        pathBuilderJ.lineTo(12.0f, 7.0f);
        pathBuilderJ.lineToRelative(-1.27f, 3.22f);
        ImageVector imageVectorBuild = ImageVector.Builder.m4024addPathoIyEayM$default(builder, b.t(pathBuilderJ, -3.47f, 0.21f, 2.69f, 2.2f), defaultFillType, "", solidColor, 1.0f, null, 1.0f, 1.0f, iM3803getButtKaPHkGw, iM3813getBevelLxFBmk8, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null).build();
        _localActivity = imageVectorBuild;
        return imageVectorBuild;
    }
}
