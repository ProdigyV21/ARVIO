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
import v.c;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\"\u0010\u0010\u0000\u001a\u0004\u0018\u00010\u0001X\u0082\u000e¢\u0006\u0002\n\u0000\"\u0015\u0010\u0002\u001a\u00020\u0001*\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"_subscript", "Landroidx/compose/ui/graphics/vector/ImageVector;", "Subscript", "Landroidx/compose/material/icons/Icons$Rounded;", "getSubscript", "(Landroidx/compose/material/icons/Icons$Rounded;)Landroidx/compose/ui/graphics/vector/ImageVector;", "material-icons-extended_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class SubscriptKt {
    private static ImageVector _subscript;

    public static final ImageVector getSubscript(Icons.Rounded rounded) {
        ImageVector imageVector = _subscript;
        if (imageVector != null) {
            return imageVector;
        }
        ImageVector.Builder builder = new ImageVector.Builder("Rounded.Subscript", Dp.m5678constructorimpl(24.0f), Dp.m5678constructorimpl(24.0f), 24.0f, 24.0f, 0L, 0, false, 96, null);
        int defaultFillType = VectorKt.getDefaultFillType();
        SolidColor solidColor = new SolidColor(Color.INSTANCE.m3498getBlack0d7_KjU(), null);
        int iM3803getButtKaPHkGw = StrokeCap.INSTANCE.m3803getButtKaPHkGw();
        int iM3813getBevelLxFBmk8 = StrokeJoin.INSTANCE.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderJ = a.j(10.52f, 10.73f, 7.3f, 5.72f);
        pathBuilderJ.curveTo(6.82f, 4.97f, 7.35f, 4.0f, 8.23f, 4.0f);
        pathBuilderJ.horizontalLineToRelative(0.0f);
        pathBuilderJ.curveToRelative(0.39f, 0.0f, 0.74f, 0.2f, 0.95f, 0.53f);
        pathBuilderJ.lineToRelative(2.76f, 4.46f);
        pathBuilderJ.horizontalLineToRelative(0.12f);
        pathBuilderJ.lineToRelative(2.74f, -4.45f);
        pathBuilderJ.curveTo(15.01f, 4.2f, 15.37f, 4.0f, 15.76f, 4.0f);
        pathBuilderJ.horizontalLineToRelative(0.0f);
        pathBuilderJ.curveToRelative(0.88f, 0.0f, 1.42f, 0.98f, 0.94f, 1.72f);
        pathBuilderJ.lineToRelative(-3.23f, 5.0f);
        pathBuilderJ.lineToRelative(3.55f, 5.55f);
        pathBuilderJ.curveTo(17.5f, 17.02f, 16.96f, 18.0f, 16.08f, 18.0f);
        pathBuilderJ.horizontalLineToRelative(0.0f);
        pathBuilderJ.curveToRelative(-0.38f, 0.0f, -0.74f, -0.2f, -0.95f, -0.52f);
        pathBuilderJ.lineToRelative(-3.07f, -4.89f);
        pathBuilderJ.horizontalLineToRelative(-0.12f);
        pathBuilderJ.lineToRelative(-3.07f, 4.89f);
        pathBuilderJ.curveTo(8.67f, 17.8f, 8.31f, 18.0f, 7.92f, 18.0f);
        pathBuilderJ.horizontalLineToRelative(0.0f);
        pathBuilderJ.curveToRelative(-0.88f, 0.0f, -1.42f, -0.97f, -0.94f, -1.72f);
        c.z(pathBuilderJ, 10.52f, 10.73f, 23.0f, 19.5f);
        pathBuilderJ.lineTo(23.0f, 19.5f);
        pathBuilderJ.curveToRelative(0.0f, -0.28f, -0.22f, -0.5f, -0.5f, -0.5f);
        pathBuilderJ.curveToRelative(0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f);
        a.n(pathBuilderJ, 20.0f, -1.0f, 2.0f);
        pathBuilderJ.curveToRelative(0.55f, 0.0f, 1.0f, -0.45f, 1.0f, -1.0f);
        pathBuilderJ.verticalLineToRelative(-1.0f);
        pathBuilderJ.curveToRelative(0.0f, -0.55f, -0.45f, -1.0f, -1.0f, -1.0f);
        pathBuilderJ.horizontalLineToRelative(-2.5f);
        pathBuilderJ.curveToRelative(-0.28f, 0.0f, -0.5f, 0.22f, -0.5f, 0.5f);
        pathBuilderJ.verticalLineToRelative(0.0f);
        pathBuilderJ.curveToRelative(0.0f, 0.28f, 0.22f, 0.5f, 0.5f, 0.5f);
        a.n(pathBuilderJ, 22.0f, 1.0f, -2.0f);
        pathBuilderJ.curveToRelative(-0.55f, 0.0f, -1.0f, 0.45f, -1.0f, 1.0f);
        pathBuilderJ.verticalLineToRelative(1.0f);
        pathBuilderJ.curveToRelative(0.0f, 0.55f, 0.45f, 1.0f, 1.0f, 1.0f);
        pathBuilderJ.horizontalLineToRelative(2.5f);
        pathBuilderJ.curveTo(22.78f, 20.0f, 23.0f, 19.78f, 23.0f, 19.5f);
        pathBuilderJ.close();
        ImageVector imageVectorBuild = ImageVector.Builder.m4024addPathoIyEayM$default(builder, pathBuilderJ.getNodes(), defaultFillType, "", solidColor, 1.0f, null, 1.0f, 1.0f, iM3803getButtKaPHkGw, iM3813getBevelLxFBmk8, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null).build();
        _subscript = imageVectorBuild;
        return imageVectorBuild;
    }
}
