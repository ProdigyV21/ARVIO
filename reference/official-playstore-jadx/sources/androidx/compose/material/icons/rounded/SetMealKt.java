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
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\"\u0010\u0010\u0000\u001a\u0004\u0018\u00010\u0001X\u0082\u000e¢\u0006\u0002\n\u0000\"\u0015\u0010\u0002\u001a\u00020\u0001*\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"_setMeal", "Landroidx/compose/ui/graphics/vector/ImageVector;", "SetMeal", "Landroidx/compose/material/icons/Icons$Rounded;", "getSetMeal", "(Landroidx/compose/material/icons/Icons$Rounded;)Landroidx/compose/ui/graphics/vector/ImageVector;", "material-icons-extended_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class SetMealKt {
    private static ImageVector _setMeal;

    public static final ImageVector getSetMeal(Icons.Rounded rounded) {
        ImageVector imageVector = _setMeal;
        if (imageVector != null) {
            return imageVector;
        }
        ImageVector.Builder builder = new ImageVector.Builder("Rounded.SetMeal", Dp.m5678constructorimpl(24.0f), Dp.m5678constructorimpl(24.0f), 24.0f, 24.0f, 0L, 0, false, 96, null);
        int defaultFillType = VectorKt.getDefaultFillType();
        SolidColor solidColor = new SolidColor(Color.INSTANCE.m3498getBlack0d7_KjU(), null);
        int iM3803getButtKaPHkGw = StrokeCap.INSTANCE.m3803getButtKaPHkGw();
        int iM3813getBevelLxFBmk8 = StrokeJoin.INSTANCE.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderJ = a.j(20.3f, 17.6f, 3.83f, 18.46f);
        pathBuilderJ.curveToRelative(-0.41f, 0.02f, -0.77f, -0.3f, -0.79f, -0.71f);
        pathBuilderJ.lineToRelative(0.0f, 0.0f);
        pathBuilderJ.curveToRelative(-0.02f, -0.41f, 0.3f, -0.77f, 0.71f, -0.79f);
        pathBuilderJ.lineToRelative(16.48f, -0.86f);
        pathBuilderJ.curveToRelative(0.41f, -0.02f, 0.77f, 0.3f, 0.79f, 0.71f);
        pathBuilderJ.verticalLineToRelative(0.0f);
        pathBuilderJ.curveTo(21.04f, 17.22f, 20.72f, 17.58f, 20.3f, 17.6f);
        w.a.r(pathBuilderJ, 20.25f, 19.48f, 3.75f);
        pathBuilderJ.curveTo(3.34f, 19.48f, 3.0f, 19.82f, 3.0f, 20.23f);
        pathBuilderJ.lineToRelative(0.0f, 0.0f);
        pathBuilderJ.curveToRelative(0.0f, 0.41f, 0.34f, 0.75f, 0.75f, 0.75f);
        pathBuilderJ.horizontalLineToRelative(16.5f);
        pathBuilderJ.curveToRelative(0.41f, 0.0f, 0.75f, -0.34f, 0.75f, -0.75f);
        pathBuilderJ.lineToRelative(0.0f, 0.0f);
        pathBuilderJ.curveTo(21.0f, 19.82f, 20.66f, 19.48f, 20.25f, 19.48f);
        d.k(pathBuilderJ, 22.0f, 5.0f, 7.0f);
        pathBuilderJ.curveToRelative(0.0f, 1.1f, -0.9f, 2.0f, -2.0f, 2.0f);
        pathBuilderJ.horizontalLineTo(4.0f);
        pathBuilderJ.curveToRelative(-1.1f, 0.0f, -2.0f, -0.9f, -2.0f, -2.0f);
        pathBuilderJ.verticalLineTo(5.0f);
        pathBuilderJ.curveToRelative(0.0f, -1.1f, 0.9f, -2.0f, 2.0f, -2.0f);
        pathBuilderJ.horizontalLineToRelative(16.0f);
        pathBuilderJ.curveTo(21.1f, 3.0f, 22.0f, 3.9f, 22.0f, 5.0f);
        pathBuilderJ.close();
        pathBuilderJ.moveTo(19.12f, 6.09f);
        pathBuilderJ.curveToRelative(-1.25f, 0.27f, -2.19f, 1.11f, -2.33f, 2.14f);
        pathBuilderJ.curveTo(16.15f, 7.5f, 14.06f, 5.5f, 10.25f, 5.5f);
        pathBuilderJ.curveToRelative(-3.44f, 0.0f, -5.48f, 1.63f, -6.31f, 2.49f);
        pathBuilderJ.curveToRelative(-0.28f, 0.29f, -0.28f, 0.74f, 0.0f, 1.03f);
        pathBuilderJ.curveToRelative(0.83f, 0.86f, 2.87f, 2.49f, 6.31f, 2.49f);
        pathBuilderJ.curveToRelative(3.81f, 0.0f, 5.9f, -2.0f, 6.54f, -2.73f);
        pathBuilderJ.curveToRelative(0.14f, 1.02f, 1.08f, 1.86f, 2.33f, 2.14f);
        pathBuilderJ.curveToRelative(0.46f, 0.1f, 0.88f, -0.28f, 0.88f, -0.74f);
        pathBuilderJ.verticalLineTo(6.84f);
        pathBuilderJ.curveTo(20.0f, 6.37f, 19.57f, 5.99f, 19.12f, 6.09f);
        pathBuilderJ.close();
        ImageVector imageVectorBuild = ImageVector.Builder.m4024addPathoIyEayM$default(builder, pathBuilderJ.getNodes(), defaultFillType, "", solidColor, 1.0f, null, 1.0f, 1.0f, iM3803getButtKaPHkGw, iM3813getBevelLxFBmk8, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null).build();
        _setMeal = imageVectorBuild;
        return imageVectorBuild;
    }
}
