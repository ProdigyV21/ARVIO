package androidx.compose.material.icons.twotone;

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
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\"\u0010\u0010\u0000\u001a\u0004\u0018\u00010\u0001X\u0082\u000e¢\u0006\u0002\n\u0000\"\u0015\u0010\u0002\u001a\u00020\u0001*\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"_cloudCircle", "Landroidx/compose/ui/graphics/vector/ImageVector;", "CloudCircle", "Landroidx/compose/material/icons/Icons$TwoTone;", "getCloudCircle", "(Landroidx/compose/material/icons/Icons$TwoTone;)Landroidx/compose/ui/graphics/vector/ImageVector;", "material-icons-extended_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class CloudCircleKt {
    private static ImageVector _cloudCircle;

    public static final ImageVector getCloudCircle(Icons.TwoTone twoTone) {
        ImageVector imageVector = _cloudCircle;
        if (imageVector != null) {
            return imageVector;
        }
        ImageVector.Builder builder = new ImageVector.Builder("TwoTone.CloudCircle", Dp.m5678constructorimpl(24.0f), Dp.m5678constructorimpl(24.0f), 24.0f, 24.0f, 0L, 0, false, 96, null);
        int defaultFillType = VectorKt.getDefaultFillType();
        Color.Companion companion = Color.INSTANCE;
        SolidColor solidColor = new SolidColor(companion.m3498getBlack0d7_KjU(), null);
        StrokeCap.Companion companion2 = StrokeCap.INSTANCE;
        int iM3803getButtKaPHkGw = companion2.m3803getButtKaPHkGw();
        StrokeJoin.Companion companion3 = StrokeJoin.INSTANCE;
        int iM3813getBevelLxFBmk8 = companion3.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderA = c.a(12.0f, 4.0f);
        pathBuilderA.curveToRelative(-4.41f, 0.0f, -8.0f, 3.59f, -8.0f, 8.0f);
        pathBuilderA.reflectiveCurveToRelative(3.59f, 8.0f, 8.0f, 8.0f);
        pathBuilderA.reflectiveCurveToRelative(8.0f, -3.59f, 8.0f, -8.0f);
        pathBuilderA.reflectiveCurveToRelative(-3.59f, -8.0f, -8.0f, -8.0f);
        a.p(pathBuilderA, 16.08f, 16.0f, 8.5f, 16.0f);
        pathBuilderA.curveTo(6.57f, 16.0f, 5.0f, 14.43f, 5.0f, 12.5f);
        pathBuilderA.curveToRelative(0.0f, -1.8f, 1.36f, -3.29f, 3.12f, -3.48f);
        pathBuilderA.curveToRelative(0.73f, -1.4f, 2.19f, -2.36f, 3.88f, -2.36f);
        pathBuilderA.curveToRelative(2.12f, 0.0f, 3.89f, 1.51f, 4.29f, 3.52f);
        pathBuilderA.curveToRelative(1.52f, 0.1f, 2.71f, 1.35f, 2.71f, 2.89f);
        pathBuilderA.curveToRelative(0.0f, 1.62f, -1.31f, 2.93f, -2.92f, 2.93f);
        pathBuilderA.close();
        ImageVector.Builder.m4024addPathoIyEayM$default(builder, pathBuilderA.getNodes(), defaultFillType, "", solidColor, 0.3f, null, 0.3f, 1.0f, iM3803getButtKaPHkGw, iM3813getBevelLxFBmk8, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null);
        int defaultFillType2 = VectorKt.getDefaultFillType();
        SolidColor solidColor2 = new SolidColor(companion.m3498getBlack0d7_KjU(), null);
        int iM3803getButtKaPHkGw2 = companion2.m3803getButtKaPHkGw();
        int iM3813getBevelLxFBmk82 = companion3.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderA2 = c.a(12.0f, 2.0f);
        pathBuilderA2.curveTo(6.48f, 2.0f, 2.0f, 6.48f, 2.0f, 12.0f);
        pathBuilderA2.reflectiveCurveToRelative(4.48f, 10.0f, 10.0f, 10.0f);
        pathBuilderA2.reflectiveCurveToRelative(10.0f, -4.48f, 10.0f, -10.0f);
        pathBuilderA2.reflectiveCurveTo(17.52f, 2.0f, 12.0f, 2.0f);
        pathBuilderA2.close();
        pathBuilderA2.moveTo(12.0f, 20.0f);
        pathBuilderA2.curveToRelative(-4.41f, 0.0f, -8.0f, -3.59f, -8.0f, -8.0f);
        pathBuilderA2.reflectiveCurveToRelative(3.59f, -8.0f, 8.0f, -8.0f);
        pathBuilderA2.reflectiveCurveToRelative(8.0f, 3.59f, 8.0f, 8.0f);
        pathBuilderA2.reflectiveCurveToRelative(-3.59f, 8.0f, -8.0f, 8.0f);
        pathBuilderA2.close();
        pathBuilderA2.moveTo(16.29f, 10.19f);
        pathBuilderA2.curveToRelative(-0.4f, -2.01f, -2.16f, -3.52f, -4.29f, -3.52f);
        pathBuilderA2.curveToRelative(-1.69f, 0.0f, -3.15f, 0.96f, -3.88f, 2.36f);
        pathBuilderA2.curveTo(6.36f, 9.21f, 5.0f, 10.7f, 5.0f, 12.5f);
        pathBuilderA2.curveTo(5.0f, 14.43f, 6.57f, 16.0f, 8.5f, 16.0f);
        pathBuilderA2.horizontalLineToRelative(7.58f);
        pathBuilderA2.curveToRelative(1.61f, 0.0f, 2.92f, -1.31f, 2.92f, -2.92f);
        pathBuilderA2.curveToRelative(0.0f, -1.54f, -1.2f, -2.79f, -2.71f, -2.89f);
        a.p(pathBuilderA2, 16.0f, 14.0f, 8.5f, 14.0f);
        pathBuilderA2.curveToRelative(-0.83f, 0.0f, -1.5f, -0.67f, -1.5f, -1.5f);
        pathBuilderA2.reflectiveCurveTo(7.67f, 11.0f, 8.5f, 11.0f);
        pathBuilderA2.horizontalLineToRelative(0.9f);
        pathBuilderA2.lineToRelative(0.49f, -1.05f);
        pathBuilderA2.curveToRelative(0.41f, -0.79f, 1.22f, -1.28f, 2.11f, -1.28f);
        pathBuilderA2.curveToRelative(1.13f, 0.0f, 2.11f, 0.8f, 2.33f, 1.91f);
        pathBuilderA2.lineToRelative(0.28f, 1.42f);
        pathBuilderA2.lineTo(16.0f, 12.0f);
        pathBuilderA2.curveToRelative(0.55f, 0.0f, 1.0f, 0.45f, 1.0f, 1.0f);
        ImageVector imageVectorBuild = ImageVector.Builder.m4024addPathoIyEayM$default(builder, c.h(pathBuilderA2, -0.45f, 1.0f, -1.0f, 1.0f), defaultFillType2, "", solidColor2, 1.0f, null, 1.0f, 1.0f, iM3803getButtKaPHkGw2, iM3813getBevelLxFBmk82, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null).build();
        _cloudCircle = imageVectorBuild;
        return imageVectorBuild;
    }
}
