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
import v.d;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\"\u0010\u0010\u0000\u001a\u0004\u0018\u00010\u0001X\u0082\u000e¢\u0006\u0002\n\u0000\"\u0015\u0010\u0002\u001a\u00020\u0001*\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"_stadium", "Landroidx/compose/ui/graphics/vector/ImageVector;", "Stadium", "Landroidx/compose/material/icons/Icons$Rounded;", "getStadium", "(Landroidx/compose/material/icons/Icons$Rounded;)Landroidx/compose/ui/graphics/vector/ImageVector;", "material-icons-extended_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class StadiumKt {
    private static ImageVector _stadium;

    public static final ImageVector getStadium(Icons.Rounded rounded) {
        ImageVector imageVector = _stadium;
        if (imageVector != null) {
            return imageVector;
        }
        ImageVector.Builder builder = new ImageVector.Builder("Rounded.Stadium", Dp.m5678constructorimpl(24.0f), Dp.m5678constructorimpl(24.0f), 24.0f, 24.0f, 0L, 0, false, 96, null);
        int defaultFillType = VectorKt.getDefaultFillType();
        SolidColor solidColor = new SolidColor(Color.INSTANCE.m3498getBlack0d7_KjU(), null);
        int iM3803getButtKaPHkGw = StrokeCap.INSTANCE.m3803getButtKaPHkGw();
        int iM3813getBevelLxFBmk8 = StrokeJoin.INSTANCE.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderJ = a.j(6.11f, 5.45f, 3.72f, 6.64f);
        pathBuilderJ.curveTo(3.39f, 6.8f, 3.0f, 6.56f, 3.0f, 6.19f);
        pathBuilderJ.verticalLineTo(3.81f);
        pathBuilderJ.curveTo(3.0f, 3.44f, 3.39f, 3.2f, 3.72f, 3.36f);
        pathBuilderJ.lineToRelative(2.38f, 1.19f);
        pathBuilderJ.curveTo(6.47f, 4.74f, 6.47f, 5.26f, 6.11f, 5.45f);
        d.k(pathBuilderJ, 18.0f, 3.81f, 2.38f);
        pathBuilderJ.curveToRelative(0.0f, 0.37f, 0.39f, 0.61f, 0.72f, 0.45f);
        pathBuilderJ.lineToRelative(2.38f, -1.19f);
        pathBuilderJ.curveToRelative(0.37f, -0.18f, 0.37f, -0.71f, 0.0f, -0.89f);
        pathBuilderJ.lineToRelative(-2.38f, -1.19f);
        pathBuilderJ.curveTo(18.39f, 3.2f, 18.0f, 3.44f, 18.0f, 3.81f);
        d.k(pathBuilderJ, 11.0f, 2.81f, 2.38f);
        pathBuilderJ.curveToRelative(0.0f, 0.37f, 0.39f, 0.61f, 0.72f, 0.45f);
        pathBuilderJ.lineToRelative(2.38f, -1.19f);
        pathBuilderJ.curveToRelative(0.37f, -0.18f, 0.37f, -0.71f, 0.0f, -0.89f);
        pathBuilderJ.lineToRelative(-2.38f, -1.19f);
        pathBuilderJ.curveTo(11.39f, 2.2f, 11.0f, 2.44f, 11.0f, 2.81f);
        pathBuilderJ.close();
        pathBuilderJ.moveTo(5.0f, 10.04f);
        pathBuilderJ.curveTo(6.38f, 10.53f, 8.77f, 11.0f, 12.0f, 11.0f);
        pathBuilderJ.reflectiveCurveToRelative(5.62f, -0.47f, 7.0f, -0.96f);
        pathBuilderJ.curveTo(19.0f, 9.86f, 16.22f, 9.0f, 12.0f, 9.0f);
        pathBuilderJ.reflectiveCurveTo(5.0f, 9.86f, 5.0f, 10.04f);
        a.g(pathBuilderJ, 14.0f, 17.0f, -4.0f);
        pathBuilderJ.curveToRelative(-0.55f, 0.0f, -1.0f, 0.45f, -1.0f, 1.0f);
        pathBuilderJ.lineToRelative(0.0f, 3.88f);
        pathBuilderJ.curveTo(4.94f, 21.49f, 2.0f, 20.34f, 2.0f, 19.0f);
        pathBuilderJ.verticalLineToRelative(-9.0f);
        pathBuilderJ.curveToRelative(0.0f, -1.66f, 4.48f, -3.0f, 10.0f, -3.0f);
        pathBuilderJ.reflectiveCurveToRelative(10.0f, 1.34f, 10.0f, 3.0f);
        pathBuilderJ.verticalLineToRelative(9.0f);
        pathBuilderJ.curveToRelative(0.0f, 1.34f, -2.94f, 2.48f, -7.0f, 2.87f);
        pathBuilderJ.lineTo(15.0f, 18.0f);
        pathBuilderJ.curveTo(15.0f, 17.45f, 14.55f, 17.0f, 14.0f, 17.0f);
        pathBuilderJ.close();
        ImageVector imageVectorBuild = ImageVector.Builder.m4024addPathoIyEayM$default(builder, pathBuilderJ.getNodes(), defaultFillType, "", solidColor, 1.0f, null, 1.0f, 1.0f, iM3803getButtKaPHkGw, iM3813getBevelLxFBmk8, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null).build();
        _stadium = imageVectorBuild;
        return imageVectorBuild;
    }
}
