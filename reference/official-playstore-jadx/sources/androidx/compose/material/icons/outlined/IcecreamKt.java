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
import v.c;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\"\u0010\u0010\u0000\u001a\u0004\u0018\u00010\u0001X\u0082\u000e¢\u0006\u0002\n\u0000\"\u0015\u0010\u0002\u001a\u00020\u0001*\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"_icecream", "Landroidx/compose/ui/graphics/vector/ImageVector;", "Icecream", "Landroidx/compose/material/icons/Icons$Outlined;", "getIcecream", "(Landroidx/compose/material/icons/Icons$Outlined;)Landroidx/compose/ui/graphics/vector/ImageVector;", "material-icons-extended_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class IcecreamKt {
    private static ImageVector _icecream;

    public static final ImageVector getIcecream(Icons.Outlined outlined) {
        ImageVector imageVector = _icecream;
        if (imageVector != null) {
            return imageVector;
        }
        ImageVector.Builder builder = new ImageVector.Builder("Outlined.Icecream", Dp.m5678constructorimpl(24.0f), Dp.m5678constructorimpl(24.0f), 24.0f, 24.0f, 0L, 0, false, 96, null);
        int defaultFillType = VectorKt.getDefaultFillType();
        SolidColor solidColor = new SolidColor(Color.INSTANCE.m3498getBlack0d7_KjU(), null);
        int iM3803getButtKaPHkGw = StrokeCap.INSTANCE.m3803getButtKaPHkGw();
        int iM3813getBevelLxFBmk8 = StrokeJoin.INSTANCE.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderA = c.a(18.38f, 6.24f);
        pathBuilderA.curveTo(17.79f, 3.24f, 15.14f, 1.0f, 12.0f, 1.0f);
        pathBuilderA.reflectiveCurveTo(6.21f, 3.24f, 5.62f, 6.24f);
        pathBuilderA.curveTo(4.08f, 6.81f, 3.0f, 8.29f, 3.0f, 10.0f);
        pathBuilderA.curveToRelative(0.0f, 2.21f, 1.79f, 4.0f, 4.0f, 4.0f);
        pathBuilderA.curveToRelative(0.12f, 0.0f, 0.23f, -0.02f, 0.34f, -0.02f);
        pathBuilderA.lineTo(12.07f, 23.0f);
        pathBuilderA.lineToRelative(4.61f, -9.03f);
        pathBuilderA.curveTo(16.79f, 13.98f, 16.89f, 14.0f, 17.0f, 14.0f);
        pathBuilderA.curveToRelative(2.21f, 0.0f, 4.0f, -1.79f, 4.0f, -4.0f);
        pathBuilderA.curveTo(21.0f, 8.29f, 19.92f, 6.81f, 18.38f, 6.24f);
        a.z(pathBuilderA, 12.05f, 18.63f, -2.73f, -5.21f);
        pathBuilderA.curveTo(10.15f, 13.79f, 11.06f, 14.0f, 12.0f, 14.0f);
        pathBuilderA.curveToRelative(0.95f, 0.0f, 1.88f, -0.21f, 2.72f, -0.6f);
        c.z(pathBuilderA, 12.05f, 18.63f, 17.0f, 12.0f);
        pathBuilderA.curveToRelative(-0.52f, 0.0f, -1.01f, -0.2f, -1.39f, -0.56f);
        pathBuilderA.lineToRelative(-0.56f, -0.54f);
        pathBuilderA.lineToRelative(-0.66f, 0.42f);
        pathBuilderA.curveTo(13.68f, 11.76f, 12.86f, 12.0f, 12.0f, 12.0f);
        pathBuilderA.reflectiveCurveToRelative(-1.68f, -0.24f, -2.39f, -0.69f);
        pathBuilderA.lineTo(8.95f, 10.9f);
        pathBuilderA.lineToRelative(-0.56f, 0.54f);
        pathBuilderA.curveTo(8.01f, 11.79f, 7.52f, 12.0f, 7.0f, 12.0f);
        pathBuilderA.curveToRelative(-1.1f, 0.0f, -2.0f, -0.89f, -2.0f, -2.0f);
        pathBuilderA.curveToRelative(0.0f, -0.98f, 0.72f, -1.82f, 1.68f, -1.97f);
        pathBuilderA.lineTo(7.45f, 7.9f);
        pathBuilderA.lineToRelative(0.06f, -0.78f);
        pathBuilderA.curveTo(7.71f, 4.8f, 9.66f, 3.0f, 12.0f, 3.0f);
        pathBuilderA.reflectiveCurveToRelative(4.29f, 1.8f, 4.48f, 4.12f);
        pathBuilderA.lineToRelative(0.06f, 0.78f);
        pathBuilderA.lineToRelative(0.77f, 0.12f);
        pathBuilderA.curveTo(18.28f, 8.18f, 19.0f, 9.01f, 19.0f, 10.0f);
        pathBuilderA.curveTo(19.0f, 11.1f, 18.1f, 12.0f, 17.0f, 12.0f);
        pathBuilderA.close();
        ImageVector imageVectorBuild = ImageVector.Builder.m4024addPathoIyEayM$default(builder, pathBuilderA.getNodes(), defaultFillType, "", solidColor, 1.0f, null, 1.0f, 1.0f, iM3803getButtKaPHkGw, iM3813getBevelLxFBmk8, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null).build();
        _icecream = imageVectorBuild;
        return imageVectorBuild;
    }
}
