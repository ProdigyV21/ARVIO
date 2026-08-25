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
import v.b;
import v.c;
import w.a;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\"\u0010\u0010\u0000\u001a\u0004\u0018\u00010\u0001X\u0082\u000e¢\u0006\u0002\n\u0000\"\u0015\u0010\u0002\u001a\u00020\u0001*\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"_masks", "Landroidx/compose/ui/graphics/vector/ImageVector;", "Masks", "Landroidx/compose/material/icons/Icons$Rounded;", "getMasks", "(Landroidx/compose/material/icons/Icons$Rounded;)Landroidx/compose/ui/graphics/vector/ImageVector;", "material-icons-extended_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class MasksKt {
    private static ImageVector _masks;

    public static final ImageVector getMasks(Icons.Rounded rounded) {
        ImageVector imageVector = _masks;
        if (imageVector != null) {
            return imageVector;
        }
        ImageVector.Builder builder = new ImageVector.Builder("Rounded.Masks", Dp.m5678constructorimpl(24.0f), Dp.m5678constructorimpl(24.0f), 24.0f, 24.0f, 0L, 0, false, 96, null);
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
        pathBuilderA.close();
        pathBuilderA.moveTo(14.3f, 11.01f);
        pathBuilderA.curveToRelative(-0.4f, -0.17f, -0.72f, -0.36f, -1.01f, -0.53f);
        pathBuilderA.curveTo(12.83f, 10.2f, 12.49f, 10.0f, 12.0f, 10.0f);
        pathBuilderA.curveToRelative(-0.49f, 0.0f, -0.84f, 0.2f, -1.31f, 0.48f);
        pathBuilderA.curveToRelative(-0.28f, 0.17f, -0.6f, 0.35f, -0.98f, 0.51f);
        pathBuilderA.curveTo(9.37f, 11.14f, 9.0f, 10.91f, 9.0f, 10.54f);
        pathBuilderA.verticalLineToRelative(0.0f);
        pathBuilderA.curveToRelative(0.0f, -0.2f, 0.11f, -0.38f, 0.29f, -0.45f);
        pathBuilderA.curveToRelative(0.34f, -0.14f, 0.62f, -0.31f, 0.88f, -0.46f);
        pathBuilderA.curveTo(10.72f, 9.3f, 11.23f, 9.0f, 12.0f, 9.0f);
        pathBuilderA.reflectiveCurveToRelative(1.27f, 0.3f, 1.8f, 0.62f);
        pathBuilderA.curveToRelative(0.27f, 0.16f, 0.55f, 0.33f, 0.9f, 0.48f);
        pathBuilderA.curveToRelative(0.18f, 0.08f, 0.29f, 0.26f, 0.29f, 0.45f);
        pathBuilderA.curveTo(15.0f, 10.91f, 14.63f, 11.15f, 14.3f, 11.01f);
        pathBuilderA.close();
        pathBuilderA.moveTo(20.5f, 9.0f);
        pathBuilderA.curveToRelative(0.0f, 3.65f, -1.49f, 5.27f, -3.01f, 5.98f);
        pathBuilderA.curveToRelative(0.64f, -1.01f, 1.01f, -2.2f, 1.01f, -3.48f);
        pathBuilderA.verticalLineToRelative(-3.0f);
        pathBuilderA.curveToRelative(0.0f, -0.55f, 0.45f, -1.0f, 1.0f, -1.0f);
        pathBuilderA.reflectiveCurveToRelative(1.0f, 0.45f, 1.0f, 1.0f);
        ImageVector imageVectorBuild = ImageVector.Builder.m4024addPathoIyEayM$default(builder, b.i(pathBuilderA, 9.0f), defaultFillType, "", solidColor, 1.0f, null, 1.0f, 1.0f, iM3803getButtKaPHkGw, iM3813getBevelLxFBmk8, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null).build();
        _masks = imageVectorBuild;
        return imageVectorBuild;
    }
}
