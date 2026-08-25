package androidx.compose.material.icons.outlined;

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
import v.d;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\"\u0010\u0010\u0000\u001a\u0004\u0018\u00010\u0001X\u0082\u000e¢\u0006\u0002\n\u0000\"\u0015\u0010\u0002\u001a\u00020\u0001*\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"_imageNotSupported", "Landroidx/compose/ui/graphics/vector/ImageVector;", "ImageNotSupported", "Landroidx/compose/material/icons/Icons$Outlined;", "getImageNotSupported", "(Landroidx/compose/material/icons/Icons$Outlined;)Landroidx/compose/ui/graphics/vector/ImageVector;", "material-icons-extended_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class ImageNotSupportedKt {
    private static ImageVector _imageNotSupported;

    public static final ImageVector getImageNotSupported(Icons.Outlined outlined) {
        ImageVector imageVector = _imageNotSupported;
        if (imageVector != null) {
            return imageVector;
        }
        ImageVector.Builder builder = new ImageVector.Builder("Outlined.ImageNotSupported", Dp.m5678constructorimpl(24.0f), Dp.m5678constructorimpl(24.0f), 24.0f, 24.0f, 0L, 0, false, 96, null);
        int defaultFillType = VectorKt.getDefaultFillType();
        SolidColor solidColor = new SolidColor(Color.INSTANCE.m3498getBlack0d7_KjU(), null);
        int iM3803getButtKaPHkGw = StrokeCap.INSTANCE.m3803getButtKaPHkGw();
        int iM3813getBevelLxFBmk8 = StrokeJoin.INSTANCE.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderR = a.r(21.9f, 21.9f, -6.1f, -6.1f);
        pathBuilderR.lineToRelative(-2.69f, -2.69f);
        pathBuilderR.lineToRelative(0.0f, 0.0f);
        pathBuilderR.lineTo(5.0f, 5.0f);
        pathBuilderR.lineToRelative(0.0f, 0.0f);
        pathBuilderR.lineTo(3.59f, 3.59f);
        pathBuilderR.lineToRelative(0.0f, 0.0f);
        pathBuilderR.lineTo(2.1f, 2.1f);
        pathBuilderR.lineTo(0.69f, 3.51f);
        pathBuilderR.lineTo(3.0f, 5.83f);
        pathBuilderR.verticalLineTo(19.0f);
        pathBuilderR.curveToRelative(0.0f, 1.1f, 0.9f, 2.0f, 2.0f, 2.0f);
        pathBuilderR.horizontalLineToRelative(13.17f);
        d.C(pathBuilderR, 2.31f, 2.31f, 21.9f, 21.9f);
        pathBuilderR.moveTo(5.0f, 19.0f);
        pathBuilderR.verticalLineTo(7.83f);
        pathBuilderR.lineToRelative(6.84f, 6.84f);
        pathBuilderR.lineTo(11.0f, 15.72f);
        pathBuilderR.lineTo(9.0f, 13.0f);
        pathBuilderR.lineToRelative(-3.0f, 4.0f);
        b.B(pathBuilderR, 8.17f, 2.0f, 2.0f, 5.0f);
        pathBuilderR.moveTo(7.83f, 5.0f);
        pathBuilderR.lineToRelative(-2.0f, -2.0f);
        pathBuilderR.horizontalLineTo(19.0f);
        pathBuilderR.curveToRelative(1.1f, 0.0f, 2.0f, 0.9f, 2.0f, 2.0f);
        a0.a.l(pathBuilderR, 13.17f, -2.0f, -2.0f, 5.0f);
        ImageVector imageVectorBuild = ImageVector.Builder.m4024addPathoIyEayM$default(builder, a.l(pathBuilderR, 7.83f), defaultFillType, "", solidColor, 1.0f, null, 1.0f, 1.0f, iM3803getButtKaPHkGw, iM3813getBevelLxFBmk8, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null).build();
        _imageNotSupported = imageVectorBuild;
        return imageVectorBuild;
    }
}
