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

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\"\u0010\u0010\u0000\u001a\u0004\u0018\u00010\u0001X\u0082\u000e¢\u0006\u0002\n\u0000\"\u0015\u0010\u0002\u001a\u00020\u0001*\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"_frontHand", "Landroidx/compose/ui/graphics/vector/ImageVector;", "FrontHand", "Landroidx/compose/material/icons/Icons$Rounded;", "getFrontHand", "(Landroidx/compose/material/icons/Icons$Rounded;)Landroidx/compose/ui/graphics/vector/ImageVector;", "material-icons-extended_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class FrontHandKt {
    private static ImageVector _frontHand;

    public static final ImageVector getFrontHand(Icons.Rounded rounded) {
        ImageVector imageVector = _frontHand;
        if (imageVector != null) {
            return imageVector;
        }
        ImageVector.Builder builder = new ImageVector.Builder("Rounded.FrontHand", Dp.m5678constructorimpl(24.0f), Dp.m5678constructorimpl(24.0f), 24.0f, 24.0f, 0L, 0, false, 96, null);
        int defaultFillType = VectorKt.getDefaultFillType();
        SolidColor solidColor = new SolidColor(Color.INSTANCE.m3498getBlack0d7_KjU(), null);
        int iM3803getButtKaPHkGw = StrokeCap.INSTANCE.m3803getButtKaPHkGw();
        int iM3813getBevelLxFBmk8 = StrokeJoin.INSTANCE.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderA = c.a(18.5f, 14.5f);
        pathBuilderA.curveToRelative(0.0f, 0.28f, -0.22f, 0.5f, -0.5f, 0.5f);
        pathBuilderA.lineToRelative(0.0f, 0.0f);
        pathBuilderA.curveToRelative(-1.51f, 0.0f, -2.77f, 1.12f, -2.97f, 2.58f);
        pathBuilderA.curveTo(15.0f, 17.82f, 14.78f, 18.0f, 14.54f, 18.0f);
        pathBuilderA.curveToRelative(-0.3f, 0.0f, -0.54f, -0.27f, -0.5f, -0.57f);
        pathBuilderA.curveToRelative(0.26f, -1.77f, 1.68f, -3.17f, 3.46f, -3.4f);
        pathBuilderA.lineToRelative(0.0f, -10.78f);
        pathBuilderA.curveTo(17.5f, 2.56f, 16.94f, 2.0f, 16.25f, 2.0f);
        pathBuilderA.curveTo(15.56f, 2.0f, 15.0f, 2.56f, 15.0f, 3.25f);
        pathBuilderA.verticalLineToRelative(7.25f);
        pathBuilderA.curveToRelative(0.0f, 0.28f, -0.22f, 0.5f, -0.5f, 0.5f);
        pathBuilderA.reflectiveCurveTo(14.0f, 10.78f, 14.0f, 10.5f);
        pathBuilderA.verticalLineTo(1.25f);
        pathBuilderA.curveTo(14.0f, 0.56f, 13.44f, 0.0f, 12.75f, 0.0f);
        pathBuilderA.reflectiveCurveTo(11.5f, 0.56f, 11.5f, 1.25f);
        pathBuilderA.verticalLineToRelative(9.25f);
        pathBuilderA.curveToRelative(0.0f, 0.28f, -0.22f, 0.5f, -0.5f, 0.5f);
        pathBuilderA.reflectiveCurveToRelative(-0.5f, -0.22f, -0.5f, -0.5f);
        pathBuilderA.verticalLineTo(2.75f);
        pathBuilderA.curveToRelative(0.0f, -0.69f, -0.56f, -1.25f, -1.25f, -1.25f);
        pathBuilderA.reflectiveCurveTo(8.0f, 2.06f, 8.0f, 2.75f);
        pathBuilderA.verticalLineToRelative(8.75f);
        pathBuilderA.curveTo(8.0f, 11.78f, 7.78f, 12.0f, 7.5f, 12.0f);
        pathBuilderA.reflectiveCurveTo(7.0f, 11.78f, 7.0f, 11.5f);
        pathBuilderA.verticalLineTo(5.75f);
        pathBuilderA.curveTo(7.0f, 5.06f, 6.44f, 4.5f, 5.75f, 4.5f);
        pathBuilderA.reflectiveCurveTo(4.5f, 5.06f, 4.5f, 5.75f);
        pathBuilderA.verticalLineToRelative(10.0f);
        pathBuilderA.curveToRelative(0.0f, 4.56f, 3.69f, 8.25f, 8.25f, 8.25f);
        pathBuilderA.reflectiveCurveTo(21.0f, 20.31f, 21.0f, 15.75f);
        pathBuilderA.verticalLineToRelative(-6.5f);
        pathBuilderA.curveTo(21.0f, 8.56f, 20.44f, 8.0f, 19.75f, 8.0f);
        pathBuilderA.reflectiveCurveTo(18.5f, 8.56f, 18.5f, 9.25f);
        ImageVector imageVectorBuild = ImageVector.Builder.m4024addPathoIyEayM$default(builder, b.i(pathBuilderA, 14.5f), defaultFillType, "", solidColor, 1.0f, null, 1.0f, 1.0f, iM3803getButtKaPHkGw, iM3813getBevelLxFBmk8, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null).build();
        _frontHand = imageVectorBuild;
        return imageVectorBuild;
    }
}
