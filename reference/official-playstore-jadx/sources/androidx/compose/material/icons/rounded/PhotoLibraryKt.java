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
import v.d;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\"\u0010\u0010\u0000\u001a\u0004\u0018\u00010\u0001X\u0082\u000e¢\u0006\u0002\n\u0000\"\u0015\u0010\u0002\u001a\u00020\u0001*\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"_photoLibrary", "Landroidx/compose/ui/graphics/vector/ImageVector;", "PhotoLibrary", "Landroidx/compose/material/icons/Icons$Rounded;", "getPhotoLibrary", "(Landroidx/compose/material/icons/Icons$Rounded;)Landroidx/compose/ui/graphics/vector/ImageVector;", "material-icons-extended_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class PhotoLibraryKt {
    private static ImageVector _photoLibrary;

    public static final ImageVector getPhotoLibrary(Icons.Rounded rounded) {
        ImageVector imageVector = _photoLibrary;
        if (imageVector != null) {
            return imageVector;
        }
        ImageVector.Builder builder = new ImageVector.Builder("Rounded.PhotoLibrary", Dp.m5678constructorimpl(24.0f), Dp.m5678constructorimpl(24.0f), 24.0f, 24.0f, 0L, 0, false, 96, null);
        int defaultFillType = VectorKt.getDefaultFillType();
        SolidColor solidColor = new SolidColor(Color.INSTANCE.m3498getBlack0d7_KjU(), null);
        int iM3803getButtKaPHkGw = StrokeCap.INSTANCE.m3803getButtKaPHkGw();
        int iM3813getBevelLxFBmk8 = StrokeJoin.INSTANCE.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderJ = a.j(22.0f, 16.0f, 22.0f, 4.0f);
        pathBuilderJ.curveToRelative(0.0f, -1.1f, -0.9f, -2.0f, -2.0f, -2.0f);
        pathBuilderJ.lineTo(8.0f, 2.0f);
        pathBuilderJ.curveToRelative(-1.1f, 0.0f, -2.0f, 0.9f, -2.0f, 2.0f);
        pathBuilderJ.verticalLineToRelative(12.0f);
        pathBuilderJ.curveToRelative(0.0f, 1.1f, 0.9f, 2.0f, 2.0f, 2.0f);
        pathBuilderJ.horizontalLineToRelative(12.0f);
        pathBuilderJ.curveToRelative(1.1f, 0.0f, 2.0f, -0.9f, 2.0f, -2.0f);
        a.z(pathBuilderJ, 11.4f, 12.53f, 1.63f, 2.18f);
        pathBuilderJ.lineToRelative(2.58f, -3.22f);
        pathBuilderJ.curveToRelative(0.2f, -0.25f, 0.58f, -0.25f, 0.78f, 0.0f);
        pathBuilderJ.lineToRelative(2.96f, 3.7f);
        pathBuilderJ.curveToRelative(0.26f, 0.33f, 0.03f, 0.81f, -0.39f, 0.81f);
        pathBuilderJ.lineTo(9.0f, 16.0f);
        pathBuilderJ.curveToRelative(-0.41f, 0.0f, -0.65f, -0.47f, -0.4f, -0.8f);
        pathBuilderJ.lineToRelative(2.0f, -2.67f);
        pathBuilderJ.curveToRelative(0.2f, -0.26f, 0.6f, -0.26f, 0.8f, 0.0f);
        d.k(pathBuilderJ, 2.0f, 7.0f, 13.0f);
        pathBuilderJ.curveToRelative(0.0f, 1.1f, 0.9f, 2.0f, 2.0f, 2.0f);
        pathBuilderJ.horizontalLineToRelative(13.0f);
        pathBuilderJ.curveToRelative(0.55f, 0.0f, 1.0f, -0.45f, 1.0f, -1.0f);
        pathBuilderJ.reflectiveCurveToRelative(-0.45f, -1.0f, -1.0f, -1.0f);
        pathBuilderJ.lineTo(5.0f, 20.0f);
        pathBuilderJ.curveToRelative(-0.55f, 0.0f, -1.0f, -0.45f, -1.0f, -1.0f);
        pathBuilderJ.lineTo(4.0f, 7.0f);
        pathBuilderJ.curveToRelative(0.0f, -0.55f, -0.45f, -1.0f, -1.0f, -1.0f);
        ImageVector imageVectorBuild = ImageVector.Builder.m4024addPathoIyEayM$default(builder, c.h(pathBuilderJ, -1.0f, 0.45f, -1.0f, 1.0f), defaultFillType, "", solidColor, 1.0f, null, 1.0f, 1.0f, iM3803getButtKaPHkGw, iM3813getBevelLxFBmk8, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null).build();
        _photoLibrary = imageVectorBuild;
        return imageVectorBuild;
    }
}
