package androidx.compose.material.icons.sharp;

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
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\"\u0010\u0010\u0000\u001a\u0004\u0018\u00010\u0001X\u0082\u000e¢\u0006\u0002\n\u0000\"\u0015\u0010\u0002\u001a\u00020\u0001*\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"_locationOff", "Landroidx/compose/ui/graphics/vector/ImageVector;", "LocationOff", "Landroidx/compose/material/icons/Icons$Sharp;", "getLocationOff", "(Landroidx/compose/material/icons/Icons$Sharp;)Landroidx/compose/ui/graphics/vector/ImageVector;", "material-icons-extended_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class LocationOffKt {
    private static ImageVector _locationOff;

    public static final ImageVector getLocationOff(Icons.Sharp sharp) {
        ImageVector imageVector = _locationOff;
        if (imageVector != null) {
            return imageVector;
        }
        ImageVector.Builder builder = new ImageVector.Builder("Sharp.LocationOff", Dp.m5678constructorimpl(24.0f), Dp.m5678constructorimpl(24.0f), 24.0f, 24.0f, 0L, 0, false, 96, null);
        int defaultFillType = VectorKt.getDefaultFillType();
        SolidColor solidColor = new SolidColor(Color.INSTANCE.m3498getBlack0d7_KjU(), null);
        int iM3803getButtKaPHkGw = StrokeCap.INSTANCE.m3803getButtKaPHkGw();
        int iM3813getBevelLxFBmk8 = StrokeJoin.INSTANCE.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderJ = a.j(3.41f, 2.86f, 2.0f, 4.27f);
        pathBuilderJ.lineToRelative(3.18f, 3.18f);
        pathBuilderJ.curveTo(5.07f, 7.95f, 5.0f, 8.47f, 5.0f, 9.0f);
        pathBuilderJ.curveToRelative(0.0f, 5.25f, 7.0f, 13.0f, 7.0f, 13.0f);
        pathBuilderJ.reflectiveCurveToRelative(1.67f, -1.85f, 3.38f, -4.35f);
        pathBuilderJ.lineTo(18.73f, 21.0f);
        d.C(pathBuilderJ, 1.41f, -1.41f, 3.41f, 2.86f);
        pathBuilderJ.moveTo(12.0f, 2.0f);
        pathBuilderJ.curveToRelative(-1.84f, 0.0f, -3.5f, 0.71f, -4.75f, 1.86f);
        pathBuilderJ.lineToRelative(3.19f, 3.19f);
        pathBuilderJ.curveToRelative(0.43f, -0.34f, 0.97f, -0.55f, 1.56f, -0.55f);
        pathBuilderJ.curveToRelative(1.38f, 0.0f, 2.5f, 1.12f, 2.5f, 2.5f);
        pathBuilderJ.curveToRelative(0.0f, 0.59f, -0.21f, 1.13f, -0.56f, 1.56f);
        pathBuilderJ.lineToRelative(3.55f, 3.55f);
        pathBuilderJ.curveTo(18.37f, 12.36f, 19.0f, 10.57f, 19.0f, 9.0f);
        pathBuilderJ.curveToRelative(0.0f, -3.87f, -3.13f, -7.0f, -7.0f, -7.0f);
        pathBuilderJ.close();
        ImageVector imageVectorBuild = ImageVector.Builder.m4024addPathoIyEayM$default(builder, pathBuilderJ.getNodes(), defaultFillType, "", solidColor, 1.0f, null, 1.0f, 1.0f, iM3803getButtKaPHkGw, iM3813getBevelLxFBmk8, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null).build();
        _locationOff = imageVectorBuild;
        return imageVectorBuild;
    }
}
