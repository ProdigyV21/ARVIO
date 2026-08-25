package androidx.compose.material.icons.rounded;

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
import v.c;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\"\u0010\u0010\u0000\u001a\u0004\u0018\u00010\u0001X\u0082\u000e¢\u0006\u0002\n\u0000\"\u0015\u0010\u0002\u001a\u00020\u0001*\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"_interpreterMode", "Landroidx/compose/ui/graphics/vector/ImageVector;", "InterpreterMode", "Landroidx/compose/material/icons/Icons$Rounded;", "getInterpreterMode", "(Landroidx/compose/material/icons/Icons$Rounded;)Landroidx/compose/ui/graphics/vector/ImageVector;", "material-icons-extended_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class InterpreterModeKt {
    private static ImageVector _interpreterMode;

    public static final ImageVector getInterpreterMode(Icons.Rounded rounded) {
        ImageVector imageVector = _interpreterMode;
        if (imageVector != null) {
            return imageVector;
        }
        ImageVector.Builder builder = new ImageVector.Builder("Rounded.InterpreterMode", Dp.m5678constructorimpl(24.0f), Dp.m5678constructorimpl(24.0f), 24.0f, 24.0f, 0L, 0, false, 96, null);
        int defaultFillType = VectorKt.getDefaultFillType();
        SolidColor solidColor = new SolidColor(Color.INSTANCE.m3498getBlack0d7_KjU(), null);
        int iM3803getButtKaPHkGw = StrokeCap.INSTANCE.m3803getButtKaPHkGw();
        int iM3813getBevelLxFBmk8 = StrokeJoin.INSTANCE.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderA = c.a(20.5f, 16.5f);
        pathBuilderA.curveToRelative(-0.83f, 0.0f, -1.5f, -0.67f, -1.5f, -1.5f);
        pathBuilderA.verticalLineToRelative(-2.5f);
        pathBuilderA.curveToRelative(0.0f, -0.83f, 0.67f, -1.5f, 1.5f, -1.5f);
        pathBuilderA.reflectiveCurveToRelative(1.5f, 0.67f, 1.5f, 1.5f);
        pathBuilderA.verticalLineTo(15.0f);
        pathBuilderA.curveTo(22.0f, 15.83f, 21.33f, 16.5f, 20.5f, 16.5f);
        pathBuilderA.close();
        pathBuilderA.moveTo(20.5f, 20.0f);
        pathBuilderA.curveToRelative(0.28f, 0.0f, 0.5f, -0.22f, 0.5f, -0.5f);
        pathBuilderA.curveToRelative(0.0f, -0.45f, 0.0f, -1.04f, 0.0f, -1.04f);
        pathBuilderA.curveToRelative(1.51f, -0.22f, 2.71f, -1.4f, 2.95f, -2.89f);
        pathBuilderA.curveTo(24.0f, 15.27f, 23.76f, 15.0f, 23.46f, 15.0f);
        pathBuilderA.curveToRelative(-0.24f, 0.0f, -0.45f, 0.17f, -0.49f, 0.41f);
        pathBuilderA.curveToRelative(-0.2f, 1.18f, -1.23f, 2.09f, -2.47f, 2.09f);
        pathBuilderA.reflectiveCurveToRelative(-2.27f, -0.9f, -2.47f, -2.09f);
        pathBuilderA.curveTo(17.99f, 15.17f, 17.78f, 15.0f, 17.54f, 15.0f);
        pathBuilderA.curveToRelative(-0.3f, 0.0f, -0.54f, 0.27f, -0.5f, 0.57f);
        pathBuilderA.curveToRelative(0.25f, 1.5f, 1.45f, 2.68f, 2.95f, 2.89f);
        pathBuilderA.curveToRelative(0.0f, 0.0f, 0.0f, 0.59f, 0.0f, 1.04f);
        pathBuilderA.curveTo(20.0f, 19.78f, 20.22f, 20.0f, 20.5f, 20.0f);
        pathBuilderA.close();
        pathBuilderA.moveTo(9.0f, 12.0f);
        pathBuilderA.curveToRelative(-2.21f, 0.0f, -4.0f, -1.79f, -4.0f, -4.0f);
        pathBuilderA.curveToRelative(0.0f, -2.21f, 1.79f, -4.0f, 4.0f, -4.0f);
        pathBuilderA.curveToRelative(0.47f, 0.0f, 0.92f, 0.08f, 1.34f, 0.23f);
        pathBuilderA.curveTo(9.5f, 5.26f, 9.0f, 6.57f, 9.0f, 8.0f);
        pathBuilderA.curveToRelative(0.0f, 1.43f, 0.5f, 2.74f, 1.34f, 3.77f);
        pathBuilderA.curveTo(9.92f, 11.92f, 9.47f, 12.0f, 9.0f, 12.0f);
        pathBuilderA.close();
        pathBuilderA.moveTo(7.11f, 13.13f);
        pathBuilderA.curveTo(5.79f, 14.05f, 5.0f, 15.57f, 5.0f, 17.22f);
        a.s(pathBuilderA, 20.0f, 1.0f, -2.78f);
        pathBuilderA.curveToRelative(0.0f, -1.12f, 0.61f, -2.15f, 1.61f, -2.66f);
        pathBuilderA.curveTo(3.85f, 13.92f, 5.37f, 13.37f, 7.11f, 13.13f);
        pathBuilderA.close();
        pathBuilderA.moveTo(11.0f, 8.0f);
        pathBuilderA.curveToRelative(0.0f, -2.21f, 1.79f, -4.0f, 4.0f, -4.0f);
        pathBuilderA.reflectiveCurveToRelative(4.0f, 1.79f, 4.0f, 4.0f);
        pathBuilderA.curveToRelative(0.0f, 2.21f, -1.79f, 4.0f, -4.0f, 4.0f);
        pathBuilderA.reflectiveCurveTo(11.0f, 10.21f, 11.0f, 8.0f);
        pathBuilderA.close();
        pathBuilderA.moveTo(18.32f, 20.0f);
        pathBuilderA.curveToRelative(-1.67f, -0.81f, -2.82f, -2.52f, -2.82f, -4.5f);
        pathBuilderA.curveToRelative(0.0f, -0.89f, 0.23f, -1.73f, 0.64f, -2.45f);
        pathBuilderA.curveTo(15.77f, 13.02f, 15.39f, 13.0f, 15.0f, 13.0f);
        pathBuilderA.curveToRelative(-2.53f, 0.0f, -4.71f, 0.7f, -6.39f, 1.56f);
        pathBuilderA.curveTo(7.61f, 15.07f, 7.0f, 16.1f, 7.0f, 17.22f);
        ImageVector imageVectorBuild = ImageVector.Builder.m4024addPathoIyEayM$default(builder, w.a.c(pathBuilderA, 20.0f, 18.32f, 20.0f), defaultFillType, "", solidColor, 1.0f, null, 1.0f, 1.0f, iM3803getButtKaPHkGw, iM3813getBevelLxFBmk8, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null).build();
        _interpreterMode = imageVectorBuild;
        return imageVectorBuild;
    }
}
