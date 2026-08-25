package androidx.compose.material.icons.rounded;

import a0.b;
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

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\"\u0010\u0010\u0000\u001a\u0004\u0018\u00010\u0001X\u0082\u000e¢\u0006\u0002\n\u0000\"\u0015\u0010\u0002\u001a\u00020\u0001*\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"_umbrella", "Landroidx/compose/ui/graphics/vector/ImageVector;", "Umbrella", "Landroidx/compose/material/icons/Icons$Rounded;", "getUmbrella", "(Landroidx/compose/material/icons/Icons$Rounded;)Landroidx/compose/ui/graphics/vector/ImageVector;", "material-icons-extended_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class UmbrellaKt {
    private static ImageVector _umbrella;

    public static final ImageVector getUmbrella(Icons.Rounded rounded) {
        ImageVector imageVector = _umbrella;
        if (imageVector != null) {
            return imageVector;
        }
        ImageVector.Builder builder = new ImageVector.Builder("Rounded.Umbrella", Dp.m5678constructorimpl(24.0f), Dp.m5678constructorimpl(24.0f), 24.0f, 24.0f, 0L, 0, false, 96, null);
        int defaultFillType = VectorKt.getDefaultFillType();
        SolidColor solidColor = new SolidColor(Color.INSTANCE.m3498getBlack0d7_KjU(), null);
        int iM3803getButtKaPHkGw = StrokeCap.INSTANCE.m3803getButtKaPHkGw();
        int iM3813getBevelLxFBmk8 = StrokeJoin.INSTANCE.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderJ = a.j(17.12f, 6.28f, 14.5f, 6.92f);
        pathBuilderJ.lineTo(13.0f, 5.77f);
        pathBuilderJ.verticalLineTo(3.88f);
        pathBuilderJ.verticalLineTo(3.4f);
        pathBuilderJ.curveToRelative(0.0f, -0.26f, 0.22f, -0.48f, 0.5f, -0.48f);
        pathBuilderJ.curveToRelative(0.23f, 0.0f, 0.43f, 0.16f, 0.49f, 0.36f);
        pathBuilderJ.curveTo(14.1f, 3.7f, 14.49f, 4.0f, 14.94f, 4.0f);
        pathBuilderJ.curveToRelative(0.55f, 0.0f, 1.0f, -0.45f, 1.0f, -1.0f);
        pathBuilderJ.curveToRelative(0.0f, -0.1f, -0.02f, -0.2f, -0.05f, -0.3f);
        pathBuilderJ.curveTo(15.59f, 1.72f, 14.63f, 1.0f, 13.5f, 1.0f);
        pathBuilderJ.curveTo(12.12f, 1.0f, 11.0f, 2.07f, 11.0f, 3.4f);
        pathBuilderJ.verticalLineToRelative(0.48f);
        pathBuilderJ.verticalLineToRelative(1.89f);
        pathBuilderJ.lineTo(9.5f, 6.92f);
        pathBuilderJ.lineTo(6.88f, 6.28f);
        pathBuilderJ.curveTo(6.5f, 6.19f, 6.16f, 6.55f, 6.28f, 6.92f);
        pathBuilderJ.lineToRelative(4.77f, 14.39f);
        pathBuilderJ.curveTo(11.2f, 21.77f, 11.6f, 22.0f, 12.0f, 22.0f);
        pathBuilderJ.reflectiveCurveToRelative(0.8f, -0.23f, 0.95f, -0.69f);
        pathBuilderJ.lineToRelative(4.77f, -14.39f);
        pathBuilderJ.curveTo(17.84f, 6.55f, 17.5f, 6.19f, 17.12f, 6.28f);
        a.p(pathBuilderJ, 11.0f, 14.8f, 9.03f, 8.86f);
        pathBuilderJ.lineToRelative(0.92f, 0.23f);
        pathBuilderJ.lineToRelative(0.76f, -0.58f);
        b.s(pathBuilderJ, 11.0f, 8.29f, 14.8f);
        pathBuilderJ.moveTo(13.0f, 14.8f);
        pathBuilderJ.verticalLineTo(8.29f);
        pathBuilderJ.lineToRelative(0.28f, 0.22f);
        pathBuilderJ.lineToRelative(0.76f, 0.58f);
        ImageVector imageVectorBuild = ImageVector.Builder.m4024addPathoIyEayM$default(builder, v.b.e(pathBuilderJ, 0.92f, -0.23f, 13.0f, 14.8f), defaultFillType, "", solidColor, 1.0f, null, 1.0f, 1.0f, iM3803getButtKaPHkGw, iM3813getBevelLxFBmk8, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null).build();
        _umbrella = imageVectorBuild;
        return imageVectorBuild;
    }
}
