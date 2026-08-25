package androidx.compose.material.icons.twotone;

import a0.a;
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
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\"\u0010\u0010\u0000\u001a\u0004\u0018\u00010\u0001X\u0082\u000e¢\u0006\u0002\n\u0000\"\u0015\u0010\u0002\u001a\u00020\u0001*\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"_extension", "Landroidx/compose/ui/graphics/vector/ImageVector;", "Extension", "Landroidx/compose/material/icons/Icons$TwoTone;", "getExtension", "(Landroidx/compose/material/icons/Icons$TwoTone;)Landroidx/compose/ui/graphics/vector/ImageVector;", "material-icons-extended_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class ExtensionKt {
    private static ImageVector _extension;

    public static final ImageVector getExtension(Icons.TwoTone twoTone) {
        ImageVector imageVector = _extension;
        if (imageVector != null) {
            return imageVector;
        }
        ImageVector.Builder builder = new ImageVector.Builder("TwoTone.Extension", Dp.m5678constructorimpl(24.0f), Dp.m5678constructorimpl(24.0f), 24.0f, 24.0f, 0L, 0, false, 96, null);
        int defaultFillType = VectorKt.getDefaultFillType();
        Color.Companion companion = Color.INSTANCE;
        SolidColor solidColor = new SolidColor(companion.m3498getBlack0d7_KjU(), null);
        StrokeCap.Companion companion2 = StrokeCap.INSTANCE;
        int iM3803getButtKaPHkGw = companion2.m3803getButtKaPHkGw();
        StrokeJoin.Companion companion3 = StrokeJoin.INSTANCE;
        int iM3813getBevelLxFBmk8 = companion3.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderU = a.u(19.0f, 13.0f, -2.0f, 7.0f, -6.0f);
        pathBuilderU.verticalLineTo(5.0f);
        pathBuilderU.curveToRelative(0.0f, -0.28f, -0.22f, -0.5f, -0.5f, -0.5f);
        pathBuilderU.reflectiveCurveToRelative(-0.5f, 0.22f, -0.5f, 0.5f);
        a.o(pathBuilderU, 2.0f, 4.0f, 0.01f, 2.12f);
        pathBuilderU.curveTo(5.76f, 9.8f, 7.0f, 11.51f, 7.0f, 13.5f);
        pathBuilderU.curveToRelative(0.0f, 1.99f, -1.25f, 3.7f, -3.0f, 4.38f);
        pathBuilderU.verticalLineTo(20.0f);
        pathBuilderU.horizontalLineToRelative(2.12f);
        pathBuilderU.curveToRelative(0.68f, -1.75f, 2.39f, -3.0f, 4.38f, -3.0f);
        pathBuilderU.curveToRelative(1.99f, 0.0f, 3.7f, 1.25f, 4.38f, 3.0f);
        v.a.n(pathBuilderU, 17.0f, -6.0f, 2.0f);
        pathBuilderU.curveToRelative(0.28f, 0.0f, 0.5f, -0.22f, 0.5f, -0.5f);
        ImageVector.Builder.m4024addPathoIyEayM$default(builder, c.h(pathBuilderU, -0.22f, -0.5f, -0.5f, -0.5f), defaultFillType, "", solidColor, 0.3f, null, 0.3f, 1.0f, iM3803getButtKaPHkGw, iM3813getBevelLxFBmk8, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null);
        int defaultFillType2 = VectorKt.getDefaultFillType();
        SolidColor solidColor2 = new SolidColor(companion.m3498getBlack0d7_KjU(), null);
        int iM3803getButtKaPHkGw2 = companion2.m3803getButtKaPHkGw();
        int iM3813getBevelLxFBmk82 = companion3.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderJ = v.a.j(19.0f, 11.0f, 19.0f, 7.0f);
        pathBuilderJ.curveToRelative(0.0f, -1.1f, -0.9f, -2.0f, -2.0f, -2.0f);
        pathBuilderJ.horizontalLineToRelative(-4.0f);
        pathBuilderJ.curveToRelative(0.0f, -1.38f, -1.12f, -2.5f, -2.5f, -2.5f);
        pathBuilderJ.reflectiveCurveTo(8.0f, 3.62f, 8.0f, 5.0f);
        pathBuilderJ.lineTo(4.0f, 5.0f);
        pathBuilderJ.curveToRelative(-1.1f, 0.0f, -1.99f, 0.9f, -1.99f, 2.0f);
        pathBuilderJ.verticalLineToRelative(3.8f);
        pathBuilderJ.horizontalLineToRelative(0.29f);
        pathBuilderJ.curveToRelative(1.49f, 0.0f, 2.7f, 1.21f, 2.7f, 2.7f);
        pathBuilderJ.reflectiveCurveToRelative(-1.21f, 2.7f, -2.7f, 2.7f);
        pathBuilderJ.lineTo(2.0f, 16.2f);
        pathBuilderJ.lineTo(2.0f, 20.0f);
        pathBuilderJ.curveToRelative(0.0f, 1.1f, 0.9f, 2.0f, 2.0f, 2.0f);
        pathBuilderJ.horizontalLineToRelative(3.8f);
        pathBuilderJ.verticalLineToRelative(-0.3f);
        pathBuilderJ.curveToRelative(0.0f, -1.49f, 1.21f, -2.7f, 2.7f, -2.7f);
        pathBuilderJ.reflectiveCurveToRelative(2.7f, 1.21f, 2.7f, 2.7f);
        pathBuilderJ.verticalLineToRelative(0.3f);
        pathBuilderJ.lineTo(17.0f, 22.0f);
        pathBuilderJ.curveToRelative(1.1f, 0.0f, 2.0f, -0.9f, 2.0f, -2.0f);
        pathBuilderJ.verticalLineToRelative(-4.0f);
        pathBuilderJ.curveToRelative(1.38f, 0.0f, 2.5f, -1.12f, 2.5f, -2.5f);
        pathBuilderJ.reflectiveCurveTo(20.38f, 11.0f, 19.0f, 11.0f);
        b.w(pathBuilderJ, 19.0f, 14.0f, -2.0f, 6.0f);
        pathBuilderJ.horizontalLineToRelative(-2.12f);
        pathBuilderJ.curveToRelative(-0.68f, -1.75f, -2.39f, -3.0f, -4.38f, -3.0f);
        pathBuilderJ.curveToRelative(-1.99f, 0.0f, -3.7f, 1.25f, -4.38f, 3.0f);
        pathBuilderJ.lineTo(4.0f, 20.0f);
        pathBuilderJ.verticalLineToRelative(-2.12f);
        pathBuilderJ.curveToRelative(1.75f, -0.68f, 3.0f, -2.39f, 3.0f, -4.38f);
        pathBuilderJ.curveToRelative(0.0f, -1.99f, -1.24f, -3.7f, -2.99f, -4.38f);
        pathBuilderJ.lineTo(4.0f, 7.0f);
        pathBuilderJ.horizontalLineToRelative(6.0f);
        pathBuilderJ.lineTo(10.0f, 5.0f);
        pathBuilderJ.curveToRelative(0.0f, -0.28f, 0.22f, -0.5f, 0.5f, -0.5f);
        pathBuilderJ.reflectiveCurveToRelative(0.5f, 0.22f, 0.5f, 0.5f);
        b.z(pathBuilderJ, 2.0f, 6.0f, 6.0f, 2.0f);
        pathBuilderJ.curveToRelative(0.28f, 0.0f, 0.5f, 0.22f, 0.5f, 0.5f);
        ImageVector imageVectorBuild = ImageVector.Builder.m4024addPathoIyEayM$default(builder, c.h(pathBuilderJ, -0.22f, 0.5f, -0.5f, 0.5f), defaultFillType2, "", solidColor2, 1.0f, null, 1.0f, 1.0f, iM3803getButtKaPHkGw2, iM3813getBevelLxFBmk82, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null).build();
        _extension = imageVectorBuild;
        return imageVectorBuild;
    }
}
