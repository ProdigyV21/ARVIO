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
import v.c;
import w.a;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\"\u0010\u0010\u0000\u001a\u0004\u0018\u00010\u0001X\u0082\u000e¢\u0006\u0002\n\u0000\"\u0015\u0010\u0002\u001a\u00020\u0001*\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"_photoCameraBack", "Landroidx/compose/ui/graphics/vector/ImageVector;", "PhotoCameraBack", "Landroidx/compose/material/icons/Icons$Rounded;", "getPhotoCameraBack", "(Landroidx/compose/material/icons/Icons$Rounded;)Landroidx/compose/ui/graphics/vector/ImageVector;", "material-icons-extended_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class PhotoCameraBackKt {
    private static ImageVector _photoCameraBack;

    public static final ImageVector getPhotoCameraBack(Icons.Rounded rounded) {
        ImageVector imageVector = _photoCameraBack;
        if (imageVector != null) {
            return imageVector;
        }
        ImageVector.Builder builder = new ImageVector.Builder("Rounded.PhotoCameraBack", Dp.m5678constructorimpl(24.0f), Dp.m5678constructorimpl(24.0f), 24.0f, 24.0f, 0L, 0, false, 96, null);
        int defaultFillType = VectorKt.getDefaultFillType();
        SolidColor solidColor = new SolidColor(Color.INSTANCE.m3498getBlack0d7_KjU(), null);
        int iM3803getButtKaPHkGw = StrokeCap.INSTANCE.m3803getButtKaPHkGw();
        int iM3813getBevelLxFBmk8 = StrokeJoin.INSTANCE.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderG = c.g(20.0f, 5.0f, -3.17f, -1.24f, -1.35f);
        pathBuilderG.curveTo(15.22f, 3.24f, 14.68f, 3.0f, 14.12f, 3.0f);
        pathBuilderG.horizontalLineTo(9.88f);
        pathBuilderG.curveTo(9.32f, 3.0f, 8.78f, 3.24f, 8.41f, 3.65f);
        pathBuilderG.lineTo(7.17f, 5.0f);
        pathBuilderG.horizontalLineTo(4.0f);
        pathBuilderG.curveTo(2.9f, 5.0f, 2.0f, 5.9f, 2.0f, 7.0f);
        pathBuilderG.verticalLineToRelative(12.0f);
        pathBuilderG.curveToRelative(0.0f, 1.1f, 0.9f, 2.0f, 2.0f, 2.0f);
        pathBuilderG.horizontalLineToRelative(16.0f);
        pathBuilderG.curveToRelative(1.1f, 0.0f, 2.0f, -0.9f, 2.0f, -2.0f);
        pathBuilderG.verticalLineTo(7.0f);
        pathBuilderG.curveTo(22.0f, 5.9f, 21.1f, 5.0f, 20.0f, 5.0f);
        a.r(pathBuilderG, 17.0f, 17.0f, 7.0f);
        pathBuilderG.curveToRelative(-0.41f, 0.0f, -0.65f, -0.47f, -0.4f, -0.8f);
        pathBuilderG.lineToRelative(2.0f, -2.67f);
        pathBuilderG.curveToRelative(0.2f, -0.27f, 0.6f, -0.27f, 0.8f, 0.0f);
        pathBuilderG.lineTo(11.25f, 16.0f);
        pathBuilderG.lineToRelative(2.6f, -3.47f);
        pathBuilderG.curveToRelative(0.2f, -0.27f, 0.6f, -0.27f, 0.8f, 0.0f);
        pathBuilderG.lineToRelative(2.75f, 3.67f);
        pathBuilderG.curveTo(17.65f, 16.53f, 17.41f, 17.0f, 17.0f, 17.0f);
        pathBuilderG.close();
        ImageVector imageVectorBuild = ImageVector.Builder.m4024addPathoIyEayM$default(builder, pathBuilderG.getNodes(), defaultFillType, "", solidColor, 1.0f, null, 1.0f, 1.0f, iM3803getButtKaPHkGw, iM3813getBevelLxFBmk8, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null).build();
        _photoCameraBack = imageVectorBuild;
        return imageVectorBuild;
    }
}
