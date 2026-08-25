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
import v.c;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\"\u0010\u0010\u0000\u001a\u0004\u0018\u00010\u0001X\u0082\u000e¢\u0006\u0002\n\u0000\"\u0015\u0010\u0002\u001a\u00020\u0001*\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"_photoFilter", "Landroidx/compose/ui/graphics/vector/ImageVector;", "PhotoFilter", "Landroidx/compose/material/icons/Icons$Rounded;", "getPhotoFilter", "(Landroidx/compose/material/icons/Icons$Rounded;)Landroidx/compose/ui/graphics/vector/ImageVector;", "material-icons-extended_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class PhotoFilterKt {
    private static ImageVector _photoFilter;

    public static final ImageVector getPhotoFilter(Icons.Rounded rounded) {
        ImageVector imageVector = _photoFilter;
        if (imageVector != null) {
            return imageVector;
        }
        ImageVector.Builder builder = new ImageVector.Builder("Rounded.PhotoFilter", Dp.m5678constructorimpl(24.0f), Dp.m5678constructorimpl(24.0f), 24.0f, 24.0f, 0L, 0, false, 96, null);
        int defaultFillType = VectorKt.getDefaultFillType();
        SolidColor solidColor = new SolidColor(Color.INSTANCE.m3498getBlack0d7_KjU(), null);
        int iM3803getButtKaPHkGw = StrokeCap.INSTANCE.m3803getButtKaPHkGw();
        int iM3813getBevelLxFBmk8 = StrokeJoin.INSTANCE.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderJ = a.j(19.02f, 10.99f, 19.02f, 18.0f);
        pathBuilderJ.curveToRelative(0.0f, 0.55f, -0.45f, 1.0f, -1.0f, 1.0f);
        pathBuilderJ.lineTo(6.0f, 19.0f);
        pathBuilderJ.curveToRelative(-0.55f, 0.0f, -1.0f, -0.45f, -1.0f, -1.0f);
        pathBuilderJ.lineTo(5.0f, 6.0f);
        pathBuilderJ.curveToRelative(0.0f, -0.55f, 0.45f, -1.0f, 1.0f, -1.0f);
        pathBuilderJ.horizontalLineToRelative(7.0f);
        pathBuilderJ.curveToRelative(0.55f, 0.0f, 1.0f, -0.45f, 1.0f, -1.0f);
        pathBuilderJ.reflectiveCurveToRelative(-0.45f, -1.0f, -1.0f, -1.0f);
        pathBuilderJ.lineTo(5.02f, 3.0f);
        pathBuilderJ.curveToRelative(-1.1f, 0.0f, -2.0f, 0.9f, -2.0f, 2.0f);
        pathBuilderJ.verticalLineToRelative(14.0f);
        pathBuilderJ.curveToRelative(0.0f, 1.1f, 0.9f, 2.0f, 2.0f, 2.0f);
        pathBuilderJ.lineTo(19.0f, 21.0f);
        pathBuilderJ.curveToRelative(1.1f, 0.0f, 2.0f, -0.89f, 2.0f, -2.0f);
        pathBuilderJ.verticalLineToRelative(-8.01f);
        pathBuilderJ.curveToRelative(0.0f, -0.55f, -0.44f, -0.99f, -0.99f, -0.99f);
        pathBuilderJ.reflectiveCurveToRelative(-0.99f, 0.44f, -0.99f, 0.99f);
        a.p(pathBuilderJ, 13.25f, 10.75f, 12.46f, 9.0f);
        pathBuilderJ.curveToRelative(-0.18f, -0.39f, -0.73f, -0.39f, -0.91f, 0.0f);
        pathBuilderJ.lineToRelative(-0.79f, 1.75f);
        pathBuilderJ.lineToRelative(-1.76f, 0.79f);
        pathBuilderJ.curveToRelative(-0.39f, 0.18f, -0.39f, 0.73f, 0.0f, 0.91f);
        pathBuilderJ.lineToRelative(1.75f, 0.79f);
        pathBuilderJ.lineToRelative(0.79f, 1.76f);
        pathBuilderJ.curveToRelative(0.18f, 0.39f, 0.73f, 0.39f, 0.91f, 0.0f);
        pathBuilderJ.lineToRelative(0.79f, -1.75f);
        pathBuilderJ.lineToRelative(1.76f, -0.79f);
        pathBuilderJ.curveToRelative(0.39f, -0.18f, 0.39f, -0.73f, 0.0f, -0.91f);
        b.C(pathBuilderJ, -1.75f, -0.8f, 17.94f, 6.06f);
        pathBuilderJ.lineToRelative(-0.6f, -1.32f);
        pathBuilderJ.curveToRelative(-0.13f, -0.29f, -0.55f, -0.29f, -0.69f, 0.0f);
        pathBuilderJ.lineToRelative(-0.6f, 1.32f);
        pathBuilderJ.lineToRelative(-1.32f, 0.6f);
        pathBuilderJ.curveToRelative(-0.29f, 0.13f, -0.29f, 0.55f, 0.0f, 0.69f);
        pathBuilderJ.lineToRelative(1.32f, 0.6f);
        pathBuilderJ.lineToRelative(0.6f, 1.32f);
        pathBuilderJ.curveToRelative(0.13f, 0.29f, 0.55f, 0.29f, 0.69f, 0.0f);
        pathBuilderJ.lineToRelative(0.6f, -1.32f);
        pathBuilderJ.lineToRelative(1.32f, -0.6f);
        pathBuilderJ.curveToRelative(0.29f, -0.13f, 0.29f, -0.55f, 0.0f, -0.69f);
        ImageVector imageVectorBuild = ImageVector.Builder.m4024addPathoIyEayM$default(builder, c.c(pathBuilderJ, -1.32f, -0.6f), defaultFillType, "", solidColor, 1.0f, null, 1.0f, 1.0f, iM3803getButtKaPHkGw, iM3813getBevelLxFBmk8, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null).build();
        _photoFilter = imageVectorBuild;
        return imageVectorBuild;
    }
}
