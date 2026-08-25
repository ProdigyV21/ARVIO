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
import v.c;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\"\u0010\u0010\u0000\u001a\u0004\u0018\u00010\u0001X\u0082\u000e¢\u0006\u0002\n\u0000\"\u0015\u0010\u0002\u001a\u00020\u0001*\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"_cloudCircle", "Landroidx/compose/ui/graphics/vector/ImageVector;", "CloudCircle", "Landroidx/compose/material/icons/Icons$Outlined;", "getCloudCircle", "(Landroidx/compose/material/icons/Icons$Outlined;)Landroidx/compose/ui/graphics/vector/ImageVector;", "material-icons-extended_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class CloudCircleKt {
    private static ImageVector _cloudCircle;

    public static final ImageVector getCloudCircle(Icons.Outlined outlined) {
        ImageVector imageVector = _cloudCircle;
        if (imageVector != null) {
            return imageVector;
        }
        ImageVector.Builder builder = new ImageVector.Builder("Outlined.CloudCircle", Dp.m5678constructorimpl(24.0f), Dp.m5678constructorimpl(24.0f), 24.0f, 24.0f, 0L, 0, false, 96, null);
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
        pathBuilderA.moveTo(16.29f, 10.19f);
        pathBuilderA.curveToRelative(-0.4f, -2.01f, -2.16f, -3.52f, -4.29f, -3.52f);
        pathBuilderA.curveToRelative(-1.69f, 0.0f, -3.15f, 0.96f, -3.88f, 2.36f);
        pathBuilderA.curveTo(6.36f, 9.21f, 5.0f, 10.7f, 5.0f, 12.5f);
        pathBuilderA.curveTo(5.0f, 14.43f, 6.57f, 16.0f, 8.5f, 16.0f);
        pathBuilderA.horizontalLineToRelative(7.58f);
        pathBuilderA.curveToRelative(1.61f, 0.0f, 2.92f, -1.31f, 2.92f, -2.92f);
        pathBuilderA.curveToRelative(0.0f, -1.54f, -1.2f, -2.79f, -2.71f, -2.89f);
        a.p(pathBuilderA, 16.0f, 14.0f, 8.5f, 14.0f);
        pathBuilderA.curveToRelative(-0.83f, 0.0f, -1.5f, -0.67f, -1.5f, -1.5f);
        pathBuilderA.reflectiveCurveTo(7.67f, 11.0f, 8.5f, 11.0f);
        pathBuilderA.horizontalLineToRelative(0.9f);
        pathBuilderA.lineToRelative(0.49f, -1.05f);
        pathBuilderA.curveToRelative(0.41f, -0.79f, 1.22f, -1.28f, 2.11f, -1.28f);
        pathBuilderA.curveToRelative(1.13f, 0.0f, 2.11f, 0.8f, 2.33f, 1.91f);
        pathBuilderA.lineToRelative(0.28f, 1.42f);
        pathBuilderA.lineTo(16.0f, 12.0f);
        pathBuilderA.curveToRelative(0.55f, 0.0f, 1.0f, 0.45f, 1.0f, 1.0f);
        ImageVector imageVectorBuild = ImageVector.Builder.m4024addPathoIyEayM$default(builder, c.h(pathBuilderA, -0.45f, 1.0f, -1.0f, 1.0f), defaultFillType, "", solidColor, 1.0f, null, 1.0f, 1.0f, iM3803getButtKaPHkGw, iM3813getBevelLxFBmk8, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null).build();
        _cloudCircle = imageVectorBuild;
        return imageVectorBuild;
    }
}
