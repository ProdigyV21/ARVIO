package androidx.compose.material.icons.twotone;

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

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\"\u0010\u0010\u0000\u001a\u0004\u0018\u00010\u0001X\u0082\u000e¢\u0006\u0002\n\u0000\"\u0015\u0010\u0002\u001a\u00020\u0001*\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"_opacity", "Landroidx/compose/ui/graphics/vector/ImageVector;", "Opacity", "Landroidx/compose/material/icons/Icons$TwoTone;", "getOpacity", "(Landroidx/compose/material/icons/Icons$TwoTone;)Landroidx/compose/ui/graphics/vector/ImageVector;", "material-icons-extended_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class OpacityKt {
    private static ImageVector _opacity;

    public static final ImageVector getOpacity(Icons.TwoTone twoTone) {
        ImageVector imageVector = _opacity;
        if (imageVector != null) {
            return imageVector;
        }
        ImageVector.Builder builder = new ImageVector.Builder("TwoTone.Opacity", Dp.m5678constructorimpl(24.0f), Dp.m5678constructorimpl(24.0f), 24.0f, 24.0f, 0L, 0, false, 96, null);
        int defaultFillType = VectorKt.getDefaultFillType();
        Color.Companion companion = Color.INSTANCE;
        SolidColor solidColor = new SolidColor(companion.m3498getBlack0d7_KjU(), null);
        StrokeCap.Companion companion2 = StrokeCap.INSTANCE;
        int iM3803getButtKaPHkGw = companion2.m3803getButtKaPHkGw();
        StrokeJoin.Companion companion3 = StrokeJoin.INSTANCE;
        int iM3813getBevelLxFBmk8 = companion3.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderJ = a.j(16.24f, 9.65f, 12.0f, 5.27f);
        pathBuilderJ.lineTo(7.76f, 9.6f);
        pathBuilderJ.curveTo(6.62f, 10.73f, 6.01f, 12.0f, 6.0f, 14.0f);
        pathBuilderJ.horizontalLineToRelative(12.0f);
        pathBuilderJ.curveToRelative(-0.01f, -2.0f, -0.62f, -3.23f, -1.76f, -4.35f);
        pathBuilderJ.close();
        ImageVector.Builder.m4024addPathoIyEayM$default(builder, pathBuilderJ.getNodes(), defaultFillType, "", solidColor, 0.3f, null, 0.3f, 1.0f, iM3803getButtKaPHkGw, iM3813getBevelLxFBmk8, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null);
        int defaultFillType2 = VectorKt.getDefaultFillType();
        SolidColor solidColor2 = new SolidColor(companion.m3498getBlack0d7_KjU(), null);
        int iM3803getButtKaPHkGw2 = companion2.m3803getButtKaPHkGw();
        int iM3813getBevelLxFBmk82 = companion3.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderJ2 = a.j(17.66f, 8.0f, 12.0f, 2.35f);
        pathBuilderJ2.lineTo(6.34f, 8.0f);
        pathBuilderJ2.curveTo(4.78f, 9.56f, 4.0f, 11.64f, 4.0f, 13.64f);
        pathBuilderJ2.reflectiveCurveToRelative(0.78f, 4.11f, 2.34f, 5.67f);
        pathBuilderJ2.reflectiveCurveToRelative(3.61f, 2.35f, 5.66f, 2.35f);
        pathBuilderJ2.reflectiveCurveToRelative(4.1f, -0.79f, 5.66f, -2.35f);
        pathBuilderJ2.reflectiveCurveTo(20.0f, 15.64f, 20.0f, 13.64f);
        pathBuilderJ2.reflectiveCurveTo(19.22f, 9.56f, 17.66f, 8.0f);
        pathBuilderJ2.close();
        pathBuilderJ2.moveTo(6.0f, 14.0f);
        pathBuilderJ2.curveToRelative(0.01f, -2.0f, 0.62f, -3.27f, 1.76f, -4.4f);
        pathBuilderJ2.lineTo(12.0f, 5.27f);
        pathBuilderJ2.lineToRelative(4.24f, 4.38f);
        pathBuilderJ2.curveTo(17.38f, 10.77f, 17.99f, 12.0f, 18.0f, 14.0f);
        ImageVector imageVectorBuild = ImageVector.Builder.m4024addPathoIyEayM$default(builder, a.l(pathBuilderJ2, 6.0f), defaultFillType2, "", solidColor2, 1.0f, null, 1.0f, 1.0f, iM3803getButtKaPHkGw2, iM3813getBevelLxFBmk82, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null).build();
        _opacity = imageVectorBuild;
        return imageVectorBuild;
    }
}
