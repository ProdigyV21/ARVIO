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
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\"\u0010\u0010\u0000\u001a\u0004\u0018\u00010\u0001X\u0082\u000e¢\u0006\u0002\n\u0000\"\u0015\u0010\u0002\u001a\u00020\u0001*\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"_backHand", "Landroidx/compose/ui/graphics/vector/ImageVector;", "BackHand", "Landroidx/compose/material/icons/Icons$Rounded;", "getBackHand", "(Landroidx/compose/material/icons/Icons$Rounded;)Landroidx/compose/ui/graphics/vector/ImageVector;", "material-icons-extended_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class BackHandKt {
    private static ImageVector _backHand;

    public static final ImageVector getBackHand(Icons.Rounded rounded) {
        ImageVector imageVector = _backHand;
        if (imageVector != null) {
            return imageVector;
        }
        ImageVector.Builder builder = new ImageVector.Builder("Rounded.BackHand", Dp.m5678constructorimpl(24.0f), Dp.m5678constructorimpl(24.0f), 24.0f, 24.0f, 0L, 0, false, 96, null);
        int defaultFillType = VectorKt.getDefaultFillType();
        SolidColor solidColor = new SolidColor(Color.INSTANCE.m3498getBlack0d7_KjU(), null);
        int iM3803getButtKaPHkGw = StrokeCap.INSTANCE.m3803getButtKaPHkGw();
        int iM3813getBevelLxFBmk8 = StrokeJoin.INSTANCE.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderA = c.a(21.0f, 16.0f);
        pathBuilderA.curveToRelative(0.0f, 4.42f, -3.58f, 8.0f, -8.0f, 8.0f);
        pathBuilderA.curveToRelative(-3.26f, 0.0f, -6.19f, -1.99f, -7.4f, -5.02f);
        pathBuilderA.lineToRelative(-3.03f, -7.61f);
        pathBuilderA.curveTo(2.26f, 10.58f, 3.0f, 9.79f, 3.81f, 10.05f);
        pathBuilderA.lineToRelative(0.79f, 0.26f);
        pathBuilderA.curveToRelative(0.56f, 0.18f, 1.02f, 0.61f, 1.24f, 1.16f);
        pathBuilderA.lineToRelative(1.28f, 3.21f);
        pathBuilderA.curveTo(7.2f, 14.88f, 7.38f, 15.0f, 7.58f, 15.0f);
        pathBuilderA.horizontalLineTo(8.0f);
        pathBuilderA.verticalLineTo(3.25f);
        pathBuilderA.curveTo(8.0f, 2.56f, 8.56f, 2.0f, 9.25f, 2.0f);
        pathBuilderA.reflectiveCurveToRelative(1.25f, 0.56f, 1.25f, 1.25f);
        pathBuilderA.verticalLineToRelative(8.25f);
        pathBuilderA.curveToRelative(0.0f, 0.28f, 0.22f, 0.5f, 0.5f, 0.5f);
        pathBuilderA.lineToRelative(0.0f, 0.0f);
        pathBuilderA.curveToRelative(0.28f, 0.0f, 0.5f, -0.22f, 0.5f, -0.5f);
        pathBuilderA.verticalLineTo(1.25f);
        pathBuilderA.curveTo(11.5f, 0.56f, 12.06f, 0.0f, 12.75f, 0.0f);
        pathBuilderA.reflectiveCurveTo(14.0f, 0.56f, 14.0f, 1.25f);
        pathBuilderA.verticalLineTo(11.5f);
        pathBuilderA.curveToRelative(0.0f, 0.28f, 0.22f, 0.5f, 0.5f, 0.5f);
        pathBuilderA.lineToRelative(0.0f, 0.0f);
        pathBuilderA.curveToRelative(0.28f, 0.0f, 0.5f, -0.22f, 0.5f, -0.5f);
        pathBuilderA.verticalLineTo(2.75f);
        pathBuilderA.curveToRelative(0.0f, -0.69f, 0.56f, -1.25f, 1.25f, -1.25f);
        pathBuilderA.curveToRelative(0.69f, 0.0f, 1.25f, 0.56f, 1.25f, 1.25f);
        pathBuilderA.verticalLineToRelative(8.75f);
        pathBuilderA.curveToRelative(0.0f, 0.28f, 0.22f, 0.5f, 0.5f, 0.5f);
        pathBuilderA.lineToRelative(0.0f, 0.0f);
        pathBuilderA.curveToRelative(0.28f, 0.0f, 0.5f, -0.22f, 0.5f, -0.5f);
        pathBuilderA.verticalLineTo(5.75f);
        pathBuilderA.curveToRelative(0.0f, -0.69f, 0.56f, -1.25f, 1.25f, -1.25f);
        pathBuilderA.reflectiveCurveTo(21.0f, 5.06f, 21.0f, 5.75f);
        ImageVector imageVectorBuild = ImageVector.Builder.m4024addPathoIyEayM$default(builder, b.i(pathBuilderA, 16.0f), defaultFillType, "", solidColor, 1.0f, null, 1.0f, 1.0f, iM3803getButtKaPHkGw, iM3813getBevelLxFBmk8, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null).build();
        _backHand = imageVectorBuild;
        return imageVectorBuild;
    }
}
