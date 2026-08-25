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
import v.c;
import v.f;
import w.a;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\"\u0010\u0010\u0000\u001a\u0004\u0018\u00010\u0001X\u0082\u000e¢\u0006\u0002\n\u0000\"\u0015\u0010\u0002\u001a\u00020\u0001*\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"_masks", "Landroidx/compose/ui/graphics/vector/ImageVector;", "Masks", "Landroidx/compose/material/icons/Icons$Outlined;", "getMasks", "(Landroidx/compose/material/icons/Icons$Outlined;)Landroidx/compose/ui/graphics/vector/ImageVector;", "material-icons-extended_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class MasksKt {
    private static ImageVector _masks;

    public static final ImageVector getMasks(Icons.Outlined outlined) {
        ImageVector imageVector = _masks;
        if (imageVector != null) {
            return imageVector;
        }
        ImageVector.Builder builder = new ImageVector.Builder("Outlined.Masks", Dp.m5678constructorimpl(24.0f), Dp.m5678constructorimpl(24.0f), 24.0f, 24.0f, 0L, 0, false, 96, null);
        int defaultFillType = VectorKt.getDefaultFillType();
        SolidColor solidColor = new SolidColor(Color.INSTANCE.m3498getBlack0d7_KjU(), null);
        int iM3803getButtKaPHkGw = StrokeCap.INSTANCE.m3803getButtKaPHkGw();
        int iM3813getBevelLxFBmk8 = StrokeJoin.INSTANCE.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderA = c.a(19.5f, 6.0f);
        pathBuilderA.curveToRelative(-1.31f, 0.0f, -2.37f, 1.01f, -2.48f, 2.3f);
        pathBuilderA.curveTo(15.14f, 7.8f, 14.18f, 6.5f, 12.0f, 6.5f);
        pathBuilderA.curveToRelative(-2.19f, 0.0f, -3.14f, 1.3f, -5.02f, 1.8f);
        pathBuilderA.curveTo(6.87f, 7.02f, 5.81f, 6.0f, 4.5f, 6.0f);
        pathBuilderA.curveTo(3.12f, 6.0f, 2.0f, 7.12f, 2.0f, 8.5f);
        pathBuilderA.verticalLineTo(9.0f);
        pathBuilderA.curveToRelative(0.0f, 6.0f, 3.6f, 7.81f, 6.52f, 7.98f);
        pathBuilderA.curveTo(9.53f, 17.62f, 10.72f, 18.0f, 12.0f, 18.0f);
        pathBuilderA.reflectiveCurveToRelative(2.47f, -0.38f, 3.48f, -1.02f);
        pathBuilderA.curveTo(18.4f, 16.81f, 22.0f, 15.0f, 22.0f, 9.0f);
        pathBuilderA.verticalLineTo(8.5f);
        pathBuilderA.curveTo(22.0f, 7.12f, 20.88f, 6.0f, 19.5f, 6.0f);
        a.u(pathBuilderA, 3.5f, 9.0f, 8.5f);
        pathBuilderA.curveToRelative(0.0f, -0.55f, 0.45f, -1.0f, 1.0f, -1.0f);
        pathBuilderA.reflectiveCurveToRelative(1.0f, 0.45f, 1.0f, 1.0f);
        pathBuilderA.verticalLineToRelative(3.0f);
        pathBuilderA.curveToRelative(0.0f, 1.28f, 0.38f, 2.47f, 1.01f, 3.48f);
        pathBuilderA.curveTo(4.99f, 14.27f, 3.5f, 12.65f, 3.5f, 9.0f);
        a.u(pathBuilderA, 7.0f, 11.5f, 9.85f);
        pathBuilderA.curveToRelative(1.12f, -0.23f, 1.95f, -0.69f, 2.66f, -1.08f);
        pathBuilderA.curveTo(10.48f, 8.33f, 11.07f, 8.0f, 12.0f, 8.0f);
        pathBuilderA.curveToRelative(0.93f, 0.0f, 1.52f, 0.33f, 2.34f, 0.78f);
        pathBuilderA.curveToRelative(0.71f, 0.39f, 1.54f, 0.84f, 2.66f, 1.08f);
        pathBuilderA.verticalLineToRelative(1.65f);
        pathBuilderA.curveToRelative(0.0f, 2.76f, -2.24f, 5.0f, -5.0f, 5.0f);
        pathBuilderA.reflectiveCurveTo(7.0f, 14.26f, 7.0f, 11.5f);
        pathBuilderA.close();
        pathBuilderA.moveTo(20.5f, 9.0f);
        pathBuilderA.curveToRelative(0.0f, 3.65f, -1.49f, 5.27f, -3.01f, 5.98f);
        pathBuilderA.curveToRelative(0.64f, -1.01f, 1.01f, -2.2f, 1.01f, -3.48f);
        pathBuilderA.verticalLineToRelative(-3.0f);
        pathBuilderA.curveToRelative(0.0f, -0.55f, 0.45f, -1.0f, 1.0f, -1.0f);
        pathBuilderA.reflectiveCurveToRelative(1.0f, 0.45f, 1.0f, 1.0f);
        f.r(pathBuilderA, 9.0f, 10.69f, 10.48f);
        pathBuilderA.curveToRelative(-0.44f, 0.26f, -0.96f, 0.56f, -1.69f, 0.76f);
        pathBuilderA.verticalLineTo(10.2f);
        pathBuilderA.curveToRelative(0.48f, -0.17f, 0.84f, -0.38f, 1.18f, -0.58f);
        pathBuilderA.curveTo(10.72f, 9.3f, 11.23f, 9.0f, 12.0f, 9.0f);
        pathBuilderA.reflectiveCurveToRelative(1.27f, 0.3f, 1.8f, 0.62f);
        pathBuilderA.curveToRelative(0.34f, 0.2f, 0.71f, 0.42f, 1.2f, 0.59f);
        pathBuilderA.verticalLineToRelative(1.04f);
        pathBuilderA.curveToRelative(-0.75f, -0.21f, -1.26f, -0.51f, -1.71f, -0.78f);
        pathBuilderA.curveTo(12.83f, 10.2f, 12.49f, 10.0f, 12.0f, 10.0f);
        pathBuilderA.curveTo(11.51f, 10.0f, 11.16f, 10.2f, 10.69f, 10.48f);
        pathBuilderA.close();
        ImageVector imageVectorBuild = ImageVector.Builder.m4024addPathoIyEayM$default(builder, pathBuilderA.getNodes(), defaultFillType, "", solidColor, 1.0f, null, 1.0f, 1.0f, iM3803getButtKaPHkGw, iM3813getBevelLxFBmk8, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null).build();
        _masks = imageVectorBuild;
        return imageVectorBuild;
    }
}
