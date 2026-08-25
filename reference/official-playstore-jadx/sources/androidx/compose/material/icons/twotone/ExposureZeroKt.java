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
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\"\u0010\u0010\u0000\u001a\u0004\u0018\u00010\u0001X\u0082\u000e¢\u0006\u0002\n\u0000\"\u0015\u0010\u0002\u001a\u00020\u0001*\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"_exposureZero", "Landroidx/compose/ui/graphics/vector/ImageVector;", "ExposureZero", "Landroidx/compose/material/icons/Icons$TwoTone;", "getExposureZero", "(Landroidx/compose/material/icons/Icons$TwoTone;)Landroidx/compose/ui/graphics/vector/ImageVector;", "material-icons-extended_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class ExposureZeroKt {
    private static ImageVector _exposureZero;

    public static final ImageVector getExposureZero(Icons.TwoTone twoTone) {
        ImageVector imageVector = _exposureZero;
        if (imageVector != null) {
            return imageVector;
        }
        ImageVector.Builder builder = new ImageVector.Builder("TwoTone.ExposureZero", Dp.m5678constructorimpl(24.0f), Dp.m5678constructorimpl(24.0f), 24.0f, 24.0f, 0L, 0, false, 96, null);
        int defaultFillType = VectorKt.getDefaultFillType();
        SolidColor solidColor = new SolidColor(Color.INSTANCE.m3498getBlack0d7_KjU(), null);
        int iM3803getButtKaPHkGw = StrokeCap.INSTANCE.m3803getButtKaPHkGw();
        int iM3813getBevelLxFBmk8 = StrokeJoin.INSTANCE.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderA = c.a(15.01f, 6.22f);
        pathBuilderA.curveToRelative(-0.36f, -0.43f, -0.8f, -0.74f, -1.31f, -0.93f);
        pathBuilderA.reflectiveCurveTo(12.63f, 5.0f, 12.0f, 5.0f);
        pathBuilderA.curveToRelative(-0.62f, 0.0f, -1.19f, 0.1f, -1.69f, 0.29f);
        pathBuilderA.curveToRelative(-0.51f, 0.19f, -0.95f, 0.5f, -1.31f, 0.93f);
        pathBuilderA.reflectiveCurveToRelative(-0.64f, 0.99f, -0.84f, 1.69f);
        pathBuilderA.curveToRelative(-0.2f, 0.7f, -0.3f, 1.55f, -0.3f, 2.55f);
        pathBuilderA.verticalLineToRelative(2.04f);
        pathBuilderA.curveToRelative(0.0f, 1.0f, 0.1f, 1.85f, 0.3f, 2.55f);
        pathBuilderA.curveToRelative(0.2f, 0.69f, 0.49f, 1.26f, 0.85f, 1.7f);
        pathBuilderA.reflectiveCurveToRelative(0.8f, 0.75f, 1.31f, 0.95f);
        pathBuilderA.curveToRelative(0.51f, 0.2f, 1.07f, 0.3f, 1.69f, 0.3f);
        pathBuilderA.curveToRelative(0.63f, 0.0f, 1.19f, -0.1f, 1.7f, -0.3f);
        pathBuilderA.curveToRelative(0.51f, -0.2f, 0.94f, -0.51f, 1.3f, -0.95f);
        pathBuilderA.curveToRelative(0.35f, -0.43f, 0.63f, -1.0f, 0.83f, -1.7f);
        pathBuilderA.curveToRelative(0.2f, -0.7f, 0.3f, -1.55f, 0.3f, -2.55f);
        pathBuilderA.horizontalLineToRelative(0.01f);
        pathBuilderA.verticalLineToRelative(-2.04f);
        pathBuilderA.curveToRelative(0.0f, -1.01f, -0.1f, -1.85f, -0.3f, -2.55f);
        pathBuilderA.curveToRelative(-0.2f, -0.7f, -0.48f, -1.26f, -0.84f, -1.69f);
        pathBuilderA.close();
        pathBuilderA.moveTo(14.04f, 12.8f);
        pathBuilderA.curveToRelative(0.0f, 0.63f, -0.04f, 1.17f, -0.13f, 1.62f);
        pathBuilderA.curveToRelative(-0.09f, 0.45f, -0.22f, 0.81f, -0.39f, 1.09f);
        pathBuilderA.reflectiveCurveToRelative(-0.38f, 0.48f, -0.63f, 0.61f);
        pathBuilderA.reflectiveCurveToRelative(-0.54f, 0.19f, -0.87f, 0.19f);
        pathBuilderA.curveToRelative(-0.33f, 0.0f, -0.62f, -0.06f, -0.87f, -0.19f);
        pathBuilderA.reflectiveCurveToRelative(-0.47f, -0.33f, -0.64f, -0.61f);
        pathBuilderA.curveToRelative(-0.17f, -0.28f, -0.31f, -0.64f, -0.4f, -1.09f);
        pathBuilderA.curveToRelative(-0.09f, -0.44f, -0.14f, -0.98f, -0.14f, -1.62f);
        pathBuilderA.verticalLineToRelative(-2.67f);
        pathBuilderA.curveToRelative(0.0f, -0.64f, 0.04f, -1.18f, 0.13f, -1.62f);
        pathBuilderA.curveToRelative(0.09f, -0.44f, 0.23f, -0.79f, 0.4f, -1.06f);
        pathBuilderA.reflectiveCurveToRelative(0.39f, -0.46f, 0.64f, -0.58f);
        pathBuilderA.reflectiveCurveToRelative(0.54f, -0.18f, 0.86f, -0.18f);
        pathBuilderA.reflectiveCurveToRelative(0.61f, 0.06f, 0.86f, 0.19f);
        pathBuilderA.curveToRelative(0.25f, 0.12f, 0.47f, 0.31f, 0.64f, 0.58f);
        pathBuilderA.curveToRelative(0.18f, 0.27f, 0.31f, 0.62f, 0.4f, 1.06f);
        pathBuilderA.curveToRelative(0.08f, 0.44f, 0.13f, 0.98f, 0.13f, 1.62f);
        ImageVector imageVectorBuild = ImageVector.Builder.m4024addPathoIyEayM$default(builder, a.s(pathBuilderA, 0.01f, 2.66f), defaultFillType, "", solidColor, 1.0f, null, 1.0f, 1.0f, iM3803getButtKaPHkGw, iM3813getBevelLxFBmk8, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null).build();
        _exposureZero = imageVectorBuild;
        return imageVectorBuild;
    }
}
