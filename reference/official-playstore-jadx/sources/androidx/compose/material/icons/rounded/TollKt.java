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
import v.c;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\"\u0010\u0010\u0000\u001a\u0004\u0018\u00010\u0001X\u0082\u000e¢\u0006\u0002\n\u0000\"\u0015\u0010\u0002\u001a\u00020\u0001*\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"_toll", "Landroidx/compose/ui/graphics/vector/ImageVector;", "Toll", "Landroidx/compose/material/icons/Icons$Rounded;", "getToll", "(Landroidx/compose/material/icons/Icons$Rounded;)Landroidx/compose/ui/graphics/vector/ImageVector;", "material-icons-extended_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class TollKt {
    private static ImageVector _toll;

    public static final ImageVector getToll(Icons.Rounded rounded) {
        ImageVector imageVector = _toll;
        if (imageVector != null) {
            return imageVector;
        }
        ImageVector.Builder builder = new ImageVector.Builder("Rounded.Toll", Dp.m5678constructorimpl(24.0f), Dp.m5678constructorimpl(24.0f), 24.0f, 24.0f, 0L, 0, false, 96, null);
        int defaultFillType = VectorKt.getDefaultFillType();
        SolidColor solidColor = new SolidColor(Color.INSTANCE.m3498getBlack0d7_KjU(), null);
        int iM3803getButtKaPHkGw = StrokeCap.INSTANCE.m3803getButtKaPHkGw();
        int iM3813getBevelLxFBmk8 = StrokeJoin.INSTANCE.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderA = c.a(15.0f, 4.0f);
        pathBuilderA.curveToRelative(-4.42f, 0.0f, -8.0f, 3.58f, -8.0f, 8.0f);
        pathBuilderA.reflectiveCurveToRelative(3.58f, 8.0f, 8.0f, 8.0f);
        pathBuilderA.reflectiveCurveToRelative(8.0f, -3.58f, 8.0f, -8.0f);
        pathBuilderA.reflectiveCurveToRelative(-3.58f, -8.0f, -8.0f, -8.0f);
        pathBuilderA.close();
        pathBuilderA.moveTo(15.0f, 18.0f);
        pathBuilderA.curveToRelative(-3.31f, 0.0f, -6.0f, -2.69f, -6.0f, -6.0f);
        pathBuilderA.reflectiveCurveToRelative(2.69f, -6.0f, 6.0f, -6.0f);
        pathBuilderA.reflectiveCurveToRelative(6.0f, 2.69f, 6.0f, 6.0f);
        pathBuilderA.reflectiveCurveToRelative(-2.69f, 6.0f, -6.0f, 6.0f);
        pathBuilderA.close();
        pathBuilderA.moveTo(3.0f, 12.0f);
        pathBuilderA.curveToRelative(0.0f, -2.39f, 1.4f, -4.46f, 3.43f, -5.42f);
        pathBuilderA.curveToRelative(0.34f, -0.16f, 0.57f, -0.47f, 0.57f, -0.84f);
        pathBuilderA.verticalLineToRelative(-0.19f);
        pathBuilderA.curveToRelative(0.0f, -0.68f, -0.71f, -1.11f, -1.32f, -0.82f);
        pathBuilderA.curveTo(2.92f, 5.99f, 1.0f, 8.77f, 1.0f, 12.0f);
        pathBuilderA.reflectiveCurveToRelative(1.92f, 6.01f, 4.68f, 7.27f);
        pathBuilderA.curveToRelative(0.61f, 0.28f, 1.32f, -0.14f, 1.32f, -0.82f);
        pathBuilderA.verticalLineToRelative(-0.18f);
        pathBuilderA.curveToRelative(0.0f, -0.37f, -0.23f, -0.69f, -0.57f, -0.85f);
        pathBuilderA.curveTo(4.4f, 16.46f, 3.0f, 14.39f, 3.0f, 12.0f);
        pathBuilderA.close();
        ImageVector imageVectorBuild = ImageVector.Builder.m4024addPathoIyEayM$default(builder, pathBuilderA.getNodes(), defaultFillType, "", solidColor, 1.0f, null, 1.0f, 1.0f, iM3803getButtKaPHkGw, iM3813getBevelLxFBmk8, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null).build();
        _toll = imageVectorBuild;
        return imageVectorBuild;
    }
}
