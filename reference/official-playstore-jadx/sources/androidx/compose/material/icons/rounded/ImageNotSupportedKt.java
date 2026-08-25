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
import v.b;
import v.f;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\"\u0010\u0010\u0000\u001a\u0004\u0018\u00010\u0001X\u0082\u000e¢\u0006\u0002\n\u0000\"\u0015\u0010\u0002\u001a\u00020\u0001*\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"_imageNotSupported", "Landroidx/compose/ui/graphics/vector/ImageVector;", "ImageNotSupported", "Landroidx/compose/material/icons/Icons$Rounded;", "getImageNotSupported", "(Landroidx/compose/material/icons/Icons$Rounded;)Landroidx/compose/ui/graphics/vector/ImageVector;", "material-icons-extended_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class ImageNotSupportedKt {
    private static ImageVector _imageNotSupported;

    public static final ImageVector getImageNotSupported(Icons.Rounded rounded) {
        ImageVector imageVector = _imageNotSupported;
        if (imageVector != null) {
            return imageVector;
        }
        ImageVector.Builder builder = new ImageVector.Builder("Rounded.ImageNotSupported", Dp.m5678constructorimpl(24.0f), Dp.m5678constructorimpl(24.0f), 24.0f, 24.0f, 0L, 0, false, 96, null);
        int defaultFillType = VectorKt.getDefaultFillType();
        SolidColor solidColor = new SolidColor(Color.INSTANCE.m3498getBlack0d7_KjU(), null);
        int iM3803getButtKaPHkGw = StrokeCap.INSTANCE.m3803getButtKaPHkGw();
        int iM3813getBevelLxFBmk8 = StrokeJoin.INSTANCE.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderR = a.r(21.19f, 21.19f, -0.78f, -0.78f);
        pathBuilderR.lineTo(18.0f, 18.0f);
        pathBuilderR.lineToRelative(-4.59f, -4.59f);
        pathBuilderR.lineTo(3.59f, 3.59f);
        pathBuilderR.verticalLineToRelative(0.0f);
        pathBuilderR.lineTo(2.81f, 2.81f);
        pathBuilderR.curveToRelative(-0.39f, -0.39f, -1.02f, -0.39f, -1.41f, 0.0f);
        pathBuilderR.curveTo(1.0f, 3.2f, 1.0f, 3.83f, 1.39f, 4.22f);
        pathBuilderR.lineTo(3.0f, 5.83f);
        pathBuilderR.verticalLineTo(19.0f);
        pathBuilderR.curveToRelative(0.0f, 1.1f, 0.9f, 2.0f, 2.0f, 2.0f);
        pathBuilderR.horizontalLineToRelative(13.17f);
        pathBuilderR.lineToRelative(1.61f, 1.61f);
        pathBuilderR.curveToRelative(0.39f, 0.39f, 1.02f, 0.39f, 1.41f, 0.0f);
        pathBuilderR.curveTo(21.58f, 22.22f, 21.58f, 21.58f, 21.19f, 21.19f);
        pathBuilderR.close();
        pathBuilderR.moveTo(6.02f, 18.0f);
        pathBuilderR.curveToRelative(-0.42f, 0.0f, -0.65f, -0.48f, -0.39f, -0.81f);
        pathBuilderR.lineToRelative(2.49f, -3.2f);
        pathBuilderR.curveToRelative(0.2f, -0.25f, 0.58f, -0.26f, 0.78f, -0.01f);
        pathBuilderR.lineToRelative(2.1f, 2.53f);
        pathBuilderR.lineTo(12.17f, 15.0f);
        f.v(pathBuilderR, 3.0f, 3.0f, 6.02f);
        pathBuilderR.moveTo(21.0f, 18.17f);
        pathBuilderR.lineTo(5.83f, 3.0f);
        pathBuilderR.horizontalLineTo(19.0f);
        pathBuilderR.curveToRelative(1.1f, 0.0f, 2.0f, 0.9f, 2.0f, 2.0f);
        ImageVector imageVectorBuild = ImageVector.Builder.m4024addPathoIyEayM$default(builder, b.i(pathBuilderR, 18.17f), defaultFillType, "", solidColor, 1.0f, null, 1.0f, 1.0f, iM3803getButtKaPHkGw, iM3813getBevelLxFBmk8, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null).build();
        _imageNotSupported = imageVectorBuild;
        return imageVectorBuild;
    }
}
