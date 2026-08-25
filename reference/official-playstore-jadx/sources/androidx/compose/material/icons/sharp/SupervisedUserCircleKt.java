package androidx.compose.material.icons.sharp;

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
import v.d;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\"\u0010\u0010\u0000\u001a\u0004\u0018\u00010\u0001X\u0082\u000e¢\u0006\u0002\n\u0000\"\u0015\u0010\u0002\u001a\u00020\u0001*\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"_supervisedUserCircle", "Landroidx/compose/ui/graphics/vector/ImageVector;", "SupervisedUserCircle", "Landroidx/compose/material/icons/Icons$Sharp;", "getSupervisedUserCircle", "(Landroidx/compose/material/icons/Icons$Sharp;)Landroidx/compose/ui/graphics/vector/ImageVector;", "material-icons-extended_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class SupervisedUserCircleKt {
    private static ImageVector _supervisedUserCircle;

    public static final ImageVector getSupervisedUserCircle(Icons.Sharp sharp) {
        ImageVector imageVector = _supervisedUserCircle;
        if (imageVector != null) {
            return imageVector;
        }
        ImageVector.Builder builder = new ImageVector.Builder("Sharp.SupervisedUserCircle", Dp.m5678constructorimpl(24.0f), Dp.m5678constructorimpl(24.0f), 24.0f, 24.0f, 0L, 0, false, 96, null);
        int defaultFillType = VectorKt.getDefaultFillType();
        SolidColor solidColor = new SolidColor(Color.INSTANCE.m3498getBlack0d7_KjU(), null);
        int iM3803getButtKaPHkGw = StrokeCap.INSTANCE.m3803getButtKaPHkGw();
        int iM3813getBevelLxFBmk8 = StrokeJoin.INSTANCE.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderA = c.a(12.0f, 2.0f);
        pathBuilderA.curveTo(6.48f, 2.0f, 2.0f, 6.48f, 2.0f, 12.0f);
        pathBuilderA.reflectiveCurveToRelative(4.48f, 10.0f, 10.0f, 10.0f);
        pathBuilderA.reflectiveCurveToRelative(10.0f, -4.48f, 10.0f, -10.0f);
        pathBuilderA.reflectiveCurveTo(17.52f, 2.0f, 12.0f, 2.0f);
        pathBuilderA.close();
        pathBuilderA.moveTo(15.61f, 8.34f);
        pathBuilderA.curveToRelative(1.07f, 0.0f, 1.93f, 0.86f, 1.93f, 1.93f);
        pathBuilderA.reflectiveCurveToRelative(-0.86f, 1.93f, -1.93f, 1.93f);
        pathBuilderA.reflectiveCurveToRelative(-1.93f, -0.86f, -1.93f, -1.93f);
        pathBuilderA.curveToRelative(-0.01f, -1.07f, 0.86f, -1.93f, 1.93f, -1.93f);
        pathBuilderA.close();
        pathBuilderA.moveTo(9.61f, 6.76f);
        pathBuilderA.curveToRelative(1.3f, 0.0f, 2.36f, 1.06f, 2.36f, 2.36f);
        pathBuilderA.reflectiveCurveToRelative(-1.06f, 2.36f, -2.36f, 2.36f);
        pathBuilderA.reflectiveCurveToRelative(-2.36f, -1.06f, -2.36f, -2.36f);
        pathBuilderA.curveToRelative(0.0f, -1.31f, 1.05f, -2.36f, 2.36f, -2.36f);
        d.k(pathBuilderA, 9.61f, 15.89f, 3.75f);
        pathBuilderA.curveToRelative(-2.4f, -0.75f, -4.3f, -2.6f, -5.14f, -4.96f);
        pathBuilderA.curveToRelative(1.05f, -1.12f, 3.67f, -1.69f, 5.14f, -1.69f);
        pathBuilderA.curveToRelative(0.53f, 0.0f, 1.2f, 0.08f, 1.9f, 0.22f);
        pathBuilderA.curveToRelative(-1.64f, 0.87f, -1.9f, 2.02f, -1.9f, 2.68f);
        pathBuilderA.close();
        pathBuilderA.moveTo(12.0f, 20.0f);
        pathBuilderA.curveToRelative(-0.27f, 0.0f, -0.53f, -0.01f, -0.79f, -0.04f);
        pathBuilderA.verticalLineToRelative(-4.07f);
        pathBuilderA.curveToRelative(0.0f, -1.42f, 2.94f, -2.13f, 4.4f, -2.13f);
        pathBuilderA.curveToRelative(1.07f, 0.0f, 2.92f, 0.39f, 3.84f, 1.15f);
        pathBuilderA.curveTo(18.28f, 17.88f, 15.39f, 20.0f, 12.0f, 20.0f);
        pathBuilderA.close();
        ImageVector imageVectorBuild = ImageVector.Builder.m4024addPathoIyEayM$default(builder, pathBuilderA.getNodes(), defaultFillType, "", solidColor, 1.0f, null, 1.0f, 1.0f, iM3803getButtKaPHkGw, iM3813getBevelLxFBmk8, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null).build();
        _supervisedUserCircle = imageVectorBuild;
        return imageVectorBuild;
    }
}
