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
import v.b;
import v.c;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\"\u0010\u0010\u0000\u001a\u0004\u0018\u00010\u0001X\u0082\u000e¢\u0006\u0002\n\u0000\"\u0015\u0010\u0002\u001a\u00020\u0001*\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"_monochromePhotos", "Landroidx/compose/ui/graphics/vector/ImageVector;", "MonochromePhotos", "Landroidx/compose/material/icons/Icons$Rounded;", "getMonochromePhotos", "(Landroidx/compose/material/icons/Icons$Rounded;)Landroidx/compose/ui/graphics/vector/ImageVector;", "material-icons-extended_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class MonochromePhotosKt {
    private static ImageVector _monochromePhotos;

    public static final ImageVector getMonochromePhotos(Icons.Rounded rounded) {
        ImageVector imageVector = _monochromePhotos;
        if (imageVector != null) {
            return imageVector;
        }
        ImageVector.Builder builder = new ImageVector.Builder("Rounded.MonochromePhotos", Dp.m5678constructorimpl(24.0f), Dp.m5678constructorimpl(24.0f), 24.0f, 24.0f, 0L, 0, false, 96, null);
        int defaultFillType = VectorKt.getDefaultFillType();
        SolidColor solidColor = new SolidColor(Color.INSTANCE.m3498getBlack0d7_KjU(), null);
        int iM3803getButtKaPHkGw = StrokeCap.INSTANCE.m3803getButtKaPHkGw();
        int iM3813getBevelLxFBmk8 = StrokeJoin.INSTANCE.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderG = c.g(20.0f, 5.0f, -3.2f, -1.2f, -1.34f);
        pathBuilderG.curveToRelative(-0.38f, -0.42f, -0.92f, -0.66f, -1.49f, -0.66f);
        pathBuilderG.lineTo(9.89f, 3.0f);
        pathBuilderG.curveToRelative(-0.57f, 0.0f, -1.11f, 0.24f, -1.49f, 0.66f);
        pathBuilderG.lineTo(7.2f, 5.0f);
        pathBuilderG.lineTo(4.0f, 5.0f);
        pathBuilderG.curveToRelative(-1.1f, 0.0f, -2.0f, 0.9f, -2.0f, 2.0f);
        pathBuilderG.verticalLineToRelative(12.0f);
        pathBuilderG.curveToRelative(0.0f, 1.1f, 0.9f, 2.0f, 2.0f, 2.0f);
        pathBuilderG.horizontalLineToRelative(16.0f);
        pathBuilderG.curveToRelative(1.1f, 0.0f, 2.0f, -0.9f, 2.0f, -2.0f);
        pathBuilderG.lineTo(22.0f, 7.0f);
        pathBuilderG.curveToRelative(0.0f, -1.1f, -0.9f, -2.0f, -2.0f, -2.0f);
        pathBuilderG.close();
        pathBuilderG.moveTo(20.0f, 18.0f);
        pathBuilderG.curveToRelative(0.0f, 0.55f, -0.45f, 1.0f, -1.0f, 1.0f);
        pathBuilderG.horizontalLineToRelative(-7.0f);
        pathBuilderG.verticalLineToRelative(-1.0f);
        pathBuilderG.curveToRelative(-2.8f, 0.0f, -5.0f, -2.2f, -5.0f, -5.0f);
        pathBuilderG.reflectiveCurveToRelative(2.2f, -5.0f, 5.0f, -5.0f);
        pathBuilderG.lineTo(12.0f, 7.0f);
        pathBuilderG.horizontalLineToRelative(7.0f);
        pathBuilderG.curveToRelative(0.55f, 0.0f, 1.0f, 0.45f, 1.0f, 1.0f);
        b.f(pathBuilderG, 10.0f, 17.0f, 13.0f);
        pathBuilderG.curveToRelative(0.0f, -2.8f, -2.2f, -5.0f, -5.0f, -5.0f);
        pathBuilderG.verticalLineToRelative(1.8f);
        pathBuilderG.curveToRelative(1.8f, 0.0f, 3.2f, 1.4f, 3.2f, 3.2f);
        pathBuilderG.reflectiveCurveToRelative(-1.4f, 3.2f, -3.2f, 3.2f);
        pathBuilderG.lineTo(12.0f, 18.0f);
        pathBuilderG.curveToRelative(2.8f, 0.0f, 5.0f, -2.2f, 5.0f, -5.0f);
        pathBuilderG.close();
        pathBuilderG.moveTo(8.8f, 13.0f);
        pathBuilderG.curveToRelative(0.0f, 1.8f, 1.4f, 3.2f, 3.2f, 3.2f);
        pathBuilderG.lineTo(12.0f, 9.8f);
        pathBuilderG.curveToRelative(-1.8f, 0.0f, -3.2f, 1.4f, -3.2f, 3.2f);
        pathBuilderG.close();
        ImageVector imageVectorBuild = ImageVector.Builder.m4024addPathoIyEayM$default(builder, pathBuilderG.getNodes(), defaultFillType, "", solidColor, 1.0f, null, 1.0f, 1.0f, iM3803getButtKaPHkGw, iM3813getBevelLxFBmk8, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null).build();
        _monochromePhotos = imageVectorBuild;
        return imageVectorBuild;
    }
}
