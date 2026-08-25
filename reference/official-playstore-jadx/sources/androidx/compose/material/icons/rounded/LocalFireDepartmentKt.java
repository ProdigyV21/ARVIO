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
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\"\u0010\u0010\u0000\u001a\u0004\u0018\u00010\u0001X\u0082\u000e¢\u0006\u0002\n\u0000\"\u0015\u0010\u0002\u001a\u00020\u0001*\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"_localFireDepartment", "Landroidx/compose/ui/graphics/vector/ImageVector;", "LocalFireDepartment", "Landroidx/compose/material/icons/Icons$Rounded;", "getLocalFireDepartment", "(Landroidx/compose/material/icons/Icons$Rounded;)Landroidx/compose/ui/graphics/vector/ImageVector;", "material-icons-extended_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class LocalFireDepartmentKt {
    private static ImageVector _localFireDepartment;

    public static final ImageVector getLocalFireDepartment(Icons.Rounded rounded) {
        ImageVector imageVector = _localFireDepartment;
        if (imageVector != null) {
            return imageVector;
        }
        ImageVector.Builder builder = new ImageVector.Builder("Rounded.LocalFireDepartment", Dp.m5678constructorimpl(24.0f), Dp.m5678constructorimpl(24.0f), 24.0f, 24.0f, 0L, 0, false, 96, null);
        int defaultFillType = VectorKt.getDefaultFillType();
        Color.Companion companion = Color.INSTANCE;
        SolidColor solidColor = new SolidColor(companion.m3498getBlack0d7_KjU(), null);
        StrokeCap.Companion companion2 = StrokeCap.INSTANCE;
        int iM3803getButtKaPHkGw = companion2.m3803getButtKaPHkGw();
        StrokeJoin.Companion companion3 = StrokeJoin.INSTANCE;
        int iM3813getBevelLxFBmk8 = companion3.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderR = a.r(12.0f, 12.9f, -2.03f, 2.0f);
        pathBuilderR.curveToRelative(-0.46f, 0.46f, -0.82f, 1.03f, -0.93f, 1.67f);
        pathBuilderR.curveTo(8.74f, 18.41f, 10.18f, 20.0f, 12.0f, 20.0f);
        pathBuilderR.reflectiveCurveToRelative(3.26f, -1.59f, 2.96f, -3.42f);
        pathBuilderR.curveToRelative(-0.11f, -0.64f, -0.46f, -1.22f, -0.93f, -1.67f);
        ImageVector.Builder.m4024addPathoIyEayM$default(builder, d.o(pathBuilderR, 12.0f, 12.9f), defaultFillType, "", solidColor, 1.0f, null, 1.0f, 1.0f, iM3803getButtKaPHkGw, iM3813getBevelLxFBmk8, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null);
        int defaultFillType2 = VectorKt.getDefaultFillType();
        SolidColor solidColor2 = new SolidColor(companion.m3498getBlack0d7_KjU(), null);
        int iM3803getButtKaPHkGw2 = companion2.m3803getButtKaPHkGw();
        int iM3813getBevelLxFBmk82 = companion3.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderJ = a.j(15.56f, 6.55f, 15.56f, 6.55f);
        pathBuilderJ.curveTo(14.38f, 8.02f, 12.0f, 7.19f, 12.0f, 5.3f);
        pathBuilderJ.verticalLineTo(3.77f);
        pathBuilderJ.curveToRelative(0.0f, -0.8f, -0.89f, -1.28f, -1.55f, -0.84f);
        pathBuilderJ.curveTo(8.12f, 4.49f, 4.0f, 7.97f, 4.0f, 13.0f);
        pathBuilderJ.curveToRelative(0.0f, 2.92f, 1.56f, 5.47f, 3.89f, 6.86f);
        pathBuilderJ.curveToRelative(-0.71f, -1.02f, -1.06f, -2.31f, -0.81f, -3.68f);
        pathBuilderJ.curveToRelative(0.19f, -1.04f, 0.75f, -1.98f, 1.51f, -2.72f);
        pathBuilderJ.lineToRelative(2.71f, -2.67f);
        pathBuilderJ.curveToRelative(0.39f, -0.38f, 1.01f, -0.38f, 1.4f, 0.0f);
        pathBuilderJ.lineToRelative(2.73f, 2.69f);
        pathBuilderJ.curveToRelative(0.74f, 0.73f, 1.3f, 1.65f, 1.48f, 2.68f);
        pathBuilderJ.curveToRelative(0.25f, 1.36f, -0.07f, 2.64f, -0.77f, 3.66f);
        pathBuilderJ.curveToRelative(1.89f, -1.15f, 3.29f, -3.06f, 3.71f, -5.3f);
        pathBuilderJ.curveToRelative(0.61f, -3.27f, -0.81f, -6.37f, -3.22f, -8.1f);
        pathBuilderJ.curveTo(16.3f, 6.17f, 15.83f, 6.22f, 15.56f, 6.55f);
        pathBuilderJ.close();
        ImageVector imageVectorBuild = ImageVector.Builder.m4024addPathoIyEayM$default(builder, pathBuilderJ.getNodes(), defaultFillType2, "", solidColor2, 1.0f, null, 1.0f, 1.0f, iM3803getButtKaPHkGw2, iM3813getBevelLxFBmk82, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null).build();
        _localFireDepartment = imageVectorBuild;
        return imageVectorBuild;
    }
}
