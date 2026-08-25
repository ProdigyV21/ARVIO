package androidx.compose.material.icons.filled;

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
import v.d;
import v.e;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\"\u0010\u0010\u0000\u001a\u0004\u0018\u00010\u0001X\u0082\u000e¢\u0006\u0002\n\u0000\"\u0015\u0010\u0002\u001a\u00020\u0001*\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"_imageSearch", "Landroidx/compose/ui/graphics/vector/ImageVector;", "ImageSearch", "Landroidx/compose/material/icons/Icons$Filled;", "getImageSearch", "(Landroidx/compose/material/icons/Icons$Filled;)Landroidx/compose/ui/graphics/vector/ImageVector;", "material-icons-extended_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class ImageSearchKt {
    private static ImageVector _imageSearch;

    public static final ImageVector getImageSearch(Icons.Filled filled) {
        ImageVector imageVector = _imageSearch;
        if (imageVector != null) {
            return imageVector;
        }
        ImageVector.Builder builder = new ImageVector.Builder("Filled.ImageSearch", Dp.m5678constructorimpl(24.0f), Dp.m5678constructorimpl(24.0f), 24.0f, 24.0f, 0L, 0, false, 96, null);
        int defaultFillType = VectorKt.getDefaultFillType();
        SolidColor solidColor = new SolidColor(Color.INSTANCE.m3498getBlack0d7_KjU(), null);
        int iM3803getButtKaPHkGw = StrokeCap.INSTANCE.m3803getButtKaPHkGw();
        int iM3813getBevelLxFBmk8 = StrokeJoin.INSTANCE.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderS = d.s(18.0f, 13.0f, 7.0f, 4.0f, 20.0f);
        pathBuilderS.lineTo(4.0f, 6.0f);
        pathBuilderS.horizontalLineToRelative(5.02f);
        pathBuilderS.curveToRelative(0.05f, -0.71f, 0.22f, -1.38f, 0.48f, -2.0f);
        pathBuilderS.lineTo(4.0f, 4.0f);
        pathBuilderS.curveToRelative(-1.1f, 0.0f, -2.0f, 0.9f, -2.0f, 2.0f);
        pathBuilderS.verticalLineToRelative(14.0f);
        pathBuilderS.curveToRelative(0.0f, 1.1f, 0.9f, 2.0f, 2.0f, 2.0f);
        pathBuilderS.horizontalLineToRelative(14.0f);
        pathBuilderS.curveToRelative(1.1f, 0.0f, 2.0f, -0.9f, 2.0f, -2.0f);
        c.t(pathBuilderS, -5.0f, -2.0f, -2.0f);
        pathBuilderS.moveTo(16.5f, 18.0f);
        pathBuilderS.horizontalLineToRelative(-11.0f);
        pathBuilderS.lineToRelative(2.75f, -3.53f);
        b.D(pathBuilderS, 1.96f, 2.36f, 2.75f, -3.54f);
        pathBuilderS.moveTo(19.3f, 8.89f);
        pathBuilderS.curveToRelative(0.44f, -0.7f, 0.7f, -1.51f, 0.7f, -2.39f);
        pathBuilderS.curveTo(20.0f, 4.01f, 17.99f, 2.0f, 15.5f, 2.0f);
        pathBuilderS.reflectiveCurveTo(11.0f, 4.01f, 11.0f, 6.5f);
        pathBuilderS.reflectiveCurveToRelative(2.01f, 4.5f, 4.49f, 4.5f);
        pathBuilderS.curveToRelative(0.88f, 0.0f, 1.7f, -0.26f, 2.39f, -0.7f);
        pathBuilderS.lineTo(21.0f, 13.42f);
        b.y(pathBuilderS, 22.42f, 12.0f, 19.3f, 8.89f);
        pathBuilderS.moveTo(15.5f, 9.0f);
        pathBuilderS.curveTo(14.12f, 9.0f, 13.0f, 7.88f, 13.0f, 6.5f);
        pathBuilderS.reflectiveCurveTo(14.12f, 4.0f, 15.5f, 4.0f);
        pathBuilderS.reflectiveCurveTo(18.0f, 5.12f, 18.0f, 6.5f);
        ImageVector imageVectorBuild = ImageVector.Builder.m4024addPathoIyEayM$default(builder, e.u(pathBuilderS, 16.88f, 9.0f, 15.5f, 9.0f), defaultFillType, "", solidColor, 1.0f, null, 1.0f, 1.0f, iM3803getButtKaPHkGw, iM3813getBevelLxFBmk8, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null).build();
        _imageSearch = imageVectorBuild;
        return imageVectorBuild;
    }
}
