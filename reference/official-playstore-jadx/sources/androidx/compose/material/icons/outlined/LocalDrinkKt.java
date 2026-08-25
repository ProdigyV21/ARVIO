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
import v.b;
import v.c;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\"\u0010\u0010\u0000\u001a\u0004\u0018\u00010\u0001X\u0082\u000e¢\u0006\u0002\n\u0000\"\u0015\u0010\u0002\u001a\u00020\u0001*\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"_localDrink", "Landroidx/compose/ui/graphics/vector/ImageVector;", "LocalDrink", "Landroidx/compose/material/icons/Icons$Outlined;", "getLocalDrink", "(Landroidx/compose/material/icons/Icons$Outlined;)Landroidx/compose/ui/graphics/vector/ImageVector;", "material-icons-extended_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class LocalDrinkKt {
    private static ImageVector _localDrink;

    public static final ImageVector getLocalDrink(Icons.Outlined outlined) {
        ImageVector imageVector = _localDrink;
        if (imageVector != null) {
            return imageVector;
        }
        ImageVector.Builder builder = new ImageVector.Builder("Outlined.LocalDrink", Dp.m5678constructorimpl(24.0f), Dp.m5678constructorimpl(24.0f), 24.0f, 24.0f, 0L, 0, false, 96, null);
        int defaultFillType = VectorKt.getDefaultFillType();
        SolidColor solidColor = new SolidColor(Color.INSTANCE.m3498getBlack0d7_KjU(), null);
        int iM3803getButtKaPHkGw = StrokeCap.INSTANCE.m3803getButtKaPHkGw();
        int iM3813getBevelLxFBmk8 = StrokeJoin.INSTANCE.m3813getBevelLxFBmk8();
        PathBuilder pathBuilderR = a.r(3.0f, 2.0f, 2.01f, 18.23f);
        pathBuilderR.curveTo(5.13f, 21.23f, 5.97f, 22.0f, 7.0f, 22.0f);
        pathBuilderR.horizontalLineToRelative(10.0f);
        pathBuilderR.curveToRelative(1.03f, 0.0f, 1.87f, -0.77f, 1.99f, -1.77f);
        b.y(pathBuilderR, 21.0f, 2.0f, 3.0f, 2.0f);
        pathBuilderR.moveTo(17.0f, 20.0f);
        pathBuilderR.lineToRelative(-10.0f, 0.01f);
        pathBuilderR.lineTo(5.89f, 10.0f);
        b.y(pathBuilderR, 18.1f, 10.0f, 17.0f, 20.0f);
        pathBuilderR.moveTo(18.33f, 8.0f);
        pathBuilderR.lineTo(5.67f, 8.0f);
        pathBuilderR.lineToRelative(-0.44f, -4.0f);
        c.v(pathBuilderR, 13.53f, -0.43f, 4.0f);
        pathBuilderR.moveTo(12.0f, 19.0f);
        pathBuilderR.curveToRelative(1.66f, 0.0f, 3.0f, -1.34f, 3.0f, -3.0f);
        pathBuilderR.curveToRelative(0.0f, -2.0f, -3.0f, -5.4f, -3.0f, -5.4f);
        pathBuilderR.reflectiveCurveTo(9.0f, 14.0f, 9.0f, 16.0f);
        pathBuilderR.curveToRelative(0.0f, 1.66f, 1.34f, 3.0f, 3.0f, 3.0f);
        pathBuilderR.close();
        pathBuilderR.moveTo(12.0f, 13.91f);
        pathBuilderR.curveToRelative(0.59f, 0.91f, 1.0f, 1.73f, 1.0f, 2.09f);
        pathBuilderR.curveToRelative(0.0f, 0.55f, -0.45f, 1.0f, -1.0f, 1.0f);
        pathBuilderR.reflectiveCurveToRelative(-1.0f, -0.45f, -1.0f, -1.0f);
        pathBuilderR.curveToRelative(0.0f, -0.37f, 0.41f, -1.19f, 1.0f, -2.09f);
        pathBuilderR.close();
        ImageVector imageVectorBuild = ImageVector.Builder.m4024addPathoIyEayM$default(builder, pathBuilderR.getNodes(), defaultFillType, "", solidColor, 1.0f, null, 1.0f, 1.0f, iM3803getButtKaPHkGw, iM3813getBevelLxFBmk8, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null).build();
        _localDrink = imageVectorBuild;
        return imageVectorBuild;
    }
}
