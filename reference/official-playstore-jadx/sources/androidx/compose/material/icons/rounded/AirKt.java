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
import v.c;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\"\u0010\u0010\u0000\u001a\u0004\u0018\u00010\u0001X\u0082\u000e¢\u0006\u0002\n\u0000\"\u0015\u0010\u0002\u001a\u00020\u0001*\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"_air", "Landroidx/compose/ui/graphics/vector/ImageVector;", "Air", "Landroidx/compose/material/icons/Icons$Rounded;", "getAir", "(Landroidx/compose/material/icons/Icons$Rounded;)Landroidx/compose/ui/graphics/vector/ImageVector;", "material-icons-extended_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class AirKt {
    private static ImageVector _air;

    public static final ImageVector getAir(Icons.Rounded rounded) {
        ImageVector imageVector = _air;
        if (imageVector != null) {
            return imageVector;
        }
        ImageVector.Builder builder = new ImageVector.Builder("Rounded.Air", Dp.m5678constructorimpl(24.0f), Dp.m5678constructorimpl(24.0f), 24.0f, 24.0f, 0L, 0, false, 96, null);
        int defaultFillType = VectorKt.getDefaultFillType();
        SolidColor solidColor = new SolidColor(Color.INSTANCE.m3498getBlack0d7_KjU(), null);
        int iM3803getButtKaPHkGw = StrokeCap.INSTANCE.m3803getButtKaPHkGw();
        int iM3813getBevelLxFBmk8 = StrokeJoin.INSTANCE.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderA = c.a(14.35f, 17.95f);
        pathBuilderA.curveToRelative(-0.28f, 0.89f, -1.01f, 1.62f, -1.9f, 1.9f);
        pathBuilderA.curveToRelative(-1.51f, 0.48f, -2.94f, -0.23f, -3.59f, -1.42f);
        pathBuilderA.curveTo(8.51f, 17.78f, 9.03f, 17.0f, 9.77f, 17.0f);
        pathBuilderA.horizontalLineToRelative(0.01f);
        pathBuilderA.curveToRelative(0.34f, 0.0f, 0.68f, 0.16f, 0.84f, 0.46f);
        pathBuilderA.curveToRelative(0.17f, 0.32f, 0.5f, 0.54f, 0.89f, 0.54f);
        pathBuilderA.curveToRelative(0.55f, 0.0f, 1.0f, -0.45f, 1.0f, -1.0f);
        pathBuilderA.reflectiveCurveToRelative(-0.45f, -1.0f, -1.0f, -1.0f);
        pathBuilderA.horizontalLineTo(3.0f);
        pathBuilderA.curveToRelative(-0.55f, 0.0f, -1.0f, -0.45f, -1.0f, -1.0f);
        pathBuilderA.verticalLineToRelative(0.0f);
        pathBuilderA.curveToRelative(0.0f, -0.55f, 0.45f, -1.0f, 1.0f, -1.0f);
        pathBuilderA.horizontalLineToRelative(8.5f);
        pathBuilderA.curveTo(13.46f, 14.0f, 15.0f, 15.9f, 14.35f, 17.95f);
        pathBuilderA.close();
        pathBuilderA.moveTo(18.91f, 5.67f);
        pathBuilderA.curveToRelative(-0.29f, -1.26f, -1.32f, -2.29f, -2.58f, -2.58f);
        pathBuilderA.curveToRelative(-1.76f, -0.4f, -3.37f, 0.53f, -4.02f, 1.98f);
        pathBuilderA.curveTo(12.0f, 5.74f, 12.48f, 6.5f, 13.21f, 6.5f);
        pathBuilderA.horizontalLineToRelative(0.0f);
        pathBuilderA.curveToRelative(0.39f, 0.0f, 0.75f, -0.22f, 0.9f, -0.57f);
        pathBuilderA.curveTo(14.34f, 5.38f, 14.87f, 5.0f, 15.5f, 5.0f);
        pathBuilderA.curveTo(16.33f, 5.0f, 17.0f, 5.67f, 17.0f, 6.5f);
        pathBuilderA.reflectiveCurveTo(16.33f, 8.0f, 15.5f, 8.0f);
        pathBuilderA.horizontalLineTo(3.0f);
        pathBuilderA.curveTo(2.45f, 8.0f, 2.0f, 8.45f, 2.0f, 9.0f);
        pathBuilderA.verticalLineToRelative(0.0f);
        pathBuilderA.curveToRelative(0.0f, 0.55f, 0.45f, 1.0f, 1.0f, 1.0f);
        pathBuilderA.horizontalLineToRelative(12.5f);
        pathBuilderA.curveTo(17.7f, 10.0f, 19.43f, 7.96f, 18.91f, 5.67f);
        a.p(pathBuilderA, 18.4f, 11.0f, 3.0f, 11.0f);
        pathBuilderA.curveToRelative(-0.55f, 0.0f, -1.0f, 0.45f, -1.0f, 1.0f);
        pathBuilderA.verticalLineToRelative(0.0f);
        pathBuilderA.curveToRelative(0.0f, 0.55f, 0.45f, 1.0f, 1.0f, 1.0f);
        pathBuilderA.horizontalLineToRelative(15.5f);
        pathBuilderA.curveToRelative(0.83f, 0.0f, 1.5f, 0.67f, 1.5f, 1.5f);
        pathBuilderA.curveToRelative(0.0f, 0.63f, -0.38f, 1.16f, -0.93f, 1.39f);
        pathBuilderA.curveToRelative(-0.36f, 0.15f, -0.57f, 0.51f, -0.57f, 0.9f);
        pathBuilderA.verticalLineToRelative(0.0f);
        pathBuilderA.curveToRelative(0.0f, 0.73f, 0.76f, 1.21f, 1.43f, 0.91f);
        pathBuilderA.curveToRelative(1.43f, -0.64f, 2.35f, -2.21f, 2.0f, -3.93f);
        pathBuilderA.curveTo(21.59f, 12.13f, 20.07f, 11.0f, 18.4f, 11.0f);
        pathBuilderA.close();
        ImageVector imageVectorBuild = ImageVector.Builder.m4024addPathoIyEayM$default(builder, pathBuilderA.getNodes(), defaultFillType, "", solidColor, 1.0f, null, 1.0f, 1.0f, iM3803getButtKaPHkGw, iM3813getBevelLxFBmk8, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null).build();
        _air = imageVectorBuild;
        return imageVectorBuild;
    }
}
