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
import v.b;
import v.c;
import v.d;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\"\u0010\u0010\u0000\u001a\u0004\u0018\u00010\u0001X\u0082\u000e¢\u0006\u0002\n\u0000\"\u0015\u0010\u0002\u001a\u00020\u0001*\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"_paragliding", "Landroidx/compose/ui/graphics/vector/ImageVector;", "Paragliding", "Landroidx/compose/material/icons/Icons$Rounded;", "getParagliding", "(Landroidx/compose/material/icons/Icons$Rounded;)Landroidx/compose/ui/graphics/vector/ImageVector;", "material-icons-extended_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class ParaglidingKt {
    private static ImageVector _paragliding;

    public static final ImageVector getParagliding(Icons.Rounded rounded) {
        ImageVector imageVector = _paragliding;
        if (imageVector != null) {
            return imageVector;
        }
        ImageVector.Builder builder = new ImageVector.Builder("Rounded.Paragliding", Dp.m5678constructorimpl(24.0f), Dp.m5678constructorimpl(24.0f), 24.0f, 24.0f, 0L, 0, false, 96, null);
        int defaultFillType = VectorKt.getDefaultFillType();
        SolidColor solidColor = new SolidColor(Color.INSTANCE.m3498getBlack0d7_KjU(), null);
        int iM3803getButtKaPHkGw = StrokeCap.INSTANCE.m3803getButtKaPHkGw();
        int iM3813getBevelLxFBmk8 = StrokeJoin.INSTANCE.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderA = c.a(12.0f, 17.0f);
        pathBuilderA.curveToRelative(-1.1f, 0.0f, -2.0f, -0.9f, -2.0f, -2.0f);
        pathBuilderA.reflectiveCurveToRelative(0.9f, -2.0f, 2.0f, -2.0f);
        pathBuilderA.reflectiveCurveToRelative(2.0f, 0.9f, 2.0f, 2.0f);
        pathBuilderA.reflectiveCurveTo(13.1f, 17.0f, 12.0f, 17.0f);
        pathBuilderA.close();
        pathBuilderA.moveTo(17.95f, 14.0f);
        pathBuilderA.curveToRelative(-0.52f, 0.0f, -0.94f, 0.4f, -0.99f, 0.92f);
        pathBuilderA.curveToRelative(-0.2f, 2.03f, -1.05f, 2.68f, -1.48f, 3.02f);
        pathBuilderA.curveTo(14.68f, 18.54f, 14.0f, 19.0f, 12.0f, 19.0f);
        pathBuilderA.reflectiveCurveToRelative(-2.68f, -0.46f, -3.48f, -1.06f);
        pathBuilderA.curveToRelative(-0.43f, -0.34f, -1.28f, -0.99f, -1.48f, -3.02f);
        pathBuilderA.curveTo(6.99f, 14.4f, 6.57f, 14.0f, 6.05f, 14.0f);
        pathBuilderA.curveToRelative(-0.59f, 0.0f, -1.06f, 0.51f, -1.0f, 1.09f);
        pathBuilderA.curveToRelative(0.22f, 2.08f, 1.07f, 3.47f, 2.24f, 4.41f);
        pathBuilderA.curveToRelative(0.5f, 0.4f, 1.1f, 0.7f, 1.7f, 0.9f);
        b.A(pathBuilderA, 9.0f, 24.0f, 6.0f, -3.6f);
        pathBuilderA.curveToRelative(0.6f, -0.2f, 1.2f, -0.5f, 1.7f, -0.9f);
        pathBuilderA.curveToRelative(1.17f, -0.94f, 2.03f, -2.32f, 2.24f, -4.41f);
        pathBuilderA.curveTo(19.01f, 14.51f, 18.53f, 14.0f, 17.95f, 14.0f);
        pathBuilderA.close();
        pathBuilderA.moveTo(12.0f, 0.0f);
        pathBuilderA.curveTo(5.92f, 0.0f, 1.0f, 1.9f, 1.0f, 4.25f);
        pathBuilderA.verticalLineToRelative(3.49f);
        pathBuilderA.curveTo(1.0f, 8.55f, 1.88f, 9.0f, 2.56f, 8.57f);
        pathBuilderA.curveTo(2.7f, 8.48f, 2.84f, 8.39f, 3.0f, 8.31f);
        pathBuilderA.lineTo(5.0f, 13.0f);
        pathBuilderA.horizontalLineToRelative(2.0f);
        pathBuilderA.lineToRelative(1.5f, -6.28f);
        pathBuilderA.curveTo(9.6f, 6.58f, 10.78f, 6.5f, 12.0f, 6.5f);
        pathBuilderA.reflectiveCurveToRelative(2.4f, 0.08f, 3.5f, 0.22f);
        pathBuilderA.lineTo(17.0f, 13.0f);
        pathBuilderA.horizontalLineToRelative(2.0f);
        pathBuilderA.lineToRelative(2.0f, -4.69f);
        pathBuilderA.curveToRelative(0.16f, 0.09f, 0.3f, 0.17f, 0.44f, 0.26f);
        pathBuilderA.curveTo(22.12f, 9.0f, 23.0f, 8.55f, 23.0f, 7.74f);
        pathBuilderA.verticalLineTo(4.25f);
        pathBuilderA.curveTo(23.0f, 1.9f, 18.08f, 0.0f, 12.0f, 0.0f);
        a.p(pathBuilderA, 5.88f, 11.24f, 4.37f, 7.69f);
        pathBuilderA.curveToRelative(0.75f, -0.28f, 1.6f, -0.52f, 2.53f, -0.71f);
        c.z(pathBuilderA, 5.88f, 11.24f, 18.12f, 11.24f);
        pathBuilderA.lineTo(17.1f, 6.98f);
        pathBuilderA.curveToRelative(0.93f, 0.19f, 1.78f, 0.43f, 2.53f, 0.71f);
        ImageVector imageVectorBuild = ImageVector.Builder.m4024addPathoIyEayM$default(builder, d.o(pathBuilderA, 18.12f, 11.24f), defaultFillType, "", solidColor, 1.0f, null, 1.0f, 1.0f, iM3803getButtKaPHkGw, iM3813getBevelLxFBmk8, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null).build();
        _paragliding = imageVectorBuild;
        return imageVectorBuild;
    }
}
