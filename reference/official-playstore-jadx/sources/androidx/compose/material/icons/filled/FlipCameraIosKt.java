package androidx.compose.material.icons.filled;

import a0.b;
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
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\"\u0010\u0010\u0000\u001a\u0004\u0018\u00010\u0001X\u0082\u000e¢\u0006\u0002\n\u0000\"\u0015\u0010\u0002\u001a\u00020\u0001*\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"_flipCameraIos", "Landroidx/compose/ui/graphics/vector/ImageVector;", "FlipCameraIos", "Landroidx/compose/material/icons/Icons$Filled;", "getFlipCameraIos", "(Landroidx/compose/material/icons/Icons$Filled;)Landroidx/compose/ui/graphics/vector/ImageVector;", "material-icons-extended_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class FlipCameraIosKt {
    private static ImageVector _flipCameraIos;

    public static final ImageVector getFlipCameraIos(Icons.Filled filled) {
        ImageVector imageVector = _flipCameraIos;
        if (imageVector != null) {
            return imageVector;
        }
        ImageVector.Builder builder = new ImageVector.Builder("Filled.FlipCameraIos", Dp.m5678constructorimpl(24.0f), Dp.m5678constructorimpl(24.0f), 24.0f, 24.0f, 0L, 0, false, 96, null);
        int defaultFillType = VectorKt.getDefaultFillType();
        SolidColor solidColor = new SolidColor(Color.INSTANCE.m3498getBlack0d7_KjU(), null);
        int iM3803getButtKaPHkGw = StrokeCap.INSTANCE.m3803getButtKaPHkGw();
        int iM3813getBevelLxFBmk8 = StrokeJoin.INSTANCE.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderI = e.i(20.0f, 5.0f, -3.17f, 15.0f, 3.0f);
        b.q(pathBuilderI, 9.0f, 7.17f, 5.0f, 4.0f);
        pathBuilderI.curveTo(2.9f, 5.0f, 2.0f, 5.9f, 2.0f, 7.0f);
        pathBuilderI.verticalLineToRelative(12.0f);
        pathBuilderI.curveToRelative(0.0f, 1.1f, 0.9f, 2.0f, 2.0f, 2.0f);
        pathBuilderI.horizontalLineToRelative(16.0f);
        pathBuilderI.curveToRelative(1.1f, 0.0f, 2.0f, -0.9f, 2.0f, -2.0f);
        pathBuilderI.verticalLineTo(7.0f);
        pathBuilderI.curveTo(22.0f, 5.9f, 21.1f, 5.0f, 20.0f, 5.0f);
        pathBuilderI.close();
        pathBuilderI.moveTo(12.0f, 18.0f);
        pathBuilderI.curveToRelative(-2.76f, 0.0f, -5.0f, -2.24f, -5.0f, -5.0f);
        pathBuilderI.horizontalLineTo(5.0f);
        pathBuilderI.lineToRelative(2.5f, -2.5f);
        pathBuilderI.lineTo(10.0f, 13.0f);
        pathBuilderI.horizontalLineTo(8.0f);
        pathBuilderI.curveToRelative(0.0f, 2.21f, 1.79f, 4.0f, 4.0f, 4.0f);
        pathBuilderI.curveToRelative(0.58f, 0.0f, 1.13f, -0.13f, 1.62f, -0.35f);
        pathBuilderI.lineToRelative(0.74f, 0.74f);
        pathBuilderI.curveTo(13.65f, 17.76f, 12.86f, 18.0f, 12.0f, 18.0f);
        a.p(pathBuilderI, 16.5f, 15.5f, 14.0f, 13.0f);
        pathBuilderI.horizontalLineToRelative(2.0f);
        pathBuilderI.curveToRelative(0.0f, -2.21f, -1.79f, -4.0f, -4.0f, -4.0f);
        pathBuilderI.curveToRelative(-0.58f, 0.0f, -1.13f, 0.13f, -1.62f, 0.35f);
        pathBuilderI.lineTo(9.64f, 8.62f);
        pathBuilderI.curveTo(10.35f, 8.24f, 11.14f, 8.0f, 12.0f, 8.0f);
        pathBuilderI.curveToRelative(2.76f, 0.0f, 5.0f, 2.24f, 5.0f, 5.0f);
        ImageVector imageVectorBuild = ImageVector.Builder.m4024addPathoIyEayM$default(builder, w.a.h(pathBuilderI, 2.0f, 16.5f, 15.5f), defaultFillType, "", solidColor, 1.0f, null, 1.0f, 1.0f, iM3803getButtKaPHkGw, iM3813getBevelLxFBmk8, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null).build();
        _flipCameraIos = imageVectorBuild;
        return imageVectorBuild;
    }
}
