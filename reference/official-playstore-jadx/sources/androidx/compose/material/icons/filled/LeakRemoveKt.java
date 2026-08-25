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
import v.b;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\"\u0010\u0010\u0000\u001a\u0004\u0018\u00010\u0001X\u0082\u000e¢\u0006\u0002\n\u0000\"\u0015\u0010\u0002\u001a\u00020\u0001*\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"_leakRemove", "Landroidx/compose/ui/graphics/vector/ImageVector;", "LeakRemove", "Landroidx/compose/material/icons/Icons$Filled;", "getLeakRemove", "(Landroidx/compose/material/icons/Icons$Filled;)Landroidx/compose/ui/graphics/vector/ImageVector;", "material-icons-extended_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class LeakRemoveKt {
    private static ImageVector _leakRemove;

    public static final ImageVector getLeakRemove(Icons.Filled filled) {
        ImageVector imageVector = _leakRemove;
        if (imageVector != null) {
            return imageVector;
        }
        ImageVector.Builder builder = new ImageVector.Builder("Filled.LeakRemove", Dp.m5678constructorimpl(24.0f), Dp.m5678constructorimpl(24.0f), 24.0f, 24.0f, 0L, 0, false, 96, null);
        int defaultFillType = VectorKt.getDefaultFillType();
        SolidColor solidColor = new SolidColor(Color.INSTANCE.m3498getBlack0d7_KjU(), null);
        int iM3803getButtKaPHkGw = StrokeCap.INSTANCE.m3803getButtKaPHkGw();
        int iM3813getBevelLxFBmk8 = StrokeJoin.INSTANCE.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderJ = a.j(10.0f, 3.0f, 8.0f, 3.0f);
        pathBuilderJ.curveToRelative(0.0f, 0.37f, -0.04f, 0.72f, -0.12f, 1.06f);
        pathBuilderJ.lineToRelative(1.59f, 1.59f);
        pathBuilderJ.curveTo(9.81f, 4.84f, 10.0f, 3.94f, 10.0f, 3.0f);
        a.z(pathBuilderJ, 3.0f, 4.27f, 2.84f, 2.84f);
        pathBuilderJ.curveTo(5.03f, 7.67f, 4.06f, 8.0f, 3.0f, 8.0f);
        pathBuilderJ.verticalLineToRelative(2.0f);
        pathBuilderJ.curveToRelative(1.61f, 0.0f, 3.09f, -0.55f, 4.27f, -1.46f);
        pathBuilderJ.lineTo(8.7f, 9.97f);
        pathBuilderJ.curveTo(7.14f, 11.24f, 5.16f, 12.0f, 3.0f, 12.0f);
        pathBuilderJ.verticalLineToRelative(2.0f);
        pathBuilderJ.curveToRelative(2.71f, 0.0f, 5.19f, -0.99f, 7.11f, -2.62f);
        pathBuilderJ.lineToRelative(2.5f, 2.5f);
        pathBuilderJ.curveTo(10.99f, 15.81f, 10.0f, 18.29f, 10.0f, 21.0f);
        pathBuilderJ.horizontalLineToRelative(2.0f);
        pathBuilderJ.curveToRelative(0.0f, -2.16f, 0.76f, -4.14f, 2.03f, -5.69f);
        pathBuilderJ.lineToRelative(1.43f, 1.43f);
        pathBuilderJ.curveTo(14.55f, 17.91f, 14.0f, 19.39f, 14.0f, 21.0f);
        pathBuilderJ.horizontalLineToRelative(2.0f);
        pathBuilderJ.curveToRelative(0.0f, -1.06f, 0.33f, -2.03f, 0.89f, -2.84f);
        pathBuilderJ.lineTo(19.73f, 21.0f);
        pathBuilderJ.lineTo(21.0f, 19.73f);
        b.y(pathBuilderJ, 4.27f, 3.0f, 3.0f, 4.27f);
        pathBuilderJ.moveTo(14.0f, 3.0f);
        pathBuilderJ.horizontalLineToRelative(-2.0f);
        pathBuilderJ.curveToRelative(0.0f, 1.5f, -0.37f, 2.91f, -1.02f, 4.16f);
        pathBuilderJ.lineToRelative(1.46f, 1.46f);
        pathBuilderJ.curveTo(13.42f, 6.98f, 14.0f, 5.06f, 14.0f, 3.0f);
        pathBuilderJ.close();
        pathBuilderJ.moveTo(19.94f, 16.12f);
        pathBuilderJ.curveToRelative(0.34f, -0.08f, 0.69f, -0.12f, 1.06f, -0.12f);
        pathBuilderJ.verticalLineToRelative(-2.0f);
        pathBuilderJ.curveToRelative(-0.94f, 0.0f, -1.84f, 0.19f, -2.66f, 0.52f);
        b.C(pathBuilderJ, 1.6f, 1.6f, 15.38f, 11.56f);
        pathBuilderJ.lineToRelative(1.46f, 1.46f);
        pathBuilderJ.curveTo(18.09f, 12.37f, 19.5f, 12.0f, 21.0f, 12.0f);
        pathBuilderJ.verticalLineToRelative(-2.0f);
        pathBuilderJ.curveToRelative(-2.06f, 0.0f, -3.98f, 0.58f, -5.62f, 1.56f);
        pathBuilderJ.close();
        ImageVector imageVectorBuild = ImageVector.Builder.m4024addPathoIyEayM$default(builder, pathBuilderJ.getNodes(), defaultFillType, "", solidColor, 1.0f, null, 1.0f, 1.0f, iM3803getButtKaPHkGw, iM3813getBevelLxFBmk8, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null).build();
        _leakRemove = imageVectorBuild;
        return imageVectorBuild;
    }
}
