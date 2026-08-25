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
import v.a;
import v.c;
import v.d;
import v.e;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\"\u0010\u0010\u0000\u001a\u0004\u0018\u00010\u0001X\u0082\u000e¢\u0006\u0002\n\u0000\"\u0015\u0010\u0002\u001a\u00020\u0001*\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"_videogameAssetOff", "Landroidx/compose/ui/graphics/vector/ImageVector;", "VideogameAssetOff", "Landroidx/compose/material/icons/Icons$Filled;", "getVideogameAssetOff", "(Landroidx/compose/material/icons/Icons$Filled;)Landroidx/compose/ui/graphics/vector/ImageVector;", "material-icons-extended_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class VideogameAssetOffKt {
    private static ImageVector _videogameAssetOff;

    public static final ImageVector getVideogameAssetOff(Icons.Filled filled) {
        ImageVector imageVector = _videogameAssetOff;
        if (imageVector != null) {
            return imageVector;
        }
        ImageVector.Builder builder = new ImageVector.Builder("Filled.VideogameAssetOff", Dp.m5678constructorimpl(24.0f), Dp.m5678constructorimpl(24.0f), 24.0f, 24.0f, 0L, 0, false, 96, null);
        int defaultFillType = VectorKt.getDefaultFillType();
        SolidColor solidColor = new SolidColor(Color.INSTANCE.m3498getBlack0d7_KjU(), null);
        int iM3803getButtKaPHkGw = StrokeCap.INSTANCE.m3803getButtKaPHkGw();
        int iM3813getBevelLxFBmk8 = StrokeJoin.INSTANCE.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderJ = a.j(21.19f, 21.19f, 2.81f, 2.81f);
        pathBuilderJ.lineTo(1.39f, 4.22f);
        pathBuilderJ.lineTo(3.3f, 6.13f);
        pathBuilderJ.curveTo(2.54f, 6.41f, 2.0f, 7.15f, 2.0f, 8.0f);
        pathBuilderJ.verticalLineToRelative(8.0f);
        pathBuilderJ.curveToRelative(0.0f, 1.1f, 0.9f, 2.0f, 2.0f, 2.0f);
        pathBuilderJ.horizontalLineToRelative(11.17f);
        d.C(pathBuilderJ, 4.61f, 4.61f, 21.19f, 21.19f);
        c.u(pathBuilderJ, 9.0f, 13.0f, 2.0f, 7.0f);
        a0.a.A(pathBuilderJ, -2.0f, 5.0f, -2.0f, 2.0f);
        pathBuilderJ.verticalLineTo(9.83f);
        pathBuilderJ.lineTo(10.17f, 13.0f);
        pathBuilderJ.horizontalLineTo(9.0f);
        pathBuilderJ.close();
        pathBuilderJ.moveTo(20.7f, 17.87f);
        pathBuilderJ.curveTo(21.46f, 17.59f, 22.0f, 16.85f, 22.0f, 16.0f);
        pathBuilderJ.verticalLineTo(8.0f);
        pathBuilderJ.curveToRelative(0.0f, -1.1f, -0.9f, -2.0f, -2.0f, -2.0f);
        w.a.w(pathBuilderJ, 8.83f, 20.7f, 17.87f);
        pathBuilderJ.moveTo(17.5f, 9.0f);
        pathBuilderJ.curveToRelative(0.83f, 0.0f, 1.5f, 0.67f, 1.5f, 1.5f);
        pathBuilderJ.reflectiveCurveTo(18.33f, 12.0f, 17.5f, 12.0f);
        pathBuilderJ.reflectiveCurveTo(16.0f, 11.33f, 16.0f, 10.5f);
        ImageVector imageVectorBuild = ImageVector.Builder.m4024addPathoIyEayM$default(builder, e.u(pathBuilderJ, 16.67f, 9.0f, 17.5f, 9.0f), defaultFillType, "", solidColor, 1.0f, null, 1.0f, 1.0f, iM3803getButtKaPHkGw, iM3813getBevelLxFBmk8, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null).build();
        _videogameAssetOff = imageVectorBuild;
        return imageVectorBuild;
    }
}
