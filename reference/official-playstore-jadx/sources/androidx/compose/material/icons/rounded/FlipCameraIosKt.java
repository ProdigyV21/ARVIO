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
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\"\u0010\u0010\u0000\u001a\u0004\u0018\u00010\u0001X\u0082\u000e¢\u0006\u0002\n\u0000\"\u0015\u0010\u0002\u001a\u00020\u0001*\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"_flipCameraIos", "Landroidx/compose/ui/graphics/vector/ImageVector;", "FlipCameraIos", "Landroidx/compose/material/icons/Icons$Rounded;", "getFlipCameraIos", "(Landroidx/compose/material/icons/Icons$Rounded;)Landroidx/compose/ui/graphics/vector/ImageVector;", "material-icons-extended_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class FlipCameraIosKt {
    private static ImageVector _flipCameraIos;

    public static final ImageVector getFlipCameraIos(Icons.Rounded rounded) {
        ImageVector imageVector = _flipCameraIos;
        if (imageVector != null) {
            return imageVector;
        }
        ImageVector.Builder builder = new ImageVector.Builder("Rounded.FlipCameraIos", Dp.m5678constructorimpl(24.0f), Dp.m5678constructorimpl(24.0f), 24.0f, 24.0f, 0L, 0, false, 96, null);
        int defaultFillType = VectorKt.getDefaultFillType();
        SolidColor solidColor = new SolidColor(Color.INSTANCE.m3498getBlack0d7_KjU(), null);
        int iM3803getButtKaPHkGw = StrokeCap.INSTANCE.m3803getButtKaPHkGw();
        int iM3813getBevelLxFBmk8 = StrokeJoin.INSTANCE.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderG = c.g(20.0f, 5.0f, -3.17f, -1.24f, -1.35f);
        pathBuilderG.curveTo(15.22f, 3.24f, 14.68f, 3.0f, 14.12f, 3.0f);
        pathBuilderG.horizontalLineTo(9.88f);
        pathBuilderG.curveToRelative(-0.56f, 0.0f, -1.1f, 0.24f, -1.48f, 0.65f);
        pathBuilderG.lineTo(7.17f, 5.0f);
        pathBuilderG.horizontalLineTo(4.0f);
        pathBuilderG.curveTo(2.9f, 5.0f, 2.0f, 5.9f, 2.0f, 7.0f);
        pathBuilderG.verticalLineToRelative(12.0f);
        pathBuilderG.curveToRelative(0.0f, 1.1f, 0.9f, 2.0f, 2.0f, 2.0f);
        pathBuilderG.horizontalLineToRelative(16.0f);
        pathBuilderG.curveToRelative(1.1f, 0.0f, 2.0f, -0.9f, 2.0f, -2.0f);
        pathBuilderG.verticalLineTo(7.0f);
        pathBuilderG.curveTo(22.0f, 5.9f, 21.1f, 5.0f, 20.0f, 5.0f);
        pathBuilderG.close();
        pathBuilderG.moveTo(13.67f, 17.7f);
        pathBuilderG.curveTo(13.15f, 17.89f, 12.59f, 18.0f, 12.0f, 18.0f);
        pathBuilderG.curveToRelative(-2.76f, 0.0f, -5.0f, -2.24f, -5.0f, -5.0f);
        pathBuilderG.horizontalLineTo(5.0f);
        pathBuilderG.lineToRelative(2.5f, -2.5f);
        pathBuilderG.lineTo(10.0f, 13.0f);
        pathBuilderG.horizontalLineTo(8.0f);
        pathBuilderG.curveToRelative(0.0f, 2.21f, 1.79f, 4.0f, 4.0f, 4.0f);
        pathBuilderG.curveToRelative(0.46f, 0.0f, 0.91f, -0.08f, 1.32f, -0.23f);
        pathBuilderG.curveToRelative(0.19f, -0.07f, 0.39f, -0.03f, 0.53f, 0.11f);
        pathBuilderG.curveTo(14.11f, 17.14f, 14.01f, 17.57f, 13.67f, 17.7f);
        a.p(pathBuilderG, 16.5f, 15.5f, 14.0f, 13.0f);
        pathBuilderG.horizontalLineToRelative(2.0f);
        pathBuilderG.curveToRelative(0.0f, -2.21f, -1.79f, -4.0f, -4.0f, -4.0f);
        pathBuilderG.curveToRelative(-0.46f, 0.0f, -0.91f, 0.08f, -1.32f, 0.23f);
        pathBuilderG.curveToRelative(-0.19f, 0.07f, -0.39f, 0.03f, -0.53f, -0.11f);
        pathBuilderG.curveTo(9.89f, 8.86f, 9.99f, 8.43f, 10.33f, 8.3f);
        pathBuilderG.curveTo(10.85f, 8.11f, 11.41f, 8.0f, 12.0f, 8.0f);
        pathBuilderG.curveToRelative(2.76f, 0.0f, 5.0f, 2.24f, 5.0f, 5.0f);
        ImageVector imageVectorBuild = ImageVector.Builder.m4024addPathoIyEayM$default(builder, w.a.h(pathBuilderG, 2.0f, 16.5f, 15.5f), defaultFillType, "", solidColor, 1.0f, null, 1.0f, 1.0f, iM3803getButtKaPHkGw, iM3813getBevelLxFBmk8, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null).build();
        _flipCameraIos = imageVectorBuild;
        return imageVectorBuild;
    }
}
