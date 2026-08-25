package androidx.compose.material.icons.automirrored.rounded;

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

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\"\u0010\u0010\u0000\u001a\u0004\u0018\u00010\u0001X\u0082\u000e¢\u0006\u0002\n\u0000\"\u0015\u0010\u0002\u001a\u00020\u0001*\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"_compareArrows", "Landroidx/compose/ui/graphics/vector/ImageVector;", "CompareArrows", "Landroidx/compose/material/icons/Icons$AutoMirrored$Rounded;", "getCompareArrows", "(Landroidx/compose/material/icons/Icons$AutoMirrored$Rounded;)Landroidx/compose/ui/graphics/vector/ImageVector;", "material-icons-extended_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class CompareArrowsKt {
    private static ImageVector _compareArrows;

    public static final ImageVector getCompareArrows(Icons.AutoMirrored.Rounded rounded) {
        ImageVector imageVector = _compareArrows;
        if (imageVector != null) {
            return imageVector;
        }
        ImageVector.Builder builder = new ImageVector.Builder("AutoMirrored.Rounded.CompareArrows", Dp.m5678constructorimpl(24.0f), Dp.m5678constructorimpl(24.0f), 24.0f, 24.0f, 0L, 0, true, 96, null);
        int defaultFillType = VectorKt.getDefaultFillType();
        SolidColor solidColor = new SolidColor(Color.INSTANCE.m3498getBlack0d7_KjU(), null);
        int iM3803getButtKaPHkGw = StrokeCap.INSTANCE.m3803getButtKaPHkGw();
        int iM3813getBevelLxFBmk8 = StrokeJoin.INSTANCE.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderJ = a.j(9.01f, 14.0f, 3.0f, 14.0f);
        pathBuilderJ.curveToRelative(-0.55f, 0.0f, -1.0f, 0.45f, -1.0f, 1.0f);
        pathBuilderJ.reflectiveCurveToRelative(0.45f, 1.0f, 1.0f, 1.0f);
        pathBuilderJ.horizontalLineToRelative(6.01f);
        pathBuilderJ.verticalLineToRelative(1.79f);
        pathBuilderJ.curveToRelative(0.0f, 0.45f, 0.54f, 0.67f, 0.85f, 0.35f);
        pathBuilderJ.lineToRelative(2.78f, -2.79f);
        pathBuilderJ.curveToRelative(0.19f, -0.2f, 0.19f, -0.51f, 0.0f, -0.71f);
        pathBuilderJ.lineToRelative(-2.78f, -2.79f);
        pathBuilderJ.curveToRelative(-0.31f, -0.32f, -0.85f, -0.09f, -0.85f, 0.35f);
        c.z(pathBuilderJ, 9.01f, 14.0f, 14.99f, 11.79f);
        pathBuilderJ.lineTo(14.99f, 10.0f);
        pathBuilderJ.lineTo(21.0f, 10.0f);
        pathBuilderJ.curveToRelative(0.55f, 0.0f, 1.0f, -0.45f, 1.0f, -1.0f);
        pathBuilderJ.reflectiveCurveToRelative(-0.45f, -1.0f, -1.0f, -1.0f);
        pathBuilderJ.horizontalLineToRelative(-6.01f);
        pathBuilderJ.lineTo(14.99f, 6.21f);
        pathBuilderJ.curveToRelative(0.0f, -0.45f, -0.54f, -0.67f, -0.85f, -0.35f);
        pathBuilderJ.lineToRelative(-2.78f, 2.79f);
        pathBuilderJ.curveToRelative(-0.19f, 0.2f, -0.19f, 0.51f, 0.0f, 0.71f);
        pathBuilderJ.lineToRelative(2.78f, 2.79f);
        pathBuilderJ.curveToRelative(0.31f, 0.31f, 0.85f, 0.09f, 0.85f, -0.36f);
        pathBuilderJ.close();
        ImageVector imageVectorBuild = ImageVector.Builder.m4024addPathoIyEayM$default(builder, pathBuilderJ.getNodes(), defaultFillType, "", solidColor, 1.0f, null, 1.0f, 1.0f, iM3803getButtKaPHkGw, iM3813getBevelLxFBmk8, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null).build();
        _compareArrows = imageVectorBuild;
        return imageVectorBuild;
    }
}
