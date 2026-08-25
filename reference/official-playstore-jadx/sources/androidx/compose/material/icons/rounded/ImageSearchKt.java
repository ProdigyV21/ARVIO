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
import v.e;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\"\u0010\u0010\u0000\u001a\u0004\u0018\u00010\u0001X\u0082\u000e¢\u0006\u0002\n\u0000\"\u0015\u0010\u0002\u001a\u00020\u0001*\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"_imageSearch", "Landroidx/compose/ui/graphics/vector/ImageVector;", "ImageSearch", "Landroidx/compose/material/icons/Icons$Rounded;", "getImageSearch", "(Landroidx/compose/material/icons/Icons$Rounded;)Landroidx/compose/ui/graphics/vector/ImageVector;", "material-icons-extended_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class ImageSearchKt {
    private static ImageVector _imageSearch;

    public static final ImageVector getImageSearch(Icons.Rounded rounded) {
        ImageVector imageVector = _imageSearch;
        if (imageVector != null) {
            return imageVector;
        }
        ImageVector.Builder builder = new ImageVector.Builder("Rounded.ImageSearch", Dp.m5678constructorimpl(24.0f), Dp.m5678constructorimpl(24.0f), 24.0f, 24.0f, 0L, 0, false, 96, null);
        int defaultFillType = VectorKt.getDefaultFillType();
        SolidColor solidColor = new SolidColor(Color.INSTANCE.m3498getBlack0d7_KjU(), null);
        int iM3803getButtKaPHkGw = StrokeCap.INSTANCE.m3803getButtKaPHkGw();
        int iM3813getBevelLxFBmk8 = StrokeJoin.INSTANCE.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderI = a.i(18.0f, 15.0f, 4.0f);
        pathBuilderI.curveToRelative(0.0f, 0.55f, -0.45f, 1.0f, -1.0f, 1.0f);
        pathBuilderI.lineTo(5.0f, 20.0f);
        pathBuilderI.curveToRelative(-0.55f, 0.0f, -1.0f, -0.45f, -1.0f, -1.0f);
        pathBuilderI.lineTo(4.0f, 7.0f);
        pathBuilderI.curveToRelative(0.0f, -0.55f, 0.45f, -1.0f, 1.0f, -1.0f);
        pathBuilderI.horizontalLineToRelative(3.02f);
        pathBuilderI.curveToRelative(0.55f, 0.0f, 1.0f, -0.45f, 1.0f, -1.0f);
        pathBuilderI.reflectiveCurveToRelative(-0.45f, -1.0f, -1.0f, -1.0f);
        pathBuilderI.lineTo(4.0f, 4.0f);
        pathBuilderI.curveToRelative(-1.1f, 0.0f, -2.0f, 0.9f, -2.0f, 2.0f);
        pathBuilderI.verticalLineToRelative(14.0f);
        pathBuilderI.curveToRelative(0.0f, 1.1f, 0.9f, 2.0f, 2.0f, 2.0f);
        pathBuilderI.horizontalLineToRelative(14.0f);
        pathBuilderI.curveToRelative(1.1f, 0.0f, 2.0f, -0.9f, 2.0f, -2.0f);
        pathBuilderI.verticalLineToRelative(-5.0f);
        pathBuilderI.curveToRelative(0.0f, -0.55f, -0.45f, -1.0f, -1.0f, -1.0f);
        pathBuilderI.reflectiveCurveToRelative(-1.0f, 0.45f, -1.0f, 1.0f);
        a.p(pathBuilderI, 15.5f, 18.0f, 6.52f, 18.0f);
        pathBuilderI.curveToRelative(-0.42f, 0.0f, -0.65f, -0.48f, -0.39f, -0.81f);
        pathBuilderI.lineToRelative(1.74f, -2.23f);
        pathBuilderI.curveToRelative(0.2f, -0.25f, 0.58f, -0.26f, 0.78f, -0.01f);
        pathBuilderI.lineToRelative(1.56f, 1.88f);
        pathBuilderI.lineToRelative(2.35f, -3.02f);
        pathBuilderI.curveToRelative(0.2f, -0.26f, 0.6f, -0.26f, 0.79f, 0.01f);
        pathBuilderI.lineToRelative(2.55f, 3.39f);
        pathBuilderI.curveToRelative(0.25f, 0.32f, 0.01f, 0.79f, -0.4f, 0.79f);
        pathBuilderI.close();
        pathBuilderI.moveTo(19.3f, 8.89f);
        pathBuilderI.curveToRelative(0.48f, -0.77f, 0.75f, -1.67f, 0.69f, -2.66f);
        pathBuilderI.curveToRelative(-0.13f, -2.15f, -1.84f, -3.97f, -3.97f, -4.2f);
        pathBuilderI.curveTo(13.3f, 1.73f, 11.0f, 3.84f, 11.0f, 6.5f);
        pathBuilderI.curveToRelative(0.0f, 2.49f, 2.01f, 4.5f, 4.49f, 4.5f);
        pathBuilderI.curveToRelative(0.88f, 0.0f, 1.7f, -0.26f, 2.39f, -0.7f);
        pathBuilderI.lineToRelative(2.41f, 2.41f);
        pathBuilderI.curveToRelative(0.39f, 0.39f, 1.03f, 0.39f, 1.42f, 0.0f);
        pathBuilderI.curveToRelative(0.39f, -0.39f, 0.39f, -1.03f, 0.0f, -1.42f);
        b.C(pathBuilderI, -2.41f, -2.4f, 15.5f, 9.0f);
        pathBuilderI.curveTo(14.12f, 9.0f, 13.0f, 7.88f, 13.0f, 6.5f);
        pathBuilderI.reflectiveCurveTo(14.12f, 4.0f, 15.5f, 4.0f);
        pathBuilderI.reflectiveCurveTo(18.0f, 5.12f, 18.0f, 6.5f);
        ImageVector imageVectorBuild = ImageVector.Builder.m4024addPathoIyEayM$default(builder, e.u(pathBuilderI, 16.88f, 9.0f, 15.5f, 9.0f), defaultFillType, "", solidColor, 1.0f, null, 1.0f, 1.0f, iM3803getButtKaPHkGw, iM3813getBevelLxFBmk8, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null).build();
        _imageSearch = imageVectorBuild;
        return imageVectorBuild;
    }
}
