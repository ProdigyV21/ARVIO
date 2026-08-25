package androidx.compose.material.icons.outlined;

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
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\"\u0010\u0010\u0000\u001a\u0004\u0018\u00010\u0001X\u0082\u000e¢\u0006\u0002\n\u0000\"\u0015\u0010\u0002\u001a\u00020\u0001*\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"_rowing", "Landroidx/compose/ui/graphics/vector/ImageVector;", "Rowing", "Landroidx/compose/material/icons/Icons$Outlined;", "getRowing", "(Landroidx/compose/material/icons/Icons$Outlined;)Landroidx/compose/ui/graphics/vector/ImageVector;", "material-icons-extended_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class RowingKt {
    private static ImageVector _rowing;

    public static final ImageVector getRowing(Icons.Outlined outlined) {
        ImageVector imageVector = _rowing;
        if (imageVector != null) {
            return imageVector;
        }
        ImageVector.Builder builder = new ImageVector.Builder("Outlined.Rowing", Dp.m5678constructorimpl(24.0f), Dp.m5678constructorimpl(24.0f), 24.0f, 24.0f, 0L, 0, false, 96, null);
        int defaultFillType = VectorKt.getDefaultFillType();
        SolidColor solidColor = new SolidColor(Color.INSTANCE.m3498getBlack0d7_KjU(), null);
        int iM3803getButtKaPHkGw = StrokeCap.INSTANCE.m3803getButtKaPHkGw();
        int iM3813getBevelLxFBmk8 = StrokeJoin.INSTANCE.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderJ = a.j(8.5f, 14.5f, 4.0f, 19.0f);
        pathBuilderJ.lineToRelative(1.5f, 1.5f);
        pathBuilderJ.lineTo(9.0f, 17.0f);
        c.v(pathBuilderJ, 2.0f, -2.5f, -2.5f);
        pathBuilderJ.moveTo(15.0f, 1.0f);
        pathBuilderJ.curveToRelative(-1.1f, 0.0f, -2.0f, 0.9f, -2.0f, 2.0f);
        pathBuilderJ.reflectiveCurveToRelative(0.9f, 2.0f, 2.0f, 2.0f);
        pathBuilderJ.reflectiveCurveToRelative(2.0f, -0.9f, 2.0f, -2.0f);
        pathBuilderJ.reflectiveCurveToRelative(-0.9f, -2.0f, -2.0f, -2.0f);
        a.p(pathBuilderJ, 21.0f, 21.01f, 18.0f, 24.0f);
        pathBuilderJ.lineToRelative(-2.99f, -3.01f);
        pathBuilderJ.lineTo(15.01f, 19.5f);
        pathBuilderJ.lineToRelative(-7.1f, -7.09f);
        pathBuilderJ.curveToRelative(-0.31f, 0.05f, -0.61f, 0.07f, -0.91f, 0.07f);
        pathBuilderJ.verticalLineToRelative(-2.16f);
        pathBuilderJ.curveToRelative(1.66f, 0.03f, 3.61f, -0.87f, 4.67f, -2.04f);
        pathBuilderJ.lineToRelative(1.4f, -1.55f);
        pathBuilderJ.curveToRelative(0.19f, -0.21f, 0.43f, -0.38f, 0.69f, -0.5f);
        pathBuilderJ.curveToRelative(0.29f, -0.14f, 0.62f, -0.23f, 0.96f, -0.23f);
        pathBuilderJ.horizontalLineToRelative(0.03f);
        pathBuilderJ.curveTo(15.99f, 6.01f, 17.0f, 7.02f, 17.0f, 8.26f);
        pathBuilderJ.verticalLineToRelative(5.75f);
        pathBuilderJ.curveToRelative(0.0f, 0.84f, -0.35f, 1.61f, -0.92f, 2.16f);
        pathBuilderJ.lineToRelative(-3.58f, -3.58f);
        pathBuilderJ.verticalLineToRelative(-2.27f);
        pathBuilderJ.curveToRelative(-0.63f, 0.52f, -1.43f, 1.02f, -2.29f, 1.39f);
        pathBuilderJ.lineTo(16.5f, 18.0f);
        ImageVector imageVectorBuild = ImageVector.Builder.m4024addPathoIyEayM$default(builder, b.v(pathBuilderJ, 18.0f, 18.0f, 3.0f, 3.01f), defaultFillType, "", solidColor, 1.0f, null, 1.0f, 1.0f, iM3803getButtKaPHkGw, iM3813getBevelLxFBmk8, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null).build();
        _rowing = imageVectorBuild;
        return imageVectorBuild;
    }
}
